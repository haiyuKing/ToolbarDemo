package com.why.project.toolbardemo.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.why.project.toolbardemo.R;

/**
 * Created by HaiyuKing
 * Used 演示如何使用toolbar的对话框界面
 */

public class HasToolbarDialog extends DialogFragment{
	private static final String TAG = HasToolbarDialog.class.getSimpleName();

	public static final String TAG_FULLSCREEN = "fullScreen";//全屏
	public static final String TAG_BELOWSTATUEBAR = "belowStatusBar";//状态栏下方

	/**View实例*/
	private View myView;
	/**context实例*/
	private Context mContext;
	/**标记：用来代表是从哪个界面打开的这个对话框*/
	private String mTag;

	private Toolbar mToolbar;


	public static HasToolbarDialog getInstance(Context mContext, Bundle bundle)
	{
		HasToolbarDialog dialog = new HasToolbarDialog();
		dialog.mContext = mContext;
		return dialog;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//android.R.style.Theme_Material_NoActionBar_Fullscreen这个样式好看
		//Display fullscreen without actionbar
		//http://www.itgo.me/a/7477281837685192473/how-to-make-material-design-full-screen-dialog
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);//全屏（在状态栏底下）
		} else {
			setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));//设置背景为透明，并且没有标题
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		//设置窗体全屏
		getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		myView = inflater.inflate(R.layout.activity_toolbar_normal, container, false);
        /*this.getDialog().setOnKeyListener(new DialogInterface.OnKeyListener()
        {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event){
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    LogUtil.w(TAG, "onKey");
                    dismiss();
                    return true; // return true是中断事件，那么下面的就接受不到按键信息了
                }else
                {
                    return false; //在return false的时候 才会事件继续向下传递。
                }
            }
        });*/
		return myView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		initToolBar();//初始化toolbar

		//初始化控件以及设置
		initView();
		//初始化数据
		initDatas();
		initEvents();
	}

	/**
	 * 设置宽度和高度值，以及打开的动画效果
	 */
	@Override
	public void onStart() {
		super.onStart();

		if(mTag.equals(TAG_FULLSCREEN)){//全屏显示
			//设置对话框的宽高，必须在onStart中
			Window window = this.getDialog().getWindow();
			window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);//全屏（盖住状态栏）
			window.setGravity(Gravity.BOTTOM);//设置在底部
			//打开的动画效果
		}else{
			//从我的场景列表界面中设置按钮打开的
			//设置对话框的宽高，必须在onStart中
			DisplayMetrics metrics = new DisplayMetrics();
			this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
			Window window = this.getDialog().getWindow();
			if(getStatusBarHeight(mContext) <= 96){
				window.setLayout(metrics.widthPixels, metrics.heightPixels - getStatusBarHeight(mContext));
			}else{
				window.setLayout(metrics.widthPixels, this.getDialog().getWindow().getAttributes().height);//适配红米6pro刘海屏
			}
			window.setGravity(Gravity.BOTTOM);//设置在底部
			//打开的动画效果
		}
	}

	/**获取状态栏的高度*/
	private int getStatusBarHeight(Context context) {
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		return context.getResources().getDimensionPixelSize(resourceId);
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}

	private void initToolBar() {
		mToolbar = myView.findViewById(R.id.toolbar_base);
		mToolbar.setTitle("");//这样设置的话，自带的标题就不会显示
		//设置自定义的标题（居中）
		TextView toolBarTitle = mToolbar.findViewById(R.id.toolbarTitle);
		toolBarTitle.setText("对话框标题");
		//setSupportActionBar(mToolbar);//由于toolbar只是一个普通控件，我们将ToolBar设置为ActionBar【dialogFragment中去掉】
		//设置导航图标要在setSupportActionBar方法之后
		mToolbar.setNavigationIcon(R.drawable.nav_back);//设置为空的话，就会不显示左侧的图标
		//对NavigationIcon添加点击
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//关闭对话框，返回null
				dismiss();
			}
		});

		//添加menu 菜单点击事件
		mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()){
					case R.id.action_edit:
						Toast.makeText(mContext,"编辑",Toast.LENGTH_SHORT).show();
						break;
					case R.id.action_share:
						Toast.makeText(mContext,"分享",Toast.LENGTH_SHORT).show();
						break;
					case R.id.action_publish:
						Toast.makeText(mContext,"发布",Toast.LENGTH_SHORT).show();
						break;
				}
				return true;
			}
		});
		//https://stackoverflow.com/questions/27608399/toolbar-in-dialogfragment
		// Inflate a menu to be displayed in the toolbar
		mToolbar.inflateMenu(R.menu.toolbar_popup_menu);
	}

	/**实例化控件*/
	private void initView() {

	}
	/**
	 * 初始化数据：tag标记、标题
	 */
	private void initDatas() {
		mTag = this.getTag();
	}

	/**
	 * 初始化监听事件
	 */
	private void initEvents() {

	}
}
