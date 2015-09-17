import ecs100.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.*;
import java.io.*;
/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake
{
    // instance variables - replace the example below with your own
    private boolean alive;      //haven't used this yet
    private int length;         //records the current length of the snake
    private Color col;          //stores the color of the snake
    private final int width = 10;       //sets the width of the snake
    private String lastDir = "right";   //stores the last direction of the snake the snake is moving to
                                        // this is to prevent the snake going back on its self
                                        // eg. snake moving up then snake moving down
    private List<List<Integer>> points;     //stores the coordinates of the body of the snake
    private List<Integer> temp;             //temp List to store xy coordinates of snake to make it move
    private boolean eat;                //to determine if the snake is eating or not
    
    /**
     * Constructor for objects of class Snake
     */
    public Snake(Color color)
    {
        // initialise instance variables
        alive = true;
        length = 5;
        eat = false;
        col = color;
        points=new ArrayList<List<Integer>>();
        for(int l = 0; l<this.length; l++){            //this loop adds initial coordinates for the body of the
                                                       //snake. this array contains an array of xy coordinates
                                                       //for each box part of the snake
            List<Integer> xy = new ArrayList<Integer>();
            Integer x = new Integer(10*l);
            Integer y = new Integer(250);
            xy.add(x);
            xy.add(y);
            points.add(xy);
        }
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int getLength()
    {
        // put your code here
        return points.size();
    }
    
    public boolean getLife(){
        return alive;
    }
    
    public void eat(){
        this.length +=1;
        this.eat=true;
    }
    
    public void move(String dir){
        if(dir.equals("up")){
            if(lastDir.equals("down")){this.moveDown(); return;}
            this.moveUp();
            lastDir = "up";
        }
        if(dir.equals("down")){
            if(lastDir.equals("up")){this.moveUp(); return;}
            this.moveDown();
            lastDir = "down";
        }
        if(dir.equals("left")){
            if(lastDir.equals("right")){this.moveRight(); return;}
            this.moveLeft();
            lastDir = "left";
        }
        if(dir.equals("right")){
            if(lastDir.equals("left")){this.moveLeft(); return;}
            this.moveRight();
            lastDir = "right";
        }
    }
    
    public void draw(){
        UI.setColor(col);
        for(int i = 0; i<points.size(); i++){
            UI.fillRect(points.get(i).get(0).intValue()+10, points.get(i).get(1).intValue()+10, width, width);
        }
    }
    
    /*#
     * Returns an array of xy coordinate of the head of the snake. this is used
     * to check if it hits its food
     */
    public int[] front(){
        int [] xypoint = new int[2];
        int front = points.size()-1;
        int x = points.get(front).get(0).intValue();
        xypoint[0] = x;
        xypoint[1] = points.get(front).get(1).intValue();
        return xypoint;
    }
    
    /*#
     * to make the snake move:
     * (a)since coordinates are stored in a 2d arraylist, to make it move
     * use a temporary array to store 2 values(the x and y coordinate values) this must be an Integer object 
     * type array.
     * 
     * (b)store the supposed new coordinates of the head of the snake to the position it is moving by
     * getting and modifying the current head coordinates. +10 in the x value to make it go right etc etc.
     * 
     * (c)if the snake eats a food, it doesn't delete the 0th index of the 2d array to make it longer\
     * 
     * (d)then add the temporary array to the 2d array and remove the coordinates at the 0th index.
     * 
     */
    public void moveUp(){
        int front = points.size()-1;
        //temp = points.get(front);
        temp = new ArrayList<Integer>();                            //(A)
        int change = points.get(front).get(1).intValue()-10;        //(B)
        if (change<0){change = 500;}                                // to make the snake loop around its field
        temp.add(points.get(front).get(0));                     //the 0th index stores the X values
        temp.add(new Integer(change));                          //the 1st index stores the Y values
        if(!eat){points.remove(0);}                                 //(C)
        eat = false;                                                //this is to reset the state of the boolean
        points.add(this.temp);                                      //(D)
    }    
    public void moveDown(){
        int front = points.size()-1;
        //temp = points.get(front);
        temp = new ArrayList<Integer>();
        int change = points.get(front).get(1).intValue()+10;
        if (change>500){change = 0;}      
        temp.add(points.get(front).get(0));  
        temp.add(new Integer(change));       
        if(!eat){points.remove(0);}
        eat = false;
        points.add(this.temp);  
    }    
    public void moveLeft(){
        int front = points.size()-1;
        //temp = points.get(front);
        temp = new ArrayList<Integer>();  
        int change = points.get(front).get(0).intValue()-10;
        if (change<0){change = 500;}
        temp.add(new Integer(change));
        temp.add(points.get(front).get(1));      
        if(!eat){points.remove(0);}
        eat = false;
        points.add(this.temp);
    }    
    public void moveRight(){
        int front = points.size()-1;
        //temp = points.get(front);
        temp = new ArrayList<Integer>();
        int change = points.get(front).get(0).intValue()+10;
        if (change>500){change = 0;}
        temp.add(new Integer(change));
        temp.add(points.get(front).get(1));      
        if(!eat){points.remove(0);}
        eat = false;
        points.add(this.temp);
    }
}
