package com.example.claudio.platform.entities;

/**
 * Created by Claudio on 31/05/2016.
 */
/*public class Background extends Entity {

    private static final int BACKGROUND_TEXTURE_ID = R.raw.background;

    private float[] backVertices = {
            -4f,  1f, 0,
            -4f, -1f, 0,
            0f, -1f, 0,
            0f,  1f, 0

    };

    private float[] backTexCoords = {
            0,0,
            0,1,
            1,1,
            1,0
    };

    private int[] backIndices = {
            0,1,3,
            3,1,2
    };

    private int side;

    private SquareBoundingBox leftMargin;
    private SquareBoundingBox rightMargin;

    public Background(Loader loader, Vector3f position, int side){
        super();
        this.side = side;
        dimension = calculateDimension(backVertices);
        RawModel model = loader.loadToVAO(backVertices, backTexCoords, backIndices);
        Log.i("point", "Background: modelCreated");
        ModelTexture texture= new ModelTexture(loader.loadTexture(BACKGROUND_TEXTURE_ID));
        Log.i("point", "Background: textureCreated");
        TexturedModel texturedModel = new TexturedModel(model, texture);
        Log.i("point", "Background: texturedModelCreated");
        texturedModel.getTexture().setHasTransparency(false);
        setEntity(texturedModel, position, dimension,
                new Vector3f(0,0,0) ,new Vector3f(1,1,1));
        Log.i("point", "Background: entitySet");
        leftMargin = new SquareBoundingBox(loader, new Vector2f(0.02f, dimension.y),
                new Vector3f((position.x+0.01f), position.y, position.z), true, true);
        rightMargin = new SquareBoundingBox(loader, new Vector2f(0.02f, dimension.y),
                new Vector3f((position.x-dimension.x), position.y, position.z), true, true);
        Log.i("point", "Background: backgroundCreated");
    }

    protected Vector2f calculateDimension(float[] vertices){
        float minX = findMinX(vertices);
        float maxX = findMaxX(vertices);
        float minY = findMinY(vertices);
        float maxY = findMaxY(vertices);
        //Log.i("point", "minX: " + minX + " maxX: " + maxX + " minY: " + minY + " maxY: " + maxY);
        return new Vector2f(maxX - minX, maxY - minY);
    }

    public int getSide() {
        return side;
    }


    public void setSide(int side) {
        this.side = side;
    }

    public void changeSide(){
        if(side == Util.LEFT)
            side = Util.RIGHT;
        else
            side = Util.LEFT;
    }

    public SquareBoundingBox getLeftMargin(){
        return leftMargin;
    }

    public SquareBoundingBox getRightMargin(){
        return rightMargin;
    }

}*/
