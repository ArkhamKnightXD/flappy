package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.Space;
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

        TextureRegion region = new TextureAtlas("images/birds.atlas").findRegion("yellow-bird");

        int regionWidth = region.getRegionWidth() / 3;

        flappingAnimation = makeAnimationByRegion(region, regionWidth, region.getRegionHeight());
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

        if (!isGameOver && Gdx.input.justTouched()) {

            actionSound.play();
            applyLinealImpulse(new Vector2(0, 20));
        }

        if (getPixelPosition().y > 700)
            Space.INSTANCE.isGameOver = true;
    }

    private void applyLinealImpulse(Vector2 impulseDirection) {
        body.applyLinearImpulse(impulseDirection, body.getWorldCenter(), true);
    }

    public void hasCollide(){
        isGameOver = true;
    }
}
