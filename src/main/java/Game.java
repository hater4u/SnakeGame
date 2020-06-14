import controller.Controller;
import model.Model;
import view.View;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private final Model model;
    private final View view;
    private final Controller controller;

    public Game() {
        model = new Model();
        view = new View(model);
        controller = new Controller(model, view);
        model.addObserver(controller);
    }

    public void startGame() {
//        view.createUI();
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                view.updateView();
            }
        };
        timer.schedule(timerTask, 0 ,300);
        model.start();
    }
}
