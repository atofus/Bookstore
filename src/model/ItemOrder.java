// Finish and comment me!

package model;

import java.util.Objects;

/**
 * The ItemOrder class purpose is to receive and store information about a purchase order
 * for an item. This includes what the item is and how much of that item was bought.
 *
 * @author Alan To
 * @version Winter 2023
 */
public final class ItemOrder {

    /** The item ordered. */
    private final Item myItem;

    /** Amount of that item that was ordered. */
    private final int myQuantity;

    /**
     * Constructor method should set the Item information and how many of that item
     * was bought with quantity.
     * @param theItem the item that was ordered
     * @param theQuantity the amount that was ordered for an individual item.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        if (theQuantity < 0) {
            throw new IllegalArgumentException("CANNOT BE NEGATIVE");
        }
        myQuantity = theQuantity;
        myItem = Objects.requireNonNull(theItem);
    }

    /**
     * What is the item?
     * @return
     */
    public Item getItem() {
        return myItem;
    }

    /**
     * How much of this item was ordered?
     * @return the quantity for this order.
     */
    public int getQuantity() {
        return myQuantity;
    }

    /**
     * Should return the information of the item and how much was bought.
     * @return a string of the item information and the quantity of the item.
     */
    @Override
    public String toString() {
        final StringBuilder str = new StringBuilder();
        str.append(myItem);
        str.append(": ");
        str.append(myQuantity);
        return str.toString();
    }

}
