package gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Calculator {
    private JPanel testView;
    private JTextField OUT;
    private JButton n2;
    private JButton n1;
    private JButton n3;
    private JButton n4;
    private JButton n5;
    private JButton n6;
    private JButton n7;
    private JButton n8;
    private JButton n9;
    private JButton n0;
    private JButton oDot;
    private JButton oNeg;
    private JButton oDiv;
    private JButton oMult;
    private JButton oSub;
    private JButton oAdd;
    private JButton oEqual;
    private JButton oDel;
    private JButton oClear;

    private double oldValue = 0;

//      0: None
//      1: Addition
//      2: Subtraction
//      3: Multiplication
//      4: Division
    private char currentOp = ' ';

    private boolean shouldEmpty = false;

    public Calculator() {
        oAdd.addActionListener(opListener('+'));
        oSub.addActionListener(opListener('-'));
        oDiv.addActionListener(opListener('/'));
        oMult.addActionListener(opListener('*'));

        oEqual.addActionListener(e -> {
            calc();
            currentOp = ' ';
        });

        oDel.addActionListener(e -> {
            String current = OUT.getText();
            current = current.substring(0, current.length() - 1);
            OUT.setText(current);
        });

        oClear.addActionListener(e -> {
            OUT.setText("0");
            oldValue = 0;
            currentOp = ' ';
        });

        oNeg.addActionListener(numberAdd("-"));
        oDot.addActionListener(numberAdd("."));

        n1.addActionListener(numberAdd("1"));
        n2.addActionListener(numberAdd("2"));
        n3.addActionListener(numberAdd("3"));
        n4.addActionListener(numberAdd("4"));
        n5.addActionListener(numberAdd("5"));
        n6.addActionListener(numberAdd("6"));
        n7.addActionListener(numberAdd("7"));
        n8.addActionListener(numberAdd("8"));
        n9.addActionListener(numberAdd("9"));
        n0.addActionListener(numberAdd("0"));
    }

    public ActionListener numberAdd(String number) {
        return e -> {
            String current = OUT.getText();
            if (current.equals("0") || shouldEmpty) {
                shouldEmpty = false;
                OUT.setText(number);
            } else {
                OUT.setText(current+number);
            }
        };
    }

    public ActionListener opListener(char op) {
        return e -> {
            calc();
            oldValue = Double.parseDouble(OUT.getText());
            shouldEmpty = true;
            currentOp = op;
        };
    }


    public void calc() {
        if (currentOp == ' ') {
            System.out.println("No Op");
            return;
        }

        double current = Double.parseDouble(OUT.getText());

        switch (currentOp) {
            case '+' -> {
                OUT.setText(Double.toString(oldValue + current));
                System.out.println("add");
            }
            case '-' -> {
                OUT.setText(Double.toString(oldValue - current));
                System.out.println("neg");
            }
            case '*' -> {
                OUT.setText(Double.toString(oldValue * current));
                System.out.println("mult");
            }
            case '/' -> {
                OUT.setText(Double.toString(oldValue / current));
                System.out.println("div");
            }
            default -> {
            }
        }
        System.out.println("Current:" + current);
        System.out.println("Old:" + oldValue);
        currentOp = ' ';

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.setContentPane(new Calculator().testView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
