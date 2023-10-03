package com.android.vcard;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VCardProperty {
    private static final String LOG_TAG = "vCard";
    private byte[] mByteValue;
    private List mGroupList;
    private String mName;
    private Map mParameterMap = new HashMap();
    private String mRawValue;
    private List mValueList;

    public void addGroup(String str) {
        if (this.mGroupList == null) {
            this.mGroupList = new ArrayList();
        }
        this.mGroupList.add(str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.util.Collection} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addParameter(java.lang.String r2, java.lang.String r3) {
        /*
            r1 = this;
            java.util.Map r0 = r1.mParameterMap
            boolean r0 = r0.containsKey(r2)
            if (r0 != 0) goto L_0x0021
            java.lang.String r0 = "TYPE"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0016
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            goto L_0x001b
        L_0x0016:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x001b:
            java.util.Map r1 = r1.mParameterMap
            r1.put(r2, r0)
            goto L_0x002a
        L_0x0021:
            java.util.Map r1 = r1.mParameterMap
            java.lang.Object r1 = r1.get(r2)
            r0 = r1
            java.util.Collection r0 = (java.util.Collection) r0
        L_0x002a:
            r0.add(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardProperty.addParameter(java.lang.String, java.lang.String):void");
    }

    public void addValues(String... strArr) {
        List list = this.mValueList;
        if (list == null) {
            this.mValueList = Arrays.asList(strArr);
        } else {
            list.addAll(Arrays.asList(strArr));
        }
    }

    public byte[] getByteValue() {
        return this.mByteValue;
    }

    public List getGroupList() {
        return this.mGroupList;
    }

    public String getName() {
        return this.mName;
    }

    public Map getParameterMap() {
        return this.mParameterMap;
    }

    public Collection getParameters(String str) {
        return (Collection) this.mParameterMap.get(str);
    }

    public String getRawValue() {
        return this.mRawValue;
    }

    public List getValueList() {
        return this.mValueList;
    }

    public void setByteValue(byte[] bArr) {
        this.mByteValue = bArr;
    }

    public void setName(String str) {
        String str2 = this.mName;
        if (str2 != null) {
            Log.w(LOG_TAG, String.format("Property name is re-defined (existing: %s, requested: %s", new Object[]{str2, str}));
        }
        this.mName = str;
    }

    public void setParameter(String str, String str2) {
        this.mParameterMap.clear();
        addParameter(str, str2);
    }

    public void setRawValue(String str) {
        this.mRawValue = str;
    }

    public void setValues(String... strArr) {
        this.mValueList = Arrays.asList(strArr);
    }

    public void setValues(List list) {
        this.mValueList = list;
    }

    public void addValues(List list) {
        List list2 = this.mValueList;
        if (list2 == null) {
            this.mValueList = new ArrayList(list);
        } else {
            list2.addAll(list);
        }
    }
}
