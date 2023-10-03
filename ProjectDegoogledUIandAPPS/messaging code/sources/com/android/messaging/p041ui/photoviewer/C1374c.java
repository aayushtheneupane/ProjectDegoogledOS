package com.android.messaging.p041ui.photoviewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.Toast;
import androidx.fragment.app.C0433s;
import androidx.loader.content.C0475f;
import com.android.messaging.R;
import com.android.messaging.datamodel.MediaScratchFileProvider;
import com.android.messaging.p041ui.conversation.C1145N;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1464na;
import com.android.messaging.util.C1485y;
import com.android.p032ex.photo.C0732m;
import com.android.p032ex.photo.C0734o;
import com.android.p032ex.photo.p034a.C0712c;

/* renamed from: com.android.messaging.ui.photoviewer.c */
public class C1374c extends C0734o {

    /* renamed from: Aw */
    private MenuItem f2192Aw;

    /* renamed from: Bw */
    private MenuItem f2193Bw;

    /* renamed from: zw */
    private ShareActionProvider f2194zw;

    public C1374c(C0732m mVar) {
        super(mVar);
    }

    /* renamed from: Yn */
    private boolean m3507Yn() {
        return MediaScratchFileProvider.m1258d(Uri.parse(getAdapter().mo5715e(mo5769Cd())));
    }

    /* renamed from: Zn */
    private void m3508Zn() {
        C0712c adapter = getAdapter();
        Cursor Cd = mo5769Cd();
        if (this.f2194zw != null && this.f2192Aw != null && adapter != null && Cd != null) {
            String e = adapter.mo5715e(Cd);
            if (m3507Yn()) {
                this.f2192Aw.setVisible(false);
                return;
            }
            String d = adapter.mo5714d(Cd);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.setType(d);
            intent.putExtra("android.intent.extra.STREAM", Uri.parse(e));
            this.f2194zw.setShareIntent(intent);
            this.f2192Aw.setVisible(true);
        }
    }

    /* renamed from: Gd */
    public void mo5773Gd() {
        Cursor Cd = mo5769Cd();
        if (this.f2193Bw != null && Cd != null) {
            this.f896kw = Cd.getString(1);
            if (TextUtils.isEmpty(this.f896kw)) {
                this.f896kw = Cd.getString(5);
            }
            this.f898lw = C1485y.m3838E(Cd.getLong(6)).toString();
            mo5778a(getActivity().mo5701ea());
            this.f2193Bw.setVisible(true ^ m3507Yn());
            m3508Zn();
        }
    }

    /* renamed from: a */
    public C0475f mo5775a(int i, Bundle bundle, String str) {
        if (i == 1 || i == 2 || i == 3) {
            return new C1372a(getActivity().getContext(), str);
        }
        C1430e.m3622e("MessagingApp", "Photoviewer unable to open bitmap loader with unknown id: " + i);
        return null;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        ((Activity) getActivity()).getMenuInflater().inflate(R.menu.photo_view_menu, menu);
        this.f2192Aw = menu.findItem(R.id.action_share);
        this.f2194zw = (ShareActionProvider) this.f2192Aw.getActionProvider();
        m3508Zn();
        this.f2193Bw = menu.findItem(R.id.action_save);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_save) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (C1464na.m3753Tj()) {
            C0712c adapter = getAdapter();
            Cursor Cd = mo5769Cd();
            if (Cd == null) {
                Context context = getActivity().getContext();
                Toast.makeText(context, context.getResources().getQuantityString(R.plurals.attachment_save_error, 1, new Object[]{1}), 0).show();
                return true;
            }
            new C1145N((Activity) getActivity(), Uri.parse(adapter.mo5715e(Cd)), adapter.mo5714d(Cd)).mo8233b(new Void[0]);
        } else {
            ((Activity) getActivity()).requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
        }
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return !this.f889dw;
    }

    /* renamed from: a */
    public C0712c mo5776a(Context context, C0433s sVar, Cursor cursor, float f) {
        return new C1373b(context, sVar, cursor, f, this.f879No);
    }
}
