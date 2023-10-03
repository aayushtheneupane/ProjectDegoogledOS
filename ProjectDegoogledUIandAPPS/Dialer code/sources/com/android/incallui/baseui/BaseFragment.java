package com.android.incallui.baseui;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import com.android.incallui.baseui.C0701Ui;
import com.android.incallui.baseui.Presenter;

public abstract class BaseFragment<T extends Presenter<U>, U extends C0701Ui> extends Fragment {
    private T presenter = createPresenter();

    protected BaseFragment() {
    }

    public abstract T createPresenter();

    public T getPresenter() {
        return this.presenter;
    }

    public abstract U getUi();

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.presenter.onUiReady(getUi());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.presenter.onRestoreInstanceState(bundle);
            if (bundle.getBoolean("key_fragment_hidden")) {
                FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
                beginTransaction.hide(this);
                beginTransaction.commit();
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.presenter.onUiDestroy(getUi());
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.presenter.onSaveInstanceState(bundle);
        bundle.putBoolean("key_fragment_hidden", isHidden());
    }
}
