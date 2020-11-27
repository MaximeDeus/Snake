package com.app.model;

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


    public Snake (int size) {
    double positionX = 3;
    double positionY = 3;
    // Construct the snake body (except head)
    for (int i = 0 ; i < size -1; i++){
        body.add(new Point(positionX,positionY));
        // TODO replace these value, must be provided by external config (depend on grid size)
        positionX += 20;
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
}
