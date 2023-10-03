package com.android.settings.network.telephony;

import com.android.internal.telephony.OperatorInfo;
import java.util.function.Function;

/* renamed from: com.android.settings.network.telephony.-$$Lambda$NetworkScanHelper$NetworkScanSyncTask$S4S8B3lK5MrUdNHJtqazaW_GHXQ */
/* compiled from: lambda */
public final /* synthetic */ class C0968x8d05bedc implements Function {
    public static final /* synthetic */ C0968x8d05bedc INSTANCE = new C0968x8d05bedc();

    private /* synthetic */ C0968x8d05bedc() {
    }

    public final Object apply(Object obj) {
        return CellInfoUtil.convertOperatorInfoToCellInfo((OperatorInfo) obj);
    }
}
