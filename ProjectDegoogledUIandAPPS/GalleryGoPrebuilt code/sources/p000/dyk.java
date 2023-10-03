package p000;

/* renamed from: dyk */
/* compiled from: PG */
final /* synthetic */ class dyk implements hpr {

    /* renamed from: a */
    private final dyr f7653a;

    /* renamed from: b */
    private final dyj f7654b;

    public dyk(dyr dyr, dyj dyj) {
        this.f7653a = dyr;
        this.f7654b = dyj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00e2, code lost:
        if (r8 < 0) goto L_0x0110;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x010a, code lost:
        if (p000.C0643xp.m15944b(r7, r6) >= 0) goto L_0x010c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0110, code lost:
        r8 = r1.f7699b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0114, code lost:
        if (r2 == 0) goto L_0x0253;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0116, code lost:
        if (r3 == 0) goto L_0x024b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0118, code lost:
        if (r3 == r10) goto L_0x017e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x011b, code lost:
        if (r3 != 2) goto L_0x015d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0123, code lost:
        if (p000.C0643xp.m15949c(r7, r8.f8271a) != false) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0127, code lost:
        if (r8.f8280j == null) goto L_0x012a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x012a, code lost:
        r8.f8280j = p000.ehd.m7468a(p000.ehc.MONTH_FORMAT, r8.f8273c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0134, code lost:
        r3 = r8.f8280j.format(r7.getTime());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0143, code lost:
        if (r8.f8279i == null) goto L_0x0146;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0146, code lost:
        r8.f8279i = p000.ehd.m7468a(p000.ehc.MONTH_FORMAT_SAME_YEAR, r8.f8273c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0150, code lost:
        r3 = r8.f8279i.format(r7.getTime());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x015d, code lost:
        r1 = p000.C0643xp.m15945b(r2);
        r4 = new java.lang.StringBuilder(r1.length() + 24);
        r4.append("Unknown timeGranularity ");
        r4.append(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x017d, code lost:
        throw new java.lang.IllegalArgumentException(r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x017e, code lost:
        r3 = r8.f8271a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0184, code lost:
        if (p000.C0643xp.m15944b(r7, r3) == 0) goto L_0x021d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x018a, code lost:
        if (p000.C0643xp.m15937a(r7, r3) != 0) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0197, code lost:
        if (r7.get(6) == (r3.get(6) - 1)) goto L_0x019a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x019c, code lost:
        if (r8.f8275e != null) goto L_0x01ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x019e, code lost:
        r8.f8275e = r8.f8272b.getResources().getString(com.google.android.apps.photosgo.R.string.yesterday_date_header);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01ad, code lost:
        r3 = r8.f8275e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01b2, code lost:
        r9 = p000.C0643xp.m15937a(r7, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01b6, code lost:
        if (r9 != 0) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01b8, code lost:
        r9 = java.lang.Integer.compare(r7.get(3), r3.get(3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01c7, code lost:
        if (r9 == 0) goto L_0x0203;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01cd, code lost:
        if (p000.C0643xp.m15949c(r7, r3) != false) goto L_0x01e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01d1, code lost:
        if (r8.f8278h == null) goto L_0x01d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01d4, code lost:
        r8.f8278h = p000.ehd.m7468a(p000.ehc.DAY_FORMAT, r8.f8273c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01de, code lost:
        r3 = r8.f8278h.format(r7.getTime());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01eb, code lost:
        if (r8.f8277g == null) goto L_0x01ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01ee, code lost:
        r8.f8277g = p000.ehd.m7468a(p000.ehc.DAY_FORMAT_SAME_YEAR, r8.f8273c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01f8, code lost:
        r3 = r8.f8277g.format(r7.getTime());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0205, code lost:
        if (r8.f8276f == null) goto L_0x0208;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0208, code lost:
        r8.f8276f = p000.ehd.m7468a(p000.ehc.DAY_FORMAT_SAME_WEEK, r8.f8273c);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0212, code lost:
        r3 = r8.f8276f.format(r7.getTime());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0220, code lost:
        if (r8.f8274d != null) goto L_0x0231;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0222, code lost:
        r8.f8274d = r8.f8272b.getResources().getString(com.google.android.apps.photosgo.R.string.today_date_header);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0231, code lost:
        r3 = r8.f8274d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0233, code lost:
        r0 = r11;
        r4 = r15;
        r4.add(new p000.dvi(new p000.dvs(r3, r14.f8286b + r14.f8287c)));
        r16 = r7;
        r7 = r6;
        r6 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0252, code lost:
        throw new java.lang.IllegalArgumentException("Cannot format date with TimeGranularity.NONE.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0255, code lost:
        throw null;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object mo1484a(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            dyr r1 = r0.f7653a
            dyj r2 = r0.f7654b
            r3 = r18
            java.util.List r3 = (java.util.List) r3
            dxy r4 = r2.mo4567b()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r3)
            dxy r3 = p000.dxy.DEFAULT_SORT_METHOD
            int r3 = r4.ordinal()
            java.lang.String r6 = "Unknown SortMethod "
            r7 = 30
            r8 = 3
            r9 = 2
            r10 = 1
            if (r3 == 0) goto L_0x004b
            if (r3 == r10) goto L_0x004b
            if (r3 == r9) goto L_0x0045
            if (r3 != r8) goto L_0x002e
            java.util.Comparator r3 = p000.dyn.f7657a
            java.util.Collections.sort(r5, r3)
            goto L_0x0050
        L_0x002e:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            int r2 = r4.f7618e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r7)
            r3.append(r6)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x0045:
            java.util.Comparator r3 = p000.dym.f7656a
            java.util.Collections.sort(r5, r3)
            goto L_0x0050
        L_0x004b:
            java.util.Comparator r3 = p000.dyl.f7655a
            java.util.Collections.sort(r5, r3)
        L_0x0050:
            int r3 = r4.ordinal()
            if (r3 == 0) goto L_0x007c
            if (r3 == r10) goto L_0x007c
            if (r3 == r9) goto L_0x0074
            if (r3 != r8) goto L_0x005d
            goto L_0x0074
        L_0x005d:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            int r2 = r4.f7618e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r7)
            r3.append(r6)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x0074:
            dzc r1 = r1.f7664c
            java.util.List r1 = r1.mo4604a(r5)
            goto L_0x0273
        L_0x007c:
            dzc r1 = r1.f7664c
            int r2 = r2.mo4568c()
            if (r2 == 0) goto L_0x0274
            if (r2 == r10) goto L_0x026f
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            boolean r6 = r5.isEmpty()
            if (r6 != 0) goto L_0x026d
            java.util.TimeZone r6 = p000.ehe.f8281a
            java.util.Calendar r6 = java.util.Calendar.getInstance(r6)
            java.util.TimeZone r7 = p000.ehe.f8281a
            java.util.Calendar r7 = java.util.Calendar.getInstance(r7)
            r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r6.setTimeInMillis(r11)
            int r11 = r5.size()
            r12 = 0
        L_0x00aa:
            if (r12 >= r11) goto L_0x026d
            java.lang.Object r13 = r5.get(r12)
            cxi r13 = (p000.cxi) r13
            ehf r14 = r13.f5917i
            if (r14 == 0) goto L_0x00b7
            goto L_0x00b9
        L_0x00b7:
            ehf r14 = p000.ehf.f8283d
        L_0x00b9:
            r15 = r4
            long r3 = r14.f8286b
            long r8 = r14.f8287c
            long r3 = r3 + r8
            long r8 = p000.dzc.f7698a
            long r3 = r3 - r8
            r7.setTimeInMillis(r3)
            int r3 = r2 + -1
            if (r2 == 0) goto L_0x026a
            if (r3 == 0) goto L_0x0256
            if (r3 == r10) goto L_0x0106
            r4 = 2
            if (r3 != r4) goto L_0x00e5
            int r8 = p000.C0643xp.m15937a((java.util.Calendar) r7, (java.util.Calendar) r6)
            if (r8 != 0) goto L_0x00e2
            int r8 = r7.get(r4)
            int r9 = r6.get(r4)
            int r8 = java.lang.Integer.compare(r8, r9)
        L_0x00e2:
            if (r8 >= 0) goto L_0x010c
            goto L_0x0110
        L_0x00e5:
            java.lang.String r1 = p000.C0643xp.m15945b((int) r2)
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            int r3 = r1.length()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r3 = r3 + 25
            r4.<init>(r3)
            java.lang.String r3 = "Unknown time granularity "
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r2.<init>(r1)
            throw r2
        L_0x0106:
            int r4 = p000.C0643xp.m15944b((java.util.Calendar) r7, (java.util.Calendar) r6)
            if (r4 < 0) goto L_0x0110
        L_0x010c:
            r0 = r11
            r4 = r15
            goto L_0x0258
        L_0x0110:
            dvs r4 = new dvs
            ehd r8 = r1.f7699b
            if (r2 == 0) goto L_0x0253
            if (r3 == 0) goto L_0x024b
            if (r3 == r10) goto L_0x017e
            r9 = 2
            if (r3 != r9) goto L_0x015d
            java.util.Calendar r3 = r8.f8271a
            boolean r3 = p000.C0643xp.m15949c(r7, r3)
            if (r3 != 0) goto L_0x0141
            java.text.DateFormat r3 = r8.f8280j
            if (r3 == 0) goto L_0x012a
            goto L_0x0134
        L_0x012a:
            ehc r3 = p000.ehc.MONTH_FORMAT
            java.util.Locale r9 = r8.f8273c
            java.text.DateFormat r3 = p000.ehd.m7468a(r3, r9)
            r8.f8280j = r3
        L_0x0134:
            java.text.DateFormat r3 = r8.f8280j
            java.util.Date r8 = r7.getTime()
            java.lang.String r3 = r3.format(r8)
            r10 = 3
            goto L_0x0233
        L_0x0141:
            java.text.DateFormat r3 = r8.f8279i
            if (r3 == 0) goto L_0x0146
            goto L_0x0150
        L_0x0146:
            ehc r3 = p000.ehc.MONTH_FORMAT_SAME_YEAR
            java.util.Locale r9 = r8.f8273c
            java.text.DateFormat r3 = p000.ehd.m7468a(r3, r9)
            r8.f8279i = r3
        L_0x0150:
            java.text.DateFormat r3 = r8.f8279i
            java.util.Date r8 = r7.getTime()
            java.lang.String r3 = r3.format(r8)
            r10 = 3
            goto L_0x0233
        L_0x015d:
            java.lang.String r1 = p000.C0643xp.m15945b((int) r2)
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            int r3 = r1.length()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r3 = r3 + 24
            r4.<init>(r3)
            java.lang.String r3 = "Unknown timeGranularity "
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r2.<init>(r1)
            throw r2
        L_0x017e:
            java.util.Calendar r3 = r8.f8271a
            int r9 = p000.C0643xp.m15944b((java.util.Calendar) r7, (java.util.Calendar) r3)
            if (r9 == 0) goto L_0x021d
            int r9 = p000.C0643xp.m15937a((java.util.Calendar) r7, (java.util.Calendar) r3)
            if (r9 != 0) goto L_0x01b2
            r9 = 6
            int r10 = r7.get(r9)
            int r9 = r3.get(r9)
            int r9 = r9 + -1
            if (r10 == r9) goto L_0x019a
            goto L_0x01b2
        L_0x019a:
            java.lang.String r3 = r8.f8275e
            if (r3 != 0) goto L_0x01ad
            android.content.Context r3 = r8.f8272b
            android.content.res.Resources r3 = r3.getResources()
            r9 = 2131886430(0x7f12015e, float:1.9407439E38)
            java.lang.String r3 = r3.getString(r9)
            r8.f8275e = r3
        L_0x01ad:
            java.lang.String r3 = r8.f8275e
            r10 = 3
            goto L_0x0233
        L_0x01b2:
            int r9 = p000.C0643xp.m15937a((java.util.Calendar) r7, (java.util.Calendar) r3)
            if (r9 != 0) goto L_0x01c6
            r10 = 3
            int r9 = r7.get(r10)
            int r0 = r3.get(r10)
            int r9 = java.lang.Integer.compare(r9, r0)
            goto L_0x01c7
        L_0x01c6:
            r10 = 3
        L_0x01c7:
            if (r9 == 0) goto L_0x0203
            boolean r0 = p000.C0643xp.m15949c(r7, r3)
            if (r0 != 0) goto L_0x01e9
            java.text.DateFormat r0 = r8.f8278h
            if (r0 == 0) goto L_0x01d4
            goto L_0x01de
        L_0x01d4:
            ehc r0 = p000.ehc.DAY_FORMAT
            java.util.Locale r3 = r8.f8273c
            java.text.DateFormat r0 = p000.ehd.m7468a(r0, r3)
            r8.f8278h = r0
        L_0x01de:
            java.text.DateFormat r0 = r8.f8278h
            java.util.Date r3 = r7.getTime()
            java.lang.String r3 = r0.format(r3)
            goto L_0x0233
        L_0x01e9:
            java.text.DateFormat r0 = r8.f8277g
            if (r0 == 0) goto L_0x01ee
            goto L_0x01f8
        L_0x01ee:
            ehc r0 = p000.ehc.DAY_FORMAT_SAME_YEAR
            java.util.Locale r3 = r8.f8273c
            java.text.DateFormat r0 = p000.ehd.m7468a(r0, r3)
            r8.f8277g = r0
        L_0x01f8:
            java.text.DateFormat r0 = r8.f8277g
            java.util.Date r3 = r7.getTime()
            java.lang.String r3 = r0.format(r3)
            goto L_0x0233
        L_0x0203:
            java.text.DateFormat r0 = r8.f8276f
            if (r0 == 0) goto L_0x0208
            goto L_0x0212
        L_0x0208:
            ehc r0 = p000.ehc.DAY_FORMAT_SAME_WEEK
            java.util.Locale r3 = r8.f8273c
            java.text.DateFormat r0 = p000.ehd.m7468a(r0, r3)
            r8.f8276f = r0
        L_0x0212:
            java.text.DateFormat r0 = r8.f8276f
            java.util.Date r3 = r7.getTime()
            java.lang.String r3 = r0.format(r3)
            goto L_0x0233
        L_0x021d:
            r10 = 3
            java.lang.String r0 = r8.f8274d
            if (r0 != 0) goto L_0x0231
            android.content.Context r0 = r8.f8272b
            android.content.res.Resources r0 = r0.getResources()
            r3 = 2131886416(0x7f120150, float:1.940741E38)
            java.lang.String r0 = r0.getString(r3)
            r8.f8274d = r0
        L_0x0231:
            java.lang.String r3 = r8.f8274d
        L_0x0233:
            long r8 = r14.f8286b
            r0 = r11
            long r10 = r14.f8287c
            long r8 = r8 + r10
            r4.<init>(r3, r8)
            dvi r3 = new dvi
            r3.<init>(r4)
            r4 = r15
            r4.add(r3)
            r16 = r7
            r7 = r6
            r6 = r16
            goto L_0x0258
        L_0x024b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Cannot format date with TimeGranularity.NONE."
            r0.<init>(r1)
            throw r0
        L_0x0253:
            r0 = 0
            throw r0
        L_0x0256:
            r0 = r11
            r4 = r15
        L_0x0258:
            dwv r3 = p000.ede.m7258a((p000.cxi) r13)
            r4.add(r3)
            int r12 = r12 + 1
            r11 = r0
            r8 = 3
            r9 = 2
            r10 = 1
            r0 = r17
            goto L_0x00aa
        L_0x026a:
            r0 = 0
            throw r0
        L_0x026d:
            r1 = r4
            goto L_0x0273
        L_0x026f:
            java.util.List r1 = r1.mo4604a(r5)
        L_0x0273:
            return r1
        L_0x0274:
            r0 = 0
            goto L_0x0278
        L_0x0277:
            throw r0
        L_0x0278:
            goto L_0x0277
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dyk.mo1484a(java.lang.Object):java.lang.Object");
    }
}
