package p000;

import android.util.Log;
import java.io.PrintWriter;

/* renamed from: ho */
/* compiled from: PG */
public final class C0210ho extends C0205hj {

    /* renamed from: a */
    public final C0681z f13135a;

    /* renamed from: b */
    public final C0209hn f13136b;

    public C0210ho(C0681z zVar, C0025aw awVar) {
        this.f13135a = zVar;
        this.f13136b = C0209hn.m11761a(awVar);
    }

    @Deprecated
    /* renamed from: a */
    public final void mo7491a(String str, PrintWriter printWriter) {
        C0209hn hnVar = this.f13136b;
        if (hnVar.f13070c.mo9338b() > 0) {
            printWriter.print(str);
            printWriter.println("Loaders:");
            String str2 = str + "    ";
            for (int i = 0; i < hnVar.f13070c.mo9338b(); i++) {
                C0206hk hkVar = (C0206hk) hnVar.f13070c.mo9342d(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(hnVar.f13070c.mo9340c(i));
                printWriter.print(": ");
                printWriter.println(hkVar.toString());
                printWriter.print(str2);
                printWriter.print("mId=");
                printWriter.print(hkVar.f12902h);
                printWriter.print(" mArgs=");
                Object obj = null;
                printWriter.println((Object) null);
                printWriter.print(str2);
                printWriter.print("mLoader=");
                printWriter.println(hkVar.f12903i);
                C0224ib ibVar = hkVar.f12903i;
                String str3 = str2 + "  ";
                printWriter.print(str3);
                printWriter.print("mId=");
                printWriter.print(ibVar.f13816c);
                printWriter.print(" mListener=");
                printWriter.println(ibVar.f13817d);
                if (ibVar.f13819f || ibVar.f13822i) {
                    printWriter.print(str3);
                    printWriter.print("mStarted=");
                    printWriter.print(ibVar.f13819f);
                    printWriter.print(" mContentChanged=");
                    printWriter.print(ibVar.f13822i);
                    printWriter.print(" mProcessingChange=");
                    printWriter.println(false);
                }
                if (ibVar.f13820g || ibVar.f13821h) {
                    printWriter.print(str3);
                    printWriter.print("mAbandoned=");
                    printWriter.print(ibVar.f13820g);
                    printWriter.print(" mReset=");
                    printWriter.println(ibVar.f13821h);
                }
                C0219hx hxVar = (C0219hx) ibVar;
                if (hxVar.f13568a != null) {
                    printWriter.print(str3);
                    printWriter.print("mTask=");
                    printWriter.print(hxVar.f13568a);
                    printWriter.print(" waiting=");
                    C0218hw hwVar = hxVar.f13568a;
                    printWriter.println(false);
                }
                if (hxVar.f13569b != null) {
                    printWriter.print(str3);
                    printWriter.print("mCancellingTask=");
                    printWriter.print(hxVar.f13569b);
                    printWriter.print(" waiting=");
                    C0218hw hwVar2 = hxVar.f13569b;
                    printWriter.println(false);
                }
                if (hkVar.f12904j != null) {
                    printWriter.print(str2);
                    printWriter.print("mCallbacks=");
                    printWriter.println(hkVar.f12904j);
                    C0207hl hlVar = hkVar.f12904j;
                    printWriter.print(str2 + "  ");
                    printWriter.print("mDeliveredData=");
                    printWriter.println(hlVar.f12955c);
                }
                printWriter.print(str2);
                printWriter.print("mData=");
                Object obj2 = hkVar.f522d;
                if (obj2 != C0009ai.f519b) {
                    obj = obj2;
                }
                printWriter.println(C0224ib.m12595b(obj));
                printWriter.print(str2);
                printWriter.print("mStarted=");
                printWriter.println(hkVar.f521c > 0);
            }
        }
    }

    /* renamed from: a */
    public static boolean m11828a(int i) {
        return Log.isLoggable("LoaderManager", i);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        sb.append(this.f13135a.getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this.f13135a)));
        sb.append("}}");
        return sb.toString();
    }
}
