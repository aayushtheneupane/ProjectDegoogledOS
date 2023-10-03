package com.android.dialer.calldetails;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.android.dialer.CoalescedIds;
import com.android.dialer.calldetails.CallDetailsEntryViewHolder;
import com.android.dialer.calldetails.CallDetailsFooterViewHolder;
import com.android.dialer.calldetails.CallDetailsHeaderViewHolder;
import com.android.dialer.callrecord.CallRecordingDataStore;
import com.android.dialer.common.Assert;
import com.android.dialer.enrichedcall.EnrichedCallComponent;
import com.android.dialer.enrichedcall.stub.EnrichedCallManagerStub;
import com.android.dialer.protos.ProtoParsers;

public final class CallDetailsActivity extends CallDetailsActivityCommon {
    /* access modifiers changed from: private */
    public CoalescedIds coalescedCallLogIds;
    private CallDetailsHeaderInfo headerInfo;

    private static final class CallDetailsLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {
        private final CallDetailsActivity activity;

        CallDetailsLoaderCallbacks(CallDetailsActivity callDetailsActivity) {
            this.activity = callDetailsActivity;
        }

        private void updateCallDetailsEntries(CallDetailsEntries callDetailsEntries) {
            this.activity.setCallDetailsEntries(callDetailsEntries);
            ((EnrichedCallManagerStub) EnrichedCallComponent.get(this.activity).getEnrichedCallManager()).requestAllHistoricalData(this.activity.getNumber(), callDetailsEntries);
        }

        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            CallDetailsActivity callDetailsActivity = this.activity;
            CoalescedIds access$000 = callDetailsActivity.coalescedCallLogIds;
            Assert.isNotNull(access$000);
            return new CallDetailsCursorLoader(callDetailsActivity, access$000);
        }

        public void onLoadFinished(Loader loader, Object obj) {
            updateCallDetailsEntries(CallDetailsCursorLoader.toCallDetailsEntries(this.activity, (Cursor) obj));
            this.activity.loadRttTranscriptAvailability();
        }

        public void onLoaderReset(Loader<Cursor> loader) {
            updateCallDetailsEntries(CallDetailsEntries.getDefaultInstance());
        }
    }

    public static Intent newInstance(Context context, CoalescedIds coalescedIds, CallDetailsHeaderInfo callDetailsHeaderInfo, boolean z, boolean z2) {
        Intent intent = new Intent(context, CallDetailsActivity.class);
        Assert.isNotNull(coalescedIds);
        ProtoParsers.put(intent, "coalesced_call_log_ids", coalescedIds);
        Assert.isNotNull(callDetailsHeaderInfo);
        ProtoParsers.put(intent, "header_info", callDetailsHeaderInfo);
        intent.putExtra("can_report_caller_id", z);
        intent.putExtra("can_support_assisted_dialing", z2);
        return intent;
    }

    /* access modifiers changed from: protected */
    public CallDetailsAdapterCommon createAdapter(CallDetailsEntryViewHolder.CallDetailsEntryListener callDetailsEntryListener, CallDetailsHeaderViewHolder.CallDetailsHeaderListener callDetailsHeaderListener, CallDetailsFooterViewHolder.ReportCallIdListener reportCallIdListener, CallDetailsFooterViewHolder.DeleteCallDetailsListener deleteCallDetailsListener, CallRecordingDataStore callRecordingDataStore) {
        return new CallDetailsAdapter(this, this.headerInfo, getCallDetailsEntries(), callDetailsEntryListener, callDetailsHeaderListener, reportCallIdListener, deleteCallDetailsListener, callRecordingDataStore);
    }

    /* access modifiers changed from: protected */
    public String getNumber() {
        return this.headerInfo.getDialerPhoneNumber().getNormalizedNumber();
    }

    /* access modifiers changed from: protected */
    public void handleIntent(Intent intent) {
        Assert.checkArgument(intent.hasExtra("coalesced_call_log_ids"));
        Assert.checkArgument(intent.hasExtra("header_info"));
        Assert.checkArgument(intent.hasExtra("can_report_caller_id"));
        Assert.checkArgument(intent.hasExtra("can_support_assisted_dialing"));
        setCallDetailsEntries(CallDetailsEntries.getDefaultInstance());
        this.coalescedCallLogIds = (CoalescedIds) ProtoParsers.getTrusted(intent, "coalesced_call_log_ids", CoalescedIds.getDefaultInstance());
        this.headerInfo = (CallDetailsHeaderInfo) ProtoParsers.getTrusted(intent, "header_info", CallDetailsHeaderInfo.getDefaultInstance());
        getLoaderManager().initLoader(0, (Bundle) null, new CallDetailsLoaderCallbacks(this));
    }

    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }
}
