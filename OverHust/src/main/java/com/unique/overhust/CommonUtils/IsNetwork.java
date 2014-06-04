package com.unique.overhust.CommonUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.unique.overhust.R;
import com.unique.overhust.UI.NetworkErrorDialog;

/**
 * Created by fhw on 12/21/13.
 */
public class IsNetwork {
    private ConnectivityManager mConnManager;
    private NetworkInfo mNetworkInfo;
    private Context mContext;
    private AlertDialog mAlertDialog;

    //网络状况标记
    private boolean isNetwork = true;

    public IsNetwork(Context context) {
        mContext = context;
        mConnManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkInfo = mConnManager.getActiveNetworkInfo();
    }

    //未连网判断以及提示
    public void isNetwork() {
        if (mNetworkInfo != null) {
            if (mNetworkInfo.isAvailable() == false) {
                //showDialog();
                NetworkErrorDialog mDialog = new NetworkErrorDialog(mContext, R.style.NetworkErrorDialog);
                mDialog.show();
                isNetwork = false;
            }
        } else {
            //showDialog();
            NetworkErrorDialog mDialog = new NetworkErrorDialog(mContext, R.style.NetworkErrorDialog);
            mDialog.show();
            isNetwork = false;
        }
    }

    //传递网络状况
    public boolean getNetworkState() {
        return isNetwork;
    }

    //获得网络类型
    public int getNetworkType() {
        int networkType = mNetworkInfo.getType();
        return networkType;
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("                 网络连接错误");
        builder.setPositiveButton("知道了!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismissDialog();
            }
        });
        builder.setTitle("OverHust");
        mAlertDialog = builder.create();
        mAlertDialog.show();
    }

    //销毁未联网对话框
    private void dismissDialog() {
        mAlertDialog.dismiss();
    }

}

