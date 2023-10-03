package com.google.android.systemui.smartspace;

import android.util.Log;

public class SmartSpaceData {
    SmartSpaceCard mCurrentCard;
    SmartSpaceCard mWeatherCard;

    public boolean hasWeather() {
        return this.mWeatherCard != null;
    }

    public boolean hasCurrent() {
        return this.mCurrentCard != null;
    }

    public long getExpirationRemainingMillis() {
        long j;
        long currentTimeMillis = System.currentTimeMillis();
        if (hasCurrent() && hasWeather()) {
            j = Math.min(this.mCurrentCard.getExpiration(), this.mWeatherCard.getExpiration());
        } else if (hasCurrent()) {
            j = this.mCurrentCard.getExpiration();
        } else if (!hasWeather()) {
            return 0;
        } else {
            j = this.mWeatherCard.getExpiration();
        }
        return j - currentTimeMillis;
    }

    public long getExpiresAtMillis() {
        if (hasCurrent() && hasWeather()) {
            return Math.min(this.mCurrentCard.getExpiration(), this.mWeatherCard.getExpiration());
        }
        if (hasCurrent()) {
            return this.mCurrentCard.getExpiration();
        }
        if (hasWeather()) {
            return this.mWeatherCard.getExpiration();
        }
        return 0;
    }

    public void clear() {
        this.mWeatherCard = null;
        this.mCurrentCard = null;
    }

    public boolean handleExpire() {
        boolean z;
        if (!hasWeather() || !this.mWeatherCard.isExpired()) {
            z = false;
        } else {
            if (SmartSpaceController.DEBUG) {
                Log.d("SmartspaceData", "weather expired " + this.mWeatherCard.getExpiration());
            }
            this.mWeatherCard = null;
            z = true;
        }
        if (!hasCurrent() || !this.mCurrentCard.isExpired()) {
            return z;
        }
        if (SmartSpaceController.DEBUG) {
            Log.d("SmartspaceData", "current expired " + this.mCurrentCard.getExpiration());
        }
        this.mCurrentCard = null;
        return true;
    }

    public String toString() {
        return "{" + this.mCurrentCard + "," + this.mWeatherCard + "}";
    }

    public SmartSpaceCard getWeatherCard() {
        return this.mWeatherCard;
    }

    public SmartSpaceCard getCurrentCard() {
        return this.mCurrentCard;
    }
}
