package com.abhi.gk;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewAnswers extends Activity {

	ListView answersListView;
	int max_numberOfQuestion = 10;
//	List<String> answers = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_answers);
        
        answersListView = (ListView) findViewById(R.id.list_question_answers);
        String[] answers = new String[max_numberOfQuestion];
        Bundle bundle_obj = getIntent().getExtras();
        answers = bundle_obj.getStringArray("question_answers");
//        for (int i =0 ; i<temp_answers.length; i++)
//        {
//        	answers.add(temp_answers[0]);
//        }
        
        ArrayAdapter<String> answers_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, answers);
        answersListView.setAdapter(answers_adapter);
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
        for (int i = 0;i < answers.length; i++){
            sb.append(answers[i]).append("\n");
        }
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, sb.toString());
		startActivity(Intent.createChooser(sharingIntent,"Share using"));
		return false;
    	
	}
}
