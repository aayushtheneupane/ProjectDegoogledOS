package com.android.messaging.mmslib.p040b;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.Telephony;
import android.util.SparseArray;
import java.util.HashSet;
import java.util.Iterator;
import p000a.p005b.C0027n;

/* renamed from: com.android.messaging.mmslib.b.e */
public final class C1000e extends C0998c {

    /* renamed from: HD */
    private static final UriMatcher f1449HD = new UriMatcher(-1);

    /* renamed from: JD */
    private static final SparseArray f1450JD = new SparseArray();
    private static C1000e sInstance;

    /* renamed from: ED */
    private final SparseArray f1451ED = new SparseArray();

    /* renamed from: FD */
    private final C0027n f1452FD = new C0027n();

    /* renamed from: GD */
    private final HashSet f1453GD = new HashSet();

    static {
        f1449HD.addURI(DefaultApnSettingsLoader.APN_TYPE_MMS, (String) null, 0);
        f1449HD.addURI(DefaultApnSettingsLoader.APN_TYPE_MMS, "#", 1);
        f1449HD.addURI(DefaultApnSettingsLoader.APN_TYPE_MMS, "inbox", 2);
        f1449HD.addURI(DefaultApnSettingsLoader.APN_TYPE_MMS, "inbox/#", 3);
        f1449HD.addURI(DefaultApnSettingsLoader.APN_TYPE_MMS, "sent", 4);
        f1449HD.addURI(DefaultApnSettingsLoader.APN_TYPE_MMS, "sent/#", 5);
        f1449HD.addURI(DefaultApnSettingsLoader.APN_TYPE_MMS, "drafts", 6);
        f1449HD.addURI(DefaultApnSettingsLoader.APN_TYPE_MMS, "drafts/#", 7);
        f1449HD.addURI(DefaultApnSettingsLoader.APN_TYPE_MMS, "outbox", 8);
        f1449HD.addURI(DefaultApnSettingsLoader.APN_TYPE_MMS, "outbox/#", 9);
        f1449HD.addURI("mms-sms", "conversations", 10);
        f1449HD.addURI("mms-sms", "conversations/#", 11);
        f1450JD.put(2, 1);
        f1450JD.put(4, 2);
        f1450JD.put(6, 3);
        f1450JD.put(8, 4);
    }

    private C1000e() {
    }

    /* renamed from: I */
    private C1001f m2306I(Uri uri) {
        this.f1453GD.remove(uri);
        C1001f fVar = (C1001f) super.mo6761s(uri);
        if (fVar == null) {
            return null;
        }
        m2309c(uri, fVar);
        m2307b(uri, fVar);
        return fVar;
    }

    /* renamed from: b */
    private void m2308b(Integer num) {
        if (num != null) {
            HashSet hashSet = (HashSet) this.f1451ED.get(num.intValue());
            this.f1451ED.remove(num.intValue());
            if (hashSet != null) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    Uri uri = (Uri) it.next();
                    this.f1453GD.remove(uri);
                    C1001f fVar = (C1001f) super.mo6761s(uri);
                    if (fVar != null) {
                        m2309c(uri, fVar);
                    }
                }
            }
        }
    }

    /* renamed from: c */
    private void m2309c(Uri uri, C1001f fVar) {
        HashSet hashSet = (HashSet) this.f1452FD.get(Long.valueOf(fVar.getThreadId()));
        if (hashSet != null) {
            hashSet.remove(uri);
        }
    }

    public static final synchronized C1000e getInstance() {
        C1000e eVar;
        synchronized (C1000e.class) {
            if (sInstance == null) {
                sInstance = new C1000e();
            }
            eVar = sInstance;
        }
        return eVar;
    }

    /* renamed from: a */
    public synchronized boolean mo6765a(Uri uri, C1001f fVar) {
        Uri uri2;
        boolean put;
        int fi = fVar.mo6768fi();
        HashSet hashSet = (HashSet) this.f1451ED.get(fi);
        if (hashSet == null) {
            hashSet = new HashSet();
            this.f1451ED.put(fi, hashSet);
        }
        long threadId = fVar.getThreadId();
        HashSet hashSet2 = (HashSet) this.f1452FD.get(Long.valueOf(threadId));
        if (hashSet2 == null) {
            hashSet2 = new HashSet();
            this.f1452FD.put(Long.valueOf(threadId), hashSet2);
        }
        int match = f1449HD.match(uri);
        if (match == 1) {
            uri2 = uri;
        } else if (match == 3 || match == 5 || match == 7 || match == 9) {
            uri2 = Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, uri.getLastPathSegment());
        } else {
            uri2 = null;
        }
        put = super.put(uri2, fVar);
        if (put) {
            hashSet.add(uri2);
            hashSet2.add(uri2);
        }
        mo6764a(uri, false);
        return put;
    }

    /* renamed from: ei */
    public synchronized void mo6758ei() {
        super.mo6758ei();
        this.f1451ED.clear();
        this.f1452FD.clear();
        this.f1453GD.clear();
    }

    /* renamed from: j */
    public synchronized boolean mo6766j(Uri uri) {
        return this.f1453GD.contains(uri);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
        return null;
     */
    /* renamed from: k */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.android.messaging.mmslib.p040b.C1001f mo6767k(android.net.Uri r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            android.content.UriMatcher r0 = f1449HD     // Catch:{ all -> 0x006a }
            int r0 = r0.match(r5)     // Catch:{ all -> 0x006a }
            r1 = 0
            switch(r0) {
                case 0: goto L_0x0065;
                case 1: goto L_0x005f;
                case 2: goto L_0x0052;
                case 3: goto L_0x0042;
                case 4: goto L_0x0052;
                case 5: goto L_0x0042;
                case 6: goto L_0x0052;
                case 7: goto L_0x0042;
                case 8: goto L_0x0052;
                case 9: goto L_0x0042;
                case 10: goto L_0x0065;
                case 11: goto L_0x000d;
                default: goto L_0x000b;
            }
        L_0x000b:
            monitor-exit(r4)
            return r1
        L_0x000d:
            long r2 = android.content.ContentUris.parseId(r5)     // Catch:{ all -> 0x006a }
            a.b.n r5 = r4.f1452FD     // Catch:{ all -> 0x006a }
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x006a }
            java.lang.Object r5 = r5.remove(r0)     // Catch:{ all -> 0x006a }
            java.util.HashSet r5 = (java.util.HashSet) r5     // Catch:{ all -> 0x006a }
            if (r5 == 0) goto L_0x0040
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x006a }
        L_0x0023:
            boolean r0 = r5.hasNext()     // Catch:{ all -> 0x006a }
            if (r0 == 0) goto L_0x0040
            java.lang.Object r0 = r5.next()     // Catch:{ all -> 0x006a }
            android.net.Uri r0 = (android.net.Uri) r0     // Catch:{ all -> 0x006a }
            java.util.HashSet r2 = r4.f1453GD     // Catch:{ all -> 0x006a }
            r2.remove(r0)     // Catch:{ all -> 0x006a }
            java.lang.Object r2 = super.mo6761s(r0)     // Catch:{ all -> 0x006a }
            com.android.messaging.mmslib.b.f r2 = (com.android.messaging.mmslib.p040b.C1001f) r2     // Catch:{ all -> 0x006a }
            if (r2 == 0) goto L_0x0023
            r4.m2307b(r0, r2)     // Catch:{ all -> 0x006a }
            goto L_0x0023
        L_0x0040:
            monitor-exit(r4)
            return r1
        L_0x0042:
            java.lang.String r5 = r5.getLastPathSegment()     // Catch:{ all -> 0x006a }
            android.net.Uri r0 = android.provider.Telephony.Mms.CONTENT_URI     // Catch:{ all -> 0x006a }
            android.net.Uri r5 = android.net.Uri.withAppendedPath(r0, r5)     // Catch:{ all -> 0x006a }
            com.android.messaging.mmslib.b.f r5 = r4.m2306I(r5)     // Catch:{ all -> 0x006a }
            monitor-exit(r4)
            return r5
        L_0x0052:
            android.util.SparseArray r5 = f1450JD     // Catch:{ all -> 0x006a }
            java.lang.Object r5 = r5.get(r0)     // Catch:{ all -> 0x006a }
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ all -> 0x006a }
            r4.m2308b(r5)     // Catch:{ all -> 0x006a }
            monitor-exit(r4)
            return r1
        L_0x005f:
            com.android.messaging.mmslib.b.f r5 = r4.m2306I(r5)     // Catch:{ all -> 0x006a }
            monitor-exit(r4)
            return r5
        L_0x0065:
            r4.mo6758ei()     // Catch:{ all -> 0x006a }
            monitor-exit(r4)
            return r1
        L_0x006a:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.mmslib.p040b.C1000e.mo6767k(android.net.Uri):com.android.messaging.mmslib.b.f");
    }

    /* renamed from: b */
    private void m2307b(Uri uri, C1001f fVar) {
        HashSet hashSet = (HashSet) this.f1452FD.get(Long.valueOf((long) fVar.mo6768fi()));
        if (hashSet != null) {
            hashSet.remove(uri);
        }
    }

    /* renamed from: a */
    public synchronized void mo6764a(Uri uri, boolean z) {
        if (z) {
            this.f1453GD.add(uri);
        } else {
            this.f1453GD.remove(uri);
        }
    }
}
