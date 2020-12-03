package com.app.model;

import com.app.Config;

import java.util.ArrayList;

/**
 * Model class for a Snake
 *
 * @author Maxime DEROISSART
 */
public class Snake {

    // ATTRIBUTES
    private ArrayList <Point> body = new ArrayList<>();
    private Direction direction = Direction.RIGHT;


    public Snake () {
    double positionX = Config.ELEMENT_MARGIN;
    double positionY = Config.ELEMENT_MARGIN;
    // Construct the snake body (except head)
    for (int i = 0 ; i < 3; i++){
        body.add(new Point(positionX,positionY));
        positionX += Config.ELEMENT_SPACING;
    }
    // Construct the snake head
    body.add(new Head(positionX,positionY));
    }

    public ArrayList<Point> getBody (){
        return body;
    }

    public Point getHead () {
        return body.get(body.size()-1);
    }
    public Point getExtremity () {
        return body.get(0);
    }

    public ArrayList<Point> getTail () {
        return new ArrayList (body.subList(0,body.size()-1));
    }

    public void removeExtremity(){
        body.remove(0);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Direction getDirection() {
        return direction;
    }

    public void insertPointAfterSnakeHead(Point point) {
        this.body.add(this.body.size()-1, point);
    }
}
