module com.example.todowithsql2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.javanoo6.todowithsql2 to javafx.fxml;
    exports com.javanoo6.todowithsql2;
}