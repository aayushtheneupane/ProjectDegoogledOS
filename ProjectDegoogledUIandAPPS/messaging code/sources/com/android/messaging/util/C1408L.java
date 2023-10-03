package com.android.messaging.util;

import androidx.core.app.NotificationCompat;
import java.io.File;
import java.io.FilenameFilter;

/* renamed from: com.android.messaging.util.L */
class C1408L implements FilenameFilter {
    final /* synthetic */ C1409M this$0;

    C1408L(C1409M m) {
        this.this$0 = m;
    }

    public boolean accept(File file, String str) {
        return str != null && ((this.this$0.mAction == NotificationCompat.CATEGORY_EMAIL && str.equals("db_copy.db")) || str.startsWith("mmsdump-") || str.startsWith("smsdump-"));
    }
}
