/*
 * TCSS 305 Assignment 1 - UW Bookstore
 */

package res;

import java.awt.Color;

/**
 * Resources class.
 * 
 * General convention: constant starts with the abbreviation for the class it is associated 
 * with.
 * 
 *  LoginFrame == LF
 *  BookstoreFrame == BS
 * 
 * @author Charles Bryan
 * @version January 2020
 */
public class R {
    
    /**
     * Resource class for color schemes. 
     * 
     * @author Charles Bryan
     * @version 7 September 2019
     */
    public static class Colors {
        
        /*
         * For the UW color palate and other UW branding information see
         * http://www.washington.edu/marketing/files/2012/09/WebColorPalette1.pdf 
         */
        
        /** The background color for content containers. */
        public static final Color CONTENT_BG = new Color(199, 153, 0); // UW Gold

        /** The background color for headers and footers. */
        public static final Color HEADER_FOOTER_BG = new Color(57, 39, 91); // UW Purple
        
        /** The text color for content containers. */
        public static final Color CONTENT_TEXT = HEADER_FOOTER_BG;         
        
        /** The text color for headers and footers. */
        public static final Color HEADER_FOOTER_TEXT = Color.WHITE; 
        
        /** The text color for background of text fields on error. */
        public static final Color ERROR_TEXT_FIELD_BG = Color.PINK; 
        
        /** The text color for background of text fields on without error. */
        public static final Color NO_ERROR_TEXT_FIELD_BG = Color.WHITE; 
        
    }
    
    /**
     * Resource ENUM for Array index values in the Items File. 
     * 
     * @author Charles Bryan
     * @version 7 January 2020
     */
    public enum ItemsFile {
        
        /** The index of the Item name in the Items files. */
        ITEM_NAME,
        
        /** The index of the Item price in the Items files. */
        ITEM_PRICE,
        
        /** The index of the Item bulk quantity in the Items files. */
        ITEM_BULK_QUANITIY,
        
        /** The index of the Item bulk price in the Items files. */
        ITEM_BULK_PRICE;
        
        /**
         * Return the associated index of the location of this element in a line in the Items
         * file. 
         * @return the associated index
         */
        public int index() {
            return this.ordinal();
        }
        
    }
    
    /**
     * Resource ENUM for Array index values in the Users File. 
     * 
     * @author Charles Bryan
     * @version 7 January 2020
     */
    public enum UsersFile {
        /** The index of the USERNAME in the credentials files. */
        USERNAME,
        /** The index of the SALT in the credentials files. */
        SALT,
        /** The index of the HASH in the credentials files. */
        HASH,
        /** The index of the CAMPUS in the credentials files. */
        CAMPUS;
        
        /**
         * Return the associated index of the location of this element in a line in the Users
         * file. 
         * @return the associated index
         */
        public int index() {
            return this.ordinal();
        }
        
    }

    /**
     * Resource class for dimensions. 
     * 
     * @author Charles Bryan
     * @version 7 September 2019
     */
    public static class Dimensions {
        
        /** The number of rows in the Login Frame: status login. */
        public static final int LF_LOGIN_ROWS = 2;
        
        /** The number of columns in the Login Frame: status login. */
        public static final int LF_LOGIN_COLS = 2;
        
        /** The number of rows in the Login Frame: status register. */
        public static final int LF_REGISTRATION_ROWS = 4;
        
        /** The number of columns in the Login Frame: status register. */
        public static final int LF_REGISTRATION_COLS = 2;
        
        /** The number of columns for Item Panels in the Bookstore frame. */
        public static final int BF_ITEMS_COLS = 1;
        
        /** General horizontal padding. */
        public static final int H_PADDING = 10;
        
        /** General vertical padding. */
        public static final int V_PADDING = 5;
        
        /** The width of the text fields in the Login GUI. */
        public static final int LF_TEXTFIELD_WIDTH = 10;
        
        /** The width of the total text field in the BookStore GUI. */
        public static final int BF_TEXTFIELD_TOTAL = 12;
        
        /** The width of the quantity text field in the BookStore GUI. */
        public static final int BF_TEXTFIELD_QUANITITY = 3;
    }

    /**
     * Resource class for String Literals. 
     * 
     * General naming convention for strings associated with Swing Components:
     * 
     *  CLASS_COMPONENT_DESCRIPTION
     *  
     * 
     * @author Charles Bryan
     * @version 7 September 2019
     */
    public static class Strings {
        
        /** The filename of the file containing the items to display in the cart. */
        public static final String IO_CONFIG_FILE = "config.txt";
        
        /** The filename of the file containing the user credentials. */
        public static final String IO_CREDENTIALS_FILE = "users.txt";
        
        /** The filename of the application icon. */
        public static final String IO_ICON_FILE = "w.gif";
        
        /** The local path of the configuration files. */
        public static final String IO_FILE_LOCATION = "files/";
        
        /** The file extension for text files. */
        public static final String IO_FILE_EXTENSION = ".txt";
        
        /** The delimiter used in text files. */
        public static final String IO_FILE_DELIMITER = ";";
        
        /** The delimiter used in text files to delimit bytes in salts and hashes. */
        public static final String IO_FILE_SALT_HASH_DELIMITER = ":";
        
        /** The character used to make a single line comment in text files. */
        public static final String IO_FILE_COMMENT = "#";
        
        /** Error message used when event handlers are implemented with method references. */
        public static final String ERROR_MSG_ILLEGAL_INVOKE = 
                        "Only invoke when used as handler for JButton.";
        
        /** Error message used when registration pwds don't match. */
        public static final String ERROR_MSG_PASSWORDS_NO_MATCH = 
                        "Passwords must be the same.";
        
        /** Error message used when registration pwds are too short. */
        public static final String ERROR_MSG_PASSWORDS_TOO_SHORT = 
                        "Password must be at least 7 charachters long.";
        
        /** Error message used when registration pwds are too short. */
        public static final String ERROR_MSG_USER_EXISTS = 
                        "Unfortunatly that user name is already taken.";
        
        /** Error message used for empty fields. */
        public static final String ERROR_MSG_EMPTY_FIELDS = 
                        "All fields are required.";
        
        /** Error message used for empty arguments. */
        public static final String ERROR_MSG_EMPTY_ARGS = 
                        "All paramaters must contain non-empty values";
        
        /** Error message for title in Error Popup. */
        public static final String ERROR_MSG_TITLE = 
                        "Oh No!";
        
        /** Error message for incorrect login credentials. */
        public static final String ERROR_MSG_CREDENTIALS = 
                        "Login credentials incorrect. Plesae try again.";
        
        /** Text in Total text field. */
        public static final String BF_TEXTFIELD_TOTAL = "$0.00";
        
        /** Text in Total text field. */
        public static final String BF_FRAME_TITLE = "UW Bookstore";
        
        /** Message on total Label. */
        public static final String BF_LABEL_TOTAL = "order total:";
        
        /** Message on clear Button. */
        public static final String BF_BUTTON_CLEAR = "Clear";
        
        /** Message on membership check box. */
        public static final String BF_CHECKBOX_MEMBER = "customer has store membership";
        
        /** Message on Login Button during login. */
        public static final String LF_BUTTON_LOGIN = "Login";
        
        /** Message on Login Button during registration. */
        public static final String LF_BUTTON_LOGIN_REGISTRATION = "Register and Login";
        
        /** Message on Register Button. */
        public static final String LF_BUTTON_REGISTER = "Register";
        
        /** Message on user name Label. */
        public static final String LF_LABEL_USERNAME = "Username:";
        
        /** Message on password Label. */
        public static final String LF_LABEL_PASSWORD = "Password:";
        
        /** Message on second password name Label. */
        public static final String LF_LABEL_PASSWORD_2 = "Re-enter Password:";
        
        /** Message on campus Label. */
        public static final String LF_LABEL_CAMPUS = "Choose your Campus:";
        
        /** Message on frame header Label. */
        public static final String LF_LABEL_HEADER = "Login to access store:";
    }
}
