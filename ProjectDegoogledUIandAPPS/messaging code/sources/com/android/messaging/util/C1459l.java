package com.android.messaging.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import com.android.messaging.R;
import com.android.messaging.p041ui.C1369oa;
import com.android.messaging.p041ui.C1376qa;
import java.util.List;

/* renamed from: com.android.messaging.util.l */
public class C1459l {

    /* renamed from: KJ */
    private Runnable f2312KJ;

    /* renamed from: LJ */
    private C1457k f2313LJ;

    /* renamed from: a */
    public void mo8188a(boolean z, Runnable runnable, View view, View view2, Activity activity, Fragment fragment) {
        C1474sa saVar = C1474sa.getDefault();
        boolean isSmsCapable = saVar.isSmsCapable();
        boolean ek = saVar.mo8206ek();
        boolean kk = saVar.mo8228kk();
        if (!isSmsCapable) {
            C1486ya.m3847Oa(R.string.sms_disabled);
        } else if (!ek) {
            C1486ya.m3847Oa(R.string.no_preferred_sim_selected);
        } else if (!kk) {
            this.f2313LJ = new C1457k(this, activity, fragment);
            if (view != null) {
                C1419X.m3578b(activity, view);
            }
            this.f2312KJ = runnable;
            if (view2 == null) {
                this.f2313LJ.run();
            } else {
                C1486ya.m3852a((Context) activity, view2, activity.getString(z ? R.string.requires_default_sms_app_to_send : R.string.requires_default_sms_app), C1369oa.m3482a(this.f2313LJ, activity.getString(R.string.requires_default_sms_change_button)), (List) null, C1376qa.m3512f(view));
            }
        }
        C1430e.m3630w("MessagingApp", "Unsatisfied action condition: isSmsCapable=" + isSmsCapable + ", hasPreferredSmsSim=" + ek + ", isDefaultSmsApp=" + kk);
    }

    /* renamed from: a */
    public void mo8187a(int i, int i2, Runnable runnable) {
        C1424b.m3592ia(this.f2312KJ == null || runnable == null);
        if (runnable == null) {
            runnable = this.f2312KJ;
        }
        if (i == 1) {
            if (i2 == -1) {
                if (runnable != null) {
                    runnable.run();
                } else {
                    C1486ya.m3847Oa(R.string.toast_after_setting_default_sms_app);
                }
            }
            this.f2312KJ = null;
        }
    }
}
