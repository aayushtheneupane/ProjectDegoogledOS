package com.android.messaging.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import com.android.messaging.R;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.datamodel.data.C0934q;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1481w;

/* renamed from: com.android.messaging.widget.c */
class C1491c extends C1489a {
    public C1491c(Context context, Intent intent) {
        super(context, intent);
    }

    /* renamed from: c */
    private String m3898c(C0934q qVar) {
        String Jf = qVar.mo6511Yf() ? qVar.mo6494Jf() : qVar.getSnippetText();
        String Hf = qVar.mo6511Yf() ? qVar.mo6492Hf() : qVar.mo6510Xf();
        if (!TextUtils.isEmpty(Jf)) {
            return Jf;
        }
        Resources resources = this.mContext.getResources();
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

    /* access modifiers changed from: protected */
    /* renamed from: Mk */
    public Cursor mo8252Mk() {
        return this.mContext.getContentResolver().query(MessagingContentProvider.f1020Bb, C0934q.f1249Wu, "(archive_status = 0)", (String[]) null, "sort_timestamp DESC");
    }

    /* access modifiers changed from: protected */
    /* renamed from: Nk */
    public int mo8253Nk() {
        return R.layout.widget_conversation_list;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Ok */
    public RemoteViews mo8263Ok() {
        if (Log.isLoggable("MessagingAppWidget", 2)) {
            C1430e.m3628v("MessagingAppWidget", "getViewMoreItemsView");
        }
        RemoteViews remoteViews = new RemoteViews(this.mContext.getPackageName(), R.layout.widget_loading);
        remoteViews.setTextViewText(R.id.loading_text, this.mContext.getText(R.string.view_more_conversations));
        Intent intent = new Intent();
        intent.putExtra("goto_conv_list", true);
        remoteViews.setOnClickFillInIntent(R.id.widget_loading, intent);
        return remoteViews;
    }

    public RemoteViews getLoadingView() {
        RemoteViews remoteViews = new RemoteViews(this.mContext.getPackageName(), R.layout.widget_loading);
        remoteViews.setTextViewText(R.id.loading_text, this.mContext.getText(R.string.loading_conversations));
        return remoteViews;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.widget.RemoteViews getViewAt(int r15) {
        /*
            r14 = this;
            r0 = 2
            java.lang.String r1 = "MessagingAppWidget"
            boolean r1 = android.util.Log.isLoggable(r1, r0)
            if (r1 == 0) goto L_0x001f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "getViewAt position: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "MessagingAppWidget"
            com.android.messaging.util.C1430e.m3628v(r2, r1)
        L_0x001f:
            java.lang.Object r1 = com.android.messaging.widget.C1489a.f2361kM
            monitor-enter(r1)
            android.database.Cursor r2 = r14.mCursor     // Catch:{ all -> 0x01f2 }
            if (r2 == 0) goto L_0x01ec
            boolean r2 = r14.f2362hM     // Catch:{ all -> 0x01f2 }
            if (r2 == 0) goto L_0x0032
            int r2 = r14.getItemCount()     // Catch:{ all -> 0x01f2 }
            if (r15 < r2) goto L_0x0032
            goto L_0x01ec
        L_0x0032:
            android.database.Cursor r2 = r14.mCursor     // Catch:{ all -> 0x01f2 }
            boolean r2 = r2.moveToPosition(r15)     // Catch:{ all -> 0x01f2 }
            if (r2 != 0) goto L_0x0056
            java.lang.String r0 = "MessagingAppWidget"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01f2 }
            r2.<init>()     // Catch:{ all -> 0x01f2 }
            java.lang.String r3 = "Failed to move to position: "
            r2.append(r3)     // Catch:{ all -> 0x01f2 }
            r2.append(r15)     // Catch:{ all -> 0x01f2 }
            java.lang.String r15 = r2.toString()     // Catch:{ all -> 0x01f2 }
            com.android.messaging.util.C1430e.m3630w(r0, r15)     // Catch:{ all -> 0x01f2 }
            android.widget.RemoteViews r14 = r14.mo8263Ok()     // Catch:{ all -> 0x01f2 }
            monitor-exit(r1)     // Catch:{ all -> 0x01f2 }
            return r14
        L_0x0056:
            com.android.messaging.datamodel.data.q r15 = new com.android.messaging.datamodel.data.q     // Catch:{ all -> 0x01f2 }
            r15.<init>()     // Catch:{ all -> 0x01f2 }
            android.database.Cursor r2 = r14.mCursor     // Catch:{ all -> 0x01f2 }
            r15.mo6513c(r2)     // Catch:{ all -> 0x01f2 }
            android.widget.RemoteViews r2 = new android.widget.RemoteViews     // Catch:{ all -> 0x01f2 }
            android.content.Context r3 = r14.mContext     // Catch:{ all -> 0x01f2 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ all -> 0x01f2 }
            r4 = 2131558545(0x7f0d0091, float:1.8742409E38)
            r2.<init>(r3, r4)     // Catch:{ all -> 0x01f2 }
            boolean r3 = r15.mo6501Qf()     // Catch:{ all -> 0x01f2 }
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L_0x0078
            r3 = r4
            goto L_0x0079
        L_0x0078:
            r3 = r5
        L_0x0079:
            android.content.Context r6 = r14.mContext     // Catch:{ all -> 0x01f2 }
            android.content.res.Resources r6 = r6.getResources()     // Catch:{ all -> 0x01f2 }
            com.android.messaging.util.sa r7 = com.android.messaging.util.C1474sa.getDefault()     // Catch:{ all -> 0x01f2 }
            boolean r7 = r7.mo8228kk()     // Catch:{ all -> 0x01f2 }
            boolean r8 = r15.mo6502Rf()     // Catch:{ all -> 0x01f2 }
            if (r8 == 0) goto L_0x0095
            r8 = 2131820840(0x7f110128, float:1.9274406E38)
            java.lang.String r8 = r6.getString(r8)     // Catch:{ all -> 0x01f2 }
            goto L_0x00a1
        L_0x0095:
            long r8 = r15.getTimestamp()     // Catch:{ all -> 0x01f2 }
            java.lang.CharSequence r8 = com.android.messaging.util.C1485y.m3841a(r8, r4)     // Catch:{ all -> 0x01f2 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x01f2 }
        L_0x00a1:
            r9 = 2131361972(0x7f0a00b4, float:1.8343711E38)
            java.lang.CharSequence r8 = r14.mo8254a(r8, r3)     // Catch:{ all -> 0x01f2 }
            r2.setTextViewText(r9, r8)     // Catch:{ all -> 0x01f2 }
            r8 = 2131362014(0x7f0a00de, float:1.8343797E38)
            java.lang.String r9 = r15.getName()     // Catch:{ all -> 0x01f2 }
            java.lang.CharSequence r9 = r14.mo8254a(r9, r3)     // Catch:{ all -> 0x01f2 }
            r2.setTextViewText(r8, r9)     // Catch:{ all -> 0x01f2 }
            com.android.messaging.ui.Ea r8 = com.android.messaging.p041ui.C1040Ea.get()     // Catch:{ all -> 0x01f2 }
            android.content.Context r9 = r14.mContext     // Catch:{ all -> 0x01f2 }
            java.lang.String r10 = r15.mo6505Ue()     // Catch:{ all -> 0x01f2 }
            r11 = 0
            android.content.Intent r8 = r8.mo6965b((android.content.Context) r9, (java.lang.String) r10, (com.android.messaging.datamodel.data.MessageData) r11)     // Catch:{ all -> 0x01f2 }
            r9 = 2131362213(0x7f0a01a5, float:1.83442E38)
            r2.setOnClickFillInIntent(r9, r8)     // Catch:{ all -> 0x01f2 }
            boolean r8 = com.android.messaging.util.C1464na.m3754Uj()     // Catch:{ all -> 0x01f2 }
            if (r8 == 0) goto L_0x010b
            android.appwidget.AppWidgetManager r8 = r14.f2363iM     // Catch:{ all -> 0x01f2 }
            int r10 = r14.mAppWidgetId     // Catch:{ all -> 0x01f2 }
            android.os.Bundle r8 = r8.getAppWidgetOptions(r10)     // Catch:{ all -> 0x01f2 }
            java.lang.String r10 = "MessagingAppWidget"
            boolean r10 = android.util.Log.isLoggable(r10, r0)     // Catch:{ all -> 0x01f2 }
            if (r10 == 0) goto L_0x0100
            java.lang.String r10 = "MessagingAppWidget"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x01f2 }
            r12.<init>()     // Catch:{ all -> 0x01f2 }
            java.lang.String r13 = "getViewAt BugleWidgetProvider.WIDGET_SIZE_KEY: "
            r12.append(r13)     // Catch:{ all -> 0x01f2 }
            java.lang.String r13 = "widgetSizeKey"
            int r13 = r8.getInt(r13)     // Catch:{ all -> 0x01f2 }
            r12.append(r13)     // Catch:{ all -> 0x01f2 }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x01f2 }
            com.android.messaging.util.C1430e.m3628v(r10, r12)     // Catch:{ all -> 0x01f2 }
        L_0x0100:
            java.lang.String r10 = "widgetSizeKey"
            int r8 = r8.getInt(r10)     // Catch:{ all -> 0x01f2 }
            if (r8 != 0) goto L_0x0109
            goto L_0x010b
        L_0x0109:
            r8 = r5
            goto L_0x010c
        L_0x010b:
            r8 = r4
        L_0x010c:
            r10 = 8
            if (r8 == 0) goto L_0x0112
            r12 = r5
            goto L_0x0113
        L_0x0112:
            r12 = r10
        L_0x0113:
            r13 = 2131361900(0x7f0a006c, float:1.8343565E38)
            r2.setViewVisibility(r13, r12)     // Catch:{ all -> 0x01f2 }
            java.lang.String r12 = r15.getIcon()     // Catch:{ all -> 0x01f2 }
            if (r12 == 0) goto L_0x0128
            java.lang.String r12 = r15.getIcon()     // Catch:{ all -> 0x01f2 }
            android.net.Uri r12 = android.net.Uri.parse(r12)     // Catch:{ all -> 0x01f2 }
            goto L_0x0129
        L_0x0128:
            r12 = r11
        L_0x0129:
            if (r8 == 0) goto L_0x012f
            android.graphics.Bitmap r11 = r14.mo8251F(r12)     // Catch:{ all -> 0x01f2 }
        L_0x012f:
            r2.setImageViewBitmap(r13, r11)     // Catch:{ all -> 0x01f2 }
            boolean r11 = r15.mo6498Nf()     // Catch:{ all -> 0x01f2 }
            if (r11 == 0) goto L_0x0142
            boolean r11 = r15.mo6499Of()     // Catch:{ all -> 0x01f2 }
            if (r11 != 0) goto L_0x0142
            if (r7 == 0) goto L_0x0142
            r11 = r4
            goto L_0x0143
        L_0x0142:
            r11 = r5
        L_0x0143:
            boolean r12 = r15.mo6511Yf()     // Catch:{ all -> 0x01f2 }
            if (r12 == 0) goto L_0x014c
            if (r7 == 0) goto L_0x014c
            goto L_0x014d
        L_0x014c:
            r4 = r5
        L_0x014d:
            r7 = 2131361954(0x7f0a00a2, float:1.8343675E38)
            if (r11 == 0) goto L_0x0156
            if (r8 == 0) goto L_0x0156
            r8 = r5
            goto L_0x0157
        L_0x0156:
            r8 = r10
        L_0x0157:
            r2.setViewVisibility(r7, r8)     // Catch:{ all -> 0x01f2 }
            r7 = 2131361995(0x7f0a00cb, float:1.8343758E38)
            r8 = 2131362150(0x7f0a0166, float:1.8344072E38)
            if (r11 != 0) goto L_0x0177
            if (r4 == 0) goto L_0x0165
            goto L_0x0177
        L_0x0165:
            r2.setViewVisibility(r7, r10)     // Catch:{ all -> 0x01f2 }
            r2.setViewVisibility(r8, r5)     // Catch:{ all -> 0x01f2 }
            java.lang.String r0 = r14.m3898c(r15)     // Catch:{ all -> 0x01f2 }
            java.lang.CharSequence r0 = r14.mo8254a(r0, r3)     // Catch:{ all -> 0x01f2 }
            r2.setTextViewText(r8, r0)     // Catch:{ all -> 0x01f2 }
            goto L_0x01d8
        L_0x0177:
            r2.setViewVisibility(r8, r10)     // Catch:{ all -> 0x01f2 }
            r2.setViewVisibility(r7, r5)     // Catch:{ all -> 0x01f2 }
            r3 = 2131361996(0x7f0a00cc, float:1.834376E38)
            java.lang.String r7 = r14.m3898c(r15)     // Catch:{ all -> 0x01f2 }
            r2.setTextViewText(r3, r7)     // Catch:{ all -> 0x01f2 }
            r3 = 2131361997(0x7f0a00cd, float:1.8343762E38)
            if (r4 == 0) goto L_0x01bd
            r4 = 2131820702(0x7f11009e, float:1.9274126E38)
            java.lang.String r4 = r6.getString(r4)     // Catch:{ all -> 0x01f2 }
            android.text.SpannableStringBuilder r7 = new android.text.SpannableStringBuilder     // Catch:{ all -> 0x01f2 }
            r7.<init>(r4)     // Catch:{ all -> 0x01f2 }
            android.text.style.StyleSpan r8 = new android.text.style.StyleSpan     // Catch:{ all -> 0x01f2 }
            r8.<init>(r0)     // Catch:{ all -> 0x01f2 }
            int r0 = r4.length()     // Catch:{ all -> 0x01f2 }
            r10 = 33
            r7.setSpan(r8, r5, r0, r10)     // Catch:{ all -> 0x01f2 }
            android.text.style.ForegroundColorSpan r0 = new android.text.style.ForegroundColorSpan     // Catch:{ all -> 0x01f2 }
            r8 = 2131099879(0x7f0600e7, float:1.7812124E38)
            int r6 = r6.getColor(r8)     // Catch:{ all -> 0x01f2 }
            r0.<init>(r6)     // Catch:{ all -> 0x01f2 }
            int r4 = r4.length()     // Catch:{ all -> 0x01f2 }
            r7.setSpan(r0, r5, r4, r10)     // Catch:{ all -> 0x01f2 }
            r2.setTextViewText(r3, r7)     // Catch:{ all -> 0x01f2 }
            goto L_0x01d8
        L_0x01bd:
            r0 = 2131820833(0x7f110121, float:1.9274392E38)
            boolean r4 = r15.mo6500Pf()     // Catch:{ all -> 0x01f2 }
            if (r4 == 0) goto L_0x01d1
            r15.getMessageStatus()     // Catch:{ all -> 0x01f2 }
            int r0 = r15.mo6503Sf()     // Catch:{ all -> 0x01f2 }
            int r0 = com.android.messaging.sms.C1029y.m2406Ga(r0)     // Catch:{ all -> 0x01f2 }
        L_0x01d1:
            java.lang.String r0 = r6.getString(r0)     // Catch:{ all -> 0x01f2 }
            r2.setTextViewText(r3, r0)     // Catch:{ all -> 0x01f2 }
        L_0x01d8:
            android.content.Context r14 = r14.mContext     // Catch:{ all -> 0x01f2 }
            android.content.res.Resources r14 = r14.getResources()     // Catch:{ all -> 0x01f2 }
            android.text.TextPaint r0 = new android.text.TextPaint     // Catch:{ all -> 0x01f2 }
            r0.<init>()     // Catch:{ all -> 0x01f2 }
            java.lang.String r14 = com.android.messaging.p041ui.conversationlist.ConversationListItemView.m3091a((android.content.res.Resources) r14, (com.android.messaging.datamodel.data.C0934q) r15)     // Catch:{ all -> 0x01f2 }
            r2.setContentDescription(r9, r14)     // Catch:{ all -> 0x01f2 }
            monitor-exit(r1)     // Catch:{ all -> 0x01f2 }
            return r2
        L_0x01ec:
            android.widget.RemoteViews r14 = r14.mo8263Ok()     // Catch:{ all -> 0x01f2 }
            monitor-exit(r1)     // Catch:{ all -> 0x01f2 }
            return r14
        L_0x01f2:
            r14 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x01f2 }
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.widget.C1491c.getViewAt(int):android.widget.RemoteViews");
    }

    public int getViewTypeCount() {
        return 2;
    }
}
