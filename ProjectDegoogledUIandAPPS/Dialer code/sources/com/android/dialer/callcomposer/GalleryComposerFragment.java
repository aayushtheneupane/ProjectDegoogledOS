package com.android.dialer.callcomposer;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.LoaderManager;
import android.support.p000v4.content.Loader;
import android.support.p000v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.dialer.R;
import com.android.dialer.common.LogUtil;
import com.android.dialer.common.concurrent.DefaultDialerExecutorFactory;
import com.android.dialer.common.concurrent.DialerExecutor;
import com.android.dialer.common.concurrent.DialerExecutorComponent;
import com.android.dialer.logging.DialerImpression$Type;
import com.android.dialer.logging.Logger;
import com.android.dialer.logging.LoggingBindingsStub;
import com.android.dialer.theme.base.ThemeComponent;
import com.android.dialer.theme.base.impl.AospThemeImpl;
import com.android.dialer.util.PermissionsUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryComposerFragment extends CallComposerFragment implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {
    private GalleryGridAdapter adapter;
    private View allowPermission;
    private DialerExecutor<Uri> copyAndResizeImage;
    private GridView galleryGridView;
    private List<GalleryGridItemData> insertedImages = new ArrayList();
    private View permissionView;
    private String[] permissions = {"android.permission.READ_EXTERNAL_STORAGE"};
    private GalleryGridItemData selectedData = null;
    private boolean selectedDataIsCopy;

    private void setSelected(GalleryGridItemData galleryGridItemData, boolean z) {
        this.selectedData = galleryGridItemData;
        this.selectedDataIsCopy = z;
        this.adapter.setSelected(this.selectedData);
        if (getListener() != null) {
            getListener().composeCall(this);
        }
    }

    private void setupGallery() {
        this.adapter = new GalleryGridAdapter(getContext(), (Cursor) null, this);
        this.galleryGridView.setAdapter(this.adapter);
        LoaderManager.getInstance(this).initLoader(0, (Bundle) null, this);
    }

    public void clearComposer() {
        setSelected((GalleryGridItemData) null, false);
    }

    public /* synthetic */ void lambda$onActivityCreated$0$GalleryComposerFragment(Pair pair) {
        GalleryGridItemData insertEntry = this.adapter.insertEntry(((File) pair.first).getAbsolutePath(), (String) pair.second);
        this.insertedImages.add(0, insertEntry);
        setSelected(insertEntry, true);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.copyAndResizeImage = ((DefaultDialerExecutorFactory) DialerExecutorComponent.get(getContext()).dialerExecutorFactory()).createUiTaskBuilder(getActivity().getFragmentManager(), "copyAndResizeImage", new CopyAndResizeImageWorker(getActivity().getApplicationContext())).onSuccess(new DialerExecutor.SuccessListener() {
            public final void onSuccess(Object obj) {
                GalleryComposerFragment.this.lambda$onActivityCreated$0$GalleryComposerFragment((Pair) obj);
            }
        }).onFailure($$Lambda$GalleryComposerFragment$ado5OiHlWj9iFwMRnxI2_6AAzXs.INSTANCE).build();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Bundle extras;
        Uri uri;
        if (i == 1 && i2 == -1 && intent != null) {
            String dataString = intent.getDataString();
            if (!(dataString != null || (extras = intent.getExtras()) == null || (uri = (Uri) extras.getParcelable("android.intent.extra.STREAM")) == null)) {
                dataString = uri.toString();
            }
            if (dataString != null) {
                this.copyAndResizeImage.executeParallel(Uri.parse(dataString));
            }
        } else if (i == 2 && PermissionsUtil.hasPermission(getContext(), "android.permission.READ_EXTERNAL_STORAGE")) {
            this.permissionView.setVisibility(8);
            setupGallery();
        }
    }

    public void onClick(View view) {
        if (view != this.allowPermission) {
            GalleryGridItemView galleryGridItemView = (GalleryGridItemView) view;
            if (galleryGridItemView.isGallery()) {
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                intent.putExtra("android.intent.extra.MIME_TYPES", GalleryCursorLoader.ACCEPTABLE_IMAGE_TYPES);
                intent.addCategory("android.intent.category.OPENABLE");
                startActivityForResult(intent, 1, (Bundle) null);
            } else if (galleryGridItemView.getData().equals(this.selectedData)) {
                setSelected((GalleryGridItemData) null, false);
            } else {
                setSelected(new GalleryGridItemData(galleryGridItemView.getData()), false);
            }
        } else if (PermissionsUtil.isFirstRequest(getContext(), this.permissions[0]) || shouldShowRequestPermissionRationale(this.permissions[0])) {
            LogUtil.m9i("GalleryComposerFragment.onClick", "Storage permission requested.", new Object[0]);
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.STORAGE_PERMISSION_REQUESTED);
            requestPermissions(this.permissions, 2);
        } else {
            LogUtil.m9i("GalleryComposerFragment.onClick", "Settings opened to enable permission.", new Object[0]);
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.STORAGE_PERMISSION_SETTINGS);
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("package:");
            outline13.append(getContext().getPackageName());
            intent2.setData(Uri.parse(outline13.toString()));
            startActivityForResult(intent2, 2, (Bundle) null);
        }
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new GalleryCursorLoader(getContext());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_gallery_composer, viewGroup, false);
        this.galleryGridView = (GridView) inflate.findViewById(R.id.gallery_grid_view);
        this.permissionView = inflate.findViewById(R.id.permission_view);
        if (!PermissionsUtil.hasPermission(getContext(), "android.permission.READ_EXTERNAL_STORAGE")) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.STORAGE_PERMISSION_DISPLAYED);
            LogUtil.m9i("GalleryComposerFragment.onCreateView", "Permission view shown.", new Object[0]);
            ImageView imageView = (ImageView) this.permissionView.findViewById(R.id.permission_icon);
            this.allowPermission = this.permissionView.findViewById(R.id.allow);
            this.allowPermission.setOnClickListener(this);
            ((TextView) this.permissionView.findViewById(R.id.permission_text)).setText(R.string.gallery_permission_text);
            imageView.setImageResource(R.drawable.quantum_ic_photo_white_48);
            imageView.setColorFilter(((AospThemeImpl) ThemeComponent.get(getContext()).theme()).getColorPrimary());
            this.permissionView.setVisibility(0);
        } else {
            if (bundle != null) {
                this.selectedData = (GalleryGridItemData) bundle.getParcelable("selected_data");
                this.selectedDataIsCopy = bundle.getBoolean("is_copy");
                this.insertedImages = bundle.getParcelableArrayList("inserted_images");
            }
            setupGallery();
        }
        return inflate;
    }

    public void onLoadFinished(Loader loader, Object obj) {
        this.adapter.swapCursor((Cursor) obj);
        List<GalleryGridItemData> list = this.insertedImages;
        if (list != null && !list.isEmpty()) {
            this.adapter.insertEntries(this.insertedImages);
        }
        setSelected(this.selectedData, this.selectedDataIsCopy);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        this.adapter.swapCursor((Cursor) null);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (strArr.length > 0 && strArr[0].equals(this.permissions[0])) {
            PermissionsUtil.permissionRequested(getContext(), strArr[0]);
        }
        if (i == 2 && iArr.length > 0 && iArr[0] == 0) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.STORAGE_PERMISSION_GRANTED);
            LogUtil.m9i("GalleryComposerFragment.onRequestPermissionsResult", "Permission granted.", new Object[0]);
            this.permissionView.setVisibility(8);
            setupGallery();
        } else if (i == 2) {
            ((LoggingBindingsStub) Logger.get(getContext())).logImpression(DialerImpression$Type.STORAGE_PERMISSION_DENIED);
            LogUtil.m9i("GalleryComposerFragment.onRequestPermissionsResult", "Permission denied.", new Object[0]);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("selected_data", this.selectedData);
        bundle.putBoolean("is_copy", this.selectedDataIsCopy);
        bundle.putParcelableArrayList("inserted_images", (ArrayList) this.insertedImages);
    }

    public boolean shouldHide() {
        GalleryGridItemData galleryGridItemData = this.selectedData;
        return galleryGridItemData == null || galleryGridItemData.getFilePath() == null || this.selectedData.getMimeType() == null;
    }
}
