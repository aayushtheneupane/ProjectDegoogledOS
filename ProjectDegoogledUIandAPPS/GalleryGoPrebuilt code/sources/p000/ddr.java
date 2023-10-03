package p000;

import java.util.List;

/* renamed from: ddr */
/* compiled from: PG */
public final class ddr {

    /* renamed from: a */
    public final String f6373a;

    /* renamed from: b */
    public final String f6374b;

    /* renamed from: c */
    public final Boolean f6375c;

    /* renamed from: d */
    public final Boolean f6376d;

    /* renamed from: e */
    public final Integer f6377e;

    /* renamed from: f */
    public final Long f6378f;

    /* renamed from: g */
    public final List f6379g;

    /* renamed from: h */
    private final Integer f6380h;

    /* renamed from: i */
    private final Integer f6381i;

    public ddr(String str, String str2, Boolean bool, Boolean bool2, Integer num, Integer num2, Long l, Integer num3, List list) {
        this.f6373a = str;
        this.f6374b = str2;
        this.f6375c = bool;
        this.f6376d = bool2;
        this.f6380h = num;
        this.f6377e = num2;
        this.f6378f = l;
        this.f6381i = num3;
        this.f6379g = list;
    }

    public final String toString() {
        String str = this.f6373a;
        String str2 = this.f6374b;
        String valueOf = String.valueOf(this.f6375c);
        String valueOf2 = String.valueOf(this.f6376d);
        String valueOf3 = String.valueOf(this.f6380h);
        String valueOf4 = String.valueOf(this.f6377e);
        String valueOf5 = String.valueOf(this.f6378f);
        String valueOf6 = String.valueOf(this.f6381i);
        String valueOf7 = String.valueOf(this.f6379g);
        int length = String.valueOf(str).length();
        int length2 = String.valueOf(str2).length();
        int length3 = String.valueOf(valueOf).length();
        int length4 = String.valueOf(valueOf2).length();
        int length5 = String.valueOf(valueOf3).length();
        int length6 = String.valueOf(valueOf4).length();
        int length7 = String.valueOf(valueOf5).length();
        StringBuilder sb = new StringBuilder(length + 195 + length2 + length3 + length4 + length5 + length6 + length7 + String.valueOf(valueOf6).length() + String.valueOf(valueOf7).length());
        sb.append("OemXmpData{specialTypeId='");
        sb.append(str);
        sb.append("', burstId='");
        sb.append(str2);
        sb.append("', isBurstPrimary=");
        sb.append(valueOf);
        sb.append(", isMicroVideo=");
        sb.append(valueOf2);
        sb.append(", microVideoVersion=");
        sb.append(valueOf3);
        sb.append(", MicroVideoOffset=");
        sb.append(valueOf4);
        sb.append(", MicroVideoStillImageTimestampUs=");
        sb.append(valueOf5);
        sb.append(", MotionPhotoImagePadding=");
        sb.append(valueOf6);
        sb.append(", disabledAutoCreations=");
        sb.append(valueOf7);
        sb.append('}');
        return sb.toString();
    }
}
