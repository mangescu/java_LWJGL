package pkg3d_studio_max;

import com.sun.opengl.util.texture.Texture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

class LateralP {

    int i_c;
    int j_p;
    int start_index;
    int end_index;
    String point;

    public LateralP() {
    }

    public LateralP(int i_c, int j_p, int start_index, int end_index, String point) {
        this.i_c = i_c;
        this.j_p = j_p;
        this.start_index = start_index;
        this.end_index = end_index;
        this.point = point;
    }
}

class midleP {

    int i_mc;
    int j_mp;
    int start_index_mp;
    int end_index_mp;
    String point_mp;

    public midleP() {
    }

    public midleP(int i_mc, int j_mp, int start_index_mp, int end_index_mp, String point_mp) {

        this.i_mc = i_mc;
        this.j_mp = j_mp;
        this.start_index_mp = start_index_mp;
        this.end_index_mp = end_index_mp;
        this.point_mp = point_mp;
    }
}

public class EdgeUtilFunction {

    static Vertex M[] = new Vertex[2];
    static int P[] = new int[2];
    static Vertex testVertex = new Vertex();
    static Vertex vert;
    static ArrayList<Vertex> listaVertex;
    static ArrayList<Vertex> listaVertex2;
    static ArrayList<LateralP> lista_P_Laterale = new ArrayList<LateralP>();
    static LateralP temp_lateral;
    static ArrayList<midleP> lista_P_midle = new ArrayList<midleP>();
    static midleP temp_midle;
    static int M_index = 0;
    static int P_index = 0;
    static String testMidleResult;
    static String rezultat;
    static boolean done = false;

    
    static int nr_edge_selected;
    static int numar;
    static boolean validate = false;
    static ArrayList<Vertex> vertex_edge_selected = new ArrayList<Vertex>();
    
    static boolean TexturesStatus = false;
    static ArrayList<TexturesCoordinates>    textureCoordonate;
    static Texture    texturee ;
    
    
    
    static void connectEdge() {
        
        try {
               checkSelectedData();
              
            } catch (Exception ex) 
                    {
                          Logger.getLogger(EdgeUtilFunction.class.getName()).log(Level.SEVERE, null, ex);
                          return;
                    }


        calculateMidle();
        loadData();
        filterLoadedData();
        //printData();
        resetLPoligonConfiguration();
        resetMPoligonConfiguration();



        removeUnnecessaryEdges();
        removeUnnecessaryPolygons();
        resetData();
        Normal3D.resetNVector();
    }
    
    static  void checkSelectedData() throws Exception 
    {
       nr_edge_selected = 0;
       numar = 0;
       validate = false;
       vertex_edge_selected.clear();
       
       for(int i=0;i<Data.final_cube_edge_array.size() ; i++)
       {
          if(Data.final_cube_edge_array.get(i).ec_ambient.x_ac == 1 && Data.final_cube_edge_array.get(i).ec_ambient.y_ac == 0 &&  Data.final_cube_edge_array.get(i).ec_ambient.z_ac == 0)
          {
            nr_edge_selected ++ ;
          }
       }
       if(nr_edge_selected != 2)
       {
         throw new Exception("nu s-au selectat 2 muchi");
       }
       
       
       for(int i=0 ; i<Data.final_cube_edge_array.size() ; i++)
       {
           if(Data.final_cube_edge_array.get(i).ec_ambient.x_ac == 1 && Data.final_cube_edge_array.get(i).ec_ambient.y_ac == 0 &&  Data.final_cube_edge_array.get(i).ec_ambient.z_ac == 0)
           {
                 vertex_edge_selected.add(Data.final_cube_edge_array.get(i).vertex_list.get(0));
                 vertex_edge_selected.add(Data.final_cube_edge_array.get(i).vertex_list.get(1));
           }
       }
       
       for(int i=0 ; i<Data.final_entity.corp_list.size() ; i++)
       {
           for(int j=0 ; j<Data.final_entity.corp_list.get(i).poligon_list.size() ; j++)
           {
               numar = 0;
               for(int k=0 ; k<Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size() ; k++)
               {
                   if(vertex_edge_selected.contains(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k)))
                   {
                         for(int p=0 ; p<vertex_edge_selected.size() ; p++) 
                         {
                            if(vertex_edge_selected.get(p).equals(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k)))
                            {
                                numar++;
                             
                            }
                         }
        
                   }
               }
               
               if(numar == 4)
               {
                  validate = true;   
               }
               
           }
       }
       
       if(validate == false)
       {
          throw new Exception("muchile trebuie sa apartina aceluiasi poligon");
       }
    
    
    }
    
    static void removeUnnecessaryEdges() {

        if (P[0] > P[1]) {

            Data.final_cube_edge_array.remove(P[0]);
            Data.final_cube_edge_array.remove(P[1]);
        }
        if (P[1] > P[0]) {

            Data.final_cube_edge_array.remove(P[1]);
            Data.final_cube_edge_array.remove(P[0]);
        }

    }

    static void removeUnnecessaryPolygons() {

        if (lista_P_Laterale.size() == 2) {
            if (lista_P_midle.get(0).j_mp > lista_P_Laterale.get(0).j_p && lista_P_midle.get(0).j_mp > lista_P_Laterale.get(1).j_p) {
                Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.remove(lista_P_midle.get(0).j_mp);

                if (lista_P_Laterale.get(0).j_p > lista_P_Laterale.get(1).j_p) {
                    Data.final_entity.corp_list.get(lista_P_Laterale.get(0).i_c).poligon_list.remove(lista_P_Laterale.get(0).j_p);
                    Data.final_entity.corp_list.get(lista_P_Laterale.get(1).i_c).poligon_list.remove(lista_P_Laterale.get(1).j_p);
                }
                if (lista_P_Laterale.get(1).j_p > lista_P_Laterale.get(0).j_p) {
                    Data.final_entity.corp_list.get(lista_P_Laterale.get(1).i_c).poligon_list.remove(lista_P_Laterale.get(1).j_p);
                    Data.final_entity.corp_list.get(lista_P_Laterale.get(0).i_c).poligon_list.remove(lista_P_Laterale.get(0).j_p);
                }
            }
            if (lista_P_Laterale.get(0).j_p > lista_P_midle.get(0).j_mp && lista_P_Laterale.get(0).j_p > lista_P_Laterale.get(1).j_p) {
                Data.final_entity.corp_list.get(lista_P_Laterale.get(0).i_c).poligon_list.remove(lista_P_Laterale.get(0).j_p);

                if (lista_P_midle.get(0).j_mp > lista_P_Laterale.get(1).j_p) {
                    Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.remove(lista_P_midle.get(0).j_mp);
                    Data.final_entity.corp_list.get(lista_P_Laterale.get(1).i_c).poligon_list.remove(lista_P_Laterale.get(1).j_p);
                }
                if (lista_P_Laterale.get(1).j_p > lista_P_midle.get(0).j_mp) {
                    Data.final_entity.corp_list.get(lista_P_Laterale.get(1).i_c).poligon_list.remove(lista_P_Laterale.get(1).j_p);
                    Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.remove(lista_P_midle.get(0).j_mp);
                }

            }
            if (lista_P_Laterale.get(1).j_p > lista_P_midle.get(0).j_mp && lista_P_Laterale.get(1).j_p > lista_P_Laterale.get(0).j_p) {
                Data.final_entity.corp_list.get(lista_P_Laterale.get(1).i_c).poligon_list.remove(lista_P_Laterale.get(1).j_p);

                if (lista_P_midle.get(0).j_mp > lista_P_Laterale.get(0).j_p) {
                    Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.remove(lista_P_midle.get(0).j_mp);
                    Data.final_entity.corp_list.get(lista_P_Laterale.get(0).i_c).poligon_list.remove(lista_P_Laterale.get(0).j_p);
                }
                if (lista_P_Laterale.get(0).j_p > lista_P_midle.get(0).j_mp) {
                    Data.final_entity.corp_list.get(lista_P_Laterale.get(0).i_c).poligon_list.remove(lista_P_Laterale.get(0).j_p);
                    Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.remove(lista_P_midle.get(0).j_mp);
                }

            }
        }
        if (lista_P_Laterale.size() == 1) {

            if (lista_P_midle.get(0).j_mp > lista_P_midle.get(0).j_mp) {
                Data.final_entity.corp_list.get(lista_P_Laterale.get(0).i_c).poligon_list.remove(lista_P_Laterale.get(0).j_p);
                Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.remove(lista_P_midle.get(0).j_mp);
            } else {
                Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.remove(lista_P_midle.get(0).j_mp);
                Data.final_entity.corp_list.get(lista_P_Laterale.get(0).i_c).poligon_list.remove(lista_P_Laterale.get(0).j_p);
            }
        }
        if (lista_P_Laterale.size() == 0) {
            Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.remove(lista_P_midle.get(0).j_mp);
        }

    }

    static void resetMPoligonConfiguration() {

        done = false;
        listaVertex = new ArrayList<Vertex>();
        listaVertex2 = new ArrayList<Vertex>();
        for (int i = 0; i < Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.size(); i++) {
            if (done == false) {
                if (i == lista_P_midle.get(0).start_index_mp || i == lista_P_midle.get(1).start_index_mp) {

                    if (i == lista_P_midle.get(0).start_index_mp) {

                        vert = Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.get(i);
                        listaVertex.add(vert);

                        if (lista_P_midle.get(0).point_mp.equals("M[0]")) {
                            vert = M[0];
                            listaVertex.add(vert);
                            vert = M[1];
                            listaVertex.add(vert);

                        }
                        if (lista_P_midle.get(0).point_mp.equals("M[1]")) {
                            vert = M[1];
                            listaVertex.add(vert);
                            vert = M[0];
                            listaVertex.add(vert);

                        }

                        if (lista_P_midle.get(1).end_index_mp != 0) {

                            for (int j = lista_P_midle.get(1).end_index_mp; j < Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.size(); j++) {
                                vert = Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.get(j);
                                listaVertex.add(vert);
                            }

                        }
//second  poligon--------------------------------------------------------------\
                        if (lista_P_midle.get(0).point_mp.equals("M[0]")) {

                            for (int p = lista_P_midle.get(0).end_index_mp; p <= lista_P_midle.get(1).start_index_mp; p++) {
                                if (p == 0) {
                                    return;
                                }
                                vert = Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.get(p);
                                listaVertex2.add(vert);

                            }
                            vert = M[1];
                            listaVertex2.add(vert);
                            vert = M[0];
                            listaVertex2.add(vert);
                        }
                        if (lista_P_midle.get(0).point_mp.equals("M[1]")) {

                            for (int p = lista_P_midle.get(0).end_index_mp; p <= lista_P_midle.get(1).start_index_mp; p++) {
                                if (p == 0) {
                                    return;
                                }
                                vert = Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.get(p);
                                listaVertex2.add(vert);

                            }
                            vert = M[0];
                            listaVertex2.add(vert);
                            vert = M[1];
                            listaVertex2.add(vert);
                        }
//-----------------------------------------------------------------------------//                
                        done = true;
                    }
                    if (i == lista_P_midle.get(1).start_index_mp) {

                        vert = Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.get(i);
                        listaVertex.add(vert);

                        if (lista_P_midle.get(1).point_mp.equals("M[0]")) {
                            vert = M[0];
                            listaVertex.add(vert);
                            vert = M[1];
                            listaVertex.add(vert);

                        }
                        if (lista_P_midle.get(1).point_mp.equals("M[1]")) {
                            vert = M[1];
                            listaVertex.add(vert);
                            vert = M[0];
                            listaVertex.add(vert);

                        }

                        if (lista_P_midle.get(0).end_index_mp != 0) {

                            for (int j = lista_P_midle.get(0).end_index_mp; j < Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.size(); j++) {
                                vert = Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.get(j);
                                listaVertex.add(vert);
                            }

                        }
//second  poligon--------------------------------------------------------------\
                        if (lista_P_midle.get(1).point_mp.equals("M[0]")) {

                            for (int p = lista_P_midle.get(1).end_index_mp; p <= lista_P_midle.get(0).start_index_mp; p++) {
                                if (p == 0) {
                                    return;
                                }
                                vert = Data.final_entity.corp_list.get(lista_P_midle.get(1).i_mc).poligon_list.get(lista_P_midle.get(1).j_mp).vertex_list.get(p);
                                listaVertex2.add(vert);

                            }
                            vert = M[1];
                            listaVertex2.add(vert);
                            vert = M[0];
                            listaVertex2.add(vert);
                        }
                        if (lista_P_midle.get(1).point_mp.equals("M[1]")) {

                            for (int p = lista_P_midle.get(1).end_index_mp; p <= lista_P_midle.get(0).start_index_mp; p++) {
                                if (p == 0) {
                                    return;
                                }
                                vert = Data.final_entity.corp_list.get(lista_P_midle.get(1).i_mc).poligon_list.get(lista_P_midle.get(1).j_mp).vertex_list.get(p);
                                listaVertex2.add(vert);


                            }
                            vert = M[0];
                            listaVertex2.add(vert);
                            vert = M[1];
                            listaVertex2.add(vert);
                        }

//-----------------------------------------------------------------------------\                

                        done = true;
                    }

                } else {
                    if (i != lista_P_midle.get(1).start_index_mp && i != lista_P_midle.get(0).start_index_mp) {
                        vert = Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.get(i);
                        listaVertex.add(vert);
                    }
                }
            }
        }
        textureCoordonate = new ArrayList<TexturesCoordinates>();
        Cube.b_p += 0.01f;
       // Cube.temp_poligon.add(new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p), new NormalV(0, 0, 1), listaVertex , TexturesStatus , textureCoordonate , texturee));
        Data.final_entity.corp_list.get(0).poligon_list.add(new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p), new NormalV(0, 0, 1), listaVertex , TexturesStatus , textureCoordonate , texturee));
        textureCoordonate = new ArrayList<TexturesCoordinates>();
        Cube.b_p += 0.01f;
       // Cube.temp_poligon.add(new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p), new NormalV(0, 0, 1), listaVertex2 , TexturesStatus , textureCoordonate , texturee));
        Data.final_entity.corp_list.get(0).poligon_list.add(new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p), new NormalV(0, 0, 1), listaVertex2 , TexturesStatus , textureCoordonate , texturee));

    }

    static void resetLPoligonConfiguration() {

        for (int p = 0; p < lista_P_Laterale.size(); p++) {
            listaVertex = new ArrayList<Vertex>();
            for (int i = 0; i < Data.final_entity.corp_list.get(lista_P_Laterale.get(p).i_c).poligon_list.get(lista_P_Laterale.get(p).j_p).vertex_list.size(); i++) {
                if (i == lista_P_Laterale.get(p).start_index) {
                    vert = Data.final_entity.corp_list.get(lista_P_Laterale.get(p).i_c).poligon_list.get(lista_P_Laterale.get(p).j_p).vertex_list.get(i);
                    listaVertex.add(vert);

                    if (lista_P_Laterale.get(p).point.equals("M[0]")) {
                        listaVertex.add(M[0]);
                    }
                    if (lista_P_Laterale.get(p).point.equals("M[1]")) {
                        listaVertex.add(M[1]);
                    }

                } else {
                    vert = Data.final_entity.corp_list.get(lista_P_Laterale.get(p).i_c).poligon_list.get(lista_P_Laterale.get(p).j_p).vertex_list.get(i);
                    listaVertex.add(vert);
                }

            }
            textureCoordonate = new ArrayList<TexturesCoordinates>();
            Cube.b_p += 0.01f;
         //   Cube.temp_poligon.add(new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p), new NormalV(0, 0, 1), listaVertex , TexturesStatus , textureCoordonate , texturee));
            Data.final_entity.corp_list.get(0).poligon_list.add(new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p), new NormalV(0, 0, 1), listaVertex , TexturesStatus , textureCoordonate , texturee));
        }

        Data.final_cube_vertex_array.add(M[0]);
        Data.final_cube_vertex_array.add(M[1]);


        listaVertex = new ArrayList<Vertex>();
        listaVertex.add(M[0]);
        listaVertex.add(M[1]);
        Cube.b_e += 0.01f;
        Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e), listaVertex));




        listaVertex = new ArrayList<Vertex>();
        listaVertex.add(Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.get(lista_P_midle.get(0).start_index_mp));
        if (lista_P_midle.get(0).point_mp.equals("M[0]")) {
            listaVertex.add(M[0]);
        }
        if (lista_P_midle.get(0).point_mp.equals("M[1]")) {
            listaVertex.add(M[1]);
        }
        Cube.b_e += 0.01f;
        Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e), listaVertex));


        listaVertex = new ArrayList<Vertex>();
        listaVertex.add(Data.final_entity.corp_list.get(lista_P_midle.get(0).i_mc).poligon_list.get(lista_P_midle.get(0).j_mp).vertex_list.get(lista_P_midle.get(0).end_index_mp));
        if (lista_P_midle.get(0).point_mp.equals("M[0]")) {
            listaVertex.add(M[0]);
        }
        if (lista_P_midle.get(0).point_mp.equals("M[1]")) {
            listaVertex.add(M[1]);
        }
        Cube.b_e += 0.01f;
        Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e), listaVertex));



        listaVertex = new ArrayList<Vertex>();
        listaVertex.add(Data.final_entity.corp_list.get(lista_P_midle.get(1).i_mc).poligon_list.get(lista_P_midle.get(1).j_mp).vertex_list.get(lista_P_midle.get(1).start_index_mp));
        if (lista_P_midle.get(1).point_mp.equals("M[0]")) {
            listaVertex.add(M[0]);
        }
        if (lista_P_midle.get(1).point_mp.equals("M[1]")) {
            listaVertex.add(M[1]);
        }
        Cube.b_e += 0.01f;
        Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e), listaVertex));


        listaVertex = new ArrayList<Vertex>();
        listaVertex.add(Data.final_entity.corp_list.get(lista_P_midle.get(1).i_mc).poligon_list.get(lista_P_midle.get(1).j_mp).vertex_list.get(lista_P_midle.get(1).end_index_mp));
        if (lista_P_midle.get(1).point_mp.equals("M[0]")) {
            listaVertex.add(M[0]);
        }
        if (lista_P_midle.get(1).point_mp.equals("M[1]")) {
            listaVertex.add(M[1]);
        }
        Cube.b_e += 0.01f;
        Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e), listaVertex));



       
    }

    static void resetData() {
        lista_P_Laterale.clear();
        lista_P_midle.clear();
    }

    static void printData() {

        for (int p = 0; p < lista_P_Laterale.size(); p++) {
            System.out.println("i=" + lista_P_Laterale.get(p).i_c + "    j=" + lista_P_Laterale.get(p).j_p + "     k= " + lista_P_Laterale.get(p).start_index + "      k+1= " + lista_P_Laterale.get(p).end_index + "       point= " + lista_P_Laterale.get(p).point);
        }

        for (int p = 0; p < lista_P_midle.size(); p++) {
            System.out.println("i=" + lista_P_midle.get(p).i_mc + "    j=" + lista_P_midle.get(p).j_mp + "     k= " + lista_P_midle.get(p).start_index_mp + "      k+1= " + lista_P_midle.get(p).end_index_mp + "       point= " + lista_P_midle.get(p).point_mp);
        }

    }

    static void filterLoadedData() {

        for (int p = 0; p < lista_P_Laterale.size() - 1; p++) {

            if (lista_P_Laterale.get(p).i_c == lista_P_Laterale.get(p + 1).i_c && lista_P_Laterale.get(p).j_p == lista_P_Laterale.get(p + 1).j_p) {

                temp_midle = new midleP();
                temp_midle.i_mc = lista_P_Laterale.get(p).i_c;
                temp_midle.j_mp = lista_P_Laterale.get(p).j_p;
                temp_midle.start_index_mp = lista_P_Laterale.get(p).start_index;
                temp_midle.end_index_mp = lista_P_Laterale.get(p).end_index;
                temp_midle.point_mp = lista_P_Laterale.get(p).point;
                lista_P_midle.add(temp_midle);

                temp_midle = new midleP();
                temp_midle.i_mc = lista_P_Laterale.get(p + 1).i_c;
                temp_midle.j_mp = lista_P_Laterale.get(p + 1).j_p;
                temp_midle.start_index_mp = lista_P_Laterale.get(p + 1).start_index;
                temp_midle.end_index_mp = lista_P_Laterale.get(p + 1).end_index;
                temp_midle.point_mp = lista_P_Laterale.get(p + 1).point;
                lista_P_midle.add(temp_midle);

                lista_P_Laterale.remove(p);
                lista_P_Laterale.remove(p);

            }

        }

    }

    static void loadData() {

        for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
            for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {

                for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {
                    if (k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size() - 1) {
                        rezultat = testMidle(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k), Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k + 1));
                    } else {
                        rezultat = testMidle(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k), Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(0));
                    }

                    if (rezultat.equals("M[0]")) {
                        temp_lateral = new LateralP();

                        if (k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size() - 1) {

                            temp_lateral.i_c = i;
                            temp_lateral.j_p = j;
                            temp_lateral.start_index = k;
                            temp_lateral.end_index = k + 1;
                            temp_lateral.point = "M[0]";
                            lista_P_Laterale.add(temp_lateral);
                        } else {

                            temp_lateral.i_c = i;
                            temp_lateral.j_p = j;
                            temp_lateral.start_index = k;
                            temp_lateral.end_index = 0;
                            temp_lateral.point = "M[0]";
                            lista_P_Laterale.add(temp_lateral);
                        }
                    }
                    if (rezultat.equals("M[1]")) {
                        temp_lateral = new LateralP();

                        if (k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size() - 1) {

                            temp_lateral.i_c = i;
                            temp_lateral.j_p = j;
                            temp_lateral.start_index = k;
                            temp_lateral.end_index = k + 1;
                            temp_lateral.point = "M[1]";
                            lista_P_Laterale.add(temp_lateral);
                        } else {

                            temp_lateral.i_c = i;
                            temp_lateral.j_p = j;
                            temp_lateral.start_index = k;
                            temp_lateral.end_index = 0;
                            temp_lateral.point = "M[1]";
                            lista_P_Laterale.add(temp_lateral);
                        }

                    }

                }


            }
        }

//----------------------------------------------------------------------------------    

    }

    static String testMidle(Vertex ver1, Vertex ver2) {
        testMidleResult = "0";

        if (ver1.x > ver2.x) {
            testVertex.x = ver2.x + (ver1.x - ver2.x) / 2;
        } else {
            testVertex.x = ver1.x + (ver2.x - ver1.x) / 2;
        }
        if (ver1.y > ver2.y) {
            testVertex.y = ver2.y + (ver1.y - ver2.y) / 2;
        } else {
            testVertex.y = ver1.y + (ver2.y - ver1.y) / 2;
        }
        if (ver1.z > ver2.z) {
            testVertex.z = ver2.z + (ver1.z - ver2.z) / 2;
        } else {
            testVertex.z = ver1.z + (ver2.z - ver1.z) / 2;
        }

        if (testVertex.x == M[0].x && testVertex.y == M[0].y && testVertex.z == M[0].z) {
            testMidleResult = "M[0]";
        }
        if (testVertex.x == M[1].x && testVertex.y == M[1].y && testVertex.z == M[1].z) {
            testMidleResult = "M[1]";
        }



        return testMidleResult;
    }

    static void calculateMidle() {
        M_index = 0;
        P_index = 0;

        for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {
            if (Data.final_cube_edge_array.get(i).ec_ambient.x_ac == 1 && Data.final_cube_edge_array.get(i).ec_ambient.y_ac == 0 && Data.final_cube_edge_array.get(i).ec_ambient.z_ac == 0) {

                P[P_index] = i;
                P_index++;
                Cube.r_v += 0.01f;
                M[M_index] = new Vertex(new C_ambient(Cube.r_v, Cube.g_v, Cube.b_v), 0, 0, 0);

                if (Data.final_cube_edge_array.get(i).vertex_list.get(0).x > Data.final_cube_edge_array.get(i).vertex_list.get(1).x) {
                    M[M_index].x = Data.final_cube_edge_array.get(i).vertex_list.get(1).x + (Data.final_cube_edge_array.get(i).vertex_list.get(0).x - Data.final_cube_edge_array.get(i).vertex_list.get(1).x) / 2;
                } else {
                    M[M_index].x = Data.final_cube_edge_array.get(i).vertex_list.get(0).x + (Data.final_cube_edge_array.get(i).vertex_list.get(1).x - Data.final_cube_edge_array.get(i).vertex_list.get(0).x) / 2;
                }
                if (Data.final_cube_edge_array.get(i).vertex_list.get(0).y > Data.final_cube_edge_array.get(i).vertex_list.get(1).y) {
                    M[M_index].y = Data.final_cube_edge_array.get(i).vertex_list.get(1).y + (Data.final_cube_edge_array.get(i).vertex_list.get(0).y - Data.final_cube_edge_array.get(i).vertex_list.get(1).y) / 2;
                } else {
                    M[M_index].y = Data.final_cube_edge_array.get(i).vertex_list.get(0).y + (Data.final_cube_edge_array.get(i).vertex_list.get(1).y - Data.final_cube_edge_array.get(i).vertex_list.get(0).y) / 2;
                }
                if (Data.final_cube_edge_array.get(i).vertex_list.get(0).z > Data.final_cube_edge_array.get(i).vertex_list.get(1).z) {
                    M[M_index].z = Data.final_cube_edge_array.get(i).vertex_list.get(1).z + (Data.final_cube_edge_array.get(i).vertex_list.get(0).z - Data.final_cube_edge_array.get(i).vertex_list.get(1).z) / 2;
                } else {
                    M[M_index].z = Data.final_cube_edge_array.get(i).vertex_list.get(0).z + (Data.final_cube_edge_array.get(i).vertex_list.get(1).z - Data.final_cube_edge_array.get(i).vertex_list.get(0).z) / 2;
                }

                M_index++;

            }
        }


    }
}
