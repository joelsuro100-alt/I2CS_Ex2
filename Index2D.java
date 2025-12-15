package assignments.Ex2;

public class Index2D implements Pixel2D {
    private int _x ; //create my own private x&y to not mess with what I get from user
    private int _y ;
    /** constructor for my X & Y
     *
     * @param w
     * @param h
     */
    public Index2D(int w, int h) {
        this._x = w; //this. means get the w and turn it inro my X only for me- the user sets w and for me it will be x
        this._y = h; //this. means get the h and turn it inro my Y only for me- the user sets h and for me it will be y
    }
    public Index2D(Pixel2D other) {
        this._x = this.getX(); //open a window for the user to see my _x
        this._y = this.getY(); //open a window for the user to see my _y
    }
    @Override
    public int getX() {
        return this._x; //give the user what is saved for me by _x but dont change it
    }

    @Override
    public int getY() {
        return this._y; //give the user what is saved for me by _y but dont change it
    }

    /**
     * calc the distance between 2 points with the equation- https://en.wikipedia.org/wiki/Euclidean_distance
     * @param p2
     * @return
     */
    @Override
    public double distance2D(Pixel2D p2) {
     int len1 = this._x - this.getX();
     int len2 = this._y - this.getY();
        return Math.sqrt((len1 *len1) + (len2 *len2));
    }

    @Override
    public String toString() {
        return "("+ this.getX() +"," + this.getY() +")";
    }

    @Override
    public boolean equals(Object p) {
        boolean ans = true;

        return ans;
    }
}
