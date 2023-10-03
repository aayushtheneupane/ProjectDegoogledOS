package p010me.leolin.shortcutbadger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import p010me.leolin.shortcutbadger.impl.AdwHomeBadger;
import p010me.leolin.shortcutbadger.impl.ApexHomeBadger;
import p010me.leolin.shortcutbadger.impl.AsusHomeBadger;
import p010me.leolin.shortcutbadger.impl.DefaultBadger;
import p010me.leolin.shortcutbadger.impl.HuaweiHomeBadger;
import p010me.leolin.shortcutbadger.impl.NewHtcHomeBadger;
import p010me.leolin.shortcutbadger.impl.NovaHomeBadger;
import p010me.leolin.shortcutbadger.impl.OPPOHomeBader;
import p010me.leolin.shortcutbadger.impl.SamsungHomeBadger;
import p010me.leolin.shortcutbadger.impl.SonyHomeBadger;
import p010me.leolin.shortcutbadger.impl.VivoHomeBadger;
import p010me.leolin.shortcutbadger.impl.XiaomiHomeBadger;
import p010me.leolin.shortcutbadger.impl.ZukHomeBadger;

/* renamed from: me.leolin.shortcutbadger.ShortcutBadger */
public final class ShortcutBadger {
    private static final List<Class<? extends Badger>> BADGERS = new LinkedList();
    private static ComponentName sComponentName;
    private static Badger sShortcutBadger;

    static {
        BADGERS.add(AdwHomeBadger.class);
        BADGERS.add(ApexHomeBadger.class);
        BADGERS.add(NewHtcHomeBadger.class);
        BADGERS.add(NovaHomeBadger.class);
        BADGERS.add(SonyHomeBadger.class);
        BADGERS.add(XiaomiHomeBadger.class);
        BADGERS.add(AsusHomeBadger.class);
        BADGERS.add(HuaweiHomeBadger.class);
        BADGERS.add(OPPOHomeBader.class);
        BADGERS.add(SamsungHomeBadger.class);
        BADGERS.add(ZukHomeBadger.class);
        BADGERS.add(VivoHomeBadger.class);
    }

    public static boolean applyCount(Context context, int i) {
        try {
            applyCountOrThrow(context, i);
            return true;
        } catch (ShortcutBadgeException e) {
            if (!Log.isLoggable("ShortcutBadger", 3)) {
                return false;
            }
            Log.d("ShortcutBadger", "Unable to execute badge", e);
            return false;
        }
    }

    public static void applyCountOrThrow(Context context, int i) throws ShortcutBadgeException {
        Badger badger;
        if (sShortcutBadger == null) {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            boolean z = false;
            if (launchIntentForPackage == null) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unable to find launch intent for package ");
                outline13.append(context.getPackageName());
                Log.e("ShortcutBadger", outline13.toString());
            } else {
                sComponentName = launchIntentForPackage.getComponent();
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
                if (resolveActivity != null && !resolveActivity.activityInfo.name.toLowerCase().contains("resolver")) {
                    String str = resolveActivity.activityInfo.packageName;
                    Iterator<Class<? extends Badger>> it = BADGERS.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        try {
                            badger = (Badger) it.next().newInstance();
                        } catch (Exception unused) {
                            badger = null;
                        }
                        if (badger != null && badger.getSupportLaunchers().contains(str)) {
                            sShortcutBadger = badger;
                            break;
                        }
                    }
                    if (sShortcutBadger == null) {
                        if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
                            sShortcutBadger = new XiaomiHomeBadger();
                        } else if (Build.MANUFACTURER.equalsIgnoreCase("ZUK")) {
                            sShortcutBadger = new ZukHomeBadger();
                        } else if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
                            sShortcutBadger = new OPPOHomeBader();
                        } else if (Build.MANUFACTURER.equalsIgnoreCase("VIVO")) {
                            sShortcutBadger = new VivoHomeBadger();
                        } else {
                            sShortcutBadger = new DefaultBadger();
                        }
                    }
                    z = true;
                }
            }
            if (!z) {
                throw new ShortcutBadgeException("No default launcher available");
            }
        }
        try {
            sShortcutBadger.executeBadge(context, sComponentName, i);
        } catch (Exception e) {
            throw new ShortcutBadgeException("Unable to execute badge", e);
        }
    }
}
