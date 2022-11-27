package com.edwardtherst.game;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.app.state.AppState;

/**
 * The JMonkeyEngine game entry, you should only do initializations for your game here, game logic is handled by
 * Custom states {@link com.jme3.app.state.BaseAppState}, Custom controls {@link com.jme3.scene.control.AbstractControl}
 * and your custom entities implementations of the previous.
 *
 */
public class Manayacal extends SimpleApplication {

    public Manayacal() {
    }

    public Manayacal(AppState... initialStates) {
        super(initialStates);
    }

    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);
        Box cube1Mesh = new Box( 1f,1f,1f);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
        
        Geometry cube1Geo = new Geometry("My Textured Box", cube1Mesh);
        cube1Geo.setLocalTranslation(new Vector3f(-3f,1.1f,0f));
        Material cube1Mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //Texture cube1Tex = assetManager.loadTexture("/Users/eddylabadorf/Documents/blank.jpg");
        //cube1Mat.setTexture("ColorMap", cube1Tex);
        cube1Geo.setMaterial(cube1Mat);
        rootNode.attachChild(cube1Geo);
    }

}
