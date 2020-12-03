package com.app;

import com.app.model.Food;
import com.app.model.Head;
import com.app.model.Point;
import com.app.model.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

// TODO rename to DrawTools
public class DrawTools {

    public static void drawPoint(GraphicsContext gc, Point p)
    {
        gc.setFill(Point.getColor());
        // TODO replace values (cf config)
        gc.fillRect(p.getPositionX(), p.getPositionY(), 15,15);
    }

    public static void drawHead(GraphicsContext gc, Point p)
    {
        gc.setFill(Head.getColor());
        // TODO replace values (cf config)
        gc.fillRect(p.getPositionX(), p.getPositionY(), 15,15);
    }

    public static void drawFood(GraphicsContext gc, Food f)
    {
        gc.drawImage(f.getImage(), f.getPositionX(), f.getPositionY());
    }

    // TODO rename to clearPoint
    public static void clearPoint(GraphicsContext gc, Point p )
    {
        // TODO replace values (cf config)
        gc.clearRect(p.getPositionX(), p.getPositionY(), 15,15);
    }

    // TODO add method drawGrid/Clear grid

    public static void drawSnake(GraphicsContext gc, Snake snake )
    {
        for (Point p : snake.getTail()) {
            drawPoint(gc,p);
        }
        drawHead(gc,snake.getHead());
    }

    public static void drawGrid(GraphicsContext gc, String color){
        // TODO replace height/width by config values
        clear(gc);
        String gridColor = "";
        switch (color){
            case "Dark":
                gridColor = "dark_grid_600_400.png";
                break;
            case "White":
                gridColor = "white_grid_600_400.png";
                break;
        }
        Image grid = new Image(String.valueOf(App.class.getResource(gridColor)));
        gc.drawImage(grid,0,0);
    }

    public static void clear(GraphicsContext gc) {
        gc.clearRect(0,0,600,400);
    }
}
