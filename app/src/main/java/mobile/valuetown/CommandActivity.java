package mobile.valuetown;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mobile.valuetown.bdd.Cart;
import mobile.valuetown.bdd.Product;
import mobile.valuetown.bdd.User;

public class CommandActivity extends AppCompatActivity {

    private ArrayList<String> cart = new ArrayList<>();
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
        setContentView(R.layout.activity_command);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for ( Product p : Cart.getInstance().getProducts()){
            cart.add(p.getName());
        }
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cart));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        builder.setMessage("kappa?")
                .setTitle("title kappa");

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        final AlertDialog dialog = builder.create();
        setUpListeners();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });


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
                CommandActivity.this.finish();
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
                    Toast.makeText(getApplicationContext(),  getString(R.string.user_toast_name),
                            Toast.LENGTH_LONG).show();
                }

                else if(_surname.getText().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(),  getString(R.string.user_toast_surname),
                            Toast.LENGTH_LONG).show();
                }

                else if(_addr.getText().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(), getString(R.string.user_toast_addr),
                            Toast.LENGTH_LONG).show();
                }

                else if(_code.getText().equals("")) {
                    // post toast
                    Toast.makeText(getApplicationContext(),  getString(R.string.user_toast_code),
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
