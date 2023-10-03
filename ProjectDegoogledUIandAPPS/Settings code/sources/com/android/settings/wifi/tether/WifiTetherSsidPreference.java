package com.android.settings.wifi.tether;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.preference.PreferenceViewHolder;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.widget.ValidatedEditTextPreference;
import com.havoc.config.center.C1715R;

public class WifiTetherSsidPreference extends ValidatedEditTextPreference {
    private View.OnClickListener mClickListener;
    private Drawable mShareIconDrawable;
    private boolean mVisible;

    public WifiTetherSsidPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initialize();
    }

    public WifiTetherSsidPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize();
    }

    public WifiTetherSsidPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }

    public WifiTetherSsidPreference(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        setLayoutResource(C1715R.layout.preference_two_target);
        setWidgetLayoutResource(C1715R.layout.wifi_button_preference_widget);
        this.mShareIconDrawable = getDrawable(C1715R.C1717drawable.ic_qrcode_24dp);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        ImageButton imageButton = (ImageButton) preferenceViewHolder.findViewById(C1715R.C1718id.button_icon);
        View findViewById = preferenceViewHolder.findViewById(C1715R.C1718id.two_target_divider);
        if (this.mVisible) {
            imageButton.setOnClickListener(this.mClickListener);
            imageButton.setVisibility(0);
            imageButton.setContentDescription(getContext().getString(C1715R.string.wifi_dpp_share_hotspot));
            imageButton.setImageDrawable(this.mShareIconDrawable);
            findViewById.setVisibility(0);
            return;
        }
        imageButton.setVisibility(8);
        findViewById.setVisibility(8);
    }

    public void setButtonOnClickListener(View.OnClickListener onClickListener) {
        this.mClickListener = onClickListener;
    }

    public void setButtonVisible(boolean z) {
        this.mVisible = z;
    }

    private Drawable getDrawable(int i) {
        try {
            return getContext().getDrawable(i);
        } catch (Resources.NotFoundException unused) {
            Log.e("WifiTetherSsidPreference", "Resource does not exist: " + i);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isQrCodeButtonAvailable() {
        return this.mVisible && this.mClickListener != null;
    }
}
