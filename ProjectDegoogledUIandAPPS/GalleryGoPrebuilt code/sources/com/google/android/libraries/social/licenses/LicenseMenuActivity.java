package com.google.android.libraries.social.licenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class LicenseMenuActivity extends C0395ok implements fsv {
    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.libraries_social_licenses_license_menu_activity);
        if (mo9534f() != null) {
            mo9534f().mo9488a(true);
        }
        C0171gd d = mo5851d();
        if (!(d.mo6432b((int) R.id.license_menu_fragment_container) instanceof fsw)) {
            fsw fsw = new fsw();
            C0182gn a = d.mo6419a();
            a.mo5246a((int) R.id.license_menu_fragment_container, (C0147fh) fsw, (String) null, 1);
            a.mo5244a();
        }
    }

    /* renamed from: a */
    public final void mo3552a(fsr fsr) {
        Intent intent = new Intent(this, LicenseActivity.class);
        intent.putExtra("license", fsr);
        startActivity(intent);
    }

    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }
}
