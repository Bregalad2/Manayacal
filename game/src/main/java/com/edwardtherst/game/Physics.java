package com.edwardtherst.game;

import java.util.concurrent.BlockingQueue;

import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

public class Physics {
    Boolean[] Keys = new Boolean[]{false, false, false, false};
    CollisionDetection Detector;
    WorldLoader Loader;
    CameraThread cThread;

    public void init(WorldLoader loader, CameraThread cameraThread, CollisionDetection CDetector) {
        Loader = loader;
        Detector = CDetector;
        cThread = cameraThread;
    }
    public void MovePlayer(Integer[] dxdy) {
        
    }
    public void run(Camera camera, Node node, BlockingQueue<Runnable> queue, Entity Player) {
            Boolean[] touching = Detector.getSolidTouching(Math.round(Player.Pos[0]), Math.round(Player.Pos[1]));
            try {
                Thread.sleep(20);
            } catch (Exception e) {}
            if (Keys[0] && Player.Speed[0] < Player.MaxSpeed) {
                Player.Speed[0] += Player.Acceleration;
            }
            if (Keys[1] && Player.Speed[0] > -Player.MaxSpeed) {
                Player.Speed[0] -= Player.Acceleration;
            }
            if (!Keys[0] && !Keys[1]) {
                if (Player.Speed[0] > 0) {
                    Player.Speed[0] -= Player.Acceleration;
                }
                if (Player.Speed[0] > 0) {
                    Player.Speed[0] += Player.Acceleration;
                }
            }
            if (touching[1] && Player.Speed[0] > 0.0f) {
                if (Player.Pos[0] > 0 && Player.Pos[0]%1 > 0.8) {
                    Player.Speed[0] = 0.0f;
                } else if (Player.Pos[0] < 0 && Player.Pos[0]%1+1 > 0.8) {
                    Player.Speed[0] = 0.0f;
                }
            }
            if (touching[2] && Player.Speed[0] < 0.0f) {
                if (Player.Pos[0] > 0 && Player.Pos[0]%1 < 0.2) {
                    Player.Speed[0] = 0.0f;
                } else if (Player.Pos[0] < 0 && Player.Pos[0]%1+1 < 0.2) {
                    Player.Speed[0] = 0.0f;
                }
            

            }
            if (Keys[2] && Player.OnGround) {
                Player.Speed[1] = Player.JumpPower;
                Player.OnGround = false;
            }
            try {
                if (touching[4]) {
                    if (Player.Speed[1] < 0) {
                        Player.Speed[1] = 0f;
                        
                        Player.OnGround = true;
                    } else if (Player.Speed[1] > 0) {}
                    if (Player.OnGround && !touching[0]) {
                        Player.Pos[1] = (float) Math.floor(Player.Pos[1]);
                    }
                } else {
                    Player.Speed[1] += Player.Gravity;
                    Player.OnGround = false;
                }
                if (touching[0]) {
                    //Player.Speed[1] = 0.2f;
                }
            //System.out.println();
            } catch (Exception e) {
                Player.Speed[1] += Player.Gravity;
            }
            Player.Pos[0] += Player.Speed[0];
            Player.Pos[1] += Player.Speed[1];
    }

    public Integer[] getPlayerXY() {
        Integer[] xy = {0, 0};
        return xy;
    }
}
