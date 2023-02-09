package com.edwardtherst.game;

import java.util.HashMap;
import java.util.Map;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.*;
import com.jme3.scene.shape.*;


public class BlockMapper {
    Map<String, String> BlockMap;
    WorldLoader wLoader;
    DataLoader dLoader;
    CameraThread camthread;
    Node rootNode;
    AssetManager assetManager;
    CollisionDetection Detector;
    
    public void init (DataLoader dloader, WorldLoader wloader, Node rNode, AssetManager aManager, CollisionDetection CDetector) {
        wLoader = wloader;
        dLoader = dloader;
        rootNode = rNode;
        assetManager = aManager;
        BlockMap = new HashMap<String, String>();
        Detector = CDetector;  
    }

    public void DrawBlocksInRange (Integer x, Integer y) {
        for (Integer a = x-10; a < x+10; a++) {
            for (Integer b = y-10; b < y+10; b++) {
                String geo = BlockMap.get(x.toString()+ "&"+y.toString());
                if (geo != null) {
                    rootNode.attachChild(rootNode.getChild(x.toString()+ "&"+y.toString()));
                    continue;
                } else {
                    String block = wLoader.getBlock(a, b, 0)[0];
                    if (block != "ungenerated") {
                        try {
                            if (dLoader.BlockImages.get(block) != null) {
                                RectangleMesh flatMesh = new RectangleMesh();
                                Geometry flatGeo = new Geometry("Block "+a.toString()+"&"+b.toString(), flatMesh);
                                flatGeo.setLocalTranslation(new Vector3f(a,b,-3f));
                                flatGeo.setMaterial(dLoader.BlockImages.get(block));
                                rootNode.attachChild(flatGeo);
                                Detector.addToMap(a, b, "solid");
                                BlockMap.put(a.toString(0, 0)+"&"+b.toString(), block);
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }
        }
        Object[] values = BlockMap.keySet().toArray();
        for (Object s: values) {
            Integer a = Integer.parseInt(s.toString().split("&", 0)[0]);
            Integer b = Integer.parseInt(s.toString().split("&", 0)[1]);
            if (a > y+10 || a < y-10 || b > x + 10 || b < x - 10) {
                try {
                    rootNode.detachChild(rootNode.getChild("Block "+s.toString()));
                    BlockMap.remove(s.toString());
                } catch (Exception e) {}

            }
        }

    }
}
