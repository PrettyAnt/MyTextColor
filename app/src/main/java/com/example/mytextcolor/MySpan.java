package com.example.mytextcolor;

import android.text.NoCopySpan;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import androidx.annotation.NonNull;

/**
 * @author ChenYu
 * Author's github https://github.com/PrettyAnt
 * <p>
 * Created on 11:17  2019-08-08
 * PackageName : com.example.mytextcolor
 * describle :
 */
public class MySpan extends SpannableString {

    private final CharSequence source;
    //    private String content;
    private SpannableString spannableString;
    private TextOnClickListener textOnClickListener;

    /**
     * For the backward compatibility reasons, this constructor copies all spans including {@link
     * NoCopySpan}.
     *
     * @param source source text
     */
    public MySpan(CharSequence source) {
        super(source);
        this.source = source;
    }

    public static MySpan getInstance(CharSequence source) {
        return new MySpan(source);
    }

    public MySpan setTextOnClickListener(TextOnClickListener textOnClickListener){
        this.textOnClickListener = textOnClickListener;
        return this;
    }

    @Override
    public void setSpan(Object what, int start, int end, int flags) {
        super.setSpan(what, start, end, flags);
    }
}
