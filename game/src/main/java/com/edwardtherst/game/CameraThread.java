package com.edwardtherst.game;

import java.util.concurrent.BlockingQueue;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.RectangleMesh;
import com.jme3.system.AppSettings;

public class CameraThread extends Thread {
    WorldLoader wLoader;
    DataLoader dLoader;
    Camera camera;
    Node rNode;
    Physics physics;
    BlockingQueue<Runnable> queue;
    Geometry flatGeo;
    RectangleMesh flatMesh;
    Entity Player;
    Node GuiNode;
    AssetManager assetManager;
    InputManager inputManager;
    AppSettings settings;

    public void init(Camera cam, WorldLoader worldLoader, DataLoader dataLoader, Node rootNode, CameraThread me, CollisionDetection Detector, Physics pphysics, BlockingQueue<Runnable> queuee) {
        camera = cam;
        wLoader = worldLoader;
        dLoader = dataLoader;
        rNode = rootNode;
        physics = pphysics;
        queue = queuee;
    }

    public void run() {
        SpellListener spellListener = new SpellListener();
        spellListener.listen();
        queue.add(new Runnable(){
            @Override
            public void run() {
                flatMesh = new RectangleMesh();
                flatGeo = new Geometry("Player", flatMesh);
                flatGeo.setLocalTranslation(new Vector3f(1,1,-3f));
                flatGeo.setMaterial((Material)dLoader.EntityImages.get("player"));
                rNode.attachChild(flatGeo);
            }
        });
        Player = new Entity();
        Gui inventry = new Gui();
        inventry.Type = "inventory";
        queue.add(new Runnable(){
            @Override
            public void run() {
                inventry.render(GuiNode, assetManager, settings);
                Item item = new Item();
                item.Count = 3;
                item.MaxCount = 6;
                item.Name = "rocks";
                item.Image = dLoader.ItemImages.get("sword");
                inventry.setItem(0, GuiNode, dLoader, item);
            }
        });
        while (true) {
            physics.run(camera, rNode, queue, Player);
            queue.add(new Runnable(){
                @Override
                public void run() {
                    rNode.getChild("Player").setLocalTranslation(new Vector3f(Player.Pos[0], Player.Pos[1],-3f));
                    camera.setLocation(new Vector3f(Player.Pos[0], Player.Pos[1], 10f));
                }
            });
        }
    }
    public void regesterClick(boolean pressed, float tpf) {
        Vector2f click2d = inputManager.getCursorPosition();
        System.out.println(click2d.x);
    }
}
