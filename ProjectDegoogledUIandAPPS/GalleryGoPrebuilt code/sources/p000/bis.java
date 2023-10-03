package p000;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* renamed from: bis */
/* compiled from: PG */
public enum bis {
    BADGE("badge"),
    EDIT("edit"),
    INTERACT("interact"),
    LAUNCH("launch");
    

    /* renamed from: f */
    private static final Set f2467f = null;

    /* renamed from: g */
    private static final Set f2468g = null;

    /* renamed from: e */
    private final String f2470e;

    static {
        f2467f = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{BADGE.f2470e, EDIT.f2470e, INTERACT.f2470e})));
        HashSet hashSet = new HashSet(f2467f);
        hashSet.add(LAUNCH.f2470e);
        f2468g = Collections.unmodifiableSet(hashSet);
    }

    public final String toString() {
        return this.f2470e;
    }

    private bis(String str) {
        this.f2470e = str;
    }

    /* renamed from: a */
    public static bis m2627a(int i, String str) {
        Set set;
        String lowerCase = str.toLowerCase();
        if (i != 1) {
            set = f2468g;
        } else {
            set = f2467f;
        }
        if (set.contains(lowerCase)) {
            for (bis bis : values()) {
                if (lowerCase.equals(bis.f2470e)) {
                    return bis;
                }
            }
        }
        return null;
    }
}
