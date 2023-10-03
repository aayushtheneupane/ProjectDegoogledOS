package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/* renamed from: fv */
/* compiled from: PG */
final class C0161fv implements LayoutInflater.Factory2 {

    /* renamed from: a */
    private final C0171gd f10613a;

    public C0161fv(C0171gd gdVar) {
        this.f10613a = gdVar;
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (C0157fr.class.getName().equals(str)) {
            return new C0157fr(context, attributeSet, this.f10613a);
        }
        C0147fh fhVar = null;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue((String) null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0122ej.f8393a);
        int i = 0;
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (attributeValue == null || !C0159ft.m9579a(context.getClassLoader(), attributeValue)) {
            return null;
        }
        if (view != null) {
            i = view.getId();
        }
        if (i == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
        }
        if (resourceId != -1) {
            fhVar = this.f10613a.mo6432b(resourceId);
        }
        if (fhVar == null && string != null) {
            fhVar = this.f10613a.mo6418a(string);
        }
        if (fhVar == null && i != -1) {
            fhVar = this.f10613a.mo6432b(i);
        }
        if (C0171gd.m10054a(2)) {
            "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + attributeValue + " existing=" + fhVar;
        }
        if (fhVar == null) {
            fhVar = this.f10613a.mo6455m().mo6175c(context.getClassLoader(), attributeValue);
            fhVar.f9599r = true;
            fhVar.f9562A = resourceId != 0 ? resourceId : i;
            fhVar.f9563B = i;
            fhVar.f9564C = string;
            fhVar.f9600s = true;
            fhVar.f9604w = this.f10613a;
            fhVar.f9605x = this.f10613a.f10991j;
            Bundle bundle = fhVar.f9588g;
            fhVar.mo5628I();
            this.f10613a.mo6447e(fhVar);
            this.f10613a.mo6441c(fhVar);
        } else if (!fhVar.f9600s) {
            fhVar.f9600s = true;
            fhVar.f9605x = this.f10613a.f10991j;
            Bundle bundle2 = fhVar.f9588g;
            fhVar.mo5628I();
        } else {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(i) + " with another fragment for " + attributeValue);
        }
        C0171gd gdVar = this.f10613a;
        if (gdVar.f10990i > 0 || !fhVar.f9599r) {
            gdVar.mo6441c(fhVar);
        } else {
            gdVar.mo6423a(fhVar, 1);
        }
        View view2 = fhVar.f9573L;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fhVar.f9573L.getTag() == null) {
                fhVar.f9573L.setTag(string);
            }
            return fhVar.f9573L;
        }
        throw new IllegalStateException("Fragment " + attributeValue + " did not create a view.");
    }

    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView((View) null, str, context, attributeSet);
    }
}
