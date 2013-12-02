package com.android.launcher3;

import android.app.Activity;
import android.os.Bundle;

import com.android.launcher3.LauncherPreferences.PrefsFragment;

public class LauncherPreferencesActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    // Display the fragment as the main content.
	    getFragmentManager().beginTransaction()
	            .replace(android.R.id.content, new PrefsFragment())
	            .commit();
	}
}
