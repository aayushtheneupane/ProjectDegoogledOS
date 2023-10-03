package p000;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bhu */
/* compiled from: PG */
public final class bhu {

    /* renamed from: a */
    private final String f2401a;

    public bhu(String str) {
        this.f2401a = str;
    }

    /* renamed from: a */
    public final bhv mo2058a(Bundle bundle) {
        cns cns;
        bih bih;
        Bundle bundle2 = bundle;
        if (bundle2 != null) {
            Bundle bundle3 = new Bundle(bundle2);
            boolean z = bundle3.getBoolean(this.f2401a.concat("recurring"));
            boolean z2 = bundle3.getBoolean(this.f2401a.concat("replace_current"));
            int i = bundle3.getInt(this.f2401a.concat("persistent"));
            int i2 = bundle3.getInt(this.f2401a.concat("constraints"));
            int[] iArr = bgv.f2355a;
            int length = iArr.length;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                int i5 = 1;
                if (i3 >= length) {
                    break;
                }
                int i6 = iArr[i3];
                if ((i2 & i6) != i6) {
                    i5 = 0;
                }
                i4 += i5;
                i3++;
            }
            int[] iArr2 = new int[i4];
            int i7 = 0;
            for (int i8 : bgv.f2355a) {
                if ((i2 & i8) == i8) {
                    iArr2[i7] = i8;
                    i7++;
                }
            }
            int i9 = bundle3.getInt(this.f2401a.concat("trigger_type"));
            if (i9 == 1) {
                cns = bii.m2610a(bundle3.getInt(this.f2401a.concat("window_start")), bundle3.getInt(this.f2401a.concat("window_end")));
            } else if (i9 == 2) {
                cns = bii.f2452a;
            } else if (i9 != 3) {
                cns = null;
            } else {
                String string = bundle3.getString(this.f2401a.concat("observed_uris"));
                ArrayList arrayList = new ArrayList();
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    JSONArray jSONArray = jSONObject.getJSONArray("uri_flags");
                    JSONArray jSONArray2 = jSONObject.getJSONArray("uris");
                    int length2 = jSONArray.length();
                    for (int i10 = 0; i10 < length2; i10++) {
                        arrayList.add(new big(Uri.parse(jSONArray2.getString(i10)), jSONArray.getInt(i10)));
                    }
                    cns = bii.m2609a(Collections.unmodifiableList(arrayList));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            int i11 = bundle3.getInt(this.f2401a.concat("retry_policy"));
            if (i11 == 1 || i11 == 2) {
                bih = new bih(i11, bundle3.getInt(this.f2401a.concat("initial_backoff_seconds")), bundle3.getInt(this.f2401a.concat("maximum_backoff_seconds")));
            } else {
                bih = bih.f2448a;
            }
            String string2 = bundle3.getString(this.f2401a.concat("tag"));
            String string3 = bundle3.getString(this.f2401a.concat("service"));
            if (string2 == null || string3 == null || cns == null) {
                return null;
            }
            bhv bhv = new bhv();
            bhv.f2402a = string2;
            bhv.f2403b = string3;
            bhv.f2410i = cns;
            bhv.f2408g = bih;
            bhv.f2404c = z;
            bhv.f2405d = i;
            bhv.f2406e = iArr2;
            bhv.f2409h = z2;
            if (!TextUtils.isEmpty(this.f2401a)) {
                Iterator it = bundle3.keySet().iterator();
                while (it.hasNext()) {
                    if (((String) it.next()).startsWith(this.f2401a)) {
                        it.remove();
                    }
                }
            }
            bhv.f2407f.putAll(bundle3);
            return bhv;
        }
        throw new IllegalArgumentException("Unexpected null Bundle provided");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final Bundle mo2057a(bhx bhx, Bundle bundle) {
        Bundle g = bhx.mo2052g();
        if (g != null) {
            bundle.putAll(g);
        }
        bundle.putInt(this.f2401a.concat("persistent"), bhx.mo2049d());
        bundle.putBoolean(this.f2401a.concat("recurring"), bhx.mo2050e());
        bundle.putBoolean(this.f2401a.concat("replace_current"), bhx.mo2054i());
        bundle.putString(this.f2401a.concat("tag"), bhx.mo2048c());
        bundle.putString(this.f2401a.concat("service"), bhx.mo2047b());
        bundle.putInt(this.f2401a.concat("constraints"), bgv.m2519a(bhx.mo2051f()));
        cns k = bhx.mo2056k();
        if (k == bii.f2452a) {
            bundle.putInt(this.f2401a.concat("trigger_type"), 2);
        } else if (k instanceof bie) {
            bie bie = (bie) k;
            bundle.putInt(this.f2401a.concat("trigger_type"), 1);
            bundle.putInt(this.f2401a.concat("window_start"), bie.f2444a);
            bundle.putInt(this.f2401a.concat("window_end"), bie.f2445b);
        } else if (k instanceof bid) {
            bundle.putInt(this.f2401a.concat("trigger_type"), 3);
            List<big> list = ((bid) k).f2443a;
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = new JSONArray();
            for (big big : list) {
                jSONArray.put(big.f2447b);
                jSONArray2.put(big.f2446a);
            }
            try {
                jSONObject.put("uri_flags", jSONArray);
                jSONObject.put("uris", jSONArray2);
                bundle.putString(this.f2401a.concat("observed_uris"), jSONObject.toString());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported trigger.");
        }
        bih h = bhx.mo2053h();
        if (h == null) {
            h = bih.f2448a;
        }
        bundle.putInt(this.f2401a.concat("retry_policy"), h.f2449b);
        bundle.putInt(this.f2401a.concat("initial_backoff_seconds"), h.f2450c);
        bundle.putInt(this.f2401a.concat("maximum_backoff_seconds"), h.f2451d);
        return bundle;
    }
}
