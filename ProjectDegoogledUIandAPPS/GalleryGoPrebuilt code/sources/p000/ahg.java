package p000;

import java.util.List;

/* renamed from: ahg */
/* compiled from: PG */
public abstract class ahg {

    /* renamed from: a */
    private static final String f490a = iol.m14236b("InputMerger");

    /* renamed from: a */
    public abstract ahf mo474a(List list);

    /* renamed from: a */
    public static ahg m491a(String str) {
        try {
            return (ahg) Class.forName(str).newInstance();
        } catch (Exception e) {
            iol.m14231a();
            String str2 = f490a;
            iol.m14234a(str2, "Trouble instantiating + " + str, e);
            return null;
        }
    }
}
