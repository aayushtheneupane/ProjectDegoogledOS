package com.android.messaging.p041ui.mediapicker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.p016v4.media.session.C0107q;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBar;
import androidx.viewpager.widget.C0616a;
import androidx.viewpager.widget.C0626k;
import androidx.viewpager.widget.ViewPager;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.data.C0896H;
import com.android.messaging.datamodel.data.C0943z;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.data.PendingAttachmentData;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.datamodel.p037a.C0786f;
import com.android.messaging.p041ui.BugleActionBarActivity;
import com.android.messaging.p041ui.C1057N;
import com.android.messaging.util.C1424b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.android.messaging.ui.mediapicker.pa */
public class C1345pa extends Fragment implements C0943z {

    /* renamed from: Sa */
    private C0786f f2129Sa;

    /* renamed from: eb */
    private Handler f2130eb;

    /* renamed from: fb */
    private int f2131fb;

    /* renamed from: gb */
    private final C1323ea[] f2132gb;
    /* access modifiers changed from: private */

    /* renamed from: hb */
    public final ArrayList f2133hb;

    /* renamed from: ib */
    private C1323ea f2134ib;

    /* renamed from: jb */
    private MediaPickerPanel f2135jb;

    /* renamed from: kb */
    private LinearLayout f2136kb;

    /* renamed from: lb */
    private ViewPager f2137lb;
    final C0783c mBinding;
    private boolean mIsAttached;
    /* access modifiers changed from: private */
    public C1343oa mListener;

    /* renamed from: mb */
    private C1057N f2138mb;

    /* renamed from: nb */
    private boolean f2139nb;

    /* renamed from: ob */
    private int f2140ob;

    /* renamed from: pb */
    private C0943z f2141pb;

    /* renamed from: qb */
    private int f2142qb;

    /* renamed from: rb */
    private boolean f2143rb;

    public C1345pa() {
        this(C0967f.get().getApplicationContext());
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0055 A[EDGE_INSN: B:26:0x0055->B:19:0x0055 ?: BREAK  , SYNTHETIC] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m3393c(int r5, boolean r6) {
        /*
            r4 = this;
            com.android.messaging.f r0 = com.android.messaging.C0967f.get()
            android.content.Context r0 = r0.getApplicationContext()
            boolean r0 = android.support.p016v4.media.session.C0107q.m134f(r0)
            if (r5 != 0) goto L_0x0033
            com.android.messaging.datamodel.a.c r1 = r4.mBinding
            com.android.messaging.datamodel.a.a r1 = r1.getData()
            com.android.messaging.datamodel.data.H r1 = (com.android.messaging.datamodel.data.C0896H) r1
            int r1 = r1.mo6232qf()
            if (r1 < 0) goto L_0x0030
            java.util.ArrayList r2 = r4.f2133hb
            int r2 = r2.size()
            if (r1 >= r2) goto L_0x0030
            java.util.ArrayList r2 = r4.f2133hb
            java.lang.Object r1 = r2.get(r1)
            com.android.messaging.ui.mediapicker.ea r1 = (com.android.messaging.p041ui.mediapicker.C1323ea) r1
            r4.mo7898a((com.android.messaging.p041ui.mediapicker.C1323ea) r1)
            goto L_0x0033
        L_0x0030:
            if (r0 == 0) goto L_0x0033
            r5 = 4
        L_0x0033:
            com.android.messaging.ui.mediapicker.ea r1 = r4.f2134ib
            if (r1 != 0) goto L_0x0055
            java.util.ArrayList r1 = r4.f2133hb
            java.util.Iterator r1 = r1.iterator()
        L_0x003d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0055
            java.lang.Object r2 = r1.next()
            com.android.messaging.ui.mediapicker.ea r2 = (com.android.messaging.p041ui.mediapicker.C1323ea) r2
            if (r5 == 0) goto L_0x0052
            int r3 = r2.mo7674Ri()
            r3 = r3 & r5
            if (r3 == 0) goto L_0x003d
        L_0x0052:
            r4.mo7898a((com.android.messaging.p041ui.mediapicker.C1323ea) r2)
        L_0x0055:
            com.android.messaging.ui.mediapicker.ea r5 = r4.f2134ib
            if (r5 != 0) goto L_0x0065
            java.util.ArrayList r5 = r4.f2133hb
            r1 = 0
            java.lang.Object r5 = r5.get(r1)
            com.android.messaging.ui.mediapicker.ea r5 = (com.android.messaging.p041ui.mediapicker.C1323ea) r5
            r4.mo7898a((com.android.messaging.p041ui.mediapicker.C1323ea) r5)
        L_0x0065:
            com.android.messaging.ui.mediapicker.MediaPickerPanel r5 = r4.f2135jb
            if (r5 == 0) goto L_0x007a
            r5.mo7739x(r0)
            com.android.messaging.ui.mediapicker.MediaPickerPanel r5 = r4.f2135jb
            r0 = 1
            java.util.ArrayList r1 = r4.f2133hb
            com.android.messaging.ui.mediapicker.ea r4 = r4.f2134ib
            int r4 = r1.indexOf(r4)
            r5.mo7731a((boolean) r0, (boolean) r6, (int) r4)
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.mediapicker.C1345pa.m3393c(int, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Aa */
    public void mo7882Aa() {
        if (this.mListener != null) {
            this.f2130eb.post(new C1337la(this));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ba */
    public void mo7883Ba() {
        setHasOptionsMenu(false);
        this.f2139nb = false;
        if (this.mListener != null) {
            this.f2130eb.post(new C1329ha(this));
        }
        C1323ea eaVar = this.f2134ib;
        if (eaVar != null) {
            eaVar.mo7675S(false);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ca */
    public void mo7884Ca() {
        setHasOptionsMenu(false);
        this.f2139nb = true;
        this.f2138mb.notifyDataSetChanged();
        if (this.mListener != null) {
            this.f2130eb.post(new C1327ga(this));
        }
        C1323ea eaVar = this.f2134ib;
        if (eaVar != null) {
            eaVar.mo7678b(false);
            this.f2134ib.mo7675S(true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Da */
    public boolean mo7885Da() {
        C1323ea eaVar = this.f2134ib;
        return (eaVar == null || eaVar.mo7672Pi() == 0) ? false : true;
    }

    /* renamed from: Ea */
    public int mo7886Ea() {
        return this.f2140ob;
    }

    /* renamed from: Fa */
    public C0786f mo7887Fa() {
        return this.f2129Sa;
    }

    /* renamed from: Ga */
    public C0786f mo7888Ga() {
        return C0784d.m1314f(this.mBinding);
    }

    /* renamed from: H */
    public int mo6582H() {
        return this.f2141pb.mo6582H();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ha */
    public C0616a mo7889Ha() {
        return this.f2138mb;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ia */
    public ViewPager mo7890Ia() {
        return this.f2137lb;
    }

    /* renamed from: Ja */
    public boolean mo7891Ja() {
        C1323ea eaVar = this.f2134ib;
        if (eaVar == null) {
            return false;
        }
        return eaVar.mo7771Ti();
    }

    /* renamed from: Ka */
    public void mo7892Ka() {
        this.f2138mb.resetState();
    }

    /* renamed from: La */
    public void mo7893La() {
        C1323ea eaVar = this.f2134ib;
        if (eaVar != null) {
            eaVar.mo7770Kb();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo7902d(MessagePartData messagePartData) {
        if (this.mListener != null) {
            this.f2130eb.post(new C1335ka(this, messagePartData));
        }
        if (isFullScreen()) {
            invalidateOptionsMenu();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public void mo7903h(int i) {
        if (this.mListener != null) {
            this.f2130eb.post(new C1341na(this, i));
        }
    }

    /* renamed from: i */
    public void mo7904i(int i) {
        this.f2140ob = i;
        LinearLayout linearLayout = this.f2136kb;
        if (linearLayout != null) {
            linearLayout.setBackgroundColor(this.f2140ob);
        }
        Iterator it = this.f2133hb.iterator();
        while (it.hasNext()) {
            ((C1323ea) it.next()).mo7773u(this.f2140ob);
        }
    }

    /* access modifiers changed from: package-private */
    public void invalidateOptionsMenu() {
        ((BugleActionBarActivity) getActivity()).supportInvalidateOptionsMenu();
    }

    public boolean isFullScreen() {
        MediaPickerPanel mediaPickerPanel = this.f2135jb;
        return mediaPickerPanel != null && mediaPickerPanel.isFullScreen();
    }

    public boolean isOpen() {
        return this.f2139nb;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public void mo7909j(int i) {
        this.f2131fb = i;
        this.f2133hb.clear();
        boolean z = false;
        for (C1323ea eaVar : this.f2132gb) {
            boolean z2 = (eaVar.mo7674Ri() & this.f2131fb) != 0;
            if (z2) {
                this.f2133hb.add(eaVar);
                if (z) {
                    mo7898a(eaVar);
                    z = false;
                }
            } else if (this.f2134ib == eaVar) {
                z = true;
            }
            ImageButton Si = eaVar.mo7853Si();
            if (Si != null) {
                Si.setVisibility(z2 ? 0 : 8);
            }
        }
        if (z && this.f2133hb.size() > 0) {
            mo7898a((C1323ea) this.f2133hb.get(0));
        }
        C1323ea[] eaVarArr = new C1323ea[this.f2133hb.size()];
        this.f2133hb.toArray(eaVarArr);
        this.f2138mb = new C1057N(eaVarArr);
        ViewPager viewPager = this.f2137lb;
        if (viewPager != null) {
            viewPager.mo5330a((C0616a) this.f2138mb);
        }
        if (this.mBinding.isBound() && getActivity() != null) {
            this.mBinding.unbind();
            this.mBinding.mo5930b(C0947h.get().mo6610h(getActivity()));
            ((C0896H) this.mBinding.getData()).mo6229a(getLoaderManager());
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.f2134ib.onActivityResult(i, i2, intent);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mIsAttached = true;
        int i = this.f2142qb;
        if (i != 32) {
            m3393c(i, this.f2143rb);
        }
    }

    public boolean onBackPressed() {
        C1323ea eaVar = this.f2134ib;
        if (eaVar == null) {
            return false;
        }
        eaVar.onBackPressed();
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((C0896H) this.mBinding.getData()).mo6229a(getLoaderManager());
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        C1323ea eaVar = this.f2134ib;
        if (eaVar != null) {
            eaVar.mo7756a(menuInflater, menu);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2135jb = (MediaPickerPanel) layoutInflater.inflate(R.layout.mediapicker_fragment, viewGroup, false);
        this.f2135jb.mo7732c(this);
        this.f2136kb = (LinearLayout) this.f2135jb.findViewById(R.id.mediapicker_tabstrip);
        this.f2136kb.setBackgroundColor(this.f2140ob);
        C1323ea[] eaVarArr = this.f2132gb;
        int length = eaVarArr.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i < length) {
                C1323ea eaVar = eaVarArr[i];
                eaVar.mo7854a(layoutInflater, (ViewGroup) this.f2136kb);
                if ((eaVar.mo7674Ri() & this.f2131fb) == 0) {
                    z = false;
                }
                ImageButton Si = eaVar.mo7853Si();
                if (Si != null) {
                    Si.setVisibility(z ? 0 : 8);
                    this.f2136kb.addView(Si);
                }
                i++;
            } else {
                this.f2137lb = (ViewPager) this.f2135jb.findViewById(R.id.mediapicker_view_pager);
                this.f2137lb.mo5331a((C0626k) new C1325fa(this));
                this.f2137lb.setOffscreenPageLimit(0);
                this.f2137lb.mo5330a((C0616a) this.f2138mb);
                this.f2135jb.mo7739x(C0107q.m134f(getActivity()));
                this.f2135jb.mo7731a(this.f2139nb, true, this.f2133hb.indexOf(this.f2134ib));
                return this.f2135jb;
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBinding.unbind();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        C1323ea eaVar = this.f2134ib;
        return (eaVar != null && eaVar.onOptionsItemSelected(menuItem)) || super.onOptionsItemSelected(menuItem);
    }

    public void onPause() {
        super.onPause();
        C1352t.get().onPause();
        Iterator it = this.f2133hb.iterator();
        while (it.hasNext()) {
            ((C1323ea) it.next()).onPause();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        C1323ea eaVar = this.f2134ib;
        if (eaVar != null) {
            eaVar.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void onResume() {
        super.onResume();
        C1352t.get().onResume();
        Iterator it = this.f2133hb.iterator();
        while (it.hasNext()) {
            ((C1323ea) it.next()).onResume();
        }
    }

    public void setFullScreen(boolean z) {
        this.f2135jb.mo7733c(z, true);
    }

    public void updateActionBar(ActionBar actionBar) {
        C1323ea eaVar;
        if (getActivity() != null) {
            if (!isFullScreen() || (eaVar = this.f2134ib) == null) {
                actionBar.hide();
            } else {
                eaVar.updateActionBar(actionBar);
            }
        }
    }

    /* renamed from: ya */
    public boolean mo7924ya() {
        C1323ea eaVar = this.f2134ib;
        if (eaVar != null) {
            eaVar.mo7858ya();
        }
        return false;
    }

    /* renamed from: za */
    public boolean mo7925za() {
        C1323ea eaVar = this.f2134ib;
        if (eaVar == null) {
            return false;
        }
        return eaVar.mo7671Eb();
    }

    public C1345pa(Context context) {
        this.mBinding = C0784d.m1315q(this);
        this.f2142qb = 32;
        this.mBinding.mo5930b(C0947h.get().mo6610h(context));
        this.f2133hb = new ArrayList();
        this.f2132gb = new C1323ea[]{new C1275C(this), new C1295X(this), new C1300b(this), new C1281I(this)};
        this.f2139nb = false;
        mo7909j(65535);
    }

    /* renamed from: a */
    public void mo7895a(C0784d dVar) {
        this.f2129Sa = C0784d.m1314f(dVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo7900b(Collection collection, boolean z) {
        if (this.mListener != null) {
            this.f2130eb.post(new C1333ja(this, collection, z));
        }
        if (isFullScreen() && !z) {
            invalidateOptionsMenu();
        }
    }

    /* renamed from: a */
    public void mo7897a(C0943z zVar) {
        this.f2141pb = zVar;
    }

    /* renamed from: a */
    public void mo7894a(int i, boolean z) {
        this.f2139nb = true;
        if (this.mIsAttached) {
            m3393c(i, z);
            return;
        }
        this.f2142qb = i;
        this.f2143rb = z;
    }

    /* renamed from: i */
    public void mo7905i(boolean z) {
        this.f2139nb = false;
        MediaPickerPanel mediaPickerPanel = this.f2135jb;
        if (mediaPickerPanel != null) {
            mediaPickerPanel.mo7731a(false, z, -1);
        }
        this.f2134ib = null;
    }

    /* renamed from: a */
    public void mo7899a(C1343oa oaVar) {
        C1424b.m3593oc();
        this.mListener = oaVar;
        this.f2130eb = oaVar != null ? new Handler() : null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo7898a(C1323ea eaVar) {
        C1323ea eaVar2 = this.f2134ib;
        if (eaVar2 != eaVar) {
            if (eaVar2 != null) {
                eaVar2.setSelected(false);
            }
            this.f2134ib = eaVar;
            C1323ea eaVar3 = this.f2134ib;
            if (eaVar3 != null) {
                eaVar3.setSelected(true);
            }
            int indexOf = this.f2133hb.indexOf(this.f2134ib);
            ViewPager viewPager = this.f2137lb;
            if (viewPager != null) {
                viewPager.setCurrentItem(indexOf, true);
            }
            if (isFullScreen()) {
                invalidateOptionsMenu();
            }
            ((C0896H) this.mBinding.getData()).mo6231na(indexOf);
            MediaPickerPanel mediaPickerPanel = this.f2135jb;
            if (mediaPickerPanel != null) {
                mediaPickerPanel.mo7730Yb();
            }
            mo7903h(indexOf);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo7901c(PendingAttachmentData pendingAttachmentData) {
        if (this.mListener != null) {
            this.f2130eb.post(new C1339ma(this, pendingAttachmentData));
        }
        if (isFullScreen()) {
            invalidateOptionsMenu();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public void mo7910j(boolean z) {
        setHasOptionsMenu(z);
        if (this.mListener != null) {
            this.f2130eb.post(new C1331ia(this, z));
        }
        C1323ea eaVar = this.f2134ib;
        if (eaVar != null) {
            eaVar.mo7678b(z);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo7896a(MessagePartData messagePartData, boolean z) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(messagePartData);
        mo7900b(arrayList, z);
    }
}
