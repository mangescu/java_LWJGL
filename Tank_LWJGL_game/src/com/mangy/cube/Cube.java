
package com.mangy.cube;

import com.mangy.vbo.IBO;
import com.mangy.vbo.Texture;
import com.mangy.vbo.VBO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glVertexPointer;


public class Cube {
    
    public String type;
    public float X = 0;
    public float Y = 0;
    public float Z = 0.1f;
    
    Data data = new Data();
    Texture  cube_texture;
    
    IBO cube_index_buffer;
    VBO cube_vertex_buffer;
    
    int FLOAT_SIZE = Float.SIZE / Byte.SIZE;  
    
    public Cube(Texture   texture , String type) throws FileNotFoundException 
    {
        this.type = type;
        cube_texture = texture;    
        
        cube_index_buffer  = new IBO(data.getCUBE_IBO());
	cube_vertex_buffer = new VBO(data.getCUBE_VBO());
		
	cube_texture.glInit();	
	cube_index_buffer.glInit();
	cube_vertex_buffer.glInit();
    }
    public void renderCube() {
        
       GL11.glTranslatef(X, Y, Z);

       cube_texture.glBind();
       cube_vertex_buffer.glBind();
       
       glVertexPointer(3, GL_FLOAT, 5 * FLOAT_SIZE, 0 * FLOAT_SIZE);
       glTexCoordPointer(2, GL_FLOAT, 5 * FLOAT_SIZE, 3 * FLOAT_SIZE);

        cube_index_buffer.glDraw(GL_QUADS);	
    }
    
    public void deleteCube()  {
          cube_index_buffer.glDispose();
          cube_vertex_buffer.glDispose();    
    }
}
