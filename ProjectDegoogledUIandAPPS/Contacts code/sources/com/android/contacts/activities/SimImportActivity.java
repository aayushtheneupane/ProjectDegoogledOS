package com.android.contacts.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import com.android.contacts.AppCompatContactsActivity;
import com.android.contacts.R;
import com.android.contacts.SimImportFragment;

public class SimImportActivity extends AppCompatContactsActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.sim_import_activity);
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentByTag("SimImport") == null) {
            fragmentManager.beginTransaction().add(R.id.root, SimImportFragment.newInstance(getIntent().getIntExtra("extraSubscriptionId", -1)), "SimImport").commit();
        }
    }
}
