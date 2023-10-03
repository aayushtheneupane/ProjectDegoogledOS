package androidx.core.view.p025a;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;

/* renamed from: androidx.core.view.a.e */
public class C0363e {
    private final AccessibilityNodeInfo mInfo;

    private C0363e(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.mInfo = accessibilityNodeInfo;
    }

    /* renamed from: a */
    public static C0363e m276a(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new C0363e(accessibilityNodeInfo);
    }

    /* renamed from: ab */
    private List m278ab(String str) {
        int i = Build.VERSION.SDK_INT;
        ArrayList<Integer> integerArrayList = this.mInfo.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList arrayList = new ArrayList();
        this.mInfo.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    public void addAction(int i) {
        this.mInfo.addAction(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0363e.class != obj.getClass()) {
            return false;
        }
        C0363e eVar = (C0363e) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
        if (accessibilityNodeInfo == null) {
            if (eVar.mInfo != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(eVar.mInfo)) {
            return false;
        }
        return true;
    }

    /* renamed from: g */
    public void mo3856g(Object obj) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((C0361c) obj).mInfo);
    }

    public Bundle getExtras() {
        int i = Build.VERSION.SDK_INT;
        return this.mInfo.getExtras();
    }

    /* renamed from: h */
    public void mo3858h(Object obj) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((C0362d) obj).mInfo);
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.mInfo;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public boolean performAction(int i, Bundle bundle) {
        int i2 = Build.VERSION.SDK_INT;
        return this.mInfo.performAction(i, bundle);
    }

    public void setCanOpenPopup(boolean z) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setCanOpenPopup(z);
    }

    public void setClassName(CharSequence charSequence) {
        this.mInfo.setClassName(charSequence);
    }

    public void setHeading(boolean z) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setHeading(z);
    }

    public void setPaneTitle(CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setPaneTitle(charSequence);
    }

    public void setScreenReaderFocusable(boolean z) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.setScreenReaderFocusable(z);
    }

    public void setScrollable(boolean z) {
        this.mInfo.setScrollable(z);
    }

    public String toString() {
        SpannableString spannableString;
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        this.mInfo.getBoundsInParent(rect);
        sb.append("; boundsInParent: " + rect);
        this.mInfo.getBoundsInScreen(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(this.mInfo.getPackageName());
        sb.append("; className: ");
        sb.append(this.mInfo.getClassName());
        sb.append("; text: ");
        if (!m278ab("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty()) {
            List ab = m278ab("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            List ab2 = m278ab("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            List ab3 = m278ab("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            List ab4 = m278ab("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
            SpannableString spannableString2 = new SpannableString(TextUtils.substring(this.mInfo.getText(), 0, this.mInfo.getText().length()));
            for (int i = 0; i < ab.size(); i++) {
                spannableString2.setSpan(new C0359a(((Integer) ab4.get(i)).intValue(), this, getExtras().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), ((Integer) ab.get(i)).intValue(), ((Integer) ab2.get(i)).intValue(), ((Integer) ab3.get(i)).intValue());
            }
            spannableString = spannableString2;
        } else {
            spannableString = this.mInfo.getText();
        }
        sb.append(spannableString);
        sb.append("; contentDescription: ");
        sb.append(this.mInfo.getContentDescription());
        sb.append("; viewId: ");
        int i2 = Build.VERSION.SDK_INT;
        sb.append(this.mInfo.getViewIdResourceName());
        sb.append("; checkable: ");
        sb.append(this.mInfo.isCheckable());
        sb.append("; checked: ");
        sb.append(this.mInfo.isChecked());
        sb.append("; focusable: ");
        sb.append(this.mInfo.isFocusable());
        sb.append("; focused: ");
        sb.append(this.mInfo.isFocused());
        sb.append("; selected: ");
        sb.append(this.mInfo.isSelected());
        sb.append("; clickable: ");
        sb.append(this.mInfo.isClickable());
        sb.append("; longClickable: ");
        sb.append(this.mInfo.isLongClickable());
        sb.append("; enabled: ");
        sb.append(this.mInfo.isEnabled());
        sb.append("; password: ");
        sb.append(this.mInfo.isPassword());
        sb.append("; scrollable: " + this.mInfo.isScrollable());
        sb.append("; [");
        int actions = this.mInfo.getActions();
        while (actions != 0) {
            int numberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(actions);
            actions &= ~numberOfTrailingZeros;
            if (numberOfTrailingZeros == 1) {
                str = "ACTION_FOCUS";
            } else if (numberOfTrailingZeros != 2) {
                switch (numberOfTrailingZeros) {
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
                    case NotificationCompat.FLAG_GROUP_SUMMARY:
                        str = "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
                        break;
                    case 1024:
                        str = "ACTION_NEXT_HTML_ELEMENT";
                        break;
                    case 2048:
                        str = "ACTION_PREVIOUS_HTML_ELEMENT";
                        break;
                    case NotificationCompat.FLAG_BUBBLE:
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
                    default:
                        str = "ACTION_UNKNOWN";
                        break;
                }
            } else {
                str = "ACTION_CLEAR_FOCUS";
            }
            sb.append(str);
            if (actions != 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public AccessibilityNodeInfo unwrap() {
        return this.mInfo;
    }

    /* renamed from: a */
    public void mo3852a(C0360b bVar) {
        int i = Build.VERSION.SDK_INT;
        this.mInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) bVar.mAction);
    }

    /* renamed from: a */
    public void mo3853a(CharSequence charSequence, View view) {
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: a */
    public static ClickableSpan[] m277a(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }
}
