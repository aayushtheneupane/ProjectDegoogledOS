package androidx.core.content.p022a;

import androidx.core.provider.FontRequest;

/* renamed from: androidx.core.content.a.f */
public final class C0313f implements C0310c {

    /* renamed from: bo */
    private final int f312bo;
    private final FontRequest mRequest;
    private final int mStrategy;

    public C0313f(FontRequest fontRequest, int i, int i2) {
        this.mRequest = fontRequest;
        this.mStrategy = i;
        this.f312bo = i2;
    }

    public FontRequest getRequest() {
        return this.mRequest;
    }

    public int getTimeout() {
        return this.f312bo;
    }

    /* renamed from: tc */
    public int mo3404tc() {
        return this.mStrategy;
    }
}
