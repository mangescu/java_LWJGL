

package com.mangy.game;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import com.mangy.bullet.Bullet;
import com.mangy.camera.Camera;
import com.mangy.client.BulletData;
import com.mangy.client.UDP_Client;
import com.mangy.colizion.Colizion;
import com.mangy.cube.Cube;
import com.mangy.cube.Level_1;
import com.mangy.entity.Entity;
import com.mangy.map.Map;
import com.mangy.player.Player;
import com.mangy.server.UDP_Server;
import com.mangy.vbo.*;
import javax.swing.JOptionPane;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.util.glu.GLU.gluLookAt;
import static org.lwjgl.util.glu.GLU.gluPerspective;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glVertexPointer;




public class Main {
	
        UDP_Client client;
        Texture bullet_texture ;
		
        public static GamePlayer gamePlayers  =  new  GamePlayer(); 
        
	boolean Fullscreen = false;
//______FPS___________________________________________________________//
	double start = System.nanoTime() / 1000000000;
	float senzitiviti = 0.2f;
	int valoare = 0;
	double end;
//____________________________________________________________________//	
	int dx = 0;
	int dy = 0;

	Camera camera = new Camera();
        Colizion colizion  =  new  Colizion();

	Map map = new Map();

	DisplayMode mode;
	int cam = 0;
	Texture map_texture;
	IBO map_index_buffer;
	VBO map_vertex_buffer;
	int FLOAT_SIZE = Float.SIZE / Byte.SIZE;
	
	String direction = "x";
	
	public Player  localPlayer , player;
	Bullet bullet;
        
	public static boolean addBuller = false;
	boolean fire = true;
        boolean delete = true;
	
	boolean animationStatus = false;
        
        Level_1  level ;
        
        Entity entity;
        String[] textures1 = new String[]{"Textures/entity/entity/Blue_Tank_0.png" , "Textures/entity/entity/Blue_Tank_1.png"};
         
         
       

	public static void main(String[] args) throws FileNotFoundException, LWJGLException {
		Main  main  =  new Main();              
		      main.mainLoop();	                                 
	}

	public void mainLoop() throws LWJGLException, FileNotFoundException {
  		  
		level  =  new  Level_1(this);
                bullet_texture = new Texture(new FileInputStream(new File("Textures/bullet/texture.png")));
		
		if (!Fullscreen) {
			mode = new DisplayMode(900, 900);
			Display.setDisplayModeAndFullscreen(mode);
			Display.setLocation(500, 100);
			Display.create();		
	
		} else {
			mode = Display.getDesktopDisplayMode();
			Display.setFullscreen(true);
			Display.setDisplayMode(mode);
			Display.create();		
		}

		glEnable(GL_TEXTURE_2D);
		glEnable(GL_DEPTH_TEST);

      
            level.loadLevel();
                
	    String[] textures = new String[]{"Textures/player/Tank/Green_Tank_0.png" , "Textures/player/Tank/Green_Tank_1.png"};           
	    localPlayer = new Player(textures);
	    localPlayer.X = +2;
            
	    
	    entity = new Entity(textures1); 
            entity.X = -4.5f;
            entity.Y = -4.5f;
            entity.direction = "x";
            entity.angle = 180;
            gamePlayers.getListaEntity().add(entity);

            entity = new Entity(textures1); 
            entity.X = -4.5f;
            entity.Y = 0;
            entity.direction = "x";
            entity.angle = 180;
            gamePlayers.getListaEntity().add(entity);
            
            entity = new Entity(textures1); 
            entity.X = -4.5f;
            entity.Y = 4.5f;
            entity.direction = "x";
            entity.angle = 180;
            gamePlayers.getListaEntity().add(entity);    
        
        
        
//______UDP CLIENT________________________________________________________________________________________//	
            	  String name =   JOptionPane.showInputDialog(this , "Plese enter a name");
     
		  client = new UDP_Client(this, "localhost" , name);
		  client.start();

  
		  client.sendData(("CONNECT/"+client.localName).getBytes());
                  client.sendData("PLAYERS_IN".getBytes());
                  
        localPlayer.name = client.localName;           
//__________________________________________________________________________________________________________//                  


     
            
            
//________INIT MAP_BUFFER______________________________________________________________//
		
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		
		map_texture = new Texture(new FileInputStream(new File("Textures/map/floor.png")));

		map_index_buffer  = new IBO(map.getMAP_IBO());
		map_vertex_buffer = new VBO(map.getMAP_VBO());
		
		map_texture.glInit();
		map_index_buffer.glInit();
		map_vertex_buffer.glInit();
		

//______________________________________________________________________________________//
		
          
while (!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !Display.isCloseRequested()) {
            


	// FPS____________________________________________________________________//
		end = System.nanoTime() / 1000000000;
		if (end - start > 1) {
			start = end;
			Display.setTitle(client.localName+" -> "+String.valueOf(valoare/2));
			valoare = 0;
		}
		valoare++;
	// __________________________________________________________________________//
			
	 if (Display.isVisible())  {		
		 
	 
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glLoadIdentity();

      
            //____CAMERA__________________________________________________________________________________________________________________________________________________________//	            	
            	glMatrixMode(GL_PROJECTION);
            	glLoadIdentity();
                	glViewport(0, 0, mode.getWidth(), mode.getHeight());
                	gluPerspective(60, 1f, 0.1f, 100);      
                	gluLookAt(camera.getXPos(), camera.getYPos(), camera.getZPos(), camera.getXLPos(), camera.getYLPos(), camera.getZLPos(), 0f, 0f, 1.0f);
            //_____________________________________________________________________________________________________________________________________________________________________//   					           
                        
                        
            //____________CAMERA_INPUT_CONTROL_________________________________________________________//
 
                	//checkKeyboard();
                	//mouseControl();               
                	localPlayerControl();
            //____________________________________________________________________________________________//   
                        
                        
                    for(int i=0 ; i < client.listaPlayers.size() ; i++) {
                        boolean ok = true;

                         for(int j=0 ; j<gamePlayers.getListaPlayer().size() ; j++) {
                            if(gamePlayers.getListaPlayer().get(j).name.equals(client.listaPlayers.get(i).nume)) {
                               ok = false;
                            }
                         }
                         if(ok == true) {
                            String[] texture = new String[]{"Textures/player/player_0.png" , "Textures/player/player_1.png"};
                            player = new Player(textures);
                            player.name =  client.listaPlayers.get(i).nume;
                            gamePlayers.getListaPlayer().add(player);
                         }
                    }                        
                     
                    
//.....................................RENDER DATA............................................................................................//
                        
//_______RENDER MAP______________________________________________________________//                                
                    glMatrixMode(GL_MODELVIEW);
                    glLoadIdentity();
                                          
                    renderMap(); 
//_______________________________________________________________________________//   
                    
      
                    
//_______RENDER LOCAL PLAYER_____________________________________________________//                    
                    glMatrixMode(GL_MODELVIEW);
                    glLoadIdentity();  
                    
                    localPlayer.renderPlayer();
//______________________________________________________________________________//
                    
 
                    
//_______RENDER CUBES___________________________________________________________//
                    
                   for(Cube  cub : gamePlayers.getListaCubes()) {
                        glMatrixMode(GL_MODELVIEW);
                        glLoadIdentity();
                        
                        cub.renderCube();
                   }
//______________________________________________________________________________//  
                   
                  
 
//______RENDER PLAYERS___________________________________________________________//
              	   for(Player  player : gamePlayers.getListaPlayer()) {
                       
                        glMatrixMode(GL_MODELVIEW);
                        glLoadIdentity(); 
                        
                        player.renderPlayer();
                   }	                  
//______________________________________________________________________________//                   
                    
                    if(addBuller == true) {
                        bullet  =  new  Bullet(bullet_texture);
                        bullet.owner = client.listaBullets.get(client.listaBullets.size()-1).owner;
                        bullet.X = client.listaBullets.get(client.listaBullets.size()-1).X;
                        bullet.Y = client.listaBullets.get(client.listaBullets.size()-1).Y;
                        bullet.Z = client.listaBullets.get(client.listaBullets.size()-1).Z;
                        bullet.direction = client.listaBullets.get(client.listaBullets.size()-1).direction;                        
                        gamePlayers.getListaBullets().add(bullet);
                        addBuller = false;
                    }
                    
//_____________RENDER ENTITY_____________________________________________________________________________________________________________________________________//
                    
                    for(Entity entity : gamePlayers.getListaEntity()) {
                        
                           if(entity.fireBullerCounter > 300) {
                               fireBuller("entity", entity.X, entity.Y, entity.direction);
                               entity.fireBullerCounter = 0;
                           } else {
                              entity.fireBullerCounter++;
                           }
                        
                            glMatrixMode(GL_MODELVIEW);
                            glLoadIdentity();  
                            
                            
                            entity.renderEntity();
                            
                             if(entity.direction.equals("-x")) {   
                                 if(!colizion.CubeEntityDetectieColiziune(this, entity.X - 0.005f, entity.Y, gamePlayers.getListaCubes())) {
                                    if(!colizion.EntityBorderColizion(entity.X - 0.005f, entity.Y)) {
                                        if(!colizion.playerEntityColizionDetection(entity.X - 0.005f, entity.Y, gamePlayers.getListaPlayer() , localPlayer)) {
                                            entity.X-=0.005f;    
                                        } 
                                    } else {
                                      entity.direction = "y";
                                      entity.angle = -90;
                                    }
                                 } else {
                                   entity.direction = "y";
                                     entity.angle = -90;
                                 }
                             }
                             if(entity.direction.equals("x")) {  
                                 if(!colizion.CubeEntityDetectieColiziune(this, entity.X + 0.005f, entity.Y, gamePlayers.getListaCubes())) {
                                     if(!colizion.EntityBorderColizion(entity.X + 0.005f, entity.Y)) {
                                         if(!colizion.playerEntityColizionDetection(entity.X + 0.005f, entity.Y, gamePlayers.getListaPlayer() , localPlayer)) {
                                             entity.X+=0.005f;
                                         }
                                     } else {
                                          entity.direction = "-y";
                                          entity.angle = 90;
                                     }
                                 } else {
                                    entity.direction = "-y";
                                    entity.angle = 90;
                                 }
                             }    
                             if(entity.direction.equals("-y")) {   
                                 if(!colizion.CubeEntityDetectieColiziune(this, entity.X , entity.Y - 0.005f, gamePlayers.getListaCubes())) {
                                    if(!colizion.EntityBorderColizion(entity.X, entity.Y - 0.005f)) {
                                         if(!colizion.playerEntityColizionDetection(entity.X , entity.Y - 0.005f, gamePlayers.getListaPlayer() , localPlayer)) {
                                             entity.Y-=0.005f;
                                         }
                                    } else {
                                        entity.direction = "-x";
                                        entity.angle = 0;
                                    }
                                 } else {
                                    entity.direction = "-x";
                                    entity.angle = 0;
                                 }
                             }                                 
                             if(entity.direction.equals("y")) {   
                                 if(!colizion.CubeEntityDetectieColiziune(this, entity.X , entity.Y + 0.005f, gamePlayers.getListaCubes())) {
                                    if(!colizion.EntityBorderColizion(entity.X, entity.Y + 0.005f)) {
                                         if(!colizion.playerEntityColizionDetection(entity.X , entity.Y + 0.005f, gamePlayers.getListaPlayer() , localPlayer)) {
                                            entity.Y+=0.005f;
                                         }
                                    } else {
                                       entity.direction = "x";
                                       entity.angle = 180;
                                    }
                                 } else {
                                    entity.direction = "x";
                                    entity.angle = 180;
                                 }
                             }                               
                            
                    }
//_______________________________________________________________________________________________________________________________________________________________//                    
                    
                    
//________RENDER BUULETS_________________________________________________________________________________________________________________________________________//                    
                    for(int i=0 ; i<gamePlayers.getListaBullets().size() ; i++) {
                    	
                        glMatrixMode(GL_MODELVIEW);
                        glLoadIdentity();
                        boolean continue_ = true;
                        
                        gamePlayers.getListaBullets().get(i).renderBullet();

                        if(gamePlayers.getListaBullets().get(i).direction.equals("-x")) {
                           if(!colizion.CubeBulletColiziune(this , gamePlayers.getListaBullets().get(i).X - 0.03f, gamePlayers.getListaBullets().get(i).Y , gamePlayers.getListaCubes())) {
                               if(!colizion.bordersColizion(gamePlayers.getListaBullets().get(i).X - 0.03f , gamePlayers.getListaBullets().get(i).Y)) {
                                   if(!colizion.BulletDestroiColizion(this, gamePlayers.getListaBullets().get(i), gamePlayers.getListaBullets().get(i).X - 0.03f , gamePlayers.getListaBullets().get(i).Y, gamePlayers.getListaPlayer() , gamePlayers.getListaEntity())) {
                                        gamePlayers.getListaBullets().get(i).X-=0.03f;
                                   } else {
                                        deleteBullet(i);
                                        continue_ = false;
                                   }
                               } else {
                                   deleteBullet(i);
                                   continue_ = false;
                               }
                           } else {
     
                                deleteBullet(i);
                                continue_ = false;
                           } 
                        } 
                        if(continue_) 
                        if(gamePlayers.getListaBullets().get(i).direction.equals("x"))  {
                           if(!colizion.CubeBulletColiziune(this , gamePlayers.getListaBullets().get(i).X + 0.03f, gamePlayers.getListaBullets().get(i).Y , gamePlayers.getListaCubes())) {
                                if(!colizion.bordersColizion(gamePlayers.getListaBullets().get(i).X + 0.03f , gamePlayers.getListaBullets().get(i).Y)) {
                                     if(!colizion.BulletDestroiColizion(this, gamePlayers.getListaBullets().get(i), gamePlayers.getListaBullets().get(i).X + 0.03f , gamePlayers.getListaBullets().get(i).Y, gamePlayers.getListaPlayer() , gamePlayers.getListaEntity())) {
                                         gamePlayers.getListaBullets().get(i).X+=0.03f;                                     
                                     } else {
                                         deleteBullet(i);
                                         continue_ = false;    
                                     }
                                } else {
                                     deleteBullet(i);
                                     continue_ = false;
                                }
                           } else {
                                 deleteBullet(i);
                                 continue_ = false;
                           }                            
                        } 
                        if(continue_)
                        if(gamePlayers.getListaBullets().get(i).direction.equals("-y")) {
                           if(!colizion.CubeBulletColiziune(this , gamePlayers.getListaBullets().get(i).X, gamePlayers.getListaBullets().get(i).Y - 0.03f, gamePlayers.getListaCubes())) {
                                if(!colizion.bordersColizion(gamePlayers.getListaBullets().get(i).X  , gamePlayers.getListaBullets().get(i).Y - 0.03f)) {
                                     if(!colizion.BulletDestroiColizion(this, gamePlayers.getListaBullets().get(i), gamePlayers.getListaBullets().get(i).X  , gamePlayers.getListaBullets().get(i).Y - 0.03f, gamePlayers.getListaPlayer() , gamePlayers.getListaEntity())) {
                                        gamePlayers.getListaBullets().get(i).Y-=0.03f;
                                     } else {
                                         deleteBullet(i);
                                         continue_ = false;   
                                     }
                                } else {
                                   deleteBullet(i);
                                   continue_ = false;
                                }
                           } else {
                                 deleteBullet(i);
                                 continue_ = false;
                           }                            
                        }
                        if(continue_)
                        if(gamePlayers.getListaBullets().get(i).direction.equals("y")) {
                           if(!colizion.CubeBulletColiziune(this , gamePlayers.getListaBullets().get(i).X, gamePlayers.getListaBullets().get(i).Y + 0.03f, gamePlayers.getListaCubes())) {
                               if(!colizion.bordersColizion(gamePlayers.getListaBullets().get(i).X  , gamePlayers.getListaBullets().get(i).Y + 0.03f)) {
                                     if(!colizion.BulletDestroiColizion(this, gamePlayers.getListaBullets().get(i), gamePlayers.getListaBullets().get(i).X  , gamePlayers.getListaBullets().get(i).Y - 0.03f, gamePlayers.getListaPlayer() , gamePlayers.getListaEntity())) {
                                        gamePlayers.getListaBullets().get(i).Y+=0.03f; 
                                     } else {
                                         deleteBullet(i);
                                         continue_ = false; 
                                     }
                               } else {
                                   deleteBullet(i);
                                   continue_ = false;
                               }
                           } else {
                                 deleteBullet(i);
                                 continue_ = false;
                           }                             	
                        }
                  
                    }
//______________________________________________________________________________________________________________________________________________________________________________________________//                    
                    
//.............................................................................................................................................................//                    


                    		
           //_____________________________________________________________________________________________//   			
			

			} else {
				if (Display.isDirty()) {}
				try {
					Thread.sleep(10);
				} catch (InterruptedException interup) {}
			}
	      Display.update();
		}
		Display.destroy();
                System.exit(0);

	}
	public void deleteBullet(int i) {
                     gamePlayers.getListaBullets().get(i).deleteBullet();
                     gamePlayers.getListaBullets().remove(i);
                     i--;           
        }
	   public void renderMap() {
		   
                map_texture.glBind();
                map_vertex_buffer.glBind();

                glVertexPointer(3, GL_FLOAT, 5 * FLOAT_SIZE, 0 * FLOAT_SIZE);
                glTexCoordPointer(2, GL_FLOAT, 5 * FLOAT_SIZE, 3 * FLOAT_SIZE);

                map_index_buffer.glDraw(GL_QUADS);		   
	   }
	   
	   public void mouseControl()
	   {
                dx = Mouse.getDX();
                camera.pitchUp(-dx * senzitiviti);
                dy = Mouse.getDY();            
                camera.yawLeft(dy * senzitiviti);	

                if (cam == 0) {
                    camera.pitch = 180;
                    camera.yaw = 90;
                    cam = 1;
                }                 
                camera.look(10);   
	   }
	   public void checkKeyboard()
	   {
           if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
               camera.moveForward(0.05f);
               camera.look(10f);
           }           
           if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
               camera.moveForward(-0.05f);
               camera.look(10f);
           }
           if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
               camera.moveLateral(0.05f);
               camera.look(10f);
           }
           if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
               camera.moveLateral(-0.05f);
               camera.look(10f);
           }
           if (Keyboard.isKeyDown(Keyboard.KEY_X)) {
               camera.moveUp(0.05f);
               camera.look(10f);
           }
           if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
               camera.moveDown(0.05f);
               camera.look(10f);
           }
           if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
               camera.pitchDown(0.3f);
               camera.look(10);
           }
           if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
               camera.pitchUp(0.3f);
               camera.look(10);
           }
	   }
	   public void localPlayerControl() throws FileNotFoundException {
		   
           if (Keyboard.isKeyDown(Keyboard.KEY_UP) && !Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
 
                       if(!colizion.CubeDetectieColiziune(this , localPlayer.X - 0.02f, localPlayer.Y , gamePlayers.getListaCubes())) {
                           if(!colizion.playerBorderColizion(localPlayer.X - 0.02f, localPlayer.Y )) {
                               if(!colizion.entityPlayerColizionDetection(localPlayer.X - 0.02f, localPlayer.Y, gamePlayers.getListaEntity())) {
                                localPlayer.X-=0.01f;  
                               }
                           }
                       } 
                       
        	   activateDinamicTexture();
        	   client.sendData(("MOVE/"+localPlayer.name+"/"+String.valueOf(localPlayer.X)+"/"+String.valueOf(localPlayer.Y)+"/"+String.valueOf(localPlayer.Z)+"/"+String.valueOf(localPlayer.angle)).getBytes());  
        	   localPlayer.angle = 0;
                   direction = "-x";
           
           } 
           if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && !Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
     
                       if(!colizion.CubeDetectieColiziune(this , localPlayer.X + 0.02f, localPlayer.Y , gamePlayers.getListaCubes())) {
                           if(!colizion.playerBorderColizion(localPlayer.X + 0.02f, localPlayer.Y )) {
                               if(!colizion.entityPlayerColizionDetection(localPlayer.X + 0.02f, localPlayer.Y, gamePlayers.getListaEntity())) {
                                   localPlayer.X+=0.01f;
                               }
                           }
                       } 
                                              
        	   activateDinamicTexture();  
        	   client.sendData(("MOVE/"+localPlayer.name+"/"+String.valueOf(localPlayer.X)+"/"+String.valueOf(localPlayer.Y)+"/"+String.valueOf(localPlayer.Z)+"/"+String.valueOf(localPlayer.angle)).getBytes());
        	   localPlayer.angle = 180;
        	   direction = "x";
           } 
           if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !Keyboard.isKeyDown(Keyboard.KEY_UP) && !Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {

                   if(!colizion.CubeDetectieColiziune(this ,  localPlayer.X , localPlayer.Y - 0.02f , gamePlayers.getListaCubes())) {
                       if(!colizion.playerBorderColizion(localPlayer.X, localPlayer.Y - 0.02f )) {
                            if(!colizion.entityPlayerColizionDetection(localPlayer.X, localPlayer.Y - 0.02f, gamePlayers.getListaEntity())) {
                                localPlayer.Y-=0.01f;
                            }
                       }
                   } 

        	   activateDinamicTexture();  
        	   client.sendData(("MOVE/"+localPlayer.name+"/"+String.valueOf(localPlayer.X)+"/"+String.valueOf(localPlayer.Y)+"/"+String.valueOf(localPlayer.Z)+"/"+String.valueOf(localPlayer.angle)).getBytes());
        	   localPlayer.angle = 90;
        	   direction = "-y";
           }  
           if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)&& !Keyboard.isKeyDown(Keyboard.KEY_UP) && !Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {

                    if(!colizion.CubeDetectieColiziune(this , localPlayer.X , localPlayer.Y + 0.02f , gamePlayers.getListaCubes()))  {
                        if(!colizion.playerBorderColizion(localPlayer.X, localPlayer.Y + 0.02f )) {
                             if(!colizion.entityPlayerColizionDetection(localPlayer.X, localPlayer.Y + 0.02f, gamePlayers.getListaEntity())) {
                                 localPlayer.Y+=0.01f;
                             }
                        }
                    }

        	   activateDinamicTexture();
        	   client.sendData(("MOVE/"+localPlayer.name+"/"+String.valueOf(localPlayer.X)+"/"+String.valueOf(localPlayer.Y)+"/"+String.valueOf(localPlayer.Z)+"/"+String.valueOf(localPlayer.angle)).getBytes());
        	   localPlayer.angle = -90;
        	   direction = "y";
           } 
           if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
        	   if(fire) {               
                        bullet  =  new  Bullet(bullet_texture);
                        bullet.direction = direction;
                        bullet.X = localPlayer.X;
                        bullet.Y = localPlayer.Y;
                        bullet.Z = localPlayer.Z;
                        bullet.owner = "player";
                        gamePlayers.getListaBullets().add(bullet);
        	   fire = false;
                   client.sendData(("FIRE/"+localPlayer.name+"/"+bullet.X+"/"+bullet.Y+"/"+bullet.Z+"/"+bullet.angle+"/"+bullet.direction).getBytes());
        	   }
           } else {
        	   fire = true;
           }
            if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
                if(delete) {
                    gamePlayers.getListaEntity().get(0).deleteEntity();
                    gamePlayers.getListaEntity().clear();
                    delete = false;
                }
            }
           if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
        	  gamePlayers.getListaBullets().clear();
           }
           if(!Keyboard.isKeyDown(Keyboard.KEY_UP) && !Keyboard.isKeyDown(Keyboard.KEY_DOWN) && !Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
        	   localPlayer.dinamicRender = false;
                   client.sendData(("DESABLE_ANIMATION/"+localPlayer.name).getBytes()); 
           }
              
	   } 
	   
	   public void activateDinamicTexture() {
    	   if(animationStatus) {    		       		   
    		   localPlayer.dinamicRender = false;
    		   animationStatus = false;
    	   } else {
    		   		localPlayer.dinamicRender = true;
    		   		animationStatus = true;
                                client.sendData(("ENABLE_ANIMATION/"+localPlayer.name).getBytes());
                  }   
	   }
           
           public void fireBuller(String owner  , float x  ,  float y  ,  String direction) throws FileNotFoundException {
               
               Bullet bullet = new Bullet(bullet_texture);
           
                        bullet.direction = direction;
                        bullet.X = x;
                        bullet.Y = y;
                        bullet.owner = owner;  
                   
                gamePlayers.getListaBullets().add(bullet);         
           }
           
           public void addGameEntity() throws FileNotFoundException {
           
                 entity = new Entity(textures1); 
                 entity.X = -4.5f;
                 entity.Y = -4.5f;
                 entity.direction = "x";
              gamePlayers.getListaEntity().add(entity);    
           }

}
