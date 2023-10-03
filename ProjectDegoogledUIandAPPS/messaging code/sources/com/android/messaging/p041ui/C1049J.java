package com.android.messaging.p041ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

/* renamed from: com.android.messaging.ui.J */
public abstract class C1049J extends C1377r implements C1051K {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final CursorAdapter mListAdapter;
    private boolean mListCursorInitialized;
    private ListView mListView;

    public C1049J(Context context, CursorAdapter cursorAdapter) {
        this.mContext = context;
        this.mListAdapter = cursorAdapter;
    }

    private void maybeSetEmptyView() {
        ListEmptyView listEmptyView;
        View view = this.mView;
        if (view != null && this.mListCursorInitialized && (listEmptyView = (ListEmptyView) view.findViewById(getEmptyViewResId())) != null) {
            listEmptyView.mo7037x(getEmptyViewTitleResId());
            listEmptyView.mo7036w(getEmptyViewImageResId());
            ((ListView) this.mView.findViewById(getListViewResId())).setEmptyView(listEmptyView);
        }
    }

    /* access modifiers changed from: protected */
    public View createView(ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(getLayoutResId(), (ViewGroup) null, false);
        ListView listView = (ListView) inflate.findViewById(getListViewResId());
        listView.setAdapter(this.mListAdapter);
        listView.setOnScrollListener(new C1047I(this));
        this.mListView = listView;
        maybeSetEmptyView();
        return inflate;
    }

    /* access modifiers changed from: protected */
    public abstract int getEmptyViewImageResId();

    /* access modifiers changed from: protected */
    public abstract int getEmptyViewResId();

    /* access modifiers changed from: protected */
    public abstract int getEmptyViewTitleResId();

    /* access modifiers changed from: protected */
    public abstract int getLayoutResId();

    /* access modifiers changed from: protected */
    public abstract int getListViewResId();

    public CharSequence getPageTitle(Context context) {
        return context.getString(getPageTitleResId());
    }

    /* access modifiers changed from: protected */
    public abstract int getPageTitleResId();

    public void invalidateList() {
        this.mListAdapter.notifyDataSetChanged();
    }

    public void onContactsCursorUpdated(Cursor cursor) {
        this.mListAdapter.swapCursor(cursor);
        if (!this.mListCursorInitialized) {
            this.mListCursorInitialized = true;
            maybeSetEmptyView();
        }
    }

    public void toggleVisibilityForPendingTransition(boolean z, View view) {
        ListView listView = this.mListView;
        if (listView != null) {
            int childCount = listView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.mListView.getChildAt(i);
                if (childAt != view) {
                    childAt.setVisibility(z ? 0 : 4);
                }
            }
        }
    }
}
