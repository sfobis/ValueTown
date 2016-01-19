package mobile.valuetown;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import mobile.valuetown.bdd.User;
import mobile.valuetown.meta.BaseActivity;

public class UserActivity extends BaseActivity {

    private ArrayList<EditText> _infoTextList = new ArrayList<>();
    private Button _returnBtn;
    private Button _editBtn;
    private Button _cancelBtn;
    private Button _saveBtn;
    private EditText _userName;
    private EditText _surname;
    private EditText _addr;
    private EditText _code;

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
        _userName = (EditText) findViewById(R.id.etname);
        _surname = (EditText) findViewById(R.id.etsurname);
        _addr = (EditText) findViewById(R.id.etaddr);
        _code = (EditText) findViewById(R.id.etcode);



        _infoTextList.add(_userName);
        _infoTextList.add(_surname);
        _infoTextList.add(_addr);
        _infoTextList.add(_code);

        _userName.setText(User.getInstance().getName());
        _surname.setText(User.getInstance().getSurname());
        _addr.setText(User.getInstance().getAddr());
        _code.setText(User.getInstance().getCode());


        _returnBtn.setOnClickListener(new View.OnClickListener(){
            //end activity
            @Override
            public void onClick(View v) {
                UserActivity.this.finish();
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
                _code.setText(User.getInstance().getCode());

            }
        });


        _saveBtn.setOnClickListener(new View.OnClickListener() {
            //Takes the data from the edit fields and saves only if the username is unique and
            //the password and confirm password are the same. Then returns screen to show account info.
            @Override
            public void onClick(View v) {

                //Show toasts for error handling.
                if(_userName.getText().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(), "Entrez un nom!",
                            Toast.LENGTH_LONG).show();
                }

                else if(_surname.getText().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(), "Entrez un nom de famille!",
                            Toast.LENGTH_LONG).show();
                }

                else if(_addr.getText().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(), "Entrez une adresse!",
                            Toast.LENGTH_LONG).show();
                }

                else if(_code.getText().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(), "Entrez un code postal!",
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
                    User.getInstance().setCode(_code.getText().toString());

                    //StoreClerk.getInstance().updateAccount(_accountInfo, _currentUser);

                }

            }
        });
    }

}
