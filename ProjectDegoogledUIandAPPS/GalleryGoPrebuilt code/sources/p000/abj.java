package p000;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.content.Context;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import java.util.ArrayList;
import java.util.List;

/* renamed from: abj */
/* compiled from: PG */
public class abj {
    public abj() {
    }

    /* renamed from: a */
    public static /* synthetic */ String m87a(int i) {
        switch (i) {
            case 1:
                return "INITIALIZE";
            case RecyclerView.SCROLL_STATE_SETTLING /*2*/:
                return "RESOURCE_CACHE";
            case 3:
                return "DATA_CACHE";
            case 4:
                return "SOURCE";
            case 5:
                return "ENCODE";
            case 6:
                return "FINISHED";
            default:
                return "null";
        }
    }

    /* renamed from: a */
    public static boolean m109a(int i, int i2) {
        return i != Integer.MIN_VALUE && i2 != Integer.MIN_VALUE && i <= 512 && i2 <= 384;
    }

    /* renamed from: b */
    public static int m111b(int i) {
        return (char) i;
    }

    /* renamed from: a */
    public void mo81a() {
    }

    /* renamed from: a */
    public void mo82a(abm abm) {
    }

    /* renamed from: a */
    public void mo83a(View view, Parcelable parcelable) {
    }

    /* renamed from: a */
    public void mo85a(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
    }

    /* renamed from: a */
    public void mo86a(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int[] iArr, int i2) {
    }

    /* renamed from: a */
    public boolean mo87a(View view) {
        return false;
    }

    /* renamed from: a */
    public boolean mo88a(CoordinatorLayout coordinatorLayout, View view, int i) {
        throw null;
    }

    /* renamed from: a */
    public boolean mo89a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        return false;
    }

    /* renamed from: a */
    public boolean mo90a(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
        return false;
    }

    /* renamed from: a */
    public boolean mo91a(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return false;
    }

    /* renamed from: a */
    public boolean mo92a(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return false;
    }

    /* renamed from: a */
    public boolean mo93a(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2) {
        return false;
    }

    /* renamed from: b */
    public boolean mo94b(View view) {
        return false;
    }

    /* renamed from: b */
    public boolean mo95b(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return false;
    }

    /* renamed from: d */
    public void mo97d(View view) {
    }

    public abj(Context context, AttributeSet attributeSet) {
    }

    /* renamed from: a */
    public void mo84a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
        iArr[0] = iArr[0] + i2;
        iArr[1] = iArr[1] + i3;
    }

    /* renamed from: c */
    public Parcelable mo96c(View view) {
        return View.BaseSavedState.EMPTY_STATE;
    }

    /* renamed from: a */
    public static ObjectAnimator m83a(Object obj, Property property, Path path) {
        int i = Build.VERSION.SDK_INT;
        return ObjectAnimator.ofObject(obj, property, (TypeConverter) null, path);
    }

    /* renamed from: a */
    public static boolean m110a(Uri uri) {
        return uri != null && "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    /* renamed from: b */
    public static boolean m118b(Uri uri) {
        return uri.getPathSegments().contains("video");
    }

    /* renamed from: b */
    public static void m116b(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: b */
    public static void m117b(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    /* renamed from: a */
    public static void m90a(Handler handler) {
        String str;
        if (Looper.myLooper() == null) {
            str = "null current looper";
        } else {
            str = Looper.myLooper().getThread().getName();
        }
        String name = handler.getLooper().getThread().getName();
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 36 + String.valueOf(str).length());
        sb.append("Must be called on ");
        sb.append(name);
        sb.append(" thread, but got ");
        sb.append(str);
        sb.append(".");
        m91a(handler, sb.toString());
    }

    /* renamed from: a */
    public static void m91a(Handler handler, String str) {
        if (Looper.myLooper() != handler.getLooper()) {
            throw new IllegalStateException(str);
        }
    }

    /* renamed from: a */
    public static String m88a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException("Given String is empty or null");
    }

    /* renamed from: a */
    public static Object m85a(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("null reference");
    }

    /* renamed from: a */
    public static Object m86a(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException((String) obj2);
    }

    /* renamed from: a */
    public static void m107a(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    public static void m108a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException((String) obj);
        }
    }

    /* renamed from: i */
    public static Bundle m129i(Parcel parcel, int i) {
        int b = m113b(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (b == 0) {
            return null;
        }
        Bundle readBundle = parcel.readBundle();
        parcel.setDataPosition(dataPosition + b);
        return readBundle;
    }

    /* renamed from: j */
    public static byte[] m130j(Parcel parcel, int i) {
        int b = m113b(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (b == 0) {
            return null;
        }
        byte[] createByteArray = parcel.createByteArray();
        parcel.setDataPosition(dataPosition + b);
        return createByteArray;
    }

    /* renamed from: k */
    public static byte[][] m131k(Parcel parcel, int i) {
        int b = m113b(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (b == 0) {
            return null;
        }
        int readInt = parcel.readInt();
        byte[][] bArr = new byte[readInt][];
        for (int i2 = 0; i2 < readInt; i2++) {
            bArr[i2] = parcel.createByteArray();
        }
        parcel.setDataPosition(dataPosition + b);
        return bArr;
    }

    /* renamed from: l */
    public static int[] m132l(Parcel parcel, int i) {
        int b = m113b(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (b == 0) {
            return null;
        }
        int[] createIntArray = parcel.createIntArray();
        parcel.setDataPosition(dataPosition + b);
        return createIntArray;
    }

    /* renamed from: a */
    public static Parcelable m84a(Parcel parcel, int i, Parcelable.Creator creator) {
        int b = m113b(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (b == 0) {
            return null;
        }
        Parcelable parcelable = (Parcelable) creator.createFromParcel(parcel);
        parcel.setDataPosition(dataPosition + b);
        return parcelable;
    }

    /* renamed from: g */
    public static String m127g(Parcel parcel, int i) {
        int b = m113b(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (b == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(dataPosition + b);
        return readString;
    }

    /* renamed from: m */
    public static String[] m133m(Parcel parcel, int i) {
        int b = m113b(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (b == 0) {
            return null;
        }
        String[] createStringArray = parcel.createStringArray();
        parcel.setDataPosition(dataPosition + b);
        return createStringArray;
    }

    /* renamed from: n */
    public static ArrayList m134n(Parcel parcel, int i) {
        int b = m113b(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (b == 0) {
            return null;
        }
        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
        parcel.setDataPosition(dataPosition + b);
        return createStringArrayList;
    }

    /* renamed from: b */
    public static Object[] m119b(Parcel parcel, int i, Parcelable.Creator creator) {
        int b = m113b(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (b == 0) {
            return null;
        }
        Object[] createTypedArray = parcel.createTypedArray(creator);
        parcel.setDataPosition(dataPosition + b);
        return createTypedArray;
    }

    /* renamed from: c */
    public static ArrayList m121c(Parcel parcel, int i, Parcelable.Creator creator) {
        int b = m113b(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (b == 0) {
            return null;
        }
        ArrayList createTypedArrayList = parcel.createTypedArrayList(creator);
        parcel.setDataPosition(dataPosition + b);
        return createTypedArrayList;
    }

    /* renamed from: o */
    public static void m135o(Parcel parcel, int i) {
        if (parcel.dataPosition() != i) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Overread allowed size end=");
            sb.append(i);
            throw new eqw(sb.toString(), parcel);
        }
    }

    /* renamed from: c */
    public static void m123c(Parcel parcel, int i, int i2) {
        int b = m113b(parcel, i);
        if (b != i2) {
            String hexString = Integer.toHexString(b);
            StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 46);
            sb.append("Expected size ");
            sb.append(i2);
            sb.append(" got ");
            sb.append(b);
            sb.append(" (0x");
            sb.append(hexString);
            sb.append(")");
            throw new eqw(sb.toString(), parcel);
        }
    }

    /* renamed from: d */
    public static boolean m124d(Parcel parcel, int i) {
        m123c(parcel, i, 4);
        return parcel.readInt() != 0;
    }

    /* renamed from: b */
    public static int m112b(Parcel parcel) {
        return parcel.readInt();
    }

    /* renamed from: h */
    public static IBinder m128h(Parcel parcel, int i) {
        int b = m113b(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (b == 0) {
            return null;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        parcel.setDataPosition(dataPosition + b);
        return readStrongBinder;
    }

    /* renamed from: e */
    public static int m125e(Parcel parcel, int i) {
        m123c(parcel, i, 4);
        return parcel.readInt();
    }

    /* renamed from: f */
    public static long m126f(Parcel parcel, int i) {
        m123c(parcel, i, 8);
        return parcel.readLong();
    }

    /* renamed from: b */
    public static int m113b(Parcel parcel, int i) {
        return (i & -65536) != -65536 ? i >>> 16 : parcel.readInt();
    }

    /* renamed from: c */
    public static void m122c(Parcel parcel, int i) {
        parcel.setDataPosition(parcel.dataPosition() + m113b(parcel, i));
    }

    /* renamed from: c */
    public static int m120c(Parcel parcel) {
        int readInt = parcel.readInt();
        int b = m113b(parcel, readInt);
        int dataPosition = parcel.dataPosition();
        if (m111b(readInt) != 20293) {
            String valueOf = String.valueOf(Integer.toHexString(readInt));
            throw new eqw(valueOf.length() == 0 ? new String("Expected object header. Got 0x") : "Expected object header. Got 0x".concat(valueOf), parcel);
        }
        int i = b + dataPosition;
        if (i >= dataPosition && i <= parcel.dataSize()) {
            return i;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("Size read is invalid start=");
        sb.append(dataPosition);
        sb.append(" end=");
        sb.append(i);
        throw new eqw(sb.toString(), parcel);
    }

    /* renamed from: a */
    public static int m82a(Parcel parcel) {
        return m136p(parcel, 20293);
    }

    /* renamed from: p */
    private static int m136p(Parcel parcel, int i) {
        parcel.writeInt(i | -65536);
        parcel.writeInt(0);
        return parcel.dataPosition();
    }

    /* renamed from: a */
    public static void m92a(Parcel parcel, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.setDataPosition(i - 4);
        parcel.writeInt(dataPosition - i);
        parcel.setDataPosition(dataPosition);
    }

    /* renamed from: a */
    public static void m100a(Parcel parcel, int i, boolean z) {
        m93a(parcel, i, 4);
        parcel.writeInt(z ? 1 : 0);
    }

    /* renamed from: a */
    public static void m95a(Parcel parcel, int i, Bundle bundle) {
        if (bundle != null) {
            int p = m136p(parcel, i);
            parcel.writeBundle(bundle);
            m92a(parcel, p);
        }
    }

    /* renamed from: a */
    public static void m101a(Parcel parcel, int i, byte[] bArr) {
        if (bArr != null) {
            int p = m136p(parcel, i);
            parcel.writeByteArray(bArr);
            m92a(parcel, p);
        }
    }

    /* renamed from: a */
    public static void m105a(Parcel parcel, int i, byte[][] bArr) {
        if (bArr != null) {
            int p = m136p(parcel, i);
            parcel.writeInt(r0);
            for (byte[] writeByteArray : bArr) {
                parcel.writeByteArray(writeByteArray);
            }
            m92a(parcel, p);
        }
    }

    /* renamed from: a */
    public static void m93a(Parcel parcel, int i, int i2) {
        parcel.writeInt(i | (i2 << 16));
    }

    /* renamed from: a */
    public static void m96a(Parcel parcel, int i, IBinder iBinder) {
        if (iBinder != null) {
            int p = m136p(parcel, i);
            parcel.writeStrongBinder(iBinder);
            m92a(parcel, p);
        }
    }

    /* renamed from: b */
    public static void m114b(Parcel parcel, int i, int i2) {
        m93a(parcel, i, 4);
        parcel.writeInt(i2);
    }

    /* renamed from: a */
    public static void m102a(Parcel parcel, int i, int[] iArr) {
        if (iArr != null) {
            int p = m136p(parcel, i);
            parcel.writeIntArray(iArr);
            m92a(parcel, p);
        }
    }

    /* renamed from: a */
    public static void m94a(Parcel parcel, int i, long j) {
        m93a(parcel, i, 8);
        parcel.writeLong(j);
    }

    /* renamed from: a */
    public static void m97a(Parcel parcel, int i, Parcelable parcelable, int i2) {
        if (parcelable != null) {
            int p = m136p(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            m92a(parcel, p);
        }
    }

    /* renamed from: a */
    public static void m98a(Parcel parcel, int i, String str) {
        if (str != null) {
            int p = m136p(parcel, i);
            parcel.writeString(str);
            m92a(parcel, p);
        }
    }

    /* renamed from: a */
    public static void m104a(Parcel parcel, int i, String[] strArr) {
        if (strArr != null) {
            int p = m136p(parcel, i);
            parcel.writeStringArray(strArr);
            m92a(parcel, p);
        }
    }

    /* renamed from: a */
    public static void m99a(Parcel parcel, int i, List list) {
        if (list != null) {
            int p = m136p(parcel, i);
            parcel.writeStringList(list);
            m92a(parcel, p);
        }
    }

    /* renamed from: a */
    public static void m103a(Parcel parcel, int i, Parcelable[] parcelableArr, int i2) {
        if (parcelableArr != null) {
            int p = m136p(parcel, i);
            parcel.writeInt(r0);
            for (Parcelable parcelable : parcelableArr) {
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    m106a(parcel, parcelable, i2);
                }
            }
            m92a(parcel, p);
        }
    }

    /* renamed from: a */
    private static void m106a(Parcel parcel, Parcelable parcelable, int i) {
        int dataPosition = parcel.dataPosition();
        parcel.writeInt(1);
        int dataPosition2 = parcel.dataPosition();
        parcelable.writeToParcel(parcel, i);
        int dataPosition3 = parcel.dataPosition();
        parcel.setDataPosition(dataPosition);
        parcel.writeInt(dataPosition3 - dataPosition2);
        parcel.setDataPosition(dataPosition3);
    }

    /* renamed from: b */
    public static void m115b(Parcel parcel, int i, List list) {
        if (list != null) {
            int p = m136p(parcel, i);
            int size = list.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                Parcelable parcelable = (Parcelable) list.get(i2);
                if (parcelable == null) {
                    parcel.writeInt(0);
                } else {
                    m106a(parcel, parcelable, 0);
                }
            }
            m92a(parcel, p);
        }
    }

    /* renamed from: a */
    public static void m89a(Context context, Throwable th) {
        try {
            m85a((Object) context);
            m85a((Object) th);
        } catch (Exception e) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", e);
        }
    }
}
