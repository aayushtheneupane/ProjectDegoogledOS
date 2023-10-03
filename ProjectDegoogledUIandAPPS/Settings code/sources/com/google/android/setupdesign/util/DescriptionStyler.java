package com.google.android.setupdesign.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;

public class DescriptionStyler {
    public static void applyPartnerCustomizationStyle(TextView textView) {
        Context context = textView.getContext();
        int color = PartnerConfigHelper.get(context).getColor(context, PartnerConfig.CONFIG_DESCRIPTION_TEXT_COLOR);
        if (color != 0) {
            setTextColor(textView, color);
        }
        int color2 = PartnerConfigHelper.get(context).getColor(context, PartnerConfig.CONFIG_DESCRIPTION_LINK_TEXT_COLOR);
        if (color2 != 0) {
            setLinkTextColor(textView, color2);
        }
        float dimension = PartnerConfigHelper.get(context).getDimension(context, PartnerConfig.CONFIG_DESCRIPTION_TEXT_SIZE, 0.0f);
        if (dimension != 0.0f) {
            setTextSize(textView, dimension);
        }
        Typeface create = Typeface.create(PartnerConfigHelper.get(context).getString(context, PartnerConfig.CONFIG_DESCRIPTION_FONT_FAMILY), 0);
        if (create != null) {
            setFontFamily(textView, create);
        }
        setGravity(textView, PartnerStyleHelper.getLayoutGravity(context));
    }

    static void setTextSize(TextView textView, float f) {
        if (textView != null) {
            textView.setTextSize(0, f);
        }
    }

    static void setFontFamily(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    static void setTextColor(TextView textView, int i) {
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    static void setLinkTextColor(TextView textView, int i) {
        if (textView != null) {
            textView.setLinkTextColor(i);
        }
    }

    static void setGravity(TextView textView, int i) {
        if (textView != null) {
            textView.setGravity(i);
        }
    }
}
