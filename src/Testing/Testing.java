package Testing;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class Point {

    float x;
    float y;
}
public class Testing {

    boolean ok,done;
    float w1 , w2 , top , top_1 , top_2 , top_3   , bottom , bottom_1 , bottom_2;
    float pointLineD , point_pointD , point_pointA;

    DecimalFormat df2 = new DecimalFormat("#.#");

    JFrame frame;
    JPanel panel;
    Timer timer;


    Random rand = new Random();

    Point A,B,C,D;
    Point P = new Point();


    public void abcdefghijklmnopqrstuvwxyz0123456789(){
    }
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Testing e = new Testing();
        e.interfata();

    }


    public void interfata() {

        frame = new JFrame();
        frame.setLayout(null);

        A = new Point();
        A.x = rand.nextInt(900);
        A.y = rand.nextInt(900);
        B = new Point();
        B.x = rand.nextInt(900);
        B.y = rand.nextInt(900);
        C = new Point();
        C.x = rand.nextInt(900);
        C.y = rand.nextInt(900);


        panel = new JPanel()
        {
            public  void paintComponent(Graphics g2d)
            {

                A = new Point();
                A.x = rand.nextInt(900);
                A.y = rand.nextInt(900);
                B = new Point();
                B.x = rand.nextInt(900);
                B.y = rand.nextInt(900);
                C = new Point();
                C.x = rand.nextInt(900);
                C.y = rand.nextInt(900);
                D = new Point();
                D.x = rand.nextInt(900);
                D.y = rand.nextInt(900);

                // C.x = A.x ;
                //C.y = A.y ;
                //D.x = B.x ;
                //D.y = B.y ;


                g2d.setColor(Color.GREEN);
//                g2d.drawLine(A.x, A.y, B.x, B.y);
//                g2d.drawLine(B.x, B.y, C.x, C.y);
//                g2d.drawLine(C.x, C.y, A.x, A.y);
//
//
//                g2d.setColor(Color.RED);
//                g2d.drawLine(A.x, A.y, B.x, B.y);
//                g2d.drawLine(C.x, C.y, D.x, D.y);
//
//
//                g2d.setColor(Color.BLUE);
//                g2d.drawString("A", A.x, A.y);
//                g2d.drawString("B", B.x, B.y);
//                g2d.drawString("C", C.x, C.y);
//                g2d.drawString("d", D.x, D.y);


                Point p = chechSegmentIntersection(A,B,C,D);

                if(p != null) {
                    g2d.setColor(Color.BLUE);
                   // g2d.fillOval(p.x-5, p.y-5, 10, 10);
                }



                for(int i=0 ; i < 1000 ; i++)
                {
                    int x = rand.nextInt(1000);
                    int y = rand.nextInt(1000);

                    if(check_If_Triangle_Conteins_Point(A.x,A.y,B.x,B.y,C.x,C.y,x,y))
                    {
                        g2d.setColor(Color.GREEN);
                        g2d.fillRect(x, y, 3, 3);
                    } else {
                        g2d.setColor(Color.red);
                        g2d.fillRect(x, y, 3, 3);
                    }


                }


            }
        };

        panel.setSize(1000,1000);
        panel.setLayout(null);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public Point  chechSegmentIntersection(Point A , Point B , Point C , Point D){

        float  K1 = B.y -  A.y;
        float  H1 = A.x - B.x;
        float  KH1 = (K1 * A.x) + (H1 * A.y);

        float K2 = D.y - C.y;
        float H2 = C.x - D.x;
        float KH2 = (K2 * C.x) + (H2 * C.y);

        float delta = K1 * H2 - K2 * H1;

        if(delta != 0) {
            P.x = (H2 * KH1 - H1 * KH2)/delta;
            P.y = (K1 * KH2 - K2 * KH1)/delta;
        } else {
            return null;
        }

        if((P.x > A.x && P.x < B.x) || ((P.x < A.x && P.x > B.x)))
        {
            if((P.x > C.x && P.x < D.x) || ((P.x < C.x && P.x > D.x)))
            {
                return P;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }
    public boolean check_If_Triangle_Conteins_Point_1(float a_x , float a_y , float b_x , float b_y , float c_x , float c_y , float p_x , float p_y)
    {
        ok = true;
        //__W1_______________________________________//
        top_1 = a_x * (c_y - a_y);
        top_2 = (p_y - a_y) * (c_x - a_x);
        top_3 = p_x * (c_y - a_y);
        top = top_1 + top_2 - top_3;

        bottom_1 = (b_y - a_y) * (c_x - a_x);
        bottom_2 = (b_x - a_x) * (c_y - a_y);
        bottom = bottom_1 - bottom_2;

        w1 = top / bottom;
        //__________________________________________//
        //__W2_________________________________________//
        bottom_1 = (c_y - a_y);
        top_1 = w1 *(b_y - a_y);
        w2 = (p_y - a_y - top_1) / bottom_1;
        //____________________________________________//
        if(w1 < 0 || w2 < 0 || (w1 + w2) > 1) {
            ok = false;
        }
        return ok;
    }
    public boolean check_If_Triangle_Conteins_Point(float a_x , float a_y , float b_x , float b_y , float c_x , float c_y , float p_x , float p_y)
    {
        ok = false;

        float deltaAB = (p_x - a_x) * (a_y - b_y) +  (p_y - a_y) * (b_x - a_x);
        float deltaBC = (p_x - b_x) * (b_y - c_y) +  (p_y - b_y) * (c_x - b_x);
        float deltaCA = (p_x - c_x) * (c_y - a_y) +  (p_y - c_y) * (a_x - c_x);

        if(deltaAB > 0){
            if(deltaBC > 0){
                if(deltaCA > 0){
                    ok = true;
                }
            }
        }
        if(deltaAB < 0){
            if(deltaBC < 0){
                if(deltaCA < 0 ){
                    ok = true;
                }
            }
        }
        return ok;
    }

}
