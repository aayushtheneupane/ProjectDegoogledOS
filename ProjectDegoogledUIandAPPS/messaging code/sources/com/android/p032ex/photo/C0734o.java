package com.android.p032ex.photo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.p016v4.media.session.C0107q;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import androidx.fragment.app.C0424j;
import androidx.fragment.app.C0433s;
import androidx.loader.content.C0475f;
import androidx.viewpager.widget.C0616a;
import androidx.viewpager.widget.C0626k;
import com.android.p032ex.photo.PhotoViewPager;
import com.android.p032ex.photo.fragments.PhotoViewFragment;
import com.android.p032ex.photo.p034a.C0712c;
import com.android.p032ex.photo.p035b.C0714a;
import com.android.p032ex.photo.p035b.C0717d;
import com.android.p032ex.photo.util.C0745e;
import com.android.p032ex.photo.util.ImageUtils$ImageSize;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import p000a.p008d.p009a.C0035a;

/* renamed from: com.android.ex.photo.o */
public class C0734o implements C0035a, C0626k {

    /* renamed from: xw */
    public static int f876xw;

    /* renamed from: yw */
    public static int f877yw;

    /* renamed from: Aj */
    protected boolean f878Aj;

    /* renamed from: No */
    protected boolean f879No;

    /* renamed from: Nv */
    private String f880Nv;

    /* renamed from: Ov */
    protected float f881Ov;

    /* renamed from: Wb */
    protected boolean f882Wb = true;

    /* renamed from: Wv */
    protected boolean f883Wv;
    /* access modifiers changed from: private */

    /* renamed from: Zv */
    public int f884Zv;

    /* renamed from: _v */
    private final View.OnSystemUiVisibilityChangeListener f885_v;

    /* renamed from: aw */
    private int f886aw;

    /* renamed from: bw */
    private String f887bw;

    /* renamed from: cw */
    protected int f888cw = -1;

    /* renamed from: dw */
    protected boolean f889dw;

    /* renamed from: ew */
    protected ImageView f890ew;

    /* renamed from: fw */
    private final Map f891fw = new HashMap();

    /* renamed from: hw */
    private final Set f892hw = new HashSet();

    /* renamed from: iw */
    private boolean f893iw;

    /* renamed from: jq */
    private String f894jq;

    /* renamed from: jw */
    private boolean f895jw;

    /* renamed from: kw */
    protected String f896kw;

    /* renamed from: lb */
    protected PhotoViewPager f897lb;

    /* renamed from: lw */
    protected String f898lw;
    private final AccessibilityManager mAccessibilityManager;
    /* access modifiers changed from: private */
    public final C0732m mActivity;
    protected C0712c mAdapter;
    protected View mBackground;
    protected final Handler mHandler = new Handler();
    private String[] mProjection;
    protected View mRootView;

    /* renamed from: mw */
    private boolean f899mw;

    /* renamed from: nw */
    protected boolean f900nw;

    /* renamed from: ow */
    protected int f901ow;

    /* renamed from: pw */
    protected int f902pw;

    /* renamed from: qw */
    protected int f903qw;

    /* renamed from: rw */
    protected int f904rw;

    /* renamed from: sw */
    protected boolean f905sw;

    /* renamed from: tw */
    protected C0733n f906tw;

    /* renamed from: uw */
    private long f907uw;

    /* renamed from: vw */
    private int f908vw = -1;

    /* renamed from: ww */
    private final Runnable f909ww = new C0728i(this);

    public C0734o(C0732m mVar) {
        this.mActivity = mVar;
        int i = Build.VERSION.SDK_INT;
        this.f885_v = new C0727h(this);
        this.mAccessibilityManager = (AccessibilityManager) mVar.getContext().getSystemService("accessibility");
    }

    /* renamed from: Vn */
    private void m1155Vn() {
        this.mHandler.removeCallbacks(this.f909ww);
    }

    /* renamed from: Wn */
    private void m1156Wn() {
        if (this.f905sw) {
            this.mHandler.postDelayed(this.f909ww, this.f907uw);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: Xn */
    public void m1157Xn() {
        int measuredWidth = this.mRootView.getMeasuredWidth();
        int measuredHeight = this.mRootView.getMeasuredHeight();
        if (mo5771Ed()) {
            this.f890ew.setVisibility(0);
        }
        float max = Math.max(((float) this.f903qw) / ((float) measuredWidth), ((float) this.f904rw) / ((float) measuredHeight));
        int a = m1158a(this.f901ow, this.f903qw, measuredWidth, max);
        int a2 = m1158a(this.f902pw, this.f904rw, measuredHeight, max);
        int i = Build.VERSION.SDK_INT;
        if (mo5770Dd()) {
            this.mBackground.setAlpha(0.0f);
            this.mBackground.animate().alpha(1.0f).setDuration(250).start();
            this.mBackground.setVisibility(0);
        }
        if (mo5771Ed()) {
            this.f890ew.setScaleX(max);
            this.f890ew.setScaleY(max);
            this.f890ew.setTranslationX((float) a);
            this.f890ew.setTranslationY((float) a2);
            C0729j jVar = new C0729j(this);
            ViewPropertyAnimator duration = this.f890ew.animate().scaleX(1.0f).scaleY(1.0f).translationX(0.0f).translationY(0.0f).setDuration(250);
            duration.withEndAction(jVar);
            duration.start();
        }
    }

    /* renamed from: n */
    private synchronized void m1164n(Cursor cursor) {
        for (C0722f a : this.f892hw) {
            a.mo5738a(cursor);
        }
    }

    /* renamed from: Cd */
    public Cursor mo5769Cd() {
        PhotoViewPager photoViewPager = this.f897lb;
        if (photoViewPager == null) {
            return null;
        }
        int currentItem = photoViewPager.getCurrentItem();
        Cursor cursor = this.mAdapter.getCursor();
        if (cursor == null) {
            return null;
        }
        cursor.moveToPosition(currentItem);
        return cursor;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Dd */
    public boolean mo5770Dd() {
        return this.mBackground != null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ed */
    public boolean mo5771Ed() {
        return this.f890ew != null;
    }

    /* renamed from: Fd */
    public void mo5772Fd() {
        mo5785d(!this.f878Aj, true);
    }

    /* renamed from: Gd */
    public void mo5773Gd() {
        int currentItem = this.f897lb.getCurrentItem() + 1;
        boolean z = this.f888cw >= 0;
        Cursor Cd = mo5769Cd();
        if (Cd != null) {
            this.f896kw = Cd.getString(Cd.getColumnIndex("_display_name"));
        } else {
            this.f896kw = null;
        }
        if (this.f889dw || !z || currentItem <= 0) {
            this.f898lw = null;
        } else {
            this.f898lw = this.mActivity.getResources().getString(C0755x.photo_view_count, new Object[]{Integer.valueOf(currentItem), Integer.valueOf(this.f888cw)});
        }
        mo5778a(this.mActivity.mo5701ea());
    }

    /* access modifiers changed from: protected */
    /* renamed from: H */
    public void mo5774H(boolean z) {
        int i = Build.VERSION.SDK_INT;
        int i2 = (!z || (this.f900nw && !this.f899mw)) ? 1792 : 3846;
        this.f884Zv = i2;
        this.mRootView.setSystemUiVisibility(i2);
    }

    /* renamed from: a */
    public void mo221a(C0475f fVar, Object obj) {
        Cursor cursor = (Cursor) obj;
        if (fVar.getId() != 100) {
            return;
        }
        if (cursor == null || cursor.getCount() == 0) {
            this.f889dw = true;
            this.mAdapter.swapCursor((Cursor) null);
            return;
        }
        this.f888cw = cursor.getCount();
        if (this.f887bw != null) {
            int columnIndex = cursor.getColumnIndex("uri");
            int i = Build.VERSION.SDK_INT;
            Uri build = Uri.parse(this.f887bw).buildUpon().clearQuery().build();
            cursor.moveToPosition(-1);
            int i2 = 0;
            while (true) {
                if (!cursor.moveToNext()) {
                    break;
                }
                String string = cursor.getString(columnIndex);
                int i3 = Build.VERSION.SDK_INT;
                Uri build2 = Uri.parse(string).buildUpon().clearQuery().build();
                if (build != null && build.equals(build2)) {
                    this.f886aw = i2;
                    break;
                }
                i2++;
            }
        }
        if (this.f882Wb) {
            this.f893iw = true;
            this.mAdapter.swapCursor((Cursor) null);
            return;
        }
        boolean z = this.f889dw;
        this.f889dw = false;
        this.mAdapter.swapCursor(cursor);
        if (this.f897lb.getAdapter() == null) {
            this.f897lb.mo5330a((C0616a) this.mAdapter);
        }
        m1164n(cursor);
        if (this.f886aw < 0) {
            this.f886aw = 0;
        }
        this.f897lb.setCurrentItem(this.f886aw, false);
        if (z) {
            mo5788ea(this.f886aw);
        }
    }

    /* renamed from: a */
    public void mo5780a(PhotoViewFragment photoViewFragment) {
    }

    /* renamed from: a */
    public void mo5781a(PhotoViewFragment photoViewFragment, Cursor cursor) {
    }

    /* renamed from: b */
    public synchronized void mo5783b(C0722f fVar) {
        this.f892hw.remove(fVar);
    }

    /* renamed from: ca */
    public void mo5784ca(int i) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo5785d(boolean z, boolean z2) {
        if (C0107q.m129a(this.mAccessibilityManager)) {
            z = false;
            z2 = false;
        }
        boolean z3 = z != this.f878Aj;
        this.f878Aj = z;
        if (this.f878Aj) {
            mo5774H(true);
            m1155Vn();
        } else {
            mo5774H(false);
            if (z2) {
                m1156Wn();
            }
        }
        if (z3) {
            for (C0726g b : this.f891fw.values()) {
                b.mo5743b(this.f878Aj);
            }
        }
    }

    /* renamed from: da */
    public void mo5786da(int i) {
        this.f891fw.remove(Integer.valueOf(i));
    }

    /* renamed from: ea */
    public void mo5788ea(int i) {
        C0726g gVar = (C0726g) this.f891fw.get(Integer.valueOf(i));
        if (gVar != null) {
            gVar.mo5739M();
        }
        Cursor Cd = mo5769Cd();
        this.f886aw = i;
        this.f887bw = Cd.getString(Cd.getColumnIndex("uri"));
        mo5773Gd();
        if (this.mAccessibilityManager.isEnabled() && this.f908vw != i) {
            String str = this.f896kw;
            if (this.f898lw != null) {
                str = this.mActivity.getContext().getResources().getString(C0755x.titles, new Object[]{this.f896kw, this.f898lw});
            }
            if (str != null) {
                View view = this.mRootView;
                int i2 = Build.VERSION.SDK_INT;
                view.announceForAccessibility(str);
                this.f908vw = i;
            }
        }
        m1155Vn();
        m1156Wn();
    }

    /* access modifiers changed from: protected */
    public View findViewById(int i) {
        return this.mActivity.findViewById(i);
    }

    public C0732m getActivity() {
        return this.mActivity;
    }

    public C0712c getAdapter() {
        return this.mAdapter;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public boolean onBackPressed() {
        ViewPropertyAnimator viewPropertyAnimator;
        if (this.f878Aj && !this.f883Wv) {
            mo5772Fd();
            return true;
        } else if (!this.f900nw) {
            return false;
        } else {
            this.mActivity.getIntent();
            int measuredWidth = this.mRootView.getMeasuredWidth();
            int measuredHeight = this.mRootView.getMeasuredHeight();
            float max = Math.max(((float) this.f903qw) / ((float) measuredWidth), ((float) this.f904rw) / ((float) measuredHeight));
            int a = m1158a(this.f901ow, this.f903qw, measuredWidth, max);
            int a2 = m1158a(this.f902pw, this.f904rw, measuredHeight, max);
            int i = Build.VERSION.SDK_INT;
            if (mo5770Dd()) {
                this.mBackground.animate().alpha(0.0f).setDuration(250).start();
                this.mBackground.setVisibility(0);
            }
            C0730k kVar = new C0730k(this);
            if (!mo5771Ed() || this.f890ew.getVisibility() != 0) {
                viewPropertyAnimator = this.f897lb.animate().scaleX(max).scaleY(max).translationX((float) a).translationY((float) a2).setDuration(250);
            } else {
                viewPropertyAnimator = this.f890ew.animate().scaleX(max).scaleY(max).translationX((float) a).translationY((float) a2).setDuration(250);
            }
            if (!this.f880Nv.equals(this.f887bw)) {
                viewPropertyAnimator.alpha(0.0f);
            }
            viewPropertyAnimator.withEndAction(kVar);
            viewPropertyAnimator.start();
            return true;
        }
    }

    public void onCreate(Bundle bundle) {
        if (f877yw == 0) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ImageUtils$ImageSize imageUtils$ImageSize = C0745e.f914Dw;
            ((WindowManager) this.mActivity.getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if (imageUtils$ImageSize.ordinal() != 0) {
                f877yw = Math.min(displayMetrics.heightPixels, displayMetrics.widthPixels);
            } else {
                f877yw = (Math.min(displayMetrics.heightPixels, displayMetrics.widthPixels) * 800) / 1000;
            }
        }
        f876xw = ((ActivityManager) this.mActivity.getApplicationContext().getSystemService("activity")).getMemoryClass();
        Intent intent = this.mActivity.getIntent();
        if (intent.hasExtra("photos_uri")) {
            this.f894jq = intent.getStringExtra("photos_uri");
        }
        this.f905sw = intent.getBooleanExtra("enable_timer_lights_out", true);
        if (intent.getBooleanExtra("scale_up_animation", false)) {
            this.f900nw = true;
            this.f901ow = intent.getIntExtra("start_x_extra", 0);
            this.f902pw = intent.getIntExtra("start_y_extra", 0);
            this.f903qw = intent.getIntExtra("start_width_extra", 0);
            this.f904rw = intent.getIntExtra("start_height_extra", 0);
        }
        this.f883Wv = intent.getBooleanExtra("action_bar_hidden_initially", false) && !C0107q.m129a(this.mAccessibilityManager);
        this.f879No = intent.getBooleanExtra("display_thumbs_fullscreen", false);
        if (intent.hasExtra("projection")) {
            this.mProjection = intent.getStringArrayExtra("projection");
        } else {
            this.mProjection = null;
        }
        this.f881Ov = intent.getFloatExtra("max_scale", 1.0f);
        this.f887bw = null;
        this.f886aw = -1;
        if (intent.hasExtra("photo_index")) {
            this.f886aw = intent.getIntExtra("photo_index", -1);
        }
        if (intent.hasExtra("initial_photo_uri")) {
            this.f880Nv = intent.getStringExtra("initial_photo_uri");
            this.f887bw = this.f880Nv;
        }
        this.f889dw = true;
        if (bundle != null) {
            this.f880Nv = bundle.getString("com.android.ex.PhotoViewFragment.INITIAL_URI");
            this.f887bw = bundle.getString("com.android.ex.PhotoViewFragment.CURRENT_URI");
            this.f886aw = bundle.getInt("com.android.ex.PhotoViewFragment.CURRENT_INDEX");
            this.f878Aj = bundle.getBoolean("com.android.ex.PhotoViewFragment.FULLSCREEN", false) && !C0107q.m129a(this.mAccessibilityManager);
            this.f896kw = bundle.getString("com.android.ex.PhotoViewFragment.ACTIONBARTITLE");
            this.f898lw = bundle.getString("com.android.ex.PhotoViewFragment.ACTIONBARSUBTITLE");
            this.f899mw = bundle.getBoolean("com.android.ex.PhotoViewFragment.SCALEANIMATIONFINISHED", false);
        } else {
            this.f878Aj = this.f883Wv;
        }
        this.mActivity.setContentView(C0754w.photo_activity_view);
        this.mAdapter = mo5776a(this.mActivity.getContext(), this.mActivity.getSupportFragmentManager(), (Cursor) null, this.f881Ov);
        Resources resources = this.mActivity.getResources();
        this.mRootView = findViewById(C0740u.photo_activity_root_view);
        int i = Build.VERSION.SDK_INT;
        this.mRootView.setOnSystemUiVisibilityChangeListener(this.f885_v);
        this.mBackground = findViewById(C0740u.photo_activity_background);
        this.f890ew = (ImageView) findViewById(C0740u.photo_activity_temporary_image);
        this.f897lb = (PhotoViewPager) findViewById(C0740u.photo_view_pager);
        this.f897lb.mo5330a((C0616a) this.mAdapter);
        this.f897lb.mo5331a((C0626k) this);
        this.f897lb.mo5708a(this);
        this.f897lb.setPageMargin(resources.getDimensionPixelSize(C0738s.photo_page_margin));
        this.f906tw = new C0733n(this, (C0727h) null);
        if (!this.f900nw || this.f899mw) {
            this.mActivity.getSupportLoaderManager().mo224a(100, (Bundle) null, this);
            if (mo5770Dd()) {
                this.mBackground.setVisibility(0);
            }
        } else {
            this.f897lb.setVisibility(8);
            Bundle bundle2 = new Bundle();
            bundle2.putString("image_uri", this.f880Nv);
            this.mActivity.getSupportLoaderManager().mo224a(2, bundle2, this.f906tw);
        }
        this.f907uw = (long) resources.getInteger(C0747v.reenter_fullscreen_delay_time_in_millis);
        C0713b ea = this.mActivity.mo5701ea();
        if (ea != null) {
            ea.setDisplayHomeAsUpEnabled(true);
            ea.mo5719b(this);
            ea.mo5718Bd();
            mo5778a(ea);
        }
        if (!this.f900nw) {
            mo5774H(this.f878Aj);
        } else {
            mo5774H(false);
        }
    }

    public C0475f onCreateLoader(int i, Bundle bundle) {
        if (i == 100) {
            return new C0717d(this.mActivity.getContext(), Uri.parse(this.f894jq), this.mProjection);
        }
        return null;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void onDestroy() {
        this.f895jw = true;
    }

    public void onEnterAnimationComplete() {
        this.f899mw = true;
        this.f897lb.setVisibility(0);
        mo5774H(this.f878Aj);
    }

    public void onMenuVisibilityChanged(boolean z) {
        if (z) {
            m1155Vn();
        } else {
            m1156Wn();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        this.mActivity.finish();
        return true;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (((double) f) < 1.0E-4d) {
            C0726g gVar = (C0726g) this.f891fw.get(Integer.valueOf(i - 1));
            if (gVar != null) {
                gVar.mo5748ha();
            }
            C0726g gVar2 = (C0726g) this.f891fw.get(Integer.valueOf(i + 1));
            if (gVar2 != null) {
                gVar2.mo5748ha();
            }
        }
    }

    public void onPageSelected(int i) {
        this.f886aw = i;
        mo5788ea(i);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    public void onResume() {
        mo5785d(this.f878Aj, false);
        this.f882Wb = false;
        if (this.f893iw) {
            this.f893iw = false;
            this.mActivity.getSupportLoaderManager().mo224a(100, (Bundle) null, this);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("com.android.ex.PhotoViewFragment.INITIAL_URI", this.f880Nv);
        bundle.putString("com.android.ex.PhotoViewFragment.CURRENT_URI", this.f887bw);
        bundle.putInt("com.android.ex.PhotoViewFragment.CURRENT_INDEX", this.f886aw);
        bundle.putBoolean("com.android.ex.PhotoViewFragment.FULLSCREEN", this.f878Aj);
        bundle.putString("com.android.ex.PhotoViewFragment.ACTIONBARTITLE", this.f896kw);
        bundle.putString("com.android.ex.PhotoViewFragment.ACTIONBARSUBTITLE", this.f898lw);
        bundle.putBoolean("com.android.ex.PhotoViewFragment.SCALEANIMATIONFINISHED", this.f899mw);
    }

    public void onStart() {
    }

    public void onStop() {
    }

    /* renamed from: u */
    public boolean mo5805u(C0424j jVar) {
        PhotoViewPager photoViewPager = this.f897lb;
        if (photoViewPager == null || this.mAdapter == null || photoViewPager.getCurrentItem() != this.mAdapter.getItemPosition(jVar)) {
            return false;
        }
        return true;
    }

    /* renamed from: v */
    public boolean mo5806v(C0424j jVar) {
        C0712c cVar;
        if (this.f897lb == null || (cVar = this.mAdapter) == null || cVar.getCount() == 0) {
            return this.f878Aj;
        }
        return this.f878Aj || this.f897lb.getCurrentItem() != this.mAdapter.getItemPosition(jVar);
    }

    /* renamed from: e */
    public PhotoViewPager.InterceptType mo5787e(float f, float f2) {
        boolean z = false;
        boolean z2 = false;
        for (C0726g gVar : this.f891fw.values()) {
            if (!z) {
                z = gVar.mo5742a(f, f2);
            }
            if (!z2) {
                z2 = gVar.mo5744b(f, f2);
            }
        }
        if (z) {
            if (z2) {
                return PhotoViewPager.InterceptType.BOTH;
            }
            return PhotoViewPager.InterceptType.LEFT;
        } else if (z2) {
            return PhotoViewPager.InterceptType.RIGHT;
        } else {
            return PhotoViewPager.InterceptType.NONE;
        }
    }

    /* renamed from: d */
    static /* synthetic */ void m1161d(C0734o oVar) {
        oVar.mActivity.finish();
        oVar.mActivity.overridePendingTransition(0, 0);
    }

    /* renamed from: a */
    public C0712c mo5776a(Context context, C0433s sVar, Cursor cursor, float f) {
        return new C0712c(context, sVar, cursor, f, this.f879No);
    }

    /* renamed from: a */
    public void mo220a(C0475f fVar) {
        if (!this.f895jw) {
            this.mAdapter.swapCursor((Cursor) null);
        }
    }

    /* renamed from: a */
    public void mo5777a(int i, C0726g gVar) {
        this.f891fw.put(Integer.valueOf(i), gVar);
    }

    /* renamed from: a */
    public synchronized void mo5779a(C0722f fVar) {
        this.f892hw.add(fVar);
    }

    /* renamed from: a */
    public C0475f mo5775a(int i, Bundle bundle, String str) {
        if (i == 1 || i == 2 || i == 3) {
            return new C0714a(this.mActivity.getContext(), str);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo5778a(C0713b bVar) {
        if (bVar != null) {
            String str = this.f896kw;
            if (str == null) {
                str = "";
            }
            bVar.setTitle(str);
            String str2 = this.f898lw;
            if (str2 == null) {
                str2 = "";
            }
            bVar.setSubtitle(str2);
        }
    }

    /* renamed from: a */
    public void mo5782a(PhotoViewFragment photoViewFragment, boolean z) {
        if (mo5771Ed() && this.f890ew.getVisibility() != 8 && TextUtils.equals(photoViewFragment.mo5752uc(), this.f887bw)) {
            if (z) {
                if (mo5771Ed()) {
                    this.f890ew.setVisibility(8);
                }
                this.f897lb.setVisibility(0);
            } else {
                Log.w("PhotoViewController", "Failed to load fragment image");
                if (mo5771Ed()) {
                    this.f890ew.setVisibility(8);
                }
                this.f897lb.setVisibility(0);
            }
            this.mActivity.getSupportLoaderManager().destroyLoader(2);
        }
    }

    /* renamed from: a */
    private int m1158a(int i, int i2, int i3, float f) {
        float f2 = (float) i3;
        float f3 = f * f2;
        return (i - Math.round((f2 - f3) / 2.0f)) - Math.round((f3 - ((float) i2)) / 2.0f);
    }

    /* renamed from: a */
    static /* synthetic */ void m1159a(C0734o oVar, Drawable drawable) {
        if (!oVar.f899mw) {
            if (oVar.mo5771Ed()) {
                oVar.f890ew.setImageDrawable(drawable);
            }
            if (drawable != null) {
                if (oVar.mRootView.getMeasuredWidth() == 0) {
                    View view = oVar.mRootView;
                    view.getViewTreeObserver().addOnGlobalLayoutListener(new C0731l(oVar, view));
                } else {
                    oVar.m1157Xn();
                }
            }
            oVar.mActivity.getSupportLoaderManager().mo224a(100, (Bundle) null, oVar);
        }
    }
}
