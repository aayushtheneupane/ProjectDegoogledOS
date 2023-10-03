package com.google.common.p043io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.google.common.io.h */
final class C1719h extends C1712a {
    private final File file;

    /* synthetic */ C1719h(File file2, C1718g gVar) {
        if (file2 != null) {
            this.file = file2;
            return;
        }
        throw new NullPointerException();
    }

    public byte[] read() {
        byte[] bArr;
        C1717f create = C1717f.create();
        try {
            FileInputStream fileInputStream = (FileInputStream) create.mo9355a((Closeable) new FileInputStream(this.file));
            long size = fileInputStream.getChannel().size();
            if (size <= 2147483647L) {
                if (size == 0) {
                    bArr = C1715d.m4648e(fileInputStream);
                } else {
                    bArr = C1715d.m4647a(fileInputStream, (int) size);
                }
                create.close();
                return bArr;
            }
            throw new OutOfMemoryError("file is too large to fit in a byte array: " + size + " bytes");
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("Files.asByteSource(");
        Pa.append(this.file);
        Pa.append(")");
        return Pa.toString();
    }
}
