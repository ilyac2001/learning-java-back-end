 package com.example.arifmeticoperation.ui.lab4;

import static com.example.arifmeticoperation.ui.FloatingNum.summ;

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
import com.example.arifmeticoperation.databinding.FragmentLab4Binding;
import com.example.arifmeticoperation.ui.FloatingNum;

 public class lab4Fragment extends Fragment {

    private com.example.arifmeticoperation.ui.lab3.lab3ViewModel lab4ViewModel;
    private FragmentLab4Binding binding;
    double first_op;
     double second_op;
    FloatingNum a,b;
    private EditText first_num, second_num;
    private TextView exception, result_operation_str, operand_in_code, result_in_code;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLab4Binding.inflate(inflater, container, false);
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
           // if(first_num.getText().toString().length() > 3) {
           //     this.a = new FloatingNum(
           //             Integer.parseInt(first_num.getText().toString().substring(0, first_num.getText().toString().indexOf('.'))), //целая
           //             Integer.parseInt(first_num.getText().toString().substring(first_num.getText().toString().indexOf('.') + 1)) //дробная
           //     );
           // }
           // if(first_num.getText().toString().length() > 3) {
           //     second_op = Double.parseDouble(first_num.getText().toString());
           //     this.b = new FloatingNum(
           //             Integer.parseInt(second_num.getText().toString().substring(0, second_num.getText().toString().indexOf('.'))), //целая
           //             Integer.parseInt(second_num.getText().toString().substring(second_num.getText().toString().indexOf('.') + 1)) //дробная
           //     );
           // }
           // PrintResult(first_num, second_num);
           // exception.setText("");
        } catch (NumberFormatException e) {
            exception.setText("введите число!");
            result_operation_str.setText("");
            operand_in_code.setText("");
            result_in_code.setText("");
        }
    }
    private void PrintResult_operation_str(TextView result_operation_str, double a, double b) {
        double result_sum = a + b;
        double result_sub = a - b;
        result_operation_str.setText(
                "первый операнд в памяти компьютера\n" +
                a + " = " +  Long.toBinaryString(Double.doubleToRawLongBits(a)).substring(0, 35) + "\n" +
                        "\nвторой операнд в памяти компьютера\n" +
                        b + " = " + Long.toBinaryString(Double.doubleToRawLongBits(b)).substring(0, 35) + "\n" +
                        "\nсумма:\n" +
                a + " + " + b + " = " + result_sum + " = " + Long.toBinaryString(Double.doubleToRawLongBits(result_sum)) + "\n" +
                        "\nразность:\n" +
                        a + " - " + b + " = " + result_sub + " = " + Long.toBinaryString(Double.doubleToRawLongBits(result_sub))
        );
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
                PrintResult_operation_str(result_operation_str, Double.parseDouble(first_num.getText().toString()) , Double.parseDouble(second_num.getText().toString()));
                PrintOperandInCode(operand_in_code, this.a, this.b);
                PrintResultInCode(result_in_code, this.a, this.b);
            }
        }
    }

    private void PrintResultInCode(TextView result_in_code,FloatingNum a,FloatingNum b) {
       ///try {
       ///    try {
       ///        String res = "сумма:\n";
       ///        res += summ(a, b).printWholeNumber();
       ///        res += "\nразность:\n";
       ///        res += "\n";
       ///        result_in_code.setText(res);
       ///    } catch (Exception e){};
       ///}
       ///catch (Exception e){};
    }

    private void PrintOperandInCode(TextView operand_in_code,FloatingNum a,FloatingNum b) {
       // try {
       //     String res = "первый операнд в двоичной системе:\n";
       //     res += a.printWholeNumber();
       //     res += "\nвторой:\n";
       //     res += b.printWholeNumber();
       //     operand_in_code.setText(res);
       // } catch (Exception e){};
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}