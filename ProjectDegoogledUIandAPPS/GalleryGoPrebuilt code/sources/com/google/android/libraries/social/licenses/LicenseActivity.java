package com.google.android.libraries.social.licenses;

import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/* compiled from: PG */
public final class LicenseActivity extends C0395ok {
    public final void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView((int) R.layout.libraries_social_licenses_license_activity);
        fsr fsr = (fsr) getIntent().getParcelableExtra("license");
        mo9534f().mo9487a((CharSequence) fsr.f10540a);
        mo9534f().mo9503i();
        mo9534f().mo9488a(true);
        mo9534f().mo9504j();
        TextView textView = (TextView) findViewById(R.id.license_activity_textview);
        long j = fsr.f10541b;
        int i = fsr.f10542c;
        String str2 = fsr.f10543d;
        if (str2.isEmpty()) {
            str = frz.m9474a(this, "third_party_licenses", j, i);
        } else {
            try {
                String a = frz.m9475a(new BufferedInputStream(new FileInputStream(str2)), j, i);
                if (a != null && !a.isEmpty()) {
                    str = a;
                }
            } catch (FileNotFoundException e) {
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 46);
            sb.append(str2);
            sb.append(" does not contain res/raw/third_party_licenses");
            throw new RuntimeException(sb.toString());
        }
        if (str == null) {
            finish();
        } else {
            textView.setText(str);
        }
    }

    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public final void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        ScrollView scrollView = (ScrollView) findViewById(R.id.license_activity_scrollview);
        int i = bundle.getInt("scroll_pos");
        if (i != 0) {
            scrollView.post(new fss(this, i, scrollView));
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ScrollView scrollView = (ScrollView) findViewById(R.id.license_activity_scrollview);
        Layout layout = ((TextView) findViewById(R.id.license_activity_textview)).getLayout();
        if (layout != null) {
            bundle.putInt("scroll_pos", layout.getLineStart(layout.getLineForVertical(scrollView.getScrollY())));
        }
    }
}
