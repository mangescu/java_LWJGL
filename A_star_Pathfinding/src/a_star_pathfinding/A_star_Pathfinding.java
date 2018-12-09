
package a_star_pathfinding;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Cell {

  int i;
  int j;
  
  int  cell_index;
  
  int color;
  
  int cost_start = 0;
  int cost_end = 0;
  int total_cost = 0;
  
  boolean checked = false;
  boolean isObstacle = false;
  ArrayList<Cell>  chieldren  =  new ArrayList<Cell>();
  Cell  parent;
  
  boolean rendebel = true;
    
}


public class A_star_Pathfinding {
    
    Random rand = new Random();


    
    int cell_index = 0;
    
    Cell del_cell , curent , temp , end_cell , start_cell;
  

    
    JFrame frame;
    JPanel panel; 
    Cell  cell;
    
    int spacer = 0;
    int cells_count = 90;
    int table_width = 900;
    int cell_width = table_width / cells_count;
    
  //  int   start_x = rand.nextInt(cells_count),start_y = rand.nextInt(cells_count);
  //  int   end_x = rand.nextInt(cells_count), end_y = rand.nextInt(cells_count);

     int   start_x = 0,start_y = 0;
    int   end_x = cells_count -1, end_y = cells_count -1;
    
    Cell[][]  cell_square = new Cell[cells_count][cells_count];
    
    ArrayList<Cell>  check_Cell  =  new ArrayList<Cell>();
    ArrayList<Cell>  temp_Cell  =  new ArrayList<Cell>();
  

  


    public static void main(String[] args) throws IOException, InterruptedException {
        
             A_star_Pathfinding cr = new A_star_Pathfinding();
                                cr.interfata();
                
    }
    
    public void interfata() throws IOException, InterruptedException
    {
        
        int index = 0;
        
        frame = new JFrame();
	frame.setLayout(null);
        panel = new JPanel();
        panel.setBackground(Color.red);
        
        
         for(int i=0; i <table_width ; i=i+cell_width) {
            for(int j=0; j <table_width ; j=j+cell_width) {
        
               cell_square[i/cell_width][j/cell_width] = new Cell();
               cell_square[i/cell_width][j/cell_width].i = i/cell_width;
               cell_square[i/cell_width][j/cell_width].j = j/cell_width;
               cell_square[i/cell_width][j/cell_width].color =0;
               
               cell_square[i/cell_width][j/cell_width].cell_index = index;
                
                if(cell_square[i/cell_width][j/cell_width].i == start_x  && cell_square[i/cell_width][j/cell_width].j == start_y) {
                   cell_square[i/cell_width][j/cell_width].color =7;
                    cell_square[i/cell_width][j/cell_width].isObstacle = true;
                    cell_square[i/cell_width][j/cell_width].total_cost = 999;
                   check_Cell.add(cell_square[i/cell_width][j/cell_width]);
                   start_cell =  cell_square[i/cell_width][j/cell_width];
                }
                if(cell_square[i/cell_width][j/cell_width].i == end_x  && cell_square[i/cell_width][j/cell_width].j == end_y){
                   cell_square[i/cell_width][j/cell_width].color =7;
                   end_cell = cell_square[i/cell_width][j/cell_width];
                }   
              
              index++;
            } 
           
        }
         
          
    for(int i=0 ; i <cells_count ; i++){
        for(int j=0 ; j <cells_count ; j++){   
            
            try {
                    if(!cell_square[i-1][j].isObstacle) {
                        cell_square[i][j].chieldren.add(cell_square[i-1][j]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
            try {
                    if(!cell_square[i+1][j].isObstacle) {
                        cell_square[i][j].chieldren.add(cell_square[i+1][j]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}   
            try {
                    if(!cell_square[i+1][j+1].isObstacle) {
                        cell_square[i][j].chieldren.add(cell_square[i+1][j+1]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
            try {
                    if(!cell_square[i+1][j-1].isObstacle) {
                        cell_square[i][j].chieldren.add(cell_square[i+1][j-1]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {} 
            try {
                    if(!cell_square[i-1][j+1].isObstacle) {
                        cell_square[i][j].chieldren.add(cell_square[i-1][j+1]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}              
            try {
                    if(!cell_square[i-1][j-1].isObstacle) {
                        cell_square[i][j].chieldren.add(cell_square[i-1][j-1]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {} 
            try {
                   if(!cell_square[i][j-1].isObstacle) {
                       cell_square[i][j].chieldren.add(cell_square[i][j-1]);
                   }
                } catch (ArrayIndexOutOfBoundsException e) {}   
            try {
                    if(!cell_square[i][j+1].isObstacle) {
                        cell_square[i][j].chieldren.add(cell_square[i][j+1]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}             
        }
    }
         
    
                    for(int i=0; i <table_width ; i=i+(table_width/cells_count)) {
                      for(int j=0; j <table_width ; j=j+(table_width/cells_count)) {
                          
                          int stone  = rand.nextInt(2);
                          if(stone == 0) {
                          
                                if(i/(table_width/cells_count) != start_x && i/(table_width/cells_count) != end_x && j/(table_width/cells_count) != start_y && j/(table_width/cells_count) != end_y)
                                {
                                 cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].color =4;
                                 cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].isObstacle = true;
                                }
                          }
                      }
                    }
    
    
 
        panel = new JPanel() {
		public void paint(Graphics g) {
                
                    g.setColor(Color.white);
                    g.fillRect(0, 0, 1000, 1000);
                    for(int i=0; i <table_width ; i=i+(table_width/cells_count)) {
                      for(int j=0; j <table_width ; j=j+(table_width/cells_count)) {
                          
                          
                          if(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].color ==0){
                             g.setColor(Color.GRAY);
                          }
                          if(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].color ==1){
                             g.setColor(Color.YELLOW);
                          }
                          if(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].color ==2){
                             g.setColor(Color.RED);
                          }
                          if(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].color ==3){
                             g.setColor(Color.BLUE);
                          }
                          if(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].color ==4){
                             g.setColor(Color.BLACK);
                          }
                          if(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].color ==5){
                             g.setColor(Color.GREEN);
                          }  
                          if(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].color ==6){
                             g.setColor(Color.WHITE);
                          } 
                           if(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].color ==7){
                             g.setColor(Color.ORANGE);
                          } 
                          
       
                          g.fillRect(i, j, table_width/cells_count - spacer, table_width/cells_count - spacer);
                          
                          if(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].color != 4) 
                          {
                             g.setColor(Color.blue);      
                             //g.drawString(String.valueOf(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].cost_end), i+cell_width/8  , j+cell_width/8);
                             //g.drawString(String.valueOf(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].cost_start), i+cell_width/2+cell_width/4  , j+cell_width/8);
                            if(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].rendebel)
                           //  g.drawString(String.valueOf(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].total_cost), i+cell_width/2, j+cell_width/2);   
                             g.setColor(Color.WHITE);    
                            // g.drawString(String.valueOf(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].cell_index), i+cell_width/8  , j + 30);

                             if(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].parent != null) 
                             {
                                  g.setColor(Color.BLACK); 
                             //     g.drawString(String.valueOf(cell_square[i/(table_width/cells_count)][j/(table_width/cells_count)].parent.cell_index), i+cell_width/8 + 60  , j + 30);  
                             }
                          }
                          
                          
                            int spacer = 10;
                            if(check_Cell.size() != 0){
                            for(int k=0 ; k<check_Cell.size() ; k++)
                            {
                                g.setColor(Color.BLACK);
                                //g.drawString(String.valueOf(" "+check_Cell.get(k).total_cost+" "), 10+spacer  ,950);
                                spacer+=30;
                            }  
                             } else {
                                g.setColor(Color.BLACK);
                                //g.drawString("list size = 0 ", 10  ,950);
                             }
                          
                          
                          
                       }                  
                    }                              
		}
	};
        
     
        
        panel.setSize(1050,1050);
        
     	frame.getContentPane().add(panel);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1050, 1050);
        frame.setLocation(0, 20);
	//frame.setLocationRelativeTo(null);
	frame.setVisible(true);   
        

           chechCellNeiborn();
           getPath(end_cell);
          
        
    }
   
    public void  chechCellNeiborn() throws IOException, InterruptedException
    {
    boolean done = false;
    
        while(check_Cell.size() != 0)
        {
               del_cell = check_Cell.get(0);
               del_cell.checked = true;

               if(check_Cell.get(0).color !=7){
                  check_Cell.get(0).color = 0;
               }
               curent  = check_Cell.get(0); 
               for(int j=0 ;j<check_Cell.get(0).chieldren.size() ; j++)  
               {
                    Cell chield = check_Cell.get(0).chieldren.get(j);
                    if(!chield.isObstacle)  {
                       if(chield.parent == null) 
                       {
                            if(curent.i == chield.i || curent.j == chield.j) {
                                chield.cost_start = curent.cost_start + 10;
                            } else {
                                chield.cost_start = curent.cost_start + 14;
                            }

                           int delta_end_i = Math.abs(chield.i   -  end_x);
                           int delta_end_j = Math.abs(chield.j   -  end_y);      
                           int delta_end =  Math.abs(delta_end_i - delta_end_j);

                           if(delta_end_i < delta_end_j) {
                                chield.cost_end = delta_end_i * 14  + delta_end  * 10;
                           } else {
                                chield.cost_end = delta_end_j * 14  + delta_end  * 10;
                           }
                           chield.total_cost = chield.cost_start + chield.cost_end;
                           if(chield.i == end_x  && chield.j == end_y) {
                               done = true;
                           }
                           if(chield.color!=7){
                              chield.color = 5;
                           }
                            chield.parent = curent;
                            addCellElement(chield);
                        }
                     }
                   }

            if(check_Cell.size()!=0){
                check_Cell.remove(0);
            }
            Thread.sleep(1);
         // System.in.read();
            repaintP(); 
            if(done) {
               check_Cell.clear();
            }
        } 
    }


    public void addCellElement(Cell cell)
    {
        boolean ok = false;
        for(int i=1 ; i<check_Cell.size() ; i++)
        {
           temp = check_Cell.get(i);
           if(cell.total_cost < temp.total_cost)
           {
               check_Cell.add(i , cell);
               ok = true;     
               break;
           } else {
                if(cell.total_cost == temp.total_cost)
                {
                      if(cell.cost_end <  temp.cost_end)
                      {
                            check_Cell.add(i , cell);
                            ok = true;     
                            break;
                      }
                }
           }
        }
        if(!ok){
          check_Cell.add(cell);
        }
        frame.setTitle(String.valueOf(check_Cell.size()));
    
    }
    public void getPath(Cell cell){     
        while(cell.parent != null) {
            if(cell.parent.color != 7) {
                cell.parent.color = 2;
            }
          cell = cell.parent;    
        }
    } 
    
    public void repaintP(){
      panel.repaint();
    }  
    
}
