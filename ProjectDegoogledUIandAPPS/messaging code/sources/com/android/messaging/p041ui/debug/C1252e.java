package com.android.messaging.p041ui.debug;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.android.messaging.R;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.debug.e */
class C1252e implements View.OnClickListener {

    /* renamed from: ax */
    final /* synthetic */ String f1977ax;
    final /* synthetic */ C1253f this$1;

    C1252e(C1253f fVar, String str) {
        this.this$1 = fVar;
        this.f1977ax = str;
    }

    public void onClick(View view) {
        this.this$1.this$0.dismiss();
        if ("load".equals(this.this$1.this$0.mAction)) {
            this.this$1.this$0.m3179Fa(this.f1977ax);
        } else if (NotificationCompat.CATEGORY_EMAIL.equals(this.this$1.this$0.mAction)) {
            C1254g gVar = this.this$1.this$0;
            String str = this.f1977ax;
            Resources resources = gVar.getResources();
            StringBuilder Pa = C0632a.m1011Pa("file://");
            Pa.append(Environment.getExternalStorageDirectory());
            Pa.append("/");
            Pa.append(str);
            String sb = Pa.toString();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("application/octet-stream");
            intent.putExtra("android.intent.extra.STREAM", Uri.parse(sb));
            intent.putExtra("android.intent.extra.SUBJECT", resources.getString(R.string.email_sms_mms_dump_file_subject));
            gVar.getActivity().startActivity(Intent.createChooser(intent, resources.getString(R.string.email_sms_mms_dump_file_chooser_title)));
        }
    }
}
