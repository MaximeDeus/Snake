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
    private ArrayList <Point> body = new ArrayList<>();
    private Movement direction = Movement.RIGHT;


    public Snake (int size) {
    double positionX = 0;
    double positionY = 0;
    for (int i = 0 ; i < size ; i++){
        body.add(new Point(positionX,positionY));
        // TODO replace these value, must be provided by external config (depend on grid size)
        positionX += 20;
    }
    }

    public ArrayList<Point> getBody (){
        return body;
    }

    public Point getHead () {
        return body.get(0);
    }

    public void setHead(Movement move){
        Point newHead = this.getHead();
        switch (move){
            case UP:
                newHead.setPositionY(newHead.getPositionY()-20);
                break;
            case DOWN:
                newHead.setPositionY(newHead.getPositionY()+20);
                break;
            case LEFT:
                newHead.setPositionX(newHead.getPositionX()-20);
                break;
            case RIGHT:
                newHead.setPositionX(newHead.getPositionX()+20);;
                break;
        }
        body.add(newHead);
    }

    public ArrayList<Point> getTail () {
        return (ArrayList<Point>) body.subList(1,body.size());
    }

    public Point getExtremity(){
        return body.get(body.size() - 1);
    }

    public Movement getDirection() {
        return direction;
    }

    public void setDirection(Movement direction) {
        this.direction = direction;
    }
}
