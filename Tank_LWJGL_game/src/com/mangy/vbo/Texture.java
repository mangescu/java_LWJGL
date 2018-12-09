

package com.mangy.vbo;



import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_LINEAR_MIPMAP_LINEAR;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL12.GL_BGRA;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL14.GL_GENERATE_MIPMAP;
import static org.lwjgl.opengl.GL11.GL_NEAREST;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;


public class Texture {

    private final int type;
    private final int width;
    private final int height;
    private final int min;
    private final int mag;
    private final ByteBuffer buffer;

    private int handle = -1;


    public Texture(final InputStream is) {

        BufferedImage image = loadImage(is);

        type = GL_BGRA;
        width = image.getWidth();
        height = image.getHeight();
        min = GL_LINEAR_MIPMAP_LINEAR;
        mag = GL_LINEAR;

        image = flipImage(image);

        final int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

        buffer = BufferUtils.createByteBuffer(pixels.length * Integer.SIZE / Byte.SIZE);
        buffer.asIntBuffer().put(pixels);
        buffer.rewind();

    }


    public Texture(final int type, final int width, final int height, final byte[] pixels, final int min, final int mag) {

        this.type = type;
        this.width = width;
        this.height = height;
        this.min = min;
        this.mag = mag;

        buffer = BufferUtils.createByteBuffer(pixels.length);
        buffer.put(pixels);
        buffer.rewind();

    }


    private BufferedImage loadImage(final InputStream is) {

        BufferedImage image;

        try {

            image = ImageIO.read(is);

            is.close();

        } catch (final IOException e) {

            throw new RuntimeException(e);

        }

        return image;

    }


    private BufferedImage flipImage(final BufferedImage image) {

        final AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -image.getHeight());

        final AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        final BufferedImage result = op.filter(image, null);

        return result;

    }


    public final void glInit() {

        handle = glGenTextures();

        glEnable(GL_TEXTURE_2D);

        glBindTexture(GL_TEXTURE_2D, handle);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);   
        
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_GENERATE_MIPMAP, GL_TRUE);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, type, GL_UNSIGNED_BYTE, buffer);
        glBindTexture(GL_TEXTURE_2D, 0);

    }


    public final void glBind() {

        glBindTexture(GL_TEXTURE_2D, handle);

    }


    public final void glDispose() {

        glDeleteTextures(handle);

    }


}
