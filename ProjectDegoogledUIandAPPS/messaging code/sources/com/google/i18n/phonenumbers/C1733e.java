package com.google.i18n.phonenumbers;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.google.i18n.phonenumbers.e */
final class C1733e {

    /* renamed from: AO */
    private final ConcurrentHashMap f2570AO = new ConcurrentHashMap();

    /* renamed from: xO */
    private final String f2571xO = "/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto";

    /* renamed from: yO */
    private final C1730b f2572yO;

    /* renamed from: zO */
    private final ConcurrentHashMap f2573zO = new ConcurrentHashMap();

    C1733e(C1730b bVar) {
        this.f2572yO = bVar;
    }

    public Phonemetadata$PhoneMetadata getMetadataForNonGeographicalRegion(int i) {
        List list = (List) C1729a.getCountryCodeToRegionCodeMap().get(Integer.valueOf(i));
        boolean z = false;
        if (list.size() == 1 && "001".equals(list.get(0))) {
            z = true;
        }
        if (!z) {
            return null;
        }
        return C1732d.m4677a(Integer.valueOf(i), this.f2570AO, this.f2571xO, this.f2572yO);
    }

    public Phonemetadata$PhoneMetadata getMetadataForRegion(String str) {
        return C1732d.m4677a(str, this.f2573zO, this.f2571xO, this.f2572yO);
    }
}
