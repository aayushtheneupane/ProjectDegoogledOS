package p000;

import java.util.Map;

/* renamed from: iit */
/* compiled from: PG */
public final class iit extends iir implements iiv, fdy {
    private iit() {
        super((iix) fdw.f9333c);
    }

    public /* synthetic */ iit(byte[] bArr) {
        super((iix) fdw.f9333c);
    }

    public /* synthetic */ iit(char[] cArr) {
        super((iix) fdx.f9338e);
    }

    public /* synthetic */ iit(boolean[] zArr) {
        super((iix) hox.f13179e);
    }

    public /* synthetic */ iit(short[] sArr) {
        super((iix) igg.f14083i);
    }

    public /* synthetic */ iit(float[] fArr) {
        super((iix) igh.f14094c);
    }

    public /* synthetic */ iit(byte[][] bArr) {
        super((iix) ign.f14129g);
    }

    public /* synthetic */ iit(int[] iArr) {
        super((iix) igw.f14160d);
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public final iiu mo8766f() {
        if (this.f14319c) {
            return (iiu) this.f14318b;
        }
        ((iiu) this.f14318b).f14321j.mo8729b();
        return (iiu) super.mo8766f();
    }

    /* renamed from: b */
    public final void mo8751b() {
        super.mo8751b();
        iiu iiu = (iiu) this.f14318b;
        iiu.f14321j = iiu.f14321j.clone();
    }

    /* renamed from: a */
    public final boolean mo8784a(iih iih) {
        iiu iiu = (iiu) this.f14318b;
        Map map = iix.f14324A;
        iiu.mo8786b(iih);
        return iiu.f14321j.mo8726a((iil) iih.f14244d);
    }

    /* renamed from: a */
    public final void mo8783a(iih iih, Object obj) {
        Map map = iix.f14324A;
        if (iih.f14241a == this.f14317a) {
            if (this.f14319c) {
                mo8751b();
                this.f14319c = false;
            }
            iim iim = ((iiu) this.f14318b).f14321j;
            if (iim.f14256b) {
                iim = iim.clone();
                ((iiu) this.f14318b).f14321j = iim;
            }
            iiw iiw = iih.f14244d;
            if (iiw.mo8719c() == imc.ENUM) {
                obj = Integer.valueOf(((iiz) obj).getNumber());
            }
            iim.mo8723a(iiw, obj);
            return;
        }
        throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
    }

    public /* synthetic */ iit(char[][] cArr) {
        super((iix) ins.f14576h);
    }

    public /* synthetic */ iit(short[][] sArr) {
        super((iix) irc.f14819g);
    }
}
