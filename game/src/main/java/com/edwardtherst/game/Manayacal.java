package com.edwardtherst.game;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;

/**
 * The JMonkeyEngine game entry, you should only do initializations for your game here, game logic is handled by
 * Custom states {@link com.jme3.app.state.BaseAppState}, Custom controls {@link com.jme3.scene.control.AbstractControl}
 * and your custom entities implementations of the previous.
 *
 */
public class Manayacal extends SimpleApplication implements ActionListener {
    PlayingScreen pScreen;
    DataLoader dLoader;
    WorldLoader wLoader;
    public static final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float intensity, float tpf) {
            if (name.equals("clickLeft")) {
                System.out.println(inputManager.getCursorPosition());
                //Vector2f click2d = inputManager.getCursorPosition();
            }
        }
    };

    public Manayacal() {
    }
    
    public void registerInput() {
        inputManager.addMapping("moveUp", new KeyTrigger(KeyInput.KEY_SPACE), new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("moveDown", new KeyTrigger(KeyInput.KEY_DOWN), new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("moveRight", new KeyTrigger(KeyInput.KEY_RIGHT), new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("moveLeft", new KeyTrigger(KeyInput.KEY_LEFT), new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("clickLeft", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        //inputManager.addMapping("displayPosition", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addListener(this, "moveUp", "moveDown", "moveRight", "moveLeft");
        inputManager.addListener(analogListener, "clickLeft");
        //inputManager.addListener(this, "displayPosition");
    }


    public Manayacal(AppState... initialStates) {
        super(initialStates);
    }

    @Override
    public void simpleInitApp() {
        pScreen = new PlayingScreen();
        dLoader = new DataLoader();
        wLoader = new WorldLoader();
        dLoader.init(null, assetManager);
        wLoader.init(new File("/Users/eddylabadorf/Documents/GitHub/manayacal/game/src/main/resources/world"));
        flyCam.setEnabled(false);
        pScreen.init(dLoader, wLoader, assetManager, rootNode, cam, queue);
        pScreen.cThread.GuiNode = guiNode;
        pScreen.cThread.assetManager = assetManager;
        pScreen.cThread.inputManager = inputManager;
        pScreen.cThread.settings = settings;
        inputManager.setCursorVisible(false);
        setDisplayStatView(false); setDisplayFps(false);
        registerInput();
    }

    @Override
    public void onAction(String name, boolean pressed, float tpf){
        pScreen.onAction(name, pressed, tpf);
    }

    @Override
    public void simpleUpdate(float tpf) {
        try {
            queue.take().run();
        } catch (InterruptedException e) {}
    }

}
