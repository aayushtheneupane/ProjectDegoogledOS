package com.android.dialer.about;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.dialer.R;

public final class LicenseActivity extends AppCompatActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.license_scrollview);
        License license = (License) getIntent().getParcelableExtra("license");
        getSupportActionBar().setTitle((CharSequence) license.getLibraryName());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo((Drawable) null);
        TextView textView = (TextView) findViewById(R.id.license_activity_textview);
        String licenseText = Licenses.getLicenseText(this, license);
        if (licenseText == null) {
            finish();
        } else {
            textView.setText(licenseText);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.license_activity_scrollview);
        final int i = bundle.getInt("scroll_pos");
        scrollView.post(new Runnable() {
            public void run() {
                TextView textView = (TextView) LicenseActivity.this.findViewById(R.id.license_activity_textview);
                scrollView.scrollTo(0, textView.getLayout().getLineTop(textView.getLayout().getLineForOffset(i)));
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        TextView textView = (TextView) findViewById(R.id.license_activity_textview);
        bundle.putInt("scroll_pos", textView.getLayout().getLineStart(textView.getLayout().getLineForVertical(((ScrollView) findViewById(R.id.license_activity_scrollview)).getScrollY())));
    }
}
