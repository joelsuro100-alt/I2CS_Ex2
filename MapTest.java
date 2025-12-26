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
    public void setuo() {
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
    }

    @Test
    void shortestPath() {
    }

    @Test
    void allDistance() {
    }
}