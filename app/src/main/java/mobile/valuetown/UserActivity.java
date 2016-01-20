package mobile.valuetown;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mobile.valuetown.bdd.CurrentStore;
import mobile.valuetown.bdd.User;
import mobile.valuetown.meta.BaseActivity;

public class UserActivity extends BaseActivity {

    private ArrayList<EditText> _infoTextList = new ArrayList<>();
    private Button _returnBtn;
    private Button _editBtn;
    private Button _cancelBtn;
    private Button _saveBtn;
    private Button _map;
    private EditText _userName;
    private EditText _surname;
    private EditText _addr;
    private EditText _code;
    private EditText _tel;
    private TextView _mag;
    private TextView _codeMag;
    private TextView _telMag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);

        setUpListeners();


    }

    private void setUpListeners(){

        //LINK UI OBJECTS TO XML HERE
        _returnBtn = (Button)  findViewById(R.id.accountReturn);
        _editBtn = (Button)  findViewById(R.id.accountEdit);
        _cancelBtn = (Button)  findViewById(R.id.cancel);
        _saveBtn = (Button)  findViewById(R.id.save);
        _map = (Button) findViewById(R.id.map);
        _userName = (EditText) findViewById(R.id.etname);
        _surname = (EditText) findViewById(R.id.etsurname);
        _addr = (EditText) findViewById(R.id.etaddr);
        _code = (EditText) findViewById(R.id.etcode);
        _tel = (EditText) findViewById(R.id.ettel);
        _mag = (TextView) findViewById(R.id.tvstore2);
        _codeMag= (TextView) findViewById(R.id.tvstorecode2);
        _telMag= (TextView) findViewById(R.id.tvstoretel2);

        _code.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        _tel.setRawInputType(InputType.TYPE_CLASS_NUMBER);

        _infoTextList.add(_userName);
        _infoTextList.add(_surname);
        _infoTextList.add(_addr);
        _infoTextList.add(_code);
        _infoTextList.add(_tel);

        _userName.setText(User.getInstance().getName());
        _surname.setText(User.getInstance().getSurname());
        _addr.setText(User.getInstance().getAddr());
        _tel.setText(User.getInstance().getTel());
        int u = User.getInstance().getCode();
        if (u == 0) {_code.setText("");}else{_code.setText(""+u);}
        _mag.setText(CurrentStore.getInstance().getVille());
        _telMag.setText(CurrentStore.getInstance().getTel());
        int i = CurrentStore.getInstance().getCode();
        if (i == 0) {_codeMag.setText("");}else{_codeMag.setText(""+u);}
        _codeMag.setText(""+i);

        if (CurrentStore.getInstance().getVille().equals("")) {_map.setVisibility(View.GONE);}

        _returnBtn.setOnClickListener(new View.OnClickListener(){
            //end activity
            @Override
            public void onClick(View v) {
                UserActivity.this.finish();
            }
        });

        _map.setOnClickListener(new View.OnClickListener() {
            //Sets the screen to be able to edit the account information
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UserActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        _editBtn.setOnClickListener(new View.OnClickListener() {
            //Sets the screen to be able to edit the account information
            @Override
            public void onClick(View v) {
                // hide the edit button and show the save button
                _returnBtn.setVisibility(View.GONE);
                _editBtn.setVisibility(View.GONE);
                _cancelBtn.setVisibility(View.VISIBLE);
                _saveBtn.setVisibility(View.VISIBLE);

                //Make the text fields editable
                for (EditText text : _infoTextList) {
                    text.setEnabled(true);
                    text.setFocusableInTouchMode(true);
                    text.setBackgroundColor(Color.LTGRAY);
                }
            }
        });

        _cancelBtn.setOnClickListener(new View.OnClickListener() {
            //Returns the screen to just show the account information
            @Override
            public void onClick(View v) {
                _returnBtn.setVisibility(View.VISIBLE);
                _editBtn.setVisibility(View.VISIBLE);
                _cancelBtn.setVisibility(View.GONE);
                _saveBtn.setVisibility(View.GONE);


                for (EditText text : _infoTextList) {
                    text.setEnabled(false);
                    text.setFocusableInTouchMode(false);
                    text.setBackgroundColor(Color.TRANSPARENT);
                }
                _userName.setText(User.getInstance().getName());
                _surname.setText(User.getInstance().getSurname());
                _addr.setText(User.getInstance().getAddr());
                _tel.setText(User.getInstance().getTel());
                int k = User.getInstance().getCode();
                if (k == 0) {_code.setText("");}else{_code.setText(""+k);}


            }
        });


        _saveBtn.setOnClickListener(new View.OnClickListener() {
            //Takes the data from the edit fields and saves only if the username is unique and
            //the password and confirm password are the same. Then returns screen to show account info.
            @Override
            public void onClick(View v) {

                //Show toasts for error handling.
                if(_userName.getText().toString().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(), getString(R.string.user_toast_name),
                            Toast.LENGTH_LONG).show();
                }else if(_surname.getText().toString().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(),  getString(R.string.user_toast_surname),
                            Toast.LENGTH_LONG).show();
                }else if(_addr.getText().toString().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(),  getString(R.string.user_toast_addr),
                            Toast.LENGTH_LONG).show();
                }else if(_code.getText().toString().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(),  getString(R.string.user_toast_code),
                            Toast.LENGTH_LONG).show();
                }else if(_tel.getText().toString().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(),  getString(R.string.user_toast_tel),
                            Toast.LENGTH_LONG).show();
                }else{
                    _returnBtn.setVisibility(View.VISIBLE);
                    _editBtn.setVisibility(View.VISIBLE);
                    _cancelBtn.setVisibility(View.GONE);
                    _saveBtn.setVisibility(View.GONE);

                    for (EditText text : _infoTextList) {
                        text.setEnabled(false);
                        text.setFocusableInTouchMode(false);
                        text.setBackgroundColor(Color.TRANSPARENT);
                    }

                    User.getInstance().setName( _userName.getText().toString());
                    User.getInstance().setSurname(_surname.getText().toString());
                    User.getInstance().setAddr(_addr.getText().toString());
                    User.getInstance().setTel(_tel.getText().toString());
                    User.getInstance().setCode(Integer.parseInt( _code.getText().toString()));

                    //StoreClerk.getInstance().updateAccount(_accountInfo, _currentUser);

                }

            }
        });
    }

}
