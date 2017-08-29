package io.github.liuu.app.calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

public class MainActivity extends AppCompatActivity {

    private boolean evalBeforeEqual = false;
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
            case R.id.btn_dot:{
                Button btn = (Button) view;
                String strAdded = btn.getText().toString();
                TextView result = (TextView) findViewById(R.id.result_area);
                String strContent = result.getText().toString();
                if(strContent.indexOf(".")>=0 && strAdded.equals(".")){
                    break;
                }

                if (evalBeforeEqual){
                    result.setText(strAdded);
                    evalBeforeEqual = false;
                }
                else {
                    String strNewContent = strContent + strAdded;
                    result.setText(strNewContent);
                }
            }
            break;
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:{

                Button btn = (Button) view;
                String strASMD = btn.getText().toString();
                TextView formula = (TextView) findViewById(R.id.formula_area);
                TextView result = (TextView) findViewById(R.id.result_area);


                String strFormula = String.valueOf(formula.getText());
                String strResult = String.valueOf(result.getText());
                if (strFormula.length() == 0 && strResult.length() > 0){
                    formula.setText(strResult + strASMD);
                    evalBeforeEqual = true;
                    break;
                }

                if (strFormula.length() > 0 && strResult.length() == 0){
                    break;
                }

//                if (strASMD.indexOf(formula.getText().charAt(strFormula.length() - 1)) >=0){
//                    break;
//                }

                if (evalBeforeEqual){

                    formula.setText(strFormula.subSequence(0, strFormula.length() - 1) + strASMD);
                    break;
                }
                else{
                    formula.setText(strFormula + strResult + strASMD );

                    try {
                        Symbols s = new Symbols();

                        double res = s.eval(strFormula + strResult);
                        String  resultText = String.valueOf(res);
                        if (resultText.length() > 2 && resultText.endsWith(".0")){
                            resultText = resultText.substring(0, resultText.length()-2);
                        }

                        result.setText(resultText);
                        evalBeforeEqual = true;

                    } catch (SyntaxException e) {
                        Toast.makeText(MainActivity.this, "错误！", Toast.LENGTH_SHORT).show();
                    }

                }

            }
                break;
            case R.id.btn_ce: {
                TextView result = (TextView) findViewById(R.id.result_area);
                result.setText("");
                break;
            }

            case R.id.btn_c: {
                TextView formula = (TextView) findViewById(R.id.formula_area);
                formula.setText("");

                TextView result = (TextView) findViewById(R.id.result_area);
                result.setText("");

                evalBeforeEqual = false;
            }
            break;
            case R.id.btn_del:{
                if(evalBeforeEqual) {
                    break;
                }

                TextView result = (TextView) findViewById(R.id.result_area);
                CharSequence resultText = result.getText();
                if (resultText != null && resultText.length() > 0) {
                    result.setText(resultText.subSequence(0, resultText.length() - 1));
                }
            }

            break;
            case R.id.btn_equ:{

                if(!evalBeforeEqual) {

                    TextView formula = (TextView) findViewById(R.id.formula_area);
                    TextView result = (TextView) findViewById(R.id.result_area);
                    String strContent = formula.getText().toString();

                    try {
                        Symbols s = new Symbols();

                        double res = s.eval(strContent + result.getText());
                        String resultText = String.valueOf(res);
                        if (resultText.length() > 2 && resultText.endsWith(".0")) {
                            resultText = resultText.substring(0, resultText.length() - 2);
                        }

                        result.setText(resultText);
                        formula.setText("");
                        evalBeforeEqual = true;

                    } catch (SyntaxException e) {
                        Toast.makeText(MainActivity.this, "错误！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
    }
}
