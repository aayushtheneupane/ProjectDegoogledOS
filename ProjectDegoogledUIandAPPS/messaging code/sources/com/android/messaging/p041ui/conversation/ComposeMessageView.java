package com.android.messaging.p041ui.conversation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.p016v4.media.session.C0107q;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0889A;
import com.android.messaging.datamodel.data.C0917ba;
import com.android.messaging.datamodel.data.C0924g;
import com.android.messaging.datamodel.data.C0931n;
import com.android.messaging.datamodel.data.C0941x;
import com.android.messaging.datamodel.data.C0942y;
import com.android.messaging.datamodel.data.C0943z;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.data.PendingAttachmentData;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.datamodel.p037a.C0786f;
import com.android.messaging.p041ui.AttachmentPreview;
import com.android.messaging.p041ui.BugleActionBarActivity;
import com.android.messaging.p041ui.PlainTextEditText;
import com.android.messaging.sms.C1024t;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1451h;
import com.android.messaging.util.C1464na;
import java.util.Collection;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.ui.conversation.ComposeMessageView */
public class ComposeMessageView extends LinearLayout implements TextView.OnEditorActionListener, C0942y, TextWatcher, C1153W {

    /* renamed from: Ah */
    private TextView f1766Ah;

    /* renamed from: Bh */
    private SimIconView f1767Bh;

    /* renamed from: Ch */
    private ImageButton f1768Ch;

    /* renamed from: Dh */
    private ImageButton f1769Dh;

    /* renamed from: Eh */
    private AttachmentPreview f1770Eh;

    /* renamed from: Fh */
    private ImageButton f1771Fh;

    /* renamed from: Gh */
    private final Context f1772Gh;

    /* renamed from: Hh */
    private int f1773Hh = 1;
    /* access modifiers changed from: private */

    /* renamed from: Ih */
    public C0786f f1774Ih;
    /* access modifiers changed from: private */

    /* renamed from: Jh */
    public C1158aa f1775Jh;

    /* renamed from: Kh */
    private final C0924g f1776Kh = new C1163d(this);

    /* renamed from: Pf */
    private View f1777Pf;
    /* access modifiers changed from: private */
    public final C0783c mBinding;
    /* access modifiers changed from: private */
    public C1183n mHost;
    /* access modifiers changed from: private */

    /* renamed from: xh */
    public PlainTextEditText f1778xh;
    /* access modifiers changed from: private */

    /* renamed from: yh */
    public PlainTextEditText f1779yh;

    /* renamed from: zh */
    private TextView f1780zh;

    public ComposeMessageView(Context context, AttributeSet attributeSet) {
        super(new ContextThemeWrapper(context, R.style.ColorAccentBlueOverrideStyle), attributeSet);
        this.f1772Gh = context;
        this.mBinding = C0784d.m1315q(this);
    }

    /* access modifiers changed from: private */
    /* renamed from: _m */
    public C0917ba m2751_m() {
        return ((C0931n) this.f1774Ih.getData()).mo6466a(((C0889A) this.mBinding.getData()).mo6198kf(), false);
    }

    /* renamed from: an */
    private boolean m2753an() {
        Uri T = this.mHost.mo7383T();
        if (T == null) {
            return false;
        }
        return "g".equals(C1426c.m3603p(T));
    }

    /* access modifiers changed from: private */
    /* renamed from: bn */
    public void m2756bn() {
        this.f1778xh.setFilters(new InputFilter[]{new InputFilter.LengthFilter(C1024t.get(((C0889A) this.mBinding.getData()).mo6199lf()).mo6845qi())});
        this.f1779yh.setFilters(new InputFilter[]{new InputFilter.LengthFilter(C1024t.get(((C0889A) this.mBinding.getData()).mo6199lf()).mo6844pi())});
    }

    /* renamed from: d */
    static /* synthetic */ void m2758d(ComposeMessageView composeMessageView) {
        composeMessageView.f1777Pf.setVisibility(8);
        composeMessageView.f1778xh.requestFocus();
    }

    /* renamed from: j */
    static /* synthetic */ void m2764j(ComposeMessageView composeMessageView) {
        if (composeMessageView.f1775Jh.mo7473k(false, true)) {
            composeMessageView.m2771va(false);
        }
    }

    /* renamed from: kb */
    private void m2766kb(int i) {
        String str;
        if (i == 1) {
            this.f1767Bh.setImportantForAccessibility(2);
            this.f1767Bh.setContentDescription((CharSequence) null);
            this.f1768Ch.setVisibility(8);
            m2768lb(1);
        } else if (i == 2) {
            this.f1767Bh.setImportantForAccessibility(1);
            SimIconView simIconView = this.f1767Bh;
            C0917ba _m = m2751_m();
            if (_m != null) {
                str = getResources().getString(R.string.sim_selector_button_content_description_with_selection, new Object[]{_m.displayName});
            } else {
                str = getResources().getString(R.string.sim_selector_button_content_description);
            }
            simIconView.setContentDescription(str);
            m2768lb(2);
        } else if (i == 3) {
            this.f1766Ah.setImportantForAccessibility(2);
            this.f1766Ah.setContentDescription((CharSequence) null);
            m2768lb(3);
        }
    }

    /* renamed from: lb */
    private void m2768lb(int i) {
        if (C1464na.m3759Zj()) {
            this.f1771Fh.setAccessibilityTraversalBefore(R.id.compose_message_text);
            if (i == 2) {
                this.f1778xh.setAccessibilityTraversalBefore(R.id.self_send_icon);
            } else if (i == 3) {
                this.f1778xh.setAccessibilityTraversalBefore(R.id.send_message_button);
            }
        }
    }

    /* renamed from: m */
    static /* synthetic */ boolean m2769m(ComposeMessageView composeMessageView) {
        if (composeMessageView.f1777Pf.getVisibility() != 8) {
            return false;
        }
        composeMessageView.f1777Pf.setVisibility(0);
        composeMessageView.f1777Pf.requestFocus();
        return true;
    }

    /* renamed from: ua */
    private void m2770ua(boolean z) {
        String str;
        Resources resources = getContext().getResources();
        if (z) {
            str = resources.getString(R.string.mediapicker_gallery_item_selected_content_description);
        } else {
            str = resources.getString(R.string.mediapicker_gallery_item_unselected_content_description);
        }
        C0107q.m128a((View) this, (AccessibilityManager) null, (CharSequence) str);
    }

    /* access modifiers changed from: private */
    /* renamed from: va */
    public void m2771va(boolean z) {
        if (this.mHost.mo7396c()) {
            boolean mf = ((C0889A) this.mBinding.getData()).mo6201mf();
            if (!z || !mf) {
                this.mHost.mo7400f(mf);
                this.f1770Eh.mo6871b((C0889A) this.mBinding.getData());
                return;
            }
            this.mHost.mo7400f(false);
            this.f1770Eh.mo6867Gb();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: wa */
    public void m2772wa(boolean z) {
        StringBuilder Pa = C0632a.m1011Pa("UI initiated message sending in conversation ");
        Pa.append(((C0889A) this.mBinding.getData()).mo6170Ue());
        C1430e.m3625i("MessagingApp", Pa.toString());
        if (((C0889A) this.mBinding.getData()).mo6203of()) {
            C1430e.m3630w("MessagingApp", "Message can't be sent: still checking draft");
        } else if (this.mHost.mo7402g()) {
            this.f1775Jh.mo7473k(false, true);
            ((C0889A) this.mBinding.getData()).mo6180aa(this.f1778xh.getText().toString());
            ((C0889A) this.mBinding.getData()).mo6171Z(this.f1779yh.getText().toString());
            ((C0889A) this.mBinding.getData()).mo6178a(z, this.mHost.mo6582H(), (C0941x) new C1159b(this, z), this.mBinding);
        } else {
            this.mHost.mo7393a(true, (Runnable) new C1161c(this, z));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01bf  */
    /* renamed from: xa */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m2773xa(boolean r12) {
        /*
            r11 = this;
            com.android.messaging.ui.PlainTextEditText r0 = r11.f1778xh
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            com.android.messaging.datamodel.a.c r1 = r11.mBinding
            com.android.messaging.datamodel.a.a r1 = r1.getData()
            com.android.messaging.datamodel.data.A r1 = (com.android.messaging.datamodel.data.C0889A) r1
            r1.mo6180aa(r0)
            com.android.messaging.ui.PlainTextEditText r2 = r11.f1779yh
            android.text.Editable r2 = r2.getText()
            java.lang.String r2 = r2.toString()
            r1.mo6171Z(r2)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            r4 = 0
            if (r3 != 0) goto L_0x002e
            android.view.View r3 = r11.f1777Pf
            r3.setVisibility(r4)
        L_0x002e:
            int r0 = android.text.TextUtils.getTrimmedLength(r0)
            r3 = 1
            if (r0 <= 0) goto L_0x0037
            r0 = r3
            goto L_0x0038
        L_0x0037:
            r0 = r4
        L_0x0038:
            int r2 = android.text.TextUtils.getTrimmedLength(r2)
            if (r2 <= 0) goto L_0x0040
            r2 = r3
            goto L_0x0041
        L_0x0040:
            r2 = r4
        L_0x0041:
            if (r0 != 0) goto L_0x0056
            if (r2 != 0) goto L_0x0056
            com.android.messaging.datamodel.a.c r0 = r11.mBinding
            com.android.messaging.datamodel.a.a r0 = r0.getData()
            com.android.messaging.datamodel.data.A r0 = (com.android.messaging.datamodel.data.C0889A) r0
            boolean r0 = r0.mo6201mf()
            if (r0 == 0) goto L_0x0054
            goto L_0x0056
        L_0x0054:
            r0 = r4
            goto L_0x0057
        L_0x0056:
            r0 = r3
        L_0x0057:
            java.util.List r2 = r1.mo6195if()
            boolean r5 = r1.mo6190ff()
            r6 = 3
            r7 = 2
            r8 = 0
            r9 = 4
            if (r5 == 0) goto L_0x0090
            boolean r5 = r1.mo6201mf()
            if (r5 == 0) goto L_0x008a
            if (r12 == 0) goto L_0x0084
            com.android.messaging.ui.conversation.m r12 = new com.android.messaging.ui.conversation.m
            android.content.Context r5 = r11.getContext()
            android.widget.TextView r10 = r11.f1780zh
            r12.<init>(r5, r10)
            java.util.List[] r5 = new java.util.List[r6]
            r5[r4] = r2
            r5[r3] = r8
            r5[r7] = r8
            r12.mo8233b(r5)
            goto L_0x00ca
        L_0x0084:
            android.widget.TextView r12 = r11.f1780zh
            r12.setVisibility(r4)
            goto L_0x00ca
        L_0x008a:
            android.widget.TextView r12 = r11.f1780zh
            r12.setVisibility(r9)
            goto L_0x00ca
        L_0x0090:
            int r12 = r1.mo6196je()
            int r5 = r1.mo6193he()
            if (r12 > r3) goto L_0x00a5
            r10 = 10
            if (r5 > r10) goto L_0x009f
            goto L_0x00a5
        L_0x009f:
            android.widget.TextView r12 = r11.f1780zh
            r12.setVisibility(r9)
            goto L_0x00ca
        L_0x00a5:
            if (r12 <= r3) goto L_0x00bc
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r5)
            java.lang.String r5 = " / "
            r10.append(r5)
            r10.append(r12)
            java.lang.String r12 = r10.toString()
            goto L_0x00c0
        L_0x00bc:
            java.lang.String r12 = java.lang.String.valueOf(r5)
        L_0x00c0:
            android.widget.TextView r5 = r11.f1780zh
            r5.setText(r12)
            android.widget.TextView r12 = r11.f1780zh
            r12.setVisibility(r4)
        L_0x00ca:
            com.android.messaging.ui.conversation.n r12 = r11.mHost
            android.net.Uri r12 = r12.mo7383T()
            if (r12 == 0) goto L_0x00d3
            goto L_0x00f0
        L_0x00d3:
            com.android.messaging.datamodel.data.ba r12 = r11.m2751_m()
            if (r12 == 0) goto L_0x00dc
            android.net.Uri r12 = r12.f1216nC
            goto L_0x00f0
        L_0x00dc:
            com.android.messaging.datamodel.a.f r12 = r11.f1774Ih
            com.android.messaging.datamodel.a.a r12 = r12.getData()
            com.android.messaging.datamodel.data.n r12 = (com.android.messaging.datamodel.data.C0931n) r12
            com.android.messaging.datamodel.data.ParticipantData r12 = r12.mo6459Ve()
            if (r12 != 0) goto L_0x00ec
            r12 = r8
            goto L_0x00f0
        L_0x00ec:
            android.net.Uri r12 = com.android.messaging.util.C1426c.m3601c(r12)
        L_0x00f0:
            if (r12 == 0) goto L_0x0168
            r5 = 8
            if (r0 == 0) goto L_0x0133
            com.android.messaging.datamodel.a.f r0 = r11.f1774Ih
            if (r0 == 0) goto L_0x0110
            boolean r0 = r0.isBound()
            if (r0 == 0) goto L_0x0110
            com.android.messaging.datamodel.a.f r0 = r11.f1774Ih
            com.android.messaging.datamodel.a.a r0 = r0.getData()
            com.android.messaging.datamodel.data.n r0 = (com.android.messaging.datamodel.data.C0931n) r0
            boolean r0 = r0.mo6465_e()
            if (r0 == 0) goto L_0x0110
            r0 = r3
            goto L_0x0111
        L_0x0110:
            r0 = r4
        L_0x0111:
            if (r0 == 0) goto L_0x0133
            android.widget.ImageButton r12 = r11.f1768Ch
            com.android.messaging.util.C1486ya.m3854a((android.view.View) r12, (int) r4, (java.lang.Runnable) r8)
            boolean r12 = r11.m2753an()
            if (r12 == 0) goto L_0x0123
            com.android.messaging.ui.conversation.SimIconView r12 = r11.f1767Bh
            com.android.messaging.util.C1486ya.m3854a((android.view.View) r12, (int) r5, (java.lang.Runnable) r8)
        L_0x0123:
            android.widget.TextView r12 = r11.f1766Ah
            boolean r0 = r1.mo6190ff()
            if (r0 == 0) goto L_0x012d
            r0 = r4
            goto L_0x012e
        L_0x012d:
            r0 = r9
        L_0x012e:
            r12.setVisibility(r0)
            r12 = r6
            goto L_0x016e
        L_0x0133:
            com.android.messaging.ui.conversation.SimIconView r0 = r11.f1767Bh
            r0.mo6930f(r12)
            boolean r12 = r11.m2753an()
            if (r12 == 0) goto L_0x0143
            com.android.messaging.ui.conversation.SimIconView r12 = r11.f1767Bh
            com.android.messaging.util.C1486ya.m3854a((android.view.View) r12, (int) r4, (java.lang.Runnable) r8)
        L_0x0143:
            android.widget.ImageButton r12 = r11.f1768Ch
            com.android.messaging.util.C1486ya.m3854a((android.view.View) r12, (int) r5, (java.lang.Runnable) r8)
            android.widget.TextView r12 = r11.f1766Ah
            r12.setVisibility(r9)
            com.android.messaging.datamodel.a.f r12 = r11.f1774Ih
            com.android.messaging.datamodel.a.a r12 = r12.getData()
            com.android.messaging.datamodel.data.n r12 = (com.android.messaging.datamodel.data.C0931n) r12
            boolean r0 = com.android.messaging.util.C1464na.m3759Zj()
            if (r0 == 0) goto L_0x0163
            int r12 = r12.mo6457J(r3)
            if (r12 <= r3) goto L_0x0163
            r12 = r3
            goto L_0x0164
        L_0x0163:
            r12 = r4
        L_0x0164:
            if (r12 == 0) goto L_0x016d
            r12 = r7
            goto L_0x016e
        L_0x0168:
            com.android.messaging.ui.conversation.SimIconView r12 = r11.f1767Bh
            r12.mo6930f(r8)
        L_0x016d:
            r12 = r3
        L_0x016e:
            int r0 = r11.f1773Hh
            if (r0 != r12) goto L_0x0174
            if (r12 != r7) goto L_0x0179
        L_0x0174:
            r11.m2766kb(r12)
            r11.f1773Hh = r12
        L_0x0179:
            int r12 = r2.size()
            if (r12 != 0) goto L_0x01bf
            com.android.messaging.datamodel.a.f r12 = r11.f1774Ih
            com.android.messaging.datamodel.a.a r12 = r12.getData()
            com.android.messaging.datamodel.data.n r12 = (com.android.messaging.datamodel.data.C0931n) r12
            com.android.messaging.datamodel.a.c r0 = r11.mBinding
            com.android.messaging.datamodel.a.a r0 = r0.getData()
            com.android.messaging.datamodel.data.A r0 = (com.android.messaging.datamodel.data.C0889A) r0
            java.lang.String r0 = r0.mo6198kf()
            com.android.messaging.datamodel.data.ba r12 = r12.mo6466a((java.lang.String) r0, (boolean) r4)
            if (r12 != 0) goto L_0x01a3
            com.android.messaging.ui.PlainTextEditText r11 = r11.f1778xh
            r12 = 2131820679(0x7f110087, float:1.927408E38)
            r11.setHint(r12)
            goto L_0x025f
        L_0x01a3:
            com.android.messaging.ui.PlainTextEditText r0 = r11.f1778xh
            android.content.res.Resources r11 = r11.getResources()
            r1 = 2131820680(0x7f110088, float:1.9274082E38)
            java.lang.Object[] r2 = new java.lang.Object[r3]
            java.lang.String r12 = r12.displayName
            r2[r4] = r12
            java.lang.String r11 = r11.getString(r1, r2)
            android.text.Spanned r11 = android.text.Html.fromHtml(r11)
            r0.setHint(r11)
            goto L_0x025f
        L_0x01bf:
            java.util.Iterator r0 = r2.iterator()
            r1 = -1
            r2 = r1
        L_0x01c5:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x01fb
            java.lang.Object r5 = r0.next()
            com.android.messaging.datamodel.data.MessagePartData r5 = (com.android.messaging.datamodel.data.MessagePartData) r5
            boolean r8 = r5.mo6304fh()
            if (r8 == 0) goto L_0x01d9
            r5 = r4
            goto L_0x01f2
        L_0x01d9:
            boolean r8 = r5.mo6302eh()
            if (r8 == 0) goto L_0x01e1
            r5 = r7
            goto L_0x01f2
        L_0x01e1:
            boolean r8 = r5.mo6315ih()
            if (r8 == 0) goto L_0x01e9
            r5 = r3
            goto L_0x01f2
        L_0x01e9:
            boolean r5 = r5.mo6314hh()
            if (r5 == 0) goto L_0x01f1
            r5 = r6
            goto L_0x01f2
        L_0x01f1:
            r5 = r9
        L_0x01f2:
            if (r2 != r1) goto L_0x01f6
            r2 = r5
            goto L_0x01c5
        L_0x01f6:
            if (r2 != r5) goto L_0x01fa
            if (r2 != r9) goto L_0x01c5
        L_0x01fa:
            r2 = r9
        L_0x01fb:
            if (r2 == 0) goto L_0x024f
            if (r2 == r3) goto L_0x023e
            if (r2 == r7) goto L_0x022d
            if (r2 == r6) goto L_0x021c
            if (r2 == r9) goto L_0x020b
            java.lang.String r11 = "Unsupported attachment type!"
            com.android.messaging.util.C1424b.fail(r11)
            goto L_0x025f
        L_0x020b:
            com.android.messaging.ui.PlainTextEditText r0 = r11.f1778xh
            android.content.res.Resources r11 = r11.getResources()
            r1 = 2131689479(0x7f0f0007, float:1.9007975E38)
            java.lang.String r11 = r11.getQuantityString(r1, r12)
            r0.setHint(r11)
            goto L_0x025f
        L_0x021c:
            com.android.messaging.ui.PlainTextEditText r0 = r11.f1778xh
            android.content.res.Resources r11 = r11.getResources()
            r1 = 2131689482(0x7f0f000a, float:1.900798E38)
            java.lang.String r11 = r11.getQuantityString(r1, r12)
            r0.setHint(r11)
            goto L_0x025f
        L_0x022d:
            com.android.messaging.ui.PlainTextEditText r0 = r11.f1778xh
            android.content.res.Resources r11 = r11.getResources()
            r1 = 2131689480(0x7f0f0008, float:1.9007977E38)
            java.lang.String r11 = r11.getQuantityString(r1, r12)
            r0.setHint(r11)
            goto L_0x025f
        L_0x023e:
            com.android.messaging.ui.PlainTextEditText r0 = r11.f1778xh
            android.content.res.Resources r11 = r11.getResources()
            r1 = 2131689483(0x7f0f000b, float:1.9007983E38)
            java.lang.String r11 = r11.getQuantityString(r1, r12)
            r0.setHint(r11)
            goto L_0x025f
        L_0x024f:
            com.android.messaging.ui.PlainTextEditText r0 = r11.f1778xh
            android.content.res.Resources r11 = r11.getResources()
            r1 = 2131689481(0x7f0f0009, float:1.9007979E38)
            java.lang.String r11 = r11.getQuantityString(r1, r12)
            r0.setHint(r11)
        L_0x025f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.conversation.ComposeMessageView.m2773xa(boolean):void");
    }

    /* renamed from: J */
    public void mo7292J() {
        this.f1778xh.requestFocus();
        this.f1775Jh.mo7467i(true, true);
        if (C0107q.m134f(getContext())) {
            int size = ((C0889A) this.mBinding.getData()).mo6197jf().size() + ((C0889A) this.mBinding.getData()).mo6195if().size();
            C0107q.m128a((View) this, (AccessibilityManager) null, (CharSequence) getContext().getResources().getQuantityString(R.plurals.attachment_changed_accessibility_announcement, size, new Object[]{Integer.valueOf(size)}));
        }
    }

    /* renamed from: Ob */
    public void mo7293Ob() {
        ((C0889A) this.mBinding.getData()).mo6200ma(this.mHost.mo7425u());
        this.mHost.mo7428y();
    }

    /* renamed from: Pb */
    public String mo7294Pb() {
        return ((C0889A) this.mBinding.getData()).mo6198kf();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Qb */
    public C0786f mo7295Qb() {
        return C0784d.m1314f(this.mBinding);
    }

    /* renamed from: Rb */
    public void mo7296Rb() {
        this.mHost.mo7404i();
    }

    /* renamed from: Sb */
    public void mo7297Sb() {
        this.f1775Jh.mo7464Sb();
    }

    /* renamed from: Tb */
    public void mo7298Tb() {
        m2772wa(false);
    }

    /* renamed from: Ub */
    public void mo7299Ub() {
        ((C0889A) this.mBinding.getData()).mo6180aa(this.f1778xh.getText().toString());
        ((C0889A) this.mBinding.getData()).mo6171Z(this.f1779yh.getText().toString());
        ((C0889A) this.mBinding.getData()).mo6186e((C0784d) this.mBinding);
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.mHost.mo7396c() && this.f1775Jh.mo7473k(false, true)) {
            m2771va(false);
        }
    }

    public boolean onBackPressed() {
        return this.f1775Jh.onBackPressed();
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        m2772wa(true);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.f1778xh = (PlainTextEditText) findViewById(R.id.compose_message_text);
        this.f1778xh.setOnEditorActionListener(this);
        this.f1778xh.addTextChangedListener(this);
        this.f1778xh.setOnFocusChangeListener(new C1165e(this));
        this.f1778xh.setOnClickListener(new C1167f(this));
        this.f1778xh.setFilters(new InputFilter[]{new InputFilter.LengthFilter(C1024t.get(-1).mo6845qi())});
        if (C1451h.m3724Hd().getBoolean("pref_show_emoticons", C0967f.get().getApplicationContext().getResources().getBoolean(R.bool.show_emoticons_pref_default))) {
            PlainTextEditText plainTextEditText = this.f1778xh;
            plainTextEditText.setInputType(plainTextEditText.getInputType() | 64);
        } else {
            PlainTextEditText plainTextEditText2 = this.f1778xh;
            plainTextEditText2.setInputType(plainTextEditText2.getInputType() & -65);
        }
        this.f1767Bh = (SimIconView) findViewById(R.id.self_send_icon);
        this.f1767Bh.setOnClickListener(new C1169g(this));
        this.f1767Bh.setOnLongClickListener(new C1171h(this));
        this.f1779yh = (PlainTextEditText) findViewById(R.id.compose_subject_text);
        this.f1779yh.addTextChangedListener(this);
        this.f1779yh.setFilters(new InputFilter[]{new InputFilter.LengthFilter(C1024t.get(-1).mo6844pi())});
        this.f1769Dh = (ImageButton) findViewById(R.id.delete_subject_button);
        this.f1769Dh.setOnClickListener(new C1173i(this));
        this.f1777Pf = findViewById(R.id.subject_view);
        this.f1768Ch = (ImageButton) findViewById(R.id.send_message_button);
        this.f1768Ch.setOnClickListener(new C1175j(this));
        this.f1768Ch.setOnLongClickListener(new C1177k(this));
        this.f1768Ch.setAccessibilityDelegate(new C1179l(this));
        this.f1771Fh = (ImageButton) findViewById(R.id.attach_media_button);
        this.f1771Fh.setOnClickListener(new C1157a(this));
        this.f1770Eh = (AttachmentPreview) findViewById(R.id.attachment_draft_view);
        this.f1770Eh.mo6869a(this);
        this.f1780zh = (TextView) findViewById(R.id.message_body_size);
        this.f1766Ah = (TextView) findViewById(R.id.mms_indicator);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Context context = this.f1772Gh;
        BugleActionBarActivity bugleActionBarActivity = context instanceof BugleActionBarActivity ? (BugleActionBarActivity) context : null;
        if (bugleActionBarActivity == null || !bugleActionBarActivity.mo6912ab()) {
            this.mBinding.mo5935yf();
            m2773xa(false);
            return;
        }
        C1430e.m3628v("MessagingApp", "got onTextChanged after onDestroy");
    }

    /* renamed from: r */
    public void mo7318r(boolean z) {
        this.f1775Jh.mo7466ba(z);
    }

    /* renamed from: s */
    public void mo7319s(boolean z) {
        ((C0889A) this.mBinding.getData()).mo6179a(this.mBinding, (MessageData) null, z);
    }

    /* renamed from: sa */
    public boolean mo7320sa() {
        return this.f1775Jh.mo7476sa();
    }

    public void unbind() {
        this.mBinding.unbind();
        this.mHost = null;
        this.f1775Jh.onDetach();
    }

    public boolean updateActionBar(ActionBar actionBar) {
        C1158aa aaVar = this.f1775Jh;
        if (aaVar != null) {
            return aaVar.updateActionBar(actionBar);
        }
        return false;
    }

    /* renamed from: v */
    public void mo7323v(String str) {
        ((C0889A) this.mBinding.getData()).mo6182c(str, true);
    }

    /* renamed from: a */
    public void mo7302a(C0889A a, C1183n nVar) {
        this.mHost = nVar;
        this.mBinding.mo5930b(a);
        a.mo6176a((C0942y) this);
        a.mo6177a((C0943z) nVar);
        int d = this.mHost.mo7397d();
        if (d != -1) {
            this.f1780zh.setTextColor(d);
        }
    }

    /* renamed from: c */
    public void mo7311c(Bundle bundle) {
        this.f1775Jh.mo7472k(bundle);
    }

    /* renamed from: g */
    public void mo7312g(boolean z) {
        if (z) {
            this.f1771Fh.setImportantForAccessibility(1);
            this.f1778xh.setImportantForAccessibility(1);
            this.f1768Ch.setImportantForAccessibility(1);
            m2766kb(this.f1773Hh);
            return;
        }
        this.f1767Bh.setImportantForAccessibility(2);
        this.f1778xh.setImportantForAccessibility(2);
        this.f1768Ch.setImportantForAccessibility(2);
        this.f1771Fh.setImportantForAccessibility(2);
    }

    /* renamed from: o */
    public PlainTextEditText m2796o() {
        return this.f1778xh;
    }

    /* renamed from: r */
    public void mo6223r() {
        this.mHost.mo7429z();
    }

    /* renamed from: b */
    public void mo7308b(MessagePartData messagePartData) {
        ((C0889A) this.mBinding.getData()).mo6189f(messagePartData);
        m2770ua(false);
    }

    /* renamed from: b */
    public void mo7309b(PendingAttachmentData pendingAttachmentData) {
        ((C0889A) this.mBinding.getData()).mo6175a(pendingAttachmentData, (C0784d) this.mBinding);
        mo7292J();
    }

    /* renamed from: b */
    public void mo7307b(MessageData messageData) {
        ((C0889A) this.mBinding.getData()).mo6179a(this.mBinding, messageData, false);
    }

    /* renamed from: a */
    public void mo7304a(C1158aa aaVar) {
        this.f1775Jh = aaVar;
    }

    /* renamed from: a */
    public void mo7301a(C0786f fVar) {
        this.f1774Ih = fVar;
        ((C0931n) this.f1774Ih.getData()).mo6471a(this.f1776Kh);
    }

    /* renamed from: a */
    public void mo6222a(C0889A a, int i) {
        this.mBinding.mo5929a(a);
        String gf = a.mo6192gf();
        String hf = a.mo6194hf();
        if ((i & 4) == 4) {
            this.f1779yh.setText(gf);
            PlainTextEditText plainTextEditText = this.f1779yh;
            plainTextEditText.setSelection(plainTextEditText.getText().length());
        }
        if ((i & 2) == 2) {
            this.f1778xh.setText(hf);
            PlainTextEditText plainTextEditText2 = this.f1778xh;
            plainTextEditText2.setSelection(plainTextEditText2.getText().length());
        }
        boolean z = true;
        if ((i & 1) == 1) {
            this.mHost.mo7400f(this.f1770Eh.mo6871b(a));
        } else {
            z = false;
        }
        if ((i & 8) == 8) {
            m2756bn();
        }
        m2773xa(z);
    }

    /* renamed from: a */
    public void mo6221a(C0889A a) {
        this.mBinding.mo5929a(a);
        this.mHost.mo7394a(false, false);
    }

    /* renamed from: a */
    public void mo7305a(Collection collection) {
        ((C0889A) this.mBinding.getData()).mo6183c(collection);
        m2770ua(true);
    }

    /* renamed from: a */
    public void mo7300a(Uri uri, Rect rect) {
        this.mHost.mo7388a(uri, rect, true);
    }

    /* renamed from: a */
    public void mo7303a(C0917ba baVar) {
        String Pb = mo7294Pb();
        String str = baVar.f1214lC;
        C1424b.m3594t(str);
        if (Pb != null && !TextUtils.equals(Pb, str)) {
            ((C0889A) this.mBinding.getData()).mo6182c(str, true);
        }
    }
}
