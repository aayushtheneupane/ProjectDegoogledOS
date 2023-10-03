package androidx.appcompat.mms.pdu;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/* renamed from: androidx.appcompat.mms.pdu.j */
public class C0193j {

    /* renamed from: _l */
    private Vector f176_l;

    /* renamed from: am */
    private Map f177am;

    /* renamed from: bm */
    private Map f178bm;

    /* renamed from: cm */
    private Map f179cm;

    /* renamed from: dm */
    private Map f180dm;

    public C0193j() {
        this.f176_l = null;
        this.f177am = null;
        this.f178bm = null;
        this.f179cm = null;
        this.f180dm = null;
        this.f176_l = new Vector();
        this.f177am = new HashMap();
        this.f178bm = new HashMap();
        this.f179cm = new HashMap();
        this.f180dm = new HashMap();
    }

    /* renamed from: b */
    private void m155b(C0197n nVar) {
        byte[] ic = nVar.mo1265ic();
        if (ic != null) {
            this.f177am.put(new String(ic), nVar);
        }
        byte[] jc = nVar.mo1266jc();
        if (jc != null) {
            this.f178bm.put(new String(jc), nVar);
        }
        byte[] name = nVar.getName();
        if (name != null) {
            this.f179cm.put(new String(name), nVar);
        }
        byte[] lc = nVar.mo1268lc();
        if (lc != null) {
            this.f180dm.put(new String(lc), nVar);
        }
    }

    /* renamed from: a */
    public boolean mo1240a(C0197n nVar) {
        if (nVar != null) {
            m155b(nVar);
            return this.f176_l.add(nVar);
        }
        throw new NullPointerException();
    }

    public C0197n getPart(int i) {
        return (C0197n) this.f176_l.get(i);
    }

    public void removeAll() {
        this.f176_l.clear();
    }

    /* renamed from: a */
    public void mo1239a(int i, C0197n nVar) {
        if (nVar != null) {
            m155b(nVar);
            this.f176_l.add(i, nVar);
            return;
        }
        throw new NullPointerException();
    }
}
