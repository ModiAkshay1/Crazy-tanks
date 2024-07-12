package com.crazytanks.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crazytanks.game.crazytanks;

public class home_page implements Screen {

        final crazytanks game;
        private Texture backimage;
        private TextureRegion backtexture;
        OrthographicCamera cam;
        private Viewport gamePort;
        private Music music;
        public home_page(final crazytanks Game){
            this.game=Game;
            backimage = new Texture(Gdx.files.internal("homeimage.jpg"));
            backtexture = new TextureRegion(backimage, 0, 0, 600, 220);
            cam = new OrthographicCamera();
            gamePort = new FitViewport(crazytanks.V_WIDTH / crazytanks.PPM, crazytanks.V_HEIGHT / crazytanks.PPM, cam);
            cam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
            cam.setToOrtho(false, 800, 480);
            music = Gdx.audio.newMusic(Gdx.files.internal("War - BGM ( background music).mp3"));
            music.play();
            music.setVolume(1f);

        }
        @Override
        public void show() {

        }

        @Override
        public void render(float delta) {

            ScreenUtils.clear(0, 0, 0, 0);
            cam.update();
            game.batch.setProjectionMatrix(cam.combined);
            game.batch.begin();
            game.batch.draw(backtexture, 0,0, 800, 480);
            game.font.draw(game.batch, "Welcome To Crazy Tanks!", 350, 130);
            game.font.draw(game.batch, "Click Anywhere to Begin!", 350, 70);
            game.batch.end();



//            if (Gdx.input.isKeyPressed(13)){
//                game.setScreen(new playscreen(game));
//                dispose();
//            }
            if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                music.stop();
                game.setScreen(new mainmenu(game));

                dispose();
            }

        }

        @Override
        public void resize(int width, int height) {

        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void dispose() {
            music.dispose();

        }
    }
