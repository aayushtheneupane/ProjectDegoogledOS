package com.android.messaging.datamodel.data;

import android.net.Uri;
import android.text.TextUtils;
import com.android.messaging.datamodel.C0770K;
import com.android.messaging.datamodel.action.C0790D;
import com.android.messaging.datamodel.action.C0791E;
import com.android.messaging.datamodel.action.ReadDraftDataAction;
import com.android.messaging.datamodel.action.WriteDraftMessageAction;
import com.android.messaging.datamodel.p037a.C0781a;
import com.android.messaging.datamodel.p037a.C0783c;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.sms.C1027w;
import com.android.messaging.sms.C1029y;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.datamodel.data.A */
public class C0889A extends C0781a implements C0790D {

    /* renamed from: Fz */
    private String f1135Fz;

    /* renamed from: Gz */
    private String f1136Gz;

    /* renamed from: Hz */
    private String f1137Hz;

    /* renamed from: Iz */
    private C0770K f1138Iz = new C0770K();

    /* renamed from: Jz */
    private boolean f1139Jz;

    /* renamed from: Ka */
    private final String f1140Ka;
    /* access modifiers changed from: private */

    /* renamed from: Kz */
    public final List f1141Kz = new ArrayList();

    /* renamed from: Lz */
    private final List f1142Lz = Collections.unmodifiableList(this.f1141Kz);

    /* renamed from: Mz */
    private final List f1143Mz = new ArrayList();

    /* renamed from: Nz */
    private final List f1144Nz = Collections.unmodifiableList(this.f1143Mz);

    /* renamed from: Oz */
    private boolean f1145Oz;
    /* access modifiers changed from: private */

    /* renamed from: Pz */
    public C0940w f1146Pz;

    /* renamed from: Yx */
    private boolean f1147Yx;
    private boolean mIsGroupConversation;
    private final DraftMessageData$DraftMessageDataEventDispatcher mListeners = new DraftMessageData$DraftMessageDataEventDispatcher(this, (C0939v) null);
    private C0791E mMonitor;

    /* renamed from: pb */
    private C0943z f1148pb;

    public C0889A(String str) {
        this.f1140Ka = str;
    }

    /* renamed from: Db */
    private void m1674Db(int i) {
        if (!this.f1145Oz) {
            C0940w wVar = this.f1146Pz;
            if (wVar != null) {
                wVar.cancel(true);
                this.f1146Pz = null;
            }
            this.mListeners.mo6222a(this, i);
        }
    }

    /* renamed from: b */
    private void m1677b(MessagePartData messagePartData, PendingAttachmentData pendingAttachmentData) {
        if (messagePartData != null && messagePartData.mo6311gh()) {
            m1683ho();
        }
        if (pendingAttachmentData != null && pendingAttachmentData.mo6311gh()) {
            m1683ho();
        }
        Iterator it = this.f1141Kz.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((MessagePartData) it.next()).mo6311gh()) {
                    m1683ho();
                    break;
                }
            } else {
                break;
            }
        }
        Iterator it2 = this.f1143Mz.iterator();
        while (true) {
            if (it2.hasNext()) {
                if (((MessagePartData) it2.next()).mo6311gh()) {
                    m1683ho();
                    break;
                }
            } else {
                break;
            }
        }
        if (messagePartData != null) {
            this.f1141Kz.add(messagePartData);
        } else if (pendingAttachmentData != null) {
            this.f1143Mz.add(pendingAttachmentData);
        }
    }

    /* renamed from: d */
    static /* synthetic */ int m1679d(C0889A a) {
        int i = 0;
        for (MessagePartData ih : a.f1141Kz) {
            if (ih.mo6315ih()) {
                i++;
            }
        }
        for (MessagePartData ih2 : a.f1143Mz) {
            if (ih2.mo6315ih()) {
                i++;
            }
        }
        return i;
    }

    /* renamed from: f */
    private void m1681f(String str, boolean z) {
        this.f1136Gz = str;
        if (z) {
            m1674Db(4);
        }
    }

    /* renamed from: g */
    private void m1682g(String str, boolean z) {
        this.f1135Fz = str;
        this.f1138Iz.mo5883c(mo6199lf(), this.f1135Fz);
        if (z) {
            m1674Db(2);
        }
    }

    /* renamed from: ho */
    private void m1683ho() {
        for (MessagePartData _g : this.f1141Kz) {
            _g.mo6294_g();
        }
        this.f1141Kz.clear();
        this.f1143Mz.clear();
    }

    /* renamed from: i */
    private boolean m1684i(MessagePartData messagePartData) {
        C1424b.m3592ia(messagePartData.mo6300dh());
        for (MessagePartData messagePartData2 : this.f1141Kz) {
            if (messagePartData2.getContentUri().equals(messagePartData.getContentUri())) {
                this.f1141Kz.remove(messagePartData2);
                messagePartData2.mo6294_g();
                m1677b(messagePartData, (PendingAttachmentData) null);
                return false;
            }
        }
        int vb = m1686vb();
        m1685io();
        if (vb >= 10) {
            messagePartData.mo6294_g();
            return true;
        }
        m1677b(messagePartData, (PendingAttachmentData) null);
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: io */
    public int m1685io() {
        C1449g.get().getInt("bugle_mms_attachment_limit", 10);
        return 10;
    }

    /* renamed from: vb */
    private int m1686vb() {
        return this.f1143Mz.size() + this.f1141Kz.size();
    }

    /* renamed from: L */
    public MessageData mo6169L(boolean z) {
        MessageData messageData;
        if (mo6190ff()) {
            messageData = MessageData.m1753a(this.f1140Ka, this.f1137Hz, this.f1135Fz, this.f1136Gz);
            for (MessagePartData g : this.f1141Kz) {
                messageData.mo6267g(g);
            }
        } else {
            messageData = MessageData.m1757b(this.f1140Ka, this.f1137Hz, this.f1135Fz);
        }
        if (z) {
            this.f1145Oz = false;
            this.f1141Kz.clear();
            m1682g("", false);
            m1681f("", false);
            m1674Db(255);
        } else {
            this.f1145Oz = true;
        }
        return messageData;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Te */
    public void mo5924Te() {
        C0791E e = this.mMonitor;
        if (e != null) {
            e.unregister();
        }
        this.mMonitor = null;
        this.mListeners.clear();
    }

    /* renamed from: Ue */
    public String mo6170Ue() {
        return this.f1140Ka;
    }

    /* renamed from: Z */
    public void mo6171Z(String str) {
        this.f1136Gz = str;
    }

    /* renamed from: aa */
    public void mo6180aa(String str) {
        this.f1135Fz = str;
        this.f1138Iz.mo5883c(mo6199lf(), this.f1135Fz);
    }

    /* renamed from: ef */
    public boolean mo6188ef() {
        return mo6190ff() && this.mIsGroupConversation;
    }

    /* renamed from: ff */
    public boolean mo6190ff() {
        int lf = mo6199lf();
        return C1027w.m2399c(this.f1147Yx, lf) || (this.mIsGroupConversation && C1029y.m2403Ea(lf)) || this.f1138Iz.mo5885ie() || !this.f1141Kz.isEmpty() || !TextUtils.isEmpty(this.f1136Gz);
    }

    /* renamed from: gf */
    public String mo6192gf() {
        return this.f1136Gz;
    }

    /* renamed from: he */
    public int mo6193he() {
        return this.f1138Iz.mo5884he();
    }

    /* renamed from: hf */
    public String mo6194hf() {
        return this.f1135Fz;
    }

    /* renamed from: if */
    public List mo6195if() {
        return this.f1142Lz;
    }

    /* renamed from: je */
    public int mo6196je() {
        return this.f1138Iz.mo5886je();
    }

    /* renamed from: jf */
    public List mo6197jf() {
        return this.f1144Nz;
    }

    /* renamed from: kf */
    public String mo6198kf() {
        return this.f1137Hz;
    }

    /* renamed from: lf */
    public int mo6199lf() {
        C0943z zVar = this.f1148pb;
        if (zVar == null) {
            return -1;
        }
        return zVar.mo6582H();
    }

    /* renamed from: ma */
    public void mo6200ma(int i) {
        m1683ho();
        m1674Db(i);
    }

    /* renamed from: mf */
    public boolean mo6201mf() {
        return !this.f1141Kz.isEmpty();
    }

    /* renamed from: nf */
    public boolean mo6202nf() {
        return !this.f1143Mz.isEmpty();
    }

    /* renamed from: of */
    public boolean mo6203of() {
        C0940w wVar = this.f1146Pz;
        return wVar != null && !wVar.isCancelled();
    }

    /* renamed from: pf */
    public boolean mo6204pf() {
        return this.f1139Jz;
    }

    /* renamed from: a */
    public void mo6176a(C0942y yVar) {
        this.mListeners.add(yVar);
    }

    /* renamed from: c */
    public void mo6183c(Collection collection) {
        Iterator it = collection.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((MessagePartData) it.next()).mo6311gh()) {
                    m1683ho();
                    break;
                }
            } else {
                break;
            }
        }
        Iterator it2 = this.f1141Kz.iterator();
        while (true) {
            if (it2.hasNext()) {
                if (((MessagePartData) it2.next()).mo6311gh()) {
                    m1683ho();
                    break;
                }
            } else {
                break;
            }
        }
        Iterator it3 = this.f1143Mz.iterator();
        while (true) {
            if (it3.hasNext()) {
                if (((MessagePartData) it3.next()).mo6311gh()) {
                    m1683ho();
                    break;
                }
            } else {
                break;
            }
        }
        boolean z = false;
        Iterator it4 = collection.iterator();
        while (it4.hasNext()) {
            z |= m1684i((MessagePartData) it4.next());
        }
        if (z) {
            this.mListeners.mo6221a(this);
        }
        m1674Db(1);
    }

    /* renamed from: e */
    public void mo6187e(PendingAttachmentData pendingAttachmentData) {
        for (PendingAttachmentData contentUri : this.f1143Mz) {
            if (contentUri.getContentUri().equals(pendingAttachmentData.getContentUri())) {
                this.f1143Mz.remove(pendingAttachmentData);
                pendingAttachmentData.mo6294_g();
                m1674Db(1);
                return;
            }
        }
    }

    /* renamed from: a */
    public void mo6177a(C0943z zVar) {
        this.f1148pb = zVar;
    }

    /* renamed from: f */
    public void mo6189f(MessagePartData messagePartData) {
        for (MessagePartData messagePartData2 : this.f1141Kz) {
            if (messagePartData2.getContentUri().equals(messagePartData.getContentUri())) {
                this.f1141Kz.remove(messagePartData2);
                messagePartData2.mo6294_g();
                m1674Db(1);
                return;
            }
        }
    }

    /* renamed from: a */
    public void mo6175a(PendingAttachmentData pendingAttachmentData, C0784d dVar) {
        if (m1676a(pendingAttachmentData, dVar.getBindingId())) {
            this.mListeners.mo6221a(this);
        }
        m1674Db(1);
    }

    /* renamed from: g */
    public boolean mo6191g(Uri uri) {
        for (MessagePartData contentUri : this.f1141Kz) {
            if (contentUri.getContentUri().equals(uri)) {
                return true;
            }
        }
        for (PendingAttachmentData contentUri2 : this.f1143Mz) {
            if (contentUri2.getContentUri().equals(uri)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public MessageData mo6184d(C0784d dVar) {
        C1424b.m3592ia(!mo6202nf());
        this.f1139Jz = true;
        MessageData L = mo6169L(true);
        this.f1139Jz = false;
        return L;
    }

    /* renamed from: e */
    public void mo6186e(C0784d dVar) {
        MessageData L = mo6169L(false);
        if (mo5926W(dVar.getBindingId())) {
            WriteDraftMessageAction.m1467a(this.f1140Ka, L);
        }
        this.f1143Mz.clear();
    }

    /* renamed from: a */
    private boolean m1676a(PendingAttachmentData pendingAttachmentData, String str) {
        int vb = m1686vb();
        m1685io();
        boolean z = vb >= 10;
        if (z || mo6191g(pendingAttachmentData.getContentUri())) {
            pendingAttachmentData.mo6294_g();
            return z;
        }
        C1424b.m3592ia(!this.f1143Mz.contains(pendingAttachmentData));
        C1424b.equals(0, pendingAttachmentData.getCurrentState());
        m1677b((MessagePartData) null, pendingAttachmentData);
        pendingAttachmentData.mo6363a(this, str);
        return false;
    }

    /* renamed from: d */
    public void mo6185d(PendingAttachmentData pendingAttachmentData) {
        this.mListeners.mo6223r();
    }

    /* renamed from: b */
    public void mo6181b(Set set) {
        Iterator it = this.f1141Kz.iterator();
        boolean z = false;
        while (it.hasNext()) {
            MessagePartData messagePartData = (MessagePartData) it.next();
            if (set.contains(messagePartData)) {
                it.remove();
                messagePartData.mo6294_g();
                z = true;
            }
        }
        if (z) {
            m1674Db(1);
        }
    }

    /* renamed from: a */
    public void mo6174a(MessagePartData messagePartData, PendingAttachmentData pendingAttachmentData) {
        for (PendingAttachmentData contentUri : this.f1143Mz) {
            if (contentUri.getContentUri().equals(pendingAttachmentData.getContentUri())) {
                this.f1143Mz.remove(pendingAttachmentData);
                if (pendingAttachmentData.mo6311gh()) {
                    messagePartData.mo6293O(true);
                }
                this.f1141Kz.add(messagePartData);
                m1674Db(1);
                return;
            }
        }
        messagePartData.mo6294_g();
    }

    /* renamed from: c */
    public void mo6182c(String str, boolean z) {
        C1430e.m3620d("MessagingApp", "DraftMessageData: set selfId=" + str + " for conversationId=" + this.f1140Ka);
        this.f1137Hz = str;
        if (z) {
            m1674Db(8);
        }
    }

    /* renamed from: a */
    public boolean mo6179a(C0784d dVar, MessageData messageData, boolean z) {
        StringBuilder Pa = C0632a.m1011Pa("DraftMessageData: ");
        Pa.append(messageData == null ? "loading" : "setting");
        Pa.append(" for conversationId=");
        Pa.append(this.f1140Ka);
        C1430e.m3620d("MessagingApp", Pa.toString());
        if (z) {
            this.f1145Oz = false;
            this.f1141Kz.clear();
            m1682g("", false);
            m1681f("", false);
        }
        boolean z2 = this.f1145Oz;
        this.f1145Oz = false;
        if (this.mMonitor != null || z2 || !mo5926W(dVar.getBindingId())) {
            return false;
        }
        this.mMonitor = ReadDraftDataAction.m1430a(this.f1140Ka, messageData, dVar.getBindingId(), this);
        return true;
    }

    /* renamed from: a */
    public void mo6173a(ReadDraftDataAction readDraftDataAction, Object obj, MessageData messageData, C0934q qVar) {
        String str = (String) obj;
        if (mo5926W(str)) {
            this.f1137Hz = messageData.mo6275kf();
            this.mIsGroupConversation = qVar.mo6499Of();
            this.f1147Yx = qVar.mo6497Mf();
            C1424b.m3594t(str);
            this.f1145Oz = false;
            if ((TextUtils.isEmpty(this.f1135Fz) && this.f1141Kz.isEmpty() && TextUtils.isEmpty(this.f1136Gz)) || (TextUtils.equals(this.f1135Fz, messageData.mo6274hf()) && TextUtils.equals(this.f1136Gz, messageData.mo6279mg()) && this.f1141Kz.isEmpty())) {
                m1682g(messageData.mo6274hf(), false);
                m1681f(messageData.mo6279mg(), false);
                Iterator it = messageData.getParts().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MessagePartData messagePartData = (MessagePartData) it.next();
                    if (messagePartData.mo6300dh()) {
                        int vb = m1686vb();
                        m1685io();
                        if (vb >= 10) {
                            this.mListeners.mo6221a(this);
                            break;
                        }
                    }
                    if (messagePartData instanceof PendingAttachmentData) {
                        PendingAttachmentData pendingAttachmentData = (PendingAttachmentData) messagePartData;
                        C1424b.equals(0, pendingAttachmentData.getCurrentState());
                        m1676a(pendingAttachmentData, str);
                    } else if (messagePartData.mo6300dh()) {
                        m1684i(messagePartData);
                    }
                }
                m1674Db(255);
            } else {
                m1674Db(8);
            }
            StringBuilder Pa = C0632a.m1011Pa("DraftMessageData: draft loaded. conversationId=");
            Pa.append(this.f1140Ka);
            Pa.append(" selfId=");
            Pa.append(this.f1137Hz);
            C1430e.m3620d("MessagingApp", Pa.toString());
        } else {
            C0632a.m1021a(C0632a.m1011Pa("DraftMessageData: draft loaded but not bound. conversationId="), this.f1140Ka, "MessagingApp");
        }
        this.mMonitor = null;
    }

    /* renamed from: a */
    public void mo6172a(ReadDraftDataAction readDraftDataAction, Object obj) {
        C0632a.m1021a(C0632a.m1011Pa("DraftMessageData: draft not loaded. conversationId="), this.f1140Ka, "MessagingApp");
        this.f1145Oz = false;
        this.mMonitor = null;
    }

    /* renamed from: a */
    public void mo6178a(boolean z, int i, C0941x xVar, C0783c cVar) {
        new C0940w(this, z, i, xVar, cVar).mo8233b(null);
    }
}
