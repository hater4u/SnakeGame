package controller;

import model.Direction;
import model.Model;
import observer.Observer;
import view.View;

import java.awt.event.KeyEvent;


public class Controller implements Observer {
    public static boolean NEWGAME = true;
    public static boolean GAMEOVER = false;
    private final Model model;
    private final View view;

    private KeyEvent keyEvent;

    private Direction direction;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

//        inputDirection();
        direction = Direction.DOWN;
        model.setDirection(direction);
    }

    private void inputDirection() {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_UP:
                direction =  Direction.UP;
            case KeyEvent.VK_DOWN:
                direction =  Direction.DOWN;
            case KeyEvent.VK_LEFT:
                direction =  Direction.LEFT;
            case KeyEvent.VK_RIGHT:
                direction =  Direction.RIGHT;
        }
    }

    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_UP) {
            model.setDirection(Direction.UP);
        }
        if (key.getKeyCode() == KeyEvent.VK_DOWN) {
            model.setDirection(Direction.DOWN);
        }
        if (key.getKeyCode() == KeyEvent.VK_LEFT) {
            model.setDirection(Direction.LEFT);
        }
        if (key.getKeyCode() == KeyEvent.VK_RIGHT) {
            model.setDirection(Direction.RIGHT);
        }

    }

    @Override
    public void processingEvent() {
//        if (NEWGAME) view.createUI();
    }
}
