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
    public void setUp() {          // חובה: שם בדיוק setUp (עם U גדולה!)
        _m0 = new Map(20, 20, 0);   // מפה בינונית לבדיקות - מאותחלת
        _m1 = new Map(20, 20, 0);   // עוד מפה להשוואות
        _m3_3 = new Map(_map_3_3); // המפה הקטנה 3x3 שלך
    }

    /*@BeforeEach
    public void setuo() {
        _m3_3 = new Map(_map_3_3);
    }*/
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

    @Test
    void testinit1(){
        //test if w & h are neggative

    }


    @Test
    void testInit1() {
    }

    @Test
    void testInit2() {
    }

    @Test
    void getMap() {
    }

    @Test
    void getWidth() {
    }

    @Test
    void getHeight() {
    }

    @Test
    void getPixel() {
    }

    @Test
    void testGetPixel() {
    }

    @Test
    void setPixel() {
    }

    @Test
    void testSetPixel() {
    }

    @Test
    void isInside() {
    }

    @Test
    void sameDimensions() {
    }

    @Test
    void addMap2D() {
    }

    @Test
    void mul() {
        _m0.init(3, 3, 42);         // all pixels = 42
        _m0.mul(0.0);               // multiply by 0
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertEquals(0, _m0.getPixel(x, y));
            }
        }
        _m0.init(2, 2, 10);         // all pixels = 10
        _m0.mul(-3.0);              // 10 * -3 = -30
        assertEquals(-30, _m0.getPixel(0, 0));
        assertEquals(-30, _m0.getPixel(1, 1));

        _m0.init(1, 4, 5);
        _m0.mul(1.8);               // 5 * 1.8 = 9.0 → 9
        assertEquals(9, _m0.getPixel(0, 0));

        _m1.init(1, 1, 7);
        _m1.mul(0.6);               // 7 * 0.6 = 4.2 → rounds to 4
        assertEquals(4, _m1.getPixel(0, 0));

        _m1.init(1, 1, 9);
        _m1.mul(1.7);               // 9 * 1.7 = 15.3 → rounds to 15
        assertEquals(15, _m1.getPixel(0, 0));

        _m0.init(_map_3_3);
        _m0.mul(2.0);
        assertEquals(0, _m0.getPixel(0, 0));
        assertEquals(2, _m0.getPixel(0, 1));
        assertEquals(8, _m0.getPixel(1, 1));
        assertEquals(16, _m0.getPixel(2, 1));
    }

    @Test
    void rescale() {
    }

    @Test
    void drawCircle() {
    }

    @Test
    void drawLine() {
    }

    @Test
    void drawRect() {
    }

    @Test
    void testEquals1() {
    }

    @Test
    void fill() {
        _m0.init(_map_3_3);
        Pixel2D p = new Index2D(0, 0);
        int filled = _m0.fill(p, 99, false);
        assertEquals(1, filled);    // only one pixel with value 0 at (0,0)
    }

    @Test
    void shortestPath() {
        _m0.init(3, 3, 0);
        _m0.drawRect(new Index2D(1, 0), new Index2D(1, 2), 1); // vertical wall
        Pixel2D p1 = new Index2D(0, 1);
        Pixel2D p2 = new Index2D(2, 1);
        assertNull(_m0.shortestPath(p1, p2, 1, false)); // blocked by wall


    }

    @Test
    void allDistance() {
        _m0.init(3, 3, 0);          // empty map
        Pixel2D start = new Index2D(1, 1);
        Map2D dist = _m0.allDistance(start, 1, false); // 1 is obstacle
        assertEquals(0, dist.getPixel(1, 1));     // center
        assertEquals(1, dist.getPixel(0, 1));     // neighbors
        assertEquals(1, dist.getPixel(1, 0));
        assertEquals(2, dist.getPixel(0, 0));     // corners
    }
}