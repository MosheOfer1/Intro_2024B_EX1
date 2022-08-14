package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static List<Object> fields = new ArrayList<>();
    public static Queen[] queen = new Queen[8];
    public static int SQUARE_SIZE = 65;
    public static int solutions;
    public static JFrame frame = new JFrame();
    public static  Label l1;

    public static void main(String[] args) {
        //creating 8 queens on the board
        for (int i = 0; i < 8; i++) {
            queen[i] = new Queen(i);
            queen[i].setBounds(700,520-(i*SQUARE_SIZE), (int) (0.6*SQUARE_SIZE), (int) (0.6*SQUARE_SIZE));
            frame.add(queen[i]);
        }
        Label l = new Label("Possible solutions: ");
        l.setBounds(600,20, 150,30);
        l.setFont(new Font("ARIEL", Font.PLAIN, 18));
        frame.add(l);

        //calculate the solutions and update the label
        solutions= logic.countSolutions();
        l1 = new Label(String.valueOf(solutions));
        l1.setBounds(750,20, 25,30);
        l1.setForeground(new Color(238, 52, 52));
        l1.setFont(new Font("ARIEL", Font.BOLD, 18));
        frame.add(l1);

        //creating the board with 64 JButtons
        int fieldCounter=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                fields.add(fieldCounter,new JButton());
                ((JButton) fields.get(fieldCounter)).setBounds(50 + (SQUARE_SIZE *j),50 + (SQUARE_SIZE * i),SQUARE_SIZE,SQUARE_SIZE);
                if ((i+j)%2==0)
                    ((JButton) fields.get(fieldCounter)).setBackground(new Color(152,102,0));
                else
                    ((JButton) fields.get(fieldCounter)).setBackground(Color.white);

                frame.add((Component) fields.get(fieldCounter));
                ((JButton) fields.get(fieldCounter)).setEnabled(false);
                int finalFieldCounter = fieldCounter;

                //when a queen had been pressed, if a button is pressed the queen moves to this button
                ((JButton) fields.get(fieldCounter)).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        //if one player had been pressed
                        if (Queen.queenClicked !=-1) {
                            if (Queen.emptySquare(finalFieldCounter)) {
                                queen[Queen.queenClicked].setLocation(
                                        ((JButton) main.fields.get(finalFieldCounter)).getX() + Queen.CENTER_A,
                                        ((JButton) main.fields.get(finalFieldCounter)).getY() + Queen.CENTER_A);
                                queen[Queen.queenClicked].setInSquare(finalFieldCounter);
                                int sol = logic.countSolutions();
                                l1.setText(String.valueOf(sol));
                                frame.add(main.l1);
                                frame.repaint();
                            }
                            //the image changes to the one without the green line
                            queen[Queen.queenClicked].drawQueen(Queen.queenImg);
                            Queen.queenClicked = -1;
                        }
                    }
                });
                fieldCounter++;
            }
        }

        frame.setSize(800, 650);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
