package com.android.messaging.p041ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentActivity;
import com.android.messaging.R;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.ui.TestActivity */
public class TestActivity extends FragmentActivity {
    public void onAttachFragment(Fragment fragment) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            getWindow().addFlags(6815872);
            setContentView(R.layout.test_activity);
            return;
        }
        throw new IllegalStateException("TestActivity cannot get recreated");
    }

    public void setFragment(Fragment fragment) {
        C1430e.m3625i("MessagingApp", "TestActivity.setFragment");
        getFragmentManager().beginTransaction().replace(R.id.test_content, fragment).commit();
        getFragmentManager().executePendingTransactions();
    }

    public void setView(View view) {
        C1430e.m3625i("MessagingApp", "TestActivity.setView");
        ((FrameLayout) findViewById(R.id.test_content)).addView(view);
    }
}
