package com.android.contacts.util;

import android.os.AsyncTask;
import java.lang.ref.WeakReference;

public abstract class WeakAsyncTask<Params, Progress, Result, WeakTarget> extends AsyncTask<Params, Progress, Result> {
    protected WeakReference<WeakTarget> mTarget;

    /* access modifiers changed from: protected */
    public abstract Result doInBackground(WeakTarget weaktarget, Params... paramsArr);

    /* access modifiers changed from: protected */
    public abstract void onPostExecute(WeakTarget weaktarget, Result result);

    /* access modifiers changed from: protected */
    public abstract void onPreExecute(WeakTarget weaktarget);

    public WeakAsyncTask(WeakTarget weaktarget) {
        this.mTarget = new WeakReference<>(weaktarget);
    }

    /* access modifiers changed from: protected */
    public final void onPreExecute() {
        Object obj = this.mTarget.get();
        if (obj != null) {
            onPreExecute(obj);
        }
    }

    /* access modifiers changed from: protected */
    public final Result doInBackground(Params... paramsArr) {
        Object obj = this.mTarget.get();
        if (obj != null) {
            return doInBackground(obj, paramsArr);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final void onPostExecute(Result result) {
        Object obj = this.mTarget.get();
        if (obj != null) {
            onPostExecute(obj, result);
        }
    }
}
