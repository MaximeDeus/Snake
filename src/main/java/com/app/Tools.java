package com.app;

import com.app.model.Point;
import com.app.model.Snake;
import javafx.scene.canvas.GraphicsContext;

public class Tools {

    public static void draw(GraphicsContext gc, Point p )
    {
        gc.drawImage(p.getImage(), p.getPositionX(), p.getPositionY());
    }

    public static void clear(GraphicsContext gc, Point p )
    {
        // TODO replace values (cf config)
        gc.clearRect(p.getPositionX(), p.getPositionY(), 15,15);
    }

    public static void drawSnake(GraphicsContext gc, Snake snake )
    {
        for (Point p : snake.getBody()) {
            draw(gc,p);
        }
    }
}
