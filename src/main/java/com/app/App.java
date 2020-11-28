package com.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage gameStage;
    private static Scene gameScene;
    private static Stage menuStage;
    private GameLayoutController gameLayoutController;
    private MenuController menuController;

    @Override
    public void start(Stage stage){
    this.gameStage = stage;
    this.gameStage.setTitle("Snake");
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
            gameLayoutController = loader.getController();
            gameLayoutController.setApp(this);

            gameScene = new Scene(root);
            gameStage.setScene(gameScene);
            gameStage.show();
            initMenuLayout();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initMenuLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("menuOverview.fxml"));
        BorderPane borderPane = loader.load();
        menuController = loader.getController();
        menuController.setController(gameLayoutController);
        menuController.setApp(this);

        menuStage = new Stage();
        Scene scene = new Scene(borderPane);
        menuStage.setScene(scene);
        menuStage.initOwner(gameStage);
        menuStage.initModality(Modality.APPLICATION_MODAL);
        showMenuLayout();
    }

    public void showMenuLayout(){
        menuStage.show();
    }
    public void hideMenuLayout(){
        menuStage.hide();
    }

    public double getSceneWidth (){
        return gameScene.getWidth();
    }
    public double getSceneHeight (){
        return gameScene.getHeight();
    }
    public static void main(String[] args) {
        launch();
    }

}