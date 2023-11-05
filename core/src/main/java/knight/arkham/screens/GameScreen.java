package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.Space;
import knight.arkham.helpers.GameContactListener;
import knight.arkham.objects.*;

import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

public class GameScreen extends ScreenAdapter {
    private final Space game;
    private final OrthographicCamera camera;
    public SpriteBatch batch;
    private final World world;
    private final Box2DDebugRenderer debugRenderer;
    private final Texture background;
    private final Texture floor;
    private final Player player;
    private final Array<Pipe> pipes;
    private float accumulator;
    private final float TIME_STEP;

    public GameScreen() {

        game = Space.INSTANCE;

        camera = game.camera;

        TIME_STEP = 1/240f;

        batch = new SpriteBatch();

        world = new World(new Vector2(0, -20), true);

        world.setContactListener(new GameContactListener());

        debugRenderer = new Box2DDebugRenderer();

        player = new Player(new Vector2(game.screenWidth/2f, game.screenHeight/2f), world);
        background = new Texture("images/background-day.png");
        floor = new Texture("images/base.png");
        pipes = new Array<>();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    private void update() {

        player.update();

        for (Pipe pipe : pipes)
            pipe.update();
    }

    private void doPhysicsTimeStep(float deltaTime) {

        float frameTime = Math.min(deltaTime, 0.25f);

        accumulator += frameTime;

        while(accumulator >= TIME_STEP) {
            world.step(TIME_STEP, 6,2);
            accumulator -= TIME_STEP;
        }
    }

    @Override
    public void render(float deltaTime) {

        ScreenUtils.clear(0, 0, 0, 0);

        update();
        draw();

        doPhysicsTimeStep(deltaTime);

        if (Gdx.input.isKeyJustPressed(Input.Keys.A))
            generatePipes();
    }

    private void generatePipes() {

        float upPipeHeight = MathUtils.random(256, game.screenHeight - 32);
//        float downPipeHeight = MathUtils.random(80, upPipeHeight - 32);

        Pipe upPipe = new Pipe(new Rectangle(game.screenWidth, game.screenHeight,64, upPipeHeight), true, world);
        Pipe downPipe = new Pipe(new Rectangle(game.screenWidth, 80, 64, upPipeHeight), false, world);

        pipes.add(upPipe, downPipe);
    }

    private void draw() {

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(
            background, 1 / PIXELS_PER_METER, 1 / PIXELS_PER_METER,
            game.screenWidth / PIXELS_PER_METER,
            game.screenHeight / PIXELS_PER_METER
        );

        for (Pipe pipe : pipes)
            pipe.draw(batch);

        batch.draw(
            floor, 1 / PIXELS_PER_METER, 1 / PIXELS_PER_METER,
            game.screenWidth / PIXELS_PER_METER,
            80 / PIXELS_PER_METER
        );

        player.draw(batch);

        batch.end();

        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void hide() {

        dispose();
    }

    @Override
    public void dispose() {

        player.dispose();

        world.dispose();
        batch.dispose();
        debugRenderer.dispose();
    }
}
