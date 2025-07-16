/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvq.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author levan
 */
public class Question {
    private int id;
    private String content;
    private String hint;
    private String image;
    
    private Category category;
    private Level level;
    private List<Choice> choices;

    private Question(Builder b) {
        this.id=b.id;
        this.hint=b.hint;
        this.image=b.image;
        this.category=b.category;
        this.level=b.level;
        this.choices=b.choices; 
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getHint() {
        return hint;
    }

    public String getImage() {
        return image;
    }

    public Category getCategory() {
        return category;
    }

    public Level getLevel() {
        return level;
    }

    public List<Choice> getChoices() {
        return choices;
    }
    
    
    
    public static class Builder {
     private int id;
    private String content;
    private String hint;
    private String image;
    
    private Category category;
    private Level level;
    private List<Choice> choices=new ArrayList<>();
    
    public Builder(String content,Category c,Level level) throws Exception
    {
        if(content.isEmpty()|| c==null|| level==null)
        {
            throw new Exception("invalid data");
        }
        this.content=content;
        this.category=category;
        this.level=level;
    }
    
    public Builder addHint(String h)
    {
        this.hint=h;
        return this;
    }
    
    public Builder addImage(String image)
    {
        this.image=image;
        return this;
    }
    public Builder addChoice(Choice c)
    {
        this.choices.add(c);
        return this;
    }
  
    public Question build()
    {
        return new Question(this);
    }
    
    }
}

