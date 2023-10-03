package com.android.contacts.util;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.Context;
import android.content.res.XmlResourceParser;
import com.android.contacts.model.account.ExternalAccountType;

public class LocalizedNameResolver {
    public static String getAllContactsName(Context context, String str) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        } else if (str == null) {
            return null;
        } else {
            return resolveAllContactsName(context, str);
        }
    }

    private static String resolveAllContactsName(Context context, String str) {
        for (AuthenticatorDescription authenticatorDescription : AccountManager.get(context).getAuthenticatorTypes()) {
            if (str.equals(authenticatorDescription.type)) {
                return resolveAllContactsNameFromMetaData(context, authenticatorDescription.packageName);
            }
        }
        return null;
    }

    private static String resolveAllContactsNameFromMetaData(Context context, String str) {
        XmlResourceParser loadContactsXml = ExternalAccountType.loadContactsXml(context, str);
        if (loadContactsXml != null) {
            return loadAllContactsNameFromXml(context, loadContactsXml, str);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        r9 = r8.obtainStyledAttributes(r1, com.android.contacts.R$styleable.ContactsDataKind);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2 = r9.getNonResourceString(5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0040, code lost:
        if (r2 == null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r9.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0045, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r1 = r9.getResourceId(5, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004b, code lost:
        if (r1 != 0) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r9.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0050, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r8 = r8.getPackageManager().getResourcesForApplication(r10).getString(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r9.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0060, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0061, code lost:
        r9.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0064, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0065, code lost:
        r9.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0068, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x006f A[Catch:{ all -> 0x0069, XmlPullParserException -> 0x007e, IOException -> 0x0077 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0013 A[Catch:{ all -> 0x0069, XmlPullParserException -> 0x007e, IOException -> 0x0077 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String loadAllContactsNameFromXml(android.content.Context r8, org.xmlpull.v1.XmlPullParser r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "Problem reading XML"
            android.util.AttributeSet r1 = android.util.Xml.asAttributeSet(r9)     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
        L_0x0006:
            int r2 = r9.next()     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            r3 = 1
            r4 = 2
            if (r2 == r4) goto L_0x0011
            if (r2 == r3) goto L_0x0011
            goto L_0x0006
        L_0x0011:
            if (r2 != r4) goto L_0x006f
            int r2 = r9.getDepth()     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
        L_0x0017:
            int r5 = r9.next()     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            r6 = 3
            r7 = 0
            if (r5 != r6) goto L_0x0025
            int r6 = r9.getDepth()     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            if (r6 <= r2) goto L_0x006e
        L_0x0025:
            if (r5 == r3) goto L_0x006e
            java.lang.String r6 = r9.getName()     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            if (r5 != r4) goto L_0x0017
            java.lang.String r5 = "ContactsDataKind"
            boolean r5 = r5.equals(r6)     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            if (r5 == 0) goto L_0x0017
            int[] r9 = com.android.contacts.R$styleable.ContactsDataKind     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            android.content.res.TypedArray r9 = r8.obtainStyledAttributes(r1, r9)     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            r1 = 5
            java.lang.String r2 = r9.getNonResourceString(r1)     // Catch:{ all -> 0x0069 }
            if (r2 == 0) goto L_0x0046
            r9.recycle()     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            return r2
        L_0x0046:
            r2 = 0
            int r1 = r9.getResourceId(r1, r2)     // Catch:{ all -> 0x0069 }
            if (r1 != 0) goto L_0x0051
            r9.recycle()     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            return r7
        L_0x0051:
            android.content.pm.PackageManager r8 = r8.getPackageManager()     // Catch:{ all -> 0x0069 }
            android.content.res.Resources r8 = r8.getResourcesForApplication(r10)     // Catch:{ NameNotFoundException -> 0x0065 }
            java.lang.String r8 = r8.getString(r1)     // Catch:{ NotFoundException -> 0x0061 }
            r9.recycle()     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            return r8
        L_0x0061:
            r9.recycle()     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            return r7
        L_0x0065:
            r9.recycle()     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            return r7
        L_0x0069:
            r8 = move-exception
            r9.recycle()     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            throw r8     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
        L_0x006e:
            return r7
        L_0x006f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            java.lang.String r9 = "No start tag found"
            r8.<init>(r9)     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
            throw r8     // Catch:{ XmlPullParserException -> 0x007e, IOException -> 0x0077 }
        L_0x0077:
            r8 = move-exception
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r0, r8)
            throw r9
        L_0x007e:
            r8 = move-exception
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r0, r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.contacts.util.LocalizedNameResolver.loadAllContactsNameFromXml(android.content.Context, org.xmlpull.v1.XmlPullParser, java.lang.String):java.lang.String");
    }
}
