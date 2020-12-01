package com.app;

import com.app.model.Food;
import com.app.model.Head;
import com.app.model.Point;
import com.app.model.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// TODO rename to DrawTools
public class Tools {

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
    public static void clear(GraphicsContext gc, Point p )
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
}
