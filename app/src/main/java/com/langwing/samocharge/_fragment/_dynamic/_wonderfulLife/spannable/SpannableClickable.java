package com.langwing.samocharge._fragment._dynamic._wonderfulLife.spannable;

import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.langwing.samocharge.ChargingApplication;
import com.langwing.samocharge.R;

public abstract class SpannableClickable extends ClickableSpan implements View.OnClickListener {

    private int textColor;

    public SpannableClickable() {
        this.textColor = ContextCompat.getColor(ChargingApplication.application, R.color.gray_82);
    }

    public SpannableClickable(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);

        ds.setColor(textColor);
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}
