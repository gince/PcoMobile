package edu.umass.parking.pcomobile.activities;

import java.util.Locale;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import edu.umass.parking.pcomobile.R;
import edu.umass.parking.pcomobile.helpers.DatabaseHelper;
import edu.umass.parking.pcomobile.helpers.ZeroThresholdACTextView;

public class CitationActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	Button mStateButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_citation);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Show the Up button in the action bar.
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Hide the Android status bar.
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	// Opens dialog windows (DialogFragment) to enter citation parameters
	// DialogFragment is constructed with three parameters: title, id and the
	// text of the button
	// that calls the dialog.
	public void showDialog(View v) {
		int button_id = v.getId();
		String dialog_title = (String) v.getTag();
		Button b = (Button) v;
		String button_text = b.getText().toString();

		// That the title is not equal to the button text, means that the
		// operator entered
		// a value for that button. That value should be kept if operator
		// activates the same
		// dialog window.
		button_text = button_text.equals(dialog_title) ? null : button_text;
		DialogFragment newFragment = new CitationDialog(dialog_title,
				button_id, button_text);
		newFragment.show(getSupportFragmentManager(), dialog_title);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.citation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.

			// TO BE DELETED
			/*
			 * Fragment fragment = new DummySectionFragment(); Bundle args = new
			 * Bundle(); args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
			 * position + 1); fragment.setArguments(args); return fragment;
			 */
			Fragment f;
			switch (position) {
			case 0:
				f = new CitationFragment();
				break;
			case 1:
				f = new PictureFragment();
				break;
			case 2:
				f = new SavePrintFragment();
				break;
			default:
				throw new IllegalArgumentException("not this many fragments: "
						+ position);
			}
			return f;

		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	public static class CitationFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public CitationFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_citation,
					container, false);

			return rootView;
		}
	}

	public static class PictureFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public PictureFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_picture,
					container, false);
			return rootView;
		}
	}

	public static class SavePrintFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public SavePrintFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_saveprint,
					container, false);
			return rootView;
		}
	}

	public class CitationDialog extends DialogFragment {
		/** The system calls this only when creating the layout in a dialog. */

		private String _title;
		private int _buttonId;
		private String _buttonText;

		/*
		 * public CitationDialog(){ super(); }
		 */
		public CitationDialog(String s, int id, String text) {
			this._title = s;
			this._buttonId = id;
			this._buttonText = text;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			// Get the layout inflater
			LayoutInflater inflater = getActivity().getLayoutInflater();
			final View view;
			if (_title.equals("Violation"))
				view = inflater.inflate(R.layout.dialog_citation_with_checkbox, null);
			else
				view = inflater.inflate(R.layout.dialog_citation, null);
			// Gets a reference to the AutoCompleteTextView in the layout
			// fragment_citation.xml
			final ZeroThresholdACTextView atw = (ZeroThresholdACTextView) view
					.findViewById(R.id.value);
			atw.setText(_buttonText); // sets the text to default or previous
										// entry
			atw.setThreshold(0); // sets the number of characters after which
									// autocomplete responds
			
			
			// Creates a database instance and gets the codes/descriptions to
			// use for autocomplete
			final DatabaseHelper dh = new DatabaseHelper(view.getContext());
			String[] valuesForAutocomplete = new String[0];

			final Button plateBtn = (Button) findViewById(R.id.plate_button);
			final Button stateBtn = (Button) findViewById(R.id.state_button);
			final String plateBtn_text = plateBtn.getText().toString().toUpperCase();
			final String stateBtn_text = stateBtn.getText().toString().toUpperCase();
			
			if (_title.equals("State"))
				valuesForAutocomplete = dh.getCodesDescsFromLookupTables(
						"states", "code");
			else if (_title.equals("Permit #")) {
				if (!plateBtn_text.equals("Plate #"))
					valuesForAutocomplete = dh.getPermitsByPlate(stateBtn_text, plateBtn_text);
			}
			else if (_title.equals("Make"))
				valuesForAutocomplete = dh.getCodesDescsFromLookupTables(
						"vehicle_makes", "description");
			else if (_title.equals("Color"))
				valuesForAutocomplete = dh.getCodesDescsFromLookupTables(
						"vehicle_colors", "description");
			else if (_title.equals("Type"))
				valuesForAutocomplete = dh.getCodesDescsFromLookupTables(
						"plate_types", "code");
			else if (_title.equals("Location"))
				valuesForAutocomplete = dh.getCodesDescsFromLookupTables(
						"locations", "description");
			else if (_title.equals("Violation"))
				valuesForAutocomplete = dh.getCodesDescsFromLookupTables(
						"violations", "description");
			else if (_title.equals("Comments (public)")
					|| _title.equals("Comments (private)"))
				valuesForAutocomplete = dh.getCodesDescsFromLookupTables(
						"comments", "description");
			else if (_title.equals("Fine"))
				atw.setInputType(InputType.TYPE_CLASS_NUMBER);

			// Creates the adapter and set it to the AutoCompleteTextView
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					CitationActivity.this, android.R.layout.simple_list_item_1,
					valuesForAutocomplete);
			atw.setAdapter(adapter);

			final CheckBox cb = new CheckBox(getApplicationContext());
			// Inflate and set the layout for the dialog
			// Pass null as the parent view because its going in the dialog
			// layout
			builder.setView(view)
					// Add action buttons
					.setPositiveButton(R.string.dialog_confirm_button,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									Button b = (Button) findViewById(_buttonId);
									if (_title.equals("Fine"))
										b.setText("$"
												+ atw.getText().toString());
									else if (_title.equals("Plate #")) {
										b.setText(atw.getText().toString().toUpperCase());
										String[] details = dh.getVehicleDetails(stateBtn_text, atw.getText().toString().toUpperCase());
										Button typeBtn = (Button) findViewById(R.id.veh_type_button);
										Button colrBtn = (Button) findViewById(R.id.veh_color_button);
										Button makeBtn = (Button) findViewById(R.id.veh_make_button);
										typeBtn.setText(details[0]);
										colrBtn.setText(details[1]);
										makeBtn.setText(details[2]);
									}
									else
										b.setText(atw.getText().toString());
								}
							})
					.setNegativeButton(R.string.dialog_cancel_button,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									CitationDialog.this.getDialog().cancel();
								}
							}).setTitle(_title);

			return builder.create();
		}
	}
	
	
}
