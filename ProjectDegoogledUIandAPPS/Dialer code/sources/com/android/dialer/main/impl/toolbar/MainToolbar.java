package com.android.dialer.main.impl.toolbar;

import android.content.Context;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.simulator.Simulator;
import com.android.dialer.simulator.SimulatorComponent;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.ViewUtil$ViewRunnable;
import com.google.common.base.Optional;

public final class MainToolbar extends Toolbar implements PopupMenu.OnMenuItemClickListener {
    private static final AccelerateDecelerateInterpolator SLIDE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private boolean hasGlobalLayoutListener;
    private SearchBarListener listener;
    private MainToolbarMenu overflowMenu;
    private SearchBarView searchBar;

    public MainToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.toolbarStyle);
    }

    public void collapse(boolean z) {
        this.searchBar.collapse(z);
    }

    public void expand(boolean z, Optional<String> optional, boolean z2) {
        this.searchBar.expand(z, optional, z2);
    }

    public String getQuery() {
        return this.searchBar.getQuery();
    }

    public void hideKeyboard() {
        this.searchBar.hideKeyboard();
    }

    public boolean isExpanded() {
        return this.searchBar.isExpanded();
    }

    public boolean isSlideUp() {
        return getHeight() != 0 && getTranslationY() == ((float) (-getHeight()));
    }

    public /* synthetic */ void lambda$onFinishInflate$0$MainToolbar(View view) {
        this.overflowMenu.show();
    }

    public /* synthetic */ void lambda$slideUp$1$MainToolbar(boolean z, View view, View view2) {
        this.hasGlobalLayoutListener = false;
        slideUp(z, view);
    }

    public void maybeShowSimulator(AppCompatActivity appCompatActivity) {
        MenuItem findItem = this.overflowMenu.getMenu().findItem(R.id.menu_simulator_submenu);
        Simulator simulator = SimulatorComponent.get(appCompatActivity).getSimulator();
        if (simulator.shouldShow()) {
            findItem.setVisible(true);
            findItem.setActionProvider(simulator.getActionProvider(appCompatActivity));
            return;
        }
        findItem.setVisible(false);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        ImageButton imageButton = (ImageButton) findViewById(R.id.main_options_menu_button);
        this.overflowMenu = new MainToolbarMenu(getContext(), imageButton);
        this.overflowMenu.inflate(R.menu.main_menu);
        this.overflowMenu.setOnMenuItemClickListener(this);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MainToolbar.this.lambda$onFinishInflate$0$MainToolbar(view);
            }
        });
        imageButton.setOnTouchListener(this.overflowMenu.getDragToOpenListener());
        this.searchBar = (SearchBarView) findViewById(R.id.search_view_container);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.listener.onMenuItemClicked(menuItem);
    }

    public void setSearchBarListener(SearchBarListener searchBarListener) {
        Assert.isNotNull(searchBarListener);
        this.listener = searchBarListener;
        this.searchBar.setSearchBarListener(searchBarListener);
    }

    public void showClearFrequents(boolean z) {
        this.overflowMenu.getMenu().findItem(R.id.clear_frequents).setVisible(z);
    }

    public void showKeyboard() {
        this.searchBar.showKeyboard();
    }

    public void slideDown(boolean z, View view) {
        if (getTranslationY() == 0.0f) {
            LogUtil.m8e("MainToolbar.slideDown", "Already slide down.", new Object[0]);
            return;
        }
        long j = 300;
        animate().translationY(0.0f).setDuration(z ? 300 : 0).setInterpolator(SLIDE_INTERPOLATOR).start();
        ViewPropertyAnimator translationY = view.animate().translationY(0.0f);
        if (!z) {
            j = 0;
        }
        translationY.setDuration(j).setInterpolator(SLIDE_INTERPOLATOR).start();
    }

    public void slideUp(boolean z, View view) {
        if (!this.hasGlobalLayoutListener) {
            if (getHeight() == 0) {
                this.hasGlobalLayoutListener = true;
                CallUtil.doOnGlobalLayout(this, new ViewUtil$ViewRunnable(z, view) {
                    private final /* synthetic */ boolean f$1;
                    private final /* synthetic */ View f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run(View view) {
                        MainToolbar.this.lambda$slideUp$1$MainToolbar(this.f$1, this.f$2, view);
                    }
                });
            } else if (isSlideUp()) {
                LogUtil.m8e("MainToolbar.slideDown", "Already slide up.", new Object[0]);
            } else {
                long j = 300;
                animate().translationY((float) (-getHeight())).setDuration(z ? 300 : 0).setInterpolator(SLIDE_INTERPOLATOR).start();
                ViewPropertyAnimator translationY = view.animate().translationY((float) (-getHeight()));
                if (!z) {
                    j = 0;
                }
                translationY.setDuration(j).setInterpolator(SLIDE_INTERPOLATOR).start();
            }
        }
    }

    public void transferQueryFromDialpad(String str) {
        this.searchBar.setQueryWithoutUpdate(str);
    }
}
