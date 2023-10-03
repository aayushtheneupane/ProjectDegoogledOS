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

/* renamed from: me.leolin.shortcutbadger.impl.ApexHomeBadger */
public class ApexHomeBadger implements Badger {
    public void executeBadge(Context context, ComponentName componentName, int i) throws ShortcutBadgeException {
        Intent intent = new Intent("com.anddoes.launcher.COUNTER_CHANGED");
        intent.putExtra("package", componentName.getPackageName());
        intent.putExtra("count", i);
        intent.putExtra("class", componentName.getClassName());
        if (R$style.canResolveBroadcast(context, intent)) {
            context.sendBroadcast(intent);
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("unable to resolve intent: ");
        outline13.append(intent.toString());
        throw new ShortcutBadgeException(outline13.toString());
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.anddoes.launcher"});
    }
}
