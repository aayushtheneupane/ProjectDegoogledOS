package com.android.voicemail.impl.sms;

import com.android.tools.p006r8.GeneratedOutlineSupport;

public class SyncMessage {
    private final String contentType;
    private final String messageId;
    private final int messageLength;
    private final long msgTimeMillis;
    private final int newMessageCount;
    private final String sender;
    private final String syncTriggerEvent;

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004b A[SYNTHETIC, Splitter:B:20:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005c A[SYNTHETIC, Splitter:B:25:0x005c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SyncMessage(android.os.Bundle r6) {
        /*
            r5 = this;
            r5.<init>()
            java.lang.String r0 = "ev"
            java.lang.String r0 = r6.getString(r0)
            java.lang.String r1 = ""
            if (r0 != 0) goto L_0x000e
            r0 = r1
        L_0x000e:
            r5.syncTriggerEvent = r0
            java.lang.String r0 = "id"
            java.lang.String r0 = r6.getString(r0)
            if (r0 != 0) goto L_0x0019
            r0 = r1
        L_0x0019:
            r5.messageId = r0
            java.lang.String r0 = "l"
            java.lang.String r0 = r6.getString(r0)
            r2 = 0
            if (r0 != 0) goto L_0x0026
        L_0x0024:
            r0 = r2
            goto L_0x002a
        L_0x0026:
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0024 }
        L_0x002a:
            r5.messageLength = r0
            java.lang.String r0 = "t"
            java.lang.String r0 = r6.getString(r0)
            if (r0 != 0) goto L_0x0035
            r0 = r1
        L_0x0035:
            r5.contentType = r0
            java.lang.String r0 = "s"
            java.lang.String r0 = r6.getString(r0)
            if (r0 != 0) goto L_0x0040
            r0 = r1
        L_0x0040:
            r5.sender = r0
            java.lang.String r0 = "c"
            java.lang.String r0 = r6.getString(r0)
            if (r0 != 0) goto L_0x004b
            goto L_0x004f
        L_0x004b:
            int r2 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x004f }
        L_0x004f:
            r5.newMessageCount = r2
            java.lang.String r0 = "dt"
            java.lang.String r6 = r6.getString(r0)
            r0 = 0
            if (r6 != 0) goto L_0x005c
            goto L_0x006d
        L_0x005c:
            java.text.SimpleDateFormat r2 = new java.text.SimpleDateFormat     // Catch:{ ParseException -> 0x006d }
            java.lang.String r3 = "dd/MM/yyyy HH:mm Z"
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ ParseException -> 0x006d }
            r2.<init>(r3, r4)     // Catch:{ ParseException -> 0x006d }
            java.util.Date r6 = r2.parse(r6)     // Catch:{ ParseException -> 0x006d }
            long r0 = r6.getTime()     // Catch:{ ParseException -> 0x006d }
        L_0x006d:
            r5.msgTimeMillis = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.voicemail.impl.sms.SyncMessage.<init>(android.os.Bundle):void");
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getId() {
        return this.messageId;
    }

    public int getLength() {
        return this.messageLength;
    }

    public int getNewMessageCount() {
        return this.newMessageCount;
    }

    public String getSender() {
        return this.sender;
    }

    public String getSyncTriggerEvent() {
        return this.syncTriggerEvent;
    }

    public long getTimestampMillis() {
        return this.msgTimeMillis;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("SyncMessage [mSyncTriggerEvent=");
        outline13.append(this.syncTriggerEvent);
        outline13.append(", mNewMessageCount=");
        outline13.append(this.newMessageCount);
        outline13.append(", mMessageId=");
        outline13.append(this.messageId);
        outline13.append(", mMessageLength=");
        outline13.append(this.messageLength);
        outline13.append(", mContentType=");
        outline13.append(this.contentType);
        outline13.append(", mSender=");
        outline13.append(this.sender);
        outline13.append(", mMsgTimeMillis=");
        outline13.append(this.msgTimeMillis);
        outline13.append("]");
        return outline13.toString();
    }
}
