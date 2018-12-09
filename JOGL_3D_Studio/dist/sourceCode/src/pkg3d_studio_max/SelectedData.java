
package pkg3d_studio_max;

import java.util.ArrayList;

class  Corp_S  {

    float x_ca;
    float y_ca;
    float z_ca;
    
   Corp_S(float x_ca  ,  float y_ca  ,  float z_ca) {
   
       this.x_ca = x_ca;
       this.y_ca = y_ca;
       this.z_ca = z_ca;
       
   } 
    
    
}
class  Poli_S  {

    float x_pa;
    float y_pa;
    float z_pa;
    
   Poli_S(float x_pa  ,  float y_pa  ,  float z_pa) {
   
       this.x_pa = x_pa;
       this.y_pa = y_pa;
       this.z_pa = z_pa;
       
   } 
    
    
}
class  Edge_S  {

    float x1;
    float y1;
    float z1;
    
    float x2;
    float y2;
    float z2;
    
    float x_ea;
    float y_ea;
    float z_ea;
    
   Edge_S(float x1 , float y1 , float z1 , float x2 , float y2 , float z2 , float x_ea  ,  float y_ea  ,  float z_ea) {
   
       this.x1 = x1;
       this.y1 = y1;
       this.z1 = z1;
       
       this.x2 = x2;
       this.y2 = y2;
       this.z2 = z2;
       
       this.x_ea = x_ea;
       this.y_ea = y_ea;
       this.z_ea = z_ea;
       
   } 
    
    
}
class  Vert_S  {

    float x;
    float y;
    float z;
            
    float x_va;
    float y_va;
    float z_va;
    
   Vert_S(float x, float y, float z, float x_va  ,  float y_va  ,  float z_va) {
   
       this.x = x;
       this.y = y;
       this.z = z;
       
       this.x_va = x_va;
       this.y_va = y_va;
       this.z_va = z_va;
       
   } 
    
    
}


public class SelectedData {
    
    static  ArrayList<Corp_S>  corp_select  =  new ArrayList<Corp_S>();
    static  ArrayList<Poli_S>  poli_select  =  new ArrayList<Poli_S>();
    static  ArrayList<Edge_S>  edge_select  =  new ArrayList<Edge_S>();
    static  ArrayList<Vert_S>  vert_select  =  new ArrayList<Vert_S>();
    
    
    static  void ListareSelecti() {
    
        System.out.println("corp_select = "+corp_select.size());
        System.out.println("poli_select = "+poli_select.size());
        System.out.println("edge_select = "+edge_select.size());
        System.out.println("vert_select = "+vert_select.size());
        System.out.println();
    }
    static  boolean  testSelection () {
    
     boolean ok = true;
     
     if(corp_select.size() == 0 && poli_select.size() == 0 && edge_select.size() == 0 && vert_select.size() == 0) {
         ok = false;
     }
     
         if(ok == true) {
            return true;
         } else {
            return false;
         }
    }
    
}
