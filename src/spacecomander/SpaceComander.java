
package spacecomander;

import AILogic.AILogic;
import AILogic.AIUtil;
import Border.Board_3;
import Camera.Camera;
import Campaign.Campaign;
import Display.SetupDisplay_LWJGL_3;
import IBO.*;
import Input.Input;
import Maths.Maths;
import Menu.MenuData;
import Planet.Planet;
import Player.Player;
import Quad.Model;
import QuadTree.QT;
import QuadTree.QTree;
import Render.Renderer;
import SolarSystem.SolarSystem;
import Sun.Sun;
import TextUtil.TextData;
import Texture.TextureUtil;
import Util.MousePicker;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowTitle;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import org.lwjgl.opengl.GL11;


import shaders.ShaderProgram;


public class SpaceComander {

    
    float bulletdeleteRange  = 1;
    public static boolean enable_map = true;

    
//___MENU_CONTROL_____________________________________________________//
   public static boolean  star_select_menu   = true;
   public static boolean  planet_select_menu = false;   
   public static boolean  colony_menu_1 = true;  
   public static boolean  colony_menu_2 = false;
   public static boolean  buildings_menu = false; 
   public static boolean  shipYard_menu = false;
   public static boolean  cargoBay_Menu = false;
   public static boolean  upgrades_menu = false;   
   public static boolean  tec_menu = false;
   public static boolean  ship_select_menu = false;
//____________________________________________________________________//

//___TEXTURE_CONTROL____________________________________________________________________//
   public static boolean shipSelectMenuText = false;



    boolean game_graphics_mod = true;
    public boolean enableGame = true;
    public static boolean  stop_game = true;
    public static int  race_index = 0;

    public boolean fullScreen = false;
    public int     numberOfPlayer = 9;
    public int     numberOfStars = 1;
 //  int     numberOfStars = 10;
    public float scaleQuad = 1f;
    
    public int minRadius = 2;
    public int maxRadius = 10;
    
        
//______FPS___________________________________________________________//
    private double start = System.nanoTime() / 1000000000;
    private int valoare = 0;
    private double end;
//____________________________________________________________________// 
    
    TextData  textData;
    public ArrayList<TextData>  textToRender = new ArrayList<TextData>();
    public ArrayList<Quad>      menuPoliList = new ArrayList<Quad>();   
    public ArrayList<Quad>      phaserList   = new ArrayList<Quad>();
    public ArrayList<Quad>      phaserEmiter   = new ArrayList<Quad>();
    public ArrayList<Quad>      linePrimitive = new ArrayList<Quad>();
    public ArrayList<Quad>      pointPrimitive = new ArrayList<Quad>();     
    public ArrayList<Quad>      shieldList   = new ArrayList<Quad>();
    public ArrayList<Quad>      explosionList   = new ArrayList<Quad>();
    public ArrayList<Quad>      bulletList   = new ArrayList<Quad>();
    public ArrayList<Sun>       sunList      = new ArrayList<Sun>();
    public ArrayList<Player>    playerList   = new ArrayList<Player>();
    public ArrayList<Quad>      colonizedInfluenceRing = new ArrayList<Quad>();
    public ArrayList<Quad>      shipSelectionRing = new ArrayList<Quad>();

    public MenuData menuData;
    PrimitiveColor pColor = new PrimitiveColor();
    
    MousePicker pick;
    
    public Model shipM;
    IBO_Data quade_IBO_Data , triangle_IBO_Data ;
    IBO      quad_ibo , triangle_ibo;
    TextureUtil textureUtil;
    SolarSystem solarSystem;
    Input  input;
    QuadUtil quadUtil;
    Campaign campaign;
    AILogic aILogic;
    QT qt = new QT();
    AIUtil  aIUtil;
    QuadsManagement  quadManagement;
    SetupDisplay_LWJGL_3   display;
    
    Maths maths ;
    
    Camera        camera;
    ShaderProgram shaderProgram;
    Renderer      renderer;
    Model galaxy , mapM;   
    Board_3 bord3;
    
    public static boolean selectQuadStatus = false;
    public static float selectQuadX  = 0; 
    public static float selectQuadY  = 0;
    public static float selectWidth  = 0f;
    public static float selectHeight = 0f;

   double xPos,yPos;
   Vector3f curentRay;   
   DoubleBuffer   xBuffer , yBuffer;
   public static float selectEdgeWidth = 0.001f;


    public static void main(String[] args) {
       
        SpaceComander   sComander  =  new  SpaceComander();
                        sComander.initObject();
                        sComander.loop();
    }

    public void initObject(){
      
        display = new SetupDisplay_LWJGL_3();
        display.initWindow();
        campaign = new Campaign();
 
    }

    public void initMultiplayer(int numberOfPlayer)  {

        if(enableGame && game_graphics_mod) {
            for(int i=0 ; i<numberOfPlayer ; i++){
                  quadManagement.addColonizationShip(null, playerList.get(i),   playerList.get(i).start_position_x , playerList.get(i).start_position_y);
                  playerList.get(i).colonizationProcess = true;
            }   
             for(int i=0 ; i<numberOfPlayer ; i++) {
                    for(int j=0 ; j<numberOfPlayer ; j++){
                             if(playerList.get(j).index != playerList.get(i).index) {
                                 playerList.get(i).enemyList.add(playerList.get(j));
                             }
                    }  
            }  
        }           
    }
public void loop()   {
   
    textureUtil = new TextureUtil();
    textureUtil.loadTextures();
    quade_IBO_Data = new IBO_Data(new Vector3f(0, 0, 0) ,1f ,1f , 0);
    quad_ibo      = new IBO(quade_IBO_Data);
    triangle_IBO_Data = new IBO_Data(new Vector3f(0, 0, 0) ,1f ,1f , 1);
    triangle_ibo      = new IBO(triangle_IBO_Data);

    quadManagement = new QuadsManagement(colonizedInfluenceRing , quad_ibo  , triangle_ibo , textureUtil,scaleQuad,playerList , shipSelectionRing,race_index,linePrimitive,pointPrimitive);
       
    campaign.setupMultiplayer(playerList , numberOfPlayer , minRadius , maxRadius,race_index);
    initMultiplayer(numberOfPlayer);

    maths  =  new Maths(playerList);
    
    float scale = 4.9f;
    mapM = new Model(quad_ibo , textureUtil.mapTex ,0 , new Vector3f(0, 0, 0)  , new Vector3f(0, 0, 0) ,  new Vector3f(scale, scale, scale));
    scale = 15f;
    galaxy = new Model(quad_ibo , textureUtil.galaxyTex ,0 , new Vector3f(0, 0, 0.00001f)  , new Vector3f(0, 0, 0) ,  new Vector3f(scale, scale, scale));

       
       aIUtil = new AIUtil();

    solarSystem =new SolarSystem(quad_ibo ,textureUtil.solarSystemTex_max,maths);
    solarSystem.generateStars(numberOfStars , sunList , minRadius , maxRadius,race_index);

            camera         =  new  Camera(new Vector3f(0, 0, 8.5f) , 0,0,0); 
            aILogic = new AILogic(aIUtil , sunList , quadManagement , playerList, textToRender,camera); 
            bord3 = new Board_3(quadManagement , maths , playerList , sunList);
            quadUtil = new QuadUtil(quadManagement , camera , mapM , playerList , maths , race_index,shipSelectionRing , sunList,bord3,qt);
            menuData = new MenuData(menuPoliList,quad_ibo,textureUtil,quadUtil);
            shaderProgram  =  new  ShaderProgram(maths);   
            renderer       =  new  Renderer(shaderProgram  ,maths,camera);      




 input = new Input(display,camera ,renderer, mapM ,galaxy , quadUtil ,maths,quadManagement,playerList,phaserList,sunList,textToRender,menuPoliList,menuData,quad_ibo,textureUtil,race_index,shipSelectionRing,bord3,qt);
    
 renderer.initQuad(quad_ibo);
     
menuData.createColonyMenu_1(camera);
if(game_graphics_mod) {
    if(playerList.get(race_index).coloniShip != null) {
        input.setupCameraLocation(playerList.get(race_index).coloniShip);
    }
}else {
   camera.getPosition().z = 15;
}

pick  = new MousePicker(display , camera , renderer.getProjectionMatrix() , maths);
shaderProgram.start(); 

qt.setRangeParam(0.1f);
 xBuffer = BufferUtils.createDoubleBuffer(1);
 yBuffer = BufferUtils.createDoubleBuffer(1);
   
while (!glfwWindowShouldClose(display.window)) 
{  
   //______NU__STERGE_________________________________________________//
    //GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);    
    glfwPollEvents();
    FPS();
    updateCursorPosition();
//____QUAD TREE__________________________________________________________________________________________________________________________________________//
//_______________________________________________________________________________________________________________________________________________________//    
   
 shaderProgram.loadViewMatrix(camera);

if(game_graphics_mod) { 
  renderMap();
  renderGalaxy();
}             
//__DRAW__PRIMITIVES___________________________________________________________________________________//
      //drawSquare(0,0,2 , 2  , pColor.Blue);
      //drawFillQuad(0, 0, 2f , pColor.Red);       
      if(selectQuadStatus)  {
          selectWidth  = (float) Math.abs(curentRay.x - selectQuadX);
          selectHeight = (float) Math.abs(curentRay.y - selectQuadY);
          drawSquare(Math.min(curentRay.x, selectQuadX) + selectWidth/2, Math.min(curentRay.y, selectQuadY) + selectHeight/2 , selectWidth/2 , selectHeight/2 , pColor.Blue , selectEdgeWidth);
      }
//____________________________________________________________________________________________________//
      
//_____________________________________________________________________________________________________//
    if(!game_graphics_mod) {
        renderLinePrimitives(); 
        renderPointPrimitives();
    } else {
                renderLinePrimitives(); 
                renderSolarSystem();
                renderColonyRings();
                renderSpaceBorder();
                renderShips();
                renderShipSelectedRing();
                renderBullets();
                renderPhaserEmiters();
                renderExplosions();
                renderShields();
                renderPhasers();
                renderMenuPoligons();
                renderText();
                quadUtil.checkTargetLockOn(playerList);
                aILogic.startAI();
                input.KeyboardControl();
           } 

  glfwSwapBuffers(display.getWindow());
}
    shaderProgram.stop();
    renderer.unbindQuadArray();
    cleanUP();
    glfwDestroyWindow(display.window);
    glfwTerminate();

}
public void QuadTreeSetup() {
    qt.arrayDataPoint.clear();
        for(int i=0 ; i<playerList.size() ; i++) {
            for(int j=0 ; j<playerList.get(i).shipList.size() ; j++) {
                qt.insertArrayData(playerList.get(i).shipList.get(j));
            }
        }
    qt.reloadTree();
}
public void updatePhaser(Quad phaser) {
            float delta_x = Math.abs(phaser.phaserEmiter.canon_1.x - phaser.phaserEmiter.target.x)/2;
            float delta_y = Math.abs(phaser.phaserEmiter.canon_1.y - phaser.phaserEmiter.target.y)/2;
            phaser.quadRotation.z = (float) ( - Math.toDegrees(Math.atan2(phaser.phaserEmiter.target.x - phaser.phaserEmiter.canon_1.x ,  phaser.phaserEmiter.target.y - phaser.phaserEmiter.canon_1.y)));
            if(phaser.phaserEmiter.targetShiep.shield > 0)
            {
                 float temp_x =   phaser.phaserEmiter.targetShiep.shielWidth  *  (float) Math.sin(Math.toRadians(phaser.quadRotation.z));
                 float temp_y =   phaser.phaserEmiter.targetShiep.shielWidth  *  (float) Math.cos(Math.toRadians(phaser.quadRotation.z));

                     if(phaser.phaserEmiter.canon_1.x > phaser.phaserEmiter.target.x  &&  phaser.phaserEmiter.canon_1.y >= phaser.phaserEmiter.target.y){
                        phaser.curentPosition.x = (phaser.phaserEmiter.canon_1.x - delta_x) + temp_x;
                        phaser.curentPosition.y = (phaser.phaserEmiter.canon_1.y - delta_y) - temp_y;
                     }
                     if(phaser.phaserEmiter.canon_1.x < phaser.phaserEmiter.target.x  &&  phaser.phaserEmiter.canon_1.y < phaser.phaserEmiter.target.y){
                        phaser.curentPosition.x = (phaser.phaserEmiter.canon_1.x + delta_x)  + temp_x;
                        phaser.curentPosition.y = (phaser.phaserEmiter.canon_1.y + delta_y)  - temp_y;
                     }   
                     if(phaser.phaserEmiter.canon_1.x > phaser.phaserEmiter.target.x  &&  phaser.phaserEmiter.canon_1.y < phaser.phaserEmiter.target.y){
                        phaser.curentPosition.x = (phaser.phaserEmiter.canon_1.x - delta_x) + temp_x;
                        phaser.curentPosition.y = (phaser.phaserEmiter.canon_1.y + delta_y) - temp_y;
                     }
                     if(phaser.phaserEmiter.canon_1.x < phaser.phaserEmiter.target.x  &&  phaser.phaserEmiter.canon_1.y >= phaser.phaserEmiter.target.y){
                        phaser.curentPosition.x = (phaser.phaserEmiter.canon_1.x + delta_x) + temp_x;
                        phaser.curentPosition.y = (phaser.phaserEmiter.canon_1.y - delta_y) - temp_y ;
                     }        
                     phaser.model.scale.y  = (float) (Math.sqrt(Math.pow(phaser.phaserEmiter.canon_1.x - phaser.phaserEmiter.target.x , 2.) + Math.pow(phaser.phaserEmiter.canon_1.y - phaser.phaserEmiter.target.y, 2))  / 2) - phaser.phaserEmiter.targetShiep.shielWidth;

             }   else  {

                             if(phaser.phaserEmiter.canon_1.x > phaser.phaserEmiter.target.x  &&  phaser.phaserEmiter.canon_1.y > phaser.phaserEmiter.target.y){
                                phaser.curentPosition.x = phaser.phaserEmiter.canon_1.x - delta_x;
                                phaser.curentPosition.y = phaser.phaserEmiter.canon_1.y - delta_y;
                             }
                             if(phaser.phaserEmiter.canon_1.x < phaser.phaserEmiter.target.x  &&  phaser.phaserEmiter.canon_1.y < phaser.phaserEmiter.target.y){
                                phaser.curentPosition.x = phaser.phaserEmiter.canon_1.x + delta_x;
                                phaser.curentPosition.y = phaser.phaserEmiter.canon_1.y + delta_y;
                             }   
                             if(phaser.phaserEmiter.canon_1.x > phaser.phaserEmiter.target.x  &&  phaser.phaserEmiter.canon_1.y < phaser.phaserEmiter.target.y){
                                phaser.curentPosition.x = phaser.phaserEmiter.canon_1.x - delta_x;
                                phaser.curentPosition.y = phaser.phaserEmiter.canon_1.y + delta_y;
                             }
                             if(phaser.phaserEmiter.canon_1.x < phaser.phaserEmiter.target.x  &&  phaser.phaserEmiter.canon_1.y > phaser.phaserEmiter.target.y){
                                phaser.curentPosition.x = phaser.phaserEmiter.canon_1.x + delta_x;
                                phaser.curentPosition.y = phaser.phaserEmiter.canon_1.y - delta_y;
                             }          
                             phaser.model.scale.y  = (float) (Math.sqrt(Math.pow(phaser.phaserEmiter.canon_1.x - phaser.phaserEmiter.target.x , 2.) + Math.pow(phaser.phaserEmiter.canon_1.y - phaser.phaserEmiter.target.y, 2))  / 2);   
                       }

}
public void renderSolarSystem(){
           renderer.loadNumbetOfRows(textureUtil.solarSystemTex_max.rows, shaderProgram);
            renderer.loadTexTransparenci(1.0f,shaderProgram);
            textureUtil.solarSystemTex_max.glBind();      
            for(Sun sun : sunList)  {
                if(sun.exploredList.get(0))  {
                    if (sun.enableToRender) {
                        renderer.loadQuadMatrix(sun.model, shaderProgram);
                        renderer.renderQuad(sun.model);
                    }
                    for (Planet planet : sun.listaPlanete) {

                        if (planet.enableToRender) {
                            renderer.loadQuadMatrix(planet.model, shaderProgram);
                            renderer.renderQuad(planet.model);
                        }
                    }
                }
            }
}
public void renderColonyRings() {
    renderer.loadNumbetOfRows(textureUtil.ringTexture.rows, shaderProgram);
    renderer.loadTexTransparenci(textureUtil.ringTexture.transparency, shaderProgram);
    textureUtil.ringTexture.glBind();
    for(Quad ring : colonizedInfluenceRing) {
        if(ring.enableToRender)  {
            if(playerList.get(ring.quadPlayerIndex).renderPlayerElements) {
                renderer.loadQuadMatrix(ring.model, shaderProgram);
                renderer.renderQuad(ring.model);
            }
        }
    }
}
public void renderShips() {
            renderer.loadNumbetOfRows(textureUtil.shipTex_max.rows, shaderProgram);
            renderer.loadTexTransparenci(textureUtil.shipTex_max.transparency, shaderProgram);
            textureUtil.shipTex_max.glBind();
            for(int i=0 ; i<playerList.size() ; i++)  {
                for(int j=0 ; j<playerList.get(i).shipList.size() ; j++)   {
                    Quad ship = playerList.get(i).shipList.get(j);                    
                    if(ship.hasAtack) {
                        ship.shipTargerLockStatus = false;
                    }                   
                    if(playerList.get(i).renderPlayerElements) {
                        if (ship.enableToRender) {
                            renderer.loadQuadMatrix(ship.model, shaderProgram);
                            renderer.renderQuad(ship.model);
                        }
                    }
                    quadUtil.move(playerList.get(i).shipList.get(j) , bulletList , explosionList);
                    quadUtil.shipCheck(playerList.get(i), ship , sunList , bulletList, explosionList ,phaserList ,shieldList,      phaserEmiter ,j);                    
                   if(!ship.alive)  {
                      if(ship.isColoniShip)  {
                        playerList.get(i).colonizationProcess = false;
                        playerList.get(i).coloniShip = null;
                      } else {
                                if (ship.deployPlanet != null)  {
                                      playerList.get(i).shipCount--;
                                      ship.deployPlanet.shipCount--;
                                }
                             }
                      playerList.get(i).shipList.remove(j);
                      j--;
                   }
                }   
            }
}
public void renderShipSelectedRing() {
    renderer.loadNumbetOfRows(textureUtil.ringTexture.rows, shaderProgram);
    renderer.loadTexTransparenci(1, shaderProgram);
    textureUtil.ringTexture.glBind();
    for(int i=0 ; i < shipSelectionRing.size() ; i++) {
        Quad shipRing = shipSelectionRing.get(i);
        renderer.loadQuadMatrix(shipRing.model, shaderProgram);
        renderer.renderQuad(shipRing.model);
        if(!shipRing.alive)  {
            shipSelectionRing.remove(i);
            i--;
        }
    }
}
public void renderBullets() {
           if(bulletList.size()!= 0){
                renderer.loadNumbetOfRows(textureUtil.bulletTex.rows, shaderProgram);
                textureUtil.bulletTex.glBind();  
            }
            for(int i=0 ; i<bulletList.size() ; i++)  {
              Quad  bullet = bulletList.get(i);  
              if(bullet.enableToRender)  {
                  if(playerList.get(bullet.quadPlayerIndex).renderPlayerElements)   {
                      renderer.loadQuadMatrix(bullet.model, shaderProgram);
                      renderer.renderQuad(bullet.model);
                  }
              }
              quadUtil.moveBullet(bullet);
              quadUtil.bulletCheck(bullet, explosionList , shieldList);              
              if(!bullet.alive)  {
                 bulletList.remove(i);
                 i--;
              } else {
                  if (Math.abs(bullet.curentPosition.x - bullet.bulletFiredCoordX) > bulletdeleteRange/quadManagement.scaleQuad || Math.abs(bullet.curentPosition.y - bullet.bulletFiredCoordY) > bulletdeleteRange/quadManagement.scaleQuad)   {
                    bulletList.remove(i);
                    i--;
                  }
              }
            }
}
public void renderPhaserEmiters() {
    if(phaserEmiter.size() != 0){
        renderer.loadNumbetOfRows(textureUtil.phasetEmiter.rows, shaderProgram);
        renderer.loadTexTransparenci(textureUtil.phasetEmiter.transparency, shaderProgram);
        textureUtil.phasetEmiter.glBind();
    }
    for(int i=0 ; i<phaserEmiter.size() ; i++)  {
        Quad emiter =  phaserEmiter.get(i);
        if(playerList.get(emiter.quadPlayerIndex).renderPlayerElements)  {
            renderer.loadQuadMatrix(emiter.model, shaderProgram);
            renderer.renderQuad(emiter.model);
        }
        emiter.model.ANIMATION_COUNT++;
        if(emiter.model.ANIMATION_COUNT == emiter.model.ANIMATION_RESET_V)  {
            emiter.model.ANIMATION_COUNT = 0;
            emiter.model.INDEX_T++;
            emiter.model.ANIMATION_DELETE_INDEX++;

        }
        if(emiter.model.ANIMATION_DELETE_INDEX == emiter.model.ANIMATION_FRAME_NUMBER)  {
            phaserEmiter.remove(i);
            i--;
        }
    }
}
public void renderSpaceBorder() {
           renderer.loadNumbetOfRows(textureUtil.board.rows, shaderProgram);
           renderer.loadTexTransparenci(textureUtil.board.transparency, shaderProgram);
            textureUtil.board.glBind();  
            for(int i=0 ; i<playerList.size() ; i++)  {
                for(int k=0 ; k<playerList.get(i).bordersList.size() ; k++)     {  
                    if(playerList.get(i).renderPlayerElements)   {
                         if(playerList.get(i).bordersList.get(k).enableToRender)    {
                            renderer.loadQuadMatrix(playerList.get(i).bordersList.get(k).model, shaderProgram);
                            renderer.renderQuad(playerList.get(i).bordersList.get(k).model);
                         }
                    }
                    if(!playerList.get(i).bordersList.get(k).alive)  {
                        playerList.get(i).bordersList.remove(k);
                        k--;
                    }
                }
            }
}
public void renderExplosions() {
            if(explosionList.size() != 0){
                renderer.loadNumbetOfRows(textureUtil.explosionTex.rows, shaderProgram);
                renderer.loadTexTransparenci(textureUtil.explosionTex.transparency, shaderProgram);
                textureUtil.explosionTex.glBind();     
            }
            for(int i=0 ; i<explosionList.size() ; i++)  { 
              Quad explosion =  explosionList.get(i);
              if(playerList.get(explosion.quadPlayerIndex).renderPlayerElements)   {
                  renderer.loadQuadMatrix(explosion.model, shaderProgram);
                  renderer.renderQuad(explosion.model);
              }
              explosion.model.ANIMATION_COUNT++;
              if(explosion.model.ANIMATION_COUNT == explosion.model.ANIMATION_RESET_V)    {   
                  explosion.model.ANIMATION_COUNT = 0;
                  explosion.model.INDEX_T++;
                  explosion.model.ANIMATION_DELETE_INDEX++;
              }
              if(explosion.model.ANIMATION_DELETE_INDEX == explosion.model.ANIMATION_FRAME_NUMBER)   {
                explosionList.remove(i);
                i--;
              }
            }  
}
public void renderShields() {
            if(shieldList.size() != 0){
                renderer.loadNumbetOfRows(textureUtil.shieldTex.rows, shaderProgram);
                renderer.loadTexTransparenci(textureUtil.shieldTex.transparency, shaderProgram);
                textureUtil.shieldTex.glBind();     
            }
            for(int i=0 ; i<shieldList.size() ; i++)  {
                    Quad shield = shieldList.get(i);
              if(playerList.get(shield.quadPlayerIndex).renderPlayerElements)     {
                  renderer.loadQuadMatrix(shield.model, shaderProgram);
                  renderer.renderQuad(shield.model);
              }
              shield.model.ANIMATION_COUNT++;
              if(shield.model.ANIMATION_COUNT == shieldList.get(i).model.ANIMATION_RESET_V)  {
                  shield.model.ANIMATION_COUNT = 0;
                  shield.model.INDEX_T++;
                  shield.model.ANIMATION_DELETE_INDEX++;
              }
              if(shield.model.ANIMATION_DELETE_INDEX == shield.model.ANIMATION_FRAME_NUMBER)   {
                shieldList.remove(i);
                i--;
              }
            } 
}
public void renderPhasers(){
    if(phaserList.size() != 0){
        renderer.loadNumbetOfRows(textureUtil.phaserTex.rows, shaderProgram);
        renderer.loadTexTransparenci(textureUtil.phaserTex.transparency, shaderProgram);
        textureUtil.phaserTex.glBind();
    }
    for(int i=0 ; i<phaserList.size() ; i++)  {
            Quad phaser = phaserList.get(i);
            updatePhaser(phaserList.get(i));
            if (phaserList.get(i).phaserEmiter.targetShiep.alive) {
                if(playerList.get(phaser.quadPlayerIndex).renderPlayerElements)    {
                    renderer.loadQuadMatrix(phaser.model, shaderProgram);
                    renderer.renderQuad(phaser.model);
                }
            }
            phaser.model.ANIMATION_COUNT++;
            if (phaser.model.ANIMATION_COUNT == phaser.model.ANIMATION_RESET_V) {
                phaser.model.ANIMATION_COUNT = 0;
                phaser.model.INDEX_T++;
                phaser.model.ANIMATION_DELETE_INDEX++;
            }
            if (phaser.model.ANIMATION_DELETE_INDEX == phaser.model.ANIMATION_FRAME_NUMBER) {
                phaserList.remove(i);
                i--;
            }
    }
}
public void renderMenuPoligons() {
    if(!shipSelectMenuText) {
        renderer.loadNumbetOfRows(textureUtil.solarSystemTex_max.rows, shaderProgram);
        renderer.loadTexTransparenci(textureUtil.solarSystemTex_max.transparency , shaderProgram);
        textureUtil.solarSystemTex_max.glBind();
    } else {
        renderer.loadNumbetOfRows(textureUtil.shipTex_max.rows, shaderProgram);
        renderer.loadTexTransparenci(textureUtil.shipTex_max.transparency , shaderProgram);
        textureUtil.shipTex_max.glBind();
    }

    for(Quad menuP : menuPoliList) {
        renderer.loadQuadMatrix(menuP.model, shaderProgram);
        renderer.renderQuad(menuP.model);
    }
}
public void renderText() {
        renderer.loadNumbetOfRows(textureUtil.textChart.rows, shaderProgram);
        textureUtil.textChart.glBind();
        for(TextData textData : textToRender)  {
            for(Quad character : textData.textChars)  {
                renderer.loadQuadMatrix(character.model, shaderProgram);
                renderer.renderQuad(character.model);
            }
        }
}
public void renderGalaxy() {
            renderer.loadNumbetOfRows(textureUtil.galaxyTex.rows, shaderProgram);
            renderer.loadTexTransparenci(textureUtil.galaxyTex.transparency,shaderProgram);
            textureUtil.galaxyTex.glBind();
            renderer.loadQuadMatrix(galaxy, shaderProgram);
            renderer.renderQuad(galaxy);
}
public void renderMap() {

        renderer.loadNumbetOfRows(textureUtil.mapTex.rows, shaderProgram);
        renderer.loadTexTransparenci(textureUtil.mapTex.transparency, shaderProgram);
        textureUtil.mapTex.glBind();
        renderer.loadQuadMatrix(mapM, shaderProgram);
        renderer.renderQuad(mapM);
}
public void renderLinePrimitives()  {         
           renderer.loadNumbetOfRows(textureUtil.board.rows, shaderProgram);
           renderer.loadTexTransparenci(textureUtil.board.transparency, shaderProgram);
           textureUtil.board.glBind();  
                for(int k=0 ; k<linePrimitive.size() ; k++)    {  
                    renderer.loadQuadMatrix(linePrimitive.get(k).model, shaderProgram);
                    renderer.renderQuad(linePrimitive.get(k).model); 
                } 
                linePrimitive.clear(); 
}
public void renderPointPrimitives()  {         
           renderer.loadNumbetOfRows(textureUtil.board.rows, shaderProgram);
           renderer.loadTexTransparenci(textureUtil.board.transparency, shaderProgram);
           textureUtil.board.glBind();  
                for(int k=0 ; k<pointPrimitive.size() ; k++)    {  
                    renderer.loadQuadMatrix(pointPrimitive.get(k).model, shaderProgram);
                    renderer.renderQuad(pointPrimitive.get(k).model); 
                } 
                pointPrimitive.clear();
}
public void drawPoint(float x , float y , float w , int textIndex) {
  quadManagement.addPoint(x, y, w, textIndex);
}
public void drawFillQuad(float x , float y , float w , int textIndex) {
   quadManagement.addPoint(x, y, w, textIndex);
}
public void drawSquare(float x , float y , float w , float h   , int textIndex , float selectEdgeWidth) {
    quadManagement.addSegmentLine(x - w, y - h, x + w, y - h , selectEdgeWidth , textIndex);
    quadManagement.addSegmentLine(x - w, y + h, x + w, y + h , selectEdgeWidth , textIndex);
    quadManagement.addSegmentLine(x - w, y + h, x - w, y - h , selectEdgeWidth , textIndex);
    quadManagement.addSegmentLine(x + w, y - h, x + w, y + h , selectEdgeWidth , textIndex);  
}
public void updateCursorPosition() {
    glfwGetCursorPos(display.window, xBuffer, yBuffer);
    xPos = xBuffer.get(0);
    yPos = yBuffer.get(0); 
    curentRay = pick.update(true , xPos , yPos);
}
public void cleanUP() {
    	shaderProgram.cleanUP();
	quad_ibo.cleanUp();
        triangle_ibo.cleanUp();
        textureUtil.solarSystemTex_max.glDispose();
        textureUtil.shipTex_max.glDispose();
        textureUtil.mapTex.glDispose();
        textureUtil.textChart.glDispose();
        input.releseCalback();
}
    public void FPS(){        
        int totalShipCount = 0;
        for(Player play : playerList) {
            totalShipCount += play.shipList.size();
        }   
        end = System.nanoTime() / 1000000000;
        if (end - start > 1) {
            start = end;
              glfwSetWindowTitle(display.getWindow(), "MadeByMangyAlex FPS " + String.valueOf(valoare/2)+"   Ships = "+totalShipCount+"   Rings = "+colonizedInfluenceRing.size()+"  Bullet = "+bulletList.size()+"  Phaser = "+phaserList.size()+"   Explosion = "+explosionList.size()+"  Shiel = "+shieldList.size()+" MENU = "+menuPoliList.size()+" Cam[z] = "+camera.getPosition().z+" [T] "+galaxy.texture.transparency+"  P"+pointPrimitive.size()+"  E"+linePrimitive.size());
              valoare = 0;
        }
        valoare++;
    }   
public void preOrder(QTree tree) {
    if(tree != null)   {
        //drawSquare(tree.boundary.x , tree.boundary.y , tree.boundary.w, tree.boundary.w , 20);
        preOrder(tree.northEast);
        preOrder(tree.northWest);
        preOrder(tree.southEast);
        preOrder(tree.southWest);
    }
}
}
class PrimitiveColor {
   int Red = 20;
   int Blue = 21;
   int Green = 22;

}
