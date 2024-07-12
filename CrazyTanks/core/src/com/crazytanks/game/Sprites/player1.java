package com.crazytanks.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.crazytanks.game.Handling.PcontactListener;
import com.crazytanks.game.Screens.playscreen_2;
import com.crazytanks.game.crazytanks;

import java.util.ArrayList;

import static com.crazytanks.game.crazytanks.PPM;

public class player1 extends Sprite {
    private World world;
    public Body b2body;
    public Body bullet=null;
    public static ArrayList<Body> bulletlist = new ArrayList<Body>();

    public double Angle = (double)(30f/180f)*Math.PI;
    //public Fixture fix1;
    //private TextureRegion tankStand;



    public player1(World world, playscreen_2 screen){
//        super(screen.getAtlas().findRegion("tank1Right"));
        this.world = world;
        definePlayer1();
//        player1Bullet();
//        tankStand = new TextureRegion(getTexture(),1,361,666,375);
//        setBounds(0,0,16/PPM,16/PPM);
//        setRegion(tankStand);

    }
    public void definePlayer1(){

        BodyDef bdef = new BodyDef();
        bdef.position.set(50/ PPM,680/ PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        b2body.setUserData("player1");

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        shape.setRadius(30/ PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
        //System.out.println(b2body.getUserData());

    }
    public void player1Bullet(){
        PcontactListener.bool1=false;
        BodyDef bdef = new BodyDef();
        bdef.position.set(b2body.getPosition().x,b2body.getPosition().y+1.4f);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bullet = world.createBody(bdef);
        bullet.setUserData("bullet");

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        shape.setRadius(2/ PPM);

        fdef.shape = shape;
        bullet.createFixture(fdef);
        bullet.setLinearVelocity((float) (Math.abs(23*(Math.cos(Angle)))),25f);
        //System.out.println(Math.abs(25*(Math.cos(Angle))));
        bulletlist.add(bullet);
    }
}
