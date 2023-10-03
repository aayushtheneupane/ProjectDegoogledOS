package com.android.p032ex.photo.fragments;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.C0424j;
import androidx.loader.content.C0475f;
import com.android.p032ex.photo.C0722f;
import com.android.p032ex.photo.C0726g;
import com.android.p032ex.photo.C0732m;
import com.android.p032ex.photo.C0734o;
import com.android.p032ex.photo.C0736q;
import com.android.p032ex.photo.C0739t;
import com.android.p032ex.photo.C0740u;
import com.android.p032ex.photo.C0754w;
import com.android.p032ex.photo.C0755x;
import com.android.p032ex.photo.p034a.C0712c;
import com.android.p032ex.photo.p035b.C0715b;
import com.android.p032ex.photo.p035b.C0716c;
import com.android.p032ex.photo.views.C0753f;
import com.android.p032ex.photo.views.PhotoView;
import p000a.p008d.p009a.C0035a;
import p000a.p008d.p009a.C0036b;

/* renamed from: com.android.ex.photo.fragments.PhotoViewFragment */
public class PhotoViewFragment extends C0424j implements C0035a, View.OnClickListener, C0726g, C0722f {

    /* renamed from: Aj */
    protected boolean f861Aj;

    /* renamed from: Bo */
    protected String f862Bo;

    /* renamed from: Co */
    protected String f863Co;

    /* renamed from: Do */
    protected BroadcastReceiver f864Do;

    /* renamed from: Eo */
    protected PhotoView f865Eo;

    /* renamed from: Fo */
    protected ImageView f866Fo;

    /* renamed from: Go */
    protected C0753f f867Go;

    /* renamed from: Ho */
    protected boolean f868Ho;

    /* renamed from: Io */
    protected boolean f869Io;

    /* renamed from: Jo */
    protected boolean f870Jo = true;

    /* renamed from: Ko */
    protected View f871Ko;

    /* renamed from: Lo */
    protected boolean f872Lo;

    /* renamed from: Mo */
    protected boolean f873Mo;

    /* renamed from: No */
    protected boolean f874No;
    protected C0712c mAdapter;
    protected C0734o mCallback;
    protected String mContentDescription;
    protected TextView mEmptyText;
    protected Intent mIntent;
    protected int mPosition;

    /* renamed from: An */
    private void m1131An() {
        C0734o oVar = this.mCallback;
        setFullScreen(oVar == null ? false : oVar.mo5806v(this));
    }

    /* renamed from: b */
    private void m1133b(C0715b bVar) {
        if (bVar.status == 1) {
            this.f870Jo = false;
            this.mEmptyText.setText(C0755x.failed);
            this.mEmptyText.setVisibility(0);
            this.mCallback.mo5782a(this, false);
            return;
        }
        this.mEmptyText.setVisibility(8);
        Drawable b = bVar.mo5727b(getResources());
        if (b != null) {
            PhotoView photoView = this.f865Eo;
            if (photoView != null) {
                photoView.mo5813a(b);
            }
            mo5753y(true);
            this.f871Ko.setVisibility(8);
            this.f870Jo = false;
        }
        this.mCallback.mo5782a(this, true);
    }

    /* renamed from: M */
    public void mo5739M() {
        if (!this.mCallback.mo5805u(this)) {
            resetViews();
            return;
        }
        if (!mo5740_b()) {
            getLoaderManager().mo225b(2, (Bundle) null, this);
        }
        this.mCallback.mo5780a(this);
    }

    /* renamed from: _b */
    public boolean mo5740_b() {
        PhotoView photoView = this.f865Eo;
        return photoView != null && photoView.mo5812_b();
    }

    /* renamed from: a */
    public void mo220a(C0475f fVar) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo5745c(View view) {
        this.f865Eo = (PhotoView) view.findViewById(C0740u.photo_view);
        this.f865Eo.mo5815b(this.mIntent.getFloatExtra("max_scale", 1.0f));
        this.f865Eo.setOnClickListener(this);
        this.f865Eo.mo5818d(this.f861Aj, false);
        this.f865Eo.mo5840y(false);
        this.f865Eo.setContentDescription(this.mContentDescription);
        this.f871Ko = view.findViewById(C0740u.photo_preview);
        this.f866Fo = (ImageView) view.findViewById(C0740u.photo_preview_image);
        this.f872Lo = false;
        this.f867Go = new C0753f((ProgressBar) view.findViewById(C0740u.determinate_progress), (ProgressBar) view.findViewById(C0740u.indeterminate_progress), true);
        this.mEmptyText = (TextView) view.findViewById(C0740u.empty_text);
        ImageView imageView = (ImageView) view.findViewById(C0740u.retry_button);
        m1131An();
    }

    /* access modifiers changed from: protected */
    public C0734o getCallbacks() {
        return ((C0732m) getActivity()).getController();
    }

    public Drawable getDrawable() {
        PhotoView photoView = this.f865Eo;
        if (photoView != null) {
            return photoView.getDrawable();
        }
        return null;
    }

    /* renamed from: ha */
    public void mo5748ha() {
        resetViews();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mCallback = getCallbacks();
        C0734o oVar = this.mCallback;
        if (oVar != null) {
            this.mAdapter = oVar.getAdapter();
            if (this.mAdapter != null) {
                m1131An();
                return;
            }
            throw new IllegalStateException("Callback reported null adapter");
        }
        throw new IllegalArgumentException("Activity must be a derived class of PhotoViewActivity");
    }

    public void onClick(View view) {
        this.mCallback.mo5772Fd();
    }

    public void onCreate(Bundle bundle) {
        Bundle bundle2;
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mIntent = (Intent) arguments.getParcelable("arg-intent");
            this.f874No = this.mIntent.getBooleanExtra("display_thumbs_fullscreen", false);
            this.mPosition = arguments.getInt("arg-position");
            this.f869Io = arguments.getBoolean("arg-show-spinner");
            this.f870Jo = true;
            if (!(bundle == null || (bundle2 = bundle.getBundle("com.android.mail.photo.fragments.PhotoViewFragment.INTENT")) == null)) {
                this.mIntent = new Intent().putExtras(bundle2);
            }
            Intent intent = this.mIntent;
            if (intent != null) {
                this.f862Bo = intent.getStringExtra("resolved_photo_uri");
                this.f863Co = this.mIntent.getStringExtra("thumbnail_uri");
                this.mContentDescription = this.mIntent.getStringExtra("content_description");
                this.f868Ho = this.mIntent.getBooleanExtra("watch_network", false);
            }
        }
    }

    public C0475f onCreateLoader(int i, Bundle bundle) {
        String str = null;
        if (this.f869Io) {
            return null;
        }
        if (i == 2) {
            str = this.f863Co;
        } else if (i == 3) {
            str = this.f862Bo;
        }
        return this.mCallback.mo5775a(i, bundle, str);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C0754w.photo_fragment_view, viewGroup, false);
        mo5745c(inflate);
        return inflate;
    }

    public void onDestroyView() {
        PhotoView photoView = this.f865Eo;
        if (photoView != null) {
            photoView.clear();
            this.f865Eo = null;
        }
        super.onDestroyView();
    }

    public void onDetach() {
        this.mCallback = null;
        super.onDetach();
    }

    public void onPause() {
        if (this.f868Ho) {
            getActivity().unregisterReceiver(this.f864Do);
        }
        this.mCallback.mo5783b(this);
        this.mCallback.mo5786da(this.mPosition);
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.mCallback.mo5777a(this.mPosition, (C0726g) this);
        this.mCallback.mo5779a((C0722f) this);
        if (this.f868Ho) {
            if (this.f864Do == null) {
                this.f864Do = new C0725c(this, (C0723a) null);
            }
            getActivity().registerReceiver(this.f864Do, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) getActivity().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                this.f873Mo = activeNetworkInfo.isConnected();
            } else {
                this.f873Mo = false;
            }
        }
        if (!mo5740_b()) {
            this.f870Jo = true;
            this.f871Ko.setVisibility(0);
            getLoaderManager().mo224a(2, (Bundle) null, this);
            getLoaderManager().mo224a(3, (Bundle) null, this);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intent intent = this.mIntent;
        if (intent != null) {
            bundle.putParcelable("com.android.mail.photo.fragments.PhotoViewFragment.INTENT", intent.getExtras());
        }
    }

    public void resetViews() {
        PhotoView photoView = this.f865Eo;
        if (photoView != null) {
            photoView.mo5814ac();
        }
    }

    public void setFullScreen(boolean z) {
        this.f861Aj = z;
    }

    /* renamed from: uc */
    public String mo5752uc() {
        return this.f862Bo;
    }

    /* renamed from: y */
    public void mo5753y(boolean z) {
        this.f865Eo.mo5840y(z);
    }

    /* renamed from: a */
    public static void m1132a(Intent intent, int i, boolean z, PhotoViewFragment photoViewFragment) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg-intent", intent);
        bundle.putInt("arg-position", i);
        bundle.putBoolean("arg-show-spinner", z);
        photoViewFragment.setArguments(bundle);
    }

    /* renamed from: a */
    public void mo221a(C0475f fVar, C0715b bVar) {
        if (getView() != null && isAdded()) {
            Drawable b = bVar.mo5727b(getResources());
            int id = fVar.getId();
            if (id != 2) {
                if (id == 3) {
                    m1133b(bVar);
                }
            } else if (this.f874No) {
                m1133b(bVar);
            } else if (!mo5740_b()) {
                if (b == null) {
                    this.f866Fo.setImageResource(C0739t.default_image);
                    this.f872Lo = false;
                } else {
                    this.f866Fo.setImageDrawable(b);
                    this.f872Lo = true;
                }
                this.f866Fo.setVisibility(0);
                if (getResources().getBoolean(C0736q.force_thumbnail_no_scaling)) {
                    this.f866Fo.setScaleType(ImageView.ScaleType.CENTER);
                }
                mo5753y(false);
            } else {
                return;
            }
            if (!this.f870Jo) {
                this.f867Go.setVisibility(8);
            }
            if (b != null) {
                this.mCallback.mo5784ca(this.mPosition);
            }
            m1131An();
        }
    }

    /* renamed from: b */
    public void mo5743b(boolean z) {
        m1131An();
    }

    /* renamed from: b */
    public boolean mo5744b(float f, float f2) {
        PhotoView photoView;
        if (this.mCallback.mo5805u(this) && (photoView = this.f865Eo) != null && photoView.mo5819d(f, f2)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo5742a(float f, float f2) {
        PhotoView photoView;
        if (this.mCallback.mo5805u(this) && (photoView = this.f865Eo) != null && photoView.mo5816c(f, f2)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void mo5738a(Cursor cursor) {
        C0475f loader;
        if (this.mAdapter != null && cursor.moveToPosition(this.mPosition) && !mo5740_b()) {
            this.mCallback.mo5781a(this, cursor);
            C0036b loaderManager = getLoaderManager();
            C0475f loader2 = loaderManager.getLoader(3);
            if (loader2 != null) {
                C0716c cVar = (C0716c) loader2;
                this.f862Bo = this.mAdapter.mo5715e(cursor);
                cVar.mo5726c(this.f862Bo);
                ((C0475f) cVar).forceLoad();
            }
            if (!this.f872Lo && (loader = loaderManager.getLoader(2)) != null) {
                C0716c cVar2 = (C0716c) loader;
                this.f863Co = this.mAdapter.mo5716f(cursor);
                cVar2.mo5726c(this.f863Co);
                ((C0475f) cVar2).forceLoad();
            }
        }
    }
}
