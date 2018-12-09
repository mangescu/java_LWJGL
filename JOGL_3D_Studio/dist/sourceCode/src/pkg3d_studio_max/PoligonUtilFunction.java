package pkg3d_studio_max;

import com.sun.opengl.util.texture.Texture;
import java.util.ArrayList;

public class PoligonUtilFunction {

    static boolean execute = true;
    static boolean ok;
    static boolean okk;
    static boolean okkk;
    static boolean done;
    static ArrayList<Poligon> listaPoligoaneSelectate = new ArrayList<Poligon>();
    static ArrayList<Poligon> listaPoligoaneNoi = new ArrayList<Poligon>();
    static ArrayList<Vertex> listaPoints = new ArrayList<Vertex>();
    static ArrayList<Vertex> listaNewPoints = new ArrayList<Vertex>();
    static ArrayList<Edge> listaNewEdge = new ArrayList<Edge>();
    static ArrayList<Edge> listaUselessEdge = new ArrayList<Edge>();
    static Vertex point;
    static Edge edge;
    static ArrayList<Vertex> listaVert;
    static ArrayList<Vertex> temp_list;
    static ArrayList<Vertex> temp_list2;
    static Poligon poligon;
    static ArrayList<Vertex> moveVertexlist = new ArrayList<Vertex>();
    static ArrayList<Edge> listaEdge = new ArrayList<Edge>();
    static ArrayList<Edge> listaEdge2 = new ArrayList<Edge>();
    static boolean contains = false;
    
    
    static boolean TexturesStatus = false;
    static ArrayList<TexturesCoordinates>    textureCoordonate;
    static Texture    texturee ;
    
    
    static void extrude(String axis, String move_direction, float value) {

        if (axis.equals("x")) {
            if (execute == true) {
                getSelectedPoligons("x", value);
                createPoligons("x", value);
                getTopUsefulEdges();
                setTopEdges();


                createOtherPoligons();
                print();


                removeEdges();
                removePoligons();

                execute = false;
                Clear();
            }
            if (move_direction.equals("increse")) {
                moveVertexlist.clear();
                for (int i = 0; i < listaPoligoaneNoi.size(); i++) {
                    for (int k = 0; k < listaPoligoaneNoi.get(i).vertex_list.size(); k++) {
                        contains = false;
                        for (int p = 0; p < moveVertexlist.size(); p++) {
                            if (listaPoligoaneNoi.get(i).vertex_list.get(k) == moveVertexlist.get(p)) {
                                contains = true;
                            }
                        }

                        if (contains == true) {
                        } else {

                            moveVertexlist.add(listaPoligoaneNoi.get(i).vertex_list.get(k));

                            listaPoligoaneNoi.get(i).vertex_list.get(k).x += value;
                        }

                    }
                }

            }
            if (move_direction.equals("decrese")) {
                moveVertexlist.clear();
                for (int i = 0; i < listaPoligoaneNoi.size(); i++) {
                    for (int k = 0; k < listaPoligoaneNoi.get(i).vertex_list.size(); k++) {
                        contains = false;
                        for (int p = 0; p < moveVertexlist.size(); p++) {
                            if (listaPoligoaneNoi.get(i).vertex_list.get(k) == moveVertexlist.get(p)) {
                                contains = true;
                            }
                        }

                        if (contains == true) {
                        } else {

                            moveVertexlist.add(listaPoligoaneNoi.get(i).vertex_list.get(k));

                            listaPoligoaneNoi.get(i).vertex_list.get(k).x -= value;
                        }

                    }
                }

            }
        }
        if (axis.equals("y")) {
            if (execute == true) {
                getSelectedPoligons("y", value);
                createPoligons("y", value);
                getTopUsefulEdges();
                setTopEdges();
                createOtherPoligons();
                print();


                removeEdges();
                removePoligons();

                execute = false;
                Clear();
            }
            if (move_direction.equals("increse")) {
                moveVertexlist.clear();
                for (int i = 0; i < listaPoligoaneNoi.size(); i++) {
                    for (int k = 0; k < listaPoligoaneNoi.get(i).vertex_list.size(); k++) {
                        contains = false;
                        for (int p = 0; p < moveVertexlist.size(); p++) {
                            if (listaPoligoaneNoi.get(i).vertex_list.get(k) == moveVertexlist.get(p)) {
                                contains = true;
                            }
                        }

                        if (contains == true) {
                        } else {

                            moveVertexlist.add(listaPoligoaneNoi.get(i).vertex_list.get(k));

                            listaPoligoaneNoi.get(i).vertex_list.get(k).y += value;
                        }

                    }
                }

            }
            if (move_direction.equals("decrese")) {
                moveVertexlist.clear();
                for (int i = 0; i < listaPoligoaneNoi.size(); i++) {
                    for (int k = 0; k < listaPoligoaneNoi.get(i).vertex_list.size(); k++) {
                        contains = false;
                        for (int p = 0; p < moveVertexlist.size(); p++) {
                            if (listaPoligoaneNoi.get(i).vertex_list.get(k) == moveVertexlist.get(p)) {
                                contains = true;
                            }
                        }

                        if (contains == true) {
                        } else {

                            moveVertexlist.add(listaPoligoaneNoi.get(i).vertex_list.get(k));

                            listaPoligoaneNoi.get(i).vertex_list.get(k).y -= value;
                        }

                    }
                }

            }
        }
        if (axis.equals("z")) {
            if (execute == true) {
                getSelectedPoligons("z", value);
                createPoligons("z", value);
                getTopUsefulEdges();
                setTopEdges();
                createOtherPoligons();
                print();


                removeEdges();
                removePoligons();

                execute = false;
                Clear();
            }
            if (move_direction.equals("increse")) {
                moveVertexlist.clear();
                for (int i = 0; i < listaPoligoaneNoi.size(); i++) {
                    for (int k = 0; k < listaPoligoaneNoi.get(i).vertex_list.size(); k++) {
                        contains = false;
                        for (int p = 0; p < moveVertexlist.size(); p++) {
                            if (listaPoligoaneNoi.get(i).vertex_list.get(k) == moveVertexlist.get(p)) {
                                contains = true;
                            }
                        }

                        if (contains == true) {
                        } else {

                            moveVertexlist.add(listaPoligoaneNoi.get(i).vertex_list.get(k));
                            listaPoligoaneNoi.get(i).vertex_list.get(k).z += value;
                        }

                    }
                }

            }
            if (move_direction.equals("decrese")) {
                moveVertexlist.clear();
                for (int i = 0; i < listaPoligoaneNoi.size(); i++) {
                    for (int k = 0; k < listaPoligoaneNoi.get(i).vertex_list.size(); k++) {
                        contains = false;
                        for (int p = 0; p < moveVertexlist.size(); p++) {
                            if (listaPoligoaneNoi.get(i).vertex_list.get(k) == moveVertexlist.get(p)) {
                                contains = true;
                            }
                        }

                        if (contains == true) {
                        } else {

                            moveVertexlist.add(listaPoligoaneNoi.get(i).vertex_list.get(k));

                            listaPoligoaneNoi.get(i).vertex_list.get(k).z -= value;
                        }

                    }
                }

            }
        }

        Normal3D.resetNVector();
    }

    static void print() {

        for (int i = 0; i < listaPoligoaneSelectate.size(); i++) {
            //    System.out.println("Poligon_selectat["+i+"]");
            //    System.out.println();
            for (int k = 0; k < listaPoligoaneSelectate.get(i).vertex_list.size(); k++) {
                //      System.out.println(listaPoligoaneSelectate.get(i).vertex_list.get(k).x+"    "+listaPoligoaneSelectate.get(i).vertex_list.get(k).y+"    "+listaPoligoaneSelectate.get(i).vertex_list.get(k).z);
            }
        }
        for (int i = 0; i < listaPoligoaneNoi.size(); i++) {
            //    System.out.println("listaPoligoaneNoi["+i+"]");
            //    System.out.println();
            for (int k = 0; k < listaPoligoaneNoi.get(i).vertex_list.size(); k++) {
                //        System.out.println(listaPoligoaneNoi.get(i).vertex_list.get(k).x+"    "+listaPoligoaneNoi.get(i).vertex_list.get(k).y+"    "+listaPoligoaneNoi.get(i).vertex_list.get(k).z);
            }
        }
        //   System.out.println();


        for (int i = 0; i < listaPoints.size(); i++) {
            //  System.out.println("vertex ["+i+"]");
        }
        //   System.out.println();
        for (int i = 0; i < listaNewPoints.size(); i++) {
            //  System.out.println("vertex new ["+i+"]");
        }
        //   System.out.println();

        for (int i = 0; i < listaNewEdge.size(); i++) {
            //   System.out.println("edge new ["+i+"]");
        }
        //    System.out.println();


        for (int i = 0; i < listaEdge.size(); i++) {
            //  System.out.println("listaEdge  ["+i+"]");
        }

        for (int i = 0; i < listaNewEdge.size(); i++) {
            //  System.out.println("listaNewEdge  ["+i+"]");
        }
        for (int i = 0; i < listaUselessEdge.size(); i++) {
        //    System.out.println("listaUselessEdge  [" + i + "]");
        }

    }

    static void removeEdges() {


        for (int i = 0; i < listaPoligoaneSelectate.size(); i++) {
            for (int k = 0; k < listaPoligoaneSelectate.get(i).vertex_list.size(); k++) {
                okkk = false;

                listaVert = new ArrayList<Vertex>();

                if (k < listaPoligoaneSelectate.get(i).vertex_list.size() - 1) {
                    point = new Vertex(new C_ambient(0, 0, 0), 0, 0, 0);
                    point = listaPoligoaneSelectate.get(i).vertex_list.get(k);
                    listaVert.add(point);

                    point = new Vertex(new C_ambient(0, 0, 0), 0, 0, 0);
                    point = listaPoligoaneSelectate.get(i).vertex_list.get(k + 1);
                    listaVert.add(point);

                    edge = new Edge(new C_ambient(0, 0, 0), listaVert);


                    for (int v = 0; v < listaEdge2.size(); v++) {

                        if (listaEdge2.get(v).vertex_list.get(0) == edge.vertex_list.get(1) && listaEdge2.get(v).vertex_list.get(1) == edge.vertex_list.get(0) || listaEdge2.get(v).vertex_list.get(0) == edge.vertex_list.get(0) && listaEdge2.get(v).vertex_list.get(1) == edge.vertex_list.get(1)) {
                            okkk = true;
                        }
                    }

                    if (okkk == false) {
                        listaEdge2.add(edge);
                    } else {
                        listaEdge.add(edge);
                    }


                } else {

                    point = new Vertex(new C_ambient(0, 0, 0), 0, 0, 0);
                    point = listaPoligoaneSelectate.get(i).vertex_list.get(k);
                    listaVert.add(point);


                    point = new Vertex(new C_ambient(0, 0, 0), 0, 0, 0);
                    point = listaPoligoaneSelectate.get(i).vertex_list.get(0);
                    listaVert.add(point);


                    edge = new Edge(new C_ambient(0, 0, 0), listaVert);

                    for (int v = 0; v < listaEdge2.size(); v++) {

                        if (listaEdge2.get(v).vertex_list.get(0) == edge.vertex_list.get(1) && listaEdge2.get(v).vertex_list.get(1) == edge.vertex_list.get(0) || listaEdge2.get(v).vertex_list.get(0) == edge.vertex_list.get(0) && listaEdge2.get(v).vertex_list.get(1) == edge.vertex_list.get(1)) {
                            okkk = true;
                        }
                    }

                    if (okkk == false) {
                        listaEdge2.add(edge);
                    } else {
                        listaEdge.add(edge);
                    }


                }

            }

        }


        for (int i = 0; i < listaEdge2.size(); i++) {
            //  System.out.println("listaEdge2  ["+i+"]");
        }
        System.out.println();
        for (int i = 0; i < listaEdge.size(); i++) {
            //   System.out.println("listaEdge  ["+i+"]");
        }





        boolean containsEdge = false;
        int poz = 0;
        for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {
            containsEdge = false;
            for (int p = 0; p < listaEdge.size(); p++) {
                if (Data.final_cube_edge_array.get(i).vertex_list.get(0) == listaEdge.get(p).vertex_list.get(0) && Data.final_cube_edge_array.get(i).vertex_list.get(1) == listaEdge.get(p).vertex_list.get(1) || Data.final_cube_edge_array.get(i).vertex_list.get(0) == listaEdge.get(p).vertex_list.get(1) && Data.final_cube_edge_array.get(i).vertex_list.get(1) == listaEdge.get(p).vertex_list.get(0)) {
                    containsEdge = true;

                }
            }
            if (containsEdge == true) {
                Data.final_cube_edge_array.remove(i);
                i--;
            }
            if (i == -1) {
                i++;
            }
        }


    }

    static void removePoligons() {

        for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
            for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                for (int p = 0; p < listaPoligoaneSelectate.size(); p++) {
                    if (listaPoligoaneSelectate.get(p).equals(Data.final_entity.corp_list.get(i).poligon_list.get(j))) {
                        Data.final_entity.corp_list.get(i).poligon_list.remove(j);
                        j--;
                    }
                    if (j == -1) {
                        j++;
                    }
                }
            }
        }
    }

    static void getSelectedPoligons(String axis, float value) {

        for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
            for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                if (Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac == 1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac == 0 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac == 0) {
                    listaPoligoaneSelectate.add(Data.final_entity.corp_list.get(i).poligon_list.get(j));

                    for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {
                        if (listaPoints.contains(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k))) {
                        } else {
                            listaPoints.add(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k));

                            Cube.r_v += 0.01f;
                            point = new Vertex(new C_ambient(Cube.r_v, Cube.g_v, Cube.b_v), 0, 0, 0);

                            point.x = Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).x;
                            point.y = Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).y;
                            point.z = Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).z;

                            if (axis.equals("x")) {
                                point.x += value;
                            }
                            if (axis.equals("y")) {
                                point.y += value;
                            }
                            if (axis.equals("z")) {
                                point.z += value;
                            }

                            listaNewPoints.add(point);
                            Data.final_cube_vertex_array.add(point);
                        }
                    }
                }
            }

        }

    }

    static void createOtherPoligons() {

        for (int i = 0; i < listaPoligoaneNoi.size(); i++) {

            for (int k = 0; k < listaPoligoaneNoi.get(i).vertex_list.size(); k++) {
                listaVert = new ArrayList<Vertex>();
                done = false;

                if (k < listaPoligoaneNoi.get(i).vertex_list.size() - 1) {

                    for (int p = 0; p < listaUselessEdge.size(); p++) {
                        if (listaUselessEdge.get(p).vertex_list.get(0).equals(listaPoligoaneNoi.get(i).vertex_list.get(k)) && listaUselessEdge.get(p).vertex_list.get(1).equals(listaPoligoaneNoi.get(i).vertex_list.get(k + 1)) || listaUselessEdge.get(p).vertex_list.get(1).equals(listaPoligoaneNoi.get(i).vertex_list.get(k)) && listaUselessEdge.get(p).vertex_list.get(0).equals(listaPoligoaneNoi.get(i).vertex_list.get(k + 1))) {
                            done = true;
                        }
                    }

                    if (done == false) {
                        temp_list = new ArrayList<Vertex>();
                        temp_list2 = new ArrayList<Vertex>();

                        point = new Vertex();
                        point = listaPoligoaneNoi.get(i).vertex_list.get(k);
                        listaVert.add(point);
                        temp_list.add(point);

                        point = new Vertex();
                        point = listaPoligoaneNoi.get(i).vertex_list.get(k + 1);
                        listaVert.add(point);
                        temp_list2.add(point);

                        point = new Vertex();
                        point = listaPoligoaneSelectate.get(i).vertex_list.get(k + 1);
                        listaVert.add(point);
                        temp_list2.add(point);

                        point = new Vertex();
                        point = listaPoligoaneSelectate.get(i).vertex_list.get(k);
                        listaVert.add(point);
                        temp_list.add(point);

                        textureCoordonate = new ArrayList<TexturesCoordinates>();
                        Cube.b_p += 0.01f;
                        poligon = new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p), new NormalV(0, 0, 1), listaVert , TexturesStatus , textureCoordonate , texturee);
                      //  Cube.temp_poligon.add(poligon);
                        Data.final_entity.corp_list.get(0).poligon_list.add(poligon);

                        textureCoordonate = new ArrayList<TexturesCoordinates>();
                        Cube.b_e += 0.01f;
                        Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e), temp_list));
               /*
               //         Cube.b_e += 0.01f;
               //         Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e), temp_list2));
               */


                    }

                } else {


                    for (int p = 0; p < listaUselessEdge.size(); p++) {
                        if (listaUselessEdge.get(p).vertex_list.get(0).equals(listaPoligoaneNoi.get(i).vertex_list.get(k)) && listaUselessEdge.get(p).vertex_list.get(1).equals(listaPoligoaneNoi.get(i).vertex_list.get(0)) || listaUselessEdge.get(p).vertex_list.get(1).equals(listaPoligoaneNoi.get(i).vertex_list.get(k)) && listaUselessEdge.get(p).vertex_list.get(0).equals(listaPoligoaneNoi.get(i).vertex_list.get(0))) {
                            done = true;
                        }

                    }

                    if (done == false) {
                        
                        temp_list = new ArrayList<Vertex>();
                        temp_list2 = new ArrayList<Vertex>();
                        
                        point = new Vertex();
                        point = listaPoligoaneNoi.get(i).vertex_list.get(k);
                        listaVert.add(point);
                        temp_list.add(point);

                        
                        point = new Vertex();
                        point = listaPoligoaneNoi.get(i).vertex_list.get(0);
                        listaVert.add(point);
                        temp_list2.add(point);

                        
                        point = new Vertex();
                        point = listaPoligoaneSelectate.get(i).vertex_list.get(0);
                        listaVert.add(point);
                        temp_list2.add(point);
                        
                        
                        point = new Vertex();
                        point = listaPoligoaneSelectate.get(i).vertex_list.get(k);
                        listaVert.add(point);
                        temp_list.add(point);

                        textureCoordonate = new ArrayList<TexturesCoordinates>();
                        Cube.b_p += 0.01f;
                        poligon = new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p), new NormalV(0, 0, 1), listaVert , TexturesStatus , textureCoordonate , texturee);
                    //    Cube.temp_poligon.add(poligon);
                        Data.final_entity.corp_list.get(0).poligon_list.add(poligon);
                        
                        Cube.b_e += 0.01f;
                        Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e), temp_list));
                /*   
                   //     Cube.b_e += 0.01f;
                   //     Data.final_cube_edge_array.add(new Edge(new C_ambient(Cube.r_e, Cube.g_e, Cube.b_e), temp_list2));
               */

                        
                        
                    }



                }

            }

        }
    }

    static void createPoligons(String axis, float value) {

        int poz = 0;

        for (int i = 0; i < listaPoligoaneSelectate.size(); i++) {
            listaVert = new ArrayList<Vertex>();

            for (int k = 0; k < listaPoligoaneSelectate.get(i).vertex_list.size(); k++) {
                ok = false;

                for (int p = 0; p < listaNewPoints.size(); p++) {
                    if (axis.equals("x")) {
                        if (listaPoligoaneSelectate.get(i).vertex_list.get(k).x + value == listaNewPoints.get(p).x && listaPoligoaneSelectate.get(i).vertex_list.get(k).y == listaNewPoints.get(p).y && listaPoligoaneSelectate.get(i).vertex_list.get(k).z == listaNewPoints.get(p).z) {
                            ok = true;
                            poz = p;
                        }
                    }

                    if (axis.equals("y")) {
                        if (listaPoligoaneSelectate.get(i).vertex_list.get(k).x == listaNewPoints.get(p).x && listaPoligoaneSelectate.get(i).vertex_list.get(k).y + value == listaNewPoints.get(p).y && listaPoligoaneSelectate.get(i).vertex_list.get(k).z == listaNewPoints.get(p).z) {
                            ok = true;
                            poz = p;
                        }
                    }

                    if (axis.equals("z")) {
                        if (listaPoligoaneSelectate.get(i).vertex_list.get(k).x == listaNewPoints.get(p).x && listaPoligoaneSelectate.get(i).vertex_list.get(k).y == listaNewPoints.get(p).y && listaPoligoaneSelectate.get(i).vertex_list.get(k).z + value == listaNewPoints.get(p).z) {
                            ok = true;
                            poz = p;
                        }
                    }

                }

                if (ok == true) {
                    listaVert.add(listaNewPoints.get(poz));
                } else {
                    listaVert.add(listaPoligoaneSelectate.get(i).vertex_list.get(k));
                }

            }

            textureCoordonate = new ArrayList<TexturesCoordinates>();
            poligon = new Poligon(new C_ambient(Cube.r_p, Cube.g_p, Cube.b_p), new NormalV(0, 0, 1), listaVert , TexturesStatus , textureCoordonate , texturee);
            Cube.b_p += 0.01f;
           // Cube.temp_poligon.add(poligon);
            Data.final_entity.corp_list.get(0).poligon_list.add(poligon);
            listaPoligoaneNoi.add(poligon);

        }


    }

    static void setTopEdges() {

        for (int i = 0; i < listaNewEdge.size(); i++) {

            Cube.b_e += 0.01f;

            listaNewEdge.get(i).ec_ambient.x_ac = Cube.r_e;
            listaNewEdge.get(i).ec_ambient.y_ac = Cube.g_e;
            listaNewEdge.get(i).ec_ambient.z_ac = Cube.b_e;

            Data.final_cube_edge_array.add(listaNewEdge.get(i));
        }
    }

    static void getTopUsefulEdges() {

        for (int i = 0; i < listaPoligoaneNoi.size(); i++) {
            for (int k = 0; k < listaPoligoaneNoi.get(i).vertex_list.size(); k++) {
                okk = false;

                listaVert = new ArrayList<Vertex>();

                if (k < listaPoligoaneNoi.get(i).vertex_list.size() - 1) {
                    point = new Vertex(new C_ambient(0, 0, 0), 0, 0, 0);
                    point = listaPoligoaneNoi.get(i).vertex_list.get(k);
                    listaVert.add(point);

                    point = new Vertex(new C_ambient(0, 0, 0), 0, 0, 0);
                    point = listaPoligoaneNoi.get(i).vertex_list.get(k + 1);
                    listaVert.add(point);

                    edge = new Edge(new C_ambient(0, 0, 0), listaVert);


                    for (int v = 0; v < listaNewEdge.size(); v++) {
                        if (listaNewEdge.get(v).vertex_list.get(0).equals(edge.vertex_list.get(1)) && listaNewEdge.get(v).vertex_list.get(1).equals(edge.vertex_list.get(0)) || listaNewEdge.get(v).vertex_list.get(1).equals(edge.vertex_list.get(1)) && listaNewEdge.get(v).vertex_list.get(0).equals(edge.vertex_list.get(0))) {
                            okk = true;
                        }
                    }

                    if (okk == false) {
                        listaNewEdge.add(edge);
                    } else {
                        listaUselessEdge.add(edge);
                    }


                } else {

                    point = new Vertex(new C_ambient(0, 0, 0), 0, 0, 0);
                    point = listaPoligoaneNoi.get(i).vertex_list.get(k);
                    listaVert.add(point);



                    point = new Vertex(new C_ambient(0, 0, 0), 0, 0, 0);
                    point = listaPoligoaneNoi.get(i).vertex_list.get(0);
                    listaVert.add(point);


                    edge = new Edge(new C_ambient(0, 0, 0), listaVert);

                    for (int v = 0; v < listaNewEdge.size(); v++) {
                        if (listaNewEdge.get(v).vertex_list.get(0).equals(edge.vertex_list.get(1)) && listaNewEdge.get(v).vertex_list.get(1).equals(edge.vertex_list.get(0)) || listaNewEdge.get(v).vertex_list.get(0).equals(edge.vertex_list.get(0)) && listaNewEdge.get(v).vertex_list.get(1).equals(edge.vertex_list.get(1))) {
                            okk = true;
                        }
                    }

                    if (okk == false) {
                        listaNewEdge.add(edge);
                    } else {
                        listaUselessEdge.add(edge);
                    }


                }

            }

        }
    }

    static void Clear() {

        listaNewPoints.clear();
        listaPoints.clear();

        listaNewEdge.clear();
        listaUselessEdge.clear();
        listaPoligoaneSelectate.clear();
        listaNewPoints.clear();
        moveVertexlist.clear();
        listaEdge.clear();
        listaEdge2.clear();
    }

    static void reset_listaPoligoaneSelectate() {
        listaPoligoaneNoi.clear();
    }
}