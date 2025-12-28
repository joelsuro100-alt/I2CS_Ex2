package assignments.Ex2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Index2DTest {

    /**
     * 1. Test initialization of X.
     * 2. Test negative values for X.
     */
    @Test
    void getX() {
        Pixel2D p1 = new Index2D(5, 10);
        assertEquals(5, p1.getX(), "Failed getX: Expected 5");

        Pixel2D p2 = new Index2D(-3, 0);
        assertEquals(-3, p2.getX(), "Failed getX: Expected -3");
    }

    /**
     * 1. Test initialization of Y.
     * 2. Test negative values for Y.
     */
    @Test
    void getY() {
        Pixel2D p1 = new Index2D(5, 10);
        assertEquals(10, p1.getY(), "Failed getY: Expected 10");

        Pixel2D p2 = new Index2D(0, -99);
        assertEquals(-99, p2.getY(), "Failed getY: Expected -99");
    }

    /**
     * 1. Test distance 3-4-5 triangle.
     * 2. Test distance to self (should be 0).
     * 3. Test distance with negative coordinates.
     */
    @Test
    void distance2D() {
        Pixel2D p0 = new Index2D(0,0);
        Pixel2D p1 = new Index2D(3,4);

        double d1 = p0.distance2D(p1);
        assertEquals(5, d1, 0.0001, "Failed distance 3-4-5");

        assertEquals(0, p0.distance2D(p0), 0.0001, "Distance to self must be 0");

        Pixel2D p2 = new Index2D(-3, -4);
        assertEquals(5, p0.distance2D(p2), 0.0001, "Distance calculation failed with negative numbers");
    }

    /**
     * 1. Test string format matching "(x,y)".
     */
    @Test
    void testToString() {
        Pixel2D p = new Index2D(5, 10);
        String s = p.toString();
        assertEquals("(5,10)", s, "Failed toString: format should be (x,y)");
    }

    /**
     * 1. Test equality between two objects with same values.
     * 2. Test inequality between different values.
     * 3. Test equality with null and other types (should be false).
     */
    @Test
    void testEquals() {
        Pixel2D p1 = new Index2D(2, 2);
        Pixel2D p2 = new Index2D(2, 2);
        Pixel2D p3 = new Index2D(3, 3);

        assertEquals(p1, p2, "Same coordinates should be equal");
        assertTrue(p1.equals(p2));

        assertNotEquals(p1, p3, "Different coordinates should not be equal");

        assertFalse(p1.equals(null), "Should return false for null");
        assertFalse(p1.equals("String Object"), "Should return false for non-Pixel2D objects");
    }
}