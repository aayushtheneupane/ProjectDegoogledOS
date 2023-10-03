package p000;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.util.Log;

/* renamed from: epq */
/* compiled from: PG */
public abstract class epq implements DialogInterface.OnClickListener {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo5134a();

    public final void onClick(DialogInterface dialogInterface, int i) {
        try {
            mo5134a();
        } catch (ActivityNotFoundException e) {
            Log.e("DialogRedirect", "Failed to start resolution intent", e);
        } catch (Throwable th) {
            dialogInterface.dismiss();
            throw th;
        }
        dialogInterface.dismiss();
    }
}
