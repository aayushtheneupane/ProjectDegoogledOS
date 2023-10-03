package com.android.dialer.calllogutils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.theme.base.Theme;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import java.util.ArrayList;
import java.util.List;

public class CallTypeIconsView extends View {
    private static Resources largeResouces;
    private static Resources resources;
    private List<Integer> callTypes;
    private int height;
    private boolean showAssistedDialed;
    private boolean showHd;
    private boolean showRtt;
    private boolean showVideo;
    private boolean showWifi;
    private final boolean useLargeIcons;
    private int width;

    private static class Resources {
        final Drawable assistedDialedCall;
        public final Drawable blocked;
        final Drawable hdCall;
        final int iconMargin;
        public final Drawable incoming;
        public final Drawable missed;
        public final Drawable outgoing;
        final Drawable rttCall;
        final Drawable videoCall;
        public final Drawable voicemail;
        final Drawable wifiCall;

        public Resources(Context context, boolean z) {
            int i;
            android.content.res.Resources resources = context.getResources();
            this.incoming = (z ? resources.getDrawable(R.drawable.quantum_ic_call_received_white_24) : getScaledBitmap(context, R.drawable.quantum_ic_call_received_white_24)).mutate();
            this.incoming.setColorFilter(resources.getColor(R.color.dialer_call_green), PorterDuff.Mode.MULTIPLY);
            this.outgoing = (z ? resources.getDrawable(R.drawable.quantum_ic_call_made_white_24) : getScaledBitmap(context, R.drawable.quantum_ic_call_made_white_24)).mutate();
            this.outgoing.setColorFilter(resources.getColor(R.color.dialer_call_green), PorterDuff.Mode.MULTIPLY);
            this.missed = (z ? resources.getDrawable(R.drawable.quantum_ic_call_missed_white_24) : getScaledBitmap(context, R.drawable.quantum_ic_call_missed_white_24)).mutate();
            this.missed.setColorFilter(resources.getColor(R.color.dialer_red), PorterDuff.Mode.MULTIPLY);
            Theme theme = ThemeComponent.get(context).theme();
            this.voicemail = (z ? resources.getDrawable(R.drawable.quantum_ic_voicemail_white_24) : getScaledBitmap(context, R.drawable.quantum_ic_voicemail_white_24)).mutate();
            AospThemeImpl aospThemeImpl = (AospThemeImpl) theme;
            this.voicemail.setColorFilter(aospThemeImpl.getColorIcon(), PorterDuff.Mode.MULTIPLY);
            this.blocked = (z ? resources.getDrawable(R.drawable.quantum_ic_block_white_24) : getScaledBitmap(context, R.drawable.quantum_ic_block_white_24)).mutate();
            this.blocked.setColorFilter(aospThemeImpl.getColorIcon(), PorterDuff.Mode.MULTIPLY);
            this.videoCall = (z ? resources.getDrawable(R.drawable.quantum_ic_videocam_vd_white_24) : getScaledBitmap(context, R.drawable.quantum_ic_videocam_vd_white_24)).mutate();
            this.videoCall.setColorFilter(aospThemeImpl.getColorIcon(), PorterDuff.Mode.MULTIPLY);
            this.hdCall = (z ? resources.getDrawable(R.drawable.quantum_ic_hd_white_24) : getScaledBitmap(context, R.drawable.quantum_ic_hd_white_24)).mutate();
            this.hdCall.setColorFilter(aospThemeImpl.getColorIcon(), PorterDuff.Mode.MULTIPLY);
            this.wifiCall = (z ? resources.getDrawable(R.drawable.quantum_ic_signal_wifi_4_bar_white_24) : getScaledBitmap(context, R.drawable.quantum_ic_signal_wifi_4_bar_white_24)).mutate();
            this.wifiCall.setColorFilter(aospThemeImpl.getColorIcon(), PorterDuff.Mode.MULTIPLY);
            this.assistedDialedCall = (z ? resources.getDrawable(R.drawable.quantum_ic_language_white_24) : getScaledBitmap(context, R.drawable.quantum_ic_language_white_24)).mutate();
            this.assistedDialedCall.setColorFilter(aospThemeImpl.getColorIcon(), PorterDuff.Mode.MULTIPLY);
            this.rttCall = (z ? resources.getDrawable(R.drawable.quantum_ic_rtt_vd_theme_24, (Resources.Theme) null) : getScaledBitmap(context, R.drawable.quantum_ic_rtt_vd_theme_24)).mutate();
            this.rttCall.setColorFilter(aospThemeImpl.getColorIcon(), PorterDuff.Mode.MULTIPLY);
            if (z) {
                i = 0;
            } else {
                i = resources.getDimensionPixelSize(R.dimen.call_log_icon_margin);
            }
            this.iconMargin = i;
        }

        private Drawable getScaledBitmap(Context context, int i) {
            Drawable drawable = context.getDrawable(i);
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.call_type_icon_size);
            Bitmap createBitmap = Bitmap.createBitmap((int) ((((float) dimensionPixelSize) / ((float) drawable.getIntrinsicHeight())) * ((float) drawable.getIntrinsicWidth())), dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return new BitmapDrawable(context.getResources(), createBitmap);
        }
    }

    public CallTypeIconsView(Context context) {
        this(context, (AttributeSet) null);
    }

    private int addDrawable(Canvas canvas, Drawable drawable, int i) {
        int intrinsicWidth = drawable.getIntrinsicWidth() + i;
        drawable.setBounds(i, 0, intrinsicWidth, drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return intrinsicWidth;
    }

    private Drawable getCallTypeDrawable(int i) {
        Resources resources2 = this.useLargeIcons ? largeResouces : resources;
        if (i != 1) {
            if (i == 2) {
                return resources2.outgoing;
            }
            if (i == 3) {
                return resources2.missed;
            }
            if (i == 4) {
                return resources2.voicemail;
            }
            if (i == 6) {
                return resources2.blocked;
            }
            if (i != 7) {
                return resources2.missed;
            }
        }
        return resources2.incoming;
    }

    public void add(int i) {
        this.callTypes.add(Integer.valueOf(i));
        Drawable callTypeDrawable = getCallTypeDrawable(i);
        this.width = callTypeDrawable.getIntrinsicWidth() + resources.iconMargin + this.width;
        this.height = Math.max(this.height, callTypeDrawable.getIntrinsicWidth());
        invalidate();
    }

    public void clear() {
        this.callTypes.clear();
        this.width = 0;
        this.height = 0;
        invalidate();
    }

    public boolean isHdShown() {
        return this.showHd;
    }

    public boolean isVideoShown() {
        return this.showVideo;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Resources resources2 = this.useLargeIcons ? largeResouces : resources;
        int i = 0;
        if (!this.useLargeIcons || (!this.showHd && !this.showVideo && !this.showWifi && !this.showAssistedDialed && !this.showRtt)) {
            int i2 = 0;
            for (Integer intValue : this.callTypes) {
                Drawable callTypeDrawable = getCallTypeDrawable(intValue.intValue());
                int intrinsicWidth = callTypeDrawable.getIntrinsicWidth() + i2;
                callTypeDrawable.setBounds(i2, 0, intrinsicWidth, callTypeDrawable.getIntrinsicHeight());
                callTypeDrawable.draw(canvas);
                i2 = resources2.iconMargin + intrinsicWidth;
            }
            i = i2;
        }
        if (this.showVideo) {
            i = resources2.iconMargin + addDrawable(canvas, resources2.videoCall, i);
        }
        if (this.showHd) {
            i = resources2.iconMargin + addDrawable(canvas, resources2.hdCall, i);
        }
        if (this.showWifi) {
            i = resources2.iconMargin + addDrawable(canvas, resources2.wifiCall, i);
        }
        if (this.showAssistedDialed) {
            i = resources2.iconMargin + addDrawable(canvas, resources2.assistedDialedCall, i);
        }
        if (this.showRtt) {
            addDrawable(canvas, resources2.rttCall, i);
            int i3 = resources2.iconMargin;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        setMeasuredDimension(this.width, this.height);
    }

    public void setShowAssistedDialed(boolean z) {
        this.showAssistedDialed = z;
        if (z) {
            int i = this.width;
            int intrinsicWidth = resources.assistedDialedCall.getIntrinsicWidth();
            Resources resources2 = resources;
            this.width = intrinsicWidth + resources2.iconMargin + i;
            this.height = Math.max(this.height, resources2.assistedDialedCall.getIntrinsicHeight());
            invalidate();
        }
    }

    public void setShowHd(boolean z) {
        this.showHd = z;
        if (z) {
            int i = this.width;
            int intrinsicWidth = resources.hdCall.getIntrinsicWidth();
            Resources resources2 = resources;
            this.width = intrinsicWidth + resources2.iconMargin + i;
            this.height = Math.max(this.height, resources2.hdCall.getIntrinsicHeight());
            invalidate();
        }
    }

    public void setShowRtt(boolean z) {
        this.showRtt = z;
        if (z) {
            int i = this.width;
            int intrinsicWidth = resources.rttCall.getIntrinsicWidth();
            Resources resources2 = resources;
            this.width = intrinsicWidth + resources2.iconMargin + i;
            this.height = Math.max(this.height, resources2.rttCall.getIntrinsicHeight());
            invalidate();
        }
    }

    public void setShowVideo(boolean z) {
        this.showVideo = z;
        if (z) {
            int i = this.width;
            int intrinsicWidth = resources.videoCall.getIntrinsicWidth();
            Resources resources2 = resources;
            this.width = intrinsicWidth + resources2.iconMargin + i;
            this.height = Math.max(this.height, resources2.videoCall.getIntrinsicHeight());
            invalidate();
        }
    }

    public void setShowWifi(boolean z) {
        this.showWifi = z;
        if (z) {
            int i = this.width;
            int intrinsicWidth = resources.wifiCall.getIntrinsicWidth();
            Resources resources2 = resources;
            this.width = intrinsicWidth + resources2.iconMargin + i;
            this.height = Math.max(this.height, resources2.wifiCall.getIntrinsicHeight());
            invalidate();
        }
    }

    public CallTypeIconsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.callTypes = new ArrayList(3);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.CallTypeIconsView, 0, 0);
        this.useLargeIcons = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        if (resources == null) {
            resources = new Resources(context, false);
        }
        if (largeResouces == null && this.useLargeIcons) {
            largeResouces = new Resources(context, true);
        }
    }
}
