package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import knight.arkham.Space;
import knight.arkham.helpers.AssetsHelper;

public class MainMenuScreen extends ScreenAdapter {
    private final Space game;
    private final Skin skin;
    private final Stage stage;
    private final Viewport viewport;
    private final Music music;

    public MainMenuScreen() {

        game = Space.INSTANCE;

        skin = AssetsHelper.loadUiSkin();

        viewport = new ExtendViewport(game.screenWidth, game.screenHeight);

        stage = new Stage(viewport);

        Table table = new Table();

        table.setFillParent(true);

        stage.addActor(table);

        addButton(table,"Play").addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen());
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        addButton(table, "Quit").addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        Gdx.input.setInputProcessor(stage);

        music = AssetsHelper.loadMusic("pixel3.mp3");

        music.play();
        music.setVolume(0.2f);
        music.setLooping(true);
    }

    private TextButton addButton(Table table, String buttonName) {

        TextButton textButton = new TextButton(buttonName, skin);

        table.add(textButton).width(400).height(60).padBottom(15);
        table.row();

        return textButton;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);

        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {

        stage.dispose();
        skin.dispose();
        music.dispose();
    }
}
