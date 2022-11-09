module com.example.minecraftserverscontrolpanel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    requires com.opencsv;
    requires org.apache.commons.io;
    requires net.dv8tion.jda;

    opens com.example.minecraftserverscontrolpanel to javafx.fxml;
    exports com.example.minecraftserverscontrolpanel;
    exports com.example.minecraftserverscontrolpanel.file;
    opens com.example.minecraftserverscontrolpanel.file to javafx.fxml;
    exports com.example.minecraftserverscontrolpanel.controller;
    opens com.example.minecraftserverscontrolpanel.controller to javafx.fxml;
    exports com.example.minecraftserverscontrolpanel.model;
    opens com.example.minecraftserverscontrolpanel.model to javafx.fxml;
    exports com.example.minecraftserverscontrolpanel.mcserver;
    opens com.example.minecraftserverscontrolpanel.mcserver to javafx.fxml;
}