package com.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {

    private App app;
    private GameLayoutController gameLayoutController;
    @FXML
    private Label lastScoreLabel;

    @FXML
    public void initialize(){}

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

    public void updateScore(int score){
        // Not necessary to bind Game Controller score
        lastScoreLabel.textProperty().set(Integer.toString(score));
    }
}
