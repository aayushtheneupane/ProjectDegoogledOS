package com.android.dialer.app.filterednumber;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import com.android.dialer.R;
import com.android.dialer.blocking.FilteredNumbersUtil;

public class ViewNumbersToImportFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {
    private ViewNumbersToImportAdapter adapter;

    public Context getContext() {
        return getActivity();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.adapter == null) {
            this.adapter = ViewNumbersToImportAdapter.newViewNumbersToImportAdapter(getActivity(), getActivity().getFragmentManager());
        }
        setListAdapter(this.adapter);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.import_button) {
            FilteredNumbersUtil.importSendToVoicemailContacts(getActivity(), new FilteredNumbersUtil.ImportSendToVoicemailContactsListener() {
                public void onImportComplete() {
                    if (ViewNumbersToImportFragment.this.getActivity() != null) {
                        ViewNumbersToImportFragment.this.getActivity().onBackPressed();
                    }
                }
            });
        } else if (view.getId() == R.id.cancel_button) {
            getActivity().onBackPressed();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getLoaderManager().initLoader(0, (Bundle) null, this);
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), ContactsContract.CommonDataKinds.Phone.CONTENT_URI, FilteredNumbersUtil.PhoneQuery.PROJECTION, "send_to_voicemail=1", (String[]) null, (String) null);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.view_numbers_to_import_fragment, viewGroup, false);
    }

    public void onDestroy() {
        setListAdapter((ListAdapter) null);
        super.onDestroy();
    }

    public void onLoadFinished(Loader loader, Object obj) {
        this.adapter.swapCursor((Cursor) obj);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        this.adapter.swapCursor((Cursor) null);
    }

    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        supportActionBar.setTitle((int) R.string.import_send_to_voicemail_numbers_label);
        supportActionBar.setDisplayShowCustomEnabled(false);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(true);
        getActivity().findViewById(R.id.cancel_button).setOnClickListener(this);
        getActivity().findViewById(R.id.import_button).setOnClickListener(this);
    }
}
