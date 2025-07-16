/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvq.services;

import com.lvq.pojo.Level;
import com.lvq.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author levan
 */
public class LevelServices {
    public List<Level> getLevels() throws SQLException{
        // kết nối cơ sở dữ liệu
        Connection conn=JdbcConnector.getInstance().connect();
        
        //truy vấn data
        
        Statement stm=conn.createStatement();
        ResultSet  rs= stm.executeQuery("Select * from Level");
        
        List<Level> levels=new ArrayList<>();
        while(rs.next())
        {
            int id=rs.getInt("id");
            String name=rs.getString("name");
            String note=rs.getString("note");
            Level c=new Level(id,name,note);
            
            levels.add(c);
        }
        return levels;
}
    
    
}
