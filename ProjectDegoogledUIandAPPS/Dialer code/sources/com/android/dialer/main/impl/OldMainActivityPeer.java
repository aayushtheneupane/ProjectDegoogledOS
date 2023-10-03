package com.android.dialer.main.impl;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.VoicemailContract;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.content.LocalBroadcastManager;
import android.support.p002v7.appcompat.R$style;
import android.support.p002v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.contacts.common.list.OnPhoneNumberPickerActionListener;
import com.android.dialer.R;
import com.android.dialer.animation.AnimUtils;
import com.android.dialer.app.DialtactsActivity;
import com.android.dialer.app.calllog.CallLogAdapter;
import com.android.dialer.app.calllog.CallLogFragment;
import com.android.dialer.app.calllog.CallLogNotificationsService;
import com.android.dialer.app.calllog.IntentProvider;
import com.android.dialer.app.calllog.VisualVoicemailCallLogFragment;
import com.android.dialer.app.list.DragDropController;
import com.android.dialer.app.list.OldSpeedDialFragment;
import com.android.dialer.app.list.OnDragDropListener;
import com.android.dialer.app.list.OnListFragmentScrolledListener;
import com.android.dialer.app.list.PhoneFavoriteSquareTileView;
import com.android.dialer.app.list.RemoveView;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.callintent.CallSpecificAppData;
import com.android.dialer.calllog.CallLogComponent;
import com.android.dialer.calllog.config.CallLogConfigComponent;
import com.android.dialer.calllog.config.CallLogConfigImpl;
import com.android.dialer.calllog.p004ui.NewCallLogFragment;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultFutureCallback;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.DialerExecutorModule;
import com.android.dialer.common.concurrent.UiListener;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.contactsfragment.ContactsFragment;
import com.android.dialer.database.CallLogQueryHandler;
import com.android.dialer.database.Database;
import com.android.dialer.dialpadview.DialpadFragment;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.interactions.PhoneNumberInteraction;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.ScreenEvent$Type;
import com.android.dialer.main.MainActivityPeer;
import com.android.dialer.main.impl.OldMainActivityPeer;
import com.android.dialer.main.impl.bottomnav.BottomNavBar;
import com.android.dialer.main.impl.bottomnav.MissedCallCountObserver;
import com.android.dialer.main.impl.toolbar.MainToolbar;
import com.android.dialer.metrics.MetricsComponent;
import com.android.dialer.metrics.StubMetrics;
import com.android.dialer.postcall.PostCall;
import com.android.dialer.precall.PreCall;
import com.android.dialer.promotion.Promotion;
import com.android.dialer.searchfragment.list.NewSearchFragment;
import com.android.dialer.speeddial.SpeedDialFragment;
import com.android.dialer.storage.StorageComponent;
import com.android.dialer.telecom.TelecomUtil;
import com.android.dialer.util.DialerUtils;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.util.TransactionSafeActivity;
import com.android.dialer.voicemail.listui.NewVoicemailFragment;
import com.android.dialer.voicemail.listui.error.OmtpVoicemailMessageCreator;
import com.android.dialer.voicemail.listui.error.VoicemailStatusCorruptionHandler$Source;
import com.android.dialer.voicemailstatus.VoicemailStatusHelper;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class OldMainActivityPeer implements MainActivityPeer, FragmentUtils.FragmentUtilListener {
    private final TransactionSafeActivity activity;
    private BottomNavBar bottomNav;
    /* access modifiers changed from: private */
    public MainBottomNavBarBottomNavTabListener bottomNavTabListener;
    private View bottomSheet;
    private MainCallLogAdapterOnActionModeStateChangedListener callLogAdapterOnActionModeStateChangedListener;
    private MainCallLogFragmentListener callLogFragmentListener;
    private MainCallLogHost callLogHostInterface;
    private MainDialpadFragmentHost dialpadFragmentHostInterface;
    private MainDialpadListener dialpadListener;
    private final BroadcastReceiver disableCallLogFrameworkReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (OldMainActivityPeer.this.bottomNavTabListener != null) {
                OldMainActivityPeer.this.bottomNavTabListener.disableNewCallLogFragment();
                OldMainActivityPeer.this.bottomNavTabListener.disableNewVoicemailFragment();
            }
        }
    };
    private UiListener<String> getLastOutgoingCallListener;
    private LastTabController lastTabController;
    private MissedCallCountObserver missedCallCountObserver;
    private UiListener<Integer> missedCallObserverUiListener;
    private MainOldSpeedDialFragmentHost oldSpeedDialFragmentHost;
    private MainOnContactSelectedListener onContactSelectedListener;
    private MainOnDialpadQueryChangedListener onDialpadQueryChangedListener;
    private MainOnListFragmentScrolledListener onListFragmentScrolledListener;
    private MainOnPhoneNumberPickerActionListener onPhoneNumberPickerActionListener;
    private String savedLanguageCode;
    private MainSearchController searchController;
    private MainSearchFragmentListener searchFragmentListener;
    private View snackbarContainer;
    private MainSpeedDialFragmentHost speedDialFragmentHost;

    private static final class LastTabController {
        private final BottomNavBar bottomNavBar;
        private final boolean canShowVoicemailTab;
        private final Context context;

        LastTabController(Context context2, BottomNavBar bottomNavBar2, boolean z) {
            this.context = context2;
            this.bottomNavBar = bottomNavBar2;
            this.canShowVoicemailTab = z;
        }

        /* access modifiers changed from: package-private */
        public int getLastTab() {
            int i = StorageComponent.get(this.context).unencryptedSharedPrefs().getInt("last_tab", 0);
            if (i != 3 || this.canShowVoicemailTab) {
                return i;
            }
            return 0;
        }

        /* access modifiers changed from: package-private */
        public void onActivityStop() {
            StorageComponent.get(this.context).unencryptedSharedPrefs().edit().putInt("last_tab", this.bottomNavBar.getSelectedTab()).apply();
        }
    }

    private static final class MainBottomNavBarBottomNavTabListener implements BottomNavBar.OnBottomNavTabSelectedListener {
        private final TransactionSafeActivity activity;
        private final View bottomSheet;
        private final FloatingActionButton fab;
        private final FragmentManager fragmentManager;
        private int selectedTab = -1;
        private final android.support.p000v4.app.FragmentManager supportFragmentManager;

        /* synthetic */ MainBottomNavBarBottomNavTabListener(TransactionSafeActivity transactionSafeActivity, FragmentManager fragmentManager2, android.support.p000v4.app.FragmentManager fragmentManager3, FloatingActionButton floatingActionButton, View view, C04991 r6) {
            this.activity = transactionSafeActivity;
            this.fragmentManager = fragmentManager2;
            this.supportFragmentManager = fragmentManager3;
            this.fab = floatingActionButton;
            this.bottomSheet = view;
        }

        static /* synthetic */ void lambda$showPromotionBottomSheet$0(Promotion promotion, BottomSheetBehavior bottomSheetBehavior, View view) {
            promotion.dismiss();
            bottomSheetBehavior.setState(5);
        }

        private void showFragment(Fragment fragment, android.support.p000v4.app.Fragment fragment2, String str) {
            LogUtil.enterBlock("MainBottomNavBarBottomNavTabListener.showFragment");
            Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag("speed_dial");
            Fragment findFragmentByTag2 = this.fragmentManager.findFragmentByTag("call_log");
            Fragment findFragmentByTag3 = this.fragmentManager.findFragmentByTag("contacts");
            Fragment findFragmentByTag4 = this.fragmentManager.findFragmentByTag("voicemail");
            FragmentTransaction beginTransaction = this.fragmentManager.beginTransaction();
            if ((!(showIfEqualElseHide(beginTransaction, fragment, findFragmentByTag) | showIfEqualElseHide(beginTransaction, fragment, findFragmentByTag2) | showIfEqualElseHide(beginTransaction, fragment, findFragmentByTag3)) && !showIfEqualElseHide(beginTransaction, fragment, findFragmentByTag4)) && fragment != null) {
                LogUtil.m9i("MainBottomNavBarBottomNavTabListener.showFragment", GeneratedOutlineSupport.outline6("Not added yet: ", fragment), new Object[0]);
                beginTransaction.add(R.id.fragment_container, fragment, str);
            }
            if (this.activity.isSafeToCommitTransactions()) {
                beginTransaction.commit();
            }
            android.support.p000v4.app.Fragment findFragmentByTag5 = this.supportFragmentManager.findFragmentByTag("speed_dial");
            android.support.p000v4.app.Fragment findFragmentByTag6 = this.supportFragmentManager.findFragmentByTag("call_log");
            android.support.p000v4.app.Fragment findFragmentByTag7 = this.supportFragmentManager.findFragmentByTag("voicemail");
            android.support.p000v4.app.FragmentTransaction beginTransaction2 = this.supportFragmentManager.beginTransaction();
            if ((!(showIfEqualElseHideSupport(beginTransaction2, fragment2, findFragmentByTag5) | showIfEqualElseHideSupport(beginTransaction2, fragment2, findFragmentByTag6)) && !showIfEqualElseHideSupport(beginTransaction2, fragment2, findFragmentByTag7)) && fragment2 != null) {
                LogUtil.m9i("MainBottomNavBarBottomNavTabListener.showFragment", GeneratedOutlineSupport.outline6("Not added yet: ", fragment2), new Object[0]);
                beginTransaction2.add(R.id.fragment_container, fragment2, str);
            }
            if (this.activity.isSafeToCommitTransactions()) {
                beginTransaction2.commit();
            }
        }

        private boolean showIfEqualElseHide(FragmentTransaction fragmentTransaction, Fragment fragment, Fragment fragment2) {
            if (fragment != null && fragment.equals(fragment2)) {
                fragmentTransaction.show(fragment);
                return true;
            } else if (fragment2 == null) {
                return false;
            } else {
                if (fragment2 instanceof VisualVoicemailCallLogFragment) {
                    fragment2.setUserVisibleHint(false);
                    ((VisualVoicemailCallLogFragment) fragment2).onNotVisible();
                }
                fragmentTransaction.hide(fragment2);
                return false;
            }
        }

        private boolean showIfEqualElseHideSupport(android.support.p000v4.app.FragmentTransaction fragmentTransaction, android.support.p000v4.app.Fragment fragment, android.support.p000v4.app.Fragment fragment2) {
            if (fragment == null || !fragment.equals(fragment2)) {
                if (fragment2 != null) {
                    fragmentTransaction.hide(fragment2);
                }
                return false;
            }
            fragmentTransaction.show(fragment);
            return true;
        }

        /* access modifiers changed from: package-private */
        public void disableNewCallLogFragment() {
            LogUtil.m9i("MainBottomNavBarBottomNavTabListener.disableNewCallLogFragment", "disabled", new Object[0]);
            android.support.p000v4.app.Fragment findFragmentByTag = this.supportFragmentManager.findFragmentByTag("call_log");
            if (findFragmentByTag != null) {
                android.support.p000v4.app.FragmentTransaction beginTransaction = this.supportFragmentManager.beginTransaction();
                beginTransaction.remove(findFragmentByTag);
                beginTransaction.commitAllowingStateLoss();
                if (this.selectedTab == 1) {
                    LogUtil.m9i("MainBottomNavBarBottomNavTabListener.disableNewCallLogFragment", "showing old", new Object[0]);
                    Fragment findFragmentByTag2 = this.fragmentManager.findFragmentByTag("call_log");
                    if (findFragmentByTag2 == null) {
                        findFragmentByTag2 = new CallLogFragment(-1, -1);
                    }
                    showFragment(findFragmentByTag2, (android.support.p000v4.app.Fragment) null, "call_log");
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void disableNewVoicemailFragment() {
            LogUtil.m9i("MainBottomNavBarBottomNavTabListener.disableNewVoicemailFragment", "disabled", new Object[0]);
            android.support.p000v4.app.Fragment findFragmentByTag = this.supportFragmentManager.findFragmentByTag("voicemail");
            if (findFragmentByTag != null) {
                android.support.p000v4.app.FragmentTransaction beginTransaction = this.supportFragmentManager.beginTransaction();
                beginTransaction.remove(findFragmentByTag);
                beginTransaction.commitAllowingStateLoss();
                if (this.selectedTab == 3) {
                    LogUtil.m9i("MainBottomNavBarBottomNavTabListener.disableNewVoicemailFragment", "showing old", new Object[0]);
                    Fragment findFragmentByTag2 = this.fragmentManager.findFragmentByTag("voicemail");
                    if (findFragmentByTag2 == null) {
                        findFragmentByTag2 = new VisualVoicemailCallLogFragment();
                    }
                    showFragment(findFragmentByTag2, (android.support.p000v4.app.Fragment) null, "voicemail");
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void ensureCorrectCallLogShown() {
            if (this.supportFragmentManager.findFragmentByTag("call_log") != null && !((CallLogConfigImpl) CallLogConfigComponent.get(this.activity).callLogConfig()).isNewCallLogFragmentEnabled()) {
                LogUtil.m9i("MainBottomNavBarBottomNavTabListener.ensureCorrectCallLogShown", "disabling", new Object[0]);
                disableNewCallLogFragment();
            }
        }

        /* access modifiers changed from: package-private */
        public void ensureCorrectVoicemailShown() {
            if (this.supportFragmentManager.findFragmentByTag("voicemail") != null && !((CallLogConfigImpl) CallLogConfigComponent.get(this.activity).callLogConfig()).isNewVoicemailFragmentEnabled()) {
                LogUtil.m9i("MainBottomNavBarBottomNavTabListener.ensureCorrectVoicemailShown", "disabling", new Object[0]);
                disableNewVoicemailFragment();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean newCallLogFragmentActive() {
            return this.supportFragmentManager.findFragmentByTag("call_log") != null || (this.fragmentManager.findFragmentByTag("call_log") == null && ((CallLogConfigImpl) CallLogConfigComponent.get(this.activity).callLogConfig()).isNewCallLogFragmentEnabled());
        }

        public void onCallLogSelected() {
            LogUtil.enterBlock("MainBottomNavBarBottomNavTabListener.onCallLogSelected");
            if (this.selectedTab != 1) {
                ((LoggingBindingsStub) Logger.get(this.activity)).logScreenView(ScreenEvent$Type.MAIN_CALL_LOG, this.activity);
                this.selectedTab = 1;
                if (((CallLogConfigImpl) CallLogConfigComponent.get(this.activity).callLogConfig()).isNewCallLogFragmentEnabled()) {
                    android.support.p000v4.app.Fragment findFragmentByTag = this.supportFragmentManager.findFragmentByTag("call_log");
                    if (findFragmentByTag == null) {
                        findFragmentByTag = new NewCallLogFragment();
                    }
                    showFragment((Fragment) null, findFragmentByTag, "call_log");
                } else {
                    Fragment findFragmentByTag2 = this.fragmentManager.findFragmentByTag("call_log");
                    if (findFragmentByTag2 == null) {
                        findFragmentByTag2 = new CallLogFragment(-1, -1);
                    }
                    showFragment(findFragmentByTag2, (android.support.p000v4.app.Fragment) null, "call_log");
                }
                this.fab.show();
                TransactionSafeActivity transactionSafeActivity = this.activity;
                View view = this.bottomSheet;
                BottomSheetBehavior from = BottomSheetBehavior.from(view);
                Optional<Promotion> highestPriorityPromotion = ((DaggerAospDialerRootComponent) ((HasRootComponent) transactionSafeActivity.getApplicationContext()).component()).promotionComponent().promotionManager().getHighestPriorityPromotion(2);
                if (!highestPriorityPromotion.isPresent()) {
                    from.setState(5);
                    return;
                }
                Promotion promotion = highestPriorityPromotion.get();
                ((ImageView) view.findViewById(R.id.promotion_icon)).setImageResource(promotion.getIconRes());
                TextView textView = (TextView) view.findViewById(R.id.promotion_details);
                textView.setText(promotion.getDetails());
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                ((TextView) view.findViewById(R.id.promotion_title)).setText(promotion.getTitle());
                view.findViewById(R.id.ok_got_it).setOnClickListener(new View.OnClickListener(from) {
                    private final /* synthetic */ BottomSheetBehavior f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        OldMainActivityPeer.MainBottomNavBarBottomNavTabListener.lambda$showPromotionBottomSheet$0(Promotion.this, this.f$1, view);
                    }
                });
                view.setVisibility(0);
                from.setState(3);
            }
        }

        public void onContactsSelected() {
            LogUtil.enterBlock("MainBottomNavBarBottomNavTabListener.onContactsSelected");
            if (this.selectedTab != 2) {
                ((LoggingBindingsStub) Logger.get(this.activity)).logScreenView(ScreenEvent$Type.MAIN_CONTACTS, this.activity);
                this.selectedTab = 2;
                Fragment findFragmentByTag = this.fragmentManager.findFragmentByTag("contacts");
                if (findFragmentByTag == null) {
                    ContactsFragment contactsFragment = new ContactsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("extra_header", 1);
                    contactsFragment.setArguments(bundle);
                    findFragmentByTag = contactsFragment;
                }
                showFragment(findFragmentByTag, (android.support.p000v4.app.Fragment) null, "contacts");
                this.fab.show();
            }
        }

        public void onSpeedDialSelected() {
            LogUtil.enterBlock("MainBottomNavBarBottomNavTabListener.onSpeedDialSelected");
            if (this.selectedTab != 0) {
                ((LoggingBindingsStub) Logger.get(this.activity)).logScreenView(ScreenEvent$Type.MAIN_SPEED_DIAL, this.activity);
                this.selectedTab = 0;
                if (((SharedPrefConfigProvider) ConfigProviderComponent.get(this.activity).getConfigProvider()).getBoolean("enable_new_favorites_tab", false)) {
                    android.support.p000v4.app.Fragment findFragmentByTag = this.supportFragmentManager.findFragmentByTag("speed_dial");
                    if (findFragmentByTag == null) {
                        findFragmentByTag = new SpeedDialFragment();
                    }
                    showFragment((Fragment) null, findFragmentByTag, "speed_dial");
                } else {
                    Fragment findFragmentByTag2 = this.fragmentManager.findFragmentByTag("speed_dial");
                    if (findFragmentByTag2 == null) {
                        findFragmentByTag2 = new OldSpeedDialFragment();
                    }
                    showFragment(findFragmentByTag2, (android.support.p000v4.app.Fragment) null, "speed_dial");
                }
                this.fab.show();
            }
        }

        public void onVoicemailSelected() {
            LogUtil.enterBlock("MainBottomNavBarBottomNavTabListener.onVoicemailSelected");
            if (this.selectedTab != 3) {
                ((LoggingBindingsStub) Logger.get(this.activity)).logScreenView(ScreenEvent$Type.MAIN_VOICEMAIL, this.activity);
                this.selectedTab = 3;
                if (((CallLogConfigImpl) CallLogConfigComponent.get(this.activity).callLogConfig()).isNewVoicemailFragmentEnabled()) {
                    android.support.p000v4.app.Fragment findFragmentByTag = this.supportFragmentManager.findFragmentByTag("voicemail");
                    if (findFragmentByTag == null) {
                        findFragmentByTag = new NewVoicemailFragment();
                    }
                    showFragment((Fragment) null, findFragmentByTag, "voicemail");
                    return;
                }
                VisualVoicemailCallLogFragment visualVoicemailCallLogFragment = (VisualVoicemailCallLogFragment) this.fragmentManager.findFragmentByTag("voicemail");
                if (visualVoicemailCallLogFragment == null) {
                    visualVoicemailCallLogFragment = new VisualVoicemailCallLogFragment();
                }
                showFragment(visualVoicemailCallLogFragment, (android.support.p000v4.app.Fragment) null, "voicemail");
                visualVoicemailCallLogFragment.setUserVisibleHint(true);
                visualVoicemailCallLogFragment.onVisible();
            }
        }
    }

    private static final class MainCallLogAdapterOnActionModeStateChangedListener implements CallLogAdapter.OnActionModeStateChangedListener {
        /* access modifiers changed from: private */
        public ActionMode actionMode;
        /* access modifiers changed from: private */
        public boolean isEnabled;

        /* synthetic */ MainCallLogAdapterOnActionModeStateChangedListener(C04991 r1) {
        }

        public boolean isActionModeStateEnabled() {
            return this.isEnabled;
        }

        public void onActionModeStateChanged(ActionMode actionMode2, boolean z) {
            this.actionMode = actionMode2;
            this.isEnabled = z;
        }
    }

    private static final class MainCallLogFragmentListener implements CallLogFragment.CallLogFragmentListener, CallLogQueryHandler.Listener, BottomNavBar.OnBottomNavTabSelectedListener {
        private boolean activityIsAlive;
        private final BottomNavBar bottomNavBar;
        private final MainBottomNavBarBottomNavTabListener bottomNavTabListener;
        /* access modifiers changed from: private */
        public final CallLogQueryHandler callLogQueryHandler;
        private final Context context;
        private int currentTab = 0;
        private long timeSelected = -1;
        private final Toolbar toolbar;
        private final ContentObserver voicemailStatusObserver = new ContentObserver(new Handler()) {
            public void onChange(boolean z) {
                LogUtil.m9i("MainCallLogFragmentListener", "voicemailStatusObserver.onChange selfChange:%b", Boolean.valueOf(z));
                super.onChange(z);
                MainCallLogFragmentListener.this.callLogQueryHandler.fetchVoicemailStatus();
            }
        };

        MainCallLogFragmentListener(Context context2, ContentResolver contentResolver, BottomNavBar bottomNavBar2, Toolbar toolbar2, MainBottomNavBarBottomNavTabListener mainBottomNavBarBottomNavTabListener) {
            this.callLogQueryHandler = new CallLogQueryHandler(context2, contentResolver, this, -1);
            this.context = context2;
            this.bottomNavBar = bottomNavBar2;
            this.toolbar = toolbar2;
            this.bottomNavTabListener = mainBottomNavBarBottomNavTabListener;
        }

        private void markMissedCallsAsReadAndRemoveNotification() {
            if (this.bottomNavTabListener.newCallLogFragmentActive()) {
                Futures.addCallback(CallLogComponent.get(this.context).getClearMissedCalls().clearAll(), new DefaultFutureCallback(), MoreExecutors.directExecutor());
                return;
            }
            this.callLogQueryHandler.markMissedCallsAsRead();
            CallLogNotificationsService.cancelAllMissedCalls(this.context);
        }

        private void setCurrentTab(int i) {
            if (this.currentTab == 1 && i != 1) {
                markMissedCallsAsReadAndRemoveNotification();
            }
            this.currentTab = i;
            this.timeSelected = System.currentTimeMillis();
        }

        public void onActivityResume() {
            LogUtil.enterBlock("MainCallLogFragmentListener.onActivityResume");
            this.activityIsAlive = true;
            Context context2 = this.context;
            LogUtil.enterBlock("MainCallLogFragmentListener.registerVoicemailStatusContentObserver");
            if (!PermissionsUtil.hasReadVoicemailPermissions(context2) || !PermissionsUtil.hasAddVoicemailPermissions(context2)) {
                LogUtil.m10w("MainCallLogFragmentListener.registerVoicemailStatusContentObserver", "no voicemail read/add permissions", new Object[0]);
            } else {
                LogUtil.m9i("MainCallLogFragmentListener.registerVoicemailStatusContentObserver", "register", new Object[0]);
                context2.getContentResolver().registerContentObserver(VoicemailContract.Status.CONTENT_URI, true, this.voicemailStatusObserver);
            }
            this.callLogQueryHandler.fetchVoicemailStatus();
            if (!this.bottomNavTabListener.newCallLogFragmentActive()) {
                this.callLogQueryHandler.fetchMissedCallsUnreadCount();
            }
            setCurrentTab(this.bottomNavBar.getSelectedTab());
        }

        public void onActivityStop(boolean z, boolean z2) {
            this.context.getContentResolver().unregisterContentObserver(this.voicemailStatusObserver);
            boolean z3 = false;
            this.activityIsAlive = false;
            if (!this.bottomNavTabListener.newCallLogFragmentActive()) {
                if (this.currentTab == 1 && this.timeSelected != -1 && System.currentTimeMillis() - this.timeSelected > TimeUnit.SECONDS.toMillis(3)) {
                    z3 = true;
                }
                if (z3 && !z && !z2) {
                    markMissedCallsAsReadAndRemoveNotification();
                }
            }
        }

        public void onCallLogSelected() {
            setCurrentTab(1);
        }

        public boolean onCallsFetched(Cursor cursor) {
            return false;
        }

        public void onContactsSelected() {
            setCurrentTab(2);
        }

        public void onMissedCallsUnreadCountFetched(Cursor cursor) {
            if (this.activityIsAlive) {
                this.bottomNavBar.setNotificationCount(1, cursor.getCount());
            }
            cursor.close();
        }

        public void onSpeedDialSelected() {
            setCurrentTab(0);
        }

        public void onVoicemailSelected() {
            setCurrentTab(3);
        }

        public void onVoicemailStatusFetched(Cursor cursor) {
            LogUtil.m9i("OldMainActivityPeer.MainCallLogFragmentListener", "onVoicemailStatusFetched", new Object[0]);
            OmtpVoicemailMessageCreator.maybeFixVoicemailStatus(this.context, cursor, VoicemailStatusCorruptionHandler$Source.Activity);
            int numberActivityVoicemailSources = VoicemailStatusHelper.getNumberActivityVoicemailSources(cursor);
            boolean z = numberActivityVoicemailSources > 0;
            LogUtil.m9i("OldMainActivityPeer.onVoicemailStatusFetched", String.format(Locale.US, "hasActiveVoicemailProvider:%b, number of active voicemail sources:%d", new Object[]{Boolean.valueOf(z), Integer.valueOf(numberActivityVoicemailSources)}), new Object[0]);
            if (z) {
                ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.MAIN_VVM_TAB_VISIBLE);
                this.bottomNavBar.showVoicemail(true);
                this.callLogQueryHandler.fetchVoicemailUnreadCount();
            } else {
                this.bottomNavBar.showVoicemail(false);
            }
            StorageComponent.get(this.context).unencryptedSharedPrefs().edit().putBoolean("has_active_voicemail_provider", z).apply();
        }

        public void onVoicemailUnreadCountFetched(Cursor cursor) {
            if (this.activityIsAlive) {
                this.bottomNavBar.setNotificationCount(3, cursor.getCount());
            }
            cursor.close();
        }

        public void showMultiSelectRemoveView(boolean z) {
            int i = 8;
            this.bottomNavBar.setVisibility(z ? 8 : 0);
            Toolbar toolbar2 = this.toolbar;
            if (!z) {
                i = 0;
            }
            toolbar2.setVisibility(i);
        }

        public void updateTabUnreadCounts() {
            this.callLogQueryHandler.fetchMissedCallsUnreadCount();
            this.callLogQueryHandler.fetchVoicemailUnreadCount();
        }
    }

    private static final class MainCallLogHost implements CallLogFragment.HostInterface {
        private final FloatingActionButton fab;
        private final MainSearchController searchController;

        MainCallLogHost(MainSearchController mainSearchController, FloatingActionButton floatingActionButton) {
            this.searchController = mainSearchController;
            this.fab = floatingActionButton;
        }

        public void enableFloatingButton(boolean z) {
            LogUtil.m9i("MainCallLogHost.enableFloatingButton", GeneratedOutlineSupport.outline10("enabled: ", z), new Object[0]);
            if (z) {
                this.fab.show();
            } else {
                this.fab.hide();
            }
        }

        public void showDialpad() {
            this.searchController.showDialpad(true);
        }
    }

    private static final class MainDialpadFragmentHost implements DialpadFragment.HostInterface {
        /* synthetic */ MainDialpadFragmentHost(C04991 r1) {
        }

        public boolean onDialpadSpacerTouchWithEmptyQuery() {
            return false;
        }

        public boolean shouldShowDialpadChooser() {
            return false;
        }
    }

    private static final class MainDialpadListener implements DialpadFragment.DialpadListener {
        private final Context context;
        private final UiListener<String> listener;
        private final MainSearchController searchController;

        MainDialpadListener(Context context2, MainSearchController mainSearchController, UiListener<String> uiListener) {
            this.context = context2;
            this.searchController = mainSearchController;
            this.listener = uiListener;
        }

        public void getLastOutgoingCall(DialpadFragment.LastOutgoingCallCallback lastOutgoingCallCallback) {
            ListenableFuture submit = DialerExecutorComponent.get(this.context).backgroundExecutor().submit(new Callable() {
                public final Object call() {
                    return OldMainActivityPeer.MainDialpadListener.this.mo6448x5320a3ac();
                }
            });
            UiListener<String> uiListener = this.listener;
            Context context2 = this.context;
            Objects.requireNonNull(lastOutgoingCallCallback);
            uiListener.listen(context2, submit, new DialerExecutor.SuccessListener() {
                public final void onSuccess(Object obj) {
                    DialpadFragment.LastOutgoingCallCallback.this.lastOutgoingCall((String) obj);
                }
            }, C0494x3e6cabcf.INSTANCE);
        }

        /* renamed from: lambda$getLastOutgoingCall$0$OldMainActivityPeer$MainDialpadListener */
        public /* synthetic */ String mo6448x5320a3ac() throws Exception {
            return CallLog.Calls.getLastOutgoingCall(this.context);
        }

        public void onCallPlacedFromDialpad() {
            this.searchController.onCallPlacedFromSearch();
        }

        public void onDialpadShown() {
            this.searchController.onDialpadShown();
        }
    }

    private static final class MainOldSpeedDialFragmentHost implements OldSpeedDialFragment.HostInterface, OnDragDropListener {
        private final BottomNavBar bottomNavBar;
        private final Context context;
        private final ImageView dragShadowOverlay;
        private final RemoveView removeView;
        private final View removeViewContent;
        private final View rootLayout;
        private final View searchViewContainer;
        private final MainToolbar toolbar;

        MainOldSpeedDialFragmentHost(Context context2, View view, BottomNavBar bottomNavBar2, ImageView imageView, RemoveView removeView2, View view2, MainToolbar mainToolbar) {
            this.context = context2;
            this.rootLayout = view;
            this.bottomNavBar = bottomNavBar2;
            this.dragShadowOverlay = imageView;
            this.removeView = removeView2;
            this.searchViewContainer = view2;
            this.toolbar = mainToolbar;
            this.removeViewContent = removeView2.findViewById(R.id.remove_view_content);
        }

        static /* synthetic */ boolean lambda$setDragDropController$0(DragDropController dragDropController, View view, DragEvent dragEvent) {
            if (dragEvent.getAction() != 2) {
                return true;
            }
            dragDropController.handleDragHovered(view, (int) dragEvent.getX(), (int) dragEvent.getY());
            return true;
        }

        public ImageView getDragShadowOverlay() {
            return this.dragShadowOverlay;
        }

        public void onDragFinished(int i, int i2) {
            AnimUtils.crossFadeViews(this.searchViewContainer, this.removeViewContent, 300);
        }

        public void onDragHovered(int i, int i2, PhoneFavoriteSquareTileView phoneFavoriteSquareTileView) {
        }

        public void onDragStarted(int i, int i2, PhoneFavoriteSquareTileView phoneFavoriteSquareTileView) {
            AnimUtils.crossFadeViews(this.removeViewContent, this.searchViewContainer, 300);
        }

        public void onDroppedOnRemove() {
        }

        public void setDragDropController(DragDropController dragDropController) {
            this.removeView.setDragDropController(dragDropController);
            this.rootLayout.setOnDragListener(new View.OnDragListener() {
                public final boolean onDrag(View view, DragEvent dragEvent) {
                    OldMainActivityPeer.MainOldSpeedDialFragmentHost.lambda$setDragDropController$0(DragDropController.this, view, dragEvent);
                    return true;
                }
            });
        }

        public void setHasFrequents(boolean z) {
            this.toolbar.showClearFrequents(z);
        }

        public void showAllContactsTab() {
            this.bottomNavBar.selectTab(2);
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.MAIN_OPEN_WITH_TAB_CONTACTS);
        }
    }

    private static final class MainOnContactSelectedListener implements ContactsFragment.OnContactSelectedListener {
        private final Context context;

        MainOnContactSelectedListener(Context context2) {
            this.context = context2;
        }

        public void onContactSelected(ImageView imageView, Uri uri, long j) {
            ContactsContract.QuickContact.showQuickContact(this.context, imageView, uri, 3, (String[]) null);
        }
    }

    protected static class MainOnDialpadQueryChangedListener implements DialpadFragment.OnDialpadQueryChangedListener {
        private final MainSearchController searchController;

        protected MainOnDialpadQueryChangedListener(MainSearchController mainSearchController) {
            this.searchController = mainSearchController;
        }

        public void onDialpadQueryChanged(String str) {
            this.searchController.onDialpadQueryChanged(str);
        }
    }

    private static final class MainOnListFragmentScrolledListener implements OnListFragmentScrolledListener {
        private final View parentLayout;

        MainOnListFragmentScrolledListener(View view) {
            this.parentLayout = view;
        }

        public void onListFragmentScroll(int i, int i2, int i3) {
        }

        public void onListFragmentScrollStateChange(int i) {
            DialerUtils.hideInputMethod(this.parentLayout);
        }
    }

    private static final class MainOnPhoneNumberPickerActionListener implements OnPhoneNumberPickerActionListener {
        private final TransactionSafeActivity activity;

        MainOnPhoneNumberPickerActionListener(TransactionSafeActivity transactionSafeActivity) {
            this.activity = transactionSafeActivity;
        }

        public void onPickDataUri(Uri uri, boolean z, CallSpecificAppData callSpecificAppData) {
            PhoneNumberInteraction.startInteractionForPhoneCall(this.activity, uri, z, callSpecificAppData);
        }

        public void onPickPhoneNumber(String str, boolean z, CallSpecificAppData callSpecificAppData) {
            if (str == null) {
                str = "";
            }
            PreCall.start(this.activity, new CallIntentBuilder(str, callSpecificAppData).setIsVideoCall(z).setAllowAssistedDial(callSpecificAppData.getAllowAssistedDialing()));
        }
    }

    private static final class MainSearchFragmentListener implements NewSearchFragment.SearchFragmentListener {
        private final MainSearchController searchController;

        MainSearchFragmentListener(MainSearchController mainSearchController) {
            this.searchController = mainSearchController;
        }

        public void onCallPlacedFromSearch() {
            this.searchController.onCallPlacedFromSearch();
        }

        public void onSearchListTouch() {
            this.searchController.onSearchListTouch();
        }

        public void requestingPermission() {
            this.searchController.requestingPermission();
        }
    }

    private static final class MainSpeedDialFragmentHost implements SpeedDialFragment.HostInterface {
        private final ViewGroup coordinatorLayout;
        private final ViewGroup fragmentContainer;
        private final ViewGroup rootLayout;
        private final MainToolbar toolbar;

        MainSpeedDialFragmentHost(MainToolbar mainToolbar, ViewGroup viewGroup, ViewGroup viewGroup2, ViewGroup viewGroup3) {
            this.toolbar = mainToolbar;
            this.rootLayout = viewGroup;
            this.coordinatorLayout = viewGroup2;
            this.fragmentContainer = viewGroup3;
        }

        public void dragFavorite(boolean z) {
            this.rootLayout.setClipChildren(!z);
            this.coordinatorLayout.setClipChildren(!z);
            this.fragmentContainer.setClipChildren(!z);
        }

        public void setHasFrequents(boolean z) {
            this.toolbar.showClearFrequents(z);
        }
    }

    public OldMainActivityPeer(TransactionSafeActivity transactionSafeActivity) {
        this.activity = transactionSafeActivity;
    }

    private void onHandleIntent(Intent intent) {
        int i;
        Uri data;
        boolean z = true;
        if ("vnd.android.cursor.dir/calls".equals(intent.getType())) {
            Bundle extras = intent.getExtras();
            if (extras == null || extras.getInt("android.provider.extra.CALL_TYPE_FILTER") != 4) {
                LogUtil.m9i("OldMainActivityPeer.onHandleIntent", "Call log content type intent", new Object[0]);
                i = 1;
            } else {
                LogUtil.m9i("OldMainActivityPeer.onHandleIntent", "Voicemail content type intent", new Object[0]);
                ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.VVM_NOTIFICATION_CLICKED);
                i = 3;
            }
        } else {
            if ("ACTION_SHOW_TAB".equals(intent.getAction()) && intent.hasExtra(DialtactsActivity.EXTRA_SHOW_TAB)) {
                LogUtil.m9i("OldMainActivityPeer.onHandleIntent", "Show tab intent", new Object[0]);
                i = intent.getIntExtra(DialtactsActivity.EXTRA_SHOW_TAB, -1);
            } else {
                LogUtil.m9i("OldMainActivityPeer.onHandleIntent", "Show last tab", new Object[0]);
                i = this.lastTabController.getLastTab();
            }
        }
        if (i == 0) {
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_OPEN_WITH_TAB_FAVORITE);
        } else if (i == 1) {
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_OPEN_WITH_TAB_CALL_LOG);
        } else if (i == 2) {
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_OPEN_WITH_TAB_CONTACTS);
        } else if (i == 3) {
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_OPEN_WITH_TAB_VOICEMAIL);
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid tab: ", i));
        }
        this.bottomNav.selectTab(i);
        if (!"android.intent.action.DIAL".equals(intent.getAction()) && (!"android.intent.action.VIEW".equals(intent.getAction()) || (data = intent.getData()) == null || !"tel".equals(data.getScheme()))) {
            z = DialpadFragment.isAddCallMode(intent);
        }
        if (z) {
            LogUtil.m9i("OldMainActivityPeer.onHandleIntent", "Dial or add call intent", new Object[0]);
            this.searchController.showDialpadFromNewIntent();
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_OPEN_WITH_DIALPAD);
        }
        if (intent.getBooleanExtra("EXTRA_CLEAR_NEW_VOICEMAILS", false)) {
            LogUtil.m9i("OldMainActivityPeer.onHandleIntent", "clearing all new voicemails", new Object[0]);
            CallLogNotificationsService.markAllNewVoicemailsAsOld(this.activity);
        }
    }

    public <T> T getImpl(Class<T> cls) {
        if (cls.isInstance(this.onContactSelectedListener)) {
            return this.onContactSelectedListener;
        }
        if (cls.isInstance(this.onDialpadQueryChangedListener)) {
            return this.onDialpadQueryChangedListener;
        }
        if (cls.isInstance(this.dialpadListener)) {
            return this.dialpadListener;
        }
        if (cls.isInstance(this.dialpadFragmentHostInterface)) {
            return this.dialpadFragmentHostInterface;
        }
        if (cls.isInstance(this.searchFragmentListener)) {
            return this.searchFragmentListener;
        }
        if (cls.isInstance(this.callLogAdapterOnActionModeStateChangedListener)) {
            return this.callLogAdapterOnActionModeStateChangedListener;
        }
        if (cls.isInstance(this.callLogHostInterface)) {
            return this.callLogHostInterface;
        }
        if (cls.isInstance(this.callLogFragmentListener)) {
            return this.callLogFragmentListener;
        }
        if (cls.isInstance(this.onListFragmentScrolledListener)) {
            return this.onListFragmentScrolledListener;
        }
        if (cls.isInstance(this.onPhoneNumberPickerActionListener)) {
            return this.onPhoneNumberPickerActionListener;
        }
        if (cls.isInstance(this.oldSpeedDialFragmentHost)) {
            return this.oldSpeedDialFragmentHost;
        }
        if (cls.isInstance(this.searchController)) {
            return this.searchController;
        }
        if (cls.isInstance(this.speedDialFragmentHost)) {
            return this.speedDialFragmentHost;
        }
        return null;
    }

    public /* synthetic */ void lambda$initLayout$0$OldMainActivityPeer(View view) {
        ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MAIN_CLICK_FAB_TO_OPEN_DIALPAD);
        this.searchController.showDialpad(true);
        if (this.callLogAdapterOnActionModeStateChangedListener.isEnabled) {
            LogUtil.m9i("OldMainActivityPeer.onFabClicked", "closing multiselect", new Object[0]);
            this.callLogAdapterOnActionModeStateChangedListener.actionMode.finish();
        }
    }

    public /* synthetic */ void lambda$onActivityResult$2$OldMainActivityPeer(String str, View view) {
        this.activity.startActivity(IntentProvider.getSendSmsIntentProvider(str).getClickIntent(this.activity));
    }

    public /* synthetic */ void lambda$onActivityResume$1$OldMainActivityPeer() {
        ((StubMetrics) MetricsComponent.get(this.activity).metrics()).recordMemory("OldMainActivityPeer.onResume");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x024b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityCreate(android.os.Bundle r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            java.lang.String r2 = "OldMainActivityPeer.onActivityCreate"
            com.android.dialer.common.LogUtil.enterBlock(r2)
            com.android.dialer.util.TransactionSafeActivity r2 = r0.activity
            com.android.dialer.theme.base.ThemeComponent r2 = com.android.dialer.theme.base.ThemeComponent.get(r2)
            com.android.dialer.theme.base.Theme r2 = r2.theme()
            com.android.dialer.theme.base.impl.AospThemeImpl r2 = (com.android.dialer.theme.base.impl.AospThemeImpl) r2
            r2.getTheme()
            com.android.dialer.util.TransactionSafeActivity r2 = r0.activity
            r3 = 2131886367(0x7f12011f, float:1.940731E38)
            r2.setTheme(r3)
            com.android.dialer.util.TransactionSafeActivity r2 = r0.activity
            r3 = 2131493015(0x7f0c0097, float:1.8609498E38)
            r2.setContentView((int) r3)
            com.android.dialer.util.TransactionSafeActivity r2 = r0.activity
            com.android.dialer.common.concurrent.DialerExecutorComponent r2 = com.android.dialer.common.concurrent.DialerExecutorComponent.get(r2)
            com.android.dialer.util.TransactionSafeActivity r3 = r0.activity
            android.app.FragmentManager r3 = r3.getFragmentManager()
            java.lang.String r4 = "Query last phone number"
            com.android.dialer.common.concurrent.UiListener r2 = r2.createUiListener((android.app.FragmentManager) r3, (java.lang.String) r4)
            r0.getLastOutgoingCallListener = r2
            com.android.dialer.util.TransactionSafeActivity r2 = r0.activity
            com.android.dialer.common.concurrent.DialerExecutorComponent r2 = com.android.dialer.common.concurrent.DialerExecutorComponent.get(r2)
            com.android.dialer.util.TransactionSafeActivity r3 = r0.activity
            android.app.FragmentManager r3 = r3.getFragmentManager()
            java.lang.String r4 = "Missed call observer"
            com.android.dialer.common.concurrent.UiListener r2 = r2.createUiListener((android.app.FragmentManager) r3, (java.lang.String) r4)
            r0.missedCallObserverUiListener = r2
            com.android.dialer.main.impl.OldMainActivityPeer$MainOnContactSelectedListener r2 = new com.android.dialer.main.impl.OldMainActivityPeer$MainOnContactSelectedListener
            com.android.dialer.util.TransactionSafeActivity r3 = r0.activity
            r2.<init>(r3)
            r0.onContactSelectedListener = r2
            com.android.dialer.main.impl.OldMainActivityPeer$MainDialpadFragmentHost r2 = new com.android.dialer.main.impl.OldMainActivityPeer$MainDialpadFragmentHost
            r3 = 0
            r2.<init>(r3)
            r0.dialpadFragmentHostInterface = r2
            com.android.dialer.util.TransactionSafeActivity r2 = r0.activity
            r4 = 2131296463(0x7f0900cf, float:1.8210843E38)
            android.view.View r2 = r2.findViewById(r4)
            r0.snackbarContainer = r2
            com.android.dialer.util.TransactionSafeActivity r2 = r0.activity
            r5 = 2131296773(0x7f090205, float:1.8211472E38)
            android.view.View r2 = r2.findViewById(r5)
            r0.bottomSheet = r2
            android.view.View r2 = r0.bottomSheet
            android.support.design.widget.BottomSheetBehavior r2 = android.support.design.widget.BottomSheetBehavior.from(r2)
            r5 = 5
            r2.setState(r5)
            com.android.dialer.util.TransactionSafeActivity r2 = r0.activity
            r5 = 2131296552(0x7f090128, float:1.8211024E38)
            android.view.View r2 = r2.findViewById(r5)
            android.support.design.widget.FloatingActionButton r2 = (android.support.design.widget.FloatingActionButton) r2
            com.android.dialer.main.impl.-$$Lambda$OldMainActivityPeer$Q82ng-xG9OMzBSLSdGJEBsVHCIo r5 = new com.android.dialer.main.impl.-$$Lambda$OldMainActivityPeer$Q82ng-xG9OMzBSLSdGJEBsVHCIo
            r5.<init>()
            r2.setOnClickListener(r5)
            com.android.dialer.util.TransactionSafeActivity r5 = r0.activity
            r6 = 2131296930(0x7f0902a2, float:1.821179E38)
            android.view.View r5 = r5.findViewById(r6)
            r15 = r5
            com.android.dialer.main.impl.toolbar.MainToolbar r15 = (com.android.dialer.main.impl.toolbar.MainToolbar) r15
            com.android.dialer.util.TransactionSafeActivity r5 = r0.activity
            r15.maybeShowSimulator(r5)
            com.android.dialer.util.TransactionSafeActivity r5 = r0.activity
            android.view.View r6 = r5.findViewById(r6)
            android.support.v7.widget.Toolbar r6 = (android.support.p002v7.widget.Toolbar) r6
            r5.setSupportActionBar(r6)
            com.android.dialer.util.TransactionSafeActivity r5 = r0.activity
            r6 = 2131296332(0x7f09004c, float:1.8210578E38)
            android.view.View r5 = r5.findViewById(r6)
            com.android.dialer.main.impl.bottomnav.BottomNavBar r5 = (com.android.dialer.main.impl.bottomnav.BottomNavBar) r5
            r0.bottomNav = r5
            com.android.dialer.main.impl.OldMainActivityPeer$MainBottomNavBarBottomNavTabListener r12 = new com.android.dialer.main.impl.OldMainActivityPeer$MainBottomNavBarBottomNavTabListener
            com.android.dialer.util.TransactionSafeActivity r6 = r0.activity
            android.app.FragmentManager r7 = r6.getFragmentManager()
            com.android.dialer.util.TransactionSafeActivity r5 = r0.activity
            android.support.v4.app.FragmentManager r8 = r5.getSupportFragmentManager()
            android.view.View r10 = r0.bottomSheet
            r11 = 0
            r5 = r12
            r9 = r2
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r0.bottomNavTabListener = r12
            com.android.dialer.main.impl.bottomnav.BottomNavBar r5 = r0.bottomNav
            com.android.dialer.main.impl.OldMainActivityPeer$MainBottomNavBarBottomNavTabListener r6 = r0.bottomNavTabListener
            r5.addOnTabSelectedListener(r6)
            com.android.dialer.util.TransactionSafeActivity r5 = r0.activity
            java.lang.String r6 = "voicemail"
            android.telecom.PhoneAccountHandle r6 = com.android.dialer.telecom.TelecomUtil.getDefaultOutgoingPhoneAccount(r5, r6)
            boolean r7 = com.android.dialer.util.PermissionsUtil.hasReadPhoneStatePermissions(r5)
            r8 = 1
            r9 = 0
            if (r7 != 0) goto L_0x00f7
            java.lang.Object[] r7 = new java.lang.Object[r9]
            java.lang.String r10 = "OldMainActivityPeer.isVoicemailAvailable"
            java.lang.String r11 = "No read phone permisison or not the default dialer."
            com.android.dialer.common.LogUtil.m9i(r10, r11, r7)
            r7 = r9
            goto L_0x0113
        L_0x00f7:
            if (r6 != 0) goto L_0x010a
            java.lang.Class<android.telephony.TelephonyManager> r7 = android.telephony.TelephonyManager.class
            java.lang.Object r7 = r5.getSystemService(r7)
            android.telephony.TelephonyManager r7 = (android.telephony.TelephonyManager) r7
            java.lang.String r7 = r7.getVoiceMailNumber()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            goto L_0x0112
        L_0x010a:
            java.lang.String r7 = com.android.dialer.telecom.TelecomUtil.getVoicemailNumber(r5, r6)
            boolean r7 = android.text.TextUtils.isEmpty(r7)
        L_0x0112:
            r7 = r7 ^ r8
        L_0x0113:
            java.lang.String r10 = "OldMainActivityPeer.canVoicemailTabBeShown"
            if (r7 != 0) goto L_0x011f
            java.lang.Object[] r5 = new java.lang.Object[r9]
            java.lang.String r6 = "Voicemail is not available"
            com.android.dialer.common.LogUtil.m9i(r10, r6, r5)
            goto L_0x013d
        L_0x011f:
            com.android.voicemail.VoicemailComponent r7 = com.android.voicemail.VoicemailComponent.get(r5)
            com.android.voicemail.VoicemailClient r7 = r7.getVoicemailClient()
            boolean r5 = r7.isVoicemailEnabled(r5, r6)
            if (r5 == 0) goto L_0x0136
            java.lang.Object[] r5 = new java.lang.Object[r9]
            java.lang.String r6 = "Voicemail is enabled"
            com.android.dialer.common.LogUtil.m9i(r10, r6, r5)
            r14 = r8
            goto L_0x013e
        L_0x0136:
            java.lang.Object[] r5 = new java.lang.Object[r9]
            java.lang.String r6 = "returning false"
            com.android.dialer.common.LogUtil.m9i(r10, r6, r5)
        L_0x013d:
            r14 = r9
        L_0x013e:
            com.android.dialer.main.impl.bottomnav.BottomNavBar r5 = r0.bottomNav
            r5.showVoicemail(r14)
            com.android.dialer.main.impl.bottomnav.MissedCallCountObserver r5 = new com.android.dialer.main.impl.bottomnav.MissedCallCountObserver
            com.android.dialer.util.TransactionSafeActivity r6 = r0.activity
            android.content.Context r6 = r6.getApplicationContext()
            com.android.dialer.main.impl.bottomnav.BottomNavBar r7 = r0.bottomNav
            com.android.dialer.common.concurrent.UiListener<java.lang.Integer> r8 = r0.missedCallObserverUiListener
            r5.<init>(r6, r7, r8)
            r0.missedCallCountObserver = r5
            com.android.dialer.main.impl.OldMainActivityPeer$MainCallLogFragmentListener r5 = new com.android.dialer.main.impl.OldMainActivityPeer$MainCallLogFragmentListener
            com.android.dialer.util.TransactionSafeActivity r8 = r0.activity
            android.content.ContentResolver r9 = r8.getContentResolver()
            com.android.dialer.main.impl.bottomnav.BottomNavBar r10 = r0.bottomNav
            com.android.dialer.main.impl.OldMainActivityPeer$MainBottomNavBarBottomNavTabListener r12 = r0.bottomNavTabListener
            r7 = r5
            r11 = r15
            r7.<init>(r8, r9, r10, r11, r12)
            r0.callLogFragmentListener = r5
            com.android.dialer.main.impl.bottomnav.BottomNavBar r5 = r0.bottomNav
            com.android.dialer.main.impl.OldMainActivityPeer$MainCallLogFragmentListener r6 = r0.callLogFragmentListener
            r5.addOnTabSelectedListener(r6)
            com.android.dialer.main.impl.bottomnav.BottomNavBar r7 = r0.bottomNav
            com.android.dialer.util.TransactionSafeActivity r5 = r0.activity
            r6 = 2131296931(0x7f0902a3, float:1.8211793E38)
            android.view.View r10 = r5.findViewById(r6)
            android.view.View r11 = r0.snackbarContainer
            com.android.dialer.main.impl.MainSearchController r12 = new com.android.dialer.main.impl.MainSearchController
            com.android.dialer.util.TransactionSafeActivity r6 = r0.activity
            r5 = r12
            r8 = r2
            r9 = r15
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r0.searchController = r12
            com.android.dialer.main.impl.MainSearchController r5 = r0.searchController
            r15.setSearchBarListener(r5)
            com.android.dialer.main.impl.MainSearchController r5 = r0.searchController
            com.android.dialer.main.impl.OldMainActivityPeer$MainOnDialpadQueryChangedListener r6 = new com.android.dialer.main.impl.OldMainActivityPeer$MainOnDialpadQueryChangedListener
            r6.<init>(r5)
            r0.onDialpadQueryChangedListener = r6
            com.android.dialer.main.impl.OldMainActivityPeer$MainDialpadListener r5 = new com.android.dialer.main.impl.OldMainActivityPeer$MainDialpadListener
            com.android.dialer.util.TransactionSafeActivity r6 = r0.activity
            com.android.dialer.main.impl.MainSearchController r7 = r0.searchController
            com.android.dialer.common.concurrent.UiListener<java.lang.String> r8 = r0.getLastOutgoingCallListener
            r5.<init>(r6, r7, r8)
            r0.dialpadListener = r5
            com.android.dialer.main.impl.OldMainActivityPeer$MainSearchFragmentListener r5 = new com.android.dialer.main.impl.OldMainActivityPeer$MainSearchFragmentListener
            com.android.dialer.main.impl.MainSearchController r6 = r0.searchController
            r5.<init>(r6)
            r0.searchFragmentListener = r5
            com.android.dialer.main.impl.OldMainActivityPeer$MainCallLogAdapterOnActionModeStateChangedListener r5 = new com.android.dialer.main.impl.OldMainActivityPeer$MainCallLogAdapterOnActionModeStateChangedListener
            r5.<init>(r3)
            r0.callLogAdapterOnActionModeStateChangedListener = r5
            com.android.dialer.main.impl.OldMainActivityPeer$MainCallLogHost r3 = new com.android.dialer.main.impl.OldMainActivityPeer$MainCallLogHost
            com.android.dialer.main.impl.MainSearchController r5 = r0.searchController
            r3.<init>(r5, r2)
            r0.callLogHostInterface = r3
            com.android.dialer.main.impl.OldMainActivityPeer$MainOnListFragmentScrolledListener r2 = new com.android.dialer.main.impl.OldMainActivityPeer$MainOnListFragmentScrolledListener
            android.view.View r3 = r0.snackbarContainer
            r2.<init>(r3)
            r0.onListFragmentScrolledListener = r2
            com.android.dialer.main.impl.OldMainActivityPeer$MainOnPhoneNumberPickerActionListener r2 = new com.android.dialer.main.impl.OldMainActivityPeer$MainOnPhoneNumberPickerActionListener
            com.android.dialer.util.TransactionSafeActivity r3 = r0.activity
            r2.<init>(r3)
            r0.onPhoneNumberPickerActionListener = r2
            com.android.dialer.main.impl.OldMainActivityPeer$MainOldSpeedDialFragmentHost r2 = new com.android.dialer.main.impl.OldMainActivityPeer$MainOldSpeedDialFragmentHost
            com.android.dialer.util.TransactionSafeActivity r8 = r0.activity
            r3 = 2131296800(0x7f090220, float:1.8211527E38)
            android.view.View r9 = r8.findViewById(r3)
            com.android.dialer.main.impl.bottomnav.BottomNavBar r10 = r0.bottomNav
            com.android.dialer.util.TransactionSafeActivity r5 = r0.activity
            r6 = 2131296425(0x7f0900a9, float:1.8210766E38)
            android.view.View r5 = r5.findViewById(r6)
            r11 = r5
            android.widget.ImageView r11 = (android.widget.ImageView) r11
            com.android.dialer.util.TransactionSafeActivity r5 = r0.activity
            r6 = 2131296791(0x7f090217, float:1.8211509E38)
            android.view.View r5 = r5.findViewById(r6)
            r12 = r5
            com.android.dialer.app.list.RemoveView r12 = (com.android.dialer.app.list.RemoveView) r12
            com.android.dialer.util.TransactionSafeActivity r5 = r0.activity
            r6 = 2131296849(0x7f090251, float:1.8211626E38)
            android.view.View r13 = r5.findViewById(r6)
            r7 = r2
            r5 = r14
            r14 = r15
            r7.<init>(r8, r9, r10, r11, r12, r13, r14)
            r0.oldSpeedDialFragmentHost = r2
            com.android.dialer.main.impl.OldMainActivityPeer$MainSpeedDialFragmentHost r2 = new com.android.dialer.main.impl.OldMainActivityPeer$MainSpeedDialFragmentHost
            com.android.dialer.util.TransactionSafeActivity r6 = r0.activity
            android.view.View r3 = r6.findViewById(r3)
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
            com.android.dialer.util.TransactionSafeActivity r6 = r0.activity
            android.view.View r4 = r6.findViewById(r4)
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            com.android.dialer.util.TransactionSafeActivity r6 = r0.activity
            r7 = 2131296569(0x7f090139, float:1.8211058E38)
            android.view.View r6 = r6.findViewById(r7)
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            r2.<init>(r15, r3, r4, r6)
            r0.speedDialFragmentHost = r2
            com.android.dialer.main.impl.OldMainActivityPeer$LastTabController r2 = new com.android.dialer.main.impl.OldMainActivityPeer$LastTabController
            com.android.dialer.util.TransactionSafeActivity r3 = r0.activity
            com.android.dialer.main.impl.bottomnav.BottomNavBar r4 = r0.bottomNav
            r2.<init>(r3, r4, r5)
            r0.lastTabController = r2
            if (r1 == 0) goto L_0x024b
            java.lang.String r2 = "saved_language_code"
            java.lang.String r2 = r1.getString(r2)
            r0.savedLanguageCode = r2
            com.android.dialer.main.impl.MainSearchController r2 = r0.searchController
            r2.onRestoreInstanceState(r1)
            com.android.dialer.main.impl.bottomnav.BottomNavBar r2 = r0.bottomNav
            java.lang.String r3 = "current_tab"
            int r1 = r1.getInt(r3)
            r2.selectTab(r1)
            goto L_0x0254
        L_0x024b:
            com.android.dialer.util.TransactionSafeActivity r1 = r0.activity
            android.content.Intent r1 = r1.getIntent()
            r0.onHandleIntent(r1)
        L_0x0254:
            com.android.dialer.util.TransactionSafeActivity r0 = r0.activity
            com.android.dialer.smartdial.util.SmartDialPrefix.initializeNanpSettings(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.main.impl.OldMainActivityPeer.onActivityCreate(android.os.Bundle):void");
    }

    public void onActivityPause() {
        this.searchController.onActivityPause();
        LocalBroadcastManager.getInstance(this.activity).unregisterReceiver(this.disableCallLogFrameworkReceiver);
        this.activity.getContentResolver().unregisterContentObserver(this.missedCallCountObserver);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.m9i("OldMainActivityPeer.onActivityResult", "requestCode:%d, resultCode:%d", Integer.valueOf(i), Integer.valueOf(i2));
        if (i == 1) {
            this.searchController.onVoiceResults(i2, intent);
        } else if (i == 2) {
            if (i2 == 1) {
                LogUtil.m9i("OldMainActivityPeer.onActivityResult", "returned from call composer, error occurred", new Object[0]);
                Snackbar.make(this.snackbarContainer, (CharSequence) this.activity.getString(R.string.call_composer_connection_failed, new Object[]{intent.getStringExtra("contact_name")}), 0).show();
                return;
            }
            LogUtil.m9i("OldMainActivityPeer.onActivityResult", "returned from call composer, no error", new Object[0]);
        } else if (i == 4) {
            if (i2 == -1 && intent != null && intent.getBooleanExtra("has_enriched_call_data", false)) {
                String stringExtra = intent.getStringExtra("phone_number");
                Snackbar make = Snackbar.make(this.snackbarContainer, (CharSequence) this.activity.getString(R.string.ec_data_deleted), 5000);
                make.setAction((int) R.string.view_conversation, (View.OnClickListener) new View.OnClickListener(stringExtra) {
                    private final /* synthetic */ String f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        OldMainActivityPeer.this.lambda$onActivityResult$2$OldMainActivityPeer(this.f$1, view);
                    }
                });
                make.setActionTextColor(ContextCompat.getColor(this.activity, R.color.dialer_snackbar_action_text_color));
                make.show();
            }
        } else if (i == 3) {
            ((DuoStub) DuoComponent.get(this.activity).getDuo()).reloadReachability(this.activity);
        } else {
            LogUtil.m8e("OldMainActivityPeer.onActivityResult", GeneratedOutlineSupport.outline5("Unknown request code: ", i), new Object[0]);
        }
    }

    @SuppressLint({"MissingPermission"})
    public void onActivityResume() {
        LogUtil.enterBlock("MainBottomNavBarBottomNavTabListener.onActivityResume");
        this.callLogFragmentListener.onActivityResume();
        Database.get(this.activity).getDatabaseHelper(this.activity).startSmartDialUpdateThread(!R$style.getLocale(this.activity).getISO3Language().equals(this.savedLanguageCode));
        if (!TelecomUtil.isInManagedCall(this.activity) && !this.searchController.isInSearch()) {
            PostCall.promptUserForMessageIfNecessary(this.activity, this.snackbarContainer);
        }
        if (this.searchController.isInSearch() || this.callLogAdapterOnActionModeStateChangedListener.isActionModeStateEnabled()) {
            this.bottomNav.setVisibility(8);
        } else {
            this.bottomNav.setVisibility(0);
        }
        LocalBroadcastManager.getInstance(this.activity).registerReceiver(this.disableCallLogFrameworkReceiver, new IntentFilter("disableCallLogFramework"));
        this.bottomNavTabListener.ensureCorrectCallLogShown();
        this.bottomNavTabListener.ensureCorrectVoicemailShown();
        if (this.bottomNavTabListener.newCallLogFragmentActive()) {
            if (PermissionsUtil.hasCallLogReadPermissions(this.activity)) {
                this.missedCallCountObserver.onChange(false);
                this.activity.getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true, this.missedCallCountObserver);
            } else {
                this.bottomNav.setNotificationCount(1, 0);
            }
        }
        DialerExecutorModule.postDelayedOnUiThread(new Runnable() {
            public final void run() {
                OldMainActivityPeer.this.lambda$onActivityResume$1$OldMainActivityPeer();
            }
        }, 1000);
    }

    public void onActivityStop() {
        this.lastTabController.onActivityStop();
        this.callLogFragmentListener.onActivityStop(this.activity.isChangingConfigurations(), ((KeyguardManager) this.activity.getSystemService(KeyguardManager.class)).isKeyguardLocked());
    }

    public boolean onBackPressed() {
        LogUtil.enterBlock("OldMainActivityPeer.onBackPressed");
        return this.searchController.onBackPressed();
    }

    public void onNewIntent(Intent intent) {
        LogUtil.enterBlock("OldMainActivityPeer.onNewIntent");
        onHandleIntent(intent);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("saved_language_code", R$style.getLocale(this.activity).getISO3Language());
        bundle.putInt("current_tab", this.bottomNav.getSelectedTab());
        this.searchController.onSaveInstanceState(bundle);
    }

    public void onUserLeaveHint() {
        this.searchController.onUserLeaveHint();
    }
}
