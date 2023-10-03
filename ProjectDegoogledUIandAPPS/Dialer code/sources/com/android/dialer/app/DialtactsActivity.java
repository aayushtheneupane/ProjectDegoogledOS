package com.android.dialer.app;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Trace;
import android.provider.ContactsContract;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.view.ViewPager;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.appcompat.R$style;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.android.contacts.common.dialog.ClearFrequentsDialog;
import com.android.contacts.common.list.OnPhoneNumberPickerActionListener;
import com.android.dialer.R;
import com.android.dialer.animation.AnimUtils;
import com.android.dialer.animation.AnimationListenerAdapter;
import com.android.dialer.app.calllog.CallLogActivity;
import com.android.dialer.app.calllog.CallLogAdapter;
import com.android.dialer.app.calllog.CallLogFragment;
import com.android.dialer.app.calllog.CallLogNotificationsService;
import com.android.dialer.app.calllog.IntentProvider;
import com.android.dialer.app.list.DragDropController;
import com.android.dialer.app.list.ListsFragment;
import com.android.dialer.app.list.OldSpeedDialFragment;
import com.android.dialer.app.list.OnDragDropListener;
import com.android.dialer.app.list.OnListFragmentScrolledListener;
import com.android.dialer.app.list.PhoneFavoriteSquareTileView;
import com.android.dialer.app.settings.DialerSettingsActivity;
import com.android.dialer.app.widget.ActionBarController;
import com.android.dialer.app.widget.SearchEditTextLayout;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.callintent.CallSpecificAppData;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.contactsfragment.ContactsFragment;
import com.android.dialer.database.Database;
import com.android.dialer.database.DialerDatabaseHelper;
import com.android.dialer.dialpadview.DialpadFragment;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.interactions.PhoneNumberInteraction;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.InteractionEvent$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.ScreenEvent$Type;
import com.android.dialer.logging.UiAction$Type;
import com.android.dialer.metrics.MetricsComponent;
import com.android.dialer.metrics.StubMetrics;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.postcall.PostCall;
import com.android.dialer.precall.PreCall;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.dialer.simulator.Simulator;
import com.android.dialer.simulator.SimulatorComponent;
import com.android.dialer.smartdial.util.SmartDialNameMatcher;
import com.android.dialer.smartdial.util.SmartDialPrefix;
import com.android.dialer.storage.StorageComponent;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.DialerUtils;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.util.TouchPointManager;
import com.android.dialer.util.TransactionSafeActivity;
import com.android.dialer.util.ViewUtil$ViewRunnable;
import com.android.dialer.widget.FloatingActionButtonController;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DialtactsActivity extends TransactionSafeActivity implements View.OnClickListener, DialpadFragment.OnDialpadQueryChangedListener, OnListFragmentScrolledListener, CallLogFragment.HostInterface, CallLogAdapter.OnActionModeStateChangedListener, ContactsFragment.OnContactsListScrolledListener, DialpadFragment.HostInterface, OldSpeedDialFragment.HostInterface, OnDragDropListener, OnPhoneNumberPickerActionListener, PopupMenu.OnMenuItemClickListener, ViewPager.OnPageChangeListener, ActionBarController.ActivityUi, PhoneNumberInteraction.InteractionErrorListener, PhoneNumberInteraction.DisambigDialogDismissedListener, ActivityCompat.OnRequestPermissionsResultCallback, DialpadFragment.DialpadListener, NewSearchFragment.SearchFragmentListener, ContactsFragment.OnContactSelectedListener {
    public static final String EXTRA_SHOW_TAB = "EXTRA_SHOW_TAB";
    private static final long HISTORY_TAB_SEEN_TIMEOUT = TimeUnit.SECONDS.toMillis(3);
    public static final String TAG_DIALPAD_FRAGMENT = "dialpad";
    private static Optional<Boolean> voiceSearchEnabledForTest = Optional.absent();
    /* access modifiers changed from: private */
    public ActionBarController actionBarController;
    private int actionBarHeight;
    private boolean clearSearchOnPause;
    private DialerDatabaseHelper dialerDatabaseHelper;
    protected DialpadFragment dialpadFragment;
    private String dialpadQuery;
    /* access modifiers changed from: private */
    public DragDropController dragDropController;
    private boolean firstLaunch;
    /* access modifiers changed from: private */
    public FloatingActionButtonController floatingActionButtonController;
    private boolean inCallDialpadUp;
    /* access modifiers changed from: private */
    public boolean inDialpadSearch;
    private boolean inNewSearch;
    /* access modifiers changed from: private */
    public boolean inRegularSearch;
    /* access modifiers changed from: private */
    public boolean isDialpadShown;
    private boolean isKeyboardOpen;
    private boolean isLandscape;
    private boolean isLastTabEnabled;
    public boolean isMultiSelectModeEnabled;
    private boolean isRestarting;
    /* access modifiers changed from: private */
    public ListsFragment listsFragment;
    /* access modifiers changed from: private */
    public NewSearchFragment newSearchFragment;
    private PopupMenu overflowMenu;
    private CoordinatorLayout parentLayout;
    private String pendingSearchViewQuery;
    private final TextWatcher phoneSearchQueryTextListener = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            String charSequence2 = charSequence.toString();
            if (!charSequence2.equals(DialtactsActivity.this.searchQuery)) {
                if (i3 != 0) {
                    PerformanceReport.recordClick(UiAction$Type.TEXT_CHANGE_WITH_INPUT);
                }
                "called with new query: " + charSequence2;
                "previous query: " + DialtactsActivity.this.searchQuery;
                String unused = DialtactsActivity.this.searchQuery = charSequence2;
                if (!TextUtils.isEmpty(charSequence2)) {
                    if (!((DialtactsActivity.this.isDialpadShown && DialtactsActivity.this.inDialpadSearch) || (!DialtactsActivity.this.isDialpadShown && DialtactsActivity.this.inRegularSearch))) {
                        DialtactsActivity dialtactsActivity = DialtactsActivity.this;
                        dialtactsActivity.enterSearchUi(dialtactsActivity.isDialpadShown, DialtactsActivity.this.searchQuery, true);
                    }
                }
                if (DialtactsActivity.this.newSearchFragment != null && DialtactsActivity.this.newSearchFragment.isVisible()) {
                    DialtactsActivity.this.newSearchFragment.setQuery(DialtactsActivity.this.searchQuery, DialtactsActivity.access$800(DialtactsActivity.this));
                }
            }
        }
    };
    private int previouslySelectedTabIndex;
    private String savedLanguageCode;
    private SearchEditTextLayout searchEditTextLayout;
    /* access modifiers changed from: private */
    public String searchQuery;
    /* access modifiers changed from: private */
    public EditText searchView;
    private final View.OnClickListener searchViewOnClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (!DialtactsActivity.this.isInSearchUi()) {
                PerformanceReport.recordClick(UiAction$Type.OPEN_SEARCH);
                DialtactsActivity.this.actionBarController.onSearchBoxTapped();
                DialtactsActivity dialtactsActivity = DialtactsActivity.this;
                dialtactsActivity.enterSearchUi(false, dialtactsActivity.searchView.getText().toString(), true);
            }
        }
    };
    private Animation slideIn;
    AnimationListenerAdapter slideInListener = new AnimationListenerAdapter() {
        public void onAnimationEnd(Animation animation) {
            DialtactsActivity.this.maybeEnterSearchUi();
        }
    };
    private Animation slideOut;
    AnimationListenerAdapter slideOutListener = new AnimationListenerAdapter() {
        public void onAnimationEnd(Animation animation) {
            DialtactsActivity.this.commitDialpadFragmentHide();
        }
    };
    private boolean stateSaved;
    private long timeTabSelected;
    private View voiceSearchButton;
    private String voiceSearchQuery;
    private boolean wasConfigurationChange;

    private class LayoutOnDragListener implements View.OnDragListener {
        /* synthetic */ LayoutOnDragListener(C02861 r2) {
        }

        public boolean onDrag(View view, DragEvent dragEvent) {
            if (dragEvent.getAction() != 2) {
                return true;
            }
            DialtactsActivity.this.dragDropController.handleDragHovered(view, (int) dragEvent.getX(), (int) dragEvent.getY());
            return true;
        }
    }

    protected class OptionsPopupMenu extends PopupMenu {
        public OptionsPopupMenu(Context context, View view) {
            super(context, view, 8388613);
        }

        public void show() {
            Menu menu = getMenu();
            menu.findItem(R.id.menu_clear_frequents).setVisible(PermissionsUtil.hasContactsReadPermissions(DialtactsActivity.this) && DialtactsActivity.this.listsFragment != null && DialtactsActivity.this.listsFragment.hasFrequents());
            menu.findItem(R.id.menu_history).setVisible(PermissionsUtil.hasPhonePermissions(DialtactsActivity.this));
            Context applicationContext = DialtactsActivity.this.getApplicationContext();
            MenuItem findItem = menu.findItem(R.id.menu_simulator_submenu);
            Simulator simulator = SimulatorComponent.get(applicationContext).getSimulator();
            if (simulator.shouldShow()) {
                findItem.setVisible(true);
                findItem.setActionProvider(simulator.getActionProvider(DialtactsActivity.this));
            } else {
                findItem.setVisible(false);
            }
            super.show();
        }
    }

    static /* synthetic */ CallInitiationType$Type access$800(DialtactsActivity dialtactsActivity) {
        if (dialtactsActivity.isDialpadShown) {
            return CallInitiationType$Type.DIALPAD;
        }
        return CallInitiationType$Type.REGULAR_SEARCH;
    }

    /* access modifiers changed from: private */
    public void commitDialpadFragmentHide() {
        DialpadFragment dialpadFragment2;
        if (!this.stateSaved && (dialpadFragment2 = this.dialpadFragment) != null && !dialpadFragment2.isHidden() && !isDestroyed()) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.hide(this.dialpadFragment);
            beginTransaction.commit();
        }
        this.floatingActionButtonController.scaleIn();
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void displayFragment(android.content.Intent r7) {
        /*
            r6 = this;
            java.lang.String r0 = r7.getAction()
            java.lang.String r1 = "android.intent.action.CALL_BUTTON"
            boolean r0 = r1.equals(r0)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0019
            boolean r0 = com.android.dialer.telecom.TelecomUtil.isInManagedCall(r6)
            if (r0 == 0) goto L_0x0019
            com.android.dialer.telecom.TelecomUtil.showInCallScreen(r6, r2)
            r0 = r1
            goto L_0x001a
        L_0x0019:
            r0 = r2
        L_0x001a:
            if (r0 == 0) goto L_0x0020
            r6.finish()
            return
        L_0x0020:
            java.lang.String r0 = r7.getAction()
            java.lang.String r3 = "ACTION_SHOW_TAB"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x003a
            boolean r0 = com.android.dialer.telecom.TelecomUtil.isInManagedCall(r6)
            if (r0 == 0) goto L_0x003a
            boolean r0 = com.android.dialer.dialpadview.DialpadFragment.isAddCallMode(r7)
            if (r0 != 0) goto L_0x003a
            r0 = r1
            goto L_0x003b
        L_0x003a:
            r0 = r2
        L_0x003b:
            android.net.Uri r3 = r7.getData()
            if (r3 == 0) goto L_0x0078
            java.lang.String r3 = r7.getAction()
            java.lang.String r4 = "android.intent.action.DIAL"
            boolean r4 = r4.equals(r3)
            if (r4 != 0) goto L_0x0073
            java.lang.String r4 = "com.android.phone.action.TOUCH_DIALER"
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x0056
            goto L_0x0073
        L_0x0056:
            java.lang.String r4 = "android.intent.action.VIEW"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0071
            android.net.Uri r3 = r7.getData()
            if (r3 == 0) goto L_0x0071
            java.lang.String r3 = r3.getScheme()
            java.lang.String r4 = "tel"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0071
            goto L_0x0073
        L_0x0071:
            r3 = r2
            goto L_0x0074
        L_0x0073:
            r3 = r1
        L_0x0074:
            if (r3 == 0) goto L_0x0078
            r3 = r1
            goto L_0x0079
        L_0x0078:
            r3 = r2
        L_0x0079:
            boolean r7 = com.android.dialer.dialpadview.DialpadFragment.isAddCallMode(r7)
            if (r0 != 0) goto L_0x00a5
            if (r3 != 0) goto L_0x00a5
            if (r7 == 0) goto L_0x0084
            goto L_0x00a5
        L_0x0084:
            boolean r7 = r6.isLastTabEnabled
            if (r7 == 0) goto L_0x00d6
            com.android.dialer.storage.StorageComponent r7 = com.android.dialer.storage.StorageComponent.get(r6)
            android.content.SharedPreferences r7 = r7.unencryptedSharedPrefs()
            java.lang.String r0 = "last_tab"
            int r7 = r7.getInt(r0, r2)
            com.android.dialer.app.list.ListsFragment r6 = r6.listsFragment
            if (r6 == 0) goto L_0x00a1
            r6.showTab(r7)
            com.android.dialer.performancereport.PerformanceReport.setStartingTabIndex(r7)
            goto L_0x00d6
        L_0x00a1:
            com.android.dialer.performancereport.PerformanceReport.setStartingTabIndex(r2)
            goto L_0x00d6
        L_0x00a5:
            r4 = 3
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r0)
            r4[r2] = r5
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r4[r1] = r3
            r3 = 2
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            r4[r3] = r7
            java.lang.String r7 = "DialtactsActivity.displayFragment"
            java.lang.String r3 = "show dialpad fragment (showDialpadChooser: %b, isDialIntent: %b, isAddCallIntent: %b)"
            com.android.dialer.common.LogUtil.m9i(r7, r3, r4)
            r6.showDialpadFragment(r2)
            com.android.dialer.dialpadview.DialpadFragment r7 = r6.dialpadFragment
            r7.setStartedFromNewIntent(r1)
            if (r0 == 0) goto L_0x00d6
            com.android.dialer.dialpadview.DialpadFragment r7 = r6.dialpadFragment
            boolean r7 = r7.isVisible()
            if (r7 != 0) goto L_0x00d6
            r6.inCallDialpadUp = r1
        L_0x00d6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.DialtactsActivity.displayFragment(android.content.Intent):void");
    }

    /* access modifiers changed from: private */
    public void enterSearchUi(boolean z, String str, boolean z2) {
        CallInitiationType$Type callInitiationType$Type;
        LogUtil.m9i("DialtactsActivity.enterSearchUi", "smart dial: %b", Boolean.valueOf(z));
        if (this.stateSaved || getFragmentManager().isDestroyed()) {
            LogUtil.m9i("DialtactsActivity.enterSearchUi", "not entering search UI (mStateSaved: %b, isDestroyed: %b)", Boolean.valueOf(this.stateSaved), Boolean.valueOf(getFragmentManager().isDestroyed()));
            return;
        }
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        this.inNewSearch = true;
        this.floatingActionButtonController.scaleOut();
        if (z2) {
            beginTransaction.setCustomAnimations(17498112, 0);
        } else {
            beginTransaction.setTransition(0);
        }
        NewSearchFragment newSearchFragment2 = (NewSearchFragment) getFragmentManager().findFragmentByTag("new_search");
        if (newSearchFragment2 == null) {
            newSearchFragment2 = new NewSearchFragment();
            beginTransaction.add(R.id.dialtacts_frame, newSearchFragment2, "new_search");
        } else {
            beginTransaction.show(newSearchFragment2);
        }
        newSearchFragment2.setHasOptionsMenu(false);
        if (this.isDialpadShown) {
            callInitiationType$Type = CallInitiationType$Type.DIALPAD;
        } else {
            callInitiationType$Type = CallInitiationType$Type.REGULAR_SEARCH;
        }
        newSearchFragment2.setQuery(str, callInitiationType$Type);
        beginTransaction.commit();
        if (z2) {
            View view = this.listsFragment.getView();
            Assert.isNotNull(view);
            view.animate().alpha(0.0f).withLayer();
        }
        this.listsFragment.setUserVisibleHint(false);
    }

    private void exitSearchUi() {
        LogUtil.enterBlock("DialtactsActivity.exitSearchUi");
        if (!getFragmentManager().isDestroyed() && !this.stateSaved) {
            this.searchView.setText((CharSequence) null);
            DialpadFragment dialpadFragment2 = this.dialpadFragment;
            if (dialpadFragment2 != null) {
                dialpadFragment2.clearDialpad();
            }
            this.inDialpadSearch = false;
            this.inRegularSearch = false;
            this.inNewSearch = false;
            if (this.floatingActionButtonController.isVisible() && getFabAlignment() != 2) {
                this.floatingActionButtonController.scaleOut(new FloatingActionButton.OnVisibilityChangedListener() {
                    public void onHidden(FloatingActionButton floatingActionButton) {
                        DialtactsActivity dialtactsActivity = DialtactsActivity.this;
                        dialtactsActivity.onPageScrolled(dialtactsActivity.listsFragment.getCurrentTabIndex(), 0.0f, 0);
                        DialtactsActivity.this.floatingActionButtonController.scaleIn();
                    }
                });
            } else if (!this.floatingActionButtonController.isVisible() && this.listsFragment.shouldShowFab()) {
                onPageScrolled(this.listsFragment.getCurrentTabIndex(), 0.0f, 0);
                DialerExecutorModule.getUiThreadHandler().postDelayed(new Runnable() {
                    public final void run() {
                        DialtactsActivity.this.lambda$exitSearchUi$5$DialtactsActivity();
                    }
                }, 300);
            }
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            NewSearchFragment newSearchFragment2 = this.newSearchFragment;
            if (newSearchFragment2 != null) {
                beginTransaction.remove(newSearchFragment2);
            }
            beginTransaction.commit();
            View view = this.listsFragment.getView();
            Assert.isNotNull(view);
            view.animate().alpha(1.0f).withLayer();
            DialpadFragment dialpadFragment3 = this.dialpadFragment;
            if (dialpadFragment3 == null || !dialpadFragment3.isVisible()) {
                this.listsFragment.sendScreenViewForCurrentPosition();
                this.listsFragment.setUserVisibleHint(true);
            }
            onPageSelected(this.listsFragment.getCurrentTabIndex());
            this.actionBarController.onSearchUiExited();
        }
    }

    private void hideDialpadFragment(boolean z, boolean z2) {
        LogUtil.enterBlock("DialtactsActivity.hideDialpadFragment");
        DialpadFragment dialpadFragment2 = this.dialpadFragment;
        if (dialpadFragment2 != null && dialpadFragment2.getView() != null) {
            if (z2) {
                this.dialpadFragment.getDigitsWidget().setImportantForAccessibility(2);
                this.dialpadFragment.clearDialpad();
                this.dialpadFragment.getDigitsWidget().setImportantForAccessibility(0);
            }
            if (this.isDialpadShown) {
                this.isDialpadShown = false;
                this.dialpadFragment.setAnimate(z);
                this.listsFragment.setUserVisibleHint(true);
                this.listsFragment.sendScreenViewForCurrentPosition();
                updateSearchFragmentPosition();
                this.floatingActionButtonController.align(getFabAlignment(), z);
                if (z) {
                    this.dialpadFragment.getView().startAnimation(this.slideOut);
                } else {
                    commitDialpadFragmentHide();
                }
                this.actionBarController.onDialpadDown();
                setTitle(R.string.launcherActivityLabel);
            }
        }
    }

    /* access modifiers changed from: private */
    public void maybeEnterSearchUi() {
        if (!isInSearchUi()) {
            enterSearchUi(true, this.searchQuery, false);
        }
    }

    static void setVoiceSearchEnabledForTest(Optional<Boolean> optional) {
        voiceSearchEnabledForTest = optional;
    }

    private void showDialpadFragment(boolean z) {
        LogUtil.m9i("DialtactActivity.showDialpadFragment", "animate: %b", Boolean.valueOf(z));
        if (this.isDialpadShown) {
            LogUtil.m9i("DialtactsActivity.showDialpadFragment", "dialpad already shown", new Object[0]);
        } else if (this.stateSaved) {
            LogUtil.m9i("DialtactsActivity.showDialpadFragment", "state already saved", new Object[0]);
        } else {
            this.isDialpadShown = true;
            this.listsFragment.setUserVisibleHint(false);
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            DialpadFragment dialpadFragment2 = this.dialpadFragment;
            if (dialpadFragment2 == null) {
                this.dialpadFragment = new DialpadFragment();
                beginTransaction.add(R.id.dialtacts_container, this.dialpadFragment, TAG_DIALPAD_FRAGMENT);
            } else {
                beginTransaction.show(dialpadFragment2);
            }
            this.dialpadFragment.setAnimate(z);
            ((LoggingBindingsStub) Logger.get(this)).logScreenView(ScreenEvent$Type.DIALPAD, this);
            beginTransaction.commit();
            if (z) {
                this.floatingActionButtonController.scaleOut();
                maybeEnterSearchUi();
            } else {
                this.floatingActionButtonController.scaleOut();
                maybeEnterSearchUi();
            }
            this.actionBarController.onDialpadUp();
            View view = this.listsFragment.getView();
            Assert.isNotNull(view);
            view.animate().alpha(0.0f).withLayer();
            setTitle(R.string.launcherDialpadActivityLabel);
        }
    }

    private void updateSearchFragmentPosition() {
        if (this.newSearchFragment != null) {
            int integer = getResources().getInteger(R.integer.dialpad_slide_in_duration);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.action_bar_height_large);
            int intrinsicHeight = getResources().getDrawable(R.drawable.search_shadow).getIntrinsicHeight();
            int i = 0;
            int i2 = isDialpadShown() ? dimensionPixelSize - intrinsicHeight : 0;
            if (!isDialpadShown()) {
                i = dimensionPixelSize - intrinsicHeight;
            }
            this.newSearchFragment.animatePosition(i2, i, integer);
        }
    }

    /* access modifiers changed from: protected */
    public OptionsPopupMenu buildOptionsMenu(View view) {
        OptionsPopupMenu optionsPopupMenu = new OptionsPopupMenu(this, view);
        optionsPopupMenu.inflate(R.menu.dialtacts_options);
        optionsPopupMenu.setOnMenuItemClickListener(this);
        return optionsPopupMenu;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            TouchPointManager.getInstance().setPoint((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void enableFloatingButton(boolean z) {
        new Object[1][0] = Boolean.valueOf(z);
        if (!isDialpadShown() || !z) {
            this.floatingActionButtonController.setVisible(z);
        }
    }

    public int getActionBarHeight() {
        return this.actionBarHeight;
    }

    public ImageView getDragShadowOverlay() {
        return (ImageView) findViewById(R.id.contact_tile_drag_shadow_overlay);
    }

    public int getFabAlignment() {
        if (((SharedPrefConfigProvider) ConfigProviderComponent.get(this).getConfigProvider()).getBoolean("enable_new_favorites_tab", false) || this.isLandscape || isInSearchUi() || this.listsFragment.getCurrentTabIndex() != 0) {
            return 2;
        }
        return 0;
    }

    public void getLastOutgoingCall(DialpadFragment.LastOutgoingCallCallback lastOutgoingCallCallback) {
        ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(this).dialerExecutorFactory()).createUiTaskBuilder(getFragmentManager(), "Query last phone number", $$Lambda$F9PVn1k2g9R6XLlY2hxC_fTA7ck.INSTANCE).onSuccess(new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                DialpadFragment.LastOutgoingCallCallback.this.lastOutgoingCall((String) obj);
            }
        }).build().executeParallel(this);
    }

    /* access modifiers changed from: protected */
    public int getSearchBoxHint() {
        return R.string.dialer_hint_find_contact;
    }

    /* access modifiers changed from: protected */
    public void handleMenuSettings() {
        startActivity(new Intent(this, DialerSettingsActivity.class));
    }

    public boolean hasSearchQuery() {
        return !TextUtils.isEmpty(this.searchQuery);
    }

    public void interactionError(int i) {
        if (i != 4) {
            throw new AssertionError(GeneratedOutlineSupport.outline5("PhoneNumberInteraction error: ", i));
        }
    }

    public boolean isActionModeStateEnabled() {
        return this.isMultiSelectModeEnabled;
    }

    public boolean isDialpadShown() {
        return this.isDialpadShown;
    }

    public boolean isInSearchUi() {
        return this.inDialpadSearch || this.inRegularSearch || this.inNewSearch;
    }

    public /* synthetic */ void lambda$exitSearchUi$5$DialtactsActivity() {
        this.floatingActionButtonController.scaleIn();
    }

    public /* synthetic */ void lambda$onActivityResult$3$DialtactsActivity(String str, View view) {
        startActivity(IntentProvider.getSendSmsIntentProvider(str).getClickIntent(this));
    }

    public /* synthetic */ void lambda$onCreate$0$DialtactsActivity(View view) {
        exitSearchUi();
    }

    public /* synthetic */ void lambda$onCreate$1$DialtactsActivity(View view) {
        this.floatingActionButtonController.setScreenWidth(this.parentLayout.getWidth());
        this.floatingActionButtonController.align(getFabAlignment(), false);
    }

    public /* synthetic */ void lambda$onResume$2$DialtactsActivity() {
        ((StubMetrics) MetricsComponent.get(this).metrics()).recordMemory("GoogleDialtactsActivity.onResume");
    }

    public void onActionModeStateChanged(ActionMode actionMode, boolean z) {
        this.isMultiSelectModeEnabled = z;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.m9i("DialtactsActivity.onActivityResult", "requestCode:%d, resultCode:%d", Integer.valueOf(i), Integer.valueOf(i2));
        if (i == 1) {
            if (i2 == -1) {
                ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
                if (stringArrayListExtra.size() > 0) {
                    this.voiceSearchQuery = stringArrayListExtra.get(0);
                } else {
                    LogUtil.m9i("DialtactsActivity.onActivityResult", "voice search - nothing heard", new Object[0]);
                }
            } else {
                LogUtil.m8e("DialtactsActivity.onActivityResult", "voice search failed", new Object[0]);
            }
        } else if (i == 2) {
            if (i2 == 1) {
                LogUtil.m9i("DialtactsActivity.onActivityResult", "returned from call composer, error occurred", new Object[0]);
                Snackbar.make((View) this.parentLayout, (CharSequence) getString(R.string.call_composer_connection_failed, new Object[]{intent.getStringExtra("contact_name")}), 0).show();
            } else {
                LogUtil.m9i("DialtactsActivity.onActivityResult", "returned from call composer, no error", new Object[0]);
            }
        } else if (i == 4) {
            if (i2 == -1 && intent != null && intent.getBooleanExtra("has_enriched_call_data", false)) {
                String stringExtra = intent.getStringExtra("phone_number");
                Snackbar make = Snackbar.make((View) this.parentLayout, (CharSequence) getString(R.string.ec_data_deleted), 5000);
                make.setAction((int) R.string.view_conversation, (View.OnClickListener) new View.OnClickListener(stringExtra) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        DialtactsActivity.this.lambda$onActivityResult$3$DialtactsActivity(this.f$1, view);
                    }
                });
                make.setActionTextColor(getResources().getColor(R.color.dialer_snackbar_action_text_color));
                make.show();
            }
        } else if (i == 3) {
            ((DuoStub) DuoComponent.get(this).getDuo()).reloadReachability(this);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onAttachFragment(Fragment fragment) {
        LogUtil.m9i("DialtactsActivity.onAttachFragment", "fragment: %s", fragment);
        if (fragment instanceof DialpadFragment) {
            this.dialpadFragment = (DialpadFragment) fragment;
        } else if (fragment instanceof ListsFragment) {
            this.listsFragment = (ListsFragment) fragment;
            this.listsFragment.addOnPageChangeListener(this);
        } else if (fragment instanceof NewSearchFragment) {
            this.newSearchFragment = (NewSearchFragment) fragment;
            updateSearchFragmentPosition();
        }
    }

    public void onBackPressed() {
        PerformanceReport.recordClick(UiAction$Type.PRESS_ANDROID_BACK_BUTTON);
        if (!this.stateSaved) {
            if (this.isDialpadShown) {
                hideDialpadFragment(true, false);
                if (TextUtils.isEmpty(this.dialpadQuery)) {
                    exitSearchUi();
                }
            } else if (!isInSearchUi()) {
                super.onBackPressed();
            } else if (this.isKeyboardOpen) {
                DialerUtils.hideInputMethod(this.parentLayout);
                PerformanceReport.recordClick(UiAction$Type.HIDE_KEYBOARD_IN_SEARCH);
            } else {
                exitSearchUi();
            }
        }
    }

    public void onCallPlacedFromDialpad() {
        this.clearSearchOnPause = true;
    }

    public void onCallPlacedFromSearch() {
        DialerUtils.hideInputMethod(this.parentLayout);
        this.clearSearchOnPause = true;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.floating_action_button) {
            if (!this.isDialpadShown) {
                LogUtil.m9i("DialtactsActivity.onClick", "floating action button clicked, going to show dialpad", new Object[0]);
                PerformanceReport.recordClick(UiAction$Type.OPEN_DIALPAD);
                this.inCallDialpadUp = false;
                showDialpadFragment(true);
                PostCall.closePrompt();
                return;
            }
            LogUtil.m9i("DialtactsActivity.onClick", "floating action button clicked, but dialpad is already showing", new Object[0]);
        } else if (id == R.id.voice_search_button) {
            try {
                startActivityForResult(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 1);
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(this, R.string.voice_search_not_available, 0).show();
            }
        } else if (id == R.id.dialtacts_options_menu_button) {
            this.overflowMenu.show();
        } else {
            throw new AssertionError(GeneratedOutlineSupport.outline6("Unexpected onClick event from ", view));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i = configuration.hardKeyboardHidden;
        if (i == 1) {
            this.isKeyboardOpen = true;
        } else if (i == 2) {
            this.isKeyboardOpen = false;
        }
    }

    public void onContactSelected(ImageView imageView, Uri uri, long j) {
        ((LoggingBindingsStub) Logger.get(this)).logInteraction(InteractionEvent$Type.OPEN_QUICK_CONTACT_FROM_CONTACTS_FRAGMENT_ITEM);
        ContactsContract.QuickContact.showQuickContact(this, imageView, uri, 3, (String[]) null);
    }

    public void onContactsListScrolled(boolean z) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Trace.beginSection("DialtactsActivity onCreate");
        LogUtil.enterBlock("DialtactsActivity.onCreate");
        super.onCreate(bundle);
        boolean z = true;
        this.firstLaunch = true;
        this.isLastTabEnabled = ((SharedPrefConfigProvider) ConfigProviderComponent.get(this).getConfigProvider()).getBoolean("last_tab_enabled", false);
        this.actionBarHeight = getResources().getDimensionPixelSize(R.dimen.action_bar_height_large);
        Trace.beginSection("DialtactsActivity setContentView");
        setContentView((int) R.layout.dialtacts_activity);
        Trace.endSection();
        getWindow().setBackgroundDrawable((Drawable) null);
        Trace.beginSection("DialtactsActivity setup Views");
        ActionBar supportActionBar = getSupportActionBar();
        Assert.isNotNull(supportActionBar);
        supportActionBar.setCustomView(R.layout.search_edittext);
        supportActionBar.setDisplayShowCustomEnabled(true);
        supportActionBar.setBackgroundDrawable((Drawable) null);
        this.searchEditTextLayout = (SearchEditTextLayout) supportActionBar.getCustomView().findViewById(R.id.search_view_container);
        this.actionBarController = new ActionBarController(this, this.searchEditTextLayout);
        this.searchView = (EditText) this.searchEditTextLayout.findViewById(R.id.search_view);
        this.searchView.addTextChangedListener(this.phoneSearchQueryTextListener);
        this.searchView.setHint(getSearchBoxHint());
        this.voiceSearchButton = this.searchEditTextLayout.findViewById(R.id.voice_search_button);
        this.searchEditTextLayout.findViewById(R.id.search_box_collapsed).setOnClickListener(this.searchViewOnClickListener);
        this.searchEditTextLayout.findViewById(R.id.search_back_button).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                DialtactsActivity.this.lambda$onCreate$0$DialtactsActivity(view);
            }
        });
        if (getResources().getConfiguration().orientation != 2) {
            z = false;
        }
        this.isLandscape = z;
        this.previouslySelectedTabIndex = 0;
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(this);
        this.floatingActionButtonController = new FloatingActionButtonController(this, floatingActionButton);
        ImageButton imageButton = (ImageButton) this.searchEditTextLayout.findViewById(R.id.dialtacts_options_menu_button);
        imageButton.setOnClickListener(this);
        this.overflowMenu = buildOptionsMenu(imageButton);
        imageButton.setOnTouchListener(this.overflowMenu.getDragToOpenListener());
        if (bundle == null) {
            getFragmentManager().beginTransaction().add(R.id.dialtacts_frame, new ListsFragment(), "favorites").commit();
        } else {
            this.searchQuery = bundle.getString("search_query");
            this.dialpadQuery = bundle.getString("dialpad_query");
            this.inRegularSearch = bundle.getBoolean("in_regular_search_ui");
            this.inDialpadSearch = bundle.getBoolean("in_dialpad_search_ui");
            this.inNewSearch = bundle.getBoolean("in_new_search_ui");
            this.firstLaunch = bundle.getBoolean("first_launch");
            this.savedLanguageCode = bundle.getString("saved_language_code");
            this.wasConfigurationChange = bundle.getBoolean("was_configuration_change");
            this.isDialpadShown = bundle.getBoolean("is_dialpad_shown");
            this.floatingActionButtonController.setVisible(bundle.getBoolean("fab_visible"));
            this.actionBarController.restoreInstanceState(bundle);
        }
        boolean isRtl = CallUtil.isRtl();
        if (this.isLandscape) {
            this.slideIn = AnimationUtils.loadAnimation(this, isRtl ? R.anim.dialpad_slide_in_left : R.anim.dialpad_slide_in_right);
            this.slideOut = AnimationUtils.loadAnimation(this, isRtl ? R.anim.dialpad_slide_out_left : R.anim.dialpad_slide_out_right);
        } else {
            this.slideIn = AnimationUtils.loadAnimation(this, R.anim.dialpad_slide_in_bottom);
            this.slideOut = AnimationUtils.loadAnimation(this, R.anim.dialpad_slide_out_bottom);
        }
        this.slideIn.setInterpolator(AnimUtils.EASE_IN);
        this.slideOut.setInterpolator(AnimUtils.EASE_OUT);
        this.slideIn.setAnimationListener(this.slideInListener);
        this.slideOut.setAnimationListener(this.slideOutListener);
        this.parentLayout = (CoordinatorLayout) findViewById(R.id.dialtacts_mainlayout);
        this.parentLayout.setOnDragListener(new LayoutOnDragListener((C02861) null));
        CallUtil.doOnGlobalLayout(floatingActionButton, new ViewUtil$ViewRunnable() {
            public final void run(View view) {
                DialtactsActivity.this.lambda$onCreate$1$DialtactsActivity(view);
            }
        });
        Trace.endSection();
        Trace.beginSection("DialtactsActivity initialize smart dialing");
        this.dialerDatabaseHelper = Database.get(this).getDatabaseHelper(this);
        SmartDialPrefix.initializeNanpSettings(this);
        Trace.endSection();
        Trace.endSection();
        updateSearchFragmentPosition();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        String str = this.pendingSearchViewQuery;
        if (str != null) {
            this.searchView.setText(str);
            this.pendingSearchViewQuery = null;
        }
        ActionBarController actionBarController2 = this.actionBarController;
        if (actionBarController2 == null) {
            return false;
        }
        actionBarController2.restoreActionBarOffset();
        return false;
    }

    public void onDialpadQueryChanged(String str) {
        this.dialpadQuery = str;
        NewSearchFragment newSearchFragment2 = this.newSearchFragment;
        if (newSearchFragment2 != null) {
            newSearchFragment2.setRawNumber(str);
        }
        String normalizeNumber = SmartDialNameMatcher.normalizeNumber(this, str);
        if (!TextUtils.equals(this.searchView.getText(), normalizeNumber)) {
            DialpadFragment dialpadFragment2 = this.dialpadFragment;
            if (dialpadFragment2 != null && dialpadFragment2.isVisible()) {
                this.searchView.setText(normalizeNumber);
            } else if (!TextUtils.isEmpty(normalizeNumber)) {
                this.pendingSearchViewQuery = normalizeNumber;
                return;
            } else {
                return;
            }
        }
        try {
            if (this.dialpadFragment != null && this.dialpadFragment.isVisible()) {
                this.dialpadFragment.process_quote_emergency_unquote(normalizeNumber);
            }
        } catch (Exception unused) {
        }
    }

    public void onDialpadShown() {
        LogUtil.enterBlock("DialtactsActivity.onDialpadShown");
        Assert.isNotNull(this.dialpadFragment);
        if (this.dialpadFragment.getAnimate()) {
            View view = this.dialpadFragment.getView();
            Assert.isNotNull(view);
            view.startAnimation(this.slideIn);
        } else {
            ((DialpadFragment.DialpadSlidingRelativeLayout) this.dialpadFragment.getView()).setYFraction(0.0f);
        }
        updateSearchFragmentPosition();
    }

    public boolean onDialpadSpacerTouchWithEmptyQuery() {
        return false;
    }

    public void onDisambigDialogDismissed() {
    }

    public void onDragFinished(int i, int i2) {
        this.listsFragment.showRemoveView(false);
    }

    public void onDragHovered(int i, int i2, PhoneFavoriteSquareTileView phoneFavoriteSquareTileView) {
    }

    public void onDragStarted(int i, int i2, PhoneFavoriteSquareTileView phoneFavoriteSquareTileView) {
        this.listsFragment.showRemoveView(true);
    }

    public void onDroppedOnRemove() {
    }

    public void onListFragmentScroll(int i, int i2, int i3) {
    }

    public void onListFragmentScrollStateChange(int i) {
        PerformanceReport.recordScrollStateChange(i);
        if (i == 1) {
            hideDialpadFragment(true, false);
            DialerUtils.hideInputMethod(this.parentLayout);
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        if (!isSafeToCommitTransactions()) {
            return true;
        }
        int itemId = menuItem.getItemId();
        if (itemId == R.id.menu_history) {
            PerformanceReport.recordClick(UiAction$Type.OPEN_CALL_HISTORY);
            startActivity(new Intent(this, CallLogActivity.class));
            return false;
        } else if (itemId == R.id.menu_clear_frequents) {
            new ClearFrequentsDialog().show(getFragmentManager(), "clearFrequents");
            ((LoggingBindingsStub) Logger.get(this)).logScreenView(ScreenEvent$Type.CLEAR_FREQUENTS, this);
            return true;
        } else if (itemId != R.id.menu_call_settings) {
            return false;
        } else {
            handleMenuSettings();
            ((LoggingBindingsStub) Logger.get(this)).logScreenView(ScreenEvent$Type.SETTINGS, this);
            return true;
        }
    }

    public void onNewIntent(Intent intent) {
        LogUtil.enterBlock("DialtactsActivity.onNewIntent");
        setIntent(intent);
        this.firstLaunch = true;
        this.stateSaved = false;
        displayFragment(intent);
        invalidateOptionsMenu();
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(this).getConfigProvider()).getBoolean("enable_new_favorites_tab", false)) {
            int currentTabIndex = this.listsFragment.getCurrentTabIndex();
            boolean isRtl = CallUtil.isRtl();
            if (!isRtl && currentTabIndex == 0 && !this.isLandscape) {
                this.floatingActionButtonController.onPageScrolled(f);
            } else if (isRtl && currentTabIndex == 1 && !this.isLandscape) {
                this.floatingActionButtonController.onPageScrolled(1.0f - f);
            } else if (currentTabIndex != 0) {
                this.floatingActionButtonController.onPageScrolled(1.0f);
            }
        }
    }

    public void onPageSelected(int i) {
        if (this.previouslySelectedTabIndex == 1) {
            this.listsFragment.markMissedCallsAsReadAndRemoveNotifications();
        }
        int currentTabIndex = this.listsFragment.getCurrentTabIndex();
        if (currentTabIndex != this.previouslySelectedTabIndex) {
            this.floatingActionButtonController.scaleIn();
        }
        LogUtil.m9i("DialtactsActivity.onPageSelected", "tabIndex: %d", Integer.valueOf(currentTabIndex));
        this.previouslySelectedTabIndex = currentTabIndex;
        this.timeTabSelected = SystemClock.elapsedRealtime();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.clearSearchOnPause) {
            if (this.isDialpadShown) {
                hideDialpadFragment(false, true);
            }
            exitSearchUi();
            this.clearSearchOnPause = false;
        }
        if (this.slideOut.hasStarted() && !this.slideOut.hasEnded()) {
            commitDialpadFragmentHide();
        }
        super.onPause();
    }

    public void onPickDataUri(Uri uri, boolean z, CallSpecificAppData callSpecificAppData) {
        this.clearSearchOnPause = true;
        PhoneNumberInteraction.startInteractionForPhoneCall(this, uri, z, callSpecificAppData);
    }

    public void onPickPhoneNumber(String str, boolean z, CallSpecificAppData callSpecificAppData) {
        if (str == null) {
            str = "";
        }
        PreCall.start(this, new CallIntentBuilder(str, callSpecificAppData).setIsVideoCall(z).setAllowAssistedDial(callSpecificAppData.getAllowAssistedDialing()));
        this.clearSearchOnPause = true;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        throw new AssertionError(String.format(Locale.US, "Permissions requested unexpectedly: %d/%s/%s", new Object[]{Integer.valueOf(i), Arrays.toString(strArr), Arrays.toString(iArr)}));
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        this.isRestarting = true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        boolean z;
        int intExtra;
        DialpadFragment dialpadFragment2;
        LogUtil.enterBlock("DialtactsActivity.onResume");
        Trace.beginSection("DialtactsActivity onResume");
        super.onResume();
        PostCall.restartPerformanceRecordingIfARecentCallExist(this);
        if (!PerformanceReport.isRecording()) {
            PerformanceReport.startRecording();
        }
        this.stateSaved = false;
        if (this.firstLaunch) {
            LogUtil.m9i("DialtactsActivity.onResume", "mFirstLaunch true, displaying fragment", new Object[0]);
            displayFragment(getIntent());
        } else if (!TelecomUtil.isInManagedCall(this) && this.inCallDialpadUp) {
            LogUtil.m9i("DialtactsActivity.onResume", "phone not in use, hiding dialpad fragment", new Object[0]);
            hideDialpadFragment(false, true);
            this.inCallDialpadUp = false;
        } else if (this.isDialpadShown) {
            LogUtil.m9i("DialtactsActivity.onResume", "showing dialpad on resume", new Object[0]);
            showDialpadFragment(false);
        } else {
            PostCall.promptUserForMessageIfNecessary(this, this.parentLayout);
        }
        if (!this.isDialpadShown && (dialpadFragment2 = this.dialpadFragment) != null && !dialpadFragment2.isHidden()) {
            LogUtil.m9i("DialtactsActivity.onResume", "mDialpadFragment attached but not hidden, forcing hide", new Object[0]);
            getFragmentManager().beginTransaction().hide(this.dialpadFragment).commit();
        }
        if (!TextUtils.isEmpty(this.voiceSearchQuery)) {
            this.actionBarController.onSearchBoxTapped();
            this.searchView.setText(this.voiceSearchQuery);
            this.voiceSearchQuery = null;
        }
        if (this.isRestarting) {
            if (this.isDialpadShown) {
                ((LoggingBindingsStub) Logger.get(this)).logScreenView(ScreenEvent$Type.DIALPAD, this);
            }
            this.isRestarting = false;
        }
        SearchEditTextLayout searchEditTextLayout2 = this.searchEditTextLayout;
        if (voiceSearchEnabledForTest.isPresent()) {
            z = voiceSearchEnabledForTest.get().booleanValue();
        } else {
            List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(new Intent("android.speech.action.RECOGNIZE_SPEECH"), 65536);
            z = queryIntentActivities != null && queryIntentActivities.size() > 0;
        }
        searchEditTextLayout2.setVoiceSearchEnabled(z);
        this.voiceSearchButton.setOnClickListener(this);
        boolean z2 = !R$style.getLocale(this).getISO3Language().equals(this.savedLanguageCode);
        if (!this.wasConfigurationChange || z2) {
            this.dialerDatabaseHelper.startSmartDialUpdateThread(z2);
        }
        if (this.isDialpadShown) {
            this.floatingActionButtonController.scaleOut();
        } else {
            this.floatingActionButtonController.align(getFabAlignment(), false);
        }
        if (this.firstLaunch) {
            if ("vnd.android.cursor.dir/calls".equals(getIntent().getType())) {
                Bundle extras = getIntent().getExtras();
                if (extras == null || extras.getInt("android.provider.extra.CALL_TYPE_FILTER") != 4) {
                    this.listsFragment.showTab(1);
                } else {
                    this.listsFragment.showTab(3);
                    ((LoggingBindingsStub) Logger.get(this)).logImpression(DialerImpression$Type.VVM_NOTIFICATION_CLICKED);
                }
            } else if (getIntent().hasExtra(EXTRA_SHOW_TAB) && (intExtra = getIntent().getIntExtra(EXTRA_SHOW_TAB, 0)) < this.listsFragment.getTabCount()) {
                hideDialpadFragment(false, false);
                exitSearchUi();
                this.listsFragment.showTab(intExtra);
            }
            if (getIntent().getBooleanExtra("EXTRA_CLEAR_NEW_VOICEMAILS", false)) {
                LogUtil.m9i("DialtactsActivity.onResume", "clearing all new voicemails", new Object[0]);
                CallLogNotificationsService.markAllNewVoicemailsAsOld(this);
            }
            DialerExecutorModule.postDelayedOnUiThread(new Runnable() {
                public final void run() {
                    DialtactsActivity.this.lambda$onResume$2$DialtactsActivity();
                }
            }, 1000);
        }
        this.firstLaunch = false;
        ((TextView) this.searchEditTextLayout.findViewById(R.id.search_box_start_search)).setHint(getSearchBoxHint());
        this.timeTabSelected = SystemClock.elapsedRealtime();
        Trace.endSection();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        LogUtil.enterBlock("DialtactsActivity.onSaveInstanceState");
        super.onSaveInstanceState(bundle);
        bundle.putString("search_query", this.searchQuery);
        bundle.putString("dialpad_query", this.dialpadQuery);
        bundle.putString("saved_language_code", R$style.getLocale(this).getISO3Language());
        bundle.putBoolean("in_regular_search_ui", this.inRegularSearch);
        bundle.putBoolean("in_dialpad_search_ui", this.inDialpadSearch);
        bundle.putBoolean("in_new_search_ui", this.inNewSearch);
        bundle.putBoolean("first_launch", this.firstLaunch);
        bundle.putBoolean("is_dialpad_shown", this.isDialpadShown);
        bundle.putBoolean("fab_visible", this.floatingActionButtonController.isVisible());
        bundle.putBoolean("was_configuration_change", isChangingConfigurations());
        this.actionBarController.saveInstanceState(bundle);
        this.stateSaved = true;
    }

    public void onSearchListTouch() {
        if (this.isDialpadShown) {
            PerformanceReport.recordClick(UiAction$Type.CLOSE_DIALPAD);
            hideDialpadFragment(true, false);
            if (TextUtils.isEmpty(this.dialpadQuery)) {
                exitSearchUi();
                return;
            }
            return;
        }
        R$style.hideKeyboardFrom(this, this.searchEditTextLayout);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        boolean z = false;
        boolean z2 = SystemClock.elapsedRealtime() - this.timeTabSelected >= HISTORY_TAB_SEEN_TIMEOUT;
        if (this.listsFragment.getCurrentTabIndex() == 1) {
            z = true;
        }
        if (z && z2 && !isChangingConfigurations() && !((KeyguardManager) getSystemService(KeyguardManager.class)).isKeyguardLocked()) {
            this.listsFragment.markMissedCallsAsReadAndRemoveNotifications();
        }
        StorageComponent.get(this).unencryptedSharedPrefs().edit().putInt("last_tab", this.listsFragment.getCurrentTabIndex()).apply();
    }

    public void requestingPermission() {
    }

    public void setActionBarHideOffset(int i) {
        ActionBar supportActionBar = getSupportActionBar();
        Assert.isNotNull(supportActionBar);
        supportActionBar.setHideOffset(i);
    }

    public void setDragDropController(DragDropController dragDropController2) {
        this.dragDropController = dragDropController2;
        this.listsFragment.getRemoveView().setDragDropController(dragDropController2);
    }

    public void setHasFrequents(boolean z) {
    }

    public boolean shouldShowDialpadChooser() {
        return true;
    }

    public void showAllContactsTab() {
        ListsFragment listsFragment2 = this.listsFragment;
        if (listsFragment2 != null) {
            listsFragment2.showTab(2);
        }
    }

    public void showDialpad() {
        showDialpadFragment(true);
    }
}
