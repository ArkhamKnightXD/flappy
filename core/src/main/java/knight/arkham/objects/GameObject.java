package knight.arkham.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import knight.arkham.helpers.AssetsHelper;
import static knight.arkham.helpers.Constants.PIXELS_PER_METER;

public abstract class GameObject {
    protected final Rectangle actualBounds;
    protected final World actualWorld;
    protected TextureRegion actualRegion;
    protected final Sound actionSound;
    protected final Body body;

    protected GameObject(Rectangle bounds, World world, String spritePath, String soundPath) {
        actualBounds = bounds;
        actualWorld = world;
        actualRegion = new TextureRegion(new Texture("images/" + spritePath));
        actionSound = AssetsHelper.loadSound(soundPath);

        body = createBody();
    }

    protected abstract Body createBody();

    private Rectangle getDrawBounds() {

        return new Rectangle(
            body.getPosition().x - (actualBounds.width / 2 / PIXELS_PER_METER),
            body.getPosition().y - (actualBounds.height / 2 / PIXELS_PER_METER),
            actualBounds.width / PIXELS_PER_METER,
            actualBounds.height / PIXELS_PER_METER
        );
    }

    public void draw(Batch batch) {

        Rectangle drawBounds = getDrawBounds();

        batch.draw(actualRegion, drawBounds.x, drawBounds.y, drawBounds.width, drawBounds.height);
    }

    public void hasCollideWithThePlayer(){
        actionSound.play();
    }

    public void dispose() {
        actualRegion.getTexture().dispose();
        actionSound.dispose();
    }
}
