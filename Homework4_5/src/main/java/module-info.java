module com.example.homework4_5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.homework4_5 to javafx.fxml;
    exports com.example.homework4_5;
}