package lab.modules;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class GraphModule {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                MyFrame frm = new MyFrame();
                frm.setVisible(true);
            }
        });
    }
}



class MyFrame extends JFrame{
    JPanel pan = new JPanel();
    PaintComp component;
    JButton but;
    JTextField tf_start;
    JTextField tf_end;

    MyFrame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400,200,700,500);
        component = new PaintComp();
        tf_start = new JTextField("50",5);
        tf_end = new JTextField("50",5);
        but = new JButton("Построить");


        pan.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan.add(tf_start);
        pan.add(tf_end);
        pan.add(but);

        add(component, BorderLayout.CENTER);
        add(pan,BorderLayout.SOUTH);

        // Событие кнопки
        but.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                component.start=Double.parseDouble(tf_start.getText()); // первое значение из текстового поля (левое)
                component.end=Double.parseDouble(tf_end.getText()); // второе значение из текстового поля (правое)
                component.repaint();
            }
        });

    }
}


// Компонент, в котором мы рисуем
class PaintComp extends JComponent{
    double x,y,x1,y1;
    double ox=350,oy=0; // ось симметрии параболы
    double start=50,end=50; // интервалы
    Graphics2D gr;
    public void paintComponent(Graphics g){
        gr = (Graphics2D) g;

        // Делаем белый фон
        Rectangle2D rect = new Rectangle2D.Double(0,0,this.getWidth(),this.getHeight());
        gr.setPaint(Color.white);
        gr.fill(rect);
        gr.draw(rect);

        // Рисуем сетку
        gr.setPaint(Color.LIGHT_GRAY);
        gr.setStroke(new BasicStroke((float) 0.2));
        for(y=0;y<=this.getWidth();y+=50){
            gr.draw(new Line2D.Double(0,y  ,this.getWidth(),y));
            gr.draw(new Line2D.Double(y,0,y,this.getHeight()));
        }
        // Рисуем оси
        gr.setPaint(Color.GREEN);
        gr.setStroke(new BasicStroke((float) 1));
        gr.draw(new Line2D.Double(ox,0,ox,this.getHeight()));
        gr.draw(new Line2D.Double(0,oy,this.getWidth(),oy));

        // Рисуем отрезок интервала
        gr.setPaint(Color.RED);
        gr.setStroke(new BasicStroke((float) 5));
        gr.draw(new Line2D.Double(ox-Math.abs(start),oy,ox+Math.abs(end),oy));

        // Рисуем параболу
        gr.setPaint(Color.BLACK);
        gr.setStroke(new BasicStroke((float) 2));

        for(x=0;x<=Math.abs(end);x++){ // левая ветвь
            y=0.01*x*x;
            x1=x-1;
            y1=0.01*x1*x1;
            gr.draw(new Line2D.Double(x+ox,y,x1+ox,y1));
        }

        for(x=0;x<=Math.abs(start);x++){ // правая ветвь
            y=0.01*x*x;
            x1=x-1;
            y1=0.01*x1*x1;
            gr.draw(new Line2D.Double(ox-x,y,ox-x1,y1));
        }
    }
}
