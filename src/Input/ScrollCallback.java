
package Input;

import Camera.Camera;
import Menu.MenuData;
import Planet.Planet;
import Player.Player;
import Quad.Model;
import Sun.Sun;
import TextUtil.TextData;
import java.util.ArrayList;
import org.lwjgl.glfw.GLFWScrollCallback;
import spacecomander.Quad;
import spacecomander.SpaceComander;


public class ScrollCallback extends  GLFWScrollCallback{

 Camera camera;   
 Model map , galaxyMap;
 ArrayList<Sun>  sunList;
 ArrayList<TextData>  textToRender;
ArrayList<Quad>      menuPoliList; 
 MenuData menuData;
 ArrayList<Player>    playerList;

 int zoomSpeed = 1;
//____MAP__SCALE______________________________________________________//
   float deltaScaleMap = 0.14412f;
//____________________________________________________________________//  
    
    public ScrollCallback(Camera camera , Model map ,Model galaxyMap ,  ArrayList<Sun>  sunList , ArrayList<TextData>  textToRender,ArrayList<Quad> menuPoliList,MenuData menuData , ArrayList<Player>    playerList) {
        this.camera = camera;
        this.map = map;
        this.galaxyMap = galaxyMap;
        this.sunList = sunList;
        this.textToRender = textToRender;
        this.menuPoliList = menuPoliList;
        this.menuData = menuData;
        this.playerList = playerList;
    }
    public void ZoomIn(){
        while(camera.getPosition().z >= 0.5f) {
            zoomCameraIn(1);
        }
    }
    
    @Override
    public void invoke(long win, double dx, double dy) {
             if (dy > 0 && camera.getPosition().z > 0.5) {
                 if(camera.getPosition().z <= 5) {
                     zoomSpeed = 1;
                 }
                 if(camera.getPosition().z <=10) {
                     zoomSpeed = 2;
                 }
                 zoomCameraIn(zoomSpeed);
                 SpaceComander.selectEdgeWidth = camera.getPosition().z/1000;
            }
            if (dy < 0 && camera.getPosition().z < 15) {

                if(camera.getPosition().z > 5) {
                    zoomSpeed = 2;
                }
                if(camera.getPosition().z > 10) {
                    zoomSpeed = 8;
                }
                zoomCameraOut(zoomSpeed);
                  SpaceComander.selectEdgeWidth = camera.getPosition().z/1000;
            }
    }
    public void zoomCameraOut(int count)
    {
        for(int i=0 ; i < count ; i++)
        {
            if(camera.getPosition().z < 15)
            {
                if(galaxyMap.texture.transparency < 0.7f) {
                    galaxyMap.texture.transparency += 0.015f;
                    map.texture.transparency       -= 0.01f;;
                }

                camera.getPosition().z += 0.25f;
                map.scale.x += deltaScaleMap;
                map.scale.y += deltaScaleMap;

                for (TextData text : textToRender) {
                    for (Quad quad : text.textChars) {
                        quad.curentPosition.z += 0.25f;
                    }
                }
                for (Quad quad : menuPoliList) {
                    quad.model.position.z += 0.25f;
                }
                checkCameraBox();
            }
        }

    }
    public void zoomCameraIn(int count)
    {
        for(int i=0 ; i < count ; i++)
        {
                if(galaxyMap.texture.transparency >= 0.01) {
                    galaxyMap.texture.transparency -= 0.015f;
                    map.texture.transparency       += 0.01f;;
                }

                camera.getPosition().z += -0.25f;
                map.scale.x -= deltaScaleMap;
                map.scale.y -= deltaScaleMap;
              

                for (TextData text : textToRender) {
                    for (Quad quad : text.textChars) {
                        quad.curentPosition.z += -0.25f;
                    }
                }
                for (Quad quad : menuPoliList) {
                    quad.model.position.z -= 0.25f;
                }

                checkCameraBox();
           // }
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
}
