package com.example.test01.one;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test01.R;


public class ReaderFragment extends Fragment {
    View mView;
    TextView imfoText;
    EditText et_search;
    ListView myListView;
    RecordSQLiteOpenHelper helper;
    SQLiteDatabase db;
    BaseAdapter adapter;
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView =inflater.inflate(R.layout.reader,container,false);

        initView();

        et_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)
                {
                    //隐藏键盘
                    ((InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS
                    );
                    boolean hasdate=hasDate(et_search.getText().toString().trim());
                    if(!hasdate)
                    {
                        insertData(et_search.getText().toString().trim());
                        queryData("");
                    }
                    imfoText.setText("zhaoxitech://com.zhaoxitech.cbook/reader?bookid="+et_search.getText().toString());
                }
                return false;
            }


        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                queryData(et_search.getText().toString());
            }
        });

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView=view.findViewById(android.R.id.text1);
                et_search.setText(textView.getText().toString());
                Toast.makeText(getContext(),textView.getText().toString(),Toast.LENGTH_SHORT).show();
                imfoText.setText("zhaoxitech://com.zhaoxitech.cbook/reader?bookid="+textView.getText().toString());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean hasdate=hasDate(et_search.getText().toString().trim());
                if(!hasdate)
                {
                    insertData(et_search.getText().toString().trim());
                    queryData("");
                }
                String pkgName = "com.zhaoxitech.cbook";
                String bookid = et_search.getText().toString();
                Uri uri = new Uri.Builder()
                        .scheme("zhaoxitech")
                        .authority(pkgName)
                        .path("reader")
                        .appendQueryParameter("bookId", bookid)
                        .build();
                imfoText.setText(uri.toString());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setPackage(pkgName);
                intent.setData(uri);
                startActivity(intent);

            }
        });
        //queryData("");
        return mView;
    }

    private void queryData(String s) {
        // room
        Cursor cursor=helper.getReadableDatabase().rawQuery
                ("select id as _id,name from records where name like '%"+ s +"%' order by id desc",null);
        adapter=new SimpleCursorAdapter(getContext(),android.R.layout.simple_list_item_1,cursor,
                new String[]{"name"},new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        myListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void insertData(String trim) {
        db=helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('"+trim+"')");
        db.close();
    }

    private boolean hasDate(String tempName) {
        Cursor cursor=helper.getReadableDatabase().rawQuery("select id as _id,name from records where name =?",new String[]{tempName});
        return cursor.moveToNext();
    }

    private void initView() {
        et_search= mView.findViewById(R.id.et_search);
        myListView= mView.findViewById(R.id.listView);
        helper=new RecordSQLiteOpenHelper(getContext());
        imfoText= mView.findViewById(R.id.text_detail1);
        button= mView.findViewById(R.id.button2);

    }
}
