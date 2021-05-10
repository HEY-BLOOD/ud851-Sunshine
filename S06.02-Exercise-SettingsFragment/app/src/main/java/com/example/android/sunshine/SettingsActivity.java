/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;
import android.view.MenuItem;

/**
 * Loads the SettingsFragment and handles the proper behavior of the up button.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_settings);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.weather_settings_container, new SettingsActivity.SettingsFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    // COMPLETED (4) Create SettingsFragment and extend PreferenceFragmentCompat
    // Do steps 5 - 11 within SettingsFragment
    // COMPLETED (10) Implement OnSharedPreferenceChangeListener from SettingsFragment
    public static class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

        // COMPLETED (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource
        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
            addPreferencesFromResource(R.xml.forecast_prefences);

            // Do step 9 within onCreatePreference
            // COMPLETED (9) Set the preference summary on each preference that isn't a CheckBoxPreference
            SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
            PreferenceScreen prefScreen = getPreferenceScreen();
            int count = prefScreen.getPreferenceCount();
            for (int i = 0; i < count; i++) {
                Preference p = prefScreen.getPreference(i);
                if (!(p instanceof CheckBoxPreference)) {
                    String value = sharedPreferences.getString(p.getKey(), "");
                    setPreferenceSummary(p, value);
                }
            }
        }

        // COMPLETED (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Preference preference = findPreference(key);
            if (null != preference) {
                if (!(preference instanceof CheckBoxPreference)) {
                    setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
                }
            }
        }

        // COMPLETED (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets the summary of the preference
        private void setPreferenceSummary(Preference preference, Object value) {
            String stringValue = value.toString();
            String key = preference.getKey();

            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int prefIndexOfList = listPreference.findIndexOfValue(stringValue);
                if (prefIndexOfList >= 0) {
                    preference.setSummary(listPreference.getEntries()[prefIndexOfList]);
                }
            } else if (preference instanceof EditTextPreference) {
                preference.setSummary(stringValue);
            }

        }


        // COMPLETED (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart
        @Override
        public void onStart() {
            super.onStart();
            PreferenceManager.getDefaultSharedPreferences(getContext()).registerOnSharedPreferenceChangeListener(this);
        }

        // COMPLETED (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop
        @Override
        public void onDestroy() {
            super.onDestroy();
            PreferenceManager.getDefaultSharedPreferences(getContext()).unregisterOnSharedPreferenceChangeListener(this);
        }
    }


}