
package pkg3d_studio_max;

import com.sun.opengl.util.texture.Texture;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.media.opengl.GL;
import javax.imageio.ImageIO;
import com.sun.opengl.util.texture.TextureIO;


public class TextureIO_  {
    
    static  Texture          texture = null;
    static  BufferedImage    im      = null;
      
    static Texture loadTextures(String file_path , GL  gl) throws IOException {
    
        
           im=ImageIO.read(new File(file_path));
	   texture = TextureIO.newTexture(im,true);
           
                    gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
                    gl.glTexParameteri(GL.GL_TEXTURE_2D, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
           
    return texture;
    }
}
