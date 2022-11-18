package com.edwardthe1rst.manayacal;

import java.sql.*;
import java.io.File;

public class WorldLoader {
    Connection WorldDB = null;
    Statement WorldReader = null;
    public void init(File WorldPath) {
        try {
            //Class.forName("com.mysql.jdbc.Driver"); 
            WorldDB = DriverManager.getConnection("jdbc:mysql:"+WorldPath.toString()+"/World.dat");
            WorldReader = WorldDB.createStatement();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        try {
            WorldReader.executeUpdate("CREATE TABLE blocks (x INTEGER, y INTEGER, dim INTEGER, states TEXT)");
        } catch (Exception e) {}
        try {
            WorldReader.executeUpdate("CREATE TABLE entities (x FLOAT, y FLOAT, dim INTEGER, states TEXT)");
        } catch (Exception e) {}
    }

    public boolean writeBlock(Integer  x, Integer  y, Integer  dim, String states) {
        try {
            WorldReader.executeUpdate("INSERT INTO blocks VALUES ("+x.toString()+", "+y.toString()+", "+dim.toString()+", '"+states+"')");
        } catch (SQLException e) {}
        return true; 
    }

    public String getBlock(Integer  x, Integer  y, Integer  dim) {
        ResultSet result = null;
        try {
            result = WorldReader.executeQuery("SELECT id FROM blocks WHERE x = "+x.toString()+" AND y = "+y.toString());
            return result.getString("states");
        } catch (SQLException e) {
            return "unloaded";
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
}
