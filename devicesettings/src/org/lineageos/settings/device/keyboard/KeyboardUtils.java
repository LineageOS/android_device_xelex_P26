/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.device.keyboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import org.lineageos.internal.util.FileUtils;

import java.io.File;

public class KeyboardUtils {

    private static final String TAG = KeyboardUtils.class.getSimpleName();

    // Values written to the synaptics_dsx key_mode_enable node
    public static final String NAVIGATION_MODE_DPAD = "1";
    public static final String NAVIGATION_MODE_TOUCH = "0";

    private static final String NAVIGATION_MODE_KEY = "keyboard_navigation_mode";

    // The inputN index of the keyboard touch panel isn't stable across boots
    private static final String KEYBOARD_TOUCH_INPUT_DIR =
            "/sys/devices/platform/soc/1101a000.i2c/i2c-6/6-0020/input";
    private static final String KEY_MODE_ENABLE_NODE = "key_mode_enable";

    private static String getKeyModeEnableNode() {
        File[] inputs = new File(KEYBOARD_TOUCH_INPUT_DIR).listFiles(
                (dir, name) -> name.startsWith("input"));
        if (inputs == null || inputs.length == 0) {
            return null;
        }
        return new File(inputs[0], KEY_MODE_ENABLE_NODE).getPath();
    }

    public static boolean isNavigationModeSupported() {
        String node = getKeyModeEnableNode();
        return node != null && FileUtils.isFileWritable(node);
    }

    public static String getNavigationMode(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(NAVIGATION_MODE_KEY, NAVIGATION_MODE_DPAD);
    }

    public static void setNavigationMode(Context context, String mode) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString(NAVIGATION_MODE_KEY, mode).apply();
        writeNavigationMode(mode);
    }

    public static void restoreNavigationMode(Context context) {
        writeNavigationMode(getNavigationMode(context));
    }

    private static void writeNavigationMode(String mode) {
        String node = getKeyModeEnableNode();
        if (node == null || !FileUtils.writeLine(node, mode)) {
            Log.e(TAG, "Failed to set navigation mode " + mode);
        }
    }
}
