package com.app.model;

import com.app.App;
import javafx.scene.image.Image;

/**
 * Class Head
 * Represents the head of the snake
 * (Only picture vary from Point model)
 */
public class Head extends Point{
    public Head(double x, double y) {
        super(x, y);
        image = new Image(String.valueOf(App.class.getResource("snake_head.png")),20,20,false,false);

    }
}
