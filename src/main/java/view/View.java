package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Deque;

public class View {
    private final Model model;

    private final GamePanel gamePanel;

    private final int height = 800;
    private final int width = 800;
    private final int scale = 15;

    private Deque<Point> snakeBody;
    private Point food;

    private JFrame frame;
    private JPanel panel;

    public View(Model model) {
        this.model = model;
        updateData();
        gamePanel = new GamePanel(width, height, scale, snakeBody, food);
        createUI();
    }

    public void createUI() {
        frame = new JFrame("Snake!");
        panel = new JPanel();
        frame.setSize(width, height);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(scale, scale, scale, scale));
        panel.add(gamePanel);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setVisible(true);
    }

    private void updateData() {
        snakeBody = model.getSnakeBody();
        food = model.getFood();
    }

    public void updateView() {
        updateData();
        gamePanel.repaint();
    }
}
