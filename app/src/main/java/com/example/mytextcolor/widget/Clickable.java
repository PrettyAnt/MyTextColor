package com.example.mytextcolor.widget;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class Clickable extends ClickableSpan implements View.OnClickListener {
    private final ClickListener clickListener;
    private int position;

    public Clickable(ClickListener clickListener, int position) {
        this.clickListener = clickListener;
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        clickListener.click(position,v);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        // TODO Auto-generated method stub
        super.updateDrawState(ds);
        ds.setColor(Color.WHITE); // 设置文件颜色
        ds.setUnderlineText(false);
    }
}