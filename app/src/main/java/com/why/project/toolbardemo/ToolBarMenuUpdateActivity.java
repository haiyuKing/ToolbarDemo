package com.why.project.toolbardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by HaiyuKing
 * Used 动态更新Menu
 */
public class ToolBarMenuUpdateActivity extends AppCompatActivity {

	private Toolbar mToolbar;

	private boolean showOk = false;//切换图标的状态值，至于默认值是true还是false，根据项目情况而定

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
		toolBarTitle.setText("动态更新Menu");
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
					case R.id.action_save:
						showOrHiddenUpdatedMenu(); //动态更新Menu
						break;
				}
				return true;
			}
		});
	}

	@Override
	public boolean onCreatePanelMenu(int featureId, Menu menu) {
		getMenuInflater().inflate(R.menu.toolbar_one_menu, menu);//toolbar添加menu菜单
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (! showOk) {
			menu.findItem(R.id.action_save).setIcon(R.drawable.nav_menu_fabu);//更新android:icon值
			menu.findItem(R.id.action_save).setTitle("发布");//更新android:title值
		} else {
			menu.findItem(R.id.action_save).setIcon(R.drawable.nav_ok);//更新android:icon值
			menu.findItem(R.id.action_save).setTitle("保存");//更新android:title值
		}
		return super.onPrepareOptionsMenu(menu);
	}

	//动态更新Menu
	private void showOrHiddenUpdatedMenu(){
		if(! showOk){
			showOk = true;
			invalidateOptionsMenu(); //重新绘制menu
		}else{
			showOk = false;
			invalidateOptionsMenu(); //重新绘制menu
		}
	}
}
