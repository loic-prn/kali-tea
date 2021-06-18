/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lperr
 */
public class SQLRequest {
    private Connection connect(){
        String url = "jdbc:sqlite:semainespe.db";
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public ArrayList<String> selectName(){
        String query = "select nom from The";
        ArrayList<String> ret = new ArrayList<>();
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getString("nom"));
                ret.add(rs.getString("nom"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    
    public ArrayList<String> selectDesc(){
        String query = "select Description from The";
        ArrayList<String> ret = new ArrayList<>();
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.println(rs.getString("Description"));
                ret.add(rs.getString("Description"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
        
    public ArrayList<String> selectCate(){
        String query = "select Categorie from The";
        ArrayList<String> ret = new ArrayList<>();
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.println(rs.getString("Categorie"));
                ret.add(rs.getString("Categorie"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    
    public ArrayList<Integer> selectTemperature(){
        String query = "select Temperature from The";
        ArrayList<Integer> ret = new ArrayList<>();
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.println(rs.getInt("Temperature"));
                ret.add(rs.getInt("Temperature"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
        
    public ArrayList<Double> selectDuree(){
        String query = "select DureeInfusion from The";
        ArrayList<Double> ret = new ArrayList<>();
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.println(rs.getDouble("DureeInfusion"));
                ret.add(rs.getDouble("DureeInfusion"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }
    
    public static void main(String[] args){
//        SQLRequest app = new SQLRequest();
//        System.out.println(app.selectName());
    }
}