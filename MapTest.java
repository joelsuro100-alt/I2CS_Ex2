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

    @Test
    void testinit1(){
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);

        // מקרה 2: שינוי במערך המקורי לא אמור להשפיע (Deep Copy)
        int[][] temp = {{1, 2}, {3, 4}};
        _m0.init(temp);
        temp[0][0] = 99; // משנים את המערך בחוץ
        assertNotEquals(99, _m0.getPixel(0, 0)); // המפה צריכה להישאר עם 1
    }


    @Test
    void testInit1() {
        // מקרה 1: אתחול ממערך קיים ובדיקת שוויון
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);

        // מקרה 2: שינוי במערך המקורי לא אמור להשפיע (Deep Copy)
        int[][] temp = {{1, 2}, {3, 4}};
        _m0.init(temp);
        temp[0][0] = 99; // משנים את המערך בחוץ
        assertNotEquals(99, _m0.getPixel(0, 0)); // המפה צריכה להישאר עם 1
    }

    @Test
    void testInit2() {
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
        // 1. הכנה: יוצרים מפה שכל הערכים בה הם 10
        Map map = new Map(2, 2, 10);

        // --- שלב א': כפל ב-0 ---
        // ציפייה: 10 * 0 = 0
        map.mul(0.0);
        assertEquals(0, map.getPixel(0, 0), "Failed mul(0): Expected 0");
        assertEquals(0, map.getPixel(1, 1), "Failed mul(0): Expected 0");

        // --- שלב ב': כפל במספר שלילי ---
        // נאתחל את המפה מחדש לערך 5
        map.init(2, 2, 5);
        // נכפיל במינוס 2. ציפייה: 5 * (-2) = -10
        map.mul(-2.0);
        assertEquals(-10, map.getPixel(0, 0), "Failed mul(-2): Expected -10");
        // --- שלב ג': כפל נוסף (שרשור) ---
        // המצב הנוכחי הוא -10. נכפיל עכשיו ב-3.
        // ציפייה: -10 * 3 = -30
        map.mul(3.0);
        assertEquals(-30, map.getPixel(0, 0), "Failed chained mul: Expected -30");

        // --- שלב ד': בדיקת עיגול (בונוס) ---
        // ציפייה: -30 * 0.5 = -15
        map.mul(0.5);
        assertEquals(-15, map.getPixel(0, 0), "Failed rounding check");
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