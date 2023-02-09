package com.edwardtherst.game;

import java.util.HashMap;
import java.util.Map;

public class CollisionDetection {

    Map<String, String> graph;

    public void init() {
        graph = new HashMap<String, String>();
    }
    public void addToMap(Integer x, Integer y, String state) {
        graph.put(x.toString()+"&"+y.toString(), state);;
    }
    public Boolean[] getSolidTouching(Integer x, Integer y) {
        /*   3
         * 1 0 2
         *   4
         */
        Boolean[] values = {false, false, false, false, false};
        if (graph.get(x.toString()+"&"+y.toString()) == "solid") {
            values[0] = true;
        }
        if (graph.get(((Integer)(x+1)).toString()+"&"+y.toString()) == "solid") {
            values[1] = true;
        }
        if (graph.get(((Integer)(x-1)).toString()+"&"+y.toString()) == "solid") {
            values[2] = true;
        }
        if (graph.get(x.toString()+"&"+((Integer)(y+1)).toString()) == "solid") {
            values[3] = true;
        }
        if (graph.get(x.toString()+"&"+((Integer)(y-1)).toString()) == "solid") {
            values[4] = true;
        }
        return values;
    }

}
