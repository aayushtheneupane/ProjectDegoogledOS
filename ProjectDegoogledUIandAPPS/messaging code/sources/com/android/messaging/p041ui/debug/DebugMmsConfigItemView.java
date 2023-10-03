package com.android.messaging.p041ui.debug;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.android.messaging.R;

/* renamed from: com.android.messaging.ui.debug.DebugMmsConfigItemView */
public class DebugMmsConfigItemView extends LinearLayout implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, DialogInterface.OnClickListener {
    /* access modifiers changed from: private */

    /* renamed from: H */
    public EditText f1970H;

    /* renamed from: Rh */
    private TextView f1971Rh;

    /* renamed from: Sh */
    private Switch f1972Sh;

    /* renamed from: Th */
    private String f1973Th;
    private String mKey;
    private C1251d mListener;
    private TextView mTitle;

    public DebugMmsConfigItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ((C1249b) this.mListener).mo7628a(this.mKey, this.f1973Th, String.valueOf(z));
    }

    public void onClick(View view) {
        if (!DefaultCarrierConfigValuesLoader.KEY_TYPE_BOOL.equals(this.f1973Th)) {
            Context context = getContext();
            this.f1970H = new EditText(context);
            this.f1970H.setText(this.f1971Rh.getText());
            this.f1970H.setFocusable(true);
            if (DefaultCarrierConfigValuesLoader.KEY_TYPE_INT.equals(this.f1973Th)) {
                this.f1970H.setInputType(3);
            } else {
                this.f1970H.setInputType(524288);
            }
            AlertDialog create = new AlertDialog.Builder(context).setTitle(this.mKey).setView(this.f1970H).setPositiveButton(17039370, this).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create();
            create.setOnShowListener(new C1250c(this, context));
            create.show();
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.mTitle = (TextView) findViewById(R.id.title);
        this.f1971Rh = (TextView) findViewById(R.id.text_value);
        this.f1972Sh = (Switch) findViewById(R.id.switch_button);
        setOnClickListener(this);
        this.f1972Sh.setOnCheckedChangeListener(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007a  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo7621a(java.lang.String r4, java.lang.String r5, java.lang.String r6, com.android.messaging.p041ui.debug.C1251d r7) {
        /*
            r3 = this;
            r3.mListener = r7
            r3.mKey = r4
            r3.f1973Th = r5
            android.widget.TextView r7 = r3.mTitle
            r7.setText(r4)
            int r4 = r5.hashCode()
            r7 = -891985903(0xffffffffcad56011, float:-6991880.5)
            r0 = 2
            r1 = 1
            r2 = 0
            if (r4 == r7) goto L_0x0036
            r7 = 104431(0x197ef, float:1.46339E-40)
            if (r4 == r7) goto L_0x002c
            r7 = 3029738(0x2e3aea, float:4.245567E-39)
            if (r4 == r7) goto L_0x0022
            goto L_0x0040
        L_0x0022:
            java.lang.String r4 = "bool"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0040
            r4 = r2
            goto L_0x0041
        L_0x002c:
            java.lang.String r4 = "int"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0040
            r4 = r0
            goto L_0x0041
        L_0x0036:
            java.lang.String r4 = "string"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0040
            r4 = r1
            goto L_0x0041
        L_0x0040:
            r4 = -1
        L_0x0041:
            r7 = 8
            if (r4 == 0) goto L_0x007a
            if (r4 == r1) goto L_0x006a
            if (r4 == r0) goto L_0x006a
            android.widget.TextView r4 = r3.f1971Rh
            r4.setVisibility(r7)
            android.widget.Switch r3 = r3.f1972Sh
            r3.setVisibility(r7)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unexpected keytype: "
            r3.append(r4)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "MessagingApp"
            com.android.messaging.util.C1430e.m3622e(r4, r3)
            goto L_0x0091
        L_0x006a:
            android.widget.TextView r4 = r3.f1971Rh
            r4.setVisibility(r2)
            android.widget.Switch r4 = r3.f1972Sh
            r4.setVisibility(r7)
            android.widget.TextView r3 = r3.f1971Rh
            r3.setText(r6)
            goto L_0x0091
        L_0x007a:
            android.widget.Switch r4 = r3.f1972Sh
            r4.setVisibility(r2)
            android.widget.TextView r4 = r3.f1971Rh
            r4.setVisibility(r7)
            android.widget.Switch r3 = r3.f1972Sh
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r6)
            boolean r4 = r4.booleanValue()
            r3.setChecked(r4)
        L_0x0091:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.debug.DebugMmsConfigItemView.mo7621a(java.lang.String, java.lang.String, java.lang.String, com.android.messaging.ui.debug.d):void");
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        ((C1249b) this.mListener).mo7628a(this.mKey, this.f1973Th, this.f1970H.getText().toString());
    }
}
