package com.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;
    private static Scene scene;

    @Override
    public void start(Stage stage){
    this.stage = stage;
    this.stage.setTitle("Snake");
    initGameLayout();
    }

    /**
     * Initializes the game layout
     */
    public void initGameLayout(){
        try {
            // Load content of gameLayout.fxml file using his URL
            FXMLLoader loader = new FXMLLoader(App.class.getResource("gameLayout.fxml"));
            // Load Node
            AnchorPane root = loader.load();

            // Load and Give the controller access to the main app.
            GameLayoutController controller = loader.getController();
            controller.setApp(this);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getSceneWidth (){
        return scene.getWidth();
    }
    public double getSceneHeight (){
        return scene.getHeight();
    }
    public static void main(String[] args) {
        launch();
    }

}