package com.android.dialer.rtt;

import android.content.res.Resources;
import android.support.p002v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.glidephotomanager.GlidePhotoManagerComponent;
import com.android.dialer.glidephotomanager.PhotoInfo;
import com.android.dialer.glidephotomanager.impl.GlidePhotoManagerImpl;

public class RttTranscriptMessageViewHolder extends RecyclerView.ViewHolder {
    private final ImageView avatarImageView;
    private final View container;
    private final TextView messageTextView;
    private final Resources resources;
    private final TextView timestampTextView;

    RttTranscriptMessageViewHolder(View view) {
        super(view);
        this.container = view.findViewById(R.id.rtt_chat_message_container);
        this.messageTextView = (TextView) view.findViewById(R.id.rtt_chat_message);
        this.avatarImageView = (ImageView) view.findViewById(R.id.rtt_chat_avatar);
        this.timestampTextView = (TextView) view.findViewById(R.id.rtt_chat_timestamp);
        this.resources = view.getResources();
    }

    /* access modifiers changed from: package-private */
    public void hideTimestamp() {
        this.timestampTextView.setVisibility(8);
    }

    /* access modifiers changed from: package-private */
    public void setMessage(RttTranscriptMessage rttTranscriptMessage, boolean z, PhotoInfo photoInfo) {
        int i;
        this.messageTextView.setText(rttTranscriptMessage.getContent());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.container.getLayoutParams();
        layoutParams.gravity = rttTranscriptMessage.getIsRemote() ? 8388611 : 8388613;
        if (z) {
            i = this.resources.getDimensionPixelSize(R.dimen.rtt_transcript_same_group_message_margin_top);
        } else {
            i = this.resources.getDimensionPixelSize(R.dimen.rtt_transcript_message_margin_top);
        }
        layoutParams.topMargin = i;
        this.container.setLayoutParams(layoutParams);
        this.messageTextView.setEnabled(rttTranscriptMessage.getIsRemote());
        if (rttTranscriptMessage.getIsRemote()) {
            if (z) {
                this.avatarImageView.setVisibility(4);
            } else {
                this.avatarImageView.setVisibility(0);
                ((GlidePhotoManagerImpl) GlidePhotoManagerComponent.get(this.container.getContext()).glidePhotoManager()).loadContactPhoto(this.avatarImageView, photoInfo);
            }
            this.messageTextView.setTextAppearance(2131886410);
            return;
        }
        this.avatarImageView.setVisibility(8);
        this.messageTextView.setTextAppearance(2131886409);
    }

    /* access modifiers changed from: package-private */
    public void showTimestamp(long j, boolean z, boolean z2) {
        this.timestampTextView.setVisibility(0);
        TextView textView = this.timestampTextView;
        textView.setText(DateUtils.formatDateTime(textView.getContext(), j, z2 ? 17 : 1));
        this.timestampTextView.setGravity(z ? 8388611 : 8388613);
    }
}
