package Algorithms.PlaneSweepHelpers;

//TODO: Implement this class
public class Rectangle {
    //Store sides of Rectangle
    public float left, right, bottom, top;

    //Constructor
    public Rectangle(float _left, float _right, float _bottom, float _top){
        left = _left;
        right = _right;
        bottom = _bottom;
        top = _top;
    }

    //Method to test if this rectangle intersects with another Rectangle
    public boolean intersects(Rectangle otherRectangle){
        return (this.left < otherRectangle.right && this.right > otherRectangle.left &&
                this.top > otherRectangle.bottom && this.bottom < otherRectangle.top);
    }
}
