package p000;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.sharing.database.StockShareReceiver_Receiver;

/* renamed from: ecr */
/* compiled from: PG */
final class ecr implements eea {

    /* renamed from: a */
    private final /* synthetic */ ect f7937a;

    public ecr(ect ect) {
        this.f7937a = ect;
    }

    /* renamed from: a */
    public final ieh mo4681a(een een) {
        return C0643xp.m15948c();
    }

    /* renamed from: b */
    public final boolean mo4684b() {
        return true;
    }

    /* renamed from: a */
    public final Drawable mo4680a(Context context) {
        return context.getDrawable(R.drawable.quantum_gm_ic_apps_vd_theme_24);
    }

    /* renamed from: a */
    public final String mo4682a() {
        return this.f7937a.f7938a.getString(R.string.sharesheet_other_label);
    }

    /* renamed from: a */
    public final boolean mo4683a(Context context, Intent intent) {
        context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.stock_sharesheet_header), PendingIntent.getBroadcast(context, 0, new Intent(context, StockShareReceiver_Receiver.class).setAction("com.google.android.apps.photosgo.sharing.STOCK_SHARE"), 1073741824).getIntentSender()));
        return true;
    }
}
