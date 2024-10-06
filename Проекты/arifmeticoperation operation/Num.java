package com.example.arifmeticoperation;

public class Num {
    private int num;
    private boolean[] bin_num_in_direct_code = new boolean[8];
    private boolean[] bin_num_in_return_code = new boolean[8];
    private boolean[] bin_num_in_additional_code = new boolean[8];
    private boolean overflow = false;

    public void setNum(int num) {
        if (num >= -127 & num <= 127) {
            this.num = num;
        } else {
            try {
                throw new Exception("OutOfRange");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void setBin_num_in_direct_code(){
        int num = this.num;
        if(this.num < 0) {
            this.bin_num_in_direct_code[7] = true;
            num = -num;
        }
        int i = 0;
        while (num != 0) {
            this.bin_num_in_direct_code[i] = (num % 2 == 1) ? true : false;
            num = num / 2;
            i++;
        }
    }
    private void setBin_num_in_return_code(){
        for (int i = 0; i < 8; i++) {
            this.bin_num_in_return_code[i] = this.bin_num_in_direct_code[i];
        }
        if(this.bin_num_in_direct_code[7]) {
            for (int i = 0; i < 7; i++) {
                this.bin_num_in_return_code[i] = !this.bin_num_in_return_code[i];
            }
        }
    }
    private  void setBin_num_in_additional_code() {
        for (int i = 0; i < 8; i++) {
            this.bin_num_in_additional_code[i] = this.bin_num_in_direct_code[i];
        }
        if (this.bin_num_in_additional_code[7]) {
            for (int i = 0; i < 7; i++) {
                this.bin_num_in_additional_code[i] = !this.bin_num_in_additional_code[i];
            }
            this.SumForAdditionCode(this, new Num(1));
        }
    }

    public Num(double num) {
        this.setNum((int) num);
        this.setBin_num_in_direct_code();
        this.setBin_num_in_return_code();
        this.setBin_num_in_additional_code();
    }
    public String PrintBineryInDirectCode(){
        String res = "";
        for (int i = 7; i >= 0; i--) {
            if (this.bin_num_in_direct_code[i])
                res+="1";
            else res+="0";
        }
        return res;
    }
    public String PrintBineryInReturnCode(){
        String res = "";
        for (int i = 7; i >= 0; i--) {
            if (this.bin_num_in_return_code[i])
                res+="1";
            else res+="0";
        }
        return res;
    }
    public String PrintBineryInAdditionalCode(){
        String res = "";
        for (int i = 7; i >= 0; i--) {
            if (this.bin_num_in_additional_code[i])
                res+="1";
            else res+="0";
        }
        return res;
    }
    private static Num OperationInDirectCode(Num a, Num b){
        Num result = new Num(0);
        if (!(a.bin_num_in_direct_code[7] ^ b.bin_num_in_direct_code[7])){
            result.bin_num_in_direct_code[7] = a.bin_num_in_direct_code[7];
            result.Sum(a, b);
        } else {
            for (int i = 6; i >= 0; i--) {
                if (a.bin_num_in_direct_code[i] ^ b.bin_num_in_direct_code[i]) {
                    result.bin_num_in_direct_code[7] = (a.bin_num_in_direct_code[i] == true) ? a.bin_num_in_direct_code[7] : b.bin_num_in_direct_code[7];
                    break;
                }
            }
            if (result.bin_num_in_direct_code[7] ^ b.bin_num_in_direct_code[7]) {
                result.Sub(a, b);
            } else {
                result.Sub(b, a);
            }
        }
        return  result;
    }
    private Num Sum(Num a, Num b){
        Num result = this;
        boolean buff = false;
        for (int i = 0; i < 7; i++) {
            result.bin_num_in_direct_code[i] = a.bin_num_in_direct_code[i] ^ b.bin_num_in_direct_code[i] ^ buff;
            if (a.bin_num_in_direct_code[i] & b.bin_num_in_direct_code[i] |
                    a.bin_num_in_direct_code[i] & buff |
                    b.bin_num_in_direct_code[i] & buff)
            { buff = true; }
            else {buff = false; }
        }
        return result;
    }

    private Num Sub(Num a, Num b){
        Num result = this;
        Num buff = new Num(a.num);
        for (int i = 0; i < 7; i++) {
            result.bin_num_in_direct_code[i] = buff.bin_num_in_direct_code[i] ^ b.bin_num_in_direct_code[i];
            if(!buff.bin_num_in_direct_code[i] & b.bin_num_in_direct_code[i]){
                int j = 1;
                while (!buff.bin_num_in_direct_code[i+j]) {
                    buff.bin_num_in_direct_code[i + j] = !buff.bin_num_in_direct_code[i + j];
                    j++;
                }
                buff.bin_num_in_direct_code[i + j] = !buff.bin_num_in_direct_code[i + j];
            }
        }
        return result;
    }
    private Object SumForReturnCode(Num a, Num b){
        Num result = this;
        boolean buff = false;
        for (int i = 0; i < 8; i++) {
            result.bin_num_in_return_code[i] = a.bin_num_in_return_code[i] ^ b.bin_num_in_return_code[i] ^ buff;
            if (a.bin_num_in_return_code[i] & b.bin_num_in_return_code[i] |
                    a.bin_num_in_return_code[i] & buff |
                    b.bin_num_in_return_code[i] & buff)
            { buff = true; }
            else {buff = false; }
        }
        if(buff) {result.overflow = true; } else {result.overflow = false;}
        return result;
    }
    private Object SumForAdditionCode(Num a, Num b) {
        Num result = this;
        boolean buff = false;
        for (int i = 0; i < 8; i++) {
            boolean q = a.bin_num_in_additional_code[i];
            result.bin_num_in_additional_code[i] = q ^ b.bin_num_in_additional_code[i] ^ buff;
            if (q & b.bin_num_in_additional_code[i] |
                    q & buff |
                    b.bin_num_in_additional_code[i] & buff)
            { buff = true; }
            else {buff = false; }
        }
        return result;
    }
    private static Num OperationInReturnCode(Num a, Num b){
        Num result = new Num(0);
        result.SumForReturnCode(a, b);
        if(result.overflow) {
            Num overflow = new Num(1);
            Num buff = new Num(0);
            for (int i = 0; i < 8; i++) {
                buff.bin_num_in_return_code[i] = result.bin_num_in_return_code[i];
            }
            result.SumForReturnCode(buff, overflow);
        }
        if(result.bin_num_in_return_code[7]){
            for (int i = 0; i < 7; i++){
                result.bin_num_in_return_code[i] = !result.bin_num_in_return_code[i];
            }
        }
        return  result;
    }

    private static Num OperationInAdditionalCode(Num a, Num b) {
        Num result = new Num(0);
        result.SumForAdditionCode(a, b);
        return  result;
    }

    public static Num SumNumberInDirectCode(Num a, Num b){ return OperationInDirectCode(a, b); }
    public static Num SubNumberInDirectCode(Num a, Num b){ return  OperationInDirectCode(a, new Num(-b.num)); }

    public static Num SumNumberInReturnCode(Num a, Num b) { return OperationInReturnCode(a, b); }
    public static Num SubNumberInReturnCode(Num a, Num b){ return OperationInReturnCode(a, new Num(-b.num)); }

    public static Num SumNumberInAdditionalCode(Num a, Num b){ return OperationInAdditionalCode(a, b); }
    public static Num SubNumberInAdditionalCode(Num a, Num b){ return OperationInAdditionalCode(a, new Num(-b.num)); }
}
