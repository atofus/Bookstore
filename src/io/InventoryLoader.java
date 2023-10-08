/*
 * TCSS 305 Assignment 1 - UW Bookstore
 */

package io;

import static res.R.ItemsFile.ITEM_BULK_PRICE;
import static res.R.ItemsFile.ITEM_PRICE;
import static res.R.ItemsFile.ITEM_BULK_QUANITIY;
import static res.R.ItemsFile.ITEM_NAME;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import model.Item;
import res.R;

/**
 * A utility class for The shopping cart application. 
 * 
 * @author Charles Bryan
 * @version Autumn 2015
 */
public final class InventoryLoader {
    
    /**
     * A private constructor, to prevent external instantiation.
     */
    private InventoryLoader() {
        
    }
    
    /**
     * Reads item information from a file and returns a List of Item objects.
     * @param theFile the name of the file to load into a List of Items
     * @return a List of Item objects created from data in an input file
     */
    public static List<Item> readItemsFromFile(final String theFile) {
        final List<Item> items = new LinkedList<>();
        
        try (Scanner input = new Scanner(Paths.get(theFile))) { 
            while (input.hasNextLine()) {
                final String[] parts = input.nextLine().split(R.Strings.IO_FILE_DELIMITER);
                final String itemName = parts[ITEM_NAME.index()];
                final BigDecimal itemPrice = new BigDecimal(parts[ITEM_PRICE.index()]);
                if (parts.length > 2) {
                    final int bulkQuantity = 
                                    Integer.valueOf(parts[ITEM_BULK_QUANITIY.index()]);
                    final BigDecimal bulkPrice = 
                                    new BigDecimal(parts[ITEM_BULK_PRICE.index()]);
                    items.add(new Item(itemName, itemPrice, bulkQuantity, bulkPrice));
                } else {
                    items.add(new Item(itemName, itemPrice));
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } 
        return items;
    }
    
    /**
     * Reads item information from a file and returns a List of Item objects.
     * @param theFile the name of the file to load into a List of Items
     * @return a List of Item objects created from data in an input file
     */
    public static List<String> readConfigurationFromFile(final String theFile) {
        final List<String> results = new LinkedList<>();
        
        try (Scanner input = new Scanner(Paths.get(theFile))) { 
            while (input.hasNextLine()) {
                final String line = input.nextLine();
                
                if (!line.startsWith(R.Strings.IO_FILE_COMMENT)) {
                    results.add(line);
                }
                
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } 
    
        return results;
    }    
}
