package lab.modules;


import lab.interfaces.IFunc;
import lab.interfaces.ISysFunc;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import lab.func.Point;



public class GraphModule extends JPanel {
    double x1, x2, y1, y2;
    double step_x = 1;
    double step_y = 1;
    //Settings
    int WIDTH = 640;
    int HEIGHT = 480;
    int lastX = 0;
    int lastY = 0;
    //step_x = Math.PI;
    ArrayList<IFunc> f = new ArrayList<>();
    ArrayList<ISysFunc> fsys = new ArrayList<>();
    ArrayList<Point> points = new ArrayList<>();

    public GraphModule(IFunc func, Point point1, Point point2, double left, double right) {
        this.x1 = left;
        this.x2 = right;
        this.y1 = left;
        this.y2 = right;
        this.f.add(func);
        points.add(point1);
        points.add(point2);
        frameOp();
    }
    public GraphModule(IFunc func, double left, double right) {
        this.x1 = left;
        this.x2 = right;
        this.y1 = left;
        this.y2 = right;
        this.f.add(func);
        frameOp();
    }

    public GraphModule(ArrayList<IFunc> func, Point point1, Point point2, double left, double right) {
        this.x1 = left;
        this.x2 = right;
        this.y1 = left;
        this.y2 = right;
        this.f.addAll(func);
        points.add(point1);
        points.add(point2);
        frameOp();
    }

    public GraphModule(ArrayList<IFunc> func, double left, double right) {
        this.x1 = left;
        this.x2 = right;
        this.y1 = left;
        this.y2 = right;
        this.f.addAll(func);
        frameOp();
    }

    public GraphModule(IFunc func, ArrayList<Point> ar, double left, double right) {
        this.x1 = left;
        this.x2 = right;
        this.y1 = left;
        this.y2 = right;
        this.f.add(func);
        points.addAll(ar);
        frameOp();
    }

    public void frameOp() {
        JFrame JF = new JFrame("Paint");
        JF.setBounds(100, 100, WIDTH + 6, HEIGHT + 28);
        JF.setLayout(null);
//        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JF.setVisible(true);
        JF.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        JF.add(this);
        this.setBackground(Color.WHITE);

        MouseAdapter MA = new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                lastX = evt.getX();
                lastY = evt.getY();
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                lastX = evt.getX();
                lastY = evt.getY();
            }

            @Override
            public void mouseDragged(MouseEvent evt) {
                if (evt.getModifiers() == evt.BUTTON1_MASK) {
                    int newX = evt.getX();
                    int newY = evt.getY();

                    double dx = (x2 - x1) / WIDTH * (lastX - newX);
                    double dy = -(y2 - y1) / HEIGHT * (lastY - newY);

                    x1 += dx;
                    x2 += dx;

                    y1 += dy;
                    y2 += dy;

                    lastX = newX;
                    lastY = newY;

                    repaint();
                } else if (evt.getModifiers() == evt.BUTTON3_MASK) {

                    int newX = evt.getX();
                    int newY = evt.getY();

                    double dx = (x2 - x1) / WIDTH * (lastX - newX);
                    double dy = -(y2 - y1) / HEIGHT * (lastY - newY);

                    //x1 += dx;
                    x2 += dx;

                    y1 += dy;
                    //y2 += dy;

                    lastX = newX;
                    lastY = newY;

                    repaint();
                }
            }
        };

        addMouseListener(MA);
        addMouseMotionListener(MA);

        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {

                int r = e.getWheelRotation();

                double dx = (x2 - x1) / (10 + Math.abs(r + 1) / 2);
                double dy = (y2 - y1) / (10 + Math.abs(r + 1) / 2);

                x1 -= r * dx * lastX / WIDTH;
                x2 += r * dx * (WIDTH - lastX) / WIDTH;
                y1 -= r * dy * (HEIGHT - lastY) / HEIGHT;
                y2 += r * dy * lastY / HEIGHT;

                repaint();

            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        paintD(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        g.setColor(Color.RED);

        paintF(g);

    }

    public void paintD(Graphics g) {
        g.setColor(Color.GRAY);

        int OX = (int) (-x1 / (x2 - x1) * WIDTH);
        int OY = (int) (HEIGHT + y1 / (y2 - y1) * HEIGHT);

        for (int i = (int) Math.floor(x1 / step_x); i <= Math.floor(x2 / step_x); i++) {
            int positionX = (int) (-(x1 - step_x * i) / (x2 - x1) * WIDTH);

            g.drawLine(positionX, 0, positionX, HEIGHT);

            int positionY;

            if (OY < 12) {
                positionY = 10;
            } else if (OY > HEIGHT - 2) {
                positionY = HEIGHT - 2;
            } else {
                positionY = OY - 2;
            }

            int format = (int) (HEIGHT / 5 / (x2 - x1) / step_x);

            if (step_x * i == (int) (step_x * i)) {
                format = 0;
            } else if (format > 10) {
                format = 10;
            }

            g.drawString(String.format("%." + String.valueOf(format) + "f", i * step_x), positionX + 2, positionY);
        }
        for (int i = (int) Math.floor(y1 / step_y); i <= Math.floor(y2 / step_y); i++) {
            int positionY = (int) (HEIGHT + (y1 - step_y * i) / (y2 - y1) * HEIGHT);

            g.drawLine(0, positionY, WIDTH, positionY);

            int positionX;

            int format = (int) (HEIGHT / 5 / (x2 - x1) / step_y);

            if (step_y * i == (int) (step_y * i)) {
                format = 0;
            } else if (format > 10) {
                format = 10;
            }

            String formated_value = String.format("%." + String.valueOf(format) + "f", i * step_y);

            int len = (int) new TextLayout(formated_value, g.getFont(), new FontRenderContext(null, true, true)).getBounds().getWidth();

            if (OX < 1) {
                positionX = 2;
            } else if (OX > WIDTH - 7 - len) {
                positionX = WIDTH - 5 - len;
            } else {
                positionX = OX + 2;
            }

            g.drawString(formated_value, positionX, positionY - 1);
        }

        g.setColor(Color.BLACK);
        //g.drawLine((int) (-x1 / (x2 - x1) * WIDTH), 0, (int) (-x1 / (x2 - x1) * WIDTH), HEIGHT);
        //g.drawLine(0, (int) (HEIGHT + y1 / (y2 - y1) * HEIGHT), WIDTH, (int) (HEIGHT + y1 / (y2 - y1) * HEIGHT));
        g.fillRect(OX - 1, 0, 3, HEIGHT);
        g.fillRect(0, OY - 1, WIDTH, 3);

    }

    public void paintF(Graphics g) {
        //Рисуем графики
        for (IFunc f1 : f) {
            int q1 = HEIGHT - (int) Math.floor((HEIGHT / (Math.abs(y2 - y1))) * (f1.solve(x1) - y1));
            for (int i = 1; i < WIDTH; i++) {
                double i2 = f1.solve(x1 + ((Math.abs(x2 - x1)) / WIDTH) * i);
                int q2 = HEIGHT - (int) Math.floor((HEIGHT / (Math.abs(y2 - y1))) * (i2 - y1));

                g.drawLine(i - 1, q1, i, q2);

                q1 = q2;
            }
        }
        //Рисуем точки
        for(Point p : points) {
            int positionX = (int) Math.floor((WIDTH / Math.abs(x1 - x2)) * (p.getX() - x1));
            int positionY = HEIGHT - (int) Math.floor((HEIGHT / (Math.abs(y2 - y1))) * (p.getY() - y1));
            g.setColor(Color.green);
            g.fillOval(positionX - 3, positionY - 3, 6, 6);
        }
    }

}

