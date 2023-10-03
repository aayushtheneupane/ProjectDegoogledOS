package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.google.android.apps.photosgo.R;
import java.lang.reflect.Method;

/* renamed from: wp */
/* compiled from: PG */
public class C0616wp implements C0490ry {

    /* renamed from: a */
    private static Method f16242a;

    /* renamed from: b */
    private static Method f16243b;

    /* renamed from: A */
    private Rect f16244A;

    /* renamed from: c */
    private Context f16245c;

    /* renamed from: d */
    private ListAdapter f16246d;

    /* renamed from: e */
    public C0582vi f16247e;

    /* renamed from: f */
    public int f16248f;

    /* renamed from: g */
    public int f16249g;

    /* renamed from: h */
    public boolean f16250h;

    /* renamed from: i */
    public boolean f16251i;

    /* renamed from: j */
    public int f16252j;

    /* renamed from: k */
    public int f16253k;

    /* renamed from: l */
    public View f16254l;

    /* renamed from: m */
    public AdapterView.OnItemClickListener f16255m;

    /* renamed from: n */
    public final C0615wo f16256n;

    /* renamed from: o */
    public final Handler f16257o;

    /* renamed from: p */
    public boolean f16258p;

    /* renamed from: q */
    public PopupWindow f16259q;

    /* renamed from: r */
    private int f16260r;

    /* renamed from: s */
    private int f16261s;

    /* renamed from: t */
    private int f16262t;

    /* renamed from: u */
    private boolean f16263u;

    /* renamed from: v */
    private DataSetObserver f16264v;

    /* renamed from: w */
    private final C0614wn f16265w;

    /* renamed from: x */
    private final C0613wm f16266x;

    /* renamed from: y */
    private final C0611wk f16267y;

    /* renamed from: z */
    private final Rect f16268z;

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                f16242a = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", new Class[]{Boolean.TYPE});
            } catch (NoSuchMethodException e) {
            }
            try {
                f16243b = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", new Class[]{Rect.class});
            } catch (NoSuchMethodException e2) {
            }
        }
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: ac */
    public final ListView mo9806ac() {
        return this.f16247e;
    }

    /* renamed from: c */
    public final int mo10503c() {
        if (this.f16263u) {
            return this.f16261s;
        }
        return 0;
    }

    /* renamed from: f */
    public final int mo10505f() {
        return this.f16249g;
    }

    public C0616wp(Context context) {
        this(context, (AttributeSet) null, R.attr.listPopupWindowStyle);
    }

    public C0616wp(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, (byte[]) null);
    }

    public C0616wp(Context context, AttributeSet attributeSet, int i, byte[] bArr) {
        this.f16260r = -2;
        this.f16248f = -2;
        this.f16262t = 1002;
        this.f16252j = 0;
        this.f16253k = Integer.MAX_VALUE;
        this.f16256n = new C0615wo(this);
        this.f16265w = new C0614wn(this);
        this.f16266x = new C0613wm(this);
        this.f16267y = new C0611wk(this);
        this.f16268z = new Rect();
        this.f16245c = context;
        this.f16257o = new Handler(context.getMainLooper());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0435px.f15587o, i, 0);
        this.f16249g = obtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.f16261s = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f16263u = true;
        }
        obtainStyledAttributes.recycle();
        C0535tp tpVar = new C0535tp(context, attributeSet, i);
        this.f16259q = tpVar;
        tpVar.setInputMethodMode(1);
    }

    /* renamed from: h */
    public final void mo10506h() {
        C0582vi viVar = this.f16247e;
        if (viVar != null) {
            viVar.f16091a = true;
            viVar.requestLayout();
        }
    }

    /* renamed from: a */
    public C0582vi mo10496a(Context context, boolean z) {
        return new C0582vi(context, z);
    }

    /* renamed from: d */
    public final void mo9810d() {
        this.f16259q.dismiss();
        this.f16259q.setContentView((View) null);
        this.f16247e = null;
        this.f16257o.removeCallbacks(this.f16256n);
    }

    /* renamed from: b */
    public final Drawable mo10501b() {
        return this.f16259q.getBackground();
    }

    /* renamed from: i */
    public final boolean mo10507i() {
        return this.f16259q.getInputMethodMode() == 2;
    }

    /* renamed from: e */
    public final boolean mo9811e() {
        return this.f16259q.isShowing();
    }

    /* renamed from: a */
    public void mo10184a(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.f16264v;
        if (dataSetObserver == null) {
            this.f16264v = new C0612wl(this);
        } else {
            ListAdapter listAdapter2 = this.f16246d;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f16246d = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f16264v);
        }
        C0582vi viVar = this.f16247e;
        if (viVar != null) {
            viVar.setAdapter(this.f16246d);
        }
    }

    /* renamed from: a */
    public final void mo10499a(Drawable drawable) {
        this.f16259q.setBackgroundDrawable(drawable);
    }

    /* renamed from: d */
    public final void mo10504d(int i) {
        Drawable background = this.f16259q.getBackground();
        if (background != null) {
            background.getPadding(this.f16268z);
            this.f16248f = this.f16268z.left + this.f16268z.right + i;
            return;
        }
        this.f16248f = i;
    }

    /* renamed from: a */
    public final void mo10498a(Rect rect) {
        this.f16244A = rect != null ? new Rect(rect) : null;
    }

    /* renamed from: b */
    public final void mo10502b(int i) {
        this.f16249g = i;
    }

    /* renamed from: j */
    public final void mo10508j() {
        this.f16259q.setInputMethodMode(2);
    }

    /* renamed from: k */
    public final void mo10509k() {
        this.f16258p = true;
        this.f16259q.setFocusable(true);
    }

    /* renamed from: a */
    public final void mo10500a(PopupWindow.OnDismissListener onDismissListener) {
        this.f16259q.setOnDismissListener(onDismissListener);
    }

    /* renamed from: a */
    public final void mo10497a(int i) {
        this.f16261s = i;
        this.f16263u = true;
    }

    /* renamed from: ab */
    public final void mo9805ab() {
        int i;
        boolean z;
        int i2;
        int i3;
        int i4;
        if (this.f16247e == null) {
            Context context = this.f16245c;
            new C0609wi(this);
            C0582vi a = mo10496a(context, !this.f16258p);
            this.f16247e = a;
            a.setAdapter(this.f16246d);
            this.f16247e.setOnItemClickListener(this.f16255m);
            this.f16247e.setFocusable(true);
            this.f16247e.setFocusableInTouchMode(true);
            this.f16247e.setOnItemSelectedListener(new C0610wj(this));
            this.f16247e.setOnScrollListener(this.f16266x);
            this.f16259q.setContentView(this.f16247e);
        } else {
            ViewGroup viewGroup = (ViewGroup) this.f16259q.getContentView();
        }
        Drawable background = this.f16259q.getBackground();
        int i5 = 0;
        if (background != null) {
            background.getPadding(this.f16268z);
            i = this.f16268z.top + this.f16268z.bottom;
            if (!this.f16263u) {
                this.f16261s = -this.f16268z.top;
            }
        } else {
            this.f16268z.setEmpty();
            i = 0;
        }
        if (this.f16259q.getInputMethodMode() == 2) {
            z = true;
        } else {
            z = false;
        }
        View view = this.f16254l;
        int i6 = this.f16261s;
        int i7 = Build.VERSION.SDK_INT;
        int maxAvailableHeight = this.f16259q.getMaxAvailableHeight(view, i6, z);
        if (this.f16260r != -1) {
            int i8 = this.f16248f;
            if (i8 == -2) {
                i3 = View.MeasureSpec.makeMeasureSpec(this.f16245c.getResources().getDisplayMetrics().widthPixels - (this.f16268z.left + this.f16268z.right), RecyclerView.UNDEFINED_DURATION);
            } else if (i8 != -1) {
                i3 = View.MeasureSpec.makeMeasureSpec(i8, 1073741824);
            } else {
                i3 = View.MeasureSpec.makeMeasureSpec(this.f16245c.getResources().getDisplayMetrics().widthPixels - (this.f16268z.left + this.f16268z.right), 1073741824);
            }
            int a2 = this.f16247e.mo10376a(i3, maxAvailableHeight);
            if (a2 > 0) {
                i4 = i + this.f16247e.getPaddingTop() + this.f16247e.getPaddingBottom();
            } else {
                i4 = 0;
            }
            i2 = a2 + i4;
        } else {
            i2 = maxAvailableHeight + i;
        }
        boolean i9 = mo10507i();
        dcm.m5899a(this.f16259q, this.f16262t);
        if (!this.f16259q.isShowing()) {
            int i10 = this.f16248f;
            if (i10 == -1) {
                i10 = -1;
            } else if (i10 == -2) {
                i10 = this.f16254l.getWidth();
            }
            int i11 = this.f16260r;
            if (i11 == -1) {
                i2 = -1;
            } else if (i11 != -2) {
                i2 = i11;
            }
            this.f16259q.setWidth(i10);
            this.f16259q.setHeight(i2);
            if (Build.VERSION.SDK_INT > 28) {
                this.f16259q.setIsClippedToScreen(true);
            } else {
                Method method = f16242a;
                if (method != null) {
                    try {
                        method.invoke(this.f16259q, new Object[]{true});
                    } catch (Exception e) {
                    }
                }
            }
            this.f16259q.setOutsideTouchable(true);
            this.f16259q.setTouchInterceptor(this.f16265w);
            if (this.f16251i) {
                dcm.m5900a(this.f16259q, this.f16250h);
            }
            if (Build.VERSION.SDK_INT > 28) {
                this.f16259q.setEpicenterBounds(this.f16244A);
            } else {
                Method method2 = f16243b;
                if (method2 != null) {
                    try {
                        method2.invoke(this.f16259q, new Object[]{this.f16244A});
                    } catch (Exception e2) {
                        Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                    }
                }
            }
            PopupWindow popupWindow = this.f16259q;
            View view2 = this.f16254l;
            int i12 = this.f16249g;
            int i13 = this.f16261s;
            int i14 = this.f16252j;
            int i15 = Build.VERSION.SDK_INT;
            popupWindow.showAsDropDown(view2, i12, i13, i14);
            this.f16247e.setSelection(-1);
            if (!this.f16258p || this.f16247e.isInTouchMode()) {
                mo10506h();
            }
            if (!this.f16258p) {
                this.f16257o.post(this.f16267y);
            }
        } else if (C0340mj.m14735z(this.f16254l)) {
            int i16 = this.f16248f;
            if (i16 == -1) {
                i16 = -1;
            } else if (i16 == -2) {
                i16 = this.f16254l.getWidth();
            }
            int i17 = this.f16260r;
            if (i17 == -1) {
                if (!i9) {
                    i2 = -1;
                }
                if (!i9) {
                    PopupWindow popupWindow2 = this.f16259q;
                    if (this.f16248f == -1) {
                        i5 = -1;
                    }
                    popupWindow2.setWidth(i5);
                    this.f16259q.setHeight(-1);
                } else {
                    this.f16259q.setWidth(this.f16248f == -1 ? -1 : 0);
                    this.f16259q.setHeight(0);
                }
            } else if (i17 != -2) {
                i2 = i17;
            }
            this.f16259q.setOutsideTouchable(true);
            this.f16259q.update(this.f16254l, this.f16249g, this.f16261s, i16 < 0 ? -1 : i16, i2 < 0 ? -1 : i2);
        }
    }
}
