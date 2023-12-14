package knight.arkham.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Floor extends GameObject {
    private float animationTimer;
    private final Animation<TextureRegion> animation;

    public Floor(Rectangle bounds, World world) {
        super(bounds, world, "base.png", "die.wav");

        TextureRegion region = new TextureAtlas("images/floor.atlas").findRegion("floor");

        //temporal parallax
        int regionWidth = region.getRegionWidth() / 5;

        animation = makeAnimationByRegion(region, regionWidth, region.getRegionHeight());
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 0, actualWorld, this)
        );
    }

    public void update(float deltaTime) {

        animationTimer += deltaTime;

        actualRegion = animation.getKeyFrame(animationTimer, true);
    }
}
