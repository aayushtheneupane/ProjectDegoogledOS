package com.android.dialer.calldetails;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.calldetails.CallDetailsEntries;
import com.android.dialer.calldetails.CallDetailsEntryViewHolder;
import com.android.dialer.calldetails.CallDetailsFooterViewHolder;
import com.android.dialer.calldetails.CallDetailsHeaderViewHolder;
import com.android.dialer.calllogutils.CallLogDates;
import com.android.dialer.calllogutils.CallTypeHelper;
import com.android.dialer.callrecord.CallRecordingDataStore;
import com.android.dialer.common.Assert;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.glidephotomanager.PhotoInfo;
import com.android.tools.p006r8.GeneratedOutlineSupport;

abstract class CallDetailsAdapterCommon extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CallDetailsEntries callDetailsEntries;
    private final CallDetailsEntryViewHolder.CallDetailsEntryListener callDetailsEntryListener;
    private final CallDetailsHeaderViewHolder.CallDetailsHeaderListener callDetailsHeaderListener;
    private final CallRecordingDataStore callRecordingDataStore;
    private final CallTypeHelper callTypeHelper;
    private final CallDetailsFooterViewHolder.DeleteCallDetailsListener deleteCallDetailsListener;
    private final CallDetailsFooterViewHolder.ReportCallIdListener reportCallIdListener;

    CallDetailsAdapterCommon(Context context, CallDetailsEntries callDetailsEntries2, CallDetailsEntryViewHolder.CallDetailsEntryListener callDetailsEntryListener2, CallDetailsHeaderViewHolder.CallDetailsHeaderListener callDetailsHeaderListener2, CallDetailsFooterViewHolder.ReportCallIdListener reportCallIdListener2, CallDetailsFooterViewHolder.DeleteCallDetailsListener deleteCallDetailsListener2, CallRecordingDataStore callRecordingDataStore2) {
        this.callDetailsEntries = callDetailsEntries2;
        this.callDetailsEntryListener = callDetailsEntryListener2;
        this.callDetailsHeaderListener = callDetailsHeaderListener2;
        this.reportCallIdListener = reportCallIdListener2;
        this.deleteCallDetailsListener = deleteCallDetailsListener2;
        this.callRecordingDataStore = callRecordingDataStore2;
        this.callTypeHelper = new CallTypeHelper(context.getResources(), DuoComponent.get(context).getDuo());
    }

    /* access modifiers changed from: protected */
    public abstract void bindCallDetailsHeaderViewHolder(CallDetailsHeaderViewHolder callDetailsHeaderViewHolder, int i);

    /* access modifiers changed from: protected */
    public abstract CallDetailsHeaderViewHolder createCallDetailsHeaderViewHolder(View view, CallDetailsHeaderViewHolder.CallDetailsHeaderListener callDetailsHeaderListener2);

    /* access modifiers changed from: package-private */
    public final CallDetailsEntries getCallDetailsEntries() {
        return this.callDetailsEntries;
    }

    /* access modifiers changed from: package-private */
    public final int getCallbackAction() {
        Assert.checkState(!this.callDetailsEntries.getEntriesList().isEmpty());
        CallDetailsEntries.CallDetailsEntry entries = this.callDetailsEntries.getEntries(0);
        return CallLogDates.getCallbackAction(getNumber(), entries.getFeatures(), entries.getIsDuoCall());
    }

    public int getItemCount() {
        if (this.callDetailsEntries.getEntriesCount() == 0) {
            return 0;
        }
        return this.callDetailsEntries.getEntriesCount() + 2;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 1;
        }
        return i == getItemCount() - 1 ? 3 : 2;
    }

    /* access modifiers changed from: protected */
    public abstract String getNumber();

    /* access modifiers changed from: protected */
    public abstract PhotoInfo getPhotoInfo();

    /* access modifiers changed from: protected */
    public abstract String getPrimaryText();

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (i == 0) {
            bindCallDetailsHeaderViewHolder((CallDetailsHeaderViewHolder) viewHolder, i);
            return;
        }
        boolean z = true;
        if (i == getItemCount() - 1) {
            ((CallDetailsFooterViewHolder) viewHolder).setPhoneNumber(getNumber());
            return;
        }
        CallDetailsEntryViewHolder callDetailsEntryViewHolder = (CallDetailsEntryViewHolder) viewHolder;
        CallDetailsEntries.CallDetailsEntry entries = this.callDetailsEntries.getEntries(i - 1);
        String number = getNumber();
        String primaryText = getPrimaryText();
        PhotoInfo photoInfo = getPhotoInfo();
        CallTypeHelper callTypeHelper2 = this.callTypeHelper;
        CallRecordingDataStore callRecordingDataStore2 = this.callRecordingDataStore;
        if (entries.getHistoryResultsList().isEmpty() || i == getItemCount() - 2) {
            z = false;
        }
        callDetailsEntryViewHolder.setCallDetails(number, primaryText, photoInfo, entries, callTypeHelper2, callRecordingDataStore2, z);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            return createCallDetailsHeaderViewHolder(from.inflate(R.layout.contact_container, viewGroup, false), this.callDetailsHeaderListener);
        }
        if (i == 2) {
            return new CallDetailsEntryViewHolder(from.inflate(R.layout.call_details_entry, viewGroup, false), this.callDetailsEntryListener);
        }
        if (i == 3) {
            return new CallDetailsFooterViewHolder(from.inflate(R.layout.call_details_footer, viewGroup, false), this.reportCallIdListener, this.deleteCallDetailsListener);
        }
        throw new IllegalStateException(GeneratedOutlineSupport.outline5("No ViewHolder available for viewType: ", i));
    }

    /* access modifiers changed from: package-private */
    public final void updateCallDetailsEntries(CallDetailsEntries callDetailsEntries2) {
        Assert.isMainThread();
        this.callDetailsEntries = callDetailsEntries2;
        notifyDataSetChanged();
    }
}
