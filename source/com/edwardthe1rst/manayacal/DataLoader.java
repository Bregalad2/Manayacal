package com.edwardthe1rst.manayacal;

import java.io.File;
import java.io.FileReader;
import org.json.simple.*;
import org.json.simple.parser.*;

public class DataLoader {
    JSONObject BLOCKS = null;
    JSONObject ITEMS = null;
    JSONObject ENTITIES = null;
    public void init (File[] paths) {
        paths[paths.length+1] = new File("../resources");

        for (Integer i = 0; i < paths.length; i++) {
            File path = paths[i];
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
        }
    }
}
