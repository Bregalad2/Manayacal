package com.edwardtherst.game;

import java.util.concurrent.BlockingQueue;

import com.jme3.math.Vector3f;

import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.RectangleMesh;

public class CameraThread extends Thread {
    WorldLoader wLoader;
    DataLoader dLoader;
    Camera camera;
    Node rNode;
    Physics physics;
    BlockingQueue<Runnable> queue;
    Geometry flatGeo;
    RectangleMesh flatMesh;

    public void init(Camera cam, WorldLoader worldLoader, DataLoader dataLoader, Node rootNode, CameraThread me, CollisionDetection Detector, Physics pphysics, BlockingQueue<Runnable> queuee) {
        camera = cam;
        wLoader = worldLoader;
        dLoader = dataLoader;
        rNode = rootNode;
        physics = pphysics;
        queue = queuee;
    }

    public void run() {
        queue.add(new Runnable(){
            @Override
            public void run() {
                flatMesh = new RectangleMesh();
                flatGeo = new Geometry("Player", flatMesh);
                flatGeo.setLocalTranslation(new Vector3f(1,1,-3f));
                flatGeo.setMaterial(dLoader.BlockImages.get("dirt"));
                rNode.attachChild(flatGeo);
            }
        });
        physics.run(camera, rNode, queue);
    }
}
