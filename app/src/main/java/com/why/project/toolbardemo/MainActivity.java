package com.why.project.toolbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initEvents();
	}

	private void initEvents() {
		findViewById(R.id.btn_title_normal).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,ToolBarNoMenuActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.btn_title_left).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,ToolBarTitleLeftActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.btn_title_center).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,ToolBarTitleCenterActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.btn_no_navigationicon).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,ToolBarNoNavigationiconActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.btn_menu_text).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,ToolBarMenuTextActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.btn_menu_popup).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,ToolBarPopupMenuActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.btn_menu_dialog).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,ToolBarDialogActivity.class);
				startActivity(intent);
			}
		});

		findViewById(R.id.btn_menu_update).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainActivity.this,ToolBarMenuUpdateActivity.class);
				startActivity(intent);
			}
		});
	}
}
