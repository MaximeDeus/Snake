package com.app;

import com.app.model.Movement;
import com.app.model.Snake;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class GameLayoutController {

    // Reference to the main application.
    private App app;
    private int SNAKE_SIZE = 3;
    private Snake snake;
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
        snake = new Snake(SNAKE_SIZE);
        gc = gameCanvas.getGraphicsContext2D();
        Image grid = new Image(String.valueOf(App.class.getResource("grid_600_400.png")));
        gc.drawImage(grid,0,0);
    }

    /**
     * Check if the snake is eating or not
     * A snake is considered as eating iff the head is on the same position as the food
     */

    public boolean isEating (Point2D p){
        // TODO add delta if equality not works
        return p.equals(snake.getHead());
    }

    public boolean isSnakeOutOfBound (){
        double width = app.getSceneWidth();
        double height = app.getSceneHeight();
        double snakeHeadX = snake.getHead().getX();
        double snakeHeadY = snake.getHead().getY();

        return snakeHeadX < 0 ||
               snakeHeadX >= width ||
               snakeHeadY < 0 ||
               snakeHeadY >= height;
    }

    public boolean isSnakeCollision (){
        return snake.getTail().contains(snake.getHead());
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

    // moving consist to remove the last point and add one as the new snake's head
    public void move (Movement move){
        ArrayList<Point2D> body = snake.getBody();
        // TODO replace line below with method snake.removeExtremity();
        body.remove(body.size()-1);
        Point2D snakeHead = snake.getHead();
        switch (move){
            case UP:
                body.add(snakeHead.add(0,-1));
                break;
            case DOWN:
                body.add(snakeHead.add(0,1));
                break;
            case LEFT:
                body.add(snakeHead.add(-1,0));
                break;
            case RIGHT:
                body.add(snakeHead.add(1,0));
                break;
        }
    }

    // TODO add eat functionality
    // TODO add food in random position

}
