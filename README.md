
# Tray icon and menu using JavaFX component
### (under developemnt)

Approach to create tray icon and popup menu using JavaFX.

Written in kotlin, works with Java 11+

Built on top of java.awt and java.swing. It allows to add tray icon and a popup  

Requires:
 - javafx.controls  
 - javafx.fxml
 - kotlin.stdlib  
 - javafx.swing;

Class with a defined style:
 - `LightTrayMenu`

![Light tray menu](https://i.ibb.co/w67Xvf1/light-menu.png)

 - `StrapTrayMenu`
 
 ![Strap tray menu](https://i.ibb.co/g9nsrCx/strap-menu.png)

The `TrayMenuFX` class can be used to embed any kind of JavaFX container along with fxml. When using `FXMLLoader` an instance of the tray menu will be injected into the field with `@TrayMenu` annotation inside the defined controller.
