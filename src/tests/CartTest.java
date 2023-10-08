package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import model.Cart;
import model.Item;
import model.ItemOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




class CartTest {

    private Cart myTestCart;
    private Item myTestItem;

    @BeforeEach
    void setup() {
        myTestCart = new Cart();
        System.out.println("setup just executed");

        myTestItem = new Item("notebook", new BigDecimal("1.99"));
        final ItemOrder testItemOrder = new ItemOrder(myTestItem, 6);

        final ItemOrder testItemOrder2 = new ItemOrder(new Item("ipad",
                new BigDecimal("399.99")), 1);

        final ItemOrder testItemOrder3 = new ItemOrder(new Item("Pencil",
                new BigDecimal("3.00")), 3);

        final ItemOrder testItemOrder4 = new ItemOrder(new Item("Mac",
                new BigDecimal("1000.00")), 1);
        myTestCart.add(testItemOrder);
        myTestCart.add(testItemOrder2);
        myTestCart.add(testItemOrder3);
        myTestCart.add(testItemOrder4);
    }

    @Test
    void add() {
        //adding another item onto the cart should make size 5
        final ItemOrder testItemOrder5 = new ItemOrder(new Item("Ruler",
                new BigDecimal("1.99")), 6);
        myTestCart.add(testItemOrder5);
        assertEquals(5, myTestCart.getCartSize());

        //adding an item that's already in the cart should replace the previous and the cart
        // size should be 5.
        final ItemOrder testItemOrder6 = new ItemOrder(myTestItem, 4);
        myTestCart.add(testItemOrder6);
        assertEquals(5, myTestCart.getCartSize());
    }

    @Test
    void setMembership() {
        myTestCart.setMembership(true);
        assertTrue(myTestCart.getMembership());
        myTestCart.setMembership(false);
        assertFalse(myTestCart.getMembership());
    }

    @Test
    void calculateTotal() {
        assertEquals(new BigDecimal("1420.93"), myTestCart.calculateTotal());

        //if they have a membership AND there's a bulk pricing/quantity
        myTestCart.setMembership(true);
        //bulk pricing here is 6 for $10.04
        myTestItem = new Item("UW Note pad", new BigDecimal("4.41"), 6,
                new BigDecimal("10.04"));

        final ItemOrder testItemOrder5 = new ItemOrder(myTestItem, 6);
        //should be $10.04 because we got 6
        myTestCart.clear();
        myTestCart.add(testItemOrder5);
        assertEquals(new BigDecimal("10.04"), myTestCart.calculateTotal());
    }

    @Test
    void clear() {
        myTestCart.clear();
        assertEquals(0, myTestCart.getCartSize());
    }

    @Test
    void getCartSize() {
        assertEquals(4, myTestCart.getCartSize());
    }


    @Test
    void testToString() {
        myTestCart = new Cart(); //the cart is now new and should be empty
        final String expected = "[]";
        assertEquals(expected, myTestCart.toString());
    }
}