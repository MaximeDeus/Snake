package com.app;

import com.app.model.Point;
import com.app.model.Snake;
import javafx.scene.canvas.GraphicsContext;

// TODO rename to DrawTools
public class Tools {

    // TODO rename to DrawPoint
    public static void draw(GraphicsContext gc, Point p )
    {
        gc.drawImage(p.getImage(), p.getPositionX(), p.getPositionY());
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
        for (Point p : snake.getBody()) {
            draw(gc,p);
        }
    }
}
