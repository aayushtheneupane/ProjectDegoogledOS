package p000;

import android.content.Context;

/* renamed from: avl */
/* compiled from: PG */
public final class avl extends avi {
    public avl(Context context) {
        this(context, "image_manager_disk_cache", 262144000);
    }

    public avl(Context context, String str, long j) {
        super(new avk(context, str), j);
    }
}
