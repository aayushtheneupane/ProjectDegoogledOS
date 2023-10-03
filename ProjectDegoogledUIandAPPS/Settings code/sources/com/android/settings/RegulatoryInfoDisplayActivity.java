package com.android.settings;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.havoc.config.center.C1715R;
import java.util.Locale;

public class RegulatoryInfoDisplayActivity extends Activity implements DialogInterface.OnDismissListener {
    private final String REGULATORY_INFO_RESOURCE = "regulatory_info";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Resources resources = getResources();
        if (!resources.getBoolean(C1715R.bool.config_show_regulatory_info)) {
            finish();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((int) C1715R.string.regulatory_labels);
        builder.setOnDismissListener(this);
        Bitmap decodeFile = BitmapFactory.decodeFile(getRegulatoryInfoImageFileName());
        boolean z = false;
        boolean z2 = decodeFile != null;
        int resourceId = !z2 ? getResourceId() : 0;
        if (resourceId != 0) {
            try {
                Drawable drawable = getDrawable(resourceId);
                if (drawable.getIntrinsicWidth() > 2 && drawable.getIntrinsicHeight() > 2) {
                    z = true;
                }
            } catch (Resources.NotFoundException unused) {
            }
        } else {
            z = z2;
        }
        CharSequence text = resources.getText(C1715R.string.regulatory_info_text);
        if (z) {
            View inflate = getLayoutInflater().inflate(C1715R.layout.regulatory_info, (ViewGroup) null);
            ImageView imageView = (ImageView) inflate.findViewById(C1715R.C1718id.regulatoryInfo);
            if (decodeFile != null) {
                imageView.setImageBitmap(decodeFile);
            } else {
                imageView.setImageResource(resourceId);
            }
            builder.setView(inflate);
            builder.show();
        } else if (text.length() > 0) {
            builder.setMessage(text);
            ((TextView) builder.show().findViewById(16908299)).setGravity(17);
        } else {
            finish();
        }
    }

    /* access modifiers changed from: package-private */
    public int getResourceId() {
        int identifier = getResources().getIdentifier("regulatory_info", "drawable", getPackageName());
        String sku = getSku();
        if (!TextUtils.isEmpty(sku)) {
            int identifier2 = getResources().getIdentifier("regulatory_info_" + sku.toLowerCase(), "drawable", getPackageName());
            if (identifier2 != 0) {
                identifier = identifier2;
            }
        }
        String coo = getCoo();
        if (!TextUtils.isEmpty(coo) && !TextUtils.isEmpty(sku)) {
            int identifier3 = getResources().getIdentifier("regulatory_info_" + sku.toLowerCase() + "_" + coo.toLowerCase(), "drawable", getPackageName());
            if (identifier3 != 0) {
                return identifier3;
            }
        }
        return identifier;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        finish();
    }

    private String getCoo() {
        return SystemProperties.get("ro.boot.hardware.coo", "");
    }

    private String getSku() {
        return SystemProperties.get("ro.boot.hardware.sku", "");
    }

    private String getRegulatoryInfoImageFileName() {
        String sku = getSku();
        if (TextUtils.isEmpty(sku)) {
            return "/data/misc/elabel/regulatory_info.png";
        }
        return String.format(Locale.US, "/data/misc/elabel/regulatory_info_%s.png", new Object[]{sku.toLowerCase()});
    }
}
