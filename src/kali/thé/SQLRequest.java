/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé;

import java.sql.*;
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
    
    public void selectName(){
        String query = "select nom from The";
        
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.println(rs.getString("nom"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
        public void selectDesc(){
        String query = "select Description from The";
        
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.println(rs.getString("Description"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        
    public void selectCate(){
        String query = "select Categorie from The";
        
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.println(rs.getString("Categorie"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
            public void selectTemperature(){
        String query = "select Temperature from The";
        
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.println(rs.getInt("Temperature"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
     }
        
        public void selectDuree(){
        String query = "select DureeInfusion from The";
        
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                System.out.println(rs.getDouble("DureeInfusion"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args){
        //SQLRequest app = new SQLRequest();
        //app.selectName();
    }
}