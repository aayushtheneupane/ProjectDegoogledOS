package com.android.systemui.tuner;

import android.os.Bundle;
import com.android.systemui.C1777R$id;
import com.android.systemui.Dependency;
import com.android.systemui.SystemUIFactory;

public class NavBarActivity extends TunerActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Dependency.initDependencies(SystemUIFactory.getInstance().getRootComponent());
        super.onCreate(bundle);
        if (getFragmentManager().findFragmentByTag("tuner") == null) {
            getIntent().getAction();
            getFragmentManager().beginTransaction().replace(C1777R$id.content_frame, new NavBarTuner(), "tuner").commit();
        }
    }
}
