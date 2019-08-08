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

public class MainActivity extends AppCompatActivity implements TextOnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SkipTextView tv_show = findViewById(R.id.tv_show);
        String soruce = "温馨提示，如需了解折算年化手续费率/利率，请点击此处";
        tv_show.setText(soruce)
                .setStart(soruce.length()-5)
                .setEnd(soruce.length())
//                .setRegex("\\(([\u4e00-\u9fa5\\w])+\\)")
                .setTextClickListener(this);
    }

    @Override
    public void onClickListener() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
