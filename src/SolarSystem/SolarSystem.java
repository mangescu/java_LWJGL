
package SolarSystem;

import IBO.IBO;
import Maths.Maths;
import Planet.Planet;
import Planet.Resources;
import Quad.Model;
import Sun.Sun;
import Texture.Textura;
import Util.RandomFloat;

import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.joml.Vector2f;
import org.joml.Vector3f;



public class SolarSystem {
    
    IBO ibo;
    Textura tex;
    Model model;
    
    Sun sun;
    Planet planet;
    ArrayList<Planet>  pList;
   
             
    DecimalFormat df2 = new DecimalFormat("#.##"); 
    DecimalFormat df3 = new DecimalFormat("#.###"); 
    RandomFloat rand = new RandomFloat();
    Random random = new Random();
    
        public ArrayList<Integer>  lavaPlanet   =  new ArrayList<Integer>();
        public ArrayList<Integer>  aridPlanet   =  new ArrayList<Integer>();
        public ArrayList<Integer>  SAridPlanet   =  new ArrayList<Integer>();
        public ArrayList<Integer>  watherPlanet   =  new ArrayList<Integer>();        
        public ArrayList<Integer>  icePlanet   =  new ArrayList<Integer>();   
        
        public ArrayList<Vector2f>  starsCoordonateList   =  new ArrayList<Vector2f>();

    public ArrayList<Integer>  planetRandAngle   =  new ArrayList<Integer>();
    public int planetAngleSpacer;

    Maths math;     
        
    float sunZ = 0.0001f;
    public float planetZ = 0.0002f;
    float deltaSun = 0.000001f;
    
    int star_index = 0;
    
    int watherMaxShipCount = 1;

    
    public SolarSystem(IBO  ibo , Textura tex , Maths math){
        this.ibo = ibo;
        this.tex = tex;
        this.math = math;
    }
    public boolean checkStarSpecer(float x , float y)
    {
      boolean ok = true;
      
      for(Vector2f vector2 : starsCoordonateList)
      {
         float distance  = math.calcPointsPointDistance(x , y , vector2);
         if(distance < 1f) {
           ok = false;
         }
        
      }
    return ok;
    }
    public void  generateStars(int starCount , List<Sun> sunList , int minRadius , int maxRadius,int race_index){
    

    star_index =0;
    float delta_x = 0.0f;

        for(float i= 9 ; i > -9 ; i = i -1.5f) {

            for (float j = -9; j <  9 ; j = j + 1.5f) {



                sun = new Sun();
                sun.position = new Vector3f(i + delta_x, j , sunZ);

                model = new Model(ibo, tex, 0, sun.position, new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
                model.INDEX_T = 7;

                float scale = Float.valueOf(df3.format(rand.getFloatRange(0.1f, 0.99f))) / 15;
                model.scale.x = scale;
                model.scale.y = scale;
                model.scale.z = scale;
                sun.radius = model.scale.x;

                sun.radius = model.scale.x;

                rand.floats.clear();
                rand.starSpacer = Float.valueOf(df3.format(sun.radius * 2));
                // rand.starSpacer = 0;

                sunZ += deltaSun;

                if (sun.radius >= (0.1f / 15) && sun.radius < (0.3f / 15)) {
                    int h = random.nextInt(3);
                    if (h == 0) {
                        sun.starType = "whiteDwarf";
                        model.INDEX_T = 3;
                    } else {
                        sun.starType = "redDwarf";
                        model.INDEX_T = 1;
                    }
                    rand.planetsSpacer = 0.015f;
                }
                if (sun.radius >= (0.3f / 15) && sun.radius < (0.6f / 15)) {
                    sun.starType = "yellow";
                    model.INDEX_T = 0;
                    rand.planetsSpacer = 0.0225f;
                }
                if (sun.radius >= (0.6f / 15) && sun.radius < (0.8f / 15)) {
                    sun.starType = "blueGiant";
                    model.INDEX_T = 2;
                    rand.planetsSpacer = 0.0325f;
                }
                if (sun.radius >= (0.8f / 15)) {
                    sun.starType = "redGiant";
                    model.INDEX_T = 1;
                    rand.planetsSpacer = 0.035f;
                }
                sun.model = model;

                sun.listaPlanete = new ArrayList<Planet>();

                lavaPlanet.clear();
                aridPlanet.clear();
                SAridPlanet.clear();
                watherPlanet.clear();
                icePlanet.clear();

                int count = 0;
                while (count == 0) {
                    count = random.nextInt(6);
                }
                planetRandAngle.clear();
                planetAngleSpacer = 360 / count;
                for (int p = 0; p < count; p++) {
                    planet = generatePlanet(sun);
                    sun.listaPlanete.add(planet);
                }


                sun.exploredList.set(race_index, true);

                float distance = (float) Math.sqrt(Math.pow(sun.position.x, 2) + Math.pow(sun.position.y, 2));
                
                if(distance < maxRadius && distance > minRadius) 
                {
                    sun.star_index = star_index;
                    sunList.add(sun);
                    star_index++;
                }


                if (delta_x == 0.75) {
                    delta_x = 0;
                } else {
                    delta_x = 0.75f;
                }

            }

        }

    }


    public Planet  generatePlanet(Sun sun)
    {
        planet = new Planet();
        planet.parentStar = sun;
        
        
        planet.position = new Vector3f();
        
         planet.spacer = sun.radius;
         planet.startP =rand.startP;
         planet.planetsSpacer = rand.planetsSpacer;        
        
        model = new Model(ibo, tex, 0, planet.position , new Vector3f(0,0,0) , new Vector3f(1,1,1));
        
        
        if(sun.starType.equalsIgnoreCase("redDwarf"))
        {
            float scale = rand.getFloatRange(0.1f, 0.3f)/100;
            model.scale.x = scale;
            model.scale.y = scale;
            model.scale.z = scale;
            planet.radius = model.scale.x;
        }        
        if(sun.starType.equalsIgnoreCase("whiteDwarf"))
        {
            float scale = rand.getFloatRange(0.1f, 0.3f)/100;
            model.scale.x = scale;
            model.scale.y = scale;
            model.scale.z = scale;
            planet.radius = model.scale.x;
        }        
        if(sun.starType.equalsIgnoreCase("yellow"))
        {
            float scale = rand.getFloatRange(0.2f, 0.4f)/100;
            model.scale.x = scale;
            model.scale.y = scale;
            model.scale.z = scale;
            planet.radius = model.scale.x;
        }        
        if(sun.starType.equalsIgnoreCase("blueGiant"))
        {
            float scale = rand.getFloatRange(0.2f, 0.6f)/100;
            model.scale.x = scale;
            model.scale.y = scale;
            model.scale.z = scale;
            planet.radius = model.scale.x;
        }
        if(sun.starType.equalsIgnoreCase("redGiant"))
        {
            float scale = rand.getFloatRange(0.3f, 0.7f)/100;
            model.scale.x = scale;
            model.scale.y = scale;
            model.scale.z = scale;
            planet.radius = model.scale.x;
        }

        model.INDEX_T  = 7;


    boolean ok = true;
    boolean done = true;
    while(ok) {
        done = true;
        planet.angleOrbit = random.nextInt(360);
        for (int i = 0; i < planetRandAngle.size(); i++) {
            if (Math.abs(planet.angleOrbit - planetRandAngle.get(i)) < (planetAngleSpacer*0.01f))
            {
                 done = false;
            }
        }
        if(done)
        {
            ok = false;
        }
    }
        planetRandAngle.add(planet.angleOrbit);

       // planet.angleOrbit = 0;
        planet.angleRotate =  planet.angleOrbit;

        planet.starDistance = rand.generateRandomDistance();

        
        float x = sun.position.x + (float)  (planet.starDistance  *  Math.cos(Math.toRadians(planet.angleOrbit)));
        float y = sun.position.y + (float)  (planet.starDistance  *  Math.sin(Math.toRadians(planet.angleOrbit)));

        planet.position.x = x;
        planet.position.y = y;
        planet.position.z = planetZ;

        
        
             float distanta = planet.starDistance;

             float spacer        = rand.starSpacer;
             float startP        = rand.startP;
             float planetsSpacer = rand.planetsSpacer;

         
        if(sun.starType.equalsIgnoreCase("redDwarf"))  {
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 0) +spacer)))   {    
                    genAridPlanetTex(model,planet);
                }
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 1) +spacer)))   {    
                    genSAridPlanetTex(model,planet);
                }   
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 2) +spacer)))   {    
                    genWatherPlanet(model,planet);
                }  
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 3) +spacer)))   {    
                    genIcePlanet(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 4) +spacer)))   {    
                    genIcePlanet(model,planet);
                }  
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 5) +spacer)))   {    
                    genIcePlanet(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 6) +spacer)))   {    
                    genIcePlanet(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 7) +spacer)))  {    
                    genIcePlanet(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 8) +spacer)))   {    
                    genIcePlanet(model,planet);
                }
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 9) +spacer)))  {    
                    genIcePlanet(model,planet);     
                }    
        }
        if(sun.starType.equalsIgnoreCase("whiteDwarf"))  {
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 0) +spacer)))   {    
                    genAridPlanetTex(model,planet);
                }
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 1) +spacer)))   {    
                    genAridPlanetTex(model,planet);
                }   
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 2) +spacer)))   {    
                    genAridPlanetTex(model,planet);
                }  
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 3) +spacer)))   {    
                    genAridPlanetTex(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 4) +spacer)))    {    
                    genIcePlanet(model,planet);
                }  
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 5) +spacer)))   {    
                    genIcePlanet(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 6) +spacer)))    {    
                    genIcePlanet(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 7) +spacer)))    {    
                    genIcePlanet(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 8) +spacer)))    {    
                    genIcePlanet(model,planet);
                }
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 9) +spacer)))    {    
                    genIcePlanet(model,planet);     
                }    
        }   
        if(sun.starType.equalsIgnoreCase("yellow"))  {
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 0) +spacer)))    {    
                    genLavaWorldTex(model,planet);
                }
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 1) +spacer)))   {    
                    genAridPlanetTex(model,planet);
                }   
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 2) +spacer)))   {    
                    genAridPlanetTex(model,planet);
                }  
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 3) +spacer)))   {    
                    genSAridPlanetTex(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 4) +spacer)))    {    
                    genSAridPlanetTex(model,planet);
                }  
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 5) +spacer)))   {    
                    genWatherPlanet(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 6) +spacer)))     {    
                    genWatherPlanet(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 7) +spacer)))     {
                    genIcePlanet(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 8) +spacer)))    {    
                    genIcePlanet(model,planet);
                }
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 9) +spacer)))    {    
                    genIcePlanet(model,planet);     
                }    
        } 
        if(sun.starType.equalsIgnoreCase("blueGiant"))  {
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 0) +spacer)))    {    
                    genLavaWorldTex(model,planet);
                }
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 1) +spacer)))    {    
                    genAridPlanetTex(model,planet);
                }   
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 2) +spacer)))    {    
                    genAridPlanetTex(model,planet);
                }  
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 3) +spacer)))   {    
                    genSAridPlanetTex(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 4) +spacer)))    {    
                    genSAridPlanetTex(model,planet);
                }  
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 5) +spacer)))    {    
                    genSAridPlanetTex(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 6) +spacer)))    {    
                    genSAridPlanetTex(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 7) +spacer)))    {    
                    genSAridPlanetTex(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 8) +spacer)))    {    
                    genWatherPlanet(model,planet);
                }
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 9) +spacer)))   {    
                    genIcePlanet(model,planet);     
                }    
        }  
        if(sun.starType.equalsIgnoreCase("redGiant")) {
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 0) +spacer)))     {    
                    genLavaWorldTex(model,planet);
                }
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 1) +spacer)))    {    
                    genLavaWorldTex(model,planet);
                }   
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 2) +spacer)))    {    
                    genLavaWorldTex(model,planet);
                }  
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 3) +spacer)))   {    
                    genAridPlanetTex(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 4) +spacer)))    {    
                    genAridPlanetTex(model,planet);
                }  
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 5) +spacer)))    {    
                    genAridPlanetTex(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 6) +spacer)))   {    
                    genAridPlanetTex(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 7) +spacer)))    {    
                    genAridPlanetTex(model,planet);
                } 
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 8) +spacer)))   {    
                    genSAridPlanetTex(model,planet);
                }
                if(distanta ==Float.valueOf(df3.format(startP + (planetsSpacer * 9) +spacer)))   {
                    genWatherPlanet(model,planet);
                }    
        }        
        planet.model = model;
   
     return planet;   
    }
    
    public void genLavaWorldTex(Model quadP , Planet planet) {
           boolean done = false;
           planet.isVulcanic = true;
           planet.classPlanet = 1;
           generateResources(planet , 0 , 5);
           while(!done)
           {           
                done = false;
                int randNumber = random.nextInt(6);

                if(!lavaPlanet.contains(randNumber) && randNumber == 0)
                {
                   quadP.INDEX_T = 4;
                   lavaPlanet.add(randNumber);
                   done = true;
                }
                if(!lavaPlanet.contains(randNumber) && randNumber == 1)
                {
                   quadP.INDEX_T = 5;
                   lavaPlanet.add(randNumber);
                   done = true;
                }
                if(!lavaPlanet.contains(randNumber) && randNumber == 2)
                {
                   quadP.INDEX_T = 6;
                   lavaPlanet.add(randNumber);
                   done = true;
                } 
                if(!lavaPlanet.contains(randNumber) && randNumber == 3)
                {
                   quadP.INDEX_T = 7;
                   lavaPlanet.add(randNumber);
                   done = true;
                }
                if(!lavaPlanet.contains(randNumber) && randNumber == 4)
                {
                   quadP.INDEX_T = 10;
                   lavaPlanet.add(randNumber);
                   done = true;
                }                
                if(!lavaPlanet.contains(randNumber) && randNumber == 5)
                {
                   quadP.INDEX_T = 11;
                   lavaPlanet.add(randNumber);
                   done = true;
                }                   
           }
            
    }
    public void genAridPlanetTex(Model quadP ,Planet planet) {
           boolean done = false;
           planet.isArid = true;
           planet.classPlanet = 2;
           generateResources(planet , 0 , 5);
           while(!done)
           {           
                done = false;
                int randNumber = random.nextInt(9);

                if(!aridPlanet.contains(randNumber) && randNumber == 0)
                {
                   quadP.INDEX_T = 12;
                   aridPlanet.add(randNumber);
                   done = true;
                }
                if(!aridPlanet.contains(randNumber) && randNumber == 1)
                {
                   quadP.INDEX_T = 13;
                   aridPlanet.add(randNumber);
                   done = true;
                }
                if(!aridPlanet.contains(randNumber) && randNumber == 2)
                {
                   quadP.INDEX_T = 14;
                   aridPlanet.add(randNumber);
                   done = true;
                } 
                if(!aridPlanet.contains(randNumber) && randNumber == 3)
                {
                   quadP.INDEX_T = 15;
                   aridPlanet.add(randNumber);
                   done = true;
                }                
                if(!aridPlanet.contains(randNumber) && randNumber == 4)
                {
                   quadP.INDEX_T = 16;
                   aridPlanet.add(randNumber);
                   done = true;
                } 
                if(!aridPlanet.contains(randNumber) && randNumber == 5)
                {
                   quadP.INDEX_T = 17;
                   aridPlanet.add(randNumber);
                   done = true;
                } 
                if(!aridPlanet.contains(randNumber) && randNumber == 6)
                {
                   quadP.INDEX_T = 20;
                   aridPlanet.add(randNumber);
                   done = true;
                }  
                if(!aridPlanet.contains(randNumber) && randNumber == 7)
                {
                   quadP.INDEX_T = 21;
                   aridPlanet.add(randNumber);
                   done = true;
                }  
                if(!aridPlanet.contains(randNumber) && randNumber == 8)
                {
                   quadP.INDEX_T = 22;
                   aridPlanet.add(randNumber);
                   done = true;
                }

           }    
    }
    
    public void genSAridPlanetTex(Model quadP,Planet planet) {
           boolean done = false;
           planet.isSemiarid = true;
           planet.classPlanet = 1;
           generateResources(planet , 0 , 5);
           while(!done)
           {           
                done = false;
                int randNumber = random.nextInt(11);

                if(!SAridPlanet.contains(randNumber) && randNumber == 0)
                {
                   quadP.INDEX_T = 23;
                   SAridPlanet.add(randNumber);
                   done = true;
                }
                if(!SAridPlanet.contains(randNumber) && randNumber == 1)
                {
                   quadP.INDEX_T = 24;
                   SAridPlanet.add(randNumber);
                   done = true;
                }
                if(!SAridPlanet.contains(randNumber) && randNumber == 2)
                {
                   quadP.INDEX_T = 25;
                   SAridPlanet.add(randNumber);
                   done = true;
                } 
                if(!SAridPlanet.contains(randNumber) && randNumber == 3)
                {
                   quadP.INDEX_T = 26;
                   SAridPlanet.add(randNumber);
                   done = true;
                }                
                if(!SAridPlanet.contains(randNumber) && randNumber == 4)
                {
                   quadP.INDEX_T = 27;
                   SAridPlanet.add(randNumber);
                   done = true;
                } 
                if(!SAridPlanet.contains(randNumber) && randNumber == 5)
                {
                   quadP.INDEX_T = 30;
                   SAridPlanet.add(randNumber);
                   done = true;
                } 
                if(!SAridPlanet.contains(randNumber) && randNumber == 6)
                {
                   quadP.INDEX_T = 31;
                   SAridPlanet.add(randNumber);
                   done = true;
                }
               if(!SAridPlanet.contains(randNumber) && randNumber == 7)
               {
                   quadP.INDEX_T = 55;
                   SAridPlanet.add(randNumber);
                   done = true;
               }
               if(!SAridPlanet.contains(randNumber) && randNumber == 8)
               {
                   quadP.INDEX_T = 56;
                   SAridPlanet.add(randNumber);
                   done = true;
               }
               if(!SAridPlanet.contains(randNumber) && randNumber == 9)
               {
                   quadP.INDEX_T = 28;
                   SAridPlanet.add(randNumber);
                   done = true;
               }
               if(!SAridPlanet.contains(randNumber) && randNumber == 10)
               {
                   quadP.INDEX_T = 18;
                   SAridPlanet.add(randNumber);
                   done = true;
               }
           }    
    }  
    public void genWatherPlanet(Model quadP,Planet planet) {
           boolean done = false;
           planet.isWather = true;
           planet.classPlanet = 0;
           planet.planetMaxShipCount = watherMaxShipCount;
           generateResources(planet , 1 , 3);
           while(!done)
           {           
                done = false;
                int randNumber = random.nextInt(12);

                if(!SAridPlanet.contains(randNumber) && randNumber == 0)
                {
                   quadP.INDEX_T = 32;
                   SAridPlanet.add(randNumber);
                   done = true;
                }                  
                if(!watherPlanet.contains(randNumber) && randNumber == 1)
                {
                   quadP.INDEX_T = 33;
                   watherPlanet.add(randNumber);
                   done = true;
                }
                if(!watherPlanet.contains(randNumber) && randNumber == 2)
                {
                   quadP.INDEX_T = 34;
                   watherPlanet.add(randNumber);
                   done = true;
                }
                if(!watherPlanet.contains(randNumber) && randNumber == 3)
                {
                   quadP.INDEX_T = 35;
                   watherPlanet.add(randNumber);
                   done = true;
                } 
                if(!watherPlanet.contains(randNumber) && randNumber == 4)
                {
                   quadP.INDEX_T = 36;
                   watherPlanet.add(randNumber);
                   done = true;
                }                
                if(!watherPlanet.contains(randNumber) && randNumber == 5)
                {
                   quadP.INDEX_T = 37;
                   watherPlanet.add(randNumber);
                   done = true;
                } 
                if(!watherPlanet.contains(randNumber) && randNumber == 6)
                {
                   quadP.INDEX_T = 40;
                   watherPlanet.add(randNumber);
                   done = true;
                } 
                if(!watherPlanet.contains(randNumber) && randNumber == 7)
                {
                   quadP.INDEX_T = 41;
                   watherPlanet.add(randNumber);
                   done = true;
                }  
                if(!watherPlanet.contains(randNumber) && randNumber == 8)
                {
                   quadP.INDEX_T = 42;
                   watherPlanet.add(randNumber);
                   done = true;
                }
               if(!watherPlanet.contains(randNumber) && randNumber == 9)
               {
                   quadP.INDEX_T = 52;
                   watherPlanet.add(randNumber);
                   done = true;
               }
               if(!watherPlanet.contains(randNumber) && randNumber == 10)
               {
                   quadP.INDEX_T = 53;
                   watherPlanet.add(randNumber);
                   done = true;
               }
               if(!watherPlanet.contains(randNumber) && randNumber == 11)
               {
                   quadP.INDEX_T = 54;
                   watherPlanet.add(randNumber);
                   done = true;
               }
           }    
    }    
    public void genIcePlanet(Model quadP , Planet planet) {
           boolean done = false;
           planet.isIce = true;
           planet.classPlanet = 3;
           generateResources(planet , 0 , 2);
           while(!done)
           {           
                done = false;
                int randNumber = random.nextInt(7);

                if(!icePlanet.contains(randNumber) && randNumber == 0)
                {
                   quadP.INDEX_T = 44;
                   icePlanet.add(randNumber);
                   done = true;
                }
                if(!icePlanet.contains(randNumber) && randNumber == 1)
                {
                   quadP.INDEX_T = 45;
                   icePlanet.add(randNumber);
                   done = true;
                }
                if(!icePlanet.contains(randNumber) && randNumber == 2)
                {
                   quadP.INDEX_T = 46;
                   float scale = rand.getFloatRange(0.6f, 0.8f)/600;;
                   model.scale.x = scale; 
                   model.scale.y = scale;
                   model.scale.z = scale;
                   icePlanet.add(randNumber);
                   done = true;
                } 
                if(!icePlanet.contains(randNumber) && randNumber == 3)
                {
                   quadP.INDEX_T = 47;
                   icePlanet.add(randNumber);
                   done = true;
                }                
                if(!icePlanet.contains(randNumber) && randNumber == 4)
                {
                   quadP.INDEX_T = 50;
                   icePlanet.add(randNumber);
                   done = true;
                } 
                if(!icePlanet.contains(randNumber) && randNumber == 5)
                {
                   quadP.INDEX_T = 51;
                   icePlanet.add(randNumber);
                   done = true;
                } 
                if(!icePlanet.contains(randNumber) && randNumber == 6)
                {
                   quadP.INDEX_T = 43;
                   icePlanet.add(randNumber);
                   done = true;
                }  

           }    
    }
    public void generateResources(Planet planet , int start , int delta)
    {
        int  energy = 0;
        int  metalOre = 0;
        int  mineral = 0;
        int  carbon = 0;
        int  gas = 0;

        if(planet.isVulcanic) {
            planet.resources.biomas = 10;
            metalOre = 5;
        }
        if(planet.isArid) {
            planet.resources.biomas = 1+ random.nextInt(2);
        }
        if(planet.isSemiarid) {
            planet.resources.biomas = 1+ random.nextInt(3);
            mineral = 5;
        }
        if(planet.isWather) {
            planet.resources.biomas =  5 +random.nextInt(6);
            carbon = 5;
        }
        if(planet.isIce){
            planet.resources.biomas =  1 +random.nextInt(2);
            gas = 5;
        }
        planet.resources.maxPopulation = planet.resources.biomas * 10;

        planet.resources.energy   = start + random.nextInt(delta+energy);
        planet.resources.metalOre = start + random.nextInt(delta+metalOre);
        planet.resources.minerals = start + random.nextInt(delta+mineral);
        planet.resources.carbon   = start + random.nextInt(delta+carbon);
        planet.resources.gas      = start + random.nextInt(delta+gas);


    }
}
