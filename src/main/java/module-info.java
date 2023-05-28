module workshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires org.checkerframework.checker.qual;
    requires com.google.gson;
    requires com.google.common;
    requires java.datatransfer;
    requires java.desktop;

    exports view;
    opens view to javafx.fxml;
}