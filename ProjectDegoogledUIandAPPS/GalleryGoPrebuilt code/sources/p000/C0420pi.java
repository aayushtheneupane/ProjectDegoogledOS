package p000;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import java.lang.reflect.Constructor;
import java.util.Map;

/* renamed from: pi */
/* compiled from: PG */
public class C0420pi {
    public static final String LOG_TAG = "AppCompatViewInflater";
    public static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};
    public static final Map sConstructorMap = new C0290kn();
    public static final Class[] sConstructorSignature = {Context.class, AttributeSet.class};
    public static final int[] sOnClickAttrs = {16843375};
    public final Object[] mConstructorArgs = new Object[2];

    /* access modifiers changed from: protected */
    public View createView(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    private void checkOnClickListener(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (context instanceof ContextWrapper) {
            int i = Build.VERSION.SDK_INT;
            if (C0340mj.m14674A(view)) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sOnClickAttrs);
                String string = obtainStyledAttributes.getString(0);
                if (string != null) {
                    view.setOnClickListener(new C0419ph(view, string));
                }
                obtainStyledAttributes.recycle();
            }
        }
    }

    /* access modifiers changed from: protected */
    public C0522tc createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new C0522tc(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0524te createButton(Context context, AttributeSet attributeSet) {
        return new C0524te(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0525tf createCheckBox(Context context, AttributeSet attributeSet) {
        return new C0525tf(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0526tg createCheckedTextView(Context context, AttributeSet attributeSet) {
        return new C0526tg(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0530tk createEditText(Context context, AttributeSet attributeSet) {
        return new C0530tk(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0531tl createImageButton(Context context, AttributeSet attributeSet) {
        return new C0531tl(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0533tn createImageView(Context context, AttributeSet attributeSet) {
        return new C0533tn(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0534to createMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new C0534to(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0537tr createRadioButton(Context context, AttributeSet attributeSet) {
        return new C0537tr(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0538ts createRatingBar(Context context, AttributeSet attributeSet) {
        return new C0538ts(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0539tt createSeekBar(Context context, AttributeSet attributeSet) {
        return new C0539tt(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0553ug createSpinner(Context context, AttributeSet attributeSet) {
        return new C0553ug(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0558ul createTextView(Context context, AttributeSet attributeSet) {
        return new C0558ul(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public C0560un createToggleButton(Context context, AttributeSet attributeSet) {
        return new C0560un(context, attributeSet);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View createView(android.view.View r1, java.lang.String r2, android.content.Context r3, android.util.AttributeSet r4, boolean r5, boolean r6, boolean r7, boolean r8) {
        /*
            r0 = this;
            if (r5 != 0) goto L_0x0003
            goto L_0x000b
        L_0x0003:
            if (r1 == 0) goto L_0x000a
            android.content.Context r1 = r1.getContext()
            goto L_0x000c
        L_0x000a:
        L_0x000b:
            r1 = r3
        L_0x000c:
            if (r6 == 0) goto L_0x000f
            goto L_0x0011
        L_0x000f:
            if (r7 == 0) goto L_0x0015
        L_0x0011:
            android.content.Context r1 = themifyContext(r1, r4, r6, r7)
        L_0x0015:
            if (r8 == 0) goto L_0x001b
            android.content.Context r1 = p000.C0680yz.m16188a(r1)
        L_0x001b:
            int r5 = r2.hashCode()
            switch(r5) {
                case -1946472170: goto L_0x00b0;
                case -1455429095: goto L_0x00a5;
                case -1346021293: goto L_0x009a;
                case -938935918: goto L_0x0090;
                case -937446323: goto L_0x0086;
                case -658531749: goto L_0x007b;
                case -339785223: goto L_0x0071;
                case 776382189: goto L_0x0067;
                case 799298502: goto L_0x005c;
                case 1125864064: goto L_0x0052;
                case 1413872058: goto L_0x0046;
                case 1601505219: goto L_0x003b;
                case 1666676343: goto L_0x0030;
                case 2001146706: goto L_0x0024;
                default: goto L_0x0022;
            }
        L_0x0022:
            goto L_0x00bb
        L_0x0024:
            java.lang.String r5 = "Button"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 2
            goto L_0x00bc
        L_0x0030:
            java.lang.String r5 = "EditText"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 3
            goto L_0x00bc
        L_0x003b:
            java.lang.String r5 = "CheckBox"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 6
            goto L_0x00bc
        L_0x0046:
            java.lang.String r5 = "AutoCompleteTextView"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 9
            goto L_0x00bc
        L_0x0052:
            java.lang.String r5 = "ImageView"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 1
            goto L_0x00bc
        L_0x005c:
            java.lang.String r5 = "ToggleButton"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 13
            goto L_0x00bc
        L_0x0067:
            java.lang.String r5 = "RadioButton"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 7
            goto L_0x00bc
        L_0x0071:
            java.lang.String r5 = "Spinner"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 4
            goto L_0x00bc
        L_0x007b:
            java.lang.String r5 = "SeekBar"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 12
            goto L_0x00bc
        L_0x0086:
            java.lang.String r5 = "ImageButton"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 5
            goto L_0x00bc
        L_0x0090:
            java.lang.String r5 = "TextView"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 0
            goto L_0x00bc
        L_0x009a:
            java.lang.String r5 = "MultiAutoCompleteTextView"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 10
            goto L_0x00bc
        L_0x00a5:
            java.lang.String r5 = "CheckedTextView"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 8
            goto L_0x00bc
        L_0x00b0:
            java.lang.String r5 = "RatingBar"
            boolean r5 = r2.equals(r5)
            if (r5 == 0) goto L_0x0022
            r5 = 11
            goto L_0x00bc
        L_0x00bb:
            r5 = -1
        L_0x00bc:
            switch(r5) {
                case 0: goto L_0x012e;
                case 1: goto L_0x0126;
                case 2: goto L_0x011e;
                case 3: goto L_0x0116;
                case 4: goto L_0x010e;
                case 5: goto L_0x0106;
                case 6: goto L_0x00fe;
                case 7: goto L_0x00f6;
                case 8: goto L_0x00ee;
                case 9: goto L_0x00e6;
                case 10: goto L_0x00de;
                case 11: goto L_0x00d6;
                case 12: goto L_0x00ce;
                case 13: goto L_0x00c5;
                default: goto L_0x00bf;
            }
        L_0x00bf:
            android.view.View r5 = r0.createView(r1, r2, r4)
            goto L_0x0135
        L_0x00c5:
            un r5 = r0.createToggleButton(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x00ce:
            tt r5 = r0.createSeekBar(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x00d6:
            ts r5 = r0.createRatingBar(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x00de:
            to r5 = r0.createMultiAutoCompleteTextView(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x00e6:
            tc r5 = r0.createAutoCompleteTextView(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x00ee:
            tg r5 = r0.createCheckedTextView(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x00f6:
            tr r5 = r0.createRadioButton(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x00fe:
            tf r5 = r0.createCheckBox(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x0106:
            tl r5 = r0.createImageButton(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x010e:
            ug r5 = r0.createSpinner(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x0116:
            tk r5 = r0.createEditText(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x011e:
            te r5 = r0.createButton(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x0126:
            tn r5 = r0.createImageView(r1, r4)
            r0.verifyNotNull(r5, r2)
            goto L_0x0135
        L_0x012e:
            ul r5 = r0.createTextView(r1, r4)
            r0.verifyNotNull(r5, r2)
        L_0x0135:
            if (r5 == 0) goto L_0x0138
        L_0x0137:
            goto L_0x013e
        L_0x0138:
            if (r3 == r1) goto L_0x0137
            android.view.View r5 = r0.createViewFromTag(r1, r2, r4)
        L_0x013e:
            if (r5 == 0) goto L_0x0143
            r0.checkOnClickListener(r5, r4)
        L_0x0143:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0420pi.createView(android.view.View, java.lang.String, android.content.Context, android.util.AttributeSet, boolean, boolean, boolean, boolean):android.view.View");
    }

    private View createViewByPrefix(Context context, String str, String str2) {
        String str3;
        Constructor<? extends U> constructor = (Constructor) sConstructorMap.get(str);
        if (constructor == null) {
            if (str2 != null) {
                try {
                    str3 = str2 + str;
                } catch (Exception e) {
                    return null;
                }
            } else {
                str3 = str;
            }
            constructor = Class.forName(str3, false, context.getClassLoader()).asSubclass(View.class).getConstructor(sConstructorSignature);
            sConstructorMap.put(str, constructor);
        }
        constructor.setAccessible(true);
        return (View) constructor.newInstance(this.mConstructorArgs);
    }

    private View createViewFromTag(Context context, String str, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue((String) null, "class");
        }
        try {
            Object[] objArr = this.mConstructorArgs;
            objArr[0] = context;
            objArr[1] = attributeSet;
            if (str.indexOf(46) != -1) {
                return createViewByPrefix(context, str, (String) null);
            }
            for (String createViewByPrefix : sClassPrefixList) {
                View createViewByPrefix2 = createViewByPrefix(context, str, createViewByPrefix);
                if (createViewByPrefix2 != null) {
                    Object[] objArr2 = this.mConstructorArgs;
                    objArr2[0] = null;
                    objArr2[1] = null;
                    return createViewByPrefix2;
                }
            }
            Object[] objArr3 = this.mConstructorArgs;
            objArr3[0] = null;
            objArr3[1] = null;
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            Object[] objArr4 = this.mConstructorArgs;
            objArr4[0] = null;
            objArr4[1] = null;
        }
    }

    private static Context themifyContext(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0435px.f15597y, 0, 0);
        int resourceId = z ? obtainStyledAttributes.getResourceId(0, 0) : 0;
        if (z2 && resourceId == 0) {
            resourceId = obtainStyledAttributes.getResourceId(4, 0);
        }
        obtainStyledAttributes.recycle();
        return (resourceId == 0 || ((context instanceof C0445qg) && ((C0445qg) context).f15605a == resourceId)) ? context : new C0445qg(context, resourceId);
    }

    private void verifyNotNull(View view, String str) {
        if (view == null) {
            throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
        }
    }
}
