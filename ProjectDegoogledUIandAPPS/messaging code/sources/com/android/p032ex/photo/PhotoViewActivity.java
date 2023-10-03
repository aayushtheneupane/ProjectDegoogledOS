package com.android.p032ex.photo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.fragment.app.FragmentActivity;

/* renamed from: com.android.ex.photo.PhotoViewActivity */
public class PhotoViewActivity extends FragmentActivity implements C0732m {
    private C0713b mActionBar;
    private C0734o mController;

    /* renamed from: ea */
    public C0713b mo5701ea() {
        if (this.mActionBar == null) {
            this.mActionBar = new C0713b(getActionBar());
        }
        return this.mActionBar;
    }

    public Context getContext() {
        return this;
    }

    public C0734o getController() {
        return this.mController;
    }

    /* renamed from: kb */
    public C0734o mo5704kb() {
        return new C0734o(this);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mController.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (!this.mController.onBackPressed()) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mController = mo5704kb();
        this.mController.onCreate(bundle);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return this.mController.onCreateOptionsMenu(menu) || super.onCreateOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mController.onDestroy();
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return this.mController.onOptionsItemSelected(menuItem) || super.onOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.mController.f882Wb = true;
        super.onPause();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return this.mController.onPrepareOptionsMenu(menu) || super.onPrepareOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mController.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mController.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mController.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        this.mController.onStop();
        super.onStop();
    }
}
