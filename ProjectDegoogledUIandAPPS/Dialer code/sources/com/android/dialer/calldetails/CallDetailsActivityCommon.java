package com.android.dialer.calldetails;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.Toolbar;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.assisteddialing.p003ui.AssistedDialingSettingActivity;
import com.android.dialer.calldetails.CallDetailsActivityCommon;
import com.android.dialer.calldetails.CallDetailsEntries;
import com.android.dialer.calldetails.CallDetailsEntryViewHolder;
import com.android.dialer.calldetails.CallDetailsFooterViewHolder;
import com.android.dialer.calldetails.CallDetailsHeaderViewHolder;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.callrecord.CallRecordingDataStore;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.common.concurrent.UiListener;
import com.android.dialer.common.database.Selection;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.EnrichedCallManager;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.glidephotomanager.PhotoInfo;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.UiAction$Type;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.postcall.PostCall;
import com.android.dialer.precall.PreCall;
import com.android.dialer.rtt.RttTranscriptActivity;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableSet;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;

abstract class CallDetailsActivityCommon extends AppCompatActivity {
    private CallDetailsAdapterCommon adapter;
    private CallDetailsEntries callDetailsEntries;
    private final CallDetailsEntryViewHolder.CallDetailsEntryListener callDetailsEntryListener = new CallDetailsEntryListener(this);
    private final CallDetailsHeaderViewHolder.CallDetailsHeaderListener callDetailsHeaderListener = new CallDetailsHeaderListener(this);
    private CallRecordingDataStore callRecordingDataStore;
    private UiListener<ImmutableSet<String>> checkRttTranscriptAvailabilityListener;
    private final CallDetailsFooterViewHolder.DeleteCallDetailsListener deleteCallDetailsListener = new DeleteCallDetailsListener(this);
    private final EnrichedCallManager.HistoricalDataChangedListener enrichedCallHistoricalDataChangedListener = new EnrichedCallHistoricalDataChangedListener(this);
    private final CallDetailsFooterViewHolder.ReportCallIdListener reportCallIdListener = new ReportCallIdListener(this);

    static final class AssistedDialingNumberParseWorker implements DialerExecutor.Worker<String, Integer> {
        AssistedDialingNumberParseWorker() {
        }

        public Object doInBackground(Object obj) throws Throwable {
            String str = (String) obj;
            try {
                return Integer.valueOf(PhoneNumberUtil.getInstance().parse(str, (String) null).getCountryCode());
            } catch (NumberParseException e) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("couldn't parse phone number: ");
                outline13.append(LogUtil.sanitizePii(str));
                LogUtil.m10w("AssistedDialingNumberParseWorker.doInBackground", outline13.toString(), e);
                return 0;
            }
        }
    }

    private static final class CallDetailsEntryListener implements CallDetailsEntryViewHolder.CallDetailsEntryListener {
        private final WeakReference<CallDetailsActivityCommon> activityWeakReference;

        CallDetailsEntryListener(CallDetailsActivityCommon callDetailsActivityCommon) {
            this.activityWeakReference = new WeakReference<>(callDetailsActivityCommon);
        }

        public void showRttTranscript(String str, String str2, PhotoInfo photoInfo) {
            CallDetailsActivityCommon callDetailsActivityCommon = (CallDetailsActivityCommon) this.activityWeakReference.get();
            MoreObjects.checkNotNull(callDetailsActivityCommon);
            CallDetailsActivityCommon callDetailsActivityCommon2 = (CallDetailsActivityCommon) this.activityWeakReference.get();
            MoreObjects.checkNotNull(callDetailsActivityCommon2);
            callDetailsActivityCommon.startActivity(RttTranscriptActivity.getIntent(callDetailsActivityCommon2, str, str2, photoInfo));
        }
    }

    private static final class CallDetailsHeaderListener implements CallDetailsHeaderViewHolder.CallDetailsHeaderListener {
        private final WeakReference<CallDetailsActivityCommon> activityWeakReference;

        CallDetailsHeaderListener(CallDetailsActivityCommon callDetailsActivityCommon) {
            this.activityWeakReference = new WeakReference<>(callDetailsActivityCommon);
        }

        private CallDetailsActivityCommon getActivity() {
            CallDetailsActivityCommon callDetailsActivityCommon = (CallDetailsActivityCommon) this.activityWeakReference.get();
            MoreObjects.checkNotNull(callDetailsActivityCommon);
            return callDetailsActivityCommon;
        }

        public void createAssistedDialerNumberParserTask(AssistedDialingNumberParseWorker assistedDialingNumberParseWorker, DialerExecutor.SuccessListener<Integer> successListener, DialerExecutor.FailureListener failureListener) {
            ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(getActivity().getApplicationContext()).dialerExecutorFactory()).createUiTaskBuilder(getActivity().getFragmentManager(), "CallDetailsActivityCommon.createAssistedDialerNumberParserTask", new AssistedDialingNumberParseWorker()).onSuccess(successListener).onFailure(failureListener).build().executeParallel(getActivity().getNumber());
        }

        public void openAssistedDialingSettings(View view) {
            getActivity().startActivity(new Intent(getActivity(), AssistedDialingSettingActivity.class));
        }

        public void placeDuoVideoCall(String str) {
            ((LoggingBindingsStub) Logger.get(getActivity())).logImpression(DialerImpression$Type.CALL_DETAILS_LIGHTBRINGER_CALL_BACK);
            PreCall.start(getActivity(), new CallIntentBuilder(str, CallInitiationType$Type.CALL_DETAILS).setIsDuoCall(true).setIsVideoCall(true));
        }

        public void placeImsVideoCall(String str) {
            ((LoggingBindingsStub) Logger.get(getActivity())).logImpression(DialerImpression$Type.CALL_DETAILS_IMS_VIDEO_CALL_BACK);
            PreCall.start(getActivity(), new CallIntentBuilder(str, CallInitiationType$Type.CALL_DETAILS).setIsVideoCall(true));
        }

        public void placeVoiceCall(String str, String str2) {
            ((LoggingBindingsStub) Logger.get(getActivity())).logImpression(DialerImpression$Type.CALL_DETAILS_VOICE_CALL_BACK);
            boolean z = getActivity().getIntent().getExtras().getBoolean("can_support_assisted_dialing", false);
            CallIntentBuilder callIntentBuilder = new CallIntentBuilder(GeneratedOutlineSupport.outline8(str, str2), CallInitiationType$Type.CALL_DETAILS);
            if (z) {
                callIntentBuilder.setAllowAssistedDial(true);
            }
            PreCall.start(getActivity(), callIntentBuilder);
        }
    }

    private static final class DeleteCallDetailsListener implements CallDetailsFooterViewHolder.DeleteCallDetailsListener {
        private final WeakReference<CallDetailsActivityCommon> activityWeakReference;

        DeleteCallDetailsListener(CallDetailsActivityCommon callDetailsActivityCommon) {
            this.activityWeakReference = new WeakReference<>(callDetailsActivityCommon);
        }

        static /* synthetic */ void lambda$delete$0(CallDetailsActivityCommon callDetailsActivityCommon, Void voidR) {
            Intent intent = new Intent();
            intent.putExtra("phone_number", callDetailsActivityCommon.getNumber());
            Iterator<CallDetailsEntries.CallDetailsEntry> it = callDetailsActivityCommon.getCallDetailsEntries().getEntriesList().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getHistoryResultsCount() > 0) {
                        intent.putExtra("has_enriched_call_data", true);
                        break;
                    }
                } else {
                    break;
                }
            }
            callDetailsActivityCommon.setResult(-1, intent);
            callDetailsActivityCommon.finish();
        }

        public void delete() {
            CallDetailsActivityCommon callDetailsActivityCommon = (CallDetailsActivityCommon) this.activityWeakReference.get();
            MoreObjects.checkNotNull(callDetailsActivityCommon);
            ((LoggingBindingsStub) Logger.get(callDetailsActivityCommon)).logImpression(DialerImpression$Type.USER_DELETED_CALL_LOG_ITEM);
            ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(callDetailsActivityCommon).dialerExecutorFactory()).createNonUiTaskBuilder(new DeleteCallsWorker(callDetailsActivityCommon)).onSuccess(new DialerExecutor.SuccessListener() {
                public final void onSuccess(Object obj) {
                    CallDetailsActivityCommon.DeleteCallDetailsListener.lambda$delete$0(CallDetailsActivityCommon.this, (Void) obj);
                }
            }).build().executeSerial(callDetailsActivityCommon.getCallDetailsEntries());
        }
    }

    private static final class DeleteCallsWorker implements DialerExecutor.Worker<CallDetailsEntries, Void> {
        private final WeakReference<Context> contextWeakReference;

        DeleteCallsWorker(Context context) {
            this.contextWeakReference = new WeakReference<>(context);
        }

        @SuppressLint({"MissingPermission"})
        public Object doInBackground(Object obj) throws Throwable {
            CallDetailsEntries callDetailsEntries = (CallDetailsEntries) obj;
            Context context = (Context) this.contextWeakReference.get();
            if (context == null) {
                return null;
            }
            Selection.Builder builder = Selection.builder();
            Selection.Column column = Selection.column("_id");
            Assert.checkArgument(callDetailsEntries.getEntriesCount() > 0);
            ArrayList arrayList = new ArrayList(callDetailsEntries.getEntriesCount());
            for (CallDetailsEntries.CallDetailsEntry callId : callDetailsEntries.getEntriesList()) {
                arrayList.add(String.valueOf(callId.getCallId()));
            }
            builder.and(column.mo5865in(arrayList));
            Selection build = builder.build();
            context.getContentResolver().delete(CallLog.Calls.CONTENT_URI, build.getSelection(), build.getSelectionArgs());
            return null;
        }
    }

    private static final class EnrichedCallHistoricalDataChangedListener implements EnrichedCallManager.HistoricalDataChangedListener {
        EnrichedCallHistoricalDataChangedListener(CallDetailsActivityCommon callDetailsActivityCommon) {
            new WeakReference(callDetailsActivityCommon);
        }
    }

    private static final class ReportCallIdListener implements CallDetailsFooterViewHolder.ReportCallIdListener {
        private final WeakReference<Activity> activityWeakReference;

        ReportCallIdListener(Activity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        public boolean canReportCallerId(String str) {
            Activity activity = (Activity) this.activityWeakReference.get();
            MoreObjects.checkNotNull(activity);
            return activity.getIntent().getExtras().getBoolean("can_report_caller_id", false);
        }

        public void reportCallId(String str) {
            ReportDialogFragment reportDialogFragment = new ReportDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("number", str);
            reportDialogFragment.setArguments(bundle);
            Activity activity = (Activity) this.activityWeakReference.get();
            MoreObjects.checkNotNull(activity);
            reportDialogFragment.show(activity.getFragmentManager(), (String) null);
        }
    }

    CallDetailsActivityCommon() {
    }

    static /* synthetic */ void lambda$loadRttTranscriptAvailability$1(Throwable th) {
        throw new RuntimeException(th);
    }

    private void setupRecyclerViewForEntries() {
        this.adapter = createAdapter(this.callDetailsEntryListener, this.callDetailsHeaderListener, this.reportCallIdListener, this.deleteCallDetailsListener, this.callRecordingDataStore);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        recyclerView.setAdapter(this.adapter);
        PerformanceReport.logOnScrollStateChange(recyclerView);
    }

    /* access modifiers changed from: private */
    public void updateCallDetailsEntriesWithRttTranscriptAvailability(ImmutableSet<String> immutableSet) {
        CallDetailsEntries.Builder newBuilder = CallDetailsEntries.newBuilder();
        for (CallDetailsEntries.CallDetailsEntry next : this.callDetailsEntries.getEntriesList()) {
            CallDetailsEntries.CallDetailsEntry.Builder newBuilder2 = CallDetailsEntries.CallDetailsEntry.newBuilder();
            newBuilder2.mergeFrom(next);
            CallDetailsEntries.CallDetailsEntry.Builder builder = newBuilder2;
            builder.setHasRttTranscript(immutableSet.contains(next.getCallMappingId()));
            newBuilder.addEntries((CallDetailsEntries.CallDetailsEntry) builder.build());
        }
        setCallDetailsEntries((CallDetailsEntries) newBuilder.build());
    }

    /* access modifiers changed from: protected */
    public abstract CallDetailsAdapterCommon createAdapter(CallDetailsEntryViewHolder.CallDetailsEntryListener callDetailsEntryListener2, CallDetailsHeaderViewHolder.CallDetailsHeaderListener callDetailsHeaderListener2, CallDetailsFooterViewHolder.ReportCallIdListener reportCallIdListener2, CallDetailsFooterViewHolder.DeleteCallDetailsListener deleteCallDetailsListener2, CallRecordingDataStore callRecordingDataStore2);

    /* access modifiers changed from: protected */
    public final CallDetailsEntries getCallDetailsEntries() {
        return this.callDetailsEntries;
    }

    /* access modifiers changed from: protected */
    public abstract String getNumber();

    /* access modifiers changed from: protected */
    public abstract void handleIntent(Intent intent);

    public /* synthetic */ void lambda$onCreate$0$CallDetailsActivityCommon(View view) {
        PerformanceReport.recordClick(UiAction$Type.CLOSE_CALL_DETAIL_WITH_CANCEL_BUTTON);
        finish();
    }

    /* access modifiers changed from: protected */
    public void loadRttTranscriptAvailability() {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (CallDetailsEntries.CallDetailsEntry callMappingId : this.callDetailsEntries.getEntriesList()) {
            builder.add((Object) callMappingId.getCallMappingId());
        }
        this.checkRttTranscriptAvailabilityListener.listen(this, DialerExecutorComponent.get(this).backgroundExecutor().submit(new Callable(this, builder.build()) {
            private final /* synthetic */ Context f$0;
            private final /* synthetic */ ImmutableSet f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final Object call() {
                return RttTranscriptUtil.lambda$getAvailableRttTranscriptIds$0(this.f$0, this.f$1);
            }
        }), new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                CallDetailsActivityCommon.this.updateCallDetailsEntriesWithRttTranscriptAvailability((ImmutableSet) obj);
            }
        }, $$Lambda$CallDetailsActivityCommon$Ok3ZY67TqreQh8qZVoE_auhb8p4.INSTANCE);
    }

    public void onBackPressed() {
        PerformanceReport.recordClick(UiAction$Type.PRESS_ANDROID_BACK_BUTTON);
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((AospThemeImpl) ThemeComponent.get(this).theme()).getTheme();
        setTheme(2131886327);
        setContentView((int) R.layout.call_details_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle((int) R.string.call_details);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CallDetailsActivityCommon.this.lambda$onCreate$0$CallDetailsActivityCommon(view);
            }
        });
        this.checkRttTranscriptAvailabilityListener = DialerExecutorComponent.get(this).createUiListener(getFragmentManager(), "Query RTT transcript availability");
        this.callRecordingDataStore = new CallRecordingDataStore();
        handleIntent(getIntent());
        setupRecyclerViewForEntries();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.callRecordingDataStore.close();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
        setupRecyclerViewForEntries();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        ((EnrichedCallManagerStub) EnrichedCallComponent.get(this).getEnrichedCallManager()).unregisterHistoricalDataChangedListener(this.enrichedCallHistoricalDataChangedListener);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        PostCall.restartPerformanceRecordingIfARecentCallExist(this);
        if (!PerformanceReport.isRecording()) {
            PerformanceReport.startRecording();
        }
        PostCall.promptUserForMessageIfNecessary(this, findViewById(R.id.recycler_view));
        ((EnrichedCallManagerStub) EnrichedCallComponent.get(this).getEnrichedCallManager()).registerHistoricalDataChangedListener(this.enrichedCallHistoricalDataChangedListener);
        ((EnrichedCallManagerStub) EnrichedCallComponent.get(this).getEnrichedCallManager()).requestAllHistoricalData(getNumber(), this.callDetailsEntries);
    }

    /* access modifiers changed from: protected */
    public final void setCallDetailsEntries(CallDetailsEntries callDetailsEntries2) {
        Assert.isMainThread();
        this.callDetailsEntries = callDetailsEntries2;
        CallDetailsAdapterCommon callDetailsAdapterCommon = this.adapter;
        if (callDetailsAdapterCommon != null) {
            callDetailsAdapterCommon.updateCallDetailsEntries(callDetailsEntries2);
        }
    }
}
