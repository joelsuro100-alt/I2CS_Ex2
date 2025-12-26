package assignments.Ex2;
import java.awt.*;
import java.awt.Color;
import static javax.swing.UIManager.getColor;

/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * The class has save and load functions, and a GUI draw function.
 * You should implement this class, it is recommender to use the StdDraw class, as in:
 * https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 *
 *
 */
public class Ex2_GUI {
    /**
     * Draws the map using StdDraw.
     *
     * @param map The map to draw.
     */
    public static void drawMap(Map2D map) {
        int w = map.getWidth(); //set the window of the map
        int h = map.getHeight();
        StdDraw.setCanvasSize(Math.min(w * 20, 1000), Math.min(h * 20, 800)); //each pixel= 20 pixel so we can see it clearly
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);
        // שיפור ביצועים: מציירים הכל בזיכרון ורק בסוף מראים למסך (Double Buffering)
        StdDraw.enableDoubleBuffering(); //enhance performing by first drawing

        // 2. עוברים על כל המפה
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int v = map.getPixel(x, y); // איזה מספר יש פה?
                Color c = getColor(v);      // איזה צבע זה אומר?
                StdDraw.setPenColor(c);
                // 3. מציירים ריבוע
                // ב-StdDraw הציור הוא לפי המרכז, לכן מוסיפים 0.5
                StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
            }
        }
        // מציגים את הציור הסופי
        StdDraw.show();
    }

    /**
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) {
        Map2D ans = null;

        return ans;
    }

    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {


    }

    public static void main(String[] a) {
        /*String mapFile = "map.txt";
        Map2D map = loadMap(mapFile);
        drawMap(map);
    }*/
        // 1. יוצרים מפה פשוטה
        Map2D map = new Map(20, 20, 0); // מפה 20x20 לבנה

        // 2. מציירים עליה דברים (משתמשים בפונקציות שכתבת ב-Map!)
        map.setPixel(0, 0, 1); // נקודה שחורה בפינה

        // נצייר קיר באמצע
        map.drawRect(new Index2D(5, 5), new Index2D(15, 6), 1);

        // נצייר עיגול אדום
        map.drawCircle(new Index2D(10, 15), 3, 4);

        // נצייר קו כחול
        map.drawLine(new Index2D(0, 19), new Index2D(19, 0), 3);

        // נפעיל את דלי הצבע (מילוי בצהוב)
        map.fill(new Index2D(10, 15), 5, false);

        // 3. שולחים לציור הגרפי!
        drawMap(map);
    }
    /// ///////////// Private functions ///////////////
    private static Color getColor(int v) {
        switch(v) {
            case 0: return StdDraw.WHITE;   //white background
            case 1: return StdDraw.BLACK;   //black background
            case 2: return StdDraw.BLUE;    //blue background
            case 3: return StdDraw.RED;     //red background
            case 4: return StdDraw.GREEN;   //green background
            case 5: return StdDraw.YELLOW;  //yellow background
            default: return StdDraw.LIGHT_GRAY; //default graw
        }
    }
}
