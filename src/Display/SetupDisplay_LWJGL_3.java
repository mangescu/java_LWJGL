
package Display;


import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;
import static org.lwjgl.opengl.GL.createCapabilities;

public class SetupDisplay_LWJGL_3 {
 
    public long window;
    
    int WIN_HEIGHT = 1000;
    int WIN_WIDTH  = 1000;
    
    public void initWindow(){
    
        if(!glfwInit()){
            System.out.println("faild to initialize");
        }
    
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        window = glfwCreateWindow(WIN_WIDTH, WIN_HEIGHT,"window", 0,0);
        
        if(window == 0 ) {
           System.out.println("faild to create window");
        }
        
        GLFWVidMode  videoMode  = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, 900, 50);
        
        glfwShowWindow(window);
      
        glfwMakeContextCurrent(window);
        
        createCapabilities();    
    }

    public long getWindow() {
        return window;
    }

    public int getWIN_HEIGHT() {
        return WIN_HEIGHT;
    }

    public int getWIN_WIDTH() {
        return WIN_WIDTH;
    }
    
}
