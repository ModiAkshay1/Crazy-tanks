package com.crazytanks.game.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crazytanks.game.crazytanks;

import static com.crazytanks.game.crazytanks.PPM;

/**
 * Created by brentaureli on 8/17/15.
 */
public class hud implements Disposable{

    //Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    private Viewport viewport;

    //Mario score/time Tracking Variables
    private Integer worldTimer;
    private boolean timeUp; // true when the world timer reaches 0
    private float timeCount;
    private static Integer score1 = 100;
    private static Integer score2 = 100;

    //Scene2D widgets
    private Label countdownLabel;
    private static Label scoreplayer1;
    private Label timeLabel;
    private static Label scoreplayer2;
    private Label player1;
    private Label player2;

    public hud(SpriteBatch sb){
        //define our tracking variables
        worldTimer = 1500;
        //timeCount = 0;
        score1 = 100;


        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(crazytanks.V_WIDTH, crazytanks.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        //define our labels using the String, and a Label style consisting of a font and color
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.RED));
        countdownLabel.setFontScale(4,4);
        scoreplayer1 =new Label(String.format("%06d", score1), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreplayer1.setFontScale(4,4);
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.RED));
        timeLabel.setFontScale(4,4);
        scoreplayer2 = new Label(String.format("%06d", score2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreplayer2.setFontScale(4,4);

        player1 = new Label("PLAYER1", new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        player1.setFontScale(4,4);
        player2 = new Label("PLAYER2", new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        player2.setFontScale(4,4);

        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(player1).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(player2).expandX().padTop(10);

        //add a second row to our table
        table.row();
        table.add(scoreplayer1).expandX();
        table.add(countdownLabel).expandX();
        table.add(scoreplayer2).expandX();


        //add our table to the stage
        stage.addActor(table);

    }

    public void update(float dt){
        timeCount += dt;
        if(timeCount >= 1){
            if (worldTimer > 0) {
                worldTimer--;
            } else {
                timeUp = true;
            }
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore1(int value){
        score1 -= value;
        scoreplayer1.setText(String.format("%06d", score1));
    }
    public static void addScore2(int value){
        score2 -= value;
        scoreplayer2.setText(String.format("%06d", score2));
    }

    @Override
    public void dispose() { stage.dispose(); }

    public boolean isTimeUp() { return timeUp; }
}
