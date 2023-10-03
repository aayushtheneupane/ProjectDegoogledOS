package com.android.dialer.about;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.LoaderManager;
import android.support.p000v4.content.Loader;
import android.support.p002v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.android.dialer.R;
import java.util.ArrayList;
import java.util.List;

public final class LicenseMenuActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<License>> {
    private ArrayAdapter<License> listAdapter;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.license_menu_activity);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        this.listAdapter = new ArrayAdapter<>(this, R.layout.license, R.id.license, new ArrayList());
        getSupportLoaderManager().initLoader(54321, (Bundle) null, this);
        ListView listView = (ListView) findViewById(R.id.license_list);
        listView.setAdapter(this.listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Intent intent = new Intent(LicenseMenuActivity.this, LicenseActivity.class);
                intent.putExtra("license", (License) adapterView.getItemAtPosition(i));
                LicenseMenuActivity.this.startActivity(intent);
            }
        });
    }

    public Loader<List<License>> onCreateLoader(int i, Bundle bundle) {
        return new LicenseLoader(this);
    }

    public void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(54321);
    }

    public void onLoaderReset(Loader<List<License>> loader) {
        this.listAdapter.clear();
        this.listAdapter.notifyDataSetChanged();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public void onLoadFinished(Loader<List<License>> loader, List<License> list) {
        this.listAdapter.clear();
        this.listAdapter.addAll(list);
        this.listAdapter.notifyDataSetChanged();
    }
}
