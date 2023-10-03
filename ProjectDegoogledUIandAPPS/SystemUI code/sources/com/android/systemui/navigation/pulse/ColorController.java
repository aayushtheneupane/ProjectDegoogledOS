package com.android.systemui.navigation.pulse;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.TypedValue;
import com.android.internal.util.ContrastColorUtil;
import com.android.systemui.Dependency;
import com.android.systemui.navigation.pulse.ColorAnimator;
import com.android.systemui.statusbar.policy.ConfigurationController;

public class ColorController extends ContentObserver implements ColorAnimator.ColorAnimationListener, ConfigurationController.ConfigurationListener {
    private int mAccentColor;
    private int mAlbumColor;
    private int mColor;
    private int mColorType;
    private Context mContext;
    private ColorAnimator mLavaLamp = new ColorAnimator();
    private Renderer mRenderer;

    public ColorController(Context context, Handler handler) {
        super(handler);
        this.mContext = context;
        this.mLavaLamp.setColorAnimatorListener(this);
        this.mAccentColor = getAccentColor();
        this.mAlbumColor = this.mAccentColor;
        updateSettings();
        startListening();
        ((ConfigurationController) Dependency.get(ConfigurationController.class)).addCallback(this);
    }

    /* access modifiers changed from: package-private */
    public void setRenderer(Renderer renderer) {
        this.mRenderer = renderer;
        notifyRenderer();
    }

    /* access modifiers changed from: package-private */
    public void startListening() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_color_mode"), false, this, -1);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_color_user"), false, this, -1);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("pulse_lavalamp_speed"), false, this, -1);
    }

    /* access modifiers changed from: package-private */
    public void updateSettings() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        if (this.mColorType == 2) {
            stopLavaLamp();
        }
        this.mColorType = Settings.Secure.getIntForUser(contentResolver, "pulse_color_mode", 0, -2);
        this.mColor = Settings.Secure.getIntForUser(contentResolver, "pulse_color_user", -1, -2);
        this.mLavaLamp.setAnimationTime((long) Settings.Secure.getIntForUser(contentResolver, "pulse_lavalamp_speed", 10000, -2));
        notifyRenderer();
    }

    /* access modifiers changed from: package-private */
    public void notifyRenderer() {
        Renderer renderer = this.mRenderer;
        if (renderer != null) {
            int i = this.mColorType;
            if (i == 0) {
                renderer.onUpdateColor(this.mAccentColor);
            } else if (i == 1) {
                renderer.onUpdateColor(this.mColor);
            } else if (i == 2 && renderer.isValidStream()) {
                startLavaLamp();
            } else if (this.mColorType == 3) {
                this.mRenderer.onUpdateColor(this.mAlbumColor);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void startLavaLamp() {
        if (this.mColorType == 2) {
            this.mLavaLamp.start();
        }
    }

    /* access modifiers changed from: package-private */
    public void stopLavaLamp() {
        this.mLavaLamp.stop();
    }

    /* access modifiers changed from: package-private */
    public int getAccentColor() {
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(16843829, typedValue, true);
        return typedValue.data;
    }

    public void onChange(boolean z, Uri uri) {
        updateSettings();
    }

    public void onConfigChanged(Configuration configuration) {
        int i = this.mAccentColor;
        int accentColor = getAccentColor();
        if (i != accentColor) {
            this.mAccentColor = accentColor;
            Renderer renderer = this.mRenderer;
            if (renderer != null && this.mColorType == 0) {
                renderer.onUpdateColor(this.mAccentColor);
            }
        }
    }

    public void onColorChanged(ColorAnimator colorAnimator, int i) {
        Renderer renderer = this.mRenderer;
        if (renderer != null) {
            renderer.onUpdateColor(i);
        }
    }

    public void setMediaNotificationColor(boolean z, int i) {
        if (z) {
            this.mAlbumColor = ContrastColorUtil.findContrastColorAgainstDark(i, 0, true, 2.0d);
            this.mAlbumColor = ContrastColorUtil.findContrastColor(this.mAlbumColor, 16777215, true, 2.0d);
        } else {
            this.mAlbumColor = this.mAccentColor;
        }
        Renderer renderer = this.mRenderer;
        if (renderer != null && this.mColorType == 3) {
            renderer.onUpdateColor(this.mAlbumColor);
        }
    }
}
