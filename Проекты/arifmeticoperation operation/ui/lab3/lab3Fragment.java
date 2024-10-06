package com.example.arifmeticoperation.ui.lab3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.arifmeticoperation.Num;
import com.example.arifmeticoperation.R;
import com.example.arifmeticoperation.databinding.FragmentLab3Binding;

public class lab3Fragment extends Fragment {

    private lab3ViewModel lab3ViewModel;
    private FragmentLab3Binding binding;
    int first_op, second_op;
    Num a, b;
    private EditText first_num, second_num;
    private TextView exception, result_operation_str, operand_in_code, result_in_code;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lab3ViewModel =
                new ViewModelProvider(this).get(lab3ViewModel.class);

        binding = FragmentLab3Binding.inflate(inflater, container, false);
        View root = binding.getRoot();
        this.first_num = root.findViewById(R.id.first_num);
        this.second_num = root.findViewById(R.id.second_num);
        this.exception = root.findViewById(R.id.exception);
        this.result_operation_str = root.findViewById(R.id.result_operation_str);
        this.operand_in_code = root.findViewById(R.id.operand_in_code);
        this.result_in_code = root.findViewById(R.id.result_in_code);

        first_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                PrintResult(first_num, second_num);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                run();
            }


            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        second_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                PrintResult(first_num, second_num);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                run();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return root;
    }
    private void run(){
        try {
            if(!first_num.getText().toString().isEmpty()) {
                first_op = Integer.parseInt(first_num.getText().toString());
                this.a = new Num(first_op);
            }
            if(!second_num.getText().toString().isEmpty()) {
                second_op = Integer.parseInt(second_num.getText().toString());
                this.b = new Num(second_op);
            }
            PrintResult(first_num, second_num);
            exception.setText("");
        } catch (NumberFormatException e) {
            exception.setText("введите число!");
            result_operation_str.setText("");
            operand_in_code.setText("");
            result_in_code.setText("");
        }
    }
    private void PrintResult_operation_str(TextView result_operation_str, int a, int b) {
        int result_sum = a + b;
        int result_sub = a - b;
        result_operation_str.setText(a + " = " + Integer.toBinaryString(a) + "\n" +
                b + " = " + Integer.toBinaryString(b) + "\n" +
                a + " + " + b + " = " + result_sum + " = " + Integer.toBinaryString(result_sum) + "\n" +
                a + " - " + b + " = " + result_sub + " = " + Integer.toBinaryString(result_sub));
    }
    private void PrintResult(EditText a, EditText b){
        if(a.getText().toString().isEmpty() &
                b.getText().toString().isEmpty()){
            exception.setText("");
            result_operation_str.setText("");
            operand_in_code.setText("");
            result_in_code.setText("");
            return;
        } else {
            if(a.getText().toString().isEmpty() ||
                    b.getText().toString().isEmpty()){
                exception.setText("");
                result_operation_str.setText("");
                operand_in_code.setText("");
                result_in_code.setText("");
            } else
            {
                PrintResult_operation_str(result_operation_str, first_op, second_op);
                PrintOperandInCode(operand_in_code, this.a, this.b);
                PrintResultInCode(result_in_code, this.a, this.b);
            }
        }
    }

    private void PrintResultInCode(TextView result_in_code, Num a, Num b) {
        try {
            try {
                String res = "сумма:\n";
                res += Num.SumNumberInAdditionalCode(a, b).PrintBineryInAdditionalCode();
                res += "\nразность:\n";
                res += Num.SubNumberInAdditionalCode(a, b).PrintBineryInAdditionalCode();
                result_in_code.setText(res);
            } catch (Exception e){};
        }
        catch (Exception e){};
    }

    private void PrintOperandInCode(TextView operand_in_code, Num a, Num b) {
        try {
            String res = "первый операнд в двоичной системе:\n";
            res += a.PrintBineryInAdditionalCode();
            res += "\nвторой:\n";
            res += b.PrintBineryInAdditionalCode();
            operand_in_code.setText(res);
        } catch (Exception e){};
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}