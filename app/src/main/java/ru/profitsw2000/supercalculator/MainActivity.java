package ru.profitsw2000.supercalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LifeCycle"    ;
    private final static String CALC = "Calculator";

    private Button one  ;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private Button point;

    private Button mc  ;
    private Button mr;
    private Button ms;
    private Button mplus;
    private Button mminus;
    private Button backspace;
    private Button ce;
    private Button clear;
    private Button plusminus;
    private Button sqrt;
    private Button divide;
    private Button percent;
    private Button multiply;
    private Button invert;
    private Button minus;
    private Button plus;
    private Button equal;

    private TextView enter_field    ;
    private Calculator calculator   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator = new Calculator()   ;
        findViews();
        buttonsListen();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator = (Calculator) savedInstanceState.getParcelable(CALC)    ;
        enter_field.setText(calculator.getOutputText());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CALC, calculator);
    }

    private void findViews(){
        zero = findViewById(R.id.button_0)  ;
        one = findViewById(R.id.button_1)   ;
        two = findViewById(R.id.button_2)   ;
        three = findViewById(R.id.button_3) ;
        four = findViewById(R.id.button_4)  ;
        five = findViewById(R.id.button_5)  ;
        six = findViewById(R.id.button_6)   ;
        seven = findViewById(R.id.button_7) ;
        eight = findViewById(R.id.button_8) ;
        nine = findViewById(R.id.button_9)  ;
        point = findViewById(R.id.button_point) ;

        mc = findViewById(R.id.button_mc)  ;
        mr = findViewById(R.id.button_mr)   ;
        ms = findViewById(R.id.button_MS)   ;
        mplus = findViewById(R.id.button_mplus) ;
        mminus = findViewById(R.id.button_mminus)  ;
        backspace = findViewById(R.id.button_backspace)  ;
        ce = findViewById(R.id.button_ce)   ;
        clear = findViewById(R.id.button_c) ;
        plusminus = findViewById(R.id.button_plus_minus) ;
        sqrt = findViewById(R.id.button_square_root)  ;
        divide = findViewById(R.id.button_divide) ;
        percent = findViewById(R.id.button_percent)  ;
        multiply = findViewById(R.id.button_multiple)   ;
        invert = findViewById(R.id.button_invert)   ;
        minus = findViewById(R.id.button_minus) ;
        plus = findViewById(R.id.button_plus)  ;
        equal = findViewById(R.id.button_equal)  ;

        enter_field = findViewById(R.id.enter_field)    ;
    }

    private void buttonsListen() {
        setListener(one);
        setListener(two);
        setListener(three);
        setListener(four);
        setListener(five);
        setListener(six);
        setListener(seven);
        setListener(eight);
        setListener(nine);
        setListener(zero);
        setListener(point);
    }

    private void setListener (Button button)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.getDigitButton(button.getText().toString());
                enter_field.setText(calculator.getOutputText());
            }
        });
    }
}