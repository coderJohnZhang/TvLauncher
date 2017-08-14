package com.gotech.tv.launcher.view;

import com.gotech.tv.launcher.R;
import com.gotech.tv.launcher.util.Constant;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.ImageButton;

/**
 * @version 1.0
 * @author:john
 * @date：Dec 1, 2015 7:30:10 PM
 * @description: custom dialog
 */
public class CustomDialog extends Dialog {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case Constant.DIALOG_AUTO_DISMISS:
                    dismiss();
                    break;

            }
            super.handleMessage(msg);
        }
    };

    public CustomDialog(Context context) {

        super(context);

    }

    public CustomDialog(Context context, int themeResId, android.view.View.OnClickListener listener) {

        super(context, themeResId);
        initView(listener);

    }

    public CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {

        super(context, cancelable, cancelListener);

    }

    private void initView(android.view.View.OnClickListener listener) {
        ImageButton openButton, moveButton, uninstallButton;
        setContentView(R.layout.dialog_appmanage);
        openButton = (ImageButton) findViewById(R.id.button_open);
        moveButton = (ImageButton) findViewById(R.id.button_move);
        uninstallButton = (ImageButton) findViewById(R.id.button_uninstall);
        ImageButton[] buttons = {openButton, moveButton, uninstallButton};
        for (int i = 0; i < buttons.length; i++) {

            buttons[i].setOnClickListener(listener);

        }
    }

    @Override
    public void show() {
        dialogAutoDismiss();
        super.show();
    }

    public void dialogAutoDismiss() {
        if (mHandler.hasMessages(Constant.DIALOG_AUTO_DISMISS)) {
            mHandler.removeMessages(Constant.DIALOG_AUTO_DISMISS);
        }
        mHandler.sendEmptyMessageDelayed(Constant.DIALOG_AUTO_DISMISS, Constant.TIME_OUT);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            dialogAutoDismiss();
        }
        return super.onKeyDown(keyCode, event);
    }


}
