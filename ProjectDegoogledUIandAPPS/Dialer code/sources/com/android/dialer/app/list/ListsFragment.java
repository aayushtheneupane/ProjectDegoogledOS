package com.android.dialer.app.list;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Trace;
import android.preference.PreferenceManager;
import android.provider.VoicemailContract;
import android.support.p000v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import com.android.contacts.common.list.ViewPagerTabs;
import com.android.dialer.R;
import com.android.dialer.app.calllog.CallLogFragment;
import com.android.dialer.app.calllog.CallLogNotificationsService;
import com.android.dialer.app.calllog.VisualVoicemailCallLogFragment;
import com.android.dialer.common.LogUtil;
import com.android.dialer.database.CallLogQueryHandler;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.ScreenEvent$Type;
import com.android.dialer.logging.UiAction$Type;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.voicemail.listui.error.OmtpVoicemailMessageCreator;
import com.android.dialer.voicemail.listui.error.VoicemailStatusCorruptionHandler$Source;
import com.android.dialer.voicemailstatus.VoicemailStatusHelper;
import java.util.ArrayList;

public class ListsFragment extends Fragment implements ViewPager.OnPageChangeListener, CallLogQueryHandler.Listener, CallLogFragment.CallLogFragmentListener {
    private UiAction$Type[] actionTypeList;
    private DialtactsPagerAdapter adapter;
    /* access modifiers changed from: private */
    public CallLogQueryHandler callLogQueryHandler;
    private final DialerImpression$Type[] clickImpressionList = new DialerImpression$Type[4];
    private Fragment currentPage;
    private boolean hasFetchedVoicemailStatus;
    private final ArrayList<ViewPager.OnPageChangeListener> onPageChangeListeners = new ArrayList<>();
    private boolean onPageScrolledBeforeScrollStateSettling;
    private boolean paused;
    private SharedPreferences prefs;
    private RemoveView removeView;
    private View removeViewContent;
    private boolean showVoicemailTabAfterVoicemailStatusIsFetched;
    private final DialerImpression$Type[] swipeImpressionList = new DialerImpression$Type[4];
    private int tabIndex = 0;
    private DialerViewPager viewPager;
    private ViewPagerTabs viewPagerTabs;
    private final ContentObserver voicemailStatusObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean z) {
            super.onChange(z);
            ListsFragment.this.callLogQueryHandler.fetchVoicemailStatus();
        }
    };

    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        if (!this.onPageChangeListeners.contains(onPageChangeListener)) {
            this.onPageChangeListeners.add(onPageChangeListener);
        }
    }

    public int getCurrentTabIndex() {
        return this.tabIndex;
    }

    public RemoveView getRemoveView() {
        return this.removeView;
    }

    public int getTabCount() {
        return this.adapter.getCount();
    }

    public boolean hasFrequents() {
        DialtactsPagerAdapter dialtactsPagerAdapter = this.adapter;
        return ((OldSpeedDialFragment) dialtactsPagerAdapter.getItem(dialtactsPagerAdapter.getRtlPosition(0))).hasFrequents();
    }

    public void markMissedCallsAsReadAndRemoveNotifications() {
        CallLogQueryHandler callLogQueryHandler2 = this.callLogQueryHandler;
        if (callLogQueryHandler2 != null) {
            callLogQueryHandler2.markMissedCallsAsRead();
            CallLogNotificationsService.cancelAllMissedCalls(getContext());
        }
    }

    public boolean onCallsFetched(Cursor cursor) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        Trace.beginSection("ListsFragment onCreate");
        super.onCreate(bundle);
        this.prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        Trace.endSection();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.enterBlock("ListsFragment.onCreateView");
        Trace.beginSection("ListsFragment onCreateView");
        Trace.beginSection("ListsFragment inflate view");
        View inflate = layoutInflater.inflate(R.layout.lists_fragment, viewGroup, false);
        Trace.endSection();
        Trace.beginSection("ListsFragment setup views");
        this.actionTypeList = new UiAction$Type[4];
        UiAction$Type[] uiAction$TypeArr = this.actionTypeList;
        uiAction$TypeArr[0] = UiAction$Type.CHANGE_TAB_TO_FAVORITE;
        uiAction$TypeArr[1] = UiAction$Type.CHANGE_TAB_TO_CALL_LOG;
        uiAction$TypeArr[2] = UiAction$Type.CHANGE_TAB_TO_CONTACTS;
        uiAction$TypeArr[3] = UiAction$Type.CHANGE_TAB_TO_VOICEMAIL;
        DialerImpression$Type[] dialerImpression$TypeArr = this.swipeImpressionList;
        dialerImpression$TypeArr[0] = DialerImpression$Type.SWITCH_TAB_TO_FAVORITE_BY_SWIPE;
        dialerImpression$TypeArr[1] = DialerImpression$Type.SWITCH_TAB_TO_CALL_LOG_BY_SWIPE;
        dialerImpression$TypeArr[2] = DialerImpression$Type.SWITCH_TAB_TO_CONTACTS_BY_SWIPE;
        dialerImpression$TypeArr[3] = DialerImpression$Type.SWITCH_TAB_TO_VOICEMAIL_BY_SWIPE;
        DialerImpression$Type[] dialerImpression$TypeArr2 = this.clickImpressionList;
        dialerImpression$TypeArr2[0] = DialerImpression$Type.SWITCH_TAB_TO_FAVORITE_BY_CLICK;
        dialerImpression$TypeArr2[1] = DialerImpression$Type.SWITCH_TAB_TO_CALL_LOG_BY_CLICK;
        dialerImpression$TypeArr2[2] = DialerImpression$Type.SWITCH_TAB_TO_CONTACTS_BY_CLICK;
        dialerImpression$TypeArr2[3] = DialerImpression$Type.SWITCH_TAB_TO_VOICEMAIL_BY_CLICK;
        String[] strArr = {getResources().getString(R.string.tab_speed_dial), getResources().getString(R.string.tab_history), getResources().getString(R.string.tab_all_contacts), getResources().getString(R.string.tab_voicemail)};
        int[] iArr = {R.drawable.quantum_ic_grade_white_24, R.drawable.quantum_ic_schedule_white_24, R.drawable.quantum_ic_people_white_24, R.drawable.quantum_ic_voicemail_white_24};
        this.viewPager = (DialerViewPager) inflate.findViewById(R.id.lists_pager);
        this.adapter = new DialtactsPagerAdapter(getChildFragmentManager(), strArr, this.prefs.getBoolean("has_active_voicemail_provider", false));
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.setOffscreenPageLimit(1);
        this.viewPager.addOnPageChangeListener(this);
        showTab(0);
        this.viewPagerTabs = (ViewPagerTabs) inflate.findViewById(R.id.lists_pager_header);
        this.viewPagerTabs.configureTabIcons(iArr);
        this.viewPagerTabs.setViewPager(this.viewPager);
        addOnPageChangeListener(this.viewPagerTabs);
        this.removeView = (RemoveView) inflate.findViewById(R.id.remove_view);
        this.removeViewContent = inflate.findViewById(R.id.remove_view_content);
        if (!PermissionsUtil.hasReadVoicemailPermissions(getContext()) || !PermissionsUtil.hasAddVoicemailPermissions(getContext())) {
            LogUtil.m10w("ListsFragment.onCreateView", "no voicemail read permissions", new Object[0]);
        } else {
            getActivity().getContentResolver().registerContentObserver(VoicemailContract.Status.CONTENT_URI, true, this.voicemailStatusObserver);
        }
        Trace.endSection();
        Trace.endSection();
        return inflate;
    }

    public void onDestroy() {
        getActivity().getContentResolver().unregisterContentObserver(this.voicemailStatusObserver);
        super.onDestroy();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.viewPager.removeOnPageChangeListener(this);
    }

    /* JADX INFO: finally extract failed */
    public void onMissedCallsUnreadCountFetched(Cursor cursor) {
        if (getActivity() != null && !getActivity().isFinishing() && cursor != null) {
            try {
                int count = cursor.getCount();
                cursor.close();
                this.viewPagerTabs.setUnreadCount(count, 1);
                this.viewPagerTabs.updateTab(1);
            } catch (Throwable th) {
                cursor.close();
                throw th;
            }
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (i != 2) {
            this.onPageScrolledBeforeScrollStateSettling = false;
        }
        int size = this.onPageChangeListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.onPageChangeListeners.get(i2).onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (i2 != 0) {
            this.onPageScrolledBeforeScrollStateSettling = true;
        }
        this.tabIndex = this.adapter.getRtlPosition(i);
        int size = this.onPageChangeListeners.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.onPageChangeListeners.get(i3).onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        if (this.onPageScrolledBeforeScrollStateSettling) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(this.swipeImpressionList[i]);
            this.onPageScrolledBeforeScrollStateSettling = false;
        } else {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(this.clickImpressionList[i]);
        }
        PerformanceReport.recordClick(this.actionTypeList[i]);
        LogUtil.m9i("ListsFragment.onPageSelected", "position: %d", Integer.valueOf(i));
        this.tabIndex = this.adapter.getRtlPosition(i);
        this.showVoicemailTabAfterVoicemailStatusIsFetched = false;
        int size = this.onPageChangeListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.onPageChangeListeners.get(i2).onPageSelected(i);
        }
        sendScreenViewForCurrentPosition();
        Fragment fragment = this.currentPage;
        if (fragment instanceof CallLogFragment) {
            ((CallLogFragment) fragment).onNotVisible();
        }
        this.currentPage = this.adapter.getItem(i);
        Fragment fragment2 = this.currentPage;
        if (fragment2 instanceof CallLogFragment) {
            ((CallLogFragment) fragment2).onVisible();
        }
    }

    public void onPause() {
        LogUtil.enterBlock("ListsFragment.onPause");
        super.onPause();
        this.paused = true;
    }

    public void onResume() {
        LogUtil.enterBlock("ListsFragment.onResume");
        Trace.beginSection("ListsFragment onResume");
        super.onResume();
        this.paused = false;
        if (getUserVisibleHint()) {
            sendScreenViewForCurrentPosition();
        }
        this.callLogQueryHandler = new CallLogQueryHandler(getActivity(), getActivity().getContentResolver(), this, -1);
        this.callLogQueryHandler.fetchVoicemailStatus();
        this.callLogQueryHandler.fetchMissedCallsUnreadCount();
        Trace.endSection();
        this.currentPage = this.adapter.getItem(this.viewPager.getCurrentItem());
    }

    public void onVoicemailStatusFetched(Cursor cursor) {
        boolean z = true;
        this.hasFetchedVoicemailStatus = true;
        if (getActivity() != null && !this.paused) {
            OmtpVoicemailMessageCreator.maybeFixVoicemailStatus(getContext(), cursor, VoicemailStatusCorruptionHandler$Source.Activity);
            if (VoicemailStatusHelper.getNumberActivityVoicemailSources(cursor) <= 0) {
                z = false;
            }
            if (z != this.adapter.hasActiveVoicemailProvider()) {
                this.adapter.setHasActiveVoicemailProvider(z);
                this.adapter.notifyDataSetChanged();
                if (z) {
                    ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.VVM_TAB_VISIBLE);
                    this.viewPagerTabs.updateTab(3);
                } else {
                    this.viewPagerTabs.removeTab(3);
                    this.adapter.removeVoicemailFragment(getChildFragmentManager());
                }
                this.prefs.edit().putBoolean("has_active_voicemail_provider", z).apply();
            }
            if (z) {
                this.callLogQueryHandler.fetchVoicemailUnreadCount();
            }
            if (this.adapter.hasActiveVoicemailProvider() && this.showVoicemailTabAfterVoicemailStatusIsFetched) {
                this.showVoicemailTabAfterVoicemailStatusIsFetched = false;
                showTab(3);
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void onVoicemailUnreadCountFetched(Cursor cursor) {
        if (getActivity() != null && !getActivity().isFinishing() && cursor != null) {
            try {
                int count = cursor.getCount();
                cursor.close();
                this.viewPagerTabs.setUnreadCount(count, 3);
                this.viewPagerTabs.updateTab(3);
            } catch (Throwable th) {
                cursor.close();
                throw th;
            }
        }
    }

    public void sendScreenViewForCurrentPosition() {
        ScreenEvent$Type screenEvent$Type;
        if (isResumed()) {
            int i = this.tabIndex;
            if (i == 0) {
                screenEvent$Type = ScreenEvent$Type.SPEED_DIAL;
            } else if (i == 1) {
                screenEvent$Type = ScreenEvent$Type.CALL_LOG;
            } else if (i == 2) {
                screenEvent$Type = ScreenEvent$Type.ALL_CONTACTS;
            } else if (i == 3) {
                screenEvent$Type = ScreenEvent$Type.VOICEMAIL_LOG;
            } else {
                return;
            }
            ((LoggingBindingsStub) Logger.get(getActivity())).logScreenView(screenEvent$Type, getActivity());
        }
    }

    public boolean shouldShowFab() {
        Fragment fragment = this.currentPage;
        return !(fragment instanceof VisualVoicemailCallLogFragment) || !((VisualVoicemailCallLogFragment) fragment).isModalAlertVisible();
    }

    public void showMultiSelectRemoveView(boolean z) {
        this.viewPagerTabs.setVisibility(z ? 8 : 0);
        this.viewPager.setEnableSwipingPages(!z);
    }

    public void showRemoveView(boolean z) {
        this.removeViewContent.setVisibility(z ? 0 : 8);
        float f = 0.0f;
        this.removeView.setAlpha(z ? 0.0f : 1.0f);
        ViewPropertyAnimator animate = this.removeView.animate();
        if (z) {
            f = 1.0f;
        }
        animate.alpha(f).start();
    }

    public void showTab(int i) {
        if (i == 3) {
            if (this.adapter.hasActiveVoicemailProvider()) {
                this.viewPager.setCurrentItem(this.adapter.getRtlPosition(3));
            } else if (!this.hasFetchedVoicemailStatus) {
                this.showVoicemailTabAfterVoicemailStatusIsFetched = true;
            }
        } else if (i < getTabCount()) {
            this.viewPager.setCurrentItem(this.adapter.getRtlPosition(i));
        }
    }

    public void updateTabUnreadCounts() {
        CallLogQueryHandler callLogQueryHandler2 = this.callLogQueryHandler;
        if (callLogQueryHandler2 != null) {
            callLogQueryHandler2.fetchMissedCallsUnreadCount();
            if (this.adapter.hasActiveVoicemailProvider()) {
                this.callLogQueryHandler.fetchVoicemailUnreadCount();
            }
        }
    }
}
