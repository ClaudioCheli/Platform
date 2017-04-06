package com.example.claudio.platform.manager;


/**
 * Created by Claudio on 31/05/2016.
 */
/*public class ButtonsManager implements Manager{

    private static List<Button> buttons = new ArrayList<>();

    private float[] buttonVertices = {
            -0.2f, 0,    0,
            -0.2f, 0.4f, 0,
            0,    0.4f, 0,
            0,    0,    0
    };

    private int[] indices = {
            0,1,3,
            3,1,2
    };

    private float[] textureCoords = {
            0,0,
            0,1,
            1,1,
            1,0
    };

    private boolean state = false;
    private int direction;

    public ButtonsManager(Loader loader){
        createButton(loader, buttonVertices, textureCoords, new Vector3f(-0.8f, -1f, -7f), indices, R.raw.left_arrow, Input.LEFT);
        createButton(loader, buttonVertices, textureCoords, new Vector3f(-0.5f, -1f, -7f), indices, R.raw.right_arrow, Input.RIGHT);
        createButton(loader, buttonVertices, textureCoords, new Vector3f(1f, -1f, -7f), indices, R.raw.jump_button, Input.JUMP);
    }

    private void createButton(Loader loader, float[] vertices, float[] textureCoords,
                              Vector3f position, int[] indices, int textureID, int type){
        Button button = new Button(loader, position, type, vertices, textureCoords,
                indices, textureID);
        buttons.add(button);
    }

    @Override
    public void update() {
        if(state)
            move();

        state = false;
    }

    private void move(){
        switch (direction){
            case PlayerAnimation.DX:
                for (Button b:buttons)
                    b.increasePosition(0.01f, 0f, 0f);
                break;
            case PlayerAnimation.SX:
                for(Button b:buttons)
                    b.increasePosition(-0.01f, 0f, 0f);
                break;
        }
    }

    public static List<Button> getButtons(){return buttons;}

    @Override
    public void addInEntityList(List<Entity> entities) {

    }

    public void setState(int direction){
        state = true;
        this.direction = direction;
    }
}*/
