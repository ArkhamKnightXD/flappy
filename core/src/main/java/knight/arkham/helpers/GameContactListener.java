package knight.arkham.helpers;

import com.badlogic.gdx.physics.box2d.*;
import knight.arkham.objects.GameObject;
import knight.arkham.objects.Player;
import static knight.arkham.helpers.Constants.*;

public class GameContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int collisionDefinition = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (collisionDefinition) {

            case PLAYER_BIT | PIPE_BIT:
            case PLAYER_BIT | FLOOR_BIT:

                if (fixtureA.getFilterData().categoryBits == PLAYER_BIT){
                    ((Player) fixtureA.getUserData()).hasCollide();
                    ((GameObject) fixtureB.getUserData()).hasCollideWithThePlayer();
                }

                else{
                    ((Player) fixtureB.getUserData()).hasCollide();
                    ((GameObject) fixtureA.getUserData()).hasCollideWithThePlayer();
                }
                break;
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
