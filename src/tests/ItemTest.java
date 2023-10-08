package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import model.Item;
import model.ItemOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ItemTest {

    private Item myTestItem;

    @BeforeEach
    void setup() {
         //this will be in every method. Will execute BEFORE EACH METHOD!!! (before each)
        // myTestItem = new Item("test item", new BigDecimal("0"));
    }

    @Test
    void testTwoParameterConstructor() {
        assertThrows(NullPointerException.class,
                () -> {
                   // myTestItem = new Item(null, null);
                    myTestItem = new Item("notebook", null);
                });
    }

    @Test
    void testGetPrice() {
        //java.util.Scanner scan = new java.util.Scanner(System.in);
        myTestItem = new Item("notebook", new BigDecimal("1.99"));
        assertEquals(new BigDecimal("1.99"), myTestItem.getPrice());
    }

    @Test
    void testGetBulkQuantity() {
        myTestItem = new Item("notebook", new BigDecimal("1.99"), 3, new BigDecimal("0.99"));
        assertEquals(3, myTestItem.getBulkQuantity());
    }

    @Test
    void testGetBulkPrice() {
        myTestItem = new Item("notebook", new BigDecimal("1.99"), 3, new BigDecimal("0.99"));
        assertEquals(new BigDecimal("0.99"), myTestItem.getBulkPrice());
    }

    @Test
    void testIsBulk() {
        myTestItem = new Item("notebook", new BigDecimal("1.99"), 3, new BigDecimal("0.99"));
        assertTrue(myTestItem.isBulk());
        myTestItem = new Item("notebook", new BigDecimal("1.99"));
        assertFalse(myTestItem.isBulk());
    }

    @Test
    void testToString() {
        myTestItem = new Item("notebook", new BigDecimal("1.99"), 3, new BigDecimal("0.99"));
        assertEquals("notebook, $1.99 (3 for $0.99)", myTestItem.toString());
        myTestItem = new Item("notebook", new BigDecimal("1.99"));
        assertEquals("notebook, $1.99", myTestItem.toString());
    }

    @Test
    void testEquals() {
        //both of these are the same
        myTestItem = new Item("notebook", new BigDecimal("1.99"), 3, new BigDecimal("0.99"));
        Item testItem2 = new Item("notebook", new BigDecimal("1.99"),
                3, new BigDecimal("0.99"));
        assertEquals(true, myTestItem.equals(testItem2));

        //Checking 2 parameter contructor, both of these are the same
        myTestItem = new Item("notebook", new BigDecimal("1.99"));
        testItem2 = new Item("notebook", new BigDecimal("1.99"));
        assertEquals(true, myTestItem.equals(testItem2));

        //These are not the same
        myTestItem = new Item("notebook", new BigDecimal("1.99"), 3, new BigDecimal("0.99"));
        testItem2 = new Item("notebook", new BigDecimal("4.00"), 3, new BigDecimal("0.99"));
        assertEquals(false, myTestItem.equals(testItem2));

        //the class are NOT the same
        final ItemOrder testItemOrder = new ItemOrder(new Item("Ruler",
                new BigDecimal("1.99")), 6);
        assertFalse(myTestItem.equals(testItemOrder));


    }

    @Test
    void testHashCode() {
        myTestItem = new Item("notebook", new BigDecimal("1.99"));
        final Item testItem2 = new Item("notebook", new BigDecimal("1.99"));
        assertTrue(myTestItem.equals(testItem2) && testItem2.equals(myTestItem));
        assertTrue(myTestItem.hashCode() == testItem2.hashCode());

    }
}