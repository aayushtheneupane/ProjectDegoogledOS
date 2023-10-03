package com.android.dialer.theme.base.impl;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.ContextThemeWrapper;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.theme.base.Theme;

public class AospThemeImpl implements Theme {
    private int colorAccent = -2;
    private int colorIcon = -2;
    private int colorIconSecondary = -2;
    private int colorPrimary = -2;
    private int colorPrimaryDark = -2;
    private int textColorPrimary = -2;
    private int textColorSecondary = -2;

    public AospThemeImpl(Context context) {
        Context applicationContext = context.getApplicationContext();
        getTheme();
        applicationContext.setTheme(2131886327);
        Resources.Theme theme = applicationContext.getTheme();
        getTheme();
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(2131886327, new int[]{16843827, 16843828, 16843829, 16842806, 16842808, 16842809, 16842906, 16842801, 16844002, R.attr.colorIcon, R.attr.colorIconSecondary, R.attr.colorTextOnUnthemedDarkBackground, R.attr.colorIconOnUnthemedDarkBackground});
        this.colorPrimary = obtainStyledAttributes.getColor(0, -2);
        this.colorPrimaryDark = obtainStyledAttributes.getColor(1, -2);
        this.colorAccent = obtainStyledAttributes.getColor(2, -2);
        this.textColorPrimary = obtainStyledAttributes.getColor(3, -2);
        this.textColorSecondary = obtainStyledAttributes.getColor(4, -2);
        obtainStyledAttributes.getColor(5, -2);
        obtainStyledAttributes.getColor(6, -2);
        obtainStyledAttributes.getColor(7, -2);
        obtainStyledAttributes.getColor(8, -2);
        this.colorIcon = obtainStyledAttributes.getColor(9, -2);
        this.colorIconSecondary = obtainStyledAttributes.getColor(10, -2);
        obtainStyledAttributes.getColor(11, -2);
        obtainStyledAttributes.getColor(12, -2);
        obtainStyledAttributes.recycle();
    }

    public int getColorAccent() {
        Assert.checkArgument(this.colorAccent != -2);
        return this.colorAccent;
    }

    public int getColorIcon() {
        Assert.checkArgument(this.colorIcon != -2);
        return this.colorIcon;
    }

    public int getColorIconSecondary() {
        Assert.checkArgument(this.colorIconSecondary != -2);
        return this.colorIconSecondary;
    }

    public int getColorPrimary() {
        Assert.checkArgument(this.colorPrimary != -2);
        return this.colorPrimary;
    }

    public int getColorPrimaryDark() {
        Assert.checkArgument(this.colorPrimaryDark != -2);
        return this.colorPrimaryDark;
    }

    public int getTextColorPrimary() {
        Assert.checkArgument(this.textColorPrimary != -2);
        return this.textColorPrimary;
    }

    public int getTextColorSecondary() {
        Assert.checkArgument(this.textColorSecondary != -2);
        return this.textColorSecondary;
    }

    public int getTheme() {
        return 1;
    }

    public Context getThemedContext(Context context) {
        getTheme();
        return new ContextThemeWrapper(context, 2131886327);
    }
}
