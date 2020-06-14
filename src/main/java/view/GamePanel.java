package view;

import javax.swing.*;
import java.awt.*;
import java.util.Deque;

public class GamePanel extends JPanel {
    private final int width;
    private final int height;
    private final int scale;
    private Point food;
    private Deque<Point> snakeBody;

    private Graphics2D graphics2D;

    public GamePanel(int width, int height, int scale, Deque<Point> snBody, Point food) {
            this.width = width;
            this.height = height;
            this.scale = scale;
            this.food = food;
            this.snakeBody = snBody;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public Color getBackground() {
        return Color.black;
    }

    @Override
    public boolean isOpaque() {
        return true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintDots();
        paintApple();
        paintSnake();
    }

    public void paintApple() {
        graphics2D.setStroke(new BasicStroke(1.5f));
        int X = food.x;
        int Y = food.y;

        graphics2D.setColor(Color.red);
        graphics2D.fillOval(X + 2, Y + 2, scale - 4, scale - 3);
        graphics2D.fillOval(X + 4, Y + 2, scale - 4, scale - 3);
    }

    public void paintSnake() {
        Color snakeColor = new Color(200, 132, 42);
        int X, Y;
        for (Point position : snakeBody) {
            graphics2D.setColor(snakeColor);
            X = position.x;
            Y = position.y;
            graphics2D.drawRoundRect(X + 2, Y + 2, scale - 4, scale - 4, 2, 2);
        }
    }

    public void paintDots() {
        graphics2D.setStroke(new BasicStroke(0.2f));
        graphics2D.setColor(Color.gray);
        for (int i = 0; i <= width / scale; i++) {
            for (int j = 0; j <= height / scale; j++) {
                if (i * scale == width) {
                    graphics2D.fillRect(i * scale - 1, j * scale, 1, 1);
                }
                else if (j * scale == height) {
                    graphics2D.fillRect(i * scale, j * scale - 1, 1, 1);
                }
                else {
                    graphics2D.fillRect(i * scale, j * scale, 1, 1);
                }
            }
        }
        graphics2D.fillRect(width - 1, height - 1, 1, 1);
    }
}
