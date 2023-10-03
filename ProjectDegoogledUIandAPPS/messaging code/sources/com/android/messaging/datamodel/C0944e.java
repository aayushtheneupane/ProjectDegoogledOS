package com.android.messaging.datamodel;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.p016v4.media.session.C0107q;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.action.MarkAsReadAction;
import com.android.messaging.datamodel.action.MarkAsSeenAction;
import com.android.messaging.datamodel.data.MessageData;
import com.android.messaging.p041ui.C1040Ea;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import com.android.messaging.util.C1451h;
import com.android.messaging.util.C1462ma;
import com.android.messaging.util.C1480va;
import com.android.messaging.util.C1481w;
import com.android.messaging.util.C1488za;
import com.android.messaging.util.ConversationIdSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import p000a.p005b.C0027n;

/* renamed from: com.android.messaging.datamodel.e */
public class C0944e {

    /* renamed from: Ax */
    private static int f1343Ax;

    /* renamed from: Bx */
    private static final C0027n f1344Bx = new C0027n();

    /* renamed from: Cx */
    private static int f1345Cx;
    private static final Object mLock = new Object();
    private static boolean sInitialized = false;

    /* renamed from: wx */
    private static final Set f1346wx = new HashSet();

    /* renamed from: xx */
    private static int f1347xx;

    /* renamed from: yx */
    private static int f1348yx;

    /* renamed from: zx */
    private static int f1349zx;

    /* renamed from: E */
    private static ConversationIdSet m2073E(Context context) {
        String string = C1451h.m3724Hd().getString(context.getString(R.string.notifications_group_children_key), "");
        if (!TextUtils.isEmpty(string)) {
            return ConversationIdSet.m3538Xa(string);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        if (r2.get(2).equals("photo") != false) goto L_0x0044;
     */
    /* renamed from: H */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.net.Uri m2074H(android.net.Uri r6) {
        /*
            java.lang.String r0 = com.android.messaging.util.C1426c.m3603p(r6)
            java.lang.String r1 = "r"
            boolean r0 = android.text.TextUtils.equals(r0, r1)
            r1 = 0
            if (r0 == 0) goto L_0x0012
            android.net.Uri r6 = com.android.messaging.util.C1426c.m3606s(r6)
            goto L_0x001a
        L_0x0012:
            boolean r0 = com.android.messaging.util.C1488za.m3877z(r6)
            if (r0 == 0) goto L_0x0019
            goto L_0x001a
        L_0x0019:
            r6 = r1
        L_0x001a:
            r0 = 2
            if (r6 == 0) goto L_0x0043
            java.lang.String r2 = r6.getAuthority()
            java.lang.String r3 = "com.android.contacts"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0043
            java.util.List r2 = r6.getPathSegments()
            int r3 = r2.size()
            r4 = 3
            if (r3 != r4) goto L_0x0043
            java.lang.Object r2 = r2.get(r0)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "photo"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r6 = r1
        L_0x0044:
            if (r6 != 0) goto L_0x0047
            return r1
        L_0x0047:
            java.util.List r1 = r6.getPathSegments()
            int r2 = r1.size()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r4 = 0
        L_0x0055:
            if (r4 >= r2) goto L_0x0070
            r5 = 47
            r3.append(r5)
            if (r4 != r0) goto L_0x0064
            java.lang.String r5 = "display_photo"
            r3.append(r5)
            goto L_0x006d
        L_0x0064:
            java.lang.Object r5 = r1.get(r4)
            java.lang.String r5 = (java.lang.String) r5
            r3.append(r5)
        L_0x006d:
            int r4 = r4 + 1
            goto L_0x0055
        L_0x0070:
            android.net.Uri$Builder r6 = r6.buildUpon()
            java.lang.String r0 = r3.toString()
            android.net.Uri$Builder r6 = r6.path(r0)
            android.net.Uri r6 = r6.build()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0944e.m2074H(android.net.Uri):android.net.Uri");
    }

    /* renamed from: I */
    public static void m2076I(String str) {
        synchronized (mLock) {
            if (TextUtils.isEmpty(str)) {
                f1344Bx.clear();
            } else {
                f1344Bx.remove(str);
            }
        }
    }

    /* renamed from: Td */
    public static boolean m2077Td() {
        try {
            C0967f.get().getApplicationContext().getPackageManager().getPackageInfo("com.google.android.wearable.app", 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: Ud */
    public static void m2078Ud() {
        new MarkAsSeenAction((String) null).start();
        m2076I((String) null);
    }

    /* renamed from: Vd */
    public static void m2079Vd() {
        m2095cb((String) null);
    }

    /* renamed from: a */
    public static void m2090a(boolean z, String str, int i) {
        String str2;
        String str3;
        PendingIntent pendingIntent;
        ConversationIdSet conversationIdSet;
        int i2;
        int i3;
        if (Log.isLoggable("MessagingAppNotif", 2)) {
            C1430e.m3628v("MessagingAppNotif", "Update: silent = " + z + " conversationId = " + str + " coverage = " + i);
        }
        C1424b.m3584Gj();
        if (!sInitialized) {
            Resources resources = C0967f.get().getApplicationContext().getResources();
            f1347xx = resources.getDimensionPixelSize(R.dimen.notification_wearable_image_width);
            f1348yx = resources.getDimensionPixelSize(R.dimen.notification_wearable_image_height);
            f1343Ax = (int) resources.getDimension(17104902);
            f1349zx = (int) resources.getDimension(17104901);
            sInitialized = true;
        }
        if ((i & 1) != 0) {
            C0771L me = C0769J.m1243me();
            boolean M = C0947h.get().mo6589M(str);
            if (me == null) {
                cancel(0);
                if (M && !TextUtils.isEmpty(str)) {
                    m2095cb(str);
                }
            } else {
                m2088a(me, z, M);
                Context applicationContext = C0967f.get().getApplicationContext();
                ConversationIdSet E = m2073E(applicationContext);
                if (E != null && E.size() > 0) {
                    m2089a(E, me);
                }
                ConversationIdSet conversationIdSet2 = new ConversationIdSet();
                if (me instanceof C0766G) {
                    for (C0771L l : ((C0766G) me).mChildren) {
                        m2088a(l, true, M);
                        ConversationIdSet conversationIdSet3 = l.f1013my;
                        if (conversationIdSet3 != null) {
                            conversationIdSet2.add(conversationIdSet3.first());
                        }
                    }
                }
                m2085a(applicationContext, conversationIdSet2);
            }
        }
        if ((i & 2) != 0) {
            Cursor query = C0947h.get().getDatabase().query("messages", MessageData.getProjection(), "((message_status = 8 OR message_status = 106) AND seen = 0)", (String[]) null, (String) null, (String) null, "conversation_id, sent_timestamp asc");
            try {
                Context applicationContext2 = C0967f.get().getApplicationContext();
                Resources resources2 = applicationContext2.getResources();
                NotificationManagerCompat from = NotificationManagerCompat.from(applicationContext2);
                if (query != null) {
                    MessageData messageData = new MessageData();
                    HashSet hashSet = new HashSet();
                    ArrayList arrayList = new ArrayList();
                    int i4 = -1;
                    query.moveToPosition(-1);
                    while (query.moveToNext()) {
                        messageData.mo6260c(query);
                        String Ue = messageData.mo6250Ue();
                        if (!C0947h.get().mo6589M(Ue)) {
                            i4 = query.getPosition();
                            arrayList.add(Integer.valueOf(i4));
                            hashSet.add(Ue);
                        }
                    }
                    if (Log.isLoggable("MessagingAppNotif", 3)) {
                        C1430e.m3620d("MessagingAppNotif", "Found " + arrayList.size() + " failed messages");
                    }
                    if (arrayList.size() > 0) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(applicationContext2, (String) null);
                        if (arrayList.size() == 1) {
                            query.moveToPosition(i4);
                            messageData.mo6260c(query);
                            String Ue2 = messageData.mo6250Ue();
                            pendingIntent = C1040Ea.get().mo6970c(applicationContext2, Ue2, (MessageData) null);
                            conversationIdSet = ConversationIdSet.m3538Xa(Ue2);
                            str3 = messageData.mo6274hf();
                            str2 = resources2.getString(messageData.getStatus() == 106 ? R.string.notification_download_failures_line1_singular : R.string.notification_send_failures_line1_singular);
                        } else {
                            PendingIntent r = C1040Ea.get().mo6981r(applicationContext2);
                            if (messageData.getStatus() == 106) {
                                i3 = R.string.notification_download_failures_line1_plural;
                                i2 = R.plurals.notification_download_failures;
                            } else {
                                i3 = R.string.notification_send_failures_line1_plural;
                                i2 = R.plurals.notification_send_failures;
                            }
                            String string = resources2.getString(i3);
                            str3 = resources2.getQuantityString(i2, hashSet.size(), new Object[]{Integer.valueOf(arrayList.size()), Integer.valueOf(hashSet.size())});
                            str2 = string;
                            pendingIntent = r;
                            conversationIdSet = null;
                        }
                        CharSequence a = C0769J.m1239a(applicationContext2, str2);
                        CharSequence a2 = C0769J.m1239a(applicationContext2, str3);
                        builder.setContentTitle(a).setTicker(a).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.ic_failed_light).setDeleteIntent(C1040Ea.get().mo6949a(applicationContext2, 2, conversationIdSet, 0)).setContentIntent(pendingIntent).setSound(C1488za.m3873i(applicationContext2, R.raw.message_failure));
                        builder.setContentText(a2);
                        from.notify(m2082a(2, (String) null), 2, builder.build());
                    } else {
                        from.cancel(m2082a(2, (String) null), 2);
                    }
                }
            } finally {
                if (query != null) {
                    query.close();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0068, code lost:
        r6 = com.android.messaging.C0967f.get().getApplicationContext();
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void m2092b(int r6, java.lang.String r7, boolean r8) {
        /*
            java.lang.Class<com.android.messaging.datamodel.e> r0 = com.android.messaging.datamodel.C0944e.class
            monitor-enter(r0)
            java.lang.String r8 = m2083a((int) r6, (java.lang.String) r7, (boolean) r8)     // Catch:{ all -> 0x008e }
            com.android.messaging.f r1 = com.android.messaging.C0967f.get()     // Catch:{ all -> 0x008e }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ all -> 0x008e }
            androidx.core.app.NotificationManagerCompat r1 = androidx.core.app.NotificationManagerCompat.from(r1)     // Catch:{ all -> 0x008e }
            java.util.Set r2 = f1346wx     // Catch:{ all -> 0x008e }
            monitor-enter(r2)     // Catch:{ all -> 0x008e }
            java.util.Set r3 = f1346wx     // Catch:{ all -> 0x008b }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x008b }
        L_0x001c:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x008b }
            if (r4 == 0) goto L_0x0043
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x008b }
            com.android.messaging.datamodel.L r4 = (com.android.messaging.datamodel.C0771L) r4     // Catch:{ all -> 0x008b }
            int r5 = r4.mType     // Catch:{ all -> 0x008b }
            if (r5 != r6) goto L_0x001c
            r5 = 1
            r4.mCanceled = r5     // Catch:{ all -> 0x008b }
            java.lang.String r4 = "MessagingAppNotif"
            r5 = 2
            boolean r4 = android.util.Log.isLoggable(r4, r5)     // Catch:{ all -> 0x008b }
            if (r4 == 0) goto L_0x003f
            java.lang.String r4 = "MessagingAppNotif"
            java.lang.String r5 = "Canceling pending notification"
            com.android.messaging.util.C1430e.m3628v(r4, r5)     // Catch:{ all -> 0x008b }
        L_0x003f:
            r3.remove()     // Catch:{ all -> 0x008b }
            goto L_0x001c
        L_0x0043:
            monitor-exit(r2)     // Catch:{ all -> 0x008b }
            r1.cancel(r8, r6)     // Catch:{ all -> 0x008e }
            java.lang.String r8 = "MessagingAppNotif"
            r1 = 3
            boolean r8 = android.util.Log.isLoggable(r8, r1)     // Catch:{ all -> 0x008e }
            if (r8 == 0) goto L_0x0066
            java.lang.String r8 = "MessagingAppNotif"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x008e }
            r1.<init>()     // Catch:{ all -> 0x008e }
            java.lang.String r2 = "Canceled notifications of type "
            r1.append(r2)     // Catch:{ all -> 0x008e }
            r1.append(r6)     // Catch:{ all -> 0x008e }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x008e }
            com.android.messaging.util.C1430e.m3620d(r8, r1)     // Catch:{ all -> 0x008e }
        L_0x0066:
            if (r6 != 0) goto L_0x0089
            com.android.messaging.f r6 = com.android.messaging.C0967f.get()     // Catch:{ all -> 0x008e }
            android.content.Context r6 = r6.getApplicationContext()     // Catch:{ all -> 0x008e }
            com.android.messaging.util.ConversationIdSet r8 = m2073E(r6)     // Catch:{ all -> 0x008e }
            if (r8 == 0) goto L_0x0089
            int r1 = r8.size()     // Catch:{ all -> 0x008e }
            if (r1 <= 0) goto L_0x0089
            if (r7 == 0) goto L_0x0085
            r8.remove(r7)     // Catch:{ all -> 0x008e }
            m2085a((android.content.Context) r6, (com.android.messaging.util.ConversationIdSet) r8)     // Catch:{ all -> 0x008e }
            goto L_0x0089
        L_0x0085:
            r6 = 0
            m2089a((com.android.messaging.util.ConversationIdSet) r8, (com.android.messaging.datamodel.C0771L) r6)     // Catch:{ all -> 0x008e }
        L_0x0089:
            monitor-exit(r0)
            return
        L_0x008b:
            r6 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x008b }
            throw r6     // Catch:{ all -> 0x008e }
        L_0x008e:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0944e.m2092b(int, java.lang.String, boolean):void");
    }

    /* renamed from: c */
    static CharSequence m2093c(String str, CharSequence charSequence, Uri uri, String str2) {
        Context applicationContext = C0967f.get().getApplicationContext();
        TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(applicationContext, R.style.NotificationSenderText);
        TextAppearanceSpan textAppearanceSpan2 = new TextAppearanceSpan(applicationContext, R.style.NotificationTertiaryText);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(str)) {
            spannableStringBuilder.append(str);
            spannableStringBuilder.setSpan(textAppearanceSpan, 0, str.length(), 0);
        }
        String string = applicationContext.getString(R.string.notification_separator);
        if (!TextUtils.isEmpty(charSequence)) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append(string);
            }
            int length = spannableStringBuilder.length();
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.setSpan(textAppearanceSpan2, length, charSequence.length() + length, 0);
        }
        if (uri != null) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append(string);
            }
            spannableStringBuilder.append(m2094c((String) null, str2));
        }
        return spannableStringBuilder;
    }

    private static synchronized void cancel(int i) {
        synchronized (C0944e.class) {
            m2092b(i, (String) null, false);
        }
    }

    /* renamed from: cb */
    private static void m2095cb(String str) {
        Context applicationContext = C0967f.get().getApplicationContext();
        if (!(((AudioManager) applicationContext.getSystemService("audio")).getRingerMode() != 2)) {
            C1462ma maVar = new C1462ma("MessagingApp");
            NotificationChannel d = C0107q.m133d(applicationContext, str);
            if (d == null) {
                d = C0107q.m133d(applicationContext, "messaging_channel");
            }
            maVar.mo8194a(d != null ? d.getSound() : null, false, 5, 0.25f);
            C1480va.getMainThreadHandler().postDelayed(new C0888d(maVar), 5000);
        }
    }

    /* renamed from: f */
    public static void m2096f(String str, String str2) {
        Context applicationContext = C0967f.get().getApplicationContext();
        CharSequence a = C0769J.m1239a(applicationContext, applicationContext.getString(R.string.notification_emergency_send_failure_line1, new Object[]{str}));
        String string = applicationContext.getString(R.string.notification_emergency_send_failure_line2, new Object[]{str});
        PendingIntent c = C1040Ea.get().mo6970c(applicationContext, str2, (MessageData) null);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(applicationContext, "messaging_channel");
        builder.setTicker(a).setContentTitle(a).setContentText(string).setStyle(new NotificationCompat.BigTextStyle(builder).bigText(string)).setSmallIcon(R.drawable.ic_failed_light).setContentIntent(c).setSound(C1488za.m3873i(applicationContext, R.raw.message_failure));
        C0107q.m126a(applicationContext, "conversation_group", (int) R.string.notification_channel_messages_title);
        C0107q.m127a(applicationContext, "messaging_channel", applicationContext.getString(R.string.notification_channel_messages_title), 4, (String) null);
        NotificationManagerCompat.from(applicationContext).notify(applicationContext.getPackageName() + ":emergency_sms_error", 2, builder.build());
    }

    /* renamed from: m */
    private static String m2097m(String str, String str2) {
        Context applicationContext = C0967f.get().getApplicationContext();
        if (str2 != null) {
            return applicationContext.getPackageName() + str + ":" + str2;
        }
        return applicationContext.getPackageName() + str;
    }

    /* renamed from: H */
    public static void m2075H(String str) {
        MarkAsReadAction.m1383Q(str);
        m2076I(str);
    }

    /* renamed from: c */
    static CharSequence m2094c(String str, String str2) {
        Context applicationContext = C0967f.get().getApplicationContext();
        TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(applicationContext, R.style.NotificationSecondaryText);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(str)) {
            TextAppearanceSpan textAppearanceSpan2 = new TextAppearanceSpan(applicationContext, R.style.NotificationSenderText);
            spannableStringBuilder.append(str);
            spannableStringBuilder.setSpan(textAppearanceSpan2, 0, str.length(), 0);
            spannableStringBuilder.append(applicationContext.getString(R.string.notification_separator));
        }
        int length = spannableStringBuilder.length();
        int i = R.string.notification_picture;
        if (C1481w.m3831za(str2)) {
            i = R.string.notification_audio;
        } else if (C1481w.m3830Ea(str2)) {
            i = R.string.notification_video;
        } else if (C1481w.m3829Da(str2)) {
            i = R.string.notification_vcard;
        }
        spannableStringBuilder.append(applicationContext.getText(i));
        spannableStringBuilder.setSpan(textAppearanceSpan, length, spannableStringBuilder.length(), 0);
        return spannableStringBuilder;
    }

    /* renamed from: b */
    protected static CharSequence m2091b(String str, CharSequence charSequence, Uri uri, String str2) {
        return m2081a(str, charSequence, uri, str2, R.string.notification_space_separator);
    }

    /* renamed from: a */
    private static void m2089a(ConversationIdSet conversationIdSet, C0771L l) {
        ConversationIdSet conversationIdSet2 = new ConversationIdSet();
        if (l instanceof C0766G) {
            for (C0771L l2 : ((C0766G) l).mChildren) {
                ConversationIdSet conversationIdSet3 = l2.f1013my;
                if (conversationIdSet3 != null) {
                    conversationIdSet2.add(conversationIdSet3.first());
                }
            }
        }
        Iterator it = conversationIdSet.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!conversationIdSet2.contains(str)) {
                m2092b(0, str, true);
            }
        }
    }

    /* renamed from: a */
    static String m2082a(int i, String str) {
        return m2083a(i, str, false);
    }

    /* renamed from: a */
    static String m2083a(int i, String str, boolean z) {
        if (i != 0) {
            if (i != 2) {
                return null;
            }
            return m2097m(":error:", (String) null);
        } else if (z) {
            return m2097m(":sms:", str);
        } else {
            return m2097m(":sms:", (String) null);
        }
    }

    /* JADX WARNING: type inference failed for: r4v8, types: [com.android.messaging.datamodel.b.I] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0172  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m2088a(com.android.messaging.datamodel.C0771L r20, boolean r21, boolean r22) {
        /*
            r0 = r20
            com.android.messaging.f r1 = com.android.messaging.C0967f.get()
            android.content.Context r1 = r1.getApplicationContext()
            com.android.messaging.util.ConversationIdSet r2 = r0.f1013my
            java.lang.String r2 = r2.first()
            android.app.NotificationChannel r3 = android.support.p016v4.media.session.C0107q.m133d(r1, r2)
            if (r3 == 0) goto L_0x0018
            r3 = r2
            goto L_0x001a
        L_0x0018:
            java.lang.String r3 = "messaging_channel"
        L_0x001a:
            androidx.core.app.NotificationCompat$Builder r4 = new androidx.core.app.NotificationCompat$Builder
            r4.<init>(r1, r3)
            java.lang.String r3 = "msg"
            r4.setCategory(r3)
            if (r22 == 0) goto L_0x0049
            r0 = 2
            java.lang.String r1 = "MessagingAppNotif"
            boolean r0 = android.util.Log.isLoggable(r1, r0)
            if (r0 == 0) goto L_0x0045
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "processAndSend: fromConversationId == sCurrentlyDisplayedConversationId so NOT showing notification, but playing soft sound. conversationId: "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "MessagingAppNotif"
            com.android.messaging.util.C1430e.m3628v(r1, r0)
        L_0x0045:
            m2095cb(r2)
            return
        L_0x0049:
            int r3 = r0.mType
            r0.f1016py = r3
            boolean r3 = r0 instanceof com.android.messaging.datamodel.C0762C
            r5 = 1
            if (r3 != 0) goto L_0x006d
            r3 = r0
            com.android.messaging.datamodel.J r3 = (com.android.messaging.datamodel.C0769J) r3
            com.android.messaging.ui.Ea r6 = com.android.messaging.p041ui.C1040Ea.get()
            com.android.messaging.f r7 = com.android.messaging.C0967f.get()
            android.content.Context r7 = r7.getApplicationContext()
            com.android.messaging.util.ConversationIdSet r8 = r3.f1013my
            int r3 = r3.f1016py
            int r3 = r3 + r5
            android.app.PendingIntent r3 = r6.mo6949a((android.content.Context) r7, (int) r5, (com.android.messaging.util.ConversationIdSet) r8, (int) r3)
            r4.setDeleteIntent(r3)
        L_0x006d:
            r3 = r21
            m2087a((com.android.messaging.datamodel.C0771L) r0, (androidx.core.app.NotificationCompat.Builder) r4, (boolean) r3, (java.lang.String) r2)
            com.android.messaging.util.ConversationIdSet r2 = r0.f1013my
            int r2 = r2.size()
            r3 = 0
            if (r2 <= r5) goto L_0x0084
            com.android.messaging.ui.Ea r2 = com.android.messaging.p041ui.C1040Ea.get()
            android.app.PendingIntent r2 = r2.mo6981r(r1)
            goto L_0x0092
        L_0x0084:
            com.android.messaging.ui.Ea r2 = com.android.messaging.p041ui.C1040Ea.get()
            com.android.messaging.util.ConversationIdSet r6 = r0.f1013my
            java.lang.String r6 = r6.first()
            android.app.PendingIntent r2 = r2.mo6970c((android.content.Context) r1, (java.lang.String) r6, (com.android.messaging.datamodel.data.MessageData) r3)
        L_0x0092:
            r4.setContentIntent(r2)
            r2 = r0
            com.android.messaging.datamodel.J r2 = (com.android.messaging.datamodel.C0769J) r2
            r4.setPriority(r5)
            androidx.core.app.NotificationCompat$Style r2 = r0.mo5878a(r4)
            r0.f1015oy = r4
            r0.f1014ny = r2
            java.util.HashSet r2 = r0.mPeople
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x00c6
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            java.util.HashSet r5 = r0.mPeople
            int r6 = r5.size()
            java.lang.String[] r6 = new java.lang.String[r6]
            java.lang.Object[] r5 = r5.toArray(r6)
            java.lang.String[] r5 = (java.lang.String[]) r5
            java.lang.String r6 = "android.people"
            r2.putStringArray(r6, r5)
            r4.addExtras(r2)
        L_0x00c6:
            java.util.ArrayList r2 = r0.f1017qy
            if (r2 == 0) goto L_0x0179
            r4 = 0
            java.lang.Object r2 = r2.get(r4)
            android.net.Uri r2 = (android.net.Uri) r2
            com.android.messaging.datamodel.b.d r10 = new com.android.messaging.datamodel.b.d
            int r6 = f1349zx
            int r7 = f1343Ax
            boolean r8 = com.android.messaging.util.C1464na.m3758Yj()
            r9 = 0
            r4 = r10
            r5 = r2
            r4.<init>(r5, r6, r7, r8, r9)
            com.android.messaging.datamodel.b.w r4 = r10.mo6084n(r1)
            java.util.Set r5 = f1346wx
            monitor-enter(r5)
            java.util.Set r6 = f1346wx     // Catch:{ all -> 0x0176 }
            r6.add(r0)     // Catch:{ all -> 0x0176 }
            monitor-exit(r5)     // Catch:{ all -> 0x0176 }
            com.android.messaging.datamodel.b.C r5 = com.android.messaging.datamodel.p038b.C0840C.get()
            com.android.messaging.datamodel.b.I r4 = r5.mo6082b(r4)
            r10 = r4
            com.android.messaging.datamodel.b.u r10 = (com.android.messaging.datamodel.p038b.C0881u) r10
            if (r10 == 0) goto L_0x0179
            boolean r4 = m2077Td()     // Catch:{ all -> 0x016b }
            if (r4 == 0) goto L_0x014a
            android.net.Uri r12 = m2074H((android.net.Uri) r2)     // Catch:{ all -> 0x016b }
            com.android.messaging.datamodel.b.L r4 = new com.android.messaging.datamodel.b.L     // Catch:{ all -> 0x016b }
            int r13 = f1347xx     // Catch:{ all -> 0x016b }
            int r14 = f1348yx     // Catch:{ all -> 0x016b }
            r15 = 0
            r16 = 1
            r17 = 0
            r18 = 0
            r19 = 0
            r11 = r4
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ all -> 0x016b }
            com.android.messaging.datamodel.b.C r5 = com.android.messaging.datamodel.p038b.C0840C.get()     // Catch:{ all -> 0x016b }
            com.android.messaging.datamodel.b.w r4 = r4.mo6084n(r1)     // Catch:{ all -> 0x016b }
            com.android.messaging.datamodel.b.I r4 = r5.mo6082b(r4)     // Catch:{ all -> 0x016b }
            r11 = r4
            com.android.messaging.datamodel.b.u r11 = (com.android.messaging.datamodel.p038b.C0881u) r11     // Catch:{ all -> 0x016b }
            if (r11 != 0) goto L_0x0148
            com.android.messaging.datamodel.b.d r12 = new com.android.messaging.datamodel.b.d     // Catch:{ all -> 0x0145 }
            int r6 = f1347xx     // Catch:{ all -> 0x0145 }
            int r7 = f1348yx     // Catch:{ all -> 0x0145 }
            r8 = 0
            r9 = 1
            r4 = r12
            r5 = r2
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0145 }
            com.android.messaging.datamodel.b.C r2 = com.android.messaging.datamodel.p038b.C0840C.get()     // Catch:{ all -> 0x0145 }
            com.android.messaging.datamodel.b.w r1 = r12.mo6084n(r1)     // Catch:{ all -> 0x0145 }
            com.android.messaging.datamodel.b.I r1 = r2.mo6082b(r1)     // Catch:{ all -> 0x0145 }
            com.android.messaging.datamodel.b.u r1 = (com.android.messaging.datamodel.p038b.C0881u) r1     // Catch:{ all -> 0x0145 }
            goto L_0x014b
        L_0x0145:
            r0 = move-exception
            r1 = r11
            goto L_0x016d
        L_0x0148:
            r1 = r11
            goto L_0x014b
        L_0x014a:
            r1 = r3
        L_0x014b:
            android.graphics.Bitmap r2 = r10.getBitmap()     // Catch:{ all -> 0x0169 }
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createBitmap(r2)     // Catch:{ all -> 0x0169 }
            if (r1 == 0) goto L_0x015d
            android.graphics.Bitmap r3 = r1.getBitmap()     // Catch:{ all -> 0x0169 }
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r3)     // Catch:{ all -> 0x0169 }
        L_0x015d:
            m2086a((com.android.messaging.datamodel.C0771L) r0, (android.graphics.Bitmap) r2, (android.graphics.Bitmap) r3)     // Catch:{ all -> 0x0169 }
            r10.release()
            if (r1 == 0) goto L_0x0168
            r1.release()
        L_0x0168:
            return
        L_0x0169:
            r0 = move-exception
            goto L_0x016d
        L_0x016b:
            r0 = move-exception
            r1 = r3
        L_0x016d:
            r10.release()
            if (r1 == 0) goto L_0x0175
            r1.release()
        L_0x0175:
            throw r0
        L_0x0176:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0176 }
            throw r0
        L_0x0179:
            m2086a((com.android.messaging.datamodel.C0771L) r0, (android.graphics.Bitmap) r3, (android.graphics.Bitmap) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0944e.m2088a(com.android.messaging.datamodel.L, boolean, boolean):void");
    }

    /* renamed from: a */
    private static void m2087a(C0771L l, NotificationCompat.Builder builder, boolean z, String str) {
        if (!z) {
            C1451h Hd = C0967f.get().mo6645Hd();
            long j = Hd.getLong("latest_notification_message_timestamp", Long.MIN_VALUE);
            long ke = l.mo5880ke();
            Hd.putLong("latest_notification_message_timestamp", Math.max(j, ke));
            if (ke > j) {
                synchronized (mLock) {
                    Long l2 = (Long) f1344Bx.get(str);
                    if (f1345Cx == 0) {
                        C1449g.get().getInt("bugle_notification_time_between_rings_seconds", 10);
                        f1345Cx = 10000;
                    }
                    if (l2 == null || SystemClock.elapsedRealtime() - l2.longValue() > ((long) f1345Cx)) {
                        f1344Bx.put(str, Long.valueOf(SystemClock.elapsedRealtime()));
                    }
                }
            }
        }
        builder.setDefaults(6);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r7v17 */
    /* JADX WARNING: type inference failed for: r7v19 */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0286, code lost:
        if (com.android.messaging.sms.C1029y.m2403Ea(r3.mSubId) != false) goto L_0x028a;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r7v3, types: [int, boolean] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0113  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void m2086a(com.android.messaging.datamodel.C0771L r19, android.graphics.Bitmap r20, android.graphics.Bitmap r21) {
        /*
            r0 = r19
            r1 = r20
            r2 = r21
            com.android.messaging.f r3 = com.android.messaging.C0967f.get()
            android.content.Context r3 = r3.getApplicationContext()
            boolean r4 = r0.mCanceled
            r5 = 3
            if (r4 == 0) goto L_0x0023
            java.lang.String r0 = "MessagingAppNotif"
            boolean r0 = android.util.Log.isLoggable(r0, r5)
            if (r0 == 0) goto L_0x0022
            java.lang.String r0 = "MessagingAppNotif"
            java.lang.String r1 = "sendNotification: Notification already cancelled; dropping it"
            com.android.messaging.util.C1430e.m3620d(r0, r1)
        L_0x0022:
            return
        L_0x0023:
            java.util.Set r4 = f1346wx
            monitor-enter(r4)
            java.util.Set r6 = f1346wx     // Catch:{ all -> 0x0319 }
            boolean r6 = r6.contains(r0)     // Catch:{ all -> 0x0319 }
            if (r6 == 0) goto L_0x0033
            java.util.Set r6 = f1346wx     // Catch:{ all -> 0x0319 }
            r6.remove(r0)     // Catch:{ all -> 0x0319 }
        L_0x0033:
            monitor-exit(r4)     // Catch:{ all -> 0x0319 }
            androidx.core.app.NotificationCompat$Builder r4 = r0.f1015oy
            int r6 = r19.getIcon()
            androidx.core.app.NotificationCompat$Builder r4 = r4.setSmallIcon(r6)
            r6 = 0
            androidx.core.app.NotificationCompat$Builder r4 = r4.setVisibility(r6)
            android.content.res.Resources r7 = r3.getResources()
            r8 = 2131099807(0x7f06009f, float:1.7811978E38)
            int r7 = r7.getColor(r8)
            androidx.core.app.NotificationCompat$Builder r4 = r4.setColor(r7)
            java.lang.String r7 = "msg"
            r4.setCategory(r7)
            if (r1 == 0) goto L_0x005e
            androidx.core.app.NotificationCompat$Builder r4 = r0.f1015oy
            r4.setLargeIcon(r1)
        L_0x005e:
            java.util.ArrayList r4 = r0.f1018ry
            if (r4 == 0) goto L_0x0084
            int r4 = r4.size()
            if (r4 <= 0) goto L_0x0084
            java.util.ArrayList r4 = r0.f1018ry
            java.util.Iterator r4 = r4.iterator()
        L_0x006e:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x0084
            java.lang.Object r7 = r4.next()
            android.net.Uri r7 = (android.net.Uri) r7
            androidx.core.app.NotificationCompat$Builder r9 = r0.f1015oy
            java.lang.String r7 = r7.toString()
            r9.addPerson(r7)
            goto L_0x006e
        L_0x0084:
            r4 = r0
            com.android.messaging.datamodel.J r4 = (com.android.messaging.datamodel.C0769J) r4
            android.net.Uri r10 = r4.f1004ey
            java.lang.String r4 = r4.f1005fy
            r15 = 1
            if (r10 == 0) goto L_0x00fd
            androidx.core.app.NotificationCompat$Style r9 = r0.f1014ny
            if (r9 == 0) goto L_0x00fd
            boolean r9 = r9 instanceof androidx.core.app.NotificationCompat.BigPictureStyle
            if (r9 == 0) goto L_0x00fd
            boolean r9 = com.android.messaging.util.C1481w.isImageType(r4)
            if (r9 != 0) goto L_0x00a2
            boolean r9 = com.android.messaging.util.C1481w.m3830Ea(r4)
            if (r9 == 0) goto L_0x00fd
        L_0x00a2:
            boolean r4 = com.android.messaging.util.C1481w.m3830Ea(r4)
            if (r4 == 0) goto L_0x00bb
            boolean r4 = com.android.messaging.util.C1452ha.m3728Qj()
            com.android.messaging.util.C1424b.m3592ia(r4)
            com.android.messaging.datamodel.b.E r4 = new com.android.messaging.datamodel.b.E
            r4.<init>((android.net.Uri) r10)
            com.android.messaging.datamodel.b.W r9 = new com.android.messaging.datamodel.b.W
            r9.<init>(r3, r4)
            r7 = r15
            goto L_0x00d8
        L_0x00bb:
            com.android.messaging.datamodel.b.L r4 = new com.android.messaging.datamodel.b.L
            int r11 = f1347xx
            int r12 = f1348yx
            r13 = 0
            r14 = 1
            r16 = 0
            r17 = 0
            r18 = 0
            r9 = r4
            r7 = r15
            r15 = r16
            r16 = r17
            r17 = r18
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17)
            com.android.messaging.datamodel.b.w r9 = r4.mo6084n(r3)
        L_0x00d8:
            com.android.messaging.datamodel.b.C r3 = com.android.messaging.datamodel.p038b.C0840C.get()
            com.android.messaging.datamodel.b.I r3 = r3.mo6082b(r9)
            com.android.messaging.datamodel.b.u r3 = (com.android.messaging.datamodel.p038b.C0881u) r3
            if (r3 == 0) goto L_0x00fe
            android.graphics.Bitmap r4 = r3.getBitmap()     // Catch:{ all -> 0x00f8 }
            android.graphics.Bitmap$Config r9 = r4.getConfig()     // Catch:{ all -> 0x00f8 }
            if (r9 != 0) goto L_0x00f0
            android.graphics.Bitmap$Config r9 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ all -> 0x00f8 }
        L_0x00f0:
            android.graphics.Bitmap r4 = r4.copy(r9, r7)     // Catch:{ all -> 0x00f8 }
            r3.release()
            goto L_0x00ff
        L_0x00f8:
            r0 = move-exception
            r3.release()
            throw r0
        L_0x00fd:
            r7 = r15
        L_0x00fe:
            r4 = 0
        L_0x00ff:
            boolean r3 = r0.mCanceled
            r9 = 2
            java.lang.String r10 = "MessagingAppNotif"
            if (r3 == 0) goto L_0x0113
            boolean r0 = android.util.Log.isLoggable(r10, r9)
            if (r0 == 0) goto L_0x0318
            java.lang.String r0 = "Firing off notification, but notification already canceled"
            com.android.messaging.util.C1430e.m3628v(r10, r0)
            goto L_0x0318
        L_0x0113:
            com.android.messaging.f r3 = com.android.messaging.C0967f.get()
            android.content.Context r3 = r3.getApplicationContext()
            boolean r11 = android.util.Log.isLoggable(r10, r9)
            if (r11 == 0) goto L_0x0135
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "MMS picture loaded, bitmap: "
            r11.append(r12)
            r11.append(r4)
            java.lang.String r11 = r11.toString()
            com.android.messaging.util.C1430e.m3628v(r10, r11)
        L_0x0135:
            androidx.core.app.NotificationCompat$Builder r11 = r0.f1015oy
            androidx.core.app.NotificationCompat$Style r12 = r0.f1014ny
            r11.setStyle(r12)
            android.content.res.Resources r12 = r3.getResources()
            int r8 = r12.getColor(r8)
            r11.setColor(r8)
            androidx.core.app.NotificationCompat$WearableExtender r8 = new androidx.core.app.NotificationCompat$WearableExtender
            r8.<init>()
            boolean r9 = android.util.Log.isLoggable(r10, r9)
            if (r9 == 0) goto L_0x0157
            java.lang.String r9 = "Group key (for wearables)=groupkey"
            com.android.messaging.util.C1430e.m3628v(r10, r9)
        L_0x0157:
            boolean r9 = r0 instanceof com.android.messaging.datamodel.C0766G
            java.lang.String r10 = "groupkey"
            if (r9 == 0) goto L_0x0165
            androidx.core.app.NotificationCompat$Builder r9 = r11.setGroup(r10)
            r9.setGroupSummary(r7)
            goto L_0x0185
        L_0x0165:
            boolean r9 = r0 instanceof com.android.messaging.datamodel.C0762C
            if (r9 == 0) goto L_0x0185
            r9 = r0
            com.android.messaging.datamodel.C r9 = (com.android.messaging.datamodel.C0762C) r9
            int r9 = r9.f984wy
            java.util.Locale r12 = java.util.Locale.US
            java.lang.Object[] r13 = new java.lang.Object[r7]
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r13[r6] = r9
            java.lang.String r9 = "%02d"
            java.lang.String r9 = java.lang.String.format(r12, r9, r13)
            androidx.core.app.NotificationCompat$Builder r10 = r11.setGroup(r10)
            r10.setSortKey(r9)
        L_0x0185:
            if (r2 == 0) goto L_0x018b
            r8.setBackground(r2)
            goto L_0x019c
        L_0x018b:
            if (r1 == 0) goto L_0x018e
            goto L_0x019c
        L_0x018e:
            android.content.res.Resources r2 = r3.getResources()
            r9 = 2131230815(0x7f08005f, float:1.8077693E38)
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeResource(r2, r9)
            r8.setBackground(r2)
        L_0x019c:
            boolean r2 = r0 instanceof com.android.messaging.datamodel.C0767H
            if (r2 == 0) goto L_0x030e
            if (r4 == 0) goto L_0x01ef
            int r9 = f1349zx
            int r10 = f1343Ax
            android.graphics.Bitmap r9 = com.android.messaging.util.C1416U.m3562a((android.graphics.Bitmap) r4, (int) r9, (int) r10)
            androidx.core.app.NotificationCompat$Style r10 = r0.f1014ny
            androidx.core.app.NotificationCompat$BigPictureStyle r10 = (androidx.core.app.NotificationCompat.BigPictureStyle) r10
            androidx.core.app.NotificationCompat$BigPictureStyle r10 = r10.bigPicture(r4)
            r10.bigLargeIcon(r1)
            androidx.core.app.NotificationCompat$Builder r1 = r0.f1015oy
            r1.setLargeIcon(r9)
            com.android.messaging.util.ConversationIdSet r1 = r0.f1013my
            java.lang.String r1 = r1.first()
            android.support.p016v4.media.session.C0107q.m133d(r3, r1)
            androidx.core.app.NotificationCompat$Builder r1 = new androidx.core.app.NotificationCompat$Builder
            com.android.messaging.f r3 = com.android.messaging.C0967f.get()
            android.content.Context r3 = r3.getApplicationContext()
            java.lang.String r9 = "messaging_channel"
            r1.<init>(r3, r9)
            androidx.core.app.NotificationCompat$WearableExtender r3 = new androidx.core.app.NotificationCompat$WearableExtender
            r3.<init>()
            r3.setHintShowBackgroundOnly(r7)
            int r9 = f1347xx
            int r10 = f1348yx
            android.graphics.Bitmap r4 = com.android.messaging.util.C1416U.m3562a((android.graphics.Bitmap) r4, (int) r9, (int) r10)
            r3.setBackground(r4)
            r1.extend(r3)
            android.app.Notification r1 = r1.build()
            r8.addPage(r1)
        L_0x01ef:
            r1 = r0
            com.android.messaging.datamodel.H r1 = (com.android.messaging.datamodel.C0767H) r1
            boolean r3 = m2077Td()
            if (r3 != 0) goto L_0x01f9
            goto L_0x0214
        L_0x01f9:
            com.android.messaging.util.ConversationIdSet r3 = r1.f1013my
            java.lang.String r3 = r3.first()
            com.android.messaging.datamodel.D r4 = r1.f1008uy
            java.util.List r4 = r4.f986Vx
            java.lang.Object r4 = r4.get(r6)
            com.android.messaging.datamodel.E r4 = (com.android.messaging.datamodel.C0764E) r4
            int r4 = r4.f996dy
            android.app.Notification r3 = com.android.messaging.datamodel.C0769J.m1242f(r3, r4)
            if (r3 == 0) goto L_0x0214
            r8.addPage(r3)
        L_0x0214:
            if (r2 != 0) goto L_0x0217
            goto L_0x0254
        L_0x0217:
            com.android.messaging.datamodel.D r3 = r1.f1008uy
            java.util.List r3 = r3.f986Vx
            java.lang.Object r3 = r3.get(r6)
            com.android.messaging.datamodel.E r3 = (com.android.messaging.datamodel.C0764E) r3
            boolean r4 = r3.mo5868fe()
            if (r4 != 0) goto L_0x0228
            goto L_0x0254
        L_0x0228:
            java.lang.String r3 = r3.mo5869ge()
            if (r3 != 0) goto L_0x022f
            goto L_0x0254
        L_0x022f:
            com.android.messaging.f r4 = com.android.messaging.C0967f.get()
            android.content.Context r4 = r4.getApplicationContext()
            android.app.PendingIntent r3 = com.android.messaging.datamodel.action.RedownloadMmsAction.m1436e(r4, r3)
            androidx.core.app.NotificationCompat$Action$Builder r9 = new androidx.core.app.NotificationCompat$Action$Builder
            r10 = 2131230900(0x7f0800b4, float:1.8077866E38)
            r12 = 2131820871(0x7f110147, float:1.927447E38)
            java.lang.String r4 = r4.getString(r12)
            r9.<init>(r10, r4, r3)
            androidx.core.app.NotificationCompat$Action r3 = r9.build()
            r11.addAction(r3)
            r8.addAction(r3)
        L_0x0254:
            if (r2 != 0) goto L_0x0258
            goto L_0x02e5
        L_0x0258:
            com.android.messaging.f r2 = com.android.messaging.C0967f.get()
            android.content.Context r2 = r2.getApplicationContext()
            com.android.messaging.util.ConversationIdSet r3 = r0.f1013my
            java.lang.String r14 = r3.first()
            com.android.messaging.datamodel.D r3 = r1.f1008uy
            java.util.List r3 = r3.f986Vx
            java.lang.Object r3 = r3.get(r6)
            com.android.messaging.datamodel.E r3 = (com.android.messaging.datamodel.C0764E) r3
            java.lang.String r15 = r3.f992_x
            boolean r4 = r3.f990Yx
            int r9 = r3.mSubId
            boolean r4 = com.android.messaging.sms.C1027w.m2399c(r4, r9)
            if (r4 != 0) goto L_0x028a
            boolean r4 = r3.f988Wx
            if (r4 == 0) goto L_0x0289
            int r3 = r3.mSubId
            boolean r3 = com.android.messaging.sms.C1029y.m2403Ea(r3)
            if (r3 == 0) goto L_0x0289
            goto L_0x028a
        L_0x0289:
            r7 = r6
        L_0x028a:
            int r1 = r1.f1016py
            int r1 = r1 + r5
            int r17 = r1 + 0
            com.android.messaging.ui.Ea r12 = com.android.messaging.p041ui.C1040Ea.get()
            r13 = r2
            r16 = r7
            android.app.PendingIntent r1 = r12.mo6951a((android.content.Context) r13, (java.lang.String) r14, (java.lang.String) r15, (boolean) r16, (int) r17)
            if (r7 == 0) goto L_0x02a0
            r3 = 2131820879(0x7f11014f, float:1.9274485E38)
            goto L_0x02a3
        L_0x02a0:
            r3 = 2131820880(0x7f110150, float:1.9274487E38)
        L_0x02a3:
            androidx.core.app.NotificationCompat$Action$Builder r4 = new androidx.core.app.NotificationCompat$Action$Builder
            r5 = 2131230958(0x7f0800ee, float:1.8077983E38)
            java.lang.String r3 = r2.getString(r3)
            r4.<init>(r5, r3, r1)
            androidx.core.app.NotificationCompat$Action r1 = r4.build()
            r11.addAction(r1)
            android.content.res.Resources r1 = r2.getResources()
            r3 = 2130903042(0x7f030002, float:1.741289E38)
            java.lang.String[] r1 = r1.getStringArray(r3)
            androidx.core.app.RemoteInput$Builder r3 = new androidx.core.app.RemoteInput$Builder
            java.lang.String r5 = "android.intent.extra.TEXT"
            r3.<init>(r5)
            r5 = 2131820878(0x7f11014e, float:1.9274483E38)
            java.lang.String r2 = r2.getString(r5)
            androidx.core.app.RemoteInput$Builder r2 = r3.setLabel(r2)
            androidx.core.app.RemoteInput$Builder r1 = r2.setChoices(r1)
            androidx.core.app.RemoteInput r1 = r1.build()
            r4.addRemoteInput(r1)
            androidx.core.app.NotificationCompat$Action r1 = r4.build()
            r8.addAction(r1)
        L_0x02e5:
            com.android.messaging.f r1 = com.android.messaging.C0967f.get()
            android.content.Context r1 = r1.getApplicationContext()
            android.app.PendingIntent r2 = r19.mo5881le()
            androidx.core.app.NotificationCompat$Action$Builder r3 = new androidx.core.app.NotificationCompat$Action$Builder
            r4 = 2131820874(0x7f11014a, float:1.9274475E38)
            java.lang.String r1 = r1.getString(r4)
            r4 = 2131230957(0x7f0800ed, float:1.8077981E38)
            r3.<init>(r4, r1, r2)
            androidx.core.app.NotificationCompat$Action r1 = r3.build()
            r11.addAction(r1)
            androidx.core.app.NotificationCompat$Action r1 = r3.build()
            r8.addAction(r1)
        L_0x030e:
            r11.extend(r8)
            android.app.Notification r1 = r11.build()
            m2084a((android.app.Notification) r1, (com.android.messaging.datamodel.C0771L) r0)
        L_0x0318:
            return
        L_0x0319:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0319 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.messaging.datamodel.C0944e.m2086a(com.android.messaging.datamodel.L, android.graphics.Bitmap, android.graphics.Bitmap):void");
    }

    /* renamed from: a */
    private static synchronized void m2084a(Notification notification, C0771L l) {
        synchronized (C0944e.class) {
            if (notification != null) {
                int i = l.mType;
                ConversationIdSet conversationIdSet = l.f1013my;
                boolean z = l instanceof C0762C;
                l.mCanceled = true;
                NotificationManagerCompat from = NotificationManagerCompat.from(C0967f.get().getApplicationContext());
                String str = null;
                if (conversationIdSet != null && conversationIdSet.size() == 1) {
                    str = conversationIdSet.first();
                }
                String a = m2083a(i, str, z);
                notification.flags |= 16;
                notification.defaults |= 4;
                Context applicationContext = C0967f.get().getApplicationContext();
                C0107q.m126a(applicationContext, "conversation_group", (int) R.string.notification_channel_messages_title);
                C0107q.m127a(applicationContext, "messaging_channel", applicationContext.getString(R.string.notification_channel_messages_title), 3, "conversation_group");
                from.notify(a, i, notification);
                C1430e.m3625i("MessagingAppNotif", "Notifying for conversation " + str + "; tag = " + a + ", type = " + i);
            }
        }
    }

    /* renamed from: a */
    protected static CharSequence m2080a(String str, CharSequence charSequence, Uri uri, String str2) {
        return m2081a(str, charSequence, uri, str2, R.string.notification_ticker_separator);
    }

    /* renamed from: a */
    private static CharSequence m2081a(String str, CharSequence charSequence, Uri uri, String str2, int i) {
        Context applicationContext = C0967f.get().getApplicationContext();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(str)) {
            spannableStringBuilder.append(str);
            spannableStringBuilder.setSpan(new StyleSpan(1), 0, str.length(), 33);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append(applicationContext.getString(i));
            }
            spannableStringBuilder.append(charSequence);
        }
        if (uri != null) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append(applicationContext.getString(R.string.notification_separator));
            }
            spannableStringBuilder.append(m2094c((String) null, str2));
        }
        return spannableStringBuilder;
    }

    /* renamed from: a */
    private static void m2085a(Context context, ConversationIdSet conversationIdSet) {
        if (!conversationIdSet.equals(m2073E(context))) {
            C1451h.m3724Hd().putString(context.getString(R.string.notifications_group_children_key), conversationIdSet.mo8037Ul());
        }
    }
}
