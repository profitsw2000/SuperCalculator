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
    private boolean operationPressed    ;
    private Operation operation ;
    private Double var;
    private Double var1;
    private Double var2;
    private double tempMemory   ;

    Calculator () {
        this.outputText = "0"   ;
        this.var = 0.0  ;
        this.var1 = 0.0  ;
        this.var2 = 0.0  ;
        this.operationPressed = false   ;
        this.operation = Operation.NONE ;
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
        String temp, string1, string2 ;
        int index   ;

        index = outputText.lastIndexOf("\n")    ;
        if (index >= 0) {
            string1 = outputText.substring(0, index)    ;
            string2 = outputText.substring(index + 1)   ;
        }
        else {
            string1 = ""    ;
            string2 = outputText    ;
        }

        if (operationPressed) {
            operationPressed = false    ;
            string2 = "0"  ;
        }

        if (string2.length() < 17) {
            if (!buttonString.equalsIgnoreCase(",")) {
                if (string2.equalsIgnoreCase("0")) string2 = buttonString;
                else {
                    temp = string2;
                    string2 = temp + buttonString;
                }
            } else {
                if (string2.indexOf(',') == -1) {
                    temp = string2;
                    string2 = temp + buttonString;
                }
            }
        }
        outputText = string1 + "\n" + string2   ;
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
                eraseLastDigit();
                break   ;

            case CLEAR_E:
                clearSecondArgument();
                break   ;

            case CLEAR:
                clearAll()  ;
                break   ;

            case PLUSMINUS:
                invertSignOfArgument();
                break   ;

            case SQRT:
                squareRootArgument();
                break   ;

            case DIVIDE:
                executeOperation(Operation.DIVIDE, DIVIDE);
                break   ;

            case PERCENT:
                setPercentage();
                break   ;

            case MULTIPLE:
                executeOperation(Operation.MULTIPLE, MULTIPLE);
                break   ;

            case INVERT:
                reciprocArgument();
                break   ;

            case MINUS:
                executeOperation(Operation.MINUS, MINUS);
                break   ;

            case PLUS:
                executeOperation(Operation.PLUS, PLUS);
                break   ;

            case EQUAL:
                resultOperation();
                break   ;
        }
    }

    private void executeOperation(Operation currentOperation, String sign) {
        String string1, string2 ;

        if (!operationPressed){
            if (operation == Operation.NONE) {
                string2 = getPartOfOutputString(false)    ;
                outputText = string2 + sign + "\n" + string2    ;
                tempMemory = Double.valueOf(string2.replace(",", "."))  ;
            }
            else {
                string1 = getPartOfOutputString(true)   ;
                string2 = getPartOfOutputString(false)  ;

                tempMemory = computeNumber(operation, tempMemory, Double.valueOf(string2.replace(",","."))) ;

                if (!String.valueOf(tempMemory).substring(String.valueOf(tempMemory).indexOf(".") + 1).equalsIgnoreCase("0")){
                    outputText = string1 + string2 + sign + "\n" + String.valueOf(tempMemory).replace('.', ',')  ;
                }
                else {
                    outputText = string1 + string2 + sign + "\n" + String.valueOf(tempMemory).substring(0, String.valueOf(tempMemory).indexOf(".")) ;
                }
            }
            operationPressed = true ;
            operation = currentOperation  ;
        }
        else {
            operation = currentOperation  ;
            setSignAtEndOfUpString(sign);
        }
    }


    private double computeNumber (Operation operation, double variable1, double variable2) {
        double result = 0   ;

        switch (operation){
            case PLUS:
                result = variable1 + variable2  ;
                break;

            case MINUS:
                result = variable1 - variable2  ;
                break;

            case DIVIDE:
                result = variable1/variable2  ;
                break;

            case MULTIPLE:
                result = variable1*variable2  ;
                break;

            default:
                break;
        }

        return result   ;
    }

    private void resultOperation(){
        String string1 = getPartOfOutputString(false)   ;

        tempMemory = computeNumber(operation, tempMemory, Double.valueOf(string1.replace(",","."))) ;

        if (!String.valueOf(tempMemory).substring(String.valueOf(tempMemory).indexOf(".") + 1).equalsIgnoreCase("0")){
            outputText = String.valueOf(tempMemory).replace('.', ',')  ;
        }
        else {
            outputText = String.valueOf(tempMemory).substring(0, String.valueOf(tempMemory).indexOf(".")) ;
        }

        this.operationPressed = false   ;
        this.operation = Operation.NONE ;
    }

    private void squareRootArgument () {
        String tempString1, tempString2   ;
        double tempArgument ;

        tempString1 = getPartOfOutputString(true)   ;
        tempString2 = getPartOfOutputString(false)  ;

        tempArgument = Double.valueOf(tempString2.replace(',','.'))  ;

        if (tempArgument >= 0)
        {
            tempArgument = Math.sqrt(tempArgument) ;
            if (!String.valueOf(tempArgument).substring(String.valueOf(tempArgument).indexOf(".") + 1).equalsIgnoreCase("0")) {
                outputText = tempString1 + "sqrt(" + tempString2 + ")" + "\n" + String.valueOf(tempArgument).replace('.', ',');
            }
            else {
                outputText = tempString1 + "sqrt(" + tempString2 + ")" + "\n" + String.valueOf(tempArgument).substring(0, String.valueOf(tempArgument).indexOf(".")) ;
            }
        }
        else {
            outputText = tempString1 + "sqrt(" + tempString2 + ")" + "\n" + "Недопустимый ввод"    ;
        }
    }

    private void reciprocArgument() {
        String tempString1, tempString2   ;
        double tempArgument ;

        tempString1 = getPartOfOutputString(true)   ;
        tempString2 = getPartOfOutputString(false)  ;

        tempArgument = Double.valueOf(tempString2.replace(',','.'))  ;

        if (tempArgument != 0) {
            tempArgument = 1/tempArgument   ;
            if (!String.valueOf(tempArgument).substring(String.valueOf(tempArgument).indexOf(".") + 1).equalsIgnoreCase("0")) {
                outputText = tempString1 + "reciproc(" + tempString2 + ")" + "\n" + String.valueOf(tempArgument).replace('.', ',');
            }
            else {
                outputText = tempString1 + "reciproc(" + tempString2 + ")" + "\n" + String.valueOf(tempArgument).substring(0, String.valueOf(tempArgument).indexOf(".")) ;
            }
        }
        else outputText = tempString1 + "reciproc(" + tempString2 + ")" + "\n" + "Недопустимый ввод"    ;
    }

    private void setPercentage() {
        String tempString1, tempString2   ;
        double tempArgument ;

        tempString1 = getPartOfOutputString(true)   ;
        tempString2 = getPartOfOutputString(false)  ;

        tempArgument = Double.valueOf(tempString2.replace(',','.'))  ;
        tempArgument = (tempMemory/100)*tempArgument    ;

        if (!String.valueOf(tempArgument).substring(String.valueOf(tempArgument).indexOf(".") + 1).equalsIgnoreCase("0")) {
            tempString2 = String.valueOf(tempArgument).replace('.', ',');
        }
        else {
            tempString2 = String.valueOf(tempArgument).substring(0, String.valueOf(tempArgument).indexOf(".")) ;
        }

        outputText = tempString1 + tempString2 + "\n" + tempString2 ;
    }

    private void invertSignOfArgument() {
        String tempString1, tempString2   ;

        tempString1 = getPartOfOutputString(true)   ;
        tempString2 = getPartOfOutputString(false)  ;

        if (!tempString2.equalsIgnoreCase("0")) {
            if (tempString2.charAt(0) == '-') tempString2 = tempString2.substring(1);
            else tempString2 = "-" + tempString2    ;
        }
        else {
            tempString2 = "0"   ;
        }
        outputText = tempString1 + "\n" + tempString2 ;
    }

    private void eraseLastDigit() {
        String tempString1, tempString2   ;

        tempString1 = getPartOfOutputString(true)   ;
        tempString2 = getPartOfOutputString(false)  ;

        if (!tempString2.equalsIgnoreCase("0") && tempString2.length() > 1 && !(tempString2.indexOf("-") >= 0 && tempString2.length() < 3)) {
            tempString2 = tempString2.substring(0,(tempString2.length() - 1));
        }
        else {
            tempString2 = "0"   ;
        }
        outputText = tempString1 + "\n" + tempString2 ;
    }

    private void clearAll() {
        outputText = "0"    ;
        var = 0.0   ;
        var1 = 0.0  ;
        var2 = 0.0  ;
        tempMemory = 0.0    ;
        operation = Operation.NONE  ;
    }

    private void clearSecondArgument() {
        String tempString   ;

        tempString = getPartOfOutputString(true)   ;
        outputText = tempString + "\n" + "0"   ;
    }

    private String getPartOfOutputString(boolean upString) {

        String partString  ;
        int index   ;

        index = outputText.lastIndexOf("\n")    ;
        if (index >= 0) {
            if(upString) partString = outputText.substring(0, index)    ;
            else partString = outputText.substring(index + 1)   ;
        }
        else {
            if(upString) partString = ""    ;
            else partString = outputText    ;
        }

        return partString   ;
    }

    private void setSignAtEndOfUpString(String sign) {
        String string1, string2 ;

        string1 = getPartOfOutputString(true)   ;
        string2 = getPartOfOutputString(false)  ;
        outputText = string1.substring(0, string1.length() - 1) + sign + "\n" + string2 ;
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

enum Operation {
    MINUS,
    PLUS,
    DIVIDE,
    MULTIPLE,
    RESULT,
    NONE
}