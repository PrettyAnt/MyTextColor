package com.example.mytextcolor.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;


import java.util.ArrayList;

public class Utils {
    public static void setText(Context context, TextView tv,
                               ArrayList<String> str, ArrayList<Integer> color,
                               ArrayList<Float> text_size_list, ClickListener clickListener, Drawable drawable) {
        // 累加数组所有的字符串为一个字符串
        StringBuffer long_str = new StringBuffer();
        for (int i = 0; i < str.size(); i++) {
            long_str.append(str.get(i));
        }
        SpannableString builder = new SpannableString(long_str.toString());
        // 设置不同字符串的点击事件
        if (clickListener != null) {
            for (int i = 0; i < str.size(); i++) {
                int p    = i;
                int star = long_str.toString().indexOf(str.get(i));
                int end  = star + str.get(i).length();
                builder.setSpan(new Clickable(clickListener, p), star, end,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        // 设置不同字符串的颜色
        if (color != null) {
            ArrayList<ForegroundColorSpan> foregroundColorSpans = new ArrayList<ForegroundColorSpan>();
            for (int i = 0; i < color.size(); i++) {
                foregroundColorSpans.add(new ForegroundColorSpan(color.get(i)));
            }
            for (int i = 0; i < str.size(); i++) {
                int star = long_str.toString().indexOf(str.get(i));
                int end  = star + str.get(i).length();
                builder.setSpan(foregroundColorSpans.get(i), star, end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        // 设置不同字符串的字号
        if (text_size_list != null) {
            ArrayList<AbsoluteSizeSpan> absoluteSizeSpans = new ArrayList<AbsoluteSizeSpan>();
            for (int i = 0; i < color.size(); i++) {
                absoluteSizeSpans.add(new AbsoluteSizeSpan(sp2px(context,
                        text_size_list.get(i))));
            }
            for (int i = 0; i < str.size(); i++) {
                int star = long_str.toString().indexOf(str.get(i));
                int end  = star + str.get(i).length();
                builder.setSpan(absoluteSizeSpans.get(i), star, end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        // 设置点击后的颜色为透明，否则会一直出现高亮
        tv.setHighlightColor(Color.TRANSPARENT);
        tv.setClickable(true);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        if (drawable != null) {
            int size = sp2px(context, 12f);
            drawable.setBounds(0, 0, size, size);
            ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            builder.setSpan(imageSpan, long_str.length() - 1, long_str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(builder);
    }

    private static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}