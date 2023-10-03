package p000;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.view.animation.Interpolator;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.photogrid.SinglePhotoView;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import p003j$.util.function.Consumer;

/* renamed from: dxu */
/* compiled from: PG */
public final class dxu implements cqg, cqb {

    /* renamed from: A */
    private TextView f7583A;

    /* renamed from: B */
    private ImageView f7584B;

    /* renamed from: C */
    private boolean f7585C;

    /* renamed from: a */
    public SinglePhotoView f7586a;

    /* renamed from: b */
    public final cqh f7587b;

    /* renamed from: c */
    public final cqc f7588c;

    /* renamed from: d */
    public final hbl f7589d;

    /* renamed from: e */
    public final float f7590e;

    /* renamed from: f */
    public final SinglePhotoView f7591f;

    /* renamed from: g */
    public final hdt f7592g;

    /* renamed from: h */
    public final ImageView f7593h;

    /* renamed from: i */
    public final View f7594i;

    /* renamed from: j */
    public final ebf f7595j;

    /* renamed from: k */
    public int f7596k;

    /* renamed from: l */
    public cxi f7597l;

    /* renamed from: m */
    public dik f7598m;

    /* renamed from: n */
    public CheckBox f7599n;

    /* renamed from: o */
    public TextView f7600o;

    /* renamed from: p */
    public boolean f7601p;

    /* renamed from: q */
    public float f7602q;

    /* renamed from: r */
    public final Consumer f7603r;

    /* renamed from: s */
    private final View f7604s;

    /* renamed from: t */
    private final cny f7605t;

    /* renamed from: u */
    private final cnx f7606u;

    /* renamed from: v */
    private final bkt f7607v;

    /* renamed from: w */
    private final cjr f7608w;

    /* renamed from: x */
    private final cwg f7609x;

    /* renamed from: y */
    private final Interpolator f7610y;

    /* renamed from: z */
    private TextView f7611z;

    public dxu(hbl hbl, SinglePhotoView singlePhotoView, hdt hdt, cqh cqh, cqc cqc, hos hos, hbl hbl2, cny cny, cnx cnx, ebf ebf, bkt bkt, cjr cjr, cwg cwg) {
        ((hch) ((Stack) hci.f12472a.get()).pop()).mo4543a(ife.m12898e((Object) this));
        this.f7610y = new acb();
        this.f7597l = null;
        this.f7598m = null;
        this.f7601p = false;
        this.f7585C = false;
        this.f7602q = 1.0f;
        this.f7591f = singlePhotoView;
        this.f7592g = hdt;
        this.f7587b = cqh;
        this.f7588c = cqc;
        this.f7589d = hbl2;
        this.f7605t = cny;
        this.f7606u = cnx;
        this.f7595j = ebf;
        this.f7607v = bkt;
        this.f7608w = cjr;
        this.f7609x = cwg;
        LayoutInflater.from(hbl).inflate(R.layout.single_photo, singlePhotoView);
        this.f7593h = (ImageView) singlePhotoView.findViewById(R.id.image1);
        this.f7604s = singlePhotoView.findViewById(R.id.top_gradient);
        this.f7594i = singlePhotoView.findViewById(R.id.photo_and_scrim);
        this.f7590e = (float) hbl.getResources().getDimensionPixelSize(R.dimen.selected_photo_inset);
        this.f7603r = new dxr(this);
        hos.mo7632a((View) singlePhotoView, (View.OnClickListener) new dxs(this));
        singlePhotoView.setOnLongClickListener(new hor(hos, new dxt(this)));
    }

    /* renamed from: a */
    private static void m6869a(View view) {
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* renamed from: d */
    public final apj mo4550d() {
        Object obj;
        cxi cxi = this.f7597l;
        if (cxi != null) {
            apj a = this.f7592g.mo7307a();
            if (cxi.f5912d) {
                obj = ebh.m7086a(cxi.f5910b);
            } else {
                obj = cxi.f5910b;
            }
            apj b = ((apj) a.mo1419a(obj).mo1854a((aqu) new bfa(Long.valueOf(cxi.f5918j)))).mo1423b(mo4549c());
            apj a2 = this.f7592g.mo7307a();
            cnx cnx = this.f7606u;
            int i = cnx.f4749b.f4767e;
            Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.RGB_565);
            cnx.f4751d.setLayerSize(0, i, i);
            cnx.f4751d.setBounds(0, 0, i, i);
            cnx.f4751d.draw(new Canvas(createBitmap));
            return b.mo1416a(a2.mo1414a(createBitmap).mo1426b(this.f7606u.f4748a.f4753b.mo1857a(atc.f1597a))).mo1426b(this.f7606u.mo3294b());
        }
        throw new IllegalStateException("Trying to load null media");
    }

    /* renamed from: a */
    public final void mo3751a(cqa cqa) {
        cxi cxi = this.f7597l;
        if (cxi != null) {
            if (!cqa.f5408a) {
                TextView textView = this.f7600o;
                if (textView != null) {
                    textView.setVisibility(8);
                }
            } else {
                if (this.f7600o == null) {
                    this.f7600o = (TextView) ((ViewStub) this.f7591f.findViewById(R.id.file_size)).inflate();
                }
                this.f7600o.setVisibility(0);
                this.f7600o.setText(Formatter.formatShortFileSize(this.f7589d, cxi.f5920l));
            }
            mo4551e();
        }
    }

    /* renamed from: a */
    public final void mo2621a() {
        mo4547a(true);
        mo4551e();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007c, code lost:
        if (r0.equals(p000.cxh.VIDEO) == false) goto L_0x007e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00cd  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo4547a(boolean r9) {
        /*
            r8 = this;
            cxi r0 = r8.f7597l
            if (r0 == 0) goto L_0x00dd
            boolean r1 = r8.f7585C
            cqh r2 = r8.f7587b
            dwv r0 = p000.ede.m7258a((p000.cxi) r0)
            boolean r0 = r2.mo3763c(r0)
            r8.f7585C = r0
            android.widget.CheckBox r0 = r8.f7599n
            if (r0 == 0) goto L_0x0017
            goto L_0x002a
        L_0x0017:
            com.google.android.apps.photosgo.photogrid.SinglePhotoView r0 = r8.f7591f
            r2 = 2131362228(0x7f0a01b4, float:1.834423E38)
            android.view.View r0 = r0.findViewById(r2)
            android.view.ViewStub r0 = (android.view.ViewStub) r0
            android.view.View r0 = r0.inflate()
            android.widget.CheckBox r0 = (android.widget.CheckBox) r0
            r8.f7599n = r0
        L_0x002a:
            android.widget.CheckBox r0 = r8.f7599n
            cqh r2 = r8.f7587b
            boolean r2 = r2.mo3764d()
            r3 = 8
            r4 = 0
            if (r2 != 0) goto L_0x003a
            r2 = 8
            goto L_0x003c
        L_0x003a:
            r2 = 0
        L_0x003c:
            r0.setVisibility(r2)
            android.widget.CheckBox r0 = r8.f7599n
            boolean r2 = r8.f7585C
            r0.setChecked(r2)
            com.google.android.apps.photosgo.photogrid.SinglePhotoView r0 = r8.f7591f
            boolean r2 = r8.f7585C
            if (r2 == 0) goto L_0x0061
            android.util.TypedValue r2 = new android.util.TypedValue
            r2.<init>()
            hbl r5 = r8.f7589d
            android.content.res.Resources$Theme r5 = r5.getTheme()
            r6 = 2130968781(0x7f0400cd, float:1.7546225E38)
            r7 = 1
            r5.resolveAttribute(r6, r2, r7)
            int r2 = r2.data
            goto L_0x0063
        L_0x0061:
            r2 = 0
        L_0x0063:
            r0.setBackgroundColor(r2)
            cxi r0 = r8.f7597l
            if (r0 != 0) goto L_0x006b
            goto L_0x007e
        L_0x006b:
            int r0 = r0.f5913e
            cxh r0 = p000.cxh.m5592a(r0)
            if (r0 == 0) goto L_0x0074
            goto L_0x0076
        L_0x0074:
            cxh r0 = p000.cxh.UNKNOWN_MEDIA_TYPE
        L_0x0076:
            cxh r2 = p000.cxh.VIDEO
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0092
        L_0x007e:
            cqh r0 = r8.f7587b
            boolean r0 = r0.mo3764d()
            if (r0 != 0) goto L_0x0087
            goto L_0x008c
        L_0x0087:
            boolean r0 = r8.f7585C
            if (r0 != 0) goto L_0x008c
            goto L_0x0092
        L_0x008c:
            android.view.View r0 = r8.f7604s
            r0.setVisibility(r3)
            goto L_0x0097
        L_0x0092:
            android.view.View r0 = r8.f7604s
            r0.setVisibility(r4)
        L_0x0097:
            boolean r0 = r8.f7585C
            r2 = 1065353216(0x3f800000, float:1.0)
            if (r0 == r1) goto L_0x00c8
            if (r9 == 0) goto L_0x00cb
            android.view.View r9 = r8.f7594i
            android.view.ViewPropertyAnimator r9 = r9.animate()
            if (r0 == 0) goto L_0x00aa
            float r1 = r8.f7602q
            goto L_0x00ad
        L_0x00aa:
            r1 = 1065353216(0x3f800000, float:1.0)
        L_0x00ad:
            android.view.ViewPropertyAnimator r9 = r9.scaleX(r1)
            if (r0 == 0) goto L_0x00b6
            float r2 = r8.f7602q
            goto L_0x00b8
        L_0x00b6:
        L_0x00b8:
            android.view.ViewPropertyAnimator r9 = r9.scaleY(r2)
            android.view.animation.Interpolator r0 = r8.f7610y
            android.view.ViewPropertyAnimator r9 = r9.setInterpolator(r0)
            r0 = 100
            r9.setDuration(r0)
            return
        L_0x00c8:
            if (r9 == 0) goto L_0x00cb
            return
        L_0x00cb:
            if (r0 == 0) goto L_0x00d0
            float r2 = r8.f7602q
            goto L_0x00d2
        L_0x00d0:
        L_0x00d2:
            android.view.View r9 = r8.f7594i
            r9.setScaleX(r2)
            android.view.View r9 = r8.f7594i
            r9.setScaleY(r2)
            return
        L_0x00dd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dxu.mo4547a(boolean):void");
    }

    /* renamed from: c */
    public final apj mo4549c() {
        Object obj;
        cxi cxi = this.f7597l;
        if (cxi == null) {
            throw new IllegalStateException("Trying to load null media");
        } else if ((cxi.f5909a & 4096) != 0) {
            return ((apj) this.f7592g.mo7307a().mo1419a((Object) cob.m4677a(Long.valueOf(cxi.f5916h), cxi.f5922n)).mo1854a((aqu) new bfa(Long.valueOf(cxi.f5918j)))).mo1426b(this.f7606u.mo3293a());
        } else {
            apj a = this.f7592g.mo7307a();
            if (cxi.f5912d) {
                obj = ebh.m7086a(cxi.f5910b);
            } else {
                obj = cxi.f5910b;
            }
            return ((apj) ((apj) ((apj) a.mo1419a(obj).mo1854a((aqu) new bfa(Long.valueOf(cxi.f5918j)))).mo1860a(true)).mo1857a(atc.f1597a)).mo1426b(this.f7606u.mo3294b());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final void mo4552f() {
        cxi cxi = this.f7597l;
        if (cxi == null) {
            return;
        }
        if (this.f7585C) {
            this.f7587b.mo3760b((cpt) ede.m7258a(cxi));
        } else {
            this.f7587b.mo3756a((cpt) ede.m7258a(cxi));
        }
    }

    /* renamed from: b */
    public final void mo4548b() {
        cxi cxi;
        if (this.f7597l != null) {
            this.f7604s.setVisibility(8);
            m6869a((View) this.f7583A);
            m6869a((View) this.f7584B);
            m6869a((View) this.f7611z);
            if (C0637xj.m15915c(this.f7598m)) {
                String str = this.f7598m.f6613e;
                if (this.f7584B == null) {
                    this.f7584B = (ImageView) ((ViewStub) this.f7591f.findViewById(R.id.badge_icon)).inflate();
                }
                this.f7592g.mo7310a(str).mo1426b(this.f7605t.mo3300d()).mo1422a(this.f7584B);
                this.f7604s.setVisibility(0);
                this.f7584B.setVisibility(0);
                return;
            }
            cxi cxi2 = this.f7597l;
            if (cxi2 != null) {
                cxh a = cxh.m5592a(cxi2.f5913e);
                if (a == null) {
                    a = cxh.UNKNOWN_MEDIA_TYPE;
                }
                if (a.equals(cxh.VIDEO)) {
                    String formatElapsedTime = DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds(this.f7597l.f5915g));
                    if (formatElapsedTime.startsWith("00")) {
                        formatElapsedTime = formatElapsedTime.substring(1);
                    }
                    if (this.f7611z == null) {
                        this.f7611z = (TextView) ((ViewStub) this.f7591f.findViewById(R.id.video_duration)).inflate();
                    }
                    this.f7604s.setVisibility(0);
                    this.f7611z.setVisibility(0);
                    this.f7611z.setText(formatElapsedTime);
                    return;
                }
            }
            if (this.f7608w.mo3175a() && (cxi = this.f7597l) != null && flw.m9194a(cxi)) {
                int size = this.f7597l.f5929u.size();
                if (this.f7583A == null) {
                    this.f7583A = (TextView) ((ViewStub) this.f7591f.findViewById(R.id.burst_icon)).inflate();
                }
                this.f7604s.setVisibility(0);
                this.f7583A.setVisibility(0);
                this.f7583A.setText(String.format(this.f7609x.mo3859a(), "%d", new Object[]{Integer.valueOf(size)}));
            }
        }
    }

    /* renamed from: e */
    public final void mo4551e() {
        String str;
        int i;
        cxi cxi = this.f7597l;
        if (cxi != null) {
            SinglePhotoView singlePhotoView = this.f7591f;
            bkt bkt = this.f7607v;
            if (C0637xj.m15915c(this.f7598m)) {
                str = this.f7598m.f6611c;
            } else {
                str = null;
            }
            cqh cqh = this.f7587b;
            TextView textView = this.f7600o;
            StringBuilder sb = new StringBuilder(bkt.mo2529a(cxi, str));
            if (cqh.mo3764d()) {
                Context context = bkt.f3075a;
                if (!cqh.mo3763c(ede.m7258a(cxi))) {
                    i = R.string.content_description_unselected;
                } else {
                    i = R.string.content_description_selected;
                }
                sb.append(context.getString(i));
            }
            if (textView != null && textView.getVisibility() == 0) {
                sb.append(bkt.f3075a.getString(R.string.content_description_with_file_size, new Object[]{textView.getText()}));
            }
            singlePhotoView.setContentDescription(sb.toString());
        }
    }

    dxu() {
        ((hch) ((Stack) hci.f12472a.get()).pop()).mo4543a(ife.m12898e((Object) this));
    }
}
