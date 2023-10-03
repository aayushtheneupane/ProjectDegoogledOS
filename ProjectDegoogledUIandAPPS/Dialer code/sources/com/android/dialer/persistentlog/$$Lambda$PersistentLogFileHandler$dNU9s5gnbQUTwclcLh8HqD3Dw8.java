package com.android.dialer.persistentlog;

import java.io.File;
import java.util.Comparator;

/* renamed from: com.android.dialer.persistentlog.-$$Lambda$PersistentLogFileHandler$dNU9s5gnbQUTwclc-Lh8HqD3Dw8  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$PersistentLogFileHandler$dNU9s5gnbQUTwclcLh8HqD3Dw8 implements Comparator {
    public static final /* synthetic */ $$Lambda$PersistentLogFileHandler$dNU9s5gnbQUTwclcLh8HqD3Dw8 INSTANCE = new $$Lambda$PersistentLogFileHandler$dNU9s5gnbQUTwclcLh8HqD3Dw8();

    private /* synthetic */ $$Lambda$PersistentLogFileHandler$dNU9s5gnbQUTwclcLh8HqD3Dw8() {
    }

    public final int compare(Object obj, Object obj2) {
        return Long.compare(Long.valueOf(((File) obj).getName()).longValue(), Long.valueOf(((File) obj2).getName()).longValue());
    }
}
