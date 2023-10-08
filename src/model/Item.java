// Finish and comment me!

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Item class is a class that should be able to store and provide information of an individual
 * item. This includes the price of the item, the name of the item, and the bulk price and
 * quantity of the item if necessary.
 *
 * @author Alan To
 * @version Winter 2023
 *
 */
public final class Item {

    /** The name of the item. */
    private final String myName;

    /** The price of the item. */
    private final BigDecimal myPrice;

    /** The bulk quantity of the item. */
    private int myBulkQuantity;

    /** The bulk price of the item. */
    private BigDecimal myBulkPrice;

    /**
     * Constructor that takes the name and price and should set name and price.
     * @param theName string of the item name.
     * @param thePrice the price of the item passed.
     */
    public Item(final String theName, final BigDecimal thePrice) {
        if (thePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("PRICE CANNOT BE NEGATIVE");
        }
        //Checks that the specified object reference is not null
        myName = Objects.requireNonNull(theName);
        myPrice = thePrice;
    }

    /**
     * Constructor that takes the name, price, bulk quantity, and bulk price. It
     * should then set all of those.
     * @param theName string of the item name.
     * @param thePrice the price of the item passed.
     * @param theBulkQuantity the bulk quantity of the item passed.
     * @param theBulkPrice the bulk price of the item passed.
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        if (thePrice.compareTo(BigDecimal.ZERO) < 0
                || theBulkPrice.compareTo(BigDecimal.ZERO) < 0 || theBulkQuantity < 0) {
            throw new IllegalArgumentException("CANNOT BE NEGATIVE");
        }
        myName = Objects.requireNonNull(theName);
        myPrice = thePrice;
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;
    }

    /**
     * What is the price of the item?
     * @return the price of the item.
     */
    public BigDecimal getPrice() {
        return myPrice;
    }

    /**
     * What is the bulk quantity of the item?
     * @return the bulk quantity for this item.
     */
    public int getBulkQuantity() {
        return myBulkQuantity;
    }

    /**
     * What is the bulk price of the item?
     * @return the bulk price for this item.
     */
    public BigDecimal getBulkPrice() {
        return myBulkPrice;
    }

    /**
     * Is the item bulk?
     * @return true if the item has bulk pricing; false otherwise.
     */
    public boolean isBulk() {
        return myBulkPrice != null;
    }

    /**
     * Method should be able to give item information. It should be able to return the
     * name of the item and the price of the item. If the item has a bulk price, it
     * should also return information about that as well.
     * @return a string representation of the item followed by the price along with
     * bulk price and bulk quantity if necessary.
     */
    @Override
    public String toString() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        final String price = nf.format(myPrice);

        final StringBuilder str = new StringBuilder();
        if (isBulk()) {
            final String bulkPrice = nf.format(myBulkPrice);
            str.append(myName);
            str.append(", ");
            str.append(price);
            str.append(" (");
            str.append(myBulkQuantity);
            str.append(" for ");
            str.append(bulkPrice);
            str.append(")");
        } else {
            str.append(myName);
            str.append(", ");
            str.append(price);
        }
        return str.toString();
    }


    /**
     * Once confirming that the object passed is not null and is an Item, we
     * should compare the object passed to another Item and see if they
     * are the same item and have the same price, along with bulk price and bulk
     * quantity.
     * @param theOther an object that's passed that will be compared to
     * with another Item object.
     * @return false if what was passed in the parameter wasn't a Item object or is null
     * and true if the 2 Items are the same with the same price, bulk price, and bulk quantity.
     */
    @Override
    public boolean equals(final Object theOther) {
        final boolean returnValue;
        if (theOther == null || this.getClass().getSimpleName()
                != theOther.getClass().getSimpleName()) {
            returnValue = false;
        } else {
            final Item otherItem = (Item) theOther;
            if (isBulk()) {
                returnValue = Objects.equals(myName, otherItem.myName)
                        && myPrice.compareTo(otherItem.getPrice()) == 0
                        && myBulkPrice.compareTo(otherItem.getBulkPrice()) == 0
                        && myBulkQuantity == otherItem.myBulkQuantity;
            } else {
                returnValue = Objects.equals(myName, otherItem.myName)
                        && myPrice.compareTo(otherItem.getPrice()) == 0;
            }
        }
        return returnValue;
    }

    /**
     * Going off equals, if the 2 Items are equal according to the equals(Object) method,
     * this method must produce the same integer result.
     * @return an integer hash code for this item.
     */
    @Override
    public int hashCode() {
        if (isBulk()) {
            return Objects.hash(myName, myPrice, myBulkPrice, myBulkQuantity);
        }
        return Objects.hash(myName, myPrice);
    }

}
