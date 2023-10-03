package p000;

import com.google.android.apps.photosgo.R;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: cbm */
/* compiled from: PG */
public final class cbm {

    /* renamed from: a */
    public static final hsu f4014a;

    static {
        hsu hsu;
        hsq g = hsu.m12070g();
        g.mo7932a(car.ALPACA, Integer.valueOf(R.string.editor_presets_alpaca));
        g.mo7932a(car.AUTO_ENHANCE, Integer.valueOf(R.string.editor_presets_auto));
        g.mo7932a(car.BAZAAR, Integer.valueOf(R.string.editor_presets_bazaar));
        g.mo7932a(car.BLUSH, Integer.valueOf(R.string.editor_presets_blush));
        g.mo7932a(car.COLOR_POP, Integer.valueOf(R.string.editor_presets_color_pop));
        g.mo7932a(car.EIFFEL, Integer.valueOf(R.string.editor_presets_eiffel));
        g.mo7932a(car.METRO, Integer.valueOf(R.string.editor_presets_metro));
        g.mo7932a(car.MODENA, Integer.valueOf(R.string.editor_presets_modena));
        g.mo7932a(car.OLLIE, Integer.valueOf(R.string.editor_presets_ollie));
        g.mo7932a(car.ORIGINAL, Integer.valueOf(R.string.editor_presets_original));
        g.mo7932a(car.PALMA, Integer.valueOf(R.string.editor_presets_palma));
        g.mo7932a(car.REEL, Integer.valueOf(R.string.editor_presets_reel));
        g.mo7932a(car.VISTA, Integer.valueOf(R.string.editor_presets_vista));
        g.mo7932a(car.VOGUE, Integer.valueOf(R.string.editor_presets_vogue));
        g.mo7932a(car.WEST, Integer.valueOf(R.string.editor_presets_west));
        Iterator it = g.mo7930a().entrySet().iterator();
        if (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Enum enumR = (Enum) entry.getKey();
            Object value = entry.getValue();
            ife.m12843a((Object) enumR, value);
            EnumMap enumMap = new EnumMap(enumR.getDeclaringClass());
            enumMap.put(enumR, value);
            while (it.hasNext()) {
                Map.Entry entry2 = (Map.Entry) it.next();
                Enum enumR2 = (Enum) entry2.getKey();
                Object value2 = entry2.getValue();
                ife.m12843a((Object) enumR2, value2);
                enumMap.put(enumR2, value2);
            }
            int size = enumMap.size();
            if (size != 0) {
                if (size != 1) {
                    hsu = new hsi(enumMap);
                } else {
                    Map.Entry entry3 = (Map.Entry) ife.m12903f((Iterable) enumMap.entrySet());
                    hsu = hsu.m12066a((Enum) entry3.getKey(), entry3.getValue());
                }
                f4014a = hsu;
            }
        }
        hsu = hvb.f13454a;
        f4014a = hsu;
    }
}
