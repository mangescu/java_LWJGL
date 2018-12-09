
package pkg3d_studio_max;

import com.sun.opengl.util.texture.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

class TextureCoordonate {

    float x;
    float y;

    
    TextureCoordonate(){
    }
    
    TextureCoordonate(float x , float y , int j){
        this.x = x;
        this.y = y;

    }
}

public class TextureMappingInterface {
 
  
     JFrame frame ;
     JPanel panel;
     Image img;
    
     JMenuBar jMenuBar1;
     JMenu jMenu1,jMenu2;
     JMenuItem jMenuItem1,jMenuItem2,jMenuItem3,jMenuItem4,jMenuItem5,jMenuItem6;
    
      JFileChooser jfc ;
      File file;
      String path;
    
     int numar_vertex_poligon;
     int  xCenter  = 300;
     int  yCenter  = 300;
     int raza = 150;
     float alpha;
     float beta=0;
     JButton  buton[];
     MouseMotionListener mos;
     MouseListener      moss; 
     int Xbutton[];
     int Ybutton[];
     int x,y;
    
      boolean imageLoad = false;
      boolean draw      = false;
      
 
      
      TextureCoordonate  textCoordonate[];
      float x_f;
      float y_f;
      int j_poli;
      Texture  texture;
            
   public void reset() {
        
       
       for(int i=0 ; i<Data.final_entity.corp_list.size() ; i++)
       {
           for(int j=0 ; j<Data.final_entity.corp_list.get(i).poligon_list.size() ; j++)
           {
               if(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac == 1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac == 0 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac == 0)
               {
                       numar_vertex_poligon = Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size();   
                       j_poli = j;
               }
           }
       }
     imageLoad = false;
     draw      = false;
     
     path = null;
     
    if(numar_vertex_poligon == 4)
    {
      alpha = 45;
    }
     
   } 

public void extractCoordonate() 
{
    Data.final_entity.corp_list.get(0).poligon_list.get(j_poli).texturesCoordinates.clear();
  
    for(int i=0 ; i<numar_vertex_poligon ; i++)
    {
       textCoordonate[i] = new TextureCoordonate();
        
       x_f = (float)((float)((buton[i].getX()-30)  * 1)/img.getWidth(null));
       y_f = (float)((float)((buton[i].getY()-30)  * 1)/img.getHeight(null));
       
       textCoordonate[i].x = x_f; 
       textCoordonate[i].y = y_f; 
    
       if(textCoordonate[i].x > 1)
       {
         textCoordonate[i].x = 1;
       }
       if(textCoordonate[i].x < 0)
       {
         textCoordonate[i].x = 0;
       }
       if(textCoordonate[i].y > 1)
       {
         textCoordonate[i].y = 1;
       }
       if(textCoordonate[i].y < 0)
       {
         textCoordonate[i].y = 0;
       }
      
            Data.final_entity.corp_list.get(0).poligon_list.get(j_poli).texturesCoordinates.add(new TexturesCoordinates(textCoordonate[i].x, textCoordonate[i].y));
   
    }
  
    
}
    
public    void   WindowInterface() {
    

        beta = 360/numar_vertex_poligon;
        
        buton    = new JButton[numar_vertex_poligon];
        Xbutton  = new int[numar_vertex_poligon];
        Ybutton  = new int[numar_vertex_poligon];
        
        textCoordonate = new TextureCoordonate[numar_vertex_poligon];
        
        frame = new  JFrame();
        frame.setLayout(null);
        panel = new  JPanel()
        {  
           public  void paintComponent(Graphics g) 
           {
               Graphics2D g2d = (Graphics2D)g;
               
               if(file != null)
               {
                 if(imageLoad == true)
                 {
                 //  g2d.drawImage(img, panel.getWidth()/2 - img.getWidth(null)/2, panel.getHeight()/2 - img.getHeight(null)/2, null);  
                   g2d.drawImage(img,30, 30, null);  
                 }
                 if(draw == true)  
                     for(int i=0;i<numar_vertex_poligon;i++)
                     {
                         g2d.setColor(Color.red);
                         if(i != numar_vertex_poligon -1)
                         g2d.drawLine(buton[i].getX()+(buton[i].getWidth()/2), buton[i].getY()+(buton[i].getHeight()/2),buton[i+1].getX()+(buton[i+1].getWidth()/2), buton[i+1].getY()+(buton[i+1].getHeight()/2));
                         else
                         g2d.drawLine(buton[i].getX()+(buton[i].getWidth()/2), buton[i].getY()+(buton[i].getHeight()/2),buton[0].getX()+(buton[0].getWidth()/2), buton[0].getY()+(buton[0].getHeight()/2));   

                     }
                 
                 extractCoordonate();
                 
                     
               }
           }
        };
        
       
        panel.setSize(550,550);
        panel.setLocation(0, 0);
        panel.setLayout(null);
        jfc = new JFileChooser();
        
          jMenuBar1 = new  JMenuBar();
                jMenu1 = new JMenu();
                jMenu1.setText("File");
                
                      jMenuItem1 = new JMenuItem();
                      jMenuItem1.setText("Load Texture");
                      jMenuItem2 = new JMenuItem();
                      jMenuItem2.setText("draw poligon vertex");
                      jMenuItem3 = new JMenuItem();
                      jMenuItem3.setText("bind texture");
                      
           jMenu1.add(jMenuItem1);  
           jMenu1.add(jMenuItem2);
           jMenu1.add(jMenuItem3);
           jMenuBar1.add(jMenu1);
                   
           
           
           
  
       jMenuItem1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
             jfc.showOpenDialog(frame);
             file = jfc.getSelectedFile();
             
                 if(file != null)
                 {
                      img = new ImageIcon(file.getPath()).getImage();
                    
                      Main.textures_filds.add(file.getPath());
                      Main.resetTextures = true;
                      
                      imageLoad = true;
                      frame.repaint();
                      
                 }
            }
        });   
        jMenuItem2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(imageLoad == true)
                {
                 for(int k=0;k<numar_vertex_poligon;k++)
                  panel.add(buton[k]);
                 
                 draw = true;
                
                 


                  
                    for(int i=0 ; i<Main.finals_textures.size() ; i++)
                    {
                            if(i == Main.finals_textures.size() - 1)
                            {
                               Data.final_entity.corp_list.get(0).poligon_list.get(j_poli).textureStream = Main.finals_textures.get(i);
                            }
                    }
                       extractCoordonate();
                       Data.final_entity.corp_list.get(0).poligon_list.get(j_poli).T_status = true;
                   
                        frame.repaint();
                }
             }
        });   
           
       
       
       
mos = new MouseMotionListener() {
            public void mouseDragged(MouseEvent e)
            {
                JButton button = (JButton) e.getSource();
                button.setLocation(e.getXOnScreen()-frame.getX()-x-5, e.getYOnScreen()-frame.getY()-y-50);
                frame.repaint();
            }
            public void mouseMoved(MouseEvent e) {}};
 
moss = new MouseListener() {
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e)
            {
                 x=e.getX();
                 y=e.getY();
            }
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        };


for(int p=0 ; p<numar_vertex_poligon;p++)
{
   buton[p] = new JButton();
   buton[p].setSize(10, 10);
   
   Xbutton[p] = (int) ((int) raza * Math.cos(Math.toRadians(alpha)));
   Ybutton[p] = (int) ((int) raza * Math.sin(Math.toRadians(alpha)));
   
   buton[p].setLocation(xCenter + Xbutton[p] ,yCenter + Ybutton[p]);
   buton[p].setSize(10, 10);
   alpha = alpha + beta;
   

     buton[p].addMouseMotionListener(mos);
     buton[p].addMouseListener(moss);
}       
       
       
       
       

        
        frame.setJMenuBar(jMenuBar1);   
        frame.getContentPane().add(panel);
        frame.setSize(578, 620);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
