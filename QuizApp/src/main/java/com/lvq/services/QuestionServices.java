/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvq.services;

import com.lvq.pojo.Question;
import com.lvq.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author levan
 */
public class QuestionServices {
    
    public List<Question> getQuestions() throws SQLException{
        // kết nối cơ sở dữ liệu
        Connection conn=JdbcConnector.getInstance().connect();
        
        //truy vấn data
        
        Statement stm=conn.createStatement();
        ResultSet  rs= stm.executeQuery("Select * From question");
        
        List<Question> questions=new ArrayList<>();
        while(rs.next())
        {
           Question q=new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
           questions.add(q);
        }
        return questions;
}
    
    
    
    
    public void  addQuestion(Question q) throws SQLException{
        Connection conn=JdbcConnector.getInstance().connect();
        
        conn.setAutoCommit(false);
        String sql= "INSERT INTO  question(content,hint,image,category_id,level_id) VALUES(?,?,?,?,?)";
        PreparedStatement stm=conn.prepareCall(sql);
        stm.setString(1,q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());
       if(stm.executeUpdate()>0)
       {
           int qId=-1;
          ResultSet r= stm.getGeneratedKeys();
          if(r.next())
          {
              qId=r.getInt(1);
          }
           sql="INSERT INTO choice(content,is_correct,question_id) VALUES(?,?,?)";
           stm=conn.prepareCall(sql);
           for( var choice : q.getChoices())
           {
              
               stm.setString(1, choice.getContent());
               stm.setBoolean(2, choice.isCorrect());
               stm.setInt(3, qId);
               stm.executeUpdate();
           }
           
           conn.commit();
       }
       else conn.rollback();
    }
}
