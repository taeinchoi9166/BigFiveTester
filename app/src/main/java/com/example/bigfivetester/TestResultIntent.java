package com.example.bigfivetester;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.bigfivetester.vo.QuestionVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestResultIntent extends AppCompatActivity {
    private final static String FACTOR_E = "E";
    private final static String FACTOR_A = "A";
    private final static String FACTOR_S = "S";
    private final static String FACTOR_C = "C";
    private final static String FACTOR_I = "I";

    private final static String SETN_PRE = "당신은 ";
    private final static String SETN_POST = "인 성향이 있습니다.";

    Button eBtn;
    Button aBtn;
    Button cBtn;
    Button sBtn;
    Button iBtn;
    Button resetBtn;
    TextView expText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_result);

        eBtn = findViewById(R.id.eBtn);
        aBtn = findViewById(R.id.aBtn);
        cBtn = findViewById(R.id.cBtn);
        sBtn = findViewById(R.id.sBtn);
        iBtn = findViewById(R.id.iBtn);
        resetBtn = findViewById(R.id.resetBtn);
        expText = findViewById(R.id.expText);

        Intent intent = getIntent();

        final float es = intent.getExtras().getFloat(FACTOR_E, 0);
        final float as = intent.getExtras().getFloat(FACTOR_A, 0);
        final float ss = intent.getExtras().getFloat(FACTOR_S, 0);
        final float cs = intent.getExtras().getFloat(FACTOR_C, 0);
        final float is = intent.getExtras().getFloat(FACTOR_I, 0);


        eBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String t = (Math.abs(es) > 65 ? "매우 " : "");
                if(es > 0) {
                    t += "외향적";
                } else if(es < 0) {
                    t += "내향적";
                } else {
                    t += "외향성 중립";
                }
                expText.setText(SETN_PRE + t + SETN_POST);
            }
        });

        aBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String t = (Math.abs(as) > 65 ? "매우 " : "");
                if(as > 0) {
                    t += "관용적";
                } else if(as < 0) {
                    t += "배타적";
                } else {
                    t += "호감성 중립";
                }
                expText.setText(SETN_PRE + t + SETN_POST);
            }
        });

        cBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String t = (Math.abs(cs) > 65 ? "매우 " : "");
                if(cs > 0) {
                    t += "성실적";
                } else if(cs < 0) {
                    t += "자유분방적";
                } else {
                    t += "성실성 중립";
                }
                expText.setText(SETN_PRE + t + SETN_POST);
            }
        });

        sBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String t = (Math.abs(ss) > 65 ? "매우 " : "");
                if(ss > 0) {
                    t += "감정적";
                } else if(ss < 0) {
                    t += "이성적";
                } else {
                    t += "심리 안정성 중립";
                }
                expText.setText(SETN_PRE + t + SETN_POST);
            }
        });

        iBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String t = (Math.abs(is) > 65 ? "매우 " : "");
                if(is > 0) {
                    t += "창의적";
                } else if(is < 0) {
                    t += "관습적";
                } else {
                    t += "개방성 중립";
                }
                expText.setText(SETN_PRE + t + SETN_POST);
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
