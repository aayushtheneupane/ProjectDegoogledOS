package com.android.messaging.p041ui.conversationlist;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.p016v4.media.session.C0107q;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.text.BidiFormatter;
import androidx.core.text.TextDirectionHeuristicsCompat;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.annotation.VisibleForAnimation;
import com.android.messaging.datamodel.action.UpdateConversationArchiveStatusAction;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.p041ui.AsyncImageView;
import com.android.messaging.p041ui.AudioAttachmentView;
import com.android.messaging.p041ui.ContactIconView;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1481w;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.conversationlist.ConversationListItemView */
public class ConversationListItemView extends FrameLayout implements View.OnClickListener, View.OnLongClickListener, View.OnLayoutChangeListener {

    /* renamed from: Ag */
    private static String f1903Ag;

    /* renamed from: Bg */
    private static final int[][][] f1904Bg = {new int[][]{new int[]{R.string.one_on_one_incoming_failed_message_prefix, R.string.one_on_one_incoming_successful_message_prefix}, new int[]{R.string.one_on_one_outgoing_failed_message_prefix, R.string.one_on_one_outgoing_successful_message_prefix, R.string.one_on_one_outgoing_draft_message_prefix, R.string.one_on_one_outgoing_sending_message_prefix}}, new int[][]{new int[]{R.string.group_incoming_failed_message_prefix, R.string.group_incoming_successful_message_prefix}, new int[]{R.string.group_outgoing_failed_message_prefix, R.string.group_outgoing_successful_message_prefix, R.string.group_outgoing_draft_message_prefix, R.string.group_outgoing_sending_message_prefix}}};

    /* renamed from: zg */
    private static String f1905zg;

    /* renamed from: hg */
    private int f1906hg;

    /* renamed from: ig */
    private int f1907ig;

    /* renamed from: jg */
    private Typeface f1908jg;

    /* renamed from: kg */
    private Typeface f1909kg;

    /* renamed from: lg */
    private final View.OnClickListener f1910lg = new C1225n(this);
    private ImageView mContactCheckmarkView;
    private ContactIconView mContactIconView;
    /* access modifiers changed from: private */
    public final C0934q mData = new C0934q();
    /* access modifiers changed from: private */
    public C1227p mHostInterface;

    /* renamed from: mg */
    private int f1911mg;

    /* renamed from: ng */
    private ViewGroup f1912ng;

    /* renamed from: og */
    private ViewGroup f1913og;

    /* renamed from: pg */
    private TextView f1914pg;

    /* renamed from: qg */
    private ImageView f1915qg;

    /* renamed from: rg */
    private TextView f1916rg;

    /* renamed from: sg */
    private TextView f1917sg;

    /* renamed from: tg */
    private TextView f1918tg;

    /* renamed from: ug */
    private ImageView f1919ug;

    /* renamed from: vg */
    private ImageView f1920vg;

    /* renamed from: wg */
    private ImageView f1921wg;

    /* renamed from: xg */
    private AsyncImageView f1922xg;

    /* renamed from: yg */
    private AudioAttachmentView f1923yg;

    public ConversationListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        context.getResources();
    }

    /* renamed from: Qm */
    private void m3088Qm() {
        if (this.mData.mo6501Qf() || this.mData.mo6511Yf()) {
            this.f1914pg.setTextColor(this.f1906hg);
            this.f1914pg.setTypeface(this.f1908jg);
        } else {
            this.f1914pg.setTextColor(this.f1907ig);
            this.f1914pg.setTypeface(this.f1909kg);
        }
        String name = this.mData.getName();
        TextPaint paint = this.f1914pg.getPaint();
        int measuredWidth = this.f1914pg.getMeasuredWidth();
        if (f1905zg == null) {
            f1905zg = C0967f.get().getApplicationContext().getResources().getString(R.string.plus_one);
        }
        String str = f1905zg;
        if (f1903Ag == null) {
            f1903Ag = C0967f.get().getApplicationContext().getResources().getString(R.string.plus_n);
        }
        this.f1914pg.setText(BidiFormatter.getInstance().unicodeWrap(C1486ya.m3849a(name, paint, measuredWidth, str, f1903Ag).toString(), TextDirectionHeuristicsCompat.LTR));
    }

    /* renamed from: Rm */
    private void m3089Rm() {
        String str;
        if (this.mData.mo6511Yf()) {
            str = this.mData.mo6495Kf();
        } else {
            str = C1029y.m2438b(getContext().getResources(), this.mData.getSubject());
        }
        if (!TextUtils.isEmpty(str)) {
            String string = getResources().getString(R.string.subject_label);
            this.f1917sg.setText(TextUtils.concat(new CharSequence[]{string, str}));
            this.f1917sg.setVisibility(0);
            return;
        }
        this.f1917sg.setVisibility(8);
    }

    private String getSnippetText() {
        String Jf = this.mData.mo6511Yf() ? this.mData.mo6494Jf() : this.mData.getSnippetText();
        String Hf = this.mData.mo6511Yf() ? this.mData.mo6492Hf() : this.mData.mo6510Xf();
        if (!TextUtils.isEmpty(Jf)) {
            return Jf;
        }
        Resources resources = getResources();
        if (C1481w.m3831za(Hf)) {
            return resources.getString(R.string.conversation_list_snippet_audio_clip);
        }
        if (C1481w.isImageType(Hf)) {
            return resources.getString(R.string.conversation_list_snippet_picture);
        }
        if (C1481w.m3830Ea(Hf)) {
            return resources.getString(R.string.conversation_list_snippet_video);
        }
        return C1481w.m3829Da(Hf) ? resources.getString(R.string.conversation_list_snippet_vcard) : Jf;
    }

    /* renamed from: Y */
    public boolean mo7561Y() {
        return this.mHostInterface.mo7541Y();
    }

    @VisibleForAnimation
    public float getSwipeTranslationX() {
        return this.f1912ng.getTranslationX();
    }

    public boolean isAnimating() {
        return this.f1911mg > 0;
    }

    /* renamed from: n */
    public final void mo7565n(boolean z) {
        int i = this.f1911mg;
        if (z) {
            this.f1911mg = i + 1;
        } else {
            this.f1911mg = i - 1;
            if (this.f1911mg < 0) {
                this.f1911mg = 0;
            }
        }
        if (this.f1911mg == 0) {
            setClickable(true);
            setLongClickable(true);
        } else if (i == 0) {
            setClickable(false);
            setLongClickable(false);
        }
    }

    public void onClick(View view) {
        m3092a(view, false);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.f1912ng = (ViewGroup) findViewById(R.id.swipeableContainer);
        this.f1913og = (ViewGroup) findViewById(R.id.crossSwipeBackground);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.swipeableContent);
        this.f1914pg = (TextView) findViewById(R.id.conversation_name);
        this.f1916rg = (TextView) findViewById(R.id.conversation_snippet);
        this.f1917sg = (TextView) findViewById(R.id.conversation_subject);
        this.f1915qg = (ImageView) findViewById(R.id.work_profile_icon);
        this.f1918tg = (TextView) findViewById(R.id.conversation_timestamp);
        this.mContactIconView = (ContactIconView) findViewById(R.id.conversation_icon);
        this.mContactCheckmarkView = (ImageView) findViewById(R.id.conversation_checkmark);
        this.f1919ug = (ImageView) findViewById(R.id.conversation_failed_status_icon);
        this.f1920vg = (ImageView) findViewById(R.id.crossSwipeArchiveIconLeft);
        this.f1921wg = (ImageView) findViewById(R.id.crossSwipeArchiveIconRight);
        this.f1922xg = (AsyncImageView) findViewById(R.id.conversation_image_preview);
        this.f1923yg = (AudioAttachmentView) findViewById(R.id.audio_attachment_view);
        this.f1914pg.addOnLayoutChangeListener(this);
        this.f1916rg.addOnLayoutChangeListener(this);
        Resources resources = getContext().getResources();
        this.f1906hg = resources.getColor(R.color.conversation_list_item_read);
        this.f1907ig = resources.getColor(R.color.conversation_list_item_unread);
        this.f1908jg = C1430e.m3610Ij();
        this.f1909kg = C1430e.m3609Hj();
        if (C1464na.m3758Yj()) {
            setTransitionGroup(true);
        }
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (view == this.f1914pg) {
            m3088Qm();
            return;
        }
        TextView textView = this.f1916rg;
        if (view == textView) {
            textView.setText(getSnippetText());
        } else if (view == this.f1917sg) {
            m3089Rm();
        }
    }

    public boolean onLongClick(View view) {
        return m3092a(view, true);
    }

    @VisibleForAnimation
    public void setSwipeTranslationX(float f) {
        this.f1912ng.setTranslationX(f);
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        if (i == 0) {
            this.f1913og.setVisibility(8);
            this.f1920vg.setVisibility(8);
            this.f1921wg.setVisibility(8);
            this.f1912ng.setBackgroundColor(0);
            return;
        }
        this.f1913og.setVisibility(0);
        if (i > 0) {
            this.f1920vg.setVisibility(0);
            this.f1921wg.setVisibility(8);
        } else {
            this.f1920vg.setVisibility(8);
            this.f1921wg.setVisibility(0);
        }
        this.f1912ng.setBackgroundResource(R.drawable.swipe_shadow_drag_new);
    }

    /* renamed from: t */
    public void mo7571t(int i) {
        String Ue = this.mData.mo6505Ue();
        if (!C0107q.m121Bb() || i != 2) {
            UpdateConversationArchiveStatusAction.m1455T(Ue);
            C1226o oVar = new C1226o(this, Ue);
            C1486ya.m3853a(getContext(), getRootView(), getResources().getString(R.string.archived_toast_message, new Object[]{1}), (Runnable) oVar, 0, this.mHostInterface.mo7546b());
            return;
        }
        this.mData.mo6523pa();
        C1486ya.m3851a(getContext(), getRootView(), getResources().getString(R.string.conversation_deleted));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m3091a(android.content.res.Resources r10, com.android.messaging.datamodel.data.C0934q r11) {
        /*
            boolean r0 = r11.mo6500Pf()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0011
            boolean r0 = r11.mo6511Yf()
            if (r0 == 0) goto L_0x000f
            goto L_0x0011
        L_0x000f:
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r0 = r2
        L_0x0012:
            r3 = 3
            r4 = 2
            if (r0 == 0) goto L_0x002b
            boolean r5 = r11.mo6511Yf()
            if (r5 == 0) goto L_0x001e
            r5 = r4
            goto L_0x0030
        L_0x001e:
            boolean r5 = r11.mo6502Rf()
            if (r5 == 0) goto L_0x0026
            r5 = r3
            goto L_0x0030
        L_0x0026:
            boolean r5 = r11.mo6498Nf()
            goto L_0x002f
        L_0x002b:
            boolean r5 = r11.mo6498Nf()
        L_0x002f:
            r5 = r5 ^ r2
        L_0x0030:
            int[][][] r6 = f1904Bg
            boolean r7 = r11.mo6499Of()
            r6 = r6[r7]
            r6 = r6[r0]
            r5 = r6[r5]
            boolean r6 = r11.mo6511Yf()
            if (r6 == 0) goto L_0x0047
            java.lang.String r6 = r11.mo6494Jf()
            goto L_0x004b
        L_0x0047:
            java.lang.String r6 = r11.getSnippetText()
        L_0x004b:
            java.lang.String r7 = r11.getName()
            if (r0 == 0) goto L_0x0053
            r8 = r7
            goto L_0x0057
        L_0x0053:
            java.lang.String r8 = r11.mo6512Zf()
        L_0x0057:
            r9 = 4
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r9[r1] = r8
            if (r6 != 0) goto L_0x0060
            java.lang.String r6 = ""
        L_0x0060:
            r9[r2] = r6
            java.lang.String r1 = r11.mo6496Lf()
            r9[r4] = r1
            r9[r3] = r7
            java.lang.String r1 = r10.getString(r5, r9)
            if (r0 == 0) goto L_0x0093
            boolean r0 = r11.mo6511Yf()
            if (r0 == 0) goto L_0x0093
            boolean r11 = r11.mo6498Nf()
            if (r11 == 0) goto L_0x0093
            java.lang.StringBuilder r11 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r1)
            r0 = 2131820766(0x7f1100de, float:1.9274256E38)
            java.lang.String r10 = r10.getString(r0)
            java.lang.String r0 = " "
            r11.append(r0)
            r11.append(r10)
            java.lang.String r1 = r11.toString()
        L_0x0093:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.conversationlist.ConversationListItemView.m3091a(android.content.res.Resources, com.android.messaging.datamodel.data.q):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:78:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x02ae  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo7562a(android.database.Cursor r21, com.android.messaging.p041ui.conversationlist.C1227p r22) {
        /*
            r20 = this;
            r0 = r20
            r1 = r22
            r0.mHostInterface = r1
            com.android.messaging.datamodel.data.q r1 = r0.mData
            r2 = r21
            r1.mo6513c(r2)
            r1 = 0
            r0.f1911mg = r1
            r2 = 1
            r0.setClickable(r2)
            r0.setLongClickable(r2)
            r3 = 0
            r0.setSwipeTranslationX(r3)
            android.view.ViewGroup r3 = r0.f1912ng
            r3.setOnClickListener(r0)
            android.view.ViewGroup r3 = r0.f1912ng
            r3.setOnLongClickListener(r0)
            android.content.Context r3 = r20.getContext()
            android.content.res.Resources r3 = r3.getResources()
            com.android.messaging.datamodel.data.q r4 = r0.mData
            boolean r4 = r4.mo6511Yf()
            if (r4 == 0) goto L_0x0037
            r4 = 2
            goto L_0x0038
        L_0x0037:
            r4 = r1
        L_0x0038:
            java.lang.String r5 = r20.getSnippetText()
            com.android.messaging.datamodel.data.q r6 = r0.mData
            boolean r6 = r6.mo6501Qf()
            r7 = 3
            if (r6 != 0) goto L_0x005c
            com.android.messaging.datamodel.data.q r6 = r0.mData
            boolean r6 = r6.mo6511Yf()
            if (r6 == 0) goto L_0x004e
            goto L_0x005c
        L_0x004e:
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 == 0) goto L_0x0056
            r5 = r1
            goto L_0x0057
        L_0x0056:
            r5 = r7
        L_0x0057:
            int r6 = r0.f1907ig
            android.graphics.Typeface r8 = r0.f1909kg
            goto L_0x0065
        L_0x005c:
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            r5 = r5 ^ r2
            int r6 = r0.f1906hg
            android.graphics.Typeface r8 = r0.f1908jg
        L_0x0065:
            android.widget.TextView r9 = r0.f1916rg
            r9.setMaxLines(r5)
            android.widget.TextView r5 = r0.f1916rg
            r5.setTextColor(r6)
            android.widget.TextView r5 = r0.f1916rg
            r5.setTypeface(r8, r4)
            android.widget.TextView r5 = r0.f1917sg
            r5.setTextColor(r6)
            android.widget.TextView r5 = r0.f1917sg
            r5.setTypeface(r8, r4)
            android.widget.TextView r5 = r0.f1916rg
            java.lang.String r6 = r20.getSnippetText()
            r5.setText(r6)
            r20.m3088Qm()
            r20.m3089Rm()
            android.widget.ImageView r5 = r0.f1915qg
            com.android.messaging.datamodel.data.q r6 = r0.mData
            boolean r6 = r6.isEnterprise()
            r8 = 8
            if (r6 == 0) goto L_0x009b
            r6 = r1
            goto L_0x009c
        L_0x009b:
            r6 = r8
        L_0x009c:
            r5.setVisibility(r6)
            com.android.messaging.datamodel.data.q r5 = r0.mData
            android.widget.TextView r6 = r0.f1914pg
            r6.getPaint()
            java.lang.String r5 = m3091a((android.content.res.Resources) r3, (com.android.messaging.datamodel.data.C0934q) r5)
            r0.setContentDescription(r5)
            com.android.messaging.util.sa r5 = com.android.messaging.util.C1474sa.getDefault()
            boolean r5 = r5.mo8228kk()
            com.android.messaging.datamodel.data.q r6 = r0.mData
            boolean r6 = r6.mo6498Nf()
            if (r6 == 0) goto L_0x00f6
            if (r5 == 0) goto L_0x00f6
            android.widget.TextView r6 = r0.f1918tg
            r7 = 2131099744(0x7f060060, float:1.781185E38)
            int r7 = r3.getColor(r7)
            r6.setTextColor(r7)
            android.widget.TextView r6 = r0.f1918tg
            android.graphics.Typeface r7 = r0.f1908jg
            r6.setTypeface(r7, r4)
            r4 = 2131820833(0x7f110121, float:1.9274392E38)
            com.android.messaging.datamodel.data.q r6 = r0.mData
            boolean r6 = r6.mo6500Pf()
            if (r6 == 0) goto L_0x00ec
            com.android.messaging.datamodel.data.q r4 = r0.mData
            r4.getMessageStatus()
            com.android.messaging.datamodel.data.q r4 = r0.mData
            int r4 = r4.mo6503Sf()
            int r4 = com.android.messaging.sms.C1029y.m2406Ga(r4)
        L_0x00ec:
            android.widget.TextView r6 = r0.f1918tg
            java.lang.String r4 = r3.getString(r4)
            r6.setText(r4)
            goto L_0x0154
        L_0x00f6:
            com.android.messaging.datamodel.data.q r6 = r0.mData
            boolean r6 = r6.mo6511Yf()
            if (r6 != 0) goto L_0x013a
            com.android.messaging.datamodel.data.q r6 = r0.mData
            int r6 = r6.getMessageStatus()
            if (r6 == r7) goto L_0x013a
            com.android.messaging.datamodel.data.q r6 = r0.mData
            int r6 = r6.getMessageStatus()
            if (r6 != 0) goto L_0x010f
            goto L_0x013a
        L_0x010f:
            android.widget.TextView r6 = r0.f1918tg
            int r7 = r0.f1906hg
            r6.setTextColor(r7)
            android.widget.TextView r6 = r0.f1918tg
            android.graphics.Typeface r7 = r0.f1908jg
            r6.setTypeface(r7, r4)
            com.android.messaging.datamodel.data.q r4 = r0.mData
            java.lang.String r4 = r4.mo6496Lf()
            com.android.messaging.datamodel.data.q r6 = r0.mData
            boolean r6 = r6.mo6502Rf()
            if (r6 == 0) goto L_0x0134
            android.widget.TextView r4 = r0.f1918tg
            r6 = 2131820840(0x7f110128, float:1.9274406E38)
            r4.setText(r6)
            goto L_0x0154
        L_0x0134:
            android.widget.TextView r6 = r0.f1918tg
            r6.setText(r4)
            goto L_0x0154
        L_0x013a:
            android.widget.TextView r6 = r0.f1918tg
            int r7 = r0.f1906hg
            r6.setTextColor(r7)
            android.widget.TextView r6 = r0.f1918tg
            android.graphics.Typeface r7 = r0.f1908jg
            r6.setTypeface(r7, r4)
            android.widget.TextView r4 = r0.f1918tg
            r6 = 2131820702(0x7f11009e, float:1.9274126E38)
            java.lang.String r6 = r3.getString(r6)
            r4.setText(r6)
        L_0x0154:
            com.android.messaging.ui.conversationlist.p r4 = r0.mHostInterface
            com.android.messaging.datamodel.data.q r6 = r0.mData
            java.lang.String r6 = r6.mo6505Ue()
            boolean r4 = r4.mo7547d(r6)
            r0.setSelected(r4)
            if (r4 == 0) goto L_0x0169
            r5 = r1
            r4 = r8
            r6 = r4
            goto L_0x0182
        L_0x0169:
            com.android.messaging.datamodel.data.q r4 = r0.mData
            boolean r4 = r4.mo6498Nf()
            if (r4 == 0) goto L_0x017f
            com.android.messaging.datamodel.data.q r4 = r0.mData
            boolean r4 = r4.mo6499Of()
            if (r4 != 0) goto L_0x017f
            if (r5 == 0) goto L_0x017f
            r4 = r1
            r6 = r4
            r5 = r8
            goto L_0x0182
        L_0x017f:
            r4 = r1
            r5 = r8
            r6 = r5
        L_0x0182:
            com.android.messaging.datamodel.data.q r7 = r0.mData
            java.lang.String r7 = r7.getIcon()
            r9 = 0
            if (r7 == 0) goto L_0x0197
            com.android.messaging.datamodel.data.q r7 = r0.mData
            java.lang.String r7 = r7.getIcon()
            android.net.Uri r7 = android.net.Uri.parse(r7)
            r11 = r7
            goto L_0x0198
        L_0x0197:
            r11 = r9
        L_0x0198:
            com.android.messaging.ui.ContactIconView r10 = r0.mContactIconView
            com.android.messaging.datamodel.data.q r7 = r0.mData
            long r12 = r7.mo6506Uf()
            com.android.messaging.datamodel.data.q r7 = r0.mData
            java.lang.String r14 = r7.mo6509Wf()
            com.android.messaging.datamodel.data.q r7 = r0.mData
            java.lang.String r15 = r7.mo6504Tf()
            r10.mo6929a(r11, r12, r14, r15)
            com.android.messaging.ui.ContactIconView r7 = r0.mContactIconView
            r7.setVisibility(r4)
            com.android.messaging.ui.ContactIconView r4 = r0.mContactIconView
            r4.setOnLongClickListener(r0)
            com.android.messaging.ui.ContactIconView r4 = r0.mContactIconView
            com.android.messaging.ui.conversationlist.p r7 = r0.mHostInterface
            boolean r7 = r7.mo7540B()
            r7 = r7 ^ r2
            r4.setClickable(r7)
            com.android.messaging.ui.ContactIconView r4 = r0.mContactIconView
            com.android.messaging.ui.conversationlist.p r7 = r0.mHostInterface
            boolean r7 = r7.mo7540B()
            r7 = r7 ^ r2
            r4.setLongClickable(r7)
            android.widget.ImageView r4 = r0.mContactCheckmarkView
            r4.setVisibility(r5)
            android.widget.ImageView r4 = r0.f1919ug
            r4.setVisibility(r6)
            com.android.messaging.datamodel.data.q r4 = r0.mData
            boolean r4 = r4.mo6511Yf()
            if (r4 == 0) goto L_0x01ea
            com.android.messaging.datamodel.data.q r4 = r0.mData
            android.net.Uri r4 = r4.mo6493If()
            goto L_0x01f0
        L_0x01ea:
            com.android.messaging.datamodel.data.q r4 = r0.mData
            android.net.Uri r4 = r4.getPreviewUri()
        L_0x01f0:
            com.android.messaging.datamodel.data.q r5 = r0.mData
            boolean r5 = r5.mo6511Yf()
            if (r5 == 0) goto L_0x01ff
            com.android.messaging.datamodel.data.q r5 = r0.mData
            java.lang.String r5 = r5.mo6492Hf()
            goto L_0x0205
        L_0x01ff:
            com.android.messaging.datamodel.data.q r5 = r0.mData
            java.lang.String r5 = r5.mo6510Xf()
        L_0x0205:
            if (r4 == 0) goto L_0x0251
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L_0x0251
            boolean r6 = com.android.messaging.util.C1481w.m3831za(r5)
            if (r6 == 0) goto L_0x0231
            com.android.messaging.datamodel.data.q r5 = r0.mData
            boolean r5 = r5.mo6511Yf()
            if (r5 != 0) goto L_0x0224
            com.android.messaging.datamodel.data.q r5 = r0.mData
            boolean r5 = r5.mo6500Pf()
            if (r5 != 0) goto L_0x0224
            goto L_0x0225
        L_0x0224:
            r2 = r1
        L_0x0225:
            com.android.messaging.ui.AudioAttachmentView r5 = r0.f1923yg
            r5.mo6877a((android.net.Uri) r4, (boolean) r2, (boolean) r1)
            r11 = r9
            r19 = r8
            r8 = r1
            r1 = r19
            goto L_0x0253
        L_0x0231:
            boolean r2 = com.android.messaging.util.C1481w.m3830Ea(r5)
            if (r2 == 0) goto L_0x0247
            android.content.Context r2 = r20.getContext()
            r4 = 2131230937(0x7f0800d9, float:1.807794E38)
            android.net.Uri r9 = com.android.messaging.util.C1488za.m3873i(r2, r4)
            android.view.View$OnClickListener r2 = r0.f1910lg
            r11 = r9
            r9 = r2
            goto L_0x0253
        L_0x0247:
            boolean r2 = com.android.messaging.util.C1481w.isImageType(r5)
            if (r2 == 0) goto L_0x0251
            android.view.View$OnClickListener r9 = r0.f1910lg
            r11 = r4
            goto L_0x0253
        L_0x0251:
            r1 = r8
            r11 = r9
        L_0x0253:
            r2 = 2131165331(0x7f070093, float:1.7944876E38)
            int r13 = r3.getDimensionPixelSize(r2)
            com.android.messaging.ui.AsyncImageView r2 = r0.f1922xg
            com.android.messaging.datamodel.b.L r3 = new com.android.messaging.datamodel.b.L
            r14 = 1
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r10 = r3
            r12 = r13
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18)
            r2.mo6858a((com.android.messaging.datamodel.p038b.C0880t) r3)
            com.android.messaging.ui.AsyncImageView r2 = r0.f1922xg
            r2.setOnLongClickListener(r0)
            com.android.messaging.ui.AsyncImageView r2 = r0.f1922xg
            r2.setVisibility(r1)
            com.android.messaging.ui.AsyncImageView r1 = r0.f1922xg
            r1.setOnClickListener(r9)
            com.android.messaging.ui.AudioAttachmentView r1 = r0.f1923yg
            r1.setOnLongClickListener(r0)
            com.android.messaging.ui.AudioAttachmentView r1 = r0.f1923yg
            r1.setVisibility(r8)
            boolean r1 = android.support.p016v4.media.session.C0107q.m121Bb()
            r2 = 2131230858(0x7f08008a, float:1.807778E38)
            if (r1 == 0) goto L_0x02ae
            android.widget.ImageView r1 = r0.f1920vg
            android.content.res.Resources r3 = r20.getResources()
            r4 = 2131230891(0x7f0800ab, float:1.8077848E38)
            android.graphics.drawable.Drawable r3 = r3.getDrawable(r4)
            r1.setImageDrawable(r3)
            android.widget.ImageView r1 = r0.f1921wg
            android.content.res.Resources r0 = r20.getResources()
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r2)
            r1.setImageDrawable(r0)
            goto L_0x02c8
        L_0x02ae:
            android.widget.ImageView r1 = r0.f1920vg
            android.content.res.Resources r3 = r20.getResources()
            android.graphics.drawable.Drawable r3 = r3.getDrawable(r2)
            r1.setImageDrawable(r3)
            android.widget.ImageView r1 = r0.f1921wg
            android.content.res.Resources r0 = r20.getResources()
            android.graphics.drawable.Drawable r0 = r0.getDrawable(r2)
            r1.setImageDrawable(r0)
        L_0x02c8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.conversationlist.ConversationListItemView.mo7562a(android.database.Cursor, com.android.messaging.ui.conversationlist.p):void");
    }

    /* renamed from: a */
    private boolean m3092a(View view, boolean z) {
        C1424b.m3592ia(view == this.f1912ng || view == this.mContactIconView || view == this.f1922xg);
        C1424b.m3594t(this.mData.getName());
        C1227p pVar = this.mHostInterface;
        if (pVar == null) {
            return false;
        }
        pVar.mo7544a(this.mData, z, this);
        return true;
    }
}
