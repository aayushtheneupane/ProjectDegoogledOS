package com.google.common.p043io;

import java.io.ByteArrayOutputStream;

/* renamed from: com.google.common.io.c */
final class C1714c extends ByteArrayOutputStream {
    /* synthetic */ C1714c(C1713b bVar) {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public void mo9354f(byte[] bArr, int i) {
        System.arraycopy(this.buf, 0, bArr, i, this.count);
    }
}
