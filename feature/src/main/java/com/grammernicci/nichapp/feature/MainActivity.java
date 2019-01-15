package com.grammernicci.nichapp.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String firstOperand;
    private String secondOperand;
    private String operation;
    private TextView tvOutput;
    private HorizontalScrollView scroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstOperand = "";
        secondOperand = "";
        operation = "";
        tvOutput = (TextView) findViewById(R.id.tv_output);
        scroll = (HorizontalScrollView) findViewById(R.id.hsv_scroll);
        scroll.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                scroll.fullScroll(View.FOCUS_RIGHT);
            }
        });
    }
    public void c7(View view){
        update("7");
    }
    public void c8(View view){
        update("8");
    }
    public void c9(View view){
        update("9");
    }
    public void cdivide(View view){
        update("÷");
    }
    public void c4(View view){
        update("4");
    }
    public void c5(View view){
        update("5");
    }
    public void c6(View view){
        update("6");
    }
    public void cmultiply(View view){
        update("x");
    }
    public void c1(View view){
        update("1");
    }
    public void c2(View view){
        update("2");
    }
    public void c3(View view){
        update("3");
    }
    public void cminus(View view){
        update("-");
    }
    public void c0(View view){
        update("0");
    }
    public void cdot(View view){
        update(".");
    }
    public void cequal(View view){
        update("=");
    }
    public void cclear(View view){
        firstOperand = "";
        secondOperand = "";
        operation = "";
        update("0");
    }
    private void evaluate() {
        double firstNum = Double.parseDouble(firstOperand);
        double secondNum = Double.parseDouble(secondOperand);
        double value = 0.0;
        switch (operation){
            case "+":
                value = firstNum + secondNum;
                update(value);
                break;
            case "-":
                value = firstNum - secondNum;
                update(value);
                break;
            case "x":
                value = firstNum * secondNum;
                update(value);
                break;
            case "÷":
                value = firstNum / secondNum;
                update(value);
                break;

        }
    }



    public void cplus(View view){
        update("+");
    }
    private void update(String symbol){
        if (symbol.equals("=")){
            evaluate();
        }else{
            if (isANumber(symbol) && firstOperand == ""){
                firstOperand = symbol;
                update(Double.parseDouble(symbol));
            }else if (isANumber(symbol) && firstOperand != ""){
                secondOperand = symbol;
                update(Double.parseDouble(symbol));
            }else{
                if (firstOperand != "" && secondOperand != ""){
                    evaluate();
                    operation = symbol;
                }else{
                    operation = symbol;
                }
            }
        }
    }

    private boolean isANumber(String symbol) {
        int dotCount = 0;
        boolean validDigit = false;
        String validDigits = "0123456789";
        for (int i = 0; i < symbol.length(); i++){
            validDigit = false;
            for (int j = 0; j < validDigits.length(); j++){
                if (symbol.charAt(i) == validDigits.charAt(j)){
                    validDigit = true;
                }
            }
            if (!validDigit)
                return false;
        }
        if (dotCount > 1)
            return false;
        else
            return true;
    }

    private void update(Double value){
        tvOutput.setText(String.valueOf(value));
    }
}
