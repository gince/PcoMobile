package edu.umass.parking.pcomobile.helpers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class ZeroThresholdACTextView extends AutoCompleteTextView {
	private int myThreshold;

	public ZeroThresholdACTextView(Context context) {
		super(context);
	}

	public ZeroThresholdACTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public ZeroThresholdACTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setThreshold(int threshold) {
		if (threshold < 0) {
			threshold = 0;
		}
		myThreshold = threshold;
	}

	@Override
	public boolean enoughToFilter() {
		return true; //getText().length() >= myThreshold;
	}

	@Override
	public int getThreshold() {
		return myThreshold;
	}
}
