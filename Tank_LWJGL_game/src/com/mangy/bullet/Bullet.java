

package com.mangy.bullet;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glVertexPointer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.lwjgl.opengl.GL11;


import com.mangy.vbo.IBO;
import com.mangy.vbo.Texture;
import com.mangy.vbo.VBO;

public class Bullet {

	public String owner;
	
	public float X = 0.1f;
	public float Y = 0.1f;
	public float Z = 0.1f;
	public float angle = 0;
        
	
	public String  direction = "0";
	
	Data data = new Data();
	Texture bullet_texture;
	
	
	IBO bullet_index_buffer;
	VBO bullet_vertex_buffer;
	
	int FLOAT_SIZE = Float.SIZE / Byte.SIZE;
	
	
	public  Bullet(Texture extures) throws FileNotFoundException {
		bullet_texture = extures;
		
		bullet_index_buffer  = new IBO(data.getBULLET_IBO());
		bullet_vertex_buffer = new VBO(data.getBULLET_VBO());
		
		bullet_texture.glInit();
		bullet_index_buffer.glInit();
		bullet_vertex_buffer.glInit();
	}
	
	public void renderBullet() {
		
		GL11.glTranslatef(X, Y, Z);
		//GL11.glRotatef(angle, 0, 0, Z);

		bullet_texture.glBind();
		bullet_vertex_buffer.glBind();
		
        glVertexPointer(3, GL_FLOAT, 5 * FLOAT_SIZE, 0 * FLOAT_SIZE);
        glTexCoordPointer(2, GL_FLOAT, 5 * FLOAT_SIZE, 3 * FLOAT_SIZE);		
        
        bullet_index_buffer.glDraw(GL_QUADS);	
		
	}
        
        public void deleteBullet()  {
           bullet_index_buffer.glDispose();
           bullet_vertex_buffer.glDispose();    
        }        
	
}
