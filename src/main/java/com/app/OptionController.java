package com.app;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;

public class OptionController {

    private App app;
    private GameLayoutController gameLayoutController;
    @FXML
    private ColorPicker headColor;
    @FXML
    private ColorPicker bodyColor;
    @FXML
    private Slider slider;

    @FXML
    public void initialize(){ }

    public void setApp(App application) {
        this.app = application;
    }

    public void setController (GameLayoutController controller){
        gameLayoutController = controller;
    }

    @FXML
    public void handleOK(){
        gameLayoutController.setHeadColor(headColor.getValue());
        gameLayoutController.setBodyColor(bodyColor.getValue());
        gameLayoutController.setSpeed(slider.getValue());
        app.loadMenuScene();
    }

    @FXML
    public void handleCancel(){
        app.loadMenuScene();
    }

}
