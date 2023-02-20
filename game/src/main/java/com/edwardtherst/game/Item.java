package com.edwardtherst.game;

import org.json.simple.JSONObject;
import com.jme3.ui.Picture;

public class Item {
    String Name = "";
    String Type = "";
    Integer Count = 0;
    Integer MaxCount = 0;
    Picture Image;
    JSONObject States;

    
    public Boolean equals(Item item) {
        if (Name == item.Name && Type == item.Type && States.equals(item.States)) {
            return true;
        }
        return false;
    }
    public Item stack(Item item) {
        if (equals(item)) {
            if (Count + item.Count > MaxCount) {
                Count = MaxCount;
                item.Count = Count+item.Count-MaxCount;
                return item;
            } else {
                Count += item.Count;
                return null;
            }
        } else {
            return item;
        }
    }
}
