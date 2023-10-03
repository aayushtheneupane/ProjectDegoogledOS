package com.android.dialer.databasepopulator;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.os.RemoteException;
import android.provider.CallLog;
import com.android.dialer.common.Assert;
import com.android.dialer.databasepopulator.AutoValue_CallLogPopulator_CallEntry;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.auto.value.AutoValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class CallLogPopulator {
    private static final CallEntry.Builder[] SIMPLE_CALL_LOG;

    @AutoValue
    static abstract class CallEntry {

        static abstract class Builder {
            Builder() {
            }

            /* access modifiers changed from: package-private */
            public abstract CallEntry build();

            /* access modifiers changed from: package-private */
            public abstract Builder setNumber(String str);

            /* access modifiers changed from: package-private */
            public abstract Builder setPresentation(int i);

            /* access modifiers changed from: package-private */
            public abstract Builder setTimeMillis(long j);

            /* access modifiers changed from: package-private */
            public abstract Builder setType(int i);
        }

        CallEntry() {
        }

        static Builder builder() {
            AutoValue_CallLogPopulator_CallEntry.Builder builder = new AutoValue_CallLogPopulator_CallEntry.Builder();
            builder.setPresentation(1);
            return builder;
        }

        /* access modifiers changed from: package-private */
        public ContentValues getAsContentValues() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("type", Integer.valueOf(getType()));
            contentValues.put("number", getNumber());
            contentValues.put("presentation", Integer.valueOf(getPresentation()));
            contentValues.put("date", Long.valueOf(getTimeMillis()));
            return contentValues;
        }

        /* access modifiers changed from: package-private */
        public abstract String getNumber();

        /* access modifiers changed from: package-private */
        public abstract int getPresentation();

        /* access modifiers changed from: package-private */
        public abstract long getTimeMillis();

        /* access modifiers changed from: package-private */
        public abstract int getType();
    }

    static {
        CallEntry.Builder builder = CallEntry.builder();
        builder.setType(3);
        builder.setNumber("+1-302-6365454");
        CallEntry.Builder builder2 = CallEntry.builder();
        builder2.setType(3);
        builder2.setNumber("");
        builder2.setPresentation(3);
        CallEntry.Builder builder3 = CallEntry.builder();
        builder3.setType(5);
        builder3.setNumber("+1-302-6365454");
        CallEntry.Builder builder4 = CallEntry.builder();
        builder4.setType(1);
        builder4.setNumber("+1-302-6365454");
        CallEntry.Builder builder5 = CallEntry.builder();
        builder5.setType(3);
        builder5.setNumber("1234");
        builder5.setPresentation(2);
        CallEntry.Builder builder6 = CallEntry.builder();
        builder6.setType(2);
        builder6.setNumber("+1-302-6365454");
        CallEntry.Builder builder7 = CallEntry.builder();
        builder7.setType(6);
        builder7.setNumber("+1-302-6365454");
        CallEntry.Builder builder8 = CallEntry.builder();
        builder8.setType(2);
        builder8.setNumber("(425) 739-5600");
        CallEntry.Builder builder9 = CallEntry.builder();
        builder9.setType(7);
        builder9.setNumber("(425) 739-5600");
        CallEntry.Builder builder10 = CallEntry.builder();
        builder10.setType(3);
        builder10.setNumber("+1 (425) 739-5600");
        CallEntry.Builder builder11 = CallEntry.builder();
        builder11.setType(2);
        builder11.setNumber("739-5600");
        CallEntry.Builder builder12 = CallEntry.builder();
        builder12.setType(2);
        builder12.setNumber("711");
        CallEntry.Builder builder13 = CallEntry.builder();
        builder13.setType(1);
        builder13.setNumber("711");
        CallEntry.Builder builder14 = CallEntry.builder();
        builder14.setType(2);
        builder14.setNumber("(425) 739-5600");
        CallEntry.Builder builder15 = CallEntry.builder();
        builder15.setType(3);
        builder15.setNumber("+44 (0) 20 7031 3000");
        CallEntry.Builder builder16 = CallEntry.builder();
        builder16.setType(2);
        builder16.setNumber("+1-650-2530000");
        CallEntry.Builder builder17 = CallEntry.builder();
        builder17.setType(2);
        builder17.setNumber("+1 303-245-0086;123,456");
        CallEntry.Builder builder18 = CallEntry.builder();
        builder18.setType(2);
        builder18.setNumber("+1 303-245-0086");
        CallEntry.Builder builder19 = CallEntry.builder();
        builder19.setType(1);
        builder19.setNumber("+1-650-2530000");
        CallEntry.Builder builder20 = CallEntry.builder();
        builder20.setType(3);
        builder20.setNumber("650-2530000");
        CallEntry.Builder builder21 = CallEntry.builder();
        builder21.setType(5);
        builder21.setNumber("2530000");
        CallEntry.Builder builder22 = CallEntry.builder();
        builder22.setType(2);
        builder22.setNumber("+1 404-487-9000");
        CallEntry.Builder builder23 = CallEntry.builder();
        builder23.setType(1);
        builder23.setNumber("+61 2 9374 4001");
        CallEntry.Builder builder24 = CallEntry.builder();
        builder24.setType(2);
        builder24.setNumber("+33 (0)1 42 68 53 00");
        CallEntry.Builder builder25 = CallEntry.builder();
        builder25.setType(2);
        builder25.setNumber("972-74-746-6245");
        CallEntry.Builder builder26 = CallEntry.builder();
        builder26.setType(1);
        builder26.setNumber("+971 4 4509500");
        CallEntry.Builder builder27 = CallEntry.builder();
        builder27.setType(1);
        builder27.setNumber("+971 4 4509500");
        CallEntry.Builder builder28 = CallEntry.builder();
        builder28.setType(2);
        builder28.setNumber("55-31-2128-6800");
        CallEntry.Builder builder29 = CallEntry.builder();
        builder29.setType(3);
        builder29.setNumber("611");
        CallEntry.Builder builder30 = CallEntry.builder();
        builder30.setType(2);
        builder30.setNumber("*86 512-343-5283");
        SIMPLE_CALL_LOG = new CallEntry.Builder[]{builder, builder2, builder3, builder4, builder5, builder6, builder7, builder8, builder9, builder10, builder11, builder12, builder13, builder14, builder15, builder16, builder17, builder18, builder19, builder20, builder21, builder22, builder23, builder24, builder25, builder26, builder27, builder28, builder29, builder30};
    }

    public static void deleteAllCallLog(Context context) {
        Assert.isWorkerThread();
        try {
            context.getContentResolver().applyBatch("call_log", new ArrayList(Arrays.asList(new ContentProviderOperation[]{ContentProviderOperation.newDelete(CallLog.Calls.CONTENT_URI).build()})));
        } catch (OperationApplicationException | RemoteException e) {
            throw new AssertionError(GeneratedOutlineSupport.outline6("failed to delete call log: ", e));
        }
    }

    public static void populateCallLog(Context context, boolean z, boolean z2) {
        Assert.isWorkerThread();
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        List<CallEntry.Builder> arrayList2 = new ArrayList<>();
        if (z2) {
            arrayList2.add(SIMPLE_CALL_LOG[0]);
        } else {
            arrayList2 = Arrays.asList(SIMPLE_CALL_LOG);
        }
        for (int i = 0; i < 4; i++) {
            for (CallEntry.Builder builder : arrayList2) {
                builder.setTimeMillis(currentTimeMillis);
                CallEntry build = builder.build();
                if (!z || builder.build().getType() != 3) {
                    arrayList.add(ContentProviderOperation.newInsert(CallLog.Calls.CONTENT_URI).withValues(build.getAsContentValues()).withYieldAllowed(true).build());
                    currentTimeMillis -= TimeUnit.HOURS.toMillis(1);
                }
            }
        }
        try {
            context.getContentResolver().applyBatch("call_log", arrayList);
        } catch (OperationApplicationException | RemoteException e) {
            throw new AssertionError(GeneratedOutlineSupport.outline6("error adding call entries: ", e));
        }
    }
}
