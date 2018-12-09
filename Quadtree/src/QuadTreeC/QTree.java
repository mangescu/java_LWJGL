
package QuadTreeC;

import java.util.ArrayList;

public class QTree {
   public Rectangle  boundary ,  nw , ne , sw , se;
   public int cap;
   public ArrayList<Point> pointsArray;
   public QTree northWest , northEast , southWest , southEast;   
   public boolean divided = false; 

   public  QTree(Rectangle  bound , int cap) {
        this.boundary = bound;
        this.cap = cap;
        pointsArray = new ArrayList<Point>();     
   }
   public void insert(Point point) {      
       if(!boundary.contains(point))  {
           return ;
       }
       if(pointsArray.size() < cap) {
          pointsArray.add(point);
          return;
       } else {
           if(!divided) {
                subdivide();
           }
        northEast.insert(point);   
        northWest.insert(point);     
        southEast.insert(point);
        southWest.insert(point); 
       }
   }
   public void subdivide(){
        ne = new Rectangle(boundary.x + boundary.w/2 , boundary.y - boundary.h/2 , boundary.w/2 , boundary.h/2);
        nw = new Rectangle(boundary.x - boundary.w/2 , boundary.y - boundary.h/2 , boundary.w/2 , boundary.h/2);
        se = new Rectangle(boundary.x + boundary.w/2 , boundary.y + boundary.h/2 , boundary.w/2 , boundary.h/2);
        sw = new Rectangle(boundary.x - boundary.w/2 , boundary.y + boundary.h/2 , boundary.w/2 , boundary.h/2);
        
        northEast = new QTree(ne,cap);
        northWest = new QTree(nw,cap);
        southEast = new QTree(se,cap);
        southWest = new QTree(sw,cap);
        
        divided = true;  
   }
}
