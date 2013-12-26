package edu.wm.cs.cs301.jesavino.UI;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import edu.wm.cs.cs301.jesavino.R;
import edu.wm.cs.cs301.jesavino.R.id;
import edu.wm.cs.cs301.jesavino.R.layout;
import edu.wm.cs.cs301.jesavino.R.menu;
import edu.wm.cs.cs301.jesavino.falstad.Constants;
import edu.wm.cs.cs301.jesavino.falstad.Globals;
import edu.wm.cs.cs301.jesavino.falstad.Maze;
import edu.wm.cs.cs301.jesavino.falstad.MazeBuilder;
import edu.wm.cs.cs301.jesavino.falstad.MazeBuilderEller;
import edu.wm.cs.cs301.jesavino.falstad.MazeBuilderPrim;
import edu.wm.cs.cs301.jesavino.falstad.MazeFileReader;
import edu.wm.cs.cs301.jesavino.falstad.Distance;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GeneratingActivity extends Activity {
	private static final int PROGRESS = 0x1;
	
	private ProgressBar mProgress;
	private int mProgressStatus = 0;
	
	private Handler mHandler = new Handler();
	String algorithm;
	String robot;
	int skill;
	int algorithmNum;
	Button btn;
	public Thread buildThread;
	int genMode;
	File loadedMaze;
	MazeFileReader mfr;
	int loadMaze;
	
	
	MazeBuilder mazebuilder;
	Maze maze;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generating);
		mProgress = (ProgressBar) findViewById(R.id.progress_generating);
		
		// Get the user params
		Intent intent = getIntent();
		
		algorithm = intent.getStringExtra("algorithm");
		robot = intent.getStringExtra("robot");
		skill = intent.getIntExtra("skill", 2);	
		Globals.mostRecentSkill = skill;
		Globals.stepsTaken = 0;
		Globals.energyConsumed = 0;
		loadMaze = genMode = intent.getIntExtra("genMode" , 0);
		
		new BuildMazeTask().execute();
		
	}
	
	/** this is where the maze is built */
	class BuildMazeTask extends AsyncTask<Integer, Void , Void> {
		@Override
		protected Void doInBackground(Integer...params) {
			// Generation via loading
			if(genMode == 1){
				Log.v("Generating" , "Loading Maze from File");
				if (skill < 4){
					InputStream inputStream;
					switch(skill) {
					case 0:
						inputStream = getResources().openRawResource(R.raw.maze0);
						break;
					case 1:
						inputStream = getResources().openRawResource(R.raw.maze1);
						break;
					case 2:
						inputStream = getResources().openRawResource(R.raw.maze2);
						break;
					case 3:
						inputStream = getResources().openRawResource(R.raw.maze3);
						break;
					default:
						inputStream = getResources().openRawResource(R.raw.maze4);
						break;
					}
					Log.v("File Loading " , "Skill " + skill);
					publishProgress();
					mfr = new MazeFileReader(inputStream);
					publishProgress();
					maze = new Maze();
					maze.mazeh = mfr.getHeight();
					maze.mazew = mfr.getWidth();
					edu.wm.cs.cs301.jesavino.falstad.Distance d = new edu.wm.cs.cs301.jesavino.falstad.Distance(mfr.getDistances()) ;
					maze.newMaze(mfr.getRootNode(),mfr.getCells(),d,mfr.getStartX(), mfr.getStartY()) ;
					publishProgress();
					
				}
				else{
					switch (skill){
					case 5:
						if (Globals.skill5 != null) {
							maze = Globals.skill5;
						}
						else loadMaze = 0;

						break;
					case 6:
						if (Globals.skill6 != null) {
							maze = Globals.skill6;
						}
						else loadMaze = 0;

						break;
					case 7:
						if (Globals.skill7 != null) {
							maze = Globals.skill7;
						}
						else loadMaze = 0;

						break;
					case 8:
						if (Globals.skill8 != null) {
							maze = Globals.skill8;
						}
						else loadMaze = 0;

						break;
					case 9:
						if (Globals.skill9 != null) {
							maze = Globals.skill9;
						}
						else loadMaze = 0;

						break;
					case 10:
						if (Globals.skill10 != null) {
							maze = Globals.skill10;
						}
						else loadMaze = 0;

						break;
					case 11:
						if (Globals.skill11 != null) {
							maze = Globals.skill11;
						}
						else loadMaze = 0;

						break;
					case 12:
						if (Globals.skill12 != null) {
							maze = Globals.skill12;
						}
						else loadMaze = 0;

						break;
					case 13:
						if (Globals.skill13 != null) {
							maze = Globals.skill13;
						}
						else loadMaze = 0;

						break;
					case 14:
						if (Globals.skill14 != null) {
							maze = Globals.skill14;
						}
						else loadMaze = 0;

						break;	
					}
				}
			}
				
			// build it yourself!
			if (loadMaze == 0){	
				/* MAZE INITIALIZATION ------------------------------ */
				if(algorithm.equals("Random Generation"))
					algorithmNum = 0;
				else if(algorithm.equals("Prim's Algorithm" ))
					algorithmNum = 1;
				else
					algorithmNum = 2; // Eller's
				// 		make the maze builder based on the algorithm type
				publishProgress();
				maze = new Maze(algorithmNum);
				publishProgress();
				// select generation method
				switch(maze.method){
				case 2: maze.mazebuilder = new MazeBuilderEller(); //generate with Eller's algorithm
				break;
				case 1 : maze.mazebuilder = new MazeBuilderPrim(); // generate with Prim's algorithm
				break ;
				case 0: // generate with Falstad's original algorithm (0 and default), note the missing break statement
				default : maze.mazebuilder = new MazeBuilder(); 
				break ;
				}
				// 	adjust settings and launch generation in a separate thread
				maze.mazew = Constants.SKILL_X[skill];
				maze.mazeh = Constants.SKILL_Y[skill];
				maze.mazebuilder.build(maze, maze.mazew, maze.mazeh, Constants.SKILL_ROOMS[skill], Constants.SKILL_PARTCT[skill]);
				publishProgress();
				if (genMode == 1){
					switch (skill){
					case 5:
						Globals.skill5 = maze;
						break;
					case 6:
						Globals.skill6 = maze;
						break;
					case 7:
						Globals.skill7 = maze;
						break;
					case 8:
						Globals.skill8 = maze;
						break;
					case 9:
						Globals.skill9 = maze;
						break;
					case 10:
						Globals.skill10 = maze;
						break;
					case 11:
						Globals.skill11 = maze;
						break;
					case 12:
						Globals.skill12 = maze;
						break;
					case 13:
						Globals.skill13 = maze;
						break;
					case 14:
						Globals.skill14 = maze;
						break;
						
						
					}
					publishProgress();
					publishProgress();
					publishProgress();
				}
				
					
			}
			try {
				maze.mazebuilder.buildThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return (null);
		}
		
		@Override
		protected void onProgressUpdate(Void... unused) {
			mProgress.incrementProgressBy(33);				
		}
		
		@Override
		protected void onPostExecute(Void unused) {
			TextView temp = (TextView) findViewById(R.id.loading_text);
			temp.setVisibility(View.INVISIBLE);
			btn = new Button(GeneratingActivity.this);
			btn.setHeight(50);
			btn.setWidth(70);
			LinearLayout layout = (LinearLayout) findViewById(R.id.generating_screen);
			btn.setText("Go to Maze");
		
			
			layout.addView(btn);
			btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.v("Generating Activity" , "Going to maze view");
					Intent playIntent = new Intent(GeneratingActivity.this , PlayActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("Cells", maze.mazecells);
					bundle.putSerializable("BSPnode" , maze.bspRoot);
					bundle.putSerializable("Distance", maze.mazedists);
					bundle.putInt("startx", maze.px);
					bundle.putInt("starty", maze.py);
					bundle.putInt("mazew" , maze.mazew);
					bundle.putInt("mazeh" , maze.mazeh);
					bundle.putString("robotDriver", robot);
					Log.v("hello world", maze.mazecells.toString());
					playIntent.putExtras(bundle);
					startActivity(playIntent);
					
				}
			});
			
		}
				
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.generating, menu);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home:
			Log.v("Generating Activity" , "Returning to main menu");
			if(buildThread != null)
				buildThread.interrupt();
			Intent intent = new Intent(this , AMazeActivity.class);
			NavUtils.navigateUpTo(this, intent);
			return true;
		
			
		default:
			return super.onOptionsItemSelected(item);
		
		}		
	}

	

}
