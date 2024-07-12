package com.crazytanks.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crazytanks.game.crazytanks;

public class mainmenu implements Screen {

    final crazytanks game1;
    private Texture backimage;
    private TextureRegion backtexture;
    OrthographicCamera cam;
    private Viewport gamePort;
    private Skin myskin;
    private Stage mystage;
    private Sound sound;
    private Music music;

    public mainmenu(final crazytanks Game){
        this.game1=Game;
        backimage = new Texture(Gdx.files.internal("NEW GAME.png"));
        backtexture = new TextureRegion(backimage, 0, 0, 2209, 1571);
        cam = new OrthographicCamera();
        gamePort = new FitViewport(crazytanks.V_WIDTH / crazytanks.PPM, crazytanks.V_HEIGHT / crazytanks.PPM, cam);
        cam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        cam.setToOrtho(false, 2209, 1571);
        sound = Gdx.audio.newSound(Gdx.files.internal("Mouse-Click-03-c-FesliyanStudios.com.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("War - BGM ( background music).mp3"));
        music.play();
        music.setVolume(0.2f);
//        myskin =new Skin(Gdx.files.internal("glassy-ui.json"));
//        mystage=new Stage(game1.screenport);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);
        cam.update();
        game1.batch.setProjectionMatrix(cam.combined);
        game1.batch.begin();
        game1.batch.draw(backtexture, 0,0, 2209, 1571);
//        game1.font.draw(game1.batch, "Welcome To Crazy Tanks!", 760, 760);
//        game1.font.draw(game1.batch, "Click Anywhere to Begin!", 1447, 950);
        game1.batch.end();

//
//        Button enterbtn= new TextButton("Enter Game",myskin,"small");
//        enterbtn.setSize(50,50);
//        enterbtn.setPosition(200,300);
//        enterbtn.addListener(new InputListener(){
//
//            @Override
//            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//                game1.setScreen(new playscreen(game1));
//                return true;
//            }
//
//            @Override
//            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//                super.touchUp(event, x, y, pointer, button);
//            }
//        });
//
//        Button settingsbtn= new TextButton("Settings",myskin,"small");
//        settingsbtn.setSize(10,10);
//        settingsbtn.setPosition(200,250);
//
//        Button exitbtn= new TextButton("EXIT",myskin,"small");
//        exitbtn.setSize(10,10);
//        exitbtn.setPosition(200,200);
//
//        mystage.addActor(enterbtn);
//        mystage.addActor(settingsbtn);
//        mystage.addActor(exitbtn);
//        Rectangle bounds = new Rectangle(500, 220, 50, 40);
//        int a=0,b=0;
//        if(Gdx.input.isTouched()){
//            a=Gdx.input.getX();
//            b=Gdx.input.getY();
//        }
//        if (bounds.contains(a,b)) {
//            System.out.println(Gdx.input.getX());
//            System.out.println(Gdx.input.getY());
//            game1.setScreen(new playscreen(game1));
//            dispose();
//        }

//        if (Gdx.input.getX()>760 && Gdx.input.getX()<1447 ){
//            if(Gdx.input.getY()>760 && Gdx.input.getY()<950){
//                System.out.println(Gdx.input.getY());
////                if(Gdx.input.isTouched()){
//            game1.setScreen(new playscreen(game1));
//            dispose();}
//        }

        if (Gdx.input.isTouched()) {
            int a=Gdx.input.getX();
            int b=Gdx.input.getY();

            if (a>636 && a<1200 && b>400 && b<535){
                sound.play(1f);
                music.stop();
                game1.setScreen(new choosetanks(game1));
                dispose();
            } else if (a>633 && a<1204 && b>821 && b<960){
                sound.play(1f);
                Gdx.app.exit();
            }

        }


//        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
//            game1.setScreen(new choosetanks(game1));
//            dispose();}
//        }
        //game1.setScreen(new playscreen(game1));



//            if (Gdx.input.isKeyPressed(13)){
//                game.setScreen(new playscreen(game));
//                dispose();
//            }

//        if (Gdx.input.isTouched()) {
//            game1.setScreen(new playscreen(game1));
//            dispose();
//        }

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
