package com.android.incallui.rtt.protocol;

import com.android.dialer.rtt.RttTranscript;
import com.android.dialer.rtt.RttTranscriptMessage;
import com.google.common.base.Splitter;
import java.util.ArrayList;
import java.util.List;

public final class RttChatMessage {
    private static final Splitter SPLITTER = Splitter.m71on("\n");
    private final StringBuilder content = new StringBuilder();
    private boolean isFinished;
    public boolean isRemote;
    private long timstamp = System.currentTimeMillis();

    public static List<RttChatMessage> fromTranscript(RttTranscript rttTranscript) {
        ArrayList arrayList = new ArrayList();
        if (rttTranscript == null) {
            return arrayList;
        }
        for (RttTranscriptMessage next : rttTranscript.getMessagesList()) {
            RttChatMessage rttChatMessage = new RttChatMessage();
            rttChatMessage.append(next.getContent());
            rttChatMessage.timstamp = next.getTimestamp();
            rttChatMessage.isRemote = next.getIsRemote();
            if (next.getIsFinished()) {
                rttChatMessage.isFinished = true;
            }
            arrayList.add(rttChatMessage);
        }
        return arrayList;
    }

    public static int getLastIndexLocalMessage(List<RttChatMessage> list) {
        int size = list.size() - 1;
        while (size >= 0 && list.get(size).isRemote) {
            size--;
        }
        return size;
    }

    public static int getLastIndexRemoteMessage(List<RttChatMessage> list) {
        int size = list.size() - 1;
        while (size >= 0 && !list.get(size).isRemote) {
            size--;
        }
        return size;
    }

    public static List<RttTranscriptMessage> toTranscriptMessageList(List<RttChatMessage> list) {
        ArrayList arrayList = new ArrayList();
        for (RttChatMessage next : list) {
            RttTranscriptMessage.Builder newBuilder = RttTranscriptMessage.newBuilder();
            newBuilder.setContent(next.getContent());
            newBuilder.setTimestamp(next.timstamp);
            newBuilder.setIsRemote(next.isRemote);
            newBuilder.setIsFinished(next.isFinished);
            arrayList.add((RttTranscriptMessage) newBuilder.build());
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void updateRemoteRttChatMessage(java.util.List<com.android.incallui.rtt.protocol.RttChatMessage> r7, java.lang.String r8) {
        /*
            com.android.dialer.common.Assert.isNotNull(r7)
            com.google.common.base.Splitter r0 = SPLITTER
            java.lang.Iterable r0 = r0.split(r8)
            java.util.Iterator r0 = r0.iterator()
        L_0x000d:
            boolean r1 = r0.hasNext()
            r2 = 1
            if (r1 == 0) goto L_0x00d5
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            int r3 = r7.size()
        L_0x001e:
            int r3 = r3 + -1
            if (r3 < 0) goto L_0x0037
            java.lang.Object r4 = r7.get(r3)
            com.android.incallui.rtt.protocol.RttChatMessage r4 = (com.android.incallui.rtt.protocol.RttChatMessage) r4
            boolean r4 = r4.isRemote
            if (r4 == 0) goto L_0x001e
            java.lang.Object r4 = r7.get(r3)
            com.android.incallui.rtt.protocol.RttChatMessage r4 = (com.android.incallui.rtt.protocol.RttChatMessage) r4
            boolean r4 = r4.isFinished
            if (r4 == 0) goto L_0x0037
            goto L_0x001e
        L_0x0037:
            if (r3 >= 0) goto L_0x0057
            com.android.incallui.rtt.protocol.RttChatMessage r3 = new com.android.incallui.rtt.protocol.RttChatMessage
            r3.<init>()
            r3.append(r1)
            r3.isRemote = r2
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x004b
            r3.isFinished = r2
        L_0x004b:
            java.lang.StringBuilder r1 = r3.content
            int r1 = r1.length()
            if (r1 == 0) goto L_0x0074
            r7.add(r3)
            goto L_0x0074
        L_0x0057:
            java.lang.Object r4 = r7.get(r3)
            com.android.incallui.rtt.protocol.RttChatMessage r4 = (com.android.incallui.rtt.protocol.RttChatMessage) r4
            r4.append(r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0068
            r4.isFinished = r2
        L_0x0068:
            java.lang.StringBuilder r1 = r4.content
            int r1 = r1.length()
            if (r1 != 0) goto L_0x0073
            r7.remove(r3)
        L_0x0073:
            r3 = r4
        L_0x0074:
            java.lang.StringBuilder r1 = r3.content
        L_0x0076:
            int r4 = r1.length()
            if (r4 <= 0) goto L_0x000d
            r4 = 0
            char r5 = r1.charAt(r4)
            r6 = 8
            if (r5 != r6) goto L_0x000d
            r7.remove(r3)
            r1.delete(r4, r2)
            int r3 = getLastIndexRemoteMessage(r7)
            if (r3 >= 0) goto L_0x00c2
        L_0x0091:
            int r3 = r1.length()
            if (r3 <= 0) goto L_0x00a1
            char r3 = r1.charAt(r4)
            if (r3 != r6) goto L_0x00a1
            r1.deleteCharAt(r4)
            goto L_0x0091
        L_0x00a1:
            int r3 = r1.length()
            if (r3 <= 0) goto L_0x000d
            com.android.incallui.rtt.protocol.RttChatMessage r3 = new com.android.incallui.rtt.protocol.RttChatMessage
            r3.<init>()
            java.lang.String r1 = r1.toString()
            r3.append(r1)
            r3.isRemote = r2
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00bd
            r3.isFinished = r2
        L_0x00bd:
            r7.add(r3)
            goto L_0x000d
        L_0x00c2:
            java.lang.Object r3 = r7.get(r3)
            com.android.incallui.rtt.protocol.RttChatMessage r3 = (com.android.incallui.rtt.protocol.RttChatMessage) r3
            r3.unfinish()
            java.lang.String r1 = r1.toString()
            r3.append(r1)
            java.lang.StringBuilder r1 = r3.content
            goto L_0x0076
        L_0x00d5:
            java.lang.String r0 = "\n"
            boolean r8 = r8.endsWith(r0)
            if (r8 == 0) goto L_0x00e9
            int r8 = getLastIndexRemoteMessage(r7)
            java.lang.Object r7 = r7.get(r8)
            com.android.incallui.rtt.protocol.RttChatMessage r7 = (com.android.incallui.rtt.protocol.RttChatMessage) r7
            r7.isFinished = r2
        L_0x00e9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.rtt.protocol.RttChatMessage.updateRemoteRttChatMessage(java.util.List, java.lang.String):void");
    }

    public void append(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == 8 && this.content.length() > 0) {
                StringBuilder sb = this.content;
                if (sb.charAt(sb.length() - 1) != 8) {
                    StringBuilder sb2 = this.content;
                    sb2.deleteCharAt(sb2.length() - 1);
                }
            }
            this.content.append(charAt);
        }
    }

    public void finish() {
        this.isFinished = true;
    }

    public String getContent() {
        return this.content.toString();
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public void unfinish() {
        this.isFinished = false;
    }
}
