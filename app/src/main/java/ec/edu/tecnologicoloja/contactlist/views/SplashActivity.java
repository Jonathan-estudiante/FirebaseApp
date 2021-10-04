package ec.edu.tecnologicoloja.contactlist.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import ec.edu.tecnologicoloja.contactlist.R;
import ec.edu.tecnologicoloja.contactlist.manager.FirebaseContactManager;
import ec.edu.tecnologicoloja.contactlist.model.Contact;

public class SplashActivity extends AppCompatActivity  {

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 2000; // 2 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                // Cuando pasen los 2 segundos, pasamos a la actividad principal de la aplicación
                Intent intent = new Intent(SplashActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
            }
        }, DURACION_SPLASH);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseContactManager.getInstance().getContactFromServer(this);
//    }
//
//    @Override
//    public void onDataChange(DataSnapshot dataSnapshot) {
//
//        for (DataSnapshot contact : dataSnapshot.getChildren()) {
//            FirebaseContactManager.getInstance().getAllContacts();
//        }
//        startMainActivity();
//    }
//
//    @Override
//    public void onCancelled(@NonNull DatabaseError databaseError) {
//        startMainActivity();
//    }
//
//    private void startMainActivity() {
//        startActivity(new Intent(SplashActivity.this, ListActivity.class));
//        finish();
//    }

}
