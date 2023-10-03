package p000;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.RemoteViews;
import java.util.ArrayList;

/* renamed from: hr */
/* compiled from: PG */
public final class C0213hr {

    /* renamed from: a */
    public final Notification.Builder f13289a;

    /* renamed from: b */
    public final C0211hp f13290b;

    /* renamed from: c */
    private final Bundle f13291c = new Bundle();

    public C0213hr(C0211hp hpVar) {
        boolean z;
        new ArrayList();
        this.f13290b = hpVar;
        int i = Build.VERSION.SDK_INT;
        this.f13289a = new Notification.Builder(hpVar.f13191a, hpVar.f13201k);
        Notification notification = hpVar.f13202l;
        Notification.Builder deleteIntent = this.f13289a.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, (RemoteViews) null).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(hpVar.f13194d).setContentText(hpVar.f13195e).setContentInfo((CharSequence) null).setContentIntent(hpVar.f13196f).setDeleteIntent(notification.deleteIntent);
        if ((notification.flags & 128) != 0) {
            z = true;
        } else {
            z = false;
        }
        deleteIntent.setFullScreenIntent((PendingIntent) null, z).setLargeIcon((Bitmap) null).setNumber(0).setProgress(0, 0, false);
        int i2 = Build.VERSION.SDK_INT;
        int i3 = Build.VERSION.SDK_INT;
        this.f13289a.setSubText((CharSequence) null).setUsesChronometer(false).setPriority(hpVar.f13197g);
        ArrayList arrayList = hpVar.f13192b;
        int size = arrayList.size();
        for (int i4 = 0; i4 < size; i4++) {
            C0350mt mtVar = (C0350mt) arrayList.get(i4);
            int i5 = Build.VERSION.SDK_INT;
            int i6 = Build.VERSION.SDK_INT;
            Notification.Action.Builder builder = new Notification.Action.Builder((Icon) null, (CharSequence) null, (PendingIntent) null);
            Bundle bundle = new Bundle((Bundle) null);
            bundle.putBoolean("android.support.allowGeneratedReplies", true);
            int i7 = Build.VERSION.SDK_INT;
            builder.setAllowGeneratedReplies(true);
            bundle.putInt("android.support.action.semanticAction", 0);
            if (Build.VERSION.SDK_INT >= 28) {
                builder.setSemanticAction(0);
            }
            if (Build.VERSION.SDK_INT >= 29) {
                builder.setContextual(false);
            }
            bundle.putBoolean("android.support.action.showsUserInterface", false);
            builder.addExtras(bundle);
            this.f13289a.addAction(builder.build());
        }
        Bundle bundle2 = hpVar.f13200j;
        if (bundle2 != null) {
            this.f13291c.putAll(bundle2);
        }
        int i8 = Build.VERSION.SDK_INT;
        int i9 = Build.VERSION.SDK_INT;
        this.f13289a.setShowWhen(true);
        int i10 = Build.VERSION.SDK_INT;
        int i11 = Build.VERSION.SDK_INT;
        this.f13289a.setLocalOnly(hpVar.f13199i).setGroup((String) null).setGroupSummary(false).setSortKey((String) null);
        int i12 = Build.VERSION.SDK_INT;
        this.f13289a.setCategory((String) null).setColor(0).setVisibility(0).setPublicVersion((Notification) null).setSound(notification.sound, notification.audioAttributes);
        ArrayList arrayList2 = hpVar.f13203m;
        int size2 = arrayList2.size();
        for (int i13 = 0; i13 < size2; i13++) {
            this.f13289a.addPerson((String) arrayList2.get(i13));
        }
        if (hpVar.f13193c.size() > 0) {
            Bundle bundle3 = hpVar.mo7637a().getBundle("android.car.EXTENSIONS");
            bundle3 = bundle3 == null ? new Bundle() : bundle3;
            Bundle bundle4 = new Bundle();
            for (int i14 = 0; i14 < hpVar.f13193c.size(); i14++) {
                String num = Integer.toString(i14);
                C0350mt mtVar2 = (C0350mt) hpVar.f13193c.get(i14);
                Bundle bundle5 = new Bundle();
                bundle5.putInt("icon", 0);
                bundle5.putCharSequence("title", (CharSequence) null);
                bundle5.putParcelable("actionIntent", (Parcelable) null);
                Bundle bundle6 = new Bundle((Bundle) null);
                bundle6.putBoolean("android.support.allowGeneratedReplies", true);
                bundle5.putBundle("extras", bundle6);
                bundle5.putParcelableArray("remoteInputs", (Parcelable[]) null);
                bundle5.putBoolean("showsUserInterface", false);
                bundle5.putInt("semanticAction", 0);
                bundle4.putBundle(num, bundle5);
            }
            bundle3.putBundle("invisible_actions", bundle4);
            hpVar.mo7637a().putBundle("android.car.EXTENSIONS", bundle3);
            this.f13291c.putBundle("android.car.EXTENSIONS", bundle3);
        }
        int i15 = Build.VERSION.SDK_INT;
        this.f13289a.setExtras(hpVar.f13200j).setRemoteInputHistory((CharSequence[]) null);
        int i16 = Build.VERSION.SDK_INT;
        this.f13289a.setBadgeIconType(0).setShortcutId((String) null).setTimeoutAfter(0).setGroupAlertBehavior(0);
        if (!TextUtils.isEmpty(hpVar.f13201k)) {
            this.f13289a.setSound((Uri) null).setDefaults(0).setLights(0, 0, 0).setVibrate((long[]) null);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f13289a.setAllowSystemGeneratedContextualActions(true);
            this.f13289a.setBubbleMetadata((Notification.BubbleMetadata) null);
        }
    }
}
