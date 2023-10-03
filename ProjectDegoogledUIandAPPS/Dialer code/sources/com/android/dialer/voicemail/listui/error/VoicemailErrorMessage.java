package com.android.dialer.voicemail.listui.error;

import android.content.Context;
import android.content.Intent;
import android.telecom.PhoneAccountHandle;
import android.view.View;
import com.android.dialer.R;
import com.android.dialer.callintent.CallInitiationType$Type;
import com.android.dialer.callintent.CallIntentBuilder;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.precall.PreCall;
import java.util.Arrays;
import java.util.List;

public class VoicemailErrorMessage {
    private final List<Action> actions;
    private final CharSequence description;
    private Integer imageResourceId;
    private boolean modal;
    private final CharSequence title;

    public VoicemailErrorMessage(CharSequence charSequence, CharSequence charSequence2, Action... actionArr) {
        this(charSequence, charSequence2, (List<Action>) Arrays.asList(actionArr));
    }

    public static Action createCallVoicemailAction(final Context context, final PhoneAccountHandle phoneAccountHandle) {
        return new Action(context.getString(R.string.voicemail_action_call_voicemail), new View.OnClickListener() {
            public void onClick(View view) {
                ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.VVM_CALL_VOICEMAIL_CLICKED);
                PreCall.start(context, CallIntentBuilder.forVoicemail(phoneAccountHandle, CallInitiationType$Type.VOICEMAIL_ERROR_MESSAGE));
            }
        });
    }

    public static Action createRetryAction(final Context context, final VoicemailStatus voicemailStatus) {
        return new Action(context.getString(R.string.voicemail_action_retry), new View.OnClickListener() {
            public void onClick(View view) {
                ((LoggingBindingsStub) Logger.get(context)).logImpression(DialerImpression$Type.VVM_USER_RETRY);
                Intent intent = new Intent("android.provider.action.SYNC_VOICEMAIL");
                intent.setPackage(voicemailStatus.sourcePackage);
                context.sendBroadcast(intent);
            }
        });
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public CharSequence getDescription() {
        return this.description;
    }

    public Integer getImageResourceId() {
        return this.imageResourceId;
    }

    public CharSequence getTitle() {
        return this.title;
    }

    public boolean isModal() {
        return this.modal;
    }

    public VoicemailErrorMessage setImageResourceId(Integer num) {
        this.imageResourceId = num;
        return this;
    }

    public VoicemailErrorMessage setModal(boolean z) {
        this.modal = z;
        return this;
    }

    public VoicemailErrorMessage(CharSequence charSequence, CharSequence charSequence2, List<Action> list) {
        this.title = charSequence;
        this.description = charSequence2;
        this.actions = list;
    }

    public static class Action {
        private final View.OnClickListener listener;
        private final boolean raised;
        private final CharSequence text;

        public Action(CharSequence charSequence, View.OnClickListener onClickListener) {
            this.text = charSequence;
            this.listener = onClickListener;
            this.raised = false;
        }

        public View.OnClickListener getListener() {
            return this.listener;
        }

        public CharSequence getText() {
            return this.text;
        }

        public boolean isRaised() {
            return this.raised;
        }

        public Action(CharSequence charSequence, View.OnClickListener onClickListener, boolean z) {
            this.text = charSequence;
            this.listener = onClickListener;
            this.raised = z;
        }
    }
}
