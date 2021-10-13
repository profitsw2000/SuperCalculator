package ru.profitsw2000.supercalculator;

import android.os.Parcel;
import android.os.Parcelable;

public class Calculator implements Parcelable {

    private String outputText   ;
    private int counter ;

    Calculator () {
        this.outputText = "0"   ;
        this.counter = 0    ;
    }

    protected Calculator(Parcel in) {
        outputText = in.readString();
        counter = in.readInt()  ;
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
        counter++   ;

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

    public String getOutputText() {
        return outputText   ;
    }

    public int getCounter() {
        return counter  ;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(outputText);
        dest.writeInt(counter);
    }
}
