package com.android.dialer.voicemail.listui.error;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.android.dialer.R;
import com.android.tools.p006r8.GeneratedOutlineSupport;

class Vvm3VoicemailMessageCreator$1 implements View.OnClickListener {
    final /* synthetic */ Context val$context;

    Vvm3VoicemailMessageCreator$1(Context context) {
        this.val$context = context;
    }

    public void onClick(View view) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("tel:");
        outline13.append(this.val$context.getString(R.string.verizon_domestic_customer_support_number));
        this.val$context.startActivity(new Intent("android.intent.action.CALL", Uri.parse(outline13.toString())));
    }
}
