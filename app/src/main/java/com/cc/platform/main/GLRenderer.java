package com.cc.platform.main;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.cc.platform.builder.ButtonBuilder;
import com.cc.platform.builder.EntityCreationDirector;
import com.cc.platform.builder.PlayerBuilder;
import com.cc.platform.builder.TileMapBuilder;
import com.cc.platform.camera.Camera;
import com.cc.platform.entities.Button;
import com.cc.platform.entities.Player;
import com.cc.platform.physicsEngine.PhysicModel;
import com.cc.platform.physicsEngine.Physical;
import com.cc.platform.physicsEngine.Physics;
import com.cc.platform.renderEngine.Renderable;
import com.cc.platform.renderEngine.Renderer;
import com.cc.platform.terrains.TileMap;
import com.cc.platform.text.Font;
import com.cc.platform.text.Text;
import com.cc.platform.text.TextRenderer;
import com.cc.platform.time.Time;
import com.cc.platform.toolBox.Util;
import com.cc.platform.toolBox.Vector2f;
import com.cc.platform.toolBox.Vector3f;
import com.cc.platform.toolBox.Vector4f;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Claudio on 28/05/2016.
 */

public class GLRenderer implements GLSurfaceView.Renderer {

    private Renderer renderer;
    private TextRenderer textRenderer;

    private final Physics physics;

    private int width;
    private int height;

    private final List<Renderable> renderables = new ArrayList<>();
    private final List<Physical> physical = new ArrayList<>();
    private final List<Button> buttons = new ArrayList<>();
    private TileMap tileMap;
    private Player player;

    private Camera camera;

//    private Server server;

    private final Context context;

    List<Text> texts = new ArrayList<>();
    Text forceInfo;
    Text accelerationInfo;
    Text velocityInfo;
    Text positionInfo;
    Text collisionText;


    public GLRenderer(Context context, int width, int height) {
        this.context = context;
        this.width = width;
        this.height = height;
        physics = new Physics();


//        Data.setDataDimension(16);
    }

    public List<Button> getButtons() {
        return buttons;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//        server = new Server();
//        new Thread(server).start();

        Log.i("point", "Creating tileMap");
        tileMap = (TileMap) new TileMapBuilder(context)
                .createEntity()
                .createTileset()
                .createTileLevels()
                .createShader()
                .bindBuffers()
                .getEntity();
        renderables.add(tileMap);
        Util.checkError();
        Log.i("point", "tileMap created");

        Log.i("point", "Creating player");
        EntityCreationDirector director = new EntityCreationDirector();
        director.setEntityBuilder(new PlayerBuilder(context));
        director.createEntity();
        player = (Player) director.getEntity();
        player.setPhysicModel(new PhysicModel(player.getPosition(), new Vector2f(0.8f, 0.5f)));
        player.start();
        renderables.add(director.getEntity());
        physical.add((Physical) director.getEntity());
        Util.checkError();
        Log.i("point", "player Created");

        Log.i("point", "Creating buttons");
        director.setEntityBuilder(new ButtonBuilder(Util.BUTTON_LEFT, context));
        director.createEntity();
        renderables.add(director.getEntity());
        buttons.add((Button) director.getEntity());
        Log.i("point", "button 1 created");
        director.setEntityBuilder(new ButtonBuilder(Util.BUTTON_RIGHT, context));
        director.createEntity();
        renderables.add(director.getEntity());
        buttons.add((Button) director.getEntity());
        Log.i("point", "button 2 created");
        director.setEntityBuilder(new ButtonBuilder(Util.BUTTON_UP, context));
        director.createEntity();
        renderables.add(director.getEntity());
        buttons.add((Button) director.getEntity());
        Log.i("point", "button 3 created");
        Log.i("point", "buttons created");

        Font roboto = new Font(context.getAssets(), "Roboto-Regular.ttf", 50, new Vector2f(2, 2));

        forceInfo = new Text("", new Vector2f(50, 50), new Vector4f(1, 1, 1, 1), roboto);
        accelerationInfo = new Text("", new Vector2f(50, 100), new Vector4f(1, 1, 1, 1), roboto);
        velocityInfo = new Text("", new Vector2f(50, 150), new Vector4f(1, 1, 1, 1), roboto);
        positionInfo = new Text("", new Vector2f(50, 200), new Vector4f(1, 1, 1, 1), roboto);
        collisionText = new Text("", new Vector2f(50, 250), new Vector4f(1, 1, 1, 1), roboto);
        //Text text2 = new Text("Hello World", new Vector2f(800,600), new Vector4f(0,1,0,0.2f), roboto);

        texts.add(forceInfo);
        texts.add(accelerationInfo);
        texts.add(velocityInfo);
        texts.add(positionInfo);
        texts.add(collisionText);
        //texts.add(text2);

        GLES20.glClearColor(1.0f, 0.5f, 0.5f, 1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, -1), new Vector3f(0, 1, 0));//forceInfo, look, up

        renderer = new Renderer(width, height);
        textRenderer = new TextRenderer(context);

        Time.start();
        Log.i("point", "GLRenderer: surfaceCreated");
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        this.height = height;
        this.width = width;
        renderer.setProjectionMatrix(width, height);
        textRenderer.setProjectionMatrix(width, height);
        Log.i("point", "GLRenderer: surfaceChanged");
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Time.update();

        Vector2f playerPosition = player.getPosition();
        PhysicModel model = player.getPhysicModel();
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        forceInfo.setText("TSx: " + df.format(model.getTargetSpeed().x) + " TSy: " + df.format(model.getTargetSpeed().y));
        accelerationInfo.setText("Ax: " + df.format(model.getAcceleration().x) + " Ay: " + df.format(model.getAcceleration().y));
        velocityInfo.setText("Vx: " + df.format(model.getCurrentSpeed().x) + " Vy: " + df.format(model.getCurrentSpeed().y));
        positionInfo.setText("Px: " + df.format(model.getPosition().x) + " Py: " + df.format(model.getPosition().y));
        camera.move(new Vector3f(playerPosition.x - (float) width / 2, playerPosition.y - (float) height / 2, 0));

        for (Renderable renderable : renderables) {
            renderable.handleInput();
        }
        collisionText.setText(Boolean.toString(physics.update(physical, tileMap)));
        renderer.render(renderables, camera);

        textRenderer.begin();
        textRenderer.render(texts, camera);
        textRenderer.end();

//        Data.setData(ByteBuffer.allocate(20).putFloat(playerPosition.x).putFloat(playerPosition.y)
//                .putFloat(player.SPEED).putFloat(player.JUMP_SPEED).putInt(Time.getFps()).array());

    }
}
