
package com.mangy.colizion;

import com.mangy.bullet.Bullet;
import com.mangy.cube.Cube;
import com.mangy.entity.Entity;
import com.mangy.game.Main;
import com.mangy.player.Player;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Colizion {
    
    public Cube    colizionCubeCheck;
    Main main;   
    
    
 public boolean BulletDestroiColizion(Main main ,Bullet bullet , float new_x , float new_y , ArrayList<Player>  listaPlayer , ArrayList<Entity>  listaEntity) throws FileNotFoundException {
 
     boolean destroi = false;
     if(bullet.owner == null) {
          System.out.println("bullet = null");
     }
     if(bullet.owner.equals("player")) 
     {
        for(int i=0 ; i<listaEntity.size() ; i++) 
        { 
            boolean x_Col = false;
            boolean y_Col = false;

            if (Math.abs((new_x - listaEntity.get(i).X)) < (0.5f + 0.1f)) {
                x_Col = true;
            }
            if (Math.abs((new_y - listaEntity.get(i).Y)) < (0.5f + 0.1f)) {
                y_Col = true;
            }

            if (x_Col == true && y_Col == true) {
                destroi = true;
                listaEntity.get(i).deleteEntity();
                main.gamePlayers.getListaEntity().remove(listaEntity.get(i));
                main.addGameEntity();
                //i--;
            }       
        }
     } else {     
         for(int i=0 ; i< listaPlayer.size() ; i++) 
        { 
            boolean x_Col = false;
            boolean y_Col = false;

            if (Math.abs((new_x - listaPlayer.get(i).X)) < (0.5f + 0.1f)) {
                x_Col = true;
            }
            if (Math.abs((new_y - listaPlayer.get(i).Y)) < (0.5f + 0.1f)) {
                y_Col = true;
            }

            if (x_Col == true && y_Col == true) {
                 destroi = true;
                 destroi = true;
                 listaEntity.get(i).deleteEntity();       
                 Main.gamePlayers.getListaPlayer().remove(listaPlayer.get(i));
                 i--;
            }        
        }   
         
            boolean x_Col = false;
            boolean y_Col = false;

            if (Math.abs((new_x - main.localPlayer.X)) < (0.5f + 0.1f)) {
                x_Col = true;
            }
            if (Math.abs((new_y - main.localPlayer.Y)) < (0.5f + 0.1f)) {
                y_Col = true;
            }

            if (x_Col == true && y_Col == true) {
                  destroi = true;
                  main.localPlayer.deleteEntity();
                  main.localPlayer = null;
                  
              String[] textures = new String[]{"Textures/player/Tank/Green_Tank_0.png" , "Textures/player/Tank/Green_Tank_1.png"}; 

	      main.localPlayer = new Player(textures);
	      main.localPlayer.X = +3;
            }           
         
         
     }
       
 return destroi;
 }    
    
 public boolean playerEntityColizionDetection(float new_x , float new_y , ArrayList<Player> listaPlayer , Player localPlayer){
 
     boolean colizion = false;

            boolean x_Col = false;
            boolean y_Col = false;
            
            if (Math.abs((new_x - localPlayer.X)) < (0.5f + 0.5f)) {
                x_Col = true;
            }
            if (Math.abs((new_y - localPlayer.Y)) < (0.5f + 0.5f)) {
                y_Col = true;
            }

            if (x_Col == true && y_Col == true) {
                colizion = true;
            }
            if (x_Col == true && y_Col == true) {
                colizion = true;
            }
     
            for (Player  player : listaPlayer) {

             x_Col = false;
             y_Col = false;

            if (Math.abs((new_x - player.X)) < (0.5f + 0.5f)) {
                x_Col = true;
            }
            if (Math.abs((new_y - player.Y)) < (0.5f + 0.5f)) {
                y_Col = true;
            }

            if (x_Col == true && y_Col == true) {
                colizion = true;
            }
        }
     
 return colizion;      
 } 
 
 public boolean entityPlayerColizionDetection(float new_x , float new_y , ArrayList<Entity> listaEntity){
 
     boolean colizion = false;
      
    for (Entity entity : listaEntity) {

            boolean x_Col = false;
            boolean y_Col = false;

            if (Math.abs((new_x - entity.X)) < (0.5f + 0.5f)) {
                x_Col = true;
            }
            if (Math.abs((new_y - entity.Y)) < (0.5f + 0.5f)) {
                y_Col = true;
            }

            if (x_Col == true && y_Col == true) {
                colizion = true;
            }
        }
    
 return colizion;      
 }   
 
 public boolean  CubeEntityDetectieColiziune(Main main ,  float new_x , float new_y ,ArrayList<Cube> cubeList)  {
         
      boolean colizion = false;

    
        for (Cube cube : cubeList) {

            boolean x_Col = false;
            boolean y_Col = false;

            if (Math.abs((new_x - cube.X)) < (0.45f + 0.25f)) {
                x_Col = true;
            }
            if (Math.abs((new_y - cube.Y)) < (0.45f + 0.25f)) {
                y_Col = true;
            }

            if (x_Col == true && y_Col == true) {
                colizion = true;
            }
        }
     return colizion;
    }    
    public boolean  CubeDetectieColiziune(Main main ,  float new_x , float new_y ,ArrayList<Cube> cubeList)  {
         
      boolean colizion = false;

    
        for (Cube cube : cubeList) {

            boolean x_Col = false;
            boolean y_Col = false;

            if (Math.abs((new_x - cube.X)) < (0.45f + 0.25f)) {
                x_Col = true;
            }
            if (Math.abs((new_y - cube.Y)) < (0.45f + 0.25f)) {
                y_Col = true;
            }

            if (x_Col == true && y_Col == true) {
                colizion = true;
            }
        }
     return colizion;
    }
 
    public boolean  CubeBulletColiziune(Main main ,  float new_x , float new_y ,ArrayList<Cube> cubeList)  {
         
      boolean colizion = false;

    
        for (int i=0 ; i< cubeList.size() ; i++) {

            boolean x_Col = false;
            boolean y_Col = false;

            if (Math.abs((new_x - cubeList.get(i).X)) < (0.25f + 0.1f)) {
                x_Col = true;
            }
            if (Math.abs((new_y - cubeList.get(i).Y)) < (0.25f + 0.1f)) {
                y_Col = true;
            }

            if (x_Col == true && y_Col == true) {
                colizion = true;
                if(main.gamePlayers.getListaCubes().get(i).type.equals("brick")) {
                    main.gamePlayers.getListaCubes().get(i).deleteCube();
                    main.gamePlayers.getListaCubes().remove(cubeList.get(i));
                    i--;
                }
            }
        }
     return colizion;
    }    
    
    public boolean bordersColizion(float new_x , float new_y) {
           
            boolean colizion = false;  
            
            if(new_x < -5) {
               colizion = true;
            }
            if(new_x > 5) {
               colizion = true;
            }            
            if(new_y < -5) {
               colizion = true;
            }
            if(new_y > 5) {
               colizion = true;
            }             
         return colizion;
    }
    public boolean playerBorderColizion(float new_x , float new_y) {
        
        boolean colizion = false;  
        
             if(new_x < -4.5) {
               colizion = true;
            }
            if(new_x > 4.5) {
               colizion = true;
            }            
            if(new_y < -4.5) {
               colizion = true;
            }
            if(new_y > 4.5) {
               colizion = true;
            }             
         return colizion;       
    
    }
 
public boolean EntityBorderColizion(float new_x , float new_y) {
        
        boolean colizion = false;  
        
             if(new_x < -4.5) {
               colizion = true;
            }
            if(new_x > 4.5) {
               colizion = true;
            }            
            if(new_y < -4.5) {
               colizion = true;
            }
            if(new_y > 4.5) {
               colizion = true;
            }             
         return colizion;       
    
    }

}
