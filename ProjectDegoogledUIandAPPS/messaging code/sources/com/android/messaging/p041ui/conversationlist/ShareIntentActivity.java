package com.android.messaging.p041ui.conversationlist;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.android.messaging.C0967f;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.data.PendingAttachmentData;
import com.android.messaging.p041ui.BaseBugleActivity;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1431ea;
import com.android.messaging.util.C1488za;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import p000a.p005b.C0015b;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.conversationlist.ShareIntentActivity */
public class ShareIntentActivity extends BaseBugleActivity implements C1211C {

    /* renamed from: Yb */
    private MessageData f1925Yb;

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0061, code lost:
        if (r3 != null) goto L_0x0063;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004b A[Catch:{ FileNotFoundException -> 0x004c, IOException -> 0x0042, all -> 0x0040, all -> 0x0067 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x006b A[SYNTHETIC, Splitter:B:44:0x006b] */
    /* renamed from: G */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m3103G(android.net.Uri r6) {
        /*
            java.lang.String r0 = "MessagingApp"
            r1 = 0
            if (r6 != 0) goto L_0x0006
            return r1
        L_0x0006:
            android.content.ContentResolver r2 = p026b.p027a.p030b.p031a.C0632a.m1012Pk()
            java.io.InputStream r2 = r2.openInputStream(r6)     // Catch:{ FileNotFoundException -> 0x004c, IOException -> 0x0042, all -> 0x0040 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x004c, IOException -> 0x0042, all -> 0x0040 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x004c, IOException -> 0x0042, all -> 0x0040 }
            r4.<init>(r2)     // Catch:{ FileNotFoundException -> 0x004c, IOException -> 0x0042, all -> 0x0040 }
            r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x004c, IOException -> 0x0042, all -> 0x0040 }
            java.lang.String r2 = r3.readLine()     // Catch:{ FileNotFoundException -> 0x004d, IOException -> 0x003e }
            if (r2 != 0) goto L_0x0022
            r3.close()     // Catch:{ IOException -> 0x0021 }
        L_0x0021:
            return r1
        L_0x0022:
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ FileNotFoundException -> 0x004d, IOException -> 0x003e }
            r4.<init>(r2)     // Catch:{ FileNotFoundException -> 0x004d, IOException -> 0x003e }
        L_0x0027:
            java.lang.String r2 = r3.readLine()     // Catch:{ FileNotFoundException -> 0x004d, IOException -> 0x003e }
            if (r2 == 0) goto L_0x0036
            java.lang.String r5 = "\n"
            r4.append(r5)     // Catch:{ FileNotFoundException -> 0x004d, IOException -> 0x003e }
            r4.append(r2)     // Catch:{ FileNotFoundException -> 0x004d, IOException -> 0x003e }
            goto L_0x0027
        L_0x0036:
            java.lang.String r6 = r4.toString()     // Catch:{ FileNotFoundException -> 0x004d, IOException -> 0x003e }
            r3.close()     // Catch:{ IOException -> 0x003d }
        L_0x003d:
            return r6
        L_0x003e:
            r6 = move-exception
            goto L_0x0044
        L_0x0040:
            r6 = move-exception
            goto L_0x0069
        L_0x0042:
            r6 = move-exception
            r3 = r1
        L_0x0044:
            java.lang.String r2 = "Can not read contentUri"
            com.android.messaging.util.C1430e.m3631w(r0, r2, r6)     // Catch:{ all -> 0x0067 }
            if (r3 == 0) goto L_0x0066
            goto L_0x0063
        L_0x004c:
            r3 = r1
        L_0x004d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0067 }
            r2.<init>()     // Catch:{ all -> 0x0067 }
            java.lang.String r4 = "Can not find contentUri "
            r2.append(r4)     // Catch:{ all -> 0x0067 }
            r2.append(r6)     // Catch:{ all -> 0x0067 }
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x0067 }
            com.android.messaging.util.C1430e.m3630w(r0, r6)     // Catch:{ all -> 0x0067 }
            if (r3 == 0) goto L_0x0066
        L_0x0063:
            r3.close()     // Catch:{ IOException -> 0x0066 }
        L_0x0066:
            return r1
        L_0x0067:
            r6 = move-exception
            r1 = r3
        L_0x0069:
            if (r1 == 0) goto L_0x006e
            r1.close()     // Catch:{ IOException -> 0x006e }
        L_0x006e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.conversationlist.ShareIntentActivity.m3103G(android.net.Uri):java.lang.String");
    }

    /* renamed from: c */
    private static String m3104c(Uri uri, String str) {
        if (uri == null) {
            return str;
        }
        String type = C0967f.get().getApplicationContext().getContentResolver().getType(uri);
        if (type != null) {
            return type;
        }
        C1431ea eaVar = new C1431ea();
        try {
            eaVar.mo8063v(uri);
            String extractMetadata = eaVar.extractMetadata(12);
            if (extractMetadata != null) {
                eaVar.release();
                return extractMetadata;
            }
        } catch (IOException e) {
            C1430e.m3626i("MessagingApp", "Could not determine type of " + uri, e);
        } catch (Throwable th) {
            eaVar.release();
            throw th;
        }
        eaVar.release();
        return str;
    }

    /* renamed from: Z */
    public void mo7108Z() {
        C1040Ea.get().mo6958a((Context) this, this.f1925Yb);
        finish();
    }

    /* renamed from: a */
    public void mo7109a(C0934q qVar) {
        C1040Ea.get().mo6960a((Context) this, qVar.mo6505Ue(), this.f1925Yb, (Bundle) null, false);
        finish();
    }

    public void onAttachFragment(Fragment fragment) {
        Intent intent = getIntent();
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("android.intent.extra.SUBJECT");
        if (stringExtra == null) {
            stringExtra = intent.getStringExtra("android.intent.extra.TITLE");
        }
        if ("android.intent.action.SEND".equals(action)) {
            Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
            if (C1488za.m3876y(uri)) {
                C1430e.m3625i("MessagingApp", "Ignoring attachment from file URI which are no longer supported.");
                return;
            }
            String c = m3104c(uri, intent.getType());
            if (Log.isLoggable("MessagingApp", 3)) {
                C1430e.m3620d("MessagingApp", String.format("onAttachFragment: contentUri=%s, intent.getType()=%s, inferredType=%s", new Object[]{uri, intent.getType(), c}));
            }
            if ("text/plain".equals(c)) {
                String stringExtra2 = intent.getStringExtra("android.intent.extra.TEXT");
                if (stringExtra2 == null) {
                    stringExtra2 = m3103G(uri);
                }
                if (stringExtra2 != null) {
                    this.f1925Yb = MessageData.m1758g(stringExtra2, stringExtra);
                } else {
                    this.f1925Yb = null;
                }
            } else if (!MessagePartData.m1809ga(c)) {
                C1430e.m3622e("MessagingApp", "Unsupported shared content type for " + uri + ": " + c + " (" + intent.getType() + ")");
                this.f1925Yb = null;
            } else if (uri != null) {
                this.f1925Yb = MessageData.m1758g((String) null, stringExtra);
                m3105c(c, uri);
            } else {
                this.f1925Yb = null;
            }
        } else if ("android.intent.action.SEND_MULTIPLE".equals(action)) {
            String type = intent.getType();
            ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            if (parcelableArrayListExtra == null || parcelableArrayListExtra.isEmpty()) {
                C1430e.m3622e("MessagingApp", "No shared URI.");
                this.f1925Yb = null;
                return;
            }
            C0015b bVar = new C0015b();
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = parcelableArrayListExtra.iterator();
            while (it.hasNext()) {
                Uri uri2 = (Uri) it.next();
                if (C1488za.m3876y(uri2)) {
                    C1430e.m3625i("MessagingApp", "Ignoring attachment from file URI which are no longer supported.");
                } else {
                    String c2 = m3104c(uri2, type);
                    if ("text/plain".equals(c2)) {
                        String G = m3103G(uri2);
                        if (G != null) {
                            if (stringBuffer.length() > 0) {
                                stringBuffer.append("\n");
                            }
                            stringBuffer.append(G);
                        }
                    } else if (MessagePartData.m1809ga(c2)) {
                        bVar.put(uri2, c2);
                    } else {
                        C1430e.m3622e("MessagingApp", "Unsupported shared content type for " + uri2 + ": " + c2);
                    }
                }
            }
            if (stringBuffer.length() > 0 || !bVar.isEmpty()) {
                this.f1925Yb = MessageData.m1758g(stringBuffer.toString(), stringExtra);
                for (Map.Entry entry : bVar.entrySet()) {
                    m3105c((String) entry.getValue(), (Uri) entry.getKey());
                }
            }
        } else {
            C1424b.fail("Unsupported action type for sharing: " + action);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (!"android.intent.action.SEND".equals(intent.getAction()) || (TextUtils.isEmpty(intent.getStringExtra("address")) && TextUtils.isEmpty(intent.getStringExtra("android.intent.extra.EMAIL")))) {
            new ShareIntentFragment().show(getFragmentManager(), "ShareIntentFragment");
            return;
        }
        Intent q = C1040Ea.get().mo6980q(this);
        q.putExtras(intent);
        q.setAction("android.intent.action.SENDTO");
        q.setDataAndType(intent.getData(), intent.getType());
        startActivity(q);
        finish();
    }

    /* renamed from: c */
    private void m3105c(String str, Uri uri) {
        if (C1430e.m3627u(uri)) {
            StringBuilder Pa = C0632a.m1011Pa("Cannot send private file ");
            Pa.append(uri.toString());
            C1424b.fail(Pa.toString());
            return;
        }
        this.f1925Yb.mo6267g(PendingAttachmentData.m1870a(str, uri));
    }
}
