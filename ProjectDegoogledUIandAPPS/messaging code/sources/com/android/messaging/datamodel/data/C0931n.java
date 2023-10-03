package com.android.messaging.datamodel.data;

import android.app.LoaderManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.messaging.R;
import com.android.messaging.datamodel.C0944e;
import com.android.messaging.datamodel.C0947h;
import com.android.messaging.datamodel.action.DeleteConversationAction;
import com.android.messaging.datamodel.action.DeleteMessageAction;
import com.android.messaging.datamodel.action.InsertNewMessageAction;
import com.android.messaging.datamodel.action.RedownloadMmsAction;
import com.android.messaging.datamodel.action.ResendMessageAction;
import com.android.messaging.datamodel.action.UpdateConversationArchiveStatusAction;
import com.android.messaging.datamodel.p037a.C0781a;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.sms.C1027w;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1474sa;
import com.android.messaging.util.C1478ua;
import com.android.messaging.util.ContactUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.android.messaging.datamodel.data.n */
public class C0931n extends C0781a {
    /* access modifiers changed from: private */

    /* renamed from: Az */
    public long f1234Az = -1;
    /* access modifiers changed from: private */

    /* renamed from: Bz */
    public String f1235Bz;
    /* access modifiers changed from: private */

    /* renamed from: Ka */
    public final String f1236Ka;
    /* access modifiers changed from: private */

    /* renamed from: Ux */
    public int f1237Ux = -1;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final ConversationData$ConversationDataEventDispatcher mListeners;
    private LoaderManager mLoaderManager;

    /* renamed from: sz */
    private final C0926i f1238sz;

    /* renamed from: tz */
    private final C0925h f1239tz;

    /* renamed from: uz */
    private final C0927j f1240uz;

    /* renamed from: vz */
    private final C0929l f1241vz;
    /* access modifiers changed from: private */

    /* renamed from: wz */
    public final C0938u f1242wz;
    /* access modifiers changed from: private */

    /* renamed from: xz */
    public final C0911X f1243xz;
    /* access modifiers changed from: private */

    /* renamed from: yz */
    public C0934q f1244yz;
    /* access modifiers changed from: private */

    /* renamed from: zz */
    public final C0919ca f1245zz;

    public C0931n(Context context, C0924g gVar, String str) {
        C1424b.m3592ia(str != null);
        this.mContext = context;
        this.f1236Ka = str;
        this.f1238sz = new C0926i(this, (C0923f) null);
        this.f1239tz = new C0925h(this, (C0923f) null);
        this.f1240uz = new C0927j(this, (C0923f) null);
        this.f1241vz = new C0929l(this, (C0923f) null);
        this.f1242wz = new C0938u();
        this.f1244yz = new C0934q();
        this.f1243xz = new C0911X();
        this.f1245zz = new C0919ca(context);
        this.mListeners = new ConversationData$ConversationDataEventDispatcher(this, (C0923f) null);
        this.mListeners.add(gVar);
    }

    /* renamed from: J */
    public int mo6457J(boolean z) {
        return this.f1243xz.mo6383J(z);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Te */
    public void mo5924Te() {
        this.mListeners.clear();
        LoaderManager loaderManager = this.mLoaderManager;
        if (loaderManager != null) {
            loaderManager.destroyLoader(1);
            this.mLoaderManager.destroyLoader(2);
            this.mLoaderManager.destroyLoader(3);
            this.mLoaderManager.destroyLoader(4);
            this.mLoaderManager = null;
        }
    }

    /* renamed from: Ue */
    public String mo6458Ue() {
        return this.f1236Ka;
    }

    /* renamed from: Ve */
    public ParticipantData mo6459Ve() {
        return this.f1243xz.mo6385Ve();
    }

    /* renamed from: We */
    public boolean mo6460We() {
        return this.f1244yz.mo6508We();
    }

    /* renamed from: Xe */
    public int mo6461Xe() {
        return this.f1242wz.mo6572Xe();
    }

    /* renamed from: Y */
    public ParticipantData mo6462Y(String str) {
        return this.f1243xz.mo6386Y(str);
    }

    /* renamed from: Ye */
    public ParticipantData mo6463Ye() {
        return this.f1242wz.mo6573Ye();
    }

    /* renamed from: Ze */
    public String mo6464Ze() {
        ParticipantData Ye = mo6463Ye();
        if (Ye == null) {
            return null;
        }
        String rh = Ye.mo6351rh();
        if (TextUtils.isEmpty(rh) || !C1027w.isPhoneNumber(rh)) {
            return null;
        }
        return rh;
    }

    /* renamed from: _e */
    public boolean mo6465_e() {
        return this.f1242wz.isLoaded();
    }

    /* renamed from: af */
    public C0919ca mo6472af() {
        return this.f1245zz;
    }

    /* renamed from: b */
    public void mo6475b(C0784d dVar, String str) {
        C1424b.m3592ia(dVar.getData() == this);
        C1424b.m3594t(str);
        RedownloadMmsAction.m1435R(str);
    }

    /* renamed from: bf */
    public void mo6476bf() {
        C0947h.get().mo6590N(this.f1236Ka);
        C0944e.m2075H(this.f1236Ka);
    }

    /* renamed from: c */
    public void mo6478c(C0784d dVar, String str) {
        C1424b.m3592ia(dVar.getData() == this);
        C1424b.m3594t(str);
        ResendMessageAction.m1438S(str);
    }

    /* renamed from: cf */
    public void mo6479cf() {
        C0947h.get().mo6590N((String) null);
    }

    public C0938u getParticipants() {
        return this.f1242wz;
    }

    public boolean isFocused() {
        return isBound() && C0947h.get().mo6588L(this.f1236Ka);
    }

    /* renamed from: qa */
    public String mo6482qa() {
        return this.f1244yz.getName();
    }

    /* renamed from: b */
    public void mo6474b(C0784d dVar) {
        C1424b.m3592ia(dVar.getData() == this);
        UpdateConversationArchiveStatusAction.m1455T(this.f1236Ka);
    }

    /* renamed from: c */
    public void mo6477c(C0784d dVar) {
        C1424b.m3592ia(dVar.getData() == this);
        UpdateConversationArchiveStatusAction.m1456U(this.f1236Ka);
    }

    /* renamed from: a */
    public void mo6471a(C0924g gVar) {
        C1424b.m3593oc();
        this.mListeners.add(gVar);
    }

    /* renamed from: b */
    public MessageData mo6473b(C0936s sVar) {
        MessagePartData messagePartData;
        MessageData messageData = new MessageData();
        String b = C1029y.m2438b(this.mContext.getResources(), sVar.mo6554mg());
        if (!TextUtils.isEmpty(b)) {
            messageData.mo6263da(this.mContext.getResources().getString(R.string.message_fwd, new Object[]{b}));
        }
        for (MessagePartData messagePartData2 : sVar.getParts()) {
            if (messagePartData2.isText()) {
                messagePartData = new MessagePartData(messagePartData2.getText());
            } else {
                messagePartData = PendingAttachmentData.m1870a(messagePartData2.getContentType(), messagePartData2.getContentUri());
            }
            messageData.mo6267g(messagePartData);
        }
        return messageData;
    }

    /* renamed from: a */
    public void mo6467a(LoaderManager loaderManager, C0784d dVar) {
        Bundle bundle = new Bundle();
        bundle.putString("bindingId", dVar.getBindingId());
        this.mLoaderManager = loaderManager;
        this.mLoaderManager.initLoader(1, bundle, this.f1238sz);
        this.mLoaderManager.initLoader(2, bundle, this.f1239tz);
        this.mLoaderManager.initLoader(3, bundle, this.f1240uz);
        this.mLoaderManager.initLoader(4, bundle, this.f1241vz);
    }

    /* renamed from: a */
    public void mo6469a(C0784d dVar, MessageData messageData) {
        C1424b.m3592ia(TextUtils.equals(this.f1236Ka, messageData.mo6250Ue()));
        C1424b.m3592ia(dVar.getData() == this);
        if (!C1464na.m3759Zj() || messageData.mo6275kf() == null) {
            InsertNewMessageAction.m1379c(messageData);
        } else {
            int defaultSmsSubscriptionId = C1474sa.getDefault().getDefaultSmsSubscriptionId();
            if (defaultSmsSubscriptionId == -1 || !this.f1243xz.mo6388pa(messageData.mo6275kf())) {
                InsertNewMessageAction.m1379c(messageData);
            } else {
                InsertNewMessageAction.m1378a(messageData, defaultSmsSubscriptionId);
            }
        }
        if (mo6465_e()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Iterator it = this.f1242wz.iterator();
            while (it.hasNext()) {
                ParticipantData participantData = (ParticipantData) it.next();
                if (!participantData.mo6362zh()) {
                    if (participantData.isEmail()) {
                        arrayList2.add(participantData.mo6351rh());
                    } else {
                        arrayList.add(participantData.mo6351rh());
                    }
                }
            }
            if (ContactUtil.m3525Kj()) {
                C1478ua.m3823a((Runnable) new C0923f(this, arrayList, arrayList2));
            }
        }
    }

    /* renamed from: a */
    public void mo6470a(C0784d dVar, String str) {
        C1424b.m3592ia(dVar.getData() == this);
        C1424b.m3594t(str);
        DeleteMessageAction.m1354f(str);
    }

    /* renamed from: a */
    public void mo6468a(C0783c cVar) {
        C1424b.m3592ia(cVar.getData() == this);
        C0934q qVar = this.f1244yz;
        if (qVar == null) {
            DeleteConversationAction.m1351a(this.f1236Ka, System.currentTimeMillis());
        } else {
            qVar.mo6523pa();
        }
    }

    /* renamed from: a */
    public C0917ba mo6466a(String str, boolean z) {
        C0919ca caVar = this.f1245zz;
        C0911X x = this.f1243xz;
        if (!C1464na.m3759Zj() || x.mo6383J(true) <= 1) {
            return null;
        }
        return caVar.mo6419d(str, z);
    }
}
