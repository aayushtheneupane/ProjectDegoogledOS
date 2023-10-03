package com.android.messaging.datamodel.data;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import com.android.messaging.datamodel.C0837b;
import com.android.messaging.datamodel.MessagingContentProvider;
import com.android.messaging.util.C1424b;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.data.h */
class C0925h implements LoaderManager.LoaderCallbacks {
    final /* synthetic */ C0931n this$0;

    /* synthetic */ C0925h(C0931n nVar, C0923f fVar) {
        this.this$0 = nVar;
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        C1424b.equals(2, i);
        String string = bundle.getString("bindingId");
        if (this.this$0.mo5926W(string)) {
            C0837b bVar = new C0837b(string, this.this$0.mContext, MessagingContentProvider.m1268m(this.this$0.f1236Ka), C0936s.getProjection(), (String) null, (String[]) null, (String) null);
            long unused = this.this$0.f1234Az = -1;
            int unused2 = this.this$0.f1237Ux = -1;
            return bVar;
        }
        C0632a.m1021a(C0632a.m1011Pa("Creating messages loader after unbinding mConversationId = "), this.this$0.f1236Ka, "bugle_datamodel");
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: com.android.messaging.datamodel.data.k} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: com.android.messaging.datamodel.data.s} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.android.messaging.datamodel.data.k} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: com.android.messaging.datamodel.data.s} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: com.android.messaging.datamodel.data.s} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.android.messaging.datamodel.data.k} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: com.android.messaging.datamodel.data.s} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: com.android.messaging.datamodel.data.k} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: com.android.messaging.datamodel.data.k} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: com.android.messaging.datamodel.data.s} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: com.android.messaging.datamodel.data.s} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLoadFinished(android.content.Loader r12, java.lang.Object r13) {
        /*
            r11 = this;
            android.database.Cursor r13 = (android.database.Cursor) r13
            com.android.messaging.datamodel.b r12 = (com.android.messaging.datamodel.C0837b) r12
            com.android.messaging.datamodel.data.n r0 = r11.this$0
            java.lang.String r12 = r12.getBindingId()
            boolean r12 = r0.mo5926W(r12)
            if (r12 == 0) goto L_0x00b2
            r12 = 0
            r0 = -1
            r1 = 0
            if (r13 == 0) goto L_0x009f
            com.android.messaging.datamodel.data.k r2 = new com.android.messaging.datamodel.data.k
            r2.<init>(r13)
            com.android.messaging.datamodel.data.n r13 = r11.this$0
            int r13 = r13.f1237Ux
            com.android.messaging.datamodel.data.n r3 = r11.this$0
            int r4 = r2.getCount()
            int unused = r3.f1237Ux = r4
            int r3 = r2.getCount()
            if (r3 <= 0) goto L_0x0045
            int r3 = r2.getPosition()
            boolean r4 = r2.moveToLast()
            if (r4 == 0) goto L_0x0045
            com.android.messaging.datamodel.data.s r4 = new com.android.messaging.datamodel.data.s
            r4.<init>()
            r4.mo6538c(r2)
            r2.move(r3)
            goto L_0x0046
        L_0x0045:
            r4 = r1
        L_0x0046:
            r5 = -1
            if (r4 == 0) goto L_0x0096
            com.android.messaging.datamodel.data.n r3 = r11.this$0
            long r7 = r3.f1234Az
            com.android.messaging.datamodel.data.n r3 = r11.this$0
            long r9 = r4.mo6558rg()
            long unused = r3.f1234Az = r9
            com.android.messaging.datamodel.data.n r3 = r11.this$0
            java.lang.String r3 = r3.f1235Bz
            com.android.messaging.datamodel.data.n r9 = r11.this$0
            java.lang.String r10 = r4.getMessageId()
            java.lang.String unused = r9.f1235Bz = r10
            com.android.messaging.datamodel.data.n r9 = r11.this$0
            java.lang.String r9 = r9.f1235Bz
            boolean r3 = android.text.TextUtils.equals(r3, r9)
            if (r3 == 0) goto L_0x007e
            com.android.messaging.datamodel.data.n r3 = r11.this$0
            int r3 = r3.f1237Ux
            if (r13 >= r3) goto L_0x007e
            r12 = 1
            goto L_0x009b
        L_0x007e:
            if (r13 == r0) goto L_0x009b
            com.android.messaging.datamodel.data.n r13 = r11.this$0
            long r9 = r13.f1234Az
            int r13 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r13 == 0) goto L_0x009b
            com.android.messaging.datamodel.data.n r13 = r11.this$0
            long r5 = r13.f1234Az
            int r13 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r13 <= 0) goto L_0x009b
            r1 = r4
            goto L_0x009b
        L_0x0096:
            com.android.messaging.datamodel.data.n r13 = r11.this$0
            long unused = r13.f1234Az = r5
        L_0x009b:
            r13 = r12
            r12 = r1
            r1 = r2
            goto L_0x00a6
        L_0x009f:
            com.android.messaging.datamodel.data.n r13 = r11.this$0
            int unused = r13.f1237Ux = r0
            r13 = r12
            r12 = r1
        L_0x00a6:
            com.android.messaging.datamodel.data.n r0 = r11.this$0
            com.android.messaging.datamodel.data.ConversationData$ConversationDataEventDispatcher r0 = r0.mListeners
            com.android.messaging.datamodel.data.n r11 = r11.this$0
            r0.mo6216a(r11, r1, r12, r13)
            goto L_0x00c3
        L_0x00b2:
            java.lang.String r12 = "Messages loader finished after unbinding mConversationId = "
            java.lang.StringBuilder r12 = p026b.p027a.p030b.p031a.C0632a.m1011Pa(r12)
            com.android.messaging.datamodel.data.n r11 = r11.this$0
            java.lang.String r11 = r11.f1236Ka
            java.lang.String r13 = "bugle_datamodel"
            p026b.p027a.p030b.p031a.C0632a.m1021a((java.lang.StringBuilder) r12, (java.lang.String) r11, (java.lang.String) r13)
        L_0x00c3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.data.C0925h.onLoadFinished(android.content.Loader, java.lang.Object):void");
    }

    public void onLoaderReset(Loader loader) {
        if (this.this$0.mo5926W(((C0837b) loader).getBindingId())) {
            this.this$0.mListeners.mo6216a(this.this$0, (Cursor) null, (C0936s) null, false);
            long unused = this.this$0.f1234Az = -1;
            int unused2 = this.this$0.f1237Ux = -1;
            return;
        }
        C0632a.m1021a(C0632a.m1011Pa("Messages loader reset after unbinding mConversationId = "), this.this$0.f1236Ka, "bugle_datamodel");
    }
}
