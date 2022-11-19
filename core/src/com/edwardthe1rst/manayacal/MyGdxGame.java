package com.edwardthe1rst.manayacal;

import java.io.File;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.*;

public class MyGdxGame extends ApplicationAdapter {

	PlayingScreen PScreen;
    DataLoader dLoader;
    WorldLoader wLoader;
	
	@Override
	public void create () {
		PScreen = new PlayingScreen();
		dLoader = new DataLoader();
		wLoader = new WorldLoader();
		wLoader.init(new File("world"));
		dLoader.init(null);
		PScreen.init(dLoader, wLoader);
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
