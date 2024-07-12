package com.crazytanks.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.crazytanks.game.Handling.PcontactListener;
import com.crazytanks.game.Screens.playscreen_2;
import com.crazytanks.game.crazytanks;
import java.lang.Math;

import java.util.ArrayList;

import static com.crazytanks.game.crazytanks.PPM;

public class player2 extends Sprite {
    public World world2;
    public Body b2body2;
    public Body bullet2=null;
    public double Angle2 = (double)(30f/180f)*Math.PI;
    public static ArrayList<Body> bulletlist2 = new ArrayList<Body>();
    //private TextureRegion tankStand;


    public player2(World world, playscreen_2 screen){
//        super(screen.getAtlas().findRegion("tank1Right"));
        this.world2 = world;
        definePlayer2();

//        tankStand = new TextureRegion(getTexture(),1,361,666,375);
//        setBounds(0,0,16/PPM,16/PPM);
//        setRegion(tankStand);

    }
    public void definePlayer2(){

        BodyDef bdef = new BodyDef();
        bdef.position.set(2500/ PPM,580/ PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body2 = world2.createBody(bdef);
        b2body2.setUserData("player2");

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        shape.setRadius(30/ PPM);

        fdef.shape = shape;
        b2body2.createFixture(fdef);

    }

    public void player2Bullet(){
        PcontactListener.bool2=false;
        BodyDef bdef = new BodyDef();
        bdef.position.set(b2body2.getPosition().x,b2body2.getPosition().y+1.4f);
        bdef.type = BodyDef.BodyType.DynamicBody;
        bullet2 = world2.createBody(bdef);
        bullet2.setUserData("bullet");

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();

        shape.setRadius(3/ PPM);

        fdef.shape = shape;
        bullet2.createFixture(fdef);
        bullet2.setLinearVelocity((float) (-Math.abs(27*(Math.cos(Angle2)))),25f);
        bulletlist2.add(bullet2);
    }
}
