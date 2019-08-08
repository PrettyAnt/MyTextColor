package com.example.mytextcolor;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ChenYu
 * Author's github https://github.com/PrettyAnt
 * <p>
 * Created on 14:33  2019-08-08
 * PackageName : com.example.mytextcolor
 * describle :给一段文字的某个区域设置颜色并且设置可点击
 * 提供了两种寻找可点击区域的位置：
 * 1、通过设置start、end的方法获取字符串中可点击区域位置
 * 2、正则表达式进行匹配
 *
 */
public class SkipTextView extends AppCompatTextView {
    private Context mContext;
    private TextOnClickListener mTextOnClickListener;
    private String source;
    private int start = 0;
    private int end;
    private boolean isRegex = false;

    public SkipTextView(Context context) {
        super(context);
        mContext = context;
    }

    public SkipTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public SkipTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


    /**
     * 设置文本内容
     *
     * @param source
     * @return
     */
    public SkipTextView setText(String source) {
        super.setText("");
        this.source = source;
        return this;
    }

    /**
     * 设置点击文本的起始位置
     *
     * @param start
     * @return
     */
    public SkipTextView setStart(int start) {
        this.start = start;
        return this;
    }

    /**
     * 设置点击文本的结束位置
     *
     * @param end
     * @return
     */
    public SkipTextView setEnd(int end) {
        this.end = end;
        return this;
    }

    /**
     * 支持正则匹配  eg:regex="\\(([\u4e00-\u9fa5\\w])+\\)"
     *
     * @param regex
     * @return
     */
    public SkipTextView setRegex(String regex) {
        isRegex = false;
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(source);
        if (matcher.find()) {
            // 获取匹配到的具体字符
            String key = matcher.group();
            // 匹配字符串的开始位置
            start = matcher.start();
            // 匹配字符串的结束的位置
            end = start + key.length();
            Log.i("sss", source.substring(start, end));
            isRegex = true;
        }
        return this;
    }

    /**
     * 确定点击的区域，并给需要点击的区域设置点击事件
     *
     * @param textClickListener
     */
    public void setTextClickListener(TextOnClickListener textClickListener) {
        mTextOnClickListener = textClickListener;
        if (end == 0) {
            end = source.length();
        }
        if (isRegex) {
            source = source.substring(0, start) + source.substring(start + 1, end - 1) + source.substring(end);
            end = end - 2;
        }
        SpannableString spStr = new SpannableString(source);
        spStr.setSpan(new ClickableSpan() {
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(mContext.getColor(R.color.colorClick));//设置文字颜色
                ds.setUnderlineText(false); //设置是否显示下划线
            }

            @Override
            public void onClick(View widget) {
                widget.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTextOnClickListener.onClickListener();
                    }
                });
            }
        }, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        setHighlightColor(mContext.getColor(R.color.colorPrimary));//点击效果(点击时文字的背景颜色)
        setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件
        setText(spStr);
    }
}
