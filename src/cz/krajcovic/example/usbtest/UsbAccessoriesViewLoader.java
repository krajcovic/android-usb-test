package cz.krajcovic.example.usbtest;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.PendingIntent;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

public class UsbAccessoriesViewLoader extends ListActivity {
	
	protected static final String TAG = UsbAccessoriesViewLoader.class.getName();

	// This is the Adapter being used to display the list's data
	//private SimpleCursorAdapter mAdapter;
	private ArrayAdapter<String> mAdapter;
	
	private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
	
	private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (ACTION_USB_PERMISSION.equals(action)) {
				synchronized (this) {
					UsbAccessory accessory = (UsbAccessory) intent
							.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);

					if (intent.getBooleanExtra(
							UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
						if (accessory != null) {
							// call method to set up accessory communication
						}
					} else {
						Log.d(TAG, "permission denied for accessory "
								+ accessory);
					}
				}
			}
		}
	};
	

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
		//myStringArray.add("Test02");
		//myStringArray.add("Test03");
		
		UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
		UsbAccessory[] accessoryList = manager.getAccessoryList();

		PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 0,
				new Intent(ACTION_USB_PERMISSION), 0);
		IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
		registerReceiver(mUsbReceiver, filter);

		if (accessoryList != null) {
			
			//int index = 0;
			//manager.requestPermission(accessoryList[index], mPermissionIntent);
			for (UsbAccessory usbAccessory : accessoryList) {
				myStringArray.add(usbAccessory.getDescription());
			}
		}

		// Intent intent = new Intent();
		// UsbAccessory accessory = (UsbAccessory) intent
		// .getParcelableExtra(UsbManager.EXTRA_ACCESSORY);

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
