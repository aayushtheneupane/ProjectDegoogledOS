package androidx.core.content.p022a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: androidx.core.content.a.b */
public final class C0309b {
    private int mColor;
    private final ColorStateList mColorStateList;
    private final Shader mShader;

    private C0309b(Shader shader, ColorStateList colorStateList, int i) {
        this.mShader = shader;
        this.mColorStateList = colorStateList;
        this.mColor = i;
    }

    /* renamed from: N */
    static C0309b m247N(int i) {
        return new C0309b((Shader) null, (ColorStateList) null, i);
    }

    /* renamed from: b */
    public static C0309b m248b(Resources resources, int i, Resources.Theme theme) {
        try {
            return m249c(resources, i, theme);
        } catch (Exception e) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e);
            return null;
        }
    }

    /* renamed from: c */
    private static C0309b m249c(Resources resources, int i, Resources.Theme theme) {
        int next;
        XmlResourceParser xml = resources.getXml(i);
        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            String name = xml.getName();
            char c = 65535;
            int hashCode = name.hashCode();
            if (hashCode != 89650992) {
                if (hashCode == 1191572447 && name.equals("selector")) {
                    c = 0;
                }
            } else if (name.equals("gradient")) {
                c = 1;
            }
            if (c == 0) {
                ColorStateList createFromXmlInner = C0308a.createFromXmlInner(resources, (XmlPullParser) xml, asAttributeSet, theme);
                return new C0309b((Shader) null, createFromXmlInner, createFromXmlInner.getDefaultColor());
            } else if (c == 1) {
                return new C0309b(C0308a.createFromXmlInner(resources, (XmlPullParser) xml, asAttributeSet, theme), (ColorStateList) null, 0);
            } else {
                throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
            }
        } else {
            throw new XmlPullParserException("No start tag found");
        }
    }

    /* renamed from: a */
    public boolean mo3388a(int[] iArr) {
        if (isStateful()) {
            ColorStateList colorStateList = this.mColorStateList;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.mColor) {
                this.mColor = colorForState;
                return true;
            }
        }
        return false;
    }

    public int getColor() {
        return this.mColor;
    }

    public Shader getShader() {
        return this.mShader;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r1.mColorStateList;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isStateful() {
        /*
            r1 = this;
            android.graphics.Shader r0 = r1.mShader
            if (r0 != 0) goto L_0x0010
            android.content.res.ColorStateList r1 = r1.mColorStateList
            if (r1 == 0) goto L_0x0010
            boolean r1 = r1.isStateful()
            if (r1 == 0) goto L_0x0010
            r1 = 1
            goto L_0x0011
        L_0x0010:
            r1 = 0
        L_0x0011:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.content.p022a.C0309b.isStateful():boolean");
    }

    /* renamed from: rc */
    public boolean mo3392rc() {
        return this.mShader != null;
    }

    /* renamed from: sc */
    public boolean mo3393sc() {
        return mo3392rc() || this.mColor != 0;
    }

    public void setColor(int i) {
        this.mColor = i;
    }
}
