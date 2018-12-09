
package quadtree;

import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import QuadTreeC.*;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;






public class Quadtree {

    QT qt = new QT();

    Random rand = new Random();
    
    JFrame frame;
   

    
    Graphics gr;
    
    MouseListener   mouseL;
    

    
    //______FPS___________________________________________________________//
    static double start = System.nanoTime() / 1000000000;
    static float senzitiviti = 0.2f;
    static int valoare = 0;
    static double end;
//____________________________________________________________________// 
    
    
    drawGraphics graphics;
    
    public static void main(String[] args) throws IOException, InterruptedException {   
        Quadtree   qT = new Quadtree();             
                   qT.interfata();
                   qT.qTree();      
    }

    public Quadtree() {
        
    }
    
    
    
    
    public void interfata() throws IOException{
    
        frame = new JFrame();
	frame.setLayout(null);
        graphics = new drawGraphics();
        graphics.qt = qt;
        
        mouseL = new MouseListener() {
      
            public void mouseClicked(MouseEvent e) {    
                
                qt.insertArrayData(e.getX() , e.getY());
                qt.reloadTree();
                graphics.repaint();
            }
            public void mousePressed(MouseEvent e) {} 
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        };
        
        

        graphics.addMouseListener(mouseL);
        frame.setContentPane(graphics);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(900, 900);
        frame.setLocation(900, 20);
	frame.setVisible(true);      
  
    }
    public   void  qTree() throws IOException, InterruptedException {
    
     
        qt.arrayDataPoint.clear();
        for(int i=0 ; i< 200 ; i++)  {
           // qt.insertArrayData(rand.nextInt(800) , rand.nextInt(800));
        }
        qt.reloadTree();
        boolean ok = true;
        
           qt.setRangeParam(200);
           qt.range.x = rand.nextInt(800);
           qt.range.y = rand.nextInt(800);
           
           int count = 0;
           
           
           qt.getRangePoints(400 , 400);
           
           
  //  while(ok) {
        
           qt.reloadTree();
          
             for(int i=0 ; i< qt.arrayDataPoint.size() ; i++)
             {
                 Point p1 = qt.arrayDataPoint.get(i);

                    qt.getRangePoints(p1.x , p1.y);
                  
                    for(int j=0 ; j< qt.arrayFound.size() ; j++)
                    {
                        Point p2 = qt.arrayFound.get(j);
                        
                        if(p1.x != p2.x || p1.y != p2.y)
                        {  
                            float distance  = (float) Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
                            if(distance < 10)
                            {
                               p1.colide = true;
                               p2.colide = true;
                            }
                            count ++;
                        }    
                    }
              }
   

  //     FPS(count);
                   
  
        
        graphics.repaint();
 //  }

        
    }  
public void FPS(int count) {
                 // FPS____________________________________________________________________//           
                   //   Display.sync(100); 
                        end = System.nanoTime() / 1000000000;
                        if (end - start > 1) {
                            start = end;
                            System.out.println(valoare / 2+"   "+qt.arrayFound.size()+"     "+count+"      qt.dataarray = "+qt.arrayDataPoint.size());
                            valoare = 0;
                        }
                        valoare++;
                   // __________________________________________________________________________// 
}
public void paintSelectedPoints(){
    gr.setColor(Color.RED); 
    gr.drawRect(qt.range.x -qt.range.w, qt.range.y -qt.range.w, qt.range.w*2, qt.range.h*2);
    for(Point p : qt.arrayFound) {
       
       gr.fillOval(p.x -4, p.y -4, 8, 8);
    }

}



    
    
private void preOrder(QTree tree)
{
   // System.out.println(" ");

    if(tree != null)
    {
        System.out.println(tree.boundary.x+"   "+tree.boundary.y+"        "+tree.boundary.w);
        for(int i=0 ; i<tree.pointsArray.size() ; i++) {
            //System.out.println(" "+tree.pointsArray.get(i).x+"   "+tree.pointsArray.get(i).y);
        }
        preOrder(tree.northEast);
        preOrder(tree.northWest);
        preOrder(tree.southEast);
        preOrder(tree.southWest);
    }
}
    
    
}

class drawGraphics extends JPanel {

    Graphics gr;
    QT qt;
    
   public void drawGraphics(QT qt){
       this.qt=qt;
   } 
   public void paintComponent(Graphics g) {
       gr = g;
       paintTree(qt.qTree);
       paintElements(qt.arrayFound);
   }
   
 public void paintTree(QTree tree)
{
    if(tree != null)
    {
      //  gr.setColor(Color.LIGHT_GRAY); 
      //  gr.fillRect(tree.boundary.x - 20,  tree.boundary.y - 20, 40, 40);
      //  gr.setColor(Color.BLACK);
      //  gr.drawString(String.valueOf(tree.pointsArray.size()), tree.boundary.x -10, tree.boundary.y - 10);
        gr.setColor(Color.BLACK); 
        gr.drawRect(tree.boundary.x - tree.boundary.w,  tree.boundary.y - tree.boundary.h, tree.boundary.w*2, tree.boundary.h*2);
       // gr.setColor(Color.RED); 
      //  gr.drawRect(range.x - range.w/2,  range.y - range.h/2, range.w, range.h);
        
        for(int i=0 ; i< tree.pointsArray.size() ; i++) 
        {
            if(tree.pointsArray.get(i).colide) {
                gr.setColor(Color.RED); 
            }else{
                gr.setColor(Color.BLUE); 
            }
            gr.drawRect(tree.pointsArray.get(i).x -5, tree.pointsArray.get(i).y -5, 10, 10);
        } 
        paintTree(tree.northEast);
        paintTree(tree.northWest);
        paintTree(tree.southEast);
        paintTree(tree.southWest);
    }
    
   
} 
public void paintElements(ArrayList<Point>   arrayList){
    if(arrayList.size() != 0) {
         gr.setColor(Color.BLUE); 
         for(int i=0 ; i< arrayList.size() ; i++) {
                  // gr.drawRect(arrayList.get(i).x -5, arrayList.get(i).y -5, 10, 10);
         }
    }
} 
   
   
}
