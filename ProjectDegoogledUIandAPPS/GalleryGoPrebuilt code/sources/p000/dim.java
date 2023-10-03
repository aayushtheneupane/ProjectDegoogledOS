package p000;

import java.util.concurrent.Callable;

/* renamed from: dim */
/* compiled from: PG */
final /* synthetic */ class dim implements Callable {

    /* renamed from: a */
    private final dio f6618a;

    /* renamed from: b */
    private final String f6619b;

    public dim(dio dio, String str) {
        this.f6618a = dio;
        this.f6619b = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0299, code lost:
        if (android.text.TextUtils.isEmpty(r8.getString(r15)) != false) goto L_0x029b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object call() {
        /*
            r19 = this;
            r1 = r19
            java.lang.String r2 = "version"
            dio r3 = r1.f6618a
            java.lang.String r4 = r1.f6619b
            r5 = 1
            java.lang.Object[] r0 = new java.lang.Object[r5]
            r6 = 0
            r0[r6] = r4
            dhl r0 = r3.f6623a
            hto r0 = r0.mo4133a()
            hvr r7 = r0.iterator()
        L_0x0018:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L_0x042f
            java.lang.Object r0 = r7.next()
            r8 = r0
            java.lang.String r8 = (java.lang.String) r8
            dhc r0 = r3.f6624b     // Catch:{ all -> 0x042c }
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ all -> 0x042c }
            r9[r6] = r8     // Catch:{ all -> 0x042c }
            r9 = 0
            android.content.ContentResolver r0 = r0.f6533a     // Catch:{ Exception -> 0x0037 }
            android.net.Uri r10 = p000.dhc.m6104a(r8)     // Catch:{ Exception -> 0x0037 }
            android.os.Bundle r9 = r0.call(r10, r2, r9, r9)     // Catch:{ Exception -> 0x0037 }
            goto L_0x0041
        L_0x0037:
            r0 = move-exception
            java.lang.String r10 = "Error querying for API version."
            java.lang.Object[] r11 = new java.lang.Object[r6]     // Catch:{ all -> 0x042c }
            p000.cwn.m5511a((java.lang.Throwable) r0, (java.lang.String) r10, (java.lang.Object[]) r11)     // Catch:{ all -> 0x042c }
        L_0x0041:
            if (r9 == 0) goto L_0x0048
            int r0 = r9.getInt(r2)     // Catch:{ all -> 0x042c }
            goto L_0x004a
        L_0x0048:
            r0 = 1
        L_0x004a:
            android.content.ContentResolver r9 = r3.f6625c     // Catch:{ all -> 0x042c }
            android.net.Uri$Builder r10 = new android.net.Uri$Builder     // Catch:{ all -> 0x042c }
            r10.<init>()     // Catch:{ all -> 0x042c }
            java.lang.String r11 = "content"
            android.net.Uri$Builder r10 = r10.scheme(r11)     // Catch:{ all -> 0x042c }
            android.net.Uri$Builder r8 = r10.authority(r8)     // Catch:{ all -> 0x042c }
            java.lang.String r10 = "data"
            android.net.Uri$Builder r8 = r8.appendPath(r10)     // Catch:{ all -> 0x042c }
            java.lang.String r10 = android.net.Uri.encode(r4)     // Catch:{ all -> 0x042c }
            android.net.Uri$Builder r8 = r8.appendEncodedPath(r10)     // Catch:{ all -> 0x042c }
            android.net.Uri r10 = r8.build()     // Catch:{ all -> 0x042c }
            java.lang.String[] r11 = p000.din.m6155a(r0)     // Catch:{ all -> 0x042c }
            r12 = 0
            r13 = 0
            r14 = 0
            android.database.Cursor r8 = r9.query(r10, r11, r12, r13, r14)     // Catch:{ all -> 0x042c }
            if (r8 == 0) goto L_0x0425
            boolean r9 = r8.moveToFirst()     // Catch:{ all -> 0x0419 }
            if (r9 == 0) goto L_0x0425
            java.lang.String r9 = "configuration"
            java.lang.String r9 = p000.fxk.m9835c(r8, r9)     // Catch:{ all -> 0x0419 }
            bis r10 = p000.bis.m2627a(r0, r9)     // Catch:{ all -> 0x0419 }
            if (r10 == 0) goto L_0x03f4
            java.lang.String r9 = "special_type_name"
            java.lang.String r9 = p000.fxk.m9835c(r8, r9)     // Catch:{ all -> 0x03f2 }
            java.lang.String r12 = "special_type_description"
            java.lang.String r12 = p000.fxk.m9835c(r8, r12)     // Catch:{ all -> 0x03f2 }
            java.lang.String r13 = "special_type_icon_uri"
            java.lang.String r13 = p000.fxk.m9835c(r8, r13)     // Catch:{ all -> 0x03f2 }
            boolean r14 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x03f2 }
            if (r14 == 0) goto L_0x00a6
            goto L_0x03da
        L_0x00a6:
            boolean r14 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x03f2 }
            if (r14 != 0) goto L_0x03da
            boolean r14 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x03f2 }
            if (r14 != 0) goto L_0x03da
            android.net.Uri r13 = android.net.Uri.parse(r13)     // Catch:{ all -> 0x03f2 }
            dik r14 = p000.dik.f6607j     // Catch:{ all -> 0x03f2 }
            iir r14 = r14.mo8793g()     // Catch:{ all -> 0x03f2 }
            boolean r15 = r14.f14319c     // Catch:{ all -> 0x03f2 }
            if (r15 != 0) goto L_0x00c1
            goto L_0x00c6
        L_0x00c1:
            r14.mo8751b()     // Catch:{ all -> 0x03f2 }
            r14.f14319c = r6     // Catch:{ all -> 0x03f2 }
        L_0x00c6:
            iix r15 = r14.f14318b     // Catch:{ all -> 0x03f2 }
            dik r15 = (p000.dik) r15     // Catch:{ all -> 0x03f2 }
            r4.getClass()     // Catch:{ all -> 0x03f2 }
            int r11 = r15.f6609a     // Catch:{ all -> 0x03f2 }
            r11 = r11 | r5
            r15.f6609a = r11     // Catch:{ all -> 0x03f2 }
            r15.f6610b = r4     // Catch:{ all -> 0x03f2 }
            r9.getClass()     // Catch:{ all -> 0x03f2 }
            r11 = r11 | 2
            r15.f6609a = r11     // Catch:{ all -> 0x03f2 }
            r15.f6611c = r9     // Catch:{ all -> 0x03f2 }
            r12.getClass()     // Catch:{ all -> 0x03f2 }
            r9 = r11 | 4
            r15.f6609a = r9     // Catch:{ all -> 0x03f2 }
            r15.f6612d = r12     // Catch:{ all -> 0x03f2 }
            int r9 = r10.ordinal()     // Catch:{ all -> 0x03f2 }
            boolean r11 = r14.f14319c     // Catch:{ all -> 0x03f2 }
            if (r11 != 0) goto L_0x00ef
            goto L_0x00f4
        L_0x00ef:
            r14.mo8751b()     // Catch:{ all -> 0x03f2 }
            r14.f14319c = r6     // Catch:{ all -> 0x03f2 }
        L_0x00f4:
            iix r11 = r14.f14318b     // Catch:{ all -> 0x03f2 }
            dik r11 = (p000.dik) r11     // Catch:{ all -> 0x03f2 }
            int r12 = r11.f6609a     // Catch:{ all -> 0x03f2 }
            r12 = r12 | 16
            r11.f6609a = r12     // Catch:{ all -> 0x03f2 }
            r11.f6614f = r9     // Catch:{ all -> 0x03f2 }
            bit r9 = p000.bit.DIALOG     // Catch:{ all -> 0x03f2 }
            android.net.Uri r9 = p000.cof.m4687a((android.net.Uri) r13, (p000.bit) r9)     // Catch:{ all -> 0x03f2 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x03f2 }
            boolean r11 = r14.f14319c     // Catch:{ all -> 0x03f2 }
            if (r11 != 0) goto L_0x010f
            goto L_0x0114
        L_0x010f:
            r14.mo8751b()     // Catch:{ all -> 0x03f2 }
            r14.f14319c = r6     // Catch:{ all -> 0x03f2 }
        L_0x0114:
            iix r11 = r14.f14318b     // Catch:{ all -> 0x03f2 }
            dik r11 = (p000.dik) r11     // Catch:{ all -> 0x03f2 }
            r9.getClass()     // Catch:{ all -> 0x03f2 }
            int r12 = r11.f6609a     // Catch:{ all -> 0x03f2 }
            r12 = r12 | 128(0x80, float:1.794E-43)
            r11.f6609a = r12     // Catch:{ all -> 0x03f2 }
            r11.f6617i = r9     // Catch:{ all -> 0x03f2 }
            int r9 = r10.ordinal()     // Catch:{ all -> 0x03f2 }
            r11 = 32
            if (r9 == 0) goto L_0x03a0
            r12 = 3
            r15 = 2
            if (r9 == r5) goto L_0x026a
            if (r9 == r15) goto L_0x01cd
            if (r9 == r12) goto L_0x0137
            r11 = 34
            goto L_0x03c6
        L_0x0137:
            java.lang.String r0 = "launch_activity_class_name"
            java.lang.String r0 = p000.fxk.m9835c(r8, r0)     // Catch:{ all -> 0x03f2 }
            java.lang.String r9 = "launch_activity_package_name"
            java.lang.String r9 = p000.fxk.m9835c(r8, r9)     // Catch:{ all -> 0x03f2 }
            boolean r10 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x03f2 }
            if (r10 == 0) goto L_0x014a
        L_0x0149:
            goto L_0x01b9
        L_0x014a:
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x03f2 }
            if (r10 != 0) goto L_0x0149
            android.net.Uri r10 = p000.cof.m4686a(r13)     // Catch:{ all -> 0x03f2 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x03f2 }
            boolean r12 = r14.f14319c     // Catch:{ all -> 0x03f2 }
            if (r12 != 0) goto L_0x015d
            goto L_0x0162
        L_0x015d:
            r14.mo8751b()     // Catch:{ all -> 0x03f2 }
            r14.f14319c = r6     // Catch:{ all -> 0x03f2 }
        L_0x0162:
            iix r12 = r14.f14318b     // Catch:{ all -> 0x03f2 }
            dik r12 = (p000.dik) r12     // Catch:{ all -> 0x03f2 }
            r10.getClass()     // Catch:{ all -> 0x03f2 }
            int r13 = r12.f6609a     // Catch:{ all -> 0x03f2 }
            r13 = r13 | 8
            r12.f6609a = r13     // Catch:{ all -> 0x03f2 }
            r12.f6613e = r10     // Catch:{ all -> 0x03f2 }
            dii r10 = p000.dii.f6597d     // Catch:{ all -> 0x03f2 }
            iir r10 = r10.mo8793g()     // Catch:{ all -> 0x03f2 }
            boolean r12 = r10.f14319c     // Catch:{ all -> 0x03f2 }
            if (r12 != 0) goto L_0x017c
            goto L_0x0181
        L_0x017c:
            r10.mo8751b()     // Catch:{ all -> 0x03f2 }
            r10.f14319c = r6     // Catch:{ all -> 0x03f2 }
        L_0x0181:
            iix r12 = r10.f14318b     // Catch:{ all -> 0x03f2 }
            dii r12 = (p000.dii) r12     // Catch:{ all -> 0x03f2 }
            r0.getClass()     // Catch:{ all -> 0x03f2 }
            int r13 = r12.f6599a     // Catch:{ all -> 0x03f2 }
            r13 = r13 | r5
            r12.f6599a = r13     // Catch:{ all -> 0x03f2 }
            r12.f6600b = r0     // Catch:{ all -> 0x03f2 }
            r9.getClass()     // Catch:{ all -> 0x03f2 }
            r0 = r13 | 2
            r12.f6599a = r0     // Catch:{ all -> 0x03f2 }
            r12.f6601c = r9     // Catch:{ all -> 0x03f2 }
            boolean r0 = r14.f14319c     // Catch:{ all -> 0x03f2 }
            if (r0 != 0) goto L_0x019d
            goto L_0x01a2
        L_0x019d:
            r14.mo8751b()     // Catch:{ all -> 0x03f2 }
            r14.f14319c = r6     // Catch:{ all -> 0x03f2 }
        L_0x01a2:
            iix r0 = r14.f14318b     // Catch:{ all -> 0x03f2 }
            dik r0 = (p000.dik) r0     // Catch:{ all -> 0x03f2 }
            iix r9 = r10.mo8770g()     // Catch:{ all -> 0x03f2 }
            dii r9 = (p000.dii) r9     // Catch:{ all -> 0x03f2 }
            r9.getClass()     // Catch:{ all -> 0x03f2 }
            r0.f6615g = r9     // Catch:{ all -> 0x03f2 }
            int r9 = r0.f6609a     // Catch:{ all -> 0x03f2 }
            r9 = r9 | r11
            r0.f6609a = r9     // Catch:{ all -> 0x03f2 }
            goto L_0x03c6
        L_0x01b9:
            java.lang.String r0 = "SpecialTypeDataResolver: Launch requires package name and activity."
            java.lang.Object[] r9 = new java.lang.Object[r6]     // Catch:{ all -> 0x03f2 }
            p000.cwn.m5510a(r0, r9)     // Catch:{ all -> 0x03f2 }
            j$.util.Optional r0 = p003j$.util.Optional.empty()     // Catch:{ all -> 0x03f2 }
            cwq r9 = r3.f6626d     // Catch:{ all -> 0x0419 }
            r10 = 34
            r9.mo3869a(r10)     // Catch:{ all -> 0x0419 }
            goto L_0x0409
        L_0x01cd:
            java.lang.String r0 = "interact_activity_class_name"
            java.lang.String r0 = p000.fxk.m9835c(r8, r0)     // Catch:{ all -> 0x03f2 }
            java.lang.String r9 = "interact_activity_package_name"
            java.lang.String r9 = p000.fxk.m9835c(r8, r9)     // Catch:{ all -> 0x03f2 }
            boolean r10 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x03f2 }
            if (r10 == 0) goto L_0x01e1
        L_0x01df:
            goto L_0x0256
        L_0x01e1:
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x03f2 }
            if (r10 != 0) goto L_0x01df
            bit r10 = p000.bit.INTERACT     // Catch:{ all -> 0x03f2 }
            android.net.Uri r10 = p000.cof.m4687a((android.net.Uri) r13, (p000.bit) r10)     // Catch:{ all -> 0x03f2 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x03f2 }
            boolean r12 = r14.f14319c     // Catch:{ all -> 0x03f2 }
            if (r12 != 0) goto L_0x01f6
            goto L_0x01fb
        L_0x01f6:
            r14.mo8751b()     // Catch:{ all -> 0x03f2 }
            r14.f14319c = r6     // Catch:{ all -> 0x03f2 }
        L_0x01fb:
            iix r12 = r14.f14318b     // Catch:{ all -> 0x03f2 }
            dik r12 = (p000.dik) r12     // Catch:{ all -> 0x03f2 }
            r10.getClass()     // Catch:{ all -> 0x03f2 }
            int r13 = r12.f6609a     // Catch:{ all -> 0x03f2 }
            r13 = r13 | 8
            r12.f6609a = r13     // Catch:{ all -> 0x03f2 }
            r12.f6613e = r10     // Catch:{ all -> 0x03f2 }
            dii r10 = p000.dii.f6597d     // Catch:{ all -> 0x03f2 }
            iir r10 = r10.mo8793g()     // Catch:{ all -> 0x03f2 }
            boolean r12 = r10.f14319c     // Catch:{ all -> 0x03f2 }
            if (r12 != 0) goto L_0x0215
            goto L_0x021a
        L_0x0215:
            r10.mo8751b()     // Catch:{ all -> 0x03f2 }
            r10.f14319c = r6     // Catch:{ all -> 0x03f2 }
        L_0x021a:
            iix r12 = r10.f14318b     // Catch:{ all -> 0x03f2 }
            dii r12 = (p000.dii) r12     // Catch:{ all -> 0x03f2 }
            r0.getClass()     // Catch:{ all -> 0x03f2 }
            int r13 = r12.f6599a     // Catch:{ all -> 0x03f2 }
            r13 = r13 | r5
            r12.f6599a = r13     // Catch:{ all -> 0x03f2 }
            r12.f6600b = r0     // Catch:{ all -> 0x03f2 }
            r9.getClass()     // Catch:{ all -> 0x03f2 }
            r0 = r13 | 2
            r12.f6599a = r0     // Catch:{ all -> 0x03f2 }
            r12.f6601c = r9     // Catch:{ all -> 0x03f2 }
            boolean r0 = r14.f14319c     // Catch:{ all -> 0x03f2 }
            if (r0 != 0) goto L_0x0236
            goto L_0x023b
        L_0x0236:
            r14.mo8751b()     // Catch:{ all -> 0x03f2 }
            r14.f14319c = r6     // Catch:{ all -> 0x03f2 }
        L_0x023b:
            iix r0 = r14.f14318b     // Catch:{ all -> 0x03f2 }
            dik r0 = (p000.dik) r0     // Catch:{ all -> 0x03f2 }
            iix r9 = r10.mo8770g()     // Catch:{ all -> 0x03f2 }
            dii r9 = (p000.dii) r9     // Catch:{ all -> 0x03f2 }
            r9.getClass()     // Catch:{ all -> 0x03f2 }
            r0.f6615g = r9     // Catch:{ all -> 0x03f2 }
            int r9 = r0.f6609a     // Catch:{ all -> 0x03f2 }
            r9 = r9 | r11
            r0.f6609a = r9     // Catch:{ all -> 0x03f2 }
            r0 = 33
            r11 = 33
            goto L_0x03c6
        L_0x0256:
            java.lang.String r0 = "SpecialTypeDataResolver: Interact requires package name and activity."
            java.lang.Object[] r9 = new java.lang.Object[r6]     // Catch:{ all -> 0x03f2 }
            p000.cwn.m5510a(r0, r9)     // Catch:{ all -> 0x03f2 }
            j$.util.Optional r0 = p003j$.util.Optional.empty()     // Catch:{ all -> 0x03f2 }
            cwq r9 = r3.f6626d     // Catch:{ all -> 0x0419 }
            r10 = 34
            r9.mo3869a(r10)     // Catch:{ all -> 0x0419 }
            goto L_0x0409
        L_0x026a:
            java.lang.String r9 = "edit_activity_class_name"
            java.lang.String r9 = p000.fxk.m9835c(r8, r9)     // Catch:{ all -> 0x03f2 }
            java.lang.String r10 = "edit_activity_package_name"
            java.lang.String r10 = p000.fxk.m9835c(r8, r10)     // Catch:{ all -> 0x03f2 }
            boolean r16 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x03f2 }
            if (r16 == 0) goto L_0x027e
        L_0x027c:
            goto L_0x038d
        L_0x027e:
            boolean r16 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x03f2 }
            if (r16 != 0) goto L_0x027c
            java.lang.String r15 = "editor_description"
            int r15 = r8.getColumnIndex(r15)     // Catch:{ all -> 0x03f2 }
            r11 = -1
            if (r0 >= r12) goto L_0x028e
            goto L_0x02af
        L_0x028e:
            if (r15 != r11) goto L_0x0291
            goto L_0x029b
        L_0x0291:
            java.lang.String r17 = r8.getString(r15)     // Catch:{ all -> 0x03f2 }
            boolean r17 = android.text.TextUtils.isEmpty(r17)     // Catch:{ all -> 0x03f2 }
            if (r17 == 0) goto L_0x02af
        L_0x029b:
            java.lang.String r0 = "SpecialTypeDataResolver: Edit in API v3 requires description."
            java.lang.Object[] r9 = new java.lang.Object[r6]     // Catch:{ all -> 0x03f2 }
            p000.cwn.m5510a(r0, r9)     // Catch:{ all -> 0x03f2 }
            j$.util.Optional r0 = p003j$.util.Optional.empty()     // Catch:{ all -> 0x03f2 }
            cwq r9 = r3.f6626d     // Catch:{ all -> 0x0419 }
            r10 = 34
            r9.mo3869a(r10)     // Catch:{ all -> 0x0419 }
            goto L_0x0409
        L_0x02af:
            java.lang.String r11 = "editor_promo"
            int r11 = r8.getColumnIndex(r11)     // Catch:{ all -> 0x03f2 }
            android.net.Uri r13 = p000.cof.m4690b(r13)     // Catch:{ all -> 0x03f2 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x03f2 }
            boolean r12 = r14.f14319c     // Catch:{ all -> 0x03f2 }
            if (r12 != 0) goto L_0x02c2
            goto L_0x02c7
        L_0x02c2:
            r14.mo8751b()     // Catch:{ all -> 0x03f2 }
            r14.f14319c = r6     // Catch:{ all -> 0x03f2 }
        L_0x02c7:
            iix r12 = r14.f14318b     // Catch:{ all -> 0x03f2 }
            dik r12 = (p000.dik) r12     // Catch:{ all -> 0x03f2 }
            r13.getClass()     // Catch:{ all -> 0x03f2 }
            int r5 = r12.f6609a     // Catch:{ all -> 0x040d }
            r5 = r5 | 8
            r12.f6609a = r5     // Catch:{ all -> 0x040d }
            r12.f6613e = r13     // Catch:{ all -> 0x040d }
            dii r5 = p000.dii.f6597d     // Catch:{ all -> 0x040d }
            iir r5 = r5.mo8793g()     // Catch:{ all -> 0x040d }
            boolean r12 = r5.f14319c     // Catch:{ all -> 0x040d }
            if (r12 != 0) goto L_0x02e1
            goto L_0x02e6
        L_0x02e1:
            r5.mo8751b()     // Catch:{ all -> 0x040d }
            r5.f14319c = r6     // Catch:{ all -> 0x040d }
        L_0x02e6:
            iix r12 = r5.f14318b     // Catch:{ all -> 0x040d }
            dii r12 = (p000.dii) r12     // Catch:{ all -> 0x040d }
            r9.getClass()     // Catch:{ all -> 0x040d }
            int r13 = r12.f6599a     // Catch:{ all -> 0x040d }
            r18 = 1
            r13 = r13 | 1
            r12.f6599a = r13     // Catch:{ all -> 0x040d }
            r12.f6600b = r9     // Catch:{ all -> 0x040d }
            r10.getClass()     // Catch:{ all -> 0x040d }
            r9 = r13 | 2
            r12.f6599a = r9     // Catch:{ all -> 0x040d }
            r12.f6601c = r10     // Catch:{ all -> 0x040d }
            boolean r9 = r14.f14319c     // Catch:{ all -> 0x040d }
            if (r9 != 0) goto L_0x0305
            goto L_0x030a
        L_0x0305:
            r14.mo8751b()     // Catch:{ all -> 0x040d }
            r14.f14319c = r6     // Catch:{ all -> 0x040d }
        L_0x030a:
            iix r9 = r14.f14318b     // Catch:{ all -> 0x040d }
            dik r9 = (p000.dik) r9     // Catch:{ all -> 0x040d }
            iix r5 = r5.mo8770g()     // Catch:{ all -> 0x040d }
            dii r5 = (p000.dii) r5     // Catch:{ all -> 0x040d }
            r5.getClass()     // Catch:{ all -> 0x040d }
            r9.f6615g = r5     // Catch:{ all -> 0x040d }
            int r5 = r9.f6609a     // Catch:{ all -> 0x040d }
            r10 = 32
            r5 = r5 | r10
            r9.f6609a = r5     // Catch:{ all -> 0x040d }
            r5 = 3
            if (r0 < r5) goto L_0x0389
            dij r0 = p000.dij.f6602d     // Catch:{ all -> 0x040d }
            iir r0 = r0.mo8793g()     // Catch:{ all -> 0x040d }
            java.lang.String r5 = r8.getString(r15)     // Catch:{ all -> 0x040d }
            boolean r9 = r0.f14319c     // Catch:{ all -> 0x040d }
            if (r9 != 0) goto L_0x0332
            goto L_0x0337
        L_0x0332:
            r0.mo8751b()     // Catch:{ all -> 0x040d }
            r0.f14319c = r6     // Catch:{ all -> 0x040d }
        L_0x0337:
            iix r9 = r0.f14318b     // Catch:{ all -> 0x040d }
            dij r9 = (p000.dij) r9     // Catch:{ all -> 0x040d }
            r5.getClass()     // Catch:{ all -> 0x040d }
            int r10 = r9.f6604a     // Catch:{ all -> 0x040d }
            r12 = 1
            r10 = r10 | r12
            r9.f6604a = r10     // Catch:{ all -> 0x040d }
            r9.f6605b = r5     // Catch:{ all -> 0x040d }
            r5 = -1
            if (r11 == r5) goto L_0x0366
            java.lang.String r5 = r8.getString(r11)     // Catch:{ all -> 0x040d }
            boolean r9 = r0.f14319c     // Catch:{ all -> 0x040d }
            if (r9 != 0) goto L_0x0352
            goto L_0x0357
        L_0x0352:
            r0.mo8751b()     // Catch:{ all -> 0x040d }
            r0.f14319c = r6     // Catch:{ all -> 0x040d }
        L_0x0357:
            iix r9 = r0.f14318b     // Catch:{ all -> 0x040d }
            dij r9 = (p000.dij) r9     // Catch:{ all -> 0x040d }
            r5.getClass()     // Catch:{ all -> 0x040d }
            int r10 = r9.f6604a     // Catch:{ all -> 0x040d }
            r11 = 2
            r10 = r10 | r11
            r9.f6604a = r10     // Catch:{ all -> 0x040d }
            r9.f6606c = r5     // Catch:{ all -> 0x040d }
        L_0x0366:
            boolean r5 = r14.f14319c     // Catch:{ all -> 0x040d }
            if (r5 != 0) goto L_0x036b
            goto L_0x0370
        L_0x036b:
            r14.mo8751b()     // Catch:{ all -> 0x040d }
            r14.f14319c = r6     // Catch:{ all -> 0x040d }
        L_0x0370:
            iix r5 = r14.f14318b     // Catch:{ all -> 0x040d }
            dik r5 = (p000.dik) r5     // Catch:{ all -> 0x040d }
            iix r0 = r0.mo8770g()     // Catch:{ all -> 0x040d }
            dij r0 = (p000.dij) r0     // Catch:{ all -> 0x040d }
            r0.getClass()     // Catch:{ all -> 0x040d }
            r5.f6616h = r0     // Catch:{ all -> 0x040d }
            int r0 = r5.f6609a     // Catch:{ all -> 0x040d }
            r0 = r0 | 64
            r5.f6609a = r0     // Catch:{ all -> 0x040d }
            r11 = 31
            goto L_0x03c6
        L_0x0389:
            r11 = 31
            goto L_0x03c6
        L_0x038d:
            java.lang.String r0 = "SpecialTypeDataResolver: Edit requires package name and activity."
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ all -> 0x040d }
            p000.cwn.m5510a(r0, r5)     // Catch:{ all -> 0x040d }
            j$.util.Optional r0 = p003j$.util.Optional.empty()     // Catch:{ all -> 0x040d }
            cwq r5 = r3.f6626d     // Catch:{ all -> 0x03ee }
            r9 = 34
            r5.mo3869a(r9)     // Catch:{ all -> 0x03ee }
            goto L_0x03ec
        L_0x03a0:
            android.net.Uri r0 = p000.cof.m4686a(r13)     // Catch:{ all -> 0x040d }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x040d }
            boolean r5 = r14.f14319c     // Catch:{ all -> 0x040d }
            if (r5 != 0) goto L_0x03ad
            goto L_0x03b2
        L_0x03ad:
            r14.mo8751b()     // Catch:{ all -> 0x040d }
            r14.f14319c = r6     // Catch:{ all -> 0x040d }
        L_0x03b2:
            iix r5 = r14.f14318b     // Catch:{ all -> 0x040d }
            dik r5 = (p000.dik) r5     // Catch:{ all -> 0x040d }
            r0.getClass()     // Catch:{ all -> 0x040d }
            int r9 = r5.f6609a     // Catch:{ all -> 0x040d }
            r9 = r9 | 8
            r5.f6609a = r9     // Catch:{ all -> 0x040d }
            r5.f6613e = r0     // Catch:{ all -> 0x040d }
            r0 = 30
            r11 = 30
        L_0x03c6:
            iix r0 = r14.mo8770g()     // Catch:{ all -> 0x03d6 }
            dik r0 = (p000.dik) r0     // Catch:{ all -> 0x03d6 }
            j$.util.Optional r0 = p003j$.util.Optional.m16285of(r0)     // Catch:{ all -> 0x03d6 }
            cwq r5 = r3.f6626d     // Catch:{ all -> 0x03ee }
            r5.mo3869a(r11)     // Catch:{ all -> 0x03ee }
            goto L_0x03ec
        L_0x03d6:
            r0 = move-exception
            r5 = 1
            goto L_0x0413
        L_0x03da:
            java.lang.String r0 = "SpecialTypeDataResolver: Badge, Edit, Interact and Launch APIs all require a name, description and icon uri."
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ all -> 0x040d }
            p000.cwn.m5510a(r0, r5)     // Catch:{ all -> 0x040d }
            j$.util.Optional r0 = p003j$.util.Optional.empty()     // Catch:{ all -> 0x040d }
            cwq r5 = r3.f6626d     // Catch:{ all -> 0x03ee }
            r9 = 34
            r5.mo3869a(r9)     // Catch:{ all -> 0x03ee }
        L_0x03ec:
            r5 = 1
            goto L_0x0409
        L_0x03ee:
            r0 = move-exception
            r9 = r0
            r5 = 1
            goto L_0x041b
        L_0x03f2:
            r0 = move-exception
            goto L_0x040f
        L_0x03f4:
            java.lang.String r0 = "SpecialTypeDataResolver: Configuration[%s] did not match any known types."
            r5 = 1
            java.lang.Object[] r10 = new java.lang.Object[r5]     // Catch:{ all -> 0x03f2 }
            r10[r6] = r9     // Catch:{ all -> 0x03f2 }
            p000.cwn.m5510a(r0, r10)     // Catch:{ all -> 0x03f2 }
            j$.util.Optional r0 = p003j$.util.Optional.empty()     // Catch:{ all -> 0x03f2 }
            cwq r9 = r3.f6626d     // Catch:{ all -> 0x0419 }
            r10 = 34
            r9.mo3869a(r10)     // Catch:{ all -> 0x0419 }
        L_0x0409:
            r8.close()     // Catch:{ all -> 0x042c }
            goto L_0x0433
        L_0x040d:
            r0 = move-exception
            r5 = 1
        L_0x040f:
            r10 = 34
            r11 = 34
        L_0x0413:
            cwq r9 = r3.f6626d     // Catch:{ all -> 0x0419 }
            r9.mo3869a(r11)     // Catch:{ all -> 0x0419 }
            throw r0     // Catch:{ all -> 0x0419 }
        L_0x0419:
            r0 = move-exception
            r9 = r0
        L_0x041b:
            r8.close()     // Catch:{ all -> 0x041f }
            goto L_0x0424
        L_0x041f:
            r0 = move-exception
            r8 = r0
            p000.ifn.m12935a((java.lang.Throwable) r9, (java.lang.Throwable) r8)     // Catch:{ all -> 0x042c }
        L_0x0424:
            throw r9     // Catch:{ all -> 0x042c }
        L_0x0425:
            if (r8 == 0) goto L_0x0018
            r8.close()     // Catch:{ all -> 0x042c }
            goto L_0x0018
        L_0x042c:
            r0 = move-exception
            goto L_0x0018
        L_0x042f:
            j$.util.Optional r0 = p003j$.util.Optional.empty()
        L_0x0433:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dim.call():java.lang.Object");
    }
}
