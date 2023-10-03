package com.android.systemui.tuner;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import com.android.systemui.Dependency;
import com.android.systemui.SystemUIFactory;
import com.android.systemui.fragments.FragmentService;

public class StatusbarItemsActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Dependency.initDependencies(SystemUIFactory.getInstance().getRootComponent());
        super.onCreate(bundle);
        getFragmentManager().beginTransaction().replace(16908290, new StatusbarItems()).commit();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Dependency.destroy(FragmentService.class, $$Lambda$StatusbarItemsActivity$VXg98mLOs9zSVvn2ioPxd2j0HA.INSTANCE);
        Dependency.clearDependencies();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}
