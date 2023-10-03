package p000;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;

/* renamed from: cpf */
/* compiled from: PG */
public final class cpf extends azl {

    /* renamed from: b */
    private static final Paint f5351b = new Paint(6);

    /* renamed from: c */
    private final Rect f5352c;

    /* renamed from: d */
    private final Rect f5353d;

    public cpf(Rect rect, Rect rect2) {
        this.f5352c = rect;
        this.f5353d = rect2;
    }

    /* renamed from: a */
    private static void m5211a(ByteBuffer byteBuffer, Rect rect) {
        byteBuffer.putInt(rect.top);
        byteBuffer.putInt(rect.left);
        byteBuffer.putInt(rect.right);
        byteBuffer.putInt(rect.bottom);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof cpf) {
            cpf cpf = (cpf) obj;
            if (!ife.m12891c((Object) this.f5352c, (Object) cpf.f5352c) || !ife.m12891c((Object) this.f5353d, (Object) cpf.f5353d)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{getClass().getName(), this.f5352c, this.f5353d});
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Bitmap mo1740a(auk auk, Bitmap bitmap, int i, int i2) {
        Rect rect = this.f5353d;
        Object[] objArr = {Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), this.f5352c, rect};
        Bitmap a = auk.mo1642a(rect.width(), this.f5353d.height(), bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888);
        bax.m2070a(bitmap, a);
        Canvas canvas = new Canvas(a);
        canvas.drawBitmap(bitmap, this.f5352c, this.f5353d, f5351b);
        canvas.setBitmap((Bitmap) null);
        return a;
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        ByteBuffer allocate = ByteBuffer.allocate(36);
        m5211a(allocate, this.f5352c);
        m5211a(allocate, this.f5353d);
        messageDigest.update(getClass().getName().getBytes(f1466a));
        messageDigest.update(allocate.array());
    }
}
