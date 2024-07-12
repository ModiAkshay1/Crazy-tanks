package com.crazytanks.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.crazytanks.game.Screens.*;

public class crazytanks extends Game {
		public static final int V_WIDTH = 2560;
		public static final int V_HEIGHT = 1280;
		public static final float PPM = 100;
	public SpriteBatch batch;
	//public Texture img;
	public BitmapFont font;
	public static home_page screen1;
	public static mainmenu screen2;
	public static choosetanks screen3;
	public static playscreen_2 screen4;
	public static ingame_menu screen5;


	public static AssetManager manager;
	public static Viewport screenport;

	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.finishLoading();
		screenport = new ScreenViewport();
		setScreen(new home_page(this));
		this.font = new BitmapFont(Gdx.files.internal("newfont.fnt"));
		screen1 = new home_page(this);
		screen2 = new mainmenu(this);
		screen3 = new choosetanks(this);
		//screen4 = new playscreen_2(this,int t1,int t2);



	}

	@Override
	public void render () {
		super.render();
		//ScreenUtils.clear(1, 0, 0, 1);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		super.dispose();
		manager.dispose();

		//img.dispose();
	}
}
