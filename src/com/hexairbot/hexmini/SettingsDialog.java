
package com.hexairbot.hexmini;

import com.hexairbot.hexmini.modal.ApplicationSettings;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


@SuppressLint("ValidFragment")
public class SettingsDialog extends DialogFragment
{
    public static final int RESULT_OK = 0;
    public static final int RESULT_CLOSE_APP = 1;

    private static final String TAG = SettingsDialog.class.getSimpleName();
    
    private SettingsViewController settingsVC;
    
    private Context context;
    private SettingsDialogDelegate delegate;
    

    public SettingsDialog(Context context, SettingsDialogDelegate delegate)
    {
        super();
        this.delegate = delegate;
        this.context = context;
    }


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.HexMiniTheme_SettingScreen);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (delegate != null) {
            this.delegate.prepareDialog(this);
        }
    	
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.settings_screen, container, false);
       
        settingsVC = new SettingsViewController(getActivity(), inflater, v, (SettingsViewControllerDelegate)(((HudActivity) delegate).getViewController()));
        
        initListeners();
        
        return v;
    }


    @Override
    public void onStart()
    {
        super.onStart();
    }


    @Override
    public void onStop()
    {
        super.onStop();
    }


    public void onOkClicked(View v)
    {
        dismiss();
    }


    @Override
    public void onDismiss(DialogInterface dialog)
    {
        super.onDismiss(dialog);

        if (delegate != null) {
            delegate.onDismissed(this);
        }
    }


    private void initListeners()
    {
        settingsVC.setBackBtnOnClickListner(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
		        switch (v.getId()) {
		        case R.id.backBtn:
		            dismiss();
		            break;
		        }
			}
        }); 
    }
    

    public void onClick(View v)
    {
        switch (v.getId()) {
        case R.id.backBtn:
            dismiss();
            break;
        }
    }
    
    
    public ViewController getViewController() {
		return settingsVC;
	}
}