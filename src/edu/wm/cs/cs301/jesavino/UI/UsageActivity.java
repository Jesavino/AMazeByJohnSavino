package edu.wm.cs.cs301.jesavino.UI;

import edu.wm.cs.cs301.jesavino.R;
import edu.wm.cs.cs301.jesavino.R.layout;
import edu.wm.cs.cs301.jesavino.R.menu;
import edu.wm.cs.cs301.jesavino.falstad.Globals;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class UsageActivity extends Activity {
	int skill;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usage);
		// Show the Up button in the action bar.
		setupActionBar();
		// update all of the maze variables
		skill = Globals.mostRecentSkill;
		
		TextView tempText = (TextView) findViewById(R.id.success_this_num);
		tempText.setText(Integer.toString(Globals.successes[skill]));
		
		tempText = (TextView) findViewById(R.id.recent_steps_num);
		tempText.setText(Integer.toString(Globals.stepsTaken));
		
		tempText = (TextView) findViewById(R.id.short_path_num);
		tempText.setText(Integer.toString(Globals.shortestPath[skill]));
		
		tempText = (TextView)	findViewById(R.id.this_energy_num);
		tempText.setText(Integer.toString(Globals.energyConsumed));
		
		
		
		tempText = (TextView) findViewById(R.id.best_energy_num);
		tempText.setText(Integer.toString(Globals.leastEnergyConsumed[skill]));
		
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.usage, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			Intent intent = new Intent(this , AMazeActivity.class);
			NavUtils.navigateUpTo(this, intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
