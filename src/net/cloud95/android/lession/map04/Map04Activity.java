package net.cloud95.android.lession.map04;

import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;

public class Map04Activity extends FragmentActivity {

	private GoogleMap map;
	private UiSettings uiSettings;

	private LinearLayout title_panel;
	private TableLayout menu_panel;
	private Switch zoom_button_switch, compass_switch, traffic_switch,
			mylocation_button_switch, mylocation_layer_switch,
			scroll_gestures_switch, zoom_gestures_switch, tilt_gestures_switch,
			rotate_gestures_switch;

	private boolean showMenu = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		processViews();
		processControllers();

		configMenuPanel();
		// 移動地圖到台北101
		LatLng taipei101 = new LatLng(25.033807, 121.56503);
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
				taipei101, 12);// 12是camera距離地面高度(1/12)

		map.moveCamera(cameraUpdate);
	}

	@Override
	public void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	@Override
	protected void onStop() {
		super.onStop();
		// 停止模擬座標
	}

	private void setUpMapIfNeeded() {
		if (map == null) {

			map = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();
		}

	}

	private void processViews() {
		// 取得地圖物件
		setUpMapIfNeeded();
		// 取得地圖UI設定物件
		uiSettings = map.getUiSettings();

		title_panel = (LinearLayout) findViewById(R.id.title_panel);
		menu_panel = (TableLayout) findViewById(R.id.menu_panel);

		zoom_button_switch = (Switch) findViewById(R.id.zoom_button_switch);
		compass_switch = (Switch) findViewById(R.id.compass_switch);
		traffic_switch = (Switch) findViewById(R.id.traffic_switch);
		mylocation_button_switch = (Switch) findViewById(R.id.mylocation_button_switch);
		mylocation_layer_switch = (Switch) findViewById(R.id.mylocation_layer_switch);
		scroll_gestures_switch = (Switch) findViewById(R.id.scroll_gestures_switch);
		zoom_gestures_switch = (Switch) findViewById(R.id.zoom_gestures_switch);
		tilt_gestures_switch = (Switch) findViewById(R.id.tilt_gestures_switch);
		rotate_gestures_switch = (Switch) findViewById(R.id.rotate_gestures_switch);
	}

	private void processControllers() {
		title_panel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				// 顯示或隱藏設定面版
				showMenu = !showMenu;//是關就開,是開就關
				menu_panel.setVisibility(showMenu ? View.VISIBLE:View.INVISIBLE);
			}

		});

		// 開啟或關閉地圖的設定
		OnCheckedChangeListener switchListener;
		switchListener = new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(buttonView == zoom_button_switch){
					// 縮放控制按鈕
					uiSettings.setZoomControlsEnabled(isChecked);
				}else if(buttonView == compass_switch){
					// 指南針圖示
					uiSettings.setCompassEnabled(isChecked);
				}else if(buttonView == compass_switch){
					// 交通資訊
					map.setTrafficEnabled(isChecked);
				}else if(buttonView == compass_switch){
					// 我的位置按鈕
					uiSettings.setMyLocationButtonEnabled(isChecked);
				}else if(buttonView == compass_switch){
					// 我的位置圖層
					map.setMyLocationEnabled(isChecked);
				}else if(buttonView == compass_switch){
					// 移動
					uiSettings.setScrollGesturesEnabled(isChecked);
				}else if(buttonView == compass_switch){
					// 縮放
					uiSettings.setZoomGesturesEnabled(isChecked);
				}else if(buttonView == compass_switch){
					// 傾斜
					uiSettings.setTiltGesturesEnabled(isChecked);
				}else if(buttonView == compass_switch){
					// 旋轉
					uiSettings.setRotateGesturesEnabled(isChecked);
				}
			}

		};

		// 註冊所有Switch元件開關狀態改變事件
		zoom_button_switch.setOnCheckedChangeListener(switchListener);
		compass_switch.setOnCheckedChangeListener(switchListener);
		traffic_switch.setOnCheckedChangeListener(switchListener);
		my
	}

	private void configMenuPanel() {
		// 讀取地圖目前的設定
	}

}
