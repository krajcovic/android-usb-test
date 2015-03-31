package cz.krajcovic.example.usbtest;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

public class UsbAccessoriesViewLoader extends ListActivity {

	// This is the Adapter being used to display the list's data
	//private SimpleCursorAdapter mAdapter;
	private ArrayAdapter<String> mAdapter;
	

	// These are the Contacts rows that we will retrieve
//	static final String[] PROJECTION = new String[] {
//			ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME };

	// This is the select criteria
//	static final String SELECTION = "((" + ContactsContract.Data.DISPLAY_NAME
//			+ " NOTNULL) AND (" + ContactsContract.Data.DISPLAY_NAME
//			+ " != '' ))";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create a progress bar to display while the list loads
		ProgressBar progressBar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		progressBar.setIndeterminate(true);
		getListView().setEmptyView(progressBar);

		// Must add the progress bar to the root of the layout
		ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
		root.addView(progressBar);

		// For the cursor adapter, specify which columns go into which views
		//String[] fromColumns = { ContactsContract.Data.DISPLAY_NAME };
		//int[] toViews = { android.R.id.text1 }; // The TextView in
												// simple_list_item_1
		
		ArrayList<String> myStringArray = new ArrayList<String>();
		myStringArray.add("Test01");
		myStringArray.add("Test02");
		myStringArray.add("Test03");

		// Create an empty adapter we will use to display the loaded data.
		// We pass null for the cursor, then update it in onLoadFinished()
//		mAdapter = new SimpleCursorAdapter(this,
//				android.R.layout.simple_list_item_1, null, fromColumns,
//				toViews, 0);
		mAdapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, myStringArray);
		setListAdapter(mAdapter);

		// Prepare the loader. Either re-connect with an existing one,
		// or start a new one.
		//getLoaderManager().initLoader(0, null, this);
	}
}
