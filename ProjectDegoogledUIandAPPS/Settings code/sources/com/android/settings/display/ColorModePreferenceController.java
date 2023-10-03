package com.android.settings.display;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class ColorModePreferenceController extends BasePreferenceController {
    private ColorDisplayManager mColorDisplayManager;

    public ColorModePreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return (this.mContext.getResources().getIntArray(17235992).length <= 0 || !((ColorDisplayManager) this.mContext.getSystemService(ColorDisplayManager.class)).isDeviceColorManaged() || ColorDisplayManager.areAccessibilityTransformsEnabled(this.mContext)) ? 4 : 1;
    }

    public CharSequence getSummary() {
        int colorMode = getColorDisplayManager().getColorMode();
        if (colorMode == 3) {
            return this.mContext.getText(C1715R.string.color_mode_option_automatic);
        }
        if (colorMode == 2) {
            return this.mContext.getText(C1715R.string.color_mode_option_saturated);
        }
        if (colorMode == 1) {
            return this.mContext.getText(C1715R.string.color_mode_option_boosted);
        }
        return this.mContext.getText(C1715R.string.color_mode_option_natural);
    }

    /* access modifiers changed from: package-private */
    public ColorDisplayManager getColorDisplayManager() {
        if (this.mColorDisplayManager == null) {
            this.mColorDisplayManager = (ColorDisplayManager) this.mContext.getSystemService(ColorDisplayManager.class);
        }
        return this.mColorDisplayManager;
    }
}
