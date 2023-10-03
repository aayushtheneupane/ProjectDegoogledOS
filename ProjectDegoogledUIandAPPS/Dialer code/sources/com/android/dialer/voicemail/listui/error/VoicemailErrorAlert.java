package com.android.dialer.voicemail.listui.error;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.app.alert.AlertManager;
import com.android.dialer.voicemail.listui.error.VoicemailErrorMessage;

public class VoicemailErrorAlert {
    private final AlertManager alertManager;
    private final Context context;
    private final TextView details = ((TextView) this.view.findViewById(R.id.error_card_details));
    private final TextView header = ((TextView) this.view.findViewById(R.id.error_card_header));
    private final VoicemailErrorMessageCreator messageCreator;
    private final AlertManager modalAlertManager;
    private View modalView;
    private final TextView primaryAction = ((TextView) this.view.findViewById(R.id.primary_action));
    private final TextView primaryActionRaised = ((TextView) this.view.findViewById(R.id.primary_action_raised));
    private final TextView secondaryAction = ((TextView) this.view.findViewById(R.id.secondary_action));
    private final TextView secondaryActionRaised = ((TextView) this.view.findViewById(R.id.secondary_action_raised));
    private final View view;

    public VoicemailErrorAlert(Context context2, AlertManager alertManager2, AlertManager alertManager3, VoicemailErrorMessageCreator voicemailErrorMessageCreator) {
        this.context = context2;
        this.alertManager = alertManager2;
        this.modalAlertManager = alertManager3;
        this.messageCreator = voicemailErrorMessageCreator;
        this.view = alertManager2.inflate(R.layout.voicemail_error_message_fragment);
    }

    public View getModalView() {
        return this.modalView;
    }

    public View getView() {
        return this.view;
    }

    /* access modifiers changed from: package-private */
    public void loadMessage(VoicemailErrorMessage voicemailErrorMessage) {
        TextView textView;
        this.header.setText(voicemailErrorMessage.getTitle());
        this.details.setText(voicemailErrorMessage.getDescription());
        TextView[] textViewArr = {this.primaryAction, this.secondaryAction};
        TextView[] textViewArr2 = {this.primaryActionRaised, this.secondaryActionRaised};
        for (int i = 0; i < textViewArr.length; i++) {
            if (voicemailErrorMessage.getActions() == null || i >= voicemailErrorMessage.getActions().size()) {
                textViewArr[i].setVisibility(8);
                textViewArr2[i].setVisibility(8);
            } else {
                VoicemailErrorMessage.Action action = voicemailErrorMessage.getActions().get(i);
                if (action.isRaised()) {
                    textView = textViewArr2[i];
                    textViewArr[i].setVisibility(8);
                } else {
                    textView = textViewArr[i];
                    textViewArr2[i].setVisibility(8);
                }
                textView.setText(action.getText());
                textView.setOnClickListener(action.getListener());
                textView.setVisibility(0);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x001f A[LOOP:0: B:1:0x001f->B:4:0x0033, LOOP_START, PHI: r1 
      PHI: (r1v3 com.android.dialer.voicemail.listui.error.VoicemailErrorMessage) = (r1v2 com.android.dialer.voicemail.listui.error.VoicemailErrorMessage), (r1v8 com.android.dialer.voicemail.listui.error.VoicemailErrorMessage) binds: [B:0:0x0000, B:4:0x0033] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateStatus(java.util.List<com.android.dialer.voicemail.listui.error.VoicemailStatus> r7, com.android.dialer.voicemail.listui.error.VoicemailStatusReader r8) {
        /*
            r6 = this;
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]
            int r2 = r7.size()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3 = 0
            r1[r3] = r2
            java.lang.String r2 = "VoicemailErrorAlert.updateStatus"
            java.lang.String r4 = "%d status"
            com.android.dialer.common.LogUtil.m9i(r2, r4, r1)
            android.view.View r1 = r6.view
            r1.setVisibility(r3)
            java.util.Iterator r7 = r7.iterator()
            r1 = 0
        L_0x001f:
            boolean r4 = r7.hasNext()
            if (r4 == 0) goto L_0x0035
            java.lang.Object r1 = r7.next()
            com.android.dialer.voicemail.listui.error.VoicemailStatus r1 = (com.android.dialer.voicemail.listui.error.VoicemailStatus) r1
            com.android.dialer.voicemail.listui.error.VoicemailErrorMessageCreator r4 = r6.messageCreator
            android.content.Context r5 = r6.context
            com.android.dialer.voicemail.listui.error.VoicemailErrorMessage r1 = r4.create(r5, r1, r8)
            if (r1 == 0) goto L_0x001f
        L_0x0035:
            com.android.dialer.app.alert.AlertManager r7 = r6.alertManager
            r7.clear()
            com.android.dialer.app.alert.AlertManager r7 = r6.modalAlertManager
            r7.clear()
            if (r1 == 0) goto L_0x0120
            r7 = 2
            java.lang.Object[] r8 = new java.lang.Object[r7]
            boolean r4 = r1.isModal()
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r8[r3] = r4
            java.lang.CharSequence r4 = r1.getTitle()
            r8[r0] = r4
            java.lang.String r4 = "isModal: %b, %s"
            com.android.dialer.common.LogUtil.m9i(r2, r4, r8)
            boolean r8 = r1.isModal()
            if (r8 == 0) goto L_0x0116
            boolean r8 = r1 instanceof com.android.dialer.voicemail.listui.error.VoicemailTosMessage
            if (r8 == 0) goto L_0x010e
            com.android.dialer.app.alert.AlertManager r8 = r6.modalAlertManager
            com.android.dialer.voicemail.listui.error.VoicemailTosMessage r1 = (com.android.dialer.voicemail.listui.error.VoicemailTosMessage) r1
            r2 = 2131493087(0x7f0c00df, float:1.8609644E38)
            android.view.View r8 = r8.inflate(r2)
            r2 = 2131296935(0x7f0902a7, float:1.82118E38)
            android.view.View r2 = r8.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.CharSequence r4 = r1.getTitle()
            r2.setText(r4)
            r2 = 2131296934(0x7f0902a6, float:1.8211799E38)
            android.view.View r2 = r8.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.CharSequence r4 = r1.getDescription()
            r2.setText(r4)
            android.text.method.MovementMethod r4 = android.text.method.LinkMovementMethod.getInstance()
            r2.setMovementMethod(r4)
            java.util.List r2 = r1.getActions()
            int r2 = r2.size()
            if (r2 != r7) goto L_0x00a1
            r7 = r0
            goto L_0x00a2
        L_0x00a1:
            r7 = r3
        L_0x00a2:
            com.android.dialer.common.Assert.checkArgument(r7)
            java.util.List r7 = r1.getActions()
            java.lang.Object r7 = r7.get(r3)
            com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action r7 = (com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.Action) r7
            r2 = 2131297008(0x7f0902f0, float:1.8211949E38)
            android.view.View r2 = r8.findViewById(r2)
            android.widget.TextView r2 = (android.widget.TextView) r2
            java.lang.CharSequence r4 = r7.getText()
            r2.setText(r4)
            android.view.View$OnClickListener r7 = r7.getListener()
            r2.setOnClickListener(r7)
            java.util.List r7 = r1.getActions()
            java.lang.Object r7 = r7.get(r0)
            com.android.dialer.voicemail.listui.error.VoicemailErrorMessage$Action r7 = (com.android.dialer.voicemail.listui.error.VoicemailErrorMessage.Action) r7
            r0 = 2131297007(0x7f0902ef, float:1.8211947E38)
            android.view.View r0 = r8.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            java.lang.CharSequence r2 = r7.getText()
            r0.setText(r2)
            android.view.View$OnClickListener r7 = r7.getListener()
            r0.setOnClickListener(r7)
            java.lang.Integer r7 = r1.getImageResourceId()
            if (r7 == 0) goto L_0x0104
            r7 = 2131297002(0x7f0902ea, float:1.8211937E38)
            android.view.View r7 = r8.findViewById(r7)
            android.widget.ImageView r7 = (android.widget.ImageView) r7
            java.lang.Integer r0 = r1.getImageResourceId()
            int r0 = r0.intValue()
            r7.setImageResource(r0)
            r7.setVisibility(r3)
        L_0x0104:
            r6.modalView = r8
            com.android.dialer.app.alert.AlertManager r7 = r6.modalAlertManager
            android.view.View r6 = r6.modalView
            r7.add(r6)
            goto L_0x0120
        L_0x010e:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "Modal message type is undefined!"
            r6.<init>(r7)
            throw r6
        L_0x0116:
            r6.loadMessage(r1)
            com.android.dialer.app.alert.AlertManager r7 = r6.alertManager
            android.view.View r6 = r6.view
            r7.add(r6)
        L_0x0120:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.voicemail.listui.error.VoicemailErrorAlert.updateStatus(java.util.List, com.android.dialer.voicemail.listui.error.VoicemailStatusReader):void");
    }
}
