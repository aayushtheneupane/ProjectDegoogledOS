package com.android.vcard;

import android.util.Log;
import com.android.vcard.exception.VCardAgentNotSupportedException;
import com.android.vcard.exception.VCardException;
import com.android.vcard.exception.VCardInvalidCommentLineException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.vcard.g */
class C1500g {
    private boolean mCanceled;
    protected VCardParserImpl_V21$CustomBufferedReader mReader;

    /* renamed from: uM */
    protected final String f2377uM = VCardConfig.DEFAULT_INTERMEDIATE_CHARSET;

    /* renamed from: vM */
    private final List f2378vM = new ArrayList();

    /* renamed from: wM */
    protected String f2379wM;

    /* renamed from: xM */
    protected final Set f2380xM = new HashSet();

    /* renamed from: yM */
    protected final Set f2381yM = new HashSet();

    public C1500g(int i) {
    }

    /* renamed from: Po */
    private void m3908Po() {
        for (VCardInterpreter onEntryStarted : this.f2378vM) {
            onEntryStarted.onEntryStarted();
        }
        mo8494Uk();
        for (VCardInterpreter onEntryEnded : this.f2378vM) {
            onEntryEnded.onEntryEnded();
        }
    }

    /* renamed from: Qo */
    private boolean m3909Qo() {
        this.f2379wM = VCardConstants.PARAM_ENCODING_8BIT;
        if (!mo8504oa(true)) {
            return false;
        }
        for (VCardInterpreter onEntryStarted : this.f2378vM) {
            onEntryStarted.onEntryStarted();
        }
        mo8494Uk();
        for (VCardInterpreter onEntryEnded : this.f2378vM) {
            onEntryEnded.onEntryEnded();
        }
        return true;
    }

    /* renamed from: f */
    private boolean m3910f(char c) {
        if (c < 'a' || c > 'z') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    /* renamed from: lb */
    private String m3911lb(String str) {
        int indexOf = str.indexOf(":");
        if (indexOf <= -1) {
            return null;
        }
        int indexOf2 = str.indexOf(";");
        if (indexOf == -1) {
            indexOf = indexOf2;
        } else if (indexOf2 != -1) {
            indexOf = Math.min(indexOf, indexOf2);
        }
        return str.substring(0, indexOf).toUpperCase();
    }

    /* renamed from: mb */
    private String m3912mb(String str) {
        if (!str.trim().endsWith("=")) {
            return str;
        }
        int length = str.length() - 1;
        do {
        } while (str.charAt(length) != '=');
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, length + 1));
        sb.append(VCardBuilder.VCARD_END_OF_LINE);
        while (true) {
            String line = getLine();
            if (line == null) {
                throw new VCardException("File ended during parsing a Quoted-Printable String");
            } else if (line.trim().endsWith("=")) {
                int length2 = line.length() - 1;
                do {
                } while (line.charAt(length2) != '=');
                sb.append(line.substring(0, length2 + 1));
                sb.append(VCardBuilder.VCARD_END_OF_LINE);
            } else {
                sb.append(line);
                return sb.toString();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: Qa */
    public String mo8488Qa(String str) {
        String peekLine;
        StringBuilder Pa = C0632a.m1011Pa(str);
        while (true) {
            peekLine = peekLine();
            if (peekLine != null) {
                String lb = m3911lb(peekLine);
                if (mo8489Qk().contains(lb) || VCardConstants.PROPERTY_X_ANDROID_CUSTOM.equals(lb)) {
                    Log.w("vCard", "Found a next property during parsing a BASE64 string, which must not contain semi-colon or colon. Treat the line as next property.");
                    Log.w("vCard", "Problematic line: " + peekLine.trim());
                } else {
                    getLine();
                    if (peekLine.length() == 0) {
                        break;
                    }
                    Pa.append(peekLine.trim());
                }
            } else {
                throw new VCardException("File ended during parsing BASE64 binary");
            }
        }
        Log.w("vCard", "Found a next property during parsing a BASE64 string, which must not contain semi-colon or colon. Treat the line as next property.");
        Log.w("vCard", "Problematic line: " + peekLine.trim());
        return Pa.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: Qk */
    public Set mo8489Qk() {
        return VCardParser_V21.sKnownPropertyNameSet;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ra */
    public String mo8490Ra(String str) {
        return str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Rk */
    public String mo8491Rk() {
        String line;
        do {
            line = getLine();
            if (line == null) {
                throw new VCardException("Reached end of buffer.");
            }
        } while (line.trim().length() <= 0);
        return line;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Sk */
    public String mo8492Sk() {
        return VCardConstants.VERSION_V21;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0248  */
    /* renamed from: Tk */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo8493Tk() {
        /*
            r15 = this;
            java.lang.String r0 = "8BIT"
            r15.f2379wM = r0
            java.lang.String r1 = r15.mo8491Rk()
            com.android.vcard.VCardProperty r2 = new com.android.vcard.VCardProperty
            r2.<init>()
            int r3 = r1.length()
            r4 = 0
            if (r3 <= 0) goto L_0x0023
            char r5 = r1.charAt(r4)
            r6 = 35
            if (r5 == r6) goto L_0x001d
            goto L_0x0023
        L_0x001d:
            com.android.vcard.exception.VCardInvalidCommentLineException r15 = new com.android.vcard.exception.VCardInvalidCommentLineException
            r15.<init>()
            throw r15
        L_0x0023:
            r5 = r4
            r6 = r5
        L_0x0025:
            if (r4 >= r3) goto L_0x03b6
            char r7 = r1.charAt(r4)
            java.lang.String r8 = ""
            java.lang.String r9 = "vCard"
            r10 = 2
            r11 = 1
            if (r5 == 0) goto L_0x0086
            java.lang.String r12 = "Double-quoted params found in vCard 2.1. Silently allow it"
            java.lang.String r13 = "2.1"
            r14 = 34
            if (r5 == r11) goto L_0x0050
            if (r5 == r10) goto L_0x003f
            goto L_0x03b2
        L_0x003f:
            if (r7 != r14) goto L_0x03b2
            java.lang.String r5 = r15.mo8492Sk()
            boolean r5 = r13.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x03b1
            android.util.Log.w(r9, r12)
            goto L_0x03b1
        L_0x0050:
            if (r7 != r14) goto L_0x0062
            java.lang.String r5 = r15.mo8492Sk()
            boolean r5 = r13.equalsIgnoreCase(r5)
            if (r5 == 0) goto L_0x005f
            android.util.Log.w(r9, r12)
        L_0x005f:
            r5 = r10
            goto L_0x03b2
        L_0x0062:
            r12 = 59
            if (r7 != r12) goto L_0x006f
            java.lang.String r6 = r1.substring(r6, r4)
            r15.mo8499b(r2, r6)
            goto L_0x03a1
        L_0x006f:
            r12 = 58
            if (r7 != r12) goto L_0x03b2
            java.lang.String r5 = r1.substring(r6, r4)
            r15.mo8499b(r2, r5)
            int r3 = r3 - r11
            if (r4 >= r3) goto L_0x0082
            int r4 = r4 + r11
            java.lang.String r8 = r1.substring(r4)
        L_0x0082:
            r2.setRawValue(r8)
            goto L_0x009c
        L_0x0086:
            r12 = 58
            if (r7 != r12) goto L_0x038a
            java.lang.String r5 = r1.substring(r6, r4)
            r2.setName(r5)
            int r3 = r3 - r11
            if (r4 >= r3) goto L_0x0099
            int r4 = r4 + r11
            java.lang.String r8 = r1.substring(r4)
        L_0x0099:
            r2.setRawValue(r8)
        L_0x009c:
            java.lang.String r1 = r2.getName()
            java.lang.String r1 = r1.toUpperCase()
            java.lang.String r3 = r2.getRawValue()
            java.lang.String r4 = "BEGIN"
            boolean r4 = r1.equals(r4)
            java.lang.String r5 = "VCARD"
            if (r4 == 0) goto L_0x00c9
            boolean r0 = r3.equalsIgnoreCase(r5)
            if (r0 == 0) goto L_0x00bd
            r15.m3908Po()
            goto L_0x0388
        L_0x00bd:
            com.android.vcard.exception.VCardException r15 = new com.android.vcard.exception.VCardException
            java.lang.String r0 = "Unknown BEGIN type: "
            java.lang.String r0 = p026b.p027a.p030b.p031a.C0632a.m1025k(r0, r3)
            r15.<init>(r0)
            throw r15
        L_0x00c9:
            java.lang.String r4 = "END"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00e4
            boolean r15 = r3.equalsIgnoreCase(r5)
            if (r15 == 0) goto L_0x00d8
            return r11
        L_0x00d8:
            com.android.vcard.exception.VCardException r15 = new com.android.vcard.exception.VCardException
            java.lang.String r0 = "Unknown END type: "
            java.lang.String r0 = p026b.p027a.p030b.p031a.C0632a.m1025k(r0, r3)
            r15.<init>(r0)
            throw r15
        L_0x00e4:
            java.lang.String r3 = r2.getRawValue()
            java.lang.String r4 = "AGENT"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00f5
            r15.mo8495a(r2)
            goto L_0x0388
        L_0x00f5:
            java.util.Set r4 = r15.mo8489Qk()
            java.lang.String r5 = r1.toUpperCase()
            boolean r4 = r4.contains(r5)
            java.lang.String r5 = "X-"
            if (r4 != 0) goto L_0x012c
            boolean r4 = r1.startsWith(r5)
            if (r4 != 0) goto L_0x012c
            java.util.Set r4 = r15.f2380xM
            boolean r4 = r4.contains(r1)
            if (r4 != 0) goto L_0x012c
            java.util.Set r4 = r15.f2380xM
            r4.add(r1)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "Property name unsupported by vCard 2.1: "
            r4.append(r6)
            r4.append(r1)
            java.lang.String r4 = r4.toString()
            android.util.Log.w(r9, r4)
        L_0x012c:
            java.lang.String r4 = "VERSION"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0162
            java.lang.String r1 = r15.mo8492Sk()
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x013f
            goto L_0x0162
        L_0x013f:
            com.android.vcard.exception.VCardVersionException r0 = new com.android.vcard.exception.VCardVersionException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Incompatible version: "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r2 = " != "
            r1.append(r2)
            java.lang.String r15 = r15.mo8492Sk()
            r1.append(r15)
            java.lang.String r15 = r1.toString()
            r0.<init>(r15)
            throw r0
        L_0x0162:
            java.lang.String r1 = r2.getName()
            java.lang.String r1 = r1.toUpperCase()
            java.lang.String r3 = r2.getRawValue()
            java.lang.String r4 = "CHARSET"
            java.util.Collection r4 = r2.getParameters(r4)
            r6 = 0
            if (r4 == 0) goto L_0x0182
            java.util.Iterator r4 = r4.iterator()
            java.lang.Object r4 = r4.next()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0183
        L_0x0182:
            r4 = r6
        L_0x0183:
            boolean r7 = android.text.TextUtils.isEmpty(r4)
            if (r7 == 0) goto L_0x018b
            java.lang.String r4 = "UTF-8"
        L_0x018b:
            java.lang.String r7 = "ADR"
            boolean r7 = r1.equals(r7)
            java.lang.String r8 = "QUOTED-PRINTABLE"
            java.lang.String r12 = "ISO-8859-1"
            if (r7 != 0) goto L_0x02f2
            java.lang.String r7 = "ORG"
            boolean r7 = r1.equals(r7)
            if (r7 != 0) goto L_0x02f2
            java.lang.String r7 = "N"
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L_0x01a9
            goto L_0x02f2
        L_0x01a9:
            java.lang.String r7 = r15.f2379wM
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x02c9
            java.lang.String r7 = "FN"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L_0x01c9
            java.lang.String r1 = "ENCODING"
            java.util.Collection r1 = r2.getParameters(r1)
            if (r1 != 0) goto L_0x01c9
            boolean r1 = com.android.vcard.VCardUtils.appearsLikeAndroidVCardQuotedPrintable(r3)
            if (r1 == 0) goto L_0x01c9
            goto L_0x02c9
        L_0x01c9:
            java.lang.String r1 = r15.f2379wM
            java.lang.String r7 = "BASE64"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x0275
            java.lang.String r1 = r15.f2379wM
            java.lang.String r7 = "B"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L_0x01df
            goto L_0x0275
        L_0x01df:
            java.lang.String r1 = r15.f2379wM
            java.lang.String r7 = "7BIT"
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x020f
            java.lang.String r1 = r15.f2379wM
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x020f
            java.lang.String r0 = r15.f2379wM
            boolean r0 = r0.startsWith(r5)
            if (r0 != 0) goto L_0x020f
            java.lang.Object[] r0 = new java.lang.Object[r10]
            java.lang.String r1 = r15.f2379wM
            r5 = 0
            r0[r5] = r1
            java.lang.String r1 = r15.mo8492Sk()
            r0[r11] = r1
            java.lang.String r1 = "The encoding \"%s\" is unsupported by vCard %s"
            java.lang.String r0 = java.lang.String.format(r1, r0)
            android.util.Log.w(r9, r0)
        L_0x020f:
            int r0 = r15.getVersion()
            if (r0 != 0) goto L_0x024c
        L_0x0215:
            java.lang.String r0 = r15.peekLine()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0246
            r1 = 0
            char r1 = r0.charAt(r1)
            r5 = 32
            if (r1 != r5) goto L_0x0246
            java.lang.String r1 = r0.toUpperCase()
            java.lang.String r5 = "END:VCARD"
            boolean r1 = r5.contains(r1)
            if (r1 != 0) goto L_0x0246
            r15.getLine()
            if (r6 != 0) goto L_0x023e
            java.lang.StringBuilder r1 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r3)
            r6 = r1
        L_0x023e:
            java.lang.String r0 = r0.substring(r11)
            r6.append(r0)
            goto L_0x0215
        L_0x0246:
            if (r6 == 0) goto L_0x024c
            java.lang.String r3 = r6.toString()
        L_0x024c:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = com.android.vcard.VCardUtils.convertStringCharset(r3, r12, r4)
            java.lang.String r1 = r15.mo8490Ra(r1)
            r0.add(r1)
            r2.setValues((java.util.List) r0)
            java.util.List r15 = r15.f2378vM
            java.util.Iterator r15 = r15.iterator()
        L_0x0265:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L_0x0388
            java.lang.Object r0 = r15.next()
            com.android.vcard.VCardInterpreter r0 = (com.android.vcard.VCardInterpreter) r0
            r0.onPropertyCreated(r2)
            goto L_0x0265
        L_0x0275:
            java.lang.String r0 = r15.mo8488Qa(r3)     // Catch:{ OutOfMemoryError -> 0x02ae }
            r1 = 0
            byte[] r0 = android.util.Base64.decode(r0, r1)     // Catch:{ IllegalArgumentException -> 0x0297 }
            r2.setByteValue(r0)     // Catch:{ IllegalArgumentException -> 0x0297 }
            java.util.List r0 = r15.f2378vM     // Catch:{ OutOfMemoryError -> 0x02ae }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ OutOfMemoryError -> 0x02ae }
        L_0x0287:
            boolean r1 = r0.hasNext()     // Catch:{ OutOfMemoryError -> 0x02ae }
            if (r1 == 0) goto L_0x0388
            java.lang.Object r1 = r0.next()     // Catch:{ OutOfMemoryError -> 0x02ae }
            com.android.vcard.VCardInterpreter r1 = (com.android.vcard.VCardInterpreter) r1     // Catch:{ OutOfMemoryError -> 0x02ae }
            r1.onPropertyCreated(r2)     // Catch:{ OutOfMemoryError -> 0x02ae }
            goto L_0x0287
        L_0x0297:
            com.android.vcard.exception.VCardException r0 = new com.android.vcard.exception.VCardException     // Catch:{ OutOfMemoryError -> 0x02ae }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x02ae }
            r1.<init>()     // Catch:{ OutOfMemoryError -> 0x02ae }
            java.lang.String r4 = "Decode error on base64 photo: "
            r1.append(r4)     // Catch:{ OutOfMemoryError -> 0x02ae }
            r1.append(r3)     // Catch:{ OutOfMemoryError -> 0x02ae }
            java.lang.String r1 = r1.toString()     // Catch:{ OutOfMemoryError -> 0x02ae }
            r0.<init>(r1)     // Catch:{ OutOfMemoryError -> 0x02ae }
            throw r0     // Catch:{ OutOfMemoryError -> 0x02ae }
        L_0x02ae:
            java.lang.String r0 = "OutOfMemoryError happened during parsing BASE64 data!"
            android.util.Log.e(r9, r0)
            java.util.List r15 = r15.f2378vM
            java.util.Iterator r15 = r15.iterator()
        L_0x02b9:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L_0x0388
            java.lang.Object r0 = r15.next()
            com.android.vcard.VCardInterpreter r0 = (com.android.vcard.VCardInterpreter) r0
            r0.onPropertyCreated(r2)
            goto L_0x02b9
        L_0x02c9:
            java.lang.String r0 = r15.m3912mb(r3)
            r1 = 0
            java.lang.String r3 = com.android.vcard.VCardUtils.parseQuotedPrintable(r0, r1, r12, r4)
            r2.setRawValue(r0)
            java.lang.String[] r0 = new java.lang.String[r11]
            r0[r1] = r3
            r2.setValues((java.lang.String[]) r0)
            java.util.List r15 = r15.f2378vM
            java.util.Iterator r15 = r15.iterator()
        L_0x02e2:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L_0x0388
            java.lang.Object r0 = r15.next()
            com.android.vcard.VCardInterpreter r0 = (com.android.vcard.VCardInterpreter) r0
            r0.onPropertyCreated(r2)
            goto L_0x02e2
        L_0x02f2:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = r15.f2379wM
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L_0x0327
            java.lang.String r1 = r15.m3912mb(r3)
            r2.setRawValue(r1)
            int r3 = r15.getVersion()
            java.util.List r1 = com.android.vcard.VCardUtils.constructListFromValue(r1, r3)
            java.util.Iterator r1 = r1.iterator()
        L_0x0312:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x036f
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            r5 = 0
            java.lang.String r3 = com.android.vcard.VCardUtils.parseQuotedPrintable(r3, r5, r12, r4)
            r0.add(r3)
            goto L_0x0312
        L_0x0327:
            java.lang.StringBuilder r1 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r3)
        L_0x032b:
            java.lang.String r3 = r15.peekLine()
            if (r3 == 0) goto L_0x034b
            int r5 = r3.length()
            if (r5 != 0) goto L_0x0338
            goto L_0x034b
        L_0x0338:
            java.lang.String r5 = r15.m3911lb(r3)
            if (r5 == 0) goto L_0x033f
            goto L_0x034b
        L_0x033f:
            r15.getLine()
            java.lang.String r5 = " "
            r1.append(r5)
            r1.append(r3)
            goto L_0x032b
        L_0x034b:
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = com.android.vcard.VCardUtils.convertStringCharset(r1, r12, r4)
            int r3 = r15.getVersion()
            java.util.List r1 = com.android.vcard.VCardUtils.constructListFromValue(r1, r3)
            java.util.Iterator r1 = r1.iterator()
        L_0x035f:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x036f
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            r0.add(r3)
            goto L_0x035f
        L_0x036f:
            r2.setValues((java.util.List) r0)
            java.util.List r15 = r15.f2378vM
            java.util.Iterator r15 = r15.iterator()
        L_0x0378:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L_0x0388
            java.lang.Object r0 = r15.next()
            com.android.vcard.VCardInterpreter r0 = (com.android.vcard.VCardInterpreter) r0
            r0.onPropertyCreated(r2)
            goto L_0x0378
        L_0x0388:
            r15 = 0
            return r15
        L_0x038a:
            r8 = 46
            if (r7 != r8) goto L_0x03a4
            java.lang.String r6 = r1.substring(r6, r4)
            int r7 = r6.length()
            if (r7 != 0) goto L_0x039e
            java.lang.String r6 = "Empty group found. Ignoring."
            android.util.Log.w(r9, r6)
            goto L_0x03a1
        L_0x039e:
            r2.addGroup(r6)
        L_0x03a1:
            int r6 = r4 + 1
            goto L_0x03b2
        L_0x03a4:
            r8 = 59
            if (r7 != r8) goto L_0x03b2
            java.lang.String r5 = r1.substring(r6, r4)
            r2.setName(r5)
            int r6 = r4 + 1
        L_0x03b1:
            r5 = r11
        L_0x03b2:
            int r4 = r4 + 1
            goto L_0x0025
        L_0x03b6:
            com.android.vcard.exception.VCardInvalidLineException r15 = new com.android.vcard.exception.VCardInvalidLineException
            java.lang.String r0 = "Invalid line: \""
            java.lang.String r2 = "\""
            java.lang.String r0 = p026b.p027a.p030b.p031a.C0632a.m1023d(r0, r1, r2)
            r15.<init>(r0)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.C1500g.mo8493Tk():boolean");
    }

    /* access modifiers changed from: protected */
    /* renamed from: Uk */
    public void mo8494Uk() {
        boolean z;
        try {
            z = mo8493Tk();
        } catch (VCardInvalidCommentLineException unused) {
            Log.e("vCard", "Invalid line which looks like some comment was found. Ignored.");
            z = false;
        }
        while (!z) {
            try {
                z = mo8493Tk();
            } catch (VCardInvalidCommentLineException unused2) {
                Log.e("vCard", "Invalid line which looks like some comment was found. Ignored.");
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8496a(VCardProperty vCardProperty, String str) {
        mo8500c(vCardProperty, str);
    }

    public void addInterpreter(VCardInterpreter vCardInterpreter) {
        this.f2378vM.add(vCardInterpreter);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo8499b(VCardProperty vCardProperty, String str) {
        String[] split = str.split("=", 2);
        int i = 0;
        if (split.length == 2) {
            String upperCase = split[0].trim().toUpperCase();
            String trim = split[1].trim();
            if (upperCase.equals(VCardConstants.PARAM_TYPE)) {
                mo8500c(vCardProperty, trim);
            } else if (upperCase.equals(VCardConstants.PARAM_VALUE)) {
                if (!VCardParser_V21.sKnownValueSet.contains(trim.toUpperCase()) && !trim.startsWith("X-") && !this.f2381yM.contains(trim)) {
                    this.f2381yM.add(trim);
                    Log.w("vCard", String.format("The value unsupported by TYPE of %s: ", new Object[]{Integer.valueOf(getVersion()), trim}));
                }
                vCardProperty.addParameter(VCardConstants.PARAM_VALUE, trim);
            } else if (upperCase.equals(VCardConstants.PARAM_ENCODING)) {
                String upperCase2 = trim.toUpperCase();
                if (VCardParser_V21.sAvailableEncoding.contains(upperCase2) || upperCase2.startsWith("X-")) {
                    vCardProperty.addParameter(VCardConstants.PARAM_ENCODING, upperCase2);
                    this.f2379wM = upperCase2.toUpperCase();
                    return;
                }
                throw new VCardException(C0632a.m1023d("Unknown encoding \"", upperCase2, "\""));
            } else if (upperCase.equals(VCardConstants.PARAM_CHARSET)) {
                vCardProperty.addParameter(VCardConstants.PARAM_CHARSET, trim);
            } else if (upperCase.equals(VCardConstants.PARAM_LANGUAGE)) {
                String[] split2 = trim.split("-");
                if (split2.length == 2) {
                    String str2 = split2[0];
                    int length = str2.length();
                    int i2 = 0;
                    while (i2 < length) {
                        if (m3910f(str2.charAt(i2))) {
                            i2++;
                        } else {
                            throw new VCardException(C0632a.m1023d("Invalid Language: \"", trim, "\""));
                        }
                    }
                    String str3 = split2[1];
                    int length2 = str3.length();
                    while (i < length2) {
                        if (m3910f(str3.charAt(i))) {
                            i++;
                        } else {
                            throw new VCardException(C0632a.m1023d("Invalid Language: \"", trim, "\""));
                        }
                    }
                    vCardProperty.addParameter(VCardConstants.PARAM_LANGUAGE, trim);
                    return;
                }
                throw new VCardException(C0632a.m1023d("Invalid Language: \"", trim, "\""));
            } else if (upperCase.startsWith("X-")) {
                mo8497a(vCardProperty, upperCase, trim);
            } else {
                throw new VCardException(C0632a.m1023d("Unknown type \"", upperCase, "\""));
            }
        } else {
            mo8496a(vCardProperty, split[0]);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo8500c(VCardProperty vCardProperty, String str) {
        if (!VCardParser_V21.sKnownTypeSet.contains(str.toUpperCase()) && !str.startsWith("X-") && !this.f2380xM.contains(str)) {
            this.f2380xM.add(str);
            Log.w("vCard", String.format("TYPE unsupported by %s: ", new Object[]{Integer.valueOf(getVersion()), str}));
        }
        vCardProperty.addParameter(VCardConstants.PARAM_TYPE, str);
    }

    public final synchronized void cancel() {
        Log.i("vCard", "ParserImpl received cancel operation.");
        this.mCanceled = true;
    }

    /* access modifiers changed from: protected */
    public String getLine() {
        return this.mReader.readLine();
    }

    /* access modifiers changed from: protected */
    public int getVersion() {
        return 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: oa */
    public boolean mo8504oa(boolean z) {
        while (true) {
            String line = getLine();
            if (line == null) {
                return false;
            }
            if (line.trim().length() > 0) {
                String[] split = line.split(":", 2);
                if (split.length == 2 && split[0].trim().equalsIgnoreCase(VCardConstants.PROPERTY_BEGIN) && split[1].trim().equalsIgnoreCase("VCARD")) {
                    return true;
                }
                if (!z) {
                    throw new VCardException(C0632a.m1023d("Expected String \"BEGIN:VCARD\" did not come (Instead, \"", line, "\" came)"));
                } else if (!z) {
                    throw new VCardException("Reached where must not be reached.");
                }
            }
        }
    }

    public void parse(InputStream inputStream) {
        if (inputStream != null) {
            this.mReader = new VCardParserImpl_V21$CustomBufferedReader(new InputStreamReader(inputStream, this.f2377uM));
            System.currentTimeMillis();
            for (VCardInterpreter onVCardStarted : this.f2378vM) {
                onVCardStarted.onVCardStarted();
            }
            while (true) {
                synchronized (this) {
                    if (!this.mCanceled) {
                        if (!m3909Qo()) {
                            break;
                        }
                    } else {
                        Log.i("vCard", "Cancel request has come. exitting parse operation.");
                        break;
                    }
                }
            }
            for (VCardInterpreter onVCardEnded : this.f2378vM) {
                onVCardEnded.onVCardEnded();
            }
            return;
        }
        throw new NullPointerException("InputStream must not be null.");
    }

    public void parseOne(InputStream inputStream) {
        if (inputStream != null) {
            this.mReader = new VCardParserImpl_V21$CustomBufferedReader(new InputStreamReader(inputStream, this.f2377uM));
            System.currentTimeMillis();
            for (VCardInterpreter onVCardStarted : this.f2378vM) {
                onVCardStarted.onVCardStarted();
            }
            m3909Qo();
            for (VCardInterpreter onVCardEnded : this.f2378vM) {
                onVCardEnded.onVCardEnded();
            }
            return;
        }
        throw new NullPointerException("InputStream must not be null.");
    }

    /* access modifiers changed from: protected */
    public String peekLine() {
        return this.mReader.peekLine();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8497a(VCardProperty vCardProperty, String str, String str2) {
        vCardProperty.addParameter(str, str2);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8495a(VCardProperty vCardProperty) {
        if (!vCardProperty.getRawValue().toUpperCase().contains("BEGIN:VCARD")) {
            for (VCardInterpreter onPropertyCreated : this.f2378vM) {
                onPropertyCreated.onPropertyCreated(vCardProperty);
            }
            return;
        }
        throw new VCardAgentNotSupportedException("AGENT Property is not supported now.");
    }
}
