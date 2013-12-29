/*package com.abhi.gk;




import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TestQuestionListViewAdapter  extends ArrayAdapter<RowItem> {

	Context context;

	public TestQuestionListViewAdapter(Context context, int resourceId,
			List<RowItem> items) {
		super(context, resourceId, items);
		this.context = context;
	}

	private view holder class
	private class ViewHolder {
//		ImageView imageView;
//		TextView txtQuestion;
		TextView txtUserAnswer;
	        TextView txtCorrectAnswer;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		RowItem rowItem = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.answer_list_item, null);
			holder = new ViewHolder();
			//  holder.txtUserAnswer = (TextView) convertView.findViewById(R.id.userAnswer);
			holder.txtQuestion = (TextView) convertView.findViewById(R.id.question);
			holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
			//  holder.txtCorrectAnswer = (TextView) convertView.findViewById(R.id.correctAnswer);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		//     holder.txtUserAnswer.setText(rowItem.getuserAnswer());
		holder.txtQuestion.setText(rowItem.getquestion());
		holder.imageView.setImageResource(rowItem.getImageId());
		//      holder.txtCorrectAnswer.setText(rowItem.getcorrectAnswer());

		// for dispaying animation on the the answer page
		Animation animation = null;
		animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
		//animation = new TranslateAnimation(0, 0, metrics.heightPixels,0);
		animation.setDuration(500);
		convertView.startAnimation(animation);
		animation = null;

		return convertView;
	}
}

*/