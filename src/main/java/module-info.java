module org.example.jst_lab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.jst_lab4 to javafx.fxml;
    exports org.example.jst_lab4;
}