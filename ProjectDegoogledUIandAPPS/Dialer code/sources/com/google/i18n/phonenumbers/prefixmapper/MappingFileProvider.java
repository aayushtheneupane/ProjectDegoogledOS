package com.google.i18n.phonenumbers.prefixmapper;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class MappingFileProvider implements Externalizable {
    private static final Map<String, String> LOCALE_NORMALIZATION_MAP;
    private List<Set<String>> availableLanguages;
    private int[] countryCallingCodes;
    private int numOfEntries = 0;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("zh_TW", "zh_Hant");
        hashMap.put("zh_HK", "zh_Hant");
        hashMap.put("zh_MO", "zh_Hant");
        LOCALE_NORMALIZATION_MAP = Collections.unmodifiableMap(hashMap);
    }

    private void appendSubsequentLocalePart(String str, StringBuilder sb) {
        if (str.length() > 0) {
            sb.append('_');
            sb.append(str);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006d, code lost:
        if (r0.contains(r7) != false) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ad, code lost:
        if (r0.contains(r7) != false) goto L_0x006f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getFileName(int r6, java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r5 = this;
            int r0 = r7.length()
            java.lang.String r1 = ""
            if (r0 != 0) goto L_0x0009
            return r1
        L_0x0009:
            int[] r0 = r5.countryCallingCodes
            int r0 = java.util.Arrays.binarySearch(r0, r6)
            if (r0 >= 0) goto L_0x0012
            return r1
        L_0x0012:
            java.util.List<java.util.Set<java.lang.String>> r2 = r5.availableLanguages
            java.lang.Object r0 = r2.get(r0)
            java.util.Set r0 = (java.util.Set) r0
            int r2 = r0.size()
            if (r2 <= 0) goto L_0x00ca
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r7)
            r5.appendSubsequentLocalePart(r8, r2)
            r5.appendSubsequentLocalePart(r9, r2)
            java.lang.String r5 = r2.toString()
            java.util.Map<java.lang.String, java.lang.String> r2 = LOCALE_NORMALIZATION_MAP
            java.lang.Object r2 = r2.get(r5)
            java.lang.String r2 = (java.lang.String) r2
            r3 = 95
            if (r2 == 0) goto L_0x0044
            boolean r4 = r0.contains(r2)
            if (r4 == 0) goto L_0x0044
            r5 = r2
            goto L_0x00b1
        L_0x0044:
            boolean r2 = r0.contains(r5)
            if (r2 == 0) goto L_0x004c
            goto L_0x00b1
        L_0x004c:
            int r5 = r8.length()
            if (r5 != 0) goto L_0x0058
            int r5 = r9.length()
            if (r5 > 0) goto L_0x0064
        L_0x0058:
            int r5 = r9.length()
            if (r5 != 0) goto L_0x0066
            int r5 = r8.length()
            if (r5 <= 0) goto L_0x0066
        L_0x0064:
            r5 = 1
            goto L_0x0067
        L_0x0066:
            r5 = 0
        L_0x0067:
            if (r5 == 0) goto L_0x0071
            boolean r5 = r0.contains(r7)
            if (r5 == 0) goto L_0x00b0
        L_0x006f:
            r5 = r7
            goto L_0x00b1
        L_0x0071:
            int r5 = r8.length()
            if (r5 <= 0) goto L_0x00b0
            int r5 = r9.length()
            if (r5 <= 0) goto L_0x00b0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r7)
            r5.append(r3)
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            boolean r8 = r0.contains(r5)
            if (r8 == 0) goto L_0x0093
            goto L_0x00b1
        L_0x0093:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r7)
            r5.append(r3)
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            boolean r8 = r0.contains(r5)
            if (r8 == 0) goto L_0x00a9
            goto L_0x00b1
        L_0x00a9:
            boolean r5 = r0.contains(r7)
            if (r5 == 0) goto L_0x00b0
            goto L_0x006f
        L_0x00b0:
            r5 = r1
        L_0x00b1:
            int r7 = r5.length()
            if (r7 <= 0) goto L_0x00ca
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r6)
            r7.append(r3)
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            return r5
        L_0x00ca:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.i18n.phonenumbers.prefixmapper.MappingFileProvider.getFileName(int, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public void readExternal(ObjectInput objectInput) throws IOException {
        this.numOfEntries = objectInput.readInt();
        int[] iArr = this.countryCallingCodes;
        if (iArr == null || iArr.length < this.numOfEntries) {
            this.countryCallingCodes = new int[this.numOfEntries];
        }
        if (this.availableLanguages == null) {
            this.availableLanguages = new ArrayList();
        }
        for (int i = 0; i < this.numOfEntries; i++) {
            this.countryCallingCodes[i] = objectInput.readInt();
            int readInt = objectInput.readInt();
            HashSet hashSet = new HashSet();
            for (int i2 = 0; i2 < readInt; i2++) {
                hashSet.add(objectInput.readUTF());
            }
            this.availableLanguages.add(hashSet);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numOfEntries; i++) {
            sb.append(this.countryCallingCodes[i]);
            sb.append('|');
            for (String append : new TreeSet(this.availableLanguages.get(i))) {
                sb.append(append);
                sb.append(',');
            }
            sb.append(10);
        }
        return sb.toString();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(this.numOfEntries);
        for (int i = 0; i < this.numOfEntries; i++) {
            objectOutput.writeInt(this.countryCallingCodes[i]);
            Set<String> set = this.availableLanguages.get(i);
            objectOutput.writeInt(set.size());
            for (String writeUTF : set) {
                objectOutput.writeUTF(writeUTF);
            }
        }
    }
}
