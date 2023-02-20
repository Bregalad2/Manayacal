package com.edwardtherst.game;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;


public class Gui {
    String Type = "";
    List<String> Slots = new ArrayList<String>();
    List<Item> Items = new ArrayList<Item>();
    List<String> Buttons = new ArrayList<String>();
    List<Runnable> SlotInteractions = new ArrayList<Runnable>();
    List<Runnable> ButtonInteractions = new ArrayList<Runnable>();
    File BGImage;

    public void render (Node guiNode, AssetManager assetManager, AppSettings settings) {
        Picture pic = new Picture("Gui "+Type);
        pic.setImage(assetManager, "Textures/guis/"+Type+".png", true);
        pic.setWidth(settings.getWidth());
        pic.setHeight(settings.getHeight());
        pic.setPosition(0, 0);
        guiNode.attachChild(pic);
        try {
            JSONObject obj = (JSONObject) new JSONParser().parse(new FileReader("game/src/main/resources/Textures/guis/"+Type+".json"));
            JSONArray jsonItems = (JSONArray) obj.get("slots");
            JSONArray itemsIteractions = (JSONArray) obj.get("slotsInteractivity");
            JSONArray jsonButtons = (JSONArray) obj.get("buttons");
            JSONArray ButtonInteractions = (JSONArray) obj.get("buttonInteractivity");
            for (Object string: jsonItems) {
                Slots.add((String) string);
            }
            for (Object string: jsonButtons) {
                Buttons.add((String) string);
            }

        } catch (Exception e) {System.out.println(e.getMessage());}
    }

    public void setItem (Integer slot, Node guiNode, DataLoader dataLoader, Item item) {
        try {guiNode.detachChildNamed("Item "+slot.toString());} catch (Exception e) {}
        Picture pic = (Picture) item.Image.clone();
        pic.setName("Item "+slot.toString());
        pic.setWidth(30);
        pic.setHeight(30);
        guiNode.attachChild(pic);
        pic.setPosition(Integer.parseInt(Slots.get(slot).split("&")[0]), Integer.parseInt(Slots.get(slot).split("&")[0]));
    }


    public void remove (Node guiNode) {
        guiNode.detachChildNamed("Gui "+Type);
    }
}
