
package QuadTree;

import java.util.ArrayList;
import org.joml.Vector3f;
import spacecomander.Quad;

public class QTree {
   public Rectangle  boundary ,  nw , ne , sw , se;
   public int cap;
   public ArrayList<Quad> pointsArray;
   public QTree northWest , northEast , southWest , southEast;   
   public boolean divided = false; 

   public  QTree(Rectangle  bound , int cap) {
        this.boundary = bound;
        this.cap = cap;
        pointsArray = new ArrayList<Quad>();     
   }
   public void insert(Quad quad) {      
       if(!boundary.contains(quad.curentPosition))  {
           return ;
       }
       if(pointsArray.size() < cap) {
          pointsArray.add(quad);
          return ;
       } else {
           if(!divided) {
                subdivide();
           }
        northEast.insert(quad);   
        northWest.insert(quad);     
        southEast.insert(quad);
        southWest.insert(quad); 
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
