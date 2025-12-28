package assignments.Ex2;
import java.awt.*;
import java.awt.Color;
import java.io.*;

/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * The class has save and load functions, and a GUI draw function.
 * You should implement this class, it is recommender to use the StdDraw class, as in:
 * https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 *
 */
public class Ex2_GUI {
    /**
     * Draws the map using StdDraw.
     *
     * @param map The map to draw.
     */
    public static void drawMap(Map2D map) {
        int w = map.getWidth(); //get the width of the map
        int h = map.getHeight(); //get the height of the map
        StdDraw.setCanvasSize(Math.min(w * 20, 1000), Math.min(h * 20, 800)); //make each pixel 20x20 but limit window size
        StdDraw.setXscale(0, w); //set x from 0 to width
        StdDraw.setYscale(0, h); //set y from 0 to height
        StdDraw.enableDoubleBuffering(); //draw everything in memory first for better speed
        for (int x = 0; x < w; x++) { //go over every column
            for (int y = 0; y < h; y++) { //go over every row
                int v = map.getPixel(x, y); //get the value at this pixel
                Color c = getColor(v); //convert value to color
                StdDraw.setPenColor(c); //set the color for drawing
                StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5); //draw square at center of pixel
            }
        }
        StdDraw.show(); //finally show everything on screen
    }

    /**
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) {
        try {
            java.util.Scanner scanner = new java.util.Scanner(new java.io.File(mapFileName)); //open the file
            // Read first line: width and height
            int w = scanner.nextInt(); //get width
            int h = scanner.nextInt(); //get height
            scanner.nextLine(); //go to next line after numbers
            // Create new map with white background (0)
            Map2D map = new Map(w, h, 0);

            // Read each row
            for (int y = h - 1; y >= 0; y--) { //start from top row (because in file top is first)
                String line = scanner.nextLine(); //read whole line
                String[] values = line.trim().split("\\s+"); //split by spaces

                for (int x = 0; x < w; x++) { //go over each number
                    int v = Integer.parseInt(values[x]); //convert string to int
                    map.setPixel(x, y, v); //put in map (note: y from top!)
                }
            }
            scanner.close(); //close the file
            return map;
        } catch (Exception e) { //if file not found or wrong format
            System.err.println("Error loading map from " + mapFileName + ": " + e.getMessage());
            return null; //return null if failed
        }
    }
    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {
        try {
            java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.File(mapFileName)); //open file for writing

            int w = map.getWidth(); //get width
            int h = map.getHeight(); //get height
            writer.println(w + " " + h); //first line- width + height

            // Write all rows, from top to bottom
            for (int y = h - 1; y >= 0; y--) { //start from top row
                for (int x = 0; x < w; x++) { //go over each column
                    writer.print(map.getPixel(x, y)); //write value
                    if (x < w - 1) {
                        writer.print(" "); //space between numbers
                    }
                }
                writer.println(); //new line after each row
            }
            writer.close(); //close the file
            System.out.println("Map saved successfully to " + mapFileName);
        } catch (Exception e) { //if you cannot write to file
            System.err.println("Error saving map to " + mapFileName + ": " + e.getMessage());
        }
    }
    public static void main(String[] a) {
        String mapFile = "map.txt"; //the file name
        Map2D mapFromFile = loadMap(mapFile); //try to load the map
        if (mapFromFile != null) { //if loading worked
            System.out.println("Loaded map from " + mapFile + ", drawing it now...");
            drawMap(mapFromFile); //show the loaded map
        } else {
            System.out.println("Could not load map from " + mapFile + ", creating a test map instead");

            Map2D map = new Map(60, 60, 0); //white map 60x60
            map.drawRect(new Index2D(27, 12), new Index2D(33, 48), 1); //black rectangle obstacle middle

            Pixel2D start = new Index2D(6, 30);
            Pixel2D end = new Index2D(51, 30);
            Pixel2D[] path = map.shortestPath(start, end, 1, false); //calc the shortestPath

            if (path != null) {
                System.out.println("Path found! Length: " + path.length);
                for (Pixel2D p : path) {
                    map.setPixel(p, 4); //line color green
                }
            } else {
                System.out.println("No path found!");
            }
            map.setPixel(start, 2); //pixel start and finish blue
            map.setPixel(end, 2);
            map.drawRect(new Index2D(51, 0), new Index2D(57, 6), 3); //red rectangle bottom right
            map.drawCircle(new Index2D(9, 9), 6, 5); //yellow circle bottom left

            saveMap(map, mapFile); //save the map for next runtime
            drawMap(map); //draw the map that we made
        }
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
