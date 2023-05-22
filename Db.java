/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lottodatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Db {
    private String database;
    private String user;
    private String password;
    private Connection kapcsolat;

    public Db(String database, String user, String password) {
        this.database = database;
        this.user = user;
        this.password = password;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("A Connector nem található!");
        }
        
        try {
            this.kapcsolat=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+database,this.user,this.password);
        } catch (SQLException ex) {
            System.out.println("Hiba az adatbázis kapcsolatban!");
        }       
        
    }
    
    public void addNumbers(LottoSzamok szamok){
        Statement statement=null;
        try {
         statement = this.kapcsolat.createStatement();
        } catch (SQLException ex) {
            System.out.println("Hiba a Statement létrehozásánál!");
        }
        String sql ="insert into szamok values "+szamok.toSQLString();
        try {
            if (statement != null) statement.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Hiba a új rekord felvitelénél!");
        }
        //System.out.println(sql);
    }
    
    public void deleteTable() throws SQLException{
      this.kapcsolat.createStatement()
              .execute("delete from szamok;");
    }
    
    public ArrayList<LottoSzamok> getAll(){
        String sql="select * from szamok;";
        ResultSet rs=null;
        ArrayList<LottoSzamok> lista = new ArrayList<>();
        try {
            rs= this.kapcsolat.createStatement().executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println("Hiba a lekérdezben!");
        }
        if (rs!= null)
            try {
                while (rs.next()){
                    int t[] = new int[5];
                    for (int i = 2; i < 7; i++) t[i-2]=rs.getInt(i);                        
                    lista.add(new LottoSzamok(rs.getInt("id"),t) );
                           
                }
        } catch (SQLException ex) {
                System.out.println("Hiba a eredmények bejárásánál!"+ex);
        }
        return lista;
    }
    


}
