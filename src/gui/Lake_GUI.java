package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;


/**
 * Created by jyheo on 2016-05-17.
 */


abstract class MyObject extends JButton {
    protected String name;
    protected String shape;
    protected int x, y;
    public MyObject(String name, String shape, int x, int y) {
        super(shape);
        setLocation(x, y);
        setSize(70, 30);
        setVisible(true);
        this.name = name;
        this.shape = shape;
        //this.x = x;
        //this.y = y;
    }


    public void new_move(int width, int height) {};

    public abstract void setvelocity(int i, int i1);
}


abstract class MyRock extends MyObject {
    public MyRock(String name, String shape, int x, int y) {
        super(name, shape, x, y);
    }
}


abstract class MyFish extends MyObject {
    private int velocity_x = 10;
    private int velocity_y = 10;

    public MyFish(String name, String shape, int x, int y) {
        super(name, shape, x, y);
    }

    public void setVelocity (int x, int y) {
        velocity_x=x;
        velocity_y=y;
    }

    public void new_move(int width, int height) {
        int x = getX();
        int y = getY();// getX(), getY()


        double rand = Math.random();
        if (rand < 0.5)
            x += velocity_x;
        else
            y += velocity_y;
        if (x + getWidth() >= width) {
            x = width - getWidth();
            velocity_x = -velocity_x;
        } else if (x <= 0) {
            x = 0;
            velocity_x = -velocity_x;
        }

        if (y +getHeight() >= height)
            y = height - getHeight();
        velocity_y = -velocity_y;
    }
    //setLocation(x, y);
}



public class Lake_GUI extends JFrame {
    private int width;
    private int height;
    private ArrayList<MyObject> objects = new ArrayList<>();


    public Lake_GUI(int width, int height) {
        setTitle("Lake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setVisible(true);

    }


    public void addMyObject(MyObject obj) {
        objects.add(obj);
        getContentPane().add(obj);
    }


    public void moveObjects() {
        int width = (int) getContnetPane().getWidth();
        int height = (int) getContnetPane().getHeight();
        //for (MyObject obj : objects) {
        //    obj.new_move(width, height);
        //}


        // getContentPane().getWidth(), getHeight()

        for (Component c : getContentPane().getComponents()) {
            if(c instanceof MyObject){
                ((MyObject)c).new_move(width, height);
            }
            //}
        }

    private RectangularShape getContnetPane() {
        return null;
    }

    public static void main(String args[]) throws InterruptedException {
        Lake_GUI lake = new Lake_GUI(320, 240);
        MyFish f = new MyFish("FIsh", "<#--<", 20, 20) {
            @Override
            public void setvelocity(int i, int i1) {
            }
        };
        lake.addMyObject(f);
        lake.addMyObject(new MyRock("Rock", "(##)", 100, 100) {
            @Override
            public void setvelocity(int i, int i1) {
            }
        });
        MyFish f2 = new MyFish("FIsh", "<#--<", 50, 50) {
            @Override
            public void setvelocity(int i, int i1) {
            }
        };
        f2.setvelocity(5,5);
        lake.addMyObject(f2);

        while (true) {
            lake.moveObjects();
            Thread.sleep(100);
        }
    }
}
