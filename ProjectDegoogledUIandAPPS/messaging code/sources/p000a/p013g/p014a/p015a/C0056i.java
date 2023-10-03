package p000a.p013g.p014a.p015a;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.core.content.p022a.C0308a;
import androidx.core.graphics.PathParser;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: a.g.a.a.i */
class C0056i extends C0060m {
    C0056i() {
    }

    /* renamed from: a */
    public void mo315a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
        if (C0308a.m244a(xmlPullParser, "pathData")) {
            TypedArray a = C0308a.m241a(resources, theme, attributeSet, C0048a.f27Pt);
            String string = a.getString(0);
            if (string != null) {
                this.mPathName = string;
            }
            String string2 = a.getString(1);
            if (string2 != null) {
                this.f59fu = PathParser.createNodesFromPathData(string2);
            }
            this.f60gu = C0308a.m245b(a, xmlPullParser, "fillType", 2, 0);
            a.recycle();
        }
    }

    /* renamed from: kd */
    public boolean mo316kd() {
        return true;
    }

    C0056i(C0056i iVar) {
        super(iVar);
    }
}
