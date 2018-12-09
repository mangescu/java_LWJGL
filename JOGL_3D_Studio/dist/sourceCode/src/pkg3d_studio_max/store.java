
package pkg3d_studio_max;

import java.util.ArrayList;

class Polii {

    Poligon poli;
    int poz;
    
    public Polii(){
    }
        
    public Polii(Poligon poli , int poz){
    
        this.poli = poli;
        this.poz = poz;        
    }

}


public class store {
 
static boolean execute = true; 

  

static ArrayList<Poligon>   listaPoligoaneSelectate = new ArrayList<Poligon>();
static Poligon   poligon;
static ArrayList<Polii>   poli= new ArrayList<Polii>();

static Polii     p;
static ArrayList<Edge>   listaMuchi = new ArrayList<Edge>();
static ArrayList<Vertex>   listaVert ;
static ArrayList<Vertex>   edgeList ;
static Vertex  temp_1;
static Vertex  temp_2;

static int curentP;
static boolean  ok_1 = false;
static boolean  ok_2 = false;
static boolean  ok_3 = false;
static boolean  status;
static boolean TexturesStatus = false;

static void extrude(String axis , String move_direction , float value) {

    if(axis.equals("x"))
    {
       if(execute == true)
       {
             getSelectedPoligons();
             selectPoligoaneNepartajate();
             setPoligons();
             setEdges();
             
              print();
             
             removePoligons();
             removeEdes();
         execute = false; 
       }
       if(move_direction.equals("increse")) 
       {
             for(int i=0;i<poli.size();i++)
            {
                for(int k=0;k<poli.get(i).poli.vertex_list.size();k++)
                {   
                   poli.get(i).poli.vertex_list.get(k).x+=value;
                }
            }
       }
       if(move_direction.equals("decrese")) 
       {
            for(int i=0;i<poli.size();i++)
            {
                for(int k=0;k<poli.get(i).poli.vertex_list.size();k++)
                {   
                   poli.get(i).poli.vertex_list.get(k).x-=value;
                }
            }
       }
       
    }

    if(axis.equals("y"))
    {
       if(execute == true)
       {
             getSelectedPoligons();
             selectPoligoaneNepartajate();
             setPoligons();
             setEdges();
             
              print();
              
             removePoligons();
             removeEdes();
         execute = false; 
       }
       if(move_direction.equals("increse")) 
       {
             for(int i=0;i<poli.size();i++)
            {
                for(int k=0;k<poli.get(i).poli.vertex_list.size();k++)
                {   
                   poli.get(i).poli.vertex_list.get(k).y+=value;
                }
            }
       }
       if(move_direction.equals("decrese")) 
       {
            for(int i=0;i<poli.size();i++)
            {
                for(int k=0;k<poli.get(i).poli.vertex_list.size();k++)
                {   
                   poli.get(i).poli.vertex_list.get(k).y-=value;
                }
            }
       }
       
    }
 
    if(axis.equals("z"))
    {
       if(execute == true)
       {
             getSelectedPoligons();
             selectPoligoaneNepartajate();                         
             setPoligons();
             setEdges();
            
             print();
             
             removePoligons();
             removeEdes();
         
         execute = false; 
       }
       if(move_direction.equals("increse")) 
       {
             for(int i=0;i<poli.size();i++)
            {
                for(int k=0;k<poli.get(i).poli.vertex_list.size();k++)
                {   
                   poli.get(i).poli.vertex_list.get(k).z+=value;
                }
            }
       }
       if(move_direction.equals("decrese")) 
       {
            for(int i=0;i<poli.size();i++)
            {
                for(int k=0;k<poli.get(i).poli.vertex_list.size();k++)
                {   
                   poli.get(i).poli.vertex_list.get(k).z-=value;
                }
            }
       }
       
    }
    
    
    
reset();
Normal3D.resetNVector();
}
static void print() {

    for(int i=0;i<listaPoligoaneSelectate.size();i++)
    {
        System.out.println("Poligon_selectat["+i+"]");
        System.out.println();
        for(int k=0;k<listaPoligoaneSelectate.get(i).vertex_list.size();k++)
        {   
             System.out.println(listaPoligoaneSelectate.get(i).vertex_list.get(k).x+"    "+listaPoligoaneSelectate.get(i).vertex_list.get(k).y+"    "+listaPoligoaneSelectate.get(i).vertex_list.get(k).z);
        }
    }
    
    System.out.println();
    System.out.println();
    System.out.println();
    
    if(listaMuchi.size() == 0)
    {
        System.out.println("nu exista muchi comune");
    }
    for(int i=0;i<listaMuchi.size();i++)
    {
        System.out.println("Muchie_comuna ["+i+"]");
        for(int k=0;k<listaMuchi.get(i).vertex_list.size();k++)
        {
             System.out.println(listaMuchi.get(i).vertex_list.get(k).x+"   "+listaMuchi.get(i).vertex_list.get(k).y+"   "+listaMuchi.get(i).vertex_list.get(k).z);        
        }
    }

}
static void removeEdes() {

  
 ee:    for(int i=0 ; i< listaMuchi.size() ; i++)
        {
           for(int j=0 ; j<Data.final_cube_edge_array.size() ; j++)
           {
               if(Data.final_cube_edge_array.get(j).vertex_list.get(0) == listaMuchi.get(i).vertex_list.get(0)  &&  Data.final_cube_edge_array.get(j).vertex_list.get(1) == listaMuchi.get(i).vertex_list.get(1)     ||    Data.final_cube_edge_array.get(j).vertex_list.get(0) == listaMuchi.get(i).vertex_list.get(1)  &&  Data.final_cube_edge_array.get(j).vertex_list.get(1) == listaMuchi.get(i).vertex_list.get(0))
               {
                   listaMuchi.remove(i);
                   Data.final_cube_edge_array.remove(j);
                  
                 
                   i--;
                   j--;
                   
                     if(i == -1)
                         i++;
                     if(j == -1)
                         j++;      
                     
                   continue  ee;
               }
           }
           
        }
    
    
}


static void removePoligons() {

for(int i=0;i<Data.final_entity.corp_list.size();i++)
{
   for(int j=0;j<Data.final_entity.corp_list.get(i).poligon_list.size();j++)
   {
           for(int p=0;p<listaPoligoaneSelectate.size();p++)
           {
               if(listaPoligoaneSelectate.get(p).equals(Data.final_entity.corp_list.get(i).poligon_list.get(j)))
               {
                  Data.final_entity.corp_list.get(i).poligon_list.remove(j);
                  j--;
               }
           }
   }
}
    
}



static void setEdges() {

    for(int p=0;p<poli.size();p++)
    {
        for(int k=0 ; k<poli.get(p).poli.vertex_list.size() ; k++)
        {   
            edgeList = new ArrayList<Vertex>();
            
            if(k < poli.get(p).poli.vertex_list.size()-1)
            {
                  temp_1 = new Vertex();
                  temp_1 =  poli.get(p).poli.vertex_list.get(k);
                  edgeList.add(temp_1);
                             
                  temp_1 = new Vertex();
                  temp_1 =  poli.get(p).poli.vertex_list.get(k+1);    
                  edgeList.add(temp_1);  
           
                  Cube.b_e+=0.01f;       
                  Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e),edgeList));         
            }
            else {
                  temp_1 = new Vertex();
                  temp_1 =  poli.get(p).poli.vertex_list.get(k);
                  edgeList.add(temp_1);
                             
                  temp_1 = new Vertex();
                  temp_1 =  poli.get(p).poli.vertex_list.get(0);    
                  edgeList.add(temp_1);  
           
                  Cube.b_e+=0.01f;       
                  Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e),edgeList));         
                    
            }
        }              
     }
}
static void setPoligons() {

    
    for(int i=0;i<listaPoligoaneSelectate.size();i++)
    {
        listaVert = new ArrayList();
        for(int k=0;k<listaPoligoaneSelectate.get(i).vertex_list.size();k++)
        {
            edgeList = new ArrayList<Vertex>();
            
          Cube.r_v+=0.001;
          temp_1 = new Vertex(new C_ambient(Cube.r_v, Cube.g_v, Cube.b_v) ,0 , 0, 0);

          
          temp_1.x = listaPoligoaneSelectate.get(i).vertex_list.get(k).x;
          temp_1.y = listaPoligoaneSelectate.get(i).vertex_list.get(k).y;
          temp_1.z = listaPoligoaneSelectate.get(i).vertex_list.get(k).z;

          
          temp_1.x+=0.01f;
          
          listaVert.add(temp_1);
          edgeList.add(temp_1);
      
        if(k < listaPoligoaneSelectate.get(i).vertex_list.size()-1)
        {
              temp_2 = new Vertex(new C_ambient(Cube.r_v, Cube.g_v, Cube.b_v) ,0 , 0, 0); 
              
                  temp_2.x = listaPoligoaneSelectate.get(i).vertex_list.get(k+1).x;
                  temp_2.y = listaPoligoaneSelectate.get(i).vertex_list.get(k+1).y;
                  temp_2.z = listaPoligoaneSelectate.get(i).vertex_list.get(k+1).z;
            temp_2.x+=0.01f;

        } else  {
              temp_2 = new Vertex(new C_ambient(Cube.r_v, Cube.g_v, Cube.b_v) ,0 , 0, 0); 
              
                  temp_2.x = listaPoligoaneSelectate.get(i).vertex_list.get(0).x;
                  temp_2.y = listaPoligoaneSelectate.get(i).vertex_list.get(0).y;
                  temp_2.z = listaPoligoaneSelectate.get(i).vertex_list.get(0).z;
             temp_2.x+=0.01f;
              

        }      

          Data.final_cube_vertex_array.add(temp_1);
        }
        
     poligon = new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p) , new NormalV(0 , 0 ,1) , listaVert , TexturesStatus , null , null); 
     Cube.b_p+=0.01f;
     Cube.temp_poligon.add(poligon);
     
     p = new Polii();   
     p.poz = i;
     p.poli = poligon;
     poli.add(p);
    }
    
    
    
    
    for(int i=0;i<listaPoligoaneSelectate.size();i++)
    {
        for(int k=0;k<listaPoligoaneSelectate.get(i).vertex_list.size();k++)
        {
            edgeList = new ArrayList<Vertex>();
            
              if(k < listaPoligoaneSelectate.get(i).vertex_list.size()-1)
              {
                   status =  test1(listaPoligoaneSelectate.get(i).vertex_list.get(k)  ,  listaPoligoaneSelectate.get(i).vertex_list.get(k+1));
              }
              else {
                   status =   test1(listaPoligoaneSelectate.get(i).vertex_list.get(k)  ,  listaPoligoaneSelectate.get(i).vertex_list.get(0));
              }
           
              
          if(status == false)
          {
          
              if(k < listaPoligoaneSelectate.get(i).vertex_list.size()-1)
              {
                  listaVert = new ArrayList<Vertex>();
                  
                  temp_1 = new Vertex();
                  temp_1 =  listaPoligoaneSelectate.get(i).vertex_list.get(k);
                  listaVert.add(temp_1);
                  edgeList.add(temp_1);
                  
                  temp_1 = new Vertex();
                  temp_1 =  listaPoligoaneSelectate.get(i).vertex_list.get(k+1); 
                  listaVert.add(temp_1);

                  
                  for(int p=0;p<poli.size();p++)
                  {
                      if(poli.get(p).poz == i)
                      {
                             temp_1 = new Vertex();
                             temp_1 =  poli.get(p).poli.vertex_list.get(k+1);
                             listaVert.add(temp_1);
                             
                             temp_1 = new Vertex();
                             temp_1 =  poli.get(p).poli.vertex_list.get(k);    
                             listaVert.add(temp_1);
                             edgeList.add(temp_1);
                      }
                  }
                   
                
                  
                 poligon = new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p) , new NormalV(0 , 0 ,1) , listaVert , TexturesStatus , null , null); 
                 Cube.b_p+=0.01f;
                 Cube.temp_poligon.add(poligon);   
                 
            Cube.b_e+=0.01f;       
            Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e),edgeList));         
        
              }
                 else 
                 {
          
                         listaVert = new ArrayList<Vertex>();

                          temp_1 = new Vertex();
                          temp_1 =  listaPoligoaneSelectate.get(i).vertex_list.get(k);
                          listaVert.add(temp_1);
                          edgeList.add(temp_1); 
                          
                          temp_1 = new Vertex();
                          temp_1 =  listaPoligoaneSelectate.get(i).vertex_list.get(0);      
                          listaVert.add(temp_1);

                          for(int p=0;p<poli.size();p++)
                          {
                              if(poli.get(p).poz == i)
                              {
                                     temp_1 = new Vertex();
                                     temp_1 =  poli.get(p).poli.vertex_list.get(0);
                                     listaVert.add(temp_1);
                                     
                                     temp_1 = new Vertex();
                                     temp_1 =  poli.get(p).poli.vertex_list.get(k);    
                                     listaVert.add(temp_1);
                                      edgeList.add(temp_1); 
                              }
                          }                     
                     
                     poligon = new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p) , new NormalV(0 , 0 ,1) , listaVert , TexturesStatus , null , null); 
                     Cube.b_p+=0.01f;
                     Cube.temp_poligon.add(poligon);    
                     
                    Cube.b_e+=0.01f;       
                    Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e),edgeList));         
          
                 }
          } else {

          }
              
       }
    }
   
    
   
    
}
static void getSelectedPoligons() {

    for(int i = 0 ; i < Data.final_entity.corp_list.size() ; i++) 
    {
        for(int j=0;j<Data.final_entity.corp_list.get(i).poligon_list.size() ; j++)
        {
            if(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac == 1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac == 0 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac ==0)
            {
               listaPoligoaneSelectate.add(Data.final_entity.corp_list.get(i).poligon_list.get(j));
            }
        }
    
    }

}
static void selectPoligoaneNepartajate() {

   for(int i=0;i<listaPoligoaneSelectate.size();i++)
   {
      curentP = i;
      
      for(int k=0;k<listaPoligoaneSelectate.get(i).vertex_list.size();k++)
      {
              if(k < listaPoligoaneSelectate.get(i).vertex_list.size()-1)
              {
                 test(listaPoligoaneSelectate.get(i).vertex_list.get(k)  ,  listaPoligoaneSelectate.get(i).vertex_list.get(k+1));
              }
              else {
                test(listaPoligoaneSelectate.get(i).vertex_list.get(k)  ,  listaPoligoaneSelectate.get(i).vertex_list.get(0));
              }
      }
   
   }
    
}
static boolean test1(Vertex ver1 , Vertex ver2) {
ok_3 = false;

    for(int i=0;i<listaMuchi.size();i++)
    {
        if(listaMuchi.get(i).vertex_list.get(0).equals(ver1)   &&  listaMuchi.get(i).vertex_list.get(1).equals(ver2)   ||   listaMuchi.get(i).vertex_list.get(0).equals(ver2)   &&  listaMuchi.get(i).vertex_list.get(1).equals(ver1))
        {
            ok_3 = true;
        }
    }
    
    return ok_3;
}
static void test(Vertex ver1 , Vertex ver2){

    for(int i=0;i<listaPoligoaneSelectate.size() ;i++)
    {
          
         if(i != curentP)
         {
             ok_1 = false;
             ok_2 = false;
             
              listaVert = new ArrayList<Vertex>();
              for(int k=0;k<listaPoligoaneSelectate.get(i).vertex_list.size();k++)
              {
                  if(listaPoligoaneSelectate.get(i).vertex_list.get(k).equals(ver1))
                  {
                     ok_1 = true;
                     temp_1 = new Vertex();
                     temp_1 = listaPoligoaneSelectate.get(i).vertex_list.get(k);
                  }
                  if(listaPoligoaneSelectate.get(i).vertex_list.get(k).equals(ver2))
                  {
                     ok_2 = true;
                     temp_2 = new Vertex();
                     temp_2 = listaPoligoaneSelectate.get(i).vertex_list.get(k);
                  }
              }
              
             if(ok_1 == true  && ok_2 == true)
             {
                listaVert.add(temp_1);
                listaVert.add(temp_2);
                listaMuchi.add(new Edge(new C_ambient(1, 1,1), listaVert));
             }
             else{
                 temp_1 = null;
                 temp_2 = null;
                 listaVert = null;
             }
         }
    }

}

static void reset()
{
    listaPoligoaneSelectate.clear();
    listaMuchi.clear();

}
static void reset_listaPoligoaneSelectate(){
    poli.clear();
}
    
}
