package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.core.view.ViewCompat;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import p026b.p027a.p030b.p031a.C0632a;

public class IconCompat extends CustomVersionedParcelable {
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;

    /* renamed from: Au */
    public int f315Au = 0;

    /* renamed from: Bu */
    public int f316Bu = 0;

    /* renamed from: Cu */
    public String f317Cu = null;
    public byte[] mData = null;
    public ColorStateList mTintList = null;
    PorterDuff.Mode mTintMode = DEFAULT_TINT_MODE;
    public int mType = -1;

    /* renamed from: yu */
    Object f318yu;

    /* renamed from: zu */
    public Parcelable f319zu = null;

    public IconCompat() {
    }

    /* renamed from: a */
    public static IconCompat m256a(Icon icon) {
        if (icon != null) {
            int i = Build.VERSION.SDK_INT;
            int type = icon.getType();
            if (type == 2) {
                int i2 = Build.VERSION.SDK_INT;
                String resPackage = icon.getResPackage();
                int i3 = Build.VERSION.SDK_INT;
                int resId = icon.getResId();
                if (resPackage == null) {
                    throw new IllegalArgumentException("Package must not be null.");
                } else if (resId != 0) {
                    IconCompat iconCompat = new IconCompat(2);
                    iconCompat.f315Au = resId;
                    iconCompat.f318yu = resPackage;
                    return iconCompat;
                } else {
                    throw new IllegalArgumentException("Drawable resource ID must not be 0");
                }
            } else if (type != 4) {
                IconCompat iconCompat2 = new IconCompat(-1);
                iconCompat2.f318yu = icon;
                return iconCompat2;
            } else {
                int i4 = Build.VERSION.SDK_INT;
                Uri uri = icon.getUri();
                if (uri != null) {
                    String uri2 = uri.toString();
                    if (uri2 != null) {
                        IconCompat iconCompat3 = new IconCompat(4);
                        iconCompat3.f318yu = uri2;
                        return iconCompat3;
                    }
                    throw new IllegalArgumentException("Uri must not be null.");
                }
                throw new IllegalArgumentException("Uri must not be null.");
            }
        } else {
            throw new NullPointerException();
        }
    }

    static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap bitmap, boolean z) {
        int min = (int) (((float) Math.min(bitmap.getWidth(), bitmap.getHeight())) * 0.6666667f);
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(3);
        float f = (float) min;
        float f2 = 0.5f * f;
        float f3 = 0.9166667f * f2;
        if (z) {
            float f4 = 0.010416667f * f;
            paint.setColor(0);
            paint.setShadowLayer(f4, 0.0f, f * 0.020833334f, 1023410176);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.setShadowLayer(f4, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((float) ((-(bitmap.getWidth() - min)) / 2), (float) ((-(bitmap.getHeight() - min)) / 2));
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f2, f2, f3, paint);
        canvas.setBitmap((Bitmap) null);
        return createBitmap;
    }

    /* renamed from: h */
    public static IconCompat m257h(Bundle bundle) {
        int i = bundle.getInt("type");
        IconCompat iconCompat = new IconCompat(i);
        iconCompat.f315Au = bundle.getInt("int1");
        iconCompat.f316Bu = bundle.getInt("int2");
        if (bundle.containsKey("tint_list")) {
            iconCompat.mTintList = (ColorStateList) bundle.getParcelable("tint_list");
        }
        if (bundle.containsKey("tint_mode")) {
            iconCompat.mTintMode = PorterDuff.Mode.valueOf(bundle.getString("tint_mode"));
        }
        if (!(i == -1 || i == 1)) {
            if (i != 2) {
                if (i == 3) {
                    iconCompat.f318yu = bundle.getByteArray("obj");
                    return iconCompat;
                } else if (i != 4) {
                    if (i != 5) {
                        Log.w("IconCompat", "Unknown type " + i);
                        return null;
                    }
                }
            }
            iconCompat.f318yu = bundle.getString("obj");
            return iconCompat;
        }
        iconCompat.f318yu = bundle.getParcelable("obj");
        return iconCompat;
    }

    /* renamed from: E */
    public void mo3440E(boolean z) {
        this.f317Cu = this.mTintMode.name();
        int i = this.mType;
        if (i != -1) {
            if (i != 1) {
                if (i == 2) {
                    this.mData = ((String) this.f318yu).getBytes(Charset.forName("UTF-16"));
                    return;
                } else if (i == 3) {
                    this.mData = (byte[]) this.f318yu;
                    return;
                } else if (i == 4) {
                    this.mData = this.f318yu.toString().getBytes(Charset.forName("UTF-16"));
                    return;
                } else if (i != 5) {
                    return;
                }
            }
            if (z) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ((Bitmap) this.f318yu).compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.mData = byteArrayOutputStream.toByteArray();
                return;
            }
            this.f319zu = (Parcelable) this.f318yu;
        } else if (!z) {
            this.f319zu = (Parcelable) this.f318yu;
        } else {
            throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
        }
    }

    public int getResId() {
        int i = this.mType;
        if (i == -1) {
            int i2 = Build.VERSION.SDK_INT;
            return ((Icon) this.f318yu).getResId();
        } else if (i == 2) {
            return this.f315Au;
        } else {
            throw new IllegalStateException(C0632a.m1014a("called getResId() on ", this));
        }
    }

    public String getResPackage() {
        int i = this.mType;
        if (i == -1) {
            int i2 = Build.VERSION.SDK_INT;
            return ((Icon) this.f318yu).getResPackage();
        } else if (i == 2) {
            return ((String) this.f318yu).split(":", -1)[0];
        } else {
            throw new IllegalStateException(C0632a.m1014a("called getResPackage() on ", this));
        }
    }

    public int getType() {
        int i = this.mType;
        if (i != -1) {
            return i;
        }
        int i2 = Build.VERSION.SDK_INT;
        return ((Icon) this.f318yu).getType();
    }

    /* renamed from: ld */
    public void mo3444ld() {
        this.mTintMode = PorterDuff.Mode.valueOf(this.f317Cu);
        int i = this.mType;
        if (i != -1) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        this.f318yu = this.mData;
                        return;
                    } else if (i != 4) {
                        if (i != 5) {
                            return;
                        }
                    }
                }
                this.f318yu = new String(this.mData, Charset.forName("UTF-16"));
                return;
            }
            Parcelable parcelable = this.f319zu;
            if (parcelable != null) {
                this.f318yu = parcelable;
                return;
            }
            byte[] bArr = this.mData;
            this.f318yu = bArr;
            this.mType = 3;
            this.f315Au = 0;
            this.f316Bu = bArr.length;
            return;
        }
        Parcelable parcelable2 = this.f319zu;
        if (parcelable2 != null) {
            this.f318yu = parcelable2;
            return;
        }
        throw new IllegalArgumentException("Invalid icon");
    }

    /* renamed from: md */
    public Icon mo3445md() {
        Icon icon;
        int i = this.mType;
        if (i == -1) {
            return (Icon) this.f318yu;
        }
        if (i == 1) {
            icon = Icon.createWithBitmap((Bitmap) this.f318yu);
        } else if (i == 2) {
            icon = Icon.createWithResource(getResPackage(), this.f315Au);
        } else if (i == 3) {
            icon = Icon.createWithData((byte[]) this.f318yu, this.f315Au, this.f316Bu);
        } else if (i == 4) {
            icon = Icon.createWithContentUri((String) this.f318yu);
        } else if (i == 5) {
            int i2 = Build.VERSION.SDK_INT;
            icon = Icon.createWithAdaptiveBitmap((Bitmap) this.f318yu);
        } else {
            throw new IllegalArgumentException("Unknown type");
        }
        ColorStateList colorStateList = this.mTintList;
        if (colorStateList != null) {
            icon.setTintList(colorStateList);
        }
        PorterDuff.Mode mode = this.mTintMode;
        if (mode != DEFAULT_TINT_MODE) {
            icon.setTintMode(mode);
        }
        return icon;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        int i = this.mType;
        if (i != -1) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        bundle.putByteArray("obj", (byte[]) this.f318yu);
                    } else if (i != 4) {
                        if (i != 5) {
                            throw new IllegalArgumentException("Invalid icon");
                        }
                    }
                }
                bundle.putString("obj", (String) this.f318yu);
            }
            bundle.putParcelable("obj", (Bitmap) this.f318yu);
        } else {
            bundle.putParcelable("obj", (Parcelable) this.f318yu);
        }
        bundle.putInt("type", this.mType);
        bundle.putInt("int1", this.f315Au);
        bundle.putInt("int2", this.f316Bu);
        ColorStateList colorStateList = this.mTintList;
        if (colorStateList != null) {
            bundle.putParcelable("tint_list", colorStateList);
        }
        PorterDuff.Mode mode = this.mTintMode;
        if (mode != DEFAULT_TINT_MODE) {
            bundle.putString("tint_mode", mode.name());
        }
        return bundle;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0042, code lost:
        if (r1 != 5) goto L_0x00b1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
            r7 = this;
            int r0 = r7.mType
            r1 = -1
            if (r0 != r1) goto L_0x000c
            java.lang.Object r7 = r7.f318yu
            java.lang.String r7 = java.lang.String.valueOf(r7)
            return r7
        L_0x000c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Icon(typ="
            r0.<init>(r1)
            int r1 = r7.mType
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == r6) goto L_0x0033
            if (r1 == r5) goto L_0x0030
            if (r1 == r4) goto L_0x002d
            if (r1 == r3) goto L_0x002a
            if (r1 == r2) goto L_0x0027
            java.lang.String r1 = "UNKNOWN"
            goto L_0x0035
        L_0x0027:
            java.lang.String r1 = "BITMAP_MASKABLE"
            goto L_0x0035
        L_0x002a:
            java.lang.String r1 = "URI"
            goto L_0x0035
        L_0x002d:
            java.lang.String r1 = "DATA"
            goto L_0x0035
        L_0x0030:
            java.lang.String r1 = "RESOURCE"
            goto L_0x0035
        L_0x0033:
            java.lang.String r1 = "BITMAP"
        L_0x0035:
            r0.append(r1)
            int r1 = r7.mType
            if (r1 == r6) goto L_0x0091
            if (r1 == r5) goto L_0x0069
            if (r1 == r4) goto L_0x0050
            if (r1 == r3) goto L_0x0045
            if (r1 == r2) goto L_0x0091
            goto L_0x00b1
        L_0x0045:
            java.lang.String r1 = " uri="
            r0.append(r1)
            java.lang.Object r1 = r7.f318yu
            r0.append(r1)
            goto L_0x00b1
        L_0x0050:
            java.lang.String r1 = " len="
            r0.append(r1)
            int r1 = r7.f315Au
            r0.append(r1)
            int r1 = r7.f316Bu
            if (r1 == 0) goto L_0x00b1
            java.lang.String r1 = " off="
            r0.append(r1)
            int r1 = r7.f316Bu
            r0.append(r1)
            goto L_0x00b1
        L_0x0069:
            java.lang.String r1 = " pkg="
            r0.append(r1)
            java.lang.String r1 = r7.getResPackage()
            r0.append(r1)
            java.lang.String r1 = " id="
            r0.append(r1)
            java.lang.Object[] r1 = new java.lang.Object[r6]
            r2 = 0
            int r3 = r7.getResId()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            java.lang.String r2 = "0x%08x"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            r0.append(r1)
            goto L_0x00b1
        L_0x0091:
            java.lang.String r1 = " size="
            r0.append(r1)
            java.lang.Object r1 = r7.f318yu
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getWidth()
            r0.append(r1)
            java.lang.String r1 = "x"
            r0.append(r1)
            java.lang.Object r1 = r7.f318yu
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r1 = r1.getHeight()
            r0.append(r1)
        L_0x00b1:
            android.content.res.ColorStateList r1 = r7.mTintList
            if (r1 == 0) goto L_0x00bf
            java.lang.String r1 = " tint="
            r0.append(r1)
            android.content.res.ColorStateList r1 = r7.mTintList
            r0.append(r1)
        L_0x00bf:
            android.graphics.PorterDuff$Mode r1 = r7.mTintMode
            android.graphics.PorterDuff$Mode r2 = DEFAULT_TINT_MODE
            if (r1 == r2) goto L_0x00cf
            java.lang.String r1 = " mode="
            r0.append(r1)
            android.graphics.PorterDuff$Mode r7 = r7.mTintMode
            r0.append(r7)
        L_0x00cf:
            java.lang.String r7 = ")"
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.toString():java.lang.String");
    }

    private IconCompat(int i) {
        this.mType = i;
    }
}
