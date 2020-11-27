package com.app.model;

import com.app.App;
import javafx.scene.image.Image;

public class Food extends Point{
    public Food(double x, double y) {
        super(x, y);
        image = new Image(String.valueOf(App.class.getResource("food_apple.png")),15,15,false,false);
    }
}
