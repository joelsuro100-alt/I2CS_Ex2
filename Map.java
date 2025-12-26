package assignments.Ex2;
import java.io.Serializable;
import java.util.ArrayList;

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
        return this.getWidth() == p.getWidth() && this.getHeight() == p.getHeight();
    }
    @Override
    public void addMap2D(Map2D p) {
        if (sameDimensions(p)){ //check that there the same dim
            for (int x = 0; x < getWidth(); x++) {
                for (int y = 0; y < getHeight(); y++) {
                    int valaue = this.getPixel(x, y) + p.getPixel(x, y); //take the existing x&y add to second map
                    this.setPixel(x, y, valaue);
                }
            }
        }
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
            for (int x = rec_startX; x <= rec_endX; x++) { //go over all the rec
                for (int y = rec_startY; y <= rec_endY; y++) {
                    double dx = x - centerX;
                    double dy = y - centerY;
                    double distance = Math.sqrt(dx * dx + dy * dy); //go over only the x,y that are in the dis of the circle
                        if (distance < rad) { //color only the pixsel that are inside the map & the circle
                            this.setPixel(x, y, color);
                }
            }
        }
    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {
        int x1 = p1.getX(); //the begging x&y
        int y1 = p1.getY();
        int x2 = p2.getX(); //the end x&y
        int y2 = p2.getY();

        if (x1 == x2 && y1 == y2) { //check if the points are equal
            this.setPixel(x1, y1, color);
            return;
        }
        int dx = Math.abs(x2 - x1); //calcs the distance between x
        int dy = Math.abs(y2 - y1); //calcs the y distance between y
        if (dx >= dy) { //if the dis from x >= than dis y
            int startX = Math.min(x1, x2); //start from the smallest x to largest
            int endX   = Math.max(x1, x2);
            for (int x = startX; x <= endX; x++) {//calc the y with the formula: Y = Y1 + Slope * (currentX - X1)
                double y = y1 + ((double)(y2 - y1) / (x2 - x1)) * (x - x1);
                this.setPixel(x, (int)Math.round(y), color); //casting to int
            }
        }
        else { //if the dis y > dis x
            int startY = Math.min(y1, y2); //start from the smallest y to largest
            int endY   = Math.max(y1, y2);
            for (int y = startY; y <= endY; y++) { //calc the x with the formula: X = X1 + (1/Slope) * (currentY - Y1)
                double x = x1 + ((double)(x2 - x1) / (y2 - y1)) * (y - y1);
                this.setPixel((int)Math.round(x), y, color); //casting to int
            }
        }
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
        if (!(ob instanceof Map2D)){return false;} //check if its even has the properties of map
        Map2D other = (Map2D) ob; //convert to map(object can be anything)
        if (!sameDimensions(other)) { return false; } //check if after converting its equal
        for (int x = 0; x < getWidth(); x++) { //we check that all the pixels are equal
            for (int y = 0; y < getHeight(); y++) {
                if (this.getPixel(x, y) != other.getPixel(x, y)) {
                    return false; //we found 1 pixel not equal
                }
            }
        }
        return true; //if in the end it's all equal.
    }
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
     * I asked chatgpt to help me write fill with an arraylist, and it suggested with Uses of BFS/DFS iterative approach.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
        int targetColor = this.getPixel(xy); //keep origin color to know what to replace
        if (targetColor == new_v) return 0; //check if the color is the same as the old then nothing to do
        ArrayList<Pixel2D> todoList = new java.util.ArrayList<>(); //create a todolist &counter
        todoList.add(xy);
        this.setPixel(xy, new_v); // color the first one
        int count = 1;
        int[] dx = {1, -1, 0, 0}; //arrays that will help find the neighbors of the target
        int[] dy = {0, 0, 1, -1};
        while (!todoList.isEmpty()) { //Main loop as long as there are tasks in the list
            Pixel2D current = todoList.remove(todoList.size() - 1); //take the last pixsel that we put in
            for (int i = 0; i < 4; i++) { //go over its neighbors with the arrays that we made
                int nextX = current.getX() + dx[i];
                int nextY = current.getY() + dy[i];
                if (cyclic) { //use of world cycle
                    nextX = (nextX + this.getWidth()) % this.getWidth(); //we take care of negative number outcome
                    nextY = (nextY + this.getHeight()) % this.getHeight();
                }
                Pixel2D neighbor = new Index2D(nextX, nextY); // create a new point
                if (isInside(neighbor) && getPixel(neighbor) == targetColor) { //check if the nighbor is inside the map and orofing color
                    this.setPixel(neighbor, new_v); // color so it won't be adding twice
                    todoList.add(neighbor);         // add todolist
                    count++;                        // update counter
                }
            }
        }
        return count;
	}
	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
        // 0. בדיקות מקדימות: אם ההתחלה או הסוף הם קירות - אין מסלול
        if (getPixel(p1) == obsColor || getPixel(p2) == obsColor) return null;
        int w = getWidth();
        int h = getHeight();

        // מערך עזר לשמירת המרחקים מ-p1. מאתחלים ב-1- (לא ביקרנו)
        int[][] distances = new int[w][h]; //instead of t/f we keep in array and each index is a neighbor
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                distances[i][j] = -1; //we didn't make it yet
            }
        }

        // --- שלב 1: BFS (התפשטות הגל) ---
        java.util.ArrayList<Pixel2D> queue = new java.util.ArrayList<>();

        // התחלה ב-p1
        distances[p1.getX()][p1.getY()] = 0;
        queue.add(p1);

        int[] dx = {1, -1, 0, 0}; // וקטורי תנועה (ימין, שמאל, למטה, למעלה)
        int[] dy = {0, 0, 1, -1};

        boolean found = false;

        while (!queue.isEmpty()) {
            Pixel2D current = queue.remove(0); // שליפה מהראש (FIFO)
            if (current.equals(p2)) { // הגענו ליעד!
                found = true;
                break;
            }
            // מעבר על 4 השכנים
            for (int i = 0; i < 4; i++) {
                int nextX = current.getX() + dx[i];
                int nextY = current.getY() + dy[i];

                // טיפול במצב Cyclic (עולם עגול)
                if (cyclic) {
                    nextX = (nextX + w) % w;
                    nextY = (nextY + h) % h;
                }

                // יצירת הנקודה השכנה (נניח ש-Index2D הוא המימוש שלך ל-Pixel2D)
                Pixel2D neighbor = new Index2D(nextX, nextY);

                // התנאים לכניסה: הנקודה בתוך המפה, היא לא מכשול, ועוד לא ביקרנו בה
                if (isInside(neighbor) && getPixel(neighbor) != obsColor && distances[nextX][nextY] == -1) {
                    distances[nextX][nextY] = distances[current.getX()][current.getY()] + 1;
                    queue.add(neighbor); // הוספה לתור להמשך סריקה
                }
            }
        }
        // אם סיימנו את הלולאה ולא מצאנו את היעד - אין דרך
        if (!found) return null;
        // --- שלב 2: Backtracking (שחזור המסלול מהסוף להתחלה) ---
        int distance = distances[p2.getX()][p2.getY()];
        Pixel2D[] path = new Pixel2D[distance + 1]; // גודל המסלול הוא המרחק + 1 (כולל ההתחלה)

        Pixel2D current = p2;
        path[distance] = current; // שמים את הסוף בתא האחרון
        // לולאה שיורדת מהמרחק המקסימלי עד 0
        for (int d = distance - 1; d >= 0; d--) {
            // מחפשים שכן שהמרחק שלו הוא בדיוק d
            for (int i = 0; i < 4; i++) {
                int prevX = current.getX() + dx[i];
                int prevY = current.getY() + dy[i];

                if (cyclic) {
                    prevX = (prevX + w) % w;
                    prevY = (prevY + h) % h;
                }

                // אם השכן חוקי והמרחק שלו הוא הצעד הקודם שחיפשנו
                if (isInside(new Index2D(prevX, prevY)) && distances[prevX][prevY] == d) {
                    current = new Index2D(prevX, prevY);
                    path[d] = current; // רושמים אותו במסלול
                    break; // מצאנו את הצעד הזה, אפשר לעבור לצעד הבא
                }
            }
        }
        return path;
    }
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        return ans;
    }
	////////////////////// Private Methods ///////////////////////

}
