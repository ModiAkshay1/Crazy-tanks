package com.crazytanks.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crazytanks.game.Handling.PcontactListener;
import com.crazytanks.game.Sprites.player1;
import com.crazytanks.game.Sprites.player2;
import com.crazytanks.game.crazytanks;
import com.crazytanks.game.utils.hud;
import com.crazytanks.game.utils.tiledobject;
import java.lang.Math;

public class playscreen_2 implements Screen {
    private crazytanks game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Stage stage;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    public World world;
    private Box2DDebugRenderer b2dr;
    private hud hud1;

    public player1 getPlayerone() {
        return playerone;
    }

    public void setPlayerone(player1 playerone) {
        this.playerone = playerone;
    }

    public player2 getPlayertwo() {
        return playertwo;
    }

    public void setPlayertwo(player2 playertwo) {
        this.playertwo = playertwo;
    }

    private player1 playerone;
    private player2 playertwo;

    private Texture texture1;

    private Texture texture2;
    private Texture bullet1;
    private Texture bullet2;
    private int tank1,tank2;

    private Sound missilesound;
    private Sound anglesound;
    private Sound tankmove;

    private Music music;

    public int getHealth1() {
        return health1;
    }

    public void setHealth1(int health1) {
        this.health1 = health1;
    }

    public int getHealth2() {
        return health2;
    }

    public void setHealth2(int health2) {
        this.health2 = health2;
    }

    private int health1=100,health2=100;

    public boolean switchtank = true;

    //private TextureAtlas atlas;
    //private Body player2;

    public playscreen_2(crazytanks game,int t1,int t2){
        this.game = game;
        this.tank1=t1;
        this.tank2=t2;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(crazytanks.V_WIDTH / crazytanks.PPM, crazytanks.V_HEIGHT / crazytanks.PPM, gamecam);
        maploader = new TmxMapLoader();
        map = maploader.load("ct_2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1  / crazytanks.PPM);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        world = new World(new Vector2(0, -40), true);
        b2dr = new Box2DDebugRenderer();
        playerone = new player1(this.world,this);
        playertwo = new player2(this.world,this);
        world.setContactListener(new PcontactListener(this));
        missilesound= Gdx.audio.newSound(Gdx.files.internal("zapsplat_warfare_missile_bomb_incoming_whoosh_into_large_explosion_005_62653.mp3"));
        anglesound= Gdx.audio.newSound(Gdx.files.internal("Mouse-Click-03-c-FesliyanStudios.com.mp3"));
        tankmove= Gdx.audio.newSound(Gdx.files.internal("soundsnap_386503_police_helicopter_3_-[AudioTrimmer.com].mp3"));
        stage = new Stage(new ScreenViewport());
        hud1 = new hud(game.batch);
//        music = crazytanks.manager.get("audio/music/mario_music.ogg", Music.class);
//        music.setLooping(true);
//        music.setVolume(0.3f);


        //System.out.println(map.getLayers().get("hills").getObjects().getCount());
        tiledobject.parseTiledObjectLayer(world,map.getLayers().get("hills").getObjects());
        if(tank1==1) {
            texture1 = new Texture("tank1Right.jpg");
            bullet1 = new Texture("bullet1.png");
        } else if (tank1==2) {
            texture1 = new Texture("tank2Right.png");
            bullet1 = new Texture("bullet2.png");
        } else if (tank1==3) {
            texture1 = new Texture("tank3Right.png");
            bullet1 = new Texture("bullet3.png");
        } else if (tank1==4) {
            texture1 = new Texture("tank4Right.png");
            bullet1 = new Texture("bullet4.png");
        }if(tank2==1) {
            texture2 = new Texture("tank1Left.png");
            bullet2 = new Texture("bullet1left.png");
        } else if (tank2==2) {
            texture2 = new Texture("tank2Left.png");
            bullet2 = new Texture("bullet2.png");
        } else if (tank2==3) {
            texture2 = new Texture("tank3Left.png");
            bullet2 = new Texture("bullet3left.png");
        } else if (tank2==4) {
            texture2 = new Texture("tank4Left.png");
            bullet2 = new Texture("bullet4left.png");
        }

        //player1 = createPlayer();
        //atlas = new TextureAtlas("tanksright.pack");


//        BodyDef bdef = new BodyDef();
//        PolygonShape shape = new PolygonShape();
//        FixtureDef fdef = new FixtureDef();
//        Body body;
//
//        for(MapObject object :map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);
//            body = world.createBody(bdef);
//            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
//            fdef.shape = shape;
//            body.createFixture(fdef);
//
//
//        }



    }
//    public TextureAtlas getAtlas(){
//        System.out.println(atlas);
//       return atlas;

    //}
    public void handleInput(float dt){
        playerone.b2body.setAngularDamping(150f);
        playerone.b2body.setLinearDamping(300f);
        playertwo.b2body2.setAngularDamping(150f);
        playertwo.b2body2.setLinearDamping(300f);

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new ingame_menu(game));
            //dispose();
        }

        if (Gdx.input.justTouched()){
        int a=Gdx.input.getX();
        int b=Gdx.input.getY();
//            System.out.println(a);
//            System.out.println(b);
        if((a>112 && a<166 && b>856 && b<904)){
                playerone.b2body.applyLinearImpulse(new Vector2(-17f, 0), playerone.b2body.getWorldCenter(), true);
            tankmove.play(1f);
            }
        if(a>167 && a<222 && b>856 && b<904){
            playerone.b2body.applyLinearImpulse(new Vector2(17f, 0), playerone.b2body.getWorldCenter(), true);
            tankmove.play(1f);
        }
        if((a>1690 && a<1750 && b>856 && b<904)){
            playertwo.b2body2.applyLinearImpulse(new Vector2(-17f, 0), playertwo.b2body2.getWorldCenter(), true);
            tankmove.play(1f);
            }
        if((a>1750 && a<1809 && b>856 && b<904)){
            playertwo.b2body2.applyLinearImpulse(new Vector2(17f, 0), playertwo.b2body2.getWorldCenter(), true);
            tankmove.play(1f);
            }
        if(a>287 && a<478 && b>855 && b<916){
                //playerone.b2body.applyLinearImpulse(new Vector2(17f, 0), playerone.b2body.getWorldCenter(), true);
                missilesound.play(1f);
                playerone.player1Bullet();

            }
        if(a>1443 && a<1626 && b>855 && b<916){
                //playerone.b2body.applyLinearImpulse(new Vector2(17f, 0), playerone.b2body.getWorldCenter(), true);
                missilesound.play(1f);
                playertwo.player2Bullet();

            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            //playerone.b2body.applyLinearImpulse(new Vector2(0,0.4f),playerone.b2body.getWorldCenter(),true);
            anglesound.play(1f);
            playerone.Angle += (double)(3f/180f)*Math.PI;
            //System.out.println(playerone.Angle);


        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
//            playerone.b2body.applyLinearImpulse(new Vector2(0,-0.4f),playerone.b2body.getWorldCenter(),true);
            anglesound.play(1f);
            playerone.Angle -= (double)(3f/180f)*Math.PI;
            //System.out.println((double)(2f/180f)*Math.PI);
            //System.out.println(playerone.Angle);
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.D)) && playerone.b2body.getLinearVelocity().x <= 2){
            tankmove.play(1f);
            playerone.b2body.applyLinearImpulse(new Vector2(7f, 0), playerone.b2body.getWorldCenter(), true);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && playerone.b2body.getLinearVelocity().x >= -2){
            tankmove.play(1f);
            playerone.b2body.applyLinearImpulse(new Vector2(-7f, 0), playerone.b2body.getWorldCenter(), true);}
//        if(switchtank == false && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
//            missilesound.play(1f);
//        //if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
//            missilesound.play(1f);
//            playertwo.player2Bullet();
//        //}
//        }





//        if(Gdx.input.justTouched()||Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
//            game.setScreen(new ingame_menu(game));
//            dispose();
//        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
//            playertwo.b2body2.applyLinearImpulse(new Vector2(0,0.4f),playertwo.b2body2.getWorldCenter(),true);
            anglesound.play(1f);
            playertwo.Angle2 += (double)(3f/180f)*Math.PI;

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
//            playertwo.b2body2.applyLinearImpulse(new Vector2(0,-5f),playertwo.b2body2.getWorldCenter(),true);
            anglesound.play(1f);
            playertwo.Angle2 -= (double)(3f/180f)*Math.PI;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && playertwo.b2body2.getLinearVelocity().x <= 2){
            tankmove.play(1f);
            playertwo.b2body2.applyLinearImpulse(new Vector2(7f, 0), playertwo.b2body2.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && playertwo.b2body2.getLinearVelocity().x >= -2){
            tankmove.play(1f);
            playertwo.b2body2.applyLinearImpulse(new Vector2(-7f, 0), playertwo.b2body2.getWorldCenter(), true);}
//        if(switchtank == true&& Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
//                missilesound.play(1f);
//                playertwo.player2Bullet();
//                switchtank = false;
//            }


    }




    public void applyfriction(){
        if(playerone.bullet!=null) {
            playerone.bullet.setLinearDamping(100f);
            if(Math.abs(playerone.bullet.getPosition().x - playertwo.b2body2.getPosition().x) < 1){
                if(Math.abs(playerone.bullet.getPosition().y - playertwo.b2body2.getPosition().y) < 1){

                    decr_health2();
                    System.out.println("he   "+this.health2);
                }
            }
            //world.destroyBody(playerone.bullet);

        }
    }

    public void update(float dt){
        handleInput(dt);
        world.step(1/60f,6,2);
        //gamecam.position.x = playerone.b2body.getPosition().x;
        gamecam.update();
        renderer.setView(gamecam);
        hud1.update(dt);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        b2dr.render(world, gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.font.draw(game.batch, "Welcome To Crazy Tanks!", 350, 130);
        game.font.draw(game.batch, "Click Anywhere to Begin!", 350, 70);
        game.batch.end();

        game.batch.begin();

        //game.batch.draw(texture1,100,100);
//        game.batch.end();
//
//        game.batch.begin();
        //System.out.println(texture1);
        //TextureRegion textba= new TextureRegion(texture1,100,100);
        game.batch.draw(texture1, (float) (playerone.b2body.getPosition().x-0.7), (float) (playerone.b2body.getPosition().y-0.8),2,1.6f);
        game.batch.draw(texture2, (float) (playertwo.b2body2.getPosition().x-0.7), (float) (playertwo.b2body2.getPosition().y-0.8),2,1.6f);
        if(playerone.bullet!=null){

        game.batch.draw(bullet1, (float) (playerone.bullet.getPosition().x), (float) (playerone.bullet.getPosition().y-0.23),0.8f,0.8f);}
        if(playertwo.bullet2!=null){
        game.batch.draw(bullet2, (float) (playertwo.bullet2.getPosition().x), (float) (playertwo.bullet2.getPosition().y-0.23),0.8f,0.8f);}

        //System.out.println(playerone.b2body.getPosition().x);
        //System.out.println(texture1.getHeight());
        game.batch.end();
        game.batch.setProjectionMatrix(hud1.stage.getCamera().combined);
        hud1.stage.draw();
        //d1();
        //game.batch.setProjectionMatrix(gamecam.combined);




    }
//    public void  d1(){
//        if(playerone.bullet!=null){
//        if(Math.abs(playerone.bullet.getPosition().x - playertwo.b2body2.getPosition().x) < 1){
//            if(Math.abs(playerone.bullet.getPosition().y - playertwo.b2body2.getPosition().y) < 1){
//                world.destroyBody(playerone.bullet);
//                decr_health2();
//                System.out.println(this.health2);
//            }}
//        }
//    }

    public void decr_health1(){
        this.health1-=10;
        if(this.health1 == 0){
            System.out.println("PLAYER 2 WON");
            Gdx.app.exit();
        }
    }
    public void decr_health2(){
        this.health2-=10;
        if(this.health2 == 0){
            System.out.println("PLAYER 1 WON");
            Gdx.app.exit();
        }
    }
//    public void cameraUpdate(float delta){
//        Vector3 position = camera
//
//
//    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();

        b2dr.dispose();
        hud1.dispose();


    }
    public hud getHud(){ return hud1; }
}
//    public Body createPlayer(){
//        Body pbody;
//        BodyDef def = new BodyDef();
//        def.type = BodyDef.BodyType.DynamicBody;
//        def.position.set(0,5F);
//        def.fixedRotation = true;
//        pbody = world.createBody(def);
//
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(0.5F, 0.5F);
//        pbody.createFixture(shape,1.0f);
//        shape.dispose();
//
//        return pbody;
//
//    }


