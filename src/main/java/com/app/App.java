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

    private Stage gameStage;
    private Stage menuOrOptionStage;
    private Scene gameScene;
    private Scene menuScene;
    private Scene optionScene;
    private GameController gameController;
    private MenuController menuController;
    private OptionController optionController;


    @Override
    public void start(Stage stage) throws IOException {
    this.gameStage = stage;
    this.gameStage.setTitle("Snake");
    initGameLayout();
    initMenuLayout();
    initOptionLayout();
    showMenuLayout();
    }

    /**
     * Initializes the game layout
     */
    public void initGameLayout(){
        try {
            // Load content of gameView.fxml file using his URL
            FXMLLoader loader = new FXMLLoader(App.class.getResource("gameView.fxml"));
            // Load Node
            AnchorPane root = loader.load();

            // Load and Give the controller access to the main app.
            gameController = loader.getController();
            gameController.setApp(this);
            gameScene = new Scene(root);
            gameStage.setScene(gameScene);
            gameStage.setResizable(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initMenuLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("menuOverview.fxml"));
        BorderPane borderPane = loader.load();
        menuController = loader.getController();
        gameController.setController(menuController);
        menuController.setController(gameController);
        menuController.setApp(this);

        menuOrOptionStage = new Stage();
        menuScene = new Scene(borderPane);
        menuOrOptionStage.setScene(menuScene);
        menuOrOptionStage.initOwner(gameStage);
        menuOrOptionStage.initModality(Modality.APPLICATION_MODAL);
        menuOrOptionStage.setResizable(false);
    }

    public void initOptionLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("optionOverview.fxml"));
        AnchorPane anchorPane = loader.load();
        optionController = loader.getController();
        optionController.setController(gameController);
        optionController.setApp(this);
        optionScene = new Scene(anchorPane);
    }

    public void loadMenuScene(){
        menuOrOptionStage.setScene(menuScene);
    }
    public void loadOptionScene(){
        menuOrOptionStage.setScene(optionScene);
    }

    public void showGameLayout(){ gameStage.show(); }
    public void hideGameLayout(){ gameStage.hide(); }

    public void showMenuLayout(){
        menuOrOptionStage.show();
    }
    public void hideMenuLayout(){
        menuOrOptionStage.hide();
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