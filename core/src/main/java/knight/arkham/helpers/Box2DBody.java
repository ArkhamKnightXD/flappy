package knight.arkham.helpers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DBody {
    public Rectangle bounds;
    public BodyDef.BodyType bodyType;
    public float density;
    public World world;
    public Object userData;

    public Box2DBody(Rectangle bounds, float density, World world, Object userData) {

        this.bounds = bounds;
        this.bodyType = density > 0 ? BodyDef.BodyType.DynamicBody : BodyDef.BodyType.StaticBody;
        this.density = density;
        this.world = world;
        this.userData = userData;
    }
}
