package p000;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.p001v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;

/* renamed from: oh */
/* compiled from: PG */
public final class C0392oh {

    /* renamed from: A */
    public ListAdapter f15374A;

    /* renamed from: B */
    public int f15375B = -1;

    /* renamed from: C */
    public final int f15376C;

    /* renamed from: D */
    public final int f15377D;

    /* renamed from: E */
    public final int f15378E;

    /* renamed from: F */
    public final int f15379F;

    /* renamed from: G */
    public final int f15380G;

    /* renamed from: H */
    public final int f15381H;

    /* renamed from: I */
    public final boolean f15382I;

    /* renamed from: J */
    public final Handler f15383J;

    /* renamed from: K */
    public final View.OnClickListener f15384K = new C0385oa(this);

    /* renamed from: a */
    public final Context f15385a;

    /* renamed from: b */
    public final C0418pg f15386b;

    /* renamed from: c */
    public final Window f15387c;

    /* renamed from: d */
    public final int f15388d;

    /* renamed from: e */
    public CharSequence f15389e;

    /* renamed from: f */
    public CharSequence f15390f;

    /* renamed from: g */
    public ListView f15391g;

    /* renamed from: h */
    public View f15392h;

    /* renamed from: i */
    public int f15393i;

    /* renamed from: j */
    public boolean f15394j = false;

    /* renamed from: k */
    public Button f15395k;

    /* renamed from: l */
    public CharSequence f15396l;

    /* renamed from: m */
    public Message f15397m;

    /* renamed from: n */
    public Drawable f15398n;

    /* renamed from: o */
    public Button f15399o;

    /* renamed from: p */
    public CharSequence f15400p;

    /* renamed from: q */
    public Message f15401q;

    /* renamed from: r */
    public Drawable f15402r;

    /* renamed from: s */
    public Button f15403s;

    /* renamed from: t */
    public NestedScrollView f15404t;

    /* renamed from: u */
    public int f15405u = 0;

    /* renamed from: v */
    public Drawable f15406v;

    /* renamed from: w */
    public ImageView f15407w;

    /* renamed from: x */
    public TextView f15408x;

    /* renamed from: y */
    public TextView f15409y;

    /* renamed from: z */
    public View f15410z;

    public C0392oh(Context context, C0418pg pgVar, Window window) {
        this.f15385a = context;
        this.f15386b = pgVar;
        this.f15387c = window;
        this.f15383J = new C0390of(pgVar);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, C0435px.f15577e, R.attr.alertDialogStyle, 0);
        this.f15376C = obtainStyledAttributes.getResourceId(0, 0);
        this.f15377D = obtainStyledAttributes.getResourceId(2, 0);
        this.f15378E = obtainStyledAttributes.getResourceId(4, 0);
        this.f15379F = obtainStyledAttributes.getResourceId(5, 0);
        this.f15380G = obtainStyledAttributes.getResourceId(7, 0);
        this.f15381H = obtainStyledAttributes.getResourceId(3, 0);
        this.f15382I = obtainStyledAttributes.getBoolean(6, true);
        this.f15388d = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        pgVar.mo9617a();
    }

    /* renamed from: a */
    static boolean m14893a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            while (childCount > 0) {
                childCount--;
                if (m14893a(viewGroup.getChildAt(childCount))) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public static final void m14892a(Button button) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        button.setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    public static final ViewGroup m14891a(View view, View view2) {
        if (view != null) {
            if (view2 != null) {
                ViewParent parent = view2.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(view2);
                }
            }
            if (view instanceof ViewStub) {
                view = ((ViewStub) view).inflate();
            }
            return (ViewGroup) view;
        }
        if (view2 instanceof ViewStub) {
            view2 = ((ViewStub) view2).inflate();
        }
        return (ViewGroup) view2;
    }

    /* renamed from: a */
    public final void mo9515a(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Drawable drawable) {
        Message message;
        if (onClickListener != null) {
            message = this.f15383J.obtainMessage(i, onClickListener);
        } else {
            message = null;
        }
        if (i != -2) {
            this.f15396l = charSequence;
            this.f15397m = message;
            this.f15398n = drawable;
            return;
        }
        this.f15400p = charSequence;
        this.f15401q = message;
        this.f15402r = drawable;
    }

    /* renamed from: a */
    public final void mo9516a(Drawable drawable) {
        this.f15406v = drawable;
        this.f15405u = 0;
        ImageView imageView = this.f15407w;
        if (imageView == null) {
            return;
        }
        if (drawable != null) {
            imageView.setVisibility(0);
            this.f15407w.setImageDrawable(drawable);
            return;
        }
        imageView.setVisibility(8);
    }

    /* renamed from: a */
    public final void mo9517a(CharSequence charSequence) {
        this.f15389e = charSequence;
        TextView textView = this.f15408x;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
