package p000;

import com.google.android.apps.photosgo.environment.BuildType;

/* renamed from: cjq */
/* compiled from: PG */
public final class cjq {

    /* renamed from: a */
    public final BuildType f4509a;

    /* renamed from: b */
    public BuildType[] f4510b = new BuildType[0];

    /* renamed from: c */
    public BuildType[] f4511c = new BuildType[0];

    /* renamed from: d */
    public boolean f4512d = false;

    public cjq(BuildType buildType) {
        this.f4509a = buildType;
    }

    /* renamed from: a */
    public final cjr mo3174a() {
        BuildType[] buildTypeArr = this.f4511c;
        int length = buildTypeArr.length;
        int i = 0;
        while (i < length) {
            BuildType buildType = buildTypeArr[i];
            if (m4405a(this.f4510b, buildType)) {
                i++;
            } else {
                String valueOf = String.valueOf(buildType);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
                sb.append(valueOf);
                sb.append(" forced, but not enabled");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return new cjr(this);
    }

    /* renamed from: a */
    public static boolean m4405a(BuildType[] buildTypeArr, BuildType buildType) {
        for (BuildType equals : buildTypeArr) {
            if (equals.equals(buildType)) {
                return true;
            }
        }
        return false;
    }
}
