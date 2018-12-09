/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class Node
{
   float x;
   float y;
   
   int startCheck;
   
   
   float width;

   byte[] index; 
}

public class SmartColision {

    ArrayList<Node>   lista  =  new ArrayList<Node>();
    Node node;
    Random rand = new Random();
    
    int rInt; 
    float rFloat;
    
    static int checkCount;
    ArrayList<Integer> number;
    
//______FPS___________________________________________________________//
    static double start = System.nanoTime() / 1000000000;
    static float senzitiviti = 0.2f;
    static int valoare = 0;
    static double end;
//____________________________________________________________________// 
    
    
    
    public static void main(String[] args) {
     
    	SmartColision col = new SmartColision();
                        col.genRandomNode();
        
        
     while(true) {
         
    // FPS____________________________________________________________________//           
            end = System.nanoTime() / 1000000000;
            if (end - start > 1) {
                start = end;
                //System.out.println(valoare / 2+"   "+checkCount);
                valoare = 0;
            }
            valoare++;
   // __________________________________________________________________________// 
       checkCount = 0;

 //      col.checkBruteForceColizion();
 //  col.checkModuleColizion_1();
//   col.checkModuleColizion_2();
      col.testLoopTipe();
     }   
        
        
        
        
    }
public int testLoopTipe() {
int max = 0;
    
        /*   
            int max = Integer.MIN_VALUE;
            for (Iterator<Integer> it = number.iterator(); it.hasNext(); ) {
            max = Integer.max(max, it.next());
            }
        */


        /*
        int max = Integer.MIN_VALUE;
        for (Integer n : number) {
        max = Integer.max(max, n);
        }
        */



        /*
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < number.size(); i++) {
            max = Integer.max(max, number.get(i));
        }
        */
        
       /* 
        int i = 0;
        int max = Integer.MIN_VALUE;
        while(i < number.size()) {
            max = Integer.max(max, number.get(i));
            i++;       
        }
      */
    return max;
}
    public void checkModuleColizion_2() {
        
        float min=0;
        


       for(int i=0 ; i<lista.size() ; i++) {
           for(int j=0 ; j< lista.size() ; j++) {
    
                   float distance =0;
                  
                   if(Math.abs(lista.get(i).x - lista.get(j).x) > 1 || Math.abs(lista.get(i).y - lista.get(j).y) >1){    
                   } else {
                         //    distance = Math.abs(lista.get(i).x - lista.get(j).x) +  Math.abs(lista.get(i).y - lista.get(j).y);
                     
                             checkCount++;
                          }

           }
  
       }   
       
    }     
     public void checkModuleColizion_1() {
        float distance =0;
        Node node1,node2; 
        for(int i=0 ; i<lista.size() ; i++) {
        	node1 =  lista.get(i);          
            for(int j=0 ; j< lista.size() ; j++) {
            	    node2 =  lista.get(i);
                   
                   
                    if(Math.abs(node1.x - node2.x) > 1 || Math.abs(node1.y - node2.y) >1){    
                    } else {
                              distance = Math.abs(node1.x - node2.x) +  Math.abs(node1.y - node2.y);

                                 checkCount++;    
                           }
            }
          
        }   
        
     }    
     public void checkBruteForceColizion() {
         
         float min=0;
         float distance =0;
         
        for(int i=0 ; i<lista.size() ; i++) {
             
            for(int j=0 ; j< lista.size() ; j++) {
                
                 
                    distance  = (float) Math.sqrt(Math.pow(lista.get(i).x - lista.get(j).x, 2) + Math.pow(lista.get(i).y - lista.get(j).y, 2));
                
                    checkCount++;
    
            }

        }   
        
     }

   public void genRandomNode() {
Random rand = new Random();
    number = new ArrayList<Integer>();
    for(int i=0 ; i < 100000 ; i++) {
        number.add(rand.nextInt(1000000));
    }       
       
       
       
        for(int i=0 ; i<10000;i++){

            node = new Node(); 
            
            rInt = rand.nextInt(100); 
            rFloat = rand.nextFloat();
            node.x = rInt + rFloat;
            
            rInt = rand.nextInt(100);            
            rFloat = rand.nextFloat();           
            node.y = rInt + rFloat;
            
            node.width = 0.5f;

         
            lista.add(node);
        }
        
     
   } 
}


