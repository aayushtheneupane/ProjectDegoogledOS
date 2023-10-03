package com.android.dialer.calldetails;

import android.content.Context;
import android.content.Intent;
import com.android.dialer.calldetails.CallDetailsEntryViewHolder;
import com.android.dialer.calldetails.CallDetailsFooterViewHolder;
import com.android.dialer.calldetails.CallDetailsHeaderViewHolder;
import com.android.dialer.callrecord.CallRecordingDataStore;
import com.android.dialer.common.Assert;
import com.android.dialer.dialercontact.DialerContact;
import com.android.dialer.protos.ProtoParsers;

public final class OldCallDetailsActivity extends CallDetailsActivityCommon {
    private DialerContact contact;

    public static boolean isLaunchIntent(Intent intent) {
        return intent.getComponent() != null && OldCallDetailsActivity.class.getName().equals(intent.getComponent().getClassName());
    }

    public static Intent newInstance(Context context, CallDetailsEntries callDetailsEntries, DialerContact dialerContact, boolean z, boolean z2) {
        Intent intent = new Intent(context, OldCallDetailsActivity.class);
        Assert.isNotNull(dialerContact);
        ProtoParsers.put(intent, "contact", dialerContact);
        Assert.isNotNull(callDetailsEntries);
        ProtoParsers.put(intent, "call_details_entries", callDetailsEntries);
        intent.putExtra("can_report_caller_id", z);
        intent.putExtra("can_support_assisted_dialing", z2);
        return intent;
    }

    /* access modifiers changed from: protected */
    public CallDetailsAdapterCommon createAdapter(CallDetailsEntryViewHolder.CallDetailsEntryListener callDetailsEntryListener, CallDetailsHeaderViewHolder.CallDetailsHeaderListener callDetailsHeaderListener, CallDetailsFooterViewHolder.ReportCallIdListener reportCallIdListener, CallDetailsFooterViewHolder.DeleteCallDetailsListener deleteCallDetailsListener, CallRecordingDataStore callRecordingDataStore) {
        return new OldCallDetailsAdapter(this, this.contact, getCallDetailsEntries(), callDetailsEntryListener, callDetailsHeaderListener, reportCallIdListener, deleteCallDetailsListener, callRecordingDataStore);
    }

    /* access modifiers changed from: protected */
    public String getNumber() {
        return this.contact.getNumber();
    }

    /* access modifiers changed from: protected */
    public void handleIntent(Intent intent) {
        Assert.checkArgument(intent.hasExtra("contact"));
        Assert.checkArgument(intent.hasExtra("call_details_entries"));
        Assert.checkArgument(intent.hasExtra("can_report_caller_id"));
        Assert.checkArgument(intent.hasExtra("can_support_assisted_dialing"));
        this.contact = (DialerContact) ProtoParsers.getTrusted(intent, "contact", DialerContact.getDefaultInstance());
        setCallDetailsEntries((CallDetailsEntries) ProtoParsers.getTrusted(intent, "call_details_entries", CallDetailsEntries.getDefaultInstance()));
        loadRttTranscriptAvailability();
    }

    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }
}
