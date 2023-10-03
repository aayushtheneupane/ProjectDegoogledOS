package com.android.dialer.main.impl;

import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;
import com.android.contacts.common.dialog.ClearFrequentsDialog;
import com.android.dialer.R;
import com.android.dialer.app.calllog.CallLogActivity;
import com.android.dialer.app.settings.DialerSettingsActivity;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.common.LogUtil;
import com.android.dialer.dialpadview.DialpadFragment;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindings;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.ScreenEvent$Type;
import com.android.dialer.main.impl.bottomnav.BottomNavBar;
import com.android.dialer.main.impl.toolbar.MainToolbar;
import com.android.dialer.main.impl.toolbar.SearchBarListener;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.dialer.smartdial.util.SmartDialNameMatcher;
import com.android.dialer.util.TransactionSafeActivity;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.List;

public class MainSearchController implements SearchBarListener {
    /* access modifiers changed from: private */
    public final TransactionSafeActivity activity;
    private final BottomNavBar bottomNav;
    private boolean callPlacedFromSearch;
    private boolean closeSearchOnPause;
    /* access modifiers changed from: private */
    public DialpadFragment dialpadFragment;
    private final FloatingActionButton fab;
    private final View fragmentContainer;
    private final List<OnSearchShowListener> onSearchShowListenerList = new ArrayList();
    private boolean requestingPermission;
    private NewSearchFragment searchFragment;
    private final MainToolbar toolbar;
    private final View toolbarShadow;

    public interface OnSearchShowListener {
        void onSearchClose();

        void onSearchOpen();
    }

    public MainSearchController(TransactionSafeActivity transactionSafeActivity, BottomNavBar bottomNavBar, FloatingActionButton floatingActionButton, MainToolbar mainToolbar, View view, View view2) {
        this.activity = transactionSafeActivity;
        this.bottomNav = bottomNavBar;
        this.fab = floatingActionButton;
        this.toolbar = mainToolbar;
        this.toolbarShadow = view;
        this.fragmentContainer = view2;
        this.dialpadFragment = (DialpadFragment) transactionSafeActivity.getFragmentManager().findFragmentByTag("dialpad_fragment_tag");
        this.searchFragment = (NewSearchFragment) transactionSafeActivity.getFragmentManager().findFragmentByTag("search_fragment_tag");
    }

    private void closeKeyboard() {
        NewSearchFragment newSearchFragment = this.searchFragment;
        if (newSearchFragment != null && newSearchFragment.isAdded()) {
            this.toolbar.hideKeyboard();
        }
    }

    private void closeSearch(boolean z) {
        LogUtil.enterBlock("MainSearchController.closeSearch");
        NewSearchFragment newSearchFragment = this.searchFragment;
        if (newSearchFragment == null) {
            LogUtil.m8e("MainSearchController.closeSearch", "Search fragment is null.", new Object[0]);
        } else if (!newSearchFragment.isAdded()) {
            LogUtil.m8e("MainSearchController.closeSearch", "Search fragment isn't added.", new Object[0]);
        } else if (this.searchFragment.isHidden()) {
            LogUtil.m8e("MainSearchController.closeSearch", "Search fragment is already hidden.", new Object[0]);
        } else {
            if (isDialpadVisible()) {
                hideDialpad(z);
            } else if (!this.fab.isShown()) {
                this.fab.show();
            }
            this.bottomNav.setVisibility(0);
            this.toolbar.collapse(z);
            this.toolbarShadow.setVisibility(8);
            this.activity.getFragmentManager().beginTransaction().hide(this.searchFragment).commit();
            DialpadFragment dialpadFragment2 = this.dialpadFragment;
            if (dialpadFragment2 != null) {
                dialpadFragment2.getDigitsWidget().setImportantForAccessibility(2);
                this.dialpadFragment.clearDialpad();
                this.dialpadFragment.getDigitsWidget().setImportantForAccessibility(0);
            }
            for (OnSearchShowListener onSearchClose : this.onSearchShowListenerList) {
                onSearchClose.onSearchClose();
            }
        }
    }

    private void hideDialpad(boolean z) {
        LogUtil.enterBlock("MainSearchController.hideDialpad");
        DialpadFragment dialpadFragment2 = this.dialpadFragment;
        if (dialpadFragment2 == null) {
            LogUtil.m8e("MainSearchController.hideDialpad", "Dialpad fragment is null.", new Object[0]);
        } else if (!dialpadFragment2.isAdded()) {
            LogUtil.m8e("MainSearchController.hideDialpad", "Dialpad fragment is not added.", new Object[0]);
        } else if (this.dialpadFragment.isHidden()) {
            LogUtil.m8e("MainSearchController.hideDialpad", "Dialpad fragment is already hidden.", new Object[0]);
        } else if (!this.dialpadFragment.isDialpadSlideUp()) {
            LogUtil.m8e("MainSearchController.hideDialpad", "Dialpad fragment is already slide down.", new Object[0]);
        } else {
            this.fab.show();
            this.toolbar.slideDown(z, this.fragmentContainer);
            this.toolbar.transferQueryFromDialpad(this.dialpadFragment.getQuery());
            this.activity.setTitle(R.string.main_activity_label);
            this.dialpadFragment.setAnimate(z);
            this.dialpadFragment.slideDown(z, new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                    if (MainSearchController.this.activity.isSafeToCommitTransactions() && !MainSearchController.this.activity.isFinishing() && !MainSearchController.this.activity.isDestroyed()) {
                        MainSearchController.this.activity.getFragmentManager().beginTransaction().hide(MainSearchController.this.dialpadFragment).commit();
                    }
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
        }
    }

    private boolean isDialpadVisible() {
        DialpadFragment dialpadFragment2 = this.dialpadFragment;
        return dialpadFragment2 != null && dialpadFragment2.isAdded() && !this.dialpadFragment.isHidden() && this.dialpadFragment.isDialpadSlideUp();
    }

    private boolean isSearchVisible() {
        NewSearchFragment newSearchFragment = this.searchFragment;
        return newSearchFragment != null && newSearchFragment.isAdded() && !this.searchFragment.isHidden();
    }

    private void notifyListenersOnSearchOpen() {
        for (OnSearchShowListener onSearchOpen : this.onSearchShowListenerList) {
            onSearchOpen.onSearchOpen();
        }
    }

    private void openSearch(Optional<String> optional) {
        LogUtil.enterBlock("MainSearchController.openSearch");
        ((LoggingBindingsStub) Logger.get(this.activity)).logScreenView(ScreenEvent$Type.MAIN_SEARCH, this.activity);
        this.fab.hide();
        this.toolbar.expand(true, optional, true);
        this.toolbar.showKeyboard();
        this.toolbarShadow.setVisibility(0);
        this.bottomNav.setVisibility(8);
        FragmentTransaction beginTransaction = this.activity.getFragmentManager().beginTransaction();
        if (this.searchFragment == null) {
            this.searchFragment = new NewSearchFragment();
            beginTransaction.add(R.id.search_fragment_container, this.searchFragment, "search_fragment_tag");
            beginTransaction.setTransition(4099);
        } else if (!isSearchVisible()) {
            beginTransaction.show(this.searchFragment);
        }
        this.searchFragment.setQuery(optional.isPresent() ? optional.get() : "", CallInitiationType$Type.REGULAR_SEARCH);
        if (this.activity.isSafeToCommitTransactions()) {
            beginTransaction.commit();
        }
        notifyListenersOnSearchOpen();
    }

    public boolean isInSearch() {
        return isSearchVisible();
    }

    public void onActivityPause() {
        LogUtil.enterBlock("MainSearchController.onActivityPause");
        closeKeyboard();
        if (this.closeSearchOnPause) {
            if (isInSearch() && (this.callPlacedFromSearch || !isDialpadVisible())) {
                closeSearch(false);
            }
            this.closeSearchOnPause = false;
            this.callPlacedFromSearch = false;
        }
    }

    public boolean onBackPressed() {
        DialerImpression$Type dialerImpression$Type;
        if (isDialpadVisible() && !TextUtils.isEmpty(this.dialpadFragment.getQuery())) {
            LogUtil.m9i("MainSearchController.onBackPressed", "Dialpad visible with query", new Object[0]);
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_PRESS_BACK_BUTTON_TO_HIDE_DIALPAD);
            hideDialpad(true);
            return true;
        } else if (!isSearchVisible()) {
            return false;
        } else {
            LogUtil.m9i("MainSearchController.onBackPressed", "Search is visible", new Object[0]);
            LoggingBindings loggingBindings = Logger.get(this.activity);
            if (isDialpadVisible()) {
                dialerImpression$Type = DialerImpression$Type.MAIN_PRESS_BACK_BUTTON_TO_CLOSE_SEARCH_AND_DIALPAD;
            } else {
                dialerImpression$Type = DialerImpression$Type.MAIN_PRESS_BACK_BUTTON_TO_CLOSE_SEARCH;
            }
            ((LoggingBindingsStub) loggingBindings).logImpression(dialerImpression$Type);
            closeSearch(true);
            return true;
        }
    }

    public void onCallPlacedFromSearch() {
        this.closeSearchOnPause = true;
        this.callPlacedFromSearch = true;
    }

    public void onDialpadQueryChanged(String str) {
        String normalizeNumber = SmartDialNameMatcher.normalizeNumber(this.activity, str);
        NewSearchFragment newSearchFragment = this.searchFragment;
        if (newSearchFragment != null) {
            newSearchFragment.setRawNumber(str);
            this.searchFragment.setQuery(normalizeNumber, CallInitiationType$Type.DIALPAD);
        }
        this.dialpadFragment.process_quote_emergency_unquote(normalizeNumber);
    }

    public void onDialpadShown() {
        LogUtil.enterBlock("MainSearchController.onDialpadShown");
        this.dialpadFragment.slideUp(true);
        this.bottomNav.setVisibility(8);
    }

    public boolean onMenuItemClicked(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.settings) {
            TransactionSafeActivity transactionSafeActivity = this.activity;
            transactionSafeActivity.startActivity(new Intent(transactionSafeActivity, DialerSettingsActivity.class));
            ((LoggingBindingsStub) Logger.get(this.activity)).logScreenView(ScreenEvent$Type.SETTINGS, this.activity);
            return true;
        } else if (menuItem.getItemId() == R.id.clear_frequents) {
            new ClearFrequentsDialog().show(this.activity.getFragmentManager(), "clearFrequents");
            ((LoggingBindingsStub) Logger.get(this.activity)).logScreenView(ScreenEvent$Type.CLEAR_FREQUENTS, this.activity);
            return true;
        } else if (menuItem.getItemId() != R.id.menu_call_history) {
            return false;
        } else {
            this.activity.startActivity(new Intent(this.activity, CallLogActivity.class));
            return false;
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        this.toolbarShadow.setVisibility(bundle.getInt("toolbar_shadow_visibility"));
        if (bundle.getBoolean("is_fab_hidden", false)) {
            this.fab.hide();
        }
        boolean z = bundle.getBoolean("is_toolbar_slide_up", false);
        if (z) {
            this.toolbar.slideUp(false, this.fragmentContainer);
        }
        if (bundle.getBoolean("is_toolbar_expanded", false)) {
            this.toolbar.expand(false, Optional.absent(), !z);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("is_fab_hidden", !this.fab.isShown());
        bundle.putInt("toolbar_shadow_visibility", this.toolbarShadow.getVisibility());
        bundle.putBoolean("is_toolbar_expanded", this.toolbar.isExpanded());
        bundle.putBoolean("is_toolbar_slide_up", this.toolbar.isSlideUp());
    }

    public void onSearchBackButtonClicked() {
        LogUtil.enterBlock("MainSearchController.onSearchBackButtonClicked");
        closeSearch(true);
    }

    public void onSearchBarClicked() {
        LogUtil.enterBlock("MainSearchController.onSearchBarClicked");
        ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_CLICK_SEARCH_BAR);
        openSearch(Optional.absent());
    }

    public void onSearchListTouch() {
        LogUtil.enterBlock("MainSearchController.onSearchListTouched");
        if (isDialpadVisible()) {
            if (TextUtils.isEmpty(this.dialpadFragment.getQuery())) {
                ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_TOUCH_DIALPAD_SEARCH_LIST_TO_CLOSE_SEARCH_AND_DIALPAD);
                closeSearch(true);
                return;
            }
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_TOUCH_DIALPAD_SEARCH_LIST_TO_HIDE_DIALPAD);
            hideDialpad(true);
        } else if (!isSearchVisible()) {
        } else {
            if (TextUtils.isEmpty(this.toolbar.getQuery())) {
                ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_TOUCH_SEARCH_LIST_TO_CLOSE_SEARCH);
                closeSearch(true);
                return;
            }
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_TOUCH_SEARCH_LIST_TO_HIDE_KEYBOARD);
            closeKeyboard();
        }
    }

    public void onSearchQueryUpdated(String str) {
        NewSearchFragment newSearchFragment = this.searchFragment;
        if (newSearchFragment != null) {
            newSearchFragment.setQuery(str, CallInitiationType$Type.REGULAR_SEARCH);
        }
    }

    public void onUserLeaveHint() {
        if (isInSearch()) {
            this.closeSearchOnPause = !this.requestingPermission;
            closeKeyboard();
        }
    }

    public void onVoiceButtonClicked(SearchBarListener.VoiceSearchResultCallback voiceSearchResultCallback) {
        ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_CLICK_SEARCH_BAR_VOICE_BUTTON);
        try {
            this.activity.startActivityForResult(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 1);
        } catch (ActivityNotFoundException unused) {
            Toast.makeText(this.activity, R.string.voice_search_not_available, 0).show();
        }
    }

    public void onVoiceResults(int i, Intent intent) {
        if (i == -1) {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
            if (stringArrayListExtra.size() > 0) {
                LogUtil.m9i("MainSearchController.onVoiceResults", "voice search - match found", new Object[0]);
                openSearch(Optional.m67of(stringArrayListExtra.get(0)));
                return;
            }
            LogUtil.m9i("MainSearchController.onVoiceResults", "voice search - nothing heard", new Object[0]);
            return;
        }
        LogUtil.m8e("MainSearchController.onVoiceResults", "voice search failed", new Object[0]);
    }

    public void requestingPermission() {
        LogUtil.enterBlock("MainSearchController.requestingPermission");
        this.requestingPermission = true;
    }

    /* access modifiers changed from: package-private */
    public void setDialpadFragment(DialpadFragment dialpadFragment2) {
        this.dialpadFragment = dialpadFragment2;
    }

    /* access modifiers changed from: package-private */
    public void setSearchFragment(NewSearchFragment newSearchFragment) {
        this.searchFragment = newSearchFragment;
    }

    public void showDialpad(boolean z) {
        LogUtil.enterBlock("MainSearchController.showDialpad");
        showDialpad(z, false);
    }

    public void showDialpadFromNewIntent() {
        LogUtil.enterBlock("MainSearchController.showDialpadFromNewIntent");
        if (isDialpadVisible()) {
            LogUtil.m9i("MainSearchController.showDialpadFromNewIntent", "Dialpad is already visible.", new Object[0]);
            this.dialpadFragment.setStartedFromNewIntent(true);
            return;
        }
        showDialpad(false, true);
    }

    private void showDialpad(boolean z, boolean z2) {
        if (isDialpadVisible()) {
            LogUtil.m8e("MainSearchController.showDialpad", "Dialpad is already visible.", new Object[0]);
            return;
        }
        ((LoggingBindingsStub) Logger.get(this.activity)).logScreenView(ScreenEvent$Type.MAIN_DIALPAD, this.activity);
        this.fab.hide();
        this.toolbar.slideUp(z, this.fragmentContainer);
        this.toolbar.expand(z, Optional.absent(), false);
        this.toolbarShadow.setVisibility(0);
        this.activity.setTitle(R.string.dialpad_activity_title);
        FragmentTransaction beginTransaction = this.activity.getFragmentManager().beginTransaction();
        if (this.searchFragment == null) {
            this.searchFragment = new NewSearchFragment();
            beginTransaction.add(R.id.search_fragment_container, this.searchFragment, "search_fragment_tag");
            beginTransaction.setTransition(4099);
        } else if (!isSearchVisible()) {
            beginTransaction.show(this.searchFragment);
        }
        DialpadFragment dialpadFragment2 = this.dialpadFragment;
        if (dialpadFragment2 == null) {
            this.dialpadFragment = new DialpadFragment();
            this.dialpadFragment.setStartedFromNewIntent(z2);
            beginTransaction.add(R.id.dialpad_fragment_container, this.dialpadFragment, "dialpad_fragment_tag");
            this.searchFragment.setQuery("", CallInitiationType$Type.DIALPAD);
        } else {
            dialpadFragment2.setStartedFromNewIntent(z2);
            beginTransaction.show(this.dialpadFragment);
        }
        beginTransaction.commit();
        notifyListenersOnSearchOpen();
    }
}
