package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.Box2DBody;
import knight.arkham.helpers.Box2DHelper;
import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

public class Player extends GameObject {
    public static int score;
    private float angle;

    public Player(Rectangle bounds, World world) {
        super(bounds, world, "player-ship.png", "laser.wav");
        score = 0;
    }

    @Override
    protected Body createBody() {
        return Box2DHelper.createBody(
            new Box2DBody(actualBounds, 5, actualWorld, this)
        );
    }

    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.W))
            applyLinealImpulse(new Vector2(angle, 1));

        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {

            angle-= 0.01f;

            body.setTransform(body.getPosition(), angle);
        }

        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            angle+= 0.01f;

            body.setTransform(body.getPosition(), angle);
        }

        manageScreenWrapping();
    }

    private void applyLinealImpulse(Vector2 impulseDirection) {
        body.applyLinearImpulse(impulseDirection, body.getWorldCenter(), true);
    }
    private void manageScreenWrapping() {

        if (getPixelPosition().x > 960)
            body.setTransform(1 / PIXELS_PER_METER, body.getPosition().y, 0);

        else if (getPixelPosition().x < 0)
            body.setTransform(960 / PIXELS_PER_METER, body.getPosition().y, 0);

        else if (getPixelPosition().y > 544)
            body.setTransform(body.getPosition().x, 1 / PIXELS_PER_METER, 0);

        else if (getPixelPosition().y < 0)
            body.setTransform(body.getPosition().x, 544 / PIXELS_PER_METER, 0);
    }
    public Vector2 getPixelPosition() {
        return new Vector2(
            body.getPosition().x * PIXELS_PER_METER,
            body.getPosition().y * PIXELS_PER_METER
        );
    }
}
