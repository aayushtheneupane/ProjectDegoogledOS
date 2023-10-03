package com.android.settings.tts;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TtsEngines;
import android.util.Log;
import android.widget.Checkable;
import androidx.preference.PreferenceCategory;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.tts.TtsEnginePreference;
import com.havoc.config.center.C1715R;
import java.util.Arrays;
import java.util.List;

public class TtsEnginePreferenceFragment extends SettingsPreferenceFragment implements TtsEnginePreference.RadioButtonGroupState {
    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() {
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = C1715R.xml.tts_engine_picker;
            return Arrays.asList(new SearchIndexableResource[]{searchIndexableResource});
        }
    };
    private Checkable mCurrentChecked;
    private String mCurrentEngine;
    private PreferenceCategory mEnginePreferenceCategory;
    private TtsEngines mEnginesHelper = null;
    private String mPreviousEngine;
    private TextToSpeech mTts = null;
    private final TextToSpeech.OnInitListener mUpdateListener = new TextToSpeech.OnInitListener() {
        public void onInit(int i) {
            TtsEnginePreferenceFragment.this.onUpdateEngine(i);
        }
    };

    public int getMetricsCategory() {
        return 93;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.tts_engine_picker);
        this.mEnginePreferenceCategory = (PreferenceCategory) findPreference("tts_engine_preference_category");
        this.mEnginesHelper = new TtsEngines(getActivity().getApplicationContext());
        this.mTts = new TextToSpeech(getActivity().getApplicationContext(), (TextToSpeech.OnInitListener) null);
        initSettings();
    }

    public void onDestroy() {
        super.onDestroy();
        TextToSpeech textToSpeech = this.mTts;
        if (textToSpeech != null) {
            textToSpeech.shutdown();
            this.mTts = null;
        }
    }

    private void initSettings() {
        TextToSpeech textToSpeech = this.mTts;
        if (textToSpeech != null) {
            this.mCurrentEngine = textToSpeech.getCurrentEngine();
        }
        this.mEnginePreferenceCategory.removeAll();
        for (TextToSpeech.EngineInfo ttsEnginePreference : this.mEnginesHelper.getEngines()) {
            this.mEnginePreferenceCategory.addPreference(new TtsEnginePreference(getPrefContext(), ttsEnginePreference, this));
        }
    }

    public Checkable getCurrentChecked() {
        return this.mCurrentChecked;
    }

    public String getCurrentKey() {
        return this.mCurrentEngine;
    }

    public void setCurrentChecked(Checkable checkable) {
        this.mCurrentChecked = checkable;
    }

    private void updateDefaultEngine(String str) {
        Log.d("TtsEnginePrefFragment", "Updating default synth to : " + str);
        this.mPreviousEngine = this.mTts.getCurrentEngine();
        Log.i("TtsEnginePrefFragment", "Shutting down current tts engine");
        TextToSpeech textToSpeech = this.mTts;
        if (textToSpeech != null) {
            try {
                textToSpeech.shutdown();
                this.mTts = null;
            } catch (Exception e) {
                Log.e("TtsEnginePrefFragment", "Error shutting down TTS engine" + e);
            }
        }
        Log.i("TtsEnginePrefFragment", "Updating engine : Attempting to connect to engine: " + str);
        this.mTts = new TextToSpeech(getActivity().getApplicationContext(), this.mUpdateListener, str);
        Log.i("TtsEnginePrefFragment", "Success");
    }

    public void onUpdateEngine(int i) {
        if (i == 0) {
            Log.d("TtsEnginePrefFragment", "Updating engine: Successfully bound to the engine: " + this.mTts.getCurrentEngine());
            Settings.Secure.putString(getContentResolver(), "tts_default_synth", this.mTts.getCurrentEngine());
            return;
        }
        Log.d("TtsEnginePrefFragment", "Updating engine: Failed to bind to engine, reverting.");
        if (this.mPreviousEngine != null) {
            this.mTts = new TextToSpeech(getActivity().getApplicationContext(), (TextToSpeech.OnInitListener) null, this.mPreviousEngine);
        }
        this.mPreviousEngine = null;
    }

    public void setCurrentKey(String str) {
        this.mCurrentEngine = str;
        updateDefaultEngine(this.mCurrentEngine);
    }
}
