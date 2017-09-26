package com.example.claudio.platform.text;

import com.example.claudio.platform.toolBox.Vector2f;

/**
 * Created by Claudio on 21/09/2017.
 */

public class TextBatch {

    final static int VERTEX_SIZE = 4;
    final static int VERTICES_PER_SPRITE = 4;
    final static int INDICES_PER_SPRITE = 6;

    TextModel model;
    float[] vertexBuffer;
    int bufferIndex;
    int maxSprites;
    int numSprites;

    public TextBatch(int maxSprites) {
        this.vertexBuffer = new float[maxSprites * VERTICES_PER_SPRITE * VERTEX_SIZE];
        this.model = new TextModel(maxSprites * VERTICES_PER_SPRITE, maxSprites * INDICES_PER_SPRITE);
        this.bufferIndex = 0;
        this.maxSprites = maxSprites;
        this.numSprites = 0;

        short[] indices = new short[maxSprites * INDICES_PER_SPRITE];
        short j = 0;
        for(int i = 0; i < indices.length; i+=INDICES_PER_SPRITE, j += VERTICES_PER_SPRITE ) {
            indices[i + 0] = (short)( j + 0 );
            indices[i + 1] = (short)( j + 1 );
            indices[i + 2] = (short)( j + 2 );
            indices[i + 3] = (short)( j + 2 );
            indices[i + 4] = (short)( j + 3 );
            indices[i + 5] = (short)( j + 0 );
        }
        model.setIndices(indices);
        model.bindBuffers();
    }

    public void beginBatch() {
        numSprites = 0;
        bufferIndex = 0;
    }

    public void endBatch() {
        if(numSprites > 0) {
            model.setVertices(vertexBuffer);
            model.bind();
            model.draw(numSprites * INDICES_PER_SPRITE);
            model.unbind();
        }
    }

    public void drawSprite(Vector2f position, Vector2f dimension, TextureRegion region) {
        if(numSprites == maxSprites) {
            endBatch();
            numSprites = 0;
            bufferIndex = 0;
        }

        float halfWidth = dimension.x / 2.0f;                 // Calculate Half Width
        float halfHeight = dimension.y / 2.0f;               // Calculate Half Height
        float x1 = position.x - halfWidth;                       // Calculate Left X
        float y1 = position.y + halfHeight;                      // Calculate Top Y
        float x2 = position.x + halfWidth;                       // Calculate Right X
        float y2 = position.y - halfHeight;                      // Calculate Bottom Y

        vertexBuffer[bufferIndex++] = x1;               // Add X for Vertex 0
        vertexBuffer[bufferIndex++] = y1;               // Add Y for Vertex 0
        vertexBuffer[bufferIndex++] = region.u1;        // Add U for Vertex 0
        vertexBuffer[bufferIndex++] = region.v2;        // Add V for Vertex 0

        vertexBuffer[bufferIndex++] = x2;               // Add X for Vertex 1
        vertexBuffer[bufferIndex++] = y1;               // Add Y for Vertex 1
        vertexBuffer[bufferIndex++] = region.u2;        // Add U for Vertex 1
        vertexBuffer[bufferIndex++] = region.v2;        // Add V for Vertex 1

        vertexBuffer[bufferIndex++] = x2;               // Add X for Vertex 2
        vertexBuffer[bufferIndex++] = y2;               // Add Y for Vertex 2
        vertexBuffer[bufferIndex++] = region.u2;        // Add U for Vertex 2
        vertexBuffer[bufferIndex++] = region.v1;        // Add V for Vertex 2

        vertexBuffer[bufferIndex++] = x1;               // Add X for Vertex 3
        vertexBuffer[bufferIndex++] = y2;               // Add Y for Vertex 3
        vertexBuffer[bufferIndex++] = region.u1;        // Add U for Vertex 3
        vertexBuffer[bufferIndex++] = region.v1;        // Add V for Vertex 3

        numSprites++;
    }



}
