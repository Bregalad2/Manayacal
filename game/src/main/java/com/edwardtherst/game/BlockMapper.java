package com.edwardthe1rst.manayacal;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlockMapper {
    Map<String, Sprite> BlockMap;
    WorldLoader wLoader;
    DataLoader dLoader;
    
    public void init (DataLoader dloader, WorldLoader wloader) {
        wLoader = wloader;
        dLoader = dloader;
        BlockMap = new HashMap<String, Sprite>();
    }

    public void DrawBlocksInRange (Integer x, Integer y, SpriteBatch Blocks) {
        for (Integer a = x-5; a < x+5; a++) {
            for (Integer b = y-5; b < y+5; b++) {
                Sprite sprite = BlockMap.get(x.toString()+ " "+y.toString());
                if (sprite != null) {
                    Blocks.draw(sprite, a*32, b*32, 32, 32);
                } else {
                    String block = wLoader.getBlock(a, b, 0)[0];
                    if (block != "ungenerated") {
                        Texture texture = dLoader.BlockImages.get(block);
                        if (texture != null) {
                            sprite = new Sprite(texture);
                            Blocks.draw(sprite, a*32+300, b*32+300, 32, 32);
                        }
                    }
                }
            }
        }
            
    }

}
