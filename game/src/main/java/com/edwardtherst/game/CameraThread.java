package com.edwardtherst.game;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.shape.*;

public class CameraThread extends Thread {
    WorldLoader wLoader;
    DataLoader dLoader;

    Float[] PlayerPos = new Float[]{0f, 0f};
	Float[] PlayerSpeed = new Float[]{0f, 0f};
	Boolean[] PlayerKeys = new Boolean[]{false, false, false, false};
    Float maxSpeed = 0.05f;
    Float acceleration = 0.01f;
    Float jumpPower = 0.04f;
    Float gravity = -0.01f;
    Float playerWidth = 0.05f;
    Float playerHeight = 0.1f;
    Boolean onGround = true;
    Integer playerId;
    Camera camera;
    Node rNode;

    public void init(Camera cam, WorldLoader worldLoader, DataLoader dataLoader, Node rootNode) {
        camera = cam;
        wLoader = worldLoader;
        dLoader = dataLoader;
        rNode = rootNode;

    }

    public void run() {
        Mesh flatMesh = new Mesh();
        Geometry flatGeo = new Geometry("Player", flatMesh);
        flatGeo.setLocalTranslation(new Vector3f(1,1,-3f));
        flatGeo.setMaterial(dLoader.BlockImages.get("dirt"));
        rNode.attachChild(flatGeo);
        while (true) {
            try {
                Thread.sleep(20);
            } catch (Exception e) {}
            if (PlayerKeys[0] && PlayerSpeed[0] < maxSpeed) {

                PlayerSpeed[0] += acceleration;
            }
            if (PlayerKeys[1] && PlayerSpeed[0] > -maxSpeed) {
                PlayerSpeed[0] -= acceleration;
            }
            if (!PlayerKeys[0] && !PlayerKeys[1]) {
                PlayerSpeed[0] = 0f;
            }
            if (PlayerKeys[2] && onGround) {
                PlayerSpeed[1] = jumpPower;
                onGround = false;
            }

            try {
                /*if () {
                    if (PlayerSpeed[1] < 0) {
                        PlayerSpeed[1] = 0f;
                        System.out.println(CHandler.getTouchingShapes(playerId));
                    }
                } else {
                    PlayerSpeed[1] += gravity;
                    System.out.println(CHandler.entities.toString());
                    onGround = false;
                }*/
            } catch (Exception e) {
                PlayerSpeed[1] += gravity;
            }
            camera.setLocation(new Vector3f(PlayerPos[0], PlayerPos[1], 10f));
        }
    }
}
