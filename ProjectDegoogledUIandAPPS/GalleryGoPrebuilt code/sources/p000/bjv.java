package p000;

import android.app.Application;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.support.p002v7.widget.RecyclerView;
import com.google.android.apps.photosgo.environment.BuildType;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

/* renamed from: bjv */
/* compiled from: PG */
final class bjv implements iqk {

    /* renamed from: a */
    private final int f2674a;

    /* renamed from: b */
    private final /* synthetic */ bjw f2675b;

    public bjv(bjw bjw, int i) {
        this.f2675b = bjw;
        this.f2674a = i;
    }

    /* renamed from: a */
    public final Object mo2097a() {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        bjv bjv;
        bjv bjv2;
        Object obj5;
        Object obj6;
        gtc gtc;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        int i = this.f2674a;
        if (i / 100 != 0) {
            switch (i) {
                case 100:
                    return (ffs) iol.m14229a((Object) new cwv(), "Cannot return null from a non-@Nullable @Provides method");
                case 101:
                    return this.f2675b.mo2150A();
                case 102:
                    return this.f2675b.mo2253bV();
                case 103:
                    return new bjs(this.f2675b);
                case 104:
                    return this.f2675b.mo2180aB();
                case 105:
                    return this.f2675b.mo2333j();
                case 106:
                    return ceo.m4214b();
                case 107:
                    return new biv(this.f2675b);
                case 108:
                    return this.f2675b.mo2211ag();
                case 109:
                    return this.f2675b.mo2222ar();
                case 110:
                    return this.f2675b.mo2245bN();
                case 111:
                    return this.f2675b.mo2308ch();
                case 112:
                    return this.f2675b.mo2310cj();
                case 113:
                    return (faz) iol.m14229a((Object) new fbh(), "Cannot return null from a non-@Nullable @Provides method");
                case 114:
                    return this.f2675b.mo2238bG();
                case 115:
                    bjw bjw = this.f2675b;
                    cjr bd = bjw.mo2261bd();
                    iqk iqk = bjw.f2699X;
                    if (iqk == null) {
                        iqk = new bjv(bjw, 116);
                        bjw.f2699X = iqk;
                    }
                    inw a = iog.m14218a(iqk);
                    if (bd.mo3175a()) {
                        obj10 = (dip) a.mo9034a();
                    } else {
                        obj10 = new diu();
                    }
                    return (dip) iol.m14229a(obj10, "Cannot return null from a non-@Nullable @Provides method");
                case 116:
                    bjw bjw2 = this.f2675b;
                    return new diq((dhl) bjw2.mo2263bf(), bjw2.mo2225au(), bjw2.mo2220ap());
                default:
                    throw new AssertionError(i);
            }
        } else {
            boolean z = false;
            switch (i) {
                case 0:
                    return this.f2675b.mo2163N();
                case 1:
                    return this.f2675b.mo2158I();
                case RecyclerView.SCROLL_STATE_SETTLING:
                    this.f2675b.mo2331h();
                    return hto.m12120a((Object) (glj) iol.m14229a((Object) new gns(), "Cannot return null from a non-@Nullable @Provides method"));
                case 3:
                    bjw bjw3 = this.f2675b;
                    gus gus = (gus) bjw3.mo2332i();
                    bjw3.mo2341r();
                    return hto.m12121a((Object) (gli) iol.m14229a((Object) new gla(), "Cannot return null from a non-@Nullable @Provides method"), (Object) (gli) iol.m14229a((Object) new hjb(), "Cannot return null from a non-@Nullable @Provides method"));
                case 4:
                    return this.f2675b.mo2336m();
                case 5:
                    return hto.m12120a((Object) this.f2675b.mo2341r());
                case 6:
                    return this.f2675b.mo2337n();
                case 7:
                    return this.f2675b.mo2338o();
                case 8:
                    return this.f2675b.mo2340q();
                case 9:
                    return this.f2675b.mo2341r();
                case 10:
                    bjw bjw4 = this.f2675b;
                    glh glh = (glh) iol.m14229a((Object) new glb((gus) bjw4.mo2332i()), "Cannot return null from a non-@Nullable @Provides method");
                    Object obj11 = bjw4.f2755b;
                    if (obj11 instanceof iok) {
                        synchronized (obj11) {
                            obj = bjw4.f2755b;
                            if (obj instanceof iok) {
                                bjw4.mo2157H();
                                gkt gkt = new gkt((iel) bjw4.mo2333j());
                                bjw4.f2755b = iog.m14219a(bjw4.f2755b, gkt);
                                obj = gkt;
                            }
                        }
                        obj11 = obj;
                    }
                    return hto.m12122a(glh, (glh) iol.m14229a((Object) new gkr((gkt) obj11), "Cannot return null from a non-@Nullable @Provides method"), (glh) iol.m14229a((Object) new hjc(bjw4.mo2341r()), "Cannot return null from a non-@Nullable @Provides method"));
                case 11:
                    bjw bjw5 = this.f2675b;
                    return hto.m12121a((Object) (glf) iol.m14229a((Object) new gnt(bjw5.mo2331h()), "Cannot return null from a non-@Nullable @Provides method"), (Object) new gnz(ftz.m9622a(bjw5.f2702a), bjw5.mo2314cn(), (iel) bjw5.mo2333j()));
                case 12:
                    return this.f2675b.mo2156G();
                case 13:
                    return this.f2675b.mo2345v();
                case 14:
                    return hto.m12120a((Object) (glk) iol.m14229a((Object) new gkz((gus) this.f2675b.mo2332i()), "Cannot return null from a non-@Nullable @Provides method"));
                case 15:
                    return this.f2675b.mo2344u();
                case 16:
                    return this.f2675b.mo2154E();
                case 17:
                    return Integer.valueOf(this.f2675b.mo2231b());
                case 18:
                    return this.f2675b.mo2347x();
                case 19:
                    return this.f2675b.mo2348y();
                case 20:
                    return this.f2675b.mo2330g();
                case 21:
                    return exn.m8328b();
                case 22:
                    bjw bjw6 = this.f2675b;
                    Object obj12 = bjw6.f2808c;
                    if (obj12 instanceof iok) {
                        synchronized (obj12) {
                            obj2 = bjw6.f2808c;
                            if (obj2 instanceof iok) {
                                hvf hvf = hvf.f13465a;
                                gzy y = bjw6.mo2348y();
                                Map x = bjw6.mo2347x();
                                htm c = hto.m12129c(hvf.f13466b);
                                hvr a2 = hvf.iterator();
                                while (a2.hasNext()) {
                                    c.mo7874b(y.mo7210a((String) a2.next()));
                                }
                                hto a3 = c.mo7993a();
                                ife.m12879b(x.keySet().containsAll(a3), "A package was specified for FreshnessOverAnalysis that does not exist: %s, %s", a3, x);
                                obj2 = (Set) iol.m14229a((Object) a3, "Cannot return null from a non-@Nullable @Provides method");
                                bjw6.f2808c = iog.m14219a(bjw6.f2808c, obj2);
                            }
                        }
                        obj12 = obj2;
                    }
                    return (Set) obj12;
                case 23:
                    return this.f2675b.mo2327d();
                case 24:
                    bjw bjw7 = this.f2675b;
                    Object obj13 = bjw7.f2861d;
                    if (obj13 instanceof iok) {
                        synchronized (obj13) {
                            obj3 = bjw7.f2861d;
                            if (obj3 instanceof iok) {
                                hsu hsu = hvb.f13454a;
                                hsu hsu2 = hvb.f13454a;
                                Map w = bjw7.mo2346w();
                                Map x2 = bjw7.mo2347x();
                                hsq g = hsu.m12070g();
                                for (Map.Entry entry : hsu.entrySet()) {
                                    String a4 = ((gzo) entry.getKey()).mo7225a();
                                    ife.m12878b(!a4.contains("#"), "Subpackages should be provided without their base package: %s", (Object) a4);
                                    String str = (String) w.get(a4);
                                    if (str != null) {
                                        StringBuilder sb = new StringBuilder(String.valueOf(a4).length() + 1 + str.length());
                                        sb.append(a4);
                                        sb.append("#");
                                        sb.append(str);
                                        a4 = sb.toString();
                                    }
                                    g.mo7932a(a4, new gyf((iqk) entry.getValue()));
                                }
                                for (Map.Entry entry2 : hsu2.entrySet()) {
                                    String a5 = ((gzo) entry2.getKey()).mo7225a();
                                    ife.m12878b(!a5.contains("#"), "Subpackages should be provided without their base package: %s", (Object) a5);
                                    String str2 = (String) w.get(a5);
                                    if (str2 != null) {
                                        StringBuilder sb2 = new StringBuilder(String.valueOf(a5).length() + 1 + str2.length());
                                        sb2.append(a5);
                                        sb2.append("#");
                                        sb2.append(str2);
                                        a5 = sb2.toString();
                                    }
                                    g.mo7932a(a5, new gyg((iqk) entry2.getValue()));
                                }
                                hsu a6 = g.mo7930a();
                                if (x2.keySet().containsAll(a6.keySet())) {
                                    obj3 = (Map) iol.m14229a((Object) a6, "Cannot return null from a non-@Nullable @Provides method");
                                    bjw7.f2861d = iog.m14219a(bjw7.f2861d, obj3);
                                } else {
                                    String valueOf = String.valueOf(x2.keySet());
                                    String valueOf2 = String.valueOf(a6.keySet());
                                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 86 + String.valueOf(valueOf2).length());
                                    sb3.append("Parameters provided for mendel package not in use. Known packages: ");
                                    sb3.append(valueOf);
                                    sb3.append(", params provided: ");
                                    sb3.append(valueOf2);
                                    throw new IllegalStateException(sb3.toString());
                                }
                            }
                        }
                        obj13 = obj3;
                    }
                    return (Map) obj13;
                case 25:
                    return this.f2675b.mo2161L();
                case 26:
                    return this.f2675b.mo2159J();
                case 27:
                    bjw bjw8 = this.f2675b;
                    Object Q = bjw8.mo2166Q();
                    hmd hmd = bjw8.f2946f;
                    if (hmd == null) {
                        iqk iqk2 = bjw8.f2914e;
                        if (iqk2 == null) {
                            iqk2 = new bjv(bjw8, 28);
                            bjw8.f2914e = iqk2;
                        }
                        hmd = new hny(iog.m14218a(iqk2));
                        bjw8.f2946f = hmd;
                    }
                    return hto.m12121a(Q, (Object) hmd);
                case 28:
                    return hpy.m11893b(this.f2675b.mo2164O());
                case 29:
                    return this.f2675b.mo2169T();
                case 30:
                    bjw bjw9 = this.f2675b;
                    Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = (Application.ActivityLifecycleCallbacks) iol.m14229a((Object) ((got) bjw9.mo2334k()).f11770d, "Cannot return null from a non-@Nullable @Provides method");
                    Application.ActivityLifecycleCallbacks activityLifecycleCallbacks2 = (Application.ActivityLifecycleCallbacks) iol.m14229a((Object) bjw9.mo2167R().f3079a, "Cannot return null from a non-@Nullable @Provides method");
                    Application.ActivityLifecycleCallbacks activityLifecycleCallbacks3 = (Application.ActivityLifecycleCallbacks) iol.m14229a((Object) new hez(), "Cannot return null from a non-@Nullable @Provides method");
                    Object obj14 = bjw9.f2947g;
                    if (obj14 instanceof iok) {
                        synchronized (obj14) {
                            obj4 = bjw9.f2947g;
                            if (obj4 instanceof iok) {
                                ham ham = new ham(hph.f13219a);
                                bjw9.f2947g = iog.m14219a(bjw9.f2947g, ham);
                                obj4 = ham;
                            }
                        }
                        obj14 = obj4;
                    }
                    return hto.m12123a(activityLifecycleCallbacks, activityLifecycleCallbacks2, activityLifecycleCallbacks3, (Application.ActivityLifecycleCallbacks) iol.m14229a((Object) new haf((ham) obj14), "Cannot return null from a non-@Nullable @Provides method"));
                case 31:
                    return this.f2675b.mo2324cx();
                case 32:
                    return this.f2675b.mo2170U();
                case 33:
                    bjw bjw10 = this.f2675b;
                    Context a7 = ftz.m9622a(bjw10.f2702a);
                    iqk iqk3 = bjw10.f2950j;
                    if (iqk3 != null) {
                        bjv = iqk3;
                    } else {
                        bjv bjv3 = new bjv(bjw10, 34);
                        bjw10.f2950j = bjv3;
                        bjv = bjv3;
                    }
                    iel cn = bjw10.mo2314cn();
                    iqk iqk4 = bjw10.f2951k;
                    if (iqk4 == null) {
                        iqk4 = new bjv(bjw10, 37);
                        bjw10.f2951k = iqk4;
                    }
                    inw a8 = iog.m14218a(iqk4);
                    iqk S = bjw10.mo2168S();
                    iqk iqk5 = bjw10.f2952l;
                    if (iqk5 == null) {
                        bjv bjv4 = new bjv(bjw10, 38);
                        bjw10.f2952l = bjv4;
                        bjv2 = bjv4;
                    } else {
                        bjv2 = iqk5;
                    }
                    return new hbk(a7, bjv, cn, a8, S, bjv2);
                case 34:
                    bjw bjw11 = this.f2675b;
                    hbg hbg = (hbg) iol.m14229a((Object) new gky(bjw11.mo2342s()), "Cannot return null from a non-@Nullable @Provides method");
                    hbg hbg2 = (hbg) iol.m14229a((Object) new hfm(ftz.m9622a(bjw11.f2702a)), "Cannot return null from a non-@Nullable @Provides method");
                    hbg hbg3 = (hbg) iol.m14229a((Object) new gyv(bjw11.mo2155F()), "Cannot return null from a non-@Nullable @Provides method");
                    fqp z2 = bjw11.mo2349z();
                    iqk iqk6 = bjw11.f2948h;
                    if (iqk6 == null) {
                        iqk6 = new bjv(bjw11, 35);
                        bjw11.f2948h = iqk6;
                    }
                    inw a9 = iog.m14218a(iqk6);
                    iqk iqk7 = bjw11.f2949i;
                    if (iqk7 == null) {
                        iqk7 = new bjv(bjw11, 36);
                        bjw11.f2949i = iqk7;
                    }
                    gzw gzw = new gzw(a9, iog.m14218a(iqk7), bjw11.mo2314cn(), bjw11.mo2348y(), bjw11.mo2231b(), hto.m12121a((Object) (String) iol.m14229a((Object) "PHOTOS_GO", "Cannot return null from a non-@Nullable @Provides method"), (Object) (String) iol.m14229a((Object) "PHOTOS_GO_ANDROID_PRIMES", "Cannot return null from a non-@Nullable @Provides method")), iog.m14218a(bjw11.mo2152C()));
                    gxz F = bjw11.mo2155F();
                    bjw11.mo2335l();
                    return hto.m12119a(5, hbg, hbg2, hbg3, (hbg) iol.m14229a((Object) new gzp(bjw11.mo2347x(), gzw, z2, F), "Cannot return null from a non-@Nullable @Provides method"), new hjn(bjw11.mo2339p()));
                case 35:
                    return Boolean.valueOf(this.f2675b.mo2171V());
                case 36:
                    return this.f2675b.mo2172W();
                case 37:
                    return this.f2675b.mo2335l();
                case 38:
                    return this.f2675b.mo2176a();
                case 39:
                    return this.f2675b.mo2175Z();
                case 40:
                    return this.f2675b.mo2174Y();
                case 41:
                    return this.f2675b.mo2173X();
                case 42:
                    bjw bjw12 = this.f2675b;
                    Object obj15 = bjw12.f2953m;
                    if (obj15 instanceof iok) {
                        synchronized (obj15) {
                            obj5 = bjw12.f2953m;
                            if (obj5 instanceof iok) {
                                Context a10 = ftz.m9622a(bjw12.f2702a);
                                ext M = bjw12.mo2162M();
                                String str3 = (String) iol.m14229a((Object) "PHOTOS_GO_ANDROID_PRIMES", "Cannot return null from a non-@Nullable @Provides method");
                                hvf hvf2 = hvf.f13465a;
                                if (hvf2.f13466b <= 1) {
                                    z = true;
                                }
                                ife.m12845a(z, (Object) "Multiple AccountProviders found.");
                                fow fow = fow.f10176b;
                                if (hvf2.f13466b == 1) {
                                    fow = (fow) hvf2.iterator().next();
                                }
                                fpc fpc = new fpc();
                                fpc.f10186a = a10;
                                fpc.f10187b = M;
                                fpc.f10188c = str3;
                                fpc.f10189d = fow;
                                obj5 = (fpn) iol.m14229a((Object) new fpn((Context) ife.m12898e((Object) fpc.f10186a), (ext) ife.m12898e((Object) fpc.f10187b), (String) ife.m12898e((Object) fpc.f10188c), fpc.f10189d), "Cannot return null from a non-@Nullable @Provides method");
                                bjw12.f2953m = iog.m14219a(bjw12.f2953m, obj5);
                            }
                        }
                        obj15 = obj5;
                    }
                    return hto.m12120a((Object) (fpn) obj15);
                case 43:
                    return this.f2675b.mo2205aa();
                case 44:
                    return (hbc) iol.m14229a((Object) hfn.f12657a, "Cannot return null from a non-@Nullable @Provides method");
                case 45:
                    return this.f2675b.mo2206ab();
                case 46:
                    return this.f2675b.mo2207ac();
                case 47:
                    bjw bjw13 = this.f2675b;
                    return new cvv(bjw13.mo2314cn(), hsu.m12066a(cvp.f5747a, (cvo) iol.m14229a((Object) new dfx(bjw13.mo2283bz(), bjw13.mo2282by()), "Cannot return null from a non-@Nullable @Provides method")), (cvx) bjw13.mo2232bA(), bjw13.mo2221aq());
                case 48:
                    bjw bjw14 = this.f2675b;
                    iel cn2 = bjw14.mo2314cn();
                    Map bs = bjw14.mo2276bs();
                    cui aq = bjw14.mo2221aq();
                    blw blw = new blw(ftz.m9622a(bjw14.f2702a));
                    bkv R = bjw14.mo2167R();
                    iqk iqk8 = bjw14.f2956p;
                    if (iqk8 == null) {
                        iqk8 = new bjv(bjw14, 73);
                        bjw14.f2956p = iqk8;
                    }
                    return new cva(cn2, bs, aq, blw, R, iog.m14218a(iqk8));
                case 49:
                    bjw bjw15 = this.f2675b;
                    return new eex(ftz.m9622a(bjw15.f2702a), bjw15.mo2210af(), bjw15.mo2212ah(), bjw15.mo2314cn());
                case 50:
                    return (Calendar) iol.m14229a((Object) Calendar.getInstance(ehe.f8281a), "Cannot return null from a non-@Nullable @Provides method");
                case 51:
                    return ftz.m9622a(this.f2675b.f2702a);
                case 52:
                    return this.f2675b.mo2314cn();
                case 53:
                    return this.f2675b.mo2209ae();
                case 54:
                    return this.f2675b.mo2228ax();
                case 55:
                    return this.f2675b.mo2217am();
                case 56:
                    return this.f2675b.mo2219ao();
                case 57:
                    return new bje(this.f2675b);
                case 58:
                    return this.f2675b.mo2190aL();
                case 59:
                    return this.f2675b.mo2187aI();
                case 60:
                    return this.f2675b.mo2194aP();
                case 61:
                    return this.f2675b.mo2196aR();
                case 62:
                    return new biy(this.f2675b);
                case 63:
                    return this.f2675b.mo2199aU();
                case 64:
                    return this.f2675b.mo2202aX();
                case 65:
                    return this.f2675b.mo2273bp();
                case 66:
                    return this.f2675b.mo2269bl();
                case 67:
                    return this.f2675b.mo2259bb();
                case 68:
                    return this.f2675b.mo2267bj();
                case 69:
                    bjw bjw16 = this.f2675b;
                    djh be = bjw16.mo2262be();
                    dil bi = bjw16.mo2266bi();
                    gus gus2 = (gus) bjw16.mo2332i();
                    iel cn3 = bjw16.mo2314cn();
                    iel f = bjw16.mo2329f();
                    iqk iqk9 = bjw16.f2954n;
                    if (iqk9 == null) {
                        iqk9 = new bjv(bjw16, 71);
                        bjw16.f2954n = iqk9;
                    }
                    return new djn(be, bi, gus2, cn3, f, iog.m14218a(iqk9));
                case 70:
                    return this.f2675b.mo2265bh();
                case 71:
                    return bjw.m2718cQ();
                case 72:
                    bjw bjw17 = this.f2675b;
                    cou cou = new cou(bjw17.mo2214aj());
                    cvm aC = bjw17.mo2181aC();
                    coq coq = new coq(bjw17.mo2180aB(), bjw17.mo2230az());
                    cwq ap = bjw17.mo2220ap();
                    cui aq2 = bjw17.mo2221aq();
                    cus aD = bjw17.mo2182aD();
                    Object obj16 = bjw17.f2955o;
                    if (obj16 instanceof iok) {
                        synchronized (obj16) {
                            obj6 = bjw17.f2955o;
                            if (obj6 instanceof iok) {
                                BuildType b = ceo.m4214b();
                                boolean d = ((gxb) bjw17.mo2183aE().f14635a.mo2097a()).mo7170a("com.google.android.apps.photosgo 17").mo7172d();
                                cjq a11 = cjr.m4407a(b);
                                a11.f4512d = d;
                                a11.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                                a11.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                                obj6 = (cjr) iol.m14229a((Object) a11.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                bjw17.f2955o = iog.m14219a(bjw17.f2955o, obj6);
                            }
                        }
                        obj16 = obj6;
                    }
                    return new cpd(cou, aC, coq, ap, aq2, aD, (cjr) obj16);
                case 73:
                    return this.f2675b.mo2220ap();
                case 74:
                    return this.f2675b.mo2282by();
                case 75:
                    return new bjb(this.f2675b);
                case 76:
                    return this.f2675b.mo2280bw();
                case 77:
                    return this.f2675b.mo2281bx();
                case 78:
                    bjw bjw18 = this.f2675b;
                    djh be2 = bjw18.mo2262be();
                    cvm aC2 = bjw18.mo2181aC();
                    div div = new div((dhl) bjw18.mo2263bf(), bjw18.mo2225au(), bjw18.mo2220ap());
                    return new djd((dhl) bjw18.mo2263bf(), be2, aC2, div, bjw18.mo2272bo(), (gus) bjw18.mo2332i(), bjw18.mo2220ap(), bjw18.mo2314cn());
                case 79:
                    bjw bjw19 = this.f2675b;
                    Object obj17 = bjw19.f2958r;
                    if (!(obj17 instanceof iok)) {
                        return obj17;
                    }
                    synchronized (obj17) {
                        Object obj18 = bjw19.f2958r;
                        if (obj18 instanceof iok) {
                            Object obj19 = bjw19.f2957q;
                            if (obj19 instanceof iok) {
                                synchronized (obj19) {
                                    obj7 = bjw19.f2957q;
                                    if (obj7 instanceof iok) {
                                        obj7 = (ahq) iol.m14229a((Object) aip.m549a(ftz.m9622a(bjw19.f2702a)), "Cannot return null from a non-@Nullable @Provides method");
                                        bjw19.f2957q = iog.m14219a(bjw19.f2957q, obj7);
                                    }
                                }
                                obj19 = obj7;
                            }
                            hsu a12 = hsu.m12067a(cvv.class, (String) iol.m14229a((Object) "com.google.android.apps.photosgo.jobs.contentupdate.ContentUpdateWorker", "Cannot return null from a non-@Nullable @Provides method"), cva.class, (String) iol.m14229a((Object) "com.google.android.apps.photosgo.jobs.background.PluggedInIdleWorker", "Cannot return null from a non-@Nullable @Provides method"));
                            bjw19.mo2329f();
                            gtc gtc2 = new gtc((ahq) obj19, a12);
                            bjw19.f2958r = iog.m14219a(bjw19.f2958r, gtc2);
                            gtc = gtc2;
                        } else {
                            gtc = obj18;
                        }
                    }
                    return gtc;
                case 80:
                    return this.f2675b.mo2192aN();
                case 81:
                    return this.f2675b.mo2233bB();
                case 82:
                    return this.f2675b.mo2234bC();
                case 83:
                    return this.f2675b.mo2198aT();
                case 84:
                    return this.f2675b.mo2201aW();
                case 85:
                    return this.f2675b.mo2226av();
                case 86:
                    return this.f2675b.mo2235bD();
                case 87:
                    return this.f2675b.mo2236bE();
                case 88:
                    bjw bjw20 = this.f2675b;
                    return dic.m6143a(bjw20.mo2226av(), bjw20.mo2264bg(), bjw20.mo2314cn());
                case 89:
                    bjw bjw21 = this.f2675b;
                    Object obj20 = bjw21.f2959s;
                    if (obj20 instanceof iok) {
                        synchronized (obj20) {
                            obj8 = bjw21.f2959s;
                            if (obj8 instanceof iok) {
                                obj8 = (cye) iol.m14229a((Object) dcm.m5893a(bjw21.mo2315co(), (CameraManager) iol.m14229a((Object) (CameraManager) ftz.m9622a(bjw21.f2702a).getSystemService("camera"), "Cannot return null from a non-@Nullable @Provides method")), "Cannot return null from a non-@Nullable @Provides method");
                                bjw21.f2959s = iog.m14219a(bjw21.f2959s, obj8);
                            }
                        }
                        obj20 = obj8;
                    }
                    return (cye) obj20;
                case 90:
                    bjw bjw22 = this.f2675b;
                    Object obj21 = bjw22.f2961u;
                    if (obj21 instanceof iok) {
                        synchronized (obj21) {
                            obj9 = bjw22.f2961u;
                            if (obj9 instanceof iok) {
                                BuildType b2 = ceo.m4214b();
                                boolean d2 = ((gxb) new iqi(bjw22.mo2160K()).f14648a.mo2097a()).mo7170a("com.google.android.apps.photosgo 32").mo7172d();
                                cjq a13 = cjr.m4407a(b2);
                                a13.f4512d = d2;
                                a13.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                                a13.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                                obj9 = (cjr) iol.m14229a((Object) a13.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                bjw22.f2961u = iog.m14219a(bjw22.f2961u, obj9);
                            }
                        }
                        obj21 = obj9;
                    }
                    return (cjr) obj21;
                case 91:
                    return this.f2675b.mo2315co();
                case 92:
                    return this.f2675b.mo2225au();
                case 93:
                    return (dee) iol.m14229a((Object) ded.f6384a, "Cannot return null from a non-@Nullable @Provides method");
                case 94:
                    return this.f2675b.mo2272bo();
                case 95:
                    return this.f2675b.mo2193aO();
                case 96:
                    return this.f2675b.mo2213ai();
                case 97:
                    return this.f2675b.mo2277bt();
                case 98:
                    bjw bjw23 = this.f2675b;
                    return hto.m12121a((Object) (fcv) iol.m14229a((Object) new ffl(bjw23.mo2314cn(), cww.m5526b(), bjw23.mo2243bL(), bjw23.mo2244bM()), "Cannot return null from a non-@Nullable @Provides method"), (Object) (fcv) iol.m14229a((Object) new ffo(bjw23.mo2314cn(), cww.m5526b(), bjw23.mo2243bL(), bjw23.mo2244bM(), hqb.ALWAYS_TRUE), "Cannot return null from a non-@Nullable @Provides method"));
                case 99:
                    return this.f2675b.mo2242bK();
                default:
                    throw new AssertionError(i);
            }
        }
    }
}
