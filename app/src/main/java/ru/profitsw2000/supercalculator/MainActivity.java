package ru.profitsw2000.supercalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LifeCycle"    ;
    private final static String CALC = "Calculator";
    private final static String SUPERCALCULATOR_MODE = "CurrentMode"   ;
    private final static String MODE = "SetMode"   ;

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
        getAppTheme();
        setContentView(R.layout.activity_main);
        calculator = new Calculator()   ;
        findViews();
        buttonsListen();
        operationButtonsListen();
    }

    private void getAppTheme() {

        Context context = getApplicationContext() ;
        SharedPreferences settings = getSharedPreferences(SUPERCALCULATOR_MODE, Context.MODE_PRIVATE);
        String mode = settings.getString(MODE, "day_mode");

        switch (mode) {
            case "day_mode":
                setTheme(R.style.Theme_SuperCalculator);
                break;
            case "night_mode":
                setTheme(R.style.Theme_SuperCalculatorNight);
                break;
        }
    }

    private void saveAppTheme(String currentMode) {

        Context context = getApplicationContext() ;
        SharedPreferences settings = getSharedPreferences(SUPERCALCULATOR_MODE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(MODE, currentMode);
        editor.apply();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId()   ;
        Log.d(TAG, Integer.toString(id))    ;

        switch (id){
            case R.id.day_mode:
                saveAppTheme("day_mode");
                recreate();
                break;
            case R.id.night_mode:
                saveAppTheme("night_mode");
                recreate();
                break;
        }
        return super.onOptionsItemSelected(item);
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

    private void operationButtonsListen() {
        setoperationButtonListener(mc);
        setoperationButtonListener(mr);
        setoperationButtonListener(ms);
        setoperationButtonListener(mplus);
        setoperationButtonListener(mminus);
        setoperationButtonListener(backspace);
        setoperationButtonListener(ce);
        setoperationButtonListener(clear);
        setoperationButtonListener(plusminus);
        setoperationButtonListener(sqrt);
        setoperationButtonListener(divide);
        setoperationButtonListener(percent);
        setoperationButtonListener(multiply);
        setoperationButtonListener(invert);
        setoperationButtonListener(minus);
        setoperationButtonListener(plus);
        setoperationButtonListener(equal);
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

    private void setoperationButtonListener (Button button)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.getOperationButton(button.getText().toString());
                enter_field.setText(calculator.getOutputText());
            }
        });
    }
}