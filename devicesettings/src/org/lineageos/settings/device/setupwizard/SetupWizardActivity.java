/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.device.setupwizard;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.lineageos.settings.device.keyboard.KeyboardUtils;
import org.lineageos.settings.device.R;

public class SetupWizardActivity extends SetupWizardBaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.setupwizard;
    }

    @Override
    protected int getTitleResId() {
        return R.string.setupwizard_title;
    }

    @Override
    protected int getIconResId() {
        return R.drawable.ic_keyboard_outline;
    }

    @Override
    protected void setupPage() {
        final RadioGroup group = requireViewById(R.id.navigation_mode_group);
        final RadioButton dpad = requireViewById(R.id.navigation_mode_dpad);
        final RadioButton touch = requireViewById(R.id.navigation_mode_touch);

        boolean supported = KeyboardUtils.isNavigationModeSupported();
        dpad.setEnabled(supported);
        touch.setEnabled(supported);

        group.check(KeyboardUtils.NAVIGATION_MODE_TOUCH.equals(
                KeyboardUtils.getNavigationMode(this))
                ? R.id.navigation_mode_touch : R.id.navigation_mode_dpad);
        group.setOnCheckedChangeListener((radioGroup, checkedId) ->
                KeyboardUtils.setNavigationMode(this, checkedId == R.id.navigation_mode_touch
                        ? KeyboardUtils.NAVIGATION_MODE_TOUCH
                        : KeyboardUtils.NAVIGATION_MODE_DPAD));
    }
}
