package com.android.messaging.p041ui.mediapicker;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import androidx.appcompat.app.ActionBar;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0890B;
import com.android.messaging.datamodel.data.C0895G;
import com.android.messaging.datamodel.data.C0896H;
import com.android.messaging.datamodel.data.MessagePartData;
import com.android.messaging.datamodel.p037a.C0784d;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1464na;

/* renamed from: com.android.messaging.ui.mediapicker.X */
class C1295X extends C1323ea implements C1292U, C0895G {

    /* renamed from: AF */
    private GalleryGridView f2049AF;

    /* renamed from: BF */
    private C1284L f2050BF = new C1284L(this.f2118Dj, new C1294W(this));
    private final C1285M mAdapter = new C1285M(C0967f.get().getApplicationContext(), (Cursor) null);

    /* renamed from: qF */
    private View f2051qF;

    C1295X(C1345pa paVar) {
        super(paVar);
    }

    /* renamed from: Ca */
    private void m3282Ca(boolean z) {
        GalleryGridView galleryGridView = this.f2049AF;
        if (galleryGridView != null) {
            int i = 0;
            galleryGridView.setVisibility(z ? 0 : 8);
            View view = this.f2051qF;
            if (z) {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    /* renamed from: qo */
    private void m3283qo() {
        ((C0896H) this.f2119nF.getData()).mo6228a(1, this.f2119nF, (Bundle) null, this);
    }

    /* renamed from: Eb */
    public boolean mo7671Eb() {
        return this.f2049AF.mo7926Eb();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Pi */
    public int mo7672Pi() {
        return R.string.mediapicker_gallery_title;
    }

    /* renamed from: Qi */
    public int mo7673Qi() {
        return R.string.mediapicker_galleryChooserDescription;
    }

    /* renamed from: Ri */
    public int mo7674Ri() {
        return 7;
    }

    /* renamed from: Vi */
    public void mo7755Vi() {
        this.f2050BF.mo7726wj();
    }

    /* renamed from: a */
    public void mo7756a(MenuInflater menuInflater, Menu menu) {
        if (this.mView != null) {
            this.f2049AF.mo7707a(menuInflater, menu);
        }
    }

    /* access modifiers changed from: protected */
    public View createView(ViewGroup viewGroup) {
        View inflate = getLayoutInflater().inflate(R.layout.mediapicker_gallery_chooser, viewGroup, false);
        this.f2049AF = (GalleryGridView) inflate.findViewById(R.id.gallery_grid_view);
        this.mAdapter.mo7727a(this.f2049AF);
        this.f2049AF.setAdapter(this.mAdapter);
        this.f2049AF.mo7710a((C1292U) this);
        this.f2049AF.mo7709a((C0784d) this.f2118Dj.mo7887Fa());
        if (C1464na.m3753Tj()) {
            m3283qo();
        }
        this.f2051qF = inflate.findViewById(R.id.missing_permission_view);
        m3282Ca(C1464na.m3753Tj());
        return inflate;
    }

    /* renamed from: da */
    public void mo7757da() {
        C1424b.m3592ia(this.f2049AF.mo7715q());
        this.f2118Dj.mo7882Aa();
    }

    public View destroyView() {
        this.f2049AF.setAdapter((ListAdapter) null);
        this.mAdapter.mo7727a((C1290S) null);
        if (C1464na.m3753Tj()) {
            ((C0896H) this.f2119nF.getData()).destroyLoader(1);
        }
        return super.destroyView();
    }

    public int getIconResource() {
        return R.drawable.ic_image_light;
    }

    /* renamed from: h */
    public void mo7758h(MessagePartData messagePartData) {
        this.f2118Dj.mo7896a(messagePartData, !this.f2049AF.mo7715q());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1400 && i2 == -1) {
            this.f2050BF.onActivityResult(i, i2, intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.mView != null) {
            return this.f2049AF.onOptionsItemSelected(menuItem);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 4) {
            boolean z = false;
            if (iArr[0] == 0) {
                z = true;
            }
            if (z) {
                m3283qo();
            }
            m3282Ca(z);
        }
    }

    public void onResume() {
        if (C1464na.m3753Tj()) {
            m3283qo();
        }
    }

    /* access modifiers changed from: protected */
    public void setSelected(boolean z) {
        super.setSelected(z);
        if (z && !C1464na.m3753Tj()) {
            this.f2118Dj.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 4);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateActionBar(ActionBar actionBar) {
        int Fb;
        int Pi = mo7672Pi();
        if (Pi == 0) {
            actionBar.hide();
        } else {
            actionBar.setCustomView((View) null);
            actionBar.setDisplayOptions(8);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.show();
            actionBar.setHomeAsUpIndicator((int) R.drawable.ic_remove_small_light);
            actionBar.setTitle(Pi);
        }
        GalleryGridView galleryGridView = this.f2049AF;
        if (galleryGridView != null && (Fb = galleryGridView.mo7706Fb()) > 0 && this.f2049AF.mo7715q()) {
            actionBar.setTitle((CharSequence) getContext().getResources().getString(R.string.mediapicker_gallery_title_selection, new Object[]{Integer.valueOf(Fb)}));
        }
    }

    /* renamed from: a */
    public void mo6227a(C0896H h, Object obj, int i) {
        this.f2119nF.mo5929a(h);
        C1424b.equals(1, i);
        Cursor cursor = obj instanceof Cursor ? (Cursor) obj : null;
        MatrixCursor matrixCursor = new MatrixCursor(C0890B.f1150MB);
        matrixCursor.addRow(new Object[]{"-1"});
        this.mAdapter.swapCursor(new MergeCursor(new Cursor[]{matrixCursor, cursor}));
    }
}
