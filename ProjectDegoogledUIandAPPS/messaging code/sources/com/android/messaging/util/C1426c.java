package com.android.messaging.util;

import android.net.Uri;
import android.text.TextUtils;
import com.android.messaging.datamodel.data.ParticipantData;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.android.messaging.util.c */
public class C1426c {

    /* renamed from: FJ */
    public static final Uri f2237FJ = new Uri.Builder().scheme("messaging").authority("avatar").appendPath("b").build();

    static {
        m3600a("", false, 0, true);
        m3600a("", false, 0, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0098 A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.net.Uri m3598a(android.net.Uri r6, java.lang.CharSequence r7, java.lang.String r8, java.lang.String r9) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            java.lang.String r1 = "i"
            java.lang.String r2 = "avatar"
            java.lang.String r3 = "messaging"
            if (r0 != 0) goto L_0x0044
            r0 = 0
            char r4 = r7.charAt(r0)
            r5 = 43
            if (r4 == r5) goto L_0x0016
            r0 = 1
        L_0x0016:
            if (r0 == 0) goto L_0x0044
            com.android.messaging.util.C1424b.m3594t(r7)
            android.net.Uri$Builder r8 = new android.net.Uri$Builder
            r8.<init>()
            r8.scheme(r3)
            r8.authority(r2)
            java.lang.String r0 = "l"
            r8.appendPath(r0)
            java.lang.String r7 = java.lang.String.valueOf(r7)
            java.lang.String r0 = "n"
            r8.appendQueryParameter(r0, r7)
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 == 0) goto L_0x003b
            goto L_0x003c
        L_0x003b:
            r7 = r9
        L_0x003c:
            r8.appendQueryParameter(r1, r7)
            android.net.Uri r7 = r8.build()
            goto L_0x0063
        L_0x0044:
            boolean r7 = android.text.TextUtils.isEmpty(r9)
            if (r7 == 0) goto L_0x004b
            goto L_0x004c
        L_0x004b:
            r8 = r9
        L_0x004c:
            android.net.Uri$Builder r7 = new android.net.Uri$Builder
            r7.<init>()
            r7.scheme(r3)
            r7.authority(r2)
            java.lang.String r9 = "d"
            r7.appendPath(r9)
            r7.appendQueryParameter(r1, r8)
            android.net.Uri r7 = r7.build()
        L_0x0063:
            if (r6 == 0) goto L_0x0098
            boolean r8 = com.android.messaging.util.C1488za.m3877z(r6)
            if (r8 == 0) goto L_0x0097
            com.android.messaging.util.C1424b.m3594t(r6)
            com.android.messaging.util.C1424b.m3594t(r7)
            android.net.Uri$Builder r8 = new android.net.Uri$Builder
            r8.<init>()
            r8.scheme(r3)
            r8.authority(r2)
            java.lang.String r9 = "r"
            r8.appendPath(r9)
            java.lang.String r6 = r6.toString()
            java.lang.String r9 = "m"
            r8.appendQueryParameter(r9, r6)
            java.lang.String r6 = r7.toString()
            java.lang.String r7 = "f"
            r8.appendQueryParameter(r7, r6)
            android.net.Uri r6 = r8.build()
        L_0x0097:
            return r6
        L_0x0098:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.util.C1426c.m3598a(android.net.Uri, java.lang.CharSequence, java.lang.String, java.lang.String):android.net.Uri");
    }

    /* renamed from: c */
    public static Uri m3601c(ParticipantData participantData) {
        Uri uri;
        C1424b.m3594t(participantData);
        String qh = participantData.mo6350qh();
        if (qh == null) {
            uri = null;
        } else {
            uri = Uri.parse(qh);
        }
        return m3598a(uri, (CharSequence) participantData.mo6349ph(), participantData.mo6353sf(), participantData.mo6342m());
    }

    /* renamed from: m */
    public static Uri m3602m(List list) {
        C1424b.m3594t(list);
        C1424b.m3592ia(!list.isEmpty());
        if (list.size() == 1) {
            return m3601c((ParticipantData) list.get(0));
        }
        int min = Math.min(list.size(), 4);
        ArrayList arrayList = new ArrayList(min);
        for (int i = 0; i < min; i++) {
            arrayList.add(m3601c((ParticipantData) list.get(i)));
        }
        C1424b.m3594t(arrayList);
        C1424b.m3592ia(!arrayList.isEmpty());
        if (arrayList.size() == 1) {
            Uri uri = (Uri) arrayList.get(0);
            C1424b.m3592ia(m3607t(uri));
            return uri;
        }
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("messaging");
        builder.authority("avatar");
        builder.appendPath("g");
        int min2 = Math.min(arrayList.size(), 4);
        for (int i2 = 0; i2 < min2; i2++) {
            Uri uri2 = (Uri) arrayList.get(i2);
            C1424b.m3594t(uri2);
            C1424b.m3592ia(C1488za.m3877z(uri2) || m3607t(uri2));
            builder.appendQueryParameter("p", uri2.toString());
        }
        return builder.build();
    }

    /* renamed from: p */
    public static String m3603p(Uri uri) {
        C1424b.m3594t(uri);
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.isEmpty()) {
            return null;
        }
        return pathSegments.get(0);
    }

    /* renamed from: q */
    public static List m3604q(Uri uri) {
        C1424b.m3594t(uri);
        return uri.getQueryParameters("p");
    }

    /* renamed from: r */
    public static String m3605r(Uri uri) {
        C1424b.m3594t(uri);
        return uri.getQueryParameter("i");
    }

    /* renamed from: s */
    public static Uri m3606s(Uri uri) {
        C1424b.m3594t(uri);
        String queryParameter = uri.getQueryParameter("m");
        if (queryParameter == null) {
            return null;
        }
        return Uri.parse(queryParameter);
    }

    /* renamed from: t */
    public static boolean m3607t(Uri uri) {
        C1424b.m3594t(uri);
        return uri != null && TextUtils.equals("messaging", uri.getScheme()) && TextUtils.equals("avatar", uri.getAuthority());
    }

    /* renamed from: a */
    public static Uri m3599a(ParticipantData participantData, String str, boolean z, boolean z2) {
        C1424b.m3594t(participantData);
        C1424b.m3592ia(participantData.mo6358wh());
        C1424b.m3592ia(!TextUtils.isEmpty(str) || !TextUtils.isEmpty(participantData.mo6350qh()));
        if (TextUtils.isEmpty(str)) {
            return m3601c(participantData);
        }
        return m3600a(str, z, participantData.mo6355th(), z2);
    }

    /* renamed from: a */
    private static Uri m3600a(String str, boolean z, int i, boolean z2) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("messaging");
        builder.authority("avatar");
        builder.appendPath("s");
        builder.appendQueryParameter("i", str);
        builder.appendQueryParameter("c", String.valueOf(i));
        builder.appendQueryParameter("s", String.valueOf(z));
        builder.appendQueryParameter("g", String.valueOf(z2));
        return builder.build();
    }
}
