package com.edwardthe1rst.manayacal;

import java.io.File;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	PlayingScreen PScreen;
	WorldLoader wLoader;
	DataLoader dLoader;
	
	@Override
	public void create () {
		PScreen = new PlayingScreen();
		dLoader = new DataLoader();
		wLoader = new WorldLoader();

		wLoader.init(new File("world"));
		dLoader.init(null);
		PScreen.init(null, null);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		if (PScreen != null) {
			PScreen.loop();
		}
		
	}
	
	@Override
	public void dispose () {
	}
}
