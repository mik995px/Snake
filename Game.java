import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;
/**
 * Write a description of class Game here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game implements UIKeyListener, UIButtonListener
{
    // instance variables - replace the example below with your own
    private int[] foodxy = new int[2];          //stores the coordinate of the food
    private Color snakeCol;                     //sets the color of the two snakes (only one to confuse players! lol)
    private Snake snake;
    private Snake snake2;
    private String direction;
    private String direction2;

    /**
     * Constructor for objects of class Game
     */
    public Game(){
        // initialise instance variables
        UI.addButton("Restart", this);
        //         UI.addButton("up", this);
        //         UI.addButton("left", this);
        //         UI.addButton("down", this);
        //         UI.addButton("right", this);
        UI.setKeyListener(this);        //Listener to make use of the keyboard
        snakeCol = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
        snake = new Snake(snakeCol);    //initiate player snake 1
        snake2 = new Snake(snakeCol);   //initiate player snake 2
        direction = "right";            //set initial direction of the snake
        direction2 = "up";              //set initial direction of the snake
        this.food();                    //set initial position of food  
        UI.println("/////////////////////////////////");
        UI.println("this is my 2 player snake game");
        UI.println("player1 will use the arrow keys");
        UI.println("to move one of the snakes, and ");
        UI.println("player2 will use WSAD to move the");
        UI.println("other snake. Confuse yourself to");
        UI.println("death and have fun!!!!!!!!!!!!!!");
        UI.println("/////////////////////////////////");
        UI.println("START!!!");
        this.move();                    //initiates movement
    }

    /**
     * 
     * keyPerformed method is inherited for the key listener.
     * reads the movement of the players
     */
    public void keyPerformed(String key)
    {
        // put your code here
        //UI.drawRect(10, 10, 510, 510);  
        if(key.equals("Up")){direction2 = "up";}
        if(key.equals("Left")){direction2 = "left";}
        if(key.equals("Right")){direction2 = "right";}
        if(key.equals("Down")){direction2 = "down";} 
        if(key.equals("w")){direction = "up";}
        if(key.equals("a")){direction = "left";}
        if(key.equals("d")){direction = "right";}
        if(key.equals("s")){direction = "down";}    
        //UI.println(key);

    }

    /**
     * buttonPerformed is inherited for the ButtonListener
     */
    public void buttonPerformed(String button)
    {
        // put your code here
        if(button.equals("Restart")){
            snakeCol = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
            snake = new Snake(snakeCol);
            snake2 = new Snake(snakeCol);
            direction = "right";            //set initial direction of the snake
            direction2 = "up";              //set initial direction of the snake
            UI.println("start agian!");
        }
        //         if(button.equals("up")){direction = "up";}
        //         if(button.equals("right")){direction = "right";}
        //         if(button.equals("left")){direction = "left";}
        //         if(button.equals("down")){direction = "down";}
    }

    /**
     * Loop to initiate animation
     */
    public void move(){
        while(true){
            UI.setImmediateRepaint(false);      //setimmediaterepaint to false so that graphics repaint at the same time
            UI.clearGraphics();
            UI.setColor(Color.black);
            UI.fillRect(10, 10, 510, 510);      //fills the background of the game
            UI.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));     //sets the color of food
            UI.fillOval(this.foodxy[0]+10, this.foodxy[1]+10, 10,10);       //draws the food
            int[] snakexy = snake.front();                      //grabs the coordinates of the head of the snake and stores in an array
            int[] snake2xy= snake2.front();                     //grabs the coordinates of the head of the snake and stores in an array
            if(snakexy[0] == foodxy[0] && snakexy[1]==foodxy[1]){
                snake.eat(); 
                UI.println("snake1: " + (snake.getLength()-4)); 
                this.food();
            }    
            if(snake2xy[0] == foodxy[0] && snake2xy[1]==foodxy[1]){
                snake2.eat(); 
                UI.println("snake2: "+(snake2.getLength()-4)); 
                this.food();
            }         
            snake.move(direction);        //calls the move method to change the coordinates of the snake in its array list
            snake2.move(direction2);
            snake.draw();                   //calls the snake to be draws
            snake2.draw();
            UI.repaintGraphics();
            UI.sleep(50);
        }
    }

    /**
     * food() to make Random position for the food
     */

    public void food(){
        this.foodxy[0] = (int)(Math.random()*50)*10;
        this.foodxy[1] = (int)(Math.random()*50)*10;
        //UI.printf("x: %2d y: %2d \n",foodxy[0], foodxy[1]); 
    }

    /**
    Main Class to initiate Game
     */
    public static void main(String[] arguments){
        Game b = new Game();
    }
}
