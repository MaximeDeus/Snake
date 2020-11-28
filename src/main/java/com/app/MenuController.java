package com.app;

import javafx.fxml.FXML;

public class MenuController {

    private App app;
    private GameLayoutController gameLayoutController;

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
}
