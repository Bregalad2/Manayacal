package com.edwardtherst.game;

import java.sql.*;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.File;

public class WorldLoader {

    Connection WorldDB = null;
    Statement WorldReader = null;
    public void init(File WorldPath) {
        try  {
            WorldDB = DriverManager.getConnection("jdbc:sqlite:"+WorldPath.getAbsolutePath().toString()+"/world.db");
            if (WorldDB != null) {
                DatabaseMetaData meta = WorldDB.getMetaData();
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            WorldReader = WorldDB.createStatement();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        try {
            WorldReader.executeUpdate("CREATE TABLE blocks (x INTEGER, y INTEGER, dim INTEGER, id TEXT, state TEXT)");
        } catch (Exception e) {}
        try {
            WorldReader.executeUpdate("CREATE TABLE entities (x FLOAT, y FLOAT, dim INTEGER, id TEXT, states TEXT)");
        } catch (Exception e) {}
    }

    public boolean addBlock(Integer  x, Integer  y, Integer  dim,String id, String state) {
        try {
            WorldReader.executeUpdate("INSERT INTO blocks VALUES ("+x.toString()+", "+y.toString()+", "+dim.toString()+", '"+id+"', '"+state+"')");
            WorldDB.commit();
        } catch (SQLException e) {
            return false;
        }
        return true; 
    }

    public String[] getBlock(Integer  x, Integer  y, Integer  dim) {
        ResultSet result = null;
        try {
            result = WorldReader.executeQuery("SELECT * FROM blocks WHERE x = "+x.toString()+" AND y = "+y.toString());
            String[] results = new String[] {
                result.getString("id"), 
                result.getString("state")
            };
            return results;
        } catch (SQLException e) {
            String[] results = new String[] {
                "unloaded",
                "null"
            };
            return results;
        }
    }

    public boolean addEntity(Float x, Float y, Integer  dim, String states) {
        return true;
    }

    public String getEntities(Integer  dim, Integer  x, Integer  y, Integer  a, Integer  b) {
        return "true";
    }

    public String getEntities(Integer  dim) {
        return "true";
    }

    public Boolean save() {
        try {
            WorldDB.commit();
        } catch (Exception e) {System.err.println(e.getMessage());}
        return false;
    }
}
