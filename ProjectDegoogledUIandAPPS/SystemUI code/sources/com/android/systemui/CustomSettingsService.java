package com.android.systemui;

public abstract class CustomSettingsService {

    public interface CustomSettingsObserver {
        void onIntSettingChanged(String str, Integer num) {
        }

        void onStringSettingChanged(String str, String str2) {
        }
    }

    public abstract void addIntObserver(CustomSettingsObserver customSettingsObserver, String... strArr);

    public abstract void removeObserver(CustomSettingsObserver customSettingsObserver);
}
