package p000;

import android.os.Build;
import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import com.google.android.apps.photosgo.R;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* renamed from: ll */
/* compiled from: PG */
public class C0315ll {

    /* renamed from: b */
    private static final View.AccessibilityDelegate f15203b = new View.AccessibilityDelegate();

    /* renamed from: a */
    public final View.AccessibilityDelegate f15204a;

    /* renamed from: c */
    private final View.AccessibilityDelegate f15205c;

    public C0315ll() {
        this(f15203b);
    }

    public C0315ll(View.AccessibilityDelegate accessibilityDelegate) {
        this.f15205c = accessibilityDelegate;
        this.f15204a = new C0314lk(this);
    }

    /* renamed from: b */
    public boolean mo9361b(View view, AccessibilityEvent accessibilityEvent) {
        return this.f15205c.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    /* renamed from: a */
    public C0358na mo9357a(View view) {
        int i = Build.VERSION.SDK_INT;
        AccessibilityNodeProvider accessibilityNodeProvider = this.f15205c.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new C0358na(accessibilityNodeProvider);
        }
        return null;
    }

    /* renamed from: b */
    static List m14603b(View view) {
        List list = (List) view.getTag(R.id.tag_accessibility_actions);
        return list == null ? Collections.emptyList() : list;
    }

    /* renamed from: d */
    public void mo6587d(View view, AccessibilityEvent accessibilityEvent) {
        this.f15205c.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    /* renamed from: a */
    public void mo232a(View view, C0354mx mxVar) {
        this.f15205c.onInitializeAccessibilityNodeInfo(view, mxVar.f15257a);
    }

    /* renamed from: c */
    public void mo6751c(View view, AccessibilityEvent accessibilityEvent) {
        this.f15205c.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    /* renamed from: a */
    public boolean mo9360a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f15205c.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    /* renamed from: a */
    public boolean mo233a(View view, int i, Bundle bundle) {
        boolean z;
        WeakReference weakReference;
        ClickableSpan clickableSpan;
        String str;
        List b = m14603b(view);
        int i2 = 0;
        while (true) {
            if (i2 >= b.size()) {
                break;
            }
            C0351mu muVar = (C0351mu) b.get(i2);
            if (muVar.mo9416a() != i) {
                i2++;
            } else if (muVar.f15254k != null) {
                Class cls = muVar.f15253j;
                if (cls != null) {
                    try {
                        cya cya = (cya) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    } catch (Exception e) {
                        Class cls2 = muVar.f15253j;
                        if (cls2 != null) {
                            str = cls2.getName();
                        } else {
                            str = "null";
                        }
                        Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + str, e);
                    }
                }
                z = muVar.f15254k.mo6416a(view);
            }
        }
        z = false;
        if (!z) {
            int i3 = Build.VERSION.SDK_INT;
            z = this.f15205c.performAccessibilityAction(view, i, bundle);
        }
        if (z || i != R.id.accessibility_action_clickable_span) {
            return z;
        }
        int i4 = bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1);
        SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
        if (sparseArray == null || (weakReference = (WeakReference) sparseArray.get(i4)) == null || (clickableSpan = (ClickableSpan) weakReference.get()) == null) {
            return false;
        }
        CharSequence text = view.createAccessibilityNodeInfo().getText();
        ClickableSpan[] clickableSpanArr = text instanceof Spanned ? (ClickableSpan[]) ((Spanned) text).getSpans(0, text.length(), ClickableSpan.class) : null;
        int i5 = 0;
        while (clickableSpanArr != null && i5 < clickableSpanArr.length) {
            if (!clickableSpan.equals(clickableSpanArr[i5])) {
                i5++;
            } else {
                clickableSpan.onClick(view);
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public void mo9358a(View view, int i) {
        this.f15205c.sendAccessibilityEvent(view, i);
    }

    /* renamed from: a */
    public void mo9359a(View view, AccessibilityEvent accessibilityEvent) {
        this.f15205c.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
