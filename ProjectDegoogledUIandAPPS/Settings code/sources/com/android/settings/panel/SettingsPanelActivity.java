package com.android.settings.panel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.core.HideNonSystemOverlayMixin;
import com.havoc.config.center.C1715R;

public class SettingsPanelActivity extends FragmentActivity {
    private final String TAG = "panel_activity";
    @VisibleForTesting
    final Bundle mBundle = new Bundle();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        createOrUpdatePanel(true);
        getLifecycle().addObserver(new HideNonSystemOverlayMixin(this));
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        createOrUpdatePanel(false);
    }

    private void createOrUpdatePanel(boolean z) {
        Intent intent = getIntent();
        if (intent == null) {
            Log.e("panel_activity", "Null intent, closing Panel Activity");
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("com.android.settings.panel.extra.PACKAGE_NAME");
        this.mBundle.putString("PANEL_TYPE_ARGUMENT", intent.getAction());
        this.mBundle.putString("PANEL_CALLING_PACKAGE_NAME", getCallingPackage());
        this.mBundle.putString("PANEL_MEDIA_PACKAGE_NAME", stringExtra);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentById = supportFragmentManager.findFragmentById(C1715R.C1718id.main_content);
        if (z || findFragmentById == null || !(findFragmentById instanceof PanelFragment)) {
            setContentView(C1715R.layout.settings_panel);
            Window window = getWindow();
            window.setGravity(80);
            window.setLayout(-1, -2);
            PanelFragment panelFragment = new PanelFragment();
            panelFragment.setArguments(this.mBundle);
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.add((int) C1715R.C1718id.main_content, (Fragment) panelFragment);
            beginTransaction.commit();
            return;
        }
        PanelFragment panelFragment2 = (PanelFragment) findFragmentById;
        panelFragment2.setArguments(this.mBundle);
        panelFragment2.updatePanelWithAnimation();
    }
}
