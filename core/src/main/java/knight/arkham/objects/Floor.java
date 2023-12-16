package knight.arkham.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

public class Floor extends GameObject {

    public Floor(Rectangle bounds, World world) {
        super(bounds, world, "base.png", "die.wav");
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 0, actualWorld, this)
        );
    }

    public void update() {

        body.setLinearVelocity(-4 , 0);

        if (getPixelPosition().x < -240) {

            Vector2 resetPosition = new Vector2(700, 40).scl(1/32f);
            body.setTransform(resetPosition, 0);
        }
    }

    private Vector2 getPixelPosition(){
        return body.getPosition().scl(PIXELS_PER_METER);
    }
}
