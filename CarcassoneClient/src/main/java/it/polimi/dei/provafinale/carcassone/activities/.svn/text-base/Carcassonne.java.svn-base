/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.polimi.dei.provafinale.carcassone.activities;

// Need the following import to get access to the app resources, since this
// class is in a sub-package.

import it.polimi.dei.provafinale.carcassone.R;
import it.polimi.dei.provafinale.carcassone.controller.MenuController;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Carcassonne extends Activity {

	private MenuController controller;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.principal_menu);
		controller = new MenuController();
		Button connect = (Button) findViewById(R.id.connectButton);
		connect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText ipEditText = (EditText) findViewById(R.id.ipEditText);
				String ipServer = ipEditText.getText().toString();
				if(!controller.validIPServerFormat(ipServer)) {
					return;
				}
				EditText portEditText = (EditText) findViewById(R.id.portEditText);
				String port = portEditText.getText().toString();
				if(!controller.validPortFormat(port)) {
					return;
				}
				// TODO Launch Connecting Activity
			}
		});
	}
}
