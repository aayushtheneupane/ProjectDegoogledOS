package com.android.dialer.calllog.database;

import android.content.ContentValues;
import java.util.function.Predicate;

/* renamed from: com.android.dialer.calllog.database.-$$Lambda$CallLogDatabaseModule$AfKA6tmxPWyegMYETUquh9sWXds  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CallLogDatabaseModule$AfKA6tmxPWyegMYETUquh9sWXds implements Predicate {
    public static final /* synthetic */ $$Lambda$CallLogDatabaseModule$AfKA6tmxPWyegMYETUquh9sWXds INSTANCE = new $$Lambda$CallLogDatabaseModule$AfKA6tmxPWyegMYETUquh9sWXds();

    private /* synthetic */ $$Lambda$CallLogDatabaseModule$AfKA6tmxPWyegMYETUquh9sWXds() {
    }

    public final boolean test(Object obj) {
        return CallLogDatabaseModule.lambda$checkCallTypeColumn$1((ContentValues) obj);
    }
}
