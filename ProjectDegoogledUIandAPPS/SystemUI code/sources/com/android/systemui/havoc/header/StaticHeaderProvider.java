package com.android.systemui.havoc.header;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.util.havoc.Utils;
import com.android.systemui.havoc.header.StatusBarHeaderMachine;
import java.util.Calendar;

public class StaticHeaderProvider implements StatusBarHeaderMachine.IStatusBarHeaderProvider {
    private Context mContext;
    private String mImage;
    private String mPackageName;
    private Resources mRes;

    public void disableProvider() {
    }

    public String getName() {
        return "static";
    }

    public StaticHeaderProvider(Context context) {
        this.mContext = context;
    }

    public void settingsChanged(Uri uri) {
        boolean z = Settings.System.getIntForUser(this.mContext.getContentResolver(), "status_bar_custom_header", 0, -2) == 1;
        String stringForUser = Settings.System.getStringForUser(this.mContext.getContentResolver(), "status_bar_custom_header_image", -2);
        if (stringForUser != null && z && stringForUser.indexOf("/") != -1) {
            String[] split = stringForUser.split("/");
            this.mPackageName = split[0];
            this.mImage = split[1];
            loadHeaderImage();
        }
    }

    public void enableProvider() {
        settingsChanged((Uri) null);
    }

    private void loadHeaderImage() {
        try {
            this.mRes = this.mContext.getPackageManager().getResourcesForApplication(this.mPackageName);
        } catch (Exception e) {
            Log.e("StaticHeaderProvider", "Failed to load icon pack " + this.mPackageName, e);
            this.mRes = null;
        }
    }

    public Drawable getCurrent(Calendar calendar) {
        if (this.mRes == null) {
            return null;
        }
        if (!Utils.isPackageInstalled(this.mContext, this.mPackageName)) {
            Log.w("StaticHeaderProvider", "Header pack image " + this.mImage + " no longer available");
            return null;
        }
        try {
            return this.mRes.getDrawable(this.mRes.getIdentifier(this.mImage, "drawable", this.mPackageName), (Resources.Theme) null);
        } catch (Resources.NotFoundException unused) {
            Log.w("StaticHeaderProvider", "No drawable found for " + calendar + " in " + this.mPackageName);
            return null;
        }
    }
}
