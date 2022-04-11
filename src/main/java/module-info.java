module com.example.findpho_adminui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.google.gson;
    requires java.sql;
    requires java.datatransfer;
    requires java.desktop;

    opens com.example.findpho_adminui to javafx.fxml, com.google.gson;
    exports com.example.findpho_adminui;
    exports com.example.findpho_adminui.controllers;
    opens com.example.findpho_adminui.controllers to com.google.gson, javafx.fxml;
    exports com.example.findpho_adminui.classes;
    opens com.example.findpho_adminui.classes to com.google.gson, javafx.fxml;
    exports com.example.findpho_adminui.api;
    opens com.example.findpho_adminui.api to com.google.gson, javafx.fxml;
}