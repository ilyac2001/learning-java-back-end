package com.example.arifmeticoperation.ui;

import java.util.Arrays;

public class FloatingNum {


    private boolean[] bin_num_floating_point = new boolean[32]; // [31] - знак, [30~23] - порядок 8 бит, [22~0] - мантиса, 23 бита, 4-х байтная запись
    //представление - (-1)^знак * мантиса * основание^порядок, где основание = 2
    //договорились, что перед запятой всегда будет одна самая старшая значищая единица, тогда знак.порядок.(единица, которую не учитывают, не пишут).мантиса


    public static final int SIZE = 8;

    public boolean[] integerPart;
    public boolean[] fractionPart = new boolean[SIZE];

    public boolean numSign;
    public int exponentInteger;
    public boolean[] exponent = new boolean[SIZE];
    public boolean[] mantissa = new boolean[SIZE*3-1];

    public FloatingNum(){};

    public FloatingNum(int integer_part, int fraction_part){
        Arrays.fill(exponent, Boolean.FALSE);
        Arrays.fill(mantissa, Boolean.FALSE);
        Arrays.fill(fractionPart, Boolean.FALSE);

        fromDecToBin(integer_part, fraction_part);
    }

    public String printWholeNumber(){
        String res;
        if(numSign) {
            res = "1";
        } else res = "0";
        res += fromBoolToString(exponent) + "." + fromBoolToString(mantissa) + "\n";
        //res += "EXP: "+(exponentInteger+127);
        return  res;
    }

    public void fromDecToBin(int integer_part, int fraction_part){
        if (integer_part<0){
            numSign = true;
            integer_part = integer_part * (-1);
        }
        else {
            numSign = false;
        }

        getIntegerPart(integer_part);

        getFractionalPart(fraction_part);


        getExp();
        getMantissa();
    }

    public boolean[] fromStringToBool(String str){
        boolean[] boolArr = new boolean[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)=='1'){
                boolArr[i] = true;
            }else{
                boolArr[i] = false;
            }
        }
        return boolArr;
    }

    public String fromBoolToString(boolean[] boolArr){
        String result="";
        for (int i = 0; i < boolArr.length; i++) {
            if (boolArr[i]){
                result=result+"1";
            }else{
                result=result+"0";
            }
        }
        return result;
    }

    public void getIntegerPart(int integer_part){
        String binaryIntegerPart = Integer.toBinaryString(integer_part);
        integerPart = new boolean[binaryIntegerPart.length()];

        integerPart = fromStringToBool(binaryIntegerPart);
    }

    public void getFractionalPart(int fraction_part){
        double buff = Double.parseDouble("0."+fraction_part);

        for (int i = 0; i < fractionPart.length; i++) {
            buff = buff * 2;
            if (buff<1){
                fractionPart[i] = false;
            }
            if (buff>=1){
                fractionPart[i] = true;
                buff = buff-1;
            }
        }
    }

    public void getExp() {
        exponentInteger = integerPart.length-1;

        String binaryExponentPart = Integer.toBinaryString(exponentInteger+127);
        exponent = fromStringToBool(binaryExponentPart);

    }

    public void getMantissa(){
        for (int i = 1; i < integerPart.length; i++) {
            mantissa[i-1] = integerPart[i];
        }

        for (int i = 0; i < fractionPart.length; i++) {
            mantissa[i+exponentInteger] = fractionPart[i];
        }
    }

    public static FloatingNum summ(FloatingNum num1,FloatingNum num2){
        FloatingNum result;

        if (num1.exponentInteger != num2.exponentInteger){
            if (num1.exponentInteger > num2.exponentInteger){
                int difference = num1.exponentInteger - num2.exponentInteger;

                num2.exponentInteger = num1.exponentInteger;
                num2.exponent = num1.exponent;
                num2.shiftExponentRight(difference);
            }
            else {
                int difference = num2.exponentInteger - num1.exponentInteger;

                num1.exponentInteger = num2.exponentInteger;
                num1.exponent = num2.exponent;
                num1.shiftExponentRight(difference);
            }
        }

        result = summFloating(num1,num2);

        return result;
    }

    public void shiftExponentRight(int steps){
        boolean[] newMantissa = new boolean[mantissa.length];
        for (int i = 0; i < steps; i++) {
            System.out.println("RIGHT");
            newMantissa[i] = false;
        }

        for (int i = steps; i < newMantissa.length; i++) {
            newMantissa[i] = mantissa[i-steps];
        }
        mantissa = newMantissa;
    }

    public void shiftExponentLeft(int steps){
        boolean[] newMantissa = new boolean[mantissa.length];
        System.out.println("LEFT");


        for (int i = newMantissa.length; i > newMantissa.length-steps; i--) {
            newMantissa[i] = false;
        }

        for (int i = 0; i < newMantissa.length-steps; i++) {
            newMantissa[i] = mantissa[i+steps];
        }

        mantissa = newMantissa;
    }

    public static FloatingNum summFloating(FloatingNum num1fl,FloatingNum num2fl){
        boolean[] num1 = num1fl.mantissa;
        boolean[] num2 = num2fl.mantissa;

        boolean membered = false;
        boolean[] summed = new boolean[num1.length];

        for (int i = num1.length-1; i > 0; i--) {
            if (!num1[i] && !num2[i])
            {
                if (membered)
                {
                    summed[i] = true;
                    membered = false;
                }
                else{
                    summed[i] = false;
                }
            }

            if (!num1[i] && num2[i] || num1[i] && !num2[i])
            {
                if (membered)
                {
                    summed[i] = false;
                    membered=true;
                }
                else{
                    summed[i] = true;
                }
            }

            if (num1[i] && num2[i])
            {
                if (membered)
                {
                    summed[i] = true;
                }
                else{
                    summed[i] = false;
                    membered = true;
                }
            }
        }

        if (membered){

            num1fl.mantissa = summed;
            num1fl.exponentInteger=num1fl.exponentInteger+1;
            num1fl.shiftExponentRight(1);

            String binaryExponentPart = Integer.toBinaryString(num1fl.exponentInteger+127);
            num1fl.exponent = num1fl.fromStringToBool(binaryExponentPart);

            num1fl.mantissa[0]=true;
        }
        else {
            num1fl.mantissa = summed;
        }


        for (int i = 0; i < num1fl.mantissa.length; i++) {
            if (num1fl.mantissa[i] == true){
                break;
            }
            if (num1fl.mantissa[i] == false){
                num1fl.shiftExponentLeft(1);
                num1fl.exponentInteger=num1fl.exponentInteger-1;
                String binaryExponentPart = Integer.toBinaryString(num1fl.exponentInteger+127);
                num1fl.exponent = num1fl.fromStringToBool(binaryExponentPart);
            }
        }


        return num1fl;
    }


    //public static void main(String[] Args){
    //    FloatingNum fl1 = new FloatingNum(15,50);
    //    FloatingNum fl2 = new FloatingNum(10,25);
//
    //    fl1.printWholeNumber();
    //    fl2.printWholeNumber();
    //    FloatingNum result = summ(fl1,fl2);
    //    result.printWholeNumber();
//
    //    System.out.println("ANSWER:");
//
    //    FloatingNum fl0 = new FloatingNum(25,75);
    //    fl0.printWholeNumber();
    //}
}








