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
import com.crazytanks.game.Screens.playscreen_2;
import com.crazytanks.game.crazytanks;

public class choosetanks implements Screen {

    final crazytanks game2;
    private Texture backimage;
    private TextureRegion backtexture;
    OrthographicCamera cam;
    private Viewport gamePort;
    private Skin myskin;
    private Stage mystage;
    private Texture texture1;
    private int tank1=0,tank2=0;
    private Sound sound;
    private Music music;
    public choosetanks(final crazytanks Game){
        this.game2=Game;
        backimage = new Texture(Gdx.files.internal("showtanks.jpeg"));
        backtexture = new TextureRegion(backimage, 0, 0, 1133, 608);
        cam = new OrthographicCamera();
        gamePort = new FitViewport(crazytanks.V_WIDTH / crazytanks.PPM, crazytanks.V_HEIGHT / crazytanks.PPM, cam);
        cam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        cam.setToOrtho(false, 1133, 608);
        texture1 = new Texture("tank1Right.jpg");
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
        game2.batch.setProjectionMatrix(cam.combined);
        game2.batch.begin();
        game2.batch.draw(backtexture, 0,0, 1133, 608);
        //game2.batch.draw(texture1,100,100);
//        game1.font.draw(game1.batch, "Welcome To Crazy Tanks!", 760, 760);
//        game1.font.draw(game1.batch, "Click Anywhere to Begin!", 1447, 950);
        game2.batch.end();

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

        if (Gdx.input.justTouched()){
            sound.play(1.0f);
            int a=Gdx.input.getX();
            int b=Gdx.input.getY();
//            System.out.println(a);
//            System.out.println(b);
            if(a>40 && a<221 && b>35 && b<99){
                game2.setScreen(new mainmenu(game2));
            }
            if(a>36 && a<461 && b>130 && b<424){
                tank1=1;
            } else if (a>483 && a<915 && b>131 && b<424) {
                tank1=2;
            } else if (a>40 && a<462 && b>453 && b<760) {
                tank1=3;
            } else if (a>486 && a<907 && b>457 && b<753) {
                tank1=4;
            } else if (a>974 && a<1400 && b>133 && b<424) {
                tank2=1;
            } else if (a>1426 && a<1820 && b>136 && b<417) {
                tank2=2;
            } else if (a>975 && a<1399 && b>456 && b<755) {
                tank2=3;
            } else if (a>1430 && a<1817 && b>451 && b<756) {
                tank2=4;
            }
//            System.out.println(tank1);
//            System.out.println(tank2);
            if(a>716 && a<1195 && b>826 && b<975){
                if(tank1!=0 && tank2!=0){
                    music.stop();
                    game2.setScreen(new playscreen_2(game2,tank1,tank2));
                }
            }
        }



//        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
//            game2.setScreen(new playscreen_2(game2));
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
