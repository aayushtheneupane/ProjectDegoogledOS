package com.android.dialer.app.calllog;

import android.app.Activity;
import android.app.Fragment;
import android.app.KeyguardManager;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.appcompat.R$style;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.v13.app.FragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.app.Bindings;
import com.android.dialer.app.calllog.CallLogAdapter;
import com.android.dialer.app.calllog.CallLogModalAlertManager;
import com.android.dialer.app.calllog.calllogcache.CallLogCache;
import com.android.dialer.app.contactinfo.ContactInfoCache;
import com.android.dialer.app.contactinfo.ExpirableCacheHeadlessFragment;
import com.android.dialer.app.legacybindings.DialerLegacyBindingsStub;
import com.android.dialer.app.voicemail.VoicemailPlaybackPresenter;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.common.FragmentUtils;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.database.CallLogQueryHandler;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.metrics.MetricsComponent;
import com.android.dialer.metrics.jank.RecyclerViewJankLogger;
import com.android.dialer.oem.CequintCallerIdManager;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.phonenumbercache.ContactInfoHelper;
import com.android.dialer.util.PermissionsUtil;
import com.android.dialer.widget.EmptyContentView;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Arrays;

public class CallLogFragment extends Fragment implements CallLogQueryHandler.Listener, CallLogAdapter.CallFetcher, CallLogAdapter.MultiSelectRemoveView, EmptyContentView.OnEmptyViewActionButtonClickedListener, FragmentCompat.OnRequestPermissionsResultCallback, CallLogModalAlertManager.Listener, View.OnClickListener {
    /* access modifiers changed from: private */
    public CallLogAdapter adapter;
    private final ContentObserver callLogObserver;
    private CallLogQueryHandler callLogQueryHandler;
    private int callTypeFilter;
    private ContactInfoCache contactInfoCache;
    private final ContentObserver contactsObserver;
    private long dateLimit;
    private final Handler displayUpdateHandler;
    private EmptyContentView emptyListView;
    /* access modifiers changed from: private */
    public final Handler handler;
    private boolean hasReadCallLogPermission;
    private boolean isCallLogActivity;
    private LinearLayoutManager layoutManager;
    private int logLimit;
    private boolean menuVisible;
    protected CallLogModalAlertManager modalAlertManager;
    private ViewGroup modalAlertView;
    private View multiSelectUnSelectAllViewContent;
    private final ContactInfoCache.OnContactInfoChangedListener onContactInfoChangedListener;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    /* access modifiers changed from: private */
    public boolean refreshDataRequired;
    private boolean scrollToTop;
    private boolean selectAllMode;
    private ImageView selectUnselectAllIcon;
    private TextView selectUnselectAllViewText;

    public interface CallLogFragmentListener {
        void showMultiSelectRemoveView(boolean z);

        void updateTabUnreadCounts();
    }

    protected class CustomContentObserver extends ContentObserver {
        public CustomContentObserver() {
            super(CallLogFragment.this.handler);
        }

        public void onChange(boolean z) {
            boolean unused = CallLogFragment.this.refreshDataRequired = true;
        }
    }

    public interface HostInterface {
        void enableFloatingButton(boolean z);

        void showDialpad();
    }

    public CallLogFragment() {
        this(-1, -1);
    }

    /* access modifiers changed from: private */
    public void refreshData() {
        if (this.refreshDataRequired) {
            this.contactInfoCache.invalidate();
            this.adapter.setLoading(true);
            fetchCalls();
            this.callLogQueryHandler.fetchVoicemailStatus();
            this.callLogQueryHandler.fetchMissedCallsUnreadCount();
            this.refreshDataRequired = false;
            return;
        }
        this.adapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void rescheduleDisplayUpdate() {
        if (!this.displayUpdateHandler.hasMessages(1)) {
            this.displayUpdateHandler.sendEmptyMessageDelayed(1, 60000 - (System.currentTimeMillis() % 60000));
        }
    }

    private void updateEmptyMessage(int i) {
        int i2;
        Activity activity = getActivity();
        if (activity != null) {
            if (!PermissionsUtil.hasPermission(activity, "android.permission.READ_CALL_LOG")) {
                this.emptyListView.setDescription(R.string.permission_no_calllog);
                this.emptyListView.setActionLabel(R.string.permission_single_turn_on);
                return;
            }
            if (i == -1) {
                i2 = R.string.call_log_all_empty;
            } else if (i == 3) {
                i2 = R.string.call_log_missed_empty;
            } else if (i == 4) {
                i2 = R.string.call_log_voicemail_empty;
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline5("Unexpected filter type in CallLogFragment: ", i));
            }
            this.emptyListView.setDescription(i2);
            if (this.isCallLogActivity) {
                this.emptyListView.setActionLabel(0);
            } else if (i == -1) {
                this.emptyListView.setActionLabel(R.string.call_log_all_empty_action);
            } else {
                this.emptyListView.setActionLabel(0);
            }
        }
    }

    private void updateSelectAllIcon() {
        if (this.selectAllMode) {
            this.selectUnselectAllIcon.setImageDrawable(getContext().getDrawable(R.drawable.ic_check_mark_blue_24dp));
            getAdapter().onAllSelected();
            return;
        }
        this.selectUnselectAllIcon.setImageDrawable(getContext().getDrawable(R.drawable.ic_empty_check_mark_white_24dp));
        getAdapter().onAllDeselected();
    }

    public void fetchCalls() {
        this.callLogQueryHandler.fetchCalls(this.callTypeFilter, this.dateLimit);
        if (!this.isCallLogActivity && getActivity() != null && !getActivity().isFinishing() && FragmentUtils.getParent((Fragment) this, CallLogFragmentListener.class) != null) {
            ((CallLogFragmentListener) FragmentUtils.getParentUnsafe((Fragment) this, CallLogFragmentListener.class)).updateTabUnreadCounts();
        }
    }

    public CallLogAdapter getAdapter() {
        return this.adapter;
    }

    /* access modifiers changed from: protected */
    public VoicemailPlaybackPresenter getVoicemailPlaybackPresenter() {
        return null;
    }

    public boolean isModalAlertVisible() {
        CallLogModalAlertManager callLogModalAlertManager = this.modalAlertManager;
        return callLogModalAlertManager != null && !callLogModalAlertManager.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public void markMissedCallsAsReadAndRemoveNotifications() {
        if (this.callLogQueryHandler != null && !((KeyguardManager) getContext().getSystemService(KeyguardManager.class)).isKeyguardLocked()) {
            this.callLogQueryHandler.markMissedCallsAsRead();
            CallLogNotificationsService.cancelAllMissedCalls(getContext());
        }
    }

    public void onActivityCreated(Bundle bundle) {
        LogUtil.enterBlock("CallLogFragment.onActivityCreated");
        super.onActivityCreated(bundle);
        setupData();
        if (bundle != null && bundle.getBoolean("select_all_mode_checked", false)) {
            updateSelectAllIcon();
        }
        this.adapter.onRestoreInstanceState(bundle);
    }

    public boolean onCallsFetched(Cursor cursor) {
        if (getActivity() == null || getActivity().isFinishing()) {
            return false;
        }
        this.adapter.invalidatePositions();
        this.adapter.setLoading(false);
        this.adapter.changeCursor(cursor);
        getActivity().invalidateOptionsMenu();
        if (cursor == null || cursor.getCount() <= 0) {
            RecyclerView recyclerView2 = this.recyclerView;
            recyclerView2.setPaddingRelative(recyclerView2.getPaddingStart(), 0, this.recyclerView.getPaddingEnd(), 0);
            this.emptyListView.setVisibility(0);
        } else {
            RecyclerView recyclerView3 = this.recyclerView;
            recyclerView3.setPaddingRelative(recyclerView3.getPaddingStart(), 0, this.recyclerView.getPaddingEnd(), getResources().getDimensionPixelSize(R.dimen.floating_action_button_list_bottom_padding));
            this.emptyListView.setVisibility(8);
        }
        if (!this.scrollToTop) {
            return true;
        }
        if (this.layoutManager.findFirstVisibleItemPosition() > 5) {
            this.recyclerView.smoothScrollToPosition(0);
        }
        this.handler.post(new Runnable() {
            public void run() {
                if (CallLogFragment.this.getActivity() != null && !CallLogFragment.this.getActivity().isFinishing()) {
                    CallLogFragment.this.recyclerView.smoothScrollToPosition(0);
                }
            }
        });
        this.scrollToTop = false;
        return true;
    }

    public void onClick(View view) {
        this.selectAllMode = !this.selectAllMode;
        if (this.selectAllMode) {
            ((LoggingBindingsStub) Logger.get(view.getContext())).logImpression(DialerImpression$Type.MULTISELECT_SELECT_ALL);
        } else {
            ((LoggingBindingsStub) Logger.get(view.getContext())).logImpression(DialerImpression$Type.MULTISELECT_UNSELECT_ALL);
        }
        updateSelectAllIcon();
    }

    public void onCreate(Bundle bundle) {
        LogUtil.enterBlock("CallLogFragment.onCreate");
        super.onCreate(bundle);
        this.refreshDataRequired = true;
        if (bundle != null) {
            this.callTypeFilter = bundle.getInt("filter_type", this.callTypeFilter);
            this.logLimit = bundle.getInt("log_limit", this.logLimit);
            this.dateLimit = bundle.getLong("date_limit", this.dateLimit);
            this.isCallLogActivity = bundle.getBoolean("is_call_log_activity", this.isCallLogActivity);
            this.hasReadCallLogPermission = bundle.getBoolean("has_read_call_log_permission", false);
            this.refreshDataRequired = bundle.getBoolean("refresh_data_required", this.refreshDataRequired);
            this.selectAllMode = bundle.getBoolean("select_all_mode_checked", false);
        }
        Activity activity = getActivity();
        this.callLogQueryHandler = new CallLogQueryHandler(activity, activity.getContentResolver(), this, this.logLimit);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.call_log_fragment, viewGroup, false);
        setupView(inflate);
        return inflate;
    }

    public void onDestroy() {
        LogUtil.enterBlock("CallLogFragment.onDestroy");
        CallLogAdapter callLogAdapter = this.adapter;
        if (callLogAdapter != null) {
            callLogAdapter.changeCursor((Cursor) null);
        }
        if (this.callLogObserver != null) {
            getActivity().getContentResolver().unregisterContentObserver(this.callLogObserver);
        }
        getActivity().getContentResolver().unregisterContentObserver(this.contactsObserver);
        super.onDestroy();
    }

    public void onEmptyViewActionButtonClicked() {
        if (getActivity() != null) {
            String[] permissionsCurrentlyDenied = PermissionsUtil.getPermissionsCurrentlyDenied(getContext(), PermissionsUtil.allPhoneGroupPermissionsUsedInDialer);
            if (permissionsCurrentlyDenied.length > 0) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Requesting permissions: ");
                outline13.append(Arrays.toString(permissionsCurrentlyDenied));
                LogUtil.m9i("CallLogFragment.onEmptyViewActionButtonClicked", outline13.toString(), new Object[0]);
                FragmentCompat.requestPermissions(this, permissionsCurrentlyDenied, 1);
            } else if (!this.isCallLogActivity) {
                LogUtil.m9i("CallLogFragment.onEmptyViewActionButtonClicked", "showing dialpad", new Object[0]);
                ((HostInterface) FragmentUtils.getParentUnsafe((Fragment) this, HostInterface.class)).showDialpad();
            }
        }
    }

    public void onMissedCallsUnreadCountFetched(Cursor cursor) {
    }

    public void onNotVisible() {
        LogUtil.enterBlock("CallLogFragment.onPageUnselected");
    }

    public void onPause() {
        LogUtil.enterBlock("CallLogFragment.onPause");
        if (getUserVisibleHint()) {
            onNotVisible();
        }
        this.displayUpdateHandler.removeMessages(1);
        this.adapter.onPause();
        super.onPause();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1 && iArr.length >= 1 && iArr[0] == 0) {
            this.refreshDataRequired = true;
        }
    }

    public void onResume() {
        LogUtil.enterBlock("CallLogFragment.onResume");
        super.onResume();
        boolean hasPermission = PermissionsUtil.hasPermission(getActivity(), "android.permission.READ_CALL_LOG");
        if (!this.hasReadCallLogPermission && hasPermission) {
            this.refreshDataRequired = true;
            updateEmptyMessage(this.callTypeFilter);
        }
        ContentResolver contentResolver = getActivity().getContentResolver();
        if (PermissionsUtil.hasCallLogReadPermissions(getContext())) {
            contentResolver.registerContentObserver(CallLog.CONTENT_URI, true, this.callLogObserver);
        } else {
            LogUtil.m10w("CallLogFragment.onCreate", "call log permission not available", new Object[0]);
        }
        if (PermissionsUtil.hasContactsReadPermissions(getContext())) {
            contentResolver.registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, this.contactsObserver);
        } else {
            LogUtil.m10w("CallLogFragment.onCreate", "contacts permission not available.", new Object[0]);
        }
        this.hasReadCallLogPermission = hasPermission;
        this.adapter.clearFilteredNumbersCache();
        refreshData();
        this.adapter.onResume();
        rescheduleDisplayUpdate();
        if (getUserVisibleHint()) {
            onVisible();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("filter_type", this.callTypeFilter);
        bundle.putInt("log_limit", this.logLimit);
        bundle.putLong("date_limit", this.dateLimit);
        bundle.putBoolean("is_call_log_activity", this.isCallLogActivity);
        bundle.putBoolean("has_read_call_log_permission", this.hasReadCallLogPermission);
        bundle.putBoolean("refresh_data_required", this.refreshDataRequired);
        bundle.putBoolean("select_all_mode_checked", this.selectAllMode);
        CallLogAdapter callLogAdapter = this.adapter;
        if (callLogAdapter != null) {
            callLogAdapter.onSaveInstanceState(bundle);
        }
    }

    public void onShowModalAlert(boolean z) {
        Object[] objArr = {Boolean.valueOf(z), this, Boolean.valueOf(getUserVisibleHint())};
        getAdapter().notifyDataSetChanged();
        HostInterface hostInterface = (HostInterface) FragmentUtils.getParent((Fragment) this, HostInterface.class);
        if (z) {
            this.recyclerView.setVisibility(8);
            this.modalAlertView.setVisibility(0);
            if (hostInterface != null && getUserVisibleHint()) {
                hostInterface.enableFloatingButton(false);
                return;
            }
            return;
        }
        this.recyclerView.setVisibility(0);
        this.modalAlertView.setVisibility(8);
        if (hostInterface != null && getUserVisibleHint()) {
            hostInterface.enableFloatingButton(true);
        }
    }

    public void onStart() {
        LogUtil.enterBlock("CallLogFragment.onStart");
        super.onStart();
        this.contactInfoCache.setCequintCallerIdManager(CequintCallerIdManager.isCequintCallerIdEnabled(getContext()) ? new CequintCallerIdManager() : null);
    }

    public void onStop() {
        LogUtil.enterBlock("CallLogFragment.onStop");
        super.onStop();
        this.adapter.onStop();
        this.contactInfoCache.stop();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updateEmptyMessage(this.callTypeFilter);
    }

    public void onVisible() {
        LogUtil.enterBlock("CallLogFragment.onPageSelected");
        if (getActivity() != null && FragmentUtils.getParent((Fragment) this, HostInterface.class) != null) {
            ((HostInterface) FragmentUtils.getParentUnsafe((Fragment) this, HostInterface.class)).enableFloatingButton(!isModalAlertVisible());
        }
    }

    public void onVoicemailStatusFetched(Cursor cursor) {
    }

    public void onVoicemailUnreadCountFetched(Cursor cursor) {
    }

    public void setMenuVisibility(boolean z) {
        super.setMenuVisibility(z);
        if (this.menuVisible != z) {
            this.menuVisible = z;
            if (z && isResumed()) {
                refreshData();
            }
        }
    }

    public void setSelectAllModeToFalse() {
        this.selectAllMode = false;
        this.selectUnselectAllIcon.setImageDrawable(getContext().getDrawable(R.drawable.ic_empty_check_mark_white_24dp));
    }

    /* access modifiers changed from: protected */
    public void setupData() {
        int i = this.isCallLogActivity ? 1 : 2;
        this.contactInfoCache = new ContactInfoCache(ExpirableCacheHeadlessFragment.attach((AppCompatActivity) getActivity()).getRetainedCache(), new ContactInfoHelper(getActivity(), R$style.getCurrentCountryIso(getActivity())), this.onContactInfoChangedListener);
        this.adapter = ((DialerLegacyBindingsStub) Bindings.getLegacy(getActivity())).newCallLogAdapter(getActivity(), this.recyclerView, this, this, (CallLogAdapter.OnActionModeStateChangedListener) FragmentUtils.getParent((Fragment) this, CallLogAdapter.OnActionModeStateChangedListener.class), new CallLogCache(getActivity()), this.contactInfoCache, getVoicemailPlaybackPresenter(), new FilteredNumberAsyncQueryHandler(getActivity()), i);
        this.recyclerView.setAdapter(this.adapter);
        if (this.adapter.getOnScrollListener() != null) {
            this.recyclerView.addOnScrollListener(this.adapter.getOnScrollListener());
        }
        fetchCalls();
    }

    /* access modifiers changed from: protected */
    public void setupView(View view) {
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        if (((SharedPrefConfigProvider) ConfigProviderComponent.get(getContext()).getConfigProvider()).getBoolean("is_call_log_item_anim_null", false)) {
            this.recyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
        }
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.addOnScrollListener(new RecyclerViewJankLogger(MetricsComponent.get(getContext()).metrics(), "OldCallLog.Jank"));
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setLayoutManager(this.layoutManager);
        PerformanceReport.logOnScrollStateChange(this.recyclerView);
        this.emptyListView = (EmptyContentView) view.findViewById(R.id.empty_list_view);
        this.emptyListView.setImage(R.drawable.empty_call_log);
        this.emptyListView.setActionClickedListener(this);
        this.modalAlertView = (ViewGroup) view.findViewById(R.id.modal_message_container);
        this.modalAlertManager = new CallLogModalAlertManager(LayoutInflater.from(getContext()), this.modalAlertView, this);
        this.multiSelectUnSelectAllViewContent = view.findViewById(R.id.multi_select_select_all_view_content);
        this.selectUnselectAllViewText = (TextView) view.findViewById(R.id.select_all_view_text);
        this.selectUnselectAllIcon = (ImageView) view.findViewById(R.id.select_all_view_icon);
        this.multiSelectUnSelectAllViewContent.setOnClickListener((View.OnClickListener) null);
        this.selectUnselectAllIcon.setOnClickListener(this);
        this.selectUnselectAllViewText.setOnClickListener(this);
    }

    public void showMultiSelectRemoveView(boolean z) {
        this.multiSelectUnSelectAllViewContent.setVisibility(z ? 0 : 8);
        float f = 0.0f;
        this.multiSelectUnSelectAllViewContent.setAlpha(z ? 0.0f : 1.0f);
        ViewPropertyAnimator animate = this.multiSelectUnSelectAllViewContent.animate();
        if (z) {
            f = 1.0f;
        }
        animate.alpha(f).start();
        if (z) {
            ((CallLogFragmentListener) FragmentUtils.getParentUnsafe((Fragment) this, CallLogFragmentListener.class)).showMultiSelectRemoveView(true);
            return;
        }
        CallLogFragmentListener callLogFragmentListener = (CallLogFragmentListener) FragmentUtils.getParent((Fragment) this, CallLogFragmentListener.class);
        if (callLogFragmentListener != null) {
            callLogFragmentListener.showMultiSelectRemoveView(false);
        }
    }

    public void tapSelectAll() {
        LogUtil.m9i("CallLogFragment.tapSelectAll", "imitating select all", new Object[0]);
        this.selectAllMode = true;
        updateSelectAllIcon();
    }

    public CallLogFragment(int i, boolean z) {
        this(i, -1);
        this.isCallLogActivity = z;
    }

    public CallLogFragment(int i, int i2) {
        this.handler = new Handler();
        this.callLogObserver = new CustomContentObserver();
        this.contactsObserver = new CustomContentObserver();
        this.onContactInfoChangedListener = new ContactInfoCache.OnContactInfoChangedListener() {
            public void onContactInfoChanged() {
                if (CallLogFragment.this.adapter != null) {
                    CallLogFragment.this.adapter.notifyDataSetChanged();
                }
            }
        };
        this.menuVisible = true;
        this.callTypeFilter = -1;
        this.logLimit = -1;
        this.dateLimit = 0;
        this.isCallLogActivity = false;
        this.displayUpdateHandler = new Handler() {
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    CallLogFragment.this.refreshData();
                    CallLogFragment.this.rescheduleDisplayUpdate();
                    return;
                }
                throw new AssertionError(GeneratedOutlineSupport.outline6("Invalid message: ", message));
            }
        };
        this.callTypeFilter = i;
        this.logLimit = i2;
        this.dateLimit = 0;
    }
}
