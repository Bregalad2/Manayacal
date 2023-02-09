package com.edwardtherst.game;

import java.util.concurrent.BlockingQueue;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

public class Physics {
    CollisionDetection Detector;
    WorldLoader Loader;
    CameraThread cThread;
    Boolean[] PlayerKeys = new Boolean[]{false, false, false, false};
    Float[] PlayerPos = new Float[]{0f, 0f};
	Float[] PlayerSpeed = new Float[]{0f, 0f};
    Float maxSpeed = 0.05f;
    Float acceleration = 0.01f;
    Float jumpPower = 0.2f;
    Float gravity = -0.01f;
    Float playerWidth = 0.05f;
    Float playerHeight = 0.1f;
    Boolean onGround = true;
    Integer playerId;

    public void init(WorldLoader loader, CameraThread cameraThread, CollisionDetection CDetector) {
        Loader = loader;
        Detector = CDetector;
        cThread = cameraThread;
    }
    public void MovePlayer(Integer[] dxdy) {
        
    }
    public void run(Camera camera, Node node, BlockingQueue<Runnable> queue) {
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
                if (Detector.getSolidTouching(Math.round(PlayerPos[0]), Math.round(PlayerPos[1]))[4]) {
                    if (PlayerSpeed[1] < 0) {
                        PlayerSpeed[1] = 0f;
                    }
                    onGround = true;
                } else {
                    PlayerSpeed[1] += gravity;
                    onGround = false;
                }
            } catch (Exception e) {
                PlayerSpeed[1] += gravity;
            }
            camera.setLocation(new Vector3f(PlayerPos[0], PlayerPos[1], 10f));
            queue.add(new Runnable(){
                @Override
                public void run() {
                    node.getChild("Player").setLocalTranslation(new Vector3f(PlayerPos[0], PlayerPos[1],-3f));
                }
            });
            PlayerPos[0] += PlayerSpeed[0];
            PlayerPos[1] += PlayerSpeed[1];
        }
    }

    public Integer[] getPlayerXY() {
        Integer[] xy = {0, 0};
        return xy;
    }
}
