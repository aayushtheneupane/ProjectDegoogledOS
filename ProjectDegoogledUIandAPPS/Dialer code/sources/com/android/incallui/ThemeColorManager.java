package com.android.incallui;

import android.content.Context;
import android.graphics.Color;
import android.support.p000v4.graphics.ColorUtils;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import com.android.contacts.common.util.MaterialColorMapUtils;
import com.android.dialer.R;
import com.android.incallui.call.DialerCall;

public class ThemeColorManager {
    private int backgroundColorBottom;
    private int backgroundColorMiddle;
    private int backgroundColorSolid;
    private int backgroundColorTop;
    private final MaterialColorMapUtils colorMap;
    private PhoneAccountHandle pendingPhoneAccountHandle;
    private int primaryColor;
    private int secondaryColor;

    public ThemeColorManager(MaterialColorMapUtils materialColorMapUtils) {
        this.colorMap = materialColorMapUtils;
    }

    private static int applyAlpha(int i, int i2) {
        return ColorUtils.setAlphaComponent(i, Color.alpha(i2));
    }

    private int getHighlightColor(Context context, PhoneAccountHandle phoneAccountHandle) {
        PhoneAccount phoneAccount;
        if (phoneAccountHandle == null || (phoneAccount = ((TelecomManager) context.getSystemService(TelecomManager.class)).getPhoneAccount(phoneAccountHandle)) == null) {
            return 0;
        }
        return phoneAccount.getHighlightColor();
    }

    private void updateThemeColors(Context context, int i, boolean z) {
        MaterialColorMapUtils.MaterialPalette materialPalette;
        if (z) {
            materialPalette = this.colorMap.calculatePrimaryAndSecondaryColor(R.color.incall_call_spam_background_color);
            this.backgroundColorTop = context.getColor(R.color.incall_background_gradient_spam_top);
            this.backgroundColorMiddle = context.getColor(R.color.incall_background_gradient_spam_middle);
            this.backgroundColorBottom = context.getColor(R.color.incall_background_gradient_spam_bottom);
            this.backgroundColorSolid = context.getColor(R.color.incall_background_multiwindow_spam);
        } else {
            MaterialColorMapUtils.MaterialPalette calculatePrimaryAndSecondaryColor = this.colorMap.calculatePrimaryAndSecondaryColor(i);
            this.backgroundColorTop = context.getColor(R.color.incall_background_gradient_top);
            this.backgroundColorMiddle = context.getColor(R.color.incall_background_gradient_middle);
            this.backgroundColorBottom = context.getColor(R.color.incall_background_gradient_bottom);
            this.backgroundColorSolid = context.getColor(R.color.incall_background_multiwindow);
            if (i != 0) {
                this.backgroundColorTop = applyAlpha(calculatePrimaryAndSecondaryColor.mPrimaryColor, this.backgroundColorTop);
                this.backgroundColorMiddle = applyAlpha(calculatePrimaryAndSecondaryColor.mPrimaryColor, this.backgroundColorMiddle);
                this.backgroundColorBottom = applyAlpha(calculatePrimaryAndSecondaryColor.mPrimaryColor, this.backgroundColorBottom);
                this.backgroundColorSolid = applyAlpha(calculatePrimaryAndSecondaryColor.mPrimaryColor, this.backgroundColorSolid);
            }
            materialPalette = calculatePrimaryAndSecondaryColor;
        }
        this.primaryColor = materialPalette.mPrimaryColor;
        this.secondaryColor = materialPalette.mSecondaryColor;
    }

    public int getBackgroundColorBottom() {
        return this.backgroundColorBottom;
    }

    public int getBackgroundColorMiddle() {
        return this.backgroundColorMiddle;
    }

    public int getBackgroundColorSolid() {
        return this.backgroundColorSolid;
    }

    public int getBackgroundColorTop() {
        return this.backgroundColorTop;
    }

    public int getPrimaryColor() {
        return this.primaryColor;
    }

    public int getSecondaryColor() {
        return this.secondaryColor;
    }

    public void onForegroundCallChanged(Context context, DialerCall dialerCall) {
        if (dialerCall == null) {
            updateThemeColors(context, getHighlightColor(context, this.pendingPhoneAccountHandle), false);
        } else {
            updateThemeColors(context, getHighlightColor(context, dialerCall.getAccountHandle()), dialerCall.isSpam());
        }
    }

    public void setPendingPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle) {
        this.pendingPhoneAccountHandle = phoneAccountHandle;
    }
}
