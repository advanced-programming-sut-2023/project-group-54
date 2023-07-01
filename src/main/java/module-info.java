module com.ap.stronghold {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.checkerframework.checker.qual;
    requires com.google.gson;
    requires com.google.common;
    requires java.datatransfer;
    requires java.desktop;

    exports com.ap.stronghold.view;
    opens com.ap.stronghold.view to javafx.fxml;

    exports com.ap.stronghold.model;
    opens com.ap.stronghold.model to com.google.gson;
    exports com.ap.stronghold.model.Buildings;
    opens com.ap.stronghold.model.Buildings to com.google.gson;
    exports com.ap.stronghold.model.units;
    opens com.ap.stronghold.model.units to com.google.gson;
    exports com.ap.stronghold.model.chat;
    opens com.ap.stronghold.model.chat to com.google.gson;
}