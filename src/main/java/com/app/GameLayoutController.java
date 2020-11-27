package com.app;

import com.app.model.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.apache.commons.lang3.EnumUtils;

public class GameLayoutController {

    // Reference to the main application.
    private App app;
    private int SNAKE_SIZE = 3;
    private Snake snake;
    private Food food;
    private AnimationTimer game;
    @FXML
    private Canvas gameCanvas;
    private GraphicsContext gc;
    // Only 1 direction update is allowed for 1 frame
    private boolean hasDirectionChanged = false;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param application
     */
    public void setApp(App application) {
        this.app = application;
}
public void initKeyEvent() {
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(
                // Anonymous EventHandler
                keyEvent -> {
                    String keyValue = keyEvent.getCode().toString();
                    // Check if the input is valid (otherwise error will be thrown)
                    if (EnumUtils.isValidEnum(Direction.class, keyValue)){
                    Direction direction = Direction.valueOf(keyValue);
                    if (isValidDirection(direction) && !hasDirectionChanged) {
                        snake.setDirection(Direction.valueOf(keyEvent.getCode().toString()));
                        hasDirectionChanged = true;
                    }
                    }
                });
}
    public boolean isValidDirection (Direction direction){
        Direction snakeDirection = snake.getDirection();
        boolean res = false;
        switch (direction){
            // If UP or DOWN, only horizontal directions are allowed
            case UP:
            case DOWN:
                res = snakeDirection.equals(Direction.LEFT) || snakeDirection.equals(Direction.RIGHT);
                break;
            // If LEFT or RIGHT, only vertical directions are allowed
            case LEFT:
            case RIGHT:
                res = snakeDirection.equals(Direction.UP) || snakeDirection.equals(Direction.DOWN);
                break;
        }
        return res;
    }
public void initGame() {
    game = new AnimationTimer() {

        private long lastUpdate = 0;

        @Override
        public void handle(long now) {
            // update every second
            double seconds = (double) (now - lastUpdate) / 1_000_000_000.0;
            if (seconds >= 0.1 ) {
                move();
                lastUpdate = now;
                hasDirectionChanged = false;
            }
        }
    };
}

public void startGame(){
        game.start();
}
    public void stopGame(){
        game.stop();
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
        generateFood();
        initKeyEvent();
        initGame();
        startGame();
    }

    /**
     * This function creates and display the food that must be eaten
     */
    public void generateFood(){
        // TODO replace these value, must be provided by external config (depend on grid size)
        int minX = 0;
        int maxX = 29;
        int minY = 0;
        int maxY = 19;
        int step = 20;
        // Create food until it is in a different place as the snake or if food has not been generated yet
        while(food == null || isFoodOnTheSamePlaceAsTheSnake()) {
            // TODO replace + 3 with + MARGIN
            // Random value between 0*20, 1*20, ...29*20
            double random_doubleX = (int) (Math.random() * (maxX - minX + 1) + minX) * step + 3;
            // Random value between 0*20, 1*20, ...19*20
            double random_doubleY = (int)(Math.random() * (maxY - minY + 1) + minY) * step + 3;

            food = new Food(random_doubleX, random_doubleY);
        }
        Tools.draw(gc,food);

    }

    /**
     * Check if the snake is eating or not
     * A snake is considered as eating iff the head is on the same position as the food
     */
    public boolean isEating (){
        return snake.getHead().equals(food);
    }
    public boolean isFoodOnTheSamePlaceAsTheSnake(){
        boolean res = false;
        for (Point p : snake.getBody()){
            if (food.equals(p)){
                res = true;
            }
        }
        return res;
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
     * Check if the move is possible
     * If not, i.e the snake hit something (a wall or himself), the game is over
     * @return True if the move is possible, False otherwise
     */
    public boolean isMoveValid (){
        return !(isSnakeCollision() || isSnakeOutOfBound());
    }

    /** a move consist to :
     * Move the head in function of current snake Direction
     * Replace by a Point the old head position (+1)
     * If snake is not eating, remove the last point (-1)
     */
    public void move (){
        // Update data structure
        Point head = snake.getHead();
        // Create a clone of the head, for clearing old head (*)
        Head headCopy = new Head(head.getPositionX(), head.getPositionY());
        Point point = new Point(head.getPositionX(), head.getPositionY());
        snake.getBody().add(snake.getBody().size()-1, point); // TODO in snake model create method insertPointAfterSnakeHead returning the inserted Point
        switch (snake.getDirection()){ // TODO replace 20 value (cf config)
            case UP:
                head.setPositionY(head.getPositionY()-20);
                break;
            case DOWN:
                head.setPositionY(head.getPositionY()+20);
                break;
            case LEFT:
                head.setPositionX(head.getPositionX()-20);
                break;
            case RIGHT:
                head.setPositionX(head.getPositionX()+20);
                break;
        }
        // Check if move is valid before drawing move (otherwise game over)
        if (isMoveValid()) {
            // Clear old head (*) picture before replacing with a Point (because they may not have the same size)
            Tools.clear(gc,headCopy);
            Tools.draw(gc,point);
            Tools.draw(gc,head);
            // Food not found
            if (!isEating()) {
                Tools.clear(gc, snake.getExtremity());
                snake.removeExtremity();
                }
            // If the snake found food, generates a new one instead of remove his extremity
            else{
                generateFood();
                }
        }
        // Game over
        else{
            stopGame();
        }
    }
}
