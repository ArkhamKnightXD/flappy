package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Player extends GameObject {
    public static int score;
    private boolean isGameOver;
    private float animationTimer;
    private final Animation<TextureRegion> flappingAnimation;


    public Player(Vector2 position, World world) {
        super(
            new Rectangle(position.x, position.y, 50, 40),
            world, "yellowbird-midflap.png", "wing.wav"
        );

        score = 0;

        TextureAtlas atlas = new TextureAtlas("images/birds.atlas");

        TextureRegion region = atlas.findRegion("yellow-bird");

        int regionWidth = region.getRegionWidth() / 3;

        flappingAnimation = makeAnimationByRegion(region, regionWidth, region.getRegionHeight());
    }

    private Animation<TextureRegion> makeAnimationByRegion(TextureRegion region, int regionWidth, int regionHeight) {

        Array<TextureRegion> animationFrames = new Array<>();

        for (int i = 0; i < 3; i++)
            animationFrames.add(new TextureRegion(region, i * regionWidth, 0, regionWidth, regionHeight));

        return new Animation<>(0.1f, animationFrames);
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 1, actualWorld, this)
        );
    }

    public void update(float deltaTime) {

        animationTimer += deltaTime;

        actualRegion = flappingAnimation.getKeyFrame(animationTimer, true);

        if (!isGameOver && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

            actionSound.play();
            applyLinealImpulse(new Vector2(0, 20));
        }
    }

    private void applyLinealImpulse(Vector2 impulseDirection) {
        body.applyLinearImpulse(impulseDirection, body.getWorldCenter(), true);
    }

    public void hasCollide(){
        isGameOver = true;
    }
}
