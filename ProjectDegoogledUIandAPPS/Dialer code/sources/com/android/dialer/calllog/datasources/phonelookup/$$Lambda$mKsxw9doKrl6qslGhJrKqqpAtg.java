package com.android.dialer.calllog.datasources.phonelookup;

import com.android.dialer.DialerPhoneNumber;
import com.google.common.base.Function;

/* renamed from: com.android.dialer.calllog.datasources.phonelookup.-$$Lambda$-mKsxw9doKrl6qslGhJrKqqpAtg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$mKsxw9doKrl6qslGhJrKqqpAtg implements Function {
    public static final /* synthetic */ $$Lambda$mKsxw9doKrl6qslGhJrKqqpAtg INSTANCE = new $$Lambda$mKsxw9doKrl6qslGhJrKqqpAtg();

    private /* synthetic */ $$Lambda$mKsxw9doKrl6qslGhJrKqqpAtg() {
    }

    public final Object apply(Object obj) {
        return ((DialerPhoneNumber) obj).getNormalizedNumber();
    }
}
