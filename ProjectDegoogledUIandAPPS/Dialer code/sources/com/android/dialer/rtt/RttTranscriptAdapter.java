package com.android.dialer.rtt;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.glidephotomanager.PhotoInfo;

public class RttTranscriptAdapter extends RecyclerView.Adapter<RttTranscriptMessageViewHolder> {
    private final Context context;
    private int firstPositionToShowTimestamp;
    private PhotoInfo photoInfo;
    private RttTranscript rttTranscript;

    RttTranscriptAdapter(Context context2) {
        this.context = context2;
    }

    public int getItemCount() {
        RttTranscript rttTranscript2 = this.rttTranscript;
        if (rttTranscript2 == null) {
            return 0;
        }
        return rttTranscript2.getMessagesCount();
    }

    public int getItemViewType(int i) {
        return 0;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int i2;
        RttTranscriptMessageViewHolder rttTranscriptMessageViewHolder = (RttTranscriptMessageViewHolder) viewHolder;
        RttTranscriptMessage messages = this.rttTranscript.getMessages(i);
        boolean z = false;
        boolean z2 = i > 0 && messages.getIsRemote() == this.rttTranscript.getMessages(i + -1).getIsRemote();
        int i3 = i + 1;
        RttTranscript rttTranscript2 = this.rttTranscript;
        if (rttTranscript2 == null) {
            i2 = 0;
        } else {
            i2 = rttTranscript2.getMessagesCount();
        }
        boolean z3 = i3 < i2 && messages.getIsRemote() == this.rttTranscript.getMessages(i3).getIsRemote();
        rttTranscriptMessageViewHolder.setMessage(messages, z2, this.photoInfo);
        if (z3) {
            rttTranscriptMessageViewHolder.hideTimestamp();
            return;
        }
        long timestamp = messages.getTimestamp();
        boolean isRemote = messages.getIsRemote();
        if (i == this.firstPositionToShowTimestamp) {
            z = true;
        }
        rttTranscriptMessageViewHolder.showTimestamp(timestamp, isRemote, z);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RttTranscriptMessageViewHolder(LayoutInflater.from(this.context).inflate(R.layout.rtt_transcript_list_item, viewGroup, false));
    }

    /* access modifiers changed from: package-private */
    public void setPhotoInfo(PhotoInfo photoInfo2) {
        this.photoInfo = photoInfo2;
    }

    /* access modifiers changed from: package-private */
    public void setRttTranscript(RttTranscript rttTranscript2) {
        int i = 0;
        if (rttTranscript2 == null) {
            LogUtil.m10w("RttTranscriptAdapter.setRttTranscript", "null RttTranscript", new Object[0]);
            return;
        }
        this.rttTranscript = rttTranscript2;
        while (true) {
            int i2 = i + 1;
            if (i2 >= rttTranscript2.getMessagesCount() || rttTranscript2.getMessages(i).getIsRemote() != rttTranscript2.getMessages(i2).getIsRemote()) {
                this.firstPositionToShowTimestamp = i;
                notifyDataSetChanged();
            } else {
                i = i2;
            }
        }
        this.firstPositionToShowTimestamp = i;
        notifyDataSetChanged();
    }
}
