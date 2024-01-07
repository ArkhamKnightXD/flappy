package knight.arkham.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameDataHelper {
    private static final String dataFilename = "flappy";

    public static void saveHighScore(int score){

        Preferences preferences = Gdx.app.getPreferences(dataFilename);

        if (score < loadHighScore())
            return;

        preferences.putInteger("playerScore", score);

        preferences.flush();
    }

    public static int loadHighScore(){

        Preferences preferences = Gdx.app.getPreferences(dataFilename);

        return preferences.getInteger("playerScore");
    }
}
