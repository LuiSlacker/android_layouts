package com.uninorte.layoutsinclase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class MainActivity extends AppCompatActivity {

    private Queue<String> queue;
    TextView txt;
    String display = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint);

        txt = (TextView) findViewById(R.id.textView);
        queue = new LinkedBlockingQueue<>();
    }

    public void onBtnClick(View view) {
        String sign = view.getTag().toString();
        queue.add(sign);
        display += sign;
        txt.setText(display);
    }

    public void calculate(View view) {
        boolean shouldAdd = false;

        int firstNumber = retreiveNumber();
        String operant = queue.poll();
        if (operant.equals("+")) shouldAdd = true;
        int secondNumber = retreiveNumber();

        int result = shouldAdd ? firstNumber + secondNumber : firstNumber - secondNumber;
        display = result+"";
        txt.setText(result+"");
        queue.add(result+"");

    }

    private int retreiveNumber() {
        int number = -1;
        String sign = queue.peek();
        while (sign != null && !sign.equals("+") && !sign.equals("-")) {
            number = (number == -1)? Integer.valueOf(queue.poll()) : number * 10 + Integer.valueOf(queue.poll());
            sign = queue.peek();
        }
        return number;
    }
}
