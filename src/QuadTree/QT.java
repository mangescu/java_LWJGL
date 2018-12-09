
package QuadTree;

import java.util.ArrayList;
import org.joml.Vector3f;
import spacecomander.Quad;


public class QT {
    
public  ArrayList<Quad> arrayFound , arrayDataPoint;    
public  Rectangle range ;    
public  QTree     qTree;   
public  int player_id;


public QT() {

    arrayFound = new ArrayList<Quad>(); 
    arrayDataPoint = new ArrayList<Quad>(); 
    range = new Rectangle(0,0,0,0);
    qTree   =   new  QTree(new Rectangle(0, 0, 10f, 10f) , 4);
    
}
public void insertArrayData(Quad quad){
  arrayDataPoint.add(quad);
}
public void reloadTree()  {
    
   // qTree = null;
    qTree =  new  QTree(new Rectangle(0, 0, 10f, 10f) , 4);
    
    for(int i=0 ; i< arrayDataPoint.size() ; i++) {                
        qTree.insert(arrayDataPoint.get(i));
    }
}
public void setRangeParam(float w) {
    range.w = w;
    range.h = w;
}
public void getRangePoints(float x , float y , int player_id) {
   range.x = x;
   range.y = y;
   this.player_id = player_id;
   arrayFound.clear();
   query(qTree);
   
}
public void  query(QTree tree) {
    if(tree != null)  {
        if(!tree.boundary.intersect_type_1(range))  { 
            return;
        } else {
           for(Quad quad : tree.pointsArray) {
              if(range.contains(quad.curentPosition) && quad.player.index != player_id) {
                  arrayFound.add(quad);
              }  
           }
        }
        if(tree.divided)  {
            query(tree.northEast);
            query(tree.northWest);
            query(tree.southEast);
            query(tree.southWest);      
        }
    }
}     
private void preOrder(QTree tree)
{
   // System.out.println(" ");

    if(tree != null)
    {
       // System.out.println(tree.boundary.x+"   "+tree.boundary.y+"        "+tree.boundary.w);
     
        preOrder(tree.northEast);
        preOrder(tree.northWest);
        preOrder(tree.southEast);
        preOrder(tree.southWest);
    }
} 

    
}
