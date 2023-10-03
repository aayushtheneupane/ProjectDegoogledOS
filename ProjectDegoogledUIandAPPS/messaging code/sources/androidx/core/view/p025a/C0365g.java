package androidx.core.view.p025a;

import android.view.View;
import android.view.accessibility.AccessibilityRecord;

/* renamed from: androidx.core.view.a.g */
public class C0365g {

    /* renamed from: go */
    private final AccessibilityRecord f328go;

    @Deprecated
    public C0365g(Object obj) {
        this.f328go = (AccessibilityRecord) obj;
    }

    @Deprecated
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0365g.class != obj.getClass()) {
            return false;
        }
        C0365g gVar = (C0365g) obj;
        AccessibilityRecord accessibilityRecord = this.f328go;
        if (accessibilityRecord == null) {
            if (gVar.f328go != null) {
                return false;
            }
        } else if (!accessibilityRecord.equals(gVar.f328go)) {
            return false;
        }
        return true;
    }

    @Deprecated
    public int hashCode() {
        AccessibilityRecord accessibilityRecord = this.f328go;
        if (accessibilityRecord == null) {
            return 0;
        }
        return accessibilityRecord.hashCode();
    }

    @Deprecated
    public void setSource(View view) {
        this.f328go.setSource(view);
    }
}
