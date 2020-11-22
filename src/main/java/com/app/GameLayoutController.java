package com.app;

import com.app.model.Food;
import com.app.model.Movement;
import com.app.model.Point;
import com.app.model.Snake;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class GameLayoutController {

    // Reference to the main application.
    private App app;
    private int SNAKE_SIZE = 3;
    private Snake snake;
    private Food food;
    @FXML
    private Canvas gameCanvas;
    private GraphicsContext gc;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param application
     */
    public void setApp(App application) {
        this.app = application;
}

    /**
    * TODO
    */
    @FXML
    public void initialize(){
        gc = gameCanvas.getGraphicsContext2D();
        Image grid = new Image(String.valueOf(App.class.getResource("grid_600_400.png")));
        gc.drawImage(grid,0,0);
        snake = new Snake(SNAKE_SIZE);
        Tools.drawSnake(gc,snake);
    }

    /**
     * This function creates and display the food that must be eaten
     */
    public void generateFood(){
        // TODO replace these value, must be provided by external config (depend on grid size)
        int minX = 0;
        int maxX = 30;
        int minY = 0;
        int maxY = 20;
        int step = 20;
        // Random value between 0*20, 1*20, ...20*20
        double random_doubleX = (int)(Math.random() * (maxX - minX + 1) + minX) * step;
        // Random value between 0*30, 1*30, ...20*30
        double random_doubleY = (int)(Math.random() * (maxY - minY + 1) + minY) * step;
        // Create and draw food
        food = new Food(random_doubleX,random_doubleY);
        Tools.draw(gc,food);
    }

    /**
     * Check if the snake is eating or not
     * A snake is considered as eating iff the head is on the same position as the food
     */
    public boolean isEating (){
        return snake.getHead().equals(food);
    }

    public boolean isSnakeOutOfBound (){
        double width = app.getSceneWidth();
        double height = app.getSceneHeight();
        double snakeHeadX = snake.getHead().getPositionX();
        double snakeHeadY = snake.getHead().getPositionY();

        return snakeHeadX < 0 ||
               snakeHeadX >= width ||
               snakeHeadY < 0 ||
               snakeHeadY >= height;
    }

    public boolean isSnakeCollision (){
        Point snakeHead = snake.getHead();
        for (Point p : snake.getTail()){
            if (snakeHead.equals(p)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the movement is possible
     * If not, i.e the snake hit something (a wall or himself), the game is over
     * @param move the player movement
     * @return True if the movement is possible, False otherwise
     */
    public boolean isMoveValid (Movement move){
        return !(isSnakeCollision() || isSnakeOutOfBound());
    }

    public void move (Movement move){
        if (isMoveValid(move)) {
            // moving consist to remove the last point and add one as the new snake's head
            snake.setHead(move);
            if (!isEating()) {
                snake.removeExtremity();
            }
        }
        else{
            // TODO throw game over method (clear game & display popup)
        }
    }
}
