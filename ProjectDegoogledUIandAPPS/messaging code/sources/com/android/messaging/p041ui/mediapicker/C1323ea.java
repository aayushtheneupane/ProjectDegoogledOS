package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.appcompat.app.ActionBar;
import com.android.messaging.R;
import com.android.messaging.datamodel.data.C0943z;
import com.android.messaging.datamodel.p037a.C0786f;
import com.android.messaging.p041ui.C1377r;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.mediapicker.ea */
abstract class C1323ea extends C1377r implements C0943z {

    /* renamed from: Dj */
    protected final C1345pa f2118Dj;
    protected boolean mSelected = false;

    /* renamed from: nF */
    protected final C0786f f2119nF;

    /* renamed from: oF */
    private ImageButton f2120oF;

    C1323ea(C1345pa paVar) {
        C1424b.m3594t(paVar);
        this.f2118Dj = paVar;
        this.f2119nF = paVar.mo7888Ga();
    }

    /* renamed from: Eb */
    public boolean mo7671Eb() {
        return false;
    }

    /* renamed from: H */
    public int mo6582H() {
        return this.f2118Dj.mo6582H();
    }

    /* renamed from: Kb */
    public void mo7770Kb() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Pi */
    public abstract int mo7672Pi();

    /* access modifiers changed from: package-private */
    /* renamed from: Qi */
    public abstract int mo7673Qi();

    /* renamed from: Ri */
    public abstract int mo7674Ri();

    /* access modifiers changed from: package-private */
    /* renamed from: S */
    public void mo7675S(boolean z) {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Si */
    public ImageButton mo7853Si() {
        return this.f2120oF;
    }

    /* renamed from: Ti */
    public boolean mo7771Ti() {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo7854a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.f2120oF = (ImageButton) layoutInflater.inflate(R.layout.mediapicker_tab_button, viewGroup, false);
        this.f2120oF.setImageResource(getIconResource());
        this.f2120oF.setContentDescription(layoutInflater.getContext().getResources().getString(mo7673Qi()));
        setSelected(this.mSelected);
        this.f2120oF.setOnClickListener(new C1321da(this));
    }

    /* renamed from: a */
    public void mo7756a(MenuInflater menuInflater, Menu menu) {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo7678b(boolean z) {
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.f2118Dj.getActivity();
    }

    /* access modifiers changed from: package-private */
    public abstract int getIconResource();

    /* access modifiers changed from: protected */
    public LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(getContext());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public boolean onBackPressed() {
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void onPause() {
    }

    /* access modifiers changed from: protected */
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    public void onResume() {
    }

    /* access modifiers changed from: protected */
    public void setSelected(boolean z) {
        this.mSelected = z;
        ImageButton imageButton = this.f2120oF;
        if (imageButton != null) {
            imageButton.setSelected(z);
            this.f2120oF.setAlpha(z ? 1.0f : 0.5f);
        }
    }

    /* renamed from: u */
    public void mo7773u(int i) {
    }

    /* access modifiers changed from: package-private */
    public void updateActionBar(ActionBar actionBar) {
        int Pi = mo7672Pi();
        if (Pi == 0) {
            actionBar.hide();
            return;
        }
        actionBar.setCustomView((View) null);
        actionBar.setDisplayOptions(8);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();
        actionBar.setHomeAsUpIndicator((int) R.drawable.ic_remove_small_light);
        actionBar.setTitle(Pi);
    }

    /* renamed from: ya */
    public boolean mo7858ya() {
        return false;
    }
}
