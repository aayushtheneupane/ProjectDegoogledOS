package p000;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.preference.Preference;
import com.google.android.apps.photosgo.R;

/* renamed from: adb */
/* compiled from: PG */
public final class adb implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    /* renamed from: a */
    private final Preference f198a;

    public adb(Preference preference) {
        this.f198a = preference;
    }

    public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        CharSequence f = this.f198a.mo1166f();
        if (this.f198a.f1125x && !TextUtils.isEmpty(f)) {
            contextMenu.setHeaderTitle(f);
            contextMenu.add(0, 0, 0, R.string.copy).setOnMenuItemClickListener(this);
        }
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        CharSequence f = this.f198a.mo1166f();
        ((ClipboardManager) this.f198a.f1111j.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Preference", f));
        Context context = this.f198a.f1111j;
        Toast.makeText(context, context.getString(R.string.preference_copied, new Object[]{f}), 0).show();
        return true;
    }
}
