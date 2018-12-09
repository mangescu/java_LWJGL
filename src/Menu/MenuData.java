
package Menu;

import Camera.Camera;
import IBO.IBO;
import Planet.Planet;
import Player.Player;
import Sun.Sun;
import TextUtil.TextData;
import Texture.Textura;
import Texture.TextureUtil;
import java.util.ArrayList;
import spacecomander.Quad;
import spacecomander.QuadUtil;


public class MenuData {
    
    public ArrayList<Quad>  menuPoliList;
    
    public MenuPoligons menuPoligons;
    TextureUtil textureUtil;
    
    public Textura curentTexture;
    
    float planet_stacer , delta;
    QuadUtil quadUtil;
    
    public float ship_select_delta = 0;
    public float coloniDelta = 0;
    
    public MenuData(ArrayList<Quad>  menuPoliList , IBO ibo, TextureUtil textureUtil , QuadUtil quadUtil) {
        this.menuPoliList = menuPoliList;
        this.textureUtil = textureUtil;
        this.quadUtil = quadUtil;
        curentTexture = textureUtil.solarSystemTex_max;
        menuPoligons = new MenuPoligons(ibo,menuPoliList);
    }
    public void createShipSelectMenu(Camera cam , Player player){
        menuPoligons.createPoligon(curentTexture, cam ,    0.0f    ,  -0.127f     ,   0.14f , 0.012f,     88  , 0);
        ship_select_delta = 0;
            if(quadUtil.cargo_ship_count != 0) {
                menuPoligons.createPoligon(curentTexture, cam ,    -0.12f + ship_select_delta  ,  -0.125f        ,   0.008f , 0.008f,     player.ship_tex_index  , 0);
                ship_select_delta += 0.025f;
            }
            if(quadUtil.coloni_ship_count != 0) {
                menuPoligons.createPoligon(curentTexture, cam ,    -0.12f + ship_select_delta  ,  -0.125f        ,   0.008f , 0.008f,     player.ship_tex_index + 1  , 0);
                ship_select_delta += 0.025f;
            }
            if(quadUtil.explorer_ship_count != 0) {
                menuPoligons.createPoligon(curentTexture, cam ,    -0.12f + ship_select_delta  ,  -0.125f        ,   0.008f , 0.008f,     player.ship_tex_index + 3  , 0);
                ship_select_delta += 0.025f;
            }
            if(quadUtil.fregate_ship_count != 0) {
                if(player.index == 0 || player.index == 1) {
                    menuPoligons.createPoligon(curentTexture, cam, -0.12f + ship_select_delta, -0.125f, 0.008f, 0.008f, player.ship_tex_index + 7, 0);
                    ship_select_delta += 0.025f;
                } else {
                    menuPoligons.createPoligon(curentTexture, cam, -0.12f + ship_select_delta, -0.125f, 0.008f, 0.008f, player.ship_tex_index + 2, 0);
                    ship_select_delta += 0.025f;
                }
            }
            if(quadUtil.destroier_ship_count != 0) {
                menuPoligons.createPoligon(curentTexture, cam ,    -0.12f + ship_select_delta  ,  -0.125f        ,   0.008f , 0.008f,     player.ship_tex_index + 4  , 0);
                ship_select_delta += 0.025f;
            }
            if(quadUtil.carrier_ship_count != 0) {
                menuPoligons.createPoligon(curentTexture, cam ,    -0.12f + ship_select_delta  ,  -0.125f        ,   0.008f , 0.008f,     player.ship_tex_index + 5  , 0);
                ship_select_delta += 0.025f;
            }
            if(quadUtil.interceptor_ship_count != 0) {
                menuPoligons.createPoligon(curentTexture, cam ,    -0.12f + ship_select_delta  ,  -0.125f        ,   0.003f , 0.003f,     player.ship_tex_index + 2  , 0);
                ship_select_delta += 0.025f;
            }
            if(quadUtil.mother_ship_count != 0) {
                menuPoligons.createPoligon(curentTexture, cam ,    -0.12f + ship_select_delta  ,  -0.125f        ,   0.008f , 0.008f,     player.ship_tex_index + 6  , 0);
                ship_select_delta += 0.025f;
            }

    }
    public  void createColonyMenu_1(Camera cam){
        menuPoligons.createPoligon(curentTexture, cam ,    0.13f ,  0.07f     ,   0.01f , 0.01f ,     71  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    0.13f ,  0.045f     ,   0.01f , 0.01f ,     76  , 0);
    }
    public void createTecMenu(Camera cam , Player player) {
    
        menuPoligons.createPoligon(curentTexture, cam ,    0 ,  0     ,   0.12f , 0.12f ,     91  , 0);
        if(!player.laserTec) {
            menuPoligons.createPoligon(curentTexture, cam ,   -0.1f ,  0.1f     ,   0.0175f , 0.0175f ,     99  , 0);
        } else {
            menuPoligons.createPoligon(curentTexture, cam ,   -0.1f ,  0.1f     ,   0.0175f , 0.0175f ,     89  , 0);
        }
        if(!player.torpedoTec) {
            menuPoligons.createPoligon(curentTexture, cam ,   -0.06f ,  0.1f     ,   0.0175f , 0.0175f ,     19  , 0);
        } else {
            menuPoligons.createPoligon(curentTexture, cam ,   -0.06f ,  0.1f     ,   0.0175f , 0.0175f ,     9  , 0);
        }    
        if(!player.shieldTec) {
            menuPoligons.createPoligon(curentTexture, cam ,   -0.02f ,  0.1f     ,   0.0175f , 0.0175f ,     39  , 0);
        } else {
            menuPoligons.createPoligon(curentTexture, cam ,   -0.02f ,  0.1f     ,   0.0175f , 0.0175f ,     29  , 0);
        }  
        if(!player.warpDriveTec) {
            menuPoligons.createPoligon(curentTexture, cam ,   0.02f ,  0.1f     ,   0.0175f , 0.0175f ,     59  , 0);
        } else {
            menuPoligons.createPoligon(curentTexture, cam ,   0.02f ,  0.1f     ,   0.0175f , 0.0175f ,     49  , 0);
        } 
        if(!player.InterceptorTec) {
            menuPoligons.createPoligon(curentTexture, cam ,   0.06f ,  0.1f     ,   0.0175f , 0.0175f ,     79  , 0);
        } else {
            menuPoligons.createPoligon(curentTexture, cam ,   0.06f ,  0.1f     ,   0.0175f , 0.0175f ,     69  , 0);
        }        
    }
    public  void createColonyMenu_2(int start , int end , Camera cam , Player player){
        menuPoligons.createPoligon(curentTexture, cam ,    0 ,  0     ,   0.12f , 0.12f ,     91  , 0);
        coloniDelta = 0;
        
        menuPoligons.createPoligon(curentTexture, cam ,   -0.08f ,  0.112f     ,   0.005f , 0.005f ,     60  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,   -0.05f ,  0.112f     ,   0.005f , 0.005f ,     61  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,   -0.02f ,  0.112f     ,   0.005f , 0.005f ,     64  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,   +0.01f ,  0.112f     ,   0.005f , 0.005f ,     65  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,   +0.04f ,  0.112f     ,   0.005f , 0.005f ,     66  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,   +0.07f ,  0.112f     ,   0.005f , 0.005f ,     67  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,   +0.1f  ,  0.112f     ,   0.005f , 0.005f ,     62  , 0);
        
        menuPoligons.createPoligon(curentTexture, cam ,   -0.01f ,  -0.112f     ,   0.005f , 0.004f ,     92  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,   +0.01f ,  -0.112f     ,   0.005f , 0.004f ,     92  , 180);
        
        if(player.watherPList.size() > end)
        {        
            for(int i=start ; i<end ; i++)
            {
                if(i < end){
                    menuPoligons.createPoligon(curentTexture, cam ,   -0.1f ,  0.1f - coloniDelta    ,   0.01f , 0.01f ,     player.watherPList.get(i).model.INDEX_T  , 0);
                    coloniDelta += 0.025f;
                }
            }
        } else {            
                    for(int i=start ; i<player.watherPList.size() ; i++)
                    {
                        if(i < end) {
                            menuPoligons.createPoligon(curentTexture, cam ,   -0.1f ,  0.1f - coloniDelta    ,   0.01f , 0.01f ,     player.watherPList.get(i).model.INDEX_T  , 0);
                            coloniDelta += 0.025f;
                        }
                    }        
               }
       
    }
    public void createCargoBayMenu(Camera cam , Planet planet)
    {
        menuPoligons.createPoligon(curentTexture, cam ,    0.0f    ,  -0.11f     ,   0.14f , 0.03f,     91  , 0);

        menuPoligons.createPoligon(curentTexture, cam ,    -0.015f    ,  -0.095f     ,   0.11f , 0.01225f,     91  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.015f    ,  -0.126f     ,   0.11f , 0.01225f,     91  , 0);

        menuPoligons.createPoligon(curentTexture, cam ,    -0.11f  ,  -0.095f        ,   0.01f , 0.01f,     planet.model.INDEX_T  , 0);

        menuPoligons.createPoligon(curentTexture, cam ,    -0.08f   ,  -0.095f        ,   0.005f , 0.005f,     60  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.05f   ,  -0.095f        ,   0.005f , 0.005f,     61  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.02f   ,  -0.095f        ,   0.005f , 0.005f,     64  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.01f   ,  -0.095f        ,   0.005f , 0.005f,     65  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.04f   ,  -0.095f        ,   0.005f , 0.005f,     63  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.07f   ,  -0.095f         ,   0.005f , 0.005f,     66  , 0);


        menuPoligons.createPoligon(curentTexture, cam ,    -0.08f   ,  -0.120f        ,   0.005f , 0.005f,     60  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.05f   ,  -0.120f        ,   0.005f , 0.005f,     61  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.02f   ,  -0.120f        ,   0.005f , 0.005f,     64  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.01f   ,  -0.120f        ,   0.005f , 0.005f,     65  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.04f   ,  -0.120f        ,   0.005f , 0.005f,     63  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.07f   ,  -0.120f         ,   0.005f , 0.005f,     66  , 0);



        menuPoligons.createPoligon(curentTexture, cam ,    -0.08f - 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     90  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.05f - 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     90  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.02f - 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     90  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.01f - 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     90  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.04f - 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     90  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.07f - 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     90  , 0);

        menuPoligons.createPoligon(curentTexture, cam ,    -0.08f + 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     87  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.05f + 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     87  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.02f + 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     87  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.01f + 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     87  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.04f + 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     87  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.07f + 0.005f   ,  -0.131f        ,   0.004f , 0.004f,     87  , 0);


        menuPoligons.createPoligon(curentTexture, cam ,    -0.11f  ,  -0.126f        ,   0.01f , 0.01f,     73 , 0);


        menuPoligons.createPoligon(curentTexture, cam ,    +0.127f  ,  -0.0880f        ,   0.008f , 0.006f,     91  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.127f  ,  -0.0880f        ,   0.007f , 0.005f,     92  , 0);
    }
    public void createShipYardMenu(Camera cam , Planet planet)
    {
       menuPoligons.createPoligon(curentTexture, cam ,    0.0f    ,  -0.11f     ,   0.14f , 0.03f,     91  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.11f  ,  -0.11f        ,   0.025f , 0.025f,     74  , 0);
       
       
       menuPoligons.createPoligon(curentTexture, cam ,    -0.07f  ,  -0.10f        ,   0.013f , 0.013f,     80  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.04f ,  -0.10f        ,   0.013f , 0.013f,     81  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.01f ,  -0.10f        ,   0.013f , 0.013f,     85  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.02f  ,  -0.10f        ,   0.013f , 0.013f,     82  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.05f ,  -0.10f        ,   0.013f , 0.013f,     83  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.08f  ,  -0.10f        ,   0.013f , 0.013f,     84  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.11f  ,  -0.10f        ,   0.013f , 0.013f,     86  , 0);

       menuPoligons.createPoligon(curentTexture, cam ,    -0.07f  ,  -0.1225f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.04f ,  -0.1225f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.01f ,  -0.1225f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.02f  ,  -0.1225f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.05f ,  -0.1225f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.08f  ,  -0.1225f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.11f  ,  -0.1225f        ,   0.005f , 0.005f,     90  , 0);
       
       menuPoligons.createPoligon(curentTexture, cam ,    +0.127f  ,  -0.0880f        ,   0.008f , 0.006f,     91  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.127f  ,  -0.0880f        ,   0.007f , 0.005f,     92  , 0);
    }
    public void createBuildingsMenu(Camera cam , Planet planet)
    {
       menuPoligons.createPoligon(curentTexture, cam ,    0.0f    ,  -0.11f     ,   0.14f , 0.03f,     91  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.11f  ,  -0.11f        ,   0.025f , 0.025f,     75  , 0);
       
       menuPoligons.createPoligon(curentTexture, cam ,    -0.07f  ,  -0.105f        ,   0.01f , 0.01f,     60  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.035f ,  -0.105f        ,   0.01f , 0.01f,     61  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.005f ,  -0.105f        ,   0.01f , 0.01f,     64  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.04f  ,  -0.105f        ,   0.01f , 0.01f,     65  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.075f ,  -0.105f        ,   0.01f , 0.01f,     63  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.11f  ,  -0.105f        ,   0.01f , 0.01f,     66  , 0);
       
       menuPoligons.createPoligon(curentTexture, cam ,    -0.07f  ,  -0.126f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.035f ,  -0.126f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.005f ,  -0.126f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.04f  ,  -0.126f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.075f ,  -0.126f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.11f  ,  -0.126f        ,   0.005f , 0.005f,     90  , 0);
       
       menuPoligons.createPoligon(curentTexture, cam ,    +0.127f  ,  -0.0880f        ,   0.008f , 0.006f,     91  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.127f  ,  -0.0880f        ,   0.007f , 0.005f,     92  , 0);
       
    }
    public void createShipUpgradesMenu(Camera cam , Planet planet)
    {
       menuPoligons.createPoligon(curentTexture, cam ,    0.0f    ,  -0.11f     ,   0.14f , 0.03f,     91  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.11f  ,  -0.11f        ,   0.025f , 0.025f,     72  , 0);
       
       menuPoligons.createPoligon(curentTexture, cam ,    -0.07f  ,  -0.105f        ,   0.01f , 0.01f,     93  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.035f ,  -0.105f        ,   0.01f , 0.01f,     94  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.005f ,  -0.105f        ,   0.01f , 0.01f,     95  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.04f  ,  -0.105f        ,   0.01f , 0.01f,     96  , 0);

       menuPoligons.createPoligon(curentTexture, cam ,    -0.07f  ,  -0.126f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.035f ,  -0.126f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.005f ,  -0.126f        ,   0.005f , 0.005f,     90  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.04f  ,  -0.126f        ,   0.005f , 0.005f,     90  , 0);
       
       menuPoligons.createPoligon(curentTexture, cam ,    +0.127f  ,  -0.0880f        ,   0.008f , 0.006f,     91  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.127f  ,  -0.0880f        ,   0.007f , 0.005f,     92  , 0);
       
    }    
    public void createSunSelectMenu(Camera cam , Sun sun)
    {
        planet_stacer = 0;
        
       curentTexture =  textureUtil.solarSystemTex_max;
       menuPoligons.createPoligon(curentTexture, cam ,    0.09f ,  -0.040f     ,   0.05f , 0.1f ,     91  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    0.07f ,   0.03f      ,   0.025f , 0.025f ,     sun.model.INDEX_T  , 0);
       
       float planetMenuSize; 
       
       for(int i=0 ; i<sun.listaPlanete.size() ; i++)
       {
         planetMenuSize =   sun.listaPlanete.get(i).model.scale.x;
           
         if(sun.starType.equalsIgnoreCase("whiteDwarf") || sun.starType.equalsIgnoreCase("redDwarf"))   {
            planetMenuSize = planetMenuSize * 2.5f;
         } else {
           planetMenuSize = planetMenuSize * 1.75f;
         }
           
         menuPoligons.createPoligon(curentTexture, cam ,  0.12f ,  0.04f - planet_stacer  ,   planetMenuSize , planetMenuSize ,     sun.listaPlanete.get(i).model.INDEX_T  , 0);
         planet_stacer += 0.03;
       }
       
    }
    public void createPlanetSelectMenu(Camera cam , Planet planet)
    {
       menuPoligons.createPoligon(curentTexture, cam ,    0.0f ,  -0.111f     ,   0.14f , 0.0325f,     91  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.115f ,  -0.107f        ,   0.019f , 0.019f,     planet.model.INDEX_T  , 0);
       
       menuPoligons.createPoligon(curentTexture, cam ,    -0.07f   ,  -0.10f        ,   0.0125f , 0.0125f,     75  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.02f   ,  -0.10f       ,   0.0125f , 0.0125f,     72  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.0301f ,  -0.10f       ,   0.0125f , 0.0125f,     74  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.0801f ,  -0.10f       ,   0.0125f , 0.0125f,     73  , 0);

       menuPoligons.createPoligon(curentTexture, cam ,    -0.0925f   ,  -0.122f        ,   0.005f , 0.005f,     60  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.0525f   ,  -0.122f        ,   0.005f , 0.005f,     61  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    -0.0125f   ,  -0.122f        ,   0.005f , 0.005f,     64  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.0325f   ,  -0.122f        ,   0.005f , 0.005f,     65  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.11f     ,  -0.122f        ,   0.005f , 0.005f,     63  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.0725f   ,  -0.122f          ,   0.005f , 0.005f,     66  , 0);

        menuPoligons.createPoligon(curentTexture, cam ,    -0.0925f   ,  -0.13f        ,   0.003f , 0.003f,     70  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.0525f   ,  -0.13f        ,   0.003f , 0.003f,     70  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.0125f   ,  -0.13f        ,   0.003f , 0.003f,     70  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.0325f   ,  -0.13f        ,   0.003f , 0.003f,     70  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.0725f   ,  -0.13f        ,   0.003f , 0.003f,     70  , 0);


        menuPoligons.createPoligon(curentTexture, cam ,    -0.0925f   ,  -0.138f        ,   0.0025f , 0.0025f,     90  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.0525f   ,  -0.138f        ,   0.0025f , 0.0025f,     90  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.0125f   ,  -0.138f        ,   0.0025f , 0.0025f,     90  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.0325f   ,  -0.138f        ,   0.0025f , 0.0025f,     90  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.0725f   ,  -0.138f        ,   0.0025f , 0.0025f,     90  , 0);

        menuPoligons.createPoligon(curentTexture, cam ,    -0.085f   ,  -0.138f        ,   0.0025f , 0.0025f,     87  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.045f   ,  -0.138f        ,   0.0025f , 0.0025f,     87  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    -0.005f   ,  -0.138f        ,   0.0025f , 0.0025f,     87  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.04f   ,  -0.138f        ,   0.0025f , 0.0025f,     87  , 0);
        menuPoligons.createPoligon(curentTexture, cam ,    +0.08f   ,  -0.138f        ,   0.0025f , 0.0025f,     87  , 0);


       menuPoligons.createPoligon(curentTexture, cam ,    +0.11f   ,  -0.095f          ,   0.005f , 0.005f,     62  , 0);
       menuPoligons.createPoligon(curentTexture, cam ,    +0.11f   ,  -0.109f          ,   0.004f , 0.005f,     70  , 0);
    }    
}
