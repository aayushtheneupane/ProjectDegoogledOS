package com.android.messaging.datamodel;

import android.app.PendingIntent;
import androidx.core.app.NotificationCompat;
import com.android.messaging.util.ConversationIdSet;
import java.util.ArrayList;
import java.util.HashSet;

/* renamed from: com.android.messaging.datamodel.L */
public abstract class C0771L {
    public boolean mCanceled;
    public final HashSet mPeople;
    public int mType;

    /* renamed from: my */
    public final ConversationIdSet f1013my;

    /* renamed from: ny */
    public NotificationCompat.Style f1014ny;

    /* renamed from: oy */
    public NotificationCompat.Builder f1015oy;

    /* renamed from: py */
    public int f1016py;

    /* renamed from: qy */
    public ArrayList f1017qy = null;

    /* renamed from: ry */
    public ArrayList f1018ry = null;

    C0771L(ConversationIdSet conversationIdSet) {
        this.f1013my = conversationIdSet;
        this.mPeople = new HashSet();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract NotificationCompat.Style mo5878a(NotificationCompat.Builder builder);

    public abstract int getIcon();

    /* renamed from: ke */
    public long mo5880ke() {
        return Long.MIN_VALUE;
    }

    /* renamed from: le */
    public abstract PendingIntent mo5881le();
}
