
package AILogic;

import Camera.Camera;
import Menu.MenuData;
import Planet.Planet;
import Player.Player;
import Sun.Sun;
import TextUtil.TextData;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import spacecomander.Quad;
import spacecomander.QuadsManagement;


public class AILogic {
    
    int count_1 , count_2 , count_3 , count_4 , count_5 , count_6 , count_7 , count_8 , count_9;
    AIUtil aIUtil;
    QuadsManagement quadManagement;
    ArrayList<Sun>   sunList;
    ArrayList<Player>    playerList;
    Random random = new Random();
    ArrayList<TextData>  textToRender;
    Camera  camera;
    int cost;
    
    public AILogic(AIUtil aIUtil , ArrayList<Sun>   sunList , QuadsManagement quadManagement , ArrayList<Player>   playerList , ArrayList<TextData>  textToRender , Camera  camera)
    {
        this.aIUtil = aIUtil;
        this.sunList = sunList;
        this.quadManagement = quadManagement;
        this.playerList = playerList;
        this.textToRender = textToRender;
        this.camera = camera;
        count_1 = 45;
    }
    
    public void startAI()  
    {
        if(count_1 < 25)
        {
           count_1++;
        } else {
            count_1 = 0;

                    if(count_2 < 2){
                       count_2++;
                    } else {
                                colonizePlanet(playerList);

                                count_2 = 0;
                                if(count_3 < 1)
                                {
                                   count_3++;
                                } else {
                                         addAIShip();
                                         
                                         updateText();
                                         count_3 = 0;
                                    if(count_4 <= 2)
                                    {

                                        incresePopulation();
                                        count_4++;
                                    } else {
                                       mineResources();

                                       deplaySquad();

                                       //deployCorgoShip(playerList);
                                        count_4 = 0;
                                    }
                                }

                           }            
               }
        
    }
    public void asignateAIWorkers(Planet planet) {
        if(planet.player.auto_assign_worker)
        {   if(planet.isWather)
            {
                if (planet.resources.workersAvaible > 0) {
                    if (planet.resources.energyWorker <= planet.resources.metalOreWorker && planet.resources.energyWorker <= planet.resources.mineralsWorker && planet.resources.energyWorker <= planet.resources.carbonWorker && planet.resources.energyWorker <= planet.resources.gasWorker) {
                        planet.resources.energyWorker++;
                        planet.resources.workersAvaible--;
                    } else {
                        if (planet.resources.metalOreWorker <= planet.resources.energyWorker && planet.resources.metalOreWorker <= planet.resources.mineralsWorker && planet.resources.metalOreWorker <= planet.resources.carbonWorker && planet.resources.metalOreWorker <= planet.resources.gasWorker) {
                            planet.resources.metalOreWorker++;
                            planet.resources.resourcesMinedCount++;
                            planet.resources.workersAvaible--;
                        } else {
                            if (planet.resources.mineralsWorker <= planet.resources.energyWorker && planet.resources.mineralsWorker <= planet.resources.metalOreWorker && planet.resources.mineralsWorker <= planet.resources.carbonWorker && planet.resources.mineralsWorker <= planet.resources.gasWorker) {
                                planet.resources.mineralsWorker++;
                                planet.resources.resourcesMinedCount++;
                                planet.resources.workersAvaible--;
                            } else {
                                if (planet.resources.carbonWorker <= planet.resources.energyWorker && planet.resources.carbonWorker <= planet.resources.metalOreWorker && planet.resources.carbonWorker <= planet.resources.mineralsWorker && planet.resources.carbonWorker <= planet.resources.gasWorker) {
                                    planet.resources.carbonWorker++;
                                    planet.resources.resourcesMinedCount++;
                                    planet.resources.workersAvaible--;
                                } else {
                                    if (planet.resources.gasWorker <= planet.resources.energyWorker && planet.resources.gasWorker <= planet.resources.metalOreWorker && planet.resources.gasWorker <= planet.resources.mineralsWorker && planet.resources.gasWorker <= planet.resources.carbonWorker) {
                                        planet.resources.gasWorker++;
                                        planet.resources.resourcesMinedCount++;
                                        planet.resources.workersAvaible--;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                        if (planet.resources.workersAvaible > 0)
                        {
                            if(planet.resources.metalOre >= planet.resources.minerals && planet.resources.metalOre >= planet.resources.carbon && planet.resources.metalOre >= planet.resources.gas) {
                                    planet.resources.metalOreWorker++;
                                    planet.resources.resourcesMinedCount++;
                                    planet.resources.workersAvaible--;
                            } else {
                                if(planet.resources.minerals >= planet.resources.metalOre && planet.resources.minerals >= planet.resources.carbon && planet.resources.minerals >= planet.resources.gas) {
                                    planet.resources.mineralsWorker++;
                                    planet.resources.resourcesMinedCount++;
                                    planet.resources.workersAvaible--;
                                } else {
                                    if(planet.resources.carbon >= planet.resources.minerals && planet.resources.carbon >= planet.resources.metalOre && planet.resources.carbon >= planet.resources.gas) {
                                        planet.resources.carbonWorker++;
                                        planet.resources.resourcesMinedCount++;
                                        planet.resources.workersAvaible--;
                                    } else {
                                        if (planet.resources.gas >= planet.resources.minerals && planet.resources.gas >= planet.resources.metalOre && planet.resources.gas >= planet.resources.carbon) {
                                            planet.resources.gasWorker++;
                                            planet.resources.resourcesMinedCount++;
                                            planet.resources.workersAvaible--;
                                        }
                                    }
                                }

                            }

                        }

                   }
        }
    }
    public void incresePopulation(){
        for(Player player : playerList) {
            for(Planet planet : player.cPlanetList) {
                if(planet.resources.maxPopulation > 0)
                {
                    if (planet.resources.population < planet.resources.maxPopulation) {
                        if (!planet.isWather) {
                            if (planet.isSemiarid) {
                                planet.resources.population += 2;
                                planet.resources.workersAvaible  = planet.resources.population / 10;
                                planet.resources.workersAvaible = planet.resources.workersAvaible - planet.resources.energyWorker - planet.resources.metalOreWorker - planet.resources.mineralsWorker - planet.resources.carbonWorker - planet.resources.gasWorker;

                            } else {
                                planet.resources.population++;
                                planet.resources.workersAvaible  = planet.resources.population / 10;
                                planet.resources.workersAvaible = planet.resources.workersAvaible - planet.resources.energyWorker - planet.resources.metalOreWorker - planet.resources.mineralsWorker - planet.resources.carbonWorker - planet.resources.gasWorker;

                            }
                        } else {
                            planet.resources.population += 5;
                            planet.resources.workersAvaible  = planet.resources.population / 10;
                            planet.resources.workersAvaible = planet.resources.workersAvaible - planet.resources.energyWorker - planet.resources.metalOreWorker - planet.resources.mineralsWorker - planet.resources.carbonWorker - planet.resources.gasWorker;
                        }
                    } else {
                        planet.resources.workersAvaible  = planet.resources.population / 10;
                        planet.resources.workersAvaible = planet.resources.workersAvaible - planet.resources.energyWorker - planet.resources.metalOreWorker - planet.resources.mineralsWorker - planet.resources.carbonWorker - planet.resources.gasWorker;

                    }
               } else {
                    if (planet.resources.population > 0){
                        planet.resources.population -= 1;
                        planet.resources.workersAvaible  = planet.resources.population / 10;
                    }
                }
                asignateAIWorkers(planet);
            }
        }
    }
    public void updateText(){
        for(TextData data : textToRender) {
             if(data.updateStatus){
                data.update(camera.getPosition().x , camera.getPosition().y , camera.getPosition().z);
             }
        }
    }    
    public void mineResources()
    {
      int pop;
      for(Player player : playerList)
      {
         for(Planet planet : player.cPlanetList)
         {
            pop = planet.resources.population / 10;
            if(planet.resources.energy > 0) {
                planet.resources.energyMined += (planet.resources.energyLevel + planet.resources.energy) * planet.resources.energyWorker;
            }
            if(planet.resources.metalOre > 0) {
                planet.resources.metalOreMined += (planet.resources.metalOreLevel + planet.resources.metalOre) * planet.resources.metalOreWorker;
            }
            if(planet.resources.minerals > 0) {
                planet.resources.mineralsMined += (planet.resources.mineralsLevel + planet.resources.minerals) * planet.resources.mineralsWorker;
            }
            if(planet.resources.carbon > 0) {
                planet.resources.carbonMined += (planet.resources.carbonLevel + planet.resources.carbon) * planet.resources.carbonWorker;
            }

            planet.resources.creditMined   += planet.resources.creditLevel+ pop;

             if(planet.resources.gas > 0) {
                 planet.resources.gasMined += (planet.resources.gasLevel + planet.resources.gas) * planet.resources.gasWorker;
             }
         }
      }
    
    }
    public void deplaySquad()
    {
       for(Player player : playerList) 
       {

           if(player.auto_deploySquad)
           {
              if(player.cPlanetList.size() != 0)
              {

                  int p = random.nextInt(player.cPlanetList.size());
                  
                   Planet tPlanet = aIUtil.getClosestEnemyPlanet(player.cPlanetList.get(p), sunList , playerList);

                   if(tPlanet != null)
                   {
                      if(player.cPlanetList.get(p).shipCount >= player.cPlanetList.get(p).planetMaxShipCount)
                      {
                          aIUtil.deplaySquad(player.cPlanetList.get(p), player, tPlanet);

                      }
                   }
              }
           }
       }
    
    
    }
    public void addAIShip() 
    {
        for(Player player : playerList)
        {
            if(player.auto_addShip)
            {
                 if(player.watherPList.size() != 0 && player.shipCount < player.maxShipCount)
                 {
                     int r= random.nextInt(5);
                 //  int p = random.nextInt(player.watherPList.size());
                     int p = random.nextInt(player.cPlanetList.size());
                     Planet planetR = player.cPlanetList.get(p); 

                     if(planetR.shipCount < planetR.planetMaxShipCount)
                     { 
                            if(r == 0){
                                if(addShipIfExistResources(1,planetR)) {
                                    quadManagement.addExploreShip(planetR, player, planetR.position.x+(random.nextFloat()/10)/quadManagement.scaleQuad, planetR.position.y+(random.nextFloat()/10)/quadManagement.scaleQuad);
                                }
                            }
                            if(r == 1){
                                if(addShipIfExistResources(2,planetR)) {
                                   quadManagement.addFreagateShip(planetR, player, planetR.position.x+(random.nextFloat()/10)/quadManagement.scaleQuad, planetR.position.y+(random.nextFloat()/10)/quadManagement.scaleQuad);
                                }
                            }
                            if(r == 2){
                                if(addShipIfExistResources(3,planetR)) {
                                    quadManagement.addDestroierShip(planetR, player, planetR.position.x+(random.nextFloat()/10)/quadManagement.scaleQuad, planetR.position.y+(random.nextFloat()/10)/quadManagement.scaleQuad);
                                }
                            }
                            if(r == 3) {
                                if(addShipIfExistResources(4,planetR))  {
                                    quadManagement.addCarrierShip(planetR, player, planetR.position.x+(random.nextFloat()/10)/quadManagement.scaleQuad, planetR.position.y+(random.nextFloat()/10)/quadManagement.scaleQuad);
                                }
                            }  
                            if(r == 4){
                                if(addShipIfExistResources(5,planetR)) {
                                    quadManagement.addMotherShip(planetR, player, planetR.position.x+(random.nextFloat()/10)/quadManagement.scaleQuad, planetR.position.y+(random.nextFloat()/10)/quadManagement.scaleQuad);
                                }
                            }
                     }                    
                 }
            }
        }
    }
    public boolean addShipIfExistResources(int i , Planet planet){
        boolean done = false;

        if(i == 1) {
            cost = 20;
            if(planet.resources.energyMined >= cost && planet.resources.metalOreMined >= cost && planet.resources.mineralsMined >= cost && planet.resources.carbonMined >= cost && planet.resources.creditMined >= cost && planet.resources.gasMined >= cost){
                setNewResourcesStorege(cost , planet);
                done = true;
            }
        }
        if(i == 2) {
            cost = 30;
            if(planet.resources.energyMined >= cost && planet.resources.metalOreMined >= cost && planet.resources.mineralsMined >= cost && planet.resources.carbonMined >= cost && planet.resources.creditMined >= cost && planet.resources.gasMined >= cost){
                setNewResourcesStorege(cost , planet);
                done = true;
            }
        }
        if(i == 3) {
            cost = 50;
            if(planet.resources.energyMined >= cost && planet.resources.metalOreMined >= cost && planet.resources.mineralsMined >= cost && planet.resources.carbonMined >= cost && planet.resources.creditMined >= cost && planet.resources.gasMined >= cost){
                setNewResourcesStorege(cost , planet);
                done = true;
            }
        }        
        if(i == 4) {
            cost = 200;
            if(planet.resources.energyMined >= cost && planet.resources.metalOreMined >= cost && planet.resources.mineralsMined >= cost && planet.resources.carbonMined >= cost && planet.resources.creditMined >= cost && planet.resources.gasMined >= cost){
                setNewResourcesStorege(cost , planet);
                done = true;
            }
        }    
        if(i == 5) {
            cost = 300;
            if(planet.resources.energyMined >= cost && planet.resources.metalOreMined >= cost && planet.resources.mineralsMined >= cost && planet.resources.carbonMined >= cost && planet.resources.creditMined >= cost && planet.resources.gasMined >= cost){
                setNewResourcesStorege(cost , planet);
                done = true;
            }
        } 
        if(i == 6) {
            cost = 500;
            if(planet.resources.energyMined >= cost && planet.resources.metalOreMined >= cost && planet.resources.mineralsMined >= cost && planet.resources.carbonMined >= cost && planet.resources.creditMined >= cost && planet.resources.gasMined >= cost){
                setNewResourcesStorege(120 , planet);
                done = true;
            }
        }        
        return done;    
    }
    public void setNewResourcesStorege(int cost , Planet p)  {
                p.resources.energyMined   -= cost;
                p.resources.metalOreMined -= cost;
                p.resources.mineralsMined -= cost;
                p.resources.carbonMined   -= cost;
                p.resources.creditMined   -= cost;
                p.resources.gasMined      -= cost;       
    }
    public void  deployCorgoShip(ArrayList<Player>  playerList)
    {
        int max_cargo = 200;
        
        for(Player player : playerList)
        {
             if(player.auto_colect_resources)
             {
                 for(Planet planet : player.cPlanetList)
                 {
                     if(player.planetColector == null)
                     {
                         if (!planet.isWather) {

                             Planet planetT = aIUtil.getClosestWatherColoniPlanet(planet, player);
                             if (planetT != null) {

                                 if (planet.resources.metalOreMined >= max_cargo || planet.resources.mineralsMined >= max_cargo || planet.resources.carbonMined >= max_cargo || planet.resources.gasMined >= max_cargo) {
                                     Quad ship = quadManagement.addCargoShip(planet, player, planet.position.x, planet.position.y);
                                     ship.isSelectebel = false;
                                     ship.metalOreCargo = planet.resources.metalOreMined;
                                     ship.mineralCargo = planet.resources.mineralsMined;
                                     ship.carbonCargo = planet.resources.carbonMined;
                                     ship.gasCargo = planet.resources.gasMined;

                                     planet.resources.metalOreMined = 0;
                                     planet.resources.mineralsMined = 0;
                                     planet.resources.carbonMined = 0;
                                     planet.resources.gasMined = 0;

                                     ship.translateQuad(planetT.position.x, planetT.position.y);
                                 }

                             }
                         }
                     } else {
                             if(planet != player.planetColector)
                             {
                                 if (planet.resources.metalOreMined >= 100 || planet.resources.mineralsMined >= 100 || planet.resources.carbonMined >= 100 || planet.resources.gasMined >= 100) {
                                     Quad ship = quadManagement.addCargoShip(planet, player, planet.position.x, planet.position.y);

                                     ship.isSelectebel = false;
                                     ship.metalOreCargo = planet.resources.metalOreMined;
                                     ship.mineralCargo = planet.resources.mineralsMined;
                                     ship.carbonCargo = planet.resources.carbonMined;
                                     ship.gasCargo = planet.resources.gasMined;

                                     planet.resources.metalOreMined = 0;
                                     planet.resources.mineralsMined = 0;
                                     planet.resources.carbonMined = 0;
                                     planet.resources.gasMined = 0;

                                     ship.translateQuad(player.planetColector.position.x, player.planetColector.position.y);
                                 }
                             }

                     }

                 }
             }
        }

    }
    public void  colonizePlanet(ArrayList<Player>  playerList)   
    {
        for(Player player : playerList)
        {
            if(player.auto_colonizePlanet)
            {
                if(player.cPlanetList.size() < player.maximumColonizePlanet)
                {
                    if(player.coloniShip == null && player.colonizationProcess == false && player.watherPList.size() != 0) {

                         int p = random.nextInt(player.watherPList.size());
                         Planet planetP = player.watherPList.get(p);
                         if(checkResources(planetP , 10))
                         {

                            quadManagement.addColonizationShip(planetP, player, planetP.position.x, planetP.position.y);

                            Planet planetT = null;

                            if (player.cPlanetList.size() < player.minDefenceColonizeCount) {
                                planetT = aIUtil.getClosestColonizebelPlanet(player.coloniShip, sunList,false,playerList);
                                if(planetT == null)
                                {
                                   Sun sun = aIUtil.getClosestUnexploredStar(player.coloniShip , sunList);
                                   if(sun != null) {
                                       player.coloniShip.translateQuad(sun.position.x, sun.position.y);
                                   }
                                }
                            } else {
                                planetT = aIUtil.getClosestEnemyPlanet(planetP, sunList, playerList);
                                if(planetT == null)
                                {
                                    Sun sun = aIUtil.getClosestUnexploredStar(player.coloniShip , sunList);
                                    if(sun != null) {
                                        player.coloniShip.translateQuad(sun.position.x, sun.position.y);
                                    }
                                }
                            }
                            if (planetT != null) {
                                player.coloniShip.translateQuad(planetT.position.x, planetT.position.y);
                                player.colonizationProcess = true;
                            }

                        }
                    
                    }
                    if(player.coloniShip != null && player.colonizationProcess == false ) 
                    {
                        Planet p = null;

                        if(player.cPlanetList.size() == 0){
                             p = aIUtil.getClosestColonizebelPlanet(player.coloniShip, sunList,true,playerList);
                            if(p == null) {
                                Sun sun = aIUtil.getClosestUnexploredStar(player.coloniShip , sunList);
                                if(sun != null) {
                                    player.coloniShip.translateQuad(sun.position.x, sun.position.y);
                                }
                            }
                        } else {
                            p = aIUtil.getClosestColonizebelPlanet(player.coloniShip, sunList,false,playerList);
                            if(p == null) {
                                Sun sun = aIUtil.getClosestUnexploredStar(player.coloniShip , sunList);
                                if(sun != null) {
                                    player.coloniShip.translateQuad(sun.position.x, sun.position.y);
                                }
                            }
                        }

                        if(p != null)
                        {
                            player.coloniShip.translateQuad(p.position.x,p.position.y);
                        }
                            player.colonizationProcess = true;
                        
                    }
                }
            }
        }
    }
    public boolean  checkResources(Planet planetP , int res) {
        if(planetP.resources.energyMined >=res && planetP.resources.metalOreMined >=res && planetP.resources.mineralsMined >=res && planetP.resources.carbonMined >=res && planetP.resources.creditMined >=res && planetP.resources.gasMined >=res) {
            planetP.resources.energyMined -= res;
            planetP.resources.metalOreMined -= res;
            planetP.resources.mineralsMined -= res;
            planetP.resources.carbonMined -= res;
            planetP.resources.creditMined -= res;
            planetP.resources.gasMined -= res;

            return  true;
        } else {
            return false;
        }
    }
}
