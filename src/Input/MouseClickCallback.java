
package Input;

import Camera.Camera;
import Display.SetupDisplay_LWJGL_3;
import IBO.IBO;
import Maths.Maths;
import Menu.MenuData;
import Planet.Planet;
import Player.Player;
import QuadTree.QT;
import Render.Renderer;
import Sun.Sun;
import TextUtil.TextData;
import Texture.TextureUtil;
import Util.MouseMenuFunctionImplementation;
import Util.MousePicker;
import java.util.ArrayList;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_2;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import spacecomander.Quad;
import spacecomander.QuadUtil;
import spacecomander.SpaceComander;


public class MouseClickCallback extends GLFWMouseButtonCallback{



   private MousePicker pick ;
   private MouseMenuFunctionImplementation   serch;
   private StringBuilder stringB = new StringBuilder();
    
    private float x_drag_start = 0;
    private float y_drag_start = 0;
    private float x_drag_end = 0;
    private float y_drag_end = 0;

    
    private Vector3f curentRay , menuSelectRay ;
    
    private SetupDisplay_LWJGL_3   display;
    private Camera camera;
    private Renderer   renderer;
    private Maths maths;
    private QuadUtil quadUtil;
    private ArrayList<Player>   playerList;
    private MenuData menuData;
    private ArrayList<Sun>   sunList;
    private ArrayList<TextData>  textToRender;
    private IBO      ibo;
    private TextureUtil textureUtil;
      
    public  Sun sun;
    public  Planet planet;
    
    private int start_coloni_index = 0;
    private int end_coloni_index = 9;
    private float coloni_delta = 0;
    private int page = 0;
    
    private TextData  textData;
    public  float ship_select_delta;

    int race_index;

    public boolean enabledragSelection = true;


    ArrayList<Quad>      shipSelectionRing;
   QT qt;
    
    public MouseClickCallback(SetupDisplay_LWJGL_3  display , Camera camera, Renderer renderer , Maths maths , QuadUtil quadUtil , ArrayList<Player>  playerList , MenuData menuData , ArrayList<Sun>  sunList,ArrayList<TextData>  textToRender,  IBO ibo,TextureUtil textureUtil ,int race_index  , ArrayList<Quad>      shipSelectionRing , QT qt){
        this.display = display;
        this.camera = camera;
        this.renderer = renderer;
        this.maths = maths;
        this.quadUtil = quadUtil;
        this.playerList = playerList;
        this.menuData = menuData;
        this.sunList = sunList;
        this.ibo = ibo;
        this.textureUtil = textureUtil;
        this.textToRender = textToRender;
        this.race_index = race_index;
        this.shipSelectionRing = shipSelectionRing;

    pick  = new MousePicker(display , camera , renderer.getProjectionMatrix() , maths);
    serch = new MouseMenuFunctionImplementation(menuData);
    
    this.qt = qt;
    }
    
    @Override
    public void invoke(long window, int button, int action, int mods) {
       if(button == GLFW_MOUSE_BUTTON_1)
       {
           if(action == GLFW_PRESS) {

 
               curentRay = pick.update(false , 0 , 0);
               menuSelectRay = pick.getMCurentRay();

               x_drag_start = curentRay.x;
               y_drag_start = curentRay.y;
               x_drag_end = curentRay.x;
               y_drag_end = curentRay.y;
               
                  SpaceComander.selectQuadStatus = true;
                  SpaceComander.selectQuadX = curentRay.x;
                  SpaceComander.selectQuadY = curentRay.y;
               
               
               //SpaceComander.rangeX =curentRay.x; 
               //SpaceComander.rangeY =curentRay.y; 
               qt.range.w = 0.1f;
               qt.getRangePoints(curentRay.x, curentRay.y , 0);
               if(qt.arrayFound.size() != 0) {

                  float dist = (float) Math.sqrt(Math.pow(menuSelectRay.x - qt.arrayFound.get(0).curentPosition.x , 2)  +  Math.pow(menuSelectRay.y - qt.arrayFound.get(0).curentPosition.y , 2));
                 // System.out.println(qt.arrayDataPoint.size()+"                 "+"sizeee ="+qt.arrayFound.size()+"       w = "+qt.range.w+"       "+dist+"        ");
               } else {
                 // System.out.println(qt.arrayFound.size());
               }
               
               
               
               //__PLANET_MENU____________________________________________________________________//
               if (spacecomander.SpaceComander.planet_select_menu) {
                   int index = serch.selectplanetMenuOption(menuSelectRay.x, menuSelectRay.y);
                   if (index == 2) {
                       createBuildingsMenu("");
                   }
                   if (index == 3) {
                       createShipUpgradesMenu("");
                   }
                   if (index == 4) {
                       createShipYardMenu("");
                   }
                   if (index == 5) {
                       createCargoBayMenu(planet, "Message....");
                   }
                   if (index == 17) {
                       if (planet.resources.workersAvaible > 0) {
                           planet.resources.energyWorker++;
                           planet.resources.workersAvaible--;
                           generatePlanetMenu(planet);
                       }
                   }
                   if (index == 22) {
                       if (planet.resources.energyWorker > 0) {
                           planet.resources.energyWorker--;
                           planet.resources.workersAvaible++;
                           generatePlanetMenu(planet);
                       }
                   }
                   if (index == 18) {
                       if (planet.resources.workersAvaible > 0) {
                           planet.resources.metalOreWorker++;
                           planet.resources.workersAvaible--;
                           generatePlanetMenu(planet);
                       }
                   }
                   if (index == 23) {
                       if (planet.resources.metalOreWorker > 0) {
                           planet.resources.metalOreWorker--;
                           planet.resources.workersAvaible++;
                           generatePlanetMenu(planet);
                       }
                   }
                   if (index == 19) {
                       if (planet.resources.workersAvaible > 0) {
                           planet.resources.mineralsWorker++;
                           planet.resources.workersAvaible--;
                           generatePlanetMenu(planet);
                       }
                   }
                   if (index == 24) {
                       if (planet.resources.mineralsWorker > 0) {
                           planet.resources.mineralsWorker--;
                           planet.resources.workersAvaible++;
                           generatePlanetMenu(planet);
                       }
                   }
                   if (index == 20) {
                       if (planet.resources.workersAvaible > 0) {
                           planet.resources.carbonWorker++;
                           planet.resources.workersAvaible--;
                           generatePlanetMenu(planet);
                       }
                   }
                   if (index == 25) {
                       if (planet.resources.carbonWorker > 0) {
                           planet.resources.carbonWorker--;
                           planet.resources.workersAvaible++;
                           generatePlanetMenu(planet);
                       }
                   }
                   if (index == 21) {
                       if (planet.resources.workersAvaible > 0) {
                           planet.resources.gasWorker++;
                           planet.resources.workersAvaible--;
                           generatePlanetMenu(planet);
                       }
                   }
                   if (index == 26) {
                       if (planet.resources.gasWorker > 0) {
                           planet.resources.gasWorker--;
                           planet.resources.workersAvaible++;
                           generatePlanetMenu(planet);
                       }
                   }
               }
               //____________________________________________________________________________________//
               if (SpaceComander.ship_select_menu) {
                   int index = serch.selectedShipOption(menuSelectRay.x, menuSelectRay.y);

                   if (index == -1)
                   {
                       SpaceComander.ship_select_menu = false;
                       resetBooleanVar();

                       textToRender.clear();
                       menuData.menuPoliList.clear();

                       enableColoniMenu_1();
                       enabledragSelection  = true;
                   }
                   ;
                   if (index == 0) {
                       shipSelectionRing.clear();
                       for (Quad ship : playerList.get(race_index).shipList) {
                           if (!ship.isCargoShip) {
                               ship.isSlected = false;
                           } else {
                               if(ship.isSlected) {
                                   quadUtil.quadManagement.addShipSelectRing(ship);
                               }
                           }
                       }
                       //quadUtil.cargo_ship_count = 0;
                       quadUtil.coloni_ship_count = 0;
                       quadUtil.explorer_ship_count = 0;
                       quadUtil.fregate_ship_count =0;
                       quadUtil.destroier_ship_count = 0;
                       quadUtil.carrier_ship_count =0;
                       quadUtil.interceptor_ship_count  =0;
                       quadUtil.mother_ship_count = 0;

                       textToRender.clear();
                       menuData.menuPoliList.clear();

                       menuData.createShipSelectMenu(camera, playerList.get(race_index));
                       shipSelectCountPrint();
                   }
                   if (index == 1) {
                       shipSelectionRing.clear();
                       for (Quad ship : playerList.get(race_index).shipList) {
                           if (!ship.isColoniShip) {
                               ship.isSlected = false;
                           } else {
                               if(ship.isSlected) {
                                   quadUtil.quadManagement.addShipSelectRing(ship);
                               }
                           }
                       }
                       quadUtil.cargo_ship_count = 0;
                       //quadUtil.coloni_ship_count = 0;
                       quadUtil.explorer_ship_count = 0;
                       quadUtil.fregate_ship_count =0;
                       quadUtil.destroier_ship_count = 0;
                       quadUtil.carrier_ship_count =0;
                       quadUtil.interceptor_ship_count  =0;
                       quadUtil.mother_ship_count = 0;

                       textToRender.clear();
                       menuData.menuPoliList.clear();

                       menuData.createShipSelectMenu(camera, playerList.get(race_index));
                       shipSelectCountPrint();
                   }
                   if (index == 2) {
                       shipSelectionRing.clear();
                       for (Quad ship : playerList.get(race_index).shipList) {
                           if (!ship.isInterceptor) {
                               ship.isSlected = false;
                           } else {
                               if(ship.isSlected) {
                                   quadUtil.quadManagement.addShipSelectRing(ship);
                               }
                           }
                       }
                       quadUtil.cargo_ship_count = 0;
                       quadUtil.coloni_ship_count = 0;
                       quadUtil.explorer_ship_count = 0;
                       quadUtil.fregate_ship_count =0;
                       quadUtil.destroier_ship_count = 0;
                       quadUtil.carrier_ship_count =0;
                       //quadUtil.interceptor_ship_count  =0;
                       quadUtil.mother_ship_count = 0;

                       textToRender.clear();
                       menuData.menuPoliList.clear();

                       menuData.createShipSelectMenu(camera, playerList.get(race_index));
                       shipSelectCountPrint();
                   }
                   if (index == 3) {
                       shipSelectionRing.clear();
                       for (Quad ship : playerList.get(race_index).shipList) {
                           if (!ship.isExploreShip) {
                               ship.isSlected = false;
                           } else {
                               if(ship.isSlected) {
                                   quadUtil.quadManagement.addShipSelectRing(ship);
                               }
                           }
                       }
                       quadUtil.cargo_ship_count = 0;
                       quadUtil.coloni_ship_count = 0;
                       //quadUtil.explorer_ship_count = 0;
                       quadUtil.fregate_ship_count =0;
                       quadUtil.destroier_ship_count = 0;
                       quadUtil.carrier_ship_count =0;
                       quadUtil.interceptor_ship_count  =0;
                       quadUtil.mother_ship_count = 0;
                       quadUtil.selected_ship_count = quadUtil.explorer_ship_count;

                       textToRender.clear();
                       menuData.menuPoliList.clear();

                       menuData.createShipSelectMenu(camera, playerList.get(race_index));
                       shipSelectCountPrint();
                   }
                   if (index == 4) {
                       shipSelectionRing.clear();
                       for (Quad ship : playerList.get(race_index).shipList) {
                           if (!ship.isDestroierShip) {
                               ship.isSlected = false;
                           } else {
                               if(ship.isSlected) {
                                   quadUtil.quadManagement.addShipSelectRing(ship);
                               }
                           }
                       }
                       quadUtil.cargo_ship_count = 0;
                       quadUtil.coloni_ship_count = 0;
                       quadUtil.explorer_ship_count = 0;
                       quadUtil.fregate_ship_count =0;
                       //quadUtil.destroier_ship_count = 0;
                       quadUtil.carrier_ship_count =0;
                       quadUtil.interceptor_ship_count  =0;
                       quadUtil.mother_ship_count = 0;
                       quadUtil.selected_ship_count = quadUtil.destroier_ship_count;

                       textToRender.clear();
                       menuData.menuPoliList.clear();

                       menuData.createShipSelectMenu(camera, playerList.get(race_index));
                       shipSelectCountPrint();
                   }
                   if (index == 5) {
                       shipSelectionRing.clear();
                       for (Quad ship : playerList.get(race_index).shipList) {
                           if (!ship.isCarrierShip) {
                               ship.isSlected = false;
                           } else {
                               if(ship.isSlected) {
                                   quadUtil.quadManagement.addShipSelectRing(ship);
                               }
                           }
                       }
                       quadUtil.cargo_ship_count = 0;
                       quadUtil.coloni_ship_count = 0;
                       quadUtil.explorer_ship_count = 0;
                      quadUtil.fregate_ship_count =0;
                       quadUtil.destroier_ship_count = 0;
                       //quadUtil.carrier_ship_count =0;
                       quadUtil.interceptor_ship_count  =0;
                       quadUtil.mother_ship_count = 0;
                       quadUtil.selected_ship_count = quadUtil.carrier_ship_count;

                       textToRender.clear();
                       menuData.menuPoliList.clear();

                       menuData.createShipSelectMenu(camera, playerList.get(race_index));
                       shipSelectCountPrint();
                   }
                   if (index == 6) {
                       shipSelectionRing.clear();
                       for (Quad ship : playerList.get(race_index).shipList) {
                           if (!ship.isMotherShip) {
                               ship.isSlected = false;
                           } else {
                               if(ship.isSlected) {
                                   quadUtil.quadManagement.addShipSelectRing(ship);
                               }
                           }
                       }
                       quadUtil.cargo_ship_count = 0;
                       quadUtil.coloni_ship_count = 0;
                       quadUtil.explorer_ship_count = 0;
                       quadUtil.fregate_ship_count =0;
                       quadUtil.destroier_ship_count = 0;
                       quadUtil.carrier_ship_count =0;
                       quadUtil.interceptor_ship_count  =0;
                       //quadUtil.mother_ship_count = 0;
                       quadUtil.selected_ship_count = quadUtil.mother_ship_count;

                       textToRender.clear();
                       menuData.menuPoliList.clear();

                       menuData.createShipSelectMenu(camera, playerList.get(race_index));
                       shipSelectCountPrint();
                   }
                   if (index == 7) {
                       shipSelectionRing.clear();
                       for (Quad ship : playerList.get(race_index).shipList) {
                           if (!ship.isFreagateShip) {
                               ship.isSlected = false;
                           } else {
                               if(ship.isSlected) {
                                   quadUtil.quadManagement.addShipSelectRing(ship);
                               }
                           }
                       }
                       quadUtil.cargo_ship_count = 0;
                       quadUtil.coloni_ship_count = 0;
                       quadUtil.explorer_ship_count = 0;
                       //quadUtil.fregate_ship_count =0;
                       quadUtil.destroier_ship_count = 0;
                       quadUtil.carrier_ship_count =0;
                       quadUtil.interceptor_ship_count  =0;
                       quadUtil.mother_ship_count = 0;
                       quadUtil.selected_ship_count = quadUtil.fregate_ship_count;

                       textToRender.clear();
                       menuData.menuPoliList.clear();

                       menuData.createShipSelectMenu(camera, playerList.get(race_index));
                       shipSelectCountPrint();
                   }
                   if (index == 8) {
                       shipSelectionRing.clear();
                       for (Quad ship : playerList.get(race_index).shipList) {
                           if (!ship.isFreagateShip) {
                               ship.isSlected = false;
                           } else {
                               quadUtil.quadManagement.addShipSelectRing(ship);
                           }
                       }
                       quadUtil.cargo_ship_count = 0;
                       quadUtil.coloni_ship_count = 0;
                       quadUtil.explorer_ship_count = 0;
                       //quadUtil.fregate_ship_count =0;
                       quadUtil.destroier_ship_count = 0;
                       quadUtil.carrier_ship_count =0;
                       quadUtil.interceptor_ship_count  =0;
                       quadUtil.mother_ship_count = 0;
                       quadUtil.selected_ship_count = quadUtil.fregate_ship_count;

                       textToRender.clear();
                       menuData.menuPoliList.clear();

                       menuData.createShipSelectMenu(camera, playerList.get(race_index));
                       shipSelectCountPrint();
                   }


                }
               if(!SpaceComander.ship_select_menu) {
                   quadUtil.checkShipSelection(playerList, curentRay.x, curentRay.y);
                   if(quadUtil.selected_ship_count != 0)
                   {
                       textToRender.clear();
                       menuData.menuPoliList.clear();
                       resetBooleanVar();

                       menuData.createShipSelectMenu(camera, playerList.get(race_index));
                       shipSelectCountPrint();
                       SpaceComander.ship_select_menu = true;
                       SpaceComander.shipSelectMenuText = true;
                   }
               }
               //___BUILDING_________________________________________________________________________//
               if(spacecomander.SpaceComander.buildings_menu)
               {

                   int index = serch.buildingMenuOption(menuSelectRay.x, menuSelectRay.y);
                   if(index == 15){
                       generatePlanetMenu(planet);
                   }
                   if(index == 8){
                       if(upgraderMiningF(planet)) {
                           planet.resources.energyLevel++;
                           createBuildingsMenu("Message: done.....");
                           incrementUpgratesCost(planet,0);
                       } else {
                           stringB.setLength(0);
                           stringB.append("Message: need more resources....."+planet.resources.energyUpgradeCost+" "+planet.resources.metalOreUpgradeCost+" "+planet.resources.mineralsUpgradeCost+" "+planet.resources.carbonUpgradeCost+" "+planet.resources.creditUpgradeCost+" "+planet.resources.gasUpgradeCost);
                           createBuildingsMenu(stringB.toString());
                       }
                   }
                   if(index == 9) {
                       if(upgraderMiningF(planet)) {
                           planet.resources.metalOreLevel++;
                           createBuildingsMenu("Message: done.....");
                           incrementUpgratesCost(planet,1);
                       } else {
                           stringB.setLength(0);
                           stringB.append("Message: need more resources....."+planet.resources.energyUpgradeCost+" "+planet.resources.metalOreUpgradeCost+" "+planet.resources.mineralsUpgradeCost+" "+planet.resources.carbonUpgradeCost+" "+planet.resources.creditUpgradeCost+" "+planet.resources.gasUpgradeCost);
                           createBuildingsMenu(stringB.toString());
                       }
                   }
                   if(index == 10){
                       if(upgraderMiningF(planet)) {
                           planet.resources.mineralsLevel++;
                           createBuildingsMenu("Message: done.....");
                           incrementUpgratesCost(planet,2);
                       } else {
                           stringB.setLength(0);
                           stringB.append("Message: need more resources....."+planet.resources.energyUpgradeCost+" "+planet.resources.metalOreUpgradeCost+" "+planet.resources.mineralsUpgradeCost+" "+planet.resources.carbonUpgradeCost+" "+planet.resources.creditUpgradeCost+" "+planet.resources.gasUpgradeCost);
                           createBuildingsMenu(stringB.toString());
                       }
                   }
                   if(index == 11){
                       if(upgraderMiningF(planet)) {
                           planet.resources.carbonLevel++;
                           createBuildingsMenu("Message: done.....");
                           incrementUpgratesCost(planet,3);
                       } else {
                           stringB.setLength(0);
                           stringB.append("Message: need more resources....."+planet.resources.energyUpgradeCost+" "+planet.resources.metalOreUpgradeCost+" "+planet.resources.mineralsUpgradeCost+" "+planet.resources.carbonUpgradeCost+" "+planet.resources.creditUpgradeCost+" "+planet.resources.gasUpgradeCost);
                           createBuildingsMenu(stringB.toString());
                       }
                   }
                   if(index == 12){
                       if(upgraderMiningF(planet)) {
                           planet.resources.creditLevel++;
                           createBuildingsMenu("Message: done.....");
                           incrementUpgratesCost(planet,4);
                       } else {
                           stringB.setLength(0);
                           stringB.append("Message: need more resources....."+planet.resources.energyUpgradeCost+" "+planet.resources.metalOreUpgradeCost+" "+planet.resources.mineralsUpgradeCost+" "+planet.resources.carbonUpgradeCost+" "+planet.resources.creditUpgradeCost+" "+planet.resources.gasUpgradeCost);
                           createBuildingsMenu(stringB.toString());
                       }
                   }
                   if(index == 13){
                       if(upgraderMiningF(planet)) {
                           planet.resources.gasLevel++;
                           createBuildingsMenu("Message: done.....");
                           incrementUpgratesCost(planet,5);
                       } else {
                           stringB.setLength(0);
                           stringB.append("Message: need more resources....."+planet.resources.energyUpgradeCost+" "+planet.resources.metalOreUpgradeCost+" "+planet.resources.mineralsUpgradeCost+" "+planet.resources.carbonUpgradeCost+" "+planet.resources.creditUpgradeCost+" "+planet.resources.gasUpgradeCost);
                           createBuildingsMenu(stringB.toString());
                       }
                   }
               }

               //____________________________________________________________________________________//

               //__CARGO_BAY_________________________________________________________________________//
               if(SpaceComander.cargoBay_Menu)
               {
                   int index = serch.shipYardMenuOption(menuSelectRay.x, menuSelectRay.y);

                   if(index == 30){
                       generatePlanetMenu(planet);
                   }
                   if(index == 16){
                       if(planet.resources.energyMined >= planet.resources.stockTransferRate)
                       {
                           planet.resources.cargoDoc_energy_Stock += planet.resources.stockTransferRate;
                           planet.resources.energyMined -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       } else {
                           createCargoBayMenu(planet, "Message....  Need more resources");
                       }
                   }
                   if(index == 22){
                       if(planet.resources.cargoDoc_energy_Stock >= planet.resources.stockTransferRate)
                       {
                           planet.resources.energyMined += planet.resources.stockTransferRate;
                           planet.resources.cargoDoc_energy_Stock -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       } else {
                           createCargoBayMenu(planet, "Message....  Need more resources");
                       }
                   }




                   if(index == 17){
                       if(planet.resources.metalOreMined >= planet.resources.stockTransferRate)
                       {
                           planet.resources.cargoDoc_metalOre_Stock += planet.resources.stockTransferRate;
                           planet.resources.metalOreMined -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       } else {
                           createCargoBayMenu(planet, "Message....  Need more resources");
                       }
                   }
                   if(index == 23){
                       if(planet.resources.cargoDoc_metalOre_Stock >= planet.resources.stockTransferRate)
                       {
                           planet.resources.metalOreMined += planet.resources.stockTransferRate;
                           planet.resources.cargoDoc_metalOre_Stock -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       } else {
                           createCargoBayMenu(planet, "Message....  Need more resources");
                       }
                   }



                   if(index == 18){
                       if(planet.resources.mineralsMined >= planet.resources.stockTransferRate)
                       {
                           planet.resources.cargoDoc_mineral_Stock += planet.resources.stockTransferRate;
                           planet.resources.mineralsMined -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       } else {
                           createCargoBayMenu(planet, "Message....  Need more resources");
                       }
                   }
                   if(index == 24){
                       if(planet.resources.cargoDoc_mineral_Stock >= planet.resources.stockTransferRate) {
                           planet.resources.mineralsMined += planet.resources.stockTransferRate;
                           planet.resources.cargoDoc_mineral_Stock -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       } else {
                           createCargoBayMenu(planet, "Message....  Need more resources");
                       }
                   }
                   if(index == 19){
                       if(planet.resources.carbonMined >= planet.resources.stockTransferRate)
                       {
                           planet.resources.cargoDoc_carbon_Stock += planet.resources.stockTransferRate;
                           planet.resources.carbonMined -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       } else {
                           createCargoBayMenu(planet, "Message....  Need more resources");
                       }
                   }
                   if(index == 25){
                       if(planet.resources.cargoDoc_carbon_Stock >= planet.resources.stockTransferRate) {
                           planet.resources.carbonMined += planet.resources.stockTransferRate;
                           planet.resources.cargoDoc_carbon_Stock -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       } else {
                           createCargoBayMenu(planet, "Message....  Need more resources");
                       }
                   }
                   if(index == 20){
                       if(planet.resources.creditMined >= planet.resources.stockTransferRate) {
                           planet.resources.cargoDoc_credit_Stock += planet.resources.stockTransferRate;
                           planet.resources.creditMined -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       } else {
                           createCargoBayMenu(planet, "Message....  Need more resources");
                       }
                   }
                   if(index == 26){
                       if(planet.resources.cargoDoc_credit_Stock >= planet.resources.stockTransferRate)
                       {
                           planet.resources.creditMined += planet.resources.stockTransferRate;
                           planet.resources.cargoDoc_credit_Stock -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       } else {
                           createCargoBayMenu(planet, "Message....  Need more resources");
                       }
                   }
                   if(index == 21){
                       if(planet.resources.gasMined >= planet.resources.stockTransferRate)
                       {
                           planet.resources.cargoDoc_gas_Stock += planet.resources.stockTransferRate;
                           planet.resources.gasMined -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       } else {
                           createCargoBayMenu(planet, "Message....  Need more resources");
                       }
                   }
                   if(index == 27){
                       if(planet.resources.cargoDoc_gas_Stock >= planet.resources.stockTransferRate)
                       {
                           planet.resources.gasMined += planet.resources.stockTransferRate;
                           planet.resources.cargoDoc_gas_Stock -= planet.resources.stockTransferRate;
                           createCargoBayMenu(planet, "Message....");
                       }
                   }
               }

               //__SHIP_YARD_________________________________________________________________________//
               if(spacecomander.SpaceComander.shipYard_menu)
               {
                   int index = serch.shipYardMenuOption(menuSelectRay.x, menuSelectRay.y);

                   if(index == 17){
                       generatePlanetMenu(planet);
                   }
                   if(index == 9){
                       if(planet.resources.cargoDoc_energy_Stock >0 || planet.resources.cargoDoc_metalOre_Stock >0 || planet.resources.cargoDoc_mineral_Stock >0 || planet.resources.cargoDoc_carbon_Stock >0 || planet.resources.cargoDoc_credit_Stock >0 || planet.resources.cargoDoc_gas_Stock >0) {
                          Quad  ship =  quadUtil.quadManagement.addCargoShip(planet, playerList.get(race_index), planet.position.x + 0.1f / quadUtil.quadManagement.scaleQuad, planet.position.y + 0.1f / quadUtil.quadManagement.scaleQuad);
                                ship.autoCargoShip = false;
                                ship.energyCargo   = planet.resources.cargoDoc_energy_Stock;
                                ship.metalOreCargo = planet.resources.cargoDoc_metalOre_Stock;
                                ship.mineralCargo  = planet.resources.cargoDoc_mineral_Stock;
                                ship.carbonCargo   = planet.resources.cargoDoc_carbon_Stock;
                                ship.creditCargo   = planet.resources.cargoDoc_credit_Stock;
                                ship.gasCargo      = planet.resources.cargoDoc_gas_Stock;

                           planet.resources.cargoDoc_energy_Stock = 0;
                           planet.resources.cargoDoc_metalOre_Stock = 0;
                           planet.resources.cargoDoc_mineral_Stock = 0;
                           planet.resources.cargoDoc_carbon_Stock = 0;
                           planet.resources.cargoDoc_credit_Stock = 0;
                           planet.resources.cargoDoc_gas_Stock = 0;

                       } else {
                           createShipYardMenu("message: cargo bay is empty.....");
                       }
                   }
                   if(index == 10){
                       if(checkShipProductionResources(0 , planet))
                       {
                           if(playerList.get(0).coloniShip == null)
                           {
                               quadUtil.quadManagement.addColonizationShip(planet, playerList.get(race_index), planet.position.x+0.04f/quadUtil.quadManagement.scaleQuad, planet.position.y+0.04f/quadUtil.quadManagement.scaleQuad);
                               createShipYardMenu("message: done...");
                           } else {
                               createShipYardMenu("message: colony ships count exceeded.....");
                           }
                       } else {
                           createShipYardMenu("message: need more resources...");
                       }
                   }
                   if(index == 11){

                           if(planet.shipCount < planet.planetMaxShipCount) {
                               if(checkShipProductionResources(1 , planet))
                               {
                                   quadUtil.quadManagement.addExploreShip(planet, playerList.get(race_index), planet.position.x + 0.04f / quadUtil.quadManagement.scaleQuad, planet.position.y + 0.04f / quadUtil.quadManagement.scaleQuad);
                                   createShipYardMenu("message: done...");
                               } else {
                                   createShipYardMenu("message: need more resources...");
                               }
                           } else {
                               createShipYardMenu("message: maxim planet ship count... "+String.valueOf(planet.shipCount)+" "+String.valueOf(planet.planetMaxShipCount));
                           }

                   }
                   if(index == 12){

                           if(planet.shipCount < planet.planetMaxShipCount) {
                               if(checkShipProductionResources(2 , planet)) {
                                   quadUtil.quadManagement.addFreagateShip(planet, playerList.get(race_index), planet.position.x + 0.04f / quadUtil.quadManagement.scaleQuad, planet.position.y + 0.04f / quadUtil.quadManagement.scaleQuad);
                                   createShipYardMenu("message: done...");
                               } else {
                                   createShipYardMenu("message: need more resources...");
                               }
                           } else {
                               createShipYardMenu("message: maxim planet ship count...");
                           }

                   }
                   if(index == 13){

                           if(planet.shipCount < planet.planetMaxShipCount) {
                               if(checkShipProductionResources(2 , planet)) {
                                   quadUtil.quadManagement.addDestroierShip(planet, playerList.get(race_index), planet.position.x + 0.04f / quadUtil.quadManagement.scaleQuad, planet.position.y + 0.04f / quadUtil.quadManagement.scaleQuad);
                                   createShipYardMenu("message: done...");
                               } else {
                                   createShipYardMenu("message: need more resources...");
                               }
                           } else {
                               createShipYardMenu("message: maxim planet ship count...");
                           }

                   }
                   if(index == 14){

                           if(planet.shipCount < planet.planetMaxShipCount) {
                               if(checkShipProductionResources(2 , planet)) {
                                   quadUtil.quadManagement.addCarrierShip(planet, playerList.get(race_index), planet.position.x + 0.04f / quadUtil.quadManagement.scaleQuad, planet.position.y + 0.04f / quadUtil.quadManagement.scaleQuad);
                                   createShipYardMenu("message: done...");
                               } else {
                                   createShipYardMenu("message: need more resources...");
                               }
                           } else {
                               createShipYardMenu("message: maxim planet ship count...");
                           }

                   }
                   if(index == 15){

                           if(planet.shipCount < planet.planetMaxShipCount) {
                               if(checkShipProductionResources(2 , planet)) {
                                   quadUtil.quadManagement.addMotherShip(planet, playerList.get(race_index), planet.position.x + 0.04f / quadUtil.quadManagement.scaleQuad, planet.position.y + 0.04f / quadUtil.quadManagement.scaleQuad);
                                   createShipYardMenu("message: done...");
                               } else {
                                   createShipYardMenu("message: need more resources...");
                               }
                           } else {
                               createShipYardMenu("message: maxim planet ship count...");
                           }

                   }
               }

               //____________________________________________________________________________________//

               //__SHIP_UPGRADE_MENU_________________________________________________________________//
               if(spacecomander.SpaceComander.upgrades_menu)
               {

                   int index = serch.shipYardMenuOption(menuSelectRay.x, menuSelectRay.y);
                   if(index == 11){
                       generatePlanetMenu(planet);
                   }
                   if(index == 6) {
                       if (planet.resources.engineUpgradeLevel < 3) {
                           if (planet.resources.energyMined >= (1000 * planet.resources.engineUpgradeCost) && planet.resources.metalOreMined >= (1000 * planet.resources.engineUpgradeCost) && planet.resources.mineralsMined >= (1000 * planet.resources.engineUpgradeCost) && planet.resources.carbonMined >= (1000 * planet.resources.engineUpgradeCost) && planet.resources.creditMined >= (1000 * planet.resources.engineUpgradeCost) && planet.resources.gasMined >= (1000 * planet.resources.engineUpgradeCost)) {
                               planet.resources.engineUpgradeLevel++;
                               planet.resources.energyMined   -= (1000 * planet.resources.engineUpgradeCost);
                               planet.resources.metalOreMined -= (1000 * planet.resources.engineUpgradeCost);
                               planet.resources.mineralsMined -= (1000 * planet.resources.engineUpgradeCost);
                               planet.resources.carbonMined   -= (1000 * planet.resources.engineUpgradeCost);
                               planet.resources.creditMined   -= (1000 * planet.resources.engineUpgradeCost);
                               planet.resources.gasMined      -= (1000 * planet.resources.engineUpgradeCost);
                               planet.resources.engineUpgradeCost ++;
                               createShipUpgradesMenu("Done...");
                           } else {
                               createShipUpgradesMenu("Need more resources....");
                           }
                       } else {
                           createShipUpgradesMenu("message: Engine  upgrade is at maximum....");
                       }
                   }
                   if(index == 7 ) {
                       if(planet.resources.gunsUpgradeLevel < 3) {
                           if (planet.resources.energyMined >= (1000 * planet.resources.gunsUpgradeCost) && planet.resources.metalOreMined >= (1000 * planet.resources.gunsUpgradeCost) && planet.resources.mineralsMined >= (1000 * planet.resources.gunsUpgradeCost) && planet.resources.carbonMined >= (1000 * planet.resources.gunsUpgradeCost) && planet.resources.creditMined >= (1000 * planet.resources.gunsUpgradeCost) && planet.resources.gasMined >= (1000 * planet.resources.gunsUpgradeCost)) {
                               planet.resources.gunsUpgradeLevel++;
                               planet.resources.energyMined   -= (1000 * planet.resources.gunsUpgradeCost);
                               planet.resources.metalOreMined -= (1000 * planet.resources.gunsUpgradeCost);
                               planet.resources.mineralsMined -= (1000 * planet.resources.gunsUpgradeCost);
                               planet.resources.carbonMined   -= (1000 * planet.resources.gunsUpgradeCost);
                               planet.resources.creditMined   -= (1000 * planet.resources.gunsUpgradeCost);
                               planet.resources.gasMined      -= (1000 * planet.resources.gunsUpgradeCost);
                               planet.resources.gunsUpgradeCost++;
                               createShipUpgradesMenu("Done...");
                           } else {
                               createShipUpgradesMenu("Need more resources....");
                           }
                       } else {
                           createShipUpgradesMenu("message: Guns  upgrades are at maximum....");
                       }
                   }
                   if(index == 8 ) {
                       if(planet.resources.hullUpgradeLevel < 3) {
                           if (planet.resources.energyMined >= (1000 * planet.resources.hullUpgradeCost) && planet.resources.metalOreMined >= (1000 * planet.resources.hullUpgradeCost) && planet.resources.mineralsMined >= (1000 * planet.resources.hullUpgradeCost) && planet.resources.carbonMined >= (1000 * planet.resources.hullUpgradeCost) && planet.resources.creditMined >= (1000 * planet.resources.hullUpgradeCost) && planet.resources.gasMined >= (1000 * planet.resources.hullUpgradeCost)) {
                               planet.resources.hullUpgradeLevel++;
                               planet.resources.energyMined   -= (1000 * planet.resources.hullUpgradeCost);
                               planet.resources.metalOreMined -= (1000 * planet.resources.hullUpgradeCost);
                               planet.resources.mineralsMined -= (1000 * planet.resources.hullUpgradeCost);
                               planet.resources.carbonMined   -= (1000 * planet.resources.hullUpgradeCost);
                               planet.resources.creditMined   -= (1000 * planet.resources.hullUpgradeCost);
                               planet.resources.gasMined      -= (1000 * planet.resources.hullUpgradeCost);
                               planet.resources.hullUpgradeCost++;
                               createShipUpgradesMenu("Done...");
                           } else {
                               createShipUpgradesMenu("Need more resources....");
                           }
                       } else {
                           createShipUpgradesMenu("message: Hull  upgrade is at maximum....");
                       }
                   }
                   if(index == 9 ) {
                       if(playerList.get(0).shieldTec) {
                           if(planet.resources.shieldUpgradeLevel < 3){
                               if (planet.resources.energyMined >= (1000 * planet.resources.shieldUpgradeCost) && planet.resources.metalOreMined >= (1000 * planet.resources.shieldUpgradeCost) && planet.resources.mineralsMined >= (1000 * planet.resources.shieldUpgradeCost) && planet.resources.carbonMined >= (1000 * planet.resources.shieldUpgradeCost) && planet.resources.creditMined >= (1000 * planet.resources.shieldUpgradeCost) && planet.resources.gasMined >= (1000 * planet.resources.shieldUpgradeCost)) {
                                   planet.resources.shieldUpgradeLevel++;
                                   planet.resources.energyMined   -= (1000 * planet.resources.shieldUpgradeCost);
                                   planet.resources.metalOreMined -= (1000 * planet.resources.shieldUpgradeCost);
                                   planet.resources.mineralsMined -= (1000 * planet.resources.shieldUpgradeCost);
                                   planet.resources.carbonMined   -= (1000 * planet.resources.shieldUpgradeCost);
                                   planet.resources.creditMined   -= (1000 * planet.resources.shieldUpgradeCost);
                                   planet.resources.gasMined      -= (1000 * planet.resources.shieldUpgradeCost);
                                   planet.resources.shieldUpgradeCost++;
                                   createShipUpgradesMenu("Done...");
                               } else {
                                   createShipUpgradesMenu("Need more resources....");
                               }
                           } else {
                               createShipUpgradesMenu("message: Shield  upgrade is at maximum....");
                           }
                       } else {
                           createShipUpgradesMenu("message: Shield Technologi not reserch....");
                       }
                   }

               }
               //____________________________________________________________________________________//
               //_____SELECT_PLANET_FROM _SUN_MENU_______________________________________________________________________________________________//
               if(spacecomander.SpaceComander.star_select_menu)
               {
                   planet = serch.serchSunPlanetSelected(menuSelectRay.x, menuSelectRay.y , sun);
                   if(planet != null)
                   {
                       if(planet.owner == spacecomander.SpaceComander.race_index)
                       {
                            if(planet.player != null) {
                                if (planet.player.index == race_index) {
                                    generatePlanetMenu(planet);
                                }
                            } else {
                                generatePlanetMenu(planet);
                            }
                       }
                   }
               }
               //______________________________________________________________________________________________________________________________//
              //______SELECT__SUN_____________________________________________________________________________________________________________________________________________________________________________________________________________//
              if(serch.serchSunSelected(curentRay.x, curentRay.y , sunList) != null && !SpaceComander.ship_select_menu && !spacecomander.SpaceComander.star_select_menu)
              {
                    sun =  serch.serchSunSelected(curentRay.x, curentRay.y , sunList);
                    if(sun != null) 
                    {
                      spacecomander.SpaceComander.colony_menu_1 = false;
                      
                            textToRender.clear();
                            menuData.menuPoliList.clear();  
                            menuData.createSunSelectMenu(camera,sun);
                            resetBooleanVar();
                            textToRender.add(new TextData("Star Type: "+sun.starType, camera.getPosition().x + 0.0275f, camera.getPosition().y + 0, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                            spacecomander.SpaceComander.star_select_menu = true;
                            //System.err.println("albastru -> "+sun.player_planets_own[0]+"  red-> "+sun.player_planets_own[1]+"  mov-> "+sun.player_planets_own[2]+"  white-> "+sun.player_planets_own[3]+"  yello -> "+sun.player_planets_own[4]+"  green-> "+sun.player_planets_own[5]+"  blue-> "+sun.player_planets_own[6]+"  maro-> "+sun.player_planets_own[7]+"  violet-> "+sun.player_planets_own[8]);
                    
                    }
              }
              //__________________________________________________________________________________________________________________________________________________________________________________________________________________//          
              
              //___SELECT_PLANETS____________________________________________________________________//
              if(spacecomander.SpaceComander.colony_menu_1)
              {
                    planet = serch.serchPlanetSelected(curentRay.x, curentRay.y, sunList);
                    if(planet != null){
                       if(planet.owner == spacecomander.SpaceComander.race_index)
                       {
                            if(planet.player != null) {
                                if (planet.player.index == race_index) {
                                    generatePlanetMenu(planet);
                                }
                            } else {
                                generatePlanetMenu(planet);
                            }
                       }
                    }
              }
              //___________________________________________________________________________________// 

              
              //__SELECT_MENU_1____________________________________________________________________//
                if(spacecomander.SpaceComander.colony_menu_1){
                    generateColoniMenu_2(start_coloni_index , end_coloni_index);
                }
              //___________________________________________________________________________________//
              
              //___RESERCH_TEC_____________________________________________________________________//
                if(spacecomander.SpaceComander.tec_menu){
                    createTecReserchMenu(playerList.get(0));
                }
              //___________________________________________________________________________________//
              
              //__SELECT_MENU_2____________________________________________________________________//
                if(spacecomander.SpaceComander.colony_menu_2){
                   int  index = serch.selectMenu_2(menuSelectRay.x, menuSelectRay.y , playerList.get(0));
                    
                   if(index  > 0 && index < 10)
                   {
                            textToRender.clear();
                            menuData.menuPoliList.clear();
                            resetBooleanVar();
                            planet = playerList.get(0).watherPList.get((index - 1)+(page*9));
                            generatePlanetMenu(planet);        
                            spacecomander.SpaceComander.planet_select_menu = true;

                   } else {
                            if(index == -1) {
                                if(start_coloni_index - 9 >=0)    {
                                    start_coloni_index -= 9;
                                    end_coloni_index   -= 9; 
                                    page--;
                                    generateColoniMenu_2(start_coloni_index , end_coloni_index);
                                }
                            } 
                            if(index == 0) {
                                if(playerList.get(0).watherPList.size() > start_coloni_index + 9)  {
                                    start_coloni_index += 9;
                                    end_coloni_index   += 9;  
                                    page++;
                                    generateColoniMenu_2(start_coloni_index , end_coloni_index);

                                }
                            }
                          }
                }
              //___________________________________________________________________________________// 

              


           }
           if(action == GLFW_RELEASE){
             // mouseIsDown = false;
              curentRay =  pick.update(false , 0 , 0);
              x_drag_end = curentRay.x;
              y_drag_end = curentRay.y;
              SpaceComander.selectQuadStatus = false;

              if(Math.abs(x_drag_start - x_drag_end) > 0.01f && Math.abs(y_drag_start - y_drag_end) > 0.01f) {

                  if (enabledragSelection)
                  {
                      quadUtil.getDragAndDropSelectedQuads(playerList, x_drag_start, y_drag_start, x_drag_end, y_drag_end);

                      if (quadUtil.selected_ship_count != 0)
                      {
                          enabledragSelection = false;

                              textToRender.clear();
                              menuData.menuPoliList.clear();
                              resetBooleanVar();

                          menuData.createShipSelectMenu(camera, playerList.get(race_index));
                          shipSelectCountPrint();
                          SpaceComander.ship_select_menu = true;
                          SpaceComander.shipSelectMenuText = true;

                      }
                  }
              }
           }
       }
       if(button == GLFW_MOUSE_BUTTON_2){
           if(action == GLFW_PRESS) {
               curentRay = pick.update(false , 0 , 0);
               quadUtil.translateSelectedShips(playerList, curentRay.x, curentRay.y);

               if(!SpaceComander.ship_select_menu){
                   menuData.menuPoliList.clear();
                   textToRender.clear();
                   resetBooleanVar();
                   enableColoniMenu_1();
               }

                sun = null;
                planet = null;
               // enableColoniMenu_1();
                page = 0;
                start_coloni_index = 0;
                end_coloni_index = 9;             
           }
           if(action == GLFW_RELEASE){}
       }
    }
    public void shipSelectCountPrint(){

        ship_select_delta = 0;
        if (quadUtil.coloni_ship_count != 0) {
            stringB.setLength(0);
            stringB.append(quadUtil.coloni_ship_count);
            textToRender.add(new TextData(stringB.toString(), camera.getPosition().x - 0.072f + ship_select_delta, camera.getPosition().y - 0.081f, camera.getPosition().z - 0.15f, ibo, textureUtil.textChart, false, 0));
            ship_select_delta += 0.015f;
        }
        if (quadUtil.explorer_ship_count != 0) {
            stringB.setLength(0);
            stringB.append(quadUtil.explorer_ship_count);
            textToRender.add(new TextData(stringB.toString(), camera.getPosition().x - 0.072f + ship_select_delta, camera.getPosition().y - 0.081f, camera.getPosition().z - 0.15f, ibo, textureUtil.textChart, false, 0));
            ship_select_delta += 0.015f;
        }
        if (quadUtil.fregate_ship_count != 0) {
            stringB.setLength(0);
            stringB.append(quadUtil.fregate_ship_count);
            textToRender.add(new TextData(stringB.toString(), camera.getPosition().x - 0.072f + ship_select_delta, camera.getPosition().y - 0.081f, camera.getPosition().z - 0.15f, ibo, textureUtil.textChart, false, 0));
            ship_select_delta += 0.015f;
        }
        if (quadUtil.destroier_ship_count != 0) {
            stringB.setLength(0);
            stringB.append(quadUtil.destroier_ship_count);
            textToRender.add(new TextData(stringB.toString(), camera.getPosition().x - 0.072f + ship_select_delta, camera.getPosition().y - 0.081f, camera.getPosition().z - 0.15f, ibo, textureUtil.textChart, false, 0));
            ship_select_delta += 0.015f;
        }
        if (quadUtil.carrier_ship_count != 0) {
            stringB.setLength(0);
            stringB.append(quadUtil.carrier_ship_count);
            textToRender.add(new TextData(stringB.toString(), camera.getPosition().x - 0.072f + ship_select_delta, camera.getPosition().y - 0.081f, camera.getPosition().z - 0.15f, ibo, textureUtil.textChart, false, 0));
            ship_select_delta += 0.015f;
        }
        if (quadUtil.interceptor_ship_count != 0) {
            stringB.setLength(0);
            stringB.append(quadUtil.interceptor_ship_count);
            textToRender.add(new TextData(stringB.toString(), camera.getPosition().x - 0.072f + ship_select_delta, camera.getPosition().y - 0.081f, camera.getPosition().z - 0.15f, ibo, textureUtil.textChart, false, 0));
            ship_select_delta += 0.015f;
        }
        if (quadUtil.mother_ship_count != 0) {
            stringB.setLength(0);
            stringB.append(quadUtil.mother_ship_count);
            textToRender.add(new TextData(stringB.toString(), camera.getPosition().x - 0.072f + ship_select_delta, camera.getPosition().y - 0.081f, camera.getPosition().z - 0.15f, ibo, textureUtil.textChart, false, 0));
            ship_select_delta += 0.015f;
        }
    }
    public boolean checkShipProductionResources(int data , Planet planet) {
       boolean ok = false;
       if(data == 0){
          if(planet.resources.energyMined >= 10 && planet.resources.metalOreMined >= 10 && planet.resources.mineralsMined >= 10 && planet.resources.carbonMined >= 10 && planet.resources.creditMined >= 10 && planet.resources.gasMined >= 10) {
             planet.resources.energyMined    -= 10;
             planet.resources.metalOreMined  -= 10;
             planet.resources.mineralsMined  -= 10;
             planet.resources.carbonMined    -= 10;
             planet.resources.creditMined    -= 10;
             planet.resources.gasMined       -= 10;
             ok = true;
          }
       }
       if(data == 1){
          if(planet.resources.energyMined >= 20 && planet.resources.metalOreMined >= 20 && planet.resources.mineralsMined >= 20 && planet.resources.carbonMined >= 20 && planet.resources.creditMined >= 20 && planet.resources.gasMined >= 20) {
             planet.resources.energyMined    -= 20;
             planet.resources.metalOreMined  -= 20;
             planet.resources.mineralsMined  -= 20;
             planet.resources.carbonMined    -= 20;
             planet.resources.creditMined    -= 20;
             planet.resources.gasMined       -= 20;
             ok = true;
          }
       }
       if(data == 2){
          if(planet.resources.energyMined >= 30 && planet.resources.metalOreMined >= 30 && planet.resources.mineralsMined >= 30 && planet.resources.carbonMined >= 30 && planet.resources.creditMined >= 30 && planet.resources.gasMined >= 30) {
             planet.resources.energyMined    -= 30;
             planet.resources.metalOreMined  -= 30;
             planet.resources.mineralsMined  -= 30;
             planet.resources.carbonMined    -= 30;
             planet.resources.creditMined    -= 30;
             planet.resources.gasMined       -= 30;
             ok = true;
          }
       }  
       if(data == 3){
          if(planet.resources.energyMined >= 40 && planet.resources.metalOreMined >= 40 && planet.resources.mineralsMined >= 40 && planet.resources.carbonMined >= 40 && planet.resources.creditMined >= 40 && planet.resources.gasMined >= 40) {
             planet.resources.energyMined    -= 40;
             planet.resources.metalOreMined  -= 40;
             planet.resources.mineralsMined  -= 40;
             planet.resources.carbonMined    -= 40;
             planet.resources.creditMined    -= 40;
             planet.resources.gasMined       -= 40;
             ok = true;
          }
       }
       if(data == 4){
          if(planet.resources.energyMined >= 80 && planet.resources.metalOreMined >= 80 && planet.resources.mineralsMined >= 80 && planet.resources.carbonMined >= 80 && planet.resources.creditMined >= 80 && planet.resources.gasMined >= 80) {
             planet.resources.energyMined    -= 80;
             planet.resources.metalOreMined  -= 80;
             planet.resources.mineralsMined  -= 80;
             planet.resources.carbonMined    -= 80;
             planet.resources.creditMined    -= 80;
             planet.resources.gasMined       -= 80;
             ok = true;
          }
       } 
       if(data == 5){
          if(planet.resources.energyMined >= 100 && planet.resources.metalOreMined >= 100 && planet.resources.mineralsMined >= 100 && planet.resources.carbonMined >= 100 && planet.resources.creditMined >= 100 && planet.resources.gasMined >= 100) {
             planet.resources.energyMined    -= 100;
             planet.resources.metalOreMined  -= 100;
             planet.resources.mineralsMined  -= 100;
             planet.resources.carbonMined    -= 100;
             planet.resources.creditMined    -= 100;
             planet.resources.gasMined       -= 100;
             ok = true;
          }
       }        
       return ok;
    }
    public void incrementUpgratesCost(Planet planet , int i){
      if(i == 0)   {
        planet.resources.energyUpgradeCost    =  planet.resources.energyUpgradeCost +10;
      }
      if(i == 1) {
        planet.resources.metalOreUpgradeCost  = planet.resources.metalOreUpgradeCost +10;
      }
      if(i == 2) {
        planet.resources.mineralsUpgradeCost  = planet.resources.mineralsUpgradeCost +10;
      }
      if(i ==3) {
        planet.resources.carbonUpgradeCost    = planet.resources.carbonUpgradeCost +10;
      }
      if(i ==4) {
        planet.resources.creditUpgradeCost    = planet.resources.creditUpgradeCost +10;
      }
      if(i ==5) {
        planet.resources.gasUpgradeCost       =  planet.resources.gasUpgradeCost  + 10;
      }
    }
    public void createTecReserchMenu(Player player)  {
        resetBooleanVar();        
        textToRender.clear();

          int  data = serch.selectMenu_1(menuSelectRay.x, menuSelectRay.y);

          if(data == 1) {
            player.laserTec = true;
          }
          if(data == 2) {
            player.torpedoTec = true;
          }
          if(data == 3) {
            player.shieldTec = true;
            
            for(Quad ship : player.shipList){
                ship.shield = 100;
            }
          }       
          if(data == 4) {
            player.warpDriveTec = true;
            
            for(Quad ship : player.shipList){
                ship.player.warpDriveTec = true;
            }
          }
          if(data == 5) {
            player.InterceptorTec = true;
          }           
        menuData.menuPoliList.clear();
        menuData.createTecMenu(camera , playerList.get(0));
        spacecomander.SpaceComander.tec_menu = true;

    }
    public boolean  upgraderMiningF(Planet planet)
    {
  
        if(planet.resources.energyMined >= planet.resources.energyUpgradeCost && planet.resources.metalOreMined >= planet.resources.metalOreUpgradeCost && planet.resources.mineralsMined >= planet.resources.mineralsUpgradeCost){
             if(planet.resources.carbonMined >= planet.resources.carbonUpgradeCost && planet.resources.creditMined >= planet.resources.creditUpgradeCost && planet.resources.gasMined >= planet.resources.gasUpgradeCost){
              
                            planet.resources.energyMined   -= planet.resources.energyUpgradeCost;
                            planet.resources.metalOreMined -= planet.resources.metalOreUpgradeCost;
                            planet.resources.mineralsMined -= planet.resources.mineralsUpgradeCost;
                            planet.resources.carbonMined   -= planet.resources.carbonUpgradeCost;
                            planet.resources.creditMined   -= planet.resources.creditUpgradeCost;
                            planet.resources.gasMined      -= planet.resources.gasUpgradeCost;   
                            
                 return true;              
                 
             } else {
                       return false;
                    }
        } else {
                 return false;
               }
    }
    public void createShipUpgradesMenu(String message) 
    {
        resetBooleanVar();        
        textToRender.clear();
        menuData.menuPoliList.clear();
        menuData.createShipUpgradesMenu(camera, planet);
        
            textToRender.add(new TextData("Engine ", camera.getPosition().x    - 0.0475f, camera.getPosition().y     - 0.055f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Guns ", camera.getPosition().x - 0.025f, camera.getPosition().y - 0.055f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Hull ", camera.getPosition().x  - 0.0f, camera.getPosition().y  - 0.055f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0)); 
            textToRender.add(new TextData("Shield ", camera.getPosition().x    + 0.020f, camera.getPosition().y    - 0.055f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));             
     
            textToRender.add(new TextData("Lv: "+String.valueOf(planet.resources.engineUpgradeLevel), camera.getPosition().x    - 0.047f, camera.getPosition().y    - 0.07f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Lv: "+String.valueOf(planet.resources.gunsUpgradeLevel), camera.getPosition().x    - 0.026f,  camera.getPosition().y    - 0.07f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Lv: "+String.valueOf(planet.resources.hullUpgradeLevel), camera.getPosition().x    - 0.0015f,  camera.getPosition().y    - 0.07f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0)); 
            textToRender.add(new TextData("Lv: "+String.valueOf(planet.resources.shieldUpgradeLevel), camera.getPosition().x    + 0.02f,  camera.getPosition().y    - 0.07f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));             
   
            textToRender.add(new TextData(message, camera.getPosition().x    - 0.047f, camera.getPosition().y    - 0.0815f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));            
                            
        
        spacecomander.SpaceComander.upgrades_menu = true;

    
    }
    public void createCargoBayMenu(Planet planet , String message)
    {
        resetBooleanVar();
        textToRender.clear();
        menuData.menuPoliList.clear();

        menuData.createCargoBayMenu(camera , planet);

        textToRender.add(new TextData("Energy",   camera.getPosition().x        - 0.05f, camera.getPosition().y       - 0.0525f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
        textToRender.add(new TextData("MetalOre", camera.getPosition().x        - 0.0315f, camera.getPosition().y       - 0.0525f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
        textToRender.add(new TextData("Mineral", camera.getPosition().x        - 0.014f, camera.getPosition().y       - 0.0525f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
        textToRender.add(new TextData("Carbon", camera.getPosition().x         + 0.004f, camera.getPosition().y       - 0.0525f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
        textToRender.add(new TextData("Credit", camera.getPosition().x         + 0.0225f, camera.getPosition().y       - 0.0525f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
        textToRender.add(new TextData("Gas", camera.getPosition().x         + 0.041f, camera.getPosition().y       - 0.0525f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));

        textToRender.add(new TextData(message,   camera.getPosition().x        - 0.07f, camera.getPosition().y       - 0.066f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));

        textToRender.add(new TextData(String.valueOf(planet.resources.cargoDoc_energy_Stock), camera.getPosition().x          - 0.0425f , camera.getPosition().y       - 0.0725f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
        textToRender.add(new TextData(String.valueOf(planet.resources.cargoDoc_metalOre_Stock), camera.getPosition().x          - 0.0245f , camera.getPosition().y       - 0.0725f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
        textToRender.add(new TextData(String.valueOf(planet.resources.cargoDoc_mineral_Stock), camera.getPosition().x          - 0.0065f , camera.getPosition().y       - 0.0725f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
        textToRender.add(new TextData(String.valueOf(planet.resources.cargoDoc_carbon_Stock), camera.getPosition().x          + 0.0115f , camera.getPosition().y       - 0.0725f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
        textToRender.add(new TextData(String.valueOf(planet.resources.cargoDoc_credit_Stock), camera.getPosition().x          + 0.0295f , camera.getPosition().y       - 0.0725f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
        textToRender.add(new TextData(String.valueOf(planet.resources.cargoDoc_gas_Stock), camera.getPosition().x          + 0.0471f , camera.getPosition().y       - 0.0725f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));


        textData = new TextData(String.valueOf(planet.resources.energyMined) , camera.getPosition().x - 0.0425f , camera.getPosition().y - 0.057f , camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true , 14);
        textData.planet = planet;
        textToRender.add(textData);
        textData = new TextData(String.valueOf(planet.resources.metalOreMined) , camera.getPosition().x - 0.0245f , camera.getPosition().y - 0.057f , camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true , 15);
        textData.planet = planet;
        textToRender.add(textData);
        textData = new TextData(String.valueOf(planet.resources.mineralsMined) , camera.getPosition().x - 0.0065f , camera.getPosition().y - 0.057f , camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true , 16);
        textData.planet = planet;
        textToRender.add(textData);
        textData = new TextData(String.valueOf(planet.resources.carbonMined) , camera.getPosition().x + 0.0115f , camera.getPosition().y - 0.057f , camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true , 17);
        textData.planet = planet;
        textToRender.add(textData);
        textData = new TextData(String.valueOf(planet.resources.creditMined) , camera.getPosition().x + 0.0295f , camera.getPosition().y - 0.057f , camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true , 18);
        textData.planet = planet;
        textToRender.add(textData);
        textData = new TextData(String.valueOf(planet.resources.gasMined) , camera.getPosition().x + 0.0471f, camera.getPosition().y - 0.057f , camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true , 19);
        textData.planet = planet;
        textToRender.add(textData);

        SpaceComander.cargoBay_Menu = true;
    }
    public void createShipYardMenu(String message)
    {
        resetBooleanVar();        
        textToRender.clear();
        menuData.menuPoliList.clear();  
        
        menuData.createShipYardMenu(camera, planet);
        
            textToRender.add(new TextData("Cargo", camera.getPosition().x          - 0.046f, camera.getPosition().y       - 0.068f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Coloni", camera.getPosition().x         - 0.027f, camera.getPosition().y        - 0.068f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Explorer", camera.getPosition().x        - 0.01f, camera.getPosition().y        - 0.068f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0)); 
            textToRender.add(new TextData("Fregata", camera.getPosition().x      + 0.0065f, camera.getPosition().y        - 0.068f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));             
            textToRender.add(new TextData("Destroier", camera.getPosition().x        + 0.025f, camera.getPosition().y         - 0.068f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Carrier", camera.getPosition().x        + 0.0425f, camera.getPosition().y         - 0.068f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));            
            textToRender.add(new TextData("Mother.Ship ", camera.getPosition().x   + 0.06f, camera.getPosition().y       - 0.068f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                    
           textToRender.add(new TextData(message, camera.getPosition().x    - 0.047f, camera.getPosition().y    - 0.0815f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));       
          
        SpaceComander.shipYard_menu = true;

    }
    public void createBuildingsMenu(String message) {
        resetBooleanVar();        
        textToRender.clear();
        menuData.menuPoliList.clear();
        menuData.createBuildingsMenu(camera, planet);
        
            textToRender.add(new TextData("Energy ", camera.getPosition().x    - 0.0475f, camera.getPosition().y     - 0.055f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Metal Ore ", camera.getPosition().x - 0.027f, camera.getPosition().y - 0.055f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Minerals ", camera.getPosition().x  - 0.002f, camera.getPosition().y  - 0.055f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0)); 
            textToRender.add(new TextData("Carbon ", camera.getPosition().x    + 0.020f, camera.getPosition().y    - 0.055f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));             
            textToRender.add(new TextData("Credit ", camera.getPosition().x    + 0.04f, camera.getPosition().y    - 0.055f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Gas ", camera.getPosition().x       + 0.0625f, camera.getPosition().y        - 0.055f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            
            textToRender.add(new TextData("Lv: "+String.valueOf(planet.resources.energyLevel), camera.getPosition().x    - 0.047f, camera.getPosition().y    - 0.07f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Lv: "+String.valueOf(planet.resources.metalOreLevel), camera.getPosition().x    - 0.026f,  camera.getPosition().y    - 0.07f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Lv: "+String.valueOf(planet.resources.mineralsLevel), camera.getPosition().x    - 0.0015f,  camera.getPosition().y    - 0.07f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0)); 
            textToRender.add(new TextData("Lv: "+String.valueOf(planet.resources.carbonLevel), camera.getPosition().x    + 0.02f,  camera.getPosition().y    - 0.07f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));             
            textToRender.add(new TextData("Lv: "+String.valueOf(planet.resources.creditLevel), camera.getPosition().x    + 0.0425f,   camera.getPosition().y    - 0.07f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Lv: "+String.valueOf(planet.resources.gasLevel), camera.getPosition().x    + 0.0625f, camera.getPosition().y    - 0.07f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0)); 

            textToRender.add(new TextData(message, camera.getPosition().x    - 0.047f, camera.getPosition().y    - 0.0815f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));            
                            
        spacecomander.SpaceComander.buildings_menu = true;

   
    }
    public void generateColoniMenu_2(int start , int end) 
    {
                   int  data = serch.selectMenu_2(menuSelectRay.x, menuSelectRay.y);
                   if(data == 0 || data == 8 || data == 9)
                   {
 
                            resetBooleanVar();
                            textToRender.clear();
                            menuData.menuPoliList.clear();                        
                            menuData.createColonyMenu_2(start , end ,camera , playerList.get(0));
                            if(playerList.get(0).watherPList.size() > end)  {
                                           coloni_delta = 0;
                                for(int i=start ; i < end ; i++) {
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.energy), camera.getPosition().x - 0.0475f ,camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.metalOre),camera.getPosition().x - 0.03f , camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.minerals),camera.getPosition().x - 0.0120f ,camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.carbon), camera.getPosition().x + 0.006f , camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.gas), camera.getPosition().x + 0.024f , camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.biomas), camera.getPosition().x + 0.042f , camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.maxPopulation+" M"), camera.getPosition().x + 0.06f , camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(page+1), camera.getPosition().x - 0.0f , camera.getPosition().y - 0.068f , camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           coloni_delta += 0.015f;   
                                } 
                            } else {                             
                                        coloni_delta = 0;
                                        for(int i=start ; i < playerList.get(0).watherPList.size() ; i++) {
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.energy), camera.getPosition().x - 0.0475f ,camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.metalOre),camera.getPosition().x - 0.03f , camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.minerals),camera.getPosition().x - 0.0120f ,camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.carbon), camera.getPosition().x + 0.006f , camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.gas), camera.getPosition().x + 0.024f , camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.biomas), camera.getPosition().x + 0.042f , camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(playerList.get(0).watherPList.get(i).resources.maxPopulation+" M"), camera.getPosition().x + 0.06f , camera.getPosition().y + 0.06f - coloni_delta, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                                           textToRender.add(new TextData(String.valueOf(page+1), camera.getPosition().x - 0.0f , camera.getPosition().y - 0.068f , camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));                                                                                          
                                           coloni_delta += 0.015f;
                                        }                                       
                                    }
                            
                       spacecomander.SpaceComander.colony_menu_2 = true;

                   }
                   if(data == 1)  {
                            resetBooleanVar();
                            textToRender.clear();
                            menuData.menuPoliList.clear();
                            menuData.createTecMenu(camera , playerList.get(0));                      
                      spacecomander.SpaceComander.tec_menu = true;

                   }
    
    }
    public void generatePlanetMenu(Planet planet)
    {
            resetBooleanVar();             
            textToRender.clear();
            menuData.menuPoliList.clear(); 
            menuData.createPlanetSelectMenu(camera, planet);
        
            if(planet.isWather) {
                textToRender.add(new TextData("Whater world", camera.getPosition().x -0.08f, camera.getPosition().y - 0.0515f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                textToRender.add(new TextData("Class A", camera.getPosition().x -0.08f, camera.getPosition().y - 0.08f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            }
            if(planet.isArid) {
                textToRender.add(new TextData("Arid world", camera.getPosition().x -0.08f, camera.getPosition().y - 0.0515f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0)); 
                textToRender.add(new TextData("Class C", camera.getPosition().x -0.08f, camera.getPosition().y - 0.08f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            }
            if(planet.isSemiarid) {
                textToRender.add(new TextData("SemiArid world", camera.getPosition().x -0.08f, camera.getPosition().y - 0.0515f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0)); 
                textToRender.add(new TextData("Class B", camera.getPosition().x -0.08f, camera.getPosition().y - 0.08f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            }
            if(planet.isVulcanic) {
                textToRender.add(new TextData("Vulcanic world", camera.getPosition().x -0.08f, camera.getPosition().y - 0.0515f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
                textToRender.add(new TextData("Class E", camera.getPosition().x -0.08f, camera.getPosition().y - 0.08f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            }
            if(planet.isIce) {
                textToRender.add(new TextData("Ice world", camera.getPosition().x -0.08f, camera.getPosition().y - 0.0515f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));        
                textToRender.add(new TextData("Class D", camera.getPosition().x -0.08f, camera.getPosition().y - 0.08f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            }
            textToRender.add(new TextData("Buildings ", camera.getPosition().x -0.05f, camera.getPosition().y - 0.05f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Reserch ", camera.getPosition().x -0.02f, camera.getPosition().y - 0.05f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("ShipYard ", camera.getPosition().x +0.01f, camera.getPosition().y - 0.05f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("StarDoc ", camera.getPosition().x +0.04f, camera.getPosition().y - 0.05f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            
            textToRender.add(new TextData("Energy ", camera.getPosition().x -0.05f, camera.getPosition().y - 0.070f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Metal Ore ", camera.getPosition().x -0.027f, camera.getPosition().y - 0.070f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Minerals ", camera.getPosition().x -0.002f, camera.getPosition().y - 0.070f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Carbon ", camera.getPosition().x +0.025f, camera.getPosition().y - 0.070f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Gas ", camera.getPosition().x +0.048f, camera.getPosition().y - 0.070f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Credit ", camera.getPosition().x +0.065f, camera.getPosition().y - 0.070f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Population ", camera.getPosition().x +0.065f, camera.getPosition().y - 0.053f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("Workers", camera.getPosition().x +0.065f, camera.getPosition().y - 0.0615f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));
            textToRender.add(new TextData("["+planet.resources.maxPopulation+"]", camera.getPosition().x +0.0755f, camera.getPosition().y - 0.058f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , false,0));

            textData = new TextData(String.valueOf(planet.resources.energyMined) , camera.getPosition().x -0.05f , camera.getPosition().y - 0.073f , camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true , 1);
            textData.planet = planet;
            textToRender.add(textData);
            textData = new TextData(String.valueOf(planet.resources.metalOreMined), camera.getPosition().x -0.027f, camera.getPosition().y - 0.073f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true,2);
            textData.planet = planet;
            textToRender.add(textData);
            textData = new TextData(String.valueOf(planet.resources.mineralsMined), camera.getPosition().x -0.002f, camera.getPosition().y - 0.073f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true,3);
            textData.planet = planet;
            textToRender.add(textData);
            textData = new TextData(String.valueOf(planet.resources.carbonMined), camera.getPosition().x  +0.025f, camera.getPosition().y - 0.073f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true,4);
            textData.planet = planet;
            textToRender.add(textData);
            textData = new TextData(String.valueOf(planet.resources.creditMined), camera.getPosition().x +0.07f, camera.getPosition().y - 0.073f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true,5);
            textData.planet = planet;
            textToRender.add(textData);
            textData = new TextData(String.valueOf(planet.resources.gasMined), camera.getPosition().x +0.048f, camera.getPosition().y - 0.073f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true,6);
            textData.planet = planet;
            textToRender.add(textData);
            textData = new TextData(String.valueOf(planet.resources.population), camera.getPosition().x +0.071f, camera.getPosition().y - 0.058f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true,7);
            textData.planet = planet;
            textToRender.add(textData);

        textData = new TextData(String.valueOf(planet.resources.energyWorker) , camera.getPosition().x -0.051f , camera.getPosition().y - 0.078f , camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true , 8);
        textData.planet = planet;
        textToRender.add(textData);
        textData = new TextData(String.valueOf(planet.resources.metalOreWorker), camera.getPosition().x -0.027f, camera.getPosition().y - 0.078f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true,9);
        textData.planet = planet;
        textToRender.add(textData);
        textData = new TextData(String.valueOf(planet.resources.mineralsWorker), camera.getPosition().x -0.0027f, camera.getPosition().y - 0.078f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true,10);
        textData.planet = planet;
        textToRender.add(textData);
        textData = new TextData(String.valueOf(planet.resources.carbonWorker), camera.getPosition().x  +0.024f, camera.getPosition().y - 0.078f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true,11);
        textData.planet = planet;
        textToRender.add(textData);
        textData = new TextData(String.valueOf(planet.resources.gasWorker), camera.getPosition().x +0.048f, camera.getPosition().y - 0.078f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true,12);
        textData.planet = planet;
        textToRender.add(textData);
        textData = new TextData(String.valueOf(planet.resources.workersAvaible), camera.getPosition().x +0.071f, camera.getPosition().y - 0.066f, camera.getPosition().z - 0.15f  , ibo , textureUtil. textChart , true,13);
        textData.planet = planet;
        textToRender.add(textData);




        spacecomander.SpaceComander.planet_select_menu = true;

    }
    public void resetBooleanVar() 
    {       
        SpaceComander.star_select_menu = false;
        SpaceComander.planet_select_menu = false;
        SpaceComander.colony_menu_1 = false;
        SpaceComander.colony_menu_2 = false;
        SpaceComander.buildings_menu = false;
        SpaceComander.shipYard_menu = false;
        SpaceComander.upgrades_menu = false;
        SpaceComander.tec_menu = false;
        //SpaceComander.ship_select_menu = false;
        SpaceComander.shipSelectMenuText = false;
        SpaceComander.cargoBay_Menu = false;

    }
    public void enableColoniMenu_1()
    {
      menuData.createColonyMenu_1(camera);  
      spacecomander.SpaceComander.colony_menu_1 = true;        
    }    
}
