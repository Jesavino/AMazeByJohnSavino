package edu.wm.cs.cs301.jesavino.UI;

import edu.wm.cs.cs301.jesavino.R;
import edu.wm.cs.cs301.jesavino.R.array;
import edu.wm.cs.cs301.jesavino.R.id;
import edu.wm.cs.cs301.jesavino.R.layout;
import edu.wm.cs.cs301.jesavino.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.app.ActionBar;
import android.content.Intent;

public class AMazeActivity extends Activity {
	// spinners
	Spinner robotSpinner;
	Spinner spinner;
	SeekBar skillLevel;
	int genMode = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// initialize the algorithm spinner
		spinner = (Spinner) findViewById(R.id.algorithm_spinner);
		spinner.setOnItemSelectedListener(new SpinnerActivity());
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.algorithms_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		// initialize the robot spinner
		robotSpinner = (Spinner) findViewById(R.id.robot_spinner);
		robotSpinner.setOnItemSelectedListener(new SpinnerActivity());
		ArrayAdapter<CharSequence> robotAdapter = ArrayAdapter.createFromResource(this, R.array.robots_array, android.R.layout.simple_spinner_item);
		robotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		robotSpinner.setAdapter(robotAdapter);
	
		skillLevel = (SeekBar) findViewById(R.id.skillSelector);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onRadioButtonClicked(View view) {
		// is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();
		
		switch(view.getId()) {
		case R.id.radio_random:
			if(checked){
				Log.v("RadioButton" , "Random Maze Selection Checked"); // this is default
				genMode = 0;
			}
			break;
		case R.id.radio_load:
			if(checked) {
				Log.v("RadioButton" , "Loading Maze From File");
				genMode = 1;
			}
			break;
		}		
		
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_play:
			generateMaze();
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		
		}		
	}

	private void generateMaze() {
		Log.v("ActionBar" , "Generating a Random Maze");
		Intent intent = new Intent(this , GeneratingActivity.class);
		intent.putExtra("robot" , robotSpinner.getSelectedItem().toString() );
		intent.putExtra("algorithm", spinner.getSelectedItem().toString());
		intent.putExtra("genMode", genMode);
		intent.putExtra("skill" , skillLevel.getProgress() );
		
		startActivity(intent);
	}

}
