package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* renamed from: eir */
/* compiled from: PG */
public final class eir extends ViewGroup.MarginLayoutParams implements eil {
    public static final Parcelable.Creator CREATOR = new eiq();

    /* renamed from: a */
    private int f8368a = 1;

    /* renamed from: b */
    private float f8369b = 0.0f;

    /* renamed from: c */
    private float f8370c = 1.0f;

    /* renamed from: d */
    private int f8371d = -1;

    /* renamed from: e */
    private float f8372e = -1.0f;

    /* renamed from: f */
    private int f8373f;

    /* renamed from: g */
    private int f8374g;

    /* renamed from: h */
    private int f8375h = 16777215;

    /* renamed from: i */
    private int f8376i = 16777215;

    /* renamed from: j */
    private boolean f8377j;

    /* renamed from: c */
    public final int mo4829c() {
        return this.f8368a;
    }

    /* renamed from: d */
    public final float mo4830d() {
        return this.f8369b;
    }

    public final int describeContents() {
        return 0;
    }

    /* renamed from: e */
    public final float mo4831e() {
        return this.f8370c;
    }

    /* renamed from: f */
    public final int mo4832f() {
        return this.f8371d;
    }

    /* renamed from: g */
    public final int mo4833g() {
        return this.f8373f;
    }

    /* renamed from: h */
    public final int mo4834h() {
        return this.f8374g;
    }

    /* renamed from: i */
    public final int mo4835i() {
        return this.f8375h;
    }

    /* renamed from: j */
    public final int mo4836j() {
        return this.f8376i;
    }

    /* renamed from: k */
    public final boolean mo4837k() {
        return this.f8377j;
    }

    /* renamed from: l */
    public final float mo4838l() {
        return this.f8372e;
    }

    public eir(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, eis.f8379b);
        this.f8368a = obtainStyledAttributes.getInt(8, 1);
        this.f8369b = obtainStyledAttributes.getFloat(2, 0.0f);
        this.f8370c = obtainStyledAttributes.getFloat(3, 1.0f);
        this.f8371d = obtainStyledAttributes.getInt(0, -1);
        this.f8372e = obtainStyledAttributes.getFraction(1, 1, 1, -1.0f);
        this.f8373f = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.f8374g = obtainStyledAttributes.getDimensionPixelSize(6, 0);
        this.f8375h = obtainStyledAttributes.getDimensionPixelSize(5, 16777215);
        this.f8376i = obtainStyledAttributes.getDimensionPixelSize(4, 16777215);
        this.f8377j = obtainStyledAttributes.getBoolean(9, false);
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected eir(Parcel parcel) {
        super(0, 0);
        boolean z = false;
        this.f8368a = parcel.readInt();
        this.f8369b = parcel.readFloat();
        this.f8370c = parcel.readFloat();
        this.f8371d = parcel.readInt();
        this.f8372e = parcel.readFloat();
        this.f8373f = parcel.readInt();
        this.f8374g = parcel.readInt();
        this.f8375h = parcel.readInt();
        this.f8376i = parcel.readInt();
        this.f8377j = parcel.readByte() != 0 ? true : z;
        this.bottomMargin = parcel.readInt();
        this.leftMargin = parcel.readInt();
        this.rightMargin = parcel.readInt();
        this.topMargin = parcel.readInt();
        this.height = parcel.readInt();
        this.width = parcel.readInt();
    }

    public eir(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
    }

    public eir(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
    }

    public eir(eir eir) {
        super(eir);
        this.f8368a = eir.f8368a;
        this.f8369b = eir.f8369b;
        this.f8370c = eir.f8370c;
        this.f8371d = eir.f8371d;
        this.f8372e = eir.f8372e;
        this.f8373f = eir.f8373f;
        this.f8374g = eir.f8374g;
        this.f8375h = eir.f8375h;
        this.f8376i = eir.f8376i;
        this.f8377j = eir.f8377j;
    }

    /* renamed from: b */
    public final int mo4828b() {
        return this.height;
    }

    /* renamed from: p */
    public final int mo4842p() {
        return this.bottomMargin;
    }

    /* renamed from: m */
    public final int mo4839m() {
        return this.leftMargin;
    }

    /* renamed from: o */
    public final int mo4841o() {
        return this.rightMargin;
    }

    /* renamed from: n */
    public final int mo4840n() {
        return this.topMargin;
    }

    /* renamed from: a */
    public final int mo4827a() {
        return this.width;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f8368a);
        parcel.writeFloat(this.f8369b);
        parcel.writeFloat(this.f8370c);
        parcel.writeInt(this.f8371d);
        parcel.writeFloat(this.f8372e);
        parcel.writeInt(this.f8373f);
        parcel.writeInt(this.f8374g);
        parcel.writeInt(this.f8375h);
        parcel.writeInt(this.f8376i);
        parcel.writeByte(this.f8377j ? (byte) 1 : 0);
        parcel.writeInt(this.bottomMargin);
        parcel.writeInt(this.leftMargin);
        parcel.writeInt(this.rightMargin);
        parcel.writeInt(this.topMargin);
        parcel.writeInt(this.height);
        parcel.writeInt(this.width);
    }
}
