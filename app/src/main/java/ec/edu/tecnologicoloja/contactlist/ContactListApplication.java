package ec.edu.tecnologicoloja.contactlist;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;


public class ContactListApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // The Firebase library must be initialized once with an Android context
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
