package company.taetech.prowave.activitys;

import android.app.ActionBar;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

import company.taetech.prowave.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView re_list;
    private FirebaseAuth mAuth;
    private FloatingActionButton fab;
    private BluetoothAdapter mBT = null;
    private int ATIVA_BT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Init();
    }

    private void Init() {

        mAuth = FirebaseAuth.getInstance();

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.item_sair:
                logoutusu();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    private void logoutusu(){
        mAuth.signOut();
        Intent sair = new Intent(this, LoginActivity.class);
        startActivity(sair);
        finish();
    }


    @Override
    public void onClick(View v) {
        if (v == fab){
            Toast.makeText(this, "deu bom", Toast.LENGTH_SHORT).show();
        }
    }
}
