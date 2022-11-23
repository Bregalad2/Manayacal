package com.edwardtherst.android;

import com.jme3.app.AndroidHarness;
import com.edwardtherst.game.Manayacal;


public class AndroidLauncher extends AndroidHarness {

    public AndroidLauncher() {
        appClass = Manayacal.class.getCanonicalName();
    }
}
