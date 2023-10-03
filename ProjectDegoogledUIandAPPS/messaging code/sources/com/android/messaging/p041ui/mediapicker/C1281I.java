package com.android.messaging.p041ui.mediapicker;

import android.view.View;
import android.view.ViewGroup;
import com.android.messaging.R;
import com.android.messaging.util.ContactUtil;

/* renamed from: com.android.messaging.ui.mediapicker.I */
class C1281I extends C1323ea {

    /* renamed from: pF */
    private View f2026pF;

    /* renamed from: qF */
    private View f2027qF;

    C1281I(C1345pa paVar) {
        super(paVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Pi */
    public int mo7672Pi() {
        return R.string.mediapicker_contact_title;
    }

    /* renamed from: Qi */
    public int mo7673Qi() {
        return R.string.mediapicker_contactChooserDescription;
    }

    /* renamed from: Ri */
    public int mo7674Ri() {
        return 8;
    }

    /* access modifiers changed from: protected */
    public View createView(ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(R.layout.mediapicker_contact_chooser, viewGroup, false);
        this.f2026pF = inflate.findViewById(R.id.mediapicker_enabled);
        this.f2027qF = inflate.findViewById(R.id.missing_permission_view);
        this.f2026pF.setOnClickListener(new C1279G(this));
        return inflate;
    }

    public int getIconResource() {
        return R.drawable.ic_person_light;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        r6.addSuppressed(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r7, int r8, android.content.Intent r9) {
        /*
            r6 = this;
            r0 = 1500(0x5dc, float:2.102E-42)
            if (r7 != r0) goto L_0x004f
            r7 = -1
            if (r8 != r7) goto L_0x004f
            android.net.Uri r1 = r9.getData()
            if (r1 == 0) goto L_0x004f
            r7 = 0
            android.content.Context r8 = r6.getContext()
            android.content.ContentResolver r0 = r8.getContentResolver()
            java.lang.String r8 = "lookup"
            java.lang.String[] r2 = new java.lang.String[]{r8}
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r8 = r0.query(r1, r2, r3, r4, r5)
            if (r8 == 0) goto L_0x003a
            r8.moveToFirst()     // Catch:{ all -> 0x002e }
            r7 = 0
            java.lang.String r7 = r8.getString(r7)     // Catch:{ all -> 0x002e }
            goto L_0x003a
        L_0x002e:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r7 = move-exception
            r8.close()     // Catch:{ all -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r8 = move-exception
            r6.addSuppressed(r8)
        L_0x0039:
            throw r7
        L_0x003a:
            if (r8 == 0) goto L_0x003f
            r8.close()
        L_0x003f:
            android.net.Uri r8 = android.provider.ContactsContract.Contacts.CONTENT_VCARD_URI
            android.net.Uri r7 = android.net.Uri.withAppendedPath(r8, r7)
            if (r7 == 0) goto L_0x004f
            com.android.messaging.ui.mediapicker.H r8 = new com.android.messaging.ui.mediapicker.H
            r8.<init>(r6, r7)
            com.android.messaging.util.C1478ua.m3823a((java.lang.Runnable) r8)
        L_0x004f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.mediapicker.C1281I.onActivityResult(int, int, android.content.Intent):void");
    }

    /* access modifiers changed from: protected */
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 5) {
            int i2 = 0;
            boolean z = iArr[0] == 0;
            this.f2026pF.setVisibility(z ? 0 : 8);
            View view = this.f2027qF;
            if (z) {
                i2 = 8;
            }
            view.setVisibility(i2);
        }
    }

    /* access modifiers changed from: protected */
    public void setSelected(boolean z) {
        super.setSelected(z);
        if (z && !ContactUtil.m3525Kj()) {
            this.f2118Dj.requestPermissions(new String[]{"android.permission.READ_CONTACTS"}, 5);
        }
    }
}
