package p000;

/* renamed from: ehj */
/* compiled from: PG */
final /* synthetic */ class ehj implements icf {

    /* renamed from: a */
    private final eho f8295a;

    public ehj(eho eho) {
        this.f8295a = eho;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        eho eho = this.f8295a;
        cjm a = cjm.m4399a(((ehh) obj).f8292b);
        if (a == null) {
            a = cjm.UNKNOWN;
        }
        int ordinal = a.ordinal();
        char c = 65535;
        if (ordinal == 0) {
            String str = eho.f8301b;
            int hashCode = str.hashCode();
            if (hashCode != -1153346636) {
                if (hashCode != -812190629) {
                    if (hashCode == 433141802 && str.equals("UNKNOWN")) {
                        c = 1;
                    }
                } else if (str.equals("RESTRICTED")) {
                    c = 0;
                }
            } else if (str.equals("UNRESTRICTED")) {
                c = 2;
            }
            if (c == 0) {
                return eho.mo4813a(cjm.NOT_APPLICABLE);
            }
            if (c == 1 || c == 2) {
                return ife.m12820a((Object) cjm.UNKNOWN);
            }
            cwn.m5514b("UserPreferencesDataService: unknown face grouping availability %s", str);
            return ife.m12820a((Object) cjm.UNKNOWN);
        } else if (ordinal == 1) {
            return ife.m12820a((Object) cjm.ACCEPTED);
        } else {
            if (ordinal == 2) {
                return ife.m12820a((Object) cjm.REVOKED);
            }
            if (ordinal == 3) {
                String str2 = eho.f8301b;
                int hashCode2 = str2.hashCode();
                if (hashCode2 != -1153346636) {
                    if (hashCode2 != -812190629) {
                        if (hashCode2 == 433141802 && str2.equals("UNKNOWN")) {
                            c = 2;
                        }
                    } else if (str2.equals("RESTRICTED")) {
                        c = 1;
                    }
                } else if (str2.equals("UNRESTRICTED")) {
                    c = 0;
                }
                if (c == 0) {
                    return eho.mo4813a(cjm.ACCEPTED);
                }
                if (c == 1) {
                    return eho.mo4813a(cjm.NOT_APPLICABLE);
                }
                if (c == 2) {
                    return ife.m12820a((Object) cjm.CONDITIONALLY_ACCEPTED);
                }
                cwn.m5514b("UserPreferencesDataService: unknown face grouping availability %s", str2);
                return ife.m12820a((Object) cjm.CONDITIONALLY_ACCEPTED);
            } else if (ordinal != 4) {
                return ife.m12820a((Object) a);
            } else {
                return ife.m12820a((Object) cjm.NOT_APPLICABLE);
            }
        }
    }
}
