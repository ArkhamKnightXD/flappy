package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;

public class Player extends GameObject {
    public static int score;

    public Player(Vector2 position, World world) {
        super(
            new Rectangle(position.x, position.y, 50, 40),
            world, "yellowbird-midflap.png", "laser.wav"
        );

        score = 0;
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 1, actualWorld, this)
        );
    }

    public void update() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            applyLinealImpulse(new Vector2(0, 20));
    }

    private void applyLinealImpulse(Vector2 impulseDirection) {
        body.applyLinearImpulse(impulseDirection, body.getWorldCenter(), true);
    }
}
