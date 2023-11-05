package knight.arkham.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Pipe extends GameObject {
    public static int score;

    public Pipe(Rectangle bounds, boolean isRotated, World world) {
        super(
            bounds,
            world, isRotated ? "pipe-green-180.png" :"pipe-green.png",
            "laser.wav"
        );

        score = 0;
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 0, actualWorld, this)
        );
    }

    public void update() {

        body.setLinearVelocity(-4 , 0);
    }
}
