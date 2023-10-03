package p000;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* renamed from: bgx */
/* compiled from: PG */
public final class bgx implements bif {

    /* renamed from: a */
    private final Context f2357a;

    public bgx(Context context) {
        this.f2357a = context;
    }

    /* renamed from: a */
    public final List mo2027a(bhx bhx) {
        List list;
        List list2;
        List list3;
        List list4;
        List list5;
        ArrayList arrayList = new ArrayList();
        bhr bhr = (bhr) bhx;
        cns cns = bhr.f2390i;
        if (cns == bii.f2452a || (cns instanceof bie) || (cns instanceof bid)) {
            list = Collections.emptyList();
        } else {
            list = Collections.singletonList("Unknown trigger provided");
        }
        arrayList.addAll(list);
        bih bih = bhr.f2387f;
        int i = bih.f2449b;
        int i2 = bih.f2450c;
        int i3 = bih.f2451d;
        ArrayList arrayList2 = new ArrayList();
        if (i3 < i2) {
            arrayList2.add("Maximum backoff must be greater than or equal to initial backoff");
        }
        if (i3 < 300) {
            arrayList2.add("Maximum backoff must be greater than 300s (5 minutes)");
        }
        if (i2 < 30) {
            arrayList2.add("Initial backoff must be at least 30s");
        }
        arrayList.addAll(arrayList2);
        Bundle bundle = bhr.f2383b;
        if (bundle == null || bundle.isEmpty()) {
            list2 = Collections.emptyList();
        } else {
            list2 = new ArrayList();
            Parcel obtain = Parcel.obtain();
            bundle.writeToParcel(obtain, 0);
            int dataSize = obtain.dataSize();
            obtain.recycle();
            if (dataSize > 10240) {
                list2.add(String.format(Locale.US, "Extras too large: %d bytes is > the max (%d bytes)", new Object[]{Integer.valueOf(dataSize), 10240}));
            }
            if (bhr.f2385d > 1) {
                for (String str : bundle.keySet()) {
                    Object obj = bundle.get(str);
                    if (obj == null || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof Boolean)) {
                        list5 = Collections.emptyList();
                    } else {
                        list5 = Collections.singletonList(String.format(Locale.US, "Received value of type '%s' for key '%s', but only the following extra parameter types are supported: Integer, Long, Double, String, and Boolean", new Object[]{obj.getClass(), str}));
                    }
                    list2.addAll(list5);
                }
            }
        }
        arrayList.addAll(list2);
        String str2 = bhr.f2384c;
        if (str2 == null) {
            list3 = Collections.singletonList("Tag can't be null");
        } else if (str2.length() > 100) {
            list3 = Collections.singletonList("Tag must be shorter than 100");
        } else {
            list3 = Collections.emptyList();
        }
        arrayList.addAll(list3);
        String str3 = bhr.f2382a;
        if (str3 != null && !str3.isEmpty()) {
            Context context = this.f2357a;
            if (context != null) {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    Intent intent = new Intent("com.firebase.jobdispatcher.ACTION_EXECUTE");
                    intent.setClassName(this.f2357a, str3);
                    List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
                    if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
                        Iterator<ResolveInfo> it = queryIntentServices.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                list4 = Collections.singletonList(str3.concat(" is disabled."));
                                break;
                            }
                            ResolveInfo next = it.next();
                            if (next.serviceInfo != null && next.serviceInfo.enabled) {
                                list4 = Collections.emptyList();
                                break;
                            }
                        }
                    } else {
                        StringBuilder sb = new StringBuilder(str3.length() + 145);
                        sb.append("Couldn't find a registered service with the name ");
                        sb.append(str3);
                        sb.append(". Is it declared in the manifest with the right intent-filter? If not, the job won't be started.");
                        Log.e("FJD.GooglePlayReceiver", sb.toString());
                        list4 = Collections.emptyList();
                    }
                } else {
                    list4 = Collections.singletonList("PackageManager is null, can't validate service");
                }
            } else {
                list4 = Collections.singletonList("Context is null, can't query PackageManager");
            }
        } else {
            list4 = Collections.singletonList("Service can't be empty");
        }
        arrayList.addAll(list4);
        if (bhr.f2389h && bhr.f2390i == bii.f2452a) {
            arrayList.add("Trigger.NOW can't be used with recurring jobs");
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }
}
