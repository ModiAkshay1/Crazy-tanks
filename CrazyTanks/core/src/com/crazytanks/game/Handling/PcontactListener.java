package com.crazytanks.game.Handling;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.crazytanks.game.Screens.playscreen_2;
import com.crazytanks.game.Sprites.player1;
import com.crazytanks.game.Sprites.player2;
import com.crazytanks.game.utils.hud;

import java.util.ArrayList;

import static com.crazytanks.game.crazytanks.PPM;

public class PcontactListener implements ContactListener {
    playscreen_2 play2;
    public static boolean bool1=false;
    public static boolean bool2=false;
    private Sound explosion;
    public PcontactListener(playscreen_2 sc){
        play2 = sc;
    }
    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        explosion = Gdx.audio.newSound(Gdx.files.internal("esm_8bit_explosion_bomb_boom_blast_cannon_retro_old_school_classic_cartoon.mp3"));
        //System.out.println(contact.getFixtureA().getShape());
        //System.out.println(contact.getFixtureB().getShape().getRadius());

        //play2.applyfriction();
//        if(fb.getShape().getRadius()==0.1f){
//            System.out.println("aaa");
//            final Array<JointEdge> lis = fb.getBody().getJointList();
//            System.out.println(lis.size);
//            while(lis.size>0){
//                play2.world.destroyJoint(lis.get(0).joint);
//            }
//            System.out.println(lis.size);
//            //play2.getPlayerone().bullet=null;
//            //play2.world.destroyBody(contact.getFixtureB().getBody());
//            if(player1.bulletlist.size()>2) play2.world.destroyBody(player1.bulletlist.get(0));
//            System.out.println("sdnwd");
//            //contact.getFixtureB().getBody().setActive(false);
//
//
//        }
        if(fb.getShape().getRadius()==0.02f){

            //System.out.println("aaa");

            if(Math.abs(player1.bulletlist.get(player1.bulletlist.size()-1).getPosition().x - play2.getPlayertwo().b2body2.getPosition().x) < 1) {
                if (Math.abs(player1.bulletlist.get(player1.bulletlist.size() - 1).getPosition().y - play2.getPlayertwo().b2body2.getPosition().y) < 1) {

                    if (!bool1) {
                        explosion.play(1f);
                        play2.decr_health2();
                        System.out.println("health 2  " + play2.getHealth2());
                        hud.addScore2(10);
                        bool1=true;

                    }
                }
            }
        }
        if(fb.getShape().getRadius()==0.03f){

            //System.out.println("aaa");

            if(Math.abs(player2.bulletlist2.get(player2.bulletlist2.size()-1).getPosition().x - play2.getPlayerone().b2body.getPosition().x) < 1) {
                if (Math.abs(player2.bulletlist2.get(player2.bulletlist2.size() - 1).getPosition().y - play2.getPlayerone().b2body.getPosition().y) < 1) {

                    if (!bool2) {
                        explosion.play(1f);
                        play2.decr_health1();
                        System.out.println("health 1  " + play2.getHealth1());
                        hud.addScore1(10);
                        bool2=true;
                    }
                }
            }
        }


//        if(fa == null || fb == null) return;
//        if(fa.getUserData() == null || fb.getUserData() == null) return;
//
//        if(isTutorialContact(fa, fb)) {
//            TutorialBox tba = (TutorialBox) fa.getUserData();
//            TutorialBox tbb = (TutorialBox) fb.getUserData();
//
//            tbb.body.applyForceToCenter(-3000, 0, false);
//        }
//        fa.getBody().getType()== BodyDef.BodyType.StaticBody



//        if(fb.getBody().getType()== BodyDef.BodyType.DynamicBody){
//           // System.out.println(fb.getType().);
//            System.out.println("fuckkkkk");
//        } else if (fa.getBody().getType()== BodyDef.BodyType.StaticBody) {
//            System.out.println("fuck2");
//        }
//        } else if (fa.getUserData().equals("player1")) {
//            System.out.println("p1");
//        } else if (fa.getUserData().equals("player2")) {
//            System.out.println("p2");
//        }

        //System.out.println(fa.getUserData());
//        System.out.println(fa.getClass());
//        System.out.println(fb.getClass());
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

//    private boolean isTutorialContact(Fixture a, Fixture b) {
//        return (a.getUserData() instanceof TutorialBox && b.getUserData() instanceof TutorialBox);
//    }
}