package com.example.test01.two.view;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.test01.R;
import com.example.test01.two.help.RecordSQLiteOpenHelper;

public class AlertDialogView extends FrameLayout {
    private String mDataName;
    private String mText;
    private Context mContext;
    private View mView;
    private EditText mSearchEt;
    private ListView mListView;
    RecordSQLiteOpenHelper mHelper;
    SQLiteDatabase mDatabase;
    BaseAdapter mAdapter;
    public AlertDialogView(@NonNull Context context) {
        super(context);
        init(context);
    }
    public AlertDialogView(@NonNull Context context, String dbName) {
        super(context);
        mDataName=dbName;
        init(context);
    }

    public AlertDialogView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AlertDialogView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AlertDialogView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.reader, this, false);
        mSearchEt = mView.findViewById(R.id.et_search);
        mListView = mView.findViewById(R.id.listView);
        mHelper = new RecordSQLiteOpenHelper(getContext(), mDataName);

        mSearchEt.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //隐藏键盘
                    /*((InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            ((Activity) mContext).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS
                    );*/
                    InputMethodManager imm = (InputMethodManager) ((Activity) mContext).getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    boolean hasdate = hasDate(mSearchEt.getText().toString().trim());
                    if (!hasdate) {
                        insertData(mSearchEt.getText().toString().trim());
                        queryData("");
                    }

                }
                return false;
            }

        });

        mSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                queryData(mSearchEt.getText().toString());
                mText=mSearchEt.getText().toString();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView=view.findViewById(android.R.id.text1);
                mSearchEt.setText(textView.getText());
                mSearchEt.requestFocus();
                mSearchEt.setSelection(mSearchEt.getText().length());
            }
        });

        addView(mView);
    }

    private void queryData(String s) {
        Cursor cursor=mHelper.getReadableDatabase().rawQuery
                ("select id as _id,name from records where name like '%"+ s +"%' order by id desc",null);
        mAdapter=new SimpleCursorAdapter(getContext(),android.R.layout.simple_list_item_1,cursor,
                new String[]{"name"},new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void insertData(String trim) {
        if(trim!=""&&!hasDate(trim))
        {
            mDatabase=mHelper.getWritableDatabase();
            mDatabase.execSQL("insert into records(name) values('"+trim+"')");
            mDatabase.close();
        }

    }

    private boolean hasDate(String tempName) {
        Cursor cursor=mHelper.getReadableDatabase().rawQuery("select id as _id,name from records where name =?",new String[]{tempName});
        return cursor.moveToNext();
    }
    public String getText()
    {
        return mText;
    }
}