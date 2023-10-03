package p000;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import java.io.FileInputStream;
import java.util.concurrent.Callable;

/* renamed from: cxx */
/* compiled from: PG */
public final /* synthetic */ class cxx implements Callable {

    /* renamed from: a */
    private final cxy f5971a;

    /* renamed from: b */
    private final cxp f5972b;

    /* renamed from: c */
    private final Uri f5973c;

    public cxx(cxy cxy, cxp cxp, Uri uri) {
        this.f5971a = cxy;
        this.f5972b = cxp;
        this.f5973c = uri;
    }

    public final Object call() {
        FileInputStream createInputStream;
        cxy cxy = this.f5971a;
        cxp cxp = this.f5972b;
        Uri uri = this.f5973c;
        cxp.mo3948b(uri.toString());
        cxp.mo3946a(uri.getLastPathSegment());
        AssetFileDescriptor a = fra.m9437a(cxy.f5976b.f7846a, uri, "r");
        try {
            if (a.getLength() != -1) {
                cxp.mo3944a(a.getLength());
            }
            createInputStream = a.createInputStream();
            cxy.m5628a(cxp, createInputStream);
            if (createInputStream != null) {
                createInputStream.close();
            }
            if (a == null) {
                return null;
            }
            a.close();
            return null;
        } catch (Throwable th) {
            if (a != null) {
                try {
                    a.close();
                } catch (Throwable th2) {
                    ifn.m12935a(th, th2);
                }
            }
            throw th;
        }
        throw th;
    }
}
