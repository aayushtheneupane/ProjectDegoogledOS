package com.android.dialer.app.calllog;

import android.view.View;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.calllogutils.CallTypeIconsView;
import com.android.dialer.widget.BidiTextView;

public final class PhoneCallDetailsViews {
    public final TextView callAccountLabel;
    public final TextView callLocationAndDate;
    public final CallTypeIconsView callTypeIcons;
    public final BidiTextView nameView;
    public final View transcriptionView;
    public final TextView voicemailTranscriptionBrandingView;
    public final View voicemailTranscriptionRatingView;
    public final TextView voicemailTranscriptionView;

    private PhoneCallDetailsViews(BidiTextView bidiTextView, View view, CallTypeIconsView callTypeIconsView, TextView textView, View view2, TextView textView2, TextView textView3, View view3, TextView textView4) {
        this.nameView = bidiTextView;
        this.callTypeIcons = callTypeIconsView;
        this.callLocationAndDate = textView;
        this.transcriptionView = view2;
        this.voicemailTranscriptionView = textView2;
        this.voicemailTranscriptionBrandingView = textView3;
        this.voicemailTranscriptionRatingView = view3;
        this.callAccountLabel = textView4;
    }

    public static PhoneCallDetailsViews fromView(View view) {
        return new PhoneCallDetailsViews((BidiTextView) view.findViewById(R.id.name), view.findViewById(R.id.call_type), (CallTypeIconsView) view.findViewById(R.id.call_type_icons), (TextView) view.findViewById(R.id.call_location_and_date), view.findViewById(R.id.transcription), (TextView) view.findViewById(R.id.voicemail_transcription), (TextView) view.findViewById(R.id.voicemail_transcription_branding), view.findViewById(R.id.voicemail_transcription_rating), (TextView) view.findViewById(R.id.call_account_label));
    }
}
