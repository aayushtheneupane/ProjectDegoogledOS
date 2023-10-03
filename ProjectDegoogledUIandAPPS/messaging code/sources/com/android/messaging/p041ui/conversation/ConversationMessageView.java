package com.android.messaging.p041ui.conversation;

import android.content.Context;
import android.graphics.Rect;
import android.support.p016v4.media.session.C0107q;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0936s;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.p041ui.AsyncImageView;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.p041ui.C1063Q;
import com.android.messaging.p041ui.C1114b;
import com.android.messaging.p041ui.ContactIconView;
import com.android.messaging.p041ui.MultiAttachmentLayout;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1486ya;
import com.google.common.base.C1509F;
import java.util.Comparator;

/* renamed from: com.android.messaging.ui.conversation.ConversationMessageView */
public class ConversationMessageView extends FrameLayout implements View.OnClickListener, View.OnLongClickListener, C1063Q {

    /* renamed from: _f */
    static final Comparator f1792_f = new C1168fa();

    /* renamed from: ag */
    static final C1509F f1793ag = new C1170ga();

    /* renamed from: bg */
    static final C1509F f1794bg = new C1172ha();

    /* renamed from: cg */
    static final C1509F f1795cg = new C1174ia();

    /* renamed from: dg */
    static final C1509F f1796dg = new C1176ja();

    /* renamed from: Ef */
    private LinearLayout f1797Ef;

    /* renamed from: Ff */
    private MultiAttachmentLayout f1798Ff;

    /* renamed from: Gf */
    private AsyncImageView f1799Gf;

    /* renamed from: Hf */
    private TextView f1800Hf;

    /* renamed from: If */
    private boolean f1801If;

    /* renamed from: Jf */
    private boolean f1802Jf;

    /* renamed from: Kf */
    private TextView f1803Kf;

    /* renamed from: Lf */
    private TextView f1804Lf;

    /* renamed from: Mf */
    private LinearLayout f1805Mf;

    /* renamed from: Nf */
    private TextView f1806Nf;

    /* renamed from: Of */
    private ConversationMessageBubbleView f1807Of;

    /* renamed from: Pf */
    private View f1808Pf;

    /* renamed from: Qf */
    private TextView f1809Qf;

    /* renamed from: Rf */
    private TextView f1810Rf;

    /* renamed from: Sf */
    private View f1811Sf;

    /* renamed from: Tf */
    private ViewGroup f1812Tf;

    /* renamed from: Uf */
    private ViewGroup f1813Uf;

    /* renamed from: Vf */
    private TextView f1814Vf;

    /* renamed from: Wf */
    private boolean f1815Wf;

    /* renamed from: Xf */
    final C1184na f1816Xf = new C1178ka(this);

    /* renamed from: Yf */
    final C1184na f1817Yf = new C1180la(this);

    /* renamed from: Zf */
    final C1184na f1818Zf = new C1182ma(this);
    private ContactIconView mContactIconView;
    /* access modifiers changed from: private */
    public final C0936s mData = new C0936s();
    private C1186oa mHost;
    private TextView mTitleTextView;

    public ConversationMessageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: Nm */
    private boolean m2831Nm() {
        return !m2833Pm() && !this.mData.mo6553mf() && !this.f1802Jf;
    }

    /* renamed from: Om */
    private boolean m2832Om() {
        if (!this.mData.hasText() && TextUtils.isEmpty(C1029y.m2438b(getResources(), this.mData.mo6554mg()))) {
            return false;
        }
        return true;
    }

    /* renamed from: Pm */
    private boolean m2833Pm() {
        return this.mData.mo6537bg();
    }

    public C0936s getData() {
        return this.mData;
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof MessagePartData) {
            mo6870a((MessagePartData) tag, C1486ya.m3858h(view), false);
        } else if (tag instanceof String) {
            C1040Ea.get().mo6978i(getContext(), (String) tag);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        this.mContactIconView = (ContactIconView) findViewById(R.id.conversation_icon);
        this.mContactIconView.setOnLongClickListener(new C1166ea(this));
        this.f1797Ef = (LinearLayout) findViewById(R.id.message_attachments);
        this.f1798Ff = (MultiAttachmentLayout) findViewById(R.id.multiple_attachments);
        this.f1798Ff.mo7040a((C1063Q) this);
        this.f1799Gf = (AsyncImageView) findViewById(R.id.message_image);
        this.f1799Gf.setOnClickListener(this);
        this.f1799Gf.setOnLongClickListener(this);
        this.f1800Hf = (TextView) findViewById(R.id.message_text);
        this.f1800Hf.setOnClickListener(this);
        C1188pa.m3025a(this.f1800Hf, this);
        this.f1803Kf = (TextView) findViewById(R.id.message_status);
        this.mTitleTextView = (TextView) findViewById(R.id.message_title);
        this.f1804Lf = (TextView) findViewById(R.id.mms_info);
        this.f1805Mf = (LinearLayout) findViewById(R.id.message_title_layout);
        this.f1806Nf = (TextView) findViewById(R.id.message_sender_name);
        this.f1807Of = (ConversationMessageBubbleView) findViewById(R.id.message_content);
        this.f1808Pf = findViewById(R.id.subject_container);
        this.f1809Qf = (TextView) this.f1808Pf.findViewById(R.id.subject_label);
        this.f1810Rf = (TextView) this.f1808Pf.findViewById(R.id.subject_text);
        this.f1811Sf = findViewById(R.id.smsDeliveredBadge);
        this.f1812Tf = (ViewGroup) findViewById(R.id.message_metadata);
        this.f1813Uf = (ViewGroup) findViewById(R.id.message_text_and_info);
        this.f1814Vf = (TextView) findViewById(R.id.sim_name);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        boolean isLayoutRtl = C0107q.isLayoutRtl(this);
        int measuredWidth = this.mContactIconView.getMeasuredWidth();
        int measuredHeight = this.mContactIconView.getMeasuredHeight();
        int paddingTop = getPaddingTop();
        int i8 = i3 - i;
        int paddingLeft = ((i8 - measuredWidth) - getPaddingLeft()) - getPaddingRight();
        int measuredHeight2 = this.f1807Of.getMeasuredHeight();
        if (this.mData.mo6546gg()) {
            if (isLayoutRtl) {
                i7 = getPaddingRight();
                i5 = (i8 - i7) - measuredWidth;
                i6 = i5 - paddingLeft;
                this.mContactIconView.layout(i5, paddingTop, measuredWidth + i5, measuredHeight + paddingTop);
                this.f1807Of.layout(i6, paddingTop, paddingLeft + i6, measuredHeight2 + paddingTop);
            }
            i5 = getPaddingLeft();
        } else if (isLayoutRtl) {
            i5 = getPaddingLeft();
        } else {
            i7 = getPaddingRight();
            i5 = (i8 - i7) - measuredWidth;
            i6 = i5 - paddingLeft;
            this.mContactIconView.layout(i5, paddingTop, measuredWidth + i5, measuredHeight + paddingTop);
            this.f1807Of.layout(i6, paddingTop, paddingLeft + i6, measuredHeight2 + paddingTop);
        }
        i6 = i5 + measuredWidth;
        this.mContactIconView.layout(i5, paddingTop, measuredWidth + i5, measuredHeight + paddingTop);
        this.f1807Of.layout(i6, paddingTop, paddingLeft + i6, measuredHeight2 + paddingTop);
    }

    public boolean onLongClick(View view) {
        if (view == this.f1800Hf) {
            return performLongClick();
        }
        Object tag = view.getTag();
        if (!(tag instanceof MessagePartData)) {
            return false;
        }
        return mo6870a((MessagePartData) tag, C1486ya.m3858h(view), true);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.conversation_message_contact_icon_size);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(dimensionPixelSize, 1073741824);
        this.mContactIconView.measure(makeMeasureSpec2, makeMeasureSpec2);
        this.f1807Of.measure(View.MeasureSpec.makeMeasureSpec((((size - (this.mContactIconView.getMeasuredWidth() * 2)) - getResources().getDimensionPixelSize(R.dimen.message_bubble_arrow_width)) - getPaddingLeft()) - getPaddingRight(), RtlSpacingHelper.UNDEFINED), makeMeasureSpec);
        setMeasuredDimension(size, getPaddingTop() + getPaddingBottom() + Math.max(this.mContactIconView.getMeasuredHeight(), this.f1807Of.getMeasuredHeight()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02fd, code lost:
        r1 = null;
        r2 = r8;
        r8 = com.android.messaging.R.string.message_title_download_failed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x031a, code lost:
        r2 = r1;
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0323, code lost:
        if (r0.mData.mo6536ag() != false) goto L_0x032c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0325, code lost:
        r1 = r0.mData.mo6541fg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x032c, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x032d, code lost:
        r2 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x032e, code lost:
        if (r8 < 0) goto L_0x0332;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0330, code lost:
        r7 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0332, code lost:
        r7 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0334, code lost:
        if (r7 == false) goto L_0x037f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0336, code lost:
        r0.mTitleTextView.setText(getResources().getString(r8));
        r0.f1804Lf.setText(getResources().getString(com.android.messaging.R.string.mms_info, new java.lang.Object[]{android.text.format.Formatter.formatFileSize(getContext(), (long) r0.mData.mo6530Fg()), android.text.format.DateUtils.formatDateTime(getContext(), r0.mData.mo6552lg(), 131097)}));
        r0.f1805Mf.setVisibility(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x037f, code lost:
        r0.f1805Mf.setVisibility(8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0384, code lost:
        r8 = !android.text.TextUtils.isEmpty(com.android.messaging.sms.C1029y.m2438b(getResources(), r0.mData.mo6554mg()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0399, code lost:
        if (r0.f1815Wf != false) goto L_0x03ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x03a1, code lost:
        if (r0.mData.mo6536ag() != false) goto L_0x03ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x03a9, code lost:
        if (r0.mData.mo6546gg() == false) goto L_0x03ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x03ab, code lost:
        r10 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x03ad, code lost:
        r10 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x03ae, code lost:
        if (r10 == false) goto L_0x03c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x03b0, code lost:
        r0.f1806Nf.setText(r0.mData.mo6565xg());
        r0.f1806Nf.setVisibility(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x03c1, code lost:
        r0.f1806Nf.setVisibility(8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x03c6, code lost:
        if (r2 < 0) goto L_0x03d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x03c8, code lost:
        r1 = getResources().getString(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x03d0, code lost:
        r0.f1803Kf.setText(r1);
        r1 = !android.text.TextUtils.isEmpty(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x03da, code lost:
        if (r1 == false) goto L_0x03e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x03dc, code lost:
        r0.f1803Kf.setVisibility(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x03e2, code lost:
        r0.f1803Kf.setVisibility(8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x03ed, code lost:
        if (r0.mData.getStatus() != 2) goto L_0x03f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x03ef, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x03f1, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x03f2, code lost:
        r9 = r0.f1811Sf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x03f4, code lost:
        if (r2 == false) goto L_0x03f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x03f6, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x03f7, code lost:
        r9.setVisibility(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0400, code lost:
        if (r0.mData.mo6546gg() == false) goto L_0x0412;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0408, code lost:
        if (r0.mData.mo6553mf() == false) goto L_0x0410;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x040e, code lost:
        if (m2832Om() == false) goto L_0x0412;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0410, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0412, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0413, code lost:
        r9 = r0.mHost.mo7386a(r0.mData.mo6559sg(), true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x041f, code lost:
        if (r9 == null) goto L_0x0433;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0427, code lost:
        if (android.text.TextUtils.isEmpty(r9.displayName) != false) goto L_0x0433;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x042f, code lost:
        if (r0.mData.mo6536ag() != false) goto L_0x0433;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0431, code lost:
        r11 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0433, code lost:
        r11 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0437, code lost:
        if (r11 == false) goto L_0x0472;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x043f, code lost:
        if (r0.mData.mo6546gg() == false) goto L_0x0453;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0441, code lost:
        r6 = getResources().getString(com.android.messaging.R.string.incoming_sim_name_text, new java.lang.Object[]{r9.displayName});
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0453, code lost:
        r6 = r9.displayName;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0455, code lost:
        r0.f1814Vf.setText(r6);
        r6 = r0.f1814Vf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x045c, code lost:
        if (r3 == false) goto L_0x0467;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x045e, code lost:
        r3 = getResources().getColor(com.android.messaging.R.color.timestamp_text_incoming);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0467, code lost:
        r3 = r9.f1217oC;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0469, code lost:
        r6.setTextColor(r3);
        r0.f1814Vf.setVisibility(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0472, code lost:
        r0.f1814Vf.setText((java.lang.CharSequence) null);
        r0.f1814Vf.setVisibility(8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x047e, code lost:
        if (r10 != false) goto L_0x0489;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0480, code lost:
        if (r1 != false) goto L_0x0489;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0482, code lost:
        if (r2 != false) goto L_0x0489;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x0484, code lost:
        if (r11 == false) goto L_0x0487;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0487, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x0489, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x048a, code lost:
        r2 = r0.f1812Tf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x048c, code lost:
        if (r1 == false) goto L_0x0490;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x048e, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0490, code lost:
        r3 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0492, code lost:
        r2.setVisibility(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0495, code lost:
        if (r7 != false) goto L_0x04a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x0497, code lost:
        if (r8 != false) goto L_0x04a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x049f, code lost:
        if (r0.mData.hasText() != false) goto L_0x04a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x04a1, code lost:
        if (r1 == false) goto L_0x04a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x04a4, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x04a6, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x04a7, code lost:
        r2 = r0.f1813Uf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x04a9, code lost:
        if (r1 == false) goto L_0x04ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x04ab, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x04ad, code lost:
        r1 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x04af, code lost:
        r2.setVisibility(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x04b6, code lost:
        if (m2833Pm() == false) goto L_0x04c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x04b8, code lost:
        r0.mContactIconView.setVisibility(8);
        r0.mContactIconView.mo6930f((android.net.Uri) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x04c5, code lost:
        r0.mContactIconView.setVisibility(0);
        r0.mContactIconView.mo6929a(com.android.messaging.util.C1426c.m3598a(r0.mData.mo6526Bg(), (java.lang.CharSequence) r0.mData.mo6567zg(), r0.mData.mo6525Ag(), r0.mData.mo6562ug()), r0.mData.mo6560tg(), r0.mData.mo6562ug(), r0.mData.mo6525Ag());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x04fd, code lost:
        r1 = getResources();
        r6 = com.android.messaging.p041ui.C1037D.get();
        r2 = r0.mData.mo6546gg();
        r3 = !r2;
        r12 = m2831Nm();
        r14 = r1.getDimensionPixelSize(com.android.messaging.R.dimen.message_padding_same_author);
        r15 = r1.getDimensionPixelSize(com.android.messaging.R.dimen.message_padding_default);
        r16 = r1.getDimensionPixelOffset(com.android.messaging.R.dimen.message_bubble_arrow_width);
        r17 = r1.getDimensionPixelSize(com.android.messaging.R.dimen.conversation_message_contact_icon_size);
        r18 = r1.getDimensionPixelOffset(com.android.messaging.R.dimen.message_text_left_right_padding);
        r19 = r1.getDimensionPixelOffset(com.android.messaging.R.dimen.message_text_top_padding);
        r20 = r1.getDimensionPixelOffset(com.android.messaging.R.dimen.message_text_bottom_padding);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0548, code lost:
        if (r0.mData.mo6553mf() == false) goto L_0x0596;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x054e, code lost:
        if (m2832Om() == false) goto L_0x0580;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x0550, code lost:
        if (r2 == false) goto L_0x0555;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0552, code lost:
        r5 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x0555, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x0556, code lost:
        if (r3 == false) goto L_0x0559;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0559, code lost:
        r16 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x055b, code lost:
        r3 = r6.mo6944a(isSelected(), r2, false, r0.mData.mo6532Ig(), r0.mData.mo6562ug());
        r11 = r5;
        r6 = r14;
        r12 = r16;
        r5 = r17;
        r7 = r18;
        r9 = r7;
        r8 = r19;
        r10 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x0580, code lost:
        if (r2 == false) goto L_0x0585;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0582, code lost:
        r6 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0585, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0586, code lost:
        if (r3 == false) goto L_0x0589;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x0589, code lost:
        r16 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x058b, code lost:
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r3 = null;
        r11 = r6;
        r12 = r16;
        r5 = 0;
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0596, code lost:
        if (r12 != false) goto L_0x059d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0598, code lost:
        if (r2 == false) goto L_0x059d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x059a, code lost:
        r5 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x059d, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x059e, code lost:
        if (r12 != false) goto L_0x05a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x05a0, code lost:
        if (r3 == false) goto L_0x05a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x05a2, code lost:
        r21 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x05a5, code lost:
        r21 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x05a7, code lost:
        r6 = r6.mo6944a(isSelected(), r2, m2831Nm(), r0.mData.mo6532Ig(), r0.mData.mo6562ug());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x05c0, code lost:
        if (r12 == false) goto L_0x05c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x05c2, code lost:
        if (r2 == false) goto L_0x05c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x05c4, code lost:
        r7 = r18 + r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x05c7, code lost:
        r7 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x05c9, code lost:
        if (r12 == false) goto L_0x05cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x05cb, code lost:
        if (r3 == false) goto L_0x05cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x05cd, code lost:
        r18 = r18 + r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x05cf, code lost:
        r11 = r5;
        r3 = r6;
        r5 = r17;
        r9 = r18;
        r8 = r19;
        r10 = r20;
        r12 = r21;
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x05dc, code lost:
        if (r2 == false) goto L_0x05e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x05de, code lost:
        r2 = 8388627;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x05e2, code lost:
        r2 = 8388629;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x05e9, code lost:
        if (m2833Pm() == false) goto L_0x05ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x05ec, code lost:
        r14 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x05ed, code lost:
        r1 = r1.getDimensionPixelOffset(com.android.messaging.R.dimen.message_metadata_top_padding);
        com.android.messaging.util.C1416U.m3566a((android.view.View) r0.f1813Uf, r3);
        r0.f1813Uf.setMinimumHeight(r5);
        ((android.widget.LinearLayout.LayoutParams) r0.f1813Uf.getLayoutParams()).topMargin = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x060c, code lost:
        if (com.android.messaging.util.C1486ya.m3860pk() == false) goto L_0x0619;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x060e, code lost:
        r0.f1813Uf.setPadding(r9, r8, r7, r10);
        r0.f1807Of.setPadding(r12, 0, r11, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x0619, code lost:
        r0.f1813Uf.setPadding(r7, r8, r9, r10);
        r0.f1807Of.setPadding(r11, 0, r12, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0623, code lost:
        setPadding(getPaddingLeft(), r14, getPaddingRight(), 0);
        r0.f1807Of.setGravity(r2);
        r0.f1797Ef.setGravity(r2);
        r2 = getResources().getColor(com.android.messaging.R.color.message_image_selected_tint);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0649, code lost:
        if (r0.f1799Gf.getVisibility() != 0) goto L_0x065c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x064f, code lost:
        if (isSelected() == false) goto L_0x0657;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x0651, code lost:
        r0.f1799Gf.setColorFilter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x0657, code lost:
        r0.f1799Gf.clearColorFilter();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x0662, code lost:
        if (r0.f1798Ff.getVisibility() != 0) goto L_0x0675;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:260:0x0668, code lost:
        if (isSelected() == false) goto L_0x0670;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:261:0x066a, code lost:
        r0.f1798Ff.setColorFilter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:262:0x0670, code lost:
        r0.f1798Ff.clearColorFilter();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0675, code lost:
        r3 = r0.f1797Ef.getChildCount();
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:0x067c, code lost:
        if (r5 >= r3) goto L_0x06a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x067e, code lost:
        r6 = r0.f1797Ef.getChildAt(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x0686, code lost:
        if ((r6 instanceof com.android.messaging.p041ui.VideoThumbnailView) == false) goto L_0x069d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x068c, code lost:
        if (r6.getVisibility() != 0) goto L_0x069d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x068e, code lost:
        r6 = (com.android.messaging.p041ui.VideoThumbnailView) r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:270:0x0694, code lost:
        if (isSelected() == false) goto L_0x069a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:271:0x0696, code lost:
        r6.setColorFilter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:272:0x069a, code lost:
        r6.clearColorFilter();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x069d, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:274:0x06a0, code lost:
        r2 = getResources().getDimensionPixelSize(com.android.messaging.R.dimen.message_padding_same_author);
        r3 = r0.f1797Ef.getChildCount();
        r5 = 0;
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:275:0x06b0, code lost:
        if (r5 >= r3) goto L_0x06cf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:276:0x06b2, code lost:
        r7 = r0.f1797Ef.getChildAt(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:277:0x06bc, code lost:
        if (r7.getVisibility() != 0) goto L_0x06cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x06be, code lost:
        if (r6 == false) goto L_0x06c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:279:0x06c0, code lost:
        r6 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:280:0x06c2, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:281:0x06c3, code lost:
        ((android.widget.LinearLayout.LayoutParams) r7.getLayoutParams()).topMargin = r6;
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:0x06cc, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x06cf, code lost:
        r0.f1812Tf.setPadding(0, r1, 0, 0);
        r1 = isSelected();
        r3 = com.android.messaging.R.color.message_action_timestamp_text;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x06e4, code lost:
        if (r1 == false) goto L_0x06fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x06f0, code lost:
        if (m2832Om() == false) goto L_0x06f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x06f3, code lost:
        r3 = com.android.messaging.R.color.timestamp_text_outgoing;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x06f4, code lost:
        r7 = r3;
        r3 = com.android.messaging.R.color.message_action_status_text;
        r1 = com.android.messaging.R.color.message_text_color_incoming;
        r5 = com.android.messaging.R.color.message_action_info_text;
        r2 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x0700, code lost:
        if (r0.mData.mo6546gg() == false) goto L_0x0704;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x0702, code lost:
        r1 = com.android.messaging.R.color.message_text_color_incoming;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x0704, code lost:
        r1 = com.android.messaging.R.color.message_text_color_outgoing;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x0707, code lost:
        r3 = r0.mData.getStatus();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:294:0x070e, code lost:
        if (r3 == 1) goto L_0x0738;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:296:0x0711, code lost:
        if (r3 == 2) goto L_0x0738;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x0713, code lost:
        switch(r3) {
            case 4: goto L_0x0738;
            case 5: goto L_0x0738;
            case 6: goto L_0x0738;
            case 7: goto L_0x0738;
            case 8: goto L_0x0724;
            case 9: goto L_0x0724;
            default: goto L_0x0716;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:298:0x0716, code lost:
        switch(r3) {
            case 101: goto L_0x0736;
            case 102: goto L_0x0736;
            case 103: goto L_0x0736;
            case 104: goto L_0x0736;
            case 105: goto L_0x0736;
            case 106: goto L_0x0729;
            case 107: goto L_0x0729;
            default: goto L_0x0719;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x0719, code lost:
        r7 = com.android.messaging.R.color.timestamp_text_incoming;
        r2 = com.android.messaging.R.color.timestamp_text_incoming;
        r5 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x0724, code lost:
        r2 = com.android.messaging.R.color.message_failed_timestamp_text;
        r3 = com.android.messaging.R.color.timestamp_text_outgoing;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x0729, code lost:
        r3 = com.android.messaging.R.color.message_download_failed_status_text;
        r5 = com.android.messaging.R.color.message_info_text_incoming_download_failed;
        r7 = com.android.messaging.R.color.message_text_color_incoming_download_failed;
        r2 = com.android.messaging.R.color.message_download_failed_timestamp_text;
        r1 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:302:0x0736, code lost:
        r2 = com.android.messaging.R.color.message_text_color_incoming;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x0738, code lost:
        r2 = com.android.messaging.R.color.timestamp_text_outgoing;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x0739, code lost:
        r3 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:305:0x073a, code lost:
        r5 = com.android.messaging.R.color.timestamp_text_incoming;
        r7 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x073e, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x073f, code lost:
        r1 = getResources().getColor(r1);
        r0.f1800Hf.setTextColor(r1);
        r0.f1800Hf.setLinkTextColor(r1);
        r0.f1810Rf.setTextColor(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:308:0x0756, code lost:
        if (r3 < 0) goto L_0x0765;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x0758, code lost:
        r0.mTitleTextView.setTextColor(getResources().getColor(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x0765, code lost:
        if (r5 < 0) goto L_0x0774;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x0767, code lost:
        r0.f1804Lf.setTextColor(getResources().getColor(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x0777, code lost:
        if (r2 != com.android.messaging.R.color.timestamp_text_incoming) goto L_0x0788;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x077f, code lost:
        if (r0.mData.mo6553mf() == false) goto L_0x0788;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:317:0x0785, code lost:
        if (m2832Om() != false) goto L_0x0788;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:318:0x0787, code lost:
        r2 = com.android.messaging.R.color.timestamp_text_outgoing;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:319:0x0788, code lost:
        r0.f1803Kf.setTextColor(getResources().getColor(r2));
        r0.f1809Qf.setTextColor(getResources().getColor(r7));
        r0.f1806Nf.setTextColor(getResources().getColor(r2));
        requestLayout();
        r1 = new java.lang.StringBuilder();
        r2 = getResources();
        r3 = r2.getString(com.android.messaging.R.string.enumeration_comma);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x07cc, code lost:
        if (android.text.TextUtils.isEmpty(r0.mData.getText()) != false) goto L_0x07d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x07d0, code lost:
        if (r0.f1801If != false) goto L_0x07d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:323:0x07d2, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x07d4, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x07db, code lost:
        if (r0.mData.mo6546gg() == false) goto L_0x07f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x07dd, code lost:
        if (r5 == false) goto L_0x07e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x07df, code lost:
        r5 = com.android.messaging.R.string.incoming_text_sender_content_description;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:0x07e3, code lost:
        r5 = com.android.messaging.R.string.incoming_sender_content_description;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:330:0x07e6, code lost:
        r1.append(r2.getString(r5, new java.lang.Object[]{r0.mData.mo6565xg()}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:331:0x07f9, code lost:
        if (r5 == false) goto L_0x07ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x07fb, code lost:
        r4 = com.android.messaging.R.string.outgoing_text_sender_content_description;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x07ff, code lost:
        r4 = com.android.messaging.R.string.outgoing_sender_content_description;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:334:0x0802, code lost:
        r1.append(r2.getString(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x080f, code lost:
        if (r0.f1808Pf.getVisibility() != 0) goto L_0x081d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:337:0x0811, code lost:
        r1.append(r3);
        r1.append(r0.f1810Rf.getText());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:339:0x0823, code lost:
        if (r0.f1800Hf.getVisibility() != 0) goto L_0x0842;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x0827, code lost:
        if (r0.f1801If == false) goto L_0x0830;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x0829, code lost:
        r0.f1800Hf.setImportantForAccessibility(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x0830, code lost:
        r0.f1800Hf.setImportantForAccessibility(2);
        r1.append(r3);
        r1.append(r0.f1800Hf.getText());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x0848, code lost:
        if (r0.f1805Mf.getVisibility() != 0) goto L_0x0862;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x084a, code lost:
        r1.append(r3);
        r1.append(r0.mTitleTextView.getText());
        r1.append(r3);
        r1.append(r0.f1804Lf.getText());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x0868, code lost:
        if (r0.f1803Kf.getVisibility() != 0) goto L_0x0876;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x086a, code lost:
        r1.append(r3);
        r1.append(r0.f1803Kf.getText());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:351:0x087c, code lost:
        if (r0.f1814Vf.getVisibility() != 0) goto L_0x088a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:0x087e, code lost:
        r1.append(r3);
        r1.append(r0.f1814Vf.getText());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x0890, code lost:
        if (r0.f1811Sf.getVisibility() != 0) goto L_0x089f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x0892, code lost:
        r1.append(r3);
        r1.append(r2.getString(com.android.messaging.R.string.delivered_status_content_description));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x089f, code lost:
        setContentDescription(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x08a2, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x0172 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:362:0x0264 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0253  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0269  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x026b  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x02ad  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x02b2  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x02df  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo7353a(android.database.Cursor r23, boolean r24, java.lang.String r25) {
        /*
            r22 = this;
            r0 = r22
            r1 = r24
            r0.f1815Wf = r1
            com.android.messaging.datamodel.data.s r1 = r0.mData
            r2 = r23
            r1.mo6538c(r2)
            com.android.messaging.datamodel.data.s r1 = r0.mData
            java.lang.String r1 = r1.getMessageId()
            r2 = r25
            boolean r1 = android.text.TextUtils.equals(r1, r2)
            r0.setSelected(r1)
            com.android.messaging.datamodel.data.s r1 = r0.mData
            java.lang.String r1 = r1.getText()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            r3 = 8
            r4 = 0
            if (r2 != 0) goto L_0x0040
            android.widget.TextView r2 = r0.f1800Hf
            r2.setText(r1)
            android.widget.TextView r1 = r0.f1800Hf
            r2 = 15
            boolean r1 = android.text.util.Linkify.addLinks(r1, r2)
            r0.f1801If = r1
            android.widget.TextView r1 = r0.f1800Hf
            r1.setVisibility(r4)
            goto L_0x0047
        L_0x0040:
            android.widget.TextView r1 = r0.f1800Hf
            r1.setVisibility(r3)
            r0.f1801If = r4
        L_0x0047:
            com.google.common.base.F r1 = f1793ag
            com.android.messaging.ui.conversation.na r2 = r0.f1816Xf
            java.lang.Class<com.android.messaging.ui.VideoThumbnailView> r5 = com.android.messaging.p041ui.VideoThumbnailView.class
            r6 = 2131558494(0x7f0d005e, float:1.8742305E38)
            r0.m2835a(r1, r6, r2, r5)
            com.google.common.base.F r1 = f1794bg
            com.android.messaging.ui.conversation.na r2 = r0.f1817Yf
            java.lang.Class<com.android.messaging.ui.AudioAttachmentView> r5 = com.android.messaging.p041ui.AudioAttachmentView.class
            r6 = 2131558492(0x7f0d005c, float:1.8742301E38)
            r0.m2835a(r1, r6, r2, r5)
            com.google.common.base.F r1 = f1795cg
            com.android.messaging.ui.conversation.na r2 = r0.f1818Zf
            java.lang.Class<com.android.messaging.ui.PersonItemView> r5 = com.android.messaging.p041ui.PersonItemView.class
            r6 = 2131558493(0x7f0d005d, float:1.8742303E38)
            r0.m2835a(r1, r6, r2, r5)
            com.android.messaging.datamodel.data.s r1 = r0.mData
            com.google.common.base.F r2 = f1796dg
            java.util.List r1 = r1.mo6535a(r2)
            int r2 = r1.size()
            r5 = 0
            r6 = 1
            if (r2 <= r6) goto L_0x008f
            java.util.Comparator r2 = f1792_f
            java.util.Collections.sort(r1, r2)
            com.android.messaging.ui.MultiAttachmentLayout r2 = r0.f1798Ff
            int r7 = r1.size()
            r2.mo7042a(r1, r5, r7)
            com.android.messaging.ui.MultiAttachmentLayout r2 = r0.f1798Ff
            r2.setVisibility(r4)
            goto L_0x0094
        L_0x008f:
            com.android.messaging.ui.MultiAttachmentLayout r2 = r0.f1798Ff
            r2.setVisibility(r3)
        L_0x0094:
            boolean r2 = r0.f1801If
            if (r2 == 0) goto L_0x0176
            int r2 = r1.size()
            if (r2 != 0) goto L_0x0176
            android.widget.TextView r2 = r0.f1800Hf
            java.lang.CharSequence r2 = r2.getText()
            r7 = r2
            android.text.Spanned r7 = (android.text.Spanned) r7
            int r2 = r2.length()
            java.lang.Class<android.text.style.URLSpan> r8 = android.text.style.URLSpan.class
            java.lang.Object[] r2 = r7.getSpans(r4, r2, r8)
            android.text.style.URLSpan[] r2 = (android.text.style.URLSpan[]) r2
            int r7 = r2.length
            r8 = r4
            r9 = r5
            r10 = r9
        L_0x00b7:
            if (r8 >= r7) goto L_0x0178
            r11 = r2[r8]
            java.lang.String r11 = r11.getURL()
            java.lang.String r12 = "http"
            boolean r12 = r11.startsWith(r12)
            if (r12 != 0) goto L_0x00ce
            java.lang.String r12 = "http://"
            java.lang.String r12 = p026b.p027a.p030b.p031a.C0632a.m1025k(r12, r11)
            goto L_0x00cf
        L_0x00ce:
            r12 = r11
        L_0x00cf:
            android.net.Uri r12 = android.net.Uri.parse(r12)
            java.lang.String r13 = r12.getHost()
            java.lang.String r14 = "www.youtube.com"
            boolean r14 = r14.equalsIgnoreCase(r13)
            java.lang.String r15 = "youtu.be"
            if (r14 != 0) goto L_0x00ff
            java.lang.String r14 = "youtube.com"
            boolean r14 = r14.equalsIgnoreCase(r13)
            if (r14 != 0) goto L_0x00ff
            java.lang.String r14 = "m.youtube.com"
            boolean r14 = r14.equalsIgnoreCase(r13)
            if (r14 != 0) goto L_0x00ff
            java.lang.String r14 = "youtube.googleapis.com"
            boolean r14 = r14.equalsIgnoreCase(r13)
            if (r14 != 0) goto L_0x00ff
            boolean r13 = r15.equalsIgnoreCase(r13)
            if (r13 == 0) goto L_0x0163
        L_0x00ff:
            java.lang.String r13 = r12.getPath()
            boolean r14 = android.text.TextUtils.isEmpty(r13)
            if (r14 == 0) goto L_0x010a
            goto L_0x0153
        L_0x010a:
            java.lang.String r14 = "/watch"
            boolean r14 = r13.startsWith(r14)
            if (r14 == 0) goto L_0x0119
            java.lang.String r13 = "v"
            java.lang.String r12 = r12.getQueryParameter(r13)
            goto L_0x0154
        L_0x0119:
            java.lang.String r14 = "/embed/"
            boolean r14 = r13.startsWith(r14)
            if (r14 == 0) goto L_0x0127
            r12 = 7
            java.lang.String r12 = r13.substring(r12)
            goto L_0x0154
        L_0x0127:
            java.lang.String r14 = "/v/"
            boolean r14 = r13.startsWith(r14)
            if (r14 == 0) goto L_0x0135
            r12 = 3
            java.lang.String r12 = r13.substring(r12)
            goto L_0x0154
        L_0x0135:
            java.lang.String r14 = "/apiplayer"
            boolean r14 = r13.startsWith(r14)
            if (r14 == 0) goto L_0x0144
            java.lang.String r13 = "video_id"
            java.lang.String r12 = r12.getQueryParameter(r13)
            goto L_0x0154
        L_0x0144:
            java.lang.String r12 = r12.getHost()
            boolean r12 = r15.equalsIgnoreCase(r12)
            if (r12 == 0) goto L_0x0153
            java.lang.String r12 = r13.substring(r6)
            goto L_0x0154
        L_0x0153:
            r12 = r5
        L_0x0154:
            boolean r13 = android.text.TextUtils.isEmpty(r12)
            if (r13 != 0) goto L_0x0163
            java.lang.String r13 = "https://img.youtube.com/vi/"
            java.lang.String r14 = "/hqdefault.jpg"
            java.lang.String r12 = p026b.p027a.p030b.p031a.C0632a.m1023d(r13, r12, r14)
            goto L_0x0164
        L_0x0163:
            r12 = r5
        L_0x0164:
            boolean r13 = android.text.TextUtils.isEmpty(r12)
            if (r13 != 0) goto L_0x0172
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 == 0) goto L_0x0176
            r10 = r11
            r9 = r12
        L_0x0172:
            int r8 = r8 + 1
            goto L_0x00b7
        L_0x0176:
            r9 = r5
            r10 = r9
        L_0x0178:
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            r2 = r2 ^ r6
            r0.f1802Jf = r2
            int r2 = r1.size()
            r7 = 2131165333(0x7f070095, float:1.794488E38)
            r8 = -1
            if (r2 == r6) goto L_0x019a
            boolean r2 = r0.f1802Jf
            if (r2 == 0) goto L_0x018e
            goto L_0x019a
        L_0x018e:
            com.android.messaging.ui.AsyncImageView r1 = r0.f1799Gf
            r1.mo6858a((com.android.messaging.datamodel.p038b.C0880t) r5)
            com.android.messaging.ui.AsyncImageView r1 = r0.f1799Gf
            r1.setVisibility(r3)
            goto L_0x024a
        L_0x019a:
            android.content.Context r2 = r22.getContext()
            java.lang.String r11 = "window"
            java.lang.Object r2 = r2.getSystemService(r11)
            android.view.WindowManager r2 = (android.view.WindowManager) r2
            android.util.DisplayMetrics r11 = new android.util.DisplayMetrics
            r11.<init>()
            android.view.Display r2 = r2.getDefaultDisplay()
            r2.getMetrics(r11)
            android.content.res.Resources r2 = r22.getResources()
            int r2 = r2.getDimensionPixelSize(r7)
            int r7 = r11.widthPixels
            int r7 = r7 - r2
            int r13 = r7 - r2
            int r2 = r1.size()
            if (r2 != r6) goto L_0x0227
            java.lang.Object r1 = r1.get(r4)
            com.android.messaging.datamodel.data.MessagePartData r1 = (com.android.messaging.datamodel.data.MessagePartData) r1
            com.android.messaging.datamodel.b.D r2 = new com.android.messaging.datamodel.b.D
            r2.<init>(r1, r13, r8, r4)
            java.lang.String r7 = r1.getContentType()
            boolean r7 = com.android.messaging.util.C1481w.isImageType(r7)
            com.android.messaging.util.C1424b.m3592ia(r7)
            com.android.messaging.ui.AsyncImageView r7 = r0.f1799Gf
            android.view.ViewGroup$LayoutParams r7 = r7.getLayoutParams()
            int r9 = r1.getWidth()
            if (r9 == r8) goto L_0x01fb
            int r9 = r1.getHeight()
            if (r9 != r8) goto L_0x01ee
            goto L_0x01fb
        L_0x01ee:
            r7.width = r8
            r9 = -2
            r7.height = r9
            com.android.messaging.ui.AsyncImageView r7 = r0.f1799Gf
            android.widget.ImageView$ScaleType r9 = android.widget.ImageView.ScaleType.FIT_CENTER
            r7.setScaleType(r9)
            goto L_0x021c
        L_0x01fb:
            android.content.res.Resources r9 = r22.getResources()
            r10 = 2131165384(0x7f0700c8, float:1.7944984E38)
            int r9 = r9.getDimensionPixelSize(r10)
            r7.width = r9
            android.content.res.Resources r9 = r22.getResources()
            r10 = 2131165383(0x7f0700c7, float:1.7944982E38)
            int r9 = r9.getDimensionPixelSize(r10)
            r7.height = r9
            com.android.messaging.ui.AsyncImageView r7 = r0.f1799Gf
            android.widget.ImageView$ScaleType r9 = android.widget.ImageView.ScaleType.CENTER_CROP
            r7.setScaleType(r9)
        L_0x021c:
            com.android.messaging.ui.AsyncImageView r7 = r0.f1799Gf
            r7.mo6858a((com.android.messaging.datamodel.p038b.C0880t) r2)
            com.android.messaging.ui.AsyncImageView r2 = r0.f1799Gf
            r2.setTag(r1)
            goto L_0x0245
        L_0x0227:
            com.android.messaging.datamodel.b.L r1 = new com.android.messaging.datamodel.b.L
            android.net.Uri r12 = android.net.Uri.parse(r9)
            r14 = -1
            r15 = 1
            r16 = 1
            r17 = 0
            r18 = 0
            r19 = 0
            r11 = r1
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19)
            com.android.messaging.ui.AsyncImageView r2 = r0.f1799Gf
            r2.mo6858a((com.android.messaging.datamodel.p038b.C0880t) r1)
            com.android.messaging.ui.AsyncImageView r1 = r0.f1799Gf
            r1.setTag(r10)
        L_0x0245:
            com.android.messaging.ui.AsyncImageView r1 = r0.f1799Gf
            r1.setVisibility(r4)
        L_0x024a:
            android.widget.LinearLayout r1 = r0.f1797Ef
            int r1 = r1.getChildCount()
            r2 = r4
        L_0x0251:
            if (r2 >= r1) goto L_0x0264
            android.widget.LinearLayout r7 = r0.f1797Ef
            android.view.View r7 = r7.getChildAt(r2)
            int r7 = r7.getVisibility()
            if (r7 != 0) goto L_0x0261
            r1 = r6
            goto L_0x0265
        L_0x0261:
            int r2 = r2 + 1
            goto L_0x0251
        L_0x0264:
            r1 = r4
        L_0x0265:
            android.widget.LinearLayout r2 = r0.f1797Ef
            if (r1 == 0) goto L_0x026b
            r1 = r4
            goto L_0x026c
        L_0x026b:
            r1 = r3
        L_0x026c:
            r2.setVisibility(r1)
            android.content.res.Resources r1 = r22.getResources()
            com.android.messaging.datamodel.data.s r2 = r0.mData
            java.lang.String r2 = r2.mo6554mg()
            java.lang.String r1 = com.android.messaging.sms.C1029y.m2438b((android.content.res.Resources) r1, (java.lang.String) r2)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            r2 = r2 ^ r6
            if (r2 == 0) goto L_0x028f
            android.widget.TextView r2 = r0.f1810Rf
            r2.setText(r1)
            android.view.View r1 = r0.f1808Pf
            r1.setVisibility(r4)
            goto L_0x0294
        L_0x028f:
            android.view.View r1 = r0.f1808Pf
            r1.setVisibility(r3)
        L_0x0294:
            com.android.messaging.ui.conversation.ConversationMessageBubbleView r1 = r0.f1807Of
            com.android.messaging.datamodel.data.s r2 = r0.mData
            r1.mo7348a((com.android.messaging.datamodel.data.C0936s) r2)
            com.android.messaging.datamodel.data.s r1 = r0.mData
            int r1 = r1.getStatus()
            r2 = 2131820831(0x7f11011f, float:1.9274388E38)
            r7 = 2131820830(0x7f11011e, float:1.9274386E38)
            r9 = 2131820841(0x7f110129, float:1.9274408E38)
            switch(r1) {
                case 4: goto L_0x02df;
                case 5: goto L_0x02df;
                case 6: goto L_0x02db;
                case 7: goto L_0x02db;
                case 8: goto L_0x02b7;
                case 9: goto L_0x02b2;
                default: goto L_0x02ad;
            }
        L_0x02ad:
            switch(r1) {
                case 101: goto L_0x0308;
                case 102: goto L_0x0301;
                case 103: goto L_0x0301;
                case 104: goto L_0x0301;
                case 105: goto L_0x0301;
                case 106: goto L_0x02ee;
                case 107: goto L_0x02e3;
                default: goto L_0x02b0;
            }
        L_0x02b0:
            goto L_0x031d
        L_0x02b2:
            r1 = 2131820838(0x7f110126, float:1.9274402E38)
            goto L_0x031a
        L_0x02b7:
            com.android.messaging.util.sa r1 = com.android.messaging.util.C1474sa.getDefault()
            boolean r1 = r1.mo8228kk()
            if (r1 == 0) goto L_0x031d
            boolean r1 = r22.isSelected()
            if (r1 == 0) goto L_0x02cb
            r1 = 2131820836(0x7f110124, float:1.9274398E38)
            goto L_0x031a
        L_0x02cb:
            com.android.messaging.datamodel.data.s r1 = r0.mData
            r1.getStatus()
            com.android.messaging.datamodel.data.s r1 = r0.mData
            int r1 = r1.mo6557qg()
            int r1 = com.android.messaging.sms.C1029y.m2406Ga(r1)
            goto L_0x031a
        L_0x02db:
            r1 = 2131820839(0x7f110127, float:1.9274404E38)
            goto L_0x031a
        L_0x02df:
            r1 = 2131820840(0x7f110128, float:1.9274406E38)
            goto L_0x031a
        L_0x02e3:
            boolean r1 = com.android.messaging.util.C1464na.m3762ak()
            if (r1 != 0) goto L_0x032c
            r1 = 2131820832(0x7f110120, float:1.927439E38)
            r8 = r1
            goto L_0x02fd
        L_0x02ee:
            boolean r1 = com.android.messaging.util.C1464na.m3762ak()
            if (r1 != 0) goto L_0x032c
            boolean r1 = r22.isSelected()
            if (r1 == 0) goto L_0x02fc
            r8 = r2
            goto L_0x02fd
        L_0x02fc:
            r8 = r7
        L_0x02fd:
            r1 = r5
            r2 = r8
            r8 = r9
            goto L_0x032e
        L_0x0301:
            r8 = 2131820842(0x7f11012a, float:1.927441E38)
            r1 = 2131820834(0x7f110122, float:1.9274394E38)
            goto L_0x031a
        L_0x0308:
            boolean r1 = com.android.messaging.util.C1464na.m3762ak()
            if (r1 != 0) goto L_0x032c
            r8 = 2131820843(0x7f11012b, float:1.9274412E38)
            boolean r1 = r22.isSelected()
            if (r1 == 0) goto L_0x0319
            r1 = r2
            goto L_0x031a
        L_0x0319:
            r1 = r7
        L_0x031a:
            r2 = r1
            r1 = r5
            goto L_0x032e
        L_0x031d:
            com.android.messaging.datamodel.data.s r1 = r0.mData
            boolean r1 = r1.mo6536ag()
            if (r1 != 0) goto L_0x032c
            com.android.messaging.datamodel.data.s r1 = r0.mData
            java.lang.String r1 = r1.mo6541fg()
            goto L_0x032d
        L_0x032c:
            r1 = r5
        L_0x032d:
            r2 = r8
        L_0x032e:
            if (r8 < 0) goto L_0x0332
            r7 = r6
            goto L_0x0333
        L_0x0332:
            r7 = r4
        L_0x0333:
            r9 = 2
            if (r7 == 0) goto L_0x037f
            android.content.res.Resources r10 = r22.getResources()
            java.lang.String r8 = r10.getString(r8)
            android.widget.TextView r10 = r0.mTitleTextView
            r10.setText(r8)
            android.content.res.Resources r8 = r22.getResources()
            r10 = 2131820853(0x7f110135, float:1.9274433E38)
            java.lang.Object[] r11 = new java.lang.Object[r9]
            android.content.Context r12 = r22.getContext()
            com.android.messaging.datamodel.data.s r13 = r0.mData
            int r13 = r13.mo6530Fg()
            long r13 = (long) r13
            java.lang.String r12 = android.text.format.Formatter.formatFileSize(r12, r13)
            r11[r4] = r12
            android.content.Context r12 = r22.getContext()
            com.android.messaging.datamodel.data.s r13 = r0.mData
            long r13 = r13.mo6552lg()
            r15 = 131097(0x20019, float:1.83706E-40)
            java.lang.String r12 = android.text.format.DateUtils.formatDateTime(r12, r13, r15)
            r11[r6] = r12
            java.lang.String r8 = r8.getString(r10, r11)
            android.widget.TextView r10 = r0.f1804Lf
            r10.setText(r8)
            android.widget.LinearLayout r8 = r0.f1805Mf
            r8.setVisibility(r4)
            goto L_0x0384
        L_0x037f:
            android.widget.LinearLayout r8 = r0.f1805Mf
            r8.setVisibility(r3)
        L_0x0384:
            android.content.res.Resources r8 = r22.getResources()
            com.android.messaging.datamodel.data.s r10 = r0.mData
            java.lang.String r10 = r10.mo6554mg()
            java.lang.String r8 = com.android.messaging.sms.C1029y.m2438b((android.content.res.Resources) r8, (java.lang.String) r10)
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            r8 = r8 ^ r6
            boolean r10 = r0.f1815Wf
            if (r10 != 0) goto L_0x03ad
            com.android.messaging.datamodel.data.s r10 = r0.mData
            boolean r10 = r10.mo6536ag()
            if (r10 != 0) goto L_0x03ad
            com.android.messaging.datamodel.data.s r10 = r0.mData
            boolean r10 = r10.mo6546gg()
            if (r10 == 0) goto L_0x03ad
            r10 = r6
            goto L_0x03ae
        L_0x03ad:
            r10 = r4
        L_0x03ae:
            if (r10 == 0) goto L_0x03c1
            android.widget.TextView r11 = r0.f1806Nf
            com.android.messaging.datamodel.data.s r12 = r0.mData
            java.lang.String r12 = r12.mo6565xg()
            r11.setText(r12)
            android.widget.TextView r11 = r0.f1806Nf
            r11.setVisibility(r4)
            goto L_0x03c6
        L_0x03c1:
            android.widget.TextView r11 = r0.f1806Nf
            r11.setVisibility(r3)
        L_0x03c6:
            if (r2 < 0) goto L_0x03d0
            android.content.res.Resources r1 = r22.getResources()
            java.lang.String r1 = r1.getString(r2)
        L_0x03d0:
            android.widget.TextView r2 = r0.f1803Kf
            r2.setText(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r1 = r1 ^ r6
            if (r1 == 0) goto L_0x03e2
            android.widget.TextView r2 = r0.f1803Kf
            r2.setVisibility(r4)
            goto L_0x03e7
        L_0x03e2:
            android.widget.TextView r2 = r0.f1803Kf
            r2.setVisibility(r3)
        L_0x03e7:
            com.android.messaging.datamodel.data.s r2 = r0.mData
            int r2 = r2.getStatus()
            if (r2 != r9) goto L_0x03f1
            r2 = r6
            goto L_0x03f2
        L_0x03f1:
            r2 = r4
        L_0x03f2:
            android.view.View r9 = r0.f1811Sf
            if (r2 == 0) goto L_0x03f7
            r3 = r4
        L_0x03f7:
            r9.setVisibility(r3)
            com.android.messaging.datamodel.data.s r3 = r0.mData
            boolean r3 = r3.mo6546gg()
            if (r3 == 0) goto L_0x0412
            com.android.messaging.datamodel.data.s r3 = r0.mData
            boolean r3 = r3.mo6553mf()
            if (r3 == 0) goto L_0x0410
            boolean r3 = r22.m2832Om()
            if (r3 == 0) goto L_0x0412
        L_0x0410:
            r3 = r6
            goto L_0x0413
        L_0x0412:
            r3 = r4
        L_0x0413:
            com.android.messaging.ui.conversation.oa r9 = r0.mHost
            com.android.messaging.datamodel.data.s r11 = r0.mData
            java.lang.String r11 = r11.mo6559sg()
            com.android.messaging.datamodel.data.ba r9 = r9.mo7386a(r11, r6)
            if (r9 == 0) goto L_0x0433
            java.lang.String r11 = r9.displayName
            boolean r11 = android.text.TextUtils.isEmpty(r11)
            if (r11 != 0) goto L_0x0433
            com.android.messaging.datamodel.data.s r11 = r0.mData
            boolean r11 = r11.mo6536ag()
            if (r11 != 0) goto L_0x0433
            r11 = r6
            goto L_0x0434
        L_0x0433:
            r11 = r4
        L_0x0434:
            r12 = 2131099868(0x7f0600dc, float:1.7812101E38)
            if (r11 == 0) goto L_0x0472
            com.android.messaging.datamodel.data.s r13 = r0.mData
            boolean r13 = r13.mo6546gg()
            if (r13 == 0) goto L_0x0453
            android.content.res.Resources r13 = r22.getResources()
            r14 = 2131820790(0x7f1100f6, float:1.9274305E38)
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.String r15 = r9.displayName
            r6[r4] = r15
            java.lang.String r6 = r13.getString(r14, r6)
            goto L_0x0455
        L_0x0453:
            java.lang.String r6 = r9.displayName
        L_0x0455:
            android.widget.TextView r13 = r0.f1814Vf
            r13.setText(r6)
            android.widget.TextView r6 = r0.f1814Vf
            if (r3 == 0) goto L_0x0467
            android.content.res.Resources r3 = r22.getResources()
            int r3 = r3.getColor(r12)
            goto L_0x0469
        L_0x0467:
            int r3 = r9.f1217oC
        L_0x0469:
            r6.setTextColor(r3)
            android.widget.TextView r3 = r0.f1814Vf
            r3.setVisibility(r4)
            goto L_0x047e
        L_0x0472:
            android.widget.TextView r3 = r0.f1814Vf
            r3.setText(r5)
            android.widget.TextView r3 = r0.f1814Vf
            r6 = 8
            r3.setVisibility(r6)
        L_0x047e:
            if (r10 != 0) goto L_0x0489
            if (r1 != 0) goto L_0x0489
            if (r2 != 0) goto L_0x0489
            if (r11 == 0) goto L_0x0487
            goto L_0x0489
        L_0x0487:
            r1 = r4
            goto L_0x048a
        L_0x0489:
            r1 = 1
        L_0x048a:
            android.view.ViewGroup r2 = r0.f1812Tf
            if (r1 == 0) goto L_0x0490
            r3 = r4
            goto L_0x0492
        L_0x0490:
            r3 = 8
        L_0x0492:
            r2.setVisibility(r3)
            if (r7 != 0) goto L_0x04a6
            if (r8 != 0) goto L_0x04a6
            com.android.messaging.datamodel.data.s r2 = r0.mData
            boolean r2 = r2.hasText()
            if (r2 != 0) goto L_0x04a6
            if (r1 == 0) goto L_0x04a4
            goto L_0x04a6
        L_0x04a4:
            r1 = r4
            goto L_0x04a7
        L_0x04a6:
            r1 = 1
        L_0x04a7:
            android.view.ViewGroup r2 = r0.f1813Uf
            if (r1 == 0) goto L_0x04ad
            r1 = r4
            goto L_0x04af
        L_0x04ad:
            r1 = 8
        L_0x04af:
            r2.setVisibility(r1)
            boolean r1 = r22.m2833Pm()
            if (r1 == 0) goto L_0x04c5
            com.android.messaging.ui.ContactIconView r1 = r0.mContactIconView
            r2 = 8
            r1.setVisibility(r2)
            com.android.messaging.ui.ContactIconView r1 = r0.mContactIconView
            r1.mo6930f(r5)
            goto L_0x04fd
        L_0x04c5:
            com.android.messaging.ui.ContactIconView r1 = r0.mContactIconView
            r1.setVisibility(r4)
            com.android.messaging.datamodel.data.s r1 = r0.mData
            android.net.Uri r1 = r1.mo6526Bg()
            com.android.messaging.datamodel.data.s r2 = r0.mData
            java.lang.String r2 = r2.mo6567zg()
            com.android.messaging.datamodel.data.s r3 = r0.mData
            java.lang.String r3 = r3.mo6525Ag()
            com.android.messaging.datamodel.data.s r6 = r0.mData
            java.lang.String r6 = r6.mo6562ug()
            android.net.Uri r8 = com.android.messaging.util.C1426c.m3598a((android.net.Uri) r1, (java.lang.CharSequence) r2, (java.lang.String) r3, (java.lang.String) r6)
            com.android.messaging.ui.ContactIconView r7 = r0.mContactIconView
            com.android.messaging.datamodel.data.s r1 = r0.mData
            long r9 = r1.mo6560tg()
            com.android.messaging.datamodel.data.s r1 = r0.mData
            java.lang.String r11 = r1.mo6562ug()
            com.android.messaging.datamodel.data.s r1 = r0.mData
            java.lang.String r12 = r1.mo6525Ag()
            r7.mo6929a(r8, r9, r11, r12)
        L_0x04fd:
            android.content.res.Resources r1 = r22.getResources()
            com.android.messaging.ui.D r6 = com.android.messaging.p041ui.C1037D.get()
            com.android.messaging.datamodel.data.s r2 = r0.mData
            boolean r2 = r2.mo6546gg()
            r3 = r2 ^ 1
            boolean r12 = r22.m2831Nm()
            r13 = 2131165402(0x7f0700da, float:1.794502E38)
            int r14 = r1.getDimensionPixelSize(r13)
            r7 = 2131165401(0x7f0700d9, float:1.7945018E38)
            int r15 = r1.getDimensionPixelSize(r7)
            r7 = 2131165399(0x7f0700d7, float:1.7945014E38)
            int r16 = r1.getDimensionPixelOffset(r7)
            r7 = 2131165333(0x7f070095, float:1.794488E38)
            int r17 = r1.getDimensionPixelSize(r7)
            r7 = 2131165404(0x7f0700dc, float:1.7945024E38)
            int r18 = r1.getDimensionPixelOffset(r7)
            r7 = 2131165405(0x7f0700dd, float:1.7945026E38)
            int r19 = r1.getDimensionPixelOffset(r7)
            r7 = 2131165403(0x7f0700db, float:1.7945022E38)
            int r20 = r1.getDimensionPixelOffset(r7)
            com.android.messaging.datamodel.data.s r7 = r0.mData
            boolean r7 = r7.mo6553mf()
            if (r7 == 0) goto L_0x0596
            boolean r7 = r22.m2832Om()
            if (r7 == 0) goto L_0x0580
            if (r2 == 0) goto L_0x0555
            r5 = r16
            goto L_0x0556
        L_0x0555:
            r5 = r4
        L_0x0556:
            if (r3 == 0) goto L_0x0559
            goto L_0x055b
        L_0x0559:
            r16 = r4
        L_0x055b:
            boolean r7 = r22.isSelected()
            r9 = 0
            com.android.messaging.datamodel.data.s r3 = r0.mData
            boolean r10 = r3.mo6532Ig()
            com.android.messaging.datamodel.data.s r3 = r0.mData
            java.lang.String r11 = r3.mo6562ug()
            r8 = r2
            android.graphics.drawable.Drawable r3 = r6.mo6944a(r7, r8, r9, r10, r11)
            r11 = r5
            r6 = r14
            r12 = r16
            r5 = r17
            r7 = r18
            r9 = r7
            r8 = r19
            r10 = r20
            goto L_0x05dc
        L_0x0580:
            if (r2 == 0) goto L_0x0585
            r6 = r16
            goto L_0x0586
        L_0x0585:
            r6 = r4
        L_0x0586:
            if (r3 == 0) goto L_0x0589
            goto L_0x058b
        L_0x0589:
            r16 = r4
        L_0x058b:
            r7 = r4
            r8 = r7
            r9 = r8
            r10 = r9
            r3 = r5
            r11 = r6
            r12 = r16
            r5 = r10
            r6 = r5
            goto L_0x05dc
        L_0x0596:
            if (r12 != 0) goto L_0x059d
            if (r2 == 0) goto L_0x059d
            r5 = r16
            goto L_0x059e
        L_0x059d:
            r5 = r4
        L_0x059e:
            if (r12 != 0) goto L_0x05a5
            if (r3 == 0) goto L_0x05a5
            r21 = r16
            goto L_0x05a7
        L_0x05a5:
            r21 = r4
        L_0x05a7:
            boolean r7 = r22.isSelected()
            boolean r9 = r22.m2831Nm()
            com.android.messaging.datamodel.data.s r8 = r0.mData
            boolean r10 = r8.mo6532Ig()
            com.android.messaging.datamodel.data.s r8 = r0.mData
            java.lang.String r11 = r8.mo6562ug()
            r8 = r2
            android.graphics.drawable.Drawable r6 = r6.mo6944a(r7, r8, r9, r10, r11)
            if (r12 == 0) goto L_0x05c7
            if (r2 == 0) goto L_0x05c7
            int r7 = r18 + r16
            goto L_0x05c9
        L_0x05c7:
            r7 = r18
        L_0x05c9:
            if (r12 == 0) goto L_0x05cf
            if (r3 == 0) goto L_0x05cf
            int r18 = r18 + r16
        L_0x05cf:
            r11 = r5
            r3 = r6
            r5 = r17
            r9 = r18
            r8 = r19
            r10 = r20
            r12 = r21
            r6 = r4
        L_0x05dc:
            if (r2 == 0) goto L_0x05e2
            r2 = 8388627(0x800013, float:1.175497E-38)
            goto L_0x05e5
        L_0x05e2:
            r2 = 8388629(0x800015, float:1.1754973E-38)
        L_0x05e5:
            boolean r16 = r22.m2833Pm()
            if (r16 == 0) goto L_0x05ec
            goto L_0x05ed
        L_0x05ec:
            r14 = r15
        L_0x05ed:
            r15 = 2131165400(0x7f0700d8, float:1.7945016E38)
            int r1 = r1.getDimensionPixelOffset(r15)
            android.view.ViewGroup r15 = r0.f1813Uf
            com.android.messaging.util.C1416U.m3566a((android.view.View) r15, (android.graphics.drawable.Drawable) r3)
            android.view.ViewGroup r3 = r0.f1813Uf
            r3.setMinimumHeight(r5)
            android.view.ViewGroup r3 = r0.f1813Uf
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r3 = (android.widget.LinearLayout.LayoutParams) r3
            r3.topMargin = r6
            boolean r3 = com.android.messaging.util.C1486ya.m3860pk()
            if (r3 == 0) goto L_0x0619
            android.view.ViewGroup r3 = r0.f1813Uf
            r3.setPadding(r9, r8, r7, r10)
            com.android.messaging.ui.conversation.ConversationMessageBubbleView r3 = r0.f1807Of
            r3.setPadding(r12, r4, r11, r4)
            goto L_0x0623
        L_0x0619:
            android.view.ViewGroup r3 = r0.f1813Uf
            r3.setPadding(r7, r8, r9, r10)
            com.android.messaging.ui.conversation.ConversationMessageBubbleView r3 = r0.f1807Of
            r3.setPadding(r11, r4, r12, r4)
        L_0x0623:
            int r3 = r22.getPaddingLeft()
            int r5 = r22.getPaddingRight()
            r0.setPadding(r3, r14, r5, r4)
            com.android.messaging.ui.conversation.ConversationMessageBubbleView r3 = r0.f1807Of
            r3.setGravity(r2)
            android.widget.LinearLayout r3 = r0.f1797Ef
            r3.setGravity(r2)
            android.content.res.Resources r2 = r22.getResources()
            r3 = 2131099796(0x7f060094, float:1.7811955E38)
            int r2 = r2.getColor(r3)
            com.android.messaging.ui.AsyncImageView r3 = r0.f1799Gf
            int r3 = r3.getVisibility()
            if (r3 != 0) goto L_0x065c
            boolean r3 = r22.isSelected()
            if (r3 == 0) goto L_0x0657
            com.android.messaging.ui.AsyncImageView r3 = r0.f1799Gf
            r3.setColorFilter(r2)
            goto L_0x065c
        L_0x0657:
            com.android.messaging.ui.AsyncImageView r3 = r0.f1799Gf
            r3.clearColorFilter()
        L_0x065c:
            com.android.messaging.ui.MultiAttachmentLayout r3 = r0.f1798Ff
            int r3 = r3.getVisibility()
            if (r3 != 0) goto L_0x0675
            boolean r3 = r22.isSelected()
            if (r3 == 0) goto L_0x0670
            com.android.messaging.ui.MultiAttachmentLayout r3 = r0.f1798Ff
            r3.setColorFilter(r2)
            goto L_0x0675
        L_0x0670:
            com.android.messaging.ui.MultiAttachmentLayout r3 = r0.f1798Ff
            r3.clearColorFilter()
        L_0x0675:
            android.widget.LinearLayout r3 = r0.f1797Ef
            int r3 = r3.getChildCount()
            r5 = r4
        L_0x067c:
            if (r5 >= r3) goto L_0x06a0
            android.widget.LinearLayout r6 = r0.f1797Ef
            android.view.View r6 = r6.getChildAt(r5)
            boolean r7 = r6 instanceof com.android.messaging.p041ui.VideoThumbnailView
            if (r7 == 0) goto L_0x069d
            int r7 = r6.getVisibility()
            if (r7 != 0) goto L_0x069d
            com.android.messaging.ui.VideoThumbnailView r6 = (com.android.messaging.p041ui.VideoThumbnailView) r6
            boolean r7 = r22.isSelected()
            if (r7 == 0) goto L_0x069a
            r6.setColorFilter(r2)
            goto L_0x069d
        L_0x069a:
            r6.clearColorFilter()
        L_0x069d:
            int r5 = r5 + 1
            goto L_0x067c
        L_0x06a0:
            android.content.res.Resources r2 = r22.getResources()
            int r2 = r2.getDimensionPixelSize(r13)
            android.widget.LinearLayout r3 = r0.f1797Ef
            int r3 = r3.getChildCount()
            r5 = r4
            r6 = r5
        L_0x06b0:
            if (r5 >= r3) goto L_0x06cf
            android.widget.LinearLayout r7 = r0.f1797Ef
            android.view.View r7 = r7.getChildAt(r5)
            int r8 = r7.getVisibility()
            if (r8 != 0) goto L_0x06cc
            if (r6 == 0) goto L_0x06c2
            r6 = r2
            goto L_0x06c3
        L_0x06c2:
            r6 = r4
        L_0x06c3:
            android.view.ViewGroup$LayoutParams r7 = r7.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r7 = (android.widget.LinearLayout.LayoutParams) r7
            r7.topMargin = r6
            r6 = 1
        L_0x06cc:
            int r5 = r5 + 1
            goto L_0x06b0
        L_0x06cf:
            android.view.ViewGroup r2 = r0.f1812Tf
            r2.setPadding(r4, r1, r4, r4)
            boolean r1 = r22.isSelected()
            r2 = 2131099801(0x7f060099, float:1.7811965E38)
            r3 = 2131099787(0x7f06008b, float:1.7811937E38)
            r5 = 2131099800(0x7f060098, float:1.7811963E38)
            r6 = 2131099869(0x7f0600dd, float:1.7812103E38)
            if (r1 == 0) goto L_0x06fa
            r1 = 2131099786(0x7f06008a, float:1.7811935E38)
            r2 = 2131099785(0x7f060089, float:1.7811933E38)
            boolean r7 = r22.m2832Om()
            if (r7 == 0) goto L_0x06f3
            goto L_0x06f4
        L_0x06f3:
            r3 = r6
        L_0x06f4:
            r7 = r3
            r3 = r1
            r1 = r5
            r5 = r2
            r2 = r7
            goto L_0x073f
        L_0x06fa:
            com.android.messaging.datamodel.data.s r1 = r0.mData
            boolean r1 = r1.mo6546gg()
            if (r1 == 0) goto L_0x0704
            r1 = r5
            goto L_0x0707
        L_0x0704:
            r1 = 2131099802(0x7f06009a, float:1.7811967E38)
        L_0x0707:
            com.android.messaging.datamodel.data.s r3 = r0.mData
            int r3 = r3.getStatus()
            r7 = 1
            if (r3 == r7) goto L_0x0738
            r7 = 2
            if (r3 == r7) goto L_0x0738
            switch(r3) {
                case 4: goto L_0x0738;
                case 5: goto L_0x0738;
                case 6: goto L_0x0738;
                case 7: goto L_0x0738;
                case 8: goto L_0x0724;
                case 9: goto L_0x0724;
                default: goto L_0x0716;
            }
        L_0x0716:
            switch(r3) {
                case 101: goto L_0x0736;
                case 102: goto L_0x0736;
                case 103: goto L_0x0736;
                case 104: goto L_0x0736;
                case 105: goto L_0x0736;
                case 106: goto L_0x0729;
                case 107: goto L_0x0729;
                default: goto L_0x0719;
            }
        L_0x0719:
            r2 = 2131099868(0x7f0600dc, float:1.7812101E38)
            r3 = -1
            r5 = 2131099868(0x7f0600dc, float:1.7812101E38)
            r7 = r2
            r2 = r5
            r5 = r3
            goto L_0x073e
        L_0x0724:
            r2 = 2131099795(0x7f060093, float:1.7811953E38)
            r3 = r6
            goto L_0x073a
        L_0x0729:
            r1 = 2131099793(0x7f060091, float:1.781195E38)
            r3 = 2131099792(0x7f060090, float:1.7811947E38)
            r5 = 2131099798(0x7f060096, float:1.781196E38)
            r7 = r2
            r2 = r1
            r1 = r7
            goto L_0x073f
        L_0x0736:
            r2 = r5
            goto L_0x0739
        L_0x0738:
            r2 = r6
        L_0x0739:
            r3 = r2
        L_0x073a:
            r5 = 2131099868(0x7f0600dc, float:1.7812101E38)
            r7 = r3
        L_0x073e:
            r3 = r1
        L_0x073f:
            android.content.res.Resources r8 = r22.getResources()
            int r1 = r8.getColor(r1)
            android.widget.TextView r8 = r0.f1800Hf
            r8.setTextColor(r1)
            android.widget.TextView r8 = r0.f1800Hf
            r8.setLinkTextColor(r1)
            android.widget.TextView r8 = r0.f1810Rf
            r8.setTextColor(r1)
            if (r3 < 0) goto L_0x0765
            android.widget.TextView r1 = r0.mTitleTextView
            android.content.res.Resources r8 = r22.getResources()
            int r3 = r8.getColor(r3)
            r1.setTextColor(r3)
        L_0x0765:
            if (r5 < 0) goto L_0x0774
            android.widget.TextView r1 = r0.f1804Lf
            android.content.res.Resources r3 = r22.getResources()
            int r3 = r3.getColor(r5)
            r1.setTextColor(r3)
        L_0x0774:
            r1 = 2131099868(0x7f0600dc, float:1.7812101E38)
            if (r2 != r1) goto L_0x0788
            com.android.messaging.datamodel.data.s r1 = r0.mData
            boolean r1 = r1.mo6553mf()
            if (r1 == 0) goto L_0x0788
            boolean r1 = r22.m2832Om()
            if (r1 != 0) goto L_0x0788
            r2 = r6
        L_0x0788:
            android.widget.TextView r1 = r0.f1803Kf
            android.content.res.Resources r3 = r22.getResources()
            int r3 = r3.getColor(r2)
            r1.setTextColor(r3)
            android.widget.TextView r1 = r0.f1809Qf
            android.content.res.Resources r3 = r22.getResources()
            int r3 = r3.getColor(r7)
            r1.setTextColor(r3)
            android.widget.TextView r1 = r0.f1806Nf
            android.content.res.Resources r3 = r22.getResources()
            int r2 = r3.getColor(r2)
            r1.setTextColor(r2)
            r22.requestLayout()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            android.content.res.Resources r2 = r22.getResources()
            r3 = 2131820757(0x7f1100d5, float:1.9274238E38)
            java.lang.String r3 = r2.getString(r3)
            com.android.messaging.datamodel.data.s r5 = r0.mData
            java.lang.String r5 = r5.getText()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x07d4
            boolean r5 = r0.f1801If
            if (r5 != 0) goto L_0x07d4
            r5 = 1
            goto L_0x07d5
        L_0x07d4:
            r5 = r4
        L_0x07d5:
            com.android.messaging.datamodel.data.s r6 = r0.mData
            boolean r6 = r6.mo6546gg()
            if (r6 == 0) goto L_0x07f9
            if (r5 == 0) goto L_0x07e3
            r5 = 2131820791(0x7f1100f7, float:1.9274307E38)
            goto L_0x07e6
        L_0x07e3:
            r5 = 2131820789(0x7f1100f5, float:1.9274303E38)
        L_0x07e6:
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]
            com.android.messaging.datamodel.data.s r7 = r0.mData
            java.lang.String r7 = r7.mo6565xg()
            r6[r4] = r7
            java.lang.String r4 = r2.getString(r5, r6)
            r1.append(r4)
            goto L_0x0809
        L_0x07f9:
            if (r5 == 0) goto L_0x07ff
            r4 = 2131820906(0x7f11016a, float:1.927454E38)
            goto L_0x0802
        L_0x07ff:
            r4 = 2131820905(0x7f110169, float:1.9274538E38)
        L_0x0802:
            java.lang.String r4 = r2.getString(r4)
            r1.append(r4)
        L_0x0809:
            android.view.View r4 = r0.f1808Pf
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x081d
            r1.append(r3)
            android.widget.TextView r4 = r0.f1810Rf
            java.lang.CharSequence r4 = r4.getText()
            r1.append(r4)
        L_0x081d:
            android.widget.TextView r4 = r0.f1800Hf
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x0842
            boolean r4 = r0.f1801If
            if (r4 == 0) goto L_0x0830
            android.widget.TextView r4 = r0.f1800Hf
            r5 = 1
            r4.setImportantForAccessibility(r5)
            goto L_0x0842
        L_0x0830:
            android.widget.TextView r4 = r0.f1800Hf
            r5 = 2
            r4.setImportantForAccessibility(r5)
            r1.append(r3)
            android.widget.TextView r4 = r0.f1800Hf
            java.lang.CharSequence r4 = r4.getText()
            r1.append(r4)
        L_0x0842:
            android.widget.LinearLayout r4 = r0.f1805Mf
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x0862
            r1.append(r3)
            android.widget.TextView r4 = r0.mTitleTextView
            java.lang.CharSequence r4 = r4.getText()
            r1.append(r4)
            r1.append(r3)
            android.widget.TextView r4 = r0.f1804Lf
            java.lang.CharSequence r4 = r4.getText()
            r1.append(r4)
        L_0x0862:
            android.widget.TextView r4 = r0.f1803Kf
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x0876
            r1.append(r3)
            android.widget.TextView r4 = r0.f1803Kf
            java.lang.CharSequence r4 = r4.getText()
            r1.append(r4)
        L_0x0876:
            android.widget.TextView r4 = r0.f1814Vf
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x088a
            r1.append(r3)
            android.widget.TextView r4 = r0.f1814Vf
            java.lang.CharSequence r4 = r4.getText()
            r1.append(r4)
        L_0x088a:
            android.view.View r4 = r0.f1811Sf
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L_0x089f
            r1.append(r3)
            r3 = 2131820730(0x7f1100ba, float:1.9274183E38)
            java.lang.String r2 = r2.getString(r3)
            r1.append(r2)
        L_0x089f:
            r0.setContentDescription(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.p041ui.conversation.ConversationMessageView.mo7353a(android.database.Cursor, boolean, java.lang.String):void");
    }

    /* renamed from: a */
    public void mo7355a(C1186oa oaVar) {
        this.mHost = oaVar;
    }

    /* renamed from: a */
    public void mo7354a(C1114b bVar) {
        C1424b.m3594t(this.f1799Gf);
        this.f1799Gf.mo6861b(bVar);
        this.f1798Ff.mo7041a(bVar);
    }

    /* renamed from: a */
    private void m2835a(C1509F f, int i, C1184na naVar, Class cls) {
        View childAt;
        LayoutInflater from = LayoutInflater.from(getContext());
        int i2 = -1;
        do {
            i2++;
            childAt = this.f1797Ef.getChildAt(i2);
            if (childAt == null || cls.isInstance(childAt)) {
            }
            i2++;
            childAt = this.f1797Ef.getChildAt(i2);
            break;
        } while (cls.isInstance(childAt));
        for (MessagePartData messagePartData : this.mData.mo6535a(f)) {
            View childAt2 = this.f1797Ef.getChildAt(i2);
            if (!cls.isInstance(childAt2)) {
                childAt2 = from.inflate(i, this.f1797Ef, false);
                childAt2.setOnClickListener(this);
                childAt2.setOnLongClickListener(this);
                this.f1797Ef.addView(childAt2, i2);
            }
            naVar.mo7496a(childAt2, messagePartData);
            childAt2.setTag(messagePartData);
            childAt2.setVisibility(0);
            i2++;
        }
        while (i2 < this.f1797Ef.getChildCount() && cls.isInstance(this.f1797Ef.getChildAt(i2))) {
            this.f1797Ef.removeViewAt(i2);
        }
    }

    /* renamed from: a */
    public boolean mo6870a(MessagePartData messagePartData, Rect rect, boolean z) {
        return this.mHost.mo7395a(this, messagePartData, rect, z);
    }
}
