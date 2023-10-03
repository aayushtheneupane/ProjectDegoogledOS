package com.google.android.setupdesign.template;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.template.Mixin;
import com.google.android.setupdesign.R$id;
import com.google.android.setupdesign.R$styleable;

public class HeaderMixin implements Mixin {
    private final TemplateLayout templateLayout;

    public HeaderMixin(TemplateLayout templateLayout2, AttributeSet attributeSet, int i) {
        this.templateLayout = templateLayout2;
        TypedArray obtainStyledAttributes = templateLayout2.getContext().obtainStyledAttributes(attributeSet, R$styleable.SucHeaderMixin, i, 0);
        CharSequence text = obtainStyledAttributes.getText(R$styleable.SucHeaderMixin_sucHeaderText);
        if (text != null) {
            setText(text);
        }
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R$styleable.SucHeaderMixin_sucHeaderTextColor);
        if (colorStateList != null) {
            setTextColor(colorStateList);
        }
        obtainStyledAttributes.recycle();
    }

    public void applyPartnerCustomizationStyle() {
        applyPartnerCustomizationStyle(this.templateLayout.getContext(), (TextView) this.templateLayout.findManagedViewById(R$id.suc_layout_title));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007c, code lost:
        if (r0.equals("center") != false) goto L_0x0080;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyPartnerCustomizationStyle(android.content.Context r7, android.widget.TextView r8) {
        /*
            r6 = this;
            if (r8 == 0) goto L_0x009e
            com.google.android.setupcompat.internal.TemplateLayout r0 = r6.templateLayout
            boolean r1 = r0 instanceof com.google.android.setupdesign.GlifLayout
            if (r1 == 0) goto L_0x009e
            com.google.android.setupdesign.GlifLayout r0 = (com.google.android.setupdesign.GlifLayout) r0
            boolean r0 = r0.shouldApplyPartnerHeavyThemeResource()
            if (r0 == 0) goto L_0x009e
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r0 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r1 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_HEADER_TEXT_COLOR
            int r0 = r0.getColor(r7, r1)
            if (r0 == 0) goto L_0x0023
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r0)
            r6.setTextColor(r0)
        L_0x0023:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r0 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r1 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_HEADER_TEXT_SIZE
            float r0 = r0.getDimension(r7, r1)
            r1 = 0
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x0035
            r6.setTextSize(r0)
        L_0x0035:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r0 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r1 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_HEADER_FONT_FAMILY
            java.lang.String r0 = r0.getString(r7, r1)
            r1 = 0
            if (r0 == 0) goto L_0x0049
            android.graphics.Typeface r0 = android.graphics.Typeface.create(r0, r1)
            r6.setFontFamily(r0)
        L_0x0049:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r0 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r2 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_LAYOUT_GRAVITY
            java.lang.String r0 = r0.getString(r7, r2)
            if (r0 == 0) goto L_0x0091
            java.util.Locale r2 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toLowerCase(r2)
            r2 = -1
            int r3 = r0.hashCode()
            r4 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            r5 = 1
            if (r3 == r4) goto L_0x0076
            r1 = 109757538(0x68ac462, float:5.219839E-35)
            if (r3 == r1) goto L_0x006c
            goto L_0x007f
        L_0x006c:
            java.lang.String r1 = "start"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x007f
            r1 = r5
            goto L_0x0080
        L_0x0076:
            java.lang.String r3 = "center"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x007f
            goto L_0x0080
        L_0x007f:
            r1 = r2
        L_0x0080:
            if (r1 == 0) goto L_0x008c
            if (r1 == r5) goto L_0x0085
            goto L_0x0091
        L_0x0085:
            r0 = 8388611(0x800003, float:1.1754948E-38)
            r6.setGravity(r8, r0)
            goto L_0x0091
        L_0x008c:
            r0 = 17
            r6.setGravity(r8, r0)
        L_0x0091:
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r8 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r7)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r0 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_HEADER_AREA_BACKGROUND_COLOR
            int r7 = r8.getColor(r7, r0)
            r6.setBackgroundColor(r7)
        L_0x009e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.template.HeaderMixin.applyPartnerCustomizationStyle(android.content.Context, android.widget.TextView):void");
    }

    public TextView getTextView() {
        return (TextView) this.templateLayout.findManagedViewById(R$id.suc_layout_title);
    }

    public void setText(int i) {
        TextView textView = getTextView();
        if (textView != null) {
            textView.setText(i);
        }
    }

    public void setText(CharSequence charSequence) {
        TextView textView = getTextView();
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public CharSequence getText() {
        TextView textView = getTextView();
        if (textView != null) {
            return textView.getText();
        }
        return null;
    }

    private void setTextSize(float f) {
        TextView textView = getTextView();
        if (textView != null) {
            textView.setTextSize(0, f);
        }
    }

    public void setTextColor(ColorStateList colorStateList) {
        TextView textView = getTextView();
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setBackgroundColor(int i) {
        TextView textView = getTextView();
        if (textView != null) {
            ViewParent parent = textView.getParent();
            if (parent instanceof LinearLayout) {
                ((LinearLayout) parent).setBackgroundColor(i);
            }
        }
    }

    private void setFontFamily(Typeface typeface) {
        TextView textView = getTextView();
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    public ColorStateList getTextColor() {
        TextView textView = getTextView();
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    private void setGravity(TextView textView, int i) {
        textView.setGravity(i);
    }
}
