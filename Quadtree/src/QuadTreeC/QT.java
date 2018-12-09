/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuadTreeC;

import java.util.ArrayList;


public class QT {
    
public  ArrayList<Point> arrayFound , arrayDataPoint;    
public  Rectangle range ;    
public  QTree  qTree;   
private Point  point;

public QT() {

    arrayFound = new ArrayList<Point>(); 
    arrayDataPoint = new ArrayList<Point>(); 
    range = new Rectangle(400 , 400 , 400 , 400);
    qTree   =   new  QTree(new Rectangle(400, 400, 400, 400) , 4);
    
}
public void insertArrayData(int x , int y){
  point = new Point(x , y);
  arrayDataPoint.add(point);
}
public void reloadTree()  {
    
    qTree = null;
    qTree =  new  QTree(new Rectangle(400, 400, 400, 400) , 4);
    
    for(int i=0 ; i< arrayDataPoint.size() ; i++) {                
        qTree.insert(arrayDataPoint.get(i));
    }
}
public void setRangeParam(int w) {
    range.w = w;
    range.h = w;
    
    range.w_2 = w/2;
    range.h_2 = w/2;
}
public void getRangePoints(int x , int y) {
   range.x = x;
   range.y = y;
   
   arrayFound.clear();
   query(qTree);
}
public void  query(QTree tree) {
    if(tree != null)  {
        if(!tree.boundary.intersect_type_1(range))  { 
            return;
        } else {
           for(Point p : tree.pointsArray) {
              if(range.contains(p)) {
                  arrayFound.add(p);
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
 

    
}
