package com.android.settings.print;

import android.content.Context;
import android.print.PrintJob;
import android.text.TextUtils;

public class PrintJobMessagePreferenceController extends PrintJobPreferenceControllerBase {
    public PrintJobMessagePreferenceController(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    public void updateUi() {
        PrintJob printJob = getPrintJob();
        if (printJob == null) {
            this.mFragment.finish();
        } else if (printJob.isCancelled() || printJob.isCompleted()) {
            this.mFragment.finish();
        } else {
            CharSequence status = printJob.getInfo().getStatus(this.mContext.getPackageManager());
            this.mPreference.setVisible(!TextUtils.isEmpty(status));
            this.mPreference.setSummary(status);
        }
    }
}
