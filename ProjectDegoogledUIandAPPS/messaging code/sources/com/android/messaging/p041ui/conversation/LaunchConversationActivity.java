package com.android.messaging.p041ui.conversation;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.C0891C;
import com.android.messaging.datamodel.data.C0892D;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1486ya;
import com.android.messaging.util.C1488za;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* renamed from: com.android.messaging.ui.conversation.LaunchConversationActivity */
public class LaunchConversationActivity extends Activity implements C0891C {

    /* renamed from: fc */
    String f1825fc;
    final C0783c mBinding = C0784d.m1315q(this);

    /* renamed from: A */
    public void mo6213A() {
        C1486ya.m3848Pa(R.string.conversation_creation_failure);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        if (!C1486ya.m3856d(this)) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if ("android.intent.action.SENDTO".equals(action) || "android.intent.action.VIEW".equals(action)) {
                String[] C = C1488za.m3863C(intent.getData());
                boolean z = !TextUtils.isEmpty(intent.getStringExtra("address"));
                boolean z2 = !TextUtils.isEmpty(intent.getStringExtra("android.intent.extra.EMAIL"));
                int i = 0;
                if (C == null && (z || z2)) {
                    C = z ? new String[]{intent.getStringExtra("address")} : new String[]{intent.getStringExtra("android.intent.extra.EMAIL")};
                }
                this.f1825fc = intent.getStringExtra("sms_body");
                if (TextUtils.isEmpty(this.f1825fc)) {
                    Uri data = intent.getData();
                    if (data != null) {
                        String schemeSpecificPart = data.getSchemeSpecificPart();
                        if (schemeSpecificPart.contains("?")) {
                            String[] split = schemeSpecificPart.substring(schemeSpecificPart.indexOf(63) + 1).split("&");
                            int length = split.length;
                            while (true) {
                                if (i >= length) {
                                    break;
                                }
                                String str2 = split[i];
                                if (str2.startsWith("body=")) {
                                    try {
                                        str = URLDecoder.decode(str2.substring(5), "UTF-8");
                                        break;
                                    } catch (UnsupportedEncodingException unused) {
                                    }
                                } else {
                                    i++;
                                }
                            }
                        }
                    }
                    str = null;
                    this.f1825fc = str;
                    if (TextUtils.isEmpty(this.f1825fc) && "text/plain".equals(intent.getType())) {
                        this.f1825fc = intent.getStringExtra("android.intent.extra.TEXT");
                    }
                }
                if (C != null) {
                    this.mBinding.mo5930b(C0947h.get().mo6595a(this));
                    ((C0892D) this.mBinding.getData()).mo6220a(this.mBinding, C);
                } else {
                    onGetOrCreateNewConversation((String) null);
                }
            } else {
                C1430e.m3630w("MessagingApp", "Unsupported conversation intent action : " + action);
            }
            finish();
        }
    }

    public void onGetOrCreateNewConversation(String str) {
        C1040Ea.get().mo6968b(C0967f.get().getApplicationContext(), str, this.f1825fc);
    }
}
