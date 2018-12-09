
package Render;

import Camera.Camera;
import IBO.IBO;
import Maths.Maths;
import Quad.Model;
import Texture.Textura;
import java.nio.FloatBuffer;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import shaders.ShaderProgram;

public class Renderer {
    
	private static final float FOV = 60;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;    
    
        public  Matrix4f projectionMatrix; 
        public  Matrix4f transformationMatrix;
        
        ShaderProgram shaderProgram;
        
      //  FloatBuffer fb = BufferUtils.createFloatBuffer(16);

        Maths maths ;
        Camera camera;
        
        public Renderer(ShaderProgram shaderProgram  , Maths maths , Camera camera) {
                this.maths = maths;
                this.shaderProgram = shaderProgram;
                this.camera = camera;
		createProjectionMatrix();
		shaderProgram.start();
		shaderProgram.loadProjectionMatrix(projectionMatrix);
		shaderProgram.stop();            
        }
	public void prepare() {
                    glClear(GL_COLOR_BUFFER_BIT);
                    glEnable(GL_BLEND);
                    glEnable(GL_TEXTURE_2D);
                    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	} 
      
	public void initQuad(IBO ibo) {
		prepare();
		glBindVertexArray(ibo.iboID);
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);

	}
        public void loadNumbetOfRows(int rows , ShaderProgram shaderProgram) {
              shaderProgram.loadNumbetOfRows(rows);
        }
        public void loadTexTransparenci(float transparancy ,  ShaderProgram shader) {
			shaderProgram.loadTexTransparency(transparancy);
		}
	public void loadQuadMatrix(Model quad , ShaderProgram shader) 
	{
		transformationMatrix = maths.createTransformationMatrix(quad.getPosition(), quad.quadRotation, quad.getScale());
		shader.loadTransformationMatrix(transformationMatrix);		
		shader.loadOffset(quad.getTextureXOffset() , quad.getTextureYOffset());
	}
	public void renderQuad(Model quad) {
		GL11.glDrawElements(GL11.GL_TRIANGLE_STRIP, quad.ibo.vertexCount , GL11.GL_UNSIGNED_INT , 0);
	}
	public void unbindQuadArray() 
	{
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glBindVertexArray(0);		
	}        
	private void createProjectionMatrix() {

    	projectionMatrix = new Matrix4f();
		float aspectRatio = (float) 900 / (float) 900;
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))));
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
          
		projectionMatrix.m00(x_scale);
		projectionMatrix.m11(y_scale);
		projectionMatrix.m22(-((FAR_PLANE + NEAR_PLANE) / frustum_length));
		projectionMatrix.m23(-1);
		projectionMatrix.m32(-((2 * NEAR_PLANE * FAR_PLANE) / frustum_length));
		projectionMatrix.m33(0);

	}        
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}    
}
