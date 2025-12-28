package assignments.Ex2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Intro2CS, 2026A, this is a very
 */
class MapTest {
    /**
     */
    private int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};
    private Map2D _m0, _m1, _m3_3;

    @BeforeEach
    public void setUp() {
        _m0 = new Map(20, 20, 0);
        _m1 = new Map(20, 20, 0);
        _m3_3 = new Map(_map_3_3);
    }
    @Test
    @Timeout(value = 1, unit = SECONDS)
    void init() {
        int[][] bigarr = new int [500][500];
        _m1.init(bigarr);
        assertEquals(bigarr.length, _m1.getWidth());
        assertEquals(bigarr[0].length, _m1.getHeight());
        Pixel2D p1 = new Index2D(3,2);
        _m1.fill(p1,1, true);
    }

    @Test
    void testInit() {
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);
    }
    @Test
    void testEquals() {
        assertEquals(_m0,_m1);
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0,_m1);
    }
    /**
     * 1. Test initialization with width, height, and value.
     * 2. Test that negative dimensions throw an exception.
     */
    @Test
    void testInit1() {
        _m0.init(10, 5, 3);
        assertEquals(10, _m0.getWidth());
        assertEquals(5, _m0.getHeight());
        assertEquals(3, _m0.getPixel(0,0));

        assertThrows(RuntimeException.class, () -> {
            _m1.init(-5, 10, 1);
        }, "Should throw exception for negative width");
    }
    /**
     * 1. Test point inside bounds.
     * 2. Test point with negative coordinates = outside.
     * 3. Test point exceeding dimensions = outside.
     */
    @Test
    void isInside() {
        _m0.init(10, 10, 0);
        assertTrue(_m0.isInside(new Index2D(5, 5)), "Failed isInside: (5,5) should be true");
        assertTrue(_m0.isInside(new Index2D(0, 0)), "Failed isInside: (0,0) should be true");

        assertFalse(_m0.isInside(new Index2D(-1, 5)), "Failed isInside: (-1,5) should be false");

        assertFalse(_m0.isInside(new Index2D(10, 5)), "Failed isInside: (10,5) should be false (max index is 9)");
        assertFalse(_m0.isInside(new Index2D(5, 11)), "Failed isInside: (5,11) should be false");
    }
    /**
     * 1. Test maps with same dimensions.
     * 2. Test maps with different width.
     * 3. Test maps with different height.
     */
    @Test
    void sameDimensions() {
        _m0.init(10, 10, 0);
        _m1.init(10, 10, 5);

        assertTrue(_m0.sameDimensions(_m1), "Failed sameDimensions: 10x10 should equal 10x10");

        _m1.init(5, 10, 0);
        assertFalse(_m0.sameDimensions(_m1), "Failed sameDimensions: 10x10 should not equal 5x10");

        _m1.init(10, 5, 0);
        assertFalse(_m0.sameDimensions(_m1), "Failed sameDimensions: 10x10 should not equal 10x5");
    }
    /**
     * 1. test that mul by 0 = 0
     * 2. test mul by negative num = -10
     * 3.test rounding num = -5
     */
    @Test
    void mul() {
        Map map = new Map(2, 2, 10);
        map.mul(0.0);
        assertEquals(0, map.getPixel(0, 0), "Failed mul(0): Expected 0");
        assertEquals(0, map.getPixel(1, 1), "Failed mul(0): Expected 0");

        map.init(2, 2, 5);
        map.mul(-2.0);
        assertEquals(-10, map.getPixel(0, 0), "Failed mul(-2): Expected -10");

        map.mul(0.5);
        assertEquals(-5, map.getPixel(0, 0), "Failed rounding check");
    }
    /**
     * 1. Test scaling up: 10x10 to 20x20.
     * 2. Test scaling down: 20x20 to 10x10.
     * 3. Test that content is preserved (center pixel) by Scale down 0.5 to 10x10 .
     */
    @Test
    void rescale() {
        _m0.init(10, 10, 5);
        _m0.setPixel(4, 4, 1);

        _m0.rescale(2.0, 2.0);
        assertEquals(20, _m0.getWidth(), "Failed scaling up width");
        assertEquals(20, _m0.getHeight(), "Failed scaling up height");
        assertEquals(1, _m0.getPixel(8, 8), "Failed preserving pixel after scale up");

        _m0.rescale(0.5, 0.5);
        assertEquals(10, _m0.getWidth(), "Failed scaling down width");
        assertEquals(10, _m0.getHeight(), "Failed scaling down height");
    }
    @Test
    void testEquals1() {
        // מקרה 1: שוויון בסיסי
        assertEquals(_m0, _m1);

        // מקרה 2: שוויון אחרי שינוי תוכן
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);

        // מקרה קצה: שינוי פיקסל אחד הופך אותם לשונים
        _m1.setPixel(0, 0, 9);
        assertNotEquals(_m0, _m1);
    }
    /**
     * 1. Test filling a single isolated pixel surrounded by different colors.
     * 2. Test filling a connected area (entire map).
     * 3. Test cyclic fill (wrapping around edges) by Creating a line of 0s on top row: [0, 0, 1, 0, 0].
     * Regular fill from (0,0) fills 2 pixels. Cyclic should jump from left to right.
     */
    @Test
    void fill() {
        _m0.init(_map_3_3); // {0,1,0}, {1,0,1}, {0,1,0}
        Pixel2D p = new Index2D(0, 0); // Value is 0
        int filled = _m0.fill(p, 99, false);
        assertEquals(1, filled, "Failed isolated fill: Expected 1 pixel changed");
        assertEquals(99, _m0.getPixel(0, 0));

        _m0.init(5, 5, 0); // All 0
        filled = _m0.fill(new Index2D(0, 0), 5, false);
        assertEquals(25, filled, "Failed full map fill: Expected 25 pixels");


        _m0.init(5, 5, 1); // Background 1 = black
        _m0.setPixel(0, 0, 0); _m0.setPixel(1, 0, 0); // Left side
        _m0.setPixel(3, 0, 0); _m0.setPixel(4, 0, 0); // Right side

        filled = _m0.fill(new Index2D(0, 0), 2, true); // Cyclic = true
        assertEquals(4, filled, "Failed cyclic fill: Expected 4 pixels connected via wrap-around");
    }
    /**
     * 1. Test path blocked by wall = returns null.
     * 2. Test valid path calculation.
     * 3. Test cyclic path = shortest way wraps around.
     * in a 10x10 map, from (0,0) to (9,0)
     */
    @Test
    void shortestPath() {
        _m0.init(3, 3, 0);
        _m0.drawRect(new Index2D(1, 0), new Index2D(1, 2), 1); // vertical wall
        Pixel2D p1 = new Index2D(0, 1);
        Pixel2D p2 = new Index2D(2, 1);
        assertNull(_m0.shortestPath(p1, p2, 1, false), "Failed blocked path: Expected null");

        _m0.init(5, 5, 0);
        Pixel2D[] path = _m0.shortestPath(new Index2D(0,0), new Index2D(0, 2), 1, false);
        assertEquals(3, path.length, "Failed simple path: Expected length 3 (0,0)->(0,1)->(0,2)");

        _m0.init(10, 10, 0);
        Pixel2D[] cycPath = _m0.shortestPath(new Index2D(0,0), new Index2D(9, 0), 1, true);
        assertEquals(2, cycPath.length, "Failed cyclic path: Expected length 2 (wrap around)");
    }
    /**
     * 1. Test distances from center in open map.
     * 2. Test unreachable pixels remain -1.
     */
    @Test
    void allDistance() {
        _m0.init(3, 3, 0);          // empty map
        Pixel2D start = new Index2D(1, 1);
        Map2D dist = _m0.allDistance(start, 1, false); // 1 is obstacle
        assertEquals(0, dist.getPixel(1, 1), "Center distance should be 0");
        assertEquals(1, dist.getPixel(0, 1), "Neighbor distance should be 1");
        assertEquals(1, dist.getPixel(1, 0), "Neighbor distance should be 1");
        assertEquals(2, dist.getPixel(0, 0), "Corner distance should be 2");

        _m0.setPixel(0, 0, 1); // Place wall at (0,0)
        dist = _m0.allDistance(start, 1, false);
        assertEquals(-1, dist.getPixel(0, 0), "Wall/Unreachable should be -1");
    }
}