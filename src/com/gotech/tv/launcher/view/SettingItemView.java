package com.gotech.tv.launcher.view;

import com.gotech.tv.launcher.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SettingItemView extends FrameLayout {

    private TextView mItemName;
    private TextView mItemValue;
    private EditText mItemValueEditor;

    public SettingItemView(Context context) {
        this(context, null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(getContext()).inflate(R.layout.setting_network_item, this, true);
        mItemName = (TextView) findViewById(R.id.nametxt);
        mItemValue = (TextView) findViewById(R.id.namevaluetxt);
        mItemValueEditor = (EditText) findViewById(R.id.editvaluetxt);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.settingItem);
        String text = a.getString(R.styleable.settingItem_name);
        if (text != null)
            mItemName.setText(text);

        text = a.getString(R.styleable.settingItem_value);
        if (text != null) {
            mItemValue.setText(text);
            mItemValueEditor.setText(text);
        }
        a.recycle();
    }

    public void setEditorMode(boolean bEdit) {
        if (bEdit) {
            mItemValueEditor.setVisibility(VISIBLE);
            mItemValue.setVisibility(GONE);
        } else {
            mItemValue.setVisibility(VISIBLE);
            mItemValueEditor.setVisibility(GONE);
        }
    }

    public void setItemName(String name) {
        mItemName.setText(name);
    }

    public void setItemName(int res) {
        mItemName.setText(res);
    }

    public void setItemValue(String name) {
        mItemValue.setText(name);
        mItemValueEditor.setText(name);
    }

    public void setItemValue(int res) {
        mItemValue.setText(res);
        mItemValueEditor.setText(res);
    }

    public String getEditText() {
        return mItemValueEditor.getText().toString();
    }

    public String getItemName() {
        return mItemName.getText().toString();
    }

    public void setTextWatcher(TextWatcher tw) {
        mItemValueEditor.addTextChangedListener(tw);
    }
}
