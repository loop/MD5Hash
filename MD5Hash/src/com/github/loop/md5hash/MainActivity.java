package com.github.loop.md5hash;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    EditText input;
    TextView md5Display;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	input = (EditText) findViewById(R.id.etStringInput);
	md5Display = (TextView) findViewById(R.id.tvMD5Display);
	Button buttonForMD5 = (Button) findViewById(R.id.bGetMD5);
	buttonForMD5.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.activity_main, menu);
	return true;
    }

    @Override
    public void onClick(View v) {
	registerForContextMenu(md5Display);
	InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	mgr.hideSoftInputFromWindow(md5Display.getWindowToken(), 0);
	String inputString = input.getText().toString();
	StringToMD5 stringToMD5 = new StringToMD5();
	String finalMD5 = stringToMD5.getHashFromString(inputString);
	md5Display.setText(finalMD5);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	// user has long pressed your TextView
	menu.add(0, v.getId(), 0, "Copy");

	// cast the received View to TextView so that you can get its text
	TextView yourTextView = (TextView) v;

	// place your TextView's text in clipboard
	ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
	clipboard.setText(yourTextView.getText());
    }

}
