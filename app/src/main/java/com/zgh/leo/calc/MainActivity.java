package com.zgh.leo.calc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.function.DoublePredicate;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;

    Button btn_point;// 小数点
    Button btn_divide;// 除以
    Button btn_multiply;// 乘以
    Button btn_minus;// 减去
    Button btn_plus;// 加
    Button btn_equal;// 等于

    Button btn_clear;//清空所有字符
    Button btn_del;//删除一个字符

    EditText et_input;//显示框

    boolean clear_flag;// 是否清空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置最下方文本跑马灯效果
        TextView welcome= this.findViewById(R.id.leo);
        welcome.setSelected(true);

        btn_0 = this.findViewById(R.id.btn_0);
        btn_1 = this.findViewById(R.id.btn_1);
        btn_2 = this.findViewById(R.id.btn_2);
        btn_3 = this.findViewById(R.id.btn_3);
        btn_4 = this.findViewById(R.id.btn_4);
        btn_5 = this.findViewById(R.id.btn_5);
        btn_6 = this.findViewById(R.id.btn_6);
        btn_7 = this.findViewById(R.id.btn_7);
        btn_8 = this.findViewById(R.id.btn_8);
        btn_9 = this.findViewById(R.id.btn_9);
        btn_point = this.findViewById(R.id.btn_point);// 小数点
        btn_divide = this.findViewById(R.id.btn_divide);// 除以
        btn_multiply = this.findViewById(R.id.btn_multiply);// 乘以
        btn_minus = this.findViewById(R.id.btn_minus);// 减去
        btn_plus = this.findViewById(R.id.btn_plus);// 加
        btn_equal = this.findViewById(R.id.btn_equal);// 等于

        btn_clear = this.findViewById(R.id.btn_clear);
        btn_del = this.findViewById(R.id.btn_del);
        et_input = this.findViewById(R.id.et_input);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_point.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);

        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();
        switch (v.getId()){
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
            case R.id.btn_point:
                if (clear_flag){
                    clear_flag = false;
                    et_input.setText("");
                    return;
                }
                et_input.setText(str+((Button)v).getText());
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                et_input.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.btn_equal:
                getResult();
                break;
            case R.id.btn_del:
                if(!str.equals("")){
                    et_input.setText(str.substring(0,str.length()-1));
                }
                else et_input.setText("");
                break;
            case R.id.btn_clear:
                et_input.setText("");
                break;

        }
    }
    private void getResult(){
        //计算结果
        String exp=et_input.getText().toString();
        //只点击了等号
        if(exp.equals("")){
            return;
        }
        //点击了数字 但是没有点击运算符
        if (!exp.contains(" ")){
            return;
        }

        if (clear_flag){
            clear_flag = false;
            return;
        }
        clear_flag= true;
        String s1 = exp.substring(0,exp.indexOf(" "));//运算符前面的数字
        String op = exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);//运算符
        String s2 = exp.substring(exp.indexOf(" ")+3);//运算符后面的数字
        double result = 0;
        if(!s1.equals("") && !s2.equals("")){
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")){
                result = d1 + d2;
            }else if (op.equals("-")){
                result = d1 - d2;
            }else if (op.equals("×")){
                result = d1 * d2;
            }else if (op.equals("÷")){
                if (d2==0){
                    result = 0;
                }else result = d1 / d2;
            }
            if (Math.ceil(result)==result){
                int res = (int) result;
                et_input.setText(res+"");
            }else et_input.setText(result+"");
        }else if (!s1.equals("") && s2.equals("")){
            et_input.setText(exp);
        }else if (s1.equals("") && !s2.equals("")){
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")){
                result = d2;
            }else if (op.equals("-")){
                result = -d2;
            }else if (op.equals("×")){
                result = 0;
            }else if (op.equals("÷")){
                    result = 0;
            }
            if (Math.ceil(result)==result){
                int res = (int) result;
                et_input.setText(res+"");
            }else et_input.setText(result+"");
        }else et_input.setText("");
    }
}
