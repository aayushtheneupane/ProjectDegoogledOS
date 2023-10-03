package p000;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: ph */
/* compiled from: PG */
final class C0419ph implements View.OnClickListener {

    /* renamed from: a */
    private final View f15509a;

    /* renamed from: b */
    private final String f15510b;

    /* renamed from: c */
    private Method f15511c;

    /* renamed from: d */
    private Context f15512d;

    public C0419ph(View view, String str) {
        this.f15509a = view;
        this.f15510b = str;
    }

    public final void onClick(View view) {
        String str;
        Method method;
        if (this.f15511c == null) {
            Context context = this.f15509a.getContext();
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.f15510b, new Class[]{View.class})) != null) {
                        this.f15511c = method;
                        this.f15512d = context;
                    }
                } catch (NoSuchMethodException e) {
                }
                if (!(context instanceof ContextWrapper)) {
                    context = null;
                } else {
                    context = ((ContextWrapper) context).getBaseContext();
                }
            }
            int id = this.f15509a.getId();
            if (id != -1) {
                str = " with id '" + this.f15509a.getContext().getResources().getResourceEntryName(id) + "'";
            } else {
                str = "";
            }
            throw new IllegalStateException("Could not find method " + this.f15510b + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.f15509a.getClass() + str);
        }
        try {
            this.f15511c.invoke(this.f15512d, new Object[]{view});
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", e2);
        } catch (InvocationTargetException e3) {
            throw new IllegalStateException("Could not execute method for android:onClick", e3);
        }
    }
}
