package com.example.clickajob;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Loading extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		
		final ImageView load = (ImageView)findViewById(R.id.loading);
		final Animation animate = AnimationUtils.loadAnimation(getBaseContext(), R.layout.rotate);
		
		
		load.startAnimation(animate);
		animate.setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				finish();
				startActivity(new Intent(Loading.this, MainActivity.class));
			}
		});
	}

	

}
