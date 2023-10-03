package com.android.dialer.calllog.database;

import android.database.Cursor;
import android.database.StaleDataException;
import com.android.dialer.CoalescedIds;
import com.android.dialer.calllog.model.CoalescedRow;
import com.android.dialer.common.Assert;
import com.android.dialer.metrics.FutureTimer;
import com.android.dialer.phonenumberproto.DialerPhoneNumberUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;

public class Coalescer {
    private final ListeningExecutorService backgroundExecutorService;
    private final FutureTimer futureTimer;

    public static final class ExpectedCoalescerException extends Exception {
        ExpectedCoalescerException(Throwable th) {
            super("Expected coalescing exception", th);
        }
    }

    private static final class RowCombiner {
        private final int callTypeColumn;
        private final CoalescedIds.Builder coalescedIdsBuilder = CoalescedIds.newBuilder();
        private final CoalescedRow.Builder coalescedRowBuilder = CoalescedRow.newBuilder();
        private DialerPhoneNumberUtil dialerPhoneNumberUtil = null;
        private final int featuresColumn;
        private final int formattedNumberColumn;
        private final int geocodedLocationColumn;
        private final int idColumn;
        private final int isNewColumn;
        private final int isReadColumn;
        private final int isVoicemailCallColumn;
        private final int numberAttributesColumn;
        private final int numberColumn;
        private final int numberPresentationColumn;
        private final int phoneAccountComponentNameColumn;
        private final int phoneAccountIdColumn;
        private final int timestampColumn;
        private final int voicemailCallTagColumn;

        RowCombiner(Cursor cursor) {
            this.idColumn = cursor.getColumnIndexOrThrow("_id");
            this.timestampColumn = cursor.getColumnIndexOrThrow("timestamp");
            this.numberColumn = cursor.getColumnIndexOrThrow("number");
            this.formattedNumberColumn = cursor.getColumnIndexOrThrow("formatted_number");
            this.numberPresentationColumn = cursor.getColumnIndexOrThrow("presentation");
            this.isReadColumn = cursor.getColumnIndexOrThrow("is_read");
            this.isNewColumn = cursor.getColumnIndexOrThrow("new");
            this.geocodedLocationColumn = cursor.getColumnIndexOrThrow("geocoded_location");
            this.phoneAccountComponentNameColumn = cursor.getColumnIndexOrThrow("phone_account_component_name");
            this.phoneAccountIdColumn = cursor.getColumnIndexOrThrow("phone_account_id");
            this.featuresColumn = cursor.getColumnIndexOrThrow("features");
            this.numberAttributesColumn = cursor.getColumnIndexOrThrow("number_attributes");
            this.isVoicemailCallColumn = cursor.getColumnIndexOrThrow("is_voicemail_call");
            this.voicemailCallTagColumn = cursor.getColumnIndexOrThrow("voicemail_call_tag");
            this.callTypeColumn = cursor.getColumnIndexOrThrow("call_type");
        }

        /* access modifiers changed from: package-private */
        public CoalescedRow combine() {
            CoalescedRow.Builder builder = this.coalescedRowBuilder;
            builder.setCoalescedIds((CoalescedIds) this.coalescedIdsBuilder.build());
            return (CoalescedRow) builder.build();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ac, code lost:
            if (r0 != false) goto L_0x00b8;
         */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00bb A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00bc  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean mergeRow(android.database.Cursor r8) {
            /*
                r7 = this;
                int r0 = r7.callTypeColumn
                int r0 = r8.getInt(r0)
                r1 = 0
                r2 = 1
                r3 = 4
                if (r0 == r3) goto L_0x000d
                r0 = r2
                goto L_0x000e
            L_0x000d:
                r0 = r1
            L_0x000e:
                com.android.dialer.common.Assert.checkArgument(r0)
                com.android.dialer.CoalescedIds$Builder r0 = r7.coalescedIdsBuilder
                java.util.List r0 = r0.getCoalescedIdList()
                boolean r0 = r0.isEmpty()
                java.lang.String r3 = "Unable to parse DialerPhoneNumber bytes"
                if (r0 != 0) goto L_0x00b8
                com.android.dialer.calllog.model.CoalescedRow$Builder r0 = r7.coalescedRowBuilder
                java.lang.String r0 = r0.getPhoneAccountComponentName()
                com.android.dialer.calllog.model.CoalescedRow$Builder r4 = r7.coalescedRowBuilder
                java.lang.String r4 = r4.getPhoneAccountId()
                android.telecom.PhoneAccountHandle r0 = com.android.dialer.telecom.TelecomUtil.composePhoneAccountHandle(r0, r4)
                int r4 = r7.phoneAccountComponentNameColumn
                java.lang.String r4 = r8.getString(r4)
                int r5 = r7.phoneAccountIdColumn
                java.lang.String r5 = r8.getString(r5)
                android.telecom.PhoneAccountHandle r4 = com.android.dialer.telecom.TelecomUtil.composePhoneAccountHandle(r4, r5)
                boolean r0 = java.util.Objects.equals(r0, r4)
                if (r0 == 0) goto L_0x00b6
                com.android.dialer.calllog.model.CoalescedRow$Builder r0 = r7.coalescedRowBuilder
                int r0 = r0.getNumberPresentation()
                int r4 = r7.numberPresentationColumn
                int r4 = r8.getInt(r4)
                if (r0 != r4) goto L_0x0055
                r0 = r2
                goto L_0x0056
            L_0x0055:
                r0 = r1
            L_0x0056:
                if (r0 == 0) goto L_0x00b6
                com.android.dialer.calllog.model.CoalescedRow$Builder r0 = r7.coalescedRowBuilder
                int r0 = r0.getFeatures()
                int r4 = r7.featuresColumn
                int r4 = r8.getInt(r4)
                java.lang.Integer r5 = com.android.dialer.compat.telephony.TelephonyManagerCompat.FEATURES_ASSISTED_DIALING
                int r5 = r5.intValue()
                r5 = r5 & r0
                java.lang.Integer r6 = com.android.dialer.compat.telephony.TelephonyManagerCompat.FEATURES_ASSISTED_DIALING
                int r6 = r6.intValue()
                r6 = r6 & r4
                if (r5 == r6) goto L_0x0075
                goto L_0x0082
            L_0x0075:
                r5 = r0 & 1
                r6 = r4 & 1
                if (r5 == r6) goto L_0x007c
                goto L_0x0082
            L_0x007c:
                r0 = r0 & 32
                r4 = r4 & 32
                if (r0 == r4) goto L_0x0084
            L_0x0082:
                r0 = r1
                goto L_0x0085
            L_0x0084:
                r0 = r2
            L_0x0085:
                if (r0 == 0) goto L_0x00b6
                com.android.dialer.calllog.model.CoalescedRow$Builder r0 = r7.coalescedRowBuilder
                com.android.dialer.DialerPhoneNumber r0 = r0.getNumber()
                int r4 = r7.numberColumn     // Catch:{ InvalidProtocolBufferException -> 0x00af }
                byte[] r4 = r8.getBlob(r4)     // Catch:{ InvalidProtocolBufferException -> 0x00af }
                if (r4 != 0) goto L_0x0097
                r0 = r1
                goto L_0x00ac
            L_0x0097:
                com.android.dialer.DialerPhoneNumber r4 = com.android.dialer.DialerPhoneNumber.parseFrom(r4)     // Catch:{ InvalidProtocolBufferException -> 0x00af }
                com.android.dialer.phonenumberproto.DialerPhoneNumberUtil r5 = r7.dialerPhoneNumberUtil
                if (r5 != 0) goto L_0x00a6
                com.android.dialer.phonenumberproto.DialerPhoneNumberUtil r5 = new com.android.dialer.phonenumberproto.DialerPhoneNumberUtil
                r5.<init>()
                r7.dialerPhoneNumberUtil = r5
            L_0x00a6:
                com.android.dialer.phonenumberproto.DialerPhoneNumberUtil r5 = r7.dialerPhoneNumberUtil
                boolean r0 = r5.isMatch(r0, r4)
            L_0x00ac:
                if (r0 == 0) goto L_0x00b6
                goto L_0x00b8
            L_0x00af:
                r7 = move-exception
                java.lang.AssertionError r8 = new java.lang.AssertionError
                r8.<init>(r3, r7)
                throw r8
            L_0x00b6:
                r0 = r1
                goto L_0x00b9
            L_0x00b8:
                r0 = r2
            L_0x00b9:
                if (r0 != 0) goto L_0x00bc
                return r1
            L_0x00bc:
                com.android.dialer.calllog.model.CoalescedRow$Builder r0 = r7.coalescedRowBuilder
                int r4 = r0.getFeatures()
                int r5 = r7.featuresColumn
                int r5 = r8.getInt(r5)
                r4 = r4 | r5
                r0.setFeatures(r4)
                com.android.dialer.CoalescedIds$Builder r0 = r7.coalescedIdsBuilder
                java.util.List r0 = r0.getCoalescedIdList()
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L_0x00e5
                com.android.dialer.CoalescedIds$Builder r0 = r7.coalescedIdsBuilder
                int r7 = r7.idColumn
                int r7 = r8.getInt(r7)
                long r7 = (long) r7
                r0.addCoalescedId(r7)
                return r2
            L_0x00e5:
                com.android.dialer.calllog.model.CoalescedRow$Builder r0 = r7.coalescedRowBuilder
                int r4 = r7.timestampColumn
                long r4 = r8.getLong(r4)
                r0.setTimestamp(r4)
                int r4 = r7.numberPresentationColumn
                int r4 = r8.getInt(r4)
                r0.setNumberPresentation(r4)
                int r4 = r7.isReadColumn
                int r4 = r8.getInt(r4)
                if (r4 != r2) goto L_0x0103
                r4 = r2
                goto L_0x0104
            L_0x0103:
                r4 = r1
            L_0x0104:
                r0.setIsRead(r4)
                int r4 = r7.isNewColumn
                int r4 = r8.getInt(r4)
                if (r4 != r2) goto L_0x0111
                r4 = r2
                goto L_0x0112
            L_0x0111:
                r4 = r1
            L_0x0112:
                r0.setIsNew(r4)
                int r4 = r7.isVoicemailCallColumn
                int r4 = r8.getInt(r4)
                if (r4 != r2) goto L_0x011e
                r1 = r2
            L_0x011e:
                r0.setIsVoicemailCall(r1)
                int r1 = r7.callTypeColumn
                int r1 = r8.getInt(r1)
                r0.setCallType(r1)
                com.android.dialer.calllog.model.CoalescedRow$Builder r0 = r7.coalescedRowBuilder     // Catch:{ InvalidProtocolBufferException -> 0x01b3 }
                int r1 = r7.numberColumn     // Catch:{ InvalidProtocolBufferException -> 0x01b3 }
                byte[] r1 = r8.getBlob(r1)     // Catch:{ InvalidProtocolBufferException -> 0x01b3 }
                com.android.dialer.DialerPhoneNumber r1 = com.android.dialer.DialerPhoneNumber.parseFrom(r1)     // Catch:{ InvalidProtocolBufferException -> 0x01b3 }
                r0.setNumber(r1)     // Catch:{ InvalidProtocolBufferException -> 0x01b3 }
                int r0 = r7.formattedNumberColumn
                java.lang.String r0 = r8.getString(r0)
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto L_0x014a
                com.android.dialer.calllog.model.CoalescedRow$Builder r1 = r7.coalescedRowBuilder
                r1.setFormattedNumber(r0)
            L_0x014a:
                int r0 = r7.geocodedLocationColumn
                java.lang.String r0 = r8.getString(r0)
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto L_0x015b
                com.android.dialer.calllog.model.CoalescedRow$Builder r1 = r7.coalescedRowBuilder
                r1.setGeocodedLocation(r0)
            L_0x015b:
                int r0 = r7.phoneAccountComponentNameColumn
                java.lang.String r0 = r8.getString(r0)
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto L_0x016c
                com.android.dialer.calllog.model.CoalescedRow$Builder r1 = r7.coalescedRowBuilder
                r1.setPhoneAccountComponentName(r0)
            L_0x016c:
                int r0 = r7.phoneAccountIdColumn
                java.lang.String r0 = r8.getString(r0)
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto L_0x017d
                com.android.dialer.calllog.model.CoalescedRow$Builder r1 = r7.coalescedRowBuilder
                r1.setPhoneAccountId(r0)
            L_0x017d:
                com.android.dialer.calllog.model.CoalescedRow$Builder r0 = r7.coalescedRowBuilder     // Catch:{ InvalidProtocolBufferException -> 0x01aa }
                int r1 = r7.numberAttributesColumn     // Catch:{ InvalidProtocolBufferException -> 0x01aa }
                byte[] r1 = r8.getBlob(r1)     // Catch:{ InvalidProtocolBufferException -> 0x01aa }
                com.android.dialer.NumberAttributes r1 = com.android.dialer.NumberAttributes.parseFrom(r1)     // Catch:{ InvalidProtocolBufferException -> 0x01aa }
                r0.setNumberAttributes(r1)     // Catch:{ InvalidProtocolBufferException -> 0x01aa }
                int r0 = r7.voicemailCallTagColumn
                java.lang.String r0 = r8.getString(r0)
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto L_0x019d
                com.android.dialer.calllog.model.CoalescedRow$Builder r1 = r7.coalescedRowBuilder
                r1.setVoicemailCallTag(r0)
            L_0x019d:
                com.android.dialer.CoalescedIds$Builder r0 = r7.coalescedIdsBuilder
                int r7 = r7.idColumn
                int r7 = r8.getInt(r7)
                long r7 = (long) r7
                r0.addCoalescedId(r7)
                return r2
            L_0x01aa:
                r7 = move-exception
                java.lang.AssertionError r8 = new java.lang.AssertionError
                java.lang.String r0 = "Unable to parse NumberAttributes bytes"
                r8.<init>(r0, r7)
                throw r8
            L_0x01b3:
                r7 = move-exception
                java.lang.AssertionError r8 = new java.lang.AssertionError
                r8.<init>(r3, r7)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.calllog.database.Coalescer.RowCombiner.mergeRow(android.database.Cursor):boolean");
        }

        /* access modifiers changed from: package-private */
        public void startNewGroup() {
            this.coalescedRowBuilder.clear();
            this.coalescedIdsBuilder.clear();
        }
    }

    Coalescer(ListeningExecutorService listeningExecutorService, FutureTimer futureTimer2) {
        this.backgroundExecutorService = listeningExecutorService;
        this.futureTimer = futureTimer2;
    }

    public ListenableFuture<ImmutableList<CoalescedRow>> coalesce(Cursor cursor) {
        ListenableFuture<ImmutableList<CoalescedRow>> submit = this.backgroundExecutorService.submit(new Callable(cursor) {
            private final /* synthetic */ Cursor f$1;

            {
                this.f$1 = r2;
            }

            public final Object call() {
                return Coalescer.this.lambda$coalesce$0$Coalescer(this.f$1);
            }
        });
        this.futureTimer.applyTiming(submit, "NewCallLog.Coalesce");
        return submit;
    }

    public /* synthetic */ ImmutableList lambda$coalesce$0$Coalescer(Cursor cursor) throws Exception {
        Assert.isNotNull(cursor);
        Cursor cursor2 = cursor;
        Assert.isWorkerThread();
        ImmutableList.Builder builder = new ImmutableList.Builder();
        try {
            if (!cursor2.moveToFirst()) {
                return ImmutableList.m74of();
            }
            RowCombiner rowCombiner = new RowCombiner(cursor2);
            rowCombiner.startNewGroup();
            long j = 0;
            do {
                boolean mergeRow = rowCombiner.mergeRow(cursor2);
                if (mergeRow) {
                    cursor2.moveToNext();
                }
                if (!mergeRow || cursor2.isAfterLast()) {
                    CoalescedRow.Builder builder2 = (CoalescedRow.Builder) rowCombiner.combine().toBuilder();
                    builder2.setId(j);
                    builder.add((Object) (CoalescedRow) builder2.build());
                    rowCombiner.startNewGroup();
                    j = 1 + j;
                }
            } while (!cursor2.isAfterLast());
            return builder.build();
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null || ((!(e instanceof StaleDataException) || !message.startsWith("Attempting to access a closed CursorWindow")) && (!(e instanceof IllegalStateException) || !message.startsWith("attempt to re-open an already-closed object")))) {
                throw e;
            }
            throw new ExpectedCoalescerException(e);
        }
    }
}
