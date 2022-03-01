module com.example.findpho_adminui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires com.google.gson;

    opens com.example.findpho_adminui to javafx.fxml;
    exports com.example.findpho_adminui;
    exports com.example.findpho_adminui.controllers;
    opens com.example.findpho_adminui.controllers to javafx.fxml;
    exports com.example.findpho_adminui.classes;
    opens com.example.findpho_adminui.classes to javafx.fxml;
}