
import java.awt.*;
import java.awt.event.*;

class Microsoft_Windows_style_calculator_only_AWT extends WindowAdapter implements ActionListener, KeyListener {
    Frame f;
    TextField TextField1, TextField2;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
    Button badd, bsub, bmult, bdiv, bmod, bcalc, bclr, bpts, bpm, bback, cancle;
    Button bMC, bMR, MemP, MemM, bms, bMem, bfac, bsq, bexp;

    // Logic Variables
    boolean startNewInput = true;
    double num1, num2, xd, memoryValue = 0;
    int check = 0;

    Color colBg = new Color(32, 32, 32);
    Color colBtnNum = new Color(59, 59, 59);
    Color colBtnOp = new Color(50, 50, 50);
    Color colBtnEq = new Color(191, 170, 118);
    Color colText = Color.WHITE;

    Microsoft_Windows_style_calculator_only_AWT() {
        f = new Frame("Calculator");
        f.setBackground(colBg);
        f.addKeyListener(this);

        TextField2 = new TextField();
        TextField2.setEditable(false);
        TextField2.setFocusable(false);
        TextField2.setBackground(colBg);
        TextField2.setForeground(Color.GRAY);
        TextField2.setBounds(20, 50, 320, 30);

        TextField1 = new TextField("0");
        TextField1.setEditable(false);
        TextField1.setFocusable(false);
        TextField1.setBackground(colBg);
        TextField1.setForeground(colText);
        TextField1.setBounds(20, 85, 320, 60);
        TextField1.setFont(new Font("Segoe UI", Font.BOLD, 48));

        // Layout creation (MC, MR, etc.)
        bMC = createBtn("MC", 20, 155, 45, 30, colBg, colText);
        bMR = createBtn("MR", 75, 155, 45, 30, colBg, colText);
        MemP = createBtn("M+", 130, 155, 45, 30, colBg, colText);
        MemM = createBtn("M-", 185, 155, 45, 30, colBg, colText);
        bms = createBtn("MS", 240, 155, 45, 30, colBg, colText);
        bMem = createBtn("M", 295, 155, 45, 30, colBg, colText);

        bmod = createBtn("%", 20, 190, 78, 50, colBtnOp, colText);
        bclr = createBtn("CE", 102, 190, 78, 50, colBtnOp, colText);
        cancle = createBtn("C", 184, 190, 78, 50, colBtnOp, colText);
        bback = createBtn("back", 266, 190, 74, 50, colBtnOp, colText);

        bfac = createBtn("1/x", 20, 245, 78, 50, colBtnOp, colText);
        bsq = createBtn("x^2", 102, 245, 78, 50, colBtnOp, colText);
        bexp = createBtn("sqrt", 184, 245, 78, 50, colBtnOp, colText);
        bdiv = createBtn("/", 266, 245, 74, 50, colBtnOp, colText);

        b7 = createBtn("7", 20, 300, 78, 50, colBtnNum, colText);
        b8 = createBtn("8", 102, 300, 78, 50, colBtnNum, colText);
        b9 = createBtn("9", 184, 300, 78, 50, colBtnNum, colText);
        bmult = createBtn("*", 266, 300, 74, 50, colBtnOp, colText);

        b4 = createBtn("4", 20, 355, 78, 50, colBtnNum, colText);
        b5 = createBtn("5", 102, 355, 78, 50, colBtnNum, colText);
        b6 = createBtn("6", 184, 355, 78, 50, colBtnNum, colText);
        bsub = createBtn("-", 266, 355, 74, 50, colBtnOp, colText);

        b1 = createBtn("1", 20, 410, 78, 50, colBtnNum, colText);
        b2 = createBtn("2", 102, 410, 78, 50, colBtnNum, colText);
        b3 = createBtn("3", 184, 410, 78, 50, colBtnNum, colText);
        badd = createBtn("+", 266, 410, 74, 50, colBtnOp, colText);

        bpm = createBtn("+/-", 20, 465, 78, 50, colBtnNum, colText);
        b0 = createBtn("0", 102, 465, 78, 50, colBtnNum, colText);
        bpts = createBtn(".", 184, 465, 78, 50, colBtnNum, colText);
        bcalc = createBtn("=", 266, 465, 74, 50, colBtnEq, Color.BLACK);

        f.add(TextField1);
        f.add(TextField2);
        f.addWindowListener(this);
        f.setSize(360, 540);
        f.setLayout(null);
        f.setResizable(false);
        f.setVisible(true);
    }

    private Button createBtn(String label, int x, int y, int w, int h, Color bg, Color fg) {
        Button b = new Button(label);
        b.setBounds(x, y, w, h);
        b.setBackground(bg);
        b.setForeground(fg);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        b.addActionListener(this);
        b.addKeyListener(this);
        f.add(b);
        return b;
    }

    private void handleNumber(String n) {
        String s = TextField1.getText();
        if (startNewInput || s.equals("0") || s.equals("Error")) {
            TextField1.setText(n);
            startNewInput = false;
        } else {
            TextField1.setText(s + n);
        }
    }

    private void handleOp(int type, String opLabel) {
        // If an operator is already active and the user just finished typing a number...
        if (check != 0 && !startNewInput) {
            calculateIntermediate();
        } else {
            num1 = Double.parseDouble(TextField1.getText());
        }

        check = type;
        TextField2.setText(formatResult(num1) + " " + opLabel);
        startNewInput = true;
    }

    private void calculateIntermediate() {
        try {
            num2 = Double.parseDouble(TextField1.getText());
            switch (check) {
                case 1 -> num1 = num1 + num2;
                case 2 -> num1 = num1 - num2;
                case 3 -> num1 = num1 * num2;
                case 4 -> {
                    if (num2 != 0) num1 = num1 / num2;
                    else { TextField1.setText("Error"); return; }
                }
                case 5 -> num1 = num1 % num2;
            }
            TextField1.setText(formatResult(num1));
        } catch (Exception e) {
            TextField1.setText("Error");
        }
    }

    private void calculate() {
        if (check == 0) return;
        calculateIntermediate();
        TextField2.setText("");
        check = 0;
        startNewInput = true;
    }

    private void handleBackspace() {
        String s = TextField1.getText();
        if (s.length() > 0) TextField1.setText(s.length() > 1 ? s.substring(0, s.length()-1) : "0");
    }

    private void handleClearAll() {
        TextField1.setText("0"); TextField2.setText(""); num1 = num2 = check = 0; startNewInput = true;
    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        String currentStr = TextField1.getText();

        if (src instanceof Button && "0123456789".contains(((Button) src).getLabel())) {
            handleNumber(((Button) src).getLabel());
        }
        else if (src == bMC) {
            memoryValue = 0;
            TextField2.setText("Memory Cleared");
        }
        else if (src == bMR) {
            TextField1.setText(formatResult(memoryValue));
            startNewInput = true;
        }
        else if (src == bms) {
            memoryValue = Double.parseDouble(currentStr);
            TextField2.setText("M Stored: " + formatResult(memoryValue));
        }
        else if (src == MemP) {
            memoryValue += Double.parseDouble(currentStr);
            TextField2.setText("M: " + formatResult(memoryValue));
        }
        else if (src == MemM) {
            memoryValue -= Double.parseDouble(currentStr);
            TextField2.setText("M: " + formatResult(memoryValue));
        }
        else if (src == bMem) {
            // Simply show the current memory value in the history line
            TextField2.setText("Memory Value: " + formatResult(memoryValue));
        }
        else if (src == bsq) {
            double val = Double.parseDouble(TextField1.getText());
            TextField1.setText(formatResult(val * val));
            startNewInput = true;
        } else if (src == bexp) {
            TextField1.setText(formatResult(Math.sqrt(Double.parseDouble(TextField1.getText()))));
            startNewInput = true;
        } else if (src == bfac) {
            double val = Double.parseDouble(TextField1.getText());
            TextField1.setText(val != 0 ? formatResult(1 / val) : "Error");
            startNewInput = true;
        } else if (src == bpm) {
            TextField1.setText(formatResult(Double.parseDouble(TextField1.getText()) * -1));
        } else if (src == bpts && !currentStr.contains(".")) {
            TextField1.setText(currentStr + ".");
            startNewInput = false;
        }
        else if (src == badd) handleOp(1, "+");
        else if (src == bsub) handleOp(2, "-");
        else if (src == bmult) handleOp(3, "*");
        else if (src == bdiv) handleOp(4, "/");
        else if (src == bmod) handleOp(5, "%");
        else if (src == bcalc) calculate();
        else if (src == cancle) handleClearAll();
        else if (src == bclr) TextField1.setText("0");
        else if (src == bback) handleBackspace();
    }

    private String formatResult(double result) {
        if (result == (long) result) return String.valueOf((long) result);
        return String.format("%.2f", result).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    // Keyboard support and main method
    public void keyTyped(KeyEvent e) {
        char key = e.getKeyChar();
        if (Character.isDigit(key)) handleNumber(String.valueOf(key));
        if (key == '.') if(!TextField1.getText().contains(".")) TextField1.setText(TextField1.getText()+".");
        if (key == '+') handleOp(1, "+");
        if (key == '-') handleOp(2, "-");
        if (key == '*') handleOp(3, "*");
        if (key == '/') handleOp(4, "/");
        if (key == '%') handleOp(5, "%");
        if (key == '=' || key == '\n') calculate();
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) handleBackspace();
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) handleClearAll();
    }
    public void keyReleased(KeyEvent e) {}
    public void windowClosing(WindowEvent e) { f.dispose(); }
    public static void main(String args[]) { new Microsoft_Windows_style_calculator_only_AWT(); }
}
