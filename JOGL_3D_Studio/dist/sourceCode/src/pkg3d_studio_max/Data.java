package pkg3d_studio_max;

import com.sun.opengl.util.texture.Texture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import javax.media.opengl.GL;

//-------------------------------------------------//
//              Object clases maping               //
//-------------------------------------------------//
class NormalV {

    float x_nv;
    float y_nv;
    float z_nv;

    NormalV() {
    }

    NormalV(float x_nv, float y_nv, float z_nv) {

        this.x_nv = x_nv;
        this.y_nv = y_nv;
        this.z_nv = z_nv;

    }
}

class C_ambient {

    float x_ac;
    float y_ac;
    float z_ac;

    C_ambient() {
    }

    C_ambient(float x_ac, float y_ac, float z_ac) {

        this.x_ac = x_ac;
        this.y_ac = y_ac;
        this.z_ac = z_ac;

    }
}
class  TexturesCoordinates {

    float x_t;
    float y_t;
    
    TexturesCoordinates(){
    }
    
    TexturesCoordinates(float x_t , float y_t) {
        
        this.x_t = x_t;
        this.y_t = y_t;
    }
    
}
class  Vertex  {

    C_ambient vc_ambient;
    
    float x;
    float y;
    float z;

    Vertex() {
    }

    Vertex(C_ambient vc_ambient, float x, float y, float z) {


        this.vc_ambient = vc_ambient;
        this.x = x;
        this.y = y;
        this.z = z;

    }
}

class Edge {

    C_ambient ec_ambient;
    ArrayList<Vertex> vertex_list;

    Edge() {
    }

    Edge(C_ambient ec_ambient, ArrayList<Vertex> vertex_list) {

        this.ec_ambient = ec_ambient;
        this.vertex_list = vertex_list;

    }
}

class Poligon {

    C_ambient pc_ambient;
    NormalV normalV;
    ArrayList<Vertex> vertex_list;

    boolean T_status ;
    ArrayList<TexturesCoordinates>    texturesCoordinates;
    Texture   textureStream;
    
    Poligon() {
    }

    Poligon(C_ambient pc_ambient, NormalV normalV, ArrayList<Vertex> vertex_list , boolean T_status , ArrayList<TexturesCoordinates>    texturesCoordinates , Texture   textureStream) {

        this.pc_ambient = pc_ambient;
        this.normalV = normalV;
        this.vertex_list = vertex_list;
        this.T_status = T_status;
        this.texturesCoordinates = texturesCoordinates;
        this.textureStream =   textureStream;
    }
}

class Corp {

    C_ambient cc_ambient;
    ArrayList<Poligon> poligon_list;

    Corp() {
    }

    Corp(C_ambient cc_ambient, ArrayList<Poligon> poligon_list) {

        this.cc_ambient = cc_ambient;
        this.poligon_list = poligon_list;

    }
}

class Entity {

    C_ambient ent_c_ambient;
    ArrayList<Corp> corp_list = new ArrayList<Corp>();

    Entity() {
    }

    Entity(C_ambient ent_c_ambient, ArrayList<Corp> corp_list) {

        this.ent_c_ambient = ent_c_ambient;
        this.corp_list = corp_list;

    }
}

//-------------------------------------------------//
public class Data {

    static Cube cub = new Cube();
        
    static ArrayList<Entity> saved_data_entity         =  new ArrayList<Entity>();
    static ArrayList<Vertex> saved_cube_vertex_array   =  new ArrayList<Vertex>();
    static ArrayList<Edge>   saved_cube_edge_array     =  new ArrayList<Edge>();
    
    static Entity              final_entity            = new Entity();
    static ArrayList<Vertex>   final_cube_vertex_array = new ArrayList<Vertex>();
    static ArrayList<Edge>     final_cube_edge_array   = new ArrayList<Edge>();

}

class Cube {

    Data data = new Data();
    Vertex temp_ver = null;
    Edge temp_linie = null;
    int index_v = 1;
    boolean TexturesStatus = false;
    
    static float x;
    static float y;
    static float z;
    static float x_l;
    static float y_l;
    static float z_l;
//-------------------------------------    
    static float r_p = 1;
    static float g_p = 0;
    
    static float b_p = 0.01f;
    
 //------------------------------------  
    static float r_v = 0.01f;
    
    static float g_v = 0;
    static float b_v = 1;
    
//-------------------------------------   
    static float r_e = 0;
    static float g_e = 1;
    
    static float b_e = 0.01f;

    Cube() {
    }

    Cube(float x, float y, float z, float x_l, float y_l, float z_l) {

        this.x = x;
        this.y = y;
        this.z = z;

        this.x_l = x_l;
        this.y_l = y_l;
        this.z_l = z_l;

    }
    static ArrayList<Vertex> cube_vertex_array =  new ArrayList<Vertex>();
    static ArrayList<Edge>   cube_edge_array   =  new ArrayList<Edge>();
           
    Edge edge = null;
    Entity entity = null;
    
    ArrayList<Vertex> temp_vertex_List = null;
    ArrayList<Vertex> vertex_List = null;
    static ArrayList<Corp> temp_corp = null;
    static ArrayList<Poligon> temp_poligon = null;
    ArrayList<Edge> temp_edge = null;
    ArrayList<TexturesCoordinates>  textureCoordonate;
    Texture    texturee ;
    
    
    public void set_CubeData() {

      
        
        cube_vertex_array.clear();
        cube_edge_array.clear();
        Data.final_cube_vertex_array.clear();
        Data.final_cube_edge_array.clear();


//------------Vertex--------------------------------------------------------------------------------------//    

        temp_ver = new Vertex(new C_ambient(r_v, g_v, b_v), x + x_l, y, 0);
        cube_vertex_array.add(temp_ver);
        r_v = r_v + 0.01f;

        temp_ver = new Vertex(new C_ambient(r_v, g_v, b_v), x + x_l, y + y_l, 0);
        cube_vertex_array.add(temp_ver);
        r_v = r_v + 0.01f;

        temp_ver = new Vertex(new C_ambient(r_v, g_v, b_v), x, y + y_l, 0);
        cube_vertex_array.add(temp_ver);
        r_v = r_v + 0.01f;

        temp_ver = new Vertex(new C_ambient(r_v, g_v, b_v), x, y, 0);
        cube_vertex_array.add(temp_ver);
        r_v = r_v + 0.01f;

        temp_ver = new Vertex(new C_ambient(r_v, g_v, b_v), x + x_l, y, z + z_l);
        cube_vertex_array.add(temp_ver);
        r_v = r_v + 0.01f;

        temp_ver = new Vertex(new C_ambient(r_v, g_v, b_v), x + x_l, y + y_l, z + z_l);
        cube_vertex_array.add(temp_ver);
        r_v = r_v + 0.001f;

        temp_ver = new Vertex(new C_ambient(r_v, g_v, b_v), x, y + y_l, z + z_l);
        cube_vertex_array.add(temp_ver);
        r_v = r_v + 0.01f;

        temp_ver = new Vertex(new C_ambient(r_v, g_v, b_v), x, y, z + z_l);
        cube_vertex_array.add(temp_ver);
        r_v = r_v + 0.01f;



        Data.final_cube_vertex_array = cube_vertex_array;

//-----Vertex----------------------------------------------------------------------------------------//  

        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(0);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(1);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;

        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(1);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(2);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;

        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(2);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(3);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;

        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(3);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(0);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;

        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(4);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(5);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;

        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(5);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(6);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;

        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(6);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(7);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;

        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(7);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(4);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;

        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(0);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(4);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;


        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(1);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(5);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;


        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(2);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(6);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;

        vertex_List = new ArrayList<Vertex>();

        temp_ver = cube_vertex_array.get(3);
        vertex_List.add(temp_ver);
        temp_ver = cube_vertex_array.get(7);
        vertex_List.add(temp_ver);
        edge = new Edge(new C_ambient(r_e, g_e, b_e), vertex_List);
        cube_edge_array.add(edge);
        b_e = b_e + 0.01f;

        Data.final_cube_edge_array = cube_edge_array;
//-----Edge------------------------------------------------------------------------------------------//


    }

    public void loadCube() {


        temp_corp = new ArrayList<Corp>();
        temp_poligon = new ArrayList<Poligon>();


        temp_vertex_List = new ArrayList<Vertex>();
        textureCoordonate = new ArrayList<TexturesCoordinates>();
        

        temp_vertex_List.add(cube_vertex_array.get(0));
        temp_vertex_List.add(cube_vertex_array.get(1));
        temp_vertex_List.add(cube_vertex_array.get(2));
        temp_vertex_List.add(cube_vertex_array.get(3));

        
        temp_poligon.add(new Poligon(new C_ambient(r_p, g_p, b_p), new NormalV(0, 0, -1), temp_vertex_List, TexturesStatus , textureCoordonate ,texturee));
        b_p = b_p + 0.01f;

        temp_vertex_List = new ArrayList<Vertex>();
        textureCoordonate = new ArrayList<TexturesCoordinates>();

        temp_vertex_List.add(cube_vertex_array.get(4));
        temp_vertex_List.add(cube_vertex_array.get(5));
        temp_vertex_List.add(cube_vertex_array.get(6));
        temp_vertex_List.add(cube_vertex_array.get(7));

        temp_poligon.add(new Poligon(new C_ambient(r_p, g_p, b_p), new NormalV(0, 0, 1), temp_vertex_List , TexturesStatus , textureCoordonate , texturee));
        b_p = b_p + 0.01f;

        temp_vertex_List = new ArrayList<Vertex>();
        textureCoordonate = new ArrayList<TexturesCoordinates>();

        temp_vertex_List.add(cube_vertex_array.get(0));
        temp_vertex_List.add(cube_vertex_array.get(4));
        temp_vertex_List.add(cube_vertex_array.get(5));
        temp_vertex_List.add(cube_vertex_array.get(1));

        temp_poligon.add(new Poligon(new C_ambient(r_p, g_p, b_p), new NormalV(1, 0, 0), temp_vertex_List , TexturesStatus , textureCoordonate , texturee));
        b_p = b_p + 0.01f;

        temp_vertex_List = new ArrayList<Vertex>();
        textureCoordonate = new ArrayList<TexturesCoordinates>();

        temp_vertex_List.add(cube_vertex_array.get(3));
        temp_vertex_List.add(cube_vertex_array.get(7));
        temp_vertex_List.add(cube_vertex_array.get(6));
        temp_vertex_List.add(cube_vertex_array.get(2));

        temp_poligon.add(new Poligon(new C_ambient(r_p, g_p, b_p), new NormalV(-1, 0, 0), temp_vertex_List , TexturesStatus , textureCoordonate , texturee));
        b_p = b_p + 0.01f;

        temp_vertex_List = new ArrayList<Vertex>();
        textureCoordonate = new ArrayList<TexturesCoordinates>();

        temp_vertex_List.add(cube_vertex_array.get(0));
        temp_vertex_List.add(cube_vertex_array.get(4));
        temp_vertex_List.add(cube_vertex_array.get(7));
        temp_vertex_List.add(cube_vertex_array.get(3));

        temp_poligon.add(new Poligon(new C_ambient(r_p, g_p, b_p), new NormalV(0, 1, 0), temp_vertex_List , TexturesStatus , textureCoordonate , texturee));
        b_p = b_p + 0.01f;

        temp_vertex_List = new ArrayList<Vertex>();
        textureCoordonate = new ArrayList<TexturesCoordinates>();

        temp_vertex_List.add(cube_vertex_array.get(2));
        temp_vertex_List.add(cube_vertex_array.get(1));
        temp_vertex_List.add(cube_vertex_array.get(5));
        temp_vertex_List.add(cube_vertex_array.get(6));

        temp_poligon.add(new Poligon(new C_ambient(r_p, g_p, b_p), new NormalV(0, -1, 0), temp_vertex_List , TexturesStatus , textureCoordonate , texturee));
        b_p = b_p + 0.01f;



        temp_corp.add(new Corp(new C_ambient(1, 0, 0), temp_poligon));
        entity = new Entity(new C_ambient(1, 0, 0), temp_corp);


        Data.final_entity = entity;




    }
}