package com.android.incallui.rtt.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.rtt.RttTranscript;
import com.android.dialer.rtt.RttTranscriptMessage;
import com.android.incallui.rtt.protocol.RttChatMessage;
import java.util.ArrayList;
import java.util.List;

public class RttChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Drawable avatarDrawable;
    private final Context context;
    private int lastIndexOfLocalMessage = -1;
    private final MessageListener messageListener;
    private List<RttChatMessage> rttMessages = new ArrayList();
    private boolean shouldShowAdvisory;

    interface MessageListener {
    }

    RttChatAdapter(Context context2, MessageListener messageListener2) {
        this.context = context2;
        this.messageListener = messageListener2;
    }

    private int toItemPosition(int i) {
        return (i >= 0 && this.shouldShowAdvisory) ? i + 1 : i;
    }

    /* access modifiers changed from: package-private */
    public void addLocalMessage(String str) {
        int i = this.lastIndexOfLocalMessage;
        RttChatMessage rttChatMessage = i >= 0 ? this.rttMessages.get(i) : null;
        if (rttChatMessage == null || rttChatMessage.isFinished()) {
            RttChatMessage rttChatMessage2 = new RttChatMessage();
            rttChatMessage2.append(str);
            this.rttMessages.add(rttChatMessage2);
            this.lastIndexOfLocalMessage = this.rttMessages.size() - 1;
            notifyItemInserted(toItemPosition(this.lastIndexOfLocalMessage));
        } else {
            rttChatMessage.append(str);
            if (TextUtils.isEmpty(rttChatMessage.getContent())) {
                this.rttMessages.remove(this.lastIndexOfLocalMessage);
                notifyItemRemoved(toItemPosition(this.lastIndexOfLocalMessage));
                this.lastIndexOfLocalMessage = -1;
            } else {
                notifyItemChanged(toItemPosition(this.lastIndexOfLocalMessage));
            }
        }
        MessageListener messageListener2 = this.messageListener;
        if (messageListener2 != null) {
            ((RttChatFragment) messageListener2).onUpdateLocalMessage(toItemPosition(this.lastIndexOfLocalMessage));
        }
    }

    /* access modifiers changed from: package-private */
    public void addRemoteMessage(String str) {
        if (!TextUtils.isEmpty(str)) {
            RttChatMessage.updateRemoteRttChatMessage(this.rttMessages, str);
            this.lastIndexOfLocalMessage = RttChatMessage.getLastIndexLocalMessage(this.rttMessages);
            notifyDataSetChanged();
            MessageListener messageListener2 = this.messageListener;
            if (messageListener2 != null) {
                ((RttChatFragment) messageListener2).onUpdateRemoteMessage(toItemPosition(RttChatMessage.getLastIndexRemoteMessage(this.rttMessages)));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String computeChangeOfLocalMessage(String str) {
        int i = this.lastIndexOfLocalMessage;
        RttChatMessage rttChatMessage = i >= 0 ? this.rttMessages.get(i) : null;
        if (rttChatMessage == null || rttChatMessage.isFinished()) {
            return str;
        }
        String content = rttChatMessage.getContent();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i2 < content.length() && i2 < str.length() && content.charAt(i2) == str.charAt(i2)) {
            i2++;
        }
        for (int i3 = i2; i3 < content.length(); i3++) {
            sb.append(8);
        }
        while (i2 < str.length()) {
            sb.append(str.charAt(i2));
            i2++;
        }
        return sb.toString();
    }

    public int getItemCount() {
        boolean z = this.shouldShowAdvisory;
        int size = this.rttMessages.size();
        return z ? size + 1 : size;
    }

    public int getItemViewType(int i) {
        return (!this.shouldShowAdvisory || i != 0) ? 2 : 1;
    }

    /* access modifiers changed from: package-private */
    public List<RttTranscriptMessage> getRttTranscriptMessageList() {
        return RttChatMessage.toTranscriptMessageList(this.rttMessages);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        boolean z = (!this.shouldShowAdvisory || i != 0) ? true : true;
        if (z) {
            return;
        }
        if (z) {
            RttChatMessageViewHolder rttChatMessageViewHolder = (RttChatMessageViewHolder) viewHolder;
            if (this.shouldShowAdvisory) {
                i--;
            }
            boolean z2 = false;
            if (i > 0 && this.rttMessages.get(i).isRemote == this.rttMessages.get(i - 1).isRemote) {
                z2 = true;
            }
            rttChatMessageViewHolder.setMessage(this.rttMessages.get(i), z2, this.avatarDrawable);
            return;
        }
        throw new RuntimeException("Unknown row type.");
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.context);
        if (i == 1) {
            return new AdvisoryViewHolder(from.inflate(R.layout.rtt_transcript_advisory, viewGroup, false));
        }
        if (i == 2) {
            return new RttChatMessageViewHolder(from.inflate(R.layout.rtt_chat_list_item, viewGroup, false));
        }
        throw new RuntimeException("Unknown row type.");
    }

    /* access modifiers changed from: package-private */
    public String onRestoreRttChat(RttTranscript rttTranscript) {
        LogUtil.enterBlock("RttChatAdapater.onRestoreRttChat");
        this.rttMessages = RttChatMessage.fromTranscript(rttTranscript);
        this.lastIndexOfLocalMessage = RttChatMessage.getLastIndexLocalMessage(this.rttMessages);
        notifyDataSetChanged();
        int i = this.lastIndexOfLocalMessage;
        if (i < 0) {
            return null;
        }
        RttChatMessage rttChatMessage = this.rttMessages.get(i);
        if (!rttChatMessage.isFinished()) {
            return rttChatMessage.getContent();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String retrieveLastLocalMessage() {
        this.lastIndexOfLocalMessage = RttChatMessage.getLastIndexLocalMessage(this.rttMessages);
        int i = this.lastIndexOfLocalMessage;
        if (i < 0) {
            return null;
        }
        RttChatMessage rttChatMessage = this.rttMessages.get(i);
        rttChatMessage.unfinish();
        return rttChatMessage.getContent();
    }

    /* access modifiers changed from: package-private */
    public void setAvatarDrawable(Drawable drawable) {
        this.avatarDrawable = drawable;
    }

    /* access modifiers changed from: package-private */
    public void showAdvisory() {
        this.shouldShowAdvisory = true;
        notifyItemInserted(0);
    }

    /* access modifiers changed from: package-private */
    public void submitLocalMessage() {
        LogUtil.enterBlock("RttChatAdapater.submitLocalMessage");
        this.rttMessages.get(this.lastIndexOfLocalMessage).finish();
        notifyItemChanged(toItemPosition(this.lastIndexOfLocalMessage));
        this.lastIndexOfLocalMessage = -1;
    }
}
