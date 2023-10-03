package com.google.protobuf;

public class LazyFieldLite {
    private ByteString delayedBytes;
    private ExtensionRegistryLite extensionRegistry;
    protected volatile MessageLite value;

    static {
        ExtensionRegistryLite.getEmptyRegistry();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:19|20) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r3.value = r4;
        r4 = com.google.protobuf.ByteString.EMPTY;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x002b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void ensureInitialized(com.google.protobuf.MessageLite r4) {
        /*
            r3 = this;
            com.google.protobuf.MessageLite r0 = r3.value
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r3)
            com.google.protobuf.MessageLite r0 = r3.value     // Catch:{ all -> 0x0031 }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            return
        L_0x000c:
            com.google.protobuf.ByteString r0 = r3.delayedBytes     // Catch:{ InvalidProtocolBufferException -> 0x002b }
            if (r0 == 0) goto L_0x0026
            r0 = r4
            com.google.protobuf.GeneratedMessageLite r0 = (com.google.protobuf.GeneratedMessageLite) r0
            com.google.protobuf.Parser r0 = r0.getParserForType()     // Catch:{ InvalidProtocolBufferException -> 0x002b }
            com.google.protobuf.ByteString r1 = r3.delayedBytes     // Catch:{ InvalidProtocolBufferException -> 0x002b }
            com.google.protobuf.ExtensionRegistryLite r2 = r3.extensionRegistry     // Catch:{ InvalidProtocolBufferException -> 0x002b }
            com.google.protobuf.AbstractParser r0 = (com.google.protobuf.AbstractParser) r0
            java.lang.Object r0 = r0.parseFrom(r1, r2)     // Catch:{ InvalidProtocolBufferException -> 0x002b }
            com.google.protobuf.MessageLite r0 = (com.google.protobuf.MessageLite) r0     // Catch:{ InvalidProtocolBufferException -> 0x002b }
            r3.value = r0     // Catch:{ InvalidProtocolBufferException -> 0x002b }
            goto L_0x002f
        L_0x0026:
            r3.value = r4     // Catch:{ InvalidProtocolBufferException -> 0x002b }
            com.google.protobuf.ByteString r4 = com.google.protobuf.ByteString.EMPTY     // Catch:{ InvalidProtocolBufferException -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r3.value = r4     // Catch:{ all -> 0x0031 }
            com.google.protobuf.ByteString r4 = com.google.protobuf.ByteString.EMPTY     // Catch:{ all -> 0x0031 }
        L_0x002f:
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            return
        L_0x0031:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.LazyFieldLite.ensureInitialized(com.google.protobuf.MessageLite):void");
    }

    public MessageLite setValue(MessageLite messageLite) {
        MessageLite messageLite2 = this.value;
        this.delayedBytes = null;
        this.value = messageLite;
        return messageLite2;
    }
}
