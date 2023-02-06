package com.edwardtherst.game;

import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl.ControlDirection;
import com.jme3.asset.AssetManager;
import com.jme3.cinematic.Cinematic;

public class PlayingScreen {
    DataLoader dLoader;
    WorldLoader wLoader;
    BlockMapper bMapper;
	Camera camera;
	Cinematic cinematic;
	CameraNode camNodeSide;
	CameraThread cThread;
	Node rootNode;
	AssetManager assetManager;
	private Integer[] OldLocation = new Integer[]{0, 0};

    public void init(DataLoader Resource, WorldLoader World, AssetManager aManager, Node rNode, Camera cam) {
		dLoader = Resource;
        wLoader = World;
		assetManager = aManager;
		rootNode = rNode;
		bMapper = new BlockMapper();
    

		camera = cam;
        cinematic = new Cinematic(rootNode, 20);
        camNodeSide = cinematic.bindCamera("sideView", cam);
        camNodeSide.setControlDir(ControlDirection.CameraToSpatial);
	
        bMapper.init(Resource, World, rootNode, assetManager);
        generate();
		bMapper.DrawBlocksInRange(0, 0);
		cThread = new CameraThread();	
		cThread.init(cam, wLoader, dLoader, rNode);
		cThread.start();
    }
    private void generate () {
        for (Integer x = -7; x < 7; x++) {
            for (Integer y = -7; y < 7; y++) {
				if (y<0 && x+y%3 != 2) {
					wLoader.addBlock(x, y, 0, "dirt", "null");
				} else {wLoader.addBlock(x, y, 0, "air", "null");}
            }
        }
    }

	public void resize(int width, int height) {
	}

	public void onAction(String name, boolean pressed, float tpf){
		if (name == "moveRight") {
			cThread.PlayerKeys[0] = pressed;
		}
		if (name == "moveLeft") {
			cThread.PlayerKeys[1] = pressed;
		}
		if (name == "moveUp") {
			cThread.PlayerKeys[2] = pressed;
		}
		if (name == "moveDown") {
			cThread.PlayerKeys[3] = pressed;
		}
		System.out.println(name);
		if (OldLocation[0]!= Math.round(cThread.PlayerPos[0]) || OldLocation[1] != Math.round(cThread.PlayerPos[1])) {
			OldLocation[0] = Math.round(cThread.PlayerPos[0]);
			OldLocation[1] = Math.round(cThread.PlayerPos[1]);
			//bMapper.DrawBlocksInRange(OldLocation[0], OldLocation[1]);
		}
	}


}
