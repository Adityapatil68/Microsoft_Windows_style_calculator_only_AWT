import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



import java.awt.*;
        import java.awt.event.*;

class Microsoft_Windows_style_calculator_only_AWT extends WindowAdapter implements ActionListener {
    Frame f;
    TextField TextField1, TextField2;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
    Button badd, bsub, bmult, bdiv, bmod, bcalc, bclr, bpts, bpm, bback, cancle;
    Button bMC, bMR, MemP, MemM, bms, bMem, bfac, bsq, bexp;

    double num1, num2, xd, memoryValue = 0;
    int check;

    Microsoft_Windows_style_calculator_only_AWT() {
        f = new Frame("AWT CALCULATOR");

        // --- DISPLAYS ---
        TextField2 = new TextField();
        TextField2.setEditable(false);
        TextField2.setBackground(Color.LIGHT_GRAY);
        TextField2.setBounds(50, 50, 260, 40);

        TextField1 = new TextField("0");
        TextField1.setEditable(false);
        TextField1.setBackground(Color.WHITE);
        TextField1.setBounds(50, 100, 260, 60);
        TextField1.setFont(new Font("Arial", Font.BOLD, 22));

        // --- BUTTONS ---
        bMC = new Button("MC");
        bMR = new Button("MR");
        MemP = new Button("M+");
        MemM = new Button("M-");
        bms = new Button("MS");
        bMem = new Button("M");
        bMC.setBounds(50, 170, 40, 35);
        bMR.setBounds(95, 170, 40, 35);
        MemP.setBounds(138, 170, 40, 35);
        MemM.setBounds(181, 170, 40, 35);
        bms.setBounds(224, 170, 40, 35);
        bMem.setBounds(270, 170, 40, 35);

        bmod = new Button("%");
        bclr = new Button("CE");
        cancle = new Button("C");
        bback = new Button("back");
        bmod.setBounds(50, 215, 55, 50);
        bclr.setBounds(111, 215, 55, 50);
        cancle.setBounds(172, 215, 55, 50);
        bback.setBounds(233, 215, 77, 50);

        bfac = new Button("1/x");
        bsq = new Button("x^2");
        bexp = new Button("sqrt");
        bdiv = new Button("/");
        bfac.setBounds(50, 270, 55, 50);
        bsq.setBounds(111, 270, 55, 50);
        bexp.setBounds(172, 270, 55, 50);
        bdiv.setBounds(233, 270, 77, 50);

        b7 = new Button("7");
        b8 = new Button("8");
        b9 = new Button("9");
        bmult = new Button("*");
        b7.setBounds(50, 325, 55, 50);
        b8.setBounds(111, 325, 55, 50);
        b9.setBounds(172, 325, 55, 50);
        bmult.setBounds(233, 325, 77, 50);

        b4 = new Button("4");
        b5 = new Button("5");
        b6 = new Button("6");
        bsub = new Button("-");
        b4.setBounds(50, 380, 55, 50);
        b5.setBounds(111, 380, 55, 50);
        b6.setBounds(172, 380, 55, 50);
        bsub.setBounds(233, 380, 77, 50);

        b1 = new Button("1");
        b2 = new Button("2");
        b3 = new Button("3");
        badd = new Button("+");
        b1.setBounds(50, 435, 55, 50);
        b2.setBounds(111, 435, 55, 50);
        b3.setBounds(172, 435, 55, 50);
        badd.setBounds(233, 435, 77, 50);

        bpm = new Button("+/-");
        b0 = new Button("0");
        bpts = new Button(".");
        bcalc = new Button("=");
        bpm.setBounds(50, 490, 55, 50);
        b0.setBounds(111, 490, 55, 50);
        bpts.setBounds(172, 490, 55, 50);
        bcalc.setBounds(233, 490, 77, 50);

        Button[] buttons = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bpts, bpm, bback, badd, bsub, bmult, bdiv, bmod, bcalc, cancle, bclr, bMC, bMR, MemP, MemM, bms, bMem, bfac, bsq, bexp};
        for (Button b : buttons) {
            b.addActionListener(this);
            f.add(b);
        }

        f.add(TextField1);
        f.add(TextField2);
        f.addWindowListener(this);
        f.setSize(360, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

    // --- YOUR FORMATTING METHOD ---
    private String formatResult(double result) {
        if (result == (long) result)
            return String.valueOf((long) result);
        else
            return String.format("%.2f", result)
                    .replaceAll("0+$", "")
                    .replaceAll("\\.$", "");
    }

    public void windowClosing(WindowEvent e) {
        f.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String currentStr = TextField1.getText();

        // Number Input
        if (src instanceof Button && "0123456789".contains(((Button) src).getLabel())) {
            if (currentStr.equals("0") || currentStr.equals("Error")) TextField1.setText(((Button) src).getLabel());
            else TextField1.setText(currentStr + ((Button) src).getLabel());
        }

        // Memory Logic
        else if (src == bMC) {
            memoryValue = 0;
            TextField2.setText("Memory Cleared");
        } else if (src == bMR) {
            TextField1.setText(formatResult(memoryValue));
        } else if (src == bms) {
            memoryValue = Double.parseDouble(TextField1.getText());
            TextField2.setText("Memory Stored: " + formatResult(memoryValue));
        } else if (src == MemP) {
            memoryValue += Double.parseDouble(TextField1.getText());
            TextField2.setText("M: " + formatResult(memoryValue));
        } else if (src == MemM) {
            memoryValue -= Double.parseDouble(TextField1.getText());
            TextField2.setText("M: " + formatResult(memoryValue));
        }

        // Unary / Scientific Ops
        else if (src == bsq) {
            double val = Double.parseDouble(TextField1.getText());
            TextField1.setText(formatResult(val * val));
        } else if (src == bexp) {
            TextField1.setText(formatResult(Math.sqrt(Double.parseDouble(TextField1.getText()))));
        } else if (src == bfac) {
            TextField1.setText(formatResult(1 / Double.parseDouble(TextField1.getText())));
        } else if (src == bpm) {
            double val = Double.parseDouble(TextField1.getText()) * -1;
            TextField1.setText(formatResult(val));
        } else if (src == bpts && !currentStr.contains(".")) {
            TextField1.setText(currentStr + ".");
        }

        // Basic Operators
        else if (src == badd || src == bsub || src == bmult || src == bdiv || src == bmod) {
            num1 = Double.parseDouble(TextField1.getText());
            if (src == badd) check = 1;
            if (src == bsub) check = 2;
            if (src == bmult) check = 3;
            if (src == bdiv) check = 4;
            if (src == bmod) check = 5;
            TextField2.setText(formatResult(num1) + " " + ((Button) src).getLabel());
            TextField1.setText("0");
        }

        // Equals Logic
        else if (src == bcalc) {
            num2 = Double.parseDouble(TextField1.getText());
            switch (check) {
                case 1 -> xd = num1 + num2;
                case 2 -> xd = num1 - num2;
                case 3 -> xd = num1 * num2;
                case 4 -> {
                    if (num2 == 0) {
                        TextField1.setText("Error");
                        return;
                    }
                    xd = num1 / num2;
                }
                case 5 -> xd = num1 % num2;
            }
            TextField1.setText(formatResult(xd));
            TextField2.setText("");
        }

        // Utility
        else if (src == cancle) {
            TextField1.setText("0");
            TextField2.setText("");
            num1 = num2 = check = 0;
        } else if (src == bclr) TextField1.setText("0");
        else if (src == bback && currentStr.length() > 0) {
            TextField1.setText(currentStr.length() > 1 ? currentStr.substring(0, currentStr.length() - 1) : "0");
        }
    }

    public static void main(String args[]) {
        new Microsoft_Windows_style_calculator_only_AWT();
    }
}