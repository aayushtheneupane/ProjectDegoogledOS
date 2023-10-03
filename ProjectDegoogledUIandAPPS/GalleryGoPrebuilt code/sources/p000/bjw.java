package p000;

import android.app.ActivityManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.WindowManager;
import com.google.android.apps.photosgo.PhotosGo_Application;
import com.google.android.apps.photosgo.editor.nativerenderer.NativeRenderer;
import com.google.android.apps.photosgo.environment.BuildType;
import com.google.android.apps.photosgo.glide.PhotosGoNonTestGlideModule;
import com.google.android.apps.photosgo.home.HomeActivity;
import com.google.apps.tiktok.concurrent.InternalForegroundService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* renamed from: bjw */
/* compiled from: PG */
public final class bjw extends bkk {

    /* renamed from: A */
    public volatile Object f2676A = new iok();

    /* renamed from: B */
    public volatile Object f2677B = new iok();

    /* renamed from: C */
    public volatile iqk f2678C;

    /* renamed from: D */
    public volatile iqk f2679D;

    /* renamed from: E */
    public volatile iqk f2680E;

    /* renamed from: F */
    public volatile iqk f2681F;

    /* renamed from: G */
    public volatile Object f2682G = new iok();

    /* renamed from: H */
    public volatile Object f2683H = new iok();

    /* renamed from: I */
    public volatile iqk f2684I;

    /* renamed from: J */
    public volatile iqk f2685J;

    /* renamed from: K */
    public volatile Object f2686K = new iok();

    /* renamed from: L */
    public volatile Object f2687L = new iok();

    /* renamed from: M */
    public volatile Object f2688M = new iok();

    /* renamed from: N */
    public volatile Object f2689N = new iok();

    /* renamed from: O */
    public volatile Object f2690O = new iok();

    /* renamed from: P */
    public volatile Object f2691P = new iok();

    /* renamed from: Q */
    public volatile Object f2692Q = new iok();

    /* renamed from: R */
    public volatile Object f2693R = new iok();

    /* renamed from: S */
    public volatile Object f2694S = new iok();

    /* renamed from: T */
    public volatile iqk f2695T;

    /* renamed from: U */
    public volatile Object f2696U = new iok();

    /* renamed from: V */
    public volatile Object f2697V = new iok();

    /* renamed from: W */
    public volatile Object f2698W = new iok();

    /* renamed from: X */
    public volatile iqk f2699X;

    /* renamed from: Y */
    public final ioq f2700Y;

    /* renamed from: Z */
    public final ioq f2701Z;

    /* renamed from: a */
    public final fty f2702a;

    /* renamed from: aA */
    private volatile Object f2703aA = new iok();

    /* renamed from: aB */
    private volatile Object f2704aB = new iok();

    /* renamed from: aC */
    private volatile Object f2705aC = new iok();

    /* renamed from: aD */
    private volatile Object f2706aD = new iok();

    /* renamed from: aE */
    private volatile Object f2707aE = new iok();

    /* renamed from: aF */
    private volatile iqk f2708aF;

    /* renamed from: aG */
    private volatile Object f2709aG = new iok();

    /* renamed from: aH */
    private volatile Object f2710aH = new iok();

    /* renamed from: aI */
    private volatile Object f2711aI = new iok();

    /* renamed from: aJ */
    private volatile Object f2712aJ = new iok();

    /* renamed from: aK */
    private volatile Object f2713aK = new iok();

    /* renamed from: aL */
    private volatile Object f2714aL = new iok();

    /* renamed from: aM */
    private volatile Object f2715aM = new iok();

    /* renamed from: aN */
    private volatile iqk f2716aN;

    /* renamed from: aO */
    private volatile Object f2717aO = new iok();

    /* renamed from: aP */
    private volatile iqk f2718aP;

    /* renamed from: aQ */
    private volatile Object f2719aQ = new iok();

    /* renamed from: aR */
    private volatile iqk f2720aR;

    /* renamed from: aS */
    private volatile Object f2721aS = new iok();

    /* renamed from: aT */
    private volatile iqk f2722aT;

    /* renamed from: aU */
    private volatile iqk f2723aU;

    /* renamed from: aV */
    private volatile iqk f2724aV;

    /* renamed from: aW */
    private volatile Object f2725aW = new iok();

    /* renamed from: aX */
    private volatile Object f2726aX = new iok();

    /* renamed from: aY */
    private volatile iqk f2727aY;

    /* renamed from: aZ */
    private volatile iqk f2728aZ;

    /* renamed from: aa */
    public final ioq f2729aa;

    /* renamed from: ab */
    public final ioq f2730ab;

    /* renamed from: ac */
    public final ioq f2731ac;

    /* renamed from: ad */
    public final ioq f2732ad;

    /* renamed from: ae */
    public final ioq f2733ae;

    /* renamed from: af */
    public final ioq f2734af;

    /* renamed from: ag */
    public final ioq f2735ag;

    /* renamed from: ah */
    public final ioq f2736ah;

    /* renamed from: ai */
    public final ioq f2737ai;

    /* renamed from: aj */
    public final ioq f2738aj;

    /* renamed from: ak */
    public final ioq f2739ak;

    /* renamed from: al */
    public final ioq f2740al;

    /* renamed from: am */
    public final ioq f2741am;

    /* renamed from: an */
    public final ioq f2742an;

    /* renamed from: ao */
    public final ioq f2743ao;

    /* renamed from: ap */
    public final ioq f2744ap;

    /* renamed from: aq */
    public final ioq f2745aq;

    /* renamed from: ar */
    public final ioq f2746ar;

    /* renamed from: as */
    public final ioq f2747as;

    /* renamed from: at */
    public final ioq f2748at;

    /* renamed from: au */
    public final ioq f2749au;

    /* renamed from: av */
    public final ioq f2750av;

    /* renamed from: aw */
    private volatile Object f2751aw = new iok();

    /* renamed from: ax */
    private volatile hfq f2752ax;

    /* renamed from: ay */
    private volatile Object f2753ay = new iok();

    /* renamed from: az */
    private volatile Object f2754az = new iok();

    /* renamed from: b */
    public volatile Object f2755b = new iok();

    /* renamed from: bA */
    private volatile Object f2756bA = new iok();

    /* renamed from: bB */
    private volatile Object f2757bB = new iok();

    /* renamed from: bC */
    private volatile iqk f2758bC;

    /* renamed from: bD */
    private volatile Object f2759bD = new iok();

    /* renamed from: bE */
    private volatile Object f2760bE = new iok();

    /* renamed from: bF */
    private volatile Object f2761bF = new iok();

    /* renamed from: bG */
    private volatile Object f2762bG = new iok();

    /* renamed from: bH */
    private volatile iqk f2763bH;

    /* renamed from: bI */
    private volatile Object f2764bI = new iok();

    /* renamed from: bJ */
    private volatile iqk f2765bJ;

    /* renamed from: bK */
    private volatile Object f2766bK = new iok();

    /* renamed from: bL */
    private volatile Object f2767bL;

    /* renamed from: bM */
    private volatile Object f2768bM = new iok();

    /* renamed from: bN */
    private volatile Object f2769bN = new iok();

    /* renamed from: bO */
    private volatile iqk f2770bO;

    /* renamed from: bP */
    private volatile iqk f2771bP;

    /* renamed from: bQ */
    private volatile iqk f2772bQ;

    /* renamed from: bR */
    private volatile iqk f2773bR;

    /* renamed from: bS */
    private volatile Object f2774bS = new iok();

    /* renamed from: bT */
    private volatile iqk f2775bT;

    /* renamed from: bU */
    private volatile iqk f2776bU;

    /* renamed from: bV */
    private volatile Object f2777bV = new iok();

    /* renamed from: bW */
    private volatile iqk f2778bW;

    /* renamed from: bX */
    private volatile Object f2779bX = new iok();

    /* renamed from: bY */
    private volatile iqk f2780bY;

    /* renamed from: bZ */
    private volatile iqk f2781bZ;

    /* renamed from: ba */
    private volatile iqk f2782ba;

    /* renamed from: bb */
    private volatile Object f2783bb = new iok();

    /* renamed from: bc */
    private volatile iqk f2784bc;

    /* renamed from: bd */
    private volatile Object f2785bd = new iok();

    /* renamed from: be */
    private volatile iqk f2786be;

    /* renamed from: bf */
    private volatile iqk f2787bf;

    /* renamed from: bg */
    private volatile iqk f2788bg;

    /* renamed from: bh */
    private volatile Object f2789bh = new iok();

    /* renamed from: bi */
    private volatile Object f2790bi = new iok();

    /* renamed from: bj */
    private volatile Object f2791bj = new iok();

    /* renamed from: bk */
    private volatile iqk f2792bk;

    /* renamed from: bl */
    private volatile iqk f2793bl;

    /* renamed from: bm */
    private volatile iqk f2794bm;

    /* renamed from: bn */
    private volatile iqk f2795bn;

    /* renamed from: bo */
    private volatile iqk f2796bo;

    /* renamed from: bp */
    private volatile iqk f2797bp;

    /* renamed from: bq */
    private volatile iqk f2798bq;

    /* renamed from: br */
    private volatile iqk f2799br;

    /* renamed from: bs */
    private volatile Object f2800bs = new iok();

    /* renamed from: bt */
    private volatile Object f2801bt = new iok();

    /* renamed from: bu */
    private volatile Object f2802bu = new iok();

    /* renamed from: bv */
    private volatile iqk f2803bv;

    /* renamed from: bw */
    private volatile gxz f2804bw;

    /* renamed from: bx */
    private volatile iqk f2805bx;

    /* renamed from: by */
    private volatile Object f2806by = new iok();

    /* renamed from: bz */
    private volatile iqk f2807bz;

    /* renamed from: c */
    public volatile Object f2808c = new iok();

    /* renamed from: cA */
    private volatile iqk f2809cA;

    /* renamed from: cB */
    private volatile Object f2810cB = new iok();

    /* renamed from: cC */
    private volatile iqk f2811cC;

    /* renamed from: cD */
    private volatile Object f2812cD = new iok();

    /* renamed from: cE */
    private volatile Object f2813cE;

    /* renamed from: cF */
    private volatile cny f2814cF;

    /* renamed from: cG */
    private volatile Object f2815cG = new iok();

    /* renamed from: cH */
    private volatile Object f2816cH = new iok();

    /* renamed from: cI */
    private volatile Object f2817cI = new iok();

    /* renamed from: cJ */
    private volatile Object f2818cJ = new iok();

    /* renamed from: cK */
    private volatile Object f2819cK = new iok();

    /* renamed from: cL */
    private volatile Object f2820cL = new iok();

    /* renamed from: cM */
    private volatile Object f2821cM = new iok();

    /* renamed from: cN */
    private volatile Object f2822cN = new iok();

    /* renamed from: cO */
    private volatile Object f2823cO = new iok();

    /* renamed from: cP */
    private volatile Object f2824cP = new iok();

    /* renamed from: cQ */
    private volatile Object f2825cQ = new iok();

    /* renamed from: cR */
    private volatile Object f2826cR = new iok();

    /* renamed from: cS */
    private volatile Object f2827cS = new iok();

    /* renamed from: cT */
    private volatile iqk f2828cT;

    /* renamed from: cU */
    private volatile iqk f2829cU;

    /* renamed from: cV */
    private volatile iqk f2830cV;

    /* renamed from: cW */
    private volatile iqk f2831cW;

    /* renamed from: cX */
    private volatile iqk f2832cX;

    /* renamed from: cY */
    private volatile iqk f2833cY;

    /* renamed from: cZ */
    private volatile Object f2834cZ = new iok();

    /* renamed from: ca */
    private volatile iqk f2835ca;

    /* renamed from: cb */
    private volatile iqk f2836cb;

    /* renamed from: cc */
    private volatile iqk f2837cc;

    /* renamed from: cd */
    private volatile iqk f2838cd;

    /* renamed from: ce */
    private volatile iqk f2839ce;

    /* renamed from: cf */
    private volatile iqk f2840cf;

    /* renamed from: cg */
    private volatile Object f2841cg = new iok();

    /* renamed from: ch */
    private volatile Object f2842ch = new iok();

    /* renamed from: ci */
    private volatile iqk f2843ci;

    /* renamed from: cj */
    private volatile iqk f2844cj;

    /* renamed from: ck */
    private volatile iqk f2845ck;

    /* renamed from: cl */
    private volatile Object f2846cl = new iok();

    /* renamed from: cm */
    private volatile iqk f2847cm;

    /* renamed from: cn */
    private volatile Object f2848cn = new iok();

    /* renamed from: co */
    private volatile iqk f2849co;

    /* renamed from: cp */
    private volatile Object f2850cp = new iok();

    /* renamed from: cq */
    private volatile Object f2851cq = new iok();

    /* renamed from: cr */
    private volatile Object f2852cr = new iok();

    /* renamed from: cs */
    private volatile Object f2853cs = new iok();

    /* renamed from: ct */
    private volatile Object f2854ct = new iok();

    /* renamed from: cu */
    private volatile iqk f2855cu;

    /* renamed from: cv */
    private volatile Object f2856cv = new iok();

    /* renamed from: cw */
    private volatile Object f2857cw = new iok();

    /* renamed from: cx */
    private volatile iqk f2858cx;

    /* renamed from: cy */
    private volatile Object f2859cy = new iok();

    /* renamed from: cz */
    private volatile Object f2860cz = new iok();

    /* renamed from: d */
    public volatile Object f2861d = new iok();

    /* renamed from: dA */
    private volatile Object f2862dA = new iok();

    /* renamed from: dB */
    private volatile iqk f2863dB;

    /* renamed from: dC */
    private volatile Object f2864dC = new iok();

    /* renamed from: dD */
    private volatile Object f2865dD = new iok();

    /* renamed from: dE */
    private volatile hlz f2866dE;

    /* renamed from: dF */
    private volatile Object f2867dF = new iok();

    /* renamed from: dG */
    private volatile Object f2868dG = new iok();

    /* renamed from: dH */
    private volatile Object f2869dH = new iok();

    /* renamed from: dI */
    private volatile iqk f2870dI;

    /* renamed from: dJ */
    private volatile Object f2871dJ = new iok();

    /* renamed from: dK */
    private volatile iqk f2872dK;

    /* renamed from: dL */
    private volatile iqk f2873dL;

    /* renamed from: dM */
    private volatile iqk f2874dM;

    /* renamed from: dN */
    private volatile iqk f2875dN;

    /* renamed from: dO */
    private volatile iqk f2876dO;

    /* renamed from: dP */
    private volatile Object f2877dP = new iok();

    /* renamed from: dQ */
    private volatile iqk f2878dQ;

    /* renamed from: dR */
    private volatile iqk f2879dR;

    /* renamed from: dS */
    private volatile iqk f2880dS;

    /* renamed from: dT */
    private volatile iqk f2881dT;

    /* renamed from: dU */
    private volatile iqk f2882dU;

    /* renamed from: dV */
    private volatile Object f2883dV = new iok();

    /* renamed from: dW */
    private volatile Object f2884dW = new iok();

    /* renamed from: dX */
    private volatile iqk f2885dX;

    /* renamed from: dY */
    private volatile iqk f2886dY;

    /* renamed from: dZ */
    private volatile iqk f2887dZ;

    /* renamed from: da */
    private volatile iqk f2888da;

    /* renamed from: db */
    private volatile Object f2889db = new iok();

    /* renamed from: dc */
    private volatile iqk f2890dc;

    /* renamed from: dd */
    private volatile Object f2891dd = new iok();

    /* renamed from: de */
    private volatile Object f2892de = new iok();

    /* renamed from: df */
    private volatile Object f2893df = new iok();

    /* renamed from: dg */
    private volatile Object f2894dg = new iok();

    /* renamed from: dh */
    private volatile Object f2895dh = new iok();

    /* renamed from: di */
    private volatile iqk f2896di;

    /* renamed from: dj */
    private volatile iqk f2897dj;

    /* renamed from: dk */
    private volatile Object f2898dk = new iok();

    /* renamed from: dl */
    private volatile iqk f2899dl;

    /* renamed from: dm */
    private volatile Object f2900dm = new iok();

    /* renamed from: dn */
    private volatile iqk f2901dn;

    /* renamed from: do */
    private volatile Object f2902do = new iok();

    /* renamed from: dp */
    private volatile Object f2903dp = new iok();

    /* renamed from: dq */
    private volatile Object f2904dq = new iok();

    /* renamed from: dr */
    private volatile iqk f2905dr;

    /* renamed from: ds */
    private volatile iqk f2906ds;

    /* renamed from: dt */
    private volatile iqk f2907dt;

    /* renamed from: du */
    private volatile Object f2908du = new iok();

    /* renamed from: dv */
    private volatile Object f2909dv = new iok();

    /* renamed from: dw */
    private volatile iqk f2910dw;

    /* renamed from: dx */
    private volatile iqk f2911dx;

    /* renamed from: dy */
    private volatile iqk f2912dy;

    /* renamed from: dz */
    private volatile iqk f2913dz;

    /* renamed from: e */
    public volatile iqk f2914e;

    /* renamed from: eA */
    private volatile iqk f2915eA;

    /* renamed from: eB */
    private volatile Object f2916eB = new iok();

    /* renamed from: eC */
    private volatile iqk f2917eC;

    /* renamed from: eD */
    private volatile iqk f2918eD;

    /* renamed from: eE */
    private volatile iqk f2919eE;

    /* renamed from: ea */
    private volatile iqk f2920ea;

    /* renamed from: eb */
    private volatile iqk f2921eb;

    /* renamed from: ec */
    private volatile iqk f2922ec;

    /* renamed from: ed */
    private volatile iqk f2923ed;

    /* renamed from: ee */
    private volatile iqk f2924ee;

    /* renamed from: ef */
    private volatile fft f2925ef;

    /* renamed from: eg */
    private volatile Object f2926eg = new iok();

    /* renamed from: eh */
    private volatile iqk f2927eh;

    /* renamed from: ei */
    private volatile Object f2928ei = new iok();

    /* renamed from: ej */
    private volatile Object f2929ej = new iok();

    /* renamed from: ek */
    private volatile Object f2930ek = new iok();

    /* renamed from: el */
    private volatile Object f2931el = new iok();

    /* renamed from: em */
    private volatile iqk f2932em;

    /* renamed from: en */
    private volatile Object f2933en = new iok();

    /* renamed from: eo */
    private volatile Object f2934eo = new iok();

    /* renamed from: ep */
    private volatile Object f2935ep = new iok();

    /* renamed from: eq */
    private volatile Object f2936eq = new iok();

    /* renamed from: er */
    private volatile Object f2937er = new iok();

    /* renamed from: es */
    private volatile iqk f2938es;

    /* renamed from: et */
    private volatile Object f2939et = new iok();

    /* renamed from: eu */
    private volatile Object f2940eu = new iok();

    /* renamed from: ev */
    private volatile Object f2941ev = new iok();

    /* renamed from: ew */
    private volatile iqk f2942ew;

    /* renamed from: ex */
    private volatile Object f2943ex = new iok();

    /* renamed from: ey */
    private volatile iqk f2944ey;

    /* renamed from: ez */
    private volatile Object f2945ez = new iok();

    /* renamed from: f */
    public volatile hmd f2946f;

    /* renamed from: g */
    public volatile Object f2947g = new iok();

    /* renamed from: h */
    public volatile iqk f2948h;

    /* renamed from: i */
    public volatile iqk f2949i;

    /* renamed from: j */
    public volatile iqk f2950j;

    /* renamed from: k */
    public volatile iqk f2951k;

    /* renamed from: l */
    public volatile iqk f2952l;

    /* renamed from: m */
    public volatile Object f2953m = new iok();

    /* renamed from: n */
    public volatile iqk f2954n;

    /* renamed from: o */
    public volatile Object f2955o = new iok();

    /* renamed from: p */
    public volatile iqk f2956p;

    /* renamed from: q */
    public volatile Object f2957q = new iok();

    /* renamed from: r */
    public volatile Object f2958r = new iok();

    /* renamed from: s */
    public volatile Object f2959s = new iok();

    /* renamed from: t */
    public volatile iqk f2960t;

    /* renamed from: u */
    public volatile Object f2961u = new iok();

    /* renamed from: v */
    public volatile Object f2962v = new iok();

    /* renamed from: w */
    public volatile Object f2963w = new iok();

    /* renamed from: x */
    public volatile Object f2964x = new iok();

    /* renamed from: y */
    public volatile Object f2965y = new iok();

    /* renamed from: z */
    public volatile Object f2966z = new iok();

    public /* synthetic */ bjw(fty fty) {
        this.f2702a = fty;
        iqk iqk = this.f2870dI;
        if (iqk == null) {
            iqk = new bjv(this, 80);
            this.f2870dI = iqk;
        }
        this.f2700Y = ipd.m14276a(iqk);
        this.f2701Z = ipd.m14276a(mo2208ad());
        iqk iqk2 = this.f2872dK;
        if (iqk2 == null) {
            iqk2 = new bjv(this, 81);
            this.f2872dK = iqk2;
        }
        this.f2729aa = ipd.m14276a(iqk2);
        iqk iqk3 = this.f2873dL;
        if (iqk3 == null) {
            iqk3 = new bjv(this, 82);
            this.f2873dL = iqk3;
        }
        this.f2730ab = ipd.m14276a(iqk3);
        iqk iqk4 = this.f2874dM;
        if (iqk4 == null) {
            iqk4 = new bjv(this, 83);
            this.f2874dM = iqk4;
        }
        this.f2731ac = ipd.m14276a(iqk4);
        iqk iqk5 = this.f2875dN;
        if (iqk5 == null) {
            iqk5 = new bjv(this, 84);
            this.f2875dN = iqk5;
        }
        this.f2732ad = ipd.m14276a(iqk5);
        iqk iqk6 = this.f2876dO;
        if (iqk6 == null) {
            iqk6 = new bjv(this, 85);
            this.f2876dO = iqk6;
        }
        this.f2733ae = ipd.m14276a(iqk6);
        iqk iqk7 = this.f2878dQ;
        if (iqk7 == null) {
            iqk7 = new bjv(this, 86);
            this.f2878dQ = iqk7;
        }
        this.f2734af = ipd.m14276a(iqk7);
        iqk iqk8 = this.f2880dS;
        if (iqk8 == null) {
            iqk8 = new bjv(this, 87);
            this.f2880dS = iqk8;
        }
        this.f2735ag = ipd.m14276a(iqk8);
        iqk iqk9 = this.f2881dT;
        if (iqk9 == null) {
            iqk9 = new bjv(this, 90);
            this.f2881dT = iqk9;
        }
        this.f2736ah = ipd.m14276a(iqk9);
        iqk iqk10 = this.f2882dU;
        if (iqk10 == null) {
            iqk10 = new bjv(this, 91);
            this.f2882dU = iqk10;
        }
        this.f2737ai = ipd.m14276a(iqk10);
        this.f2738aj = ipd.m14276a(m2744do());
        iqk iqk11 = this.f2885dX;
        if (iqk11 == null) {
            iqk11 = new bjv(this, 92);
            this.f2885dX = iqk11;
        }
        this.f2739ak = ipd.m14276a(iqk11);
        this.f2740al = ipd.m14276a(m2744do());
        this.f2741am = ipd.m14276a(mo2151B());
        iqk iqk12 = this.f2886dY;
        if (iqk12 == null) {
            iqk12 = new bjv(this, 93);
            this.f2886dY = iqk12;
        }
        this.f2742an = ipd.m14276a(iqk12);
        this.f2743ao = ipd.m14276a(m2752dw());
        this.f2744ap = ipd.m14276a(mo2239bH());
        iqk iqk13 = this.f2920ea;
        if (iqk13 == null) {
            iqk13 = new bjv(this, 95);
            this.f2920ea = iqk13;
        }
        this.f2745aq = ipd.m14276a(iqk13);
        this.f2746ar = ipd.m14276a(mo2188aJ());
        iqk iqk14 = this.f2921eb;
        if (iqk14 == null) {
            iqk14 = new bjv(this, 96);
            this.f2921eb = iqk14;
        }
        this.f2747as = ipd.m14276a(iqk14);
        this.f2748at = ipd.m14276a(mo2240bI());
        iqk iqk15 = this.f2918eD;
        if (iqk15 == null) {
            iqk15 = new bjv(this, 114);
            this.f2918eD = iqk15;
        }
        this.f2749au = ipd.m14276a(iqk15);
        iqk iqk16 = this.f2919eE;
        if (iqk16 == null) {
            iqk16 = new bjv(this, 115);
            this.f2919eE = iqk16;
        }
        this.f2750av = ipd.m14276a(iqk16);
    }

    /* renamed from: cO */
    public final hpy mo2299cO() {
        return hph.f13219a;
    }

    /* renamed from: cv */
    public final ioa mo2322cv() {
        return new bjh(this);
    }

    /* renamed from: v */
    public final gld mo2345v() {
        Object H = mo2157H();
        mo2342s();
        mo2343t();
        if (this.f2786be == null) {
            this.f2786be = new bjv(this, 15);
        }
        return new gld((glp) H);
    }

    /* renamed from: s */
    public final glm mo2342s() {
        return new glm(m2735df());
    }

    /* renamed from: u */
    public final Object mo2344u() {
        return new glo((glp) mo2157H(), m2735df());
    }

    /* renamed from: H */
    public final Object mo2157H() {
        Object obj;
        Object obj2;
        ftz.m9622a(this.f2702a);
        Object obj3 = this.f2703aA;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj = this.f2703aA;
                if (obj instanceof iok) {
                    iel cn = mo2314cn();
                    Context a = ftz.m9622a(this.f2702a);
                    iel cn2 = mo2314cn();
                    Object obj4 = this.f2754az;
                    if (obj4 instanceof iok) {
                        synchronized (obj4) {
                            obj2 = this.f2754az;
                            if (obj2 instanceof iok) {
                                hfu g = hfv.m11397g();
                                g.f12674a = "TikTokAccountStoreMigration";
                                g.mo7383a(gni.f11680b);
                                obj2 = (fzx) iol.m14229a((Object) mo2328e().mo7385a((hfv) iol.m14229a((Object) g.mo7382a(), "Cannot return null from a non-@Nullable @Provides method")), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2754az = iog.m14219a(this.f2754az, obj2);
                            }
                        }
                        obj4 = obj2;
                    }
                    gne gne = new gne(cn, new gnb(a, cn2, (fzx) obj4));
                    hfu g2 = hfv.m11397g();
                    g2.f12674a = "AccountData";
                    g2.mo7383a(gng.f11674d);
                    gmy gmy = new gmy(gne.f11672b, "accountmanager", gne);
                    if (g2.f12676c == null) {
                        g2.f12676c = hso.m12048j();
                    }
                    g2.f12676c.mo7908c(gmy);
                    obj = (fzx) iol.m14229a((Object) mo2328e().mo7385a((hfv) iol.m14229a((Object) g2.mo7382a(), "Cannot return null from a non-@Nullable @Provides method")), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2703aA = iog.m14219a(this.f2703aA, obj);
                }
            }
            obj3 = obj;
        }
        fzx fzx = (fzx) obj3;
        iel f = mo2329f();
        if (this.f2708aF == null) {
            this.f2708aF = new bjv(this, 2);
        }
        if (this.f2727aY == null) {
            this.f2727aY = new bjv(this, 3);
        }
        iqk iqk = this.f2728aZ;
        if (iqk == null) {
            iqk = new bjv(this, 10);
            this.f2728aZ = iqk;
        }
        iqk iqk2 = this.f2782ba;
        if (iqk2 == null) {
            iqk2 = new bjv(this, 11);
            this.f2782ba = iqk2;
        }
        if (this.f2805bx == null) {
            this.f2805bx = new bjv(this, 12);
        }
        return new glp(fzx, f, iqk, iqk2);
    }

    /* renamed from: df */
    private final glx m2735df() {
        Object obj;
        bjv bjv;
        Object obj2;
        Object obj3 = this.f2785bd;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj = this.f2785bd;
                if (obj instanceof iok) {
                    ioh ioh = iom.f14602a;
                    Object H = mo2157H();
                    Object obj4 = this.f2783bb;
                    if (obj4 instanceof iok) {
                        synchronized (obj4) {
                            obj2 = this.f2783bb;
                            if (obj2 instanceof iok) {
                                hfu g = hfv.m11397g();
                                g.f12674a = "AccountSyncData";
                                g.mo7383a(glz.f11602c);
                                obj2 = (fzx) iol.m14229a((Object) mo2328e().mo7385a((hfv) iol.m14229a((Object) g.mo7382a(), "Cannot return null from a non-@Nullable @Provides method")), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2783bb = iog.m14219a(this.f2783bb, obj2);
                            }
                        }
                        obj4 = obj2;
                    }
                    fzx fzx = (fzx) obj4;
                    exm b = exn.m8328b();
                    iqk iqk = this.f2784bc;
                    if (iqk == null) {
                        bjv bjv2 = new bjv(this, 14);
                        this.f2784bc = bjv2;
                        bjv = bjv2;
                    } else {
                        bjv = iqk;
                    }
                    glx glx = new glx(ioh, (glp) H, fzx, b, bjv, mo2329f());
                    this.f2785bd = iog.m14219a(this.f2785bd, glx);
                    obj = glx;
                }
            }
            obj3 = obj;
        }
        return (glx) obj3;
    }

    /* renamed from: cV */
    private final ActivityManager m2722cV() {
        return (ActivityManager) iol.m14229a((Object) (ActivityManager) ftz.m9622a(this.f2702a).getSystemService("activity"), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: l */
    public final goo mo2335l() {
        Object obj;
        Object obj2 = this.f2713aK;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2713aK;
                if (obj instanceof iok) {
                    m2722cV();
                    mo2325cy();
                    goo goo = new goo(ftz.m9622a(this.f2702a), (PowerManager) iol.m14229a((Object) (PowerManager) ftz.m9622a(this.f2702a).getSystemService("power"), "Cannot return null from a non-@Nullable @Provides method"), (NotificationManager) iol.m14229a((Object) (NotificationManager) ftz.m9622a(this.f2702a).getSystemService("notification"), "Cannot return null from a non-@Nullable @Provides method"), (iel) mo2333j(), (gqw) m2723cW(), mo2314cn(), mo2330g());
                    this.f2713aK = iog.m14219a(this.f2713aK, goo);
                    obj = goo;
                }
            }
            obj2 = obj;
        }
        return (goo) obj2;
    }

    /* renamed from: bg */
    public final dhc mo2264bg() {
        return dhd.m6105a(mo2225au(), mo2226av(), (dhl) mo2263bf());
    }

    /* renamed from: bL */
    public final fft mo2243bL() {
        fft fft = this.f2925ef;
        if (fft != null) {
            return fft;
        }
        iqk iqk = this.f2923ed;
        if (iqk == null) {
            iqk = new bjv(this, 99);
            this.f2923ed = iqk;
        }
        iqk iqk2 = this.f2924ee;
        if (iqk2 == null) {
            iqk2 = new bjv(this, 100);
            this.f2924ee = iqk2;
        }
        fft fft2 = (fft) iol.m14229a((Object) new fft(hsu.m12067a(-1, iqk, 200000019, iqk2)), "Cannot return null from a non-@Nullable @Provides method");
        this.f2925ef = fft2;
        return fft2;
    }

    /* renamed from: k */
    public final Object mo2334k() {
        Object obj;
        Object obj2 = this.f2711aI;
        if (!(obj2 instanceof iok)) {
            return obj2;
        }
        synchronized (obj2) {
            obj = this.f2711aI;
            if (obj instanceof iok) {
                obj = new got();
                this.f2711aI = iog.m14219a(this.f2711aI, obj);
            }
        }
        return obj;
    }

    /* renamed from: cC */
    public final hpy mo2287cC() {
        Object obj;
        Object obj2 = this.f2869dH;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2869dH;
                if (obj instanceof iok) {
                    PhotosGoNonTestGlideModule photosGoNonTestGlideModule = new PhotosGoNonTestGlideModule((avl) iol.m14229a((Object) new avl(ftz.m9622a(this.f2702a), "glide_cache", 15728640), "Cannot return null from a non-@Nullable @Provides method"));
                    this.f2869dH = iog.m14219a(this.f2869dH, photosGoNonTestGlideModule);
                    obj = photosGoNonTestGlideModule;
                }
            }
            obj2 = obj;
        }
        return hpy.m11893b((PhotosGoNonTestGlideModule) obj2);
    }

    /* renamed from: ad */
    public final iqk mo2208ad() {
        iqk iqk = this.f2844cj;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 51);
        this.f2844cj = bjv;
        return bjv;
    }

    /* renamed from: ar */
    public final hdt mo2222ar() {
        Object obj;
        Object obj2 = this.f2860cz;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2860cz;
                if (obj instanceof iok) {
                    obj = (hdt) iol.m14229a((Object) new hdt(new hdw(ftz.m9622a(this.f2702a)), hdx.f12561a, false), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2860cz = iog.m14219a(this.f2860cz, obj);
                }
            }
            obj2 = obj;
        }
        return (hdt) obj2;
    }

    /* renamed from: bC */
    public final ice mo2234bC() {
        fzx dy = m2754dy();
        dy.getClass();
        return (ice) iol.m14229a((Object) new cyv(dy), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: av */
    public final gtt mo2226av() {
        ContentResolver au = mo2225au();
        Object i = mo2332i();
        iel cn = mo2314cn();
        exn.m8328b();
        mo2324cx();
        iel iel = (iel) mo2333j();
        return gtu.m10790a(au, i, cn);
    }

    /* renamed from: bB */
    public final icf mo2233bB() {
        return (icf) iol.m14229a((Object) new cyw(m2754dy()), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: dp */
    private final hgf m2745dp() {
        iqk ad = mo2208ad();
        iqk iqk = m2744do();
        iqk iqk2 = this.f2847cm;
        if (iqk2 == null) {
            iqk2 = new bjv(this, 53);
            this.f2847cm = iqk2;
        }
        return new hgf(ad, iqk, iqk2);
    }

    /* renamed from: cn */
    public final iel mo2314cn() {
        Object obj;
        Object obj2;
        Object obj3 = this.f2865dD;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj = this.f2865dD;
                if (obj instanceof iok) {
                    Object obj4 = this.f2864dC;
                    if (obj4 instanceof iok) {
                        synchronized (obj4) {
                            obj2 = this.f2864dC;
                            if (obj2 instanceof iok) {
                                obj2 = (iel) iol.m14229a((Object) gpt.m10595a(gqb.m10619a("BG Thread", 4, 10, hkc.m11619b().mo7506b()), m2721cU()), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2864dC = iog.m14219a(this.f2864dC, obj2);
                            }
                        }
                        obj4 = obj2;
                    }
                    obj = (iel) iol.m14229a((Object) gpw.m10607a((iel) obj4), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2865dD = iog.m14219a(this.f2865dD, obj);
                }
            }
            obj3 = obj;
        }
        return (iel) obj3;
    }

    /* renamed from: do */
    private final iqk m2744do() {
        iqk iqk = this.f2845ck;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 52);
        this.f2845ck = bjv;
        return bjv;
    }

    /* renamed from: aE */
    public final ipw mo2183aE() {
        return new ipw(mo2160K());
    }

    /* renamed from: cL */
    public final iek mo2296cL() {
        return mo2314cn();
    }

    /* renamed from: m */
    public final hhf mo2336m() {
        return (hhf) iol.m14229a((Object) m2726cZ(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: n */
    public final hhf mo2337n() {
        return (hhf) iol.m14229a((Object) m2731db(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: o */
    public final hhf mo2338o() {
        return (hhf) iol.m14229a((Object) m2732dc(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: az */
    public final cnr mo2230az() {
        return new cnr(mo2222ar(), mo2314cn());
    }

    /* renamed from: g */
    public final iel mo2330g() {
        Object obj;
        Object obj2 = this.f2707aE;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2707aE;
                if (obj instanceof iok) {
                    obj = (iel) iol.m14229a((Object) gpt.m10595a(ife.m12824a(Executors.newCachedThreadPool(gqb.m10620a("Blocking Thread", 11, (hpy) hph.f13219a))), m2721cU()), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2707aE = iog.m14219a(this.f2707aE, obj);
                }
            }
            obj2 = obj;
        }
        return (iel) iol.m14229a((Object) gpw.m10607a((iel) obj2), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bv */
    public final Object mo2279bv() {
        return new bmc(mo2214aj());
    }

    /* renamed from: bm */
    public final cjr mo2270bm() {
        Object obj;
        Object obj2 = this.f2902do;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2902do;
                if (obj instanceof iok) {
                    cjq a = cjr.m4407a(ceo.m4214b());
                    a.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST};
                    a.f4511c = new BuildType[]{BuildType.DEV};
                    obj = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2902do = iog.m14219a(this.f2902do, obj);
                }
            }
            obj2 = obj;
        }
        return (cjr) obj2;
    }

    /* renamed from: bw */
    public final bmp mo2280bw() {
        return new bmp((bmc) mo2279bv(), mo2181aC(), mo2272bo(), (gus) mo2332i(), mo2314cn());
    }

    /* renamed from: bx */
    public final bmy mo2281bx() {
        return new bmy((bmc) mo2279bv(), mo2181aC(), mo2272bo(), (gus) mo2332i(), mo2314cn());
    }

    /* renamed from: bh */
    public final Object mo2265bh() {
        Object obj;
        Object obj2 = this.f2895dh;
        if (!(obj2 instanceof iok)) {
            return obj2;
        }
        synchronized (obj2) {
            obj = this.f2895dh;
            if (obj instanceof iok) {
                dih dih = new dih(ftz.m9622a(this.f2702a), new dio((dhl) mo2263bf(), mo2264bg(), mo2225au(), mo2314cn(), mo2220ap()));
                this.f2895dh = iog.m14219a(this.f2895dh, dih);
                obj = dih;
            }
        }
        return obj;
    }

    /* renamed from: aH */
    public final bnq mo2186aH() {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        Object obj10;
        Object obj11 = this.f2827cS;
        if (obj11 instanceof iok) {
            synchronized (obj11) {
                obj = this.f2827cS;
                if (obj instanceof iok) {
                    bnk bnk = new bnk(mo2214aj());
                    gus gus = (gus) mo2332i();
                    Object obj12 = this.f2818cJ;
                    if (obj12 instanceof iok) {
                        synchronized (obj12) {
                            obj10 = this.f2818cJ;
                            if (obj10 instanceof iok) {
                                BuildType b = ceo.m4214b();
                                boolean d = ((gxb) mo2185aG().f14644a.mo2097a()).mo7170a("com.google.android.apps.photosgo 25").mo7172d();
                                cjq a = cjr.m4407a(b);
                                a.f4512d = d;
                                a.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                                a.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                                obj10 = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2818cJ = iog.m14219a(this.f2818cJ, obj10);
                            }
                        }
                        obj12 = obj10;
                    }
                    cjr cjr = (cjr) obj12;
                    Object obj13 = this.f2819cK;
                    if (obj13 instanceof iok) {
                        synchronized (obj13) {
                            obj9 = this.f2819cK;
                            if (obj9 instanceof iok) {
                                BuildType b2 = ceo.m4214b();
                                boolean d2 = ((gxb) mo2185aG().f14644a.mo2097a()).mo7170a("com.google.android.apps.photosgo 24").mo7172d();
                                cjq a2 = cjr.m4407a(b2);
                                a2.f4512d = d2;
                                a2.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                                a2.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                                obj9 = (cjr) iol.m14229a((Object) a2.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2819cK = iog.m14219a(this.f2819cK, obj9);
                            }
                        }
                        obj13 = obj9;
                    }
                    cjr cjr2 = (cjr) obj13;
                    Object obj14 = this.f2820cL;
                    if (obj14 instanceof iok) {
                        synchronized (obj14) {
                            obj8 = this.f2820cL;
                            if (obj8 instanceof iok) {
                                BuildType b3 = ceo.m4214b();
                                boolean d3 = ((gxb) mo2185aG().f14644a.mo2097a()).mo7170a("com.google.android.apps.photosgo 23").mo7172d();
                                cjq a3 = cjr.m4407a(b3);
                                a3.f4512d = d3;
                                a3.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                                a3.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                                obj8 = (cjr) iol.m14229a((Object) a3.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2820cL = iog.m14219a(this.f2820cL, obj8);
                            }
                        }
                        obj14 = obj8;
                    }
                    cjr cjr3 = (cjr) obj14;
                    Object obj15 = this.f2821cM;
                    if (obj15 instanceof iok) {
                        synchronized (obj15) {
                            obj7 = this.f2821cM;
                            if (obj7 instanceof iok) {
                                BuildType b4 = ceo.m4214b();
                                boolean d4 = ((gxb) mo2185aG().f14644a.mo2097a()).mo7170a("com.google.android.apps.photosgo 30").mo7172d();
                                cjq a4 = cjr.m4407a(b4);
                                a4.f4512d = d4;
                                a4.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                                a4.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                                obj7 = (cjr) iol.m14229a((Object) a4.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2821cM = iog.m14219a(this.f2821cM, obj7);
                            }
                        }
                        obj15 = obj7;
                    }
                    cjr cjr4 = (cjr) obj15;
                    Object obj16 = this.f2822cN;
                    if (obj16 instanceof iok) {
                        synchronized (obj16) {
                            obj6 = this.f2822cN;
                            if (obj6 instanceof iok) {
                                BuildType b5 = ceo.m4214b();
                                boolean d5 = ((gxb) mo2185aG().f14644a.mo2097a()).mo7170a("com.google.android.apps.photosgo 22").mo7172d();
                                cjq a5 = cjr.m4407a(b5);
                                a5.f4512d = d5;
                                a5.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                                a5.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                                obj6 = (cjr) iol.m14229a((Object) a5.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2822cN = iog.m14219a(this.f2822cN, obj6);
                            }
                        }
                        obj16 = obj6;
                    }
                    cjr cjr5 = (cjr) obj16;
                    Object obj17 = this.f2823cO;
                    if (obj17 instanceof iok) {
                        synchronized (obj17) {
                            obj5 = this.f2823cO;
                            if (obj5 instanceof iok) {
                                BuildType b6 = ceo.m4214b();
                                boolean d6 = ((gxb) mo2185aG().f14644a.mo2097a()).mo7170a("com.google.android.apps.photosgo 20").mo7172d();
                                cjq a6 = cjr.m4407a(b6);
                                a6.f4512d = d6;
                                a6.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                                a6.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                                obj5 = (cjr) iol.m14229a((Object) a6.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2823cO = iog.m14219a(this.f2823cO, obj5);
                            }
                        }
                        obj17 = obj5;
                    }
                    cjr cjr6 = (cjr) obj17;
                    Object obj18 = this.f2824cP;
                    if (obj18 instanceof iok) {
                        synchronized (obj18) {
                            obj4 = this.f2824cP;
                            if (obj4 instanceof iok) {
                                BuildType b7 = ceo.m4214b();
                                boolean d7 = ((gxb) mo2185aG().f14644a.mo2097a()).mo7170a("com.google.android.apps.photosgo 21").mo7172d();
                                cjq a7 = cjr.m4407a(b7);
                                a7.f4512d = d7;
                                a7.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                                a7.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                                obj4 = (cjr) iol.m14229a((Object) a7.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2824cP = iog.m14219a(this.f2824cP, obj4);
                            }
                        }
                        obj18 = obj4;
                    }
                    cjr cjr7 = (cjr) obj18;
                    Object obj19 = this.f2825cQ;
                    if (obj19 instanceof iok) {
                        synchronized (obj19) {
                            obj3 = this.f2825cQ;
                            if (obj3 instanceof iok) {
                                BuildType b8 = ceo.m4214b();
                                boolean d8 = ((gxb) mo2185aG().f14644a.mo2097a()).mo7170a("com.google.android.apps.photosgo 19").mo7172d();
                                cjq a8 = cjr.m4407a(b8);
                                a8.f4512d = d8;
                                a8.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                                a8.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                                obj3 = (cjr) iol.m14229a((Object) a8.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2825cQ = iog.m14219a(this.f2825cQ, obj3);
                            }
                        }
                        obj19 = obj3;
                    }
                    cjr cjr8 = (cjr) obj19;
                    Object obj20 = this.f2826cR;
                    if (obj20 instanceof iok) {
                        synchronized (obj20) {
                            obj2 = this.f2826cR;
                            if (obj2 instanceof iok) {
                                BuildType b9 = ceo.m4214b();
                                boolean d9 = ((gxb) mo2185aG().f14644a.mo2097a()).mo7170a("com.google.android.apps.photosgo 18").mo7172d();
                                cjq a9 = cjr.m4407a(b9);
                                a9.f4512d = d9;
                                a9.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                                a9.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                                obj2 = (cjr) iol.m14229a((Object) a9.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2826cR = iog.m14219a(this.f2826cR, obj2);
                            }
                        }
                        obj20 = obj2;
                    }
                    obj = r3;
                    bnq bnq = new bnq(bnk, gus, cjr, cjr2, cjr3, cjr4, cjr5, cjr6, cjr7, cjr8, (cjr) obj20, mo2314cn(), mo2189aK());
                    this.f2827cS = iog.m14219a(this.f2827cS, obj);
                }
            }
            obj11 = obj;
        }
        return (bnq) obj11;
    }

    /* renamed from: bM */
    public final fdd mo2244bM() {
        Object obj;
        Object obj2 = this.f2926eg;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2926eg;
                if (obj instanceof iok) {
                    obj = (fdd) iol.m14229a((Object) new fdd(ftz.m9622a(this.f2702a), mo2314cn(), mo2162M()), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2926eg = iog.m14219a(this.f2926eg, obj);
                }
            }
            obj2 = obj;
        }
        return (fdd) obj2;
    }

    /* renamed from: dj */
    private final ipx m2739dj() {
        return new ipx(mo2160K());
    }

    /* renamed from: L */
    public final ifr mo2161L() {
        Object obj;
        Object obj2;
        Object obj3 = this.f2757bB;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj = this.f2757bB;
                if (obj instanceof iok) {
                    Object obj4 = this.f2756bA;
                    if (obj4 instanceof iok) {
                        synchronized (obj4) {
                            obj2 = this.f2756bA;
                            if (obj2 instanceof iok) {
                                BuildType b = ceo.m4214b();
                                boolean d = ((gxb) m2739dj().f14636a.mo2097a()).mo7170a("com.google.android.apps.photosgo 14").mo7172d();
                                cjq a = cjr.m4407a(b);
                                a.f4512d = d;
                                a.f4510b = new BuildType[]{BuildType.DOGFOOD, BuildType.RELEASE};
                                a.f4511c = new BuildType[0];
                                obj2 = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2756bA = iog.m14219a(this.f2756bA, obj2);
                            }
                        }
                        obj4 = obj2;
                    }
                    obj = (ifr) iol.m14229a((Object) new cwh((cjr) obj4, ((Long) ((gxb) m2739dj().f14636a.mo2097a()).mo7170a("com.google.android.apps.photosgo 15").mo7164a()).longValue()), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2757bB = iog.m14219a(this.f2757bB, obj);
                }
            }
            obj3 = obj;
        }
        return (ifr) obj3;
    }

    /* renamed from: cA */
    public final hde mo2285cA() {
        return new hde(ftz.m9622a(this.f2702a), mo2162M(), m2740dk(), mo2314cn());
    }

    /* renamed from: B */
    public final iqk mo2151B() {
        iqk iqk = this.f2796bo;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 21);
        this.f2796bo = bjv;
        return bjv;
    }

    /* renamed from: aW */
    public final cie mo2201aW() {
        Object obj;
        Object obj2 = this.f2834cZ;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2834cZ;
                if (obj instanceof iok) {
                    hfu g = hfv.m11397g();
                    g.f12674a = "ClusterStore";
                    g.mo7383a(cig.f4455d);
                    obj = (fzx) iol.m14229a((Object) mo2328e().mo7385a((hfv) iol.m14229a((Object) g.mo7382a(), "Cannot return null from a non-@Nullable @Provides method")), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2834cZ = iog.m14219a(this.f2834cZ, obj);
                }
            }
            obj2 = obj;
        }
        return new cie((fzx) obj2);
    }

    /* renamed from: ak */
    public final Object mo2215ak() {
        return new bow(mo2214aj());
    }

    /* renamed from: cz */
    public final gxu mo2326cz() {
        return new gxu(mo2347x(), mo2155F());
    }

    /* renamed from: F */
    public final gxz mo2155F() {
        gxz gxz = this.f2804bw;
        if (gxz != null) {
            return gxz;
        }
        iqk iqk = this.f2787bf;
        if (iqk == null) {
            iqk = new bjv(this, 13);
            this.f2787bf = iqk;
        }
        hpy b = hpy.m11893b(new gxg(iog.m14218a(iqk), iog.m14218a(m2736dg()), ftz.m9622a(this.f2702a)));
        gzy y = mo2348y();
        iqk iqk2 = this.f2803bv;
        if (iqk2 == null) {
            iqk2 = new bjv(this, 16);
            this.f2803bv = iqk2;
        }
        Map x = mo2347x();
        ftz.m9622a(this.f2702a);
        gxz gxz2 = new gxz(b, y, iqk2, x);
        this.f2804bw = gxz2;
        return gxz2;
    }

    /* renamed from: da */
    private final ConnectivityManager m2730da() {
        return (ConnectivityManager) iol.m14229a((Object) (ConnectivityManager) ftz.m9622a(this.f2702a).getSystemService("connectivity"), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: D */
    public final gyd mo2153D() {
        bjv bjv;
        bjv bjv2;
        bjv bjv3;
        bjv bjv4;
        bjv bjv5;
        bjv bjv6;
        iqk iqk = this.f2792bk;
        if (iqk == null) {
            bjv bjv7 = new bjv(this, 17);
            this.f2792bk = bjv7;
            bjv = bjv7;
        } else {
            bjv = iqk;
        }
        iqk iqk2 = this.f2793bl;
        if (iqk2 == null) {
            bjv bjv8 = new bjv(this, 18);
            this.f2793bl = bjv8;
            bjv2 = bjv8;
        } else {
            bjv2 = iqk2;
        }
        iqk iqk3 = this.f2794bm;
        if (iqk3 == null) {
            bjv bjv9 = new bjv(this, 19);
            this.f2794bm = bjv9;
            bjv3 = bjv9;
        } else {
            bjv3 = iqk3;
        }
        iqk iqk4 = this.f2795bn;
        if (iqk4 == null) {
            bjv bjv10 = new bjv(this, 20);
            this.f2795bn = bjv10;
            bjv4 = bjv10;
        } else {
            bjv4 = iqk4;
        }
        iqk B = mo2151B();
        iqk iqk5 = this.f2797bp;
        if (iqk5 == null) {
            bjv bjv11 = new bjv(this, 22);
            this.f2797bp = bjv11;
            bjv5 = bjv11;
        } else {
            bjv5 = iqk5;
        }
        iqk iqk6 = this.f2798bq;
        if (iqk6 == null) {
            bjv bjv12 = new bjv(this, 23);
            this.f2798bq = bjv12;
            bjv6 = bjv12;
        } else {
            bjv6 = iqk6;
        }
        return new gyd(bjv, bjv2, bjv3, bjv4, B, bjv5, bjv6, mo2152C());
    }

    /* renamed from: au */
    public final ContentResolver mo2225au() {
        return (ContentResolver) iol.m14229a((Object) ftz.m9622a(this.f2702a).getContentResolver(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bA */
    public final Object mo2232bA() {
        return new cvx(mo2314cn(), iog.m14218a(m2753dx()));
    }

    /* renamed from: ct */
    public final fad mo2320ct() {
        return (fad) iol.m14229a((Object) new faq(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: t */
    public final guj mo2343t() {
        gus gus = (gus) mo2332i();
        return new guj(exn.m8328b());
    }

    /* renamed from: bU */
    public final bqq mo2252bU() {
        Object obj;
        Object obj2 = this.f2935ep;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2935ep;
                if (obj instanceof iok) {
                    bqq bqq = new bqq(mo2343t(), (gus) mo2332i());
                    this.f2935ep = iog.m14219a(this.f2935ep, bqq);
                    obj = bqq;
                }
            }
            obj2 = obj;
        }
        return (bqq) obj2;
    }

    /* renamed from: A */
    public final gyj mo2150A() {
        return new gyj(mo2349z());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: bsz} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: bsz} */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: bc */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.bsz mo2260bc() {
        /*
            r9 = this;
            java.lang.Object r0 = r9.f2891dd
            boolean r1 = r0 instanceof p000.iok
            if (r1 == 0) goto L_0x004c
            monitor-enter(r0)
            java.lang.Object r1 = r9.f2891dd     // Catch:{ all -> 0x0049 }
            boolean r2 = r1 instanceof p000.iok     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0045
            fty r1 = r9.f2702a     // Catch:{ all -> 0x0049 }
            android.content.Context r3 = p000.ftz.m9622a(r1)     // Catch:{ all -> 0x0049 }
            cxo r4 = r9.mo2271bn()     // Catch:{ all -> 0x0049 }
            java.lang.Object r1 = r9.mo2332i()     // Catch:{ all -> 0x0049 }
            r5 = r1
            gus r5 = (p000.gus) r5     // Catch:{ all -> 0x0049 }
            iel r6 = r9.mo2314cn()     // Catch:{ all -> 0x0049 }
            iel r7 = r9.mo2329f()     // Catch:{ all -> 0x0049 }
            iqk r1 = r9.f2890dc     // Catch:{ all -> 0x0049 }
            if (r1 != 0) goto L_0x0033
            bjv r1 = new bjv     // Catch:{ all -> 0x0049 }
            r2 = 67
            r1.<init>(r9, r2)     // Catch:{ all -> 0x0049 }
            r9.f2890dc = r1     // Catch:{ all -> 0x0049 }
        L_0x0033:
            inw r8 = p000.iog.m14218a(r1)     // Catch:{ all -> 0x0049 }
            bsz r1 = new bsz     // Catch:{ all -> 0x0049 }
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0049 }
            java.lang.Object r2 = r9.f2891dd     // Catch:{ all -> 0x0049 }
            java.lang.Object r2 = p000.iog.m14219a(r2, r1)     // Catch:{ all -> 0x0049 }
            r9.f2891dd = r2     // Catch:{ all -> 0x0049 }
        L_0x0045:
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            r0 = r1
            goto L_0x004d
        L_0x0049:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            throw r1
        L_0x004c:
        L_0x004d:
            bsz r0 = (p000.bsz) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bjw.mo2260bc():bsz");
    }

    /* renamed from: E */
    public final gyt mo2154E() {
        Object obj;
        Object obj2 = this.f2801bt;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2801bt;
                if (obj instanceof iok) {
                    gyj A = mo2150A();
                    gyd D = mo2153D();
                    hfq c = mo2284c();
                    iel g = mo2330g();
                    hsu hsu = hvb.f13454a;
                    gxz F = mo2155F();
                    Set dh = m2737dh();
                    gww gww = gww.UI_DEVICE;
                    hag hag = new hag(g, dh, c);
                    gzm gzm = hah.f12406a;
                    gzm gzm2 = hai.f12407a;
                    A.getClass();
                    gww gww2 = gww;
                    hag hag2 = hag;
                    obj = (gyc) iol.m14229a((Object) D.mo7209a(gww2, hag2, (iel) mo2333j(), hsu, gzm, gzm2, new haj(A), new hak(F)), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2801bt = iog.m14219a(this.f2801bt, obj);
                }
            }
            obj2 = obj;
        }
        return new gyt((gyc) obj2, m2738di(), mo2347x(), mo2349z());
    }

    /* renamed from: dh */
    private final Set m2737dh() {
        Object obj;
        Object obj2 = this.f2800bs;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2800bs;
                if (obj instanceof iok) {
                    hsu hsu = hvb.f13454a;
                    gzy y = mo2348y();
                    htm c = hto.m12129c(((hvb) hsu).f13455b);
                    for (String a : hsu.keySet()) {
                        c.mo7874b(y.mo7210a(a));
                    }
                    obj = (Set) iol.m14229a((Object) c.mo7993a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2800bs = iog.m14219a(this.f2800bs, obj);
                }
            }
            obj2 = obj;
        }
        return (Set) obj2;
    }

    /* renamed from: V */
    public final boolean mo2171V() {
        Object obj;
        Object obj2 = this.f2774bS;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2774bS;
                if (obj instanceof iok) {
                    fqp z = mo2349z();
                    obj = Boolean.valueOf(z.f10284b.mo5389a(z.f10289g) >= 12200000);
                    this.f2774bS = iog.m14219a(this.f2774bS, obj);
                }
            }
            obj2 = obj;
        }
        return ((Boolean) obj2).booleanValue();
    }

    /* renamed from: dz */
    private final fcq m2755dz() {
        return (fcq) iol.m14229a((Object) new fcp(mo2314cn(), hto.m12120a((Object) (fcq) iol.m14229a((Object) new fef(), "Cannot return null from a non-@Nullable @Provides method"))), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: dA */
    private final fcu m2727dA() {
        Object obj;
        bjv bjv;
        Object obj2 = this.f2928ei;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2928ei;
                if (obj instanceof iok) {
                    fcq dz = m2755dz();
                    iel cn = mo2314cn();
                    iqk iqk = this.f2927eh;
                    if (iqk == null) {
                        bjv bjv2 = new bjv(this, 98);
                        this.f2927eh = bjv2;
                        bjv = bjv2;
                    } else {
                        bjv = iqk;
                    }
                    obj = (fcu) iol.m14229a((Object) new fcu(cn, bjv, hto.m12120a((Object) (fcw) iol.m14229a((Object) ffx.m8759b(), "Cannot return null from a non-@Nullable @Provides method")), dz, exn.m8328b()), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2928ei = iog.m14219a(this.f2928ei, obj);
                }
            }
            obj2 = obj;
        }
        return (fcu) obj2;
    }

    /* renamed from: cN */
    public final hos mo2298cN() {
        return hot.m11849a(mo2324cx(), iom.f14602a, iom.f14602a);
    }

    /* renamed from: bR */
    public final iij mo2249bR() {
        Object obj;
        Object obj2 = this.f2934eo;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2934eo;
                if (obj instanceof iok) {
                    hlj a = hnb.m11765a("provideExtensionRegistry");
                    try {
                        iij b = iij.m13502b();
                        if (a != null) {
                            a.close();
                        }
                        obj = (iij) iol.m14229a((Object) b, "Cannot return null from a non-@Nullable @Provides method");
                        this.f2934eo = iog.m14219a(this.f2934eo, obj);
                    } catch (Throwable th) {
                        ifn.m12935a(th, th);
                    }
                }
            }
            obj2 = obj;
        }
        return (iij) obj2;
        throw th;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: bjv} */
    /* JADX WARNING: type inference failed for: r4v0, types: [java.lang.Object, imp] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: aR */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.cfu mo2196aR() {
        /*
            r11 = this;
            iqk r0 = r11.f2831cW
            if (r0 == 0) goto L_0x0006
            r3 = r0
            goto L_0x0010
        L_0x0006:
            bjv r0 = new bjv
            r1 = 62
            r0.<init>(r11, r1)
            r11.f2831cW = r0
            r3 = r0
        L_0x0010:
            java.lang.Object r4 = r11.mo2224at()
            cip r5 = r11.mo2193aO()
            iel r6 = r11.mo2314cn()
            cwq r7 = r11.mo2220ap()
            cui r8 = r11.mo2221aq()
            cjr r9 = r11.m2748ds()
            cue r10 = r11.m2749dt()
            cfu r0 = new cfu
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bjw.mo2196aR():cfu");
    }

    /* renamed from: ds */
    private final cjr m2748ds() {
        Object obj;
        Object obj2 = this.f2816cH;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2816cH;
                if (obj instanceof iok) {
                    BuildType b = ceo.m4214b();
                    boolean d = ((gxb) mo2183aE().f14635a.mo2097a()).mo7170a("com.google.android.apps.photosgo 13").mo7172d();
                    cjq a = cjr.m4407a(b);
                    a.f4512d = d;
                    a.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                    a.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                    obj = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2816cH = iog.m14219a(this.f2816cH, obj);
                }
            }
            obj2 = obj;
        }
        return (cjr) obj2;
    }

    /* renamed from: aX */
    public final cgi mo2202aX() {
        return new cgi(mo2201aW(), mo2220ap(), mo2221aq(), mo2189aK(), mo2213ai(), ((gxb) m2750du().f14641a.mo2097a()).mo7170a("com.google.android.apps.photosgo 43").mo7172d());
    }

    /* renamed from: aO */
    public final cip mo2193aO() {
        return new cip(mo2214aj());
    }

    /* renamed from: aL */
    public final cgr mo2190aL() {
        return new cgr(new cis(mo2214aj()), mo2213ai(), new cgs(new cji(ftz.m9622a(this.f2702a), mo2229ay())), mo2230az(), mo2180aB(), mo2314cn(), mo2181aC(), mo2220ap(), mo2221aq(), mo2182aD(), m2748ds(), m2749dt());
    }

    /* renamed from: aP */
    public final chf mo2194aP() {
        return new chf(mo2192aN(), mo2193aO(), new cgu(new cjj(ftz.m9622a(this.f2702a), mo2229ay())), mo2230az(), mo2180aB(), mo2314cn(), cvn.m5475a(mo2314cn()), mo2220ap(), mo2221aq(), m2748ds(), m2749dt());
    }

    /* renamed from: dt */
    private final cue m2749dt() {
        return (cue) iol.m14229a((Object) new chg(mo2189aK()), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: aU */
    public final chw mo2199aU() {
        return new chw(mo2193aO(), mo2198aT(), new cho(mo2192aN(), mo2230az(), mo2314cn(), ftz.m9622a(this.f2702a)), mo2314cn(), cvn.m5475a(mo2314cn()), mo2187aI(), mo2220ap(), mo2221aq(), m2748ds(), m2749dt());
    }

    /* renamed from: cs */
    public final ezz mo2319cs() {
        return fas.m8491b();
    }

    /* renamed from: cu */
    public final ezx mo2321cu() {
        return fat.m8493b();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: hem} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: hem} */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: ch */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.hem mo2308ch() {
        /*
            r14 = this;
            java.lang.Object r0 = r14.f2945ez
            boolean r1 = r0 instanceof p000.iok
            if (r1 == 0) goto L_0x0067
            monitor-enter(r0)
            java.lang.Object r1 = r14.f2945ez     // Catch:{ all -> 0x0064 }
            boolean r2 = r1 instanceof p000.iok     // Catch:{ all -> 0x0064 }
            if (r2 == 0) goto L_0x0060
            fty r1 = r14.f2702a     // Catch:{ all -> 0x0064 }
            android.content.Context r3 = p000.ftz.m9622a(r1)     // Catch:{ all -> 0x0064 }
            iel r4 = r14.mo2329f()     // Catch:{ all -> 0x0064 }
            iel r5 = r14.mo2314cn()     // Catch:{ all -> 0x0064 }
            iel r1 = r14.mo2329f()     // Catch:{ all -> 0x0064 }
            gwp r6 = new gwp     // Catch:{ all -> 0x0064 }
            r6.<init>(r1)     // Catch:{ all -> 0x0064 }
            iqk r7 = p000.ioj.f14600a     // Catch:{ all -> 0x0064 }
            eyj r8 = p000.eyv.m8395b()     // Catch:{ all -> 0x0064 }
            ezz r9 = p000.fas.m8491b()     // Catch:{ all -> 0x0064 }
            ezx r10 = p000.fat.m8493b()     // Catch:{ all -> 0x0064 }
            fap r1 = new fap     // Catch:{ all -> 0x0064 }
            r1.<init>()     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = "Cannot return null from a non-@Nullable @Provides method"
            java.lang.Object r1 = p000.iol.m14229a((java.lang.Object) r1, (java.lang.String) r2)     // Catch:{ all -> 0x0064 }
            r11 = r1
            fac r11 = (p000.fac) r11     // Catch:{ all -> 0x0064 }
            fau r1 = new fau     // Catch:{ all -> 0x0064 }
            r1.<init>()     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = "Cannot return null from a non-@Nullable @Provides method"
            java.lang.Object r1 = p000.iol.m14229a((java.lang.Object) r1, (java.lang.String) r2)     // Catch:{ all -> 0x0064 }
            r12 = r1
            faf r12 = (p000.faf) r12     // Catch:{ all -> 0x0064 }
            gmg r13 = r14.mo2158I()     // Catch:{ all -> 0x0064 }
            hem r1 = new hem     // Catch:{ all -> 0x0064 }
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x0064 }
            java.lang.Object r2 = r14.f2945ez     // Catch:{ all -> 0x0064 }
            java.lang.Object r2 = p000.iog.m14219a(r2, r1)     // Catch:{ all -> 0x0064 }
            r14.f2945ez = r2     // Catch:{ all -> 0x0064 }
        L_0x0060:
            monitor-exit(r0)     // Catch:{ all -> 0x0064 }
            r0 = r1
            goto L_0x0068
        L_0x0064:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0064 }
            throw r1
        L_0x0067:
        L_0x0068:
            hem r0 = (p000.hem) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bjw.mo2308ch():hem");
    }

    /* renamed from: ci */
    public final iqk mo2309ci() {
        iqk iqk = this.f2915eA;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 111);
        this.f2915eA = bjv;
        return bjv;
    }

    /* renamed from: N */
    public final Set mo2163N() {
        Object obj;
        Object obj2;
        Object obj3 = this.f2761bF;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj2 = this.f2761bF;
                if (obj2 instanceof iok) {
                    obj2 = (hxp) iol.m14229a((Object) new hcu((hdm) m2741dl()), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2761bF = iog.m14219a(this.f2761bF, obj2);
                }
            }
            obj3 = obj2;
        }
        hxp hxp = (hxp) obj3;
        Object obj4 = this.f2762bG;
        if (obj4 instanceof iok) {
            synchronized (obj4) {
                obj = this.f2762bG;
                if (obj instanceof iok) {
                    obj = (hxp) iol.m14229a((Object) new hxy(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2762bG = iog.m14219a(this.f2762bG, obj);
                }
            }
            obj4 = obj;
        }
        return hto.m12121a((Object) hxp, (Object) (hxp) obj4);
    }

    /* renamed from: di */
    private final gyc m2738di() {
        Object obj;
        Object obj2 = this.f2802bu;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2802bu;
                if (obj instanceof iok) {
                    gyd D = mo2153D();
                    gyj A = mo2150A();
                    hfq c = mo2284c();
                    iel g = mo2330g();
                    gxz F = mo2155F();
                    hsq a = hsu.m12065a(45);
                    try {
                        a.mo7932a("com.google.android.apps.photosgo 13", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 16", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 44", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 17", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 15", (gxc) iol.m14229a((Object) gxc.m10993a(((Long) hph.f13219a.mo7645a(900L)).longValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 14", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(false)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 53", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(false)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 49", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(false)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 45", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 31", (gxc) iol.m14229a((Object) gxc.m10994a((String) hph.f13219a.mo7645a("U")), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 48", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 34", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(false)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 35", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(false)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 33", (gxc) iol.m14229a((Object) gxc.m10994a((String) hph.f13219a.mo7645a("gallerygohelp_learngg")), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 43", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(false)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 10", (gxc) iol.m14229a((Object) gxc.m10994a((String) hph.f13219a.mo7645a("")), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 52", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 51", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 42", (gxc) iol.m14229a((Object) gxc.m10994a((String) hph.f13219a.mo7645a("UNKNOWN")), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 22", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 26", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(false)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 20", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 30", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 18", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 23", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 25", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 21", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 24", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 19", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 47", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 1", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 2", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 9", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 5", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 3", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 4", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 8", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 6", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 7", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 11", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(false)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 50", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 32", (gxc) iol.m14229a((Object) gxc.m10995a(((Boolean) hph.f13219a.mo7645a(true)).booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 41", (gxc) iol.m14229a((Object) gxc.m10994a((String) hph.f13219a.mo7645a("https://support.google.com/android/?p=gallerygosettings_learngg")), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 39", (gxc) iol.m14229a((Object) gxc.m10994a((String) hph.f13219a.mo7645a("https://www.google.com/policies/privacy/")), "Cannot return null from a non-@Nullable @Provides method"));
                        a.mo7932a("com.google.android.apps.photosgo 40", (gxc) iol.m14229a((Object) gxc.m10994a((String) hph.f13219a.mo7645a("https://www.google.com/policies/terms/")), "Cannot return null from a non-@Nullable @Provides method"));
                        hsu a2 = a.mo7930a();
                        Set dh = m2737dh();
                        gww gww = gww.DEVICE;
                        gyn gyn = new gyn(g, dh, c);
                        gzm gzm = gyo.f12313a;
                        gzm gzm2 = gyp.f12314a;
                        A.getClass();
                        obj = (gyc) iol.m14229a((Object) D.mo7209a(gww, gyn, g, a2, gzm, gzm2, new gyq(A), new gyr(F)), "Cannot return null from a non-@Nullable @Provides method");
                        this.f2802bu = iog.m14219a(this.f2802bu, obj);
                    } catch (RuntimeException e) {
                        throw e;
                    } catch (Exception e2) {
                        throw new RuntimeException(e2);
                    } catch (RuntimeException e3) {
                        throw e3;
                    } catch (Exception e4) {
                        throw new RuntimeException(e4);
                    } catch (RuntimeException e5) {
                        throw e5;
                    } catch (Exception e6) {
                        throw new RuntimeException(e6);
                    } catch (RuntimeException e7) {
                        throw e7;
                    } catch (Exception e8) {
                        throw new RuntimeException(e8);
                    } catch (RuntimeException e9) {
                        throw e9;
                    } catch (Exception e10) {
                        throw new RuntimeException(e10);
                    } catch (RuntimeException e11) {
                        throw e11;
                    } catch (Exception e12) {
                        throw new RuntimeException(e12);
                    } catch (RuntimeException e13) {
                        throw e13;
                    } catch (Exception e14) {
                        throw new RuntimeException(e14);
                    } catch (RuntimeException e15) {
                        throw e15;
                    } catch (Exception e16) {
                        throw new RuntimeException(e16);
                    } catch (RuntimeException e17) {
                        throw e17;
                    } catch (Exception e18) {
                        throw new RuntimeException(e18);
                    } catch (RuntimeException e19) {
                        throw e19;
                    } catch (Exception e20) {
                        throw new RuntimeException(e20);
                    } catch (RuntimeException e21) {
                        throw e21;
                    } catch (Exception e22) {
                        throw new RuntimeException(e22);
                    } catch (RuntimeException e23) {
                        throw e23;
                    } catch (Exception e24) {
                        throw new RuntimeException(e24);
                    } catch (RuntimeException e25) {
                        throw e25;
                    } catch (Exception e26) {
                        throw new RuntimeException(e26);
                    } catch (RuntimeException e27) {
                        throw e27;
                    } catch (Exception e28) {
                        throw new RuntimeException(e28);
                    } catch (RuntimeException e29) {
                        throw e29;
                    } catch (Exception e30) {
                        throw new RuntimeException(e30);
                    } catch (RuntimeException e31) {
                        throw e31;
                    } catch (Exception e32) {
                        throw new RuntimeException(e32);
                    } catch (RuntimeException e33) {
                        throw e33;
                    } catch (Exception e34) {
                        throw new RuntimeException(e34);
                    } catch (RuntimeException e35) {
                        throw e35;
                    } catch (Exception e36) {
                        throw new RuntimeException(e36);
                    } catch (RuntimeException e37) {
                        throw e37;
                    } catch (Exception e38) {
                        throw new RuntimeException(e38);
                    } catch (RuntimeException e39) {
                        throw e39;
                    } catch (Exception e40) {
                        throw new RuntimeException(e40);
                    } catch (RuntimeException e41) {
                        throw e41;
                    } catch (Exception e42) {
                        throw new RuntimeException(e42);
                    } catch (RuntimeException e43) {
                        throw e43;
                    } catch (Exception e44) {
                        throw new RuntimeException(e44);
                    } catch (RuntimeException e45) {
                        throw e45;
                    } catch (Exception e46) {
                        throw new RuntimeException(e46);
                    } catch (RuntimeException e47) {
                        throw e47;
                    } catch (Exception e48) {
                        throw new RuntimeException(e48);
                    } catch (RuntimeException e49) {
                        throw e49;
                    } catch (Exception e50) {
                        throw new RuntimeException(e50);
                    } catch (RuntimeException e51) {
                        throw e51;
                    } catch (Exception e52) {
                        throw new RuntimeException(e52);
                    } catch (RuntimeException e53) {
                        throw e53;
                    } catch (Exception e54) {
                        throw new RuntimeException(e54);
                    } catch (RuntimeException e55) {
                        throw e55;
                    } catch (Exception e56) {
                        throw new RuntimeException(e56);
                    } catch (RuntimeException e57) {
                        throw e57;
                    } catch (Exception e58) {
                        throw new RuntimeException(e58);
                    } catch (RuntimeException e59) {
                        throw e59;
                    } catch (Exception e60) {
                        throw new RuntimeException(e60);
                    } catch (RuntimeException e61) {
                        throw e61;
                    } catch (Exception e62) {
                        throw new RuntimeException(e62);
                    } catch (RuntimeException e63) {
                        throw e63;
                    } catch (Exception e64) {
                        throw new RuntimeException(e64);
                    } catch (RuntimeException e65) {
                        throw e65;
                    } catch (Exception e66) {
                        throw new RuntimeException(e66);
                    } catch (RuntimeException e67) {
                        throw e67;
                    } catch (Exception e68) {
                        throw new RuntimeException(e68);
                    } catch (RuntimeException e69) {
                        throw e69;
                    } catch (Exception e70) {
                        throw new RuntimeException(e70);
                    } catch (RuntimeException e71) {
                        throw e71;
                    } catch (Exception e72) {
                        throw new RuntimeException(e72);
                    } catch (RuntimeException e73) {
                        throw e73;
                    } catch (Exception e74) {
                        throw new RuntimeException(e74);
                    } catch (RuntimeException e75) {
                        throw e75;
                    } catch (Exception e76) {
                        throw new RuntimeException(e76);
                    } catch (RuntimeException e77) {
                        throw e77;
                    } catch (Exception e78) {
                        throw new RuntimeException(e78);
                    } catch (RuntimeException e79) {
                        throw e79;
                    } catch (Exception e80) {
                        throw new RuntimeException(e80);
                    } catch (RuntimeException e81) {
                        throw e81;
                    } catch (Exception e82) {
                        throw new RuntimeException(e82);
                    } catch (RuntimeException e83) {
                        throw e83;
                    } catch (Exception e84) {
                        throw new RuntimeException(e84);
                    } catch (RuntimeException e85) {
                        throw e85;
                    } catch (Exception e86) {
                        throw new RuntimeException(e86);
                    } catch (RuntimeException e87) {
                        throw e87;
                    } catch (Exception e88) {
                        throw new RuntimeException(e88);
                    } catch (RuntimeException e89) {
                        throw e89;
                    } catch (Exception e90) {
                        throw new RuntimeException(e90);
                    }
                }
            }
            obj2 = obj;
        }
        return (gyc) obj2;
    }

    /* renamed from: J */
    public final gxb mo2159J() {
        Object obj;
        Object obj2 = this.f2806by;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2806by;
                if (obj instanceof iok) {
                    gyc di = m2738di();
                    di.getClass();
                    obj = (gxb) iol.m14229a((Object) new gym(di), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2806by = iog.m14219a(this.f2806by, obj);
                }
            }
            obj2 = obj;
        }
        return (gxb) obj2;
    }

    /* renamed from: K */
    public final iqk mo2160K() {
        iqk iqk = this.f2807bz;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 26);
        this.f2807bz = bjv;
        return bjv;
    }

    /* renamed from: cW */
    private final Object m2723cW() {
        Object obj;
        Object obj2 = this.f2712aJ;
        if (!(obj2 instanceof iok)) {
            return obj2;
        }
        synchronized (obj2) {
            obj = this.f2712aJ;
            if (obj instanceof iok) {
                gqw gqw = new gqw(ftz.m9622a(this.f2702a), (got) mo2334k(), mo2314cn());
                this.f2712aJ = iog.m14219a(this.f2712aJ, gqw);
                obj = gqw;
            }
        }
        return obj;
    }

    /* renamed from: R */
    public final bkv mo2167R() {
        Object obj;
        Object obj2 = this.f2769bN;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2769bN;
                if (obj instanceof iok) {
                    obj = new bkv();
                    this.f2769bN = iog.m14219a(this.f2769bN, obj);
                }
            }
            obj2 = obj;
        }
        return (bkv) obj2;
    }

    /* renamed from: I */
    public final gmg mo2158I() {
        return new gmg(hpy.m11893b(mo2157H()));
    }

    /* renamed from: dg */
    private final iqk m2736dg() {
        iqk iqk = this.f2788bg;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 1);
        this.f2788bg = bjv;
        return bjv;
    }

    /* renamed from: M */
    public final ext mo2162M() {
        ftz.m9622a(this.f2702a);
        return (ext) iol.m14229a((Object) new exw((byte[]) null), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: z */
    public final fqp mo2349z() {
        return new fqp(ftz.m9622a(this.f2702a), ezm.m8421b(), (fbq) iol.m14229a((Object) new fca(), "Cannot return null from a non-@Nullable @Provides method"), (fbn) iol.m14229a((Object) new fcb(), "Cannot return null from a non-@Nullable @Provides method"), (fbm) iol.m14229a((Object) new fbz(ftz.m9622a(this.f2702a)), "Cannot return null from a non-@Nullable @Provides method"), eyv.m8395b(), mo2314cn());
    }

    /* renamed from: dC */
    private static final Map m2729dC() {
        return hsu.m12066a("com.google.android.apps.photosgo", (gww) iol.m14229a((Object) gww.DEVICE, "Cannot return null from a non-@Nullable @Provides method"));
    }

    /* renamed from: ac */
    public final hbc mo2207ac() {
        return (hbc) iol.m14229a((Object) new hfo((fcd) iol.m14229a((Object) new fce(), "Cannot return null from a non-@Nullable @Provides method"), ezm.m8421b(), ftz.m9622a(this.f2702a)), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: cB */
    public final Set mo2286cB() {
        Object obj;
        Object obj2 = this.f2868dG;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2868dG;
                if (obj instanceof iok) {
                    obj = (bdl) iol.m14229a((Object) new col(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2868dG = iog.m14219a(this.f2868dG, obj);
                }
            }
            obj2 = obj;
        }
        return hto.m12120a((Object) (bdl) obj2);
    }

    /* renamed from: aq */
    public final cui mo2221aq() {
        Object obj;
        Object obj2 = this.f2859cy;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2859cy;
                if (obj instanceof iok) {
                    cui cui = new cui(mo2314cn());
                    this.f2859cy = iog.m14219a(this.f2859cy, cui);
                    obj = cui;
                }
            }
            obj2 = obj;
        }
        return (cui) obj2;
    }

    /* renamed from: cr */
    public final eyi mo2318cr() {
        return (eyi) iol.m14229a((Object) new eys(ftz.m9622a(this.f2702a)), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: ba */
    public final dyr mo2258ba() {
        Object obj;
        Object obj2 = this.f2889db;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2889db;
                if (obj instanceof iok) {
                    dyr dyr = new dyr(mo2271bn(), mo2260bc(), new dzc(new ehd(ftz.m9622a(this.f2702a), (Calendar) iol.m14229a((Object) Calendar.getInstance(ehe.f8282b), "Cannot return null from a non-@Nullable @Provides method"), mo2204aZ())), mo2314cn(), (gus) mo2332i());
                    this.f2889db = iog.m14219a(this.f2889db, dyr);
                    obj = dyr;
                }
            }
            obj2 = obj;
        }
        return (dyr) obj2;
    }

    /* renamed from: ce */
    public final iqa mo2305ce() {
        return new iqa(mo2160K());
    }

    /* renamed from: cj */
    public final hey mo2310cj() {
        Object obj;
        Object obj2 = this.f2916eB;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2916eB;
                if (obj instanceof iok) {
                    hey hey = new hey(ftz.m9622a(this.f2702a), mo2308ch(), (fay) iol.m14229a((Object) new fbf(), "Cannot return null from a non-@Nullable @Provides method"), mo2314cn());
                    this.f2916eB = iog.m14219a(this.f2916eB, hey);
                    obj = hey;
                }
            }
            obj2 = obj;
        }
        return (hey) obj2;
    }

    /* renamed from: bO */
    public final Class mo2246bO() {
        Object obj;
        Object obj2 = this.f2931el;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2931el;
                if (obj instanceof iok) {
                    obj = (Class) iol.m14229a((Object) HomeActivity.class, "Cannot return null from a non-@Nullable @Provides method");
                    this.f2931el = iog.m14219a(this.f2931el, obj);
                }
            }
            obj2 = obj;
        }
        return (Class) obj2;
    }

    /* renamed from: ax */
    public final bph mo2228ax() {
        return bpi.m3327a(mo2215ak(), mo2216al(), mo2220ap(), mo2221aq(), mo2218an(), mo2222ar(), m2747dr(), exn.m8328b(), mo2314cn(), mo2227aw());
    }

    /* renamed from: aw */
    public final cjr mo2227aw() {
        Object obj;
        Object obj2 = this.f2810cB;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2810cB;
                if (obj instanceof iok) {
                    cjq a = cjr.m4407a(ceo.m4214b());
                    a.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST};
                    a.f4511c = new BuildType[0];
                    obj = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2810cB = iog.m14219a(this.f2810cB, obj);
                }
            }
            obj2 = obj;
        }
        return (cjr) obj2;
    }

    /* renamed from: bp */
    public final csi mo2273bp() {
        cus cus;
        Object obj;
        bpt aj = mo2214aj();
        int i = csr.f5587a;
        cso cso = new cso(aj);
        crz crz = new crz(ftz.m9622a(this.f2702a), (String) iol.m14229a((Object) csr.m5362c(), "Cannot return null from a non-@Nullable @Provides method"), (String) iol.m14229a((Object) csr.m5361b(), "Cannot return null from a non-@Nullable @Provides method"), (Float) iol.m14229a((Object) csr.m5363d(), "Cannot return null from a non-@Nullable @Provides method"), mo2229ay());
        cnr az = mo2230az();
        csp csp = (csp) iol.m14229a((Object) csr.m5360a(), "Cannot return null from a non-@Nullable @Provides method");
        cny aB = mo2180aB();
        iel cn = mo2314cn();
        cvm aC = mo2181aC();
        cwq ap = mo2220ap();
        ble bo = mo2272bo();
        cui aq = mo2221aq();
        cus aD = mo2182aD();
        Object obj2 = this.f2904dq;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2904dq;
                if (obj instanceof iok) {
                    BuildType b = ceo.m4214b();
                    boolean d = ((gxb) mo2183aE().f14635a.mo2097a()).mo7170a("com.google.android.apps.photosgo 16").mo7172d();
                    cjq a = cjr.m4407a(b);
                    a.f4512d = d;
                    cus = aD;
                    a.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                    a.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                    obj = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2904dq = iog.m14219a(this.f2904dq, obj);
                } else {
                    cus = aD;
                }
            }
            obj2 = obj;
        } else {
            cus = aD;
        }
        return new csi(cso, crz, az, csp, aB, cn, aC, ap, bo, aq, cus, (cjr) obj2);
    }

    /* renamed from: aB */
    public final cny mo2180aB() {
        cny cny = this.f2814cF;
        if (cny != null) {
            return cny;
        }
        cny cny2 = new cny((coa) mo2179aA());
        this.f2814cF = cny2;
        return cny2;
    }

    /* renamed from: cb */
    public final iqk mo2302cb() {
        iqk iqk = this.f2942ew;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 104);
        this.f2942ew = bjv;
        return bjv;
    }

    /* renamed from: aA */
    public final Object mo2179aA() {
        Object obj = this.f2813cE;
        if (obj != null) {
            return obj;
        }
        coa coa = new coa(ftz.m9622a(this.f2702a), (WindowManager) iol.m14229a((Object) (WindowManager) ftz.m9622a(this.f2702a).getSystemService("window"), "Cannot return null from a non-@Nullable @Provides method"), mo2218an());
        this.f2813cE = coa;
        return coa;
    }

    /* renamed from: G */
    public final Set mo2156G() {
        mo2155F();
        return hto.m12120a((Object) (gli) iol.m14229a((Object) new gxh(), "Cannot return null from a non-@Nullable @Provides method"));
    }

    /* renamed from: bS */
    public final fdv mo2250bS() {
        return (fdv) iol.m14229a((Object) new fdv(m2727dA()), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bV */
    public final bkz mo2253bV() {
        Object obj;
        Object obj2;
        Object obj3 = this.f2937er;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj = this.f2937er;
                if (obj instanceof iok) {
                    iel cn = mo2314cn();
                    hto a = hto.m12121a((Object) new cvc(mo2314cn(), iog.m14218a(m2753dx())), mo2232bA());
                    Object obj4 = this.f2936eq;
                    if (obj4 instanceof iok) {
                        synchronized (obj4) {
                            obj2 = this.f2936eq;
                            if (obj2 instanceof iok) {
                                hfu g = hfv.m11397g();
                                g.f12674a = "FirstTimeAppOpen";
                                g.mo7383a(cwj.f5798c);
                                obj2 = (fzx) iol.m14229a((Object) mo2328e().mo7385a((hfv) iol.m14229a((Object) g.mo7382a(), "Cannot return null from a non-@Nullable @Provides method")), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2936eq = iog.m14219a(this.f2936eq, obj2);
                            }
                        }
                        obj4 = obj2;
                    }
                    bkz bkz = new bkz(cn, a, new cwl((fzx) obj4, mo2220ap()));
                    this.f2937er = iog.m14219a(this.f2937er, bkz);
                    obj = bkz;
                }
            }
            obj3 = obj;
        }
        return (bkz) obj3;
    }

    /* renamed from: bW */
    public final iqk mo2254bW() {
        iqk iqk = this.f2938es;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 102);
        this.f2938es = bjv;
        return bjv;
    }

    /* renamed from: aF */
    public final String mo2184aF() {
        return (String) iol.m14229a((Object) ((gxb) new iqd(mo2160K()).f14643a.mo2097a()).mo7170a("com.google.android.apps.photosgo 42").mo7171c(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bJ */
    public final String mo2241bJ() {
        return (String) iol.m14229a((Object) ((gxb) m2750du().f14641a.mo2097a()).mo7170a("com.google.android.apps.photosgo 10").mo7171c(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: cf */
    public final String mo2306cf() {
        return (String) iol.m14229a((Object) ((gxb) m2728dB().f14649a.mo2097a()).mo7170a("com.google.android.apps.photosgo 40").mo7171c(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: cg */
    public final String mo2307cg() {
        return (String) iol.m14229a((Object) ((gxb) m2728dB().f14649a.mo2097a()).mo7170a("com.google.android.apps.photosgo 39").mo7171c(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: cl */
    public final String mo2312cl() {
        return (String) iol.m14229a((Object) ((gxb) m2728dB().f14649a.mo2097a()).mo7170a("com.google.android.apps.photosgo 41").mo7171c(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: aD */
    public final cus mo2182aD() {
        Object obj;
        Object obj2 = this.f2815cG;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2815cG;
                if (obj instanceof iok) {
                    cjq a = cjr.m4407a(ceo.m4214b());
                    a.f4510b = new BuildType[]{BuildType.DEV};
                    a.f4511c = new BuildType[]{BuildType.DEV};
                    obj = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2815cG = iog.m14219a(this.f2815cG, obj);
                }
            }
            obj2 = obj;
        }
        return new cus((cjr) obj2, iog.m14218a(mo2151B()));
    }

    /* renamed from: du */
    private final iqb m2750du() {
        return new iqb(mo2160K());
    }

    /* renamed from: f */
    public final iel mo2329f() {
        Object obj;
        Object obj2;
        Object obj3 = this.f2706aD;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj = this.f2706aD;
                if (obj instanceof iok) {
                    Object obj4 = this.f2705aC;
                    if (obj4 instanceof iok) {
                        synchronized (obj4) {
                            obj2 = this.f2705aC;
                            if (obj2 instanceof iok) {
                                obj2 = (iel) iol.m14229a((Object) gpt.m10595a(gqb.m10619a("Lite Thread", Math.max(2, Runtime.getRuntime().availableProcessors() - 2), 0, hkc.m11619b().mo7505a()), m2721cU()), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2705aC = iog.m14219a(this.f2705aC, obj2);
                            }
                        }
                        obj4 = obj2;
                    }
                    obj = (iel) iol.m14229a((Object) gpw.m10607a((iel) obj4), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2706aD = iog.m14219a(this.f2706aD, obj);
                }
            }
            obj3 = obj;
        }
        return (iel) obj3;
    }

    /* renamed from: bz */
    public final deo mo2283bz() {
        Object obj;
        Object obj2 = this.f2862dA;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2862dA;
                if (obj instanceof iok) {
                    deo deo = new deo(mo2225au(), iog.m14218a(m2752dw()), mo2324cx(), mo2314cn());
                    this.f2862dA = iog.m14219a(this.f2862dA, deo);
                    obj = deo;
                }
            }
            obj2 = obj;
        }
        return (deo) obj2;
    }

    /* renamed from: cm */
    public final blf mo2313cm() {
        return new blf(iog.m14218a(m2746dq()));
    }

    /* renamed from: aZ */
    public final cwg mo2204aZ() {
        return new cwg(ftz.m9622a(this.f2702a));
    }

    /* renamed from: dk */
    private final hdh m2740dk() {
        Object obj;
        Object obj2 = this.f2759bD;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2759bD;
                if (obj instanceof iok) {
                    obj = new hdh();
                    this.f2759bD = iog.m14219a(this.f2759bD, obj);
                }
            }
            obj2 = obj;
        }
        return (hdh) obj2;
    }

    /* renamed from: Q */
    public final Object mo2166Q() {
        return new hkz((hkf) mo2165P());
    }

    /* JADX WARNING: Removed duplicated region for block: B:76:0x0183 A[Catch:{ Exception -> 0x035e }] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x018b A[Catch:{ Exception -> 0x035e }] */
    /* renamed from: W */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map mo2172W() {
        /*
            r18 = this;
            java.lang.String r1 = "string"
            java.lang.String r2 = "PhenotypeResourceReader"
            r3 = r18
            fty r0 = r3.f2702a
            android.content.Context r0 = p000.ftz.m9622a(r0)
            android.content.pm.PackageManager r4 = r18.m2719cS()
            fqs r5 = new fqs
            r5.<init>(r4)
            java.lang.String r0 = r0.getPackageName()     // Catch:{ NameNotFoundException -> 0x037b }
            r6 = 128(0x80, float:1.794E-43)
            android.content.pm.PackageInfo r4 = r4.getPackageInfo(r0, r6)     // Catch:{ NameNotFoundException -> 0x037b }
            if (r4 == 0) goto L_0x0373
            r8 = 0
            android.content.pm.ApplicationInfo r0 = r4.applicationInfo     // Catch:{ Exception -> 0x0173 }
            if (r0 != 0) goto L_0x0028
        L_0x0026:
            goto L_0x016d
        L_0x0028:
            android.content.pm.ApplicationInfo r0 = r4.applicationInfo     // Catch:{ Exception -> 0x0173 }
            android.os.Bundle r0 = r0.metaData     // Catch:{ Exception -> 0x0173 }
            if (r0 == 0) goto L_0x0026
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Exception -> 0x0173 }
            r0.<init>()     // Catch:{ Exception -> 0x0173 }
            android.content.pm.ApplicationInfo r9 = r4.applicationInfo     // Catch:{ Exception -> 0x0173 }
            android.os.Bundle r9 = r9.metaData     // Catch:{ Exception -> 0x0173 }
            java.util.Set r9 = r9.keySet()     // Catch:{ Exception -> 0x0173 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ Exception -> 0x0173 }
        L_0x003f:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x0173 }
            java.lang.String r11 = "com.google.android.gms.phenotype.registration.xml:"
            java.lang.String r12 = "com.google.android.gms.phenotype.registration.xml"
            if (r10 == 0) goto L_0x006d
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x0173 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x0173 }
            boolean r12 = r12.equals(r10)     // Catch:{ Exception -> 0x0173 }
            if (r12 != 0) goto L_0x005b
            boolean r11 = r10.startsWith(r11)     // Catch:{ Exception -> 0x0173 }
            if (r11 == 0) goto L_0x003f
        L_0x005b:
            android.content.pm.ApplicationInfo r11 = r4.applicationInfo     // Catch:{ Exception -> 0x0173 }
            android.os.Bundle r11 = r11.metaData     // Catch:{ Exception -> 0x0173 }
            int r10 = r11.getInt(r10, r8)     // Catch:{ Exception -> 0x0173 }
            if (r10 == 0) goto L_0x003f
            java.util.List r10 = r5.mo6034a((int) r10, (android.content.pm.PackageInfo) r4)     // Catch:{ Exception -> 0x0173 }
            r0.addAll(r10)     // Catch:{ Exception -> 0x0173 }
            goto L_0x003f
        L_0x006d:
            android.content.pm.ServiceInfo[] r9 = r4.services     // Catch:{ Exception -> 0x0173 }
            r10 = 5
            if (r9 != 0) goto L_0x0074
            goto L_0x00fb
        L_0x0074:
            android.content.pm.ServiceInfo[] r9 = r4.services     // Catch:{ Exception -> 0x0173 }
            int r13 = r9.length     // Catch:{ Exception -> 0x0173 }
            r14 = 0
        L_0x0078:
            if (r14 >= r13) goto L_0x00fb
            r15 = r9[r14]     // Catch:{ Exception -> 0x0173 }
            if (r15 == 0) goto L_0x00f7
            java.lang.String r6 = "com.google.android.libraries.phenotype.registration.PhenotypeMetadataHolderService"
            java.lang.String r7 = r15.name     // Catch:{ Exception -> 0x0173 }
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x0173 }
            if (r6 != 0) goto L_0x0089
            goto L_0x00f7
        L_0x0089:
            android.os.Bundle r6 = r15.metaData     // Catch:{ Exception -> 0x0173 }
            if (r6 == 0) goto L_0x00fb
            android.os.Bundle r6 = r15.metaData     // Catch:{ Exception -> 0x0173 }
            java.util.Set r6 = r6.keySet()     // Catch:{ Exception -> 0x0173 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x0173 }
        L_0x0097:
            boolean r7 = r6.hasNext()     // Catch:{ Exception -> 0x0173 }
            if (r7 == 0) goto L_0x00fb
            java.lang.Object r7 = r6.next()     // Catch:{ Exception -> 0x0173 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x0173 }
            boolean r9 = r12.equals(r7)     // Catch:{ Exception -> 0x0173 }
            if (r9 != 0) goto L_0x00af
            boolean r9 = r7.startsWith(r11)     // Catch:{ Exception -> 0x0173 }
            if (r9 == 0) goto L_0x0097
        L_0x00af:
            android.os.Bundle r9 = r15.metaData     // Catch:{ Exception -> 0x0173 }
            int r7 = r9.getInt(r7, r8)     // Catch:{ Exception -> 0x0173 }
            if (r7 == 0) goto L_0x0097
            java.util.List r7 = r5.mo6034a((int) r7, (android.content.pm.PackageInfo) r4)     // Catch:{ Exception -> 0x0173 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ Exception -> 0x0173 }
        L_0x00bf:
            boolean r9 = r7.hasNext()     // Catch:{ Exception -> 0x0173 }
            if (r9 == 0) goto L_0x0097
            java.lang.Object r9 = r7.next()     // Catch:{ Exception -> 0x0173 }
            ifq r9 = (p000.ifq) r9     // Catch:{ Exception -> 0x0173 }
            java.lang.Object r13 = r9.mo8790b((int) r10)     // Catch:{ Exception -> 0x0173 }
            iir r13 = (p000.iir) r13     // Catch:{ Exception -> 0x0173 }
            r13.mo8503a((p000.iix) r9)     // Catch:{ Exception -> 0x0173 }
            boolean r9 = r13.f14319c     // Catch:{ Exception -> 0x0173 }
            if (r9 != 0) goto L_0x00d9
            goto L_0x00de
        L_0x00d9:
            r13.mo8751b()     // Catch:{ Exception -> 0x0173 }
            r13.f14319c = r8     // Catch:{ Exception -> 0x0173 }
        L_0x00de:
            iix r9 = r13.f14318b     // Catch:{ Exception -> 0x0173 }
            ifq r9 = (p000.ifq) r9     // Catch:{ Exception -> 0x0173 }
            ifq r14 = p000.ifq.f14004k     // Catch:{ Exception -> 0x0173 }
            r14 = 1
            r9.f14015j = r14     // Catch:{ Exception -> 0x0173 }
            int r14 = r9.f14006a     // Catch:{ Exception -> 0x0173 }
            r14 = r14 | 64
            r9.f14006a = r14     // Catch:{ Exception -> 0x0173 }
            iix r9 = r13.mo8770g()     // Catch:{ Exception -> 0x0173 }
            ifq r9 = (p000.ifq) r9     // Catch:{ Exception -> 0x0173 }
            r0.add(r9)     // Catch:{ Exception -> 0x0173 }
            goto L_0x00bf
        L_0x00f7:
            int r14 = r14 + 1
            goto L_0x0078
        L_0x00fb:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Exception -> 0x0173 }
            int r7 = r0.size()     // Catch:{ Exception -> 0x0173 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0173 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x0173 }
        L_0x0108:
            boolean r7 = r0.hasNext()     // Catch:{ Exception -> 0x0173 }
            if (r7 == 0) goto L_0x017f
            java.lang.Object r7 = r0.next()     // Catch:{ Exception -> 0x0173 }
            ifq r7 = (p000.ifq) r7     // Catch:{ Exception -> 0x0173 }
            java.lang.Object r9 = r7.mo8790b((int) r10)     // Catch:{ Exception -> 0x0173 }
            iir r9 = (p000.iir) r9     // Catch:{ Exception -> 0x0173 }
            r9.mo8503a((p000.iix) r7)     // Catch:{ Exception -> 0x0173 }
            java.lang.String r11 = r4.packageName     // Catch:{ Exception -> 0x0173 }
            boolean r12 = r9.f14319c     // Catch:{ Exception -> 0x0173 }
            if (r12 != 0) goto L_0x0124
            goto L_0x0129
        L_0x0124:
            r9.mo8751b()     // Catch:{ Exception -> 0x0173 }
            r9.f14319c = r8     // Catch:{ Exception -> 0x0173 }
        L_0x0129:
            iix r12 = r9.f14318b     // Catch:{ Exception -> 0x0173 }
            ifq r12 = (p000.ifq) r12     // Catch:{ Exception -> 0x0173 }
            ifq r13 = p000.ifq.f14004k     // Catch:{ Exception -> 0x0173 }
            r11.getClass()     // Catch:{ Exception -> 0x0173 }
            int r13 = r12.f14006a     // Catch:{ Exception -> 0x0173 }
            r13 = r13 | 16
            r12.f14006a = r13     // Catch:{ Exception -> 0x0173 }
            r12.f14013h = r11     // Catch:{ Exception -> 0x0173 }
            int r7 = r7.f14008c     // Catch:{ Exception -> 0x0173 }
            if (r7 != 0) goto L_0x0156
            int r7 = r4.versionCode     // Catch:{ Exception -> 0x0173 }
            boolean r11 = r9.f14319c     // Catch:{ Exception -> 0x0173 }
            if (r11 != 0) goto L_0x0145
            goto L_0x014a
        L_0x0145:
            r9.mo8751b()     // Catch:{ Exception -> 0x0173 }
            r9.f14319c = r8     // Catch:{ Exception -> 0x0173 }
        L_0x014a:
            iix r11 = r9.f14318b     // Catch:{ Exception -> 0x0173 }
            ifq r11 = (p000.ifq) r11     // Catch:{ Exception -> 0x0173 }
            int r12 = r11.f14006a     // Catch:{ Exception -> 0x0173 }
            r13 = 2
            r12 = r12 | r13
            r11.f14006a = r12     // Catch:{ Exception -> 0x0173 }
            r11.f14008c = r7     // Catch:{ Exception -> 0x0173 }
        L_0x0156:
            iix r7 = r9.f14318b     // Catch:{ Exception -> 0x0173 }
            ifq r7 = (p000.ifq) r7     // Catch:{ Exception -> 0x0173 }
            r11 = 2
            r7.f14014i = r11     // Catch:{ Exception -> 0x0173 }
            int r11 = r7.f14006a     // Catch:{ Exception -> 0x0173 }
            r11 = r11 | 32
            r7.f14006a = r11     // Catch:{ Exception -> 0x0173 }
            iix r7 = r9.mo8770g()     // Catch:{ Exception -> 0x0173 }
            ifq r7 = (p000.ifq) r7     // Catch:{ Exception -> 0x0173 }
            r6.add(r7)     // Catch:{ Exception -> 0x0173 }
            goto L_0x0108
        L_0x016d:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Exception -> 0x0173 }
            r6.<init>()     // Catch:{ Exception -> 0x0173 }
        L_0x0172:
            goto L_0x017f
        L_0x0173:
            r0 = move-exception
            java.lang.String r6 = "Error reading phenotype XML registration format: "
            android.util.Log.e(r2, r6, r0)     // Catch:{ NameNotFoundException -> 0x037b }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ NameNotFoundException -> 0x037b }
            r6.<init>()     // Catch:{ NameNotFoundException -> 0x037b }
            goto L_0x0172
        L_0x017f:
            android.content.pm.ApplicationInfo r0 = r4.applicationInfo     // Catch:{ Exception -> 0x035e }
            if (r0 != 0) goto L_0x0185
        L_0x0183:
            goto L_0x0354
        L_0x0185:
            android.content.pm.ApplicationInfo r0 = r4.applicationInfo     // Catch:{ Exception -> 0x035e }
            android.os.Bundle r0 = r0.metaData     // Catch:{ Exception -> 0x035e }
            if (r0 == 0) goto L_0x0183
            android.content.pm.ApplicationInfo r0 = r4.applicationInfo     // Catch:{ Exception -> 0x035e }
            android.os.Bundle r0 = r0.metaData     // Catch:{ Exception -> 0x035e }
            java.lang.String r7 = "com.google.android.gms.phenotype.registration.array"
            int r0 = r0.getInt(r7, r8)     // Catch:{ Exception -> 0x035e }
            if (r0 == 0) goto L_0x034e
            android.content.pm.PackageManager r5 = r5.f10291a     // Catch:{ Exception -> 0x035e }
            java.lang.String r7 = r4.packageName     // Catch:{ Exception -> 0x035e }
            android.content.res.Resources r5 = r5.getResourcesForApplication(r7)     // Catch:{ Exception -> 0x035e }
            java.lang.String[] r0 = r5.getStringArray(r0)     // Catch:{ Exception -> 0x035e }
            if (r0 == 0) goto L_0x0348
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ Exception -> 0x035e }
            int r9 = r0.length     // Catch:{ Exception -> 0x035e }
            r7.<init>(r9)     // Catch:{ Exception -> 0x035e }
            r10 = 0
        L_0x01ac:
            if (r10 >= r9) goto L_0x0359
            r11 = r0[r10]     // Catch:{ Exception -> 0x035e }
            ifq r12 = p000.ifq.f14004k     // Catch:{ Exception -> 0x035e }
            iir r12 = r12.mo8793g()     // Catch:{ Exception -> 0x035e }
            boolean r13 = r12.f14319c     // Catch:{ Exception -> 0x035e }
            if (r13 != 0) goto L_0x01bb
            goto L_0x01c0
        L_0x01bb:
            r12.mo8751b()     // Catch:{ Exception -> 0x035e }
            r12.f14319c = r8     // Catch:{ Exception -> 0x035e }
        L_0x01c0:
            iix r13 = r12.f14318b     // Catch:{ Exception -> 0x035e }
            ifq r13 = (p000.ifq) r13     // Catch:{ Exception -> 0x035e }
            r11.getClass()     // Catch:{ Exception -> 0x035e }
            int r14 = r13.f14006a     // Catch:{ Exception -> 0x035e }
            r15 = 1
            r14 = r14 | r15
            r13.f14006a = r14     // Catch:{ Exception -> 0x035e }
            r13.f14007b = r11     // Catch:{ Exception -> 0x035e }
            java.lang.String r13 = r4.packageName     // Catch:{ Exception -> 0x035e }
            boolean r14 = r12.f14319c     // Catch:{ Exception -> 0x035e }
            if (r14 != 0) goto L_0x01d6
            goto L_0x01db
        L_0x01d6:
            r12.mo8751b()     // Catch:{ Exception -> 0x035e }
            r12.f14319c = r8     // Catch:{ Exception -> 0x035e }
        L_0x01db:
            iix r14 = r12.f14318b     // Catch:{ Exception -> 0x035e }
            ifq r14 = (p000.ifq) r14     // Catch:{ Exception -> 0x035e }
            r13.getClass()     // Catch:{ Exception -> 0x035e }
            int r15 = r14.f14006a     // Catch:{ Exception -> 0x035e }
            r15 = r15 | 16
            r14.f14006a = r15     // Catch:{ Exception -> 0x035e }
            r14.f14013h = r13     // Catch:{ Exception -> 0x035e }
            r13 = 2
            r14.f14014i = r13     // Catch:{ Exception -> 0x035e }
            r13 = r15 | 32
            r14.f14006a = r13     // Catch:{ Exception -> 0x035e }
            java.lang.String r13 = "[^A-Za-z0-9]"
            java.lang.String r14 = "_"
            java.lang.String r13 = r11.replaceAll(r13, r14)     // Catch:{ Exception -> 0x035e }
            java.lang.String r14 = "phenotype_version_"
            java.lang.String r15 = java.lang.String.valueOf(r13)     // Catch:{ Exception -> 0x035e }
            int r17 = r15.length()     // Catch:{ Exception -> 0x035e }
            if (r17 != 0) goto L_0x020b
            java.lang.String r15 = new java.lang.String     // Catch:{ Exception -> 0x035e }
            r15.<init>(r14)     // Catch:{ Exception -> 0x035e }
            goto L_0x020f
        L_0x020b:
            java.lang.String r15 = r14.concat(r15)     // Catch:{ Exception -> 0x035e }
        L_0x020f:
            java.lang.String r14 = "integer"
            java.lang.String r8 = r4.packageName     // Catch:{ Exception -> 0x035e }
            int r8 = r5.getIdentifier(r15, r14, r8)     // Catch:{ Exception -> 0x035e }
            if (r8 != 0) goto L_0x0237
            int r8 = r4.versionCode     // Catch:{ Exception -> 0x035e }
            boolean r14 = r12.f14319c     // Catch:{ Exception -> 0x035e }
            if (r14 != 0) goto L_0x0220
            goto L_0x0226
        L_0x0220:
            r12.mo8751b()     // Catch:{ Exception -> 0x035e }
            r14 = 0
            r12.f14319c = r14     // Catch:{ Exception -> 0x035e }
        L_0x0226:
            iix r14 = r12.f14318b     // Catch:{ Exception -> 0x035e }
            ifq r14 = (p000.ifq) r14     // Catch:{ Exception -> 0x035e }
            int r15 = r14.f14006a     // Catch:{ Exception -> 0x035e }
            r16 = 2
            r15 = r15 | 2
            r14.f14006a = r15     // Catch:{ Exception -> 0x035e }
            r14.f14008c = r8     // Catch:{ Exception -> 0x035e }
            r16 = 2
            goto L_0x0254
        L_0x0237:
            int r8 = r5.getInteger(r8)     // Catch:{ Exception -> 0x035e }
            boolean r14 = r12.f14319c     // Catch:{ Exception -> 0x035e }
            if (r14 != 0) goto L_0x0240
            goto L_0x0246
        L_0x0240:
            r12.mo8751b()     // Catch:{ Exception -> 0x035e }
            r14 = 0
            r12.f14319c = r14     // Catch:{ Exception -> 0x035e }
        L_0x0246:
            iix r14 = r12.f14318b     // Catch:{ Exception -> 0x035e }
            ifq r14 = (p000.ifq) r14     // Catch:{ Exception -> 0x035e }
            int r15 = r14.f14006a     // Catch:{ Exception -> 0x035e }
            r16 = 2
            r15 = r15 | 2
            r14.f14006a = r15     // Catch:{ Exception -> 0x035e }
            r14.f14008c = r8     // Catch:{ Exception -> 0x035e }
        L_0x0254:
            java.lang.String r8 = "phenotype_logSources_"
            java.lang.String r14 = java.lang.String.valueOf(r13)     // Catch:{ Exception -> 0x035e }
            int r15 = r14.length()     // Catch:{ Exception -> 0x035e }
            if (r15 != 0) goto L_0x0266
            java.lang.String r14 = new java.lang.String     // Catch:{ Exception -> 0x035e }
            r14.<init>(r8)     // Catch:{ Exception -> 0x035e }
            goto L_0x026a
        L_0x0266:
            java.lang.String r14 = r8.concat(r14)     // Catch:{ Exception -> 0x035e }
        L_0x026a:
            java.lang.String r8 = "array"
            java.lang.String r15 = r4.packageName     // Catch:{ Exception -> 0x035e }
            int r8 = r5.getIdentifier(r14, r8, r15)     // Catch:{ Exception -> 0x035e }
            if (r8 != 0) goto L_0x0275
            goto L_0x0282
        L_0x0275:
            java.lang.String[] r8 = r5.getStringArray(r8)     // Catch:{ Exception -> 0x035e }
            if (r8 == 0) goto L_0x0282
            java.util.List r8 = java.util.Arrays.asList(r8)     // Catch:{ Exception -> 0x035e }
            r12.mo8753b((java.lang.Iterable) r8)     // Catch:{ Exception -> 0x035e }
        L_0x0282:
            java.lang.String r8 = "phenotype_params_"
            java.lang.String r14 = java.lang.String.valueOf(r13)     // Catch:{ Exception -> 0x035e }
            int r15 = r14.length()     // Catch:{ Exception -> 0x035e }
            if (r15 != 0) goto L_0x0294
            java.lang.String r14 = new java.lang.String     // Catch:{ Exception -> 0x035e }
            r14.<init>(r8)     // Catch:{ Exception -> 0x035e }
            goto L_0x0298
        L_0x0294:
            java.lang.String r14 = r8.concat(r14)     // Catch:{ Exception -> 0x035e }
        L_0x0298:
            java.lang.String r8 = r4.packageName     // Catch:{ Exception -> 0x035e }
            int r8 = r5.getIdentifier(r14, r1, r8)     // Catch:{ Exception -> 0x035e }
            if (r8 != 0) goto L_0x02a1
            goto L_0x02c9
        L_0x02a1:
            java.lang.String r8 = r5.getString(r8)     // Catch:{ Exception -> 0x035e }
            r14 = 8
            byte[] r8 = android.util.Base64.decode(r8, r14)     // Catch:{ Exception -> 0x035e }
            ihw r8 = p000.ihw.m13162a((byte[]) r8)     // Catch:{ Exception -> 0x035e }
            boolean r14 = r12.f14319c     // Catch:{ Exception -> 0x035e }
            if (r14 != 0) goto L_0x02b4
            goto L_0x02ba
        L_0x02b4:
            r12.mo8751b()     // Catch:{ Exception -> 0x035e }
            r14 = 0
            r12.f14319c = r14     // Catch:{ Exception -> 0x035e }
        L_0x02ba:
            iix r14 = r12.f14318b     // Catch:{ Exception -> 0x035e }
            ifq r14 = (p000.ifq) r14     // Catch:{ Exception -> 0x035e }
            r8.getClass()     // Catch:{ Exception -> 0x035e }
            int r15 = r14.f14006a     // Catch:{ Exception -> 0x035e }
            r15 = r15 | 4
            r14.f14006a = r15     // Catch:{ Exception -> 0x035e }
            r14.f14011f = r8     // Catch:{ Exception -> 0x035e }
        L_0x02c9:
            java.lang.String r8 = "phenotype_subpackage_"
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ Exception -> 0x035e }
            int r14 = r13.length()     // Catch:{ Exception -> 0x035e }
            if (r14 != 0) goto L_0x02db
            java.lang.String r13 = new java.lang.String     // Catch:{ Exception -> 0x035e }
            r13.<init>(r8)     // Catch:{ Exception -> 0x035e }
            goto L_0x02df
        L_0x02db:
            java.lang.String r13 = r8.concat(r13)     // Catch:{ Exception -> 0x035e }
        L_0x02df:
            java.lang.String r8 = r4.packageName     // Catch:{ Exception -> 0x035e }
            int r8 = r5.getIdentifier(r13, r1, r8)     // Catch:{ Exception -> 0x035e }
            if (r8 == 0) goto L_0x0338
            java.lang.String r8 = r5.getString(r8)     // Catch:{ Exception -> 0x035e }
            if (r8 == 0) goto L_0x0335
            boolean r13 = r8.isEmpty()     // Catch:{ Exception -> 0x035e }
            if (r13 != 0) goto L_0x0332
            java.lang.String r13 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x035e }
            int r13 = r13.length()     // Catch:{ Exception -> 0x035e }
            r14 = 1
            int r13 = r13 + r14
            int r14 = r8.length()     // Catch:{ Exception -> 0x035e }
            int r13 = r13 + r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x035e }
            r14.<init>(r13)     // Catch:{ Exception -> 0x035e }
            r14.append(r11)     // Catch:{ Exception -> 0x035e }
            java.lang.String r11 = "#"
            r14.append(r11)     // Catch:{ Exception -> 0x035e }
            r14.append(r8)     // Catch:{ Exception -> 0x035e }
            java.lang.String r8 = r14.toString()     // Catch:{ Exception -> 0x035e }
            boolean r11 = r12.f14319c     // Catch:{ Exception -> 0x035e }
            if (r11 != 0) goto L_0x031c
            r11 = 0
            goto L_0x0322
        L_0x031c:
            r12.mo8751b()     // Catch:{ Exception -> 0x035e }
            r11 = 0
            r12.f14319c = r11     // Catch:{ Exception -> 0x035e }
        L_0x0322:
            iix r13 = r12.f14318b     // Catch:{ Exception -> 0x035e }
            ifq r13 = (p000.ifq) r13     // Catch:{ Exception -> 0x035e }
            r8.getClass()     // Catch:{ Exception -> 0x035e }
            int r14 = r13.f14006a     // Catch:{ Exception -> 0x035e }
            r15 = 1
            r14 = r14 | r15
            r13.f14006a = r14     // Catch:{ Exception -> 0x035e }
            r13.f14007b = r8     // Catch:{ Exception -> 0x035e }
            goto L_0x033a
        L_0x0332:
            r11 = 0
            r15 = 1
            goto L_0x033a
        L_0x0335:
            r11 = 0
            r15 = 1
            goto L_0x033a
        L_0x0338:
            r11 = 0
            r15 = 1
        L_0x033a:
            iix r8 = r12.mo8770g()     // Catch:{ Exception -> 0x035e }
            ifq r8 = (p000.ifq) r8     // Catch:{ Exception -> 0x035e }
            r7.add(r8)     // Catch:{ Exception -> 0x035e }
            int r10 = r10 + 1
            r8 = 0
            goto L_0x01ac
        L_0x0348:
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ Exception -> 0x035e }
            r7.<init>()     // Catch:{ Exception -> 0x035e }
            goto L_0x0359
        L_0x034e:
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ Exception -> 0x035e }
            r7.<init>()     // Catch:{ Exception -> 0x035e }
            goto L_0x0359
        L_0x0354:
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ Exception -> 0x035e }
            r7.<init>()     // Catch:{ Exception -> 0x035e }
        L_0x0359:
            r6.addAll(r7)     // Catch:{ Exception -> 0x035e }
            goto L_0x0364
        L_0x035e:
            r0 = move-exception
            java.lang.String r1 = "Error reading phenotype alternate registration format: "
            android.util.Log.e(r2, r1, r0)     // Catch:{ NameNotFoundException -> 0x037b }
        L_0x0364:
            hpr r0 = p000.gzq.f12369a     // Catch:{ NameNotFoundException -> 0x037b }
            hsu r0 = p000.ife.m12812a((java.lang.Iterable) r6, (p000.hpr) r0)     // Catch:{ NameNotFoundException -> 0x037b }
            java.lang.String r1 = "Cannot return null from a non-@Nullable @Provides method"
            java.lang.Object r0 = p000.iol.m14229a((java.lang.Object) r0, (java.lang.String) r1)
            java.util.Map r0 = (java.util.Map) r0
            return r0
        L_0x0373:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ NameNotFoundException -> 0x037b }
            java.lang.String r1 = "null reference"
            r0.<init>(r1)     // Catch:{ NameNotFoundException -> 0x037b }
            throw r0     // Catch:{ NameNotFoundException -> 0x037b }
        L_0x037b:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            r1.<init>(r0)
            goto L_0x0383
        L_0x0382:
            throw r1
        L_0x0383:
            goto L_0x0382
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bjw.mo2172W():java.util.Map");
    }

    /* renamed from: bs */
    public final Map mo2276bs() {
        hsq a = hsu.m12065a(8);
        cup cup = cup.IMAGE_COMPRESSION_JOB;
        iqk iqk = this.f2811cC;
        if (iqk == null) {
            iqk = new bjv(this, 54);
            this.f2811cC = iqk;
        }
        a.mo7932a(cup, boy.m3316a(iog.m14218a(iqk)));
        a.mo7932a(cup.FACE_DETECTION_JOB, mo2191aM());
        a.mo7932a(cup.FACE_EMBEDDING_JOB, mo2195aQ());
        a.mo7932a(cup.FACE_CLUSTERING_JOB, mo2197aS());
        a.mo7932a(cup.FACE_THUMBNAILING_JOB, mo2200aV());
        a.mo7932a(cup.FACE_CLUSTERING_WIPEOUT_JOB, mo2203aY());
        a.mo7932a(cup.IMAGE_LABELING_JOB, mo2274bq());
        a.mo7932a(cup.THUMBNAIL_JOB, mo2275br());
        return a.mo7930a();
    }

    /* renamed from: de */
    private final Map m2734de() {
        return hsu.m12068a(hgz.ON_CHARGER, mo2336m(), hgz.ON_NETWORK_CONNECTED, mo2337n(), hgz.ON_NETWORK_UNMETERED, mo2338o());
    }

    /* renamed from: ca */
    public final dgo mo2301ca() {
        Object obj;
        Object obj2 = this.f2941ev;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2941ev;
                if (obj instanceof iok) {
                    dgo dgo = new dgo(ftz.m9622a(this.f2702a), mo2257bZ(), m2747dr(), mo2314cn());
                    this.f2941ev = iog.m14219a(this.f2941ev, dgo);
                    obj = dgo;
                }
            }
            obj2 = obj;
        }
        return (dgo) obj2;
    }

    /* renamed from: aN */
    public final cyr mo2192aN() {
        return new cyr(mo2214aj());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: cxo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: cxo} */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: bn */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.cxo mo2271bn() {
        /*
            r10 = this;
            java.lang.Object r0 = r10.f2903dp
            boolean r1 = r0 instanceof p000.iok
            if (r1 == 0) goto L_0x004a
            monitor-enter(r0)
            java.lang.Object r1 = r10.f2903dp     // Catch:{ all -> 0x0047 }
            boolean r2 = r1 instanceof p000.iok     // Catch:{ all -> 0x0047 }
            if (r2 == 0) goto L_0x0043
            cyr r4 = r10.mo2192aN()     // Catch:{ all -> 0x0047 }
            iel r5 = r10.mo2314cn()     // Catch:{ all -> 0x0047 }
            java.lang.Object r1 = r10.mo2332i()     // Catch:{ all -> 0x0047 }
            r6 = r1
            gus r6 = (p000.gus) r6     // Catch:{ all -> 0x0047 }
            iqk r1 = r10.f2901dn     // Catch:{ all -> 0x0047 }
            if (r1 != 0) goto L_0x0029
            bjv r1 = new bjv     // Catch:{ all -> 0x0047 }
            r2 = 66
            r1.<init>(r10, r2)     // Catch:{ all -> 0x0047 }
            r10.f2901dn = r1     // Catch:{ all -> 0x0047 }
        L_0x0029:
            inw r7 = p000.iog.m14218a(r1)     // Catch:{ all -> 0x0047 }
            com.google.android.apps.photosgo.environment.BuildType r8 = p000.ceo.m4214b()     // Catch:{ all -> 0x0047 }
            cjr r9 = r10.mo2270bm()     // Catch:{ all -> 0x0047 }
            cxo r1 = new cxo     // Catch:{ all -> 0x0047 }
            r3 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0047 }
            java.lang.Object r2 = r10.f2903dp     // Catch:{ all -> 0x0047 }
            java.lang.Object r2 = p000.iog.m14219a(r2, r1)     // Catch:{ all -> 0x0047 }
            r10.f2903dp = r2     // Catch:{ all -> 0x0047 }
        L_0x0043:
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            r0 = r1
            goto L_0x004b
        L_0x0047:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            throw r1
        L_0x004a:
        L_0x004b:
            cxo r0 = (p000.cxo) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bjw.mo2271bn():cxo");
    }

    /* renamed from: bu */
    public final deq mo2278bu() {
        Object obj;
        Object obj2 = this.f2908du;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2908du;
                if (obj instanceof iok) {
                    obj = new deq();
                    this.f2908du = iog.m14219a(this.f2908du, obj);
                }
            }
            obj2 = obj;
        }
        return (deq) obj2;
    }

    /* renamed from: dy */
    private final fzx m2754dy() {
        Object obj;
        Object obj2 = this.f2871dJ;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2871dJ;
                if (obj instanceof iok) {
                    hfu g = hfv.m11397g();
                    g.f12674a = "MediaStoreVersion";
                    g.mo7383a(cyu.f6048c);
                    obj = (fzx) iol.m14229a((Object) mo2328e().mo7385a((hfv) iol.m14229a((Object) g.mo7382a(), "Cannot return null from a non-@Nullable @Provides method")), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2871dJ = iog.m14219a(this.f2871dJ, obj);
                }
            }
            obj2 = obj;
        }
        return (fzx) obj2;
    }

    /* renamed from: an */
    public final dgp mo2218an() {
        return new dgp(m2722cV());
    }

    /* renamed from: x */
    public final Map mo2347x() {
        Object obj;
        Object obj2 = this.f2790bi;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2790bi;
                if (obj instanceof iok) {
                    Map dC = m2729dC();
                    Map w = mo2346w();
                    hsq a = hsu.m12065a(dC.size());
                    for (Map.Entry entry : dC.entrySet()) {
                        String str = (String) w.get(entry.getKey());
                        if (str != null) {
                            String str2 = (String) entry.getKey();
                            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + str.length());
                            sb.append(str2);
                            sb.append("#");
                            sb.append(str);
                            a.mo7932a(sb.toString(), (gww) entry.getValue());
                        } else {
                            a.mo7933a(entry);
                        }
                    }
                    obj = (Map) iol.m14229a((Object) a.mo7930a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2790bi = iog.m14219a(this.f2790bi, obj);
                }
            }
            obj2 = obj;
        }
        return (Map) obj2;
    }

    /* renamed from: bY */
    public final NativeRenderer mo2256bY() {
        Object obj;
        Object obj2 = this.f2940eu;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2940eu;
                if (obj instanceof iok) {
                    NativeRenderer nativeRenderer = new NativeRenderer(mo2229ay());
                    this.f2940eu = iog.m14219a(this.f2940eu, nativeRenderer);
                    obj = nativeRenderer;
                }
            }
            obj2 = obj;
        }
        return (NativeRenderer) obj2;
    }

    /* renamed from: dv */
    private final iqc m2751dv() {
        return new iqc(mo2160K());
    }

    /* renamed from: bf */
    public final Object mo2263bf() {
        Object obj;
        Object obj2;
        Object obj3 = this.f2894dg;
        if (!(obj3 instanceof iok)) {
            return obj3;
        }
        synchronized (obj3) {
            obj = this.f2894dg;
            if (obj instanceof iok) {
                Context a = ftz.m9622a(this.f2702a);
                PackageManager cS = m2719cS();
                Object obj4 = this.f2893df;
                if (obj4 instanceof iok) {
                    synchronized (obj4) {
                        obj2 = this.f2893df;
                        if (obj2 instanceof iok) {
                            cjq a2 = cjr.m4407a(ceo.m4214b());
                            a2.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD};
                            a2.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD};
                            obj2 = (cjr) iol.m14229a((Object) a2.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                            this.f2893df = iog.m14219a(this.f2893df, obj2);
                        }
                    }
                    obj4 = obj2;
                }
                dhf dhf = new dhf(a, cS, (cjr) obj4);
                this.f2894dg = iog.m14219a(this.f2894dg, dhf);
                obj = dhf;
            }
        }
        return obj;
    }

    /* renamed from: cP */
    public static final ddu m2717cP() {
        return new ddu();
    }

    /* renamed from: cZ */
    private final hhh m2726cZ() {
        Object obj;
        Object obj2 = this.f2717aO;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2717aO;
                if (obj instanceof iok) {
                    hhh hhh = new hhh(ftz.m9622a(this.f2702a), m2719cS(), m2725cY());
                    this.f2717aO = iog.m14219a(this.f2717aO, hhh);
                    obj = hhh;
                }
            }
            obj2 = obj;
        }
        return (hhh) obj2;
    }

    /* renamed from: cD */
    public final hhi mo2288cD() {
        return new hhi(m2726cZ());
    }

    /* renamed from: bb */
    public final ble mo2259bb() {
        dyr ba = mo2258ba();
        ba.getClass();
        return (ble) iol.m14229a((Object) new ble(hto.m12120a((Object) (Runnable) iol.m14229a((Object) new dys(ba), "Cannot return null from a non-@Nullable @Provides method"))), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: aI */
    public final ble mo2187aI() {
        bnq aH = mo2186aH();
        aH.getClass();
        return (ble) iol.m14229a((Object) new ble(hto.m12121a((Object) (Runnable) iol.m14229a((Object) new bnz(aH), "Cannot return null from a non-@Nullable @Provides method"), (Object) (Runnable) iol.m14229a((Object) new chx((gus) mo2332i()), "Cannot return null from a non-@Nullable @Provides method"))), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: aJ */
    public final iqk mo2188aJ() {
        iqk iqk = this.f2828cT;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 59);
        this.f2828cT = bjv;
        return bjv;
    }

    /* renamed from: bo */
    public final ble mo2272bo() {
        bnq aH = mo2186aH();
        aH.getClass();
        cxo bn = mo2271bn();
        bn.getClass();
        return (ble) iol.m14229a((Object) new ble(hto.m12121a((Object) (Runnable) iol.m14229a((Object) new bnx(aH), "Cannot return null from a non-@Nullable @Provides method"), (Object) (Runnable) iol.m14229a((Object) new cxz(bn), "Cannot return null from a non-@Nullable @Provides method"))), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bH */
    public final iqk mo2239bH() {
        iqk iqk = this.f2887dZ;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 94);
        this.f2887dZ = bjv;
        return bjv;
    }

    /* renamed from: bl */
    public final ble mo2269bl() {
        bsz bc = mo2260bc();
        bc.getClass();
        Runnable runnable = (Runnable) iol.m14229a((Object) new bth(bc), "Cannot return null from a non-@Nullable @Provides method");
        iqk iqk = this.f2899dl;
        if (iqk == null) {
            iqk = new bjv(this, 68);
            this.f2899dl = iqk;
        }
        cml bk = mo2268bk();
        bk.getClass();
        return (ble) iol.m14229a((Object) new ble(hto.m12122a(runnable, (Runnable) iol.m14229a((Object) new djo(iog.m14218a(iqk)), "Cannot return null from a non-@Nullable @Provides method"), (Runnable) iol.m14229a((Object) new cmm(bk), "Cannot return null from a non-@Nullable @Provides method"))), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: db */
    private final hhk m2731db() {
        Object obj;
        Object obj2 = this.f2719aQ;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2719aQ;
                if (obj instanceof iok) {
                    hhk hhk = new hhk(ftz.m9622a(this.f2702a), m2719cS(), m2730da(), m2725cY());
                    this.f2719aQ = iog.m14219a(this.f2719aQ, hhk);
                    obj = hhk;
                }
            }
            obj2 = obj;
        }
        return (hhk) obj2;
    }

    /* renamed from: cE */
    public final hhl mo2289cE() {
        return new hhl(m2731db());
    }

    /* renamed from: dc */
    private final hhn m2732dc() {
        Object obj;
        Object obj2 = this.f2721aS;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2721aS;
                if (obj instanceof iok) {
                    hhn hhn = new hhn(ftz.m9622a(this.f2702a), m2719cS(), m2730da(), m2725cY());
                    this.f2721aS = iog.m14219a(this.f2721aS, hhn);
                    obj = hhn;
                }
            }
            obj2 = obj;
        }
        return (hhn) obj2;
    }

    /* renamed from: cF */
    public final hho mo2290cF() {
        return new hho(m2732dc());
    }

    /* renamed from: bt */
    public final ble mo2277bt() {
        bnq aH = mo2186aH();
        aH.getClass();
        return (ble) iol.m14229a((Object) new ble(hto.m12121a((Object) (Runnable) iol.m14229a((Object) new bny(aH), "Cannot return null from a non-@Nullable @Provides method"), (Object) (Runnable) iol.m14229a((Object) new chy((gus) mo2332i()), "Cannot return null from a non-@Nullable @Provides method"))), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bI */
    public final iqk mo2240bI() {
        iqk iqk = this.f2922ec;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 97);
        this.f2922ec = bjv;
        return bjv;
    }

    /* renamed from: ah */
    public final ble mo2212ah() {
        ees ag = mo2211ag();
        ag.getClass();
        return (ble) iol.m14229a((Object) eey.m7352a(hto.m12120a((Object) (Runnable) iol.m14229a((Object) new eet(ag), "Cannot return null from a non-@Nullable @Provides method"))), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: cQ */
    public static final ble m2718cQ() {
        return (ble) iol.m14229a((Object) new ble(hvf.f13465a), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bX */
    public final blc mo2255bX() {
        Object obj;
        Object obj2 = this.f2939et;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2939et;
                if (obj instanceof iok) {
                    blc blc = new blc(iog.m14218a(m2752dw()), iog.m14218a(m2746dq()), mo2314cn());
                    this.f2939et = iog.m14219a(this.f2939et, blc);
                    obj = blc;
                }
            }
            obj2 = obj;
        }
        return (blc) obj2;
    }

    /* renamed from: cS */
    private final PackageManager m2719cS() {
        return (PackageManager) iol.m14229a((Object) ftz.m9622a(this.f2702a).getPackageManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: c */
    public final hfq mo2284c() {
        hfq hfq = this.f2752ax;
        if (hfq != null) {
            return hfq;
        }
        hfq hfq2 = new hfq(ftz.m9622a(this.f2702a));
        this.f2752ax = hfq2;
        return hfq2;
    }

    /* renamed from: aT */
    public final cjh mo2198aT() {
        return new cjh(mo2214aj());
    }

    /* renamed from: aG */
    public final iqe mo2185aG() {
        return new iqe(mo2160K());
    }

    /* renamed from: aj */
    public final bpt mo2214aj() {
        return (bpt) iol.m14229a((Object) cyt.m5751a(mo2213ai(), mo2314cn()), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: ai */
    public final hge mo2213ai() {
        Object obj;
        Object obj2 = this.f2852cr;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2852cr;
                if (obj instanceof iok) {
                    obj = (hge) iol.m14229a((Object) m2745dp().mo7398a("media_db", cyt.f6047a), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2852cr = iog.m14219a(this.f2852cr, obj);
                }
            }
            obj2 = obj;
        }
        return (hge) obj2;
    }

    /* renamed from: ap */
    public final cwq mo2220ap() {
        iqk iqk = this.f2855cu;
        if (iqk == null) {
            iqk = new bjv(this, 55);
            this.f2855cu = iqk;
        }
        inw a = iog.m14218a(iqk);
        iel cn = mo2314cn();
        iqk iqk2 = this.f2858cx;
        if (iqk2 == null) {
            iqk2 = new bjv(this, 56);
            this.f2858cx = iqk2;
        }
        return new cwq(a, cn, iog.m14218a(iqk2));
    }

    /* renamed from: ao */
    public final bka mo2219ao() {
        Object obj;
        int i;
        Object obj2;
        Object obj3 = this.f2857cw;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj = this.f2857cw;
                if (obj instanceof iok) {
                    Context a = ftz.m9622a(this.f2702a);
                    PackageManager cS = m2719cS();
                    dgp an = mo2218an();
                    BuildType b = ceo.m4214b();
                    Object obj4 = this.f2856cv;
                    if (obj4 instanceof iok) {
                        synchronized (obj4) {
                            obj2 = this.f2856cv;
                            if (obj2 instanceof iok) {
                                obj2 = (biq) iol.m14229a((Object) new bir(ftz.m9622a(this.f2702a)), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2856cv = iog.m14219a(this.f2856cv, obj2);
                            }
                        }
                        obj4 = obj2;
                    }
                    biq biq = (biq) obj4;
                    try {
                        PackageInfo packageInfo = cS.getPackageInfo(a.getPackageName(), 0);
                        iir g = bka.f2975h.mo8793g();
                        if (packageInfo.versionName != null) {
                            String str = packageInfo.versionName;
                            if (g.f14319c) {
                                g.mo8751b();
                                g.f14319c = false;
                            }
                            bka bka = (bka) g.f14318b;
                            str.getClass();
                            bka.f2977a |= 1;
                            bka.f2978b = str;
                        }
                        int i2 = packageInfo.versionCode;
                        if (g.f14319c) {
                            g.mo8751b();
                            g.f14319c = false;
                        }
                        bka bka2 = (bka) g.f14318b;
                        int i3 = 2;
                        bka2.f2977a |= 2;
                        bka2.f2979c = i2;
                        int a2 = an.mo4122a();
                        if (g.f14319c) {
                            g.mo8751b();
                            g.f14319c = false;
                        }
                        bka bka3 = (bka) g.f14318b;
                        bka3.f2977a |= 16;
                        bka3.f2982f = a2;
                        String packageName = a.getPackageName();
                        if ((packageInfo.applicationInfo.flags & 1) != 0) {
                            i = 2;
                        } else {
                            String installerPackageName = cS.getInstallerPackageName(packageName);
                            i = !"com.android.vending".equals(installerPackageName) ? !TextUtils.isEmpty(installerPackageName) ? 4 : 5 : 3;
                        }
                        if (g.f14319c) {
                            g.mo8751b();
                            g.f14319c = false;
                        }
                        bka bka4 = (bka) g.f14318b;
                        bka4.f2981e = i - 1;
                        bka4.f2977a |= 8;
                        BuildType buildType = BuildType.DEV;
                        int ordinal = b.ordinal();
                        if (ordinal == 0) {
                            i3 = 5;
                        } else if (ordinal == 1) {
                            i3 = 4;
                        } else if (ordinal == 2) {
                            i3 = 3;
                        } else if (ordinal != 3) {
                            String valueOf = String.valueOf(b);
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 56);
                            sb.append("No logging BuildType corresponding to client build type ");
                            sb.append(valueOf);
                            throw new IllegalStateException(sb.toString());
                        }
                        if (g.f14319c) {
                            g.mo8751b();
                            g.f14319c = false;
                        }
                        bka bka5 = (bka) g.f14318b;
                        bka5.f2980d = i3 - 1;
                        bka5.f2977a |= 4;
                        boolean a3 = biq.mo2092a();
                        if (g.f14319c) {
                            g.mo8751b();
                            g.f14319c = false;
                        }
                        bka bka6 = (bka) g.f14318b;
                        bka6.f2977a |= 32;
                        bka6.f2983g = a3;
                        obj = (bka) iol.m14229a((Object) (bka) g.mo8770g(), "Cannot return null from a non-@Nullable @Provides method");
                        this.f2857cw = iog.m14219a(this.f2857cw, obj);
                    } catch (PackageManager.NameNotFoundException e) {
                        throw new IllegalStateException(e);
                    }
                }
            }
            obj3 = obj;
        }
        return (bka) obj3;
    }

    /* renamed from: am */
    public final eja mo2217am() {
        Object obj;
        Object obj2;
        Object obj3 = this.f2854ct;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj = this.f2854ct;
                if (obj instanceof iok) {
                    Context a = ftz.m9622a(this.f2702a);
                    Object obj4 = this.f2853cs;
                    if (obj4 instanceof iok) {
                        synchronized (obj4) {
                            obj2 = this.f2853cs;
                            if (obj2 instanceof iok) {
                                obj2 = new eke();
                                this.f2853cs = iog.m14219a(this.f2853cs, obj2);
                            }
                        }
                        obj4 = obj2;
                    }
                    eke eke = (eke) obj4;
                    obj = (eja) iol.m14229a((Object) new eja(a, "PHOTOS_GO"), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2854ct = iog.m14219a(this.f2854ct, obj);
                }
            }
            obj3 = obj;
        }
        return (eja) obj3;
    }

    /* renamed from: Y */
    public final fkl mo2174Y() {
        Object obj;
        Object obj2 = this.f2779bX;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2779bX;
                if (obj instanceof iok) {
                    hpy b = hpy.m11893b((fkv) iol.m14229a((Object) flw.m9187a((Application) ftz.m9622a(this.f2702a)), "Cannot return null from a non-@Nullable @Provides method"));
                    Context a = ftz.m9622a(this.f2702a);
                    iqk iqk = this.f2778bW;
                    if (iqk == null) {
                        iqk = new bjv(this, 41);
                        this.f2778bW = iqk;
                    }
                    iel cn = mo2314cn();
                    fml h = fmm.m9221h();
                    h.f10052a = gte.m10774a(cn);
                    hpy b2 = hpy.m11893b((fmm) iol.m14229a((Object) h.mo5971a(), "Cannot return null from a non-@Nullable @Provides method"));
                    fkv fkv = (fkv) b.mo7645a(flw.m9187a((Application) a));
                    iqk.getClass();
                    fkv.f9930f = new fon(iqk);
                    fkv.f9931g = (fmm) ((hqc) b2).f13250a;
                    iqk iqk2 = (iqk) ife.m12898e((Object) fkv.f9930f);
                    fmm a2 = fkv.f9931g == null ? fmm.m9221h().mo5971a() : fkv.f9931g;
                    fna fna = fkl.f9892a;
                    hqk hqk = fkv.f9928d;
                    fkt fkt = new fkt(fkv);
                    hqk hqk2 = fkv.f9929e;
                    obj = (fkl) iol.m14229a((Object) fkl.m9083a(((fhr) ife.m12898e((Object) fkv.f9926b)).mo5734a((Application) ife.m12898e((Object) fkv.f9925a), iqk2, (hqk) ife.m12898e((Object) fkv.f9927c), fkt, a2, fku.f9924a)), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2779bX = iog.m14219a(this.f2779bX, obj);
                }
            }
            obj2 = obj;
        }
        return (fkl) obj2;
    }

    /* renamed from: X */
    public final fld mo2173X() {
        Object obj;
        boolean z;
        boolean z2;
        boolean z3;
        Object obj2 = this.f2777bV;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2777bV;
                if (obj instanceof iok) {
                    iqk iqk = this.f2776bU;
                    if (iqk == null) {
                        iqk = new bjv(this, 42);
                        this.f2776bU = iqk;
                    }
                    fpg fpg = new fpg((byte[]) null);
                    fpg.f10196a = iqk;
                    if (!fpg.f10197b.isEmpty() || !fpg.f10198c.isEmpty() || fpg.f10196a != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    ife.m12896d(z);
                    fpi fpi = (fpi) iol.m14229a((Object) new fpi(fpg.f10197b, fpg.f10198c, fpg.f10196a), "Cannot return null from a non-@Nullable @Provides method");
                    boolean d = ((gxb) m2743dn().f14645a.mo2097a()).mo7170a("com.google.android.apps.photosgo 3").mo7172d();
                    flx flx = new flx((byte[]) null);
                    flx.mo5955a(false);
                    flx.f10004b = 3;
                    flx.f10005c = false;
                    flx.f10006d = hph.f13219a;
                    flx.f10007e = false;
                    flx.f10008f = false;
                    flx.mo5955a(d);
                    String str = "";
                    if (flx.f10003a == null) {
                        str = " enabled";
                    }
                    if (flx.f10004b == null) {
                        str = str.concat(" sampleRatePerSecond");
                    }
                    if (flx.f10005c == null) {
                        str = String.valueOf(str).concat(" recordMetricPerProcess");
                    }
                    if (flx.f10007e == null) {
                        str = String.valueOf(str).concat(" forceGcBeforeRecordMemory");
                    }
                    if (flx.f10008f == null) {
                        str = String.valueOf(str).concat(" captureRssHwm");
                    }
                    if (str.isEmpty()) {
                        hpy b = hpy.m11893b((fly) iol.m14229a((Object) new fil(flx.f10003a.booleanValue(), flx.f10004b.intValue(), flx.f10005c.booleanValue(), flx.f10006d, flx.f10007e.booleanValue(), flx.f10008f.booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                        boolean d2 = ((gxb) m2743dn().f14645a.mo2097a()).mo7170a("com.google.android.apps.photosgo 6").mo7172d();
                        fmr fmr = new fmr((byte[]) null);
                        fmr.mo5975a(false);
                        fmr.f10063c = 10;
                        fmr.f10062b = Float.valueOf(1.0f);
                        fmr.f10064d = hph.f13219a;
                        fmr.mo5975a(d2);
                        String str2 = "";
                        if (fmr.f10061a == null) {
                            str2 = " enabled";
                        }
                        if (fmr.f10062b == null) {
                            str2 = str2.concat(" samplingProbability");
                        }
                        if (fmr.f10063c == null) {
                            str2 = String.valueOf(str2).concat(" sampleRatePerSecond");
                        }
                        if (str2.isEmpty()) {
                            fio fio = new fio(fmr.f10061a.booleanValue(), fmr.f10062b.floatValue(), fmr.f10063c.intValue(), fmr.f10064d);
                            if (fio.f9743b >= 0) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            ife.m12876b(z2, (Object) "Samples rate per second shall be >= 0");
                            float f = fio.f9742a;
                            if (f <= 0.0f || f > 1.0f) {
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            ife.m12876b(z3, (Object) "Sampling Probability shall be > 0 and <= 1");
                            hpy b2 = hpy.m11893b((fms) iol.m14229a((Object) fio, "Cannot return null from a non-@Nullable @Provides method"));
                            ((gxb) m2743dn().f14645a.mo2097a()).mo7170a("com.google.android.apps.photosgo 4").mo7172d();
                            hpy b3 = hpy.m11893b((frz) iol.m14229a((Object) new frz((byte[]) null), "Cannot return null from a non-@Nullable @Provides method"));
                            boolean d3 = ((gxb) m2743dn().f14645a.mo2097a()).mo7170a("com.google.android.apps.photosgo 2").mo7172d();
                            fle fle = new fle((byte[]) null);
                            fle.f9973b = Float.valueOf(100.0f);
                            fpa fpa = fpa.f10185b;
                            if (fpa != null) {
                                fle.f9974c = fpa;
                                fle.mo5917a(false);
                                fle.f9975d = false;
                                fle.mo5917a(d3);
                                String str3 = "";
                                if (fle.f9972a == null) {
                                    str3 = " enabled";
                                }
                                if (fle.f9973b == null) {
                                    str3 = str3.concat(" startupSamplePercentage");
                                }
                                if (fle.f9974c == null) {
                                    str3 = String.valueOf(str3).concat(" stackTraceTransmitter");
                                }
                                if (fle.f9975d == null) {
                                    str3 = String.valueOf(str3).concat(" deferredInitLogging");
                                }
                                if (str3.isEmpty()) {
                                    hpy b4 = hpy.m11893b((flf) iol.m14229a((Object) new fih(fle.f9972a.booleanValue(), fle.f9973b.floatValue(), fle.f9974c, fle.f9975d.booleanValue()), "Cannot return null from a non-@Nullable @Provides method"));
                                    boolean d4 = ((gxb) m2743dn().f14645a.mo2097a()).mo7170a("com.google.android.apps.photosgo 8").mo7172d();
                                    boolean d5 = ((gxb) m2743dn().f14645a.mo2097a()).mo7170a("com.google.android.apps.photosgo 9").mo7172d();
                                    flz flz = new flz((byte[]) null);
                                    flz.f10009a = d4;
                                    flg flg = new flg((byte[]) null);
                                    flg.mo5918a(false);
                                    flg.f9977b = 5;
                                    flg.mo5918a(d5);
                                    Pattern compile = Pattern.compile(".*");
                                    if (flg.f9978c == null) {
                                        if (flg.f9979d != null) {
                                            flg.f9978c = hso.m12048j();
                                            flg.f9978c.mo7907b((Iterable) flg.f9979d);
                                            flg.f9979d = null;
                                        } else {
                                            flg.f9978c = hso.m12048j();
                                        }
                                    }
                                    flg.f9978c.mo7908c(compile);
                                    String str4 = " enabled";
                                    hsj hsj = flg.f9978c;
                                    if (hsj != null) {
                                        flg.f9979d = hsj.mo7905a();
                                    } else if (flg.f9979d == null) {
                                        flg.f9979d = hso.m12047f();
                                    }
                                    if (flg.f9976a != null) {
                                        str4 = "";
                                    }
                                    if (flg.f9977b == null) {
                                        str4 = str4.concat(" maxFolderDepth");
                                    }
                                    if (str4.isEmpty()) {
                                        flz.f10011c = hpy.m11893b(new fii(flg.f9976a.booleanValue(), flg.f9977b.intValue(), flg.f9979d));
                                        boolean z4 = flz.f10009a;
                                        boolean z5 = flz.f10010b;
                                        hpy b5 = hpy.m11893b((fma) iol.m14229a((Object) new fma(z4, flz.f10011c), "Cannot return null from a non-@Nullable @Provides method"));
                                        boolean d6 = ((gxb) m2743dn().f14645a.mo2097a()).mo7170a("com.google.android.apps.photosgo 5").mo7172d();
                                        flq flq = new flq((byte[]) null);
                                        flq.mo5927a(false);
                                        flq.mo5928b(false);
                                        int i = Build.VERSION.SDK_INT;
                                        flq.mo5926a();
                                        flq.f9995d = 10;
                                        flq.f9996e = hph.f13219a;
                                        flq.mo5927a(d6);
                                        flq.mo5928b(true);
                                        flq.mo5926a();
                                        String str5 = "";
                                        if (flq.f9992a == null) {
                                            str5 = " enabled";
                                        }
                                        if (flq.f9993b == null) {
                                            str5 = str5.concat(" monitorActivities");
                                        }
                                        if (flq.f9994c == null) {
                                            str5 = String.valueOf(str5).concat(" useAnimator");
                                        }
                                        if (flq.f9995d == null) {
                                            str5 = String.valueOf(str5).concat(" sampleRatePerSecond");
                                        }
                                        if (str5.isEmpty()) {
                                            hpy b6 = hpy.m11893b((flr) iol.m14229a((Object) new fik(flq.f9992a.booleanValue(), flq.f9993b.booleanValue(), flq.f9994c.booleanValue(), flq.f9995d.intValue(), flq.f9996e), "Cannot return null from a non-@Nullable @Provides method"));
                                            hpy b7 = hpy.m11893b(mo2164O());
                                            boolean d7 = ((gxb) m2743dn().f14645a.mo2097a()).mo7170a("com.google.android.apps.photosgo 1").mo7172d();
                                            fkx c = fky.m9108c();
                                            c.mo5904a(d7);
                                            hpy b8 = hpy.m11893b((fky) iol.m14229a((Object) c.mo5903a(), "Cannot return null from a non-@Nullable @Provides method"));
                                            fxk.m9836c();
                                            fla fla = new fla((byte[]) null);
                                            fla.f9937a = (iqk) fpi.f10201a.mo2652a();
                                            fla.f9939c = hpy.m11894c((fly) ((hqc) b).f13250a);
                                            fla.f9940d = hpy.m11894c((fms) ((hqc) b2).f13250a);
                                            fla.f9941e = hpy.m11894c((flf) ((hqc) b4).f13250a);
                                            hpy.m11894c((frz) ((hqc) b3).f13250a);
                                            fla.f9942f = hpy.m11894c((fma) ((hqc) b5).f13250a);
                                            fla.f9943g = hpy.m11894c((flr) ((hqc) b6).f13250a);
                                            fla.f9944h = hpy.m11894c((fmq) ((hqc) b7).f13250a);
                                            fla.f9946j = hpy.m11894c((fky) ((hqc) b8).f13250a);
                                            obj = (fld) iol.m14229a((Object) fld.m9134a(new flb(fla.f9937a, fla.f9938b, fla.f9939c, fla.f9940d, fla.f9941e, fla.f9942f, fla.f9943g, fla.f9944h, fla.f9945i, fla.f9946j, fla.f9947k)), "Cannot return null from a non-@Nullable @Provides method");
                                            this.f2777bV = iog.m14219a(this.f2777bV, obj);
                                        } else {
                                            String valueOf = String.valueOf(str5);
                                            throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
                                        }
                                    } else {
                                        String valueOf2 = String.valueOf(str4);
                                        throw new IllegalStateException(valueOf2.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf2));
                                    }
                                } else {
                                    String valueOf3 = String.valueOf(str3);
                                    throw new IllegalStateException(valueOf3.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf3));
                                }
                            } else {
                                throw new NullPointerException("Null stackTraceTransmitter");
                            }
                        } else {
                            String valueOf4 = String.valueOf(str2);
                            throw new IllegalStateException(valueOf4.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf4));
                        }
                    } else {
                        String valueOf5 = String.valueOf(str);
                        throw new IllegalStateException(valueOf5.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf5));
                    }
                }
            }
            obj2 = obj;
        }
        return (fld) obj2;
    }

    /* renamed from: dn */
    private final iqf m2743dn() {
        return new iqf(mo2160K());
    }

    /* renamed from: O */
    public final fmq mo2164O() {
        boolean d = ((gxb) m2743dn().f14645a.mo2097a()).mo7170a("com.google.android.apps.photosgo 7").mo7172d();
        fmn e = fmq.m9233e();
        e.mo5973a(d);
        return (fmq) iol.m14229a((Object) e.mo5972a(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bD */
    public final cjr mo2235bD() {
        Object obj;
        Object obj2 = this.f2877dP;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2877dP;
                if (obj instanceof iok) {
                    BuildType b = ceo.m4214b();
                    boolean d = ((gxb) m2751dv().f14642a.mo2097a()).mo7170a("com.google.android.apps.photosgo 52").mo7172d();
                    cjq a = cjr.m4407a(b);
                    a.f4512d = d;
                    a.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                    a.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                    obj = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2877dP = iog.m14219a(this.f2877dP, obj);
                }
            }
            obj2 = obj;
        }
        return (cjr) obj2;
    }

    /* renamed from: bE */
    public final dhv mo2236bE() {
        cjr bD = mo2235bD();
        iqk iqk = this.f2879dR;
        if (iqk == null) {
            iqk = new bjv(this, 88);
            this.f2879dR = iqk;
        }
        return die.m6147a(bD, iog.m14218a(iqk));
    }

    /* renamed from: e */
    public final hfx mo2328e() {
        return new hfx(mo2314cn(), mo2284c(), mo2327d());
    }

    /* renamed from: d */
    public final fxr mo2327d() {
        Object obj;
        Object obj2 = this.f2753ay;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2753ay;
                if (obj instanceof iok) {
                    Context a = ftz.m9622a(this.f2702a);
                    exp.m8335a();
                    obj = (fxr) iol.m14229a((Object) new fxr(hso.m12033a((Object) new fxu(new fxt(a)))), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2753ay = iog.m14219a(this.f2753ay, obj);
                }
            }
            obj2 = obj;
        }
        return (fxr) obj2;
    }

    /* renamed from: T */
    public final hbc mo2169T() {
        Context a = ftz.m9622a(this.f2702a);
        iqk iqk = this.f2770bO;
        if (iqk == null) {
            iqk = new bjv(this, 30);
            this.f2770bO = iqk;
        }
        return (hbc) iol.m14229a((Object) new hcm(a, iqk, mo2168S()), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bK */
    public final ffs mo2242bK() {
        return (ffs) iol.m14229a((Object) new cwt(mo2219ao()), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: aS */
    public final cut mo2197aS() {
        iqk iqk = this.f2832cX;
        if (iqk == null) {
            iqk = new bjv(this, 61);
            this.f2832cX = iqk;
        }
        return (cut) iol.m14229a((Object) new chk(iog.m14218a(iqk)), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: aY */
    public final cut mo2203aY() {
        iqk iqk = this.f2888da;
        if (iqk == null) {
            iqk = new bjv(this, 64);
            this.f2888da = iqk;
        }
        return (cut) iol.m14229a((Object) new chm(iog.m14218a(iqk)), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: aM */
    public final cut mo2191aM() {
        iqk iqk = this.f2829cU;
        if (iqk == null) {
            iqk = new bjv(this, 58);
            this.f2829cU = iqk;
        }
        return (cut) iol.m14229a((Object) new chi(iog.m14218a(iqk)), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: aQ */
    public final cut mo2195aQ() {
        iqk iqk = this.f2830cV;
        if (iqk == null) {
            iqk = new bjv(this, 60);
            this.f2830cV = iqk;
        }
        return (cut) iol.m14229a((Object) new chj(iog.m14218a(iqk)), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: aV */
    public final cut mo2200aV() {
        iqk iqk = this.f2833cY;
        if (iqk == null) {
            iqk = new bjv(this, 63);
            this.f2833cY = iqk;
        }
        return (cut) iol.m14229a((Object) new chl(iog.m14218a(iqk)), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bq */
    public final cut mo2274bq() {
        iqk iqk = this.f2905dr;
        if (iqk == null) {
            iqk = new bjv(this, 65);
            this.f2905dr = iqk;
        }
        return (cut) iol.m14229a((Object) new csj(iog.m14218a(iqk)), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: U */
    public final hbc mo2170U() {
        return (hbc) iol.m14229a((Object) hpd.m11867a(ftz.m9622a(this.f2702a), hph.f13219a), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: ab */
    public final hbc mo2206ab() {
        return (hbc) iol.m14229a((Object) new hff(ftz.m9622a(this.f2702a), (fsz) iol.m14229a((Object) new fsz(), "Cannot return null from a non-@Nullable @Provides method"), (fta) iol.m14229a((Object) new cwi(((gxb) new iqg(mo2160K()).f14646a.mo2097a()).mo7170a("com.google.android.apps.photosgo 11").mo7172d()), "Cannot return null from a non-@Nullable @Provides method")), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: Z */
    public final hbc mo2175Z() {
        iqk iqk = this.f2780bY;
        if (iqk == null) {
            iqk = new bjv(this, 40);
            this.f2780bY = iqk;
        }
        return (hbc) iol.m14229a((Object) new hfa(iqk), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: aa */
    public final hbc mo2205aa() {
        Context a = ftz.m9622a(this.f2702a);
        long dm = m2742dm();
        Application application = (Application) a;
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        return (hbc) iol.m14229a((Object) new hfb(application, new hfd(atomicBoolean, application), atomicBoolean, dm), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: br */
    public final cut mo2275br() {
        iqk iqk = this.f2906ds;
        if (iqk == null) {
            iqk = new bjv(this, 72);
            this.f2906ds = iqk;
        }
        return (cut) iol.m14229a((Object) new cpe(iog.m14218a(iqk)), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: ae */
    public final hgd mo2209ae() {
        Object obj;
        Object obj2 = this.f2846cl;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2846cl;
                if (obj instanceof iok) {
                    obj = new hgd();
                    this.f2846cl = iog.m14219a(this.f2846cl, obj);
                }
            }
            obj2 = obj;
        }
        return (hgd) obj2;
    }

    /* renamed from: i */
    public final Object mo2332i() {
        Object obj;
        Object obj2 = this.f2709aG;
        if (!(obj2 instanceof iok)) {
            return obj2;
        }
        synchronized (obj2) {
            obj = this.f2709aG;
            if (obj instanceof iok) {
                obj = new gus((byte[]) null);
                this.f2709aG = iog.m14219a(this.f2709aG, obj);
            }
        }
        return obj;
    }

    /* renamed from: ay */
    public final cwf mo2229ay() {
        Object obj;
        Object obj2 = this.f2812cD;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2812cD;
                if (obj instanceof iok) {
                    cwf cwf = new cwf(ftz.m9622a(this.f2702a), m2720cT());
                    this.f2812cD = iog.m14219a(this.f2812cD, cwf);
                    obj = cwf;
                }
            }
            obj2 = obj;
        }
        return (cwf) obj2;
    }

    /* renamed from: co */
    public final ebi mo2315co() {
        return new ebi(ftz.m9622a(this.f2702a));
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, imp] */
    /* renamed from: dr */
    private final dbs m2747dr() {
        return dcy.m5935a(mo2223as(), mo2224at(), daw.m5836a(mo2226av(), mo2314cn()), mo2314cn());
    }

    /* renamed from: as */
    public final iqk mo2223as() {
        iqk iqk = this.f2809cA;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 57);
        this.f2809cA = bjv;
        return bjv;
    }

    /* renamed from: cU */
    private final iel m2721cU() {
        Object obj;
        Object obj2 = this.f2704aB;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2704aB;
                if (obj instanceof iok) {
                    iex iex = new iex();
                    iex.mo8476a("Scheduler Thread #%d");
                    iex.mo8475a();
                    obj = (iel) iol.m14229a((Object) ife.m12825a(Executors.newScheduledThreadPool(1, iex.m12778a(iex))), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2704aB = iog.m14219a(this.f2704aB, obj);
                }
            }
            obj2 = obj;
        }
        return (iel) obj2;
    }

    /* renamed from: ck */
    public final iqk mo2311ck() {
        iqk iqk = this.f2917eC;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 113);
        this.f2917eC = bjv;
        return bjv;
    }

    /* renamed from: al */
    public final cvm mo2216al() {
        return cvn.m5475a(mo2314cn());
    }

    /* renamed from: aC */
    public final cvm mo2181aC() {
        return cvn.m5475a(mo2314cn());
    }

    /* renamed from: bP */
    public final dgw mo2247bP() {
        goo l = mo2335l();
        Context a = ftz.m9622a(this.f2702a);
        dgu dgu = dgu.DOGFOOD_JOB;
        NotificationChannel notificationChannel = new NotificationChannel(dgu.DOGFOOD_JOB.name(), "Dogfood task processing notifications.", 2);
        notificationChannel.setDescription("Foreground notifications related to Dogfood Task execution.");
        hsu a2 = hsu.m12066a(dgu, (NotificationChannel) iol.m14229a((Object) notificationChannel, "Cannot return null from a non-@Nullable @Provides method"));
        ((NotificationManager) a.getSystemService(NotificationManager.class)).createNotificationChannels(new ArrayList(a2.values()));
        EnumMap enumMap = new EnumMap(dgu.class);
        for (Map.Entry entry : a2.entrySet()) {
            enumMap.put((dgu) entry.getKey(), ((NotificationChannel) entry.getValue()).getId());
        }
        return new dgw(l, (Map) iol.m14229a((Object) enumMap, "Cannot return null from a non-@Nullable @Provides method"), ftz.m9622a(this.f2702a), mo2246bO());
    }

    /* renamed from: cY */
    private final iqk m2725cY() {
        iqk iqk = this.f2716aN;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 5);
        this.f2716aN = bjv;
        return bjv;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: bjv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: een} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: een} */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: af */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.een mo2210af() {
        /*
            r8 = this;
            java.lang.Object r0 = r8.f2850cp
            boolean r1 = r0 instanceof p000.iok
            if (r1 == 0) goto L_0x0087
            monitor-enter(r0)
            java.lang.Object r1 = r8.f2850cp     // Catch:{ all -> 0x0084 }
            boolean r2 = r1 instanceof p000.iok     // Catch:{ all -> 0x0084 }
            if (r2 == 0) goto L_0x0080
            iqk r1 = r8.f2843ci     // Catch:{ all -> 0x0084 }
            if (r1 != 0) goto L_0x001c
            bjv r1 = new bjv     // Catch:{ all -> 0x0084 }
            r2 = 50
            r1.<init>(r8, r2)     // Catch:{ all -> 0x0084 }
            r8.f2843ci = r1     // Catch:{ all -> 0x0084 }
            r3 = r1
            goto L_0x001d
        L_0x001c:
            r3 = r1
        L_0x001d:
            java.lang.Object r1 = r8.f2848cn     // Catch:{ all -> 0x0084 }
            boolean r2 = r1 instanceof p000.iok     // Catch:{ all -> 0x0084 }
            if (r2 == 0) goto L_0x004c
            monitor-enter(r1)     // Catch:{ all -> 0x0084 }
            java.lang.Object r2 = r8.f2848cn     // Catch:{ all -> 0x0049 }
            boolean r4 = r2 instanceof p000.iok     // Catch:{ all -> 0x0049 }
            if (r4 == 0) goto L_0x0046
            hgf r2 = r8.m2745dp()     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = "sharing_db"
            hgj r5 = p000.eey.f8129a     // Catch:{ all -> 0x0049 }
            hge r2 = r2.mo7398a(r4, r5)     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = "Cannot return null from a non-@Nullable @Provides method"
            java.lang.Object r2 = p000.iol.m14229a((java.lang.Object) r2, (java.lang.String) r4)     // Catch:{ all -> 0x0049 }
            hge r2 = (p000.hge) r2     // Catch:{ all -> 0x0049 }
            java.lang.Object r4 = r8.f2848cn     // Catch:{ all -> 0x0049 }
            java.lang.Object r4 = p000.iog.m14219a(r4, r2)     // Catch:{ all -> 0x0049 }
            r8.f2848cn = r4     // Catch:{ all -> 0x0049 }
        L_0x0046:
            monitor-exit(r1)     // Catch:{ all -> 0x0049 }
            r1 = r2
            goto L_0x004d
        L_0x0049:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0049 }
            throw r2     // Catch:{ all -> 0x0084 }
        L_0x004c:
        L_0x004d:
            hge r1 = (p000.hge) r1     // Catch:{ all -> 0x0084 }
            iel r2 = r8.mo2314cn()     // Catch:{ all -> 0x0084 }
            bpt r1 = p000.eey.m7353a(r1, r2)     // Catch:{ all -> 0x0084 }
            java.lang.String r2 = "Cannot return null from a non-@Nullable @Provides method"
            java.lang.Object r1 = p000.iol.m14229a((java.lang.Object) r1, (java.lang.String) r2)     // Catch:{ all -> 0x0084 }
            r4 = r1
            bpt r4 = (p000.bpt) r4     // Catch:{ all -> 0x0084 }
            iel r5 = r8.mo2314cn()     // Catch:{ all -> 0x0084 }
            fty r1 = r8.f2702a     // Catch:{ all -> 0x0084 }
            android.content.Context r6 = p000.ftz.m9622a(r1)     // Catch:{ all -> 0x0084 }
            iqk r1 = r8.m2746dq()     // Catch:{ all -> 0x0084 }
            inw r7 = p000.iog.m14218a(r1)     // Catch:{ all -> 0x0084 }
            een r1 = new een     // Catch:{ all -> 0x0084 }
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0084 }
            java.lang.Object r2 = r8.f2850cp     // Catch:{ all -> 0x0084 }
            java.lang.Object r2 = p000.iog.m14219a(r2, r1)     // Catch:{ all -> 0x0084 }
            r8.f2850cp = r2     // Catch:{ all -> 0x0084 }
        L_0x0080:
            monitor-exit(r0)     // Catch:{ all -> 0x0084 }
            r0 = r1
            goto L_0x0088
        L_0x0084:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0084 }
            throw r1
        L_0x0087:
        L_0x0088:
            een r0 = (p000.een) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bjw.mo2210af():een");
    }

    /* renamed from: ag */
    public final ees mo2211ag() {
        Object obj;
        Object obj2 = this.f2851cq;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2851cq;
                if (obj instanceof iok) {
                    ees ees = new ees(mo2210af(), mo2314cn(), new edx(mo2210af(), ftz.m9622a(this.f2702a)), (gus) mo2332i());
                    this.f2851cq = iog.m14219a(this.f2851cq, ees);
                    obj = ees;
                }
            }
            obj2 = obj;
        }
        return (ees) obj2;
    }

    /* renamed from: dq */
    private final iqk m2746dq() {
        iqk iqk = this.f2849co;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 49);
        this.f2849co = bjv;
        return bjv;
    }

    /* renamed from: be */
    public final djh mo2262be() {
        return new djh(mo2214aj());
    }

    /* renamed from: bi */
    public final dil mo2266bi() {
        Object obj;
        cjr bd = mo2261bd();
        iqk iqk = this.f2896di;
        if (iqk == null) {
            iqk = new bjv(this, 70);
            this.f2896di = iqk;
        }
        inw a = iog.m14218a(iqk);
        if (bd.mo3175a()) {
            obj = (dil) a.mo9034a();
        } else {
            obj = new dit();
        }
        return (dil) iol.m14229a(obj, "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bj */
    public final dji mo2267bj() {
        Object obj;
        Object obj2;
        Object obj3 = this.f2898dk;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj = this.f2898dk;
                if (obj instanceof iok) {
                    cjr bd = mo2261bd();
                    iqk iqk = this.f2897dj;
                    if (iqk == null) {
                        iqk = new bjv(this, 69);
                        this.f2897dj = iqk;
                    }
                    inw a = iog.m14218a(iqk);
                    if (!bd.mo3175a()) {
                        obj2 = new djp();
                    } else {
                        obj2 = (dji) a.mo9034a();
                    }
                    obj = (dji) iol.m14229a(obj2, "Cannot return null from a non-@Nullable @Provides method");
                    this.f2898dk = iog.m14219a(this.f2898dk, obj);
                }
            }
            obj3 = obj;
        }
        return (dji) obj3;
    }

    /* renamed from: bd */
    public final cjr mo2261bd() {
        Object obj;
        Object obj2 = this.f2892de;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2892de;
                if (obj instanceof iok) {
                    BuildType b = ceo.m4214b();
                    boolean d = ((gxb) m2751dv().f14642a.mo2097a()).mo7170a("com.google.android.apps.photosgo 51").mo7172d();
                    cjq a = cjr.m4407a(b);
                    a.f4512d = d;
                    a.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                    a.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                    obj = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2892de = iog.m14219a(this.f2892de, obj);
                }
            }
            obj2 = obj;
        }
        return (cjr) obj2;
    }

    /* renamed from: dm */
    private final long m2742dm() {
        return ((hbe) ftz.m9622a(this.f2702a)).mo3320l();
    }

    /* renamed from: cp */
    public final eez mo2316cp() {
        return new eez(mo2210af(), mo2212ah(), mo2314cn());
    }

    /* renamed from: bF */
    public final fzx mo2237bF() {
        Object obj;
        Object obj2 = this.f2883dV;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2883dV;
                if (obj instanceof iok) {
                    hfu g = hfv.m11397g();
                    g.f12674a = "StorageAccess";
                    g.mo7383a(efy.f8178b);
                    obj = (fzx) iol.m14229a((Object) mo2328e().mo7385a((hfv) iol.m14229a((Object) g.mo7382a(), "Cannot return null from a non-@Nullable @Provides method")), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2883dV = iog.m14219a(this.f2883dV, obj);
                }
            }
            obj2 = obj;
        }
        return (fzx) obj2;
    }

    /* renamed from: bG */
    public final cjr mo2238bG() {
        Object obj;
        Object obj2 = this.f2884dW;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2884dW;
                if (obj instanceof iok) {
                    BuildType b = ceo.m4214b();
                    boolean d = ((gxb) new iqh(mo2160K()).f14647a.mo2097a()).mo7170a("com.google.android.apps.photosgo 50").mo7172d();
                    cjq a = cjr.m4407a(b);
                    a.f4512d = d;
                    a.f4510b = new BuildType[]{BuildType.DEV, BuildType.TEST, BuildType.DOGFOOD, BuildType.RELEASE};
                    a.f4511c = new BuildType[]{BuildType.DEV, BuildType.TEST};
                    obj = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2884dW = iog.m14219a(this.f2884dW, obj);
                }
            }
            obj2 = obj;
        }
        return (cjr) obj2;
    }

    /* renamed from: bk */
    public final cml mo2268bk() {
        Object obj;
        Object obj2 = this.f2900dm;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2900dm;
                if (obj instanceof iok) {
                    cml cml = new cml(ftz.m9622a(this.f2702a), (gus) mo2332i(), mo2314cn(), exn.m8328b());
                    this.f2900dm = iog.m14219a(this.f2900dm, cml);
                    obj = cml;
                }
            }
            obj2 = obj;
        }
        return (cml) obj2;
    }

    /* renamed from: C */
    public final iqk mo2152C() {
        iqk iqk = this.f2799br;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 24);
        this.f2799br = bjv;
        return bjv;
    }

    /* renamed from: w */
    public final Map mo2346w() {
        Object obj;
        Object obj2 = this.f2789bh;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2789bh;
                if (obj instanceof iok) {
                    gxp gxp = new gxp("com.google.android.apps.photosgo");
                    BuildType b = ceo.m4214b();
                    BuildType buildType = BuildType.DEV;
                    int ordinal = b.ordinal();
                    hsu a = hsu.m12066a(gxp, (String) iol.m14229a((Object) (ordinal == 0 || ordinal == 1) ? "dev" : ordinal != 2 ? ordinal != 3 ? "release" : "release" : "dogfood", "Cannot return null from a non-@Nullable @Provides method"));
                    Map dC = m2729dC();
                    hvf hvf = hvf.f13465a;
                    hsq a2 = hsu.m12065a(((hvb) a).f13455b);
                    HashSet<String> hashSet = new HashSet<>(hvf);
                    Iterator it = a.entrySet().iterator();
                    while (true) {
                        boolean z = false;
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry entry = (Map.Entry) it.next();
                        String a3 = ((gzx) entry.getKey()).mo7187a();
                        gww gww = (gww) dC.get(a3);
                        ife.m12879b(gww != null, "Subpackage prefix provided for unknown package: %s, known packages: %s", a3, dC.keySet());
                        String str = (String) entry.getValue();
                        if (hashSet.contains(a3)) {
                            if (gww == gww.USER || gww == gww.UI_USER) {
                                z = true;
                            }
                            ife.m12879b(z, "MultiCommit can only be specified on USER or UI_USER consistency tiers, but was specified on package %s which has tier %s", a3, gww);
                            String valueOf = String.valueOf(str);
                            str = valueOf.length() == 0 ? new String("@") : "@".concat(valueOf);
                            hashSet.remove(a3);
                        }
                        a2.mo7932a(a3, str);
                    }
                    for (String str2 : hashSet) {
                        gww gww2 = (gww) dC.get(str2);
                        ife.m12879b(gww2 != null, "MultiCommit provided for unknown package: %s, known packages: %s", str2, dC.keySet());
                        ife.m12879b(gww2 == gww.USER || gww2 == gww.UI_USER, "MultiCommit can only be specified on USER or UI_USER consistency tiers, but was specified on package %s which has tier %s", str2, gww2);
                        a2.mo7932a(str2, "@");
                    }
                    obj = (Map) iol.m14229a((Object) a2.mo7930a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2789bh = iog.m14219a(this.f2789bh, obj);
                }
            }
            obj2 = obj;
        }
        return (Map) obj2;
    }

    /* renamed from: y */
    public final gzy mo2348y() {
        Object obj;
        Object obj2 = this.f2791bj;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2791bj;
                if (obj instanceof iok) {
                    obj = (gzy) iol.m14229a((Object) new gye(mo2347x(), mo2346w()), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2791bj = iog.m14219a(this.f2791bj, obj);
                }
            }
            obj2 = obj;
        }
        return (gzy) obj2;
    }

    /* renamed from: q */
    public final hhs mo2340q() {
        return new hhs(ftz.m9622a(this.f2702a), exn.m8328b(), m2719cS(), m2733dd(), mo2314cn(), mo2339p(), m2734de());
    }

    /* renamed from: cG */
    public final hht mo2291cG() {
        return new hht(mo2341r());
    }

    /* renamed from: cM */
    public final hjv mo2297cM() {
        return new hjv(mo2341r(), ezl.m8419b());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v3, resolved type: bjv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: bjv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v7, resolved type: bjv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v43, resolved type: hiq} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v22, resolved type: hiq} */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: r */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.hiq mo2341r() {
        /*
            r28 = this;
            r1 = r28
            java.lang.Object r2 = r1.f2726aX
            boolean r0 = r2 instanceof p000.iok
            if (r0 == 0) goto L_0x01e1
            monitor-enter(r2)
            java.lang.Object r0 = r1.f2726aX     // Catch:{ all -> 0x01de }
            boolean r3 = r0 instanceof p000.iok     // Catch:{ all -> 0x01de }
            if (r3 == 0) goto L_0x01da
            exm r5 = p000.exn.m8328b()     // Catch:{ all -> 0x01de }
            fty r0 = r1.f2702a     // Catch:{ all -> 0x01de }
            android.content.Context r6 = p000.ftz.m9622a(r0)     // Catch:{ all -> 0x01de }
            iel r7 = r28.mo2314cn()     // Catch:{ all -> 0x01de }
            iel r8 = r28.mo2314cn()     // Catch:{ all -> 0x01de }
            goo r9 = r28.mo2335l()     // Catch:{ all -> 0x01de }
            java.lang.Object r0 = r28.mo2157H()     // Catch:{ all -> 0x01de }
            hpy r10 = p000.hpy.m11893b(r0)     // Catch:{ all -> 0x01de }
            hiz r11 = r28.m2724cX()     // Catch:{ all -> 0x01de }
            java.lang.Object r3 = r1.f2715aM     // Catch:{ all -> 0x01de }
            boolean r0 = r3 instanceof p000.iok     // Catch:{ all -> 0x01de }
            if (r0 == 0) goto L_0x009f
            monitor-enter(r3)     // Catch:{ all -> 0x01de }
            java.lang.Object r0 = r1.f2715aM     // Catch:{ all -> 0x009c }
            boolean r4 = r0 instanceof p000.iok     // Catch:{ all -> 0x009c }
            if (r4 == 0) goto L_0x0099
            fty r0 = r1.f2702a     // Catch:{ all -> 0x009c }
            android.content.Context r0 = p000.ftz.m9622a(r0)     // Catch:{ all -> 0x009c }
            hsu r4 = p000.hvb.f13454a     // Catch:{ all -> 0x009c }
            iel r12 = r28.mo2314cn()     // Catch:{ all -> 0x009c }
            gog r13 = new gog     // Catch:{ all -> 0x009c }
            r13.<init>(r0, r4, r12)     // Catch:{ all -> 0x009c }
            hhd r0 = p000.hhe.m11487d()     // Catch:{ all -> 0x009c }
            java.lang.String r4 = "OrphanCacheSingletonSynclet"
            hha r4 = p000.hha.m11482a(r4)     // Catch:{ all -> 0x009c }
            r0.f12727a = r4     // Catch:{ all -> 0x009c }
            r0.mo7440a(r13)     // Catch:{ all -> 0x009c }
            hgv r4 = p000.hgw.m11470d()     // Catch:{ all -> 0x009c }
            r12 = 14
            java.util.concurrent.TimeUnit r14 = java.util.concurrent.TimeUnit.DAYS     // Catch:{ all -> 0x009c }
            r4.mo7431a(r12, r14)     // Catch:{ all -> 0x009c }
            hgx r12 = p000.hgy.m11476c()     // Catch:{ all -> 0x009c }
            hgz r13 = p000.hgz.ON_CHARGER     // Catch:{ all -> 0x009c }
            r12.f12717a = r13     // Catch:{ all -> 0x009c }
            r13 = 7
            java.util.concurrent.TimeUnit r15 = java.util.concurrent.TimeUnit.DAYS     // Catch:{ all -> 0x009c }
            r12.mo7434a(r13, r15)     // Catch:{ all -> 0x009c }
            hgy r12 = r12.mo7433a()     // Catch:{ all -> 0x009c }
            r4.mo7432a(r12)     // Catch:{ all -> 0x009c }
            hgw r4 = r4.mo7430a()     // Catch:{ all -> 0x009c }
            r0.f12728b = r4     // Catch:{ all -> 0x009c }
            hhe r0 = r0.mo7439a()     // Catch:{ all -> 0x009c }
            java.lang.String r4 = "Cannot return null from a non-@Nullable @Provides method"
            java.lang.Object r0 = p000.iol.m14229a((java.lang.Object) r0, (java.lang.String) r4)     // Catch:{ all -> 0x009c }
            hhe r0 = (p000.hhe) r0     // Catch:{ all -> 0x009c }
            java.lang.Object r4 = r1.f2715aM     // Catch:{ all -> 0x009c }
            java.lang.Object r4 = p000.iog.m14219a(r4, r0)     // Catch:{ all -> 0x009c }
            r1.f2715aM = r4     // Catch:{ all -> 0x009c }
        L_0x0099:
            monitor-exit(r3)     // Catch:{ all -> 0x009c }
            r3 = r0
            goto L_0x00a0
        L_0x009c:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x009c }
            throw r0     // Catch:{ all -> 0x01de }
        L_0x009f:
        L_0x00a0:
            hhe r3 = (p000.hhe) r3     // Catch:{ all -> 0x01de }
            java.lang.String r0 = "AccountWipeout"
            java.lang.Object r4 = r28.mo2157H()     // Catch:{ all -> 0x01de }
            iel r12 = r28.mo2314cn()     // Catch:{ all -> 0x01de }
            hfq r13 = r28.mo2284c()     // Catch:{ all -> 0x01de }
            gnw r14 = r28.mo2331h()     // Catch:{ all -> 0x01de }
            gnr r15 = new gnr     // Catch:{ all -> 0x01de }
            glp r4 = (p000.glp) r4     // Catch:{ all -> 0x01de }
            r15.<init>(r4, r12, r13, r14)     // Catch:{ all -> 0x01de }
            hsu r0 = p000.hsu.m12066a(r0, r15)     // Catch:{ all -> 0x01de }
            iel r4 = r28.mo2329f()     // Catch:{ all -> 0x01de }
            hgq r12 = new hgq     // Catch:{ all -> 0x01de }
            r12.<init>(r0, r4)     // Catch:{ all -> 0x01de }
            hhd r0 = p000.hhe.m11487d()     // Catch:{ all -> 0x01de }
            java.lang.String r4 = "WipeoutSynclet"
            hha r4 = p000.hha.m11482a(r4)     // Catch:{ all -> 0x01de }
            r0.f12727a = r4     // Catch:{ all -> 0x01de }
            r0.mo7440a(r12)     // Catch:{ all -> 0x01de }
            hgv r4 = p000.hgw.m11470d()     // Catch:{ all -> 0x01de }
            r12 = 3
            java.util.concurrent.TimeUnit r14 = java.util.concurrent.TimeUnit.DAYS     // Catch:{ all -> 0x01de }
            r4.mo7431a(r12, r14)     // Catch:{ all -> 0x01de }
            hgx r12 = p000.hgy.m11476c()     // Catch:{ all -> 0x01de }
            hgz r13 = p000.hgz.ON_CHARGER     // Catch:{ all -> 0x01de }
            r12.f12717a = r13     // Catch:{ all -> 0x01de }
            r13 = 4
            java.util.concurrent.TimeUnit r15 = java.util.concurrent.TimeUnit.DAYS     // Catch:{ all -> 0x01de }
            r12.mo7434a(r13, r15)     // Catch:{ all -> 0x01de }
            hgy r12 = r12.mo7433a()     // Catch:{ all -> 0x01de }
            r4.mo7432a(r12)     // Catch:{ all -> 0x01de }
            hgw r4 = r4.mo7430a()     // Catch:{ all -> 0x01de }
            r0.f12728b = r4     // Catch:{ all -> 0x01de }
            hhe r0 = r0.mo7439a()     // Catch:{ all -> 0x01de }
            java.lang.String r4 = "Cannot return null from a non-@Nullable @Provides method"
            java.lang.Object r0 = p000.iol.m14229a((java.lang.Object) r0, (java.lang.String) r4)     // Catch:{ all -> 0x01de }
            hhe r0 = (p000.hhe) r0     // Catch:{ all -> 0x01de }
            hto r12 = p000.hto.m12121a((java.lang.Object) r3, (java.lang.Object) r0)     // Catch:{ all -> 0x01de }
            hvf r13 = p000.hvf.f13465a     // Catch:{ all -> 0x01de }
            hgz r14 = p000.hgz.ON_CHARGER     // Catch:{ all -> 0x01de }
            iqk r0 = r1.f2718aP     // Catch:{ all -> 0x01de }
            if (r0 != 0) goto L_0x0120
            bjv r0 = new bjv     // Catch:{ all -> 0x01de }
            r3 = 4
            r0.<init>(r1, r3)     // Catch:{ all -> 0x01de }
            r1.f2718aP = r0     // Catch:{ all -> 0x01de }
            r15 = r0
            goto L_0x0121
        L_0x0120:
            r15 = r0
        L_0x0121:
            hgz r16 = p000.hgz.ON_NETWORK_CONNECTED     // Catch:{ all -> 0x01de }
            iqk r0 = r1.f2720aR     // Catch:{ all -> 0x01de }
            if (r0 != 0) goto L_0x0132
            bjv r0 = new bjv     // Catch:{ all -> 0x01de }
            r3 = 6
            r0.<init>(r1, r3)     // Catch:{ all -> 0x01de }
            r1.f2720aR = r0     // Catch:{ all -> 0x01de }
            r17 = r0
            goto L_0x0134
        L_0x0132:
            r17 = r0
        L_0x0134:
            hgz r18 = p000.hgz.ON_NETWORK_UNMETERED     // Catch:{ all -> 0x01de }
            iqk r0 = r1.f2722aT     // Catch:{ all -> 0x01de }
            if (r0 != 0) goto L_0x0145
            bjv r0 = new bjv     // Catch:{ all -> 0x01de }
            r3 = 7
            r0.<init>(r1, r3)     // Catch:{ all -> 0x01de }
            r1.f2722aT = r0     // Catch:{ all -> 0x01de }
            r19 = r0
            goto L_0x0147
        L_0x0145:
            r19 = r0
        L_0x0147:
            hsu r14 = p000.hsu.m12068a(r14, r15, r16, r17, r18, r19)     // Catch:{ all -> 0x01de }
            iqk r0 = r1.f2724aV     // Catch:{ all -> 0x01de }
            if (r0 == 0) goto L_0x0151
        L_0x014f:
            r15 = r0
            goto L_0x015b
        L_0x0151:
            bjv r0 = new bjv     // Catch:{ all -> 0x01de }
            r3 = 8
            r0.<init>(r1, r3)     // Catch:{ all -> 0x01de }
            r1.f2724aV = r0     // Catch:{ all -> 0x01de }
            goto L_0x014f
        L_0x015b:
            fty r0 = r1.f2702a     // Catch:{ all -> 0x01de }
            android.content.Context r17 = p000.ftz.m9622a(r0)     // Catch:{ all -> 0x01de }
            exm r18 = p000.exn.m8328b()     // Catch:{ all -> 0x01de }
            java.util.Map r19 = r28.m2734de()     // Catch:{ all -> 0x01de }
            eyc r20 = p000.ezl.m8419b()     // Catch:{ all -> 0x01de }
            android.content.pm.PackageManager r21 = r28.m2719cS()     // Catch:{ all -> 0x01de }
            hhs r22 = r28.mo2340q()     // Catch:{ all -> 0x01de }
            hjm r23 = r28.m2733dd()     // Catch:{ all -> 0x01de }
            java.lang.Object r3 = r1.f2725aW     // Catch:{ all -> 0x01de }
            boolean r0 = r3 instanceof p000.iok     // Catch:{ all -> 0x01de }
            if (r0 == 0) goto L_0x01b1
            monitor-enter(r3)     // Catch:{ all -> 0x01de }
            java.lang.Object r0 = r1.f2725aW     // Catch:{ all -> 0x01ae }
            boolean r4 = r0 instanceof p000.iok     // Catch:{ all -> 0x01ae }
            if (r4 == 0) goto L_0x01a9
            fty r0 = r1.f2702a     // Catch:{ all -> 0x01ae }
            android.content.Context r0 = p000.ftz.m9622a(r0)     // Catch:{ all -> 0x01ae }
            bhe r4 = new bhe     // Catch:{ all -> 0x01ae }
            r27 = r15
            bhg r15 = new bhg     // Catch:{ all -> 0x01ae }
            r15.<init>(r0)     // Catch:{ all -> 0x01ae }
            r4.<init>(r15)     // Catch:{ all -> 0x01ae }
            java.lang.String r0 = "Cannot return null from a non-@Nullable @Provides method"
            java.lang.Object r0 = p000.iol.m14229a((java.lang.Object) r4, (java.lang.String) r0)     // Catch:{ all -> 0x01ae }
            bhe r0 = (p000.bhe) r0     // Catch:{ all -> 0x01ae }
            java.lang.Object r4 = r1.f2725aW     // Catch:{ all -> 0x01ae }
            java.lang.Object r4 = p000.iog.m14219a(r4, r0)     // Catch:{ all -> 0x01ae }
            r1.f2725aW = r4     // Catch:{ all -> 0x01ae }
            goto L_0x01ab
        L_0x01a9:
            r27 = r15
        L_0x01ab:
            monitor-exit(r3)     // Catch:{ all -> 0x01ae }
            r3 = r0
            goto L_0x01b3
        L_0x01ae:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x01ae }
            throw r0     // Catch:{ all -> 0x01de }
        L_0x01b1:
            r27 = r15
        L_0x01b3:
            r24 = r3
            bhe r24 = (p000.bhe) r24     // Catch:{ all -> 0x01de }
            iel r25 = r28.mo2314cn()     // Catch:{ all -> 0x01de }
            iqk r26 = r28.mo2339p()     // Catch:{ all -> 0x01de }
            hju r0 = new hju     // Catch:{ all -> 0x01de }
            r16 = r0
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)     // Catch:{ all -> 0x01de }
            hpy r16 = p000.hpy.m11893b(r0)     // Catch:{ all -> 0x01de }
            hiq r0 = new hiq     // Catch:{ all -> 0x01de }
            r4 = r0
            r15 = r27
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x01de }
            java.lang.Object r3 = r1.f2726aX     // Catch:{ all -> 0x01de }
            java.lang.Object r3 = p000.iog.m14219a(r3, r0)     // Catch:{ all -> 0x01de }
            r1.f2726aX = r3     // Catch:{ all -> 0x01de }
        L_0x01da:
            monitor-exit(r2)     // Catch:{ all -> 0x01de }
            r2 = r0
            goto L_0x01e2
        L_0x01de:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x01de }
            throw r0
        L_0x01e1:
        L_0x01e2:
            hiq r2 = (p000.hiq) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bjw.mo2341r():hiq");
    }

    /* renamed from: cX */
    private final hiz m2724cX() {
        Object obj;
        Object obj2 = this.f2714aL;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2714aL;
                if (obj instanceof iok) {
                    hiz hiz = new hiz(ftz.m9622a(this.f2702a), mo2314cn(), exn.m8328b());
                    this.f2714aL = iog.m14219a(this.f2714aL, hiz);
                    obj = hiz;
                }
            }
            obj2 = obj;
        }
        return (hiz) obj2;
    }

    /* renamed from: p */
    public final iqk mo2339p() {
        iqk iqk = this.f2723aU;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 9);
        this.f2723aU = bjv;
        return bjv;
    }

    /* renamed from: cH */
    public final hjd mo2292cH() {
        return new hjd(mo2341r());
    }

    /* renamed from: cI */
    public final hjf mo2293cI() {
        return new hjf(mo2341r());
    }

    /* renamed from: dd */
    private final hjm m2733dd() {
        exm b = exn.m8328b();
        hiz cX = m2724cX();
        iel cn = mo2314cn();
        int i = Build.VERSION.SDK_INT;
        return new hjm(b, cX, cn, (Random) iol.m14229a((Object) new SecureRandom(), "Cannot return null from a non-@Nullable @Provides method"));
    }

    /* renamed from: cK */
    public final iqk mo2295cK() {
        return mo2339p();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: bjv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: det} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: det} */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r4v9, types: [java.lang.Object, imp] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: by */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p000.dge mo2282by() {
        /*
            r11 = this;
            java.lang.Object r0 = r11.f2909dv
            boolean r1 = r0 instanceof p000.iok
            if (r1 == 0) goto L_0x0058
            monitor-enter(r0)
            java.lang.Object r1 = r11.f2909dv     // Catch:{ all -> 0x0055 }
            boolean r2 = r1 instanceof p000.iok     // Catch:{ all -> 0x0055 }
            if (r2 == 0) goto L_0x0052
            iqk r1 = r11.f2907dt     // Catch:{ all -> 0x0055 }
            if (r1 != 0) goto L_0x001c
            bjv r1 = new bjv     // Catch:{ all -> 0x0055 }
            r2 = 75
            r1.<init>(r11, r2)     // Catch:{ all -> 0x0055 }
            r11.f2907dt = r1     // Catch:{ all -> 0x0055 }
            r3 = r1
            goto L_0x001d
        L_0x001c:
            r3 = r1
        L_0x001d:
            java.lang.Object r4 = r11.mo2224at()     // Catch:{ all -> 0x0055 }
            ble r5 = r11.mo2272bo()     // Catch:{ all -> 0x0055 }
            ble r1 = r11.mo2187aI()     // Catch:{ all -> 0x0055 }
            j$.util.Optional r6 = p003j$.util.Optional.m16285of(r1)     // Catch:{ all -> 0x0055 }
            ble r1 = r11.mo2277bt()     // Catch:{ all -> 0x0055 }
            j$.util.Optional r7 = p003j$.util.Optional.m16285of(r1)     // Catch:{ all -> 0x0055 }
            deq r8 = r11.mo2278bu()     // Catch:{ all -> 0x0055 }
            java.lang.Object r1 = r11.mo2332i()     // Catch:{ all -> 0x0055 }
            r9 = r1
            gus r9 = (p000.gus) r9     // Catch:{ all -> 0x0055 }
            iel r10 = r11.mo2314cn()     // Catch:{ all -> 0x0055 }
            det r1 = new det     // Catch:{ all -> 0x0055 }
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0055 }
            java.lang.Object r2 = r11.f2909dv     // Catch:{ all -> 0x0055 }
            java.lang.Object r2 = p000.iog.m14219a(r2, r1)     // Catch:{ all -> 0x0055 }
            r11.f2909dv = r2     // Catch:{ all -> 0x0055 }
        L_0x0052:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            r0 = r1
            goto L_0x0059
        L_0x0055:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r1
        L_0x0058:
        L_0x0059:
            det r0 = (p000.det) r0
            dfy r1 = p000.dfy.BURST_PRIMARY
            cjr r2 = r11.mo2270bm()
            iqk r3 = r11.f2910dw
            if (r3 == 0) goto L_0x0066
            goto L_0x006f
        L_0x0066:
            bjv r3 = new bjv
            r4 = 76
            r3.<init>(r11, r4)
            r11.f2910dw = r3
        L_0x006f:
            inw r3 = p000.iog.m14218a(r3)
            boolean r2 = r2.mo3175a()
            if (r2 == 0) goto L_0x007f
            bmn r2 = new bmn
            r2.<init>(r3)
            goto L_0x0081
        L_0x007f:
            dfz r2 = p000.bmo.f3149a
        L_0x0081:
            java.lang.String r3 = "Cannot return null from a non-@Nullable @Provides method"
            java.lang.Object r2 = p000.iol.m14229a((java.lang.Object) r2, (java.lang.String) r3)
            dfz r2 = (p000.dfz) r2
            dfy r3 = p000.dfy.BURST
            cjr r4 = r11.mo2270bm()
            iqk r5 = r11.f2911dx
            if (r5 == 0) goto L_0x0095
            goto L_0x009e
        L_0x0095:
            bjv r5 = new bjv
            r6 = 77
            r5.<init>(r11, r6)
            r11.f2911dx = r5
        L_0x009e:
            inw r5 = p000.iog.m14218a(r5)
            boolean r4 = r4.mo3175a()
            if (r4 == 0) goto L_0x00ae
            bmw r4 = new bmw
            r4.<init>(r5)
            goto L_0x00b0
        L_0x00ae:
            dfz r4 = p000.bmx.f3166a
        L_0x00b0:
            java.lang.String r5 = "Cannot return null from a non-@Nullable @Provides method"
            java.lang.Object r4 = p000.iol.m14229a((java.lang.Object) r4, (java.lang.String) r5)
            dfz r4 = (p000.dfz) r4
            dfy r5 = p000.dfy.SPECIAL_TYPES
            cjr r6 = r11.mo2261bd()
            iqk r7 = r11.f2912dy
            if (r7 == 0) goto L_0x00c4
            goto L_0x00cd
        L_0x00c4:
            bjv r7 = new bjv
            r8 = 78
            r7.<init>(r11, r8)
            r11.f2912dy = r7
        L_0x00cd:
            inw r7 = p000.iog.m14218a(r7)
            boolean r6 = r6.mo3175a()
            if (r6 == 0) goto L_0x00dd
            dir r6 = new dir
            r6.<init>(r7)
            goto L_0x00df
        L_0x00dd:
            dfz r6 = p000.dis.f6632a
        L_0x00df:
            java.lang.String r7 = "Cannot return null from a non-@Nullable @Provides method"
            java.lang.Object r6 = p000.iol.m14229a((java.lang.Object) r6, (java.lang.String) r7)
            dfz r6 = (p000.dfz) r6
            hsu r1 = p000.hsu.m12068a(r1, r2, r3, r4, r5, r6)
            cyr r2 = r11.mo2192aN()
            iel r3 = r11.mo2314cn()
            dge r4 = new dge
            r4.<init>(r0, r1, r2, r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bjw.mo2282by():dge");
    }

    /* renamed from: dw */
    private final iqk m2752dw() {
        iqk iqk = this.f2913dz;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 74);
        this.f2913dz = bjv;
        return bjv;
    }

    /* renamed from: bT */
    public final fge mo2251bT() {
        fee bN = mo2245bN();
        mo2250bS();
        return (fge) iol.m14229a((Object) new fge(bN), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: cR */
    public final void mo2300cR() {
        mo2245bN();
        fhg fhg = (fhg) iol.m14229a((Object) new fhg(), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: at */
    public final Object mo2224at() {
        return new ino(iom.f14602a, mo2151B());
    }

    /* renamed from: bZ */
    public final ehb mo2257bZ() {
        return new ehb(ftz.m9622a(this.f2702a), mo2314cn());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: hdm} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: hdm} */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: dl */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object m2741dl() {
        /*
            r11 = this;
            java.lang.Object r0 = r11.f2760bE
            boolean r1 = r0 instanceof p000.iok
            if (r1 == 0) goto L_0x0057
            monitor-enter(r0)
            java.lang.Object r1 = r11.f2760bE     // Catch:{ all -> 0x0054 }
            boolean r2 = r1 instanceof p000.iok     // Catch:{ all -> 0x0054 }
            if (r2 == 0) goto L_0x0050
            fty r1 = r11.f2702a     // Catch:{ all -> 0x0054 }
            android.content.Context r3 = p000.ftz.m9622a(r1)     // Catch:{ all -> 0x0054 }
            int r4 = r11.mo2231b()     // Catch:{ all -> 0x0054 }
            java.lang.String r5 = r11.m2720cT()     // Catch:{ all -> 0x0054 }
            iqk r1 = r11.m2736dg()     // Catch:{ all -> 0x0054 }
            inw r6 = p000.iog.m14218a(r1)     // Catch:{ all -> 0x0054 }
            iqk r1 = r11.f2758bC     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0028
        L_0x0027:
            goto L_0x0032
        L_0x0028:
            bjv r1 = new bjv     // Catch:{ all -> 0x0054 }
            r2 = 25
            r1.<init>(r11, r2)     // Catch:{ all -> 0x0054 }
            r11.f2758bC = r1     // Catch:{ all -> 0x0054 }
            goto L_0x0027
        L_0x0032:
            inw r7 = p000.iog.m14218a(r1)     // Catch:{ all -> 0x0054 }
            ext r8 = r11.mo2162M()     // Catch:{ all -> 0x0054 }
            hdh r9 = r11.m2740dk()     // Catch:{ all -> 0x0054 }
            iel r10 = r11.mo2314cn()     // Catch:{ all -> 0x0054 }
            hdm r1 = new hdm     // Catch:{ all -> 0x0054 }
            r2 = r1
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0054 }
            java.lang.Object r2 = r11.f2760bE     // Catch:{ all -> 0x0054 }
            java.lang.Object r2 = p000.iog.m14219a(r2, r1)     // Catch:{ all -> 0x0054 }
            r11.f2760bE = r2     // Catch:{ all -> 0x0054 }
        L_0x0050:
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            r0 = r1
            goto L_0x0058
        L_0x0054:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            throw r1
        L_0x0057:
        L_0x0058:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bjw.m2741dl():java.lang.Object");
    }

    /* renamed from: a */
    public final PackageInfo mo2176a() {
        Object obj;
        Object obj2 = this.f2751aw;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2751aw;
                if (obj instanceof iok) {
                    Context a = ftz.m9622a(this.f2702a);
                    try {
                        obj = (PackageInfo) iol.m14229a((Object) m2719cS().getPackageInfo(a.getPackageName(), 0), "Cannot return null from a non-@Nullable @Provides method");
                        this.f2751aw = iog.m14219a(this.f2751aw, obj);
                    } catch (PackageManager.NameNotFoundException e) {
                        throw new IllegalStateException("Can't find our own package", e);
                    }
                }
            }
            obj2 = obj;
        }
        return (PackageInfo) obj2;
    }

    /* renamed from: dx */
    private final iqk m2753dx() {
        iqk iqk = this.f2863dB;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 79);
        this.f2863dB = bjv;
        return bjv;
    }

    /* renamed from: cJ */
    public final hlz mo2294cJ() {
        return mo2324cx();
    }

    /* renamed from: S */
    public final iqk mo2168S() {
        iqk iqk = this.f2771bP;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 31);
        this.f2771bP = bjv;
        return bjv;
    }

    /* renamed from: P */
    public final Object mo2165P() {
        Object obj;
        bjv bjv;
        Object obj2;
        Object obj3;
        Object obj4 = this.f2768bM;
        if (!(obj4 instanceof iok)) {
            return obj4;
        }
        synchronized (obj4) {
            obj = this.f2768bM;
            if (obj instanceof iok) {
                Object obj5 = this.f2764bI;
                if (obj5 instanceof iok) {
                    synchronized (obj5) {
                        obj3 = this.f2764bI;
                        if (obj3 instanceof iok) {
                            obj3 = new grj(mo2330g());
                            this.f2764bI = iog.m14219a(this.f2764bI, obj3);
                        }
                    }
                    obj5 = obj3;
                }
                grj grj = (grj) obj5;
                exm b = exn.m8328b();
                iel f = mo2329f();
                iqk iqk = this.f2765bJ;
                if (iqk == null) {
                    bjv bjv2 = new bjv(this, 27);
                    this.f2765bJ = bjv2;
                    bjv = bjv2;
                } else {
                    bjv = iqk;
                }
                Object obj6 = this.f2766bK;
                if (obj6 instanceof iok) {
                    synchronized (obj6) {
                        obj2 = this.f2766bK;
                        if (obj2 instanceof iok) {
                            obj2 = new hmv();
                            this.f2766bK = iog.m14219a(this.f2766bK, obj2);
                        }
                    }
                    obj6 = obj2;
                }
                hmv hmv = (hmv) obj6;
                Object obj7 = this.f2767bL;
                if (obj7 == null) {
                    obj7 = new hky();
                    this.f2767bL = obj7;
                }
                hmh hmh = new hmh(grj, b, f, bjv, hmv, (hky) obj7);
                this.f2768bM = iog.m14219a(this.f2768bM, hmh);
                obj = hmh;
            }
        }
        return obj;
    }

    /* renamed from: cc */
    public final cjr mo2303cc() {
        Object obj;
        Object obj2 = this.f2943ex;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2943ex;
                if (obj instanceof iok) {
                    cjq a = cjr.m4407a(ceo.m4214b());
                    a.f4510b = new BuildType[]{BuildType.DEV};
                    a.f4511c = new BuildType[]{BuildType.DEV};
                    obj = (cjr) iol.m14229a((Object) a.mo3174a(), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2943ex = iog.m14219a(this.f2943ex, obj);
                }
            }
            obj2 = obj;
        }
        return (cjr) obj2;
    }

    /* renamed from: j */
    public final Object mo2333j() {
        Object obj;
        Object obj2 = this.f2710aH;
        if (!(obj2 instanceof iok)) {
            return obj2;
        }
        synchronized (obj2) {
            obj = this.f2710aH;
            if (obj instanceof iok) {
                grr grr = new grr(exn.m8328b());
                this.f2710aH = iog.m14219a(this.f2710aH, grr);
                obj = grr;
            }
        }
        return obj;
    }

    /* renamed from: dB */
    private final iqj m2728dB() {
        return new iqj(mo2160K());
    }

    /* renamed from: bQ */
    public final hat mo2248bQ() {
        Object obj;
        Object obj2 = this.f2933en;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2933en;
                if (obj instanceof iok) {
                    ftz.m9622a(this.f2702a);
                    iqk iqk = this.f2932em;
                    if (iqk == null) {
                        iqk = new bjv(this, 101);
                        this.f2932em = iqk;
                    }
                    hat hat = new hat(iog.m14218a(iqk));
                    this.f2933en = iog.m14219a(this.f2933en, hat);
                    obj = hat;
                }
            }
            obj2 = obj;
        }
        return (hat) obj2;
    }

    /* renamed from: aK */
    public final eho mo2189aK() {
        Object obj;
        Object obj2 = this.f2817cI;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2817cI;
                if (obj instanceof iok) {
                    hfu g = hfv.m11397g();
                    g.f12674a = "UserConsent";
                    g.mo7383a(ehh.f8289d);
                    obj = (fzx) iol.m14229a((Object) mo2328e().mo7385a((hfv) iol.m14229a((Object) g.mo7382a(), "Cannot return null from a non-@Nullable @Provides method")), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2817cI = iog.m14219a(this.f2817cI, obj);
                }
            }
            obj2 = obj;
        }
        return new eho((fzx) obj2, mo2184aF(), iog.m14218a(mo2188aJ()));
    }

    /* renamed from: b */
    public final int mo2231b() {
        return mo2176a().versionCode;
    }

    /* renamed from: cT */
    private final String m2720cT() {
        return (String) iol.m14229a((Object) (String) ife.m12869b((Object) mo2176a().versionName, (Object) "PackageInfo's versionName is null. If this is a Robolectric test, make sure to either specify versionName in your test's manifest or via http://go/be#android_local_test.manifest_values"), "Cannot return null from a non-@Nullable @Provides method");
    }

    /* renamed from: bN */
    public final fee mo2245bN() {
        Object obj;
        Object obj2;
        Object obj3 = this.f2930ek;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj = this.f2930ek;
                if (obj instanceof iok) {
                    hvf hvf = hvf.f13465a;
                    Object obj4 = this.f2929ej;
                    if (obj4 instanceof iok) {
                        synchronized (obj4) {
                            obj2 = this.f2929ej;
                            if (obj2 instanceof iok) {
                                fcu dA = m2727dA();
                                hpy b = hpy.m11893b((fei) iol.m14229a((Object) new goa(mo2324cx()), "Cannot return null from a non-@Nullable @Provides method"));
                                m2755dz();
                                obj2 = (fep) iol.m14229a((Object) new fep(dA, (fei) b.mo7645a(few.f9412a)), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2929ej = iog.m14219a(this.f2929ej, obj2);
                            }
                        }
                        obj4 = obj2;
                    }
                    htm j = hto.m12130j();
                    j.mo7995b((Iterable) hvf);
                    j.mo7874b(((fep) obj4).f9392j);
                    obj = (fee) iol.m14229a((Object) new fee(j.mo7993a(), (fds) iol.m14229a((Object) ffx.m8758a(), "Cannot return null from a non-@Nullable @Provides method"), ffa.f9433a), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2930ek = iog.m14219a(this.f2930ek, obj);
                }
            }
            obj3 = obj;
        }
        return (fee) obj3;
    }

    /* renamed from: cd */
    public final iqk mo2304cd() {
        iqk iqk = this.f2944ey;
        if (iqk != null) {
            return iqk;
        }
        bjv bjv = new bjv(this, 110);
        this.f2944ey = bjv;
        return bjv;
    }

    /* renamed from: cq */
    public final egr mo2317cq() {
        return new egr(mo2269bl(), mo2314cn());
    }

    /* renamed from: h */
    public final gnw mo2331h() {
        return new gnw(mo2330g(), mo2284c());
    }

    /* renamed from: a */
    public final void mo2178a(InternalForegroundService internalForegroundService) {
        internalForegroundService.f5306a = (gqw) m2723cW();
    }

    /* renamed from: a */
    public final void mo2177a(PhotosGo_Application photosGo_Application) {
        bjv bjv;
        bjv bjv2;
        bjv bjv3;
        Object obj;
        Object obj2;
        photosGo_Application.f12461b = mo2324cx();
        gti gti = gti.LOGGING;
        iqk iqk = this.f2763bH;
        if (iqk == null) {
            iqk = new bjv(this, 0);
            this.f2763bH = iqk;
        }
        photosGo_Application.f12462c = new gtk(hsu.m12068a(gti, new gtg(new hcr(iqk)), gti.PRIMES_STARTUP, new hfe(ftz.m9622a(this.f2702a), m2742dm()), gti.UNCAUGHT_EXCEPTION_HANDLER, new gtm(hto.m12121a((Object) (gth) m2741dl(), mo2166Q()))));
        iqk iqk2 = this.f2772bQ;
        if (iqk2 == null) {
            bjv bjv4 = new bjv(this, 29);
            this.f2772bQ = bjv4;
            bjv = bjv4;
        } else {
            bjv = iqk2;
        }
        iqk iqk3 = this.f2773bR;
        if (iqk3 == null) {
            bjv bjv5 = new bjv(this, 32);
            this.f2773bR = bjv5;
            bjv2 = bjv5;
        } else {
            bjv2 = iqk3;
        }
        iqk iqk4 = this.f2775bT;
        if (iqk4 != null) {
            bjv3 = iqk4;
        } else {
            bjv bjv6 = new bjv(this, 33);
            this.f2775bT = bjv6;
            bjv3 = bjv6;
        }
        photosGo_Application.f12463d = hsu.m12068a("ActivityLifecycleCallbacks", bjv, "NightMode", bjv2, "AfterPackageReplaced", bjv3);
        iqk iqk5 = this.f2781bZ;
        if (iqk5 == null) {
            iqk5 = new bjv(this, 39);
            this.f2781bZ = iqk5;
        }
        iqk iqk6 = this.f2835ca;
        if (iqk6 == null) {
            iqk6 = new bjv(this, 43);
            this.f2835ca = iqk6;
        }
        iqk iqk7 = this.f2836cb;
        if (iqk7 == null) {
            iqk7 = new bjv(this, 44);
            this.f2836cb = iqk7;
        }
        iqk iqk8 = this.f2837cc;
        if (iqk8 == null) {
            iqk8 = new bjv(this, 45);
            this.f2837cc = iqk8;
        }
        iqk iqk9 = this.f2838cd;
        if (iqk9 == null) {
            iqk9 = new bjv(this, 46);
            this.f2838cd = iqk9;
        }
        ife.m12843a((Object) "Primes", (Object) iqk5);
        ife.m12843a((Object) "PrimesStartup", (Object) iqk6);
        ife.m12843a((Object) "PrngFixes", (Object) iqk7);
        ife.m12843a((Object) "SilentFeedback", (Object) iqk8);
        ife.m12843a((Object) "SslGuard", (Object) iqk9);
        photosGo_Application.f12464e = hvb.m12214a(5, new Object[]{"Primes", iqk5, "PrimesStartup", iqk6, "PrngFixes", iqk7, "SilentFeedback", iqk8, "SslGuard", iqk9});
        Object obj3 = this.f2842ch;
        if (obj3 instanceof iok) {
            synchronized (obj3) {
                obj = this.f2842ch;
                if (obj instanceof iok) {
                    iel cn = mo2314cn();
                    Object obj4 = this.f2841cg;
                    if (obj4 instanceof iok) {
                        synchronized (obj4) {
                            obj2 = this.f2841cg;
                            if (obj2 instanceof iok) {
                                iqk iqk10 = this.f2839ce;
                                if (iqk10 == null) {
                                    iqk10 = new bjv(this, 47);
                                    this.f2839ce = iqk10;
                                }
                                iqk iqk11 = this.f2840cf;
                                if (iqk11 == null) {
                                    iqk11 = new bjv(this, 48);
                                    this.f2840cf = iqk11;
                                }
                                obj2 = (ahv) iol.m14229a((Object) new gtd(mo2324cx(), hsu.m12067a("com.google.android.apps.photosgo.jobs.contentupdate.ContentUpdateWorker", iqk10, "com.google.android.apps.photosgo.jobs.background.PluggedInIdleWorker", iqk11)), "Cannot return null from a non-@Nullable @Provides method");
                                this.f2841cg = iog.m14219a(this.f2841cg, obj2);
                            }
                        }
                        obj4 = obj2;
                    }
                    agx agx = new agx();
                    agx.f460a = cn;
                    agx.f462c = cn;
                    agx.f461b = (ahv) obj4;
                    obj = (agz) iol.m14229a((Object) new agz(agx), "Cannot return null from a non-@Nullable @Provides method");
                    this.f2842ch = iog.m14219a(this.f2842ch, obj);
                }
            }
            obj3 = obj;
        }
        photosGo_Application.f4803a = (agz) obj3;
    }

    /* renamed from: cy */
    public final gor mo2325cy() {
        Object obj;
        Object obj2 = this.f2867dF;
        if (obj2 instanceof iok) {
            synchronized (obj2) {
                obj = this.f2867dF;
                if (obj instanceof iok) {
                    gor gor = new gor(ftz.m9622a(this.f2702a));
                    this.f2867dF = iog.m14219a(this.f2867dF, gor);
                    obj = gor;
                }
            }
            obj2 = obj;
        }
        return (gor) obj2;
    }

    /* renamed from: cw */
    public final hcb mo2323cw() {
        return new bjl(this);
    }

    /* renamed from: cx */
    public final hlz mo2324cx() {
        hlz hlz = this.f2866dE;
        if (hlz != null) {
            return hlz;
        }
        hlz a = hmb.m11726a(exn.m8328b(), mo2165P(), hvf.f13465a, hma.m11724b());
        this.f2866dE = a;
        return a;
    }
}
