package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import model.Item;
import model.ItemOrder;
import org.junit.jupiter.api.Test;


class ItemOrderTest {

    private ItemOrder myTestItemOrder;
    private Item myTestItem;

    @Test
    void testTwoParameterConstructor() {
        myTestItem = new Item("Ipad", new BigDecimal("1.000"));
        assertThrows(NullPointerException.class,
                () -> {
                    myTestItemOrder = new ItemOrder(null, 5);
                    myTestItemOrder = new ItemOrder(myTestItem, -5);
                });
    }

    @Test
    void testGetItem() {
        myTestItem = new Item("notebook", new BigDecimal("1.99"));
        myTestItemOrder = new ItemOrder(myTestItem, 5);
        assertEquals(myTestItem, myTestItemOrder.getItem());

        //with bulk
        myTestItem = new Item("notebook", new BigDecimal("1.99"), 3, new BigDecimal("0.99"));
        myTestItemOrder = new ItemOrder(myTestItem, 4);
        assertEquals(myTestItem, myTestItemOrder.getItem());
    }

    @Test
    void testGetQuantity() {
        myTestItem = new Item("notebook", new BigDecimal("1.99"));
        myTestItemOrder = new ItemOrder(myTestItem, 6);
        assertEquals(6, myTestItemOrder.getQuantity());
    }

    @Test
    void testToString() {
        myTestItem = new Item("notebook", new BigDecimal("1.99"));
        myTestItemOrder = new ItemOrder(myTestItem, 5);
        assertEquals("notebook, $1.99: 5", myTestItemOrder.toString());

    }
}