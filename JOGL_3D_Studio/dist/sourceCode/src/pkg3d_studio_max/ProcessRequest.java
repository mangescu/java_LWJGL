package pkg3d_studio_max;

import java.util.ArrayList;
import java.util.Vector;

public class ProcessRequest {

    static ArrayList<Vertex> vert = new ArrayList<Vertex>();
    static boolean exist = false;

    static void delete(String type) {
        if (type.equals("corp")) {
            Data.final_entity.corp_list.clear();
        }
        if (type.equals("poligon")) {

            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {

                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {

                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac == 1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac == 0 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac == 0) {
                        Data.final_entity.corp_list.get(i).poligon_list.remove(j);
                        j--;
                    }

                }
            }

        }
        if (type.equals("edge")) {
            boolean vert11;
            boolean vert22;
            boolean ok3;

            for (int p = 0; p < SelectedData.edge_select.size(); p++) {
                vert11 = false;
                vert22 = false;


                for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
                    E2:
                    for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {

                        vert11 = false;
                        vert22 = false;

                        for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {

                            if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).x == SelectedData.edge_select.get(p).x1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).y == SelectedData.edge_select.get(p).y1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).z == SelectedData.edge_select.get(p).z1) {
                                vert11 = true;
                            }
                            if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).x == SelectedData.edge_select.get(p).x2 && Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).y == SelectedData.edge_select.get(p).y2 && Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).z == SelectedData.edge_select.get(p).z2) {
                                vert22 = true;
                            }
                        }

                        if (vert11 == true && vert22 == true) {
                            Data.final_entity.corp_list.get(i).poligon_list.remove(j);
                            j--;
                            continue E2;
                        }

                    }
                }


            }
        }

        if (type.equals("vertex")) {
            for (int p = 0; p < SelectedData.vert_select.size(); p++) {
                for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
                    E1:
                    for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                        for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {
                            if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).x == SelectedData.vert_select.get(p).x && Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).y == SelectedData.vert_select.get(p).y && Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).z == SelectedData.vert_select.get(p).z) {
                                Data.final_entity.corp_list.get(i).poligon_list.remove(j);
                                j--;
                                continue E1;
                            }
                        }
                    }

                }

            }

        }



        resetData();
    }

    static void resetData() {
        boolean vert1;
        boolean vert2;
        boolean ok;

        for (int p = 0; p < Data.final_cube_edge_array.size(); p++) {


            vert1 = false;
            vert2 = false;

            ok = false;

            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {

                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {

                    vert1 = false;
                    vert2 = false;

                    for (int m = 0; m < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); m++) {
                        if (Data.final_cube_edge_array.get(p).vertex_list.get(0).x == Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(m).x && Data.final_cube_edge_array.get(p).vertex_list.get(0).y == Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(m).y && Data.final_cube_edge_array.get(p).vertex_list.get(0).z == Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(m).z) {
                            vert1 = true;
                        }
                        if (Data.final_cube_edge_array.get(p).vertex_list.get(1).x == Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(m).x && Data.final_cube_edge_array.get(p).vertex_list.get(1).y == Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(m).y && Data.final_cube_edge_array.get(p).vertex_list.get(1).z == Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(m).z) {
                            vert2 = true;
                        }
                    }

                    if (vert1 == true && vert2 == true) {
                        ok = true;
                    }

                }

            }



            if (ok == false) {
                Data.final_cube_edge_array.remove(p);
                p--;
            }

        }


        for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
            boolean ok2 = false;

            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                    for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {
                        if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == Data.final_cube_vertex_array.get(h)) {
                            ok2 = true;
                        }
                    }
                }
            }
            if (ok2 == false) {
                Data.final_cube_vertex_array.remove(h);
                h--;
            }

        }






    }

    static void move(String entity, String type, String axis, float value) {

        if (entity.equals("corp") && type.equals("increse") && axis.equals("x")) {

            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                Data.final_cube_vertex_array.get(i).x += value;
            }

        }
        if (entity.equals("corp") && type.equals("decrese") && axis.equals("x")) {

            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                Data.final_cube_vertex_array.get(i).x -= value;
            }

        }
        if (entity.equals("corp") && type.equals("increse") && axis.equals("y")) {

            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                Data.final_cube_vertex_array.get(i).y += value;
            }

        }
        if (entity.equals("corp") && type.equals("decrese") && axis.equals("y")) {

            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                Data.final_cube_vertex_array.get(i).y -= value;
            }

        }
        if (entity.equals("corp") && type.equals("increse") && axis.equals("z")) {

            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                Data.final_cube_vertex_array.get(i).z += value;
            }

        }
        if (entity.equals("corp") && type.equals("decrese") && axis.equals("z")) {

            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                Data.final_cube_vertex_array.get(i).z -= value;
            }

        }
        if (entity.equals("poligon") && type.equals("increse") && axis.equals("x")) {

            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac == 1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac == 0 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac == 0) {
                        for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {

                            exist = false;
                            for (int p = 0; p < vert.size(); p++) {
                                if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == vert.get(p)) {
                                    exist = true;
                                }
                            }
                            if (exist == true) {
                            } else {
                                vert.add(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k));
                                for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == Data.final_cube_vertex_array.get(h)) {
                                        Data.final_cube_vertex_array.get(h).x += value;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            vert.clear();

        }

        if (entity.equals("poligon") && type.equals("decrese") && axis.equals("x")) {

            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac == 1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac == 0 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac == 0) {
                        for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {

                            exist = false;
                            for (int p = 0; p < vert.size(); p++) {
                                if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == vert.get(p)) {
                                    exist = true;
                                }
                            }
                            if (exist == true) {
                            } else {
                                vert.add(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k));
                                for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == Data.final_cube_vertex_array.get(h)) {
                                        Data.final_cube_vertex_array.get(h).x -= value;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            vert.clear();
        }

        if (entity.equals("poligon") && type.equals("increse") && axis.equals("y")) {
            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac == 1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac == 0 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac == 0) {
                        for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {

                            exist = false;
                            for (int p = 0; p < vert.size(); p++) {
                                if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == vert.get(p)) {
                                    exist = true;
                                }
                            }
                            if (exist == true) {
                            } else {
                                vert.add(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k));
                                for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == Data.final_cube_vertex_array.get(h)) {
                                        Data.final_cube_vertex_array.get(h).y += value;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            vert.clear();


        }

        if (entity.equals("poligon") && type.equals("decrese") && axis.equals("y")) {
            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac == 1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac == 0 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac == 0) {
                        for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {

                            exist = false;
                            for (int p = 0; p < vert.size(); p++) {
                                if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == vert.get(p)) {
                                    exist = true;
                                }
                            }
                            if (exist == true) {
                            } else {
                                vert.add(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k));
                                for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == Data.final_cube_vertex_array.get(h)) {
                                        Data.final_cube_vertex_array.get(h).y -= value;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            vert.clear();


        }
        if (entity.equals("poligon") && type.equals("increse") && axis.equals("z")) {
            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac == 1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac == 0 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac == 0) {
                        for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {

                            exist = false;
                            for (int p = 0; p < vert.size(); p++) {
                                if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == vert.get(p)) {
                                    exist = true;
                                }
                            }
                            if (exist == true) {
                            } else {
                                vert.add(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k));
                                for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == Data.final_cube_vertex_array.get(h)) {
                                        Data.final_cube_vertex_array.get(h).z += value;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            vert.clear();


        }
        if (entity.equals("poligon") && type.equals("decrese") && axis.equals("z")) {
            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {
                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {
                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac == 1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac == 0 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac == 0) {
                        for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {

                            exist = false;
                            for (int p = 0; p < vert.size(); p++) {
                                if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == vert.get(p)) {
                                    exist = true;
                                }
                            }
                            if (exist == true) {
                            } else {
                                vert.add(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k));
                                for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                    if (Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k) == Data.final_cube_vertex_array.get(h)) {
                                        Data.final_cube_vertex_array.get(h).z -= value;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            vert.clear();

        }
        if (entity.equals("edge") && type.equals("increse") && axis.equals("x")) {

            for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {
                if (Data.final_cube_edge_array.get(i).ec_ambient.x_ac == 1 && Data.final_cube_edge_array.get(i).ec_ambient.y_ac == 0 && Data.final_cube_edge_array.get(i).ec_ambient.z_ac == 0) {
                    for (int j = 0; j < Data.final_cube_edge_array.get(i).vertex_list.size(); j++) {
                        exist = false;

                        for (int p = 0; p < vert.size(); p++) {
                            if (vert.get(p) == Data.final_cube_edge_array.get(i).vertex_list.get(j)) {
                                exist = true;
                            }
                        }
                        if (exist == true) {
                        } else {
                            vert.add(Data.final_cube_edge_array.get(i).vertex_list.get(j));
                            for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                if (Data.final_cube_edge_array.get(i).vertex_list.get(j) == Data.final_cube_vertex_array.get(h)) {
                                    Data.final_cube_vertex_array.get(h).x += value;
                                }
                            }
                        }
                    }
                }
            }
            vert.clear();
        }

        if (entity.equals("edge") && type.equals("decrese") && axis.equals("x")) {
            for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {
                if (Data.final_cube_edge_array.get(i).ec_ambient.x_ac == 1 && Data.final_cube_edge_array.get(i).ec_ambient.y_ac == 0 && Data.final_cube_edge_array.get(i).ec_ambient.z_ac == 0) {
                    for (int j = 0; j < Data.final_cube_edge_array.get(i).vertex_list.size(); j++) {
                        exist = false;

                        for (int p = 0; p < vert.size(); p++) {
                            if (vert.get(p) == Data.final_cube_edge_array.get(i).vertex_list.get(j)) {
                                exist = true;
                            }
                        }
                        if (exist == true) {
                        } else {
                            vert.add(Data.final_cube_edge_array.get(i).vertex_list.get(j));
                            for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                if (Data.final_cube_edge_array.get(i).vertex_list.get(j) == Data.final_cube_vertex_array.get(h)) {
                                    Data.final_cube_vertex_array.get(h).x -= value;
                                }
                            }
                        }
                    }
                }
            }
            vert.clear();
        }

        if (entity.equals("edge") && type.equals("increse") && axis.equals("y")) {
            for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {
                if (Data.final_cube_edge_array.get(i).ec_ambient.x_ac == 1 && Data.final_cube_edge_array.get(i).ec_ambient.y_ac == 0 && Data.final_cube_edge_array.get(i).ec_ambient.z_ac == 0) {
                    for (int j = 0; j < Data.final_cube_edge_array.get(i).vertex_list.size(); j++) {
                        exist = false;

                        for (int p = 0; p < vert.size(); p++) {
                            if (vert.get(p) == Data.final_cube_edge_array.get(i).vertex_list.get(j)) {
                                exist = true;
                            }
                        }
                        if (exist == true) {
                        } else {
                            vert.add(Data.final_cube_edge_array.get(i).vertex_list.get(j));
                            for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                if (Data.final_cube_edge_array.get(i).vertex_list.get(j) == Data.final_cube_vertex_array.get(h)) {
                                    Data.final_cube_vertex_array.get(h).y += value;
                                }
                            }
                        }
                    }
                }
            }
            vert.clear();
        }

        if (entity.equals("edge") && type.equals("decrese") && axis.equals("y")) {
            for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {
                if (Data.final_cube_edge_array.get(i).ec_ambient.x_ac == 1 && Data.final_cube_edge_array.get(i).ec_ambient.y_ac == 0 && Data.final_cube_edge_array.get(i).ec_ambient.z_ac == 0) {
                    for (int j = 0; j < Data.final_cube_edge_array.get(i).vertex_list.size(); j++) {
                        exist = false;

                        for (int p = 0; p < vert.size(); p++) {
                            if (vert.get(p) == Data.final_cube_edge_array.get(i).vertex_list.get(j)) {
                                exist = true;
                            }
                        }
                        if (exist == true) {
                        } else {
                            vert.add(Data.final_cube_edge_array.get(i).vertex_list.get(j));
                            for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                if (Data.final_cube_edge_array.get(i).vertex_list.get(j) == Data.final_cube_vertex_array.get(h)) {
                                    Data.final_cube_vertex_array.get(h).y -= value;
                                }
                            }
                        }
                    }
                }
            }
            vert.clear();
        }
        if (entity.equals("edge") && type.equals("increse") && axis.equals("z")) {
            for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {
                if (Data.final_cube_edge_array.get(i).ec_ambient.x_ac == 1 && Data.final_cube_edge_array.get(i).ec_ambient.y_ac == 0 && Data.final_cube_edge_array.get(i).ec_ambient.z_ac == 0) {
                    for (int j = 0; j < Data.final_cube_edge_array.get(i).vertex_list.size(); j++) {
                        exist = false;

                        for (int p = 0; p < vert.size(); p++) {
                            if (vert.get(p) == Data.final_cube_edge_array.get(i).vertex_list.get(j)) {
                                exist = true;
                            }
                        }
                        if (exist == true) {
                        } else {
                            vert.add(Data.final_cube_edge_array.get(i).vertex_list.get(j));
                            for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                if (Data.final_cube_edge_array.get(i).vertex_list.get(j) == Data.final_cube_vertex_array.get(h)) {
                                    Data.final_cube_vertex_array.get(h).z += value;
                                }
                            }
                        }
                    }
                }
            }
            vert.clear();
        }

        if (entity.equals("edge") && type.equals("decrese") && axis.equals("z")) {
            for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {
                if (Data.final_cube_edge_array.get(i).ec_ambient.x_ac == 1 && Data.final_cube_edge_array.get(i).ec_ambient.y_ac == 0 && Data.final_cube_edge_array.get(i).ec_ambient.z_ac == 0) {
                    for (int j = 0; j < Data.final_cube_edge_array.get(i).vertex_list.size(); j++) {
                        exist = false;

                        for (int p = 0; p < vert.size(); p++) {
                            if (vert.get(p) == Data.final_cube_edge_array.get(i).vertex_list.get(j)) {
                                exist = true;
                            }
                        }
                        if (exist == true) {
                        } else {
                            vert.add(Data.final_cube_edge_array.get(i).vertex_list.get(j));
                            for (int h = 0; h < Data.final_cube_vertex_array.size(); h++) {
                                if (Data.final_cube_edge_array.get(i).vertex_list.get(j) == Data.final_cube_vertex_array.get(h)) {
                                    Data.final_cube_vertex_array.get(h).z -= value;
                                }
                            }
                        }
                    }
                }
            }
            vert.clear();
        }

        if (entity.equals("vertex") && type.equals("increse") && axis.equals("x")) {
            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                if (Data.final_cube_vertex_array.get(i).vc_ambient.x_ac == 1 && Data.final_cube_vertex_array.get(i).vc_ambient.y_ac == 0 && Data.final_cube_vertex_array.get(i).vc_ambient.z_ac == 0) {

                    Data.final_cube_vertex_array.get(i).x += value;
                }
            }
        }
        if (entity.equals("vertex") && type.equals("decrese") && axis.equals("x")) {
            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                if (Data.final_cube_vertex_array.get(i).vc_ambient.x_ac == 1 && Data.final_cube_vertex_array.get(i).vc_ambient.y_ac == 0 && Data.final_cube_vertex_array.get(i).vc_ambient.z_ac == 0) {

                    Data.final_cube_vertex_array.get(i).x -= value;
                }
            }
        }
        if (entity.equals("vertex") && type.equals("increse") && axis.equals("y")) {
            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                if (Data.final_cube_vertex_array.get(i).vc_ambient.x_ac == 1 && Data.final_cube_vertex_array.get(i).vc_ambient.y_ac == 0 && Data.final_cube_vertex_array.get(i).vc_ambient.z_ac == 0) {

                    Data.final_cube_vertex_array.get(i).y += value;
                }
            }
        }
        if (entity.equals("vertex") && type.equals("decrese") && axis.equals("y")) {
            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                if (Data.final_cube_vertex_array.get(i).vc_ambient.x_ac == 1 && Data.final_cube_vertex_array.get(i).vc_ambient.y_ac == 0 && Data.final_cube_vertex_array.get(i).vc_ambient.z_ac == 0) {

                    Data.final_cube_vertex_array.get(i).y -= value;
                }
            }
        }
        if (entity.equals("vertex") && type.equals("increse") && axis.equals("z")) {
            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                if (Data.final_cube_vertex_array.get(i).vc_ambient.x_ac == 1 && Data.final_cube_vertex_array.get(i).vc_ambient.y_ac == 0 && Data.final_cube_vertex_array.get(i).vc_ambient.z_ac == 0) {

                    Data.final_cube_vertex_array.get(i).z += value;
                }
            }
        }
        if (entity.equals("vertex") && type.equals("decrese") && axis.equals("z")) {
            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {
                if (Data.final_cube_vertex_array.get(i).vc_ambient.x_ac == 1 && Data.final_cube_vertex_array.get(i).vc_ambient.y_ac == 0 && Data.final_cube_vertex_array.get(i).vc_ambient.z_ac == 0) {

                    Data.final_cube_vertex_array.get(i).z -= value;
                }
            }
        }
        Normal3D.resetNVector();
    }
}
