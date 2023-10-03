package com.android.dialer.calldetails;

import android.content.Context;
import android.view.View;
import com.android.dialer.calldetails.CallDetailsEntryViewHolder;
import com.android.dialer.calldetails.CallDetailsFooterViewHolder;
import com.android.dialer.calldetails.CallDetailsHeaderViewHolder;
import com.android.dialer.callrecord.CallRecordingDataStore;
import com.android.dialer.dialercontact.DialerContact;
import com.android.dialer.glidephotomanager.PhotoInfo;

final class OldCallDetailsAdapter extends CallDetailsAdapterCommon {
    private final DialerContact contact;

    OldCallDetailsAdapter(Context context, DialerContact dialerContact, CallDetailsEntries callDetailsEntries, CallDetailsEntryViewHolder.CallDetailsEntryListener callDetailsEntryListener, CallDetailsHeaderViewHolder.CallDetailsHeaderListener callDetailsHeaderListener, CallDetailsFooterViewHolder.ReportCallIdListener reportCallIdListener, CallDetailsFooterViewHolder.DeleteCallDetailsListener deleteCallDetailsListener, CallRecordingDataStore callRecordingDataStore) {
        super(context, callDetailsEntries, callDetailsEntryListener, callDetailsHeaderListener, reportCallIdListener, deleteCallDetailsListener, callRecordingDataStore);
        this.contact = dialerContact;
    }

    /* access modifiers changed from: protected */
    public void bindCallDetailsHeaderViewHolder(CallDetailsHeaderViewHolder callDetailsHeaderViewHolder, int i) {
        callDetailsHeaderViewHolder.updateContactInfo(this.contact, getCallbackAction());
        callDetailsHeaderViewHolder.updateAssistedDialingInfo(getCallDetailsEntries().getEntries(i));
    }

    /* access modifiers changed from: protected */
    public CallDetailsHeaderViewHolder createCallDetailsHeaderViewHolder(View view, CallDetailsHeaderViewHolder.CallDetailsHeaderListener callDetailsHeaderListener) {
        return new CallDetailsHeaderViewHolder(view, this.contact.getNumber(), this.contact.getPostDialDigits(), callDetailsHeaderListener);
    }

    /* access modifiers changed from: protected */
    public String getNumber() {
        return this.contact.getNumber();
    }

    /* access modifiers changed from: protected */
    public PhotoInfo getPhotoInfo() {
        PhotoInfo.Builder newBuilder = PhotoInfo.newBuilder();
        newBuilder.setPhotoUri(this.contact.getPhotoUri());
        newBuilder.setPhotoId(this.contact.getPhotoId());
        newBuilder.setName(this.contact.getNameOrNumber());
        newBuilder.setLookupUri(this.contact.getContactUri());
        int contactType = this.contact.getContactType();
        if (contactType == 2) {
            newBuilder.setIsBusiness(true);
        } else if (contactType == 3) {
            newBuilder.setIsVoicemail(true);
        } else if (contactType == 5) {
            newBuilder.setIsSpam(true);
        }
        return (PhotoInfo) newBuilder.build();
    }

    /* access modifiers changed from: protected */
    public String getPrimaryText() {
        return this.contact.getNameOrNumber();
    }
}
