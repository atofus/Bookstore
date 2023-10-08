/*
 * TCSS 305 Assignment 1 - UW Bookstore
 */

package io;

import static res.R.UsersFile.SALT;
import static res.R.UsersFile.HASH;
import static res.R.UsersFile.USERNAME;
import static res.R.UsersFile.CAMPUS;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import res.R;

/**
 * A utility class for The shopping cart application. Includes methods that: 
 * Write new user information to the Users file (register)
 * Checks entered credentials against existing store credentials (login) 
 * 
 * 
 * @author Charles Bryan
 * @version January 2020
 */
public final class CredentialingLoader {
    
    /** The size of the salt. */
    private static final int SALT_SIZE = 16; 
    
    /**
     * A private constructor, to prevent external instantiation.
     */
    private CredentialingLoader() {
        
    }
    
    /**
     * Attempts to match a user name and password with the information stored in the 
     * credentialing system. 
     * 
     * @param theUsername the user name to attempt
     * @param thePassword the password to attempt
     * @return the users campus or the empty String if login is unsuccessful 
     */
    public static String login(final String theUsername, final char[] thePassword) {
        String result = "";
        try (Scanner input = new Scanner(Paths.get(R.Strings.IO_FILE_LOCATION
                                                   + R.Strings.IO_CREDENTIALS_FILE))) {
            while (input.hasNextLine()) {
                final String lineAsString = input.nextLine();
                if (!lineAsString.startsWith(R.Strings.IO_FILE_COMMENT)) {
                    final String[] parts = 
                                    lineAsString.split(R.Strings.IO_FILE_DELIMITER);
                    
                    final String username = parts[USERNAME.index()];
                    if (username.equals(theUsername)
                            && testHashForEquality(byteStringToByteArray(parts[SALT.index()]), 
                                                   byteStringToByteArray(parts[HASH.index()]), 
                                                    thePassword)) {
                        result = parts[CAMPUS.index()];
                        break;
                    } 
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } // no file
        
        return result;
    }
    
    /**
     * Attempts to add a new user to the credentials file. Note, this method does not perform
     * validation. Any and all validation must be performed before calling this method. 
     * 
     * @param theUsername the user name to add
     * @param thePassword the password associated to the user name
     * @param theCampus the campus associated to the user name
     * @return empty String if written, an error message otherwise
     * @throws IllegalArgumentException when any of the arguments are non-null but "empty"
     */
    public static String register(final String theUsername,
                                  final char[] thePassword,
                                  final String theCampus) {
        String result = "";
        if ("".equals(theUsername) || "".equals(theCampus) || thePassword.length == 0) {
            throw new IllegalArgumentException(R.Strings.ERROR_MSG_EMPTY_ARGS);
        }
        
        if (checkIfUserExists(theUsername)) {
            result = R.Strings.ERROR_MSG_USER_EXISTS;
        } else {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(
                                                 Paths.get(R.Strings.IO_FILE_LOCATION
                                                       + R.Strings.IO_CREDENTIALS_FILE).
                                                       toFile(), true))) {
                printWriter.append(theUsername);
                final byte[] salt = generateSalt();
                final byte[] hash = generateHash(thePassword, salt);
                printWriter.append(R.Strings.IO_FILE_DELIMITER);
                for (final byte b : salt) {
                    printWriter.print(b);
                    printWriter.print(R.Strings.IO_FILE_SALT_HASH_DELIMITER);
                }
                printWriter.append(R.Strings.IO_FILE_DELIMITER);
                for (final byte b : hash) {
                    printWriter.print(b);
                    printWriter.print(R.Strings.IO_FILE_SALT_HASH_DELIMITER);
                }
                printWriter.append(R.Strings.IO_FILE_DELIMITER);
                printWriter.append(theCampus);
                printWriter.append(System.lineSeparator());
            } catch (final IOException ioException) {
                ioException.printStackTrace();
                result = ioException.getMessage();
            } 
        }
        return result;
    }
    
    /**
     * Given a char[] password, and a salt, does the hashing algorithm return the same hash, 
     * in other words, was the same password entered.
     * 
     * @param theSalt the original salt used to create the users hash
     * @param theHash the users hashed password
     * @param thePassword the password to check for equivalence
     * @return true if the password produces the same hash
     */
    private static boolean testHashForEquality(final byte[] theSalt, 
                                final byte[] theHash, 
                                final char[] thePassword) {
        return Arrays.equals(theHash, generateHash(thePassword, theSalt));
    }
    
    /**
     * Convert the formatted Strings stored in the Users file back into byte[]. 
     * 
     * @param theByteString the formatted string to convert
     * @return the original byte[] that was written to the file
     */
    private static byte[] byteStringToByteArray(final String theByteString) {
        final byte[] result = new byte[SALT_SIZE];
        try (Scanner input = new Scanner(theByteString)) {
            input.useDelimiter(R.Strings.IO_FILE_SALT_HASH_DELIMITER);
            int index = 0;
            while (input.hasNextByte()) {
                result[index++] = input.nextByte();
            }
        }
        return result;
    }
    
    /**
     * Attempts to find the user name stored in the credentialing system. 
     * 
     * @param theUsername the user name to look for
     * @return true if the user name already exists, false otherwise 
     */
    private static boolean checkIfUserExists(final String theUsername) {
        boolean found = false;
        try (Scanner input = new Scanner(Paths.get(R.Strings.IO_FILE_LOCATION
                                                   + R.Strings.IO_CREDENTIALS_FILE))) {
            while (input.hasNextLine() && !found) {
                final String lineAsString = input.nextLine();
                if (!lineAsString.startsWith(R.Strings.IO_FILE_COMMENT)) {
                    found = theUsername.equals(lineAsString.split(
                        R.Strings.IO_FILE_DELIMITER)[USERNAME.index()]);
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } // no file
        
        return found;
    }
    
    /**
     * Generate a salt specific to this user. 
     * 
     * @return a salt specific to this user
     */
    private static byte[] generateSalt() {
        final SecureRandom random = new SecureRandom();
        final byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        return salt;
    }
    
    /**
     * Generate a private hash for this password. 
     * 
     * @param thePassword the password to hash 
     * @param theSalt the salt to add to the password
     * @return a private hash for this password
     */
    private static byte[] generateHash(final char[] thePassword,
                                       final byte[] theSalt) {
        byte[] result = null;
        final KeySpec spec = new PBEKeySpec(thePassword, theSalt, 65536, SALT_SIZE * 8);
        final SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            result = factory.generateSecret(spec).getEncoded();
        } catch (final InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (final NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        return result;
    }
  
}
