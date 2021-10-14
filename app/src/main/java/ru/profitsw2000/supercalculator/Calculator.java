package ru.profitsw2000.supercalculator;

import android.os.Parcel;
import android.os.Parcelable;

public class Calculator implements Parcelable {

    private final String MEMORYCLEAR = "MC"    ;
    private final String MEMORYREAD = "MR"    ;
    private final String MEMORYSET = "MS"    ;
    private final String MEMORYPLUS = "M+"    ;
    private final String MEMORYMINUS = "M-"    ;
    private final String BACKSPACE = "<--"    ;
    private final String CLEAR_E = "CE"    ;
    private final String CLEAR = "C"    ;
    private final String PLUSMINUS = "±"    ;
    private final String SQRT = "√"    ;
    private final String DIVIDE = "/"    ;
    private final String PERCENT = "%"    ;
    private final String MULTIPLE = "*"    ;
    private final String INVERT = "1/x"    ;
    private final String MINUS = "-"    ;
    private final String PLUS = "+"    ;
    private final String EQUAL = "="    ;

    private String outputText   ;
    private Double var;
    private Double var1;
    private Double var2;

    Calculator () {
        this.outputText = "0"   ;
        this.var = 0.0  ;
        this.var1 = 0.0  ;
        this.var2 = 0.0  ;
    }

    protected Calculator(Parcel in) {
        outputText = in.readString();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    public void getDigitButton(String buttonString) {
        String temp ;

        if (outputText.length() < 17) {
            if (!buttonString.equalsIgnoreCase(",")) {
                if (outputText.equalsIgnoreCase("0")) outputText = buttonString;
                else {
                    temp = outputText;
                    outputText = temp + buttonString;
                }
            } else {
                if (outputText.indexOf(',') == -1) {
                    temp = outputText;
                    outputText = temp + buttonString;
                }
            }
        }
    }

    public void getOperationButton(String buttonText) {
        switch (buttonText) {
            case MEMORYCLEAR:
                break;

            case MEMORYREAD:
                break   ;

            case MEMORYSET:
                break   ;

            case MEMORYPLUS:
                break   ;

            case MEMORYMINUS:
                break   ;

            case BACKSPACE:
                break   ;

            case CLEAR_E:
                break   ;

            case CLEAR:
                clearAll()  ;
                break   ;

            case PLUSMINUS:
                break   ;

            case SQRT:
                break   ;

            case DIVIDE:
                break   ;

            case PERCENT:
                break   ;

            case MULTIPLE:
                break   ;

            case INVERT:
                break   ;

            case MINUS:
                break   ;

            case PLUS:
                break   ;

            case EQUAL:
                break   ;
        }
    }

    private void clearAll() {
        outputText = "0"    ;
        var = 0.0   ;
        var1 = 0.0  ;
        var2 = 0.0  ;
    }

    public String getOutputText() {
        return outputText   ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(outputText);
    }

}
