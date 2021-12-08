package ru.gb.makulin.mymoviefinder.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import ru.gb.makulin.mymoviefinder.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}