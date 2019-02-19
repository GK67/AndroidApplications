package com.example.gk.calculatortry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button buttonNumber1;
    private Button buttonNumber2;
    private Button buttonNumber3;
    private Button buttonNumber4;
    private Button buttonNumber5;
    private Button buttonNumber6;
    private Button buttonNumber7;
    private Button buttonNumber8;
    private Button buttonNumber9;
    private Button buttonNumber0;
    private Button ButtonSymbolAdd;
    private Button ButtonSymbolminus;
    private Button ButtonSymboltimes;
    private Button ButtonSymboldevide;
    private Button ButtonSymbolequal;
    private Button ButtonClear;
    private double firstNumber =0;
    private double secondNumber =0;
    private double answer = 0;
    private boolean firstNumberFinish = false;
    private boolean calculated = false;
    private char symbol = '\u0000';
    private TextView myTextView;
    private TextView answerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonNumber1 = (Button) findViewById(R.id.number1);
        buttonNumber2 = (Button) findViewById(R.id.number2);
        buttonNumber3 = (Button) findViewById(R.id.number3);
        buttonNumber4 = (Button) findViewById(R.id.number4);
        buttonNumber5 = (Button) findViewById(R.id.number5);
        buttonNumber6 = (Button) findViewById(R.id.number6);
        buttonNumber7 = (Button) findViewById(R.id.number7);
        buttonNumber8 = (Button) findViewById(R.id.number8);
        buttonNumber9 = (Button) findViewById(R.id.number9);
        buttonNumber0 = (Button) findViewById(R.id.number0);
        ButtonSymbolAdd = (Button) findViewById(R.id.add);
        ButtonSymbolminus = (Button) findViewById(R.id.minus);
        ButtonSymboltimes = (Button) findViewById(R.id.times);
        ButtonSymboldevide = (Button) findViewById(R.id.devide);
        ButtonSymbolequal = (Button) findViewById(R.id.equal);
        ButtonClear = (Button) findViewById(R.id.clear);
        myTextView = (TextView) findViewById(R.id.textView);
        answerTextView = (TextView)findViewById(R.id.answerTextView);

        buttonNumber1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();

                myTextView.append("1");

                inputNumber(1);
            }
        });
        buttonNumber2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                myTextView.append("2");
                inputNumber(2);
            }
        });
        buttonNumber3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                myTextView.append("3");
                inputNumber(3);
            }
        });
        buttonNumber4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                myTextView.append("4");
                inputNumber(4);
            }
        });
        buttonNumber5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                myTextView.append("5");
                inputNumber(5);
            }
        });
        buttonNumber6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                myTextView.append("6");
                inputNumber(6);
            }
        });
        buttonNumber7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                myTextView.append("7");
                inputNumber(7);
            }
        });
        buttonNumber8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                myTextView.append("8");
                inputNumber(8);
            }
        });
        buttonNumber9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                myTextView.append("9");
                inputNumber(9);
            }
        });
        buttonNumber0.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                myTextView.append("0");
                inputNumber(0);
            }
        });
        ButtonSymbolAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                if(firstNumberFinish == false){
                myTextView.append("+");
                symbol = '+';
                firstNumberFinish = true;
                }
                else{

                    String tempAnswer = Calculate(firstNumber,secondNumber,symbol);
                    answerTextView.setText(tempAnswer);
                    calculated = true;
                }
            }
        });
        ButtonSymbolminus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                if(firstNumberFinish == false){

                    myTextView.append("-");
                    symbol = '-';
                    firstNumberFinish = true;
                }
                else{

                    String tempAnswer = Calculate(firstNumber,secondNumber,symbol);
                    answerTextView.setText(tempAnswer);
                    calculated = true;
                }
            }
        });
        ButtonSymboltimes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                if(firstNumberFinish == false){

                    myTextView.append("*");
                    symbol = '*';
                    firstNumberFinish = true;
                }
                else{

                    String tempAnswer = Calculate(firstNumber,secondNumber,symbol);
                    answerTextView.setText(tempAnswer);
                    calculated = true;
                }
            }
        });
        ButtonSymboldevide.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                if(firstNumberFinish == false){

                    myTextView.append("/");
                    symbol = '/';
                    firstNumberFinish = true;
                }
                else{


                    String tempAnswer = Calculate(firstNumber,secondNumber,symbol);
                    answerTextView.setText(tempAnswer);
                    calculated = true;
                }
            }
        });
        ButtonSymbolequal.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                if(calculated == true)
                    clear();
                String tempAnswer = Calculate(firstNumber,secondNumber,symbol);
                answerTextView.setText(tempAnswer);
                calculated = true;

            }
        });
        ButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });



    }
    private void clear(){
        answerTextView.setText("0");
        myTextView.setText("");
        firstNumber=0;
        secondNumber=0;
        symbol='\u0000';
        firstNumberFinish = false;
        calculated = false;
    }
    private void inputNumber(int s) {
        if(firstNumberFinish == false){
            if(firstNumber == 0 && s ==0){
                firstNumber =0;
            }
            else{
                firstNumber = firstNumber*10+s;
            }

        }
        else{
            if(secondNumber == 0 && s ==0){
                secondNumber =0;
            }
            else{
                secondNumber = secondNumber*10+s;
            }

        }
    }

    private String Calculate(double firstNumber,double secondNumber,char symbol) {
        String returnAnswer = "0";
        double answer = 0;
        if (symbol =='+'){
            Log.d("STATE","the firstnumber is "+ firstNumber);
            Log.d("STATE","the secondnumber is "+ secondNumber);
            answer = (firstNumber + secondNumber);
            Log.d("STATE","the answer is "+ answer);
        }
        if (symbol =='-'){
            answer = firstNumber-secondNumber;
        }
        if (symbol =='*'){
            answer = firstNumber*secondNumber;
        }
        if (symbol =='/'){
            if(secondNumber ==0){
                returnAnswer ="Undefined";
                return returnAnswer;
            }
            answer = firstNumber+secondNumber;
        }
        returnAnswer =String.valueOf(answer);
        return returnAnswer;
    }
}
