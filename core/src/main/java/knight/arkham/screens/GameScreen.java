package knight.arkham.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.Space;
import knight.arkham.helpers.GameContactListener;
import knight.arkham.objects.*;

public class GameScreen extends ScreenAdapter {
    private final Space game;
    private final OrthographicCamera camera;
    public SpriteBatch batch;
    private final World world;
    private final Box2DDebugRenderer debugRenderer;
    private final Player player;
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

        player = new Player(new Rectangle(game.screenWidth/2f, game.screenHeight/2f , 32, 32), world);
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    private void update() {

        player.update();
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
    }

    private void draw() {

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

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
