package edu.wm.cs.cs301.jesavino.UI;

import edu.wm.cs.cs301.jesavino.R;
import edu.wm.cs.cs301.jesavino.R.layout;
import edu.wm.cs.cs301.jesavino.R.menu;
import edu.wm.cs.cs301.jesavino.falstad.Globals;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ShareActionProvider;

public class FinishActivity extends Activity {
	ShareActionProvider mShareActionProvider;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		int skill = Globals.mostRecentSkill;
		
		Globals.successes[skill]++;
		if(Globals.shortestPath[skill] == 0)
			Globals.shortestPath[skill] = Globals.stepsTaken;
		if(Globals.stepsTaken < Globals.shortestPath[skill])
			Globals.shortestPath[skill] = Globals.stepsTaken;
		if(Globals.leastEnergyConsumed[skill] == 0)
			Globals.leastEnergyConsumed[skill] = Globals.energyConsumed;
		if(Globals.energyConsumed < Globals.leastEnergyConsumed[skill])
			Globals.leastEnergyConsumed[skill] = Globals.energyConsumed;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finish, menu);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		MenuItem item = menu.findItem(R.id.menu_item_share);
		mShareActionProvider = (ShareActionProvider) item.getActionProvider();
		setShareIntent();
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.v("Menu", "Selected");
		switch(item.getItemId()) {
		case android.R.id.home:
			Log.v("FinishActivity" , "Returning to main menu");
			Intent intent = new Intent(this , AMazeActivity.class);
			NavUtils.navigateUpTo(this, intent);
			return true;
		
			
			
		default:
			return super.onOptionsItemSelected(item);
		
		}		
	}
	// Call to update the share intent
	private void setShareIntent() {
	    if (mShareActionProvider != null) {
	    	Intent shareIntent = new Intent(Intent.ACTION_SEND);
	    	shareIntent.setAction(Intent.ACTION_SEND);			
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_TEXT, "I just completed a maze on difficulty " + Globals.mostRecentSkill + "!!!");
	        mShareActionProvider.setShareIntent(shareIntent);
	    }
	}
	public void onUsageClicked(View view) {
	
		Log.v("Finish" , "Showing Usage Stats");
		Intent usageIntent = new Intent(this, UsageActivity.class);
		startActivity(usageIntent);
	}

}
