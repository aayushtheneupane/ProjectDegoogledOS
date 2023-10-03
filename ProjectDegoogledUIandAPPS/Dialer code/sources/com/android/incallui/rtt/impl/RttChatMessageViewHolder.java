package com.android.incallui.rtt.impl;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.incallui.rtt.protocol.RttChatMessage;

public class RttChatMessageViewHolder extends RecyclerView.ViewHolder {
    private final ImageView avatarImageView;
    private final View container;
    private final TextView messageTextView;
    private final Resources resources;

    RttChatMessageViewHolder(View view) {
        super(view);
        this.container = view.findViewById(R.id.rtt_chat_message_container);
        this.messageTextView = (TextView) view.findViewById(R.id.rtt_chat_message);
        this.avatarImageView = (ImageView) view.findViewById(R.id.rtt_chat_avatar);
        this.resources = view.getResources();
    }

    /* access modifiers changed from: package-private */
    public void setMessage(RttChatMessage rttChatMessage, boolean z, Drawable drawable) {
        int i;
        this.messageTextView.setText(rttChatMessage.getContent());
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.container.getLayoutParams();
        layoutParams.gravity = rttChatMessage.isRemote ? 8388611 : 8388613;
        if (z) {
            i = this.resources.getDimensionPixelSize(R.dimen.rtt_same_group_message_margin_top);
        } else {
            i = this.resources.getDimensionPixelSize(R.dimen.rtt_message_margin_top);
        }
        layoutParams.topMargin = i;
        this.container.setLayoutParams(layoutParams);
        this.messageTextView.setEnabled(rttChatMessage.isRemote);
        if (!rttChatMessage.isRemote) {
            this.avatarImageView.setVisibility(8);
        } else if (z) {
            this.avatarImageView.setVisibility(4);
        } else {
            this.avatarImageView.setVisibility(0);
            this.avatarImageView.setImageDrawable(drawable);
        }
    }
}
