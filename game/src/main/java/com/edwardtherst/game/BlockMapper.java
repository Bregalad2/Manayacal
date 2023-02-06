package com.edwardtherst.game;

import java.util.HashMap;
import java.util.Map;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.*;
import com.jme3.scene.shape.*;


public class BlockMapper {
    Map<String, Geometry> BlockMap;
    WorldLoader wLoader;
    DataLoader dLoader;
    CameraThread camthread;
    Node rootNode;
    AssetManager assetManager;
    
    public void init (DataLoader dloader, WorldLoader wloader, Node rNode, AssetManager aManager) {
        wLoader = wloader;
        dLoader = dloader;
        rootNode = rNode;
        assetManager = aManager;
        BlockMap = new HashMap<String, Geometry>();
    }

    public void DrawBlocksInRange (Integer x, Integer y) {
        for (Integer a = x-10; a < x+10; a++) {
            for (Integer b = y-10; b < y+10; b++) {
                if (a==10 || b==10 || a==-10 || b==-10) {
                    BlockMap.remove(x.toString()+ " "+y.toString());
                    continue;
                }
                Geometry geo = BlockMap.get(x.toString()+ " "+y.toString());
                if (geo != null) {
                    //rootNode.attachChild(geo);
                    continue;
                } else {
                    String block = wLoader.getBlock(a, b, 0)[0];
                    if (block != "ungenerated") {
                        try {
                            if (dLoader.BlockImages.get(block) != null) {
                                RectangleMesh flatMesh = new RectangleMesh();
                                Geometry flatGeo = new Geometry("Block "+a.toString()+", "+b.toString(), flatMesh);
                                flatGeo.setLocalTranslation(new Vector3f(a,b,-3f));
                                flatGeo.setMaterial(dLoader.BlockImages.get(block));
                                rootNode.attachChild(flatGeo);
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }
        }
            
    }

}
