package com.abhi.gk;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ViewScore extends Activity {

	public TextView score_display;
	int no_of_question = 0;
	int max_numberOfQuestion = 20;
	int score = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_score);
        
        score_display = (TextView) findViewById(R.id.scoretextView);
//        share_FB = (TextView) findViewById(R.id.fbsharetextView);
//        see_answer = (TextView) findViewById(R.id.seeanswertextView);
//        replay = (TextView) findViewById(R.id.replaytextView);
        Bundle bundle_obj = getIntent().getExtras();
        score = bundle_obj.getInt("score");
        
        no_of_question = bundle_obj.getInt("no_of_question");
        String test = "You have answered "+score+ " questions out of "+no_of_question+" correctly";
        score_display.setText(test);
        
        Button replayButton = (Button) findViewById(R.id.replayButton);
        Button shareFBButton = (Button) findViewById(R.id.shareFBButton);
        Button seeAnswerButton = (Button) findViewById(R.id.seeAnswerButton);
        replayButton.setOnClickListener(replayButtonClicked);
        shareFBButton.setOnClickListener(shareFBButtonClicked);
        seeAnswerButton.setOnClickListener(seeAnswerButtonClicked);
    }

    OnClickListener replayButtonClicked = new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(ViewScore.this, Splashstart.class);
			i.setFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
		}
    	
	};
	
	OnClickListener shareFBButtonClicked = new OnClickListener()
	{
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent sharingIntent = new Intent(Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(Intent.EXTRA_TEXT, "http://www.google.fr/");
			startActivity(Intent.createChooser(sharingIntent,"Share using"));
			
		}
		
	};
	
	public void onBackPressed() {
	    // TODO Auto-generated method stub
	   // super.onBackPressed();
	  
		AlertDialog.Builder alert = new AlertDialog.Builder(ViewScore.this);
		alert.setTitle(R.string.viewscore_alert_box_title);
		alert.setMessage(R.string.viewscore_alert_box_msg);
		
		alert.setNegativeButton(R.string.alert_negative_button, null);
		
		alert.setPositiveButton(R.string.alert_positive_button, 
				new DialogInterface.OnClickListener()
        {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//finish();
				moveTaskToBack(true);
			}
			
        }
		
		);
		alert.show();
	}
	
	
	OnClickListener seeAnswerButtonClicked = new OnClickListener()
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String[] question_answers = new String[max_numberOfQuestion];
			Bundle bundle_obj = getIntent().getExtras();
			question_answers = bundle_obj.getStringArray("question_answers");
			Log.v("question_answer", question_answers[0]);
			
			Intent seeAnswer = new Intent(ViewScore.this, ViewAnswers.class);
			seeAnswer.putExtra("question_answers", question_answers);
			startActivity(seeAnswer);
		}
	};
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.activity_view_score, menu);
        return true;
    }
}
