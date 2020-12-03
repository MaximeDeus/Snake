package com.app.controllers;

import com.app.App;
import com.app.controllers.GameController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuController {

    private App app;
    private GameController gameController;
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

    public void setController (GameController controller){
        gameController = controller;
    }

    public void setApp(App application) {
        this.app = application;
    }

    @FXML
    public void handlePlay(){
        gameController.initGrid();
        gameController.initGame();
        gameController.startGame();
        app.showGameLayout();
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

    @FXML
    public void handleOptions(){
        app.loadOptionScene();
    }
}
