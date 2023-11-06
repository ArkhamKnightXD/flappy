package knight.arkham.helpers;

import com.badlogic.gdx.physics.box2d.*;
import knight.arkham.objects.Floor;
import knight.arkham.objects.Player;
import static knight.arkham.helpers.Constants.*;
import static knight.arkham.helpers.Constants.PLAYER_BIT;

public class Box2DHelper {

    public static Body createBody(Box2DBody box2DBody) {

        PolygonShape shape = new PolygonShape();

        shape.setAsBox(box2DBody.bounds.width / 2 / PIXELS_PER_METER, box2DBody.bounds.height / 2 / PIXELS_PER_METER);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        fixtureDef.density = box2DBody.density;

        if (box2DBody.userData instanceof Player)
            fixtureDef.filter.categoryBits = PLAYER_BIT;

        else if (box2DBody.userData instanceof Floor) {

            //Since I only define kinematic and dynamic and my box2DBody, I have to define static body here.
            fixtureDef.filter.categoryBits = FLOOR_BIT;
            box2DBody.bodyType = BodyDef.BodyType.StaticBody;
        }

        else
            fixtureDef.filter.categoryBits = PIPE_BIT;

        Body body = createBox2DBodyByType(box2DBody);

        body.createFixture(fixtureDef).setUserData(box2DBody.userData);

        shape.dispose();

        return body;
    }

    private static Body createBox2DBodyByType(Box2DBody box2DBody) {

        BodyDef bodyDef = new BodyDef();

        bodyDef.type = box2DBody.bodyType;

        bodyDef.position.set(box2DBody.bounds.x / PIXELS_PER_METER, box2DBody.bounds.y / PIXELS_PER_METER);

        bodyDef.fixedRotation = true;

        return box2DBody.world.createBody(bodyDef);
    }
}
