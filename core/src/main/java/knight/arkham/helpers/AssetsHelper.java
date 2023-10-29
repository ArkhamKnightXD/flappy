package knight.arkham.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import knight.arkham.Space;

public class AssetsHelper {

    public static Sound loadSound(String filename){
        return Gdx.audio.newSound(Gdx.files.internal("sounds/"+ filename));
    }

    public static Music loadMusic(String filename){
        return Gdx.audio.newMusic(Gdx.files.internal("music/"+ filename));
    }

    public static Skin loadUiSkin() {

        AssetManager assetManager = new AssetManager();

        assetManager.load(Space.INSTANCE.uiSkin);

        assetManager.finishLoading();

        return assetManager.get(Space.INSTANCE.uiSkin);
    }
}
