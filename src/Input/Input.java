
package Input;

import Border.Board_3;
import Camera.Camera;
import Display.SetupDisplay_LWJGL_3;
import IBO.IBO;
import static Input.KeyCallBack.keys;
import Maths.Maths;
import Menu.MenuData;
import Planet.Planet;
import Player.Player;
import Quad.Model;
import QuadTree.QT;
import Render.Renderer;
import Sun.Sun;
import TextUtil.TextData;
import Texture.TextureUtil;
import Util.MousePicker;

import java.util.ArrayList;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import spacecomander.Quad;
import spacecomander.QuadUtil;
import spacecomander.QuadsManagement;
import spacecomander.SpaceComander;


public class Input  {

//____MAP__SCALE______________________________________________________//
   float deltaScaleMap = 0.14412f;
//____________________________________________________________________//
   

    Camera camera;
    Renderer      renderer;
    Model map,galaxyMap;
    QuadUtil quadUtil;
    ArrayList<Quad>    shipList;
    float moveSpeed;
    Vector3f curentRay;

    float x_percent;
    float y_percent;
     
    MousePicker pick ;
    Maths maths;
    QuadsManagement  quadManagement;
    ArrayList<Player>    playerList;
    ArrayList<Sun>  sunList;
    ArrayList<TextData>  textToRender;
    

    
    boolean mouseIsDown;
    ArrayList<Quad>      phaserList;
    ArrayList<Quad>      bordersList;
    SetupDisplay_LWJGL_3   display;
    
    boolean BUTTON_1_PRESS = false;
    boolean BUTTON_2_PRESS = false;
    
    public  GLFWScrollCallback scrollCallback;
    public  GLFWKeyCallback    keyCallback;
    public  GLFWMouseButtonCallback mouseButtonCallback;
    public  GLFWCursorPosCallback mousePos;
    
    boolean space = false;
    boolean mkey = false;
    

    ArrayList<Quad>      menuPoliList;
    MenuData menuData;
    IBO      ibo;
    TextureUtil textureUtil;
    int race_index;
    ScrollCallback scroll;
    KeyCallBack keyboard;
    MouseClickCallback   mouseClick;

    

    ArrayList<Quad>      shipSelectionRing;
    
    Board_3 bord3;
    QT qt;
     
    public Input(SetupDisplay_LWJGL_3 display ,Camera camera ,Renderer  renderer, Model map ,Model galaxyMap ,QuadUtil quadUtil, Maths maths ,QuadsManagement  quadManagement ,ArrayList<Player>  playerList ,ArrayList<Quad>  phaserList ,ArrayList<Sun>  sunList ,ArrayList<TextData>  textToRender ,ArrayList<Quad>  menuPoliList ,MenuData menuData, IBO  ibo,TextureUtil textureUtil ,int race_index , ArrayList<Quad>  shipSelectionRing,Board_3 bord3 , QT qt){
        this.display = display;
        this.camera = camera;
        this.renderer = renderer;
        this.map = map;
        this.galaxyMap = galaxyMap;
        this.maths = maths;
        this.shipList = shipList;
        this.quadUtil = quadUtil;
        this.quadManagement  =  quadManagement;
        this.playerList = playerList;
        this.phaserList = phaserList;
        this.sunList = sunList;
        this.bordersList = bordersList;
        pick = new MousePicker(display , camera , renderer.getProjectionMatrix() , maths);
        this.textToRender = textToRender;
        this.menuPoliList = menuPoliList;
        this.menuData = this.menuData;
        this.ibo = ibo;
        this.textureUtil = textureUtil;
        this.race_index = race_index;
        this.shipSelectionRing = shipSelectionRing;

        int Xpos , Ypos;

        scroll  = new ScrollCallback(camera , map ,galaxyMap , sunList,textToRender, menuPoliList, menuData,playerList);
        keyboard = new KeyCallBack();
        mouseClick = new MouseClickCallback(display , camera , renderer , maths , quadUtil , playerList,menuData,sunList, textToRender,ibo,textureUtil,race_index,shipSelectionRing,qt);

        scrollCallback =  glfwSetScrollCallback(display.getWindow(), scroll);
        keyCallback    =  glfwSetKeyCallback(display.getWindow(), keyboard );
        mouseButtonCallback = glfwSetMouseButtonCallback(display.getWindow(), mouseClick);
   
        
        
       this.bord3 = bord3;
       this.qt = qt;
    }
    public void setupCameraLocation(Quad ship)
    {
        scroll.ZoomIn();
        setupXYKeyboardLocation(ship);
        map.texture.transparency = 1.0f;
        galaxyMap.texture.transparency = 0.01f;
        SpaceComander.selectEdgeWidth = camera.getPosition().z/1000;
    }
    public void setupXYKeyboardLocation(Quad ship)
    {
        if(ship.curentPosition.y > camera.getPosition().y)
        {
            while(Math.abs(ship.curentPosition.y - camera.getPosition().y) >=0.01f)
            {
                moveUpY();
            }
        }
        if(ship.curentPosition.y < camera.getPosition().y)
        {
            while(Math.abs(camera.getPosition().y - ship.curentPosition.y) >=0.01f)
            {
                moveDownY();
            }
        }
        if(ship.curentPosition.x > camera.getPosition().x)
        {
            while(Math.abs(ship.curentPosition.x - camera.getPosition().x) >=0.01f)
            {
                moveRightX();
            }
        }
        if(ship.curentPosition.x < camera.getPosition().x)
        {
            while(Math.abs(camera.getPosition().x - ship.curentPosition.x) >=0.01f)
            {
                moveLeftX();
            }
        }
    }
    public void moveUpY(){
        moveSpeed = 0.005f * Math.abs(camera.getPosition().z);
        camera.getPosition().y += moveSpeed;
        updateYtext(moveSpeed);
        updateMenuPoligonsY(moveSpeed);
        map.position.y +=moveSpeed;
        checkCameraBox();
    }
    public void moveDownY()
    {
        moveSpeed = 0.005f * Math.abs(camera.getPosition().z);
        camera.getPosition().y -= moveSpeed;
        updateYtext(-moveSpeed);
        updateMenuPoligonsY(-moveSpeed);
        map.position.y -=moveSpeed;
        checkCameraBox();

    }
    public void moveRightX()
    {
        moveSpeed = 0.005f * Math.abs(camera.getPosition().z);
        camera.getPosition().x += moveSpeed;
        updateXtext(moveSpeed);
        updateMenuPoligonsX(moveSpeed);
        map.position.x +=moveSpeed;
        checkCameraBox();

    }
    public void moveLeftX(){
        moveSpeed = 0.005f * Math.abs(camera.getPosition().z);
        camera.getPosition().x -= moveSpeed;
        updateXtext(-moveSpeed);
        updateMenuPoligonsX(-moveSpeed);
        map.position.x -=moveSpeed;
        checkCameraBox();
    }
    public void KeyboardControl(){

         if(keys[GLFW.GLFW_KEY_W]) {
             moveUpY();
         }
         if(keys[GLFW.GLFW_KEY_S]) {
             moveDownY();
         }     
         if(keys[GLFW.GLFW_KEY_A]) {
             moveLeftX();
         }         
         if(keys[GLFW.GLFW_KEY_D]) {
             moveRightX();
         }
        if(keys[GLFW.GLFW_KEY_C]) {
            playerList.get(race_index).planetColector = mouseClick.planet;
        }
        if(keys[GLFW.GLFW_KEY_ESCAPE]) {
              glfwSetWindowShouldClose(display.getWindow(), true);  
              space = true;
         }                 
         if(keys[GLFW.GLFW_KEY_M]) {
             if(!mkey)
             {
                mkey = true;
             }

         }
        if(keys[GLFW.GLFW_KEY_U]) {
            if(!mkey)
            {
                textureUtil.mapTex.glDispose();
                mkey = true;
            }

        }
        if(keys[GLFW.GLFW_KEY_Y]) {
            if(mkey)
            {
                textureUtil.mapTex.glInit();
                mkey = false;
            }

        }
         if(keys[GLFW.GLFW_KEY_X]) {
          space = false;
          mkey = false;
             if(!space) {

                 SpaceComander.stop_game = true;

                 for(Player p :playerList)
                 {
                     bord3.buildBorder(p);
                     //System.out.println("----------------------------------------");
                 }
               
                 space = false;
             }

         }          
        if(keys[GLFW.GLFW_KEY_SPACE]) {


            if(!space)
            {
              SpaceComander.stop_game = false;
              space = true;            
            }
         if(keys[GLFW.GLFW_KEY_B]) { 
             playerList.get(0).shipList.clear();
         }           
         }         
    }
    public void updateMenuPoligonsX(float delta) {
        for(Quad menuP : menuPoliList) {
            menuP.curentPosition.x += delta;
        }    
    }
    public void updateMenuPoligonsY(float delta) {
        for(Quad menuP : menuPoliList) {
            menuP.curentPosition.y += delta;
        }    
    }    
    public void updateXtext(float delta){
        for(TextData text : textToRender) {
            for(Quad quad : text.textChars) {
               quad.curentPosition.x += delta;
            }
        }     
    }
    public void updateYtext(float delta) {
        for(TextData text : textToRender){
             for(Quad quad : text.textChars) {
                 quad.curentPosition.y += delta;
            }
        }    
    }    
    public void checkCameraBox() 
    {
       for(Sun sun : sunList) {
              if(checkConstrainsS(sun)) {
                 sun.enableToRender = true;
              } else {
                       sun.enableToRender = false;
                     }           
           
           for(Planet planet : sun.listaPlanete){
              if(checkConstrainsP(planet)){
                 planet.enableToRender = true;
                 if(planet.ring != null) {
                     planet.ring.enableToRender = true;
                 }
              } else {
                       planet.enableToRender = false;
                        if(planet.ring != null) {
                            planet.ring.enableToRender = false;
                        }                       
                     }
            if(camera.getPosition().z > 2.5)
            {
                planet.enableToRender = false;
            } else {
                        planet.enableToRender = true;
                   }  

           }
       
       }
    }
    public boolean checkConstrainsS(Sun sun) {
        if(sun.position.x > camera.getPosition().x - map.scale.x   &&  sun.position.x < camera.getPosition().x + map.scale.x){
            if(sun.position.y > camera.getPosition().y - map.scale.y   &&  sun.position.y < camera.getPosition().y + map.scale.y){
               return true;
            } else {
                     return false;
                   }        
        } else {
                 return false;
               }
    }    
    public boolean checkConstrainsP(Planet planet) {
        if(planet.position.x > camera.getPosition().x - map.scale.x   &&  planet.position.x < camera.getPosition().x + map.scale.x){
            if(planet.position.y > camera.getPosition().y - map.scale.y   &&  planet.position.y < camera.getPosition().y + map.scale.y){
               return  true;
            } else {
                      return false;
                   }        
        } else {
                  return false;
               }
    }
 public void releseCalback(){
   // keyCallback.free();
   // scrollCallback.free();
 }
  
}
