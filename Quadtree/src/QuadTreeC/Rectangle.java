
package QuadTreeC;

public class Rectangle {   
    public int x , y , w , h , w_2 , h_2;
    public Rectangle(int x , int y , int w , int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        w_2 = w/2;
        h_2 = h/2;        
    }
    public boolean contains(Point point){
        return point.x >= (x - w) && point.x < (x + w) && point.y >= (y - h) && point.y < (y + h); 
    }
    public boolean intersect_type_1(Rectangle range) {
        return !(range.x - range.w > x + w  ||  range.x + range.w < x - w ||  range.y - range.h > y + h  ||  range.y + range.h < y - h);
    }
    public boolean intersect_type_2(Rectangle range) {
        return Math.abs(range.x - x) < w && Math.abs(range.y - y) < h;
    }    
}
