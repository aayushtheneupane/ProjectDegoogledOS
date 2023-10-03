package com.android.settings.homepage;

import android.app.ActivityManager;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.android.settings.accounts.AvatarViewMixin;
import com.android.settings.core.HideNonSystemOverlayMixin;
import com.android.settings.homepage.contextualcards.ContextualCardsFragment;
import com.android.settings.overlay.FeatureFactory;
import com.havoc.config.center.C1715R;

public class SettingsHomepageActivity extends FragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1715R.layout.settings_homepage_container);
        findViewById(C1715R.C1718id.settings_homepage_container).setSystemUiVisibility(768);
        setHomepageContainerPaddingTop();
        FeatureFactory.getFactory(this).getSearchFeatureProvider().initSearchToolbar(this, (Toolbar) findViewById(C1715R.C1718id.search_action_bar), 1502);
        getLifecycle().addObserver(new AvatarViewMixin(this, (ImageView) findViewById(C1715R.C1718id.account_avatar)));
        getLifecycle().addObserver(new HideNonSystemOverlayMixin(this));
        if (!((ActivityManager) getSystemService(ActivityManager.class)).isLowRamDevice()) {
            showFragment(new ContextualCardsFragment(), C1715R.C1718id.contextual_cards_content);
        }
        showFragment(new TopLevelSettings(), C1715R.C1718id.main_content);
        ((FrameLayout) findViewById(C1715R.C1718id.main_content)).getLayoutTransition().enableTransitionType(4);
    }

    private void showFragment(Fragment fragment, int i) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        Fragment findFragmentById = supportFragmentManager.findFragmentById(i);
        if (findFragmentById == null) {
            beginTransaction.add(i, fragment);
        } else {
            beginTransaction.show(findFragmentById);
        }
        beginTransaction.commit();
    }

    /* access modifiers changed from: package-private */
    public void setHomepageContainerPaddingTop() {
        findViewById(C1715R.C1718id.homepage_container).setPadding(0, getResources().getDimensionPixelSize(C1715R.dimen.search_bar_height) + (getResources().getDimensionPixelSize(C1715R.dimen.search_bar_margin) * 2), 0, 0);
    }
}
