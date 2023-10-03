package com.android.dialer.searchfragment.list;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.searchfragment.common.RowClickListener;
import com.android.dialer.util.CallUtil;
import com.android.dialer.util.DialerUtils;
import com.android.tools.p006r8.GeneratedOutlineSupport;

final class SearchActionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private int action;
    private final ImageView actionImage;
    private final TextView actionText;
    private final Context context;
    private final RowClickListener listener;
    private int position;
    private String query;

    SearchActionViewHolder(View view, RowClickListener rowClickListener) {
        super(view);
        this.context = view.getContext();
        this.actionImage = (ImageView) view.findViewById(R.id.search_action_image);
        this.actionText = (TextView) view.findViewById(R.id.search_action_text);
        this.listener = rowClickListener;
        view.setOnClickListener(this);
    }

    /* access modifiers changed from: package-private */
    public int getAction() {
        return this.action;
    }

    public void onClick(View view) {
        int i = this.action;
        if (i == 1) {
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.CREATE_NEW_CONTACT_FROM_DIALPAD);
            DialerUtils.startActivityWithErrorToast(this.context, CallUtil.getNewContactIntent((CharSequence) null, this.query, -1), R.string.activity_not_available);
        } else if (i == 2) {
            ((LoggingBindingsStub) Logger.get(this.context)).logImpression(DialerImpression$Type.ADD_TO_A_CONTACT_FROM_DIALPAD);
            DialerUtils.startActivityWithErrorToast(this.context, CallUtil.getAddToExistingContactIntent((CharSequence) null, this.query, -1), R.string.add_contact_not_available);
        } else if (i == 3) {
            DialerUtils.startActivityWithErrorToast(this.context, CallUtil.getSendSmsIntent(this.query), R.string.activity_not_available);
        } else if (i == 4) {
            this.listener.placeVideoCall(this.query, this.position);
        } else if (i == 5) {
            this.listener.placeVoiceCall(this.query, this.position);
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid action: ");
            outline13.append(this.action);
            throw new IllegalStateException(outline13.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void setAction(int i, int i2, String str) {
        this.action = i;
        this.position = i2;
        this.query = str;
        if (i == 1) {
            this.actionText.setText(R.string.search_shortcut_create_new_contact);
            this.actionImage.setImageResource(R.drawable.quantum_ic_person_add_vd_theme_24);
        } else if (i == 2) {
            this.actionText.setText(R.string.search_shortcut_add_to_contact);
            this.actionImage.setImageResource(R.drawable.quantum_ic_person_add_vd_theme_24);
        } else if (i == 3) {
            this.actionText.setText(R.string.search_shortcut_send_sms_message);
            this.actionImage.setImageResource(R.drawable.quantum_ic_message_vd_theme_24);
        } else if (i == 4) {
            this.actionText.setText(R.string.search_shortcut_make_video_call);
            this.actionImage.setImageResource(R.drawable.quantum_ic_videocam_vd_theme_24);
        } else if (i == 5) {
            this.actionText.setText(this.context.getString(R.string.search_shortcut_make_voice_call, new Object[]{str}));
            this.actionImage.setImageResource(R.drawable.quantum_ic_phone_vd_theme_24);
        } else {
            throw new IllegalStateException(GeneratedOutlineSupport.outline5("Invalid action: ", i));
        }
    }
}
