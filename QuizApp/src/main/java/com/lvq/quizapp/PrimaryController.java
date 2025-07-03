package com.lvq.quizapp;

import com.lvq.utils.MyAlert;
import com.lvq.utils.MyStage;
import com.lvq.utils.themes.Theme;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;


public class PrimaryController implements Initializable{
    @FXML ComboBox<Theme> cbThemes;
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
    }
    public void handleQuesTionManagement(ActionEvent event) throws IOException
    {
        MyStage.getInstance().showStage("questions.fxml");
       
    }
     public void handlePractice(ActionEvent event)
    {
        MyAlert.getInstance().showMessage("coming soon");
    }
     public void handleChangeTheme(ActionEvent event){
       this.cbThemes.getSelectionModel().getSelectedItem().updateTheme(this.cbThemes.getScene());
     }
   
}
