package com.android.p032ex.chips;

import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.widget.Filter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/* renamed from: com.android.ex.chips.c */
final class C0668c extends Filter {
    final /* synthetic */ C0684k this$0;

    /* synthetic */ C0668c(C0684k kVar, C0666b bVar) {
        this.this$0 = kVar;
    }

    public CharSequence convertResultToString(Object obj) {
        C0699ra raVar = (C0699ra) obj;
        String displayName = raVar.getDisplayName();
        String destination = raVar.getDestination();
        return (TextUtils.isEmpty(displayName) || TextUtils.equals(displayName, destination)) ? destination : new Rfc822Token(displayName, destination, (String) null).toString();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.widget.Filter.FilterResults performFiltering(java.lang.CharSequence r27) {
        /*
            r26 = this;
            r0 = r26
            android.widget.Filter$FilterResults r1 = new android.widget.Filter$FilterResults
            r1.<init>()
            boolean r2 = android.text.TextUtils.isEmpty(r27)
            if (r2 == 0) goto L_0x0013
            com.android.ex.chips.k r0 = r0.this$0
            r0.clearTempEntries()
            return r1
        L_0x0013:
            com.android.ex.chips.k r2 = r0.this$0
            android.content.Context r2 = r2.mContext
            boolean r2 = com.android.p032ex.chips.C0688m.m1070g(r2)
            r3 = 1
            if (r2 != 0) goto L_0x0067
            com.android.ex.chips.k r2 = r0.this$0
            r2.clearTempEntries()
            com.android.ex.chips.k r0 = r0.this$0
            boolean r0 = r0.mShowRequestPermissionsItem
            if (r0 != 0) goto L_0x002c
            return r1
        L_0x002c:
            java.lang.String[] r19 = com.android.p032ex.chips.C0688m.f805ev
            com.android.ex.chips.ra r0 = new com.android.ex.chips.ra
            r5 = 1
            r8 = 0
            r10 = -1
            r12 = 0
            r13 = -1
            r15 = 0
            r16 = 1
            r17 = 0
            r18 = 0
            java.lang.String r6 = ""
            java.lang.String r7 = ""
            java.lang.String r9 = ""
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r12, r13, r15, r16, r17, r18, r19)
            com.android.ex.chips.d r2 = new com.android.ex.chips.d
            java.util.List r21 = java.util.Collections.singletonList(r0)
            java.util.LinkedHashMap r22 = new java.util.LinkedHashMap
            r22.<init>()
            java.util.List r23 = java.util.Collections.singletonList(r0)
            java.util.Set r24 = java.util.Collections.emptySet()
            r25 = 0
            r20 = r2
            r20.<init>(r21, r22, r23, r24, r25)
            r1.values = r2
            r1.count = r3
            return r1
        L_0x0067:
            r2 = 0
            com.android.ex.chips.k r4 = r0.this$0     // Catch:{ all -> 0x00b9 }
            com.android.ex.chips.k r5 = r0.this$0     // Catch:{ all -> 0x00b9 }
            int r5 = r5.mPreferredMaxResultCount     // Catch:{ all -> 0x00b9 }
            r6 = r27
            android.database.Cursor r4 = r4.doQuery(r6, r5, r2)     // Catch:{ all -> 0x00b9 }
            if (r4 != 0) goto L_0x0077
            goto L_0x00b0
        L_0x0077:
            java.util.LinkedHashMap r7 = new java.util.LinkedHashMap     // Catch:{ all -> 0x00b6 }
            r7.<init>()     // Catch:{ all -> 0x00b6 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x00b6 }
            r8.<init>()     // Catch:{ all -> 0x00b6 }
            java.util.HashSet r9 = new java.util.HashSet     // Catch:{ all -> 0x00b6 }
            r9.<init>()     // Catch:{ all -> 0x00b6 }
        L_0x0086:
            boolean r5 = r4.moveToNext()     // Catch:{ all -> 0x00b6 }
            if (r5 == 0) goto L_0x0095
            com.android.ex.chips.j r5 = new com.android.ex.chips.j     // Catch:{ all -> 0x00b6 }
            r5.<init>(r4, r2)     // Catch:{ all -> 0x00b6 }
            com.android.p032ex.chips.C0684k.putOneEntry(r5, r3, r7, r8, r9)     // Catch:{ all -> 0x00b6 }
            goto L_0x0086
        L_0x0095:
            com.android.ex.chips.k r2 = r0.this$0     // Catch:{ all -> 0x00b6 }
            java.util.List r2 = r2.constructEntryList(r7, r8)     // Catch:{ all -> 0x00b6 }
            com.android.ex.chips.k r0 = r0.this$0     // Catch:{ all -> 0x00b6 }
            java.util.List r10 = r0.searchOtherDirectories(r9)     // Catch:{ all -> 0x00b6 }
            com.android.ex.chips.d r0 = new com.android.ex.chips.d     // Catch:{ all -> 0x00b6 }
            r5 = r0
            r6 = r2
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x00b6 }
            r1.values = r0     // Catch:{ all -> 0x00b6 }
            int r0 = r2.size()     // Catch:{ all -> 0x00b6 }
            r1.count = r0     // Catch:{ all -> 0x00b6 }
        L_0x00b0:
            if (r4 == 0) goto L_0x00b5
            r4.close()
        L_0x00b5:
            return r1
        L_0x00b6:
            r0 = move-exception
            r2 = r4
            goto L_0x00ba
        L_0x00b9:
            r0 = move-exception
        L_0x00ba:
            if (r2 == 0) goto L_0x00bf
            r2.close()
        L_0x00bf:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p032ex.chips.C0668c.performFiltering(java.lang.CharSequence):android.widget.Filter$FilterResults");
    }

    /* access modifiers changed from: protected */
    public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        int i;
        C0684k kVar = this.this$0;
        kVar.mCurrentConstraint = charSequence;
        kVar.clearTempEntries();
        Object obj = filterResults.values;
        if (obj != null) {
            C0670d dVar = (C0670d) obj;
            LinkedHashMap unused = this.this$0.mEntryMap = dVar.f786Su;
            List unused2 = this.this$0.mNonAggregatedEntries = dVar.f787Tu;
            Set unused3 = this.this$0.mExistingDestinations = dVar.f788Uu;
            C0684k kVar2 = this.this$0;
            int size = dVar.entries.size();
            List list = dVar.f789Vu;
            if (list == null) {
                i = 0;
            } else {
                i = list.size();
            }
            kVar2.cacheCurrentEntriesIfNeeded(size, i);
            this.this$0.updateEntries(dVar.entries);
            if (dVar.f789Vu != null) {
                this.this$0.startSearchOtherDirectories(charSequence, dVar.f789Vu, this.this$0.mPreferredMaxResultCount - dVar.f788Uu.size());
                return;
            }
            return;
        }
        this.this$0.updateEntries(Collections.emptyList());
    }
}
