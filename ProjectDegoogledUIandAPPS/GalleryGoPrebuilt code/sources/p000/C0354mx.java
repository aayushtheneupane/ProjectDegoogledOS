package p000;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: mx */
/* compiled from: PG */
public final class C0354mx {

    /* renamed from: a */
    public final AccessibilityNodeInfo f15257a;

    /* renamed from: b */
    public int f15258b = -1;

    /* renamed from: c */
    public int f15259c = -1;

    private C0354mx(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f15257a = accessibilityNodeInfo;
    }

    /* renamed from: a */
    public final void mo9420a(int i) {
        this.f15257a.addAction(i);
    }

    /* renamed from: a */
    public final void mo9425a(C0351mu muVar) {
        int i = Build.VERSION.SDK_INT;
        this.f15257a.addAction((AccessibilityNodeInfo.AccessibilityAction) muVar.f15251h);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof C0354mx)) {
            C0354mx mxVar = (C0354mx) obj;
            AccessibilityNodeInfo accessibilityNodeInfo = this.f15257a;
            if (accessibilityNodeInfo != null) {
                if (!accessibilityNodeInfo.equals(mxVar.f15257a)) {
                    return false;
                }
            } else if (mxVar.f15257a != null) {
                return false;
            }
            return this.f15259c == mxVar.f15259c && this.f15258b == mxVar.f15258b;
        }
        return false;
    }

    /* renamed from: a */
    private final List m14776a(String str) {
        int i = Build.VERSION.SDK_INT;
        ArrayList<Integer> integerArrayList = this.f15257a.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList arrayList = new ArrayList();
        this.f15257a.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    @Deprecated
    /* renamed from: a */
    public final void mo9422a(Rect rect) {
        this.f15257a.getBoundsInParent(rect);
    }

    /* renamed from: c */
    public final void mo9432c(Rect rect) {
        this.f15257a.getBoundsInScreen(rect);
    }

    /* renamed from: g */
    public final CharSequence mo9441g() {
        return this.f15257a.getClassName();
    }

    /* renamed from: i */
    public final CharSequence mo9444i() {
        return this.f15257a.getContentDescription();
    }

    /* renamed from: k */
    private final Bundle m14779k() {
        int i = Build.VERSION.SDK_INT;
        return this.f15257a.getExtras();
    }

    /* renamed from: h */
    public final CharSequence mo9442h() {
        if (!(!m14776a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty())) {
            return this.f15257a.getText();
        }
        List a = m14776a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List a2 = m14776a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List a3 = m14776a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List a4 = m14776a("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f15257a.getText(), 0, this.f15257a.getText().length()));
        for (int i = 0; i < a.size(); i++) {
            spannableString.setSpan(new C0349ms(((Integer) a4.get(i)).intValue(), this, m14779k().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), ((Integer) a.get(i)).intValue(), ((Integer) a2.get(i)).intValue(), ((Integer) a3.get(i)).intValue());
        }
        return spannableString;
    }

    public final int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f15257a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    /* renamed from: b */
    public final boolean mo9431b() {
        return this.f15257a.isChecked();
    }

    /* renamed from: d */
    public final boolean mo9437d() {
        return this.f15257a.isEnabled();
    }

    /* renamed from: c */
    public final boolean mo9435c() {
        return this.f15257a.isFocusable();
    }

    /* renamed from: e */
    public final boolean mo9438e() {
        return this.f15257a.isPassword();
    }

    /* renamed from: f */
    public final boolean mo9440f() {
        return this.f15257a.isScrollable();
    }

    /* renamed from: a */
    public static C0354mx m14777a() {
        return m14778a(AccessibilityNodeInfo.obtain());
    }

    /* renamed from: b */
    public final void mo9430b(boolean z) {
        int i = Build.VERSION.SDK_INT;
        this.f15257a.setAccessibilityFocused(z);
    }

    /* renamed from: a */
    public final void mo9421a(int i, boolean z) {
        Bundle k = m14779k();
        if (k != null) {
            int i2 = k.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (i ^ -1);
            if (!z) {
                i = 0;
            }
            k.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | i2);
        }
    }

    @Deprecated
    /* renamed from: b */
    public final void mo9427b(Rect rect) {
        this.f15257a.setBoundsInParent(rect);
    }

    /* renamed from: d */
    public final void mo9436d(Rect rect) {
        this.f15257a.setBoundsInScreen(rect);
    }

    /* renamed from: a */
    public final void mo9426a(boolean z) {
        this.f15257a.setCheckable(z);
    }

    /* renamed from: a */
    public final void mo9423a(CharSequence charSequence) {
        this.f15257a.setClassName(charSequence);
    }

    /* renamed from: a */
    public final void mo9424a(Object obj) {
        Object obj2;
        int i = Build.VERSION.SDK_INT;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f15257a;
        if (obj != null) {
            obj2 = ((C0352mv) obj).f15255a;
        } else {
            obj2 = null;
        }
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) obj2);
    }

    /* renamed from: b */
    public final void mo9429b(Object obj) {
        int i = Build.VERSION.SDK_INT;
        this.f15257a.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) ((C0353mw) obj).f15256a);
    }

    /* renamed from: c */
    public final void mo9434c(boolean z) {
        int i = Build.VERSION.SDK_INT;
        this.f15257a.setDismissable(z);
    }

    /* renamed from: c */
    public final void mo9433c(CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        this.f15257a.setHintText(charSequence);
    }

    /* renamed from: j */
    public final void mo9445j() {
        this.f15257a.setScrollable(true);
    }

    /* renamed from: b */
    public final void mo9428b(CharSequence charSequence) {
        this.f15257a.setText(charSequence);
    }

    public final String toString() {
        List list;
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        mo9422a(rect);
        sb.append("; boundsInParent: " + rect);
        mo9432c(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(this.f15257a.getPackageName());
        sb.append("; className: ");
        sb.append(mo9441g());
        sb.append("; text: ");
        sb.append(mo9442h());
        sb.append("; contentDescription: ");
        sb.append(mo9444i());
        sb.append("; viewId: ");
        int i = Build.VERSION.SDK_INT;
        sb.append(this.f15257a.getViewIdResourceName());
        sb.append("; checkable: ");
        sb.append(this.f15257a.isCheckable());
        sb.append("; checked: ");
        sb.append(mo9431b());
        sb.append("; focusable: ");
        sb.append(mo9435c());
        sb.append("; focused: ");
        sb.append(this.f15257a.isFocused());
        sb.append("; selected: ");
        sb.append(this.f15257a.isSelected());
        sb.append("; clickable: ");
        sb.append(this.f15257a.isClickable());
        sb.append("; longClickable: ");
        sb.append(this.f15257a.isLongClickable());
        sb.append("; enabled: ");
        sb.append(mo9437d());
        sb.append("; password: ");
        sb.append(mo9438e());
        sb.append("; scrollable: " + mo9440f());
        sb.append("; [");
        int i2 = Build.VERSION.SDK_INT;
        int i3 = Build.VERSION.SDK_INT;
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = this.f15257a.getActionList();
        if (actionList != null) {
            list = new ArrayList();
            int size = actionList.size();
            for (int i4 = 0; i4 < size; i4++) {
                list.add(new C0351mu((Object) actionList.get(i4)));
            }
        } else {
            list = Collections.emptyList();
        }
        for (int i5 = 0; i5 < list.size(); i5++) {
            C0351mu muVar = (C0351mu) list.get(i5);
            int a = muVar.mo9416a();
            if (a == 1) {
                str = "ACTION_FOCUS";
            } else if (a != 2) {
                switch (a) {
                    case 4:
                        str = "ACTION_SELECT";
                        break;
                    case 8:
                        str = "ACTION_CLEAR_SELECTION";
                        break;
                    case 16:
                        str = "ACTION_CLICK";
                        break;
                    case 32:
                        str = "ACTION_LONG_CLICK";
                        break;
                    case 64:
                        str = "ACTION_ACCESSIBILITY_FOCUS";
                        break;
                    case 128:
                        str = "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
                        break;
                    case 256:
                        str = "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
                        break;
                    case 512:
                        str = "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                        break;
                    case 1024:
                        str = "ACTION_NEXT_HTML_ELEMENT";
                        break;
                    case 2048:
                        str = "ACTION_PREVIOUS_HTML_ELEMENT";
                        break;
                    case 4096:
                        str = "ACTION_SCROLL_FORWARD";
                        break;
                    case 8192:
                        str = "ACTION_SCROLL_BACKWARD";
                        break;
                    case 16384:
                        str = "ACTION_COPY";
                        break;
                    case 32768:
                        str = "ACTION_PASTE";
                        break;
                    case 65536:
                        str = "ACTION_CUT";
                        break;
                    case 131072:
                        str = "ACTION_SET_SELECTION";
                        break;
                    case 262144:
                        str = "ACTION_EXPAND";
                        break;
                    case 524288:
                        str = "ACTION_COLLAPSE";
                        break;
                    case 2097152:
                        str = "ACTION_SET_TEXT";
                        break;
                    case 16908354:
                        str = "ACTION_MOVE_WINDOW";
                        break;
                    default:
                        switch (a) {
                            case 16908342:
                                str = "ACTION_SHOW_ON_SCREEN";
                                break;
                            case 16908343:
                                str = "ACTION_SCROLL_TO_POSITION";
                                break;
                            case 16908344:
                                str = "ACTION_SCROLL_UP";
                                break;
                            case 16908345:
                                str = "ACTION_SCROLL_LEFT";
                                break;
                            case 16908346:
                                str = "ACTION_SCROLL_DOWN";
                                break;
                            case 16908347:
                                str = "ACTION_SCROLL_RIGHT";
                                break;
                            case 16908348:
                                str = "ACTION_CONTEXT_CLICK";
                                break;
                            case 16908349:
                                str = "ACTION_SET_PROGRESS";
                                break;
                            default:
                                switch (a) {
                                    case 16908356:
                                        str = "ACTION_SHOW_TOOLTIP";
                                        break;
                                    case 16908357:
                                        str = "ACTION_HIDE_TOOLTIP";
                                        break;
                                    case 16908358:
                                        str = "ACTION_PAGE_UP";
                                        break;
                                    case 16908359:
                                        str = "ACTION_PAGE_DOWN";
                                        break;
                                    case 16908360:
                                        str = "ACTION_PAGE_LEFT";
                                        break;
                                    case 16908361:
                                        str = "ACTION_PAGE_RIGHT";
                                        break;
                                    default:
                                        str = "ACTION_UNKNOWN";
                                        break;
                                }
                        }
                }
            } else {
                str = "ACTION_CLEAR_FOCUS";
            }
            if (str.equals("ACTION_UNKNOWN") && muVar.mo9417b() != null) {
                str = muVar.mo9417b().toString();
            }
            sb.append(str);
            if (i5 != list.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* renamed from: a */
    public static C0354mx m14778a(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new C0354mx(accessibilityNodeInfo);
    }
}
