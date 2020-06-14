package model;

import controller.Controller;
import observer.Observable;
import observer.Observer;

import java.awt.*;
import java.util.*;

public class Model implements Observable {
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private final int SCALE = 20;
    private final int MAX_X = WIDTH / SCALE;
    private final int MAX_Y = HEIGHT / SCALE;
    private int foodEaten = 0;

    private final ArrayList<Observer> observers;
    private Deque<Point> snakeBody;
    private final Set<Point> inaccessiblePos = new LinkedHashSet<>();

    private Direction direction;
    private final Random random = new Random();

    private Point food ;
    private Timer timer;
//    private Field[] field;

    public Model () {
        observers = new ArrayList<>();

        initGame();
        notifyObserver();
    }

    public void start() {
        initGame();

        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                gameStep();
            }
        };
        timer.schedule(timerTask, 0 ,300);
    }

    private void initGame() {
        snakeBody = new ArrayDeque<>();
        food = new Point();

        generateSnake();
        generateFood();

        Controller.NEWGAME = true;
    }

    private void gameStep() {
        move();
        if (Controller.GAMEOVER = true) timer.cancel();
        notifyObserver();
    }


    public void move() {
        int NextX = snakeBody.getFirst().x;
        int NextY = snakeBody.getFirst().y;

        switch (direction) {
            case UP:
                NextY -= SCALE;
                break;
            case DOWN:
                NextY += SCALE;
                break;
            case LEFT:
                NextX -= SCALE;
                break;
            case RIGHT:
                NextX += SCALE;
                break;
        }

//        for (int i = 0; i < snakeBody.size() - 1; i++) {
//            snakeBody.get(i).setLocation(snakeBody.get(i + 1));
//        }
        if (checkOverlap(NextX, NextY)) {
            Controller.GAMEOVER = true;
            direction = Direction.UP;
        }

        snakeBody.getLast().setLocation(NextX, NextY); //?
        if (snakeBody.getFirst().equals(food)) {
            snakeBody.addFirst(new Point(NextX, NextY));
            inaccessiblePos.add(snakeBody.getFirst());
            generateFood();
            foodEaten++;
        }
        else {
            snakeBody.addFirst(snakeBody.removeLast());
        }
    }

    private void generateFood() {
//        int x = 0, y = 0;
        int x = random.nextInt(MAX_X) * SCALE;
        int y = random.nextInt(MAX_Y) * SCALE;
        for (Point p: snakeBody) {
            if (p.x != x || p.y != y) break;
            x = random.nextInt(MAX_X) * SCALE;
            y = random.nextInt(MAX_Y) * SCALE;
        }
        food.setLocation(x , y);
    }

    private void generateSnake() {
        int x = WIDTH / 2;
        int y = HEIGHT / 2;
        for (int i = 0; i < 4; i++) {
            snakeBody.add(new Point(x , y - i * SCALE));
        }
    }

    private boolean checkOverlap(int NextX, int NextY) {
        return snakeBody.contains(new Point(NextX, NextY)) || (NextX < 0) || (NextX >= WIDTH) || (NextY < 0)
                || (NextY >= HEIGHT);
    }

    public void setDirection(Direction dir) {
        direction = dir;
    }

    public Deque<Point> getSnakeBody() {
        return snakeBody;
    }

    public Point getFood() {
        return food;
    }

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObserver() {
        for (Observer obs: observers) {
            obs.processingEvent();
        }
    }

}
