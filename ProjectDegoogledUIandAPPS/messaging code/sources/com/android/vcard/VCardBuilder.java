package com.android.vcard;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.android.vcard.VCardUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p026b.p027a.p030b.p031a.C0632a;

public class VCardBuilder {
    public static final int DEFAULT_EMAIL_TYPE = 3;
    public static final int DEFAULT_PHONE_TYPE = 1;
    public static final int DEFAULT_POSTAL_TYPE = 1;
    private static final String LOG_TAG = "vCard";
    private static final String SHIFT_JIS = "SHIFT_JIS";
    private static final String VCARD_DATA_PUBLIC = "PUBLIC";
    private static final String VCARD_DATA_SEPARATOR = ":";
    private static final String VCARD_DATA_VCARD = "VCARD";
    public static final String VCARD_END_OF_LINE = "\r\n";
    private static final String VCARD_ITEM_SEPARATOR = ";";
    private static final String VCARD_PARAM_ENCODING_BASE64_AS_B = "ENCODING=B";
    private static final String VCARD_PARAM_ENCODING_BASE64_V21 = "ENCODING=BASE64";
    private static final String VCARD_PARAM_ENCODING_QP = "ENCODING=QUOTED-PRINTABLE";
    private static final String VCARD_PARAM_EQUAL = "=";
    private static final String VCARD_PARAM_SEPARATOR = ";";
    private static final String VCARD_WS = " ";
    private static final Set sAllowedAndroidPropertySet = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"vnd.android.cursor.item/nickname", "vnd.android.cursor.item/contact_event", "vnd.android.cursor.item/relation"})));
    private static final Map sPostalTypePriorityMap = new HashMap();
    private final boolean mAppendTypeParamName;
    private StringBuilder mBuilder;
    private final String mCharset;
    private boolean mEndAppended;
    private final boolean mIsDoCoMo;
    private final boolean mIsJapaneseMobilePhone;
    private final boolean mIsV30OrV40;
    private final boolean mNeedsToConvertPhoneticString;
    private final boolean mOnlyOneNoteFieldIsAvailable;
    private final boolean mRefrainsQPToNameProperties;
    private final boolean mShouldAppendCharsetParam;
    private final boolean mShouldUseQuotedPrintable;
    private final boolean mUsesAndroidProperty;
    private final boolean mUsesDefactProperty;
    private final String mVCardCharsetParameter;
    private final int mVCardType;

    static {
        sPostalTypePriorityMap.put(1, 0);
        sPostalTypePriorityMap.put(2, 1);
        sPostalTypePriorityMap.put(3, 2);
        sPostalTypePriorityMap.put(0, 3);
    }

    public VCardBuilder(int i) {
        this(i, (String) null);
    }

    private VCardBuilder appendNamePropertiesV40(List list) {
        String str;
        if (this.mIsDoCoMo || this.mNeedsToConvertPhoneticString) {
            Log.w(LOG_TAG, "Invalid flag is used in vCard 4.0 construction. Ignored.");
        }
        if (list == null || list.isEmpty()) {
            appendLine(VCardConstants.PROPERTY_FN, "");
            return this;
        }
        ContentValues primaryContentValueWithStructuredName = getPrimaryContentValueWithStructuredName(list);
        String asString = primaryContentValueWithStructuredName.getAsString("data3");
        String asString2 = primaryContentValueWithStructuredName.getAsString("data5");
        String asString3 = primaryContentValueWithStructuredName.getAsString("data2");
        String asString4 = primaryContentValueWithStructuredName.getAsString("data4");
        String asString5 = primaryContentValueWithStructuredName.getAsString("data6");
        String asString6 = primaryContentValueWithStructuredName.getAsString("data1");
        if (!TextUtils.isEmpty(asString) || !TextUtils.isEmpty(asString3) || !TextUtils.isEmpty(asString2) || !TextUtils.isEmpty(asString4) || !TextUtils.isEmpty(asString5)) {
            str = asString;
        } else if (TextUtils.isEmpty(asString6)) {
            appendLine(VCardConstants.PROPERTY_FN, "");
            return this;
        } else {
            str = asString6;
        }
        String asString7 = primaryContentValueWithStructuredName.getAsString("data9");
        String asString8 = primaryContentValueWithStructuredName.getAsString("data8");
        String asString9 = primaryContentValueWithStructuredName.getAsString("data7");
        String escapeCharacters = escapeCharacters(str);
        String escapeCharacters2 = escapeCharacters(asString3);
        String escapeCharacters3 = escapeCharacters(asString2);
        ContentValues contentValues = primaryContentValueWithStructuredName;
        String escapeCharacters4 = escapeCharacters(asString4);
        String str2 = VCardConstants.PROPERTY_FN;
        String escapeCharacters5 = escapeCharacters(asString5);
        String str3 = asString5;
        String str4 = asString4;
        this.mBuilder.append(VCardConstants.PROPERTY_N);
        if (!TextUtils.isEmpty(asString7) || !TextUtils.isEmpty(asString8) || !TextUtils.isEmpty(asString9)) {
            this.mBuilder.append(";");
            String str5 = escapeCharacters(asString7) + ';' + escapeCharacters(asString9) + ';' + escapeCharacters(asString8);
            StringBuilder sb = this.mBuilder;
            sb.append("SORT-AS=");
            sb.append(VCardUtils.toStringAsV40ParamValue(str5));
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(escapeCharacters);
        this.mBuilder.append(";");
        this.mBuilder.append(escapeCharacters2);
        this.mBuilder.append(";");
        this.mBuilder.append(escapeCharacters3);
        this.mBuilder.append(";");
        this.mBuilder.append(escapeCharacters4);
        this.mBuilder.append(";");
        this.mBuilder.append(escapeCharacters5);
        this.mBuilder.append(VCARD_END_OF_LINE);
        if (TextUtils.isEmpty(asString6)) {
            Log.w(LOG_TAG, "DISPLAY_NAME is empty.");
            appendLine(str2, escapeCharacters(VCardUtils.constructNameFromElements(VCardConfig.getNameOrderType(this.mVCardType), str, asString2, asString3, str4, str3)));
        } else {
            String escapeCharacters6 = escapeCharacters(asString6);
            this.mBuilder.append(str2);
            this.mBuilder.append(VCARD_DATA_SEPARATOR);
            this.mBuilder.append(escapeCharacters6);
            this.mBuilder.append(VCARD_END_OF_LINE);
        }
        appendPhoneticNameFields(contentValues);
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f7, code lost:
        if (com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii(r13) == false) goto L_0x00f9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x025a  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0274  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01ab  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0205  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x021a  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0228  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void appendPhoneticNameFields(android.content.ContentValues r13) {
        /*
            r12 = this;
            java.lang.String r0 = "data9"
            java.lang.String r0 = r13.getAsString(r0)
            java.lang.String r1 = "data8"
            java.lang.String r1 = r13.getAsString(r1)
            java.lang.String r2 = "data7"
            java.lang.String r13 = r13.getAsString(r2)
            boolean r2 = r12.mNeedsToConvertPhoneticString
            if (r2 == 0) goto L_0x0022
            java.lang.String r0 = com.android.vcard.VCardUtils.toHalfWidthString(r0)
            java.lang.String r1 = com.android.vcard.VCardUtils.toHalfWidthString(r1)
            java.lang.String r13 = com.android.vcard.VCardUtils.toHalfWidthString(r13)
        L_0x0022:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            java.lang.String r3 = "X-IRMC-N"
            java.lang.String r4 = "SOUND"
            java.lang.String r5 = "\r\n"
            java.lang.String r6 = ":"
            java.lang.String r7 = ";"
            if (r2 == 0) goto L_0x0070
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L_0x0070
            boolean r2 = android.text.TextUtils.isEmpty(r13)
            if (r2 == 0) goto L_0x0070
            boolean r13 = r12.mIsDoCoMo
            if (r13 == 0) goto L_0x006f
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r4)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r7)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r3)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r6)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r7)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r7)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r7)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r7)
            java.lang.StringBuilder r12 = r12.mBuilder
            r12.append(r5)
        L_0x006f:
            return
        L_0x0070:
            int r2 = r12.mVCardType
            boolean r2 = com.android.vcard.VCardConfig.isVersion40(r2)
            r8 = 1
            r9 = 0
            if (r2 == 0) goto L_0x007c
            goto L_0x0187
        L_0x007c:
            int r2 = r12.mVCardType
            boolean r2 = com.android.vcard.VCardConfig.isVersion30(r2)
            if (r2 == 0) goto L_0x00c4
            int r2 = r12.mVCardType
            java.lang.String r2 = com.android.vcard.VCardUtils.constructNameFromElements(r2, r0, r1, r13)
            java.lang.StringBuilder r3 = r12.mBuilder
            java.lang.String r4 = "SORT-STRING"
            r3.append(r4)
            int r3 = r12.mVCardType
            boolean r3 = com.android.vcard.VCardConfig.isVersion30(r3)
            if (r3 == 0) goto L_0x00af
            java.lang.String[] r3 = new java.lang.String[r8]
            r3[r9] = r2
            boolean r3 = r12.shouldAppendCharsetParam(r3)
            if (r3 == 0) goto L_0x00af
            java.lang.StringBuilder r3 = r12.mBuilder
            r3.append(r7)
            java.lang.StringBuilder r3 = r12.mBuilder
            java.lang.String r4 = r12.mVCardCharsetParameter
            r3.append(r4)
        L_0x00af:
            java.lang.StringBuilder r3 = r12.mBuilder
            r3.append(r6)
            java.lang.StringBuilder r3 = r12.mBuilder
            java.lang.String r2 = r12.escapeCharacters(r2)
            r3.append(r2)
            java.lang.StringBuilder r2 = r12.mBuilder
            r2.append(r5)
            goto L_0x0187
        L_0x00c4:
            boolean r2 = r12.mIsJapaneseMobilePhone
            if (r2 == 0) goto L_0x0187
            java.lang.StringBuilder r2 = r12.mBuilder
            r2.append(r4)
            java.lang.StringBuilder r2 = r12.mBuilder
            r2.append(r7)
            java.lang.StringBuilder r2 = r12.mBuilder
            r2.append(r3)
            boolean r2 = r12.mRefrainsQPToNameProperties
            if (r2 != 0) goto L_0x00fb
            java.lang.String[] r2 = new java.lang.String[r8]
            r2[r9] = r0
            boolean r2 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r2)
            if (r2 == 0) goto L_0x00f9
            java.lang.String[] r2 = new java.lang.String[r8]
            r2[r9] = r1
            boolean r2 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r2)
            if (r2 == 0) goto L_0x00f9
            java.lang.String[] r2 = new java.lang.String[r8]
            r2[r9] = r13
            boolean r2 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r2)
            if (r2 != 0) goto L_0x00fb
        L_0x00f9:
            r2 = r8
            goto L_0x00fc
        L_0x00fb:
            r2 = r9
        L_0x00fc:
            if (r2 == 0) goto L_0x010b
            java.lang.String r2 = r12.encodeQuotedPrintable(r0)
            java.lang.String r3 = r12.encodeQuotedPrintable(r1)
            java.lang.String r4 = r12.encodeQuotedPrintable(r13)
            goto L_0x0117
        L_0x010b:
            java.lang.String r2 = r12.escapeCharacters(r0)
            java.lang.String r3 = r12.escapeCharacters(r1)
            java.lang.String r4 = r12.escapeCharacters(r13)
        L_0x0117:
            r10 = 3
            java.lang.String[] r10 = new java.lang.String[r10]
            r10[r9] = r2
            r10[r8] = r3
            r11 = 2
            r10[r11] = r4
            boolean r10 = r12.shouldAppendCharsetParam(r10)
            if (r10 == 0) goto L_0x0133
            java.lang.StringBuilder r10 = r12.mBuilder
            r10.append(r7)
            java.lang.StringBuilder r10 = r12.mBuilder
            java.lang.String r11 = r12.mVCardCharsetParameter
            r10.append(r11)
        L_0x0133:
            java.lang.StringBuilder r10 = r12.mBuilder
            r10.append(r6)
            boolean r10 = android.text.TextUtils.isEmpty(r2)
            if (r10 != 0) goto L_0x0145
            java.lang.StringBuilder r10 = r12.mBuilder
            r10.append(r2)
            r2 = r9
            goto L_0x0146
        L_0x0145:
            r2 = r8
        L_0x0146:
            boolean r10 = android.text.TextUtils.isEmpty(r3)
            r11 = 32
            if (r10 != 0) goto L_0x015c
            if (r2 == 0) goto L_0x0152
            r2 = r9
            goto L_0x0157
        L_0x0152:
            java.lang.StringBuilder r10 = r12.mBuilder
            r10.append(r11)
        L_0x0157:
            java.lang.StringBuilder r10 = r12.mBuilder
            r10.append(r3)
        L_0x015c:
            boolean r3 = android.text.TextUtils.isEmpty(r4)
            if (r3 != 0) goto L_0x016e
            if (r2 != 0) goto L_0x0169
            java.lang.StringBuilder r2 = r12.mBuilder
            r2.append(r11)
        L_0x0169:
            java.lang.StringBuilder r2 = r12.mBuilder
            r2.append(r4)
        L_0x016e:
            java.lang.StringBuilder r2 = r12.mBuilder
            r2.append(r7)
            java.lang.StringBuilder r2 = r12.mBuilder
            r2.append(r7)
            java.lang.StringBuilder r2 = r12.mBuilder
            r2.append(r7)
            java.lang.StringBuilder r2 = r12.mBuilder
            r2.append(r7)
            java.lang.StringBuilder r2 = r12.mBuilder
            r2.append(r5)
        L_0x0187:
            boolean r2 = r12.mUsesDefactProperty
            if (r2 == 0) goto L_0x029b
            boolean r2 = android.text.TextUtils.isEmpty(r13)
            java.lang.String r3 = "ENCODING=QUOTED-PRINTABLE"
            if (r2 != 0) goto L_0x01e7
            boolean r2 = r12.mShouldUseQuotedPrintable
            if (r2 == 0) goto L_0x01a3
            java.lang.String[] r2 = new java.lang.String[r8]
            r2[r9] = r13
            boolean r2 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r2)
            if (r2 != 0) goto L_0x01a3
            r2 = r8
            goto L_0x01a4
        L_0x01a3:
            r2 = r9
        L_0x01a4:
            if (r2 == 0) goto L_0x01ab
            java.lang.String r4 = r12.encodeQuotedPrintable(r13)
            goto L_0x01af
        L_0x01ab:
            java.lang.String r4 = r12.escapeCharacters(r13)
        L_0x01af:
            java.lang.StringBuilder r10 = r12.mBuilder
            java.lang.String r11 = "X-PHONETIC-FIRST-NAME"
            r10.append(r11)
            java.lang.String[] r10 = new java.lang.String[r8]
            r10[r9] = r13
            boolean r13 = r12.shouldAppendCharsetParam(r10)
            if (r13 == 0) goto L_0x01cc
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r7)
            java.lang.StringBuilder r13 = r12.mBuilder
            java.lang.String r10 = r12.mVCardCharsetParameter
            r13.append(r10)
        L_0x01cc:
            if (r2 == 0) goto L_0x01d8
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r7)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r3)
        L_0x01d8:
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r6)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r4)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r5)
        L_0x01e7:
            boolean r13 = android.text.TextUtils.isEmpty(r1)
            if (r13 != 0) goto L_0x0241
            boolean r13 = r12.mShouldUseQuotedPrintable
            if (r13 == 0) goto L_0x01fd
            java.lang.String[] r13 = new java.lang.String[r8]
            r13[r9] = r1
            boolean r13 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r13)
            if (r13 != 0) goto L_0x01fd
            r13 = r8
            goto L_0x01fe
        L_0x01fd:
            r13 = r9
        L_0x01fe:
            if (r13 == 0) goto L_0x0205
            java.lang.String r2 = r12.encodeQuotedPrintable(r1)
            goto L_0x0209
        L_0x0205:
            java.lang.String r2 = r12.escapeCharacters(r1)
        L_0x0209:
            java.lang.StringBuilder r4 = r12.mBuilder
            java.lang.String r10 = "X-PHONETIC-MIDDLE-NAME"
            r4.append(r10)
            java.lang.String[] r4 = new java.lang.String[r8]
            r4[r9] = r1
            boolean r1 = r12.shouldAppendCharsetParam(r4)
            if (r1 == 0) goto L_0x0226
            java.lang.StringBuilder r1 = r12.mBuilder
            r1.append(r7)
            java.lang.StringBuilder r1 = r12.mBuilder
            java.lang.String r4 = r12.mVCardCharsetParameter
            r1.append(r4)
        L_0x0226:
            if (r13 == 0) goto L_0x0232
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r7)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r3)
        L_0x0232:
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r6)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r2)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r5)
        L_0x0241:
            boolean r13 = android.text.TextUtils.isEmpty(r0)
            if (r13 != 0) goto L_0x029b
            boolean r13 = r12.mShouldUseQuotedPrintable
            if (r13 == 0) goto L_0x0257
            java.lang.String[] r13 = new java.lang.String[r8]
            r13[r9] = r0
            boolean r13 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r13)
            if (r13 != 0) goto L_0x0257
            r13 = r8
            goto L_0x0258
        L_0x0257:
            r13 = r9
        L_0x0258:
            if (r13 == 0) goto L_0x025f
            java.lang.String r1 = r12.encodeQuotedPrintable(r0)
            goto L_0x0263
        L_0x025f:
            java.lang.String r1 = r12.escapeCharacters(r0)
        L_0x0263:
            java.lang.StringBuilder r2 = r12.mBuilder
            java.lang.String r4 = "X-PHONETIC-LAST-NAME"
            r2.append(r4)
            java.lang.String[] r2 = new java.lang.String[r8]
            r2[r9] = r0
            boolean r0 = r12.shouldAppendCharsetParam(r2)
            if (r0 == 0) goto L_0x0280
            java.lang.StringBuilder r0 = r12.mBuilder
            r0.append(r7)
            java.lang.StringBuilder r0 = r12.mBuilder
            java.lang.String r2 = r12.mVCardCharsetParameter
            r0.append(r2)
        L_0x0280:
            if (r13 == 0) goto L_0x028c
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r7)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r3)
        L_0x028c:
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r6)
            java.lang.StringBuilder r13 = r12.mBuilder
            r13.append(r1)
            java.lang.StringBuilder r12 = r12.mBuilder
            r12.append(r5)
        L_0x029b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardBuilder.appendPhoneticNameFields(android.content.ContentValues):void");
    }

    private void appendPostalsForDoCoMo(List list) {
        ContentValues contentValues;
        int i;
        Iterator it = list.iterator();
        int i2 = Integer.MAX_VALUE;
        ContentValues contentValues2 = null;
        int i3 = Integer.MAX_VALUE;
        while (true) {
            if (!it.hasNext()) {
                contentValues = contentValues2;
                i = i2;
                break;
            }
            ContentValues contentValues3 = (ContentValues) it.next();
            if (contentValues3 != null) {
                Integer asInteger = contentValues3.getAsInteger("data2");
                Integer num = (Integer) sPostalTypePriorityMap.get(asInteger);
                int intValue = num != null ? num.intValue() : Integer.MAX_VALUE;
                if (intValue < i3) {
                    i2 = asInteger.intValue();
                    if (intValue == 0) {
                        i = i2;
                        contentValues = contentValues3;
                        break;
                    }
                    contentValues2 = contentValues3;
                    i3 = intValue;
                } else {
                    continue;
                }
            }
        }
        if (contentValues == null) {
            Log.w(LOG_TAG, "Should not come here. Must have at least one postal data.");
        } else {
            appendPostalLine(i, contentValues.getAsString("data3"), contentValues, false, true);
        }
    }

    private void appendPostalsForGeneric(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ContentValues contentValues = (ContentValues) it.next();
            if (contentValues != null) {
                Integer asInteger = contentValues.getAsInteger("data2");
                int intValue = asInteger != null ? asInteger.intValue() : 1;
                String asString = contentValues.getAsString("data3");
                Integer asInteger2 = contentValues.getAsInteger("is_primary");
                boolean z = false;
                if (asInteger2 != null && asInteger2.intValue() > 0) {
                    z = true;
                }
                appendPostalLine(intValue, asString, contentValues, z, false);
            }
        }
    }

    private void appendTypeParameter(String str) {
        appendTypeParameter(this.mBuilder, str);
    }

    private void appendTypeParameters(List list) {
        String str;
        Iterator it = list.iterator();
        boolean z = true;
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (VCardConfig.isVersion30(this.mVCardType) || VCardConfig.isVersion40(this.mVCardType)) {
                if (VCardConfig.isVersion40(this.mVCardType)) {
                    str = VCardUtils.toStringAsV40ParamValue(str2);
                } else {
                    str = VCardUtils.toStringAsV30ParamValue(str2);
                }
                if (!TextUtils.isEmpty(str)) {
                    if (z) {
                        z = false;
                    } else {
                        this.mBuilder.append(";");
                    }
                    appendTypeParameter(this.mBuilder, str);
                }
            } else if (VCardUtils.isV21Word(str2)) {
                if (z) {
                    z = false;
                } else {
                    this.mBuilder.append(";");
                }
                appendTypeParameter(this.mBuilder, str2);
            }
        }
    }

    private void appendUncommonPhoneType(StringBuilder sb, Integer num) {
        if (this.mIsDoCoMo) {
            sb.append(VCardConstants.PARAM_TYPE_VOICE);
            return;
        }
        String phoneTypeString = VCardUtils.getPhoneTypeString(num);
        if (phoneTypeString != null) {
            appendTypeParameter(this.mBuilder, phoneTypeString);
            return;
        }
        Log.e(LOG_TAG, "Unknown or unsupported (by vCard) Phone type: " + num);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void buildSinglePartNameField(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            boolean r0 = r5.mRefrainsQPToNameProperties
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0012
            java.lang.String[] r0 = new java.lang.String[r1]
            r0[r2] = r7
            boolean r0 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r0)
            if (r0 != 0) goto L_0x0012
            r0 = r1
            goto L_0x0013
        L_0x0012:
            r0 = r2
        L_0x0013:
            if (r0 == 0) goto L_0x001a
            java.lang.String r3 = r5.encodeQuotedPrintable(r7)
            goto L_0x001e
        L_0x001a:
            java.lang.String r3 = r5.escapeCharacters(r7)
        L_0x001e:
            java.lang.StringBuilder r4 = r5.mBuilder
            r4.append(r6)
            java.lang.String[] r6 = new java.lang.String[r1]
            r6[r2] = r7
            boolean r6 = r5.shouldAppendCharsetParam(r6)
            java.lang.String r7 = ";"
            if (r6 == 0) goto L_0x003b
            java.lang.StringBuilder r6 = r5.mBuilder
            r6.append(r7)
            java.lang.StringBuilder r6 = r5.mBuilder
            java.lang.String r1 = r5.mVCardCharsetParameter
            r6.append(r1)
        L_0x003b:
            if (r0 == 0) goto L_0x0049
            java.lang.StringBuilder r6 = r5.mBuilder
            r6.append(r7)
            java.lang.StringBuilder r6 = r5.mBuilder
            java.lang.String r7 = "ENCODING=QUOTED-PRINTABLE"
            r6.append(r7)
        L_0x0049:
            java.lang.StringBuilder r6 = r5.mBuilder
            java.lang.String r7 = ":"
            r6.append(r7)
            java.lang.StringBuilder r5 = r5.mBuilder
            r5.append(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardBuilder.buildSinglePartNameField(java.lang.String, java.lang.String):void");
    }

    private boolean containsNonEmptyName(ContentValues contentValues) {
        return !TextUtils.isEmpty(contentValues.getAsString("data3")) || !TextUtils.isEmpty(contentValues.getAsString("data5")) || !TextUtils.isEmpty(contentValues.getAsString("data2")) || !TextUtils.isEmpty(contentValues.getAsString("data4")) || !TextUtils.isEmpty(contentValues.getAsString("data6")) || !TextUtils.isEmpty(contentValues.getAsString("data9")) || !TextUtils.isEmpty(contentValues.getAsString("data8")) || !TextUtils.isEmpty(contentValues.getAsString("data7")) || !TextUtils.isEmpty(contentValues.getAsString("data1"));
    }

    private String encodeQuotedPrintable(String str) {
        byte[] bArr;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            bArr = str.getBytes(this.mCharset);
        } catch (UnsupportedEncodingException unused) {
            StringBuilder Pa = C0632a.m1011Pa("Charset ");
            Pa.append(this.mCharset);
            Pa.append(" cannot be used. Try default charset");
            Log.e(LOG_TAG, Pa.toString());
            bArr = str.getBytes();
        }
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            sb.append(String.format("=%02X", new Object[]{Byte.valueOf(bArr[i])}));
            i++;
            i2 += 3;
            if (i2 >= 67) {
                sb.append("=\r\n");
                i2 = 0;
            }
        }
        return sb.toString();
    }

    private String escapeCharacters(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != 10) {
                if (charAt != 13) {
                    if (charAt != ',') {
                        if (charAt != '>') {
                            if (charAt != '\\') {
                                if (charAt == ';') {
                                    sb.append('\\');
                                    sb.append(';');
                                } else if (charAt != '<') {
                                    sb.append(charAt);
                                }
                            } else if (this.mIsV30OrV40) {
                                sb.append("\\\\");
                            }
                        }
                        if (this.mIsDoCoMo) {
                            sb.append('\\');
                            sb.append(charAt);
                        } else {
                            sb.append(charAt);
                        }
                    } else if (this.mIsV30OrV40) {
                        sb.append("\\,");
                    } else {
                        sb.append(charAt);
                    }
                } else if (i + 1 < length && str.charAt(i) == 10) {
                }
            }
            sb.append("\\n");
        }
        return sb.toString();
    }

    private ContentValues getPrimaryContentValueWithStructuredName(List list) {
        Iterator it = list.iterator();
        ContentValues contentValues = null;
        ContentValues contentValues2 = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ContentValues contentValues3 = (ContentValues) it.next();
            if (contentValues3 != null) {
                Integer asInteger = contentValues3.getAsInteger("is_super_primary");
                if (asInteger != null && asInteger.intValue() > 0) {
                    contentValues = contentValues3;
                    break;
                } else if (contentValues == null) {
                    Integer asInteger2 = contentValues3.getAsInteger("is_primary");
                    if (asInteger2 != null && asInteger2.intValue() > 0 && containsNonEmptyName(contentValues3)) {
                        contentValues = contentValues3;
                    } else if (contentValues2 == null && containsNonEmptyName(contentValues3)) {
                        contentValues2 = contentValues3;
                    }
                }
            }
        }
        if (contentValues == null) {
            return contentValues2 != null ? contentValues2 : new ContentValues();
        }
        return contentValues;
    }

    private boolean shouldAppendCharsetParam(String... strArr) {
        if (!this.mShouldAppendCharsetParam) {
            return false;
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (!VCardUtils.containsOnlyPrintableAscii(strArr[i])) {
                return true;
            }
        }
        return false;
    }

    private List splitPhoneNumbers(String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != 10 || sb.length() <= 0) {
                sb.append(charAt);
            } else {
                arrayList.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0) {
            arrayList.add(sb.toString());
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0115  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.android.vcard.C1495b tryConstructPostalStruct(android.content.ContentValues r13) {
        /*
            r12 = this;
            java.lang.String r0 = "data5"
            java.lang.String r0 = r13.getAsString(r0)
            java.lang.String r1 = "data6"
            java.lang.String r1 = r13.getAsString(r1)
            java.lang.String r2 = "data4"
            java.lang.String r2 = r13.getAsString(r2)
            java.lang.String r3 = "data7"
            java.lang.String r3 = r13.getAsString(r3)
            java.lang.String r4 = "data8"
            java.lang.String r4 = r13.getAsString(r4)
            java.lang.String r5 = "data9"
            java.lang.String r5 = r13.getAsString(r5)
            java.lang.String r6 = "data10"
            java.lang.String r6 = r13.getAsString(r6)
            r7 = 7
            java.lang.String[] r7 = new java.lang.String[r7]
            r8 = 0
            r7[r8] = r0
            r9 = 1
            r7[r9] = r1
            r10 = 2
            r7[r10] = r2
            r10 = 3
            r7[r10] = r3
            r10 = 4
            r7[r10] = r4
            r10 = 5
            r7[r10] = r5
            r10 = 6
            r7[r10] = r6
            boolean r10 = com.android.vcard.VCardUtils.areAllEmpty(r7)
            java.lang.String r11 = ";"
            if (r10 != 0) goto L_0x00e6
            boolean r13 = r12.mShouldUseQuotedPrintable
            if (r13 == 0) goto L_0x0055
            boolean r13 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r7)
            if (r13 != 0) goto L_0x0055
            r8 = r9
        L_0x0055:
            boolean r13 = com.android.vcard.VCardUtils.containsOnlyPrintableAscii((java.lang.String[]) r7)
            r13 = r13 ^ r9
            boolean r7 = android.text.TextUtils.isEmpty(r3)
            if (r7 == 0) goto L_0x006b
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 == 0) goto L_0x0069
            java.lang.String r3 = ""
            goto L_0x0078
        L_0x0069:
            r3 = r1
            goto L_0x0078
        L_0x006b:
            boolean r7 = android.text.TextUtils.isEmpty(r1)
            if (r7 == 0) goto L_0x0072
            goto L_0x0078
        L_0x0072:
            java.lang.String r7 = " "
            java.lang.String r3 = p026b.p027a.p030b.p031a.C0632a.m1023d(r3, r7, r1)
        L_0x0078:
            if (r8 == 0) goto L_0x0093
            java.lang.String r0 = r12.encodeQuotedPrintable(r0)
            java.lang.String r1 = r12.encodeQuotedPrintable(r2)
            java.lang.String r2 = r12.encodeQuotedPrintable(r3)
            java.lang.String r3 = r12.encodeQuotedPrintable(r4)
            java.lang.String r4 = r12.encodeQuotedPrintable(r5)
            java.lang.String r12 = r12.encodeQuotedPrintable(r6)
            goto L_0x00b3
        L_0x0093:
            java.lang.String r0 = r12.escapeCharacters(r0)
            java.lang.String r2 = r12.escapeCharacters(r2)
            java.lang.String r3 = r12.escapeCharacters(r3)
            java.lang.String r4 = r12.escapeCharacters(r4)
            java.lang.String r5 = r12.escapeCharacters(r5)
            java.lang.String r6 = r12.escapeCharacters(r6)
            r12.escapeCharacters(r1)
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r12 = r6
        L_0x00b3:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r5.append(r11)
            r5.append(r11)
            r5.append(r1)
            r5.append(r11)
            r5.append(r2)
            r5.append(r11)
            r5.append(r3)
            r5.append(r11)
            r5.append(r4)
            r5.append(r11)
            r5.append(r12)
            com.android.vcard.b r12 = new com.android.vcard.b
            java.lang.String r0 = r5.toString()
            r12.<init>(r8, r13, r0)
            return r12
        L_0x00e6:
            java.lang.String r0 = "data1"
            java.lang.String r13 = r13.getAsString(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            if (r0 == 0) goto L_0x00f4
            r12 = 0
            return r12
        L_0x00f4:
            boolean r0 = r12.mShouldUseQuotedPrintable
            if (r0 == 0) goto L_0x0104
            java.lang.String[] r0 = new java.lang.String[r9]
            r0[r8] = r13
            boolean r0 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r0)
            if (r0 != 0) goto L_0x0104
            r0 = r9
            goto L_0x0105
        L_0x0104:
            r0 = r8
        L_0x0105:
            java.lang.String[] r1 = new java.lang.String[r9]
            r1[r8] = r13
            boolean r1 = com.android.vcard.VCardUtils.containsOnlyPrintableAscii((java.lang.String[]) r1)
            r1 = r1 ^ r9
            if (r0 == 0) goto L_0x0115
            java.lang.String r12 = r12.encodeQuotedPrintable(r13)
            goto L_0x0119
        L_0x0115:
            java.lang.String r12 = r12.escapeCharacters(r13)
        L_0x0119:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r11)
            r13.append(r12)
            r13.append(r11)
            r13.append(r11)
            r13.append(r11)
            r13.append(r11)
            r13.append(r11)
            com.android.vcard.b r12 = new com.android.vcard.b
            java.lang.String r13 = r13.toString()
            r12.<init>(r0, r1, r13)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardBuilder.tryConstructPostalStruct(android.content.ContentValues):com.android.vcard.b");
    }

    public void appendAndroidSpecificProperty(String str, ContentValues contentValues) {
        String str2;
        if (sAllowedAndroidPropertySet.contains(str)) {
            ArrayList<String> arrayList = new ArrayList<>();
            boolean z = true;
            for (int i = 1; i <= 15; i++) {
                String asString = contentValues.getAsString("data" + i);
                if (asString == null) {
                    asString = "";
                }
                arrayList.add(asString);
            }
            boolean z2 = this.mShouldAppendCharsetParam && !VCardUtils.containsOnlyNonCrLfPrintableAscii((Collection) arrayList);
            if (!this.mShouldUseQuotedPrintable || VCardUtils.containsOnlyNonCrLfPrintableAscii((Collection) arrayList)) {
                z = false;
            }
            this.mBuilder.append(VCardConstants.PROPERTY_X_ANDROID_CUSTOM);
            if (z2) {
                this.mBuilder.append(";");
                this.mBuilder.append(this.mVCardCharsetParameter);
            }
            if (z) {
                this.mBuilder.append(";");
                this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
            }
            this.mBuilder.append(VCARD_DATA_SEPARATOR);
            this.mBuilder.append(str);
            for (String str3 : arrayList) {
                if (z) {
                    str2 = encodeQuotedPrintable(str3);
                } else {
                    str2 = escapeCharacters(str3);
                }
                this.mBuilder.append(";");
                this.mBuilder.append(str2);
            }
            this.mBuilder.append(VCARD_END_OF_LINE);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0060  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void appendEmailLine(int r4, java.lang.String r5, java.lang.String r6, boolean r7) {
        /*
            r3 = this;
            java.lang.String r0 = "CELL"
            r1 = 1
            r2 = 0
            if (r4 == 0) goto L_0x002e
            if (r4 == r1) goto L_0x002b
            r5 = 2
            if (r4 == r5) goto L_0x0028
            r5 = 3
            if (r4 == r5) goto L_0x004d
            r5 = 4
            if (r4 == r5) goto L_0x004e
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Unknown Email type: "
            r5.append(r0)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.lang.String r5 = "vCard"
            android.util.Log.e(r5, r4)
            goto L_0x004d
        L_0x0028:
            java.lang.String r0 = "WORK"
            goto L_0x004e
        L_0x002b:
            java.lang.String r0 = "HOME"
            goto L_0x004e
        L_0x002e:
            boolean r4 = com.android.vcard.VCardUtils.isMobilePhoneLabel(r5)
            if (r4 == 0) goto L_0x0035
            goto L_0x004e
        L_0x0035:
            boolean r4 = android.text.TextUtils.isEmpty(r5)
            if (r4 != 0) goto L_0x004d
            java.lang.String[] r4 = new java.lang.String[r1]
            r0 = 0
            r4[r0] = r5
            boolean r4 = com.android.vcard.VCardUtils.containsOnlyAlphaDigitHyphen((java.lang.String[]) r4)
            if (r4 == 0) goto L_0x004d
            java.lang.String r4 = "X-"
            java.lang.String r0 = p026b.p027a.p030b.p031a.C0632a.m1025k(r4, r5)
            goto L_0x004e
        L_0x004d:
            r0 = r2
        L_0x004e:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            if (r7 == 0) goto L_0x005a
            java.lang.String r5 = "PREF"
            r4.add(r5)
        L_0x005a:
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 != 0) goto L_0x0063
            r4.add(r0)
        L_0x0063:
            java.lang.String r5 = "EMAIL"
            r3.appendLineWithCharsetAndQPDetection((java.lang.String) r5, (java.util.List) r4, (java.lang.String) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardBuilder.appendEmailLine(int, java.lang.String, java.lang.String, boolean):void");
    }

    public VCardBuilder appendEmails(List list) {
        boolean z;
        if (list != null) {
            HashSet hashSet = new HashSet();
            Iterator it = list.iterator();
            z = false;
            while (it.hasNext()) {
                ContentValues contentValues = (ContentValues) it.next();
                String asString = contentValues.getAsString("data1");
                if (asString != null) {
                    asString = asString.trim();
                }
                if (!TextUtils.isEmpty(asString)) {
                    Integer asInteger = contentValues.getAsInteger("data2");
                    int intValue = asInteger != null ? asInteger.intValue() : 3;
                    String asString2 = contentValues.getAsString("data3");
                    Integer asInteger2 = contentValues.getAsInteger("is_primary");
                    boolean z2 = asInteger2 != null && asInteger2.intValue() > 0;
                    if (!hashSet.contains(asString)) {
                        hashSet.add(asString);
                        appendEmailLine(intValue, asString2, asString, z2);
                    }
                    z = true;
                }
            }
        } else {
            z = false;
        }
        if (!z && this.mIsDoCoMo) {
            appendEmailLine(1, "", "", false);
        }
        return this;
    }

    public VCardBuilder appendEvents(List list) {
        if (list != null) {
            Iterator it = list.iterator();
            String str = null;
            String str2 = null;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ContentValues contentValues = (ContentValues) it.next();
                if (contentValues != null) {
                    Integer asInteger = contentValues.getAsInteger("data2");
                    if ((asInteger != null ? asInteger.intValue() : 2) == 3) {
                        String asString = contentValues.getAsString("data1");
                        if (asString == null) {
                            continue;
                        } else {
                            Integer asInteger2 = contentValues.getAsInteger("is_super_primary");
                            boolean z = false;
                            if (asInteger2 != null && asInteger2.intValue() > 0) {
                                str = asString;
                                break;
                            }
                            Integer asInteger3 = contentValues.getAsInteger("is_primary");
                            if (asInteger3 != null && asInteger3.intValue() > 0) {
                                z = true;
                            }
                            if (z) {
                                str = asString;
                            } else if (str2 == null) {
                                str2 = asString;
                            }
                        }
                    } else if (this.mUsesAndroidProperty) {
                        appendAndroidSpecificProperty("vnd.android.cursor.item/contact_event", contentValues);
                    }
                }
            }
            if (str != null) {
                appendLineWithCharsetAndQPDetection(VCardConstants.PROPERTY_BDAY, str.trim());
            } else if (str2 != null) {
                appendLineWithCharsetAndQPDetection(VCardConstants.PROPERTY_BDAY, str2.trim());
            }
        }
        return this;
    }

    public VCardBuilder appendIms(List list) {
        String propertyNameForIm;
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ContentValues contentValues = (ContentValues) it.next();
                Integer asInteger = contentValues.getAsInteger("data5");
                if (!(asInteger == null || (propertyNameForIm = VCardUtils.getPropertyNameForIm(asInteger.intValue())) == null)) {
                    String asString = contentValues.getAsString("data1");
                    if (asString != null) {
                        asString = asString.trim();
                    }
                    if (!TextUtils.isEmpty(asString)) {
                        Integer asInteger2 = contentValues.getAsInteger("data2");
                        int intValue = asInteger2 != null ? asInteger2.intValue() : 3;
                        String str = null;
                        if (intValue == 0) {
                            String asString2 = contentValues.getAsString("data3");
                            if (asString2 != null) {
                                str = C0632a.m1025k("X-", asString2);
                            }
                        } else if (intValue == 1) {
                            str = VCardConstants.PARAM_TYPE_HOME;
                        } else if (intValue == 2) {
                            str = VCardConstants.PARAM_TYPE_WORK;
                        }
                        ArrayList arrayList = new ArrayList();
                        if (!TextUtils.isEmpty(str)) {
                            arrayList.add(str);
                        }
                        Integer asInteger3 = contentValues.getAsInteger("is_primary");
                        boolean z = false;
                        if (asInteger3 != null && asInteger3.intValue() > 0) {
                            z = true;
                        }
                        if (z) {
                            arrayList.add(VCardConstants.PARAM_TYPE_PREF);
                        }
                        appendLineWithCharsetAndQPDetection(propertyNameForIm, (List) arrayList, asString);
                    }
                }
            }
        }
        return this;
    }

    public void appendLine(String str, String str2) {
        appendLine(str, str2, false, false);
    }

    public void appendLineWithCharsetAndQPDetection(String str, String str2) {
        appendLineWithCharsetAndQPDetection(str, (List) null, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00e6, code lost:
        if (com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii(r15) == false) goto L_0x00e8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0204  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0212  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.vcard.VCardBuilder appendNameProperties(java.util.List r21) {
        /*
            r20 = this;
            r0 = r20
            int r1 = r0.mVCardType
            boolean r1 = com.android.vcard.VCardConfig.isVersion40(r1)
            if (r1 == 0) goto L_0x000e
            r20.appendNamePropertiesV40(r21)
            return r0
        L_0x000e:
            java.lang.String r1 = "FN"
            java.lang.String r2 = ""
            java.lang.String r3 = "N"
            if (r21 == 0) goto L_0x0231
            boolean r4 = r21.isEmpty()
            if (r4 == 0) goto L_0x001e
            goto L_0x0231
        L_0x001e:
            android.content.ContentValues r4 = r20.getPrimaryContentValueWithStructuredName(r21)
            java.lang.String r5 = "data3"
            java.lang.String r5 = r4.getAsString(r5)
            java.lang.String r6 = "data5"
            java.lang.String r12 = r4.getAsString(r6)
            java.lang.String r6 = "data2"
            java.lang.String r13 = r4.getAsString(r6)
            java.lang.String r6 = "data4"
            java.lang.String r14 = r4.getAsString(r6)
            java.lang.String r6 = "data6"
            java.lang.String r15 = r4.getAsString(r6)
            java.lang.String r6 = "data1"
            java.lang.String r6 = r4.getAsString(r6)
            boolean r7 = android.text.TextUtils.isEmpty(r5)
            java.lang.String r11 = "\r\n"
            java.lang.String r10 = ";"
            if (r7 == 0) goto L_0x009b
            boolean r7 = android.text.TextUtils.isEmpty(r13)
            if (r7 != 0) goto L_0x0057
            goto L_0x009b
        L_0x0057:
            boolean r5 = android.text.TextUtils.isEmpty(r6)
            if (r5 != 0) goto L_0x0084
            r0.buildSinglePartNameField(r3, r6)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r10)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r10)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r10)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r10)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r11)
            r0.buildSinglePartNameField(r1, r6)
            java.lang.StringBuilder r1 = r0.mBuilder
            r1.append(r11)
        L_0x0081:
            r1 = r4
            goto L_0x022d
        L_0x0084:
            int r5 = r0.mVCardType
            boolean r5 = com.android.vcard.VCardConfig.isVersion30(r5)
            if (r5 == 0) goto L_0x0093
            r0.appendLine((java.lang.String) r3, (java.lang.String) r2)
            r0.appendLine((java.lang.String) r1, (java.lang.String) r2)
            goto L_0x0081
        L_0x0093:
            boolean r1 = r0.mIsDoCoMo
            if (r1 == 0) goto L_0x0081
            r0.appendLine((java.lang.String) r3, (java.lang.String) r2)
            goto L_0x0081
        L_0x009b:
            r2 = 5
            java.lang.String[] r2 = new java.lang.String[r2]
            r16 = 0
            r2[r16] = r5
            r9 = 1
            r2[r9] = r13
            r7 = 2
            r2[r7] = r12
            r7 = 3
            r2[r7] = r14
            r7 = 4
            r2[r7] = r15
            boolean r2 = r0.shouldAppendCharsetParam(r2)
            boolean r7 = r0.mRefrainsQPToNameProperties
            if (r7 != 0) goto L_0x00eb
            java.lang.String[] r7 = new java.lang.String[r9]
            r7[r16] = r5
            boolean r7 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r7)
            if (r7 == 0) goto L_0x00e8
            java.lang.String[] r7 = new java.lang.String[r9]
            r7[r16] = r13
            boolean r7 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r7)
            if (r7 == 0) goto L_0x00e8
            java.lang.String[] r7 = new java.lang.String[r9]
            r7[r16] = r12
            boolean r7 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r7)
            if (r7 == 0) goto L_0x00e8
            java.lang.String[] r7 = new java.lang.String[r9]
            r7[r16] = r14
            boolean r7 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r7)
            if (r7 == 0) goto L_0x00e8
            java.lang.String[] r7 = new java.lang.String[r9]
            r7[r16] = r15
            boolean r7 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r7)
            if (r7 != 0) goto L_0x00eb
        L_0x00e8:
            r17 = r9
            goto L_0x00ed
        L_0x00eb:
            r17 = r16
        L_0x00ed:
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 != 0) goto L_0x00fb
            r21 = r4
            r4 = r9
            r18 = r10
            r19 = r11
            goto L_0x010f
        L_0x00fb:
            int r6 = r0.mVCardType
            r6 = r6 & 12
            r7 = r5
            r8 = r12
            r21 = r4
            r4 = r9
            r9 = r13
            r18 = r10
            r10 = r14
            r19 = r11
            r11 = r15
            java.lang.String r6 = com.android.vcard.VCardUtils.constructNameFromElements(r6, r7, r8, r9, r10, r11)
        L_0x010f:
            java.lang.String[] r7 = new java.lang.String[r4]
            r7[r16] = r6
            boolean r7 = r0.shouldAppendCharsetParam(r7)
            boolean r8 = r0.mRefrainsQPToNameProperties
            if (r8 != 0) goto L_0x0127
            java.lang.String[] r8 = new java.lang.String[r4]
            r8[r16] = r6
            boolean r8 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r8)
            if (r8 != 0) goto L_0x0127
            r16 = r4
        L_0x0127:
            if (r17 == 0) goto L_0x013e
            java.lang.String r4 = r0.encodeQuotedPrintable(r5)
            java.lang.String r5 = r0.encodeQuotedPrintable(r13)
            java.lang.String r8 = r0.encodeQuotedPrintable(r12)
            java.lang.String r9 = r0.encodeQuotedPrintable(r14)
            java.lang.String r10 = r0.encodeQuotedPrintable(r15)
            goto L_0x0152
        L_0x013e:
            java.lang.String r4 = r0.escapeCharacters(r5)
            java.lang.String r5 = r0.escapeCharacters(r13)
            java.lang.String r8 = r0.escapeCharacters(r12)
            java.lang.String r9 = r0.escapeCharacters(r14)
            java.lang.String r10 = r0.escapeCharacters(r15)
        L_0x0152:
            if (r16 == 0) goto L_0x0159
            java.lang.String r11 = r0.encodeQuotedPrintable(r6)
            goto L_0x015d
        L_0x0159:
            java.lang.String r11 = r0.escapeCharacters(r6)
        L_0x015d:
            java.lang.StringBuilder r12 = r0.mBuilder
            r12.append(r3)
            boolean r3 = r0.mIsDoCoMo
            java.lang.String r12 = "ENCODING=QUOTED-PRINTABLE"
            java.lang.String r13 = ":"
            if (r3 == 0) goto L_0x01a8
            if (r2 == 0) goto L_0x017b
            java.lang.StringBuilder r2 = r0.mBuilder
            r3 = r18
            r2.append(r3)
            java.lang.StringBuilder r2 = r0.mBuilder
            java.lang.String r4 = r0.mVCardCharsetParameter
            r2.append(r4)
            goto L_0x017d
        L_0x017b:
            r3 = r18
        L_0x017d:
            if (r17 == 0) goto L_0x0189
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r3)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r12)
        L_0x0189:
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r13)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r6)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r3)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r3)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r3)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r3)
            goto L_0x01f6
        L_0x01a8:
            r3 = r18
            if (r2 == 0) goto L_0x01b8
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r3)
            java.lang.StringBuilder r2 = r0.mBuilder
            java.lang.String r6 = r0.mVCardCharsetParameter
            r2.append(r6)
        L_0x01b8:
            if (r17 == 0) goto L_0x01c4
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r3)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r12)
        L_0x01c4:
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r13)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r4)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r3)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r5)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r3)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r8)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r3)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r9)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r3)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r10)
        L_0x01f6:
            java.lang.StringBuilder r2 = r0.mBuilder
            r4 = r19
            r2.append(r4)
            java.lang.StringBuilder r2 = r0.mBuilder
            r2.append(r1)
            if (r7 == 0) goto L_0x0210
            java.lang.StringBuilder r1 = r0.mBuilder
            r1.append(r3)
            java.lang.StringBuilder r1 = r0.mBuilder
            java.lang.String r2 = r0.mVCardCharsetParameter
            r1.append(r2)
        L_0x0210:
            if (r16 == 0) goto L_0x021c
            java.lang.StringBuilder r1 = r0.mBuilder
            r1.append(r3)
            java.lang.StringBuilder r1 = r0.mBuilder
            r1.append(r12)
        L_0x021c:
            java.lang.StringBuilder r1 = r0.mBuilder
            r1.append(r13)
            java.lang.StringBuilder r1 = r0.mBuilder
            r1.append(r11)
            java.lang.StringBuilder r1 = r0.mBuilder
            r1.append(r4)
            r1 = r21
        L_0x022d:
            r0.appendPhoneticNameFields(r1)
            return r0
        L_0x0231:
            int r4 = r0.mVCardType
            boolean r4 = com.android.vcard.VCardConfig.isVersion30(r4)
            if (r4 == 0) goto L_0x0240
            r0.appendLine((java.lang.String) r3, (java.lang.String) r2)
            r0.appendLine((java.lang.String) r1, (java.lang.String) r2)
            goto L_0x0247
        L_0x0240:
            boolean r1 = r0.mIsDoCoMo
            if (r1 == 0) goto L_0x0247
            r0.appendLine((java.lang.String) r3, (java.lang.String) r2)
        L_0x0247:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardBuilder.appendNameProperties(java.util.List):com.android.vcard.VCardBuilder");
    }

    public VCardBuilder appendNickNames(List list) {
        boolean z;
        if (this.mIsV30OrV40) {
            z = false;
        } else {
            if (this.mUsesAndroidProperty) {
                z = true;
            }
            return this;
        }
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ContentValues contentValues = (ContentValues) it.next();
                String asString = contentValues.getAsString("data1");
                if (!TextUtils.isEmpty(asString)) {
                    if (z) {
                        appendAndroidSpecificProperty("vnd.android.cursor.item/nickname", contentValues);
                    } else {
                        appendLineWithCharsetAndQPDetection(VCardConstants.PROPERTY_NICKNAME, asString);
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendNotes(List list) {
        boolean z;
        if (list != null) {
            boolean z2 = false;
            if (this.mOnlyOneNoteFieldIsAvailable) {
                StringBuilder sb = new StringBuilder();
                Iterator it = list.iterator();
                boolean z3 = true;
                while (it.hasNext()) {
                    String asString = ((ContentValues) it.next()).getAsString("data1");
                    if (asString == null) {
                        asString = "";
                    }
                    if (asString.length() > 0) {
                        if (z3) {
                            z3 = false;
                        } else {
                            sb.append(10);
                        }
                        sb.append(asString);
                    }
                }
                String sb2 = sb.toString();
                boolean z4 = !VCardUtils.containsOnlyPrintableAscii(sb2);
                if (this.mShouldUseQuotedPrintable) {
                    if (!VCardUtils.containsOnlyNonCrLfPrintableAscii(sb2)) {
                        z2 = true;
                    }
                }
                appendLine(VCardConstants.PROPERTY_NOTE, sb2, z4, z2);
            } else {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    String asString2 = ((ContentValues) it2.next()).getAsString("data1");
                    if (!TextUtils.isEmpty(asString2)) {
                        boolean z5 = !VCardUtils.containsOnlyPrintableAscii(asString2);
                        if (this.mShouldUseQuotedPrintable) {
                            if (!VCardUtils.containsOnlyNonCrLfPrintableAscii(asString2)) {
                                z = true;
                                appendLine(VCardConstants.PROPERTY_NOTE, asString2, z5, z);
                            }
                        }
                        z = false;
                        appendLine(VCardConstants.PROPERTY_NOTE, asString2, z5, z);
                    }
                }
            }
        }
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0098, code lost:
        if (com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii(r0) == false) goto L_0x009c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0006 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.android.vcard.VCardBuilder appendOrganizations(java.util.List r8) {
        /*
            r7 = this;
            if (r8 == 0) goto L_0x00a3
            java.util.Iterator r8 = r8.iterator()
        L_0x0006:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x00a3
            java.lang.Object r0 = r8.next()
            android.content.ContentValues r0 = (android.content.ContentValues) r0
            java.lang.String r1 = "data1"
            java.lang.String r1 = r0.getAsString(r1)
            if (r1 == 0) goto L_0x001e
            java.lang.String r1 = r1.trim()
        L_0x001e:
            java.lang.String r2 = "data5"
            java.lang.String r2 = r0.getAsString(r2)
            if (r2 == 0) goto L_0x002a
            java.lang.String r2 = r2.trim()
        L_0x002a:
            java.lang.String r3 = "data4"
            java.lang.String r0 = r0.getAsString(r3)
            if (r0 == 0) goto L_0x0036
            java.lang.String r0 = r0.trim()
        L_0x0036:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L_0x0044
            r3.append(r1)
        L_0x0044:
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            if (r1 != 0) goto L_0x0058
            int r1 = r3.length()
            if (r1 <= 0) goto L_0x0055
            r1 = 59
            r3.append(r1)
        L_0x0055:
            r3.append(r2)
        L_0x0058:
            java.lang.String r1 = r3.toString()
            r2 = 1
            java.lang.String[] r3 = new java.lang.String[r2]
            r4 = 0
            r3[r4] = r1
            boolean r3 = com.android.vcard.VCardUtils.containsOnlyPrintableAscii((java.lang.String[]) r3)
            r3 = r3 ^ r2
            boolean r5 = r7.mShouldUseQuotedPrintable
            if (r5 == 0) goto L_0x0077
            java.lang.String[] r5 = new java.lang.String[r2]
            r5[r4] = r1
            boolean r5 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r5)
            if (r5 != 0) goto L_0x0077
            r5 = r2
            goto L_0x0078
        L_0x0077:
            r5 = r4
        L_0x0078:
            java.lang.String r6 = "ORG"
            r7.appendLine((java.lang.String) r6, (java.lang.String) r1, (boolean) r3, (boolean) r5)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0006
            java.lang.String[] r1 = new java.lang.String[r2]
            r1[r4] = r0
            boolean r1 = com.android.vcard.VCardUtils.containsOnlyPrintableAscii((java.lang.String[]) r1)
            r1 = r1 ^ r2
            boolean r3 = r7.mShouldUseQuotedPrintable
            if (r3 == 0) goto L_0x009b
            java.lang.String[] r3 = new java.lang.String[r2]
            r3[r4] = r0
            boolean r3 = com.android.vcard.VCardUtils.containsOnlyNonCrLfPrintableAscii((java.lang.String[]) r3)
            if (r3 != 0) goto L_0x009b
            goto L_0x009c
        L_0x009b:
            r2 = r4
        L_0x009c:
            java.lang.String r3 = "TITLE"
            r7.appendLine((java.lang.String) r3, (java.lang.String) r0, (boolean) r1, (boolean) r2)
            goto L_0x0006
        L_0x00a3:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardBuilder.appendOrganizations(java.util.List):com.android.vcard.VCardBuilder");
    }

    public VCardBuilder appendPhones(List list, VCardPhoneNumberTranslationCallback vCardPhoneNumberTranslationCallback) {
        boolean z;
        if (list != null) {
            HashSet hashSet = new HashSet();
            Iterator it = list.iterator();
            z = false;
            while (it.hasNext()) {
                ContentValues contentValues = (ContentValues) it.next();
                Integer asInteger = contentValues.getAsInteger("data2");
                String asString = contentValues.getAsString("data3");
                Integer asInteger2 = contentValues.getAsInteger("is_primary");
                boolean z2 = asInteger2 != null && asInteger2.intValue() > 0;
                String asString2 = contentValues.getAsString("data1");
                if (asString2 != null) {
                    asString2 = asString2.trim();
                }
                if (!TextUtils.isEmpty(asString2)) {
                    int intValue = asInteger != null ? asInteger.intValue() : 1;
                    if (vCardPhoneNumberTranslationCallback != null) {
                        String onValueReceived = vCardPhoneNumberTranslationCallback.onValueReceived(asString2, intValue, asString, z2);
                        if (!hashSet.contains(onValueReceived)) {
                            hashSet.add(onValueReceived);
                            appendTelLine(Integer.valueOf(intValue), asString, onValueReceived, z2);
                        }
                    } else {
                        if (intValue != 6 && !VCardConfig.refrainPhoneNumberFormatting(this.mVCardType)) {
                            List<String> splitPhoneNumbers = splitPhoneNumbers(asString2);
                            if (!splitPhoneNumbers.isEmpty()) {
                                for (String str : splitPhoneNumbers) {
                                    if (!hashSet.contains(str)) {
                                        String replace = str.replace(',', 'p').replace(';', 'w');
                                        if (TextUtils.equals(replace, str)) {
                                            StringBuilder sb = new StringBuilder();
                                            int length = str.length();
                                            for (int i = 0; i < length; i++) {
                                                char charAt = str.charAt(i);
                                                if (Character.isDigit(charAt) || charAt == '+') {
                                                    sb.append(charAt);
                                                }
                                            }
                                            replace = VCardUtils.PhoneNumberUtilsPort.formatNumber(sb.toString(), VCardUtils.getPhoneNumberFormat(this.mVCardType));
                                        }
                                        if (VCardConfig.isVersion40(this.mVCardType) && !TextUtils.isEmpty(replace) && !replace.startsWith("tel:")) {
                                            replace = C0632a.m1025k("tel:", replace);
                                        }
                                        hashSet.add(str);
                                        appendTelLine(Integer.valueOf(intValue), asString, replace, z2);
                                    }
                                }
                            }
                        } else if (!hashSet.contains(asString2)) {
                            hashSet.add(asString2);
                            appendTelLine(Integer.valueOf(intValue), asString, asString2, z2);
                        }
                        z = true;
                    }
                }
            }
        } else {
            z = false;
        }
        if (!z && this.mIsDoCoMo) {
            appendTelLine(1, "", "", false);
        }
        return this;
    }

    public void appendPhotoLine(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(VCardConstants.PROPERTY_PHOTO);
        sb.append(";");
        if (this.mIsV30OrV40) {
            sb.append(VCARD_PARAM_ENCODING_BASE64_AS_B);
        } else {
            sb.append(VCARD_PARAM_ENCODING_BASE64_V21);
        }
        sb.append(";");
        appendTypeParameter(sb, str2);
        sb.append(VCARD_DATA_SEPARATOR);
        sb.append(str);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        int length = sb2.length();
        int i = 73;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            sb3.append(sb2.charAt(i3));
            i2++;
            if (i2 > i) {
                sb3.append(VCARD_END_OF_LINE);
                sb3.append(VCARD_WS);
                i = 72;
                i2 = 0;
            }
        }
        this.mBuilder.append(sb3.toString());
        this.mBuilder.append(VCARD_END_OF_LINE);
        this.mBuilder.append(VCARD_END_OF_LINE);
    }

    public VCardBuilder appendPhotos(List list) {
        byte[] asByteArray;
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ContentValues contentValues = (ContentValues) it.next();
                if (!(contentValues == null || (asByteArray = contentValues.getAsByteArray("data15")) == null)) {
                    String guessImageType = VCardUtils.guessImageType(asByteArray);
                    if (guessImageType == null) {
                        Log.d(LOG_TAG, "Unknown photo type. Ignored.");
                    } else {
                        String str = new String(Base64.encode(asByteArray, 2));
                        if (!TextUtils.isEmpty(str)) {
                            appendPhotoLine(str, guessImageType);
                        }
                    }
                }
            }
        }
        return this;
    }

    public void appendPostalLine(int i, String str, ContentValues contentValues, boolean z, boolean z2) {
        boolean z3;
        String str2;
        boolean z4;
        C1495b tryConstructPostalStruct = tryConstructPostalStruct(contentValues);
        if (tryConstructPostalStruct != null) {
            z3 = tryConstructPostalStruct.f2371nM;
            z4 = tryConstructPostalStruct.f2372oM;
            str2 = tryConstructPostalStruct.f2373pM;
        } else if (z2) {
            str2 = "";
            z3 = false;
            z4 = false;
        } else {
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(VCardConstants.PARAM_TYPE_PREF);
        }
        if (i != 0) {
            if (i == 1) {
                arrayList.add(VCardConstants.PARAM_TYPE_HOME);
            } else if (i == 2) {
                arrayList.add(VCardConstants.PARAM_TYPE_WORK);
            } else if (i != 3) {
                Log.e(LOG_TAG, "Unknown StructuredPostal type: " + i);
            }
        } else if (!TextUtils.isEmpty(str)) {
            if (VCardUtils.containsOnlyAlphaDigitHyphen(str)) {
                arrayList.add("X-" + str);
            }
        }
        this.mBuilder.append(VCardConstants.PROPERTY_ADR);
        if (!arrayList.isEmpty()) {
            this.mBuilder.append(";");
            appendTypeParameters(arrayList);
        }
        if (z4) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (z3) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(str2);
        this.mBuilder.append(VCARD_END_OF_LINE);
    }

    public VCardBuilder appendPostals(List list) {
        if (list == null || list.isEmpty()) {
            if (this.mIsDoCoMo) {
                this.mBuilder.append(VCardConstants.PROPERTY_ADR);
                this.mBuilder.append(";");
                this.mBuilder.append(VCardConstants.PARAM_TYPE_HOME);
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(VCARD_END_OF_LINE);
            }
        } else if (this.mIsDoCoMo) {
            appendPostalsForDoCoMo(list);
        } else {
            appendPostalsForGeneric(list);
        }
        return this;
    }

    public VCardBuilder appendRelation(List list) {
        if (this.mUsesAndroidProperty && list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ContentValues contentValues = (ContentValues) it.next();
                if (contentValues != null) {
                    appendAndroidSpecificProperty("vnd.android.cursor.item/relation", contentValues);
                }
            }
        }
        return this;
    }

    public VCardBuilder appendSipAddresses(List list) {
        boolean z;
        if (this.mIsV30OrV40) {
            z = false;
        } else {
            if (this.mUsesDefactProperty) {
                z = true;
            }
            return this;
        }
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String asString = ((ContentValues) it.next()).getAsString("data1");
                if (!TextUtils.isEmpty(asString)) {
                    if (z) {
                        if (asString.startsWith("sip:")) {
                            if (asString.length() != 4) {
                                asString = asString.substring(4);
                            }
                        }
                        appendLineWithCharsetAndQPDetection(VCardConstants.PROPERTY_X_SIP, asString);
                    } else {
                        if (!asString.startsWith("sip:")) {
                            asString = C0632a.m1025k("sip:", asString);
                        }
                        appendLineWithCharsetAndQPDetection(VCardConfig.isVersion40(this.mVCardType) ? VCardConstants.PROPERTY_TEL : VCardConstants.PROPERTY_IMPP, asString);
                    }
                }
            }
        }
        return this;
    }

    public void appendTelLine(Integer num, String str, String str2, boolean z) {
        int i;
        this.mBuilder.append(VCardConstants.PROPERTY_TEL);
        this.mBuilder.append(";");
        if (num == null) {
            i = 7;
        } else {
            i = num.intValue();
        }
        ArrayList arrayList = new ArrayList();
        boolean z2 = true;
        switch (i) {
            case 0:
                if (!TextUtils.isEmpty(str)) {
                    if (!VCardUtils.isMobilePhoneLabel(str)) {
                        if (!this.mIsV30OrV40) {
                            String upperCase = str.toUpperCase();
                            if (!VCardUtils.isValidInV21ButUnknownToContactsPhoteType(upperCase)) {
                                if (VCardUtils.containsOnlyAlphaDigitHyphen(str)) {
                                    arrayList.add("X-" + str);
                                    break;
                                }
                            } else {
                                arrayList.add(upperCase);
                                break;
                            }
                        } else {
                            arrayList.add(str);
                            break;
                        }
                    } else {
                        arrayList.add(VCardConstants.PARAM_TYPE_CELL);
                        break;
                    }
                } else {
                    arrayList.add(VCardConstants.PARAM_TYPE_VOICE);
                    break;
                }
                break;
            case 1:
                arrayList.addAll(Arrays.asList(new String[]{VCardConstants.PARAM_TYPE_HOME}));
                break;
            case 2:
                arrayList.add(VCardConstants.PARAM_TYPE_CELL);
                break;
            case 3:
                arrayList.addAll(Arrays.asList(new String[]{VCardConstants.PARAM_TYPE_WORK}));
                break;
            case 4:
                arrayList.addAll(Arrays.asList(new String[]{VCardConstants.PARAM_TYPE_WORK, VCardConstants.PARAM_TYPE_FAX}));
                break;
            case 5:
                arrayList.addAll(Arrays.asList(new String[]{VCardConstants.PARAM_TYPE_HOME, VCardConstants.PARAM_TYPE_FAX}));
                break;
            case 6:
                if (!this.mIsDoCoMo) {
                    arrayList.add(VCardConstants.PARAM_TYPE_PAGER);
                    break;
                } else {
                    arrayList.add(VCardConstants.PARAM_TYPE_VOICE);
                    break;
                }
            case 7:
                arrayList.add(VCardConstants.PARAM_TYPE_VOICE);
                break;
            case 9:
                arrayList.add(VCardConstants.PARAM_TYPE_CAR);
                break;
            case 10:
                arrayList.add(VCardConstants.PARAM_TYPE_WORK);
                break;
            case 11:
                arrayList.add(VCardConstants.PARAM_TYPE_ISDN);
                break;
            case 12:
                break;
            case 13:
                arrayList.add(VCardConstants.PARAM_TYPE_FAX);
                break;
            case 15:
                arrayList.add(VCardConstants.PARAM_TYPE_TLX);
                break;
            case 17:
                arrayList.addAll(Arrays.asList(new String[]{VCardConstants.PARAM_TYPE_WORK, VCardConstants.PARAM_TYPE_CELL}));
                break;
            case 18:
                arrayList.add(VCardConstants.PARAM_TYPE_WORK);
                if (!this.mIsDoCoMo) {
                    arrayList.add(VCardConstants.PARAM_TYPE_PAGER);
                    break;
                } else {
                    arrayList.add(VCardConstants.PARAM_TYPE_VOICE);
                    break;
                }
            case 20:
                arrayList.add(VCardConstants.PARAM_TYPE_MSG);
                break;
        }
        z2 = z;
        if (z2) {
            arrayList.add(VCardConstants.PARAM_TYPE_PREF);
        }
        if (arrayList.isEmpty()) {
            appendUncommonPhoneType(this.mBuilder, Integer.valueOf(i));
        } else {
            appendTypeParameters(arrayList);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(str2);
        this.mBuilder.append(VCARD_END_OF_LINE);
    }

    public VCardBuilder appendWebsites(List list) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String asString = ((ContentValues) it.next()).getAsString("data1");
                if (asString != null) {
                    asString = asString.trim();
                }
                if (!TextUtils.isEmpty(asString)) {
                    appendLineWithCharsetAndQPDetection(VCardConstants.PROPERTY_URL, asString);
                }
            }
        }
        return this;
    }

    public void clear() {
        this.mBuilder = new StringBuilder();
        this.mEndAppended = false;
        appendLine(VCardConstants.PROPERTY_BEGIN, VCARD_DATA_VCARD);
        if (VCardConfig.isVersion40(this.mVCardType)) {
            appendLine(VCardConstants.PROPERTY_VERSION, VCardConstants.VERSION_V40);
        } else if (VCardConfig.isVersion30(this.mVCardType)) {
            appendLine(VCardConstants.PROPERTY_VERSION, VCardConstants.VERSION_V30);
        } else {
            if (!VCardConfig.isVersion21(this.mVCardType)) {
                Log.w(LOG_TAG, "Unknown vCard version detected.");
            }
            appendLine(VCardConstants.PROPERTY_VERSION, VCardConstants.VERSION_V21);
        }
    }

    public String toString() {
        if (!this.mEndAppended) {
            if (this.mIsDoCoMo) {
                appendLine(VCardConstants.PROPERTY_X_CLASS, VCARD_DATA_PUBLIC);
                appendLine(VCardConstants.PROPERTY_X_REDUCTION, "");
                appendLine(VCardConstants.PROPERTY_X_NO, "");
                appendLine(VCardConstants.PROPERTY_X_DCM_HMN_MODE, "");
            }
            appendLine(VCardConstants.PROPERTY_END, VCARD_DATA_VCARD);
            this.mEndAppended = true;
        }
        return this.mBuilder.toString();
    }

    public VCardBuilder(int i, String str) {
        this.mVCardType = i;
        if (VCardConfig.isVersion40(i)) {
            Log.w(LOG_TAG, "Should not use vCard 4.0 when building vCard. It is not officially published yet.");
        }
        boolean z = false;
        this.mIsV30OrV40 = VCardConfig.isVersion30(i) || VCardConfig.isVersion40(i);
        this.mShouldUseQuotedPrintable = VCardConfig.shouldUseQuotedPrintable(i);
        this.mIsDoCoMo = VCardConfig.isDoCoMo(i);
        this.mIsJapaneseMobilePhone = VCardConfig.needsToConvertPhoneticString(i);
        this.mOnlyOneNoteFieldIsAvailable = VCardConfig.onlyOneNoteFieldIsAvailable(i);
        this.mUsesAndroidProperty = VCardConfig.usesAndroidSpecificProperty(i);
        this.mUsesDefactProperty = VCardConfig.usesDefactProperty(i);
        this.mRefrainsQPToNameProperties = VCardConfig.shouldRefrainQPToNameProperties(i);
        this.mAppendTypeParamName = VCardConfig.appendTypeParamName(i);
        this.mNeedsToConvertPhoneticString = VCardConfig.needsToConvertPhoneticString(i);
        this.mShouldAppendCharsetParam = (!VCardConfig.isVersion30(i) || !"UTF-8".equalsIgnoreCase(str)) ? true : z;
        if (VCardConfig.isDoCoMo(i)) {
            if (SHIFT_JIS.equalsIgnoreCase(str)) {
                this.mCharset = str;
            } else if (TextUtils.isEmpty(str)) {
                this.mCharset = SHIFT_JIS;
            } else {
                this.mCharset = str;
            }
            this.mVCardCharsetParameter = "CHARSET=SHIFT_JIS";
        } else if (TextUtils.isEmpty(str)) {
            Log.i(LOG_TAG, "Use the charset \"UTF-8\" for export.");
            this.mCharset = "UTF-8";
            this.mVCardCharsetParameter = "CHARSET=UTF-8";
        } else {
            this.mCharset = str;
            this.mVCardCharsetParameter = C0632a.m1025k("CHARSET=", str);
        }
        clear();
    }

    private void appendTypeParameter(StringBuilder sb, String str) {
        if (VCardConfig.isVersion40(this.mVCardType) || ((VCardConfig.isVersion30(this.mVCardType) || this.mAppendTypeParamName) && !this.mIsDoCoMo)) {
            sb.append(VCardConstants.PARAM_TYPE);
            sb.append(VCARD_PARAM_EQUAL);
        }
        sb.append(str);
    }

    public void appendLine(String str, List list) {
        appendLine(str, list, false, false);
    }

    public void appendLineWithCharsetAndQPDetection(String str, List list) {
        appendLineWithCharsetAndQPDetection(str, (List) null, list);
    }

    public void appendLine(String str, String str2, boolean z, boolean z2) {
        appendLine(str, (List) null, str2, z, z2);
    }

    public void appendLineWithCharsetAndQPDetection(String str, List list, String str2) {
        boolean z;
        boolean z2 = !VCardUtils.containsOnlyPrintableAscii(str2);
        if (this.mShouldUseQuotedPrintable) {
            if (!VCardUtils.containsOnlyNonCrLfPrintableAscii(str2)) {
                z = true;
                appendLine(str, list, str2, z2, z);
            }
        }
        z = false;
        appendLine(str, list, str2, z2, z);
    }

    public void appendLine(String str, List list, String str2) {
        appendLine(str, list, str2, false, false);
    }

    public void appendLine(String str, List list, String str2, boolean z, boolean z2) {
        String str3;
        this.mBuilder.append(str);
        if (list != null && list.size() > 0) {
            this.mBuilder.append(";");
            appendTypeParameters(list);
        }
        if (z) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (z2) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
            str3 = encodeQuotedPrintable(str2);
        } else {
            str3 = escapeCharacters(str2);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(str3);
        this.mBuilder.append(VCARD_END_OF_LINE);
    }

    public void appendLineWithCharsetAndQPDetection(String str, List list, List list2) {
        appendLine(str, list, list2, this.mShouldAppendCharsetParam && !VCardUtils.containsOnlyNonCrLfPrintableAscii((Collection) list2), this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii((Collection) list2));
    }

    public void appendLine(String str, List list, boolean z, boolean z2) {
        appendLine(str, (List) null, list, z, z2);
    }

    public void appendLine(String str, List list, List list2, boolean z, boolean z2) {
        String str2;
        this.mBuilder.append(str);
        if (list != null && list.size() > 0) {
            this.mBuilder.append(";");
            appendTypeParameters(list);
        }
        if (z) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (z2) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        boolean z3 = true;
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            if (z2) {
                str2 = encodeQuotedPrintable(str3);
            } else {
                str2 = escapeCharacters(str3);
            }
            if (z3) {
                z3 = false;
            } else {
                this.mBuilder.append(";");
            }
            this.mBuilder.append(str2);
        }
        this.mBuilder.append(VCARD_END_OF_LINE);
    }
}
