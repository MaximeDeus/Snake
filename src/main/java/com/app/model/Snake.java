package com.app.model;

import javafx.geometry.Point2D;

import java.util.ArrayList;

/**
 * Model class for a Snake
 *
 * @author Maxime DEROISSART
 */
public class Snake {

    // ATTRIBUTES
    private ArrayList <Point2D> body = new ArrayList<>();
    private Movement direction = Movement.RIGHT;


    public Snake (int size) {
    for (int x = 0 ; x < size ; x++){
        body.add(new Point2D(x,0));
    }
    }

    public ArrayList<Point2D> getBody (){
        return body;
    }

    public Point2D getHead () {
        return body.get(0);
    }

    public ArrayList<Point2D> getTail () {
        return (ArrayList<Point2D>) body.subList(1,body.size());
    }

    public Point2D getExtremity(){
        return body.get(body.size() - 1);
    }

    public Movement getDirection() {
        return direction;
    }

    public void setDirection(Movement direction) {
        this.direction = direction;
    }
}
