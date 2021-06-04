package com.example.bigfivetester;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.bigfivetester.vo.QuestionVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestProcessIntent extends AppCompatActivity {
    private final static String FACTOR_E = "E";
    private final static String FACTOR_A = "A";
    private final static String FACTOR_S = "S";
    private final static String FACTOR_C = "C";
    private final static String FACTOR_I = "I";


    TextView questionText;
    ProgressBar progBar;
    TextView progPerText;
    LinearLayout btnGroup;

    Button vaBtn;
    Button aBtn;
    Button mBtn;
    Button iaBtn;
    Button viaBtn;
    Button resBtn;

    List<QuestionVO> qList;
    Map<String, Float> scores = new HashMap<>();

    float es = 0;
    float as = 0;
    float cs = 0;
    float ss = 0;
    float is = 0;

    int qIdx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.test_question);

        final Context ctx = getApplicationContext();

        questionText = findViewById(R.id.questionText);
        progBar = findViewById(R.id.progBar);
        progPerText = findViewById(R.id.progPerText);
        btnGroup = findViewById(R.id.btnGroup);

        vaBtn = findViewById(R.id.vaBtn);
        aBtn = findViewById(R.id.aBtn);
        mBtn = findViewById(R.id.mBtn);
        iaBtn = findViewById(R.id.iaBtn);
        viaBtn = findViewById(R.id.viaBtn);
        resBtn = findViewById(R.id.resBtn);



        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qIdx < qList.size() - 1){
                    float val = 0;

                    QuestionVO q = qList.get(qIdx);
                    val = q.isPlusFlag() ? 1 : -1;

                    switch(v.getId()) {
                        case R.id.vaBtn:
                            val *= 5;
                            break;
                        case R.id.aBtn:
                            val *= 2.5;
                            break;
                        case R.id.mBtn:
                            val *= 0;
                            break;
                        case R.id.iaBtn:
                            val *= -2.5;
                            break;
                        case R.id.viaBtn:
                            val *= -5;
                            break;
                        default:
                            break;
                    }

                    switch(q.getFactorType()) {
                        case FACTOR_E:
                            es += val;
                            break;
                        case FACTOR_A:
                            as += val;
                            break;
                        case FACTOR_S:
                            ss += val;
                            break;
                        case FACTOR_C:
                            cs += val;
                            break;
                        case FACTOR_I:
                            is += val;
                            break;
                        default:
                            break;
                    }


                }else {
                    btnGroup.setVisibility(View.INVISIBLE);
                    resBtn.setVisibility(View.VISIBLE);

                }

                goNextQuestion();
            }
        };

        vaBtn.setOnClickListener(clickListener);
        aBtn.setOnClickListener(clickListener);
        mBtn.setOnClickListener(clickListener);
        iaBtn.setOnClickListener(clickListener);
        viaBtn.setOnClickListener(clickListener);

        resBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Intent resIntent = new Intent(ctx, TestResultIntent.class);

            resIntent.putExtra(FACTOR_E, es);
            resIntent.putExtra(FACTOR_A, as);
            resIntent.putExtra(FACTOR_S, ss);
            resIntent.putExtra(FACTOR_C, cs);
            resIntent.putExtra(FACTOR_I, is);


            Toast.makeText(getApplicationContext(), es + "", Toast.LENGTH_LONG).show();

            startActivity(resIntent);
            finish();
            }
        });

        Gson gson = new Gson();

        try{
            InputStream is = getAssets().open("bigfive.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);

            qList= gson.fromJson( jsonObject.get("questions").toString(), new TypeToken<List<QuestionVO>>(){}.getType());

            goNextQuestion();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void goNextQuestion() {
        if(qIdx < qList.size() - 1){
            qIdx++;
            QuestionVO qvo = qList.get(qIdx);

            questionText.setText(qvo.getText());
            progPerText.setText((qIdx) + "%");
            progBar.setProgress(qIdx);
        } else {
            questionText.setText("당신의 성향 결과는?");
            progPerText.setText("100%");
            progBar.setProgress(100);

            Toast.makeText(getApplicationContext(), "질문이 모두 끝났습니다.", Toast.LENGTH_LONG).show();
        }

    }
}
