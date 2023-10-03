package com.android.systemui.havoc.logo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.systemui.C1776R$drawable;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.DarkIconDispatcher;

public class LogoImageViewRight extends ImageView {
    private boolean mAttached;
    /* access modifiers changed from: private */
    public Context mContext;
    private final Handler mHandler;
    private boolean mHavocLogo;
    private int mHavocLogoPosition;
    private int mHavocLogoStyle;
    private SettingsObserver mSettingsObserver;
    private int mTintColor;

    private class SettingsObserver extends ContentObserver {
        SettingsObserver(Handler handler) {
            super(handler);
        }

        /* access modifiers changed from: package-private */
        public void observe() {
            ContentResolver contentResolver = LogoImageViewRight.this.mContext.getContentResolver();
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_logo"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_logo_position"), false, this);
            contentResolver.registerContentObserver(Settings.System.getUriFor("status_bar_logo_style"), false, this);
        }

        public void onChange(boolean z) {
            LogoImageViewRight.this.updateSettings();
        }
    }

    public LogoImageViewRight(Context context) {
        this(context, (AttributeSet) null);
    }

    public LogoImageViewRight(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LogoImageViewRight(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTintColor = -1;
        this.mHandler = new Handler();
        this.mSettingsObserver = new SettingsObserver(this.mHandler);
        getResources();
        this.mContext = context;
        this.mSettingsObserver.observe();
        updateSettings();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!this.mAttached) {
            this.mAttached = true;
            ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).addDarkReceiver((ImageView) this);
            updateSettings();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAttached) {
            this.mAttached = false;
            ((DarkIconDispatcher) Dependency.get(DarkIconDispatcher.class)).removeDarkReceiver((ImageView) this);
        }
    }

    public void updateHavocLogo() {
        Drawable drawable;
        if (!this.mHavocLogo || this.mHavocLogoPosition == 0) {
            setImageDrawable((Drawable) null);
            setVisibility(8);
            return;
        }
        setVisibility(0);
        int i = this.mHavocLogoStyle;
        if (i == 0) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_havoc_logo);
        } else if (i == 1) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_android_logo);
        } else if (i == 2) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_apple_logo);
        } else if (i == 3) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_beats);
        } else if (i == 4) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_biohazard);
        } else if (i == 5) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_blackberry);
        } else if (i == 6) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_blogger);
        } else if (i == 7) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_bomb);
        } else if (i == 8) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_brain);
        } else if (i == 9) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_cake);
        } else if (i == 10) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_cannabis);
        } else if (i == 11) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_death_star);
        } else if (i == 12) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_emoticon);
        } else if (i == 13) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_emoticon_cool);
        } else if (i == 14) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_emoticon_dead);
        } else if (i == 15) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_emoticon_devil);
        } else if (i == 16) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_emoticon_happy);
        } else if (i == 17) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_emoticon_neutral);
        } else if (i == 18) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_emoticon_poop);
        } else if (i == 19) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_emoticon_sad);
        } else if (i == 20) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_emoticon_tongue);
        } else if (i == 21) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_fire);
        } else if (i == 22) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_flask);
        } else if (i == 23) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_gender_female);
        } else if (i == 24) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_gender_male);
        } else if (i == 25) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_gender_male_female);
        } else if (i == 26) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_ghost);
        } else if (i == 27) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_google);
        } else if (i == 28) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_guitar_acoustic);
        } else if (i == 29) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_guitar_electric);
        } else if (i == 30) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_heart);
        } else if (i == 31) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_human_female);
        } else if (i == 32) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_human_male);
        } else if (i == 33) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_human_male_female);
        } else if (i == 34) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_incognito);
        } else if (i == 35) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_ios_logo);
        } else if (i == 36) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_linux);
        } else if (i == 37) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_lock);
        } else if (i == 38) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_music_note);
        } else if (i == 39) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_ninja);
        } else if (i == 40) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_pac_man);
        } else if (i == 41) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_peace);
        } else if (i == 42) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_robot);
        } else if (i == 43) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_skull);
        } else if (i == 44) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_smoking);
        } else if (i == 45) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_wallet);
        } else if (i == 46) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_windows);
        } else if (i == 47) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_xbox);
        } else if (i == 48) {
            drawable = this.mContext.getDrawable(C1776R$drawable.ic_xbox_controller);
        } else {
            drawable = i == 49 ? this.mContext.getDrawable(C1776R$drawable.ic_yin_yang) : null;
        }
        setImageDrawable((Drawable) null);
        clearColorFilter();
        drawable.setTint(this.mTintColor);
        setImageDrawable(drawable);
    }

    public void updateSettings() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        boolean z = true;
        if (Settings.System.getInt(contentResolver, "status_bar_logo", 0) != 1) {
            z = false;
        }
        this.mHavocLogo = z;
        this.mHavocLogoPosition = Settings.System.getInt(contentResolver, "status_bar_logo_position", 0);
        this.mHavocLogoStyle = Settings.System.getInt(contentResolver, "status_bar_logo_style", 0);
        updateHavocLogo();
    }
}
