package com.android.messaging.p041ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.PermissionCheckActivity */
public class PermissionCheckActivity extends Activity {

    /* renamed from: dc */
    private long f1666dc;

    /* renamed from: ec */
    private TextView f1667ec;
    private TextView mNextView;

    /* renamed from: Fm */
    private void m2626Fm() {
        C1040Ea.get().mo6987x(this);
        finish();
    }

    /* access modifiers changed from: private */
    /* renamed from: Gm */
    public void m2627Gm() {
        String[] Rj = C1464na.m3751Rj();
        if (Rj.length == 0) {
            m2626Fm();
            return;
        }
        this.f1666dc = SystemClock.elapsedRealtime();
        requestPermissions(Rj, 1);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        boolean z;
        super.onCreate(bundle);
        if (!C1464na.m3752Sj()) {
            z = false;
        } else {
            m2626Fm();
            z = true;
        }
        if (!z) {
            setContentView(R.layout.permission_check_activity);
            C1486ya.m3850a((Activity) this, getColor(R.color.permission_check_activity_background));
            findViewById(R.id.exit).setOnClickListener(new C1069W(this));
            this.mNextView = (TextView) findViewById(R.id.next);
            this.mNextView.setOnClickListener(new C1070X(this));
            this.f1667ec = (TextView) findViewById(R.id.settings);
            this.f1667ec.setOnClickListener(new C1071Y(this));
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 1) {
            return;
        }
        if (C1464na.m3752Sj()) {
            C0967f.get().mo6655Rd();
            m2626Fm();
        } else if (SystemClock.elapsedRealtime() - this.f1666dc < 250) {
            this.mNextView.setVisibility(8);
            this.f1667ec.setVisibility(0);
            findViewById(R.id.enable_permission_procedure).setVisibility(0);
        }
    }

    public void onResume() {
        super.onResume();
        if (C1464na.m3752Sj()) {
            m2626Fm();
        }
    }
}
