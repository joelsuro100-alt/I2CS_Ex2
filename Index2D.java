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
        this._x = other.getX(); //take the objects x and put in mine
        this._y = other.getY(); //take the objects y and put in mine

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
     double len1 = this._x - p2.getX() ;
     double len2 = this._y - p2.getY() ;
        return Math.sqrt((len1 *len1) + (len2 *len2));
    }

    @Override
    public String toString() {
        return "("+ _x +"," + _y +")";
    }

    @Override
    public boolean equals(Object p) {
        if (p==null || !(p instanceof Pixel2D)) {return false;} //check if p is null or not a type
        Pixel2D t = (Pixel2D) p;
        return (this.getX()==t.getX() && this.getY()==t.getY());
    }
}
