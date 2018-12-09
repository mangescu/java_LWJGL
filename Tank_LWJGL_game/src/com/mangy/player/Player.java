

package com.mangy.player;


import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glVertexPointer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import  org.lwjgl.opengl.GL11;

import com.mangy.vbo.IBO;
import com.mangy.vbo.Texture;
import com.mangy.vbo.VBO;

 public class Player {
	
	public int counter = 0;
	int resetCount = 10;
	int frame_counter = 0;
	
	public boolean dinamicRender = false;
	
	
	public String name;

	public float X = 1;
	public float Y = 0;
	public float Z = 0.1f;

	public float angle = 0;
	
	
	
	Data data = new Data();
	
	Texture player_texture_frame0;
	Texture player_texture_frame1;
	
	
	IBO player_index_buffer;
	VBO player_vertex_buffer;
	
	int FLOAT_SIZE = Float.SIZE / Byte.SIZE;
	

	
	public Player(String[] Textures) throws FileNotFoundException 
	{
		player_texture_frame0 = new Texture(new FileInputStream(new File(Textures[0])));
		player_texture_frame1 = new Texture(new FileInputStream(new File(Textures[1])));
	
		player_index_buffer  = new IBO(data.getPLAYER_IBO());
		player_vertex_buffer = new VBO(data.getPLAYER_VBO());
		
		player_texture_frame0.glInit();
		player_texture_frame1.glInit();
		
		player_index_buffer.glInit();
		player_vertex_buffer.glInit();
		
	}
	
	public void renderPlayer() {
		
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
        	player_texture_frame0.glBind(); 
        } else {
        	player_texture_frame1.glBind();
        }
		player_vertex_buffer.glBind();

        glVertexPointer(3, GL_FLOAT, 5 * FLOAT_SIZE, 0 * FLOAT_SIZE);
        glTexCoordPointer(2, GL_FLOAT, 5 * FLOAT_SIZE, 3 * FLOAT_SIZE);

        
        player_index_buffer.glDraw(GL_QUADS);	
		
	}
        
        public void deleteEntity()  {
           player_texture_frame0.glDispose();
           player_texture_frame1.glDispose();           
           player_index_buffer.glDispose();
           player_vertex_buffer.glDispose();    
        }          

	
}

