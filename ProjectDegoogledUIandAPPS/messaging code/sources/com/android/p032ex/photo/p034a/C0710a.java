package com.android.p032ex.photo.p034a;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcelable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.fragment.app.C0396O;
import androidx.fragment.app.C0424j;
import androidx.fragment.app.C0433s;
import androidx.viewpager.widget.C0616a;
import java.util.HashMap;
import p000a.p005b.C0020g;

/* renamed from: com.android.ex.photo.a.a */
public abstract class C0710a extends C0616a {
    /* access modifiers changed from: private */

    /* renamed from: Lu */
    public C0396O f834Lu = null;

    /* renamed from: Mu */
    private C0424j f835Mu = null;

    /* renamed from: Nu */
    private C0020g f836Nu = new C0711b(this, 5);

    /* renamed from: Ou */
    protected SparseIntArray f837Ou;

    /* renamed from: Pu */
    protected final HashMap f838Pu;
    protected Context mContext;
    protected Cursor mCursor;
    private final C0433s mFragmentManager;
    protected int mRowIDColumn;

    public C0710a(Context context, C0433s sVar, Cursor cursor) {
        this.mFragmentManager = sVar;
        this.f838Pu = new HashMap();
        boolean z = cursor != null;
        this.mCursor = cursor;
        this.mContext = context;
        this.mRowIDColumn = z ? this.mCursor.getColumnIndex("uri") : -1;
    }

    /* renamed from: zb */
    private boolean m1104zb(int i) {
        Cursor cursor = this.mCursor;
        if (cursor == null || cursor.isClosed()) {
            return false;
        }
        return this.mCursor.moveToPosition(i);
    }

    /* renamed from: a */
    public abstract C0424j mo5710a(Context context, Cursor cursor, int i);

    public void destroyItem(View view, int i, Object obj) {
        this.f838Pu.remove(obj);
        if (this.f834Lu == null) {
            this.f834Lu = this.mFragmentManager.beginTransaction();
        }
        C0424j jVar = (C0424j) obj;
        String tag = jVar.getTag();
        if (tag == null) {
            tag = mo5712r(view.getId(), i);
        }
        this.f836Nu.put(tag, jVar);
        this.f834Lu.mo4183p(jVar);
    }

    public void finishUpdate(View view) {
        if (this.f834Lu != null && !this.mFragmentManager.isDestroyed()) {
            this.f834Lu.commitAllowingStateLoss();
            this.f834Lu = null;
            this.mFragmentManager.executePendingTransactions();
        }
    }

    public int getCount() {
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            return cursor.getCount();
        }
        return 0;
    }

    public Cursor getCursor() {
        return this.mCursor;
    }

    public int getItemPosition(Object obj) {
        SparseIntArray sparseIntArray;
        Integer num = (Integer) this.f838Pu.get(obj);
        if (num == null || (sparseIntArray = this.f837Ou) == null) {
            return -2;
        }
        return sparseIntArray.get(num.intValue(), -2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object instantiateItem(android.view.View r5, int r6) {
        /*
            r4 = this;
            android.database.Cursor r0 = r4.mCursor
            if (r0 == 0) goto L_0x007e
            boolean r0 = r4.m1104zb(r6)
            r1 = 0
            if (r0 == 0) goto L_0x001c
            android.database.Cursor r0 = r4.mCursor
            int r2 = r4.mRowIDColumn
            java.lang.String r0 = r0.getString(r2)
            int r0 = r0.hashCode()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L_0x001d
        L_0x001c:
            r0 = r1
        L_0x001d:
            androidx.fragment.app.O r2 = r4.f834Lu
            if (r2 != 0) goto L_0x0029
            androidx.fragment.app.s r2 = r4.mFragmentManager
            androidx.fragment.app.O r2 = r2.beginTransaction()
            r4.f834Lu = r2
        L_0x0029:
            int r2 = r5.getId()
            java.lang.String r2 = r4.mo5712r(r2, r6)
            a.b.g r3 = r4.f836Nu
            r3.remove(r2)
            androidx.fragment.app.s r3 = r4.mFragmentManager
            androidx.fragment.app.j r2 = r3.findFragmentByTag(r2)
            if (r2 == 0) goto L_0x0045
            androidx.fragment.app.O r5 = r4.f834Lu
            r5.mo4182o(r2)
        L_0x0043:
            r1 = r2
            goto L_0x006e
        L_0x0045:
            android.database.Cursor r2 = r4.mCursor
            if (r2 == 0) goto L_0x0058
            boolean r2 = r4.m1104zb(r6)
            if (r2 == 0) goto L_0x0058
            android.content.Context r2 = r4.mContext
            android.database.Cursor r3 = r4.mCursor
            androidx.fragment.app.j r2 = r4.mo5710a(r2, r3, r6)
            goto L_0x0059
        L_0x0058:
            r2 = r1
        L_0x0059:
            if (r2 != 0) goto L_0x005c
            goto L_0x0076
        L_0x005c:
            androidx.fragment.app.O r1 = r4.f834Lu
            int r3 = r5.getId()
            int r5 = r5.getId()
            java.lang.String r5 = r4.mo5712r(r5, r6)
            r1.mo4175a(r3, r2, r5)
            goto L_0x0043
        L_0x006e:
            androidx.fragment.app.j r5 = r4.f835Mu
            if (r1 == r5) goto L_0x0076
            r5 = 0
            r1.setMenuVisibility(r5)
        L_0x0076:
            if (r1 == 0) goto L_0x007d
            java.util.HashMap r4 = r4.f838Pu
            r4.put(r1, r0)
        L_0x007d:
            return r1
        L_0x007e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "this should only be called when the cursor is valid"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p032ex.photo.p034a.C0710a.instantiateItem(android.view.View, int):java.lang.Object");
    }

    public boolean isViewFromObject(View view, Object obj) {
        Object view2 = ((C0424j) obj).getView();
        for (Object obj2 = view; obj2 instanceof View; obj2 = ((View) obj2).getParent()) {
            if (obj2 == view2) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: r */
    public String mo5712r(int i, int i2) {
        if (m1104zb(i2)) {
            return "android:pager:" + i + ":" + this.mCursor.getString(this.mRowIDColumn).hashCode();
        }
        return "android:switcher:" + i + ":" + i2;
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable saveState() {
        return null;
    }

    public void setPrimaryItem(View view, int i, Object obj) {
        C0424j jVar = (C0424j) obj;
        C0424j jVar2 = this.f835Mu;
        if (jVar != jVar2) {
            if (jVar2 != null) {
                jVar2.setMenuVisibility(false);
            }
            if (jVar != null) {
                jVar.setMenuVisibility(true);
            }
            this.f835Mu = jVar;
        }
    }

    public void startUpdate(View view) {
    }
}
