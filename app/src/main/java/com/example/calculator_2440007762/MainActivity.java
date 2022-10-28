package com.example.calculator_2440007762;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import java.math.BigDecimal;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button zero, one, two, three, four, five, six, seven, eight, nine;
    Button opbc, clbc, pctng, clear, plus, slash, min, multi, point, equals;
    TextView answer, res;

    private void createID(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answer = findViewById(R.id.answer);
        res = findViewById(R.id.res);
        createID(zero, R.id.zero);
        createID(one, R.id.one);
        createID(two, R.id.two);
        createID(three, R.id.three);
        createID(four, R.id.four);
        createID(five, R.id.five);
        createID(six, R.id.six);
        createID(seven, R.id.seven);
        createID(eight, R.id.eight);
        createID(nine, R.id.nine);
        createID(opbc, R.id.opbc);
        createID(clbc, R.id.clbc);
        createID(pctng, R.id.pcntg);
        createID(clear, R.id.clear);
        createID(plus, R.id.plus);
        createID(slash, R.id.slash);
        createID(min, R.id.min);
        createID(multi, R.id.mutiple);
        createID(point, R.id.point);
        createID(equals, R.id.equals);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String btnText =btn.getText().toString();
        String calculate = answer.getText().toString();

        if (btnText.equals("AC")){
            answer.setText("");
            res.setText("0");
            return;
        }
        if (btnText.equals("=")){
            answer.setText(res.getText());
            return;
        }
        else {
            calculate += btnText;
        }

        answer.setText(calculate);
        String ress = calculateRes(calculate);
        if (!ress.equals("Error")){
            Double resNumber = Double.parseDouble(ress);
            BigDecimal bdc = new BigDecimal(resNumber);
            Double rounding = bdc.setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();

            String fRes = rounding.toString();
            if (fRes.endsWith(".0")){
                fRes = fRes.replace(".0", "");
            }
            res.setText(fRes);
        }
    }

    private String calculateRes(String input) {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result = context.evaluateString(scriptable, input, "Javascript", 1, null).toString();
            return result;
        }catch(Exception e){
            return "Error";
        }
    }
}