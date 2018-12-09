package pkg3d_studio_max;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.MouseInputListener;




public class Main    {

    
    JLabel x_label, y_label, z_label , TR , TR_Field;
    JLabel disable_color;
    JTextField x_fild, y_fild, z_fild ;
    JSlider slider;
    JCheckBox cb;
    Data data = new Data();
    Cube cube;
   
    JFrame frame = null;
    JButton cam_buton_1 = null;
    JButton cam_buton_2 = null;
    JButton editPoliVertex = null;
    JButton editPoliEdge = null;
    JButton editPoliPoligon = null;
    JButton editPoliCorp = null;
    JButton printColor   = null;
    JButton box = null;
    ImageIcon icon_cam1 = null;
    ImageIcon icon_cam2 = null;
    ImageIcon icon_cam_1 = null;
    ImageIcon icon_cam_2 = null;
    ImageIcon icon_selectV = null;
    ImageIcon icon_selectE = null;
    ImageIcon icon_selectP = null;
    ImageIcon icon_selectC = null;
    ImageIcon icon_selectV2 = null;
    ImageIcon icon_selectE2 = null;
    ImageIcon icon_selectP2 = null;
    ImageIcon icon_selectC2 = null;
    ImageIcon box_of = null;
    ImageIcon box_on = null;
    JList menu_list = null;
    JList second_menu_list = null;
    GLCanvas canvas = null;
    GLEventListener gl_listener = null;
    MouseListener mouseListener = null;
    MouseListener list_listener = null;
    MouseListener list2_listener = null;
    MouseMotionListener mouseMotion = null;
    MouseWheelListener wheel = null;
    KeyListener keylist = null;
    Animator animator = null;
    Camera camera = null;
    GLUT glut = null;
    GLU glu = null;
    GL gl = null;
    
    static float x_point = 3;
    static float y_point = 3;
    static float z_point = 0;
    static float lenglt = 1f;
    
    int width;
    int height;
    int x_temp;
    int y_temp;
    int x_pres;
    int y_pres;
    float lightDiffuse[] = {1.0f, 1.0f, 1.0f, 5.0f};
    float lightAmbient[] = {2.0f, 0.0f, 0.0f, 1.0f};
    boolean cam_ok_rotate = false;
    boolean cam_ok_zoom = false;
    boolean vertex_select_status = false;
    boolean edge_select_status = false;
    boolean poligon_select_status = false;
    boolean corp_select_status = false;
    boolean box_status = false;
    boolean box_control = true;
    boolean start_drag = false;
    boolean relese_drag = false;
    boolean CTRL_Status = false;
    boolean shift_Status = false;
    boolean F4_status = false;
    boolean status = false;
    boolean move_x_status = false;
    boolean move_y_status = false;
    boolean move_z_status = false;
    boolean moveInProgres = false;
    boolean move_status = false;
    boolean extrude_x_status = false;
    boolean extrude_y_status = false;
    boolean extrude_z_status = false;
    boolean extrudeInProgres = false;
    boolean extrude_status = false;
    boolean texture_status = false;
    double senzitiviti = 0.2;
    boolean disable_colorMaterial = false;
    Cursor customCursor = null;
    Cursor customCursor2 = null;
    float[] color = new float[4];
    FloatBuffer pixel = FloatBuffer.allocate(4);
    double coor[] = new double[4];
    float[] RGB = new float[3];
    float[] RGB_click = new float[3];
    double mouse_x;
    double mouse_y;
    int mouse_X;
    int mouse_Y;
    int X1, Y1;
    int BUFSIZE = 512;
    int[] array = new int[1];
    DecimalFormat df = new DecimalFormat("#.####");
    DecimalFormat formatCuloare = new DecimalFormat("#.##");
    Cursor cursor2 = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    Cursor cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
    Cursor move = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
    int viewport[] = new int[4];
    double mvmatrix[] = new double[16];
    double projmatrix[] = new double[16];
    double Ncoord[] = new double[4];
    double TempNcoord[] = new double[4];
    double TempNcoord2[] = new double[4];
    Corp_S cs = null;
    Poli_S ps = null;
    Edge_S es = null;
    Vert_S vs = null;
    SelectedData   data_Select = new SelectedData();
    DataTemporalStore  store = new DataTemporalStore();
    float transparenta = 1f;
    float fff = 1f;
    
    	static double start = System.nanoTime()/1000000000;
	static double end;
	static int valoare=0;
    
//   test .................................//
    Texture  tt;
    int textureParser;
    static ArrayList<String>   textures_filds           =   new  ArrayList<String>();
    static ArrayList<Texture>  finals_textures         =   new  ArrayList<Texture>();
//............................................................
    
   static boolean  resetTextures = false; 
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        Main m = new Main();
         m.interfata();

    }

    public void interfata() {


final TextureMappingInterface  a = new TextureMappingInterface();
 
        frame = new JFrame();
        frame.setLayout(null);
        frame.setBackground(Color.BLACK);
        canvas = new GLCanvas();
        canvas.setSize(650, 650);
        canvas.setLocation(10, 10);
        animator = new Animator(canvas);
        camera = new Camera();
        icon_cam1 = new ImageIcon("Images/poza1.png");
        icon_cam2 = new ImageIcon("Images/poza2.png");
        icon_cam_1 = new ImageIcon("Images/poza_1.jpg");
        icon_cam_2 = new ImageIcon("Images/poza_2.jpg");
        icon_selectV = new ImageIcon("Images/vertex_select.jpg");
        icon_selectV2 = new ImageIcon("Images/vertex_select_1.jpg");
        icon_selectE = new ImageIcon("Images/edge_select.jpg");
        icon_selectE2 = new ImageIcon("Images/edge_select_1.jpg");
        icon_selectP = new ImageIcon("Images/poligon_select.jpg");
        icon_selectP2 = new ImageIcon("Images/poligon_select_1.jpg");
        icon_selectC = new ImageIcon("Images/corp_select.jpg");
        icon_selectC2 = new ImageIcon("Images/corp_select_1.jpg");
        box_of = new ImageIcon("Images/box_of.jpg");
        box_on = new ImageIcon("Images/box_on.jpg");

        String str[] = {"MOVE", "CORP                                 >>", "POLIGON                           >>", "EDGE                                  >>", "VERTEX                             >>"};

        final String corp_str[] = {"DELETE CORP"};
        final String poli_str[] = {"DELETE POLI", "EXTRUDE POLI","TEXTURE MAPPING"};
        final String Edge_str[] = {"DELETE EDGE", "CONECT EDGE", "REMOVE EDGE"};
        final String Vertex_str[] = {"DELETE VERT", "CONECT VERT", "FUSION VERT"};

        menu_list = new JList(str);
        menu_list.setSize(0, 0);
        menu_list.setLocation(0, 0);
        menu_list.setBackground(Color.DARK_GRAY);
        menu_list.setForeground(Color.WHITE);

        second_menu_list = new JList();
        second_menu_list.setSize(0, 0);
        second_menu_list.setLocation(0, 0);
        second_menu_list.setBackground(Color.DARK_GRAY);
        second_menu_list.setForeground(Color.WHITE);


        cam_buton_1 = new JButton("");
        cam_buton_1.setSize(30, 30);
        cam_buton_1.setBackground(Color.GRAY);
        cam_buton_1.setLocation(680, 630);
        cam_buton_1.setIcon(icon_cam1);
        cam_buton_2 = new JButton("");
        cam_buton_2.setSize(30, 30);
        cam_buton_2.setBackground(Color.GRAY);
        cam_buton_2.setLocation(715, 630);
        cam_buton_2.setIcon(icon_cam_1);
        cb = new JCheckBox();
        cb.setSize(25, 25);
        cb.setLocation(675, 250);       
        editPoliVertex = new JButton("");
        editPoliVertex.setSize(48, 46);
        editPoliVertex.setLocation(670, 100);
        editPoliVertex.setIcon(icon_selectV);
        editPoliEdge = new JButton("");
        editPoliEdge.setSize(48, 46);
        editPoliEdge.setLocation(720, 100);
        editPoliEdge.setIcon(icon_selectE);
        editPoliPoligon = new JButton("");
        editPoliPoligon.setSize(48, 46);
        editPoliPoligon.setLocation(770, 100);
        editPoliPoligon.setIcon(icon_selectP);
        editPoliCorp = new JButton("");
        editPoliCorp.setSize(48, 46);
        editPoliCorp.setLocation(820, 100);
        editPoliCorp.setIcon(icon_selectC);
        box = new JButton();
        box.setSize(69, 18);
        box.setLocation(670, 10);
        box.setIcon(box_of);
 
// printf color ----------------------------------        
        printColor = new JButton("print");
        printColor.setSize(69, 18);
        printColor.setLocation(670, 40);
   
//------------------------------------------------        
        slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        slider.setSize(200, 20);slider.setLocation(680, 200);
        
        disable_color = new JLabel("DISABLE   [GL_COLOR_MATERIAL]");
        disable_color.setSize(180, 25);
        disable_color.setLocation(700, 250);   
        
        TR = new  JLabel("Transparenta");
        TR.setSize(150, 25);
        TR.setLocation(680, 170);
        TR_Field = new JLabel("0%");
        TR_Field.setSize(60, 20);
        TR_Field.setLocation(780, 170);
        x_label = new JLabel("X= ");
        x_label.setSize(50, 20);
        x_label.setForeground(Color.red);
        x_label.setLocation(10, 660);
        x_fild = new JTextField();
        x_fild.setSize(110, 25);
        x_fild.setLocation(35, 662);
        y_label = new JLabel("Y= ");
        y_label.setSize(50, 20);
        y_label.setForeground(Color.red);
        y_label.setLocation(150, 660);
        y_fild = new JTextField();
        y_fild.setSize(110, 25);
        y_fild.setLocation(170, 662);
        z_label = new JLabel("Z= ");
        z_label.setSize(50, 20);
        z_label.setForeground(Color.red);
        z_label.setLocation(290, 660);
        z_fild = new JTextField();
        z_fild.setSize(110, 25);
        z_fild.setLocation(310, 662);

        glut = new GLUT();

        gl_listener = new GLEventListener() {

            public void init(GLAutoDrawable glad) {

                gl = glad.getGL();


                gl.glEnable(GL.GL_LIGHTING);
                gl.glEnable(GL.GL_LIGHT0);
                gl.glBlendFunc (GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

                gl.glEnable(GL.GL_COLOR_MATERIAL);
             //   gl.glColorMaterial(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE);

                          
                
            }

            public void display(GLAutoDrawable glad) 
            {
               
               
               end = System.nanoTime()/1000000000;			
			if(end - start >1) {
				start = end;
                                    frame.setTitle(String.valueOf(valoare)+"   FPS");
				valoare=0;
                                System.gc();
			}
	       valoare++;
 
                   
               
              gl.glClearColor(0.0f, 0.0f, 0.0f, 0f);
              gl = glad.getGL();


                glu = new GLU();

                gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);


                gl.glEnable(gl.GL_BLEND);
                gl.glEnable(gl.GL_DEPTH_TEST);
                gl.glEnable(GL.GL_LIGHTING);
                gl.glEnable(GL.GL_LIGHT0);

 
                  
                if(resetTextures == true)
                {
                   readTextures();
                   resetTextures = false;
                }
                
                
                width = glad.getWidth();
                height = glad.getHeight();


                gl.glMatrixMode(GL.GL_PROJECTION);
                gl.glLoadIdentity();


                gl.glViewport(0, 0, width, height);
                glu.gluPerspective(60, 1, 0.1, 100);
                glu.gluLookAt(camera.getxPos(), camera.getyPos(), camera.getzPos(), camera.getxLPos(), camera.getyLPos(), camera.getzLPos(), 0.0, 0.0, 1.0);


                gl.glMatrixMode(GL.GL_MODELVIEW);
                gl.glLoadIdentity();



                float[] lightPosition = {(float) camera.getCxPos(), (float) camera.getCyPos(), (float) camera.getCzPos(), 1.0f};

                gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, lightDiffuse, 0);
                gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, lightPosition, 0);


                gl.glMatrixMode(GL.GL_MODELVIEW);
                gl.glLoadIdentity();


                gl.glDisable(GL.GL_LIGHTING);
                gl.glDisable(GL.GL_LIGHT0);


                draw_model(gl, "non rendable");



                //---------Color coding  picking-----------------------------------------------------------------------------------------//    

                gl.glReadBuffer(GL.GL_BACK);
                gl.glPixelStorei(gl.GL_PACK_ALIGNMENT, 1);

                gl.glReadPixels(mouse_X, viewport[3] - mouse_Y, 1, 1, gl.GL_RGB, gl.GL_FLOAT, pixel);

                RGB[0] = Float.valueOf(formatCuloare.format(pixel.get(0)));
                RGB[1] = Float.valueOf(formatCuloare.format(pixel.get(1)));
                RGB[2] = Float.valueOf(formatCuloare.format(pixel.get(2)));

//-----------glReadPixels--------------------------------------------------------------------------------------------------//      



// reset  cursor  ----------------------------------------------------------------//

                if (RGB[0] != 0 && RGB[1] != 0 && corp_select_status == true && cam_ok_rotate == false && RGB[0] != 0.5f && RGB[1] != 0.5f) {
                    canvas.setCursor(cursor2);
                } else {
                    if (cam_ok_rotate == false && poligon_select_status == false && vertex_select_status == false && edge_select_status == false && box_status == false) {
                        status = false;

                        if (RGB[0] == 0.0f && RGB[1] == 0.95f && RGB[2] == 0.0f) {
                            if (moveInProgres == false) {
                                canvas.setCursor(move);

                                move_x_status = true;
                                move_y_status = false;
                                move_z_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_x_status = true;
                                extrude_y_status = false;
                                extrude_z_status = false;
                            }
                            status = true;
                        }
                        if (RGB[0] == 0.95f && RGB[1] == 0.0f && RGB[2] == 0.95f) {

                            if (moveInProgres == false) {
                                canvas.setCursor(move);

                                move_z_status = true;
                                move_x_status = false;
                                move_y_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_z_status = true;
                                extrude_x_status = false;
                                extrude_y_status = false;
                            }
                            status = true;
                        }
                        if (RGB[0] == 0.0f && RGB[1] == 0.0f && RGB[2] == 0.95f) {


                            if (moveInProgres == false) {
                                canvas.setCursor(move);

                                move_y_status = true;
                                move_x_status = false;
                                move_z_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_y_status = true;
                                extrude_x_status = false;
                                extrude_z_status = false;
                            }

                            status = true;
                        }
                        if (status == false) {
                            canvas.setCursor(cursor);
                        }
                    }
                }


//  poligon  color ----------------------------------------------//

                if (RGB[0] == 1  && poligon_select_status == true && cam_ok_rotate == false) {
                    canvas.setCursor(cursor2);
                } else {
                    if (cam_ok_rotate == false && corp_select_status == false && vertex_select_status == false && edge_select_status == false && box_status == false) {
                        status = false;

                        if (RGB[0] == 0.0f && RGB[1] == 0.95f && RGB[2] == 0.0f) {
                            if (moveInProgres == false) {

                                canvas.setCursor(move);

                                move_x_status = true;
                                move_y_status = false;
                                move_z_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_x_status = true;
                                extrude_y_status = false;
                                extrude_z_status = false;
                            }
                            status = true;
                        }
                        if (RGB[0] == 0.95f && RGB[1] == 0.0f && RGB[2] == 0.95f) {
                            if (moveInProgres == false) {
                                canvas.setCursor(move);

                                move_z_status = true;
                                move_x_status = false;
                                move_y_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_z_status = true;
                                extrude_x_status = false;
                                extrude_y_status = false;
                            }
                            status = true;
                        }
                        if (RGB[0] == 0.0f && RGB[1] == 0.0f && RGB[2] == 0.95f) {
                            if (moveInProgres == false) {
                                canvas.setCursor(move);

                                move_y_status = true;
                                move_x_status = false;
                                move_z_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_y_status = true;
                                extrude_x_status = false;
                                extrude_z_status = false;
                            }
                            status = true;
                        }
                        if (status == false) {
                            canvas.setCursor(cursor);
                        }
                    }
                }

// vertex color ---------------------------------------------------//

                if (RGB[2] == 1 && vertex_select_status == true && cam_ok_rotate == false) {
                    canvas.setCursor(cursor2);
                } else {
                    if (cam_ok_rotate == false && corp_select_status == false && poligon_select_status == false && edge_select_status == false && box_status == false) {
                        status = false;

                        if (RGB[0] == 0.0f && RGB[1] == 0.95f && RGB[2] == 0.0f) {
                            if (moveInProgres == false) {
                                canvas.setCursor(move);

                                move_x_status = true;
                                move_y_status = false;
                                move_z_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_x_status = true;
                                extrude_y_status = false;
                                extrude_z_status = false;

                            }
                            status = true;
                        }
                        if (RGB[0] == 0.95f && RGB[1] == 0.0f && RGB[2] == 0.95f) {
                            if (moveInProgres == false) {
                                canvas.setCursor(move);

                                move_z_status = true;
                                move_x_status = false;
                                move_y_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_z_status = true;
                                extrude_x_status = false;
                                extrude_y_status = false;
                            }
                            status = true;
                        }
                        if (RGB[0] == 0.0f && RGB[1] == 0.0f && RGB[2] == 0.95f) {
                            if (moveInProgres == false) {
                                canvas.setCursor(move);

                                move_y_status = true;
                                move_x_status = false;
                                move_z_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_y_status = true;
                                extrude_x_status = false;
                                extrude_z_status = false;
                            }
                            status = true;
                        }
                        if (status == false) {
                            canvas.setCursor(cursor);
                        }
                    }
                }

//  edge  color ----------------------------------------------//

                if (RGB[1] == 1 && edge_select_status == true && cam_ok_rotate == false) {
                    canvas.setCursor(cursor2);
                } else {
                    if (cam_ok_rotate == false && corp_select_status == false && poligon_select_status == false && vertex_select_status == false && box_status == false) {
                        status = false;

                        if (RGB[0] == 0.0f && RGB[1] == 0.95f && RGB[2] == 0.0f) {
                            if (moveInProgres == false) {
                                canvas.setCursor(move);

                                move_x_status = true;
                                move_y_status = false;
                                move_z_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_x_status = true;
                                extrude_y_status = false;
                                extrude_z_status = false;
                            }
                            status = true;
                        }
                        if (RGB[0] == 0.95f && RGB[1] == 0.0f && RGB[2] == 0.95f) {
                            if (moveInProgres == false) {
                                canvas.setCursor(move);

                                move_z_status = true;
                                move_x_status = false;
                                move_y_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_z_status = true;
                                extrude_x_status = false;
                                extrude_y_status = false;

                            }
                            status = true;
                        }
                        if (RGB[0] == 0.0f && RGB[1] == 0.0f && RGB[2] == 0.95f) {
                            if (moveInProgres == false) {
                                canvas.setCursor(move);

                                move_y_status = true;
                                move_x_status = false;
                                move_z_status = false;
                            }
                            if (extrudeInProgres == false) {
                                canvas.setCursor(move);

                                extrude_y_status = true;
                                extrude_x_status = false;
                                extrude_z_status = false;
                            }
                            status = true;
                        }
                        if (status == false) {
                            canvas.setCursor(cursor);
                        }
                    }

                }


                gl.glClear(gl.GL_COLOR_BUFFER_BIT | gl.GL_DEPTH_BUFFER_BIT);



                drawPlanes(gl, TempNcoord[1] + (cube.x_l / 2));

                GluUnProject(mouse_X, viewport[3] - mouse_Y, TempNcoord2);

                if (start_drag == true) {
                    GluUnProject(mouse_X, viewport[3] - mouse_Y, TempNcoord);
                }


                gl.glEnable(GL.GL_LIGHTING);
                gl.glEnable(GL.GL_LIGHT0);

                draw_model(gl, "rendable");

                GluUnProject(mouse_X, viewport[3] - mouse_Y, Ncoord);





                if (CTRL_Status == true) {
                    gl.glWindowPos2d(0, 5);
                    glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "CTRL ON");
                }
                if (F4_status == true) {
                    gl.glWindowPos2d(5, 620);
                    glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "F4 ON");
                }
                if (shift_Status == true) {
                    gl.glWindowPos2d(0, 5);
                    glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "SHIFT ON");
                }
                if (move_status == true || extrude_status == true) {
                    gl.glColor3f(1, 1, 1);
                    GluProject(x_point, y_point, z_point + lenglt + (lenglt / 2) + (lenglt / 6));
                    gl.glWindowPos2d(coor[0], coor[1]);
                    glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "z");
                    GluProject(x_point + lenglt + (lenglt / 2) + (lenglt / 6), y_point, z_point);
                    gl.glWindowPos2d(coor[0], coor[1]);
                    glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "x");
                    GluProject(x_point, y_point + lenglt + (lenglt / 2) + (lenglt / 6), z_point);
                    gl.glWindowPos2d(coor[0], coor[1]);
                    glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "y");
                }

                gl.glFlush();

            }

            public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) 
            {
                width = glad.getWidth();
                height = glad.getHeight();

                final GL gl = glad.getGL();
                final GLU glu = new GLU();

                gl.glMatrixMode(GL.GL_PROJECTION);
                gl.glLoadIdentity();

                gl.glViewport(0, 0, width, height);
                glu.gluPerspective(60, 1, 0.1, 100);

                gl.glMatrixMode(GL.GL_MODELVIEW);
                gl.glLoadIdentity();
            }

            public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
            }
        };

        mouseListener = new MouseInputListener() {

            public void mouseClicked(MouseEvent e) {

                //      printColors();   

                if (SelectedData.testSelection() && e.getButton() == 3) {

                    menu_list.setSize(150, 90);
                    menu_list.setLocation(e.getX() + 15, e.getY());

                } else {

                    menu_list.setSize(0, 0);
                    menu_list.setLocation(0, 0);

                    second_menu_list.setSize(0, 0);
                    second_menu_list.setLocation(0, 0);

                }



                if (box_status == true) {
                    box_status = false;
                    box.setIcon(box_of);
                }

                if (e.getButton() == 1) {

                    RGB_click[0] = Float.valueOf(formatCuloare.format(pixel.get(0)));
                    RGB_click[1] = Float.valueOf(formatCuloare.format(pixel.get(1)));
                    RGB_click[2] = Float.valueOf(formatCuloare.format(pixel.get(2)));

                    //     System.out.println("pixel.get(0)="+pixel.get(0)+"     pixel.get(1)= "+pixel.get(1)+"      pixel.get(2)"+pixel.get(2)); 
                    //     System.out.println("RGB_click[0]="+RGB_click[0]+"     RGB_click[1]= "+RGB_click[1]+"      RGB_click[2]"+RGB_click[2]);    
                }


                processData();

                if (e.getButton() == 1) {
                    mouse_x = e.getX();
                    mouse_y = e.getY();
                }

                if (e.getButton() == 3) {
                    cam_ok_rotate = false;
                    cam_buton_1.setIcon(icon_cam1);
                    cam_buton_2.setIcon(icon_cam_1);
                    box_status = false;
                    box.setIcon(box_of);

                    canvas.setCursor(cursor);
                }

            }

            public void mousePressed(MouseEvent e) {

                x_pres = e.getX();
                y_pres = e.getY();

                if (box_control == true && box_status == true) {

                    cube.x = (float) Ncoord[0];
                    cube.y = (float) Ncoord[1];
                    cube.z = 0.01f;

                    cube.set_CubeData();
                    cube.loadCube();


                    box_control = false;
                    start_drag = false;
                    relese_drag = false;
                }



            }

            public void mouseReleased(MouseEvent e) {

                x_temp = e.getX();
                y_temp = e.getY();

                if (box_status == true) {
                    relese_drag = true;
                    start_drag = false;

                }
                moveInProgres = false;
                extrudeInProgres = false;
                PoligonUtilFunction.reset_listaPoligoaneSelectate();
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent e) {
            }
        };
        wheel = new MouseWheelListener() {

            public void mouseWheelMoved(MouseWheelEvent e) {

                if (e.getPreciseWheelRotation() < 0) {
                    camera.scrol_in(0.1);
                } else {
                    camera.scrol_out(0.1);
                }
            }
        };
        mouseMotion = new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                //..Extrude---------------------------------------------------------------//     
                if (extrude_x_status == true && extrude_status == true) {

                    extrudeInProgres = true;
                    if (poligon_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (X1 > 0) {
                            PoligonUtilFunction.extrude("x", "increse", (float) ((X1 * senzitiviti) / 10));

                        } else {
                            PoligonUtilFunction.extrude("x", "decrese", (float) (-(X1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                }
                if (extrude_y_status == true && extrude_status == true) {
                    extrudeInProgres = true;
                    if (poligon_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (X1 > 0) {
                            PoligonUtilFunction.extrude("y", "increse", (float) ((X1 * senzitiviti) / 10));

                        } else {
                            PoligonUtilFunction.extrude("y", "decrese", (float) (-(X1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                }
                if (extrude_z_status == true && extrude_status == true) {
                    extrudeInProgres = true;
                    if (poligon_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (Y1 > 0) {
                            PoligonUtilFunction.extrude("z", "increse", (float) (-(Y1 * senzitiviti) / 10));

                        } else {
                            PoligonUtilFunction.extrude("z", "decrese", (float) ((Y1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                }
                //-------------------------------------------------------------------------//

                //..Move--------------------------------------------------------------------//
                if (move_x_status == true && move_status == true) {
                    moveInProgres = true;
                    if (poligon_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (X1 > 0) {
                            ProcessRequest.move("poligon", "increse", "x", (float) ((X1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("poligon", "decrese", "x", (float) (-(X1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                    if (edge_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (X1 > 0) {
                            ProcessRequest.move("edge", "increse", "x", (float) ((X1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("edge", "decrese", "x", (float) (-(X1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                    if (vertex_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (X1 > 0) {
                            ProcessRequest.move("vertex", "increse", "x", (float) ((X1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("vertex", "decrese", "x", (float) (-(X1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                    if (corp_select_status == true) {

                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (X1 > 0) {
                            ProcessRequest.move("corp", "increse", "x", (float) ((X1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("corp", "decrese", "x", (float) (-(X1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();

                    }
                }
                if (move_y_status == true && move_status == true) {
                    moveInProgres = true;
                    if (poligon_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (X1 > 0) {
                            ProcessRequest.move("poligon", "increse", "y", (float) ((X1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("poligon", "decrese", "y", (float) (-(X1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                    if (edge_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (X1 > 0) {
                            ProcessRequest.move("edge", "increse", "y", (float) ((X1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("edge", "decrese", "y", (float) (-(X1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                    if (vertex_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (X1 > 0) {
                            ProcessRequest.move("vertex", "increse", "y", (float) ((X1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("vertex", "decrese", "y", (float) (-(X1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                    if (corp_select_status == true) {

                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (X1 > 0) {
                            ProcessRequest.move("corp", "increse", "y", (float) ((X1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("corp", "decrese", "y", (float) (-(X1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();

                    }

                }
                if (move_z_status == true && move_status == true) {
                    moveInProgres = true;

                    if (poligon_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (Y1 > 0) {
                            ProcessRequest.move("poligon", "increse", "z", (float) (-(Y1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("poligon", "decrese", "z", (float) ((Y1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                    if (edge_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();

                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (Y1 > 0) {
                            ProcessRequest.move("edge", "increse", "z", (float) ((-Y1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("edge", "decrese", "z", (float) ((Y1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                    if (vertex_select_status == true) {
                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (Y1 > 0) {
                            ProcessRequest.move("vertex", "increse", "z", (float) (-(Y1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("vertex", "decrese", "z", (float) ((Y1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();
                    }
                    if (corp_select_status == true) {

                        int X2 = e.getX();
                        int Y2 = e.getY();
                        X1 = X2 - x_temp;
                        Y1 = Y2 - y_temp;

                        if (X1 > 0) {
                            ProcessRequest.move("corp", "increse", "z", (float) (-(Y1 * senzitiviti) / 10));

                        } else {
                            ProcessRequest.move("corp", "decrese", "z", (float) ((Y1 * senzitiviti) / 10));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();

                    }
                }
//----------------------------------------------------                
                mouse_X = e.getX();
                mouse_Y = e.getY();

                if (box_status == true) {
                    if (!canvas.getCursor().equals(cursor)) {
                        canvas.setCursor(cursor);
                    }


                    cube.x_l = (float) Ncoord[0] - cube.x;
                    cube.y_l = (float) Ncoord[1] - cube.y;

                    cube.set_CubeData();
                    cube.loadCube();
                    start_drag = true;
                }


                if (cam_ok_rotate == true) {
                    Normal3D.resetNVector();
                    try {
                        int X = e.getX();
                        int Y = e.getY();
                        X1 = X - x_temp;
                        Y1 = Y - y_temp;
                        if (X1 > 0) {
                            camera.rotate_right((X1 * senzitiviti));

                        } else {
                            camera.rotate_left((X1 * senzitiviti));
                        }
                        if (Y1 > 0) {
                            camera.rotate_up(-(Y1 * senzitiviti));
                        } else {
                            camera.rotate_down((Y1 * senzitiviti));
                        }
                        x_temp = e.getX();
                        y_temp = e.getY();

                    } catch (Exception e1) {
                    }

                }

            }

            public void mouseMoved(MouseEvent e) {

                if (box_status == true && relese_drag == true) {

                    cube.z_l = (float) (TempNcoord2[2] - cube.z);

                    cube.set_CubeData();
                    cube.loadCube();

                }

                x_temp = e.getX();
                y_temp = e.getY();

                mouse_X = e.getX();
                mouse_Y = e.getY();


                x_fild.setText(String.valueOf(df.format(Ncoord[0])));
                y_fild.setText(String.valueOf(df.format(Ncoord[1])));
                z_fild.setText(String.valueOf(df.format(Ncoord[2])));
            }
        };

        keylist = new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                    CTRL_Status = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_F4) {

                    if (F4_status == false) {
                        F4_status = true;
                    } else {
                        F4_status = false;
                    }

                }
                if(e.getKeyCode() == KeyEvent.VK_F2)
                {
                  store.saveTempData();
                }
                 if(e.getKeyCode() == KeyEvent.VK_S)
                {
                  store.printSize();
                }
                if(e.getKeyCode() == KeyEvent.VK_P)
                {
                  store.PrintData();
                }
             
                if(e.getKeyCode() == KeyEvent.VK_F5)
                {
                   store.loadTempData();
                   Normal3D.resetNVector();
                }
                
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    shift_Status = true;
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                    CTRL_Status = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    shift_Status = false;
                }
            }
        };
        list_listener = new MouseInputListener() {

            public void mouseClicked(MouseEvent e) {
                menu_list.setSelectionBackground(Color.BLACK);
                menu_list.setSelectionForeground(Color.WHITE);

                if (menu_list.getSelectedValue().equals("MOVE")) {

                    if (corp_select_status == true) {

                        x_point = (float) Ncoord[0];
                        y_point = (float) Ncoord[1];
                        z_point = (float) Ncoord[2];

                        move_status = true;

                    }
                    if (poligon_select_status == true) {
                        x_point = (float) Ncoord[0];
                        y_point = (float) Ncoord[1];
                        z_point = (float) Ncoord[2];

                        move_status = true;

                    }
                    if (edge_select_status == true) {
                        x_point = (float) Ncoord[0];
                        y_point = (float) Ncoord[1];
                        z_point = (float) Ncoord[2];

                        move_status = true;
                    }
                    if (vertex_select_status == true) {
                        x_point = (float) Ncoord[0];
                        y_point = (float) Ncoord[1];
                        z_point = (float) Ncoord[2];

                        move_status = true;
                    }

                    menu_list.setSize(0, 0);
                    menu_list.setLocation(0, 0);
                    second_menu_list.setSize(0, 0);
                    second_menu_list.setLocation(0, 0);
                }
                if (menu_list.getSelectedValue().equals("CORP                                 >>")) {

                    second_menu_list.setListData(corp_str);
                    second_menu_list.setSize(120, 20);
                    second_menu_list.setLocation(menu_list.getX() + menu_list.getWidth(), menu_list.getY() + 18);


                }
                if (menu_list.getSelectedValue().equals("POLIGON                           >>")) {
                    second_menu_list.setListData(poli_str);
                    second_menu_list.setSize(120, 50);
                    second_menu_list.setLocation(menu_list.getX() + menu_list.getWidth(), menu_list.getY() + 37);

                }
                if (menu_list.getSelectedValue().equals("EDGE                                  >>")) {
                    second_menu_list.setListData(Edge_str);
                    second_menu_list.setSize(120, 55);
                    second_menu_list.setLocation(menu_list.getX() + menu_list.getWidth(), menu_list.getY() + 55);

                }
                if (menu_list.getSelectedValue().equals("VERTEX                             >>")) {
                    second_menu_list.setListData(Vertex_str);
                    second_menu_list.setSize(120, 55);
                    second_menu_list.setLocation(menu_list.getX() + menu_list.getWidth(), menu_list.getY() + 73);

                }

            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseDragged(MouseEvent e) {
            }

            public void mouseMoved(MouseEvent e) {
            }
        };
        list2_listener = new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                if (second_menu_list.getSelectedValue().equals("DELETE POLI") && poligon_select_status == true) {
                    ProcessRequest.delete("poligon");
                    resetColor();

                    menu_list.setSize(0, 0);
                    menu_list.setLocation(0, 0);
                    second_menu_list.setSize(0, 0);
                    second_menu_list.setLocation(0, 0);
                }
                if (second_menu_list.getSelectedValue().equals("DELETE CORP") && corp_select_status == true) {

                    ProcessRequest.delete("corp");
                    resetColor();
                    
                    menu_list.setSize(0, 0);
                    menu_list.setLocation(0, 0);
                    second_menu_list.setSize(0, 0);
                    second_menu_list.setLocation(0, 0);
                }
                if (second_menu_list.getSelectedValue().equals("DELETE VERT") && vertex_select_status == true) {
                    ProcessRequest.delete("vertex");
                    resetColor();
                    menu_list.setSize(0, 0);
                    menu_list.setLocation(0, 0);
                    second_menu_list.setSize(0, 0);
                    second_menu_list.setLocation(0, 0);
                }
                if (second_menu_list.getSelectedValue().equals("DELETE EDGE") && edge_select_status == true) {
                    ProcessRequest.delete("edge");
                    resetColor();

                    menu_list.setSize(0, 0);
                    menu_list.setLocation(0, 0);
                    second_menu_list.setSize(0, 0);
                    second_menu_list.setLocation(0, 0);
                }
                if (second_menu_list.getSelectedValue().equals("CONECT EDGE") && edge_select_status == true) {
                    EdgeUtilFunction.connectEdge();
                    resetColor();

                    menu_list.setSize(0, 0);
                    menu_list.setLocation(0, 0);
                    second_menu_list.setSize(0, 0);
                    second_menu_list.setLocation(0, 0);
                }
                if (second_menu_list.getSelectedValue().equals("FUSION VERT") && vertex_select_status == true) {
                    VertexUtilFunction.fusionVertex();
                    resetColor();

                    menu_list.setSize(0, 0);
                    menu_list.setLocation(0, 0);
                    second_menu_list.setSize(0, 0);
                    second_menu_list.setLocation(0, 0);
                }
                if (second_menu_list.getSelectedValue().equals("EXTRUDE POLI") && poligon_select_status == true) {
                    PoligonUtilFunction.execute = true;
                    extrude_status = true;
                   

                    menu_list.setSize(0, 0);
                    menu_list.setLocation(0, 0);
                    second_menu_list.setSize(0, 0);
                    second_menu_list.setLocation(0, 0);
                }
                if (second_menu_list.getSelectedValue().equals("TEXTURE MAPPING") && poligon_select_status == true) {
                    a.reset();
                    a.WindowInterface();
                
                    menu_list.setSize(0, 0);
                    menu_list.setLocation(0, 0);
                    second_menu_list.setSize(0, 0);
                    second_menu_list.setLocation(0, 0);
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        };


        cam_buton_1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (cam_buton_1.getIcon().equals(icon_cam1)) {
                    cam_buton_1.setIcon(icon_cam2);
                    cam_buton_2.setIcon(icon_cam_1);
                    box.setIcon(box_of);

                    cam_ok_rotate = true;
                    cam_ok_zoom = false;
                    box_status = false;


                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Image cursorImage = toolkit.getImage("images/mouse_camera_icon.gif");
                    Point cursorHotSpot = new Point(0, 0);
                    customCursor2 = toolkit.createCustomCursor(cursorImage, cursorHotSpot, "Cursor");
                    canvas.setCursor(customCursor2);

                    move_x_status = false;
                    move_y_status = false;
                    move_z_status = false;

                } else {
                    cam_buton_1.setIcon(icon_cam1);
                    canvas.setCursor(cursor);
                    cam_ok_rotate = false;
                }
            }
        });
        cam_buton_2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (cam_buton_2.getIcon().equals(icon_cam_1)) {
                    cam_buton_2.setIcon(icon_cam_2);
                    cam_buton_1.setIcon(icon_cam1);


                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Image cursorImage = toolkit.getImage("images/mouse_hand_icon.gif");
                    Point cursorHotSpot = new Point(0, 0);
                    customCursor = toolkit.createCustomCursor(cursorImage, cursorHotSpot, "Cursor");
                    canvas.setCursor(customCursor);
                    box.setIcon(box_of);

                    cam_ok_rotate = false;
                    cam_ok_zoom = false;
                    box_status = false;

                } else {
                    cam_buton_2.setIcon(icon_cam_1);
                    canvas.setCursor(cursor);

                }
            }
        });
        editPoliVertex.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (editPoliVertex.getIcon().equals(icon_selectV)) {
                    editPoliVertex.setIcon(icon_selectV2);
                    editPoliEdge.setIcon(icon_selectE);
                    editPoliPoligon.setIcon(icon_selectP);
                    editPoliCorp.setIcon(icon_selectC);
                    box.setIcon(box_of);
                    cam_buton_1.setIcon(icon_cam1);
                    cam_buton_2.setIcon(icon_cam_1);

                    cam_ok_rotate = false;

                    vertex_select_status = true;
                    edge_select_status = false;
                    poligon_select_status = false;
                    corp_select_status = false;
                    box_status = false;
                    canvas.setCursor(cursor);
                    resetSelection();
                    F4_status = true;
                    resetColor();
                } else {
                    editPoliVertex.setIcon(icon_selectV);

                    vertex_select_status = false;
                    edge_select_status = false;
                    poligon_select_status = false;
                    corp_select_status = false;
                }
            }
        });
        editPoliEdge.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (editPoliEdge.getIcon().equals(icon_selectE)) {
                    editPoliEdge.setIcon(icon_selectE2);
                    editPoliVertex.setIcon(icon_selectV);
                    editPoliPoligon.setIcon(icon_selectP);
                    editPoliCorp.setIcon(icon_selectC);
                    box.setIcon(box_of);

                    cam_buton_1.setIcon(icon_cam1);
                    cam_buton_2.setIcon(icon_cam_1);

                    cam_ok_rotate = false;

                    vertex_select_status = false;
                    edge_select_status = true;
                    poligon_select_status = false;
                    corp_select_status = false;
                    box_status = false;

                    canvas.setCursor(cursor);
                    resetSelection();
                    F4_status = false;
                    resetColor();
                } else {
                    editPoliEdge.setIcon(icon_selectE);

                    vertex_select_status = false;
                    edge_select_status = false;
                    poligon_select_status = false;
                    corp_select_status = false;
                    F4_status = true;
                }
            }
        });
        editPoliPoligon.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (editPoliPoligon.getIcon().equals(icon_selectP)) {
                    editPoliPoligon.setIcon(icon_selectP2);
                    editPoliVertex.setIcon(icon_selectV);
                    editPoliEdge.setIcon(icon_selectE);
                    editPoliCorp.setIcon(icon_selectC);
                    box.setIcon(box_of);
                    cam_buton_1.setIcon(icon_cam1);
                    cam_buton_2.setIcon(icon_cam_1);

                    cam_ok_rotate = false;

                    vertex_select_status = false;
                    edge_select_status = false;
                    poligon_select_status = true;
                    corp_select_status = false;
                    box_status = false;
                    canvas.setCursor(cursor);
                    resetSelection();
                    F4_status = true;
                    resetColor();

                } else {
                    editPoliPoligon.setIcon(icon_selectP);

                    vertex_select_status = false;
                    edge_select_status = false;
                    poligon_select_status = false;
                    corp_select_status = false;

                }
            }
        });
        editPoliCorp.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (editPoliCorp.getIcon().equals(icon_selectC)) {
                    editPoliCorp.setIcon(icon_selectC2);
                    editPoliPoligon.setIcon(icon_selectP);
                    editPoliVertex.setIcon(icon_selectV);
                    editPoliEdge.setIcon(icon_selectE);

                    cam_buton_1.setIcon(icon_cam1);
                    cam_buton_2.setIcon(icon_cam_1);
                    box.setIcon(box_of);

                    cam_ok_rotate = false;

                    vertex_select_status = false;
                    edge_select_status = false;
                    poligon_select_status = false;
                    corp_select_status = true;
                    box_status = false;
                    canvas.setCursor(cursor);
                    resetSelection();
                    F4_status = true;
                    resetColor();
                } else {
                    editPoliCorp.setIcon(icon_selectC);

                    vertex_select_status = false;
                    edge_select_status = false;
                    poligon_select_status = false;
                    corp_select_status = false;
                }
            }
        });
        box.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (box.getIcon().equals(box_of)) {
                    box.setIcon(box_on);
                    box_status = true;
                    box_control = true;
                    vertex_select_status = false;
                    edge_select_status = false;
                    poligon_select_status = false;
                    corp_select_status = false;
                    cam_ok_rotate = false;

                    editPoliVertex.setIcon(icon_selectV);
                    editPoliEdge.setIcon(icon_selectE);
                    editPoliPoligon.setIcon(icon_selectP);
                    editPoliCorp.setIcon(icon_selectC);
                    cam_buton_1.setIcon(icon_cam1);
                    cam_buton_2.setIcon(icon_cam_1);
                    canvas.setCursor(cursor);

                    cube = new Cube(0, 0, 0, 0, 0, 0);

                    start_drag = false;
                    relese_drag = false;

                } else {
                    box.setIcon(box_of);
                    box_status = false;
                }
            }
        });


printColor.addActionListener(new ActionListener() {

     public void actionPerformed(ActionEvent e) {
             printMethod();
     }
 });        
cb.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
             int state = e.getStateChange();             
                 if (state == ItemEvent.SELECTED)
                 {
                    disable_colorMaterial = true;
                 }     
                 else {
                     disable_colorMaterial = false;
                  }    
            }
  });        
        

slider.addChangeListener(new ChangeListener() {

	public void stateChanged(ChangeEvent val) {
   
               fff = (float) slider.getValue();
		transparenta =  1 - (fff/100);
                TR_Field.setText(String.valueOf(fff)+"%");
	}
});


        menu_list.addMouseListener(list_listener);
        second_menu_list.addMouseListener(list2_listener);
    
        frame.add(cam_buton_1);
        frame.add(cam_buton_2);
        frame.add(editPoliVertex);
        frame.add(editPoliEdge);
        frame.add(editPoliPoligon);
        frame.add(editPoliCorp);
        frame.add(box);
        frame.add(cb);

        frame.add(disable_color);
        frame.add(x_fild);
        frame.add(y_fild);
        frame.add(z_fild);

        frame.add(menu_list);
        frame.add(second_menu_list);

        frame.add(x_label);
        frame.add(y_label);
        frame.add(z_label);

        frame.add(slider);
        frame.add(TR);
        frame.add(TR_Field);
        animator.setRunAsFastAsPossible(true);
        animator.start();

        canvas.addKeyListener(keylist);
        canvas.addGLEventListener(gl_listener);
        canvas.addMouseListener(mouseListener);
        canvas.addMouseWheelListener(wheel);
        canvas.addMouseMotionListener(mouseMotion);
        frame.getContentPane().add(canvas);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(900, 730);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

 // print method -------------------------------------------------
    public void printMethod() {
  
      for(int i=0;i<Data.final_entity.corp_list.size() ; i++)
      {
         for(int j=0 ; j<Data.final_entity.corp_list.get(i).poligon_list.size() ; j++)
         {
              System.out.println("pc_ambient.x_ac ="+formatCuloare.format(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac)+"           pc_ambient.y_ac ="+formatCuloare.format(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac)+"            pc_ambient.z_ac = "+formatCuloare.format(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac));
         }    
      }
      System.out.println();
      System.out.println();
      System.out.println();
      for(int i=0 ; i<Data.final_cube_edge_array.size() ; i++)
      {
           System.out.println("ec_ambient.x_ac = "+formatCuloare.format(Data.final_cube_edge_array.get(i).ec_ambient.x_ac)+"                   ec_ambient.y_ac = "+formatCuloare.format(Data.final_cube_edge_array.get(i).ec_ambient.y_ac)+"                 ec_ambient.z_ac = "+formatCuloare.format(Data.final_cube_edge_array.get(i).ec_ambient.z_ac));
      }
      System.out.println();
      System.out.println();
      System.out.println(); 
      for(int i=0 ; i<Data.final_cube_vertex_array.size() ; i++)
      {
          System.out.println("vc_ambient.x_ac = "+formatCuloare.format(Data.final_cube_vertex_array.get(i).vc_ambient.x_ac) +"                 vc_ambient.y_ac = "+formatCuloare.format(Data.final_cube_vertex_array.get(i).vc_ambient.y_ac)+"                      vc_ambient.z_ac = "+formatCuloare.format(Data.final_cube_vertex_array.get(i).vc_ambient.z_ac));
      }
      
    }
  //----------------------------------------------------------  
    
    
    
    public void draw_model(GL gl, String type) {


//------------------------------------------------------------------------------------------//
        gl.glEnable(GL.GL_COLOR_MATERIAL);
        if (type.equals("non rendable")) 
        {
            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) 
            {

                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) 
                {
                        
                        gl.glBegin(GL.GL_POLYGON);
                        gl.glNormal3f(Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.x_nv, Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.y_nv, Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.z_nv);

                        for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++) {

                            gl.glColor3f(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac, Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac, Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac);
                            gl.glVertex3f(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).x, Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).y, Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).z);

                        }
                        gl.glEnd();
                    
                }

            }
            
        }
         gl.glDisable(GL.GL_COLOR_MATERIAL);
        if (type.equals("rendable")) 
        {

            for (int i = 0; i < Data.final_entity.corp_list.size(); i++)
            {

                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++)
                {

                        textureParser = 0;
                        
                        if(Data.final_entity.corp_list.get(i).poligon_list.get(j).T_status == true)
                        {
                               gl.glEnable(GL.GL_TEXTURE_2D);
                               gl.glDisable(GL.GL_COLOR_MATERIAL);
                               Data.final_entity.corp_list.get(i).poligon_list.get(j).textureStream.bind();
                               
                        } else {
                               gl.glDisable(GL.GL_TEXTURE_2D);
                               gl.glEnable(GL.GL_COLOR_MATERIAL);
                        }
                    
                       
               
                 
                    
                        gl.glBegin(GL.GL_POLYGON);
                        gl.glNormal3f(Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.x_nv, Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.y_nv, Data.final_entity.corp_list.get(i).poligon_list.get(j).normalV.z_nv);

                        for (int k = 0; k < Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.size(); k++)
                        {

                            if(Data.final_entity.corp_list.get(i).poligon_list.get(j).T_status == false)
                            {
                           
                                if(disable_colorMaterial == false)
                                {
                                     if (Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac == 1 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac == 0 && Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac == 0) {
                                          gl.glColor4f(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac, Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac, Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac , transparenta);
                                     } else {
                                
                                             gl.glColor4f(1, 1, 0, transparenta); 
                                     }
                                }
                            }

                                                                                  
                            if(Data.final_entity.corp_list.get(i).poligon_list.get(j).T_status == true)
                            {
                                   gl.glTexCoord2f(Data.final_entity.corp_list.get(i).poligon_list.get(j).texturesCoordinates.get(textureParser).x_t , Data.final_entity.corp_list.get(i).poligon_list.get(j).texturesCoordinates.get(textureParser).y_t);
                            }           
                          
                            gl.glVertex3f(Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).x, Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).y, Data.final_entity.corp_list.get(i).poligon_list.get(j).vertex_list.get(k).z);
                           
                            textureParser++;
                        }
                        gl.glEnd();
                        

                }

            }
            
            gl.glDisable(GL.GL_TEXTURE_2D);
         
        }

//-----------------------------------------------------------------------------------------------------------------//    


        if (F4_status == true) {

            gl.glDisable(GL.GL_LIGHTING);
            gl.glDisable(GL.GL_LIGHT0);



            gl.glLineWidth(2);
            gl.glBegin(GL.GL_LINES);

            for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {



                gl.glColor3f(0.90f, 0.90f, 0.90f);
                
                gl.glVertex3f(Data.final_cube_edge_array.get(i).vertex_list.get(0).x, Data.final_cube_edge_array.get(i).vertex_list.get(0).y, Data.final_cube_edge_array.get(i).vertex_list.get(0).z);
                gl.glVertex3f(Data.final_cube_edge_array.get(i).vertex_list.get(1).x, Data.final_cube_edge_array.get(i).vertex_list.get(1).y, Data.final_cube_edge_array.get(i).vertex_list.get(1).z);


            }
            gl.glEnd();


        }






        if (vertex_select_status == true) {

            if (type.equals("non rendable")) {

                gl.glDisable(GL.GL_LIGHTING);
                gl.glDisable(GL.GL_LIGHT0);
                gl.glDisable(gl.GL_BLEND);
                gl.glDisable(gl.GL_DEPTH_TEST);

                gl.glPointSize(5);
                gl.glBegin(GL.GL_POINTS);

                for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {

                    gl.glColor3f(Data.final_cube_vertex_array.get(i).vc_ambient.x_ac, Data.final_cube_vertex_array.get(i).vc_ambient.y_ac, Data.final_cube_vertex_array.get(i).vc_ambient.z_ac);
                    gl.glVertex3f(Data.final_cube_vertex_array.get(i).x, Data.final_cube_vertex_array.get(i).y, Data.final_cube_vertex_array.get(i).z);


                }
                gl.glEnd();

                gl.glEnable(GL.GL_LIGHTING);
                gl.glEnable(GL.GL_LIGHT0);
                gl.glEnable(gl.GL_BLEND);
                gl.glEnable(gl.GL_DEPTH_TEST);
            }
            if (type.equals("rendable")) {

                gl.glDisable(GL.GL_LIGHTING);
                gl.glDisable(GL.GL_LIGHT0);


                gl.glPointSize(5);
                gl.glBegin(GL.GL_POINTS);

                for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {


                    if (Data.final_cube_vertex_array.get(i).vc_ambient.x_ac == 1 && Data.final_cube_vertex_array.get(i).vc_ambient.y_ac == 0 && Data.final_cube_vertex_array.get(i).vc_ambient.z_ac == 0) {
                        gl.glColor3f(Data.final_cube_vertex_array.get(i).vc_ambient.x_ac, Data.final_cube_vertex_array.get(i).vc_ambient.y_ac, Data.final_cube_vertex_array.get(i).vc_ambient.z_ac);
                    } else {
                        gl.glColor3f(0, 0, 1);
                        
                    }

                    gl.glVertex3f(Data.final_cube_vertex_array.get(i).x, Data.final_cube_vertex_array.get(i).y, Data.final_cube_vertex_array.get(i).z);


                }
                gl.glEnd();

                gl.glEnable(GL.GL_LIGHTING);
                gl.glEnable(GL.GL_LIGHT0);

            }
        }


gl.glEnable (GL.GL_BLEND);
        if (edge_select_status == true) {
            if (type.equals("rendable")) {
                gl.glDisable(GL.GL_LIGHTING);
                gl.glDisable(GL.GL_LIGHT0);



                gl.glLineWidth(2);
                gl.glBegin(GL.GL_LINES);

                for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {


                    if (Data.final_cube_edge_array.get(i).ec_ambient.x_ac == 1 && Data.final_cube_edge_array.get(i).ec_ambient.y_ac == 0 && Data.final_cube_edge_array.get(i).ec_ambient.z_ac == 0) {
                        gl.glColor3f(Data.final_cube_edge_array.get(i).ec_ambient.x_ac, Data.final_cube_edge_array.get(i).ec_ambient.y_ac, Data.final_cube_edge_array.get(i).ec_ambient.z_ac);
                    } else {
                        gl.glColor3f(0, 1, 0);
                    }

                    gl.glVertex3f(Data.final_cube_edge_array.get(i).vertex_list.get(0).x, Data.final_cube_edge_array.get(i).vertex_list.get(0).y, Data.final_cube_edge_array.get(i).vertex_list.get(0).z);
                    gl.glVertex3f(Data.final_cube_edge_array.get(i).vertex_list.get(1).x, Data.final_cube_edge_array.get(i).vertex_list.get(1).y, Data.final_cube_edge_array.get(i).vertex_list.get(1).z);


                }
                gl.glEnd();
            }
            if (type.equals("non rendable")) {
                gl.glDisable(GL.GL_LIGHTING);
                gl.glDisable(GL.GL_LIGHT0);
                gl.glDisable(gl.GL_BLEND);
                gl.glDisable(gl.GL_DEPTH_TEST);


                gl.glLineWidth(2);
                gl.glBegin(GL.GL_LINES);

                for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {


                    gl.glColor3f(Data.final_cube_edge_array.get(i).ec_ambient.x_ac, Data.final_cube_edge_array.get(i).ec_ambient.y_ac, Data.final_cube_edge_array.get(i).ec_ambient.z_ac);
                    gl.glVertex3f(Data.final_cube_edge_array.get(i).vertex_list.get(0).x, Data.final_cube_edge_array.get(i).vertex_list.get(0).y, Data.final_cube_edge_array.get(i).vertex_list.get(0).z);
                    gl.glVertex3f(Data.final_cube_edge_array.get(i).vertex_list.get(1).x, Data.final_cube_edge_array.get(i).vertex_list.get(1).y, Data.final_cube_edge_array.get(i).vertex_list.get(1).z);


                }
                gl.glEnd();
            }
            gl.glEnable(gl.GL_BLEND);
            gl.glEnable(gl.GL_DEPTH_TEST);
        }



//------MOVE---------------------------------------------------------//

        if (move_status == true || extrude_status == true) {

            gl.glDisable(GL.GL_LIGHTING);
            gl.glDisable(GL.GL_LIGHT0);
            gl.glDisable(gl.GL_BLEND);
            gl.glDisable(gl.GL_DEPTH_TEST);

            gl.glLineWidth(3);
            gl.glColor3f(0.95f, 0, 0.95f);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(x_point, y_point, z_point + lenglt / 4);
            gl.glVertex3f(x_point, y_point, z_point + lenglt);
            gl.glEnd();
            gl.glColor3f(0, 0.95f, 0);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(x_point + lenglt / 4, y_point, z_point);
            gl.glVertex3f(x_point + lenglt, y_point, z_point);
            gl.glEnd();
            gl.glColor3f(0, 0, 0.95f);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(x_point, y_point + lenglt / 4, z_point);
            gl.glVertex3f(x_point, y_point + lenglt, z_point);
            gl.glEnd();


//----Axa_Z------------------------------//

            gl.glColor3f(0.95f, 0, 0.95f);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex3f(x_point + (lenglt / 15), y_point + (lenglt / 15), z_point + lenglt);
            gl.glVertex3f(x_point - (lenglt / 15), y_point + (lenglt / 15), z_point + lenglt);
            gl.glVertex3f(x_point - (lenglt / 15), y_point - (lenglt / 15), z_point + lenglt);
            gl.glVertex3f(x_point + (lenglt / 15), y_point - (lenglt / 15), z_point + lenglt);
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point + (lenglt / 15), y_point + (lenglt / 15), z_point + lenglt);
            gl.glVertex3f(x_point - (lenglt / 15), y_point + (lenglt / 15), z_point + lenglt);
            gl.glVertex3f(x_point, y_point, z_point + lenglt + (lenglt / 2));
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point - (lenglt / 15), y_point + (lenglt / 15), z_point + lenglt);
            gl.glVertex3f(x_point - (lenglt / 15), y_point - (lenglt / 15), z_point + lenglt);
            gl.glVertex3f(x_point, y_point, z_point + lenglt + (lenglt / 2));
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point - (lenglt / 15), y_point - (lenglt / 15), z_point + lenglt);
            gl.glVertex3f(x_point + (lenglt / 15), y_point - (lenglt / 15), z_point + lenglt);
            gl.glVertex3f(x_point, y_point, z_point + lenglt + (lenglt / 2));
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point + (lenglt / 15), y_point - (lenglt / 15), z_point + lenglt);
            gl.glVertex3f(x_point + (lenglt / 15), y_point + (lenglt / 15), z_point + lenglt);
            gl.glVertex3f(x_point, y_point, z_point + lenglt + (lenglt / 2));
            gl.glEnd();
//------------------------------------------------------------------------//


//----Axa_X------------------------------//
            gl.glColor3f(0, 0.95f, 0);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex3f(x_point + lenglt, y_point + (lenglt / 15), z_point + (lenglt / 15));
            gl.glVertex3f(x_point + lenglt, y_point - (lenglt / 15), z_point + (lenglt / 15));
            gl.glVertex3f(x_point + lenglt, y_point - (lenglt / 15), z_point - (lenglt / 15));
            gl.glVertex3f(x_point + lenglt, y_point + (lenglt / 15), z_point - (lenglt / 15));
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point + lenglt, y_point + (lenglt / 15), z_point + (lenglt / 15));
            gl.glVertex3f(x_point + lenglt, y_point - (lenglt / 15), z_point + (lenglt / 15));
            gl.glVertex3f(x_point + lenglt + (lenglt / 2), y_point, z_point);
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point + lenglt, y_point - (lenglt / 15), z_point + (lenglt / 15));
            gl.glVertex3f(x_point + lenglt, y_point - (lenglt / 15), z_point - (lenglt / 15));
            gl.glVertex3f(x_point + lenglt + (lenglt / 2), y_point, z_point);
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point + lenglt, y_point - (lenglt / 15), z_point - (lenglt / 15));
            gl.glVertex3f(x_point + lenglt, y_point + (lenglt / 15), z_point - (lenglt / 15));
            gl.glVertex3f(x_point + lenglt + (lenglt / 2), y_point, z_point);
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point + lenglt, y_point + (lenglt / 15), z_point - (lenglt / 15));
            gl.glVertex3f(x_point + lenglt, y_point + (lenglt / 15), z_point + (lenglt / 15));
            gl.glVertex3f(x_point + lenglt + (lenglt / 2), y_point, z_point);
            gl.glEnd();
//--------------------------------------------//

            
//----Axa_Y-------------------------------------//
            gl.glColor3f(0, 0, 0.95f);
            gl.glBegin(GL.GL_QUADS);
            gl.glVertex3f(x_point + (lenglt / 15), y_point + lenglt, z_point + (lenglt / 15));
            gl.glVertex3f(x_point - (lenglt / 15), y_point + lenglt, z_point + (lenglt / 15));
            gl.glVertex3f(x_point - (lenglt / 15), y_point + lenglt, z_point - (lenglt / 15));
            gl.glVertex3f(x_point + (lenglt / 15), y_point + lenglt, z_point - (lenglt / 15));
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point + (lenglt / 15), y_point + lenglt, z_point + (lenglt / 15));
            gl.glVertex3f(x_point - (lenglt / 15), y_point + lenglt, z_point + (lenglt / 15));
            gl.glVertex3f(x_point, y_point + lenglt + (lenglt / 2), z_point);
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point - (lenglt / 15), y_point + lenglt, z_point + (lenglt / 15));
            gl.glVertex3f(x_point - (lenglt / 15), y_point + lenglt, z_point - (lenglt / 15));
            gl.glVertex3f(x_point, y_point + lenglt + (lenglt / 2), z_point);
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point - (lenglt / 15), y_point + lenglt, z_point - (lenglt / 15));
            gl.glVertex3f(x_point + (lenglt / 15), y_point + lenglt, z_point - (lenglt / 15));
            gl.glVertex3f(x_point, y_point + lenglt + (lenglt / 2), z_point);
            gl.glEnd();
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex3f(x_point + (lenglt / 15), y_point + lenglt, z_point - (lenglt / 15));
            gl.glVertex3f(x_point + (lenglt / 15), y_point + lenglt, z_point + (lenglt / 15));
            gl.glVertex3f(x_point, y_point + lenglt + (lenglt / 2), z_point);
            gl.glEnd();
//----------------------------------------------//


            gl.glEnable(gl.GL_BLEND);
            gl.glEnable(gl.GL_DEPTH_TEST);
        }
//-------------------------------------------------------------//

    }

    public void drawPlanes(GL gl, double y) {



        if (box_status == true) {


            gl.glDisable(GL.GL_LIGHTING);
            gl.glDisable(GL.GL_LIGHT0);

            if (relese_drag == false) {


                gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(0.5f, 0.5f, 0.5f);
                gl.glVertex3f(-10, -10, 0);
                gl.glVertex3f(10, -10, 0);
                gl.glVertex3f(10, 10, 0);
                gl.glVertex3f(-10, 10, 0);
                gl.glEnd();

            }
            if (relese_drag == true) {

                gl.glBegin(GL.GL_QUADS);
                gl.glColor3f(0.5f, 0.5f, 0.5f);
                gl.glVertex3f(-10, (float) y, -10);
                gl.glVertex3f(10, (float) y, -10);
                gl.glVertex3f(10, (float) y, 10);
                gl.glVertex3f(-10, (float) y, 10);
                gl.glEnd();

            }


        } else {

            gl.glDisable(GL.GL_LIGHTING);
            gl.glDisable(GL.GL_LIGHT0);
            gl.glColor3f(1f, 1f, 1f);
            gl.glPointSize(3);
            gl.glBegin(gl.GL_POINTS);
            gl.glVertex3f(0, 0, 0);
            gl.glEnd();

            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(-10, -10, 0);
            gl.glVertex3f(10, -10, 0);
            gl.glEnd();
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(10, -10, 0);
            gl.glVertex3f(10, 10, 0);
            gl.glEnd();
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(10, 10, 0);
            gl.glVertex3f(-10, 10, 0);
            gl.glEnd();
            gl.glBegin(GL.GL_LINES);
            gl.glVertex3f(-10, 10, 0);
            gl.glVertex3f(-10, -10, 0);
            gl.glEnd();

        }

    }

    public void processData() {


        if (RGB_click[0] == 0 && RGB_click[1] == 0 && RGB_click[2] == 0 || RGB_click[0] == 0.5f && RGB_click[1] == 0.5f && RGB_click[2] == 0.5f) {
            resetColor();

            move_status = false;
            extrude_status = false;

            move_x_status = false;
            move_y_status = false;
            move_z_status = false;

            extrude_x_status = false;
            extrude_y_status = false;
            extrude_z_status = false;
        }


        if (poligon_select_status == true) {

            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {

                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {


                    if (Float.valueOf(formatCuloare.format(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac)) == RGB_click[0] && Float.valueOf(formatCuloare.format(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac)) == RGB_click[1] && Float.valueOf(formatCuloare.format(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac)) == RGB_click[2]) {

                        if (CTRL_Status == false) {
                            resetColor();
                            SelectedData.poli_select.clear();
                            ps = new Poli_S(RGB_click[0], RGB_click[1], RGB_click[2]);
                            SelectedData.poli_select.add(ps);
                        } else {
                            ps = new Poli_S(RGB_click[0], RGB_click[1], RGB_click[2]);
                            SelectedData.poli_select.add(ps);
                        }


                        Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac = 1;
                        Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac = 0;
                        Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac = 0;

                    }

                }

            }

            x_point = (float) Ncoord[0];
            y_point = (float) Ncoord[1];
            z_point = (float) Ncoord[2];
        }



        if (corp_select_status == true) {

            int index;

            for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {

                for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {

                    if (Float.valueOf(formatCuloare.format(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac)) == RGB_click[0] && Float.valueOf(formatCuloare.format(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac)) == RGB_click[1] && Float.valueOf(formatCuloare.format(Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac)) == RGB_click[2]) {

                        SelectedData.corp_select.clear();
                        cs = new Corp_S(0, 0, 0);
                        SelectedData.corp_select.add(cs);

                        index = i;

                        for (int k = 0; k < Data.final_entity.corp_list.get(index).poligon_list.size(); k++) {


                            Data.final_entity.corp_list.get(index).poligon_list.get(k).pc_ambient.x_ac = 1;
                            Data.final_entity.corp_list.get(index).poligon_list.get(k).pc_ambient.y_ac = 0;
                            Data.final_entity.corp_list.get(index).poligon_list.get(k).pc_ambient.z_ac = 0;
                        }


                    }

                }

            }
            x_point = (float) Ncoord[0];
            y_point = (float) Ncoord[1];
            z_point = (float) Ncoord[2];
        }

        if (vertex_select_status == true) {

            for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {


                if (Float.valueOf(formatCuloare.format(Data.final_cube_vertex_array.get(i).vc_ambient.x_ac)) == RGB_click[0] && Float.valueOf(formatCuloare.format(Data.final_cube_vertex_array.get(i).vc_ambient.y_ac)) == RGB_click[1] && Float.valueOf(formatCuloare.format(Data.final_cube_vertex_array.get(i).vc_ambient.z_ac)) == RGB_click[2]) {

                    if (CTRL_Status == false) {
                        resetColor();
                        SelectedData.vert_select.clear();
                        vs = new Vert_S(Data.final_cube_vertex_array.get(i).x, Data.final_cube_vertex_array.get(i).y, Data.final_cube_vertex_array.get(i).z, RGB_click[0], RGB_click[1], RGB_click[2]);
                        SelectedData.vert_select.add(vs);
                    } else {
                        vs = new Vert_S(Data.final_cube_vertex_array.get(i).x, Data.final_cube_vertex_array.get(i).y, Data.final_cube_vertex_array.get(i).z, RGB_click[0], RGB_click[1], RGB_click[2]);
                        SelectedData.vert_select.add(vs);
                    }

                    Data.final_cube_vertex_array.get(i).vc_ambient.x_ac = 1;
                    Data.final_cube_vertex_array.get(i).vc_ambient.y_ac = 0;
                    Data.final_cube_vertex_array.get(i).vc_ambient.z_ac = 0;

                    x_point = (float) Ncoord[0];
                    y_point = (float) Ncoord[1];
                    z_point = (float) Ncoord[2];

                }


            }

        }

        if (edge_select_status == true) {

            String str;
            int s = 0;


            for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {


                if (Float.valueOf(formatCuloare.format(Data.final_cube_edge_array.get(i).ec_ambient.x_ac)) == RGB_click[0] && Float.valueOf(formatCuloare.format(Data.final_cube_edge_array.get(i).ec_ambient.y_ac)) == RGB_click[1] && Float.valueOf(formatCuloare.format(Data.final_cube_edge_array.get(i).ec_ambient.z_ac)) == RGB_click[2]) {
                    if (CTRL_Status == false) {
                        resetColor();
                        SelectedData.edge_select.clear();
                        es = new Edge_S(Data.final_cube_edge_array.get(i).vertex_list.get(0).x, Data.final_cube_edge_array.get(i).vertex_list.get(0).y, Data.final_cube_edge_array.get(i).vertex_list.get(0).z, Data.final_cube_edge_array.get(i).vertex_list.get(1).x, Data.final_cube_edge_array.get(i).vertex_list.get(1).y, Data.final_cube_edge_array.get(i).vertex_list.get(1).z, RGB_click[0], RGB_click[1], RGB_click[2]);
                        SelectedData.edge_select.add(es);
                    } else {
                        es = new Edge_S(Data.final_cube_edge_array.get(i).vertex_list.get(0).x, Data.final_cube_edge_array.get(i).vertex_list.get(0).y, Data.final_cube_edge_array.get(i).vertex_list.get(0).z, Data.final_cube_edge_array.get(i).vertex_list.get(1).x, Data.final_cube_edge_array.get(i).vertex_list.get(1).y, Data.final_cube_edge_array.get(i).vertex_list.get(1).z, RGB_click[0], RGB_click[1], RGB_click[2]);
                        SelectedData.edge_select.add(es);
                    }

                    Data.final_cube_edge_array.get(i).ec_ambient.x_ac = 1;
                    Data.final_cube_edge_array.get(i).ec_ambient.y_ac = 0;
                    Data.final_cube_edge_array.get(i).ec_ambient.z_ac = 0;

                    x_point = (float) Ncoord[0];
                    y_point = (float) Ncoord[1];
                    z_point = (float) Ncoord[2];

                }
                s++;
            }

        }

    }

    public void resetSelection() {

        SelectedData.corp_select.clear();
        SelectedData.edge_select.clear();
        SelectedData.poli_select.clear();
        SelectedData.vert_select.clear();
    }

    public void resetColor() {

        resetSelection();


        float r_p = 1;
        float g_p = 0;
        
        float b_p = 0.01f;


        for (int i = 0; i < Data.final_entity.corp_list.size(); i++) {

            for (int j = 0; j < Data.final_entity.corp_list.get(i).poligon_list.size(); j++) {


                if(Float.valueOf(formatCuloare.format(b_p))  <  0.99f)
                {
                   b_p += 0.01f;
                }
                else
                {                
                   g_p += 0.01f;
                   b_p =  0.01f;
                } 
                
                
                
                Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.x_ac = r_p;
                Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.y_ac = g_p;
                Data.final_entity.corp_list.get(i).poligon_list.get(j).pc_ambient.z_ac = b_p;


            }

        }



        float r_v = 0.01f;
        
        float g_v = 0;
        float b_v = 1;


        for (int i = 0; i < Data.final_cube_vertex_array.size(); i++) {


            
             if(Float.valueOf(formatCuloare.format(r_v)) < 0.99f)
            {
               r_v += 0.01f;
            }
             else
                  { 
                      g_v+= 0.01f;  
                      r_v = 0.01f;
                  }
                                               
            Data.final_cube_vertex_array.get(i).vc_ambient.x_ac = r_v;
            Data.final_cube_vertex_array.get(i).vc_ambient.y_ac = g_v;
            Data.final_cube_vertex_array.get(i).vc_ambient.z_ac = b_v;

        }




        float r_e = 0;
        float g_e = 1;
        
        float b_e = 0.01f;


        for (int i = 0; i < Data.final_cube_edge_array.size(); i++) {


            if(Float.valueOf(formatCuloare.format(b_e)) < 0.99f)
            {
               b_e += 0.01f;
            }
            else
            {
               r_e+=0.01f; 
               b_e =0.01f;
            }
                        
            
            Data.final_cube_edge_array.get(i).ec_ambient.x_ac = r_e;
            Data.final_cube_edge_array.get(i).ec_ambient.y_ac = g_e;
            Data.final_cube_edge_array.get(i).ec_ambient.z_ac = b_e;

        }

    }

    public void processDeleteRequest() 
    {

        if (SelectedData.corp_select.size() == 1) {
            Data.final_entity.corp_list.clear();
            SelectedData.corp_select.clear();

            cube = null;
        }

    }

    public void GluProject(float x3d, float y3d, float z3d) 
    {
        glu.gluProject(x3d, y3d, z3d, mvmatrix, 0, projmatrix, 0, viewport, 0, coor, 0);
    }

    
    public void GluUnProject(int x, int y, double Ncoord[]) {

        gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
        gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, mvmatrix, 0);
        gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, projmatrix, 0);

        FloatBuffer floatBuffer = FloatBuffer.allocate(1);
        gl.glReadPixels(x, y, 1, 1, GL.GL_DEPTH_COMPONENT, GL.GL_FLOAT, floatBuffer);

        float mouse_Z = floatBuffer.get(0);

        boolean test1 = glu.gluUnProject(x, y, mouse_Z, mvmatrix, 0, projmatrix, 0, viewport, 0, Ncoord, 0);

    }

    public Texture LoadImage(String path) throws IOException
    {
        Texture texture =   TextureIO_.loadTextures(path, gl);
 
        return  texture;
    }
    
    public void readTextures(){
         
                for(int i=0;i<textures_filds.size() ; i++)
                {
                    try {
                        
                            tt =  TextureIO_.loadTextures(textures_filds.get(i), gl);
                            finals_textures.add(tt);
                           
                        } catch (IOException ex) 
                               {
                                   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                               }
                  
                }
        
    }
}