package com.cc.platform.tile;

import com.cc.platform.toolBox.Vector2f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Claudio on 29/03/2017.
 */

public class TileLevel {

    private String name;
    private int levelWidth, levelHeight, tileWidth, tileHeight;
    private List<Integer> levelData = new ArrayList<>();
    private int tilesNumber;
    private float[] positions;

    public TileLevel(){}

    public void calculatePositions(){
        positions = new float[levelWidth*levelHeight*3];
        int k=0;
        for(int i=0; i<levelHeight; i++){
            for(int j=0; j<levelWidth; j++){
                positions[k] = j*tileWidth;  k++;
                positions[k] = i*tileHeight; k++;
                positions[k] = -50; k++;
            }
        }
    }

    public void setData(String data){
        data = data.replaceAll("\\s+", "");
        String[] tilesBuffer = data.split(",");
        for(String str : tilesBuffer){
            levelData.add(Integer.parseInt(str));
        }
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){return name;}

    public void setLevelWidth(int width){
        this.levelWidth = width;
    }

    public void setLevelHeight(int height){
        this.levelHeight = height;
    }

    public void setTileWidth(int width){this.tileWidth = width;}

    public void setTileHeight(int height){this.tileHeight = height;}

    public int getTilesNumber(){return positions.length/3;}

    public int getTileId(int tile){
        try {
            return levelData.get(tile).intValue();
        }catch (IndexOutOfBoundsException e){
            return 0;
        }
    }

    public Vector2f getDimension(){return new Vector2f(levelWidth, levelHeight);}

    public Vector2f getTilePositions(int tile){
        return new Vector2f(positions[tile*3], positions[tile*3+1]);
    }

    public FloatBuffer getPositions(){
        ByteBuffer bbf = ByteBuffer.allocateDirect(positions.length*4);
        bbf.order(ByteOrder.nativeOrder());
        FloatBuffer positionBuffer = bbf.asFloatBuffer();
        positionBuffer.put(positions);
        positionBuffer.position(0);
        return positionBuffer;
    }

    public IntBuffer getTextureIndex(){
        ByteBuffer bbf = ByteBuffer.allocateDirect(levelData.size()*4);
        bbf.order(ByteOrder.nativeOrder());
        IntBuffer textureIndexBuffer = bbf.asIntBuffer();
        int[] data = new int[levelData.size()];
        for(int i=0; i<levelData.size(); i++)
            data[i] = levelData.get(i);
        textureIndexBuffer.put(data);
        textureIndexBuffer.position(0);
        return textureIndexBuffer;
    }

}
