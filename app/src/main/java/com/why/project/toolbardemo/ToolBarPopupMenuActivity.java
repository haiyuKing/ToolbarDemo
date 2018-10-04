package com.why.project.toolbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HaiyuKing
 * Used 溢出菜单(popup)的样式
 */

public class ToolBarPopupMenuActivity extends AppCompatActivity {
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
		toolBarTitle.setText("预览");
		setSupportActionBar(mToolbar);//由于toolbar只是一个普通控件，我们将ToolBar设置为ActionBar
		//设置导航图标要在setSupportActionBar方法之后
		//mToolbar.setNavigationIcon(null);//设置为空的话，就会不显示左侧的图标
		//对NavigationIcon添加点击
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		//添加menu 菜单点击事件
		mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()){
					case R.id.action_edit:
						Toast.makeText(ToolBarPopupMenuActivity.this,"编辑",Toast.LENGTH_SHORT).show();
						break;
					case R.id.action_share:
						Toast.makeText(ToolBarPopupMenuActivity.this,"分享",Toast.LENGTH_SHORT).show();
						break;
					case R.id.action_publish:
						Toast.makeText(ToolBarPopupMenuActivity.this,"发布",Toast.LENGTH_SHORT).show();
						break;
				}
				return true;
			}
		});
	}

	@Override
	public boolean onCreatePanelMenu(int featureId, Menu menu) {
		getMenuInflater().inflate(R.menu.toolbar_popup_menu, menu);//toolbar添加menu菜单
		return true;
	}
}
