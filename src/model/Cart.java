// Finish and comment me!

package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Cart class should store information "about the customer's overall purchase." Once a customer
 * orders something, it should go into a cart. This class will act as a shopping cart where
 * we could store our item orders in, along with changing what's in the cart. With all
 * the item ordered in the cart, the cart class should also be able to return the total
 * price of everything.
 *
 * @author Alan
 * @version Winter 2023
 */
public class Cart {

    /** Will be used as a cart where we put our item orders in. */
    private final List<ItemOrder> myCart;

    /** See if they have a membership. */
    private boolean myMemberShip;

    /**
     * Default constructor that should set the instance fields: myCart and myMemberShip.
     */
    public Cart() {
        myCart = new ArrayList<>();
        myMemberShip = false;
    }

    /**
     * This method is used to add an item order into the cart. "It should replace any
     * previous order for an equivalent item with the new order."
     *
     * @param theOrder the item that was ordered.
     */
    public void add(final ItemOrder theOrder) {
        for (int i = 0; i < myCart.size(); i++) {
            if (myCart.get(i).getItem() == theOrder.getItem()) {
                myCart.set(i, Objects.requireNonNull(theOrder));
                return; //so we don't go down and add another order
            }
        }
        myCart.add(Objects.requireNonNull(theOrder));
    }

    /**
     * Sets whether the customer for the shopping cart has a store membership.
     * @param theMembership true if the customer has a membership; false otherwise.
     */
    public void setMembership(final boolean theMembership) {
        myMemberShip = theMembership;
    }


    /**
     * Do they have a membership? This method will mainly only be used for the junit testing.
     * @return true if they have a membership; false otherwise.
     */
    public boolean getMembership() {
        return myMemberShip;
    }

    /**
     * This method will be looking at the cart and every single one of its item it's holding.
     * Its purpose is to calculate the total price that's that's needed to be paid in the cart.
     * If the item has a bulk price along with a membership, we should calculate the total
     * price with the discount appropriately.
     * @return the total price of each item.
     */
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO; //initialize total to 0
        BigDecimal res = BigDecimal.ZERO;
        for (ItemOrder item : myCart) { //go through ONE item at a time
            final BigDecimal quantity = new BigDecimal(item.getQuantity());
            final BigDecimal bulkQuantity = new BigDecimal(item.getItem().getBulkQuantity());
            final BigDecimal price = item.getItem().getPrice();
            final BigDecimal bulkPrice = item.getItem().getBulkPrice();

            //check if item has a bulk pricing and bulk quantity, along with a membership.
            if (myMemberShip
                    && item.getItem().isBulk()) { //check the bulk pricing and bulk quantity
                res = quantity.divideToIntegralValue(bulkQuantity);
                res = res.multiply(bulkPrice);
                res = res.add((quantity.remainder(bulkQuantity)).multiply(price));
                total = total.add(res);
            } else { //if they don't have a membership, we should just find the normal price
                res = quantity.multiply(price);
                total = total.add(res); //same as total = total + result;
            }
        }
        return total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * Method is used to remove every item order that's in the cart.
     */
    public void clear() {
        myCart.clear();
    }

    /**
     * How many items that were ordered are in the cart?
     * @return the amount of items that's in the cart.
     */
    public int getCartSize() {
        return myCart.size();
    }

    /**
     * Method is used to give information on what's in the shopping cart.
     * @return information of the item orders that's in the cart as an array list.
     */
    @Override
    public String toString() {
        return myCart.toString();
    }
}
