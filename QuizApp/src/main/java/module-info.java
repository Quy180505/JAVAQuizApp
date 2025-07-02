module com.lvq.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
   requires  java.sql;
   requires lombok;
    opens com.lvq.quizapp to javafx.fxml;
    exports com.lvq.quizapp;
}
