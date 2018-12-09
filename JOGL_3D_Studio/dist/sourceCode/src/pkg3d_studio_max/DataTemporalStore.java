

package pkg3d_studio_max;

import com.sun.opengl.util.texture.Texture;
import java.util.ArrayList;


public class DataTemporalStore {
    
     Vertex vert = null;
     ArrayList<Vertex> vertex_List ;
     ArrayList<Corp> temp_corp = null;
     ArrayList<Poligon> poligoane_List = null;
     ArrayList<TexturesCoordinates>  textureCoordonate = null;
     Texture      texturee=null;
    
    public  void saveTempData() 
    {
              
        for(int i=0 ; i<Data.final_cube_vertex_array.size() ; i++)
        {
            vert = new Vertex(new C_ambient(Data.final_cube_vertex_array.get(i).vc_ambient.x_ac, Data.final_cube_vertex_array.get(i).vc_ambient.y_ac, Data.final_cube_vertex_array.get(i).vc_ambient.z_ac), Data.final_cube_vertex_array.get(i).x,Data.final_cube_vertex_array.get(i).y, Data.final_cube_vertex_array.get(i).z);
            Data.saved_cube_vertex_array.add(vert);
        }

        for(int i=0 ; i<Data.final_cube_edge_array.size() ; i++)
        {
            int poz_e[]=new int[2];
            int k_e=0;
            
            
            for(int j=0 ; j<Data.final_cube_vertex_array.size() ; j++)
            {
                if(Data.final_cube_edge_array.get(i).vertex_list.get(0).x == Data.final_cube_vertex_array.get(j).x   &&  Data.final_cube_edge_array.get(i).vertex_list.get(0).y == Data.final_cube_vertex_array.get(j).y  &&  Data.final_cube_edge_array.get(i).vertex_list.get(0).z == Data.final_cube_vertex_array.get(j).z  ||  Data.final_cube_edge_array.get(i).vertex_list.get(1).x == Data.final_cube_vertex_array.get(j).x   &&  Data.final_cube_edge_array.get(i).vertex_list.get(1).y == Data.final_cube_vertex_array.get(j).y  &&  Data.final_cube_edge_array.get(i).vertex_list.get(1).z == Data.final_cube_vertex_array.get(j).z)
                {
                    poz_e[k_e] = j;
                    k_e++;
                }
                
            }
            vertex_List = new ArrayList<Vertex>();
            vertex_List.add(Data.saved_cube_vertex_array.get(poz_e[0]));
            vertex_List.add(Data.saved_cube_vertex_array.get(poz_e[1]));
            
            Data.saved_cube_edge_array.add(new Edge(new C_ambient(Data.final_cube_edge_array.get(i).ec_ambient.x_ac, Data.final_cube_edge_array.get(i).ec_ambient.y_ac,Data.final_cube_edge_array.get(i).ec_ambient.z_ac), vertex_List));
        }
        
        for(int i=0 ; i<Data.final_entity.corp_list.size() ; i++)
        { 
            poligoane_List = new ArrayList<Poligon>();
            
            for(int j=0 ; j<Data.final_entity.corp_list.get(i).poligon_list.size() ; j++)
            {
                int poz_vp[]=new int[Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size()];
                int k_p=0;
                
                for(int k=0 ; k<Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size() ; k++)
                {
                              for(int p=0 ; p<Data.final_cube_vertex_array.size() ; p++)
                              {
                                    if(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).x == Data.final_cube_vertex_array.get(p).x  && Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).y == Data.final_cube_vertex_array.get(p).y  &&  Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).z == Data.final_cube_vertex_array.get(p).z) 
                                    {
                                       poz_vp[k_p] = p;
                                       k_p++;
                                    }
                              }
                }
                
                vertex_List = new ArrayList<Vertex>();
                
                for(int m=0 ; m<Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size() ; m++)
                {
                    vertex_List.add(Data.saved_cube_vertex_array.get(poz_vp[m]));
                }
                          
                poligoane_List.add(new Poligon(new C_ambient(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac, Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac, Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac), new NormalV(Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.x_nv, Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.y_nv, Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.z_nv), vertex_List, false, textureCoordonate, texturee));    
            }
            
            temp_corp = new ArrayList<Corp>();
            temp_corp.add(new Corp(Data.final_entity.corp_list.get(i).cc_ambient , poligoane_List));
        }
        
        Data.saved_data_entity.add(new Entity(Data.final_entity.ent_c_ambient , temp_corp));
            
    }
    
    public  void  loadTempData() {
  
     if(Data.saved_data_entity.size()!=0)
     {
       releseMemori();
   
       for(int i=0 ; i<Data.saved_cube_vertex_array.size() ; i++)
       {
            Data.final_cube_vertex_array.add(new Vertex(new C_ambient(Data.saved_cube_vertex_array.get(i).vc_ambient.x_ac, Data.saved_cube_vertex_array.get(i).vc_ambient.y_ac,Data.saved_cube_vertex_array.get(i).vc_ambient.z_ac), Data.saved_cube_vertex_array.get(i).x, Data.saved_cube_vertex_array.get(i).y,Data.saved_cube_vertex_array.get(i).z));
       }
       
       for(int i=0 ; i<Data.saved_cube_edge_array.size() ; i++)
       {
              
            int poz_e[]=new int[2];
            int k_e=0;
            
            
            for(int j=0 ; j<Data.saved_cube_vertex_array.size() ; j++)
            {
                if(Data.saved_cube_edge_array.get(i).vertex_list.get(0).x == Data.saved_cube_vertex_array.get(j).x   &&  Data.saved_cube_edge_array.get(i).vertex_list.get(0).y == Data.saved_cube_vertex_array.get(j).y  &&  Data.saved_cube_edge_array.get(i).vertex_list.get(0).z == Data.saved_cube_vertex_array.get(j).z  ||  Data.saved_cube_edge_array.get(i).vertex_list.get(1).x == Data.saved_cube_vertex_array.get(j).x   &&  Data.saved_cube_edge_array.get(i).vertex_list.get(1).y == Data.saved_cube_vertex_array.get(j).y  &&  Data.saved_cube_edge_array.get(i).vertex_list.get(1).z == Data.saved_cube_vertex_array.get(j).z)
                {
                    poz_e[k_e] = j;
                    k_e++;
                }
                
            }

            vertex_List = new ArrayList<Vertex>();
            vertex_List.add(Data.final_cube_vertex_array.get(poz_e[0]));
            vertex_List.add(Data.final_cube_vertex_array.get(poz_e[1]));
           
           
            Data.final_cube_edge_array.add(new Edge(new C_ambient(Data.saved_cube_edge_array.get(i).ec_ambient.x_ac, Data.saved_cube_edge_array.get(i).ec_ambient.y_ac, Data.saved_cube_edge_array.get(i).ec_ambient.z_ac), vertex_List));
       }
  
       for(int i=0 ; i< Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.size() ; i++)
       {
            poligoane_List = new ArrayList<Poligon>();
            for(int j=0 ; j<Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.size() ; j++)
            {                          
                
                int poz_vp[]=new int[Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).vertex_list.size()];
                int k_p=0;
                
                for(int k=0 ; k<Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).vertex_list.size() ; k++)
                {
                              for(int p=0 ; p<Data.saved_cube_vertex_array.size() ; p++)
                              {
                                    if(Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).vertex_list.get(k).x == Data.saved_cube_vertex_array.get(p).x  && Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).vertex_list.get(k).y == Data.saved_cube_vertex_array.get(p).y  &&  Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).vertex_list.get(k).z == Data.saved_cube_vertex_array.get(p).z) 
                                    {
                                       poz_vp[k_p] = p;
                                       k_p++;
                                    }
                              }
                }   

                vertex_List = new ArrayList<Vertex>();
                
                for(int m=0 ; m<Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).vertex_list.size() ; m++)
                {
                    vertex_List.add(Data.final_cube_vertex_array.get(poz_vp[m]));
                }
                
                poligoane_List.add(new Poligon(new C_ambient(Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac, Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac, Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac), new NormalV(Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).normalV.x_nv, Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).normalV.y_nv, Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).poligon_list.get(j).normalV.z_nv), vertex_List, false, textureCoordonate, texturee));
            }
            Data.final_entity.corp_list.add(new Corp(new C_ambient(Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).cc_ambient.x_ac, Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).cc_ambient.y_ac, Data.saved_data_entity.get(Data.saved_data_entity.size()-1).corp_list.get(i).cc_ambient.z_ac), poligoane_List));
       }
       
     }
    }
    
    public static void releseMemori() {
    
        Data.final_cube_edge_array.clear();
        Data.final_cube_vertex_array.clear();
        Data.final_entity.corp_list.clear();

    }
    
    public  void printSize() {
        
    System.out.println("Vertex    =  "+Data.final_cube_vertex_array.size());
    System.out.println("Edges     =  "+Data.final_cube_edge_array.size());
    System.out.println("Poligoane =  "+Data.final_entity.corp_list.get(0).poligon_list.size());

    } 
    
    public  void PrintData() {
    
        for(int i=0 ; i<Data.final_entity.corp_list.size() ; i++)
        {
            for(int j=0 ; j<Data.final_entity.corp_list.get(i).poligon_list.size() ; j++)
            { 
                System.out.println("Poligon ["+j+"]");
                for(int k=0 ; k<Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size() ; k++)
                {
                   System.out.println("\t x = "+Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).x+"      y= "+Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).y+"       z= "+Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).z);  
                }
            }
        }
    
    }
    
}
