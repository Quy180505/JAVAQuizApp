/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.lvq.quizapp;

import com.lvq.pojo.Category;
import com.lvq.pojo.Choice;
import com.lvq.pojo.Level;
import com.lvq.pojo.Question;
import com.lvq.services.CategoryServices;
import com.lvq.services.LevelServices;
import com.lvq.services.QuestionServices;
import com.lvq.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuestionsController implements Initializable {
      
    
      @FXML private ComboBox<Category> cbCates; 
      @FXML private ComboBox<Level> cbLevels;
      @FXML private VBox vboxChoices;
      @FXML private TextArea txtContent;
     @FXML  private ToggleGroup ToggleChoice;
      
       private static final CategoryServices cateServices=new CategoryServices();
       private static final LevelServices levelSerVices=new LevelServices();
       private static final QuestionServices questionServices=new QuestionServices();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(cateServices.getCates()));
            this.cbLevels.setItems(FXCollections.observableList(levelSerVices.getLevels()));
        } catch (SQLException  ex) {
            ex.printStackTrace();
            }
        }
    
    public void addChoice(ActionEvent event)
    {
        HBox h=new HBox();
       h.getStyleClass().add("main");
        RadioButton rdo=new RadioButton();
        rdo.setToggleGroup(ToggleChoice);
        TextField txt=new TextField();
        h.getChildren().addAll(rdo,txt); 
       h.setAlignment(Pos.CENTER);
        
       this.vboxChoices.getChildren().add(h);
    }
    
    public void addQuesTion(ActionEvent event) throws Exception
    {
          try {
              Question.Builder b=new Question.Builder(txtContent.getText(),
                      this.cbCates.getSelectionModel().getSelectedItem(), this.cbLevels.getSelectionModel().getSelectedItem());
              for(var c: vboxChoices.getChildren())
              {
                          HBox h=(HBox) c;
                          Choice choice=new Choice(((TextField) h.getChildren().get(1)).getText(), ((RadioButton) h.getChildren().get(0)).isSelected());
                          b.addChoice(choice);
               } 
           //   Question q=b.build(); 
              questionServices.addQuestion(b.build());
              MyAlert.getInstance().showMessage("Them cau hoi thanh cong");
          } catch(SQLException ex)   {
              MyAlert.getInstance().showMessage("Themcau hoi that bai,ly do: "+ex.getMessage());
          }  catch (Exception ex) {
              MyAlert.getInstance().showMessage("Du lieu khong hop le");
          }
    }
    
}
