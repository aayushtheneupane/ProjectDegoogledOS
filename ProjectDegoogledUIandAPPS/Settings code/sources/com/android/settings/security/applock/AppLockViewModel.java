package com.android.settings.security.applock;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class AppLockViewModel extends AndroidViewModel {
    private final AppListLiveData mLiveData;

    public AppLockViewModel(Application application) {
        super(application);
        this.mLiveData = new AppListLiveData(application);
    }

    public LiveData<List<AppLockInfo>> getAppList() {
        return this.mLiveData;
    }
}
