package p000;

import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;

/* renamed from: fqs */
/* compiled from: PG */
public final class fqs {

    /* renamed from: a */
    public final PackageManager f10291a;

    public fqs(PackageManager packageManager) {
        this.f10291a = packageManager;
    }

    /* renamed from: a */
    private static final void m9430a(XmlResourceParser xmlResourceParser, String str) {
        if (xmlResourceParser.getEventType() != 3) {
            int lineNumber = xmlResourceParser.getLineNumber();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 45);
            sb.append("Expected an end tag named ");
            sb.append(str);
            sb.append(" (line ");
            sb.append(lineNumber);
            sb.append(")");
            throw new IllegalArgumentException(sb.toString());
        } else if (!str.equals(xmlResourceParser.getName())) {
            int lineNumber2 = xmlResourceParser.getLineNumber();
            String name = xmlResourceParser.getName();
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 58 + String.valueOf(name).length());
            sb2.append("Mismatched end tag at line ");
            sb2.append(lineNumber2);
            sb2.append(". Expected ");
            sb2.append(str);
            sb2.append(" but was ");
            sb2.append(name);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    /* renamed from: b */
    private static final void m9431b(XmlResourceParser xmlResourceParser, String str) {
        if (xmlResourceParser.getEventType() != 2) {
            int lineNumber = xmlResourceParser.getLineNumber();
            StringBuilder sb = new StringBuilder(str.length() + 46);
            sb.append("Expected a start tag named ");
            sb.append(str);
            sb.append(" (line ");
            sb.append(lineNumber);
            sb.append(")");
            throw new IllegalArgumentException(sb.toString());
        } else if (!str.equals(xmlResourceParser.getName())) {
            int lineNumber2 = xmlResourceParser.getLineNumber();
            String name = xmlResourceParser.getName();
            StringBuilder sb2 = new StringBuilder(String.valueOf(name).length() + 53 + str.length());
            sb2.append("Unexpected start tag at line ");
            sb2.append(lineNumber2);
            sb2.append(": ");
            sb2.append(name);
            sb2.append(". Expected ");
            sb2.append(str);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    /* renamed from: a */
    private final void m9429a(XmlResourceParser xmlResourceParser) {
        String name = xmlResourceParser.getName();
        while (xmlResourceParser.next() != 3) {
            int eventType = xmlResourceParser.getEventType();
            if (eventType == 2) {
                m9429a(xmlResourceParser);
            } else if (eventType == 4) {
                xmlResourceParser.next();
                m9430a(xmlResourceParser, name);
                return;
            } else {
                int eventType2 = xmlResourceParser.getEventType();
                StringBuilder sb = new StringBuilder(29);
                sb.append("Unexpected event: ");
                sb.append(eventType2);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        m9430a(xmlResourceParser, name);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List mo6034a(int r20, android.content.pm.PackageInfo r21) {
        /*
            r19 = this;
            r1 = r19
            r0 = r21
            java.lang.String r2 = "#"
            java.lang.String r3 = "phenotype-registration"
            java.lang.String r4 = "phenotype-registrations"
            android.content.pm.PackageManager r5 = r1.f10291a
            java.lang.String r6 = r0.packageName
            android.content.res.Resources r5 = r5.getResourcesForApplication(r6)
            r6 = r20
            android.content.res.XmlResourceParser r5 = r5.getXml(r6)
            if (r5 != 0) goto L_0x0020
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            return r0
        L_0x0020:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            r5.next()     // Catch:{ all -> 0x02cc }
        L_0x0028:
            int r7 = r5.next()     // Catch:{ all -> 0x02cc }
            r8 = 1
            if (r7 == r8) goto L_0x02c8
            int r7 = r5.getEventType()     // Catch:{ all -> 0x02cc }
            r9 = 2
            if (r7 != r9) goto L_0x02ab
            java.lang.String r7 = r5.getName()     // Catch:{ all -> 0x02cc }
            boolean r10 = r4.equals(r7)     // Catch:{ all -> 0x02cc }
            if (r10 == 0) goto L_0x029f
            m9431b(r5, r4)     // Catch:{ all -> 0x02cc }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x02cc }
            r10.<init>()     // Catch:{ all -> 0x02cc }
        L_0x0048:
            int r11 = r5.nextTag()     // Catch:{ all -> 0x02cc }
            r12 = 3
            if (r11 == r12) goto L_0x0296
            java.lang.String r11 = r5.getName()     // Catch:{ all -> 0x02cc }
            boolean r13 = r3.equals(r11)     // Catch:{ all -> 0x02cc }
            if (r13 == 0) goto L_0x0285
            m9431b(r5, r3)     // Catch:{ all -> 0x02cc }
            ifq r13 = p000.ifq.f14004k     // Catch:{ all -> 0x02cc }
            iir r13 = r13.mo8793g()     // Catch:{ all -> 0x02cc }
            r15 = 0
        L_0x0063:
            int r9 = r5.nextTag()     // Catch:{ all -> 0x02cc }
            java.lang.String r14 = "configuration-package"
            java.lang.String r8 = "auto-subpackage"
            r17 = r7
            r7 = 4
            if (r9 != r12) goto L_0x00f8
            m9430a((android.content.res.XmlResourceParser) r5, (java.lang.String) r3)     // Catch:{ all -> 0x02cc }
            iix r9 = r13.f14318b     // Catch:{ all -> 0x02cc }
            ifq r9 = (p000.ifq) r9     // Catch:{ all -> 0x02cc }
            java.lang.String r9 = r9.f14007b     // Catch:{ all -> 0x02cc }
            boolean r18 = r9.isEmpty()     // Catch:{ all -> 0x02cc }
            if (r18 != 0) goto L_0x00f0
            if (r15 == 0) goto L_0x00c8
            boolean r15 = r9.contains(r2)     // Catch:{ all -> 0x02cc }
            if (r15 != 0) goto L_0x00af
            java.lang.String r7 = r0.packageName     // Catch:{ all -> 0x02cc }
            java.lang.String r8 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x02cc }
            int r8 = r8.length()     // Catch:{ all -> 0x02cc }
            r12 = 1
            int r8 = r8 + r12
            java.lang.String r12 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x02cc }
            int r12 = r12.length()     // Catch:{ all -> 0x02cc }
            int r8 = r8 + r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x02cc }
            r12.<init>(r8)     // Catch:{ all -> 0x02cc }
            r12.append(r9)     // Catch:{ all -> 0x02cc }
            r12.append(r2)     // Catch:{ all -> 0x02cc }
            r12.append(r7)     // Catch:{ all -> 0x02cc }
            java.lang.String r9 = r12.toString()     // Catch:{ all -> 0x02cc }
            goto L_0x00c8
        L_0x00af:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x02cc }
            java.lang.Object[] r3 = new java.lang.Object[r7]     // Catch:{ all -> 0x02cc }
            r4 = 0
            r3[r4] = r8     // Catch:{ all -> 0x02cc }
            r4 = 1
            r3[r4] = r14     // Catch:{ all -> 0x02cc }
            r4 = 2
            r3[r4] = r2     // Catch:{ all -> 0x02cc }
            r3[r12] = r9     // Catch:{ all -> 0x02cc }
            java.lang.String r2 = "When %s is present, %s should not contain subpackage separator %s (config package=%s)"
            java.lang.String r2 = java.lang.String.format(r2, r3)     // Catch:{ all -> 0x02cc }
            r0.<init>(r2)     // Catch:{ all -> 0x02cc }
            throw r0     // Catch:{ all -> 0x02cc }
        L_0x00c8:
            boolean r7 = r13.f14319c     // Catch:{ all -> 0x02cc }
            if (r7 != 0) goto L_0x00cd
            goto L_0x00d3
        L_0x00cd:
            r13.mo8751b()     // Catch:{ all -> 0x02cc }
            r7 = 0
            r13.f14319c = r7     // Catch:{ all -> 0x02cc }
        L_0x00d3:
            iix r7 = r13.f14318b     // Catch:{ all -> 0x02cc }
            ifq r7 = (p000.ifq) r7     // Catch:{ all -> 0x02cc }
            r9.getClass()     // Catch:{ all -> 0x02cc }
            int r8 = r7.f14006a     // Catch:{ all -> 0x02cc }
            r12 = 1
            r8 = r8 | r12
            r7.f14006a = r8     // Catch:{ all -> 0x02cc }
            r7.f14007b = r9     // Catch:{ all -> 0x02cc }
            iix r7 = r13.mo8770g()     // Catch:{ all -> 0x02cc }
            ifq r7 = (p000.ifq) r7     // Catch:{ all -> 0x02cc }
            r10.add(r7)     // Catch:{ all -> 0x02cc }
            r14 = 2
            r16 = 1
            goto L_0x028d
        L_0x00f0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x02cc }
            java.lang.String r2 = "Empty configuration package"
            r0.<init>(r2)     // Catch:{ all -> 0x02cc }
            throw r0     // Catch:{ all -> 0x02cc }
        L_0x00f8:
            java.lang.String r9 = r5.getName()     // Catch:{ all -> 0x02cc }
            int r18 = r9.hashCode()
            java.lang.String r7 = "experiment-ids"
            java.lang.String r12 = "log-sources"
            switch(r18) {
                case -995427962: goto L_0x0133;
                case -260675356: goto L_0x012b;
                case 515651183: goto L_0x0123;
                case 770547247: goto L_0x011b;
                case 1674321665: goto L_0x0111;
                case 2055708904: goto L_0x0108;
                default: goto L_0x0107;
            }
        L_0x0107:
            goto L_0x013d
        L_0x0108:
            boolean r8 = r9.equals(r7)
            if (r8 == 0) goto L_0x0107
            r8 = 5
            goto L_0x013e
        L_0x0111:
            java.lang.String r8 = "configuration-version"
            boolean r8 = r9.equals(r8)
            if (r8 == 0) goto L_0x0107
            r8 = 1
            goto L_0x013e
        L_0x011b:
            boolean r8 = r9.equals(r12)
            if (r8 == 0) goto L_0x0107
            r8 = 2
            goto L_0x013e
        L_0x0123:
            boolean r8 = r9.equals(r14)
            if (r8 == 0) goto L_0x0107
            r8 = 0
            goto L_0x013e
        L_0x012b:
            boolean r8 = r9.equals(r8)
            if (r8 == 0) goto L_0x0107
            r8 = 4
            goto L_0x013e
        L_0x0133:
            java.lang.String r8 = "params"
            boolean r8 = r9.equals(r8)
            if (r8 == 0) goto L_0x0107
            r8 = 3
            goto L_0x013e
        L_0x013d:
            r8 = -1
        L_0x013e:
            if (r8 == 0) goto L_0x0258
            r14 = 1
            if (r8 == r14) goto L_0x0237
            r14 = 2
            if (r8 == r14) goto L_0x0203
            r14 = 3
            if (r8 == r14) goto L_0x01d9
            r14 = 4
            if (r8 == r14) goto L_0x01cc
            r14 = 5
            if (r8 == r14) goto L_0x0157
            r1.m9429a(r5)     // Catch:{ all -> 0x02cc }
        L_0x0152:
            r14 = 2
        L_0x0153:
            r16 = 1
            goto L_0x027b
        L_0x0157:
            m9431b(r5, r7)     // Catch:{ all -> 0x02cc }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x02cc }
            r8.<init>()     // Catch:{ all -> 0x02cc }
        L_0x0160:
            int r14 = r5.nextTag()     // Catch:{ all -> 0x02cc }
            r12 = 3
            if (r14 == r12) goto L_0x018a
            java.lang.String r12 = r5.getName()     // Catch:{ all -> 0x02cc }
            java.lang.String r14 = "experiment-id"
            boolean r14 = r14.equals(r12)     // Catch:{ all -> 0x02cc }
            if (r14 == 0) goto L_0x0183
            java.lang.String r14 = r5.nextText()     // Catch:{ all -> 0x02cc }
            int r14 = java.lang.Integer.parseInt(r14)     // Catch:{ all -> 0x02cc }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ all -> 0x02cc }
            r8.add(r14)     // Catch:{ all -> 0x02cc }
            goto L_0x0186
        L_0x0183:
            r1.m9429a(r5)     // Catch:{ all -> 0x02cc }
        L_0x0186:
            m9430a((android.content.res.XmlResourceParser) r5, (java.lang.String) r12)     // Catch:{ all -> 0x02cc }
            goto L_0x0160
        L_0x018a:
            m9430a((android.content.res.XmlResourceParser) r5, (java.lang.String) r7)     // Catch:{ all -> 0x02cc }
            boolean r7 = r13.f14319c     // Catch:{ all -> 0x02cc }
            if (r7 != 0) goto L_0x0193
            goto L_0x0199
        L_0x0193:
            r13.mo8751b()     // Catch:{ all -> 0x02cc }
            r7 = 0
            r13.f14319c = r7     // Catch:{ all -> 0x02cc }
        L_0x0199:
            iix r7 = r13.f14318b     // Catch:{ all -> 0x02cc }
            ifq r7 = (p000.ifq) r7     // Catch:{ all -> 0x02cc }
            ijc r12 = r7.f14010e     // Catch:{ all -> 0x02cc }
            boolean r12 = r12.mo8521a()     // Catch:{ all -> 0x02cc }
            if (r12 != 0) goto L_0x01ad
            ijc r12 = r7.f14010e     // Catch:{ all -> 0x02cc }
            ijc r12 = p000.iix.m13606a((p000.ijc) r12)     // Catch:{ all -> 0x02cc }
            r7.f14010e = r12     // Catch:{ all -> 0x02cc }
        L_0x01ad:
            ijc r7 = r7.f14010e     // Catch:{ all -> 0x02cc }
            p000.igz.m12986a((java.lang.Iterable) r8, (java.util.List) r7)     // Catch:{ all -> 0x02cc }
            boolean r7 = r13.f14319c     // Catch:{ all -> 0x02cc }
            if (r7 != 0) goto L_0x01b7
            goto L_0x01bd
        L_0x01b7:
            r13.mo8751b()     // Catch:{ all -> 0x02cc }
            r7 = 0
            r13.f14319c = r7     // Catch:{ all -> 0x02cc }
        L_0x01bd:
            iix r7 = r13.f14318b     // Catch:{ all -> 0x02cc }
            ifq r7 = (p000.ifq) r7     // Catch:{ all -> 0x02cc }
            int r8 = r7.f14006a     // Catch:{ all -> 0x02cc }
            r12 = 8
            r8 = r8 | r12
            r7.f14006a = r8     // Catch:{ all -> 0x02cc }
            r8 = 1
            r7.f14012g = r8     // Catch:{ all -> 0x02cc }
            goto L_0x0152
        L_0x01cc:
            java.lang.String r7 = r5.nextText()     // Catch:{ all -> 0x02cc }
            boolean r15 = java.lang.Boolean.parseBoolean(r7)     // Catch:{ all -> 0x02cc }
            r14 = 2
            r16 = 1
            goto L_0x027b
        L_0x01d9:
            java.lang.String r7 = r5.nextText()     // Catch:{ all -> 0x02cc }
            r8 = 8
            byte[] r7 = android.util.Base64.decode(r7, r8)     // Catch:{ all -> 0x02cc }
            ihw r7 = p000.ihw.m13162a((byte[]) r7)     // Catch:{ all -> 0x02cc }
            boolean r8 = r13.f14319c     // Catch:{ all -> 0x02cc }
            if (r8 != 0) goto L_0x01ec
            goto L_0x01f2
        L_0x01ec:
            r13.mo8751b()     // Catch:{ all -> 0x02cc }
            r8 = 0
            r13.f14319c = r8     // Catch:{ all -> 0x02cc }
        L_0x01f2:
            iix r8 = r13.f14318b     // Catch:{ all -> 0x02cc }
            ifq r8 = (p000.ifq) r8     // Catch:{ all -> 0x02cc }
            r7.getClass()     // Catch:{ all -> 0x02cc }
            int r12 = r8.f14006a     // Catch:{ all -> 0x02cc }
            r14 = 4
            r12 = r12 | r14
            r8.f14006a = r12     // Catch:{ all -> 0x02cc }
            r8.f14011f = r7     // Catch:{ all -> 0x02cc }
            goto L_0x0152
        L_0x0203:
            m9431b(r5, r12)     // Catch:{ all -> 0x02cc }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x02cc }
            r7.<init>()     // Catch:{ all -> 0x02cc }
        L_0x020c:
            int r8 = r5.nextTag()     // Catch:{ all -> 0x02cc }
            r14 = 3
            if (r8 == r14) goto L_0x022e
            java.lang.String r8 = r5.getName()     // Catch:{ all -> 0x02cc }
            java.lang.String r14 = "log-source"
            boolean r14 = r14.equals(r8)     // Catch:{ all -> 0x02cc }
            if (r14 == 0) goto L_0x0227
            java.lang.String r14 = r5.nextText()     // Catch:{ all -> 0x02cc }
            r7.add(r14)     // Catch:{ all -> 0x02cc }
            goto L_0x022a
        L_0x0227:
            r1.m9429a(r5)     // Catch:{ all -> 0x02cc }
        L_0x022a:
            m9430a((android.content.res.XmlResourceParser) r5, (java.lang.String) r8)     // Catch:{ all -> 0x02cc }
            goto L_0x020c
        L_0x022e:
            m9430a((android.content.res.XmlResourceParser) r5, (java.lang.String) r12)     // Catch:{ all -> 0x02cc }
            r13.mo8753b((java.lang.Iterable) r7)     // Catch:{ all -> 0x02cc }
            goto L_0x0152
        L_0x0237:
            java.lang.String r7 = r5.nextText()     // Catch:{ all -> 0x02cc }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ all -> 0x02cc }
            boolean r8 = r13.f14319c     // Catch:{ all -> 0x02cc }
            if (r8 != 0) goto L_0x0244
            goto L_0x024a
        L_0x0244:
            r13.mo8751b()     // Catch:{ all -> 0x02cc }
            r8 = 0
            r13.f14319c = r8     // Catch:{ all -> 0x02cc }
        L_0x024a:
            iix r8 = r13.f14318b     // Catch:{ all -> 0x02cc }
            ifq r8 = (p000.ifq) r8     // Catch:{ all -> 0x02cc }
            int r12 = r8.f14006a     // Catch:{ all -> 0x02cc }
            r14 = 2
            r12 = r12 | r14
            r8.f14006a = r12     // Catch:{ all -> 0x02cc }
            r8.f14008c = r7     // Catch:{ all -> 0x02cc }
            goto L_0x0153
        L_0x0258:
            r14 = 2
            java.lang.String r7 = r5.nextText()     // Catch:{ all -> 0x02cc }
            boolean r8 = r13.f14319c     // Catch:{ all -> 0x02cc }
            if (r8 != 0) goto L_0x0263
            r8 = 0
            goto L_0x0269
        L_0x0263:
            r13.mo8751b()     // Catch:{ all -> 0x02cc }
            r8 = 0
            r13.f14319c = r8     // Catch:{ all -> 0x02cc }
        L_0x0269:
            iix r12 = r13.f14318b     // Catch:{ all -> 0x02cc }
            ifq r12 = (p000.ifq) r12     // Catch:{ all -> 0x02cc }
            r7.getClass()     // Catch:{ all -> 0x02cc }
            int r8 = r12.f14006a     // Catch:{ all -> 0x02cc }
            r16 = 1
            r8 = r8 | 1
            r12.f14006a = r8     // Catch:{ all -> 0x02cc }
            r12.f14007b = r7     // Catch:{ all -> 0x02cc }
        L_0x027b:
            m9430a((android.content.res.XmlResourceParser) r5, (java.lang.String) r9)     // Catch:{ all -> 0x02cc }
            r7 = r17
            r8 = 1
            r9 = 2
            r12 = 3
            goto L_0x0063
        L_0x0285:
            r17 = r7
            r14 = 2
            r16 = 1
            r1.m9429a(r5)     // Catch:{ all -> 0x02cc }
        L_0x028d:
            m9430a((android.content.res.XmlResourceParser) r5, (java.lang.String) r11)     // Catch:{ all -> 0x02cc }
            r7 = r17
            r8 = 1
            r9 = 2
            goto L_0x0048
        L_0x0296:
            r17 = r7
            m9430a((android.content.res.XmlResourceParser) r5, (java.lang.String) r4)     // Catch:{ all -> 0x02cc }
            r6.addAll(r10)     // Catch:{ all -> 0x02cc }
            goto L_0x02a4
        L_0x029f:
            r17 = r7
            r1.m9429a(r5)     // Catch:{ all -> 0x02cc }
        L_0x02a4:
            r7 = r17
            m9430a((android.content.res.XmlResourceParser) r5, (java.lang.String) r7)     // Catch:{ all -> 0x02cc }
            goto L_0x0028
        L_0x02ab:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x02cc }
            int r2 = r5.getEventType()     // Catch:{ all -> 0x02cc }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x02cc }
            r4 = 29
            r3.<init>(r4)     // Catch:{ all -> 0x02cc }
            java.lang.String r4 = "Unexpected event: "
            r3.append(r4)     // Catch:{ all -> 0x02cc }
            r3.append(r2)     // Catch:{ all -> 0x02cc }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x02cc }
            r0.<init>(r2)     // Catch:{ all -> 0x02cc }
            throw r0     // Catch:{ all -> 0x02cc }
        L_0x02c8:
            r5.close()
            return r6
        L_0x02cc:
            r0 = move-exception
            r5.close()
            goto L_0x02d2
        L_0x02d1:
            throw r0
        L_0x02d2:
            goto L_0x02d1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.fqs.mo6034a(int, android.content.pm.PackageInfo):java.util.List");
    }
}
