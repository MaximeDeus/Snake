package com.app.controllers;

import com.app.App;
import com.app.controllers.GameController;
import com.app.model.Head;
import com.app.model.Point;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OptionController {

    private App app;
    private GameController gameController;
    @FXML
    private ColorPicker headColor;
    @FXML
    private ColorPicker bodyColor;
    @FXML
    private Slider slider;
    @FXML
    private ToggleGroup gridToggleGroup;

    @FXML
    public void initialize(){
        // Load default snake colors when starting app (i.e before launching first game)
        Head.setColor(headColor.getValue());
        Point.setColor(bodyColor.getValue());
    }

    public void setApp(App application) {
        this.app = application;
    }

    public void setController (GameController controller){
        gameController = controller;
    }



    @FXML
    public void handleOK(){
        Head.setColor(headColor.getValue());
        Point.setColor(bodyColor.getValue());
        gameController.setSpeed(slider.getValue());
        RadioButton radiobutton = (RadioButton) gridToggleGroup.getSelectedToggle();
        gameController.setGridColor(radiobutton.getText());
        app.loadMenuScene();
    }

    @FXML
    public void handleCancel(){
        app.loadMenuScene();
    }

}
