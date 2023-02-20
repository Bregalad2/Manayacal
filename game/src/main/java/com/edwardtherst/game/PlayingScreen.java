package com.edwardtherst.game;

import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl.ControlDirection;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import com.jme3.asset.AssetManager;
import com.jme3.cinematic.Cinematic;
import com.jme3.math.Vector2f;

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
	CollisionDetection Detector;
	Physics physics;
	private Integer[] OldLocation = {0, 0};

    public void init(DataLoader Resource, WorldLoader World, AssetManager aManager, Node rNode, Camera cam, BlockingQueue<Runnable> queue) {
		dLoader = Resource;
        wLoader = World;
		assetManager = aManager;
		rootNode = rNode;
		bMapper = new BlockMapper();
		camera = cam;
        cinematic = new Cinematic(rootNode, 20);
        camNodeSide = cinematic.bindCamera("sideView", cam);
        camNodeSide.setControlDir(ControlDirection.CameraToSpatial);

		Detector = new CollisionDetection();
		Detector.init();
	
        bMapper.init(Resource, World, rootNode, assetManager, Detector);
        generate();
		bMapper.DrawBlocksInRange(0, 0);
		cThread = new CameraThread();	
		physics = new Physics();
		physics.init(World, cThread, Detector);
		cThread.init(cam, wLoader, dLoader, rNode, cThread, Detector, physics, queue);
		cThread.start();
    }
    private void generate () {
        for (Integer x = -20; x < 20; x++) {
            for (Integer y = -20; y < 20; y++) {
				if (y<(x^2)) {
					wLoader.addBlock(x, y, 0, "dirt", "null");
				} else {wLoader.addBlock(x, y, 0, "air", "null");}
            }
        }
    }

	public void resize(int width, int height) {
	}

	public void onAction(String name, boolean pressed, float tpf){
		if (name == "moveRight") {
			physics.Keys[0] = pressed;
		}
		if (name == "moveLeft") {
			physics.Keys[1] = pressed;
		}
		if (name == "moveUp") {
			physics.Keys[2] = pressed;
		}
		if (name == "moveDown") {
			physics.Keys[3] = pressed;
		}
		if (OldLocation[0] > Math.round(cThread.Player.Pos[0])+4 || OldLocation[0] < Math.round(cThread.Player.Pos[0])-4 || OldLocation[1] > Math.round(cThread.Player.Pos[1]) +4 || OldLocation[1] < Math.round(cThread.Player.Pos[1]) -4) {
			OldLocation[0] = Math.round(cThread.Player.Pos[0]);
			OldLocation[1] = Math.round(cThread.Player.Pos[1]);
			bMapper.DrawBlocksInRange(Math.round(cThread.Player.Pos[0]), Math.round(cThread.Player.Pos[1]));
			System.out.println(Arrays.toString(OldLocation));
		}
		if (name == "click") {
			cThread.regesterClick(pressed, tpf);
		}
	}


}
