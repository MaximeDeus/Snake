package com.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuController {

    private App app;
    private GameLayoutController gameLayoutController;
    @FXML
    private Label lastScoreLabel;
    @FXML
    private Label bestScoreLabel;
    @FXML
    ImageView imageView;

    @FXML
    public void initialize(){
        imageView.setImage(new Image(String.valueOf(App.class.getResource("snake-logo.png"))));
    }

    public void setController (GameLayoutController controller){
        gameLayoutController = controller;
    }

    public void setApp(App application) {
        this.app = application;
    }

    @FXML
    public void handlePlay(){
        gameLayoutController.initGrid();
        gameLayoutController.initGame();
        gameLayoutController.startGame();
        app.hideMenuLayout();
    }

    public void updateLastScore(int score){
        // Not necessary to bind Game Controller score
        lastScoreLabel.textProperty().set(Integer.toString(score));
        updateBestScore(score);
    }
    public void updateBestScore(int score){
        if (Integer.valueOf(bestScoreLabel.textProperty().get()) < score){
            bestScoreLabel.textProperty().set(String.valueOf(score));
        }
    }
}
