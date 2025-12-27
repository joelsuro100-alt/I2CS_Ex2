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
        int w = getWidth(); //get map width
        int h = getHeight(); //get map height

        for (int x = 0; x < w; x++) { //go over every column
            for (int y = 0; y < h; y++) { //go over every row
                int oldValue = getPixel(x, y); //get current value
                double newDouble = oldValue * scalar; //multiply by scalar
                int newValue = (int) Math.round(newDouble); //round to nearest int
                setPixel(x, y, newValue); //save back to map
            }
        }
    }
    @Override
    public void rescale(double sx, double sy) {
        int oldW = getWidth(); //original width
        int oldH = getHeight(); //original height

        int newW = (int) Math.round(oldW * sx); //calculate new x dimensions & round to nearest int
        int newH = (int) Math.round(oldH * sy); //calculate new y dimensions & round to nearest int
        if (newW == oldW && newH == oldH) { //if size didn't change do nothing
            return;
        }
        int[][] newMap = new int[newW][newH]; //create a new array with the new size
        for (int x = 0; x < newW; x++) { //go over every pixel in the new map and check that its inside borders and which pixel this corresponds to
            for (int y = 0; y < newH; y++) {
                double oldX = x / sx;  // reverse scaling
                double oldY = y / sy;
                int srcX = (int) Math.round(oldX); //take the nearest pixel and round to int
                int srcY = (int) Math.round(oldY);

                srcX = Math.max(0, Math.min(srcX, oldW - 1));//make sure it's inside the old map bounds
                srcY = Math.max(0, Math.min(srcY, oldH - 1));
                newMap[x][y] = _map[srcX][srcY]; //copy the value from old map
            }
        }
        this._map = newMap; //replace the old map with the new one
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
        if (getPixel(p1) == obsColor || getPixel(p2) == obsColor) return null; //check if the end or begging are the walls
        int w = getWidth();
        int h = getHeight();

        int[][] dist = new int[w][h]; //Distance array
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                dist[x][y] = -1;  // mark all as not visited
            }
        }
        Pixel2D[][] parent = new Pixel2D[w][h]; //array to remember from which pixel we came to each pixel
        int[] dx = {1, -1, 0, 0}; //possible moves 4 ways-right, left, down, up
        int[] dy = {0, 0, 1, -1};

        ArrayList<Pixel2D> queue = new ArrayList<>(); //queue for BFS
        queue.add(p1);                                //add starting point
        dist[p1.getX()][p1.getY()] = 0;                //distance 0 at start
        parent[p1.getX()][p1.getY()] = null;           //no parent for start
        while (!queue.isEmpty()) { //main BTS loop
            Pixel2D curr = queue.remove(0);  // take the first pixel in queue (FIFO System)
            if (curr.equals(p2)) { //if we reached the end rebuild and return the path
                return reconstructPath(parent, p1, p2);
            }
            for (int i = 0; i < 4; i++) { //check all 4 neighbors
                int nx = curr.getX() + dx[i];
                int ny = curr.getY() + dy[i];

                if (cyclic) {  //handle cyclic world - wrap around edges
                    nx = (nx + w) % w;
                    ny = (ny + h) % h;
                }
                if (!cyclic && (nx < 0 || nx >= w || ny < 0 || ny >= h)) { //if not cyclic, check if neighbor are inside the map
                    continue;  //if they are inside then continue
                }
                Pixel2D neighbor = new Index2D(nx, ny);

                if (getPixel(neighbor) != obsColor && dist[nx][ny] == -1) { //only enter if it's not an obstacle and we haven't been here before
                    dist[nx][ny] = dist[curr.getX()][curr.getY()] + 1;  // one step further
                    parent[nx][ny] = curr;                              // remember who brought us here
                    queue.add(neighbor);                                // add to queue for later exploration
                }
            }
        }
        return null; //finish the loop without finding the end then there is no path
    }
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        int w = getWidth(); //get width
        int h = getHeight(); //get height
        Map2D distMap = new Map(w, h, -1); //create a new map for the distances where all pixels start with -1=unreachable

        if (getPixel(start) == obsColor) { //if start is obstacle do nothing
            return distMap; //return all -1
        }
        int[] dx = {1, -1, 0, 0}; //4 moves-right, left, down, up
        int[] dy = {0, 0, 1, -1};

        ArrayList<Pixel2D> queue = new ArrayList<>(); //BFS queue like shortestpath
        queue.add(start); //start from the source
        distMap.setPixel(start, 0); //distance 0 at start

        while (!queue.isEmpty()) { //same BFS as shortestPath
            Pixel2D curr = queue.remove(0); //take first in queue
            for (int i = 0; i < 4; i++) { //check 4 neighbors
                int nx = curr.getX() + dx[i];
                int ny = curr.getY() + dy[i];
                if (cyclic) { //wrap around if cyclic
                    nx = (nx + w) % w;
                    ny = (ny + h) % h;
                }
                if (!cyclic && (nx < 0 || nx >= w || ny < 0 || ny >= h)) { //check out of bounds
                    continue; //skip
                }
                Pixel2D neighbor = new Index2D(nx, ny);
                if (getPixel(neighbor) != obsColor && distMap.getPixel(neighbor) == -1) { //only go to free pixels that we haven't visited yet
                    int newDist = distMap.getPixel(curr) + 1; //one more than parent
                    distMap.setPixel(neighbor, newDist); //save distance
                    queue.add(neighbor); //add to queue
                }
            }
        }

        return distMap; //return the full distance map
    }
	////////////////////// Private Methods ///////////////////////
    /**
     * Helper function for shortestPath -switches the path by going backwards from end to start using parents
     */
    private Pixel2D[] reconstructPath(Pixel2D[][] parent, Pixel2D start, Pixel2D end) {
        ArrayList<Pixel2D> path = new ArrayList<>();
        Pixel2D current = end;
        while (current != null) { //set the path backwards -end to start
            path.add(current); //add current pixel
            current = parent[current.getX()][current.getY()]; //go to parent
        }
        Pixel2D[] correctPath = new Pixel2D[path.size()];  //Create a new array for the correct order
        for (int i = 0; i < path.size(); i++) { //Copy elements from the end of the list to the beginning of the array
            correctPath[i] = path.get(path.size() - 1 - i); //for i0 last element= start, i1= last-1-index
        }
        return correctPath;
    }
}
