package io.github.liuu.app.calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:{
                Button btn = (Button) view;
                String strAdded = btn.getText().toString();
                TextView formula = (TextView) findViewById(R.id.formula_area);
                String strContent = formula.getText().toString();
                String strNewContent = strContent + strAdded;
                formula.setText(strNewContent);
            }
                break;
            case R.id.btn_dot:
            case R.id.btn_c: {
                TextView formula = (TextView) findViewById(R.id.formula_area);
                formula.setText("");

                TextView result = (TextView) findViewById(R.id.result_area);
                result.setText("");
            }
            break;
            case R.id.btn_del:{
                TextView formula = (TextView) findViewById(R.id.formula_area);
                if (formula.getText()!=null && formula.getText().length() > 0){
                    formula.setText(formula.getText().subSequence(0, formula.getText().length()-1));
                }

                TextView result = (TextView) findViewById(R.id.result_area);
                CharSequence resultText = result.getText();
                if (resultText!=null && resultText.length() > 0){
                    result.setText(resultText.subSequence(0, resultText.length()-1));
                }
            }

            break;
            case R.id.btn_equ:{

            }
            break;
        }
    }
}
