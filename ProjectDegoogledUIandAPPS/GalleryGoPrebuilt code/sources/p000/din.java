package p000;

/* renamed from: din */
/* compiled from: PG */
final class din {

    /* renamed from: a */
    private static final String[] f6620a;

    /* renamed from: b */
    private static final String[] f6621b;

    /* renamed from: c */
    private static final String[] f6622c;

    static {
        String[] strArr = {"special_type_name", "configuration", "special_type_description", "special_type_icon_uri", "edit_activity_package_name", "edit_activity_class_name", "interact_activity_package_name", "interact_activity_class_name"};
        f6620a = strArr;
        String[] strArr2 = (String[]) ife.m12860a((Object[]) strArr, (Object[]) new String[]{"launch_activity_package_name", "launch_activity_class_name"}, String.class);
        f6621b = strArr2;
        f6622c = (String[]) ife.m12860a((Object[]) strArr2, (Object[]) new String[]{"editor_description", "editor_promo"}, String.class);
    }

    /* renamed from: a */
    static String[] m6155a(int i) {
        return i != 1 ? i != 2 ? f6622c : f6621b : f6620a;
    }
}
