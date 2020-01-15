package com.example.mytextcolor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytextcolor.widget.ClickListener;
import com.example.mytextcolor.widget.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextOnClickListener, ClickListener {

    private TextView tv_show2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SkipTextView tv_show = findViewById(R.id.tv_show);
        String       soruce  = "温馨提示，如需了解折算年化手续费率/利率，请点击此处";
        tv_show.setText(soruce)
                .setStart(soruce.length() - 5)
                .setEnd(soruce.length())
//                .setRegex("\\(([\u4e00-\u9fa5\\w])+\\)")
                .setTextClickListener(this);


        tv_show2 = (TextView) findViewById(R.id.tv_show2);
        initData();
        Utils.setText(this, tv_show2, str, colors, null, this, null);
    }

    private ArrayList<String>  str    = new ArrayList<>();
    private ArrayList<Integer> colors = new ArrayList<>();
    private ArrayList<Float>   sizes  = new ArrayList<>();

    /**
     * 装在数据
     */
    private void initData() {
        str.clear();
        colors.clear();
        sizes.clear();

        str.add("hellohellohellohellohellohello");
        str.add("温馨提示");
        str.add("，如需了解折算年化手续费率/利率，请点击此处");
        colors.add(Color.BLACK);
        colors.add(Color.parseColor("#72B8CC"));
        colors.add(Color.RED);

    }

    @Override
    public void onClickListener() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }

    /**
     * 自定义文字区域的点击事件
     * @param position
     * @param v
     */
    @Override
    public void click(int position, View v) {
        switch (v.getId()) {
            case R.id.tv_show2:
//                switch (position) {
//                    case 0:
//                        break;
//                    case 1:
//                        break;
//                }
                Toast.makeText(this, "您点击了-->>" + str.get(position), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
