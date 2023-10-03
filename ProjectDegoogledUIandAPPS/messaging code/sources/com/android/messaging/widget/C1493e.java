package com.android.messaging.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import com.android.messaging.R;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.C0936s;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.p038b.C0840C;
import com.android.messaging.datamodel.p038b.C0841D;
import com.android.messaging.datamodel.p038b.C0842E;
import com.android.messaging.datamodel.p038b.C0849L;
import com.android.messaging.datamodel.p038b.C0881u;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1430e;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.widget.e */
class C1493e extends C1489a {

    /* renamed from: Ka */
    private String f2367Ka;

    /* renamed from: Nj */
    private C0881u f2368Nj;

    public C1493e(Context context, Intent intent) {
        super(context, intent);
        this.f2367Ka = intent.getStringExtra("conversation_id");
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "BugleFactory intent: " + intent + "widget id: " + this.mAppWidgetId);
        }
        this.mIconSize = (int) context.getResources().getDimension(R.dimen.contact_icon_view_normal_size);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        r6 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005d, code lost:
        if (r18.mo6536ag() != false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005f, code lost:
        com.android.messaging.util.C1485y.m3841a(r18.mo6558rg(), false).toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006a, code lost:
        r5 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006b, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0075, code lost:
        r4 = new java.lang.StringBuilder();
        r9 = r0.mContext.getString(com.android.messaging.R.string.enumeration_comma);
        r10 = !android.text.TextUtils.isEmpty(r18.getText());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0090, code lost:
        if (r18.mo6546gg() == false) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0092, code lost:
        if (r10 == false) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0094, code lost:
        r10 = com.android.messaging.R.string.incoming_text_sender_content_description;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0098, code lost:
        r10 = com.android.messaging.R.string.incoming_sender_content_description;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009b, code lost:
        r4.append(r0.mContext.getString(r10, new java.lang.Object[]{r18.mo6565xg()}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ad, code lost:
        if (r10 == false) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00af, code lost:
        r10 = com.android.messaging.R.string.outgoing_text_sender_content_description;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b3, code lost:
        r10 = com.android.messaging.R.string.outgoing_sender_content_description;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b6, code lost:
        r4.append(r0.mContext.getString(r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00bf, code lost:
        if (r6 < 0) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c1, code lost:
        r10 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c3, code lost:
        r10 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ca, code lost:
        if (r10 == false) goto L_0x0105;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00cc, code lost:
        r2.setTextViewText(com.android.messaging.R.id.message, r0.mContext.getString(r6));
        r1 = r0.mContext;
        r1 = r1.getString(com.android.messaging.R.string.mms_info, new java.lang.Object[]{android.text.format.Formatter.formatFileSize(r1, (long) r18.mo6530Fg()), android.text.format.DateUtils.formatDateTime(r0.mContext, r18.mo6552lg(), 131097)});
        r2.setTextViewText(com.android.messaging.R.id.date, r1);
        r4.append(r9);
        r4.append(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0109, code lost:
        if (android.text.TextUtils.isEmpty(r17) != false) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x010b, code lost:
        r2.setTextViewText(com.android.messaging.R.id.message, r1);
        r4.append(r9);
        r4.append(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0115, code lost:
        r2.setViewVisibility(com.android.messaging.R.id.message, 8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x011a, code lost:
        r1 = com.android.messaging.sms.C1029y.m2438b(r0.mContext.getResources(), r18.mo6554mg());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x012c, code lost:
        if (android.text.TextUtils.isEmpty(r1) != false) goto L_0x0134;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x012e, code lost:
        r4.append(r9);
        r4.append(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0134, code lost:
        if (r5 < 0) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0136, code lost:
        r1 = r0.mContext.getString(r5);
        r5 = new android.text.SpannableString(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0141, code lost:
        if (r3 == false) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0143, code lost:
        r5.setSpan(new android.text.style.ForegroundColorSpan(r0.mContext.getResources().getColor(com.android.messaging.R.color.timestamp_text_failed)), 0, r1.length(), 33);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x015e, code lost:
        r2.setTextViewText(com.android.messaging.R.id.date, r5);
        r4.append(r9);
        r4.append(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0168, code lost:
        r4.append(r9);
        r4.append(com.android.messaging.util.C1485y.m3841a(r18.mo6558rg(), false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x017a, code lost:
        if (r18.mo6553mf() == false) goto L_0x01c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x017c, code lost:
        r1 = r18.mo6534_f().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0188, code lost:
        if (r1.hasNext() == false) goto L_0x01c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x018a, code lost:
        r3 = (com.android.messaging.datamodel.data.MessagePartData) r1.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0194, code lost:
        if (r3.mo6304fh() == false) goto L_0x019a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0196, code lost:
        r3 = com.android.messaging.R.string.conversation_list_snippet_picture;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x019e, code lost:
        if (r3.mo6315ih() == false) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01a0, code lost:
        r3 = com.android.messaging.R.string.conversation_list_snippet_video;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a8, code lost:
        if (r3.mo6302eh() == false) goto L_0x01ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01aa, code lost:
        r3 = com.android.messaging.R.string.conversation_list_snippet_audio_clip;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01b2, code lost:
        if (r3.mo6314hh() == false) goto L_0x01b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01b4, code lost:
        r3 = com.android.messaging.R.string.conversation_list_snippet_vcard;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01b8, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01b9, code lost:
        if (r3 <= 0) goto L_0x0184;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01bb, code lost:
        r4.append(r9);
        r4.append(r0.mContext.getString(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01cc, code lost:
        if (r18.mo6546gg() == false) goto L_0x01d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01ce, code lost:
        r0 = com.android.messaging.R.id.widget_message_item_incoming;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01d2, code lost:
        r0 = com.android.messaging.R.id.widget_message_item_outgoing;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01d5, code lost:
        r2.setContentDescription(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0028, code lost:
        if (com.android.messaging.util.C1464na.m3762ak() == false) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01d8, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002a, code lost:
        r6 = com.android.messaging.R.string.message_title_download_failed;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m3902a(java.lang.String r17, com.android.messaging.datamodel.data.C0936s r18, android.widget.RemoteViews r19) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r19
            int r3 = r18.getStatus()
            r4 = 2131820841(0x7f110129, float:1.9274408E38)
            r5 = 2131820830(0x7f11011e, float:1.9274386E38)
            r6 = -1
            r7 = 1
            r8 = 0
            switch(r3) {
                case 4: goto L_0x0071;
                case 5: goto L_0x0071;
                case 6: goto L_0x006d;
                case 7: goto L_0x006d;
                case 8: goto L_0x0043;
                case 9: goto L_0x003e;
                default: goto L_0x0016;
            }
        L_0x0016:
            switch(r3) {
                case 101: goto L_0x0034;
                case 102: goto L_0x002c;
                case 103: goto L_0x002c;
                case 104: goto L_0x002c;
                case 105: goto L_0x002c;
                case 106: goto L_0x0024;
                case 107: goto L_0x001a;
                default: goto L_0x0019;
            }
        L_0x0019:
            goto L_0x0059
        L_0x001a:
            boolean r3 = com.android.messaging.util.C1464na.m3762ak()
            if (r3 != 0) goto L_0x006a
            r5 = 2131820832(0x7f110120, float:1.927439E38)
            goto L_0x002a
        L_0x0024:
            boolean r3 = com.android.messaging.util.C1464na.m3762ak()
            if (r3 != 0) goto L_0x006a
        L_0x002a:
            r6 = r4
            goto L_0x0041
        L_0x002c:
            r4 = 2131820842(0x7f11012a, float:1.927441E38)
            r5 = 2131820834(0x7f110122, float:1.9274394E38)
        L_0x0032:
            r6 = r4
            goto L_0x006b
        L_0x0034:
            boolean r3 = com.android.messaging.util.C1464na.m3762ak()
            if (r3 != 0) goto L_0x006a
            r4 = 2131820843(0x7f11012b, float:1.9274412E38)
            goto L_0x0032
        L_0x003e:
            r5 = 2131820838(0x7f110126, float:1.9274402E38)
        L_0x0041:
            r3 = r7
            goto L_0x0075
        L_0x0043:
            com.android.messaging.util.sa r3 = com.android.messaging.util.C1474sa.getDefault()
            boolean r3 = r3.mo8228kk()
            if (r3 == 0) goto L_0x0059
            r18.getStatus()
            int r3 = r18.mo6557qg()
            int r5 = com.android.messaging.sms.C1029y.m2406Ga(r3)
            goto L_0x0041
        L_0x0059:
            boolean r3 = r18.mo6536ag()
            if (r3 != 0) goto L_0x006a
            long r3 = r18.mo6558rg()
            java.lang.CharSequence r3 = com.android.messaging.util.C1485y.m3841a(r3, r8)
            r3.toString()
        L_0x006a:
            r5 = r6
        L_0x006b:
            r3 = r8
            goto L_0x0075
        L_0x006d:
            r5 = 2131820839(0x7f110127, float:1.9274404E38)
            goto L_0x006b
        L_0x0071:
            r5 = 2131820840(0x7f110128, float:1.9274406E38)
            goto L_0x006b
        L_0x0075:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            android.content.Context r9 = r0.mContext
            r10 = 2131820757(0x7f1100d5, float:1.9274238E38)
            java.lang.String r9 = r9.getString(r10)
            java.lang.String r10 = r18.getText()
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            r10 = r10 ^ r7
            boolean r11 = r18.mo6546gg()
            if (r11 == 0) goto L_0x00ad
            if (r10 == 0) goto L_0x0098
            r10 = 2131820791(0x7f1100f7, float:1.9274307E38)
            goto L_0x009b
        L_0x0098:
            r10 = 2131820789(0x7f1100f5, float:1.9274303E38)
        L_0x009b:
            android.content.Context r11 = r0.mContext
            java.lang.Object[] r12 = new java.lang.Object[r7]
            java.lang.String r13 = r18.mo6565xg()
            r12[r8] = r13
            java.lang.String r10 = r11.getString(r10, r12)
            r4.append(r10)
            goto L_0x00bf
        L_0x00ad:
            if (r10 == 0) goto L_0x00b3
            r10 = 2131820906(0x7f11016a, float:1.927454E38)
            goto L_0x00b6
        L_0x00b3:
            r10 = 2131820905(0x7f110169, float:1.9274538E38)
        L_0x00b6:
            android.content.Context r11 = r0.mContext
            java.lang.String r10 = r11.getString(r10)
            r4.append(r10)
        L_0x00bf:
            if (r6 < 0) goto L_0x00c3
            r10 = r7
            goto L_0x00c4
        L_0x00c3:
            r10 = r8
        L_0x00c4:
            r11 = 2131361972(0x7f0a00b4, float:1.8343711E38)
            r12 = 2131362047(0x7f0a00ff, float:1.8343864E38)
            if (r10 == 0) goto L_0x0105
            android.content.Context r1 = r0.mContext
            java.lang.String r1 = r1.getString(r6)
            r2.setTextViewText(r12, r1)
            android.content.Context r1 = r0.mContext
            r6 = 2131820853(0x7f110135, float:1.9274433E38)
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]
            int r12 = r18.mo6530Fg()
            long r12 = (long) r12
            java.lang.String r12 = android.text.format.Formatter.formatFileSize(r1, r12)
            r10[r8] = r12
            android.content.Context r12 = r0.mContext
            long r13 = r18.mo6552lg()
            r15 = 131097(0x20019, float:1.83706E-40)
            java.lang.String r12 = android.text.format.DateUtils.formatDateTime(r12, r13, r15)
            r10[r7] = r12
            java.lang.String r1 = r1.getString(r6, r10)
            r2.setTextViewText(r11, r1)
            r4.append(r9)
            r4.append(r1)
            goto L_0x011a
        L_0x0105:
            boolean r6 = android.text.TextUtils.isEmpty(r17)
            if (r6 != 0) goto L_0x0115
            r2.setTextViewText(r12, r1)
            r4.append(r9)
            r4.append(r1)
            goto L_0x011a
        L_0x0115:
            r1 = 8
            r2.setViewVisibility(r12, r1)
        L_0x011a:
            android.content.Context r1 = r0.mContext
            android.content.res.Resources r1 = r1.getResources()
            java.lang.String r6 = r18.mo6554mg()
            java.lang.String r1 = com.android.messaging.sms.C1029y.m2438b((android.content.res.Resources) r1, (java.lang.String) r6)
            boolean r6 = android.text.TextUtils.isEmpty(r1)
            if (r6 != 0) goto L_0x0134
            r4.append(r9)
            r4.append(r1)
        L_0x0134:
            if (r5 < 0) goto L_0x0168
            android.content.Context r1 = r0.mContext
            java.lang.String r1 = r1.getString(r5)
            android.text.SpannableString r5 = new android.text.SpannableString
            r5.<init>(r1)
            if (r3 == 0) goto L_0x015e
            android.text.style.ForegroundColorSpan r3 = new android.text.style.ForegroundColorSpan
            android.content.Context r6 = r0.mContext
            android.content.res.Resources r6 = r6.getResources()
            r7 = 2131099867(0x7f0600db, float:1.78121E38)
            int r6 = r6.getColor(r7)
            r3.<init>(r6)
            int r1 = r1.length()
            r6 = 33
            r5.setSpan(r3, r8, r1, r6)
        L_0x015e:
            r2.setTextViewText(r11, r5)
            r4.append(r9)
            r4.append(r5)
            goto L_0x0176
        L_0x0168:
            r4.append(r9)
            long r5 = r18.mo6558rg()
            java.lang.CharSequence r1 = com.android.messaging.util.C1485y.m3841a(r5, r8)
            r4.append(r1)
        L_0x0176:
            boolean r1 = r18.mo6553mf()
            if (r1 == 0) goto L_0x01c8
            java.util.List r1 = r18.mo6534_f()
            java.util.Iterator r1 = r1.iterator()
        L_0x0184:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x01c8
            java.lang.Object r3 = r1.next()
            com.android.messaging.datamodel.data.MessagePartData r3 = (com.android.messaging.datamodel.data.MessagePartData) r3
            boolean r5 = r3.mo6304fh()
            if (r5 == 0) goto L_0x019a
            r3 = 2131820706(0x7f1100a2, float:1.9274135E38)
            goto L_0x01b9
        L_0x019a:
            boolean r5 = r3.mo6315ih()
            if (r5 == 0) goto L_0x01a4
            r3 = 2131820708(0x7f1100a4, float:1.9274139E38)
            goto L_0x01b9
        L_0x01a4:
            boolean r5 = r3.mo6302eh()
            if (r5 == 0) goto L_0x01ae
            r3 = 2131820705(0x7f1100a1, float:1.9274132E38)
            goto L_0x01b9
        L_0x01ae:
            boolean r3 = r3.mo6314hh()
            if (r3 == 0) goto L_0x01b8
            r3 = 2131820707(0x7f1100a3, float:1.9274137E38)
            goto L_0x01b9
        L_0x01b8:
            r3 = r8
        L_0x01b9:
            if (r3 <= 0) goto L_0x0184
            r4.append(r9)
            android.content.Context r5 = r0.mContext
            java.lang.String r3 = r5.getString(r3)
            r4.append(r3)
            goto L_0x0184
        L_0x01c8:
            boolean r0 = r18.mo6546gg()
            if (r0 == 0) goto L_0x01d2
            r0 = 2131362218(0x7f0a01aa, float:1.834421E38)
            goto L_0x01d5
        L_0x01d2:
            r0 = 2131362219(0x7f0a01ab, float:1.8344212E38)
        L_0x01d5:
            r2.setContentDescription(r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.widget.C1493e.m3902a(java.lang.String, com.android.messaging.datamodel.data.s, android.widget.RemoteViews):void");
    }

    /* renamed from: j */
    private Bitmap m3903j(MessagePartData messagePartData) {
        C0849L l;
        if (messagePartData.mo6304fh()) {
            l = new C0841D(messagePartData, 400, 400, true);
        } else {
            if (messagePartData.mo6315ih()) {
                l = new C0842E(messagePartData);
            }
            return null;
        }
        C0881u uVar = (C0881u) C0840C.get().mo6082b(l.mo6084n(this.mContext));
        if (uVar == null || uVar.getBitmap() == null) {
            C0881u uVar2 = this.f2368Nj;
            if (uVar2 != null) {
                uVar2.release();
            }
            this.f2368Nj = null;
            return null;
        }
        C0881u uVar3 = this.f2368Nj;
        if (uVar3 != uVar) {
            if (uVar3 != null) {
                uVar3.release();
            }
            this.f2368Nj = null;
            this.f2368Nj = uVar;
        }
        return Bitmap.createBitmap(uVar.getBitmap());
    }

    /* access modifiers changed from: protected */
    /* renamed from: Mk */
    public Cursor mo8252Mk() {
        if (TextUtils.isEmpty(this.f2367Ka)) {
            C1430e.m3630w("MessagingAppWidget", "doQuery no conversation id");
            return null;
        }
        Uri m = MessagingContentProvider.m1268m(this.f2367Ka);
        if (m != null) {
            StringBuilder Pa = C0632a.m1011Pa("doQuery uri: ");
            Pa.append(m.toString());
            C1430e.m3630w("MessagingAppWidget", Pa.toString());
        }
        return this.mContext.getContentResolver().query(m, C0936s.getProjection(), (String) null, (String[]) null, (String) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Nk */
    public int mo8253Nk() {
        return R.layout.widget_conversation;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ok */
    public RemoteViews mo8268Ok() {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "getViewMoreConversationsView");
        }
        RemoteViews remoteViews = new RemoteViews(this.mContext.getPackageName(), R.layout.widget_loading);
        remoteViews.setTextViewText(R.id.loading_text, this.mContext.getText(R.string.view_more_messages));
        remoteViews.setOnClickFillInIntent(R.id.widget_loading, C1040Ea.get().mo6965b(this.mContext, this.f2367Ka, (MessageData) null));
        return remoteViews;
    }

    public RemoteViews getLoadingView() {
        RemoteViews remoteViews = new RemoteViews(this.mContext.getPackageName(), R.layout.widget_loading);
        remoteViews.setTextViewText(R.id.loading_text, this.mContext.getText(R.string.loading_messages));
        return remoteViews;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0131, code lost:
        r1 = r3.getContentUri();
        r6.setViewVisibility(com.android.messaging.R.id.attachmentFrame, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x013f, code lost:
        if (r3.mo6315ih() == false) goto L_0x0143;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0141, code lost:
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0143, code lost:
        r9 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0145, code lost:
        r6.setViewVisibility(com.android.messaging.R.id.playButton, r9);
        r6.setImageViewBitmap(com.android.messaging.R.id.attachment, m3903j(r3));
        r7.putExtra("attachment_uri", r1.toString());
        r7.putExtra("attachment_type", r3.getContentType());
     */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x016f  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x01b9  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01c8  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01ca  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01ea  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01f4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.widget.RemoteViews getViewAt(int r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            java.lang.Object r2 = com.android.messaging.widget.C1489a.f2361kM
            monitor-enter(r2)
            android.database.Cursor r3 = r0.mCursor     // Catch:{ all -> 0x0227 }
            if (r3 == 0) goto L_0x0221
            boolean r3 = r0.f2362hM     // Catch:{ all -> 0x0227 }
            if (r3 == 0) goto L_0x0013
            if (r1 != 0) goto L_0x0013
            goto L_0x0221
        L_0x0013:
            int r3 = r17.getCount()     // Catch:{ all -> 0x0227 }
            int r3 = r3 - r1
            r4 = 1
            int r3 = r3 - r4
            android.database.Cursor r5 = r0.mCursor     // Catch:{ all -> 0x0227 }
            boolean r5 = r5.moveToPosition(r3)     // Catch:{ all -> 0x0227 }
            if (r5 != 0) goto L_0x003e
            java.lang.String r1 = "MessagingAppWidget"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0227 }
            r4.<init>()     // Catch:{ all -> 0x0227 }
            java.lang.String r5 = "Failed to move to position: "
            r4.append(r5)     // Catch:{ all -> 0x0227 }
            r4.append(r3)     // Catch:{ all -> 0x0227 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x0227 }
            com.android.messaging.util.C1430e.m3630w(r1, r3)     // Catch:{ all -> 0x0227 }
            android.widget.RemoteViews r0 = r17.mo8268Ok()     // Catch:{ all -> 0x0227 }
            monitor-exit(r2)     // Catch:{ all -> 0x0227 }
            return r0
        L_0x003e:
            com.android.messaging.datamodel.data.s r5 = new com.android.messaging.datamodel.data.s     // Catch:{ all -> 0x0227 }
            r5.<init>()     // Catch:{ all -> 0x0227 }
            android.database.Cursor r6 = r0.mCursor     // Catch:{ all -> 0x0227 }
            r5.mo6538c(r6)     // Catch:{ all -> 0x0227 }
            android.widget.RemoteViews r6 = new android.widget.RemoteViews     // Catch:{ all -> 0x0227 }
            android.content.Context r7 = r0.mContext     // Catch:{ all -> 0x0227 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ all -> 0x0227 }
            boolean r8 = r5.mo6546gg()     // Catch:{ all -> 0x0227 }
            if (r8 == 0) goto L_0x005a
            r8 = 2131558547(0x7f0d0093, float:1.8742413E38)
            goto L_0x005d
        L_0x005a:
            r8 = 2131558548(0x7f0d0094, float:1.8742415E38)
        L_0x005d:
            r6.<init>(r7, r8)     // Catch:{ all -> 0x0227 }
            r7 = 2131361972(0x7f0a00b4, float:1.8343711E38)
            long r8 = r5.mo6558rg()     // Catch:{ all -> 0x0227 }
            r10 = 0
            java.lang.CharSequence r8 = com.android.messaging.util.C1485y.m3841a(r8, r10)     // Catch:{ all -> 0x0227 }
            java.lang.CharSequence r8 = r0.mo8254a(r8, r10)     // Catch:{ all -> 0x0227 }
            r6.setTextViewText(r7, r8)     // Catch:{ all -> 0x0227 }
            com.android.messaging.ui.Ea r7 = com.android.messaging.p041ui.C1040Ea.get()     // Catch:{ all -> 0x0227 }
            android.content.Context r8 = r0.mContext     // Catch:{ all -> 0x0227 }
            java.lang.String r9 = r0.f2367Ka     // Catch:{ all -> 0x0227 }
            r11 = 0
            android.content.Intent r7 = r7.mo6965b((android.content.Context) r8, (java.lang.String) r9, (com.android.messaging.datamodel.data.MessageData) r11)     // Catch:{ all -> 0x0227 }
            r8 = 2131361888(0x7f0a0060, float:1.8343541E38)
            r9 = 8
            r6.setViewVisibility(r8, r9)     // Catch:{ all -> 0x0227 }
            android.database.Cursor r12 = r0.mCursor     // Catch:{ all -> 0x0227 }
            int r12 = r12.getCount()     // Catch:{ all -> 0x0227 }
            r13 = 25
            if (r12 <= r13) goto L_0x0096
            int r14 = r12 + -25
            int r14 = r14 + r1
            goto L_0x0097
        L_0x0096:
            r14 = r1
        L_0x0097:
            java.lang.String r15 = "MessagingAppWidget"
            r4 = 2
            boolean r15 = android.util.Log.isLoggable(r15, r4)     // Catch:{ all -> 0x0227 }
            if (r15 == 0) goto L_0x00d6
            java.lang.String r15 = "MessagingAppWidget"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0227 }
            r9.<init>()     // Catch:{ all -> 0x0227 }
            java.lang.String r11 = "getViewAt position: "
            r9.append(r11)     // Catch:{ all -> 0x0227 }
            r9.append(r1)     // Catch:{ all -> 0x0227 }
            java.lang.String r1 = " computed position: "
            r9.append(r1)     // Catch:{ all -> 0x0227 }
            r9.append(r3)     // Catch:{ all -> 0x0227 }
            java.lang.String r1 = " scrollToPosition: "
            r9.append(r1)     // Catch:{ all -> 0x0227 }
            r9.append(r14)     // Catch:{ all -> 0x0227 }
            java.lang.String r1 = " cursorCount: "
            r9.append(r1)     // Catch:{ all -> 0x0227 }
            r9.append(r12)     // Catch:{ all -> 0x0227 }
            java.lang.String r1 = " MAX_ITEMS_TO_SHOW: "
            r9.append(r1)     // Catch:{ all -> 0x0227 }
            r9.append(r13)     // Catch:{ all -> 0x0227 }
            java.lang.String r1 = r9.toString()     // Catch:{ all -> 0x0227 }
            com.android.messaging.util.C1430e.m3628v(r15, r1)     // Catch:{ all -> 0x0227 }
        L_0x00d6:
            java.lang.String r1 = "message_position"
            r7.putExtra(r1, r14)     // Catch:{ all -> 0x0227 }
            boolean r1 = r5.mo6553mf()     // Catch:{ all -> 0x0227 }
            if (r1 == 0) goto L_0x0164
            java.util.List r1 = r5.mo6534_f()     // Catch:{ all -> 0x0227 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0227 }
        L_0x00e9:
            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x0227 }
            if (r3 == 0) goto L_0x0164
            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x0227 }
            com.android.messaging.datamodel.data.MessagePartData r3 = (com.android.messaging.datamodel.data.MessagePartData) r3     // Catch:{ all -> 0x0227 }
            boolean r9 = r3.mo6315ih()     // Catch:{ all -> 0x0227 }
            if (r9 == 0) goto L_0x0109
            boolean r9 = com.android.messaging.util.C1452ha.m3728Qj()     // Catch:{ all -> 0x0227 }
            if (r9 != 0) goto L_0x0107
            boolean r9 = r5.mo6546gg()     // Catch:{ all -> 0x0227 }
            if (r9 != 0) goto L_0x0109
        L_0x0107:
            r9 = 1
            goto L_0x010a
        L_0x0109:
            r9 = r10
        L_0x010a:
            boolean r11 = r3.mo6304fh()     // Catch:{ all -> 0x0227 }
            if (r11 != 0) goto L_0x0131
            if (r9 == 0) goto L_0x0113
            goto L_0x0131
        L_0x0113:
            boolean r9 = r3.mo6315ih()     // Catch:{ all -> 0x0227 }
            if (r9 == 0) goto L_0x011d
            r1 = 2131820708(0x7f1100a4, float:1.9274139E38)
            goto L_0x0165
        L_0x011d:
            boolean r9 = r3.mo6302eh()     // Catch:{ all -> 0x0227 }
            if (r9 == 0) goto L_0x0127
            r1 = 2131820705(0x7f1100a1, float:1.9274132E38)
            goto L_0x0165
        L_0x0127:
            boolean r3 = r3.mo6314hh()     // Catch:{ all -> 0x0227 }
            if (r3 == 0) goto L_0x00e9
            r1 = 2131820707(0x7f1100a3, float:1.9274137E38)
            goto L_0x0165
        L_0x0131:
            android.net.Uri r1 = r3.getContentUri()     // Catch:{ all -> 0x0227 }
            r6.setViewVisibility(r8, r10)     // Catch:{ all -> 0x0227 }
            r8 = 2131362098(0x7f0a0132, float:1.8343967E38)
            boolean r9 = r3.mo6315ih()     // Catch:{ all -> 0x0227 }
            if (r9 == 0) goto L_0x0143
            r9 = r10
            goto L_0x0145
        L_0x0143:
            r9 = 8
        L_0x0145:
            r6.setViewVisibility(r8, r9)     // Catch:{ all -> 0x0227 }
            r8 = 2131361887(0x7f0a005f, float:1.834354E38)
            android.graphics.Bitmap r9 = r0.m3903j(r3)     // Catch:{ all -> 0x0227 }
            r6.setImageViewBitmap(r8, r9)     // Catch:{ all -> 0x0227 }
            java.lang.String r8 = "attachment_uri"
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0227 }
            r7.putExtra(r8, r1)     // Catch:{ all -> 0x0227 }
            java.lang.String r1 = "attachment_type"
            java.lang.String r3 = r3.getContentType()     // Catch:{ all -> 0x0227 }
            r7.putExtra(r1, r3)     // Catch:{ all -> 0x0227 }
        L_0x0164:
            r1 = r10
        L_0x0165:
            boolean r3 = r5.mo6546gg()     // Catch:{ all -> 0x0227 }
            if (r3 == 0) goto L_0x016f
            r3 = 2131362218(0x7f0a01aa, float:1.834421E38)
            goto L_0x0172
        L_0x016f:
            r3 = 2131362219(0x7f0a01ab, float:1.8344212E38)
        L_0x0172:
            r6.setOnClickFillInIntent(r3, r7)     // Catch:{ all -> 0x0227 }
            boolean r3 = com.android.messaging.util.C1464na.m3754Uj()     // Catch:{ all -> 0x0227 }
            if (r3 == 0) goto L_0x01b5
            android.appwidget.AppWidgetManager r3 = r0.f2363iM     // Catch:{ all -> 0x0227 }
            int r7 = r0.mAppWidgetId     // Catch:{ all -> 0x0227 }
            android.os.Bundle r3 = r3.getAppWidgetOptions(r7)     // Catch:{ all -> 0x0227 }
            java.lang.String r7 = "MessagingAppWidget"
            boolean r4 = android.util.Log.isLoggable(r7, r4)     // Catch:{ all -> 0x0227 }
            if (r4 == 0) goto L_0x01a7
            java.lang.String r4 = "MessagingAppWidget"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0227 }
            r7.<init>()     // Catch:{ all -> 0x0227 }
            java.lang.String r8 = "getViewAt BugleWidgetProvider.WIDGET_SIZE_KEY: "
            r7.append(r8)     // Catch:{ all -> 0x0227 }
            java.lang.String r8 = "widgetSizeKey"
            int r8 = r3.getInt(r8)     // Catch:{ all -> 0x0227 }
            r7.append(r8)     // Catch:{ all -> 0x0227 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0227 }
            com.android.messaging.util.C1430e.m3628v(r4, r7)     // Catch:{ all -> 0x0227 }
        L_0x01a7:
            java.lang.String r4 = "widgetSizeKey"
            int r3 = r3.getInt(r4)     // Catch:{ all -> 0x0227 }
            if (r3 != 0) goto L_0x01b1
            r4 = 1
            goto L_0x01b2
        L_0x01b1:
            r4 = r10
        L_0x01b2:
            r16 = r4
            goto L_0x01b7
        L_0x01b5:
            r16 = 1
        L_0x01b7:
            if (r16 == 0) goto L_0x01bb
            r3 = r10
            goto L_0x01bd
        L_0x01bb:
            r3 = 8
        L_0x01bd:
            r4 = 2131361900(0x7f0a006c, float:1.8343565E38)
            r6.setViewVisibility(r4, r3)     // Catch:{ all -> 0x0227 }
            r3 = 2131361899(0x7f0a006b, float:1.8343563E38)
            if (r16 == 0) goto L_0x01ca
            r7 = r10
            goto L_0x01cc
        L_0x01ca:
            r7 = 8
        L_0x01cc:
            r6.setViewVisibility(r3, r7)     // Catch:{ all -> 0x0227 }
            android.net.Uri r3 = r5.mo6526Bg()     // Catch:{ all -> 0x0227 }
            java.lang.String r7 = r5.mo6567zg()     // Catch:{ all -> 0x0227 }
            java.lang.String r8 = r5.mo6525Ag()     // Catch:{ all -> 0x0227 }
            java.lang.String r9 = r5.mo6562ug()     // Catch:{ all -> 0x0227 }
            android.net.Uri r3 = com.android.messaging.util.C1426c.m3598a((android.net.Uri) r3, (java.lang.CharSequence) r7, (java.lang.String) r8, (java.lang.String) r9)     // Catch:{ all -> 0x0227 }
            if (r16 == 0) goto L_0x01ea
            android.graphics.Bitmap r11 = r0.mo8251F(r3)     // Catch:{ all -> 0x0227 }
            goto L_0x01eb
        L_0x01ea:
            r11 = 0
        L_0x01eb:
            r6.setImageViewBitmap(r4, r11)     // Catch:{ all -> 0x0227 }
            java.lang.String r3 = r5.getText()     // Catch:{ all -> 0x0227 }
            if (r1 == 0) goto L_0x0216
            android.content.Context r4 = r0.mContext     // Catch:{ all -> 0x0227 }
            java.lang.String r1 = r4.getString(r1)     // Catch:{ all -> 0x0227 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0227 }
            if (r4 != 0) goto L_0x0215
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0227 }
            r4.<init>()     // Catch:{ all -> 0x0227 }
            r4.append(r3)     // Catch:{ all -> 0x0227 }
            r3 = 10
            r4.append(r3)     // Catch:{ all -> 0x0227 }
            r4.append(r1)     // Catch:{ all -> 0x0227 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x0227 }
            goto L_0x0216
        L_0x0215:
            r3 = r1
        L_0x0216:
            r1 = 2131362047(0x7f0a00ff, float:1.8343864E38)
            r6.setViewVisibility(r1, r10)     // Catch:{ all -> 0x0227 }
            r0.m3902a(r3, r5, r6)     // Catch:{ all -> 0x0227 }
            monitor-exit(r2)     // Catch:{ all -> 0x0227 }
            return r6
        L_0x0221:
            android.widget.RemoteViews r0 = r17.mo8268Ok()     // Catch:{ all -> 0x0227 }
            monitor-exit(r2)     // Catch:{ all -> 0x0227 }
            return r0
        L_0x0227:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0227 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.widget.C1493e.getViewAt(int):android.widget.RemoteViews");
    }

    public int getViewTypeCount() {
        return 3;
    }

    public void onCreate() {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "onCreate");
        }
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "onCreate");
        }
        if (!WidgetConversationProvider.m3890l(this.mAppWidgetId)) {
            WidgetConversationProvider.m3887b(this.mContext, this.mAppWidgetId);
        }
    }
}
