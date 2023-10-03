package com.android.settings.wifi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.wifi.dpp.WifiDppUtils;
import com.havoc.config.center.C1715R;

public class AddWifiNetworkPreference extends Preference {
    private final Drawable mScanIconDrawable = getDrawable(C1715R.C1717drawable.ic_scan_24dp);

    public AddWifiNetworkPreference(Context context) {
        super(context);
        setLayoutResource(C1715R.layout.preference_access_point);
        setWidgetLayoutResource(C1715R.layout.wifi_button_preference_widget);
        setIcon((int) C1715R.C1717drawable.ic_add_24dp);
        setTitle((int) C1715R.string.wifi_add_network);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        ImageButton imageButton = (ImageButton) preferenceViewHolder.findViewById(C1715R.C1718id.button_icon);
        imageButton.setImageDrawable(this.mScanIconDrawable);
        imageButton.setContentDescription(getContext().getString(C1715R.string.wifi_dpp_scan_qr_code));
        imageButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AddWifiNetworkPreference.this.lambda$onBindViewHolder$0$AddWifiNetworkPreference(view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$AddWifiNetworkPreference(View view) {
        getContext().startActivity(WifiDppUtils.getEnrolleeQrCodeScannerIntent((String) null));
    }

    private Drawable getDrawable(int i) {
        try {
            return getContext().getDrawable(i);
        } catch (Resources.NotFoundException unused) {
            Log.e("AddWifiNetworkPreference", "Resource does not exist: " + i);
            return null;
        }
    }
}
