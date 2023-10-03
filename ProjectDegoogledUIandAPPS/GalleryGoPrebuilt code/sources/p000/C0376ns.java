package p000;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.Editable;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* renamed from: ns */
/* compiled from: PG */
public final class C0376ns implements ActionMode.Callback {

    /* renamed from: a */
    private final ActionMode.Callback f15307a;

    /* renamed from: b */
    private final TextView f15308b;

    /* renamed from: c */
    private Class f15309c;

    /* renamed from: d */
    private Method f15310d;

    /* renamed from: e */
    private boolean f15311e;

    /* renamed from: f */
    private boolean f15312f = false;

    public C0376ns(ActionMode.Callback callback, TextView textView) {
        this.f15307a = callback;
        this.f15308b = textView;
    }

    /* renamed from: a */
    private static final Intent m14832a() {
        return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
    }

    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.f15307a.onActionItemClicked(actionMode, menuItem);
    }

    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.f15307a.onCreateActionMode(actionMode, menu);
    }

    public final void onDestroyActionMode(ActionMode actionMode) {
        this.f15307a.onDestroyActionMode(actionMode);
    }

    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        Method method;
        boolean z;
        Context context = this.f15308b.getContext();
        PackageManager packageManager = context.getPackageManager();
        if (!this.f15312f) {
            this.f15312f = true;
            try {
                Class<?> cls = Class.forName("com.android.internal.view.menu.MenuBuilder");
                this.f15309c = cls;
                this.f15310d = cls.getDeclaredMethod("removeItemAt", new Class[]{Integer.TYPE});
                this.f15311e = true;
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                this.f15309c = null;
                this.f15310d = null;
                this.f15311e = false;
            }
        }
        try {
            if (!this.f15311e || !this.f15309c.isInstance(menu)) {
                method = menu.getClass().getDeclaredMethod("removeItemAt", new Class[]{Integer.TYPE});
            } else {
                method = this.f15310d;
            }
            for (int size = menu.size() - 1; size >= 0; size--) {
                MenuItem item = menu.getItem(size);
                if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                    method.invoke(menu, new Object[]{Integer.valueOf(size)});
                }
            }
            ArrayList arrayList = new ArrayList();
            if (context instanceof Activity) {
                for (ResolveInfo next : packageManager.queryIntentActivities(m14832a(), 0)) {
                    if (context.getPackageName().equals(next.activityInfo.packageName) || (next.activityInfo.exported && (next.activityInfo.permission == null || context.checkSelfPermission(next.activityInfo.permission) == 0))) {
                        arrayList.add(next);
                    }
                }
            }
            for (int i = 0; i < arrayList.size(); i++) {
                ResolveInfo resolveInfo = (ResolveInfo) arrayList.get(i);
                MenuItem add = menu.add(0, 0, i + 100, resolveInfo.loadLabel(packageManager));
                TextView textView = this.f15308b;
                Intent a = m14832a();
                if (!(textView instanceof Editable) || !textView.onCheckIsTextEditor() || !textView.isEnabled()) {
                    z = false;
                } else {
                    z = true;
                }
                add.setIntent(a.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !z).setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name)).setShowAsAction(1);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
        }
        return this.f15307a.onPrepareActionMode(actionMode, menu);
    }
}
