package com.example.jetpack_demos.navigation.navui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.jetpack_demos.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}