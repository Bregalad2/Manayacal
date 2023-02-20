package com.edwardtherst.game;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.*;
import org.json.simple.parser.*;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.texture.Texture;
import com.jme3.ui.Picture;

public class DataLoader {
    List<File> Paths =  new LinkedList<File>();
    AssetManager assetManager;
    JSONObject BLOCKS = null;
    JSONObject ITEMS = null;
    JSONObject ENTITIES = null;
    Map<String, Material> BlockImages = new HashMap<String, Material>();
    Map<String, Picture> ItemImages = new HashMap<String, Picture>();
    Map<String, Material> EntityImages = new HashMap<String, Material>();
    public void init (List<File> paths, AssetManager manager) {
        BLOCKS = new JSONObject();
        ITEMS = new JSONObject();
        ENTITIES = new JSONObject();


        assetManager = manager;

        if (paths != null) {
            Paths.addAll(paths);
        }
        Paths.add(new File("game/src/main/resources/Textures"));

        for (Integer i = 0; i < Paths.size(); i++) {
            File path = Paths.get(i);

            //blocks
            try {
                File[] blockfiles = new File(path.toString()+"/blocks").listFiles();
                for (Integer a = 0; a < blockfiles.length; a++) {
                    try {
                        File blockfile = blockfiles[a];
                        if (blockfile.toString().endsWith(".json")) {
                            Object obj = new JSONParser().parse(new FileReader(blockfile));
                            JSONObject jo = (JSONObject) obj;
                            String id = (String) jo.get("id");
                            BLOCKS.put(id, jo);
                        }
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}

            //items

            try {
                File[] itemfiles = new File(path.toString()+"/items").listFiles();
                for (Integer a = 0; a < itemfiles.length; a++) {
                    try {
                        File itemfile = itemfiles[a];
                        if (itemfile.toString().endsWith(".json")) {
                            Object obj = new JSONParser().parse(new FileReader(itemfile));
                            JSONObject jo = (JSONObject) obj;
                            String id = (String) jo.get("id");
                            ITEMS.put(id, jo);
                        }
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}

            //entities

            try {
                File[] entityfiles = new File(path.toString()+"/entities").listFiles();
                for (Integer a = 0; a < entityfiles.length; a++) {
                    try {
                        File entityfile = entityfiles[a];
                        if (entityfile.toString().endsWith(".json")) {
                            Object obj = new JSONParser().parse(new FileReader(entityfile));
                            JSONObject jo = (JSONObject) obj;
                            String id = (String) jo.get("id");
                            ENTITIES.put(id, jo);
                        }
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}

            // images
            // blocks
            try {
                File[] blockimagefiles = new File(path.toString()+"/blocks").listFiles();
                for (Integer a = 0; a < blockimagefiles.length; a++) {
                    try {
                        File blockfile = blockimagefiles[a];
                        if (blockfile.toString().endsWith(".png")) {
                            Texture image = assetManager.loadTexture(blockfile.toString().replace("game/src/main/resources/", ""));
                            Material flatMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                            flatMat.setTexture("ColorMap", image);
                            String name = blockfile.toString().split("/")[blockfile.toString().split("/").length-1].replace(".png", "");
                            BlockImages.put(name, flatMat);
                        }
                    } catch (Exception e) {System.out.println(e.getMessage());}
                }
            } catch (Exception e) {System.out.println(e.getMessage());}
            //items

            try {
                File[] itemimagefiles = new File(path.toString()+"/items").listFiles();
                for (Integer a = 0; a < itemimagefiles.length; a++) {
                    try {
                        File itemfile = itemimagefiles[a];
                        if (itemfile.toString().endsWith(".png")) {
                            Picture pic = new Picture("Default Icon of "+itemfile);
                            pic.setImage(assetManager, itemfile.toString().replace("game/src/main/resources/", ""), true);
                            String name = itemfile.toString().split("/")[itemfile.toString().split("/").length-1].replace(".png", "");
                            ItemImages.put(name, pic);
                        }
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}
            try {
                File[] entityfiles = new File(path.toString()+"/entities").listFiles();
                for (Integer a = 0; a < entityfiles.length; a++) {
                    try {
                        File entityfile = entityfiles[a];
                        if (entityfile.toString().endsWith(".png")) {
                            Texture image = assetManager.loadTexture(entityfile.toString().replace("game/src/main/resources/", ""));
                            Material flatMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                            flatMat.setTexture("ColorMap", image);
                            String name = entityfile.toString().split("/")[entityfile.toString().split("/").length-1].replace(".png", "");
                            EntityImages.put(name, flatMat);
                        }
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}
        }
        System.out.println(BlockImages.toString());
        System.out.println(ItemImages.toString());
        System.out.println(EntityImages.toString());


    }

    public void destroy () {
    }
}
