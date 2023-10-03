package p000;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* renamed from: ws */
/* compiled from: PG */
public final class C0619ws extends C0616wp implements C0617wq {

    /* renamed from: a */
    public static Method f16273a;

    /* renamed from: b */
    public C0617wq f16274b;

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                f16273a = PopupWindow.class.getDeclaredMethod("setTouchModal", new Class[]{Boolean.TYPE});
            }
        } catch (NoSuchMethodException e) {
        }
    }

    public C0619ws(Context context, int i) {
        super(context, (AttributeSet) null, i, (byte[]) null);
    }

    /* renamed from: a */
    public final C0582vi mo10496a(Context context, boolean z) {
        C0618wr wrVar = new C0618wr(context, z);
        wrVar.f16269c = this;
        return wrVar;
    }
}
