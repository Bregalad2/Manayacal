package com.edwardtherst.desktopmodule;

import com.edwardtherst.game.Manayacal;
import com.jme3.system.AppSettings;


public class DesktopLauncher {
    public static void main(String[] args) {
        final Manayacal game = new Manayacal();

        final AppSettings appSettings = new AppSettings(true);

        game.setSettings(appSettings);
        game.setShowSettings(false); //Settings dialog not supported on mac
        game.start();
    }
}
