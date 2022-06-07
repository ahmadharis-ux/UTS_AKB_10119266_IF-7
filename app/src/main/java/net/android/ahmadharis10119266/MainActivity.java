package net.android.ahmadharis10119266;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.android.ahmadharis10119266.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText tanggal, judul, kategori, catatan;
    Button insert;
    DBHelper DB;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        tanggal = findViewById(R.id.tanggal);
        judul = findViewById(R.id.judul);
        kategori = findViewById(R.id.kategori);
        catatan = findViewById(R.id.catatan);

        insert = findViewById(R.id.btn_insert);

        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggalTXT= tanggal.getText().toString();
                String judulTXT= judul.getText().toString();
                String kategoriTXT= kategori.getText().toString();
                String catatanTXT= catatan.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(tanggalTXT,judulTXT,kategoriTXT,catatanTXT);
                if (checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Not Entry", Toast.LENGTH_SHORT).show();
            }
        });

        binding.bottomNavigationView.setOnItemSelectedListener(item->{
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case  R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.note:
                    replaceFragment(new NoteFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}