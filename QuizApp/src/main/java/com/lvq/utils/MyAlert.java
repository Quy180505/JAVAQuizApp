package com.lvq.utils;

import javafx.scene.control.Alert;

public class MyAlert {
    private static MyAlert instance;
    private final Alert alert;
    private MyAlert()
    {
        this.alert=new Alert(Alert.AlertType.INFORMATION);
        this.alert.setHeaderText("QuizApp");
    }
    
    public static MyAlert getInstance()
    {
        if(instance==null)
        {
            instance=new MyAlert();
        }
        return instance;
    }
    
    public void showMessage(String msg)
    {
        this.alert.setContentText(msg);
        this.alert.showAndWait();
    }
    
}