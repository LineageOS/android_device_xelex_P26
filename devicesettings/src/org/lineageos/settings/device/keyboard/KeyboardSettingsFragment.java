/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.device.keyboard;

import android.os.Bundle;

import com.android.settingslib.widget.SelectorWithWidgetPreference;
import com.android.settingslib.widget.SettingsBasePreferenceFragment;

import org.lineageos.settings.device.R;

public class KeyboardSettingsFragment extends SettingsBasePreferenceFragment
        implements SelectorWithWidgetPreference.OnClickListener {

    private static final String NAVIGATION_MODE_DPAD_KEY = "navigation_mode_dpad";
    private static final String NAVIGATION_MODE_TOUCH_KEY = "navigation_mode_touch";

    private SelectorWithWidgetPreference mDpadPref;
    private SelectorWithWidgetPreference mTouchPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.keyboard_panel, rootKey);

        mDpadPref = findPreference(NAVIGATION_MODE_DPAD_KEY);
        mTouchPref = findPreference(NAVIGATION_MODE_TOUCH_KEY);
        mDpadPref.setOnClickListener(this);
        mTouchPref.setOnClickListener(this);

        boolean supported = KeyboardUtils.isNavigationModeSupported();
        mDpadPref.setEnabled(supported);
        mTouchPref.setEnabled(supported);

        updateNavigationModePreferences(KeyboardUtils.getNavigationMode(getContext()));
    }

    @Override
    public void onRadioButtonClicked(SelectorWithWidgetPreference pref) {
        String mode = pref == mDpadPref
                ? KeyboardUtils.NAVIGATION_MODE_DPAD : KeyboardUtils.NAVIGATION_MODE_TOUCH;
        KeyboardUtils.setNavigationMode(getContext(), mode);
        updateNavigationModePreferences(mode);
    }

    private void updateNavigationModePreferences(String mode) {
        mDpadPref.setChecked(KeyboardUtils.NAVIGATION_MODE_DPAD.equals(mode));
        mTouchPref.setChecked(KeyboardUtils.NAVIGATION_MODE_TOUCH.equals(mode));
    }
}
