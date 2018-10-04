package com.why.project.toolbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HaiyuKing
 * Used 无右侧菜单项样式
 */

public class ToolBarNoMenuActivity extends AppCompatActivity {
	private Toolbar mToolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toolbar_normal);

		initToolBar();//初始化toolbar
	}

	private void initToolBar() {
		mToolbar = findViewById(R.id.toolbar_base);
		mToolbar.setTitle("");//这样设置的话，自带的标题就不会显示
		//设置自定义的标题（居中）
		TextView toolBarTitle = mToolbar.findViewById(R.id.toolbarTitle);
		toolBarTitle.setText("首页");
		setSupportActionBar(mToolbar);//由于toolbar只是一个普通控件，我们将ToolBar设置为ActionBar
		//设置导航图标要在setSupportActionBar方法之后
		mToolbar.setNavigationIcon(R.drawable.nav_notifications);//设置为空的话，就会不显示左侧的图标
		//对NavigationIcon添加点击
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
