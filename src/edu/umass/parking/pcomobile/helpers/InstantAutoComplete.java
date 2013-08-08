package edu.umass.parking.pcomobile.helpers;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class InstantAutoComplete extends AutoCompleteTextView {
	private int myThreshold;

	public InstantAutoComplete(Context context) {
		super(context);
	}

	public InstantAutoComplete(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public InstantAutoComplete(Context context, AttributeSet attrs) {
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
		return getText().length() >= myThreshold;
	}
	
	@Override
    protected void onFocusChanged(boolean focused, int direction,
            Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            performFiltering(getText(), 0);
        }
    }

	@Override
	public int getThreshold() {
		return myThreshold;
	}
}
