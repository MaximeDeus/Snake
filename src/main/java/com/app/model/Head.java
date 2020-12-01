package com.app.model;

import javafx.scene.paint.Color;

/**
 * Class Head
 * Represents the head of the snake
 * (Only picture vary from Point model)
 */
public class Head extends Point{

    private static Color color;
    public Head(double x, double y) {
        super(x, y);
    }

    public static void setColor (Color c){
        color = c;
    }

    public static Color getColor() {
        return color;
    }
}
