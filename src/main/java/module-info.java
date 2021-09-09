module com.github.lukfisz.traymenufx {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive kotlin.stdlib;
    requires transitive javafx.swing;
    requires java.desktop;

    opens com.github.lukfisz.traymenufx.core to javafx.fxml;
    exports com.github.lukfisz.traymenufx.core;
    exports com.github.lukfisz.traymenufx.menu;
}