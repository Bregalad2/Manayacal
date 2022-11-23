package com.edwardthe1rst.manayacal;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.*;

public class PlayingScreen {
    OrthographicCamera cam;
    DataLoader dLoader;
    WorldLoader wLoader;
    BlockMapper bMapper = new BlockMapper();
    SpriteBatch Blocks;
	StretchViewport viewport;

    public void init(DataLoader Resource, WorldLoader World) {
		Gdx.graphics.setTitle("Manayacal");
		Gdx.graphics.setWindowedMode (1280, 675);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(30, 30 * (h / w));
		cam.position.set(30, 30, 1f);
		viewport = new StretchViewport(30, 30 * (h / w));
		
		viewport.setCamera(cam);
		//viewport.update(30, 30);
		viewport.apply(true);
		
		
		//cam.update();
		viewport.apply();
        Blocks = new SpriteBatch();
        dLoader = Resource;
        wLoader = World;
        bMapper.init(Resource, World);
        generate();
    }
    private void generate () {
        for (Integer x = -25; x < 25; x++) {
            for (Integer y = -25; y < 25; y++) {
				wLoader.addBlock(x, y, 0, "air", "null");
            }
        }
    }
    public void loop () {
        handleInput();
		viewport.getCamera().far = MathUtils.clamp(30, 0.1f, 100);
		cam.update();
		Blocks.setProjectionMatrix(cam.combined);//*/
		Blocks.setProjectionMatrix(viewport.getCamera().combined);
		viewport.getCamera().update();
		viewport.apply(true);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Blocks.begin();
        bMapper.DrawBlocksInRange(0, 0, Blocks);
        Blocks.end();
    }

    private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			//cam.zoom += 0.02;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			//cam.zoom -= 0.02;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			//cam.translate(3, 0, 0);
			viewport.getCamera().translate(3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			//cam.translate(-3, 0, 0);
			viewport.getCamera().translate(-3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			//cam.translate(0, 3, 0);
			viewport.getCamera().translate(0, 3, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			//cam.translate(0, -3, 0);
			viewport.getCamera().translate(0, -3, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			//cam.translate(0, 0, 3);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			//cam.translate(0, 0, -3);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			//cam.rotate(-0.5f, 0, 0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			//cam.rotate(0.5f, 0, 0, 1);
		}
	}

	public void resize(int width, int height) {
		//cam.viewportWidth = 32f;
		//cam.viewportHeight = cam.viewportWidth * height/width;
		//cam.update();
	}


}
