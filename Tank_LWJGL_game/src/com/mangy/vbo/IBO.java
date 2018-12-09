


package com.mangy.vbo;


import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;


public class IBO {

    private final IntBuffer buffer;

    private int handle = -1;


    public IBO(final int[] indices) {

        buffer = BufferUtils.createIntBuffer(indices.length);
        buffer.put(indices);
        buffer.flip();

    }


    public final void glInit() {

        handle = glGenBuffers();

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, handle);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

    }


    public final void glDraw(final int type) {

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, handle);
        glDrawElements(type, buffer.capacity(), GL_UNSIGNED_INT, 0);

    }


    public final void glDispose() {

        glDeleteBuffers(handle);

    }


}
