package com.abhi.gk;

import java.util.ArrayList;
import java.util.List;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.InterstitialAd;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;



public class ViewAnswers extends Activity implements AdListener{

	ListView answersListView;
	int max_numberOfQuestion = 10;

	final Integer[] images = { R.drawable.right, R.drawable.wrong };
	List<RowItem> rowItems = new ArrayList<RowItem>(); // this will used for displaying answers in see answers activity

	private InterstitialAd interstitial;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_answers);
		 
		//************************************** code for displaying ads
		// Create the interstitial
	    interstitial = new InterstitialAd(this, "a15250822869d38");

	    // Create ad request
	    AdRequest adRequest = new AdRequest();

	    // Begin loading your interstitial
	    interstitial.loadAd(adRequest);

	    // Set Ad Listener to use the callbacks below
	    interstitial.setAdListener(this);

	    //**************************************
	    
		answersListView = (ListView) findViewById(R.id.list_question_answers);
		String[] answers = new String[max_numberOfQuestion];
		Bundle bundle_obj = getIntent().getExtras();
		answers = bundle_obj.getStringArray("question_answers");
		max_numberOfQuestion = bundle_obj.getInt("no_of_question");

		// decide which image to be displayed 

		for (int i = 0; i <max_numberOfQuestion ; i++) 
		{	

			Log.v("question_answers", answers[i]);
			RowItem item = new RowItem(images[decide_image(answers[i])], answers[i]);  // bug it will always display right mark 
			rowItems.add(item);
		}


		//        ArrayAdapter<String> answers_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, answers);
		//        answersListView.setAdapter(answers_adapter);

		CustomListViewAdapter answers_adapter = new CustomListViewAdapter(this, R.layout.answer_list_item, rowItems);
		answersListView.setAdapter(answers_adapter);
	}

	
	// temporary solution -- it will fail when question has @@ in it.
	public int decide_image(String question_answer)
	{	
		if (question_answer.contains("you did not answered this question"))
		{
			return 1;
		}
		String[] temp =question_answer.replace("\n", "@@").split("@@");
		/*Log.v("decide image temp[0]", temp[0]);
		Log.v("decide image temp[1]", temp[1]);
		Log.v("decide image temp[2]", temp[2]);*/
		
		String[] answer = temp[1].split(":"); 	// temp[0] will have question, temp[1] will have answer, temp[2] will have answer selected by user.
		String[] user_answer = temp[2].split(":");


		try
		{
			if((answer[1].trim().toLowerCase()).equals(user_answer[1].trim().toLowerCase()))
				return 0;
			else 
				return 1;
		}
		catch(Exception ex)
		{
			return 0;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menu_inflator = getMenuInflater();
		menu_inflator.inflate(R.menu.activity_view_answers, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent sharingIntent = new Intent(Intent.ACTION_SEND);
		sharingIntent.setType("text/html");
		Bundle bundle_obj = getIntent().getExtras();
		String[] answers = new String[max_numberOfQuestion];
		answers = bundle_obj.getStringArray("question_answers");

		StringBuffer sb = new StringBuffer();
		for (int i = 0;i < answers.length; i++)
		{
			sb.append(answers[i]).append("\n");
		}
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, sb.toString());
		startActivity(Intent.createChooser(sharingIntent,"Share using"));
		return false;

	}


	@Override
	public void onDismissScreen(Ad arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onLeaveApplication(Ad arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onPresentScreen(Ad arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onReceiveAd(Ad ad) {
		// TODO Auto-generated method stub
		Log.d("OK", "Received ad");
	    if (ad == interstitial) {
	      interstitial.show();
	    }
	}
}
