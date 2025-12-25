package assignments.Ex2;
import java.io.Serializable;
/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{
    //TODO

	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
        private int[][] _map;
	public Map(int w, int h, int v) {init(w, h, v);}
        /**
         * Constructs a square map (size*size).
         * @param size
         */
	public Map(int size) {this(size,size, 0);}
	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}
	@Override
	public void init(int w, int h, int v) {
        this._map = new int[w][h]; //create the double array with the w&h
        for (int x = 0; x < w; x++) { //go over the width
            for (int y = 0; y < h; y++) { //go over the height
                this._map[x][y] = v; //put the V inside the double array
            }
        }
    }

	@Override
	public void init(int[][] arr) {
    if (arr == null || arr.length == 0){ //check if the array is null
        throw new RuntimeException();}
        int w = arr.length; //take our w&h from the array we got
        int h = arr[0].length;
        this._map = new int[w][h]; //create oir new array
        for (int x = 0; x < w; x++) //deep copy for the new array
            for (int y = 0; y <h; y++)
                this._map[x][y] = arr[x][y];
	}
    @Override
	public int[][] getMap() {
        int w = this.getWidth();
        int h = this.getHeight();
        int[][] ans = new int[w][h];

        for (int x = 0; x < w; x++) //deep copy for the new array
            for (int y = 0; y <h; y++)
                ans[x][y] = this._map[x][y];
        return ans;
    }

    @Override
    public int getWidth() {
        return this._map.length;
    }
    @Override
	public int getHeight() {
        return this._map[0].length; //whats the leng in index 0(will be the same for all of them
    }
	@Override
	public int getPixel(int x, int y) {
        return this._map[x][y];
    }
	@Override
	public int getPixel(Pixel2D p) {
        int x = p.getX(); //get the x cord
        int y = p.getY(); //get the y cord
        return this._map[x][y]; //go to the map with the x&y extracted
	}
    @Override
    public void setPixel(int x, int y, int v) {
        if (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight()) {
            this._map[x][y] = v;
        }
    }
    @Override
	public void setPixel(Pixel2D p, int v) {
    if (isInside(p)){
        this.setPixel(p.getX(), p.getY(), v);
        }
	}

    @Override
    public boolean isInside(Pixel2D p) {
        int x = p.getX();
        int y = p.getY();
        if (x < 0 || x >= (this.getWidth())){return false;} ;
        if (y < 0 || y >= (this.getHeight())){return false;} ;
            return true;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = false;

        return ans;
    }

    @Override
    public void addMap2D(Map2D p) {

    }

    @Override
    public void mul(double scalar) {

    }

    @Override
    public void rescale(double sx, double sy) {

    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {
    int centerX = center.getX(); //take the center point x&y value
    int centerY = center.getY();
    int r = (int) rad;

    int rec_startX = centerX - r; //the bottom left X of the rec outside the circle
    int rec_startY = centerY - r; //the bottom left Y of the rec outside the circle
    int rec_endX = centerX + r; //the top right X of the rec outside the circle
    int rec_endY = centerY + r; //the top right X of the rec outside the circle
        for (int x = rec_startX; x <= rec_endX; x++) { //go ovel all the rec
            for (int y = rec_startY; y <= rec_endY; y++) {
                double dx = x - centerX;
                double dy = y - centerY;
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance < rad) {
                    this.setPixel(x, y, color);
                }
            }
        }
    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        int xmin = Math.min(p1.getX(), p2.getX()); //find the min x point
        int xmax   = Math.max(p1.getX(), p2.getX()); //find the max x point
        int ymin = Math.min(p1.getY(), p2.getY()); //find the min x point -the down left corner
        int ymax   = Math.max(p1.getY(), p2.getY()); //find the max x point -the upper right corner

        for (int x = xmin; x <= xmax; x++) { //double loop for the hole area
            for (int y = ymin; y <= ymax; y++) {
                this.setPixel(x, y, color); //color each pixel with setPixesl function
            }
        }
    }

    @Override
    public boolean equals(Object ob) {
        boolean ans = false;

        return ans;
    }
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
		int ans = -1;

		return ans;
	}

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
		Pixel2D[] ans = null;  // the result.

		return ans;
	}
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        return ans;
    }
	////////////////////// Private Methods ///////////////////////

}
