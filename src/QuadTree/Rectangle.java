
package QuadTree;

import org.joml.Vector3f;

public class Rectangle {   
    public float x , y , w , h;
    public Rectangle(float x , float y , float w , float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
   
    }
    public boolean contains(Vector3f vect){
        return vect.x >= (x - w) && vect.x < (x + w) && vect.y >= (y - h) && vect.y < (y + h); 
    }
    public boolean intersect_type_1(Rectangle range) {
        return !(range.x - range.w > x + w  ||  range.x + range.w < x - w ||  range.y - range.h > y + h  ||  range.y + range.h < y - h);
    }
    public boolean intersect_type_2(Rectangle range) {
        return Math.abs(range.x - x) < w && Math.abs(range.y - y) < h;
    }    
}
