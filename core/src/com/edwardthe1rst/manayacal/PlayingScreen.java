package com.edwardthe1rst.manayacal;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayingScreen {
    DataLoader dLoader;
    WorldLoader wLoader;
    BlockMapper bMapper = new BlockMapper();
    SpriteBatch Blocks;

    public void init(DataLoader Resource, WorldLoader World) {
        Blocks = new SpriteBatch();
        dLoader = Resource;
        wLoader = World;
        bMapper.init(Resource, World);
        generate();
    }
    private void generate () {
        Random random = new Random();
        for (Integer x = -25; x < 25; x++) {
            for (Integer y = -25; y < 25; y++) {
                if (random.nextInt() == 2) {
                    wLoader.addBlock(x, y, 0, "dirt", "null");
                }
            }
        }
    }
    public void loop () {
        Blocks.begin();
        bMapper.DrawBlocksInRange(0, 0, Blocks);
        Blocks.end();
    }

}
