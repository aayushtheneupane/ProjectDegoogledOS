package p000;

import android.content.Context;
import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import java.util.Locale;

/* renamed from: qb */
/* compiled from: PG */
public final class C0440qb implements TransformationMethod {

    /* renamed from: a */
    private final Locale f15601a;

    public C0440qb(Context context) {
        this.f15601a = context.getResources().getConfiguration().locale;
    }

    public final void onFocusChanged(View view, CharSequence charSequence, boolean z, int i, Rect rect) {
    }

    public final CharSequence getTransformation(CharSequence charSequence, View view) {
        if (charSequence != null) {
            return charSequence.toString().toUpperCase(this.f15601a);
        }
        return null;
    }
}
