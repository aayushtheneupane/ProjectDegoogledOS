package p000;

/* renamed from: dnd */
/* compiled from: PG */
final /* synthetic */ class dnd implements dof {

    /* renamed from: a */
    private final dnn f6859a;

    public dnd(dnn dnn) {
        this.f6859a = dnn;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01fc  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0204  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo4261a(p003j$.util.Optional r12, p003j$.util.Optional r13) {
        /*
            r11 = this;
            dnn r0 = r11.f6859a
            java.lang.Object r1 = r12.get()
            cxi r1 = (p000.cxi) r1
            j$.util.function.Function r2 = p000.dni.f6865a
            j$.util.Optional r2 = r13.map(r2)
            r3 = 0
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            java.lang.Object r2 = r2.orElse(r4)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            com.google.android.apps.photosgo.oneup.OneUpActionsView r4 = r0.f6880J
            int r5 = r1.f5909a
            r6 = 1048576(0x100000, float:1.469368E-39)
            r5 = r5 & r6
            r7 = 1
            r8 = 3
            if (r5 != 0) goto L_0x002a
            r5 = 1
            goto L_0x002c
        L_0x002a:
            r5 = 3
        L_0x002c:
            android.view.View r4 = r4.f4877a
            com.google.android.apps.photosgo.oneup.OneUpActionsView.m4816a(r4, r5)
            com.google.android.apps.photosgo.oneup.OneUpActionsView r4 = r0.f6880J
            dme r5 = r0.f6895d
            boolean r5 = r5.f6832h
            if (r5 == 0) goto L_0x0042
            int r5 = r1.f5909a
            r5 = r5 & r6
            if (r5 == 0) goto L_0x003f
            goto L_0x0042
        L_0x003f:
            r5 = 1
            goto L_0x0043
        L_0x0042:
            r5 = 3
        L_0x0043:
            android.view.View r4 = r4.f4880d
            com.google.android.apps.photosgo.oneup.OneUpActionsView.m4816a(r4, r5)
            com.google.android.apps.photosgo.oneup.OneUpActionsView r4 = r0.f6880J
            int r5 = r1.f5909a
            r5 = r5 & r6
            r9 = 2
            if (r5 != 0) goto L_0x0080
            dme r5 = r0.f6895d
            boolean r5 = r5.f6832h
            if (r5 != 0) goto L_0x0057
            goto L_0x0080
        L_0x0057:
            if (r2 != 0) goto L_0x0080
            cxh r5 = p000.cxh.IMAGE
            int r10 = r1.f5913e
            cxh r10 = p000.cxh.m5592a(r10)
            if (r10 != 0) goto L_0x0065
            cxh r10 = p000.cxh.UNKNOWN_MEDIA_TYPE
        L_0x0065:
            boolean r5 = r5.equals(r10)
            if (r5 == 0) goto L_0x0080
            java.lang.String r5 = r1.f5914f
            java.lang.String r10 = "image/gif"
            boolean r5 = r10.equals(r5)
            if (r5 == 0) goto L_0x0076
            goto L_0x0080
        L_0x0076:
            boolean r5 = r0.mo4271a((p000.cxi) r1)
            if (r5 == 0) goto L_0x007e
            r5 = 1
            goto L_0x0081
        L_0x007e:
            r5 = 2
            goto L_0x0081
        L_0x0080:
            r5 = 3
        L_0x0081:
            android.view.View r4 = r4.f4878b
            com.google.android.apps.photosgo.oneup.OneUpActionsView.m4816a(r4, r5)
            com.google.android.apps.photosgo.oneup.OneUpActionsView r4 = r0.f6880J
            int r5 = r1.f5909a
            r5 = r5 & r6
            if (r5 != 0) goto L_0x00b3
            dme r5 = r0.f6895d
            boolean r5 = r5.f6832h
            if (r5 != 0) goto L_0x0094
            goto L_0x00b3
        L_0x0094:
            if (r2 != 0) goto L_0x00b3
            cxh r2 = p000.cxh.IMAGE
            int r5 = r1.f5913e
            cxh r5 = p000.cxh.m5592a(r5)
            if (r5 != 0) goto L_0x00a2
            cxh r5 = p000.cxh.UNKNOWN_MEDIA_TYPE
        L_0x00a2:
            boolean r2 = r2.equals(r5)
            if (r2 != 0) goto L_0x00b1
            boolean r2 = r0.mo4271a((p000.cxi) r1)
            if (r2 == 0) goto L_0x00af
            goto L_0x00b1
        L_0x00af:
            r8 = 2
            goto L_0x00b4
        L_0x00b1:
            r8 = 1
            goto L_0x00b4
        L_0x00b3:
        L_0x00b4:
            android.view.View r2 = r4.f4879c
            com.google.android.apps.photosgo.oneup.OneUpActionsView.m4816a(r2, r8)
            cjr r2 = r0.f6917z
            boolean r2 = r2.mo3175a()
            if (r2 == 0) goto L_0x00d7
            j$.util.function.Function r2 = p000.dmg.f6836a
            j$.util.Optional r2 = r13.map(r2)
            dmh r4 = new dmh
            r4.<init>(r12)
            java.lang.Object r2 = r2.orElseGet(r4)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            goto L_0x00d9
        L_0x00d7:
            r2 = 0
        L_0x00d9:
            com.google.android.apps.photosgo.oneup.OneUpActionsView r4 = r0.f6880J
            r5 = 0
            if (r2 != 0) goto L_0x00ec
            hlz r6 = r0.f6905n
            dng r8 = new dng
            r8.<init>(r0)
            java.lang.String r10 = "OneUp auto-enhance"
            android.view.View$OnClickListener r6 = r6.mo7575a((android.view.View.OnClickListener) r8, (java.lang.String) r10)
            goto L_0x00ee
        L_0x00ec:
            r6 = r5
        L_0x00ee:
            android.view.View r4 = r4.f4878b
            r4.setOnClickListener(r6)
            com.google.android.apps.photosgo.oneup.OneUpActionsView r4 = r0.f6880J
            if (r2 != 0) goto L_0x0105
            hlz r2 = r0.f6905n
            dnh r6 = new dnh
            r6.<init>(r0)
            java.lang.String r8 = "OneUp edit"
            android.view.View$OnClickListener r2 = r2.mo7575a((android.view.View.OnClickListener) r6, (java.lang.String) r8)
            goto L_0x0107
        L_0x0105:
            r2 = r5
        L_0x0107:
            android.view.View r4 = r4.f4879c
            r4.setOnClickListener(r2)
            android.view.Menu r2 = r0.f6879I
            r4 = 2131362136(0x7f0a0158, float:1.8344044E38)
            r2.removeItem(r4)
            cjr r2 = r0.f6871A
            boolean r2 = r2.mo3175a()
            r6 = 8
            if (r2 == 0) goto L_0x01d5
            boolean r2 = p000.flw.m9194a((p000.cxi) r1)
            if (r2 == 0) goto L_0x01d5
            android.view.Menu r13 = r0.f6879I
            r2 = 2131886331(0x7f1200fb, float:1.9407238E38)
            r13.add(r3, r4, r3, r2)
            dik r13 = p000.dik.f6607j
            iir r13 = r13.mo8793g()
            dmb r2 = r0.f6897f
            android.content.Context r2 = r2.mo2634k()
            r4 = 2131886329(0x7f1200f9, float:1.9407234E38)
            java.lang.String r2 = r2.getString(r4)
            boolean r4 = r13.f14319c
            if (r4 == 0) goto L_0x0148
            r13.mo8751b()
            r13.f14319c = r3
        L_0x0148:
            iix r4 = r13.f14318b
            dik r4 = (p000.dik) r4
            r2.getClass()
            int r8 = r4.f6609a
            r8 = r8 | r9
            r4.f6609a = r8
            r4.f6611c = r2
            dmb r2 = r0.f6897f
            android.content.Context r2 = r2.mo2634k()
            android.content.res.Resources r2 = r2.getResources()
            android.net.Uri$Builder r4 = new android.net.Uri$Builder
            r4.<init>()
            java.lang.String r8 = "android.resource"
            android.net.Uri$Builder r4 = r4.scheme(r8)
            r8 = 2131230957(0x7f0800ed, float:1.8077981E38)
            java.lang.String r10 = r2.getResourcePackageName(r8)
            android.net.Uri$Builder r4 = r4.authority(r10)
            java.lang.String r10 = r2.getResourceTypeName(r8)
            android.net.Uri$Builder r4 = r4.appendPath(r10)
            java.lang.String r2 = r2.getResourceEntryName(r8)
            android.net.Uri$Builder r2 = r4.appendPath(r2)
            android.net.Uri r2 = r2.build()
            java.lang.String r2 = r2.toString()
            boolean r4 = r13.f14319c
            if (r4 == 0) goto L_0x0197
            r13.mo8751b()
            r13.f14319c = r3
        L_0x0197:
            iix r4 = r13.f14318b
            dik r4 = (p000.dik) r4
            r2.getClass()
            int r8 = r4.f6609a
            r8 = r8 | r6
            r4.f6609a = r8
            r4.f6613e = r2
            dmb r2 = r0.f6897f
            android.content.Context r2 = r2.mo2634k()
            r4 = 2131886330(0x7f1200fa, float:1.9407236E38)
            java.lang.String r2 = r2.getString(r4)
            boolean r4 = r13.f14319c
            if (r4 == 0) goto L_0x01bb
            r13.mo8751b()
            r13.f14319c = r3
        L_0x01bb:
            iix r4 = r13.f14318b
            dik r4 = (p000.dik) r4
            r2.getClass()
            int r8 = r4.f6609a
            r8 = r8 | 4
            r4.f6609a = r8
            r4.f6612d = r2
            iix r13 = r13.mo8770g()
            dik r13 = (p000.dik) r13
            j$.util.Optional r13 = p003j$.util.Optional.m16285of(r13)
            goto L_0x01d6
        L_0x01d5:
        L_0x01d6:
            dku r2 = r0.f6901j
            android.view.Menu r4 = r0.f6879I
            r2.mo4184a((android.view.Menu) r4, (p003j$.util.Optional) r12)
            dlh r2 = r0.f6902k
            android.view.Menu r4 = r0.f6879I
            r2.mo4208a(r4, r12)
            dot r12 = r0.f6881K
            boolean r0 = r13.isPresent()
            java.lang.Object[] r2 = new java.lang.Object[r9]
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r0)
            r2[r3] = r4
            j$.util.function.Function r4 = p000.dop.f6949a
            j$.util.Optional r4 = r13.map(r4)
            r2[r7] = r4
            if (r0 != 0) goto L_0x0204
            android.widget.TextView r12 = r12.f6961g
            if (r12 == 0) goto L_0x0203
            r12.setVisibility(r6)
        L_0x0203:
            return
        L_0x0204:
            android.widget.TextView r0 = r12.f6961g
            if (r0 == 0) goto L_0x0209
            goto L_0x0213
        L_0x0209:
            android.view.ViewStub r0 = r12.f6960f
            android.view.View r0 = r0.inflate()
            android.widget.TextView r0 = (android.widget.TextView) r0
            r12.f6961g = r0
        L_0x0213:
            fee r0 = r12.f6958d
            feb r0 = r0.f9364c
            r2 = 84028(0x1483c, float:1.17748E-40)
            fea r0 = r0.mo5563a(r2)
            fdp r2 = p000.ffh.f9451a
            fdj r0 = r0.mo5513a((p000.fdp) r2)
            fea r0 = (p000.fea) r0
            java.lang.String r2 = r12.f6962h
            if (r2 == 0) goto L_0x0237
            java.lang.String r4 = r1.f5910b
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x0237
            android.widget.TextView r2 = r12.f6961g
            p000.fee.m8692a(r2)
        L_0x0237:
            android.widget.TextView r2 = r12.f6961g
            r0.mo5562b(r2)
            java.lang.String r0 = r1.f5910b
            r12.f6962h = r0
            java.lang.Object r13 = r13.get()
            dik r13 = (p000.dik) r13
            android.widget.TextView r0 = r12.f6961g
            r0.setVisibility(r3)
            android.widget.TextView r0 = r12.f6961g
            java.lang.String r2 = r13.f6611c
            r0.setText(r2)
            boolean r0 = p000.C0637xj.m15912b((p000.dik) r13)
            if (r0 == 0) goto L_0x027e
            android.widget.TextView r0 = r12.f6961g
            hbl r2 = r12.f6957c
            java.lang.Object[] r4 = new java.lang.Object[r7]
            java.lang.String r5 = r13.f6611c
            r4[r3] = r5
            r3 = 2131886342(0x7f120106, float:1.940726E38)
            java.lang.String r2 = r2.getString(r3, r4)
            r0.setContentDescription(r2)
            android.widget.TextView r0 = r12.f6961g
            r2 = 1065353216(0x3f800000, float:1.0)
            r0.setAlpha(r2)
            android.widget.TextView r0 = r12.f6961g
            doq r2 = new doq
            r2.<init>(r12, r13, r1)
            r0.setOnClickListener(r2)
            goto L_0x0295
        L_0x027e:
            android.widget.TextView r0 = r12.f6961g
            r0.setContentDescription(r5)
            android.widget.TextView r0 = r12.f6961g
            r1 = 1058642330(0x3f19999a, float:0.6)
            r0.setAlpha(r1)
            android.widget.TextView r0 = r12.f6961g
            dor r1 = new dor
            r1.<init>(r12, r13)
            r0.setOnClickListener(r1)
        L_0x0295:
            hdt r0 = r12.f6955a
            java.lang.String r13 = r13.f6613e
            apj r13 = r0.mo7310a((java.lang.String) r13)
            cny r0 = r12.f6956b
            bdx r0 = r0.mo3300d()
            apj r13 = r13.mo1426b((p000.bdx) r0)
            dos r0 = new dos
            android.widget.TextView r12 = r12.f6961g
            r0.<init>(r12)
            r13.mo1421a((p000.ber) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.dnd.mo4261a(j$.util.Optional, j$.util.Optional):void");
    }
}
