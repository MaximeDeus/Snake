package com.app.model;

import com.app.App;
import com.app.Config;
import javafx.scene.image.Image;

public class Food extends Point{
    private Image image;
    public Food(double x, double y) {
        super(x, y);
        image = new Image(String.valueOf(App.class.getResource("food_apple.png")), Config.ELEMENT_WIDTH,Config.ELEMENT_HEIGHT,false,false);
    }
    public Image getImage() {
        return image;
    }


}
