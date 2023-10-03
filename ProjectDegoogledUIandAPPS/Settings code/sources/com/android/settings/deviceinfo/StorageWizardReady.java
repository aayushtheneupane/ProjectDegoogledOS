package com.android.settings.deviceinfo;

import android.os.Bundle;
import android.view.View;
import com.havoc.config.center.C1715R;

public class StorageWizardReady extends StorageWizardBase {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.mDisk == null) {
            finish();
            return;
        }
        setContentView(C1715R.layout.storage_wizard_generic);
        setHeaderText(C1715R.string.storage_wizard_ready_title, getDiskShortDescription());
        if (findFirstVolume(1) == null) {
            setBodyText(C1715R.string.storage_wizard_ready_v2_external_body, getDiskDescription());
        } else if (getIntent().getBooleanExtra("migrate_skip", false)) {
            setBodyText(C1715R.string.storage_wizard_ready_v2_internal_body, getDiskDescription());
        } else {
            setBodyText(C1715R.string.storage_wizard_ready_v2_internal_moved_body, getDiskDescription(), getDiskShortDescription());
        }
        setNextButtonText(C1715R.string.done, new CharSequence[0]);
        setBackButtonVisibility(4);
    }

    public void onNavigateNext(View view) {
        finishAffinity();
    }
}
