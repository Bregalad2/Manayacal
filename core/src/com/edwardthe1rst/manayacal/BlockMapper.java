package com.edwardthe1rst.manayacal;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlockMapper {
    WorldLoader wLoader;
    DataLoader dLoader;
    
    public void init (DataLoader dloader, WorldLoader wloader) {
        wLoader = wloader;
        dLoader = dloader;
    }

    public void DrawBlocksInRange (Integer x, Integer y, SpriteBatch Blocks) {
        for (Integer a = x-25; a < x+25; a++) {
            for (Integer b = y-25; b < y+25; b++) {
                String block = wLoader.getBlock(a, b, 0)[0];
                if (block != "ungenerated") {
                    Texture texture = dLoader.BlockImages.get(block);
                    if (texture != null) {
                        Sprite sprite = new Sprite(texture);
                        Blocks.draw(sprite, a*32, b*32);
                    }
                }
            }
        }
            
    }

}
