package pkg3d_studio_max;

import java.util.ArrayList;

public class VertexUtilFunction {

    static int P[] = new int[2];
    static int Poz[] = new int[2];
    static int index;
    static boolean contains = false;
    static boolean contains2 = false;
    static boolean existStatus;
    static boolean ok;
    static ArrayList<Poligon> listaPoligoaneTemporara = new ArrayList<Poligon>();
    static ArrayList<Vertex> temp_vert;
    static ArrayList<Vertex> edge_temp_vert = new ArrayList<Vertex>();
    static ArrayList<Vertex> tempList;
    static Vertex temp;
    static  boolean TexturesStatus = false;
    
    static void fusionVertex() {

        loadVIndex();
        sortData();
        selectUsefulPoligons();
        ProcesUsefulPoligons();

        removeUnnecessaryEdge();
        addEdges();

        resetListaPoligoane();
        resetListaEdge();
        checkPoligonsVertexStatus();
        Normal3D.resetNVector();
        ProcessRequest.resetData();
    }

    static void checkPoligonsVertexStatus() {

        for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
            for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size() == 2) {
                    Data.final_entity.corp_list.get(i).poligon_list.remove(j);
                    j--;
                    if (j == -1) {
                        j++;
                    }
                }
            }
        }

    }

    static void addEdges() {

        for (int i = 0; i < edge_temp_vert.size(); i++) {
            tempList = new ArrayList<Vertex>();

            tempList.add(edge_temp_vert.get(i));
            tempList.add(Data.final_cube_vertex_array.get(Poz[1]));
            
            if(!edge_temp_vert.get(i).equals(Data.final_cube_vertex_array.get(Poz[1])))
            {
               if(!existDouble(edge_temp_vert.get(i)  , Data.final_cube_vertex_array.get(Poz[1])))
               {
                    Cube.b_e += 0.01f;
                    Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e), tempList));
               }
            }         

        }
        Data.final_cube_vertex_array.remove(Poz[0]);
    }
   public static   boolean existDouble(Vertex v1 , Vertex v2) {
   
      existStatus = false;
     
     
     for(int i=0 ; i<Data.final_cube_edge_array.size() ; i++)
     {
         if(Data.final_cube_edge_array.get(i).vertex_list.get(0).equals(v1) && Data.final_cube_edge_array.get(i).vertex_list.get(1).equals(v2)  || Data.final_cube_edge_array.get(i).vertex_list.get(1).equals(v1) && Data.final_cube_edge_array.get(i).vertex_list.get(0).equals(v2))          
         {
             existStatus = true;
         }
        
     }
     
       
   return existStatus;
   }
    
    static void removeUnnecessaryEdge() {

      //  et2:
et2:    for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {
            for (int j = 0; j < Data.final_cube_edge_array.get(i).vertex_list.size(); j++) {
                if (Data.final_cube_edge_array.get(i).vertex_list.get(j).equals(Data.final_cube_vertex_array.get(Poz[0]))) {

                    if (j == 0) {
                        edge_temp_vert.add(Data.final_cube_edge_array.get(i).vertex_list.get(1));
                    }
                    if (j == 1) {

                        edge_temp_vert.add(Data.final_cube_edge_array.get(i).vertex_list.get(0));
                    }

                    Data.final_cube_edge_array.remove(i);
                    i--;
                    continue et2;
                }

            }

        }

    }

    static void ProcesUsefulPoligons() {


        for (int i = 0; i < listaPoligoaneTemporara.size(); i++) {
            temp_vert = new ArrayList<Vertex>();

            for (int j = 0; j < listaPoligoaneTemporara.get(i).vertex_list.size(); j++) {
                if (listaPoligoaneTemporara.get(i).vertex_list.get(j).equals(Data.final_cube_vertex_array.get(Poz[0]))) {

                    ok = testPoligon(listaPoligoaneTemporara.get(i), Data.final_cube_vertex_array.get(Poz[1]));
                    if (ok == false) {
                        temp_vert.add(Data.final_cube_vertex_array.get(Poz[1]));
                    }

                } else {

                    temp_vert.add(listaPoligoaneTemporara.get(i).vertex_list.get(j));
                }

            }

            Cube.b_p += 0.01f;
        //  Cube.temp_poligon.add(new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p), new NormalV(0, 0, 1), temp_vert , TexturesStatus , null , null));
            Data.final_entity.corp_list.get(0).poligon_list.add(new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p), new NormalV(0, 0, 1), temp_vert , TexturesStatus , null , null));

        }

    }

    static boolean testPoligon(Poligon poli, Vertex ver) {
        contains2 = false;

        for (int i = 0; i < poli.vertex_list.size(); i++) {
            if (poli.vertex_list.get(i).equals(ver)) {
                contains2 = true;
            }
        }
        return contains2;
    }

    static void selectUsefulPoligons() {

        for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {

            et:
            for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                contains = false;

                for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {
                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).equals(Data.final_cube_vertex_array.get(Poz[0]))) {
                        listaPoligoaneTemporara.add(Data.final_entity.corp_list.get(i).poligon_list.get(j));
                        Data.final_entity.corp_list.get(i).poligon_list.remove(j);
                        j--;

                        continue et;
                    }

                }

            }

        }
    }

    static void sortData() {

        if (SelectedData.vert_select.get(0).x == Data.final_cube_vertex_array.get(P[0]).x && SelectedData.vert_select.get(0).y == Data.final_cube_vertex_array.get(P[0]).y && SelectedData.vert_select.get(0).z == Data.final_cube_vertex_array.get(P[0]).z) {
            Poz[0] = P[0];
            Poz[1] = P[1];

        }
        if (SelectedData.vert_select.get(0).x == Data.final_cube_vertex_array.get(P[1]).x && SelectedData.vert_select.get(0).y == Data.final_cube_vertex_array.get(P[1]).y && SelectedData.vert_select.get(0).z == Data.final_cube_vertex_array.get(P[1]).z) {
            Poz[0] = P[1];
            Poz[1] = P[0];

        }

    }

    static void loadVIndex() {

        index = 0;
        for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
            if (Data.final_cube_vertex_array.get(i).vc_ambient.x_ac == 1 && Data.final_cube_vertex_array.get(i).vc_ambient.y_ac == 0 && Data.final_cube_vertex_array.get(i).vc_ambient.z_ac == 0) {
                P[index] = i;
                index++;
            }
        }

    }

    static void resetListaEdge() {
        edge_temp_vert.clear();
    }

    static void resetListaPoligoane() {
        listaPoligoaneTemporara.clear();
    }
}
