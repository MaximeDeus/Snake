package com.app;

import com.app.model.Point;
import com.app.model.Snake;
import javafx.scene.canvas.GraphicsContext;

public class Tools {

    public static void drawSnake(GraphicsContext gc, Snake snake )
    {
        for (Point p : snake.getBody()) {
            gc.drawImage(p.getImage(), p.getPositionX(), p.getPositionY());
        }
    }
}
