package com.edwardthe1rst.manayacal;

import java.io.File;
import java.io.FileReader;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.*;

import com.badlogic.gdx.graphics.Texture;

public class DataLoader {
    List<File> Paths =  new LinkedList<File>();
    JSONObject BLOCKS = null;
    JSONObject ITEMS = null;
    JSONObject ENTITIES = null;
    Dictionary<String, Texture> BlockImages = null;
    Dictionary<String, Texture> ItemImages = null;
    Dictionary<String, Texture> EntityImages = null;
    public void init (List<File> paths) {
        if (paths != null) {
            Paths.addAll(paths);
        }
        Paths.add(new File("../resources"));

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
                            Texture image = new Texture(blockfile.toString());
                            BlockImages.put(blockfile.toString().split("/")[-1].replace(".png", ""), image);
                        }
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}

            //items

            try {
                File[] itemimagefiles = new File(path.toString()+"/blocks").listFiles();
                for (Integer a = 0; a < itemimagefiles.length; a++) {
                    try {
                        File itemfile = itemimagefiles[a];
                        if (itemfile.toString().endsWith(".png")) {
                            Texture image = new Texture("badlogic.jpg");
                            ItemImages.put(itemfile.toString().split("/")[-1].replace(".png", ""), image);
                        }
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}
            try {
                File[] entityfiles = new File(path.toString()+"/blocks").listFiles();
                for (Integer a = 0; a < entityfiles.length; a++) {
                    try {
                        File entityfile = entityfiles[a];
                        if (entityfile.toString().endsWith(".png")) {
                            Texture image = new Texture("badlogic.jpg");
                            EntityImages.put(entityfile.toString().split("/")[-1].replace(".png", ""), image);
                        }
                    } catch (Exception e) {}
                }
            } catch (Exception e) {}
        }
    }

    public void destroy () {
    }
}
