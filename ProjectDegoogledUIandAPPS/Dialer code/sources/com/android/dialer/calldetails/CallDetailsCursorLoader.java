package com.android.dialer.calldetails;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import com.android.dialer.calldetails.CallDetailsEntries;
import com.android.dialer.common.Assert;
import com.android.dialer.duo.DuoComponent;
import com.android.dialer.duo.stub.DuoStub;

public final class CallDetailsCursorLoader extends CursorLoader {
    public static final String[] COLUMNS_FOR_CALL_DETAILS = {"_id", "call_type", "features", "timestamp", "duration", "data_usage", "phone_account_component_name", "call_mapping_id"};

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    CallDetailsCursorLoader(android.content.Context r9, com.android.dialer.CoalescedIds r10) {
        /*
            r8 = this;
            android.net.Uri r2 = com.android.dialer.calllog.database.contract.AnnotatedCallLogContract.AnnotatedCallLog.CONTENT_URI
            java.lang.String[] r3 = COLUMNS_FOR_CALL_DETAILS
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            r4 = r1
        L_0x000b:
            int r5 = r10.getCoalescedIdCount()
            if (r4 >= r5) goto L_0x0020
            if (r4 == 0) goto L_0x0018
            java.lang.String r5 = ", "
            r0.append(r5)
        L_0x0018:
            java.lang.String r5 = "?"
            r0.append(r5)
            int r4 = r4 + 1
            goto L_0x000b
        L_0x0020:
            java.lang.String r4 = "_id IN ("
            java.lang.String r5 = ")"
            java.lang.String r4 = com.android.tools.p006r8.GeneratedOutlineSupport.outline7(r4, r0, r5)
            int r0 = r10.getCoalescedIdCount()
            java.lang.String[] r5 = new java.lang.String[r0]
        L_0x002e:
            int r0 = r10.getCoalescedIdCount()
            if (r1 >= r0) goto L_0x0041
            long r6 = r10.getCoalescedId(r1)
            java.lang.String r0 = java.lang.String.valueOf(r6)
            r5[r1] = r0
            int r1 = r1 + 1
            goto L_0x002e
        L_0x0041:
            java.lang.String r6 = "timestamp DESC"
            r0 = r8
            r1 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.calldetails.CallDetailsCursorLoader.<init>(android.content.Context, com.android.dialer.CoalescedIds):void");
    }

    static CallDetailsEntries toCallDetailsEntries(Context context, Cursor cursor) {
        Assert.isNotNull(cursor);
        Assert.checkArgument(cursor.moveToFirst());
        CallDetailsEntries.Builder newBuilder = CallDetailsEntries.newBuilder();
        do {
            CallDetailsEntries.CallDetailsEntry.Builder newBuilder2 = CallDetailsEntries.CallDetailsEntry.newBuilder();
            newBuilder2.setCallId(cursor.getLong(0));
            newBuilder2.setCallType(cursor.getInt(1));
            newBuilder2.setFeatures(cursor.getInt(2));
            newBuilder2.setDate(cursor.getLong(3));
            newBuilder2.setDuration(cursor.getLong(4));
            newBuilder2.setDataUsage(cursor.getLong(5));
            newBuilder2.setCallMappingId(cursor.getString(7));
            ((DuoStub) DuoComponent.get(context).getDuo()).isDuoAccount(cursor.getString(6));
            newBuilder2.setIsDuoCall(false);
            newBuilder.addEntries((CallDetailsEntries.CallDetailsEntry) newBuilder2.build());
        } while (cursor.moveToNext());
        return (CallDetailsEntries) newBuilder.build();
    }

    public void onContentChanged() {
    }
}
