package pkg3d_studio_max;

class Point {

    float x;
    float y;
    float z;

    Point() {
    }

    public Point(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;

    }
}

public class Normal3D {

    static Point A = new Point();
    static Point B = new Point();
    static Point N1 = new Point();
    static float dist;
    static float distance1;
    static float distance2;
    static boolean ok = false;
    static float dist1;
    static float dist0;
    static float dist2;
    static boolean done;
    static int index;

    static Point calculateNormalVector(Vertex v1, Vertex v2, Vertex v3) {


        A.x = v2.x - v1.x;
        A.y = v2.y - v1.y;
        A.z = v2.z - v1.z;

        B.x = v3.x - v1.x;
        B.y = v3.y - v1.y;
        B.z = v3.z - v1.z;

        N1.x = (A.y * B.z) - (A.z * B.y);
        N1.y = (A.z * B.x) - (A.x * B.z);
        N1.z = (A.x * B.y) - (A.y * B.x);




        return N1;
    }

    static void resetNVector() {

        Point p = new Point();
        Point N = new Point();

        distance1 = 0;
        distance2 = 0;

        for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {

            for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {

                index = 0;
                do {


                    done = validateColiniariti(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(0), Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(1), Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(2 + index));

                    if (done == true) {
                        p = calculateNormalVector(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(0), Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(1), Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(2 + index));
                    }
                    index++;

                } while (done == false && 2 + index <= Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size());

                N.x = -p.x;
                N.y = -p.y;
                N.z = -p.z;

                distance1 = (float) Math.sqrt(Math.pow((Camera.xPos - p.x), 2) + Math.pow((Camera.yPos - p.y), 2) + Math.pow((Camera.zPos - p.z), 2));
                distance2 = (float) Math.sqrt(Math.pow((Camera.xPos - N.x), 2) + Math.pow((Camera.yPos - N.y), 2) + Math.pow((Camera.zPos - N.z), 2));



                dist = (float) Math.sqrt((double) (p.x * p.x) + (p.y * p.y) + (p.z * p.z));




                if (((Math.abs(distance1) - Math.abs(distance2)) > 0)) {
                    Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.x_nv = N.x / dist;
                    Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.y_nv = N.y / dist;
                    Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.z_nv = N.z / dist;
                } else {
                    Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.x_nv = p.x / dist;
                    Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.y_nv = p.y / dist;
                    Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.z_nv = p.z / dist;
                }


            }
        }

    }

    static boolean validateColiniariti(Vertex ver1, Vertex ver0, Vertex ver2) {

        ok = false;

        dist0 = (float) Math.sqrt(Math.pow(ver1.x - ver0.x, 2) + Math.pow(ver1.y - ver0.y, 2) + Math.pow(ver1.z - ver0.z, 2));
        dist1 = (float) Math.sqrt(Math.pow(ver0.x - ver2.x, 2) + Math.pow(ver0.y - ver2.y, 2) + Math.pow(ver0.z - ver2.z, 2));
        dist2 = (float) Math.sqrt(Math.pow(ver1.x - ver2.x, 2) + Math.pow(ver1.y - ver2.y, 2) + Math.pow(ver1.z - ver2.z, 2));

        if ((dist2) / (dist0 + dist1) > 0.99) {
            ok = false;

            //System.out.println("ver1.x="+ver1.x+"  ver1.y="+ver1.y+"  ver1.z="+ver1.x);
            //System.out.println("ver0.x="+ver0.x+"  ver0.y="+ver0.y+"  ver0.z="+ver0.x);
            //System.out.println("ver2.x="+ver2.x+"  ver2.y="+ver2.y+"  ver2.z="+ver2.x);

        } else {

            ok = true;
        }


        return ok;
    }
}
