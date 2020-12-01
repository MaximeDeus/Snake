package com.app;

import com.app.model.Head;
import com.app.model.Point;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

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
    public void initialize(){
        // Load default snake colors when starting app (i.e before launching first game)
        Head.setColor(headColor.getValue());
        Point.setColor(bodyColor.getValue());
    }

    public void setApp(App application) {
        this.app = application;
    }

    public void setController (GameLayoutController controller){
        gameLayoutController = controller;
    }



    @FXML
    public void handleOK(){
        Head.setColor(headColor.getValue());
        Point.setColor(bodyColor.getValue());
        gameLayoutController.setSpeed(slider.getValue());
        app.loadMenuScene();
    }

    @FXML
    public void handleCancel(){
        app.loadMenuScene();
    }

}
