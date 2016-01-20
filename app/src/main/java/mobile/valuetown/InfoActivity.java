package mobile.valuetown;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {

    private Button _returnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _returnBtn = (Button)  findViewById(R.id.inforeturn);

        _returnBtn.setOnClickListener(new View.OnClickListener() {
            //Sets the screen to be able to edit the account information
            @Override
            public void onClick(View v) {
                InfoActivity.this.finish();
            }
        });
    }


}
