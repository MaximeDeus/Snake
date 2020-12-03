package com.app;

import com.app.model.*;
import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import org.apache.commons.lang3.EnumUtils;

public class GameController {

    // Reference to the main application.
    private App app;
    private Snake snake;
    private Food food;
    private AnimationTimer game;
    @FXML
    private Canvas gameCanvas;
    @FXML
    private Canvas gridCanvas;
    @FXML
    private Label scoreLabel;
    private IntegerProperty score = new SimpleIntegerProperty();
    private GraphicsContext gc;
    // Only 1 direction update is allowed for 1 frame
    private boolean hasDirectionChanged = false;
    private MenuController menuController;
    private double speed = 0.12; // default speed value
    private String gridColor = "White"; // default grid value

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param application
     */
    public void setApp(App application) {
        this.app = application;
}
    public void setController (MenuController controller) { menuController = controller; }
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

public void initGrid(){
    DrawTools.drawGrid(gridCanvas.getGraphicsContext2D(), gridColor);
}
public void initGame() {
        score.set(0);
        food = null;
        scoreLabel.textProperty().bind(score.asString());
        snake = new Snake();
        // Clear previous game
        DrawTools.clear(gc);
        DrawTools.drawSnake(gc,snake);
        generateFood();

        game = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                double elapsedTimeInSeconds = (double) (now - lastUpdate) / 1_000_000_000.0;
                if (elapsedTimeInSeconds >= speed ) {
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

    @FXML
    public void initialize(){
        gc = gameCanvas.getGraphicsContext2D();
        initGrid();
        initKeyEvent();
    }

    public void incrementScore(){
        score.set(score.get()+1);
    }

    /**
     * This function creates and display the food that must be eaten
     */
    public void generateFood(){
        int minX = 0;
        int maxX =(int) (Config.STAGE_WIDTH / Config.ELEMENT_SPACING) - 1;
        int minY = 0;
        int maxY = (int) (Config.STAGE_HEIGHT / Config.ELEMENT_SPACING) - 1;;
        int step = Config.ELEMENT_SPACING;
        int margin = 3;
        // Create food until it is in a different place as the snake or if food has not been generated yet
        while(food == null || isFoodOnTheSamePlaceAsTheSnake()) {
            // Random value between 0*20, 1*20, ...29*20
            double random_doubleX = (int) (Math.random() * (maxX - minX + 1) + minX) * step + margin;
            // Random value between 0*20, 1*20, ...19*20
            double random_doubleY = (int)(Math.random() * (maxY - minY + 1) + minY) * step + margin;

            food = new Food(random_doubleX, random_doubleY);
        }
        DrawTools.drawFood(gc,food);

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
        double width = Config.STAGE_WIDTH;
        double height = Config.STAGE_HEIGHT;
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
        snake.insertPointAfterSnakeHead(point);
        switch (snake.getDirection()){
            case UP:
                head.setPositionY(head.getPositionY()-Config.ELEMENT_SPACING);
                break;
            case DOWN:
                head.setPositionY(head.getPositionY()+Config.ELEMENT_SPACING);
                break;
            case LEFT:
                head.setPositionX(head.getPositionX()-Config.ELEMENT_SPACING);
                break;
            case RIGHT:
                head.setPositionX(head.getPositionX()+Config.ELEMENT_SPACING);
                break;
        }
        // Check if move is valid before drawing move (otherwise game over)
        if (isMoveValid()) {
            // Clear old head (*) picture before replacing with a Point (because they may not have the same size)
            DrawTools.clearPoint(gc,headCopy);
            DrawTools.drawPoint(gc,point);
            DrawTools.drawHead(gc,head);
            // Food not found
            if (!isEating()) {
                DrawTools.clearPoint(gc, snake.getExtremity());
                snake.removeExtremity();
                }
            // If the snake found food, increment score and generates a new one instead of remove his extremity
            else{
                incrementScore();
                generateFood();
                }
        }
        // Game over
        else{
            stopGame();
            menuController.updateLastScore(score.get());
            app.hideGameLayout();
            app.showMenuLayout();
        }
    }

    public void setSpeed(double s) {
        // 0.2, 0.18... 0.02 second for each frame (5 to 50 frames per second)
        speed = 0.22 - s*2/100;
    }

    public void setGridColor(String gridColor) {
        this.gridColor = gridColor;
    }
}
