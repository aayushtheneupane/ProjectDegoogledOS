package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.p018a.p019a.C0130a;

public class TintTypedArray {
    private final Context mContext;
    private TypedValue mTypedValue;
    private final TypedArray mWrapped;

    private TintTypedArray(Context context, TypedArray typedArray) {
        this.mContext = context;
        this.mWrapped = typedArray;
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public boolean getBoolean(int i, boolean z) {
        return this.mWrapped.getBoolean(i, z);
    }

    public int getChangingConfigurations() {
        return this.mWrapped.getChangingConfigurations();
    }

    public int getColor(int i, int i2) {
        return this.mWrapped.getColor(i, i2);
    }

    public ColorStateList getColorStateList(int i) {
        int resourceId;
        ColorStateList colorStateList;
        if (!this.mWrapped.hasValue(i) || (resourceId = this.mWrapped.getResourceId(i, 0)) == 0 || (colorStateList = C0130a.getColorStateList(this.mContext, resourceId)) == null) {
            return this.mWrapped.getColorStateList(i);
        }
        return colorStateList;
    }

    public float getDimension(int i, float f) {
        return this.mWrapped.getDimension(i, f);
    }

    public int getDimensionPixelOffset(int i, int i2) {
        return this.mWrapped.getDimensionPixelOffset(i, i2);
    }

    public int getDimensionPixelSize(int i, int i2) {
        return this.mWrapped.getDimensionPixelSize(i, i2);
    }

    public Drawable getDrawable(int i) {
        int resourceId;
        if (!this.mWrapped.hasValue(i) || (resourceId = this.mWrapped.getResourceId(i, 0)) == 0) {
            return this.mWrapped.getDrawable(i);
        }
        return C0130a.getDrawable(this.mContext, resourceId);
    }

    public Drawable getDrawableIfKnown(int i) {
        int resourceId;
        if (!this.mWrapped.hasValue(i) || (resourceId = this.mWrapped.getResourceId(i, 0)) == 0) {
            return null;
        }
        return AppCompatDrawableManager.get().getDrawable(this.mContext, resourceId, true);
    }

    public float getFloat(int i, float f) {
        return this.mWrapped.getFloat(i, f);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Typeface getFont(int r12, int r13, androidx.core.content.p022a.C0318k r14) {
        /*
            r11 = this;
            android.content.res.TypedArray r0 = r11.mWrapped
            r1 = 0
            int r12 = r0.getResourceId(r12, r1)
            r0 = 0
            if (r12 != 0) goto L_0x000b
            return r0
        L_0x000b:
            android.util.TypedValue r1 = r11.mTypedValue
            if (r1 != 0) goto L_0x0016
            android.util.TypedValue r1 = new android.util.TypedValue
            r1.<init>()
            r11.mTypedValue = r1
        L_0x0016:
            android.content.Context r2 = r11.mContext
            android.util.TypedValue r11 = r11.mTypedValue
            boolean r1 = r2.isRestricted()
            if (r1 == 0) goto L_0x0022
            goto L_0x00e0
        L_0x0022:
            r9 = 1
            android.content.res.Resources r4 = r2.getResources()
            r1 = 1
            r4.getValue(r12, r11, r1)
            java.lang.String r1 = "ResourcesCompat"
            java.lang.CharSequence r3 = r11.string
            if (r3 == 0) goto L_0x00e1
            java.lang.String r11 = r3.toString()
            java.lang.String r3 = "res/"
            boolean r3 = r11.startsWith(r3)
            r10 = -3
            if (r3 != 0) goto L_0x0045
            if (r14 == 0) goto L_0x00bf
            r14.callbackFailAsync(r10, r0)
            goto L_0x00bf
        L_0x0045:
            android.graphics.Typeface r3 = androidx.core.graphics.TypefaceCompat.findFromCache(r4, r12, r13)
            if (r3 == 0) goto L_0x0053
            if (r14 == 0) goto L_0x0050
            r14.callbackSuccessAsync(r3, r0)
        L_0x0050:
            r0 = r3
            goto L_0x00bf
        L_0x0053:
            java.lang.String r3 = r11.toLowerCase()     // Catch:{ XmlPullParserException -> 0x00a5, IOException -> 0x008f }
            java.lang.String r5 = ".xml"
            boolean r3 = r3.endsWith(r5)     // Catch:{ XmlPullParserException -> 0x00a5, IOException -> 0x008f }
            if (r3 == 0) goto L_0x007e
            android.content.res.XmlResourceParser r3 = r4.getXml(r12)     // Catch:{ XmlPullParserException -> 0x00a5, IOException -> 0x008f }
            androidx.core.content.a.c r3 = androidx.core.content.p022a.C0308a.parse(r3, r4)     // Catch:{ XmlPullParserException -> 0x00a5, IOException -> 0x008f }
            if (r3 != 0) goto L_0x0074
            java.lang.String r13 = "Failed to find font-family tag"
            android.util.Log.e(r1, r13)     // Catch:{ XmlPullParserException -> 0x00a5, IOException -> 0x008f }
            if (r14 == 0) goto L_0x00bf
            r14.callbackFailAsync(r10, r0)     // Catch:{ XmlPullParserException -> 0x00a5, IOException -> 0x008f }
            goto L_0x00bf
        L_0x0074:
            r8 = 0
            r5 = r12
            r6 = r13
            r7 = r14
            android.graphics.Typeface r11 = androidx.core.graphics.TypefaceCompat.createFromResourcesFamilyXml(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ XmlPullParserException -> 0x00a5, IOException -> 0x008f }
            r0 = r11
            goto L_0x00bf
        L_0x007e:
            android.graphics.Typeface r13 = androidx.core.graphics.TypefaceCompat.createFromResourcesFontFile(r2, r4, r12, r11, r13)     // Catch:{ XmlPullParserException -> 0x00a5, IOException -> 0x008f }
            if (r14 == 0) goto L_0x008d
            if (r13 == 0) goto L_0x008a
            r14.callbackSuccessAsync(r13, r0)     // Catch:{ XmlPullParserException -> 0x00a5, IOException -> 0x008f }
            goto L_0x008d
        L_0x008a:
            r14.callbackFailAsync(r10, r0)     // Catch:{ XmlPullParserException -> 0x00a5, IOException -> 0x008f }
        L_0x008d:
            r0 = r13
            goto L_0x00bf
        L_0x008f:
            r13 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to read xml resource "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            android.util.Log.e(r1, r11, r13)
            goto L_0x00ba
        L_0x00a5:
            r13 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to parse xml resource "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            android.util.Log.e(r1, r11, r13)
        L_0x00ba:
            if (r14 == 0) goto L_0x00bf
            r14.callbackFailAsync(r10, r0)
        L_0x00bf:
            if (r0 != 0) goto L_0x00e0
            if (r14 == 0) goto L_0x00c4
            goto L_0x00e0
        L_0x00c4:
            android.content.res.Resources$NotFoundException r11 = new android.content.res.Resources$NotFoundException
            java.lang.String r13 = "Font resource ID #0x"
            java.lang.StringBuilder r13 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r13)
            java.lang.String r12 = java.lang.Integer.toHexString(r12)
            r13.append(r12)
            java.lang.String r12 = " could not be retrieved."
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            r11.<init>(r12)
            throw r11
        L_0x00e0:
            return r0
        L_0x00e1:
            android.content.res.Resources$NotFoundException r13 = new android.content.res.Resources$NotFoundException
            java.lang.String r14 = "Resource \""
            java.lang.StringBuilder r14 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r14)
            java.lang.String r0 = r4.getResourceName(r12)
            r14.append(r0)
            java.lang.String r0 = "\" ("
            r14.append(r0)
            java.lang.String r12 = java.lang.Integer.toHexString(r12)
            r14.append(r12)
            java.lang.String r12 = ") is not a Font: "
            r14.append(r12)
            r14.append(r11)
            java.lang.String r11 = r14.toString()
            r13.<init>(r11)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.TintTypedArray.getFont(int, int, androidx.core.content.a.k):android.graphics.Typeface");
    }

    public float getFraction(int i, int i2, int i3, float f) {
        return this.mWrapped.getFraction(i, i2, i3, f);
    }

    public int getIndex(int i) {
        return this.mWrapped.getIndex(i);
    }

    public int getIndexCount() {
        return this.mWrapped.getIndexCount();
    }

    public int getInt(int i, int i2) {
        return this.mWrapped.getInt(i, i2);
    }

    public int getInteger(int i, int i2) {
        return this.mWrapped.getInteger(i, i2);
    }

    public int getLayoutDimension(int i, String str) {
        return this.mWrapped.getLayoutDimension(i, str);
    }

    public String getNonResourceString(int i) {
        return this.mWrapped.getNonResourceString(i);
    }

    public String getPositionDescription() {
        return this.mWrapped.getPositionDescription();
    }

    public int getResourceId(int i, int i2) {
        return this.mWrapped.getResourceId(i, i2);
    }

    public Resources getResources() {
        return this.mWrapped.getResources();
    }

    public String getString(int i) {
        return this.mWrapped.getString(i);
    }

    public CharSequence getText(int i) {
        return this.mWrapped.getText(i);
    }

    public CharSequence[] getTextArray(int i) {
        return this.mWrapped.getTextArray(i);
    }

    public int getType(int i) {
        int i2 = Build.VERSION.SDK_INT;
        return this.mWrapped.getType(i);
    }

    public boolean getValue(int i, TypedValue typedValue) {
        return this.mWrapped.getValue(i, typedValue);
    }

    public boolean hasValue(int i) {
        return this.mWrapped.hasValue(i);
    }

    public int length() {
        return this.mWrapped.length();
    }

    public TypedValue peekValue(int i) {
        return this.mWrapped.peekValue(i);
    }

    public void recycle() {
        this.mWrapped.recycle();
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public int getLayoutDimension(int i, int i2) {
        return this.mWrapped.getLayoutDimension(i, i2);
    }

    public static TintTypedArray obtainStyledAttributes(Context context, int i, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(i, iArr));
    }
}
