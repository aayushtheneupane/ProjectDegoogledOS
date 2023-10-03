package com.google.common.base;

import java.util.Arrays;

/* renamed from: com.google.common.base.b */
class C1527b extends C1545t {

    /* renamed from: FM */
    final /* synthetic */ char[] f2403FM;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1527b(String str, char[] cArr) {
        super(str);
        this.f2403FM = cArr;
    }

    /* renamed from: d */
    public boolean mo8551d(char c) {
        return Arrays.binarySearch(this.f2403FM, c) >= 0;
    }
}
