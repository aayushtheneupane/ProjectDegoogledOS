package com.android.dialer.app.calllog;

import android.app.Activity;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.p002v7.app.AlertDialog;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.app.calllog.CallLogAsyncTaskUtil;
import com.android.dialer.app.calllog.CallLogFragment;
import com.android.dialer.app.calllog.CallLogGroupBuilder;
import com.android.dialer.app.calllog.CallLogListItemViewHolder;
import com.android.dialer.app.calllog.calllogcache.CallLogCache;
import com.android.dialer.app.contactinfo.ContactInfoCache;
import com.android.dialer.app.voicemail.VoicemailPlaybackPresenter;
import com.android.dialer.blocking.FilteredNumberAsyncQueryHandler;
import com.android.dialer.calllogutils.PhoneCallDetails;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.AsyncTaskExecutor;
import com.android.dialer.common.concurrent.AsyncTaskExecutors;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.DuoListener;
import com.android.dialer.duo.stub.DuoStub;
import com.android.dialer.enrichedcall.EnrichedCallCapabilities;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.EnrichedCallManager;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindings;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.UiAction$Type;
import com.android.dialer.main.MainActivityPeer;
import com.android.dialer.main.impl.OldMainActivityPeer;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.phonenumbercache.ContactInfo;
import com.android.dialer.spam.SpamComponent;
import com.android.dialer.spam.stub.SpamSettingsStub;
import com.android.dialer.util.PermissionsUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CallLogAdapter extends GroupingListAdapter implements CallLogGroupBuilder.GroupCreator, VoicemailPlaybackPresenter.OnVoicemailDeletedListener, DuoListener {
    static final String FILTER_EMERGENCY_CALLS_FLAG = "filter_emergency_calls";
    public ActionMode actionMode = null;
    /* access modifiers changed from: private */
    public final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() != R.id.action_bar_delete_menu_item) {
                return false;
            }
            ((LoggingBindingsStub) Logger.get(CallLogAdapter.this.activity)).logImpression(DialerImpression$Type.MULTISELECT_TAP_DELETE_ICON);
            if (CallLogAdapter.this.selectedItems.size() <= 0) {
                return true;
            }
            CallLogAdapter.access$400(CallLogAdapter.this);
            return true;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            CallLogAdapter callLogAdapter = CallLogAdapter.this;
            Activity activity = callLogAdapter.activity;
            if (activity != null) {
                callLogAdapter.announceforAccessibility(activity.getCurrentFocus(), CallLogAdapter.this.activity.getString(R.string.description_entering_bulk_action_mode));
            }
            CallLogAdapter.this.actionMode = actionMode;
            actionMode.getMenuInflater().inflate(R.menu.actionbar_delete, menu);
            CallLogAdapter.this.multiSelectRemoveView.showMultiSelectRemoveView(true);
            CallLogAdapter.this.actionModeStateChangedListener.onActionModeStateChanged(actionMode, true);
            return true;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            CallLogAdapter callLogAdapter = CallLogAdapter.this;
            Activity activity = callLogAdapter.activity;
            if (activity != null) {
                callLogAdapter.announceforAccessibility(activity.getCurrentFocus(), CallLogAdapter.this.activity.getString(R.string.description_leaving_bulk_action_mode));
            }
            CallLogAdapter.this.selectedItems.clear();
            CallLogAdapter callLogAdapter2 = CallLogAdapter.this;
            callLogAdapter2.actionMode = null;
            callLogAdapter2.selectAllMode = false;
            callLogAdapter2.deselectAllMode = false;
            callLogAdapter2.multiSelectRemoveView.showMultiSelectRemoveView(false);
            CallLogAdapter.this.actionModeStateChangedListener.onActionModeStateChanged((ActionMode) null, false);
            CallLogAdapter.this.notifyDataSetChanged();
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }
    };
    /* access modifiers changed from: private */
    public final OnActionModeStateChangedListener actionModeStateChangedListener;
    protected final Activity activity;
    /* access modifiers changed from: private */
    public final int activityType;
    private final AsyncTaskExecutor asyncTaskExecutor = AsyncTaskExecutors.createAsyncTaskExecutor();
    private CallLogListItemViewHolder.OnClickListener blockReportSpamListener;
    private final CallFetcher callFetcher;
    private final CallLogAlertManager callLogAlertManager;
    protected final CallLogCache callLogCache;
    private final CallLogGroupBuilder callLogGroupBuilder;
    private final CallLogListItemHelper callLogListItemHelper;
    private final Map<Long, Integer> callbackActions = new ArrayMap();
    private ContactInfoCache contactInfoCache;
    private final ConcurrentMap<String, LoggingBindings.ContactsProviderMatchInfo> contactsProviderMatchInfos = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public int currentlyExpandedPosition = -1;
    /* access modifiers changed from: private */
    public long currentlyExpandedRowId = -1;
    private final Map<Long, Integer> dayGroups = new ArrayMap();
    public boolean deselectAllMode = false;
    /* access modifiers changed from: private */
    public final View.OnClickListener expandCollapseListener = new View.OnClickListener() {
        public void onClick(View view) {
            PerformanceReport.recordClick(UiAction$Type.CLICK_CALL_LOG_ITEM);
            CallLogListItemViewHolder callLogListItemViewHolder = (CallLogListItemViewHolder) view.getTag();
            if (callLogListItemViewHolder != null) {
                CallLogAdapter callLogAdapter = CallLogAdapter.this;
                if (callLogAdapter.actionMode == null || callLogListItemViewHolder.voicemailUri == null) {
                    VoicemailPlaybackPresenter voicemailPlaybackPresenter = CallLogAdapter.this.voicemailPlaybackPresenter;
                    if (voicemailPlaybackPresenter != null) {
                        voicemailPlaybackPresenter.resetAll();
                    }
                    if (callLogListItemViewHolder.number != null) {
                        ((EnrichedCallManagerStub) CallLogAdapter.this.getEnrichedCallManager()).getCapabilities(callLogListItemViewHolder.number);
                    }
                    EnrichedCallCapabilities enrichedCallCapabilities = EnrichedCallCapabilities.NO_CAPABILITIES;
                    callLogListItemViewHolder.isCallComposerCapable = enrichedCallCapabilities.isCallComposerCapable();
                    if (enrichedCallCapabilities.isTemporarilyUnavailable()) {
                        LogUtil.m9i("mExpandCollapseListener.onClick", "%s is temporarily unavailable, requesting capabilities", LogUtil.sanitizePhoneNumber(callLogListItemViewHolder.number));
                        ((EnrichedCallManagerStub) CallLogAdapter.this.getEnrichedCallManager()).requestCapabilities(callLogListItemViewHolder.number);
                    }
                    if (callLogListItemViewHolder.rowId == CallLogAdapter.this.currentlyExpandedRowId) {
                        callLogListItemViewHolder.showActions(false);
                        int unused = CallLogAdapter.this.currentlyExpandedPosition = -1;
                        long unused2 = CallLogAdapter.this.currentlyExpandedRowId = -1;
                        return;
                    }
                    if (callLogListItemViewHolder.callType == 3) {
                        CallLogAsyncTaskUtil.markCallAsRead(CallLogAdapter.this.activity, callLogListItemViewHolder.callIds);
                        if (CallLogAdapter.this.activityType == 2) {
                            Assert.checkState(view.getContext() instanceof MainActivityPeer.PeerSupplier, "%s is not a PeerSupplier", view.getContext().getClass());
                            ((CallLogFragment.CallLogFragmentListener) ((OldMainActivityPeer) ((MainActivityPeer.PeerSupplier) view.getContext()).getPeer()).getImpl(CallLogFragment.CallLogFragmentListener.class)).updateTabUnreadCounts();
                        }
                    }
                    CallLogAdapter.this.expandViewHolderActions(callLogListItemViewHolder);
                    return;
                }
                callLogAdapter.selectAllMode = false;
                callLogAdapter.deselectAllMode = false;
                callLogAdapter.multiSelectRemoveView.setSelectAllModeToFalse();
                int access$800 = CallLogAdapter.getVoicemailId(callLogListItemViewHolder.voicemailUri);
                if (CallLogAdapter.this.selectedItems.get(access$800) != null) {
                    ((LoggingBindingsStub) Logger.get(CallLogAdapter.this.activity)).logImpression(DialerImpression$Type.MULTISELECT_SINGLE_PRESS_UNSELECT_ENTRY);
                    CallLogAdapter.this.uncheckMarkCallLogEntry(callLogListItemViewHolder, access$800);
                    return;
                }
                ((LoggingBindingsStub) Logger.get(CallLogAdapter.this.activity)).logImpression(DialerImpression$Type.MULTISELECT_SINGLE_PRESS_SELECT_ENTRY);
                CallLogAdapter.this.checkMarkCallLogEntry(callLogListItemViewHolder);
                if (CallLogAdapter.this.getItemCount() == CallLogAdapter.this.selectedItems.size()) {
                    LogUtil.m9i("mExpandCollapseListener.onClick", "getitem count %d is equal to items select count %d, check select all box", Integer.valueOf(CallLogAdapter.this.getItemCount()), Integer.valueOf(CallLogAdapter.this.selectedItems.size()));
                    CallLogAdapter.this.multiSelectRemoveView.tapSelectAll();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final FilteredNumberAsyncQueryHandler filteredNumberAsyncQueryHandler;
    private final Set<Uri> hiddenItemUris = new ArraySet();
    private Set<Long> hiddenRowIds = new ArraySet();
    /* access modifiers changed from: private */
    public boolean isSpamEnabled;
    private boolean loading = true;
    private final View.OnLongClickListener longPressListener = new View.OnLongClickListener() {
        public boolean onLongClick(View view) {
            if (((SharedPrefConfigProvider) ConfigProviderComponent.get(view.getContext()).getConfigProvider()).getBoolean("enable_call_log_multiselect", true) && CallLogAdapter.this.voicemailPlaybackPresenter != null && (view.getId() == R.id.primary_action_view || view.getId() == R.id.quick_contact_photo)) {
                CallLogAdapter callLogAdapter = CallLogAdapter.this;
                if (callLogAdapter.actionMode == null) {
                    ((LoggingBindingsStub) Logger.get(callLogAdapter.activity)).logImpression(DialerImpression$Type.MULTISELECT_LONG_PRESS_ENTER_MULTI_SELECT_MODE);
                    CallLogAdapter callLogAdapter2 = CallLogAdapter.this;
                    callLogAdapter2.actionMode = view.startActionMode(callLogAdapter2.actionModeCallback);
                }
                ((LoggingBindingsStub) Logger.get(CallLogAdapter.this.activity)).logImpression(DialerImpression$Type.MULTISELECT_LONG_PRESS_TAP_ENTRY);
                CallLogListItemViewHolder callLogListItemViewHolder = (CallLogListItemViewHolder) view.getTag();
                callLogListItemViewHolder.quickContactView.setVisibility(8);
                callLogListItemViewHolder.checkBoxView.setVisibility(0);
                CallLogAdapter.this.expandCollapseListener.onClick(view);
            }
            return true;
        }
    };
    /* access modifiers changed from: private */
    public final MultiSelectRemoveView multiSelectRemoveView;
    public boolean selectAllMode = false;
    /* access modifiers changed from: private */
    public final SparseArray<String> selectedItems = new SparseArray<>();
    protected final VoicemailPlaybackPresenter voicemailPlaybackPresenter;

    public interface CallFetcher {
        void fetchCalls();
    }

    public interface MultiSelectRemoveView {
        void setSelectAllModeToFalse();

        void showMultiSelectRemoveView(boolean z);

        void tapSelectAll();
    }

    public interface OnActionModeStateChangedListener {
        boolean isActionModeStateEnabled();

        void onActionModeStateChanged(ActionMode actionMode, boolean z);
    }

    public CallLogAdapter(Activity activity2, ViewGroup viewGroup, CallFetcher callFetcher2, MultiSelectRemoveView multiSelectRemoveView2, OnActionModeStateChangedListener onActionModeStateChangedListener, CallLogCache callLogCache2, ContactInfoCache contactInfoCache2, VoicemailPlaybackPresenter voicemailPlaybackPresenter2, FilteredNumberAsyncQueryHandler filteredNumberAsyncQueryHandler2, int i) {
        this.activity = activity2;
        this.callFetcher = callFetcher2;
        this.actionModeStateChangedListener = onActionModeStateChangedListener;
        this.multiSelectRemoveView = multiSelectRemoveView2;
        this.voicemailPlaybackPresenter = voicemailPlaybackPresenter2;
        VoicemailPlaybackPresenter voicemailPlaybackPresenter3 = this.voicemailPlaybackPresenter;
        if (voicemailPlaybackPresenter3 != null) {
            voicemailPlaybackPresenter3.setOnVoicemailDeletedListener(this);
        }
        this.activityType = i;
        this.contactInfoCache = contactInfoCache2;
        if (!PermissionsUtil.hasContactsReadPermissions(activity2)) {
            this.contactInfoCache.disableRequestProcessing();
        }
        Resources resources = this.activity.getResources();
        this.callLogCache = callLogCache2;
        this.callLogListItemHelper = new CallLogListItemHelper(new PhoneCallDetailsHelper(this.activity, resources, this.callLogCache), resources, this.callLogCache);
        this.callLogGroupBuilder = new CallLogGroupBuilder(activity2.getApplicationContext(), this);
        Assert.isNotNull(filteredNumberAsyncQueryHandler2);
        this.filteredNumberAsyncQueryHandler = filteredNumberAsyncQueryHandler2;
        Activity activity3 = this.activity;
        this.blockReportSpamListener = new BlockReportSpamListener(activity3, activity3.findViewById(R.id.call_log_fragment_root), ((AppCompatActivity) this.activity).getSupportFragmentManager(), this, this.filteredNumberAsyncQueryHandler);
        setHasStableIds(true);
        this.callLogAlertManager = new CallLogAlertManager(this, LayoutInflater.from(this.activity), viewGroup);
    }

    static /* synthetic */ int access$1900(CallLogAdapter callLogAdapter, long j) {
        Integer num = callLogAdapter.callbackActions.get(Long.valueOf(j));
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    static /* synthetic */ void access$400(CallLogAdapter callLogAdapter) {
        final SparseArray<String> clone = callLogAdapter.selectedItems.clone();
        AlertDialog.Builder builder = new AlertDialog.Builder(callLogAdapter.activity);
        builder.setCancelable(true);
        builder.setTitle((CharSequence) callLogAdapter.activity.getResources().getQuantityString(R.plurals.delete_voicemails_confirmation_dialog_title, callLogAdapter.selectedItems.size()));
        builder.setPositiveButton((int) R.string.voicemailMultiSelectDeleteConfirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("onClick, these items to delete ");
                outline13.append(clone);
                LogUtil.m9i("CallLogAdapter.showDeleteSelectedItemsDialog", outline13.toString(), new Object[0]);
                CallLogAdapter.this.deleteSelectedItems(clone);
                CallLogAdapter.this.actionMode.finish();
                dialogInterface.cancel();
                ((LoggingBindingsStub) Logger.get(CallLogAdapter.this.activity)).logImpression(DialerImpression$Type.MULTISELECT_DELETE_ENTRY_VIA_CONFIRMATION_DIALOG);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                ((LoggingBindingsStub) Logger.get(CallLogAdapter.this.activity)).logImpression(DialerImpression$Type.MULTISELECT_CANCEL_CONFIRMATION_DIALOG_VIA_CANCEL_TOUCH);
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton((int) R.string.voicemailMultiSelectDeleteCancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((LoggingBindingsStub) Logger.get(CallLogAdapter.this.activity)).logImpression(DialerImpression$Type.MULTISELECT_CANCEL_CONFIRMATION_DIALOG_VIA_CANCEL_BUTTON);
                dialogInterface.cancel();
            }
        });
        builder.create().show();
        ((LoggingBindingsStub) Logger.get(callLogAdapter.activity)).logImpression(DialerImpression$Type.MULTISELECT_DISPLAY_DELETE_CONFIRMATION_DIALOG);
    }

    /* access modifiers changed from: private */
    public void announceforAccessibility(View view, String str) {
        if (view != null) {
            view.announceForAccessibility(str);
        }
    }

    /* access modifiers changed from: private */
    public void checkMarkCallLogEntry(CallLogListItemViewHolder callLogListItemViewHolder) {
        View currentFocus = this.activity.getCurrentFocus();
        String string = this.activity.getString(R.string.description_selecting_bulk_action_mode, new Object[]{callLogListItemViewHolder.nameOrNumber});
        if (currentFocus != null) {
            currentFocus.announceForAccessibility(string);
        }
        callLogListItemViewHolder.quickContactView.setVisibility(8);
        callLogListItemViewHolder.checkBoxView.setVisibility(0);
        this.selectedItems.put(getVoicemailId(callLogListItemViewHolder.voicemailUri), callLogListItemViewHolder.voicemailUri);
        updateActionBar();
    }

    /* access modifiers changed from: private */
    public void deleteSelectedItems(SparseArray<String> sparseArray) {
        for (int i = 0; i < sparseArray.size(); i++) {
            String str = sparseArray.get(sparseArray.keyAt(i));
            LogUtil.m9i("CallLogAdapter.deleteSelectedItems", GeneratedOutlineSupport.outline8("deleting uri:", str), new Object[0]);
            CallLogAsyncTaskUtil.deleteVoicemail(this.activity, Uri.parse(str), (CallLogAsyncTaskUtil.CallLogAsyncTaskListener) null);
        }
    }

    /* access modifiers changed from: private */
    public void expandViewHolderActions(CallLogListItemViewHolder callLogListItemViewHolder) {
        if (!TextUtils.isEmpty(callLogListItemViewHolder.voicemailUri)) {
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.VOICEMAIL_EXPAND_ENTRY);
        }
        int i = this.currentlyExpandedPosition;
        callLogListItemViewHolder.showActions(true);
        this.currentlyExpandedPosition = callLogListItemViewHolder.getAdapterPosition();
        this.currentlyExpandedRowId = callLogListItemViewHolder.rowId;
        if (i != -1) {
            notifyItemChanged(i);
        }
    }

    /* access modifiers changed from: private */
    public int getDayGroup(long j) {
        Integer num = this.dayGroups.get(Long.valueOf(j));
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public EnrichedCallManager getEnrichedCallManager() {
        return EnrichedCallComponent.get(this.activity).getEnrichedCallManager();
    }

    /* access modifiers changed from: private */
    public static int getVoicemailId(String str) {
        boolean z = true;
        Assert.checkArgument(str != null);
        if (str.length() <= 0) {
            z = false;
        }
        Assert.checkArgument(z);
        return (int) ContentUris.parseId(Uri.parse(str));
    }

    /* JADX WARNING: type inference failed for: r3v11, types: [java.lang.CharSequence] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean loadData(com.android.dialer.app.calllog.CallLogListItemViewHolder r17, long r18, com.android.dialer.calllogutils.PhoneCallDetails r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r20
            com.android.dialer.common.Assert.isWorkerThread()
            long r3 = r1.rowId
            int r3 = (r18 > r3 ? 1 : (r18 == r3 ? 0 : -1))
            r4 = 0
            if (r3 == 0) goto L_0x001a
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r1 = "CallLogAdapter.loadData"
            java.lang.String r2 = "rowId of viewHolder changed after load task is issued, aborting load"
            com.android.dialer.common.LogUtil.m9i(r1, r2, r0)
            return r4
        L_0x001a:
            java.lang.String r3 = r2.accountComponentName
            java.lang.String r5 = r2.accountId
            android.telecom.PhoneAccountHandle r3 = com.android.dialer.telecom.TelecomUtil.composePhoneAccountHandle(r3, r5)
            com.android.dialer.app.calllog.calllogcache.CallLogCache r5 = r0.callLogCache
            java.lang.CharSequence r6 = r2.number
            boolean r5 = r5.isVoicemailNumber(r3, r6)
            com.android.dialer.phonenumbercache.ContactInfo r6 = com.android.dialer.phonenumbercache.ContactInfo.EMPTY
            java.lang.CharSequence r7 = r2.number
            int r8 = r2.numberPresentation
            boolean r7 = com.android.dialer.phonenumberutil.PhoneNumberHelper.canPlaceCallsTo(r7, r8)
            if (r7 == 0) goto L_0x00fa
            if (r5 != 0) goto L_0x00fa
            int r6 = r17.getAdapterPosition()
            com.android.dialer.app.contactinfo.ContactInfoCache r7 = r0.contactInfoCache
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.CharSequence r10 = r2.number
            r9.append(r10)
            java.lang.String r10 = r2.postDialDigits
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            java.lang.String r10 = r2.countryIso
            com.android.dialer.phonenumbercache.ContactInfo r11 = r2.cachedContactInfo
            long r12 = (long) r6
            android.app.Activity r6 = r0.activity
            com.android.dialer.configprovider.ConfigProviderComponent r6 = com.android.dialer.configprovider.ConfigProviderComponent.get(r6)
            com.android.dialer.configprovider.ConfigProvider r6 = r6.getConfigProvider()
            r14 = 5
            com.android.dialer.configprovider.SharedPrefConfigProvider r6 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r6
            java.lang.String r8 = "number_of_call_to_do_remote_lookup"
            long r14 = r6.getLong(r8, r14)
            int r6 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r6 >= 0) goto L_0x0070
            r6 = 1
            goto L_0x0071
        L_0x0070:
            r6 = r4
        L_0x0071:
            com.android.dialer.phonenumbercache.ContactInfo r6 = r7.getValue(r9, r10, r11, r6)
            java.lang.CharSequence r7 = r2.number
            if (r7 != 0) goto L_0x007b
            goto L_0x00fa
        L_0x007b:
            com.android.dialer.logging.LoggingBindings$ContactsProviderMatchInfo$Builder r8 = com.android.dialer.logging.LoggingBindings.ContactsProviderMatchInfo.builder()
            java.lang.String r9 = r7.toString()
            java.lang.String r9 = android.telephony.PhoneNumberUtils.normalizeNumber(r9)
            int r9 = r9.length()
            r8.setInputNumberLength(r9)
            java.lang.String r9 = r7.toString()
            java.lang.String r9 = android.telephony.PhoneNumberUtils.extractPostDialPortion(r9)
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x00a9
            java.lang.String r9 = r2.postDialDigits
            if (r9 == 0) goto L_0x00a7
            boolean r9 = r9.isEmpty()
            if (r9 != 0) goto L_0x00a7
            goto L_0x00a9
        L_0x00a7:
            r9 = r4
            goto L_0x00aa
        L_0x00a9:
            r9 = 1
        L_0x00aa:
            r8.setInputNumberHasPostdialDigits(r9)
            com.google.i18n.phonenumbers.PhoneNumberUtil r9 = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance()
            java.lang.String r10 = r2.countryIso     // Catch:{ NumberParseException -> 0x00bf }
            com.google.i18n.phonenumbers.Phonenumber$PhoneNumber r10 = r9.parse(r7, r10)     // Catch:{ NumberParseException -> 0x00bf }
            boolean r9 = r9.isValidNumber(r10)     // Catch:{ NumberParseException -> 0x00bf }
            r8.setInputNumberValid(r9)     // Catch:{ NumberParseException -> 0x00bf }
            goto L_0x00c2
        L_0x00bf:
            r8.setInputNumberValid(r4)
        L_0x00c2:
            if (r6 == 0) goto L_0x00ed
            java.lang.String r9 = r6.number
            if (r9 == 0) goto L_0x00ed
            com.android.dialer.logging.ContactSource$Type r9 = r6.sourceType
            com.android.dialer.logging.ContactSource$Type r10 = com.android.dialer.logging.ContactSource$Type.SOURCE_TYPE_DIRECTORY
            if (r9 != r10) goto L_0x00ed
            r9 = 1
            r8.setMatchedContact(r9)
            java.lang.String r10 = r6.number
            java.lang.String r10 = android.telephony.PhoneNumberUtils.normalizeNumber(r10)
            int r10 = r10.length()
            r8.setMatchedNumberLength(r10)
            java.lang.String r10 = r6.number
            java.lang.String r10 = android.telephony.PhoneNumberUtils.extractPostDialPortion(r10)
            boolean r10 = r10.isEmpty()
            r10 = r10 ^ r9
            r8.setMatchedNumberHasPostdialDigits(r10)
        L_0x00ed:
            java.util.concurrent.ConcurrentMap<java.lang.String, com.android.dialer.logging.LoggingBindings$ContactsProviderMatchInfo> r9 = r0.contactsProviderMatchInfos
            java.lang.String r7 = r7.toString()
            com.android.dialer.logging.LoggingBindings$ContactsProviderMatchInfo r8 = r8.build()
            r9.put(r7, r8)
        L_0x00fa:
            java.lang.String r7 = r6.formattedNumber
            if (r7 != 0) goto L_0x0100
            r7 = 0
            goto L_0x0104
        L_0x0100:
            java.lang.CharSequence r7 = android.telephony.PhoneNumberUtils.createTtsSpannable(r7)
        L_0x0104:
            android.app.Activity r8 = r0.activity
            r2.updateDisplayNumber(r8, r7, r5)
            java.lang.String r5 = r2.displayNumber
            r1.displayNumber = r5
            r1.accountHandle = r3
            r2.accountHandle = r3
            java.lang.String r3 = r6.name
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0121
            java.lang.String r3 = r6.nameAlternative
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0151
        L_0x0121:
            android.net.Uri r3 = r6.lookupUri
            java.lang.String r3 = r6.name
            r2.namePrimary = r3
            java.lang.String r3 = r6.nameAlternative
            r2.nameAlternative = r3
            android.app.Activity r3 = r0.activity
            com.android.dialer.contacts.ContactsComponent r3 = com.android.dialer.contacts.ContactsComponent.get(r3)
            com.android.dialer.contacts.displaypreference.ContactDisplayPreferences r3 = r3.contactDisplayPreferences()
            com.android.dialer.contacts.displaypreference.ContactDisplayPreferences$DisplayOrder r3 = r3.getDisplayOrder()
            r2.nameDisplayOrder = r3
            int r3 = r6.type
            r2.numberType = r3
            java.lang.String r3 = r6.label
            r2.numberLabel = r3
            android.net.Uri r3 = r6.photoUri
            r2.photoUri = r3
            com.android.dialer.logging.ContactSource$Type r3 = r6.sourceType
            r2.sourceType = r3
            java.lang.String r3 = r6.objectId
            long r7 = r6.userType
            r2.contactUserType = r7
        L_0x0151:
            r3 = 5
            java.lang.Object[] r3 = new java.lang.Object[r3]
            int r5 = r17.getAdapterPosition()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r3[r4] = r5
            java.lang.String r4 = r2.geocode
            r5 = 1
            r3[r5] = r4
            r4 = 2
            java.lang.String r5 = r6.geoDescription
            r3[r4] = r5
            r4 = 3
            android.net.Uri r7 = r2.photoUri
            r3[r4] = r7
            r4 = 4
            android.net.Uri r7 = r6.photoUri
            r3[r4] = r7
            boolean r3 = android.text.TextUtils.isEmpty(r5)
            if (r3 != 0) goto L_0x017c
            java.lang.String r3 = r6.geoDescription
            r2.geocode = r3
        L_0x017c:
            r1.info = r6
            android.app.Activity r3 = r0.activity
            android.content.res.Resources r3 = r3.getResources()
            com.android.dialer.logging.ContactSource$Type r4 = r2.sourceType
            com.android.dialer.logging.ContactSource$Type r5 = com.android.dialer.logging.ContactSource$Type.SOURCE_TYPE_CNAP
            java.lang.String r6 = ""
            if (r4 == r5) goto L_0x01a9
            com.android.dialer.logging.ContactSource$Type r5 = com.android.dialer.logging.ContactSource$Type.SOURCE_TYPE_CEQUINT_CALLER_ID
            if (r4 != r5) goto L_0x0191
            goto L_0x01a9
        L_0x0191:
            int r4 = r2.numberType
            if (r4 != 0) goto L_0x019e
            java.lang.CharSequence r4 = r2.numberLabel
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x019e
            goto L_0x01a9
        L_0x019e:
            int r4 = r2.numberType
            java.lang.CharSequence r5 = r2.numberLabel
            java.lang.CharSequence r3 = android.provider.ContactsContract.CommonDataKinds.Phone.getTypeLabel(r3, r4, r5)
            r6 = r3
            java.lang.String r6 = (java.lang.String) r6
        L_0x01a9:
            r1.numberType = r6
            com.android.dialer.app.calllog.CallLogListItemHelper r0 = r0.callLogListItemHelper
            r0.updatePhoneCallDetails(r2)
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.calllog.CallLogAdapter.loadData(com.android.dialer.app.calllog.CallLogListItemViewHolder, long, com.android.dialer.calllogutils.PhoneCallDetails):boolean");
    }

    /* access modifiers changed from: private */
    public void render(CallLogListItemViewHolder callLogListItemViewHolder, PhoneCallDetails phoneCallDetails, long j) {
        String str;
        String str2;
        Assert.isMainThread();
        if (j != callLogListItemViewHolder.rowId) {
            LogUtil.m9i("CallLogAdapter.render", "rowId of viewHolder changed after load task is issued, aborting render", new Object[0]);
            return;
        }
        callLogListItemViewHolder.primaryActionView.setVisibility(0);
        callLogListItemViewHolder.workIconView.setVisibility(phoneCallDetails.contactUserType == 1 ? 0 : 8);
        if (this.selectAllMode && (str2 = callLogListItemViewHolder.voicemailUri) != null) {
            this.selectedItems.put(getVoicemailId(str2), callLogListItemViewHolder.voicemailUri);
        }
        if (this.deselectAllMode && (str = callLogListItemViewHolder.voicemailUri) != null) {
            this.selectedItems.delete(getVoicemailId(str));
        }
        String str3 = callLogListItemViewHolder.voicemailUri;
        if (str3 != null && this.selectedItems.get(getVoicemailId(str3)) != null) {
            callLogListItemViewHolder.checkBoxView.setVisibility(0);
            callLogListItemViewHolder.quickContactView.setVisibility(8);
        } else if (callLogListItemViewHolder.voicemailUri != null) {
            callLogListItemViewHolder.checkBoxView.setVisibility(8);
            callLogListItemViewHolder.quickContactView.setVisibility(0);
        }
        this.callLogListItemHelper.setPhoneCallDetails(callLogListItemViewHolder, phoneCallDetails);
        if (this.currentlyExpandedRowId == callLogListItemViewHolder.rowId) {
            this.currentlyExpandedPosition = callLogListItemViewHolder.getAdapterPosition();
            callLogListItemViewHolder.showActions(true);
        } else {
            callLogListItemViewHolder.showActions(false);
        }
        callLogListItemViewHolder.dayGroupHeader.setVisibility(callLogListItemViewHolder.dayGroupHeaderVisibility);
        callLogListItemViewHolder.dayGroupHeader.setText(callLogListItemViewHolder.dayGroupHeaderText);
    }

    /* access modifiers changed from: private */
    public void uncheckMarkCallLogEntry(CallLogListItemViewHolder callLogListItemViewHolder, int i) {
        View currentFocus = this.activity.getCurrentFocus();
        String string = this.activity.getString(R.string.description_unselecting_bulk_action_mode, new Object[]{callLogListItemViewHolder.nameOrNumber});
        if (currentFocus != null) {
            currentFocus.announceForAccessibility(string);
        }
        this.selectedItems.delete(i);
        callLogListItemViewHolder.checkBoxView.setVisibility(8);
        callLogListItemViewHolder.quickContactView.setVisibility(0);
        updateActionBar();
    }

    private void updateActionBar() {
        if (this.actionMode == null && this.selectedItems.size() > 0) {
            ((LoggingBindingsStub) Logger.get(this.activity)).logImpression(DialerImpression$Type.MULTISELECT_ROTATE_AND_SHOW_ACTION_MODE);
            this.activity.startActionMode(this.actionModeCallback);
        }
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 != null) {
            actionMode2.setTitle(this.activity.getResources().getString(R.string.voicemailMultiSelectActionBarTitle, new Object[]{Integer.toString(this.selectedItems.size())}));
        }
    }

    private void updateCheckMarkedStatusOfEntry(CallLogListItemViewHolder callLogListItemViewHolder) {
        String str;
        if (this.selectedItems.size() > 0 && (str = callLogListItemViewHolder.voicemailUri) != null) {
            int voicemailId = getVoicemailId(str);
            if (this.selectedItems.get(voicemailId) != null) {
                checkMarkCallLogEntry(callLogListItemViewHolder);
            } else {
                uncheckMarkCallLogEntry(callLogListItemViewHolder, voicemailId);
            }
        }
    }

    public /* bridge */ /* synthetic */ void addGroup(int i, int i2) {
        super.addGroup(i, i2);
    }

    /* access modifiers changed from: protected */
    public void addGroups(Cursor cursor) {
        this.callLogGroupBuilder.addGroups(cursor);
    }

    public /* bridge */ /* synthetic */ void changeCursor(Cursor cursor) {
        super.changeCursor(cursor);
    }

    public void clearDayGroups() {
        this.dayGroups.clear();
    }

    public void clearFilteredNumbersCache() {
        this.filteredNumberAsyncQueryHandler.clearCache();
    }

    /* access modifiers changed from: package-private */
    public void disableRequestProcessingForTest() {
        this.contactInfoCache.disableRequestProcessing();
    }

    public CallLogAlertManager getAlertManager() {
        return this.callLogAlertManager;
    }

    public View.OnClickListener getExpandCollapseListener() {
        return this.expandCollapseListener;
    }

    public int getGroupSize(int i) {
        return super.getGroupSize(i - (this.callLogAlertManager.isEmpty() ^ true ? 1 : 0));
    }

    public Object getItem(int i) {
        return super.getItem(i - (this.callLogAlertManager.isEmpty() ^ true ? 1 : 0));
    }

    public int getItemCount() {
        return super.getItemCount() + (this.callLogAlertManager.isEmpty() ^ true ? 1 : 0);
    }

    public long getItemId(int i) {
        Cursor cursor = (Cursor) getItem(i);
        if (cursor != null) {
            return cursor.getLong(0);
        }
        return 0;
    }

    public int getItemViewType(int i) {
        return (i != 0 || this.callLogAlertManager.isEmpty()) ? 2 : 1;
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public void injectContactInfoForTest(String str, String str2, ContactInfo contactInfo) {
        this.contactInfoCache.injectContactInfoForTest(str, str2, contactInfo);
    }

    public void invalidatePositions() {
        this.currentlyExpandedPosition = -1;
    }

    public boolean isEmpty() {
        if (!this.loading && getItemCount() == 0) {
            return true;
        }
        return false;
    }

    public void onAllDeselected() {
        this.selectAllMode = false;
        this.deselectAllMode = true;
        this.selectedItems.clear();
        updateActionBar();
        notifyDataSetChanged();
    }

    public void onAllSelected() {
        this.selectAllMode = true;
        this.deselectAllMode = false;
        this.selectedItems.clear();
        for (int i = 0; i < getItemCount(); i++) {
            Cursor cursor = (Cursor) getItem(i);
            if (cursor != null) {
                Assert.checkArgument(6 == cursor.getColumnIndex("voicemail_uri"));
                String string = cursor.getString(6);
                this.selectedItems.put(getVoicemailId(string), string);
            }
        }
        updateActionBar();
        notifyDataSetChanged();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x01f4 A[LOOP:4: B:24:0x01f4->B:27:0x0209, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(android.support.p002v7.widget.RecyclerView.ViewHolder r17, int r18) {
        /*
            r16 = this;
            r6 = r16
            r0 = r18
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "onBindViewHolder: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            android.os.Trace.beginSection(r1)
            int r1 = r6.getItemViewType(r0)
            r2 = 1
            if (r1 == r2) goto L_0x02ff
            java.lang.Object r1 = r6.getItem(r0)
            android.database.Cursor r1 = (android.database.Cursor) r1
            if (r1 != 0) goto L_0x0029
            goto L_0x02ff
        L_0x0029:
            r7 = r17
            com.android.dialer.app.calllog.CallLogListItemViewHolder r7 = (com.android.dialer.app.calllog.CallLogListItemViewHolder) r7
            r6.updateCheckMarkedStatusOfEntry(r7)
            r8 = 0
            r7.isLoaded = r8
            int r0 = r6.getGroupSize(r0)
            com.android.dialer.common.Assert.isMainThread()
            int r3 = r1.getPosition()
            com.android.dialer.calldetails.CallDetailsEntries$Builder r4 = com.android.dialer.calldetails.CallDetailsEntries.newBuilder()
            r5 = r8
        L_0x0043:
            r9 = 2
            r10 = 3
            r11 = 18
            r12 = 21
            r13 = 20
            r14 = 4
            if (r5 >= r0) goto L_0x00ae
            com.android.dialer.calldetails.CallDetailsEntries$CallDetailsEntry$Builder r15 = com.android.dialer.calldetails.CallDetailsEntries.CallDetailsEntry.newBuilder()
            r17 = r3
            long r2 = r1.getLong(r8)
            r15.setCallId(r2)
            int r2 = r1.getInt(r14)
            r15.setCallType(r2)
            long r2 = r1.getLong(r12)
            r15.setDataUsage(r2)
            long r2 = r1.getLong(r9)
            r15.setDate(r2)
            long r2 = r1.getLong(r10)
            r15.setDuration(r2)
            int r2 = r1.getInt(r13)
            r15.setFeatures(r2)
            long r2 = r1.getLong(r9)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r15.setCallMappingId(r2)
            java.lang.String r2 = r1.getString(r11)
            android.app.Activity r3 = r6.activity
            com.android.dialer.duo.DuoComponent r3 = com.android.dialer.duo.DuoComponent.get(r3)
            com.android.dialer.duo.Duo r3 = r3.getDuo()
            com.android.dialer.duo.stub.DuoStub r3 = (com.android.dialer.duo.stub.DuoStub) r3
            r3.isDuoAccount((java.lang.String) r2)
            com.google.protobuf.GeneratedMessageLite r2 = r15.build()
            com.android.dialer.calldetails.CallDetailsEntries$CallDetailsEntry r2 = (com.android.dialer.calldetails.CallDetailsEntries.CallDetailsEntry) r2
            r4.addEntries(r2)
            r1.moveToNext()
            int r5 = r5 + 1
            r3 = r17
            r2 = 1
            goto L_0x0043
        L_0x00ae:
            r2 = r3
            r1.moveToPosition(r2)
            com.google.protobuf.GeneratedMessageLite r2 = r4.build()
            com.android.dialer.calldetails.CallDetailsEntries r2 = (com.android.dialer.calldetails.CallDetailsEntries) r2
            com.android.dialer.common.Assert.isMainThread()
            r3 = 1
            java.lang.String r4 = r1.getString(r3)
            r3 = 24
            java.lang.String r5 = r1.getString(r3)
            r15 = 25
            java.lang.String r15 = r1.getString(r15)
            r8 = 17
            int r8 = r1.getInt(r8)
            com.android.dialer.phonenumbercache.ContactInfo r12 = new com.android.dialer.phonenumbercache.ContactInfo
            r12.<init>()
            r11 = 11
            java.lang.String r11 = r1.getString(r11)
            android.net.Uri r11 = android.support.p002v7.appcompat.R$style.parseUriOrNull(r11)
            r12.lookupUri = r11
            r11 = 8
            java.lang.String r14 = r1.getString(r11)
            r12.name = r14
            r14 = 9
            int r14 = r1.getInt(r14)
            r12.type = r14
            r14 = 10
            java.lang.String r14 = r1.getString(r14)
            r12.label = r14
            r14 = 12
            java.lang.String r14 = r1.getString(r14)
            java.lang.String r3 = r1.getString(r3)
            if (r14 != 0) goto L_0x011b
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r11 = 1
            java.lang.String r13 = r1.getString(r11)
            r14.append(r13)
            r14.append(r3)
            java.lang.String r14 = r14.toString()
        L_0x011b:
            r12.number = r14
            r3 = 13
            java.lang.String r3 = r1.getString(r3)
            r12.normalizedNumber = r3
            r3 = 14
            long r13 = r1.getLong(r3)
            r12.photoId = r13
            r3 = 23
            java.lang.String r3 = r1.getString(r3)
            android.net.Uri r3 = android.support.p002v7.appcompat.R$style.parseUriOrNull(r3)
            android.net.Uri r3 = android.support.p002v7.appcompat.R$style.nullForNonContactsUri(r3)
            r12.photoUri = r3
            r3 = 15
            java.lang.String r3 = r1.getString(r3)
            r12.formattedNumber = r3
            int r3 = android.os.Build.VERSION.SDK_INT
            r3 = 26
            int r3 = r1.getInt(r3)
            com.android.dialer.calllogutils.PhoneCallDetails r11 = new com.android.dialer.calllogutils.PhoneCallDetails
            r11.<init>(r4, r8, r5)
            r11.viaNumber = r15
            r5 = 5
            java.lang.String r5 = r1.getString(r5)
            r11.countryIso = r5
            long r13 = r1.getLong(r9)
            r11.date = r13
            long r13 = r1.getLong(r10)
            r11.duration = r13
            int r5 = r1.getPosition()
            r9 = 0
            r13 = 0
        L_0x016d:
            if (r9 >= r0) goto L_0x017c
            r14 = 20
            int r15 = r1.getInt(r14)
            r13 = r13 | r15
            r1.moveToNext()
            int r9 = r9 + 1
            goto L_0x016d
        L_0x017c:
            r1.moveToPosition(r5)
            r11.features = r13
            r5 = 7
            java.lang.String r5 = r1.getString(r5)
            r11.geocode = r5
            r5 = 22
            java.lang.String r5 = r1.getString(r5)
            r11.transcription = r5
            r11.transcriptionState = r3
            int r3 = r1.getPosition()
            int[] r5 = new int[r0]
            r9 = 0
        L_0x0199:
            if (r9 >= r0) goto L_0x01a8
            r13 = 4
            int r14 = r1.getInt(r13)
            r5[r9] = r14
            r1.moveToNext()
            int r9 = r9 + 1
            goto L_0x0199
        L_0x01a8:
            r1.moveToPosition(r3)
            r11.callTypes = r5
            r3 = 18
            java.lang.String r3 = r1.getString(r3)
            r11.accountComponentName = r3
            r3 = 19
            java.lang.String r3 = r1.getString(r3)
            r11.accountId = r3
            r11.cachedContactInfo = r12
            r3 = 21
            boolean r5 = r1.isNull(r3)
            if (r5 != 0) goto L_0x01ce
            long r12 = r1.getLong(r3)
            java.lang.Long.valueOf(r12)
        L_0x01ce:
            r3 = 0
            long r12 = r1.getLong(r3)
            r7.rowId = r12
            int r5 = r1.getPosition()
            long[] r9 = new long[r0]
            r12 = r3
        L_0x01dc:
            if (r12 >= r0) goto L_0x01eb
            long r13 = r1.getLong(r3)
            r9[r12] = r13
            r1.moveToNext()
            int r12 = r12 + 1
            r3 = 0
            goto L_0x01dc
        L_0x01eb:
            r1.moveToPosition(r5)
            r7.callIds = r9
            int r0 = r1.getPosition()
        L_0x01f4:
            boolean r3 = r1.moveToPrevious()
            if (r3 == 0) goto L_0x020c
            java.util.Set<java.lang.Long> r3 = r6.hiddenRowIds
            r5 = 0
            long r12 = r1.getLong(r5)
            java.lang.Long r5 = java.lang.Long.valueOf(r12)
            boolean r3 = r3.contains(r5)
            if (r3 == 0) goto L_0x020c
            goto L_0x01f4
        L_0x020c:
            boolean r3 = r1.isBeforeFirst()
            if (r3 == 0) goto L_0x0217
            r1.moveToPosition(r0)
            r0 = -1
            goto L_0x0224
        L_0x0217:
            r3 = 0
            long r12 = r1.getLong(r3)
            int r3 = r6.getDayGroup(r12)
            r1.moveToPosition(r0)
            r0 = r3
        L_0x0224:
            r11.previousGroup = r0
            r7.number = r4
            java.lang.String r0 = r11.countryIso
            r7.countryIso = r0
            java.lang.String r0 = r11.postDialDigits
            r7.postDialDigits = r0
            r7.numberPresentation = r8
            int[] r0 = r11.callTypes
            r3 = 0
            r4 = r0[r3]
            r5 = 4
            if (r4 == r5) goto L_0x023e
            r0 = r0[r3]
            if (r0 != r10) goto L_0x024c
        L_0x023e:
            r0 = 16
            int r0 = r1.getInt(r0)
            r3 = 1
            if (r0 != r3) goto L_0x0249
            r0 = 1
            goto L_0x024a
        L_0x0249:
            r0 = 0
        L_0x024a:
            r11.isRead = r0
        L_0x024c:
            r0 = 4
            int r0 = r1.getInt(r0)
            r7.callType = r0
            r0 = 6
            java.lang.String r0 = r1.getString(r0)
            r7.voicemailUri = r0
            java.lang.String r0 = r7.voicemailUri
            r11.voicemailUri = r0
            java.lang.String r0 = r7.number
            r3 = 0
            long r4 = r1.getLong(r3)
            android.app.Activity r1 = r6.activity
            com.android.dialer.configprovider.ConfigProviderComponent r1 = com.android.dialer.configprovider.ConfigProviderComponent.get(r1)
            com.android.dialer.configprovider.ConfigProvider r1 = r1.getConfigProvider()
            com.android.dialer.configprovider.SharedPrefConfigProvider r1 = (com.android.dialer.configprovider.SharedPrefConfigProvider) r1
            java.lang.String r8 = "filter_emergency_calls"
            boolean r1 = r1.getBoolean(r8, r3)
            if (r1 != 0) goto L_0x027a
            goto L_0x0284
        L_0x027a:
            if (r0 == 0) goto L_0x0284
            boolean r0 = android.telephony.PhoneNumberUtils.isEmergencyNumber(r0)
            if (r0 == 0) goto L_0x0284
            r0 = 1
            goto L_0x0285
        L_0x0284:
            r0 = 0
        L_0x0285:
            if (r0 == 0) goto L_0x0288
            goto L_0x0294
        L_0x0288:
            java.util.Set<java.lang.Long> r0 = r6.hiddenRowIds
            java.lang.Long r1 = java.lang.Long.valueOf(r4)
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x0296
        L_0x0294:
            r0 = 1
            goto L_0x0297
        L_0x0296:
            r0 = 0
        L_0x0297:
            if (r0 == 0) goto L_0x02a6
            android.support.v7.widget.CardView r0 = r7.callLogEntryView
            r1 = 8
            r0.setVisibility(r1)
            android.widget.TextView r0 = r7.dayGroupHeader
            r0.setVisibility(r1)
            goto L_0x02ff
        L_0x02a6:
            android.support.v7.widget.CardView r0 = r7.callLogEntryView
            r1 = 0
            r0.setVisibility(r1)
            long r0 = r6.currentlyExpandedRowId
            long r3 = r7.rowId
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x02b7
            r7.inflateActionViewStub()
        L_0x02b7:
            long r4 = r7.rowId
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]
            int r1 = r7.getAdapterPosition()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r8 = 0
            r0[r8] = r1
            r7.isSpam = r8
            r0 = 0
            r7.blockId = r0
            r7.isSpamFeatureEnabled = r8
            java.lang.String r0 = r7.number
            if (r0 != 0) goto L_0x02d3
            goto L_0x02e5
        L_0x02d3:
            com.android.dialer.enrichedcall.EnrichedCallManager r1 = r16.getEnrichedCallManager()
            com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub r1 = (com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub) r1
            r1.getCapabilities(r0)
            com.android.dialer.enrichedcall.EnrichedCallManager r1 = r16.getEnrichedCallManager()
            com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub r1 = (com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub) r1
            r1.requestCapabilities(r0)
        L_0x02e5:
            r7.isCallComposerCapable = r8
            r7.setDetailedPhoneDetails(r2)
            com.android.dialer.app.calllog.CallLogAdapter$7 r9 = new com.android.dialer.app.calllog.CallLogAdapter$7
            r0 = r9
            r1 = r16
            r2 = r7
            r3 = r11
            r0.<init>(r2, r3, r4)
            r7.asyncTask = r9
            com.android.dialer.common.concurrent.AsyncTaskExecutor r0 = r6.asyncTaskExecutor
            java.lang.Void[] r1 = new java.lang.Void[r8]
            java.lang.String r2 = "load_data"
            r0.submit(r2, r9, r1)
        L_0x02ff:
            android.os.Trace.endSection()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.app.calllog.CallLogAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void");
    }

    /* access modifiers changed from: protected */
    public void onContentChanged() {
        this.callFetcher.fetchCalls();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return this.callLogAlertManager.createViewHolder(viewGroup);
        }
        CallLogListItemViewHolder create = CallLogListItemViewHolder.create(LayoutInflater.from(this.activity).inflate(R.layout.call_log_list_item, viewGroup, false), this.activity, this.blockReportSpamListener, this.expandCollapseListener, this.longPressListener, this.actionModeStateChangedListener, this.callLogCache, this.callLogListItemHelper, this.voicemailPlaybackPresenter);
        create.callLogEntryView.setTag(create);
        create.primaryActionView.setTag(create);
        create.quickContactView.setTag(create);
        return create;
    }

    public void onPause() {
        if (!this.contactsProviderMatchInfos.isEmpty()) {
            ((LoggingBindingsStub) Logger.get(this.activity)).logContactsProviderMetrics(this.contactsProviderMatchInfos.values());
        }
        ((DuoStub) DuoComponent.get(this.activity).getDuo()).unregisterListener(this);
        pauseCache();
        for (Uri deleteVoicemail : this.hiddenItemUris) {
            CallLogAsyncTaskUtil.deleteVoicemail(this.activity, deleteVoicemail, (CallLogAsyncTaskUtil.CallLogAsyncTaskListener) null);
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.currentlyExpandedPosition = bundle.getInt("expanded_position", -1);
            this.currentlyExpandedRowId = bundle.getLong("expanded_row_id", -1);
            ArrayList<String> stringArrayList = bundle.getStringArrayList("action_mode_selected_items");
            if (stringArrayList != null) {
                LogUtil.m9i("CallLogAdapter.onRestoreInstanceState", "restored selectedItemsList:%d", Integer.valueOf(stringArrayList.size()));
                if (!stringArrayList.isEmpty()) {
                    for (int i = 0; i < stringArrayList.size(); i++) {
                        String str = stringArrayList.get(i);
                        int voicemailId = getVoicemailId(str);
                        LogUtil.m9i("CallLogAdapter.onRestoreInstanceState", "restoring selected index %d, id=%d, uri=%s ", Integer.valueOf(i), Integer.valueOf(voicemailId), str);
                        this.selectedItems.put(voicemailId, str);
                    }
                    LogUtil.m9i("CallLogAdapter.onRestoreInstance", "restored selectedItems %s", this.selectedItems.toString());
                    updateActionBar();
                }
            }
        }
    }

    public void onResume() {
        this.contactsProviderMatchInfos.clear();
        if (PermissionsUtil.hasPermission(this.activity, "android.permission.READ_CONTACTS")) {
            this.contactInfoCache.start();
        }
        ((SpamSettingsStub) SpamComponent.get(this.activity).spamSettings()).isSpamEnabled();
        this.isSpamEnabled = false;
        ((DuoStub) DuoComponent.get(this.activity).getDuo()).registerListener(this);
        notifyDataSetChanged();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("expanded_position", this.currentlyExpandedPosition);
        bundle.putLong("expanded_row_id", this.currentlyExpandedRowId);
        ArrayList arrayList = new ArrayList();
        if (this.selectedItems.size() > 0) {
            for (int i = 0; i < this.selectedItems.size(); i++) {
                int keyAt = this.selectedItems.keyAt(i);
                String valueAt = this.selectedItems.valueAt(i);
                LogUtil.m9i("CallLogAdapter.onSaveInstanceState", "index %d, id=%d, uri=%s ", Integer.valueOf(i), Integer.valueOf(keyAt), valueAt);
                arrayList.add(valueAt);
            }
        }
        bundle.putStringArrayList("action_mode_selected_items", arrayList);
        LogUtil.m9i("CallLogAdapter.onSaveInstanceState", "saved: %d, selectedItemsSize:%d", Integer.valueOf(arrayList.size()), Integer.valueOf(this.selectedItems.size()));
    }

    public void onStop() {
        ((EnrichedCallManagerStub) getEnrichedCallManager()).clearCachedData();
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getItemViewType() == 2) {
            CallLogListItemViewHolder callLogListItemViewHolder = (CallLogListItemViewHolder) viewHolder;
        }
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getItemViewType() == 2) {
            CallLogListItemViewHolder callLogListItemViewHolder = (CallLogListItemViewHolder) viewHolder;
        }
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getItemViewType() == 2) {
            CallLogListItemViewHolder callLogListItemViewHolder = (CallLogListItemViewHolder) viewHolder;
            updateCheckMarkedStatusOfEntry(callLogListItemViewHolder);
            AsyncTask<Void, Void, ?> asyncTask = callLogListItemViewHolder.asyncTask;
            if (asyncTask != null) {
                asyncTask.cancel(true);
            }
        }
    }

    public void onVoicemailDeleteUndo(long j, int i, Uri uri) {
        this.hiddenItemUris.remove(uri);
        this.hiddenRowIds.remove(Long.valueOf(j));
        notifyItemChanged(i);
        notifyItemChanged(i + 1);
    }

    public void onVoicemailDeleted(CallLogListItemViewHolder callLogListItemViewHolder, Uri uri) {
        this.hiddenRowIds.add(Long.valueOf(callLogListItemViewHolder.rowId));
        this.hiddenItemUris.add(uri);
        this.currentlyExpandedRowId = -1;
        this.currentlyExpandedPosition = -1;
        notifyItemChanged(callLogListItemViewHolder.getAdapterPosition());
        notifyItemChanged(callLogListItemViewHolder.getAdapterPosition() + 1);
    }

    public void onVoicemailDeletedInDatabase(long j, Uri uri) {
        this.hiddenItemUris.remove(uri);
    }

    /* access modifiers changed from: package-private */
    public void pauseCache() {
        this.contactInfoCache.stop();
        this.callLogCache.reset();
    }

    public void setCallbackAction(long j, int i) {
        this.callbackActions.put(Long.valueOf(j), Integer.valueOf(i));
    }

    public void setDayGroup(long j, int i) {
        this.dayGroups.put(Long.valueOf(j), Integer.valueOf(i));
    }

    public void setLoading(boolean z) {
        this.loading = z;
    }
}
