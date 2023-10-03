package com.android.vcard;

import android.util.Log;
import com.android.vcard.exception.VCardAgentNotSupportedException;
import com.android.vcard.exception.VCardException;
import com.android.vcard.exception.VCardInvalidCommentLineException;
import com.android.vcard.exception.VCardInvalidLineException;
import com.android.vcard.exception.VCardVersionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class VCardParserImpl_V21 {
    private boolean mCanceled;
    protected String mCurrentCharset;
    protected String mCurrentEncoding;
    protected final String mIntermediateCharset;
    private final List<VCardInterpreter> mInterpreterList;
    protected CustomBufferedReader mReader;
    protected final Set<String> mUnknownTypeSet;
    protected final Set<String> mUnknownValueSet;

    private boolean isAsciiLetter(char c) {
        if (c < 'a' || c > 'z') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int getVersion() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public String getVersionString() {
        return "2.1";
    }

    /* access modifiers changed from: protected */
    public String maybeUnescapeText(String str) {
        return str;
    }

    protected static final class CustomBufferedReader extends BufferedReader {
        private String mNextLine;
        private boolean mNextLineIsValid;
        private long mTime;

        public CustomBufferedReader(Reader reader) {
            super(reader);
        }

        public String readLine() throws IOException {
            if (this.mNextLineIsValid) {
                String str = this.mNextLine;
                this.mNextLine = null;
                this.mNextLineIsValid = false;
                return str;
            }
            long currentTimeMillis = System.currentTimeMillis();
            String readLine = super.readLine();
            this.mTime += System.currentTimeMillis() - currentTimeMillis;
            return readLine;
        }

        public String peekLine() throws IOException {
            if (!this.mNextLineIsValid) {
                long currentTimeMillis = System.currentTimeMillis();
                String readLine = super.readLine();
                this.mTime += System.currentTimeMillis() - currentTimeMillis;
                this.mNextLine = readLine;
                this.mNextLineIsValid = true;
            }
            return this.mNextLine;
        }
    }

    public VCardParserImpl_V21() {
        this(VCardConfig.VCARD_TYPE_DEFAULT);
    }

    public VCardParserImpl_V21(int i) {
        this.mInterpreterList = new ArrayList();
        this.mUnknownTypeSet = new HashSet();
        this.mUnknownValueSet = new HashSet();
        this.mIntermediateCharset = "ISO-8859-1";
    }

    /* access modifiers changed from: protected */
    public boolean isValidPropertyName(String str) {
        if (getKnownPropertyNameSet().contains(str.toUpperCase()) || str.startsWith("X-") || this.mUnknownTypeSet.contains(str)) {
            return true;
        }
        this.mUnknownTypeSet.add(str);
        Log.w("vCard", "Property name unsupported by vCard 2.1: " + str);
        return true;
    }

    /* access modifiers changed from: protected */
    public String getLine() throws IOException {
        return this.mReader.readLine();
    }

    /* access modifiers changed from: protected */
    public String peekLine() throws IOException {
        return this.mReader.peekLine();
    }

    /* access modifiers changed from: protected */
    public String getNonEmptyLine() throws IOException, VCardException {
        String line;
        do {
            line = getLine();
            if (line == null) {
                throw new VCardException("Reached end of buffer.");
            }
        } while (line.trim().length() <= 0);
        return line;
    }

    private boolean parseOneVCard() throws IOException, VCardException {
        this.mCurrentEncoding = "8BIT";
        this.mCurrentCharset = "UTF-8";
        if (!readBeginVCard(true)) {
            return false;
        }
        for (VCardInterpreter onEntryStarted : this.mInterpreterList) {
            onEntryStarted.onEntryStarted();
        }
        parseItems();
        for (VCardInterpreter onEntryEnded : this.mInterpreterList) {
            onEntryEnded.onEntryEnded();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean readBeginVCard(boolean z) throws IOException, VCardException {
        while (true) {
            String line = getLine();
            if (line == null) {
                return false;
            }
            if (line.trim().length() > 0) {
                String[] split = line.split(":", 2);
                if (split.length == 2 && split[0].trim().equalsIgnoreCase("BEGIN") && split[1].trim().equalsIgnoreCase("VCARD")) {
                    return true;
                }
                if (!z) {
                    throw new VCardException("Expected String \"BEGIN:VCARD\" did not come (Instead, \"" + line + "\" came)");
                } else if (!z) {
                    throw new VCardException("Reached where must not be reached.");
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void parseItems() throws IOException, VCardException {
        boolean z;
        try {
            z = parseItem();
        } catch (VCardInvalidCommentLineException unused) {
            Log.e("vCard", "Invalid line which looks like some comment was found. Ignored.");
            z = false;
        }
        while (!z) {
            try {
                z = parseItem();
            } catch (VCardInvalidCommentLineException unused2) {
                Log.e("vCard", "Invalid line which looks like some comment was found. Ignored.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean parseItem() throws IOException, VCardException {
        this.mCurrentEncoding = "8BIT";
        VCardProperty constructPropertyData = constructPropertyData(getNonEmptyLine());
        String upperCase = constructPropertyData.getName().toUpperCase();
        String rawValue = constructPropertyData.getRawValue();
        if (upperCase.equals("BEGIN")) {
            if (rawValue.equalsIgnoreCase("VCARD")) {
                handleNest();
                return false;
            }
            throw new VCardException("Unknown BEGIN type: " + rawValue);
        } else if (!upperCase.equals("END")) {
            parseItemInter(constructPropertyData, upperCase);
            return false;
        } else if (rawValue.equalsIgnoreCase("VCARD")) {
            return true;
        } else {
            throw new VCardException("Unknown END type: " + rawValue);
        }
    }

    private void parseItemInter(VCardProperty vCardProperty, String str) throws IOException, VCardException {
        String rawValue = vCardProperty.getRawValue();
        if (str.equals("AGENT")) {
            handleAgent(vCardProperty);
        } else if (!isValidPropertyName(str)) {
            throw new VCardException("Unknown property name: \"" + str + "\"");
        } else if (!str.equals("VERSION") || rawValue.equals(getVersionString())) {
            handlePropertyValue(vCardProperty, str);
        } else {
            throw new VCardVersionException("Incompatible version: " + rawValue + " != " + getVersionString());
        }
    }

    private void handleNest() throws IOException, VCardException {
        for (VCardInterpreter onEntryStarted : this.mInterpreterList) {
            onEntryStarted.onEntryStarted();
        }
        parseItems();
        for (VCardInterpreter onEntryEnded : this.mInterpreterList) {
            onEntryEnded.onEntryEnded();
        }
    }

    /* access modifiers changed from: protected */
    public VCardProperty constructPropertyData(String str) throws VCardException {
        String str2 = str;
        VCardProperty vCardProperty = new VCardProperty();
        int length = str.length();
        if (length <= 0 || str2.charAt(0) != '#') {
            char c = 0;
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str2.charAt(i2);
                String str3 = "";
                if (c != 0) {
                    if (c != 1) {
                        if (c == 2 && charAt == '\"') {
                            if ("2.1".equalsIgnoreCase(getVersionString())) {
                                Log.w("vCard", "Double-quoted params found in vCard 2.1. Silently allow it");
                            }
                        }
                    } else if (charAt == '\"') {
                        if ("2.1".equalsIgnoreCase(getVersionString())) {
                            Log.w("vCard", "Double-quoted params found in vCard 2.1. Silently allow it");
                        }
                        c = 2;
                    } else if (charAt == ';') {
                        handleParams(vCardProperty, str2.substring(i, i2));
                        i = i2 + 1;
                    } else if (charAt == ':') {
                        handleParams(vCardProperty, str2.substring(i, i2));
                        if (i2 < length - 1) {
                            str3 = str2.substring(i2 + 1);
                        }
                        vCardProperty.setRawValue(str3);
                        return vCardProperty;
                    }
                } else if (charAt == ':') {
                    vCardProperty.setName(str2.substring(i, i2));
                    if (i2 < length - 1) {
                        str3 = str2.substring(i2 + 1);
                    }
                    vCardProperty.setRawValue(str3);
                    return vCardProperty;
                } else if (charAt == '.') {
                    String substring = str2.substring(i, i2);
                    if (substring.length() == 0) {
                        Log.w("vCard", "Empty group found. Ignoring.");
                    } else {
                        vCardProperty.addGroup(substring);
                    }
                    i = i2 + 1;
                } else if (charAt == ';') {
                    vCardProperty.setName(str2.substring(i, i2));
                    i = i2 + 1;
                }
                c = 1;
            }
            throw new VCardInvalidLineException("Invalid line: \"" + str2 + "\"");
        }
        throw new VCardInvalidCommentLineException();
    }

    /* access modifiers changed from: protected */
    public void handleParams(VCardProperty vCardProperty, String str) throws VCardException {
        String[] split = str.split("=", 2);
        if (split.length == 2) {
            String upperCase = split[0].trim().toUpperCase();
            String trim = split[1].trim();
            if (upperCase.equals("TYPE")) {
                handleType(vCardProperty, trim);
            } else if (upperCase.equals("VALUE")) {
                handleValue(vCardProperty, trim);
            } else if (upperCase.equals("ENCODING")) {
                handleEncoding(vCardProperty, trim.toUpperCase());
            } else if (upperCase.equals("CHARSET")) {
                handleCharset(vCardProperty, trim);
            } else if (upperCase.equals("LANGUAGE")) {
                handleLanguage(vCardProperty, trim);
            } else if (upperCase.startsWith("X-")) {
                handleAnyParam(vCardProperty, upperCase, trim);
            } else {
                throw new VCardException("Unknown type \"" + upperCase + "\"");
            }
        } else {
            handleParamWithoutName(vCardProperty, split[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void handleParamWithoutName(VCardProperty vCardProperty, String str) {
        handleType(vCardProperty, str);
    }

    /* access modifiers changed from: protected */
    public void handleType(VCardProperty vCardProperty, String str) {
        if (!getKnownTypeSet().contains(str.toUpperCase()) && !str.startsWith("X-") && !this.mUnknownTypeSet.contains(str)) {
            this.mUnknownTypeSet.add(str);
            Log.w("vCard", String.format("TYPE unsupported by %s: ", new Object[]{Integer.valueOf(getVersion()), str}));
        }
        vCardProperty.addParameter("TYPE", str);
    }

    /* access modifiers changed from: protected */
    public void handleValue(VCardProperty vCardProperty, String str) {
        if (!getKnownValueSet().contains(str.toUpperCase()) && !str.startsWith("X-") && !this.mUnknownValueSet.contains(str)) {
            this.mUnknownValueSet.add(str);
            Log.w("vCard", String.format("The value unsupported by TYPE of %s: ", new Object[]{Integer.valueOf(getVersion()), str}));
        }
        vCardProperty.addParameter("VALUE", str);
    }

    /* access modifiers changed from: protected */
    public void handleEncoding(VCardProperty vCardProperty, String str) throws VCardException {
        if (getAvailableEncodingSet().contains(str) || str.startsWith("X-")) {
            vCardProperty.addParameter("ENCODING", str);
            this.mCurrentEncoding = str.toUpperCase();
            return;
        }
        throw new VCardException("Unknown encoding \"" + str + "\"");
    }

    /* access modifiers changed from: protected */
    public void handleCharset(VCardProperty vCardProperty, String str) {
        this.mCurrentCharset = str;
        vCardProperty.addParameter("CHARSET", str);
    }

    /* access modifiers changed from: protected */
    public void handleLanguage(VCardProperty vCardProperty, String str) throws VCardException {
        String[] split = str.split("-");
        if (split.length == 2) {
            int i = 0;
            String str2 = split[0];
            int length = str2.length();
            int i2 = 0;
            while (i2 < length) {
                if (isAsciiLetter(str2.charAt(i2))) {
                    i2++;
                } else {
                    throw new VCardException("Invalid Language: \"" + str + "\"");
                }
            }
            String str3 = split[1];
            int length2 = str3.length();
            while (i < length2) {
                if (isAsciiLetter(str3.charAt(i))) {
                    i++;
                } else {
                    throw new VCardException("Invalid Language: \"" + str + "\"");
                }
            }
            vCardProperty.addParameter("LANGUAGE", str);
            return;
        }
        throw new VCardException("Invalid Language: \"" + str + "\"");
    }

    /* access modifiers changed from: protected */
    public void handleAnyParam(VCardProperty vCardProperty, String str, String str2) {
        vCardProperty.addParameter(str, str2);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handlePropertyValue(com.android.vcard.VCardProperty r9, java.lang.String r10) throws java.io.IOException, com.android.vcard.exception.VCardException {
        /*
            r8 = this;
            java.lang.String r10 = r9.getName()
            java.lang.String r10 = r10.toUpperCase()
            java.lang.String r0 = r9.getRawValue()
            java.lang.String r1 = "CHARSET"
            java.util.Collection r1 = r9.getParameters(r1)
            r2 = 0
            if (r1 == 0) goto L_0x0020
            java.util.Iterator r1 = r1.iterator()
            java.lang.Object r1 = r1.next()
            java.lang.String r1 = (java.lang.String) r1
            goto L_0x0021
        L_0x0020:
            r1 = r2
        L_0x0021:
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 == 0) goto L_0x0029
            java.lang.String r1 = "UTF-8"
        L_0x0029:
            java.lang.String r3 = "ADR"
            boolean r3 = r10.equals(r3)
            java.lang.String r4 = "ISO-8859-1"
            if (r3 != 0) goto L_0x0199
            java.lang.String r3 = "ORG"
            boolean r3 = r10.equals(r3)
            if (r3 != 0) goto L_0x0199
            java.lang.String r3 = "N"
            boolean r3 = r10.equals(r3)
            if (r3 == 0) goto L_0x0045
            goto L_0x0199
        L_0x0045:
            java.lang.String r3 = r8.mCurrentEncoding
            java.lang.String r5 = "QUOTED-PRINTABLE"
            boolean r3 = r3.equals(r5)
            r5 = 1
            r6 = 0
            if (r3 != 0) goto L_0x0170
            java.lang.String r3 = "FN"
            boolean r10 = r10.equals(r3)
            if (r10 == 0) goto L_0x0069
            java.lang.String r10 = "ENCODING"
            java.util.Collection r10 = r9.getParameters(r10)
            if (r10 != 0) goto L_0x0069
            boolean r10 = com.android.vcard.VCardUtils.appearsLikeAndroidVCardQuotedPrintable(r0)
            if (r10 == 0) goto L_0x0069
            goto L_0x0170
        L_0x0069:
            java.lang.String r10 = r8.mCurrentEncoding
            java.lang.String r3 = "BASE64"
            boolean r10 = r10.equals(r3)
            java.lang.String r3 = "vCard"
            if (r10 != 0) goto L_0x011d
            java.lang.String r10 = r8.mCurrentEncoding
            java.lang.String r7 = "B"
            boolean r10 = r10.equals(r7)
            if (r10 == 0) goto L_0x0081
            goto L_0x011d
        L_0x0081:
            java.lang.String r10 = r8.mCurrentEncoding
            java.lang.String r7 = "7BIT"
            boolean r10 = r10.equals(r7)
            if (r10 != 0) goto L_0x00b5
            java.lang.String r10 = r8.mCurrentEncoding
            java.lang.String r7 = "8BIT"
            boolean r10 = r10.equals(r7)
            if (r10 != 0) goto L_0x00b5
            java.lang.String r10 = r8.mCurrentEncoding
            java.lang.String r7 = "X-"
            boolean r10 = r10.startsWith(r7)
            if (r10 != 0) goto L_0x00b5
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]
            java.lang.String r7 = r8.mCurrentEncoding
            r10[r6] = r7
            java.lang.String r7 = r8.getVersionString()
            r10[r5] = r7
            java.lang.String r7 = "The encoding \"%s\" is unsupported by vCard %s"
            java.lang.String r10 = java.lang.String.format(r7, r10)
            android.util.Log.w(r3, r10)
        L_0x00b5:
            int r10 = r8.getVersion()
            if (r10 != 0) goto L_0x00f4
        L_0x00bb:
            java.lang.String r10 = r8.peekLine()
            boolean r3 = android.text.TextUtils.isEmpty(r10)
            if (r3 != 0) goto L_0x00ee
            char r3 = r10.charAt(r6)
            r7 = 32
            if (r3 != r7) goto L_0x00ee
            java.lang.String r3 = r10.toUpperCase()
            java.lang.String r7 = "END:VCARD"
            boolean r3 = r7.contains(r3)
            if (r3 != 0) goto L_0x00ee
            r8.getLine()
            if (r2 != 0) goto L_0x00e6
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
        L_0x00e6:
            java.lang.String r10 = r10.substring(r5)
            r2.append(r10)
            goto L_0x00bb
        L_0x00ee:
            if (r2 == 0) goto L_0x00f4
            java.lang.String r0 = r2.toString()
        L_0x00f4:
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.lang.String r0 = com.android.vcard.VCardUtils.convertStringCharset(r0, r4, r1)
            java.lang.String r0 = r8.maybeUnescapeText(r0)
            r10.add(r0)
            r9.setValues((java.util.List<java.lang.String>) r10)
            java.util.List<com.android.vcard.VCardInterpreter> r10 = r8.mInterpreterList
            java.util.Iterator r10 = r10.iterator()
        L_0x010d:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x0198
            java.lang.Object r0 = r10.next()
            com.android.vcard.VCardInterpreter r0 = (com.android.vcard.VCardInterpreter) r0
            r0.onPropertyCreated(r9)
            goto L_0x010d
        L_0x011d:
            java.lang.String r10 = r8.getBase64(r0)     // Catch:{ OutOfMemoryError -> 0x0155 }
            byte[] r10 = android.util.Base64.decode(r10, r6)     // Catch:{ IllegalArgumentException -> 0x013e }
            r9.setByteValue(r10)     // Catch:{ IllegalArgumentException -> 0x013e }
            java.util.List<com.android.vcard.VCardInterpreter> r10 = r8.mInterpreterList     // Catch:{ OutOfMemoryError -> 0x0155 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ OutOfMemoryError -> 0x0155 }
        L_0x012e:
            boolean r0 = r10.hasNext()     // Catch:{ OutOfMemoryError -> 0x0155 }
            if (r0 == 0) goto L_0x0198
            java.lang.Object r0 = r10.next()     // Catch:{ OutOfMemoryError -> 0x0155 }
            com.android.vcard.VCardInterpreter r0 = (com.android.vcard.VCardInterpreter) r0     // Catch:{ OutOfMemoryError -> 0x0155 }
            r0.onPropertyCreated(r9)     // Catch:{ OutOfMemoryError -> 0x0155 }
            goto L_0x012e
        L_0x013e:
            com.android.vcard.exception.VCardException r10 = new com.android.vcard.exception.VCardException     // Catch:{ OutOfMemoryError -> 0x0155 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ OutOfMemoryError -> 0x0155 }
            r1.<init>()     // Catch:{ OutOfMemoryError -> 0x0155 }
            java.lang.String r2 = "Decode error on base64 photo: "
            r1.append(r2)     // Catch:{ OutOfMemoryError -> 0x0155 }
            r1.append(r0)     // Catch:{ OutOfMemoryError -> 0x0155 }
            java.lang.String r0 = r1.toString()     // Catch:{ OutOfMemoryError -> 0x0155 }
            r10.<init>(r0)     // Catch:{ OutOfMemoryError -> 0x0155 }
            throw r10     // Catch:{ OutOfMemoryError -> 0x0155 }
        L_0x0155:
            java.lang.String r10 = "OutOfMemoryError happened during parsing BASE64 data!"
            android.util.Log.e(r3, r10)
            java.util.List<com.android.vcard.VCardInterpreter> r10 = r8.mInterpreterList
            java.util.Iterator r10 = r10.iterator()
        L_0x0160:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x0198
            java.lang.Object r0 = r10.next()
            com.android.vcard.VCardInterpreter r0 = (com.android.vcard.VCardInterpreter) r0
            r0.onPropertyCreated(r9)
            goto L_0x0160
        L_0x0170:
            java.lang.String r10 = r8.getQuotedPrintablePart(r0)
            java.lang.String r0 = com.android.vcard.VCardUtils.parseQuotedPrintable(r10, r6, r4, r1)
            r9.setRawValue(r10)
            java.lang.String[] r10 = new java.lang.String[r5]
            r10[r6] = r0
            r9.setValues((java.lang.String[]) r10)
            java.util.List<com.android.vcard.VCardInterpreter> r10 = r8.mInterpreterList
            java.util.Iterator r10 = r10.iterator()
        L_0x0188:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x0198
            java.lang.Object r0 = r10.next()
            com.android.vcard.VCardInterpreter r0 = (com.android.vcard.VCardInterpreter) r0
            r0.onPropertyCreated(r9)
            goto L_0x0188
        L_0x0198:
            return
        L_0x0199:
            r8.handleAdrOrgN(r9, r0, r4, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardParserImpl_V21.handlePropertyValue(com.android.vcard.VCardProperty, java.lang.String):void");
    }

    private void handleAdrOrgN(VCardProperty vCardProperty, String str, String str2, String str3) throws VCardException, IOException {
        ArrayList arrayList = new ArrayList();
        if (this.mCurrentEncoding.equals("QUOTED-PRINTABLE")) {
            String quotedPrintablePart = getQuotedPrintablePart(str);
            vCardProperty.setRawValue(quotedPrintablePart);
            for (String parseQuotedPrintable : VCardUtils.constructListFromValue(quotedPrintablePart, getVersion())) {
                arrayList.add(VCardUtils.parseQuotedPrintable(parseQuotedPrintable, false, str2, str3));
            }
        } else {
            for (String add : VCardUtils.constructListFromValue(VCardUtils.convertStringCharset(getPotentialMultiline(str), str2, str3), getVersion())) {
                arrayList.add(add);
            }
        }
        vCardProperty.setValues((List<String>) arrayList);
        for (VCardInterpreter onPropertyCreated : this.mInterpreterList) {
            onPropertyCreated.onPropertyCreated(vCardProperty);
        }
    }

    private String getQuotedPrintablePart(String str) throws IOException, VCardException {
        if (!str.trim().endsWith("=")) {
            return str;
        }
        int length = str.length() - 1;
        do {
        } while (str.charAt(length) != '=');
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, length + 1));
        sb.append("\r\n");
        while (true) {
            String line = getLine();
            if (line == null) {
                throw new VCardException("File ended during parsing a Quoted-Printable String");
            } else if (line.trim().endsWith("=")) {
                int length2 = line.length() - 1;
                do {
                } while (line.charAt(length2) != '=');
                sb.append(line.substring(0, length2 + 1));
                sb.append("\r\n");
            } else {
                sb.append(line);
                return sb.toString();
            }
        }
    }

    private String getPotentialMultiline(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        while (true) {
            String peekLine = peekLine();
            if (peekLine != null && peekLine.length() != 0 && getPropertyNameUpperCase(peekLine) == null) {
                getLine();
                sb.append(" ");
                sb.append(peekLine);
            }
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String getBase64(String str) throws IOException, VCardException {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        while (true) {
            String peekLine = peekLine();
            if (peekLine != null) {
                String propertyNameUpperCase = getPropertyNameUpperCase(peekLine);
                if (getKnownPropertyNameSet().contains(propertyNameUpperCase) || "X-ANDROID-CUSTOM".equals(propertyNameUpperCase)) {
                    Log.w("vCard", "Found a next property during parsing a BASE64 string, which must not contain semi-colon or colon. Treat the line as next property.");
                    Log.w("vCard", "Problematic line: " + peekLine.trim());
                } else {
                    getLine();
                    if (peekLine.length() == 0) {
                        break;
                    }
                    sb.append(peekLine.trim());
                }
            } else {
                throw new VCardException("File ended during parsing BASE64 binary");
            }
        }
        return sb.toString();
    }

    private String getPropertyNameUpperCase(String str) {
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

    /* access modifiers changed from: protected */
    public void handleAgent(VCardProperty vCardProperty) throws VCardException {
        if (!vCardProperty.getRawValue().toUpperCase().contains("BEGIN:VCARD")) {
            for (VCardInterpreter onPropertyCreated : this.mInterpreterList) {
                onPropertyCreated.onPropertyCreated(vCardProperty);
            }
            return;
        }
        throw new VCardAgentNotSupportedException("AGENT Property is not supported now.");
    }

    static String unescapeCharacter(char c) {
        if (c == '\\' || c == ';' || c == ':' || c == ',') {
            return String.valueOf(c);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Set<String> getKnownPropertyNameSet() {
        return VCardParser_V21.sKnownPropertyNameSet;
    }

    /* access modifiers changed from: protected */
    public Set<String> getKnownTypeSet() {
        return VCardParser_V21.sKnownTypeSet;
    }

    /* access modifiers changed from: protected */
    public Set<String> getKnownValueSet() {
        return VCardParser_V21.sKnownValueSet;
    }

    /* access modifiers changed from: protected */
    public Set<String> getAvailableEncodingSet() {
        return VCardParser_V21.sAvailableEncoding;
    }

    public void addInterpreter(VCardInterpreter vCardInterpreter) {
        this.mInterpreterList.add(vCardInterpreter);
    }

    public void parse(InputStream inputStream) throws IOException, VCardException {
        if (inputStream != null) {
            this.mReader = new CustomBufferedReader(new InputStreamReader(inputStream, this.mIntermediateCharset));
            System.currentTimeMillis();
            for (VCardInterpreter onVCardStarted : this.mInterpreterList) {
                onVCardStarted.onVCardStarted();
            }
            while (true) {
                synchronized (this) {
                    if (!this.mCanceled) {
                        if (!parseOneVCard()) {
                            break;
                        }
                    } else {
                        Log.i("vCard", "Cancel request has come. exitting parse operation.");
                        break;
                    }
                }
            }
            for (VCardInterpreter onVCardEnded : this.mInterpreterList) {
                onVCardEnded.onVCardEnded();
            }
            return;
        }
        throw new NullPointerException("InputStream must not be null.");
    }

    public final synchronized void cancel() {
        Log.i("vCard", "ParserImpl received cancel operation.");
        this.mCanceled = true;
    }
}
