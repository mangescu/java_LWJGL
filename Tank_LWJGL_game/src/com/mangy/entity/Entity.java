

package com.mangy.entity;

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


public class Entity {
  
    	public int counter = 0;
	int resetCount = 10;
	int frame_counter = 0;
	
	public boolean dinamicRender = true;
	
	
	public String name;

	public float X = 1;
	public float Y = 0;
	public float Z = 0.1f;
	public float angle = 0;
        
	Data data = new Data();
	public String direction = "-x"; 
	public int fireBullerCounter = 0;
        
        
	Texture entity_texture_frame0;
	Texture entity_texture_frame1;
	
	IBO entity_index_buffer;
	VBO entity_vertex_buffer;
	
	int FLOAT_SIZE = Float.SIZE / Byte.SIZE;
        
        
     public Entity(String[] Textures) throws FileNotFoundException {
		entity_texture_frame0 = new Texture(new FileInputStream(new File(Textures[0])));
		entity_texture_frame1 = new Texture(new FileInputStream(new File(Textures[1])));
	
		entity_index_buffer  = new IBO(data.getPLAYER_IBO());
		entity_vertex_buffer = new VBO(data.getPLAYER_VBO());
		
		entity_texture_frame0.glInit();
		entity_texture_frame1.glInit();
		
		entity_index_buffer.glInit();
		entity_vertex_buffer.glInit();  
     }  
     
	public void renderEntity() {
		
		if(counter > resetCount) {
			if(frame_counter == 0) {
				if(dinamicRender)
			   frame_counter = 1;
			} else { 
			   frame_counter = 0;
			}
			counter = 0;
		}
		counter++;
		
		
		GL11.glTranslatef(X, Y, Z);
		GL11.glRotatef(angle, 0, 0, Z);
		
            if(frame_counter == 0) {
                   entity_texture_frame0.glBind(); 
            } else {
                   entity_texture_frame1.glBind();
            }
                   entity_vertex_buffer.glBind();

            glVertexPointer(3, GL_FLOAT, 5 * FLOAT_SIZE, 0 * FLOAT_SIZE);
            glTexCoordPointer(2, GL_FLOAT, 5 * FLOAT_SIZE, 3 * FLOAT_SIZE);


            entity_index_buffer.glDraw(GL_QUADS);	
		
	}     
        
        public void deleteEntity()  {
           entity_texture_frame0.glDispose();;
           entity_texture_frame1.glDispose();
           
           entity_index_buffer.glDispose();
           entity_vertex_buffer.glDispose();    
        }
}
