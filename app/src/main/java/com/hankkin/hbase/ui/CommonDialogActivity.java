package com.hankkin.hbase.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hankkin.hbase.R;
import com.hankkin.xlibrary.base.BaseAcitvity;

import butterknife.OnClick;

public class CommonDialogActivity extends BaseAcitvity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_dialog;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initToolBar("统一DIALOG");
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_basic_no_title)
    public void showBasicNoTitle() {
        new MaterialDialog.Builder(this)
                .content("这是一个普通无标题对话框")
                .positiveText(R.string.OK)
                .negativeText(R.string.cancel)
                .show();
    }

    @OnClick(R.id.btn_basic)
    public void showBasic(){
        new MaterialDialog.Builder(this)
                .title("标题")
                .content("这是一个普通有标题对话框")
                .positiveText(R.string.OK)
                .negativeText(R.string.cancel)
                .show();
    }

    @OnClick(R.id.btn_basic_icon)
    public void showBasicIcon(){
        new MaterialDialog.Builder(this)
                .iconRes(R.mipmap.ic_launcher)
                .limitIconToDefaultSize()
                .title(R.string.dialog_title)
                .content("这是一个普通带图标的对话框")
                .positiveText(R.string.OK)
                .negativeText(R.string.cancel)
                .show();
    }

    @OnClick(R.id.btn_basic_list_no_title)
    public void showListNoTitle(){
        new MaterialDialog.Builder(this)
                .items(R.array.dialog_list)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        toast(text);
                    }
                }).show();
    }

    @OnClick(R.id.btn_basic_list)
    public void showList(){
        new MaterialDialog.Builder(this)
                .title(R.string.dialog_title)
                .items(R.array.dialog_list)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        toast(text);
                    }
                }).show();
    }

    @OnClick(R.id.btn_choice)
    public void showSingleChoice(){
        new MaterialDialog.Builder(this)
                .title(R.string.dialog_title)
                .items(R.array.dialog_list)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        toast(text);
                        return true;
                    }
                }).positiveText(R.string.OK)
                .show();
    }

    @OnClick(R.id.btn_input)
    public void showInput(){
        new MaterialDialog.Builder(this)
                .title(R.string.input)
                .content(R.string.input)
                .inputType(InputType.TYPE_CLASS_TEXT
                        | InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                        | InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                .inputRange(2,16)
                .positiveText(R.string.OK)
                .input("", "", false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        toast(input);
                    }
                }).show();
    }


    @OnClick(R.id.btn_progress)
    public void showProgres(){
        new MaterialDialog.Builder(this)
                .title(R.string.dialog_title)
                .content("请稍后...")
                .progress(true,0)
                .progressIndeterminateStyle(false)
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                })
                .showListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {

                    }
                })
                .show();
    }


}
