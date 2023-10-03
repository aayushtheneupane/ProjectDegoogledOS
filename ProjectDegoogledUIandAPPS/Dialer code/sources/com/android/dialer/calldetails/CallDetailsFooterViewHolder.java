package com.android.dialer.calldetails;

import android.content.Context;
import android.content.Intent;
import android.support.p002v7.appcompat.R$style;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.calldetails.CallDetailsActivityCommon;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.logging.UiAction$Type;
import com.android.dialer.performancereport.PerformanceReport;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.DialerUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class CallDetailsFooterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final View copy;
    private final View delete;
    private final DeleteCallDetailsListener deleteCallDetailsListener;
    private final View edit;
    private String number;
    private final ReportCallIdListener reportCallIdListener;
    private final View reportCallerId;

    interface DeleteCallDetailsListener {
    }

    interface ReportCallIdListener {
    }

    CallDetailsFooterViewHolder(View view, ReportCallIdListener reportCallIdListener2, DeleteCallDetailsListener deleteCallDetailsListener2) {
        super(view);
        this.reportCallIdListener = reportCallIdListener2;
        this.deleteCallDetailsListener = deleteCallDetailsListener2;
        this.copy = view.findViewById(R.id.call_detail_action_copy);
        this.edit = view.findViewById(R.id.call_detail_action_edit_before_call);
        this.reportCallerId = view.findViewById(R.id.call_detail_action_report_caller_id);
        this.delete = view.findViewById(R.id.call_detail_action_delete);
        this.copy.setOnClickListener(this);
        this.edit.setOnClickListener(this);
        this.reportCallerId.setOnClickListener(this);
        this.delete.setOnClickListener(this);
    }

    public void onClick(View view) {
        Context context = view.getContext();
        if (view == this.copy) {
            PerformanceReport.recordClick(UiAction$Type.COPY_NUMBER_IN_CALL_DETAIL);
            ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.CALL_DETAILS_COPY_NUMBER);
            R$style.copyText(context, (CharSequence) null, this.number, true);
        } else if (view == this.edit) {
            PerformanceReport.recordClick(UiAction$Type.EDIT_NUMBER_BEFORE_CALL_IN_CALL_DETAIL);
            PerformanceReport.setIgnoreActionOnce(UiAction$Type.TEXT_CHANGE_WITH_INPUT);
            ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.CALL_DETAILS_EDIT_BEFORE_CALL);
            DialerUtils.startActivityWithErrorToast(context, new Intent("android.intent.action.DIAL", CallUtil.getCallUri(this.number)), R.string.activity_not_available);
        } else if (view == this.reportCallerId) {
            ((CallDetailsActivityCommon.ReportCallIdListener) this.reportCallIdListener).reportCallId(this.number);
        } else if (view == this.delete) {
            ((CallDetailsActivityCommon.DeleteCallDetailsListener) this.deleteCallDetailsListener).delete();
        } else {
            throw new UnsupportedOperationException(GeneratedOutlineSupport.outline6("View on click not implemented: ", view));
        }
    }

    public void setPhoneNumber(String str) {
        this.number = str;
        if (TextUtils.isEmpty(str)) {
            this.copy.setVisibility(8);
            this.edit.setVisibility(8);
        } else if (((CallDetailsActivityCommon.ReportCallIdListener) this.reportCallIdListener).canReportCallerId(str)) {
            this.reportCallerId.setVisibility(0);
        }
    }
}
