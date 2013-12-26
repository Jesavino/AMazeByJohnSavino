package edu.wm.cs.cs301.jesavino.UI;

import edu.wm.cs.cs301.jesavino.R;
import edu.wm.cs.cs301.jesavino.R.id;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class SpinnerActivity extends Activity implements OnItemSelectedListener {
	static String algorithm;
	static String robot;
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		int spinner = parent.getId();
		
		switch(spinner) {
		case R.id.algorithm_spinner:
			algorithm = (String) parent.getItemAtPosition(pos);
			Log.v("SpinnerActivity" , "Changed the algorithm to " + algorithm );
			break;
		case R.id.robot_spinner:
			robot = (String) parent.getItemAtPosition(pos);
			Log.v("SpinnerActivity" , "Changed the robot type to " + robot);
		
		
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
