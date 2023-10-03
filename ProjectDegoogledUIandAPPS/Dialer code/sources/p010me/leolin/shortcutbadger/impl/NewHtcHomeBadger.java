package p010me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.p002v7.appcompat.R$style;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.Arrays;
import java.util.List;
import p010me.leolin.shortcutbadger.Badger;
import p010me.leolin.shortcutbadger.ShortcutBadgeException;

/* renamed from: me.leolin.shortcutbadger.impl.NewHtcHomeBadger */
public class NewHtcHomeBadger implements Badger {
    public void executeBadge(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        Intent intent = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
        intent.putExtra("com.htc.launcher.extra.COMPONENT", componentName.flattenToShortString());
        intent.putExtra("com.htc.launcher.extra.COUNT", i);
        Intent intent2 = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
        intent2.putExtra("packagename", componentName.getPackageName());
        intent2.putExtra("count", i);
        if (R$style.canResolveBroadcast(context, intent) || R$style.canResolveBroadcast(context, intent2)) {
            context.sendBroadcast(intent);
            context.sendBroadcast(intent2);
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("unable to resolve intent: ");
        outline13.append(intent2.toString());
        throw new ShortcutBadgeException(outline13.toString());
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.htc.launcher"});
    }
}
