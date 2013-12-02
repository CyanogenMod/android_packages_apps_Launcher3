package com.android.launcher3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

public final class LauncherPreferences {
        public static final String KEY_WORKSPACE_ROWS = "pref_key_workspaceRows";
        public static final String KEY_WORKSPACE_COLS = "pref_key_workspaceCols";

        public static final String KEY_WORKSPACE_DEFAULT_PAGE = "pref_key_workspaceDefaultPage";

        public static final String KEY_SHOW_SEARCHBAR = "pref_key_showSearchBar";

        private static final String TAG = "LauncherPreferences";

        public static class PrefsFragment  extends PreferenceFragment {
            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                // Load the preferences from an XML resource
                addPreferencesFromResource(R.xml.preferences );

                SharedPreferences prefs = getPreferenceManager().getSharedPreferences();

                DynamicGrid grid = LauncherAppState.getInstance().getDynamicGrid();

                if(grid != null) {
                        // initialize default values from current Profile
                        DeviceProfile prof = grid.getDeviceProfile();

                        SharedPreferences.Editor editor = prefs.edit();

                        if(prefs.getInt(KEY_WORKSPACE_ROWS, 0) < 1) {
                                Log.i(TAG,"Loading r default value from: "+grid.toString());
                                editor.putInt(KEY_WORKSPACE_ROWS, (int)prof.numRows);
                        }
                        if(prefs.getInt(KEY_WORKSPACE_COLS, 0) < 1) {
                                Log.i(TAG,"Loading c default value from: "+grid.toString());
                                editor.putInt(KEY_WORKSPACE_COLS, (int)prof.numColumns);
                        }

                        editor.apply();
                }
                else {
                        Log.w(TAG, "No DynamicGrid to get default values!");
            }
            }
        }

        private LauncherPreferences() {}

        public static boolean isLauncherPreference(String key) {
                return key.equals(KEY_WORKSPACE_ROWS)
                                || key.equals(KEY_WORKSPACE_COLS)
                                || key.equals(KEY_WORKSPACE_DEFAULT_PAGE)
                                || key.equals(KEY_SHOW_SEARCHBAR);
        }
}
