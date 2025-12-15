package assignments.Ex2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Index2DTest {

    @Test
    void getX() {
    }

    @Test
    void getY() {
    }

    @Test
    void distance2D() {
        Pixel2D p0 = new Index2D(0,0);
        Pixel2D p1 = new Index2D(3,4);
        double d1 = p0.distance2D(p1);
        assertEquals(d1,5,0);

    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }
}