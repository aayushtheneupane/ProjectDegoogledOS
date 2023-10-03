package com.android.dialer.calldetails;

import android.content.Context;
import android.view.View;
import com.android.dialer.calldetails.CallDetailsEntryViewHolder;
import com.android.dialer.calldetails.CallDetailsFooterViewHolder;
import com.android.dialer.calldetails.CallDetailsHeaderViewHolder;
import com.android.dialer.callrecord.CallRecordingDataStore;
import com.android.dialer.glidephotomanager.PhotoInfo;

final class CallDetailsAdapter extends CallDetailsAdapterCommon {
    private final CallDetailsHeaderInfo headerInfo;

    CallDetailsAdapter(Context context, CallDetailsHeaderInfo callDetailsHeaderInfo, CallDetailsEntries callDetailsEntries, CallDetailsEntryViewHolder.CallDetailsEntryListener callDetailsEntryListener, CallDetailsHeaderViewHolder.CallDetailsHeaderListener callDetailsHeaderListener, CallDetailsFooterViewHolder.ReportCallIdListener reportCallIdListener, CallDetailsFooterViewHolder.DeleteCallDetailsListener deleteCallDetailsListener, CallRecordingDataStore callRecordingDataStore) {
        super(context, callDetailsEntries, callDetailsEntryListener, callDetailsHeaderListener, reportCallIdListener, deleteCallDetailsListener, callRecordingDataStore);
        this.headerInfo = callDetailsHeaderInfo;
    }

    /* access modifiers changed from: protected */
    public void bindCallDetailsHeaderViewHolder(CallDetailsHeaderViewHolder callDetailsHeaderViewHolder, int i) {
        callDetailsHeaderViewHolder.updateContactInfo(this.headerInfo, getCallbackAction());
        callDetailsHeaderViewHolder.updateAssistedDialingInfo(getCallDetailsEntries().getEntries(i));
    }

    /* access modifiers changed from: protected */
    public CallDetailsHeaderViewHolder createCallDetailsHeaderViewHolder(View view, CallDetailsHeaderViewHolder.CallDetailsHeaderListener callDetailsHeaderListener) {
        return new CallDetailsHeaderViewHolder(view, this.headerInfo.getDialerPhoneNumber().getNormalizedNumber(), this.headerInfo.getDialerPhoneNumber().getPostDialPortion(), callDetailsHeaderListener);
    }

    /* access modifiers changed from: protected */
    public String getNumber() {
        return this.headerInfo.getDialerPhoneNumber().getNormalizedNumber();
    }

    /* access modifiers changed from: protected */
    public PhotoInfo getPhotoInfo() {
        return this.headerInfo.getPhotoInfo();
    }

    /* access modifiers changed from: protected */
    public String getPrimaryText() {
        return this.headerInfo.getPrimaryText();
    }
}
