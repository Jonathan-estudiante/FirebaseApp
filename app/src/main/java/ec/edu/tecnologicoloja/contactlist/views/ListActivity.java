package ec.edu.tecnologicoloja.contactlist.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ec.edu.tecnologicoloja.contactlist.R;
import ec.edu.tecnologicoloja.contactlist.adapter.ContactAdapter;
import ec.edu.tecnologicoloja.contactlist.model.Contact;


public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ListView mListView;
    private ContactAdapter contactAdapter;
    private ArrayList<Contact> listaContactos = new ArrayList<>();
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Set views
        mListView = findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);
        inicializarFirebase();
        listarDatos();

        // check if list is null or empty to show the list or an informative text
        if (listaContactos != null && listaContactos.size() > 0) {
            mListView.setVisibility(View.VISIBLE);
        } else {
            mListView.setVisibility(View.GONE);
        }
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon_eliminar_todo: {
                break;
            }
            case R.id.icon_nuevo_contacto:
                Intent intent = new Intent(ListActivity.this, SaveActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.itemEliminar:
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Aviso");
                dialogo1.setMessage("¿Desea eliminar este contacto?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Toast t = Toast.makeText(ListActivity.this, "Contacto eliminado", Toast.LENGTH_SHORT);
                        t.show();
                        Contact posicion = (Contact) contactAdapter.getItem(info.position);
                        contact.setObjectId(posicion.getObjectId());

                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.show();
                break;
            case R.id.itemEditar:

                break;

            default:
                break;
        }
        return true;
    }

    private void delete(String position) {
        // creating a variable for our Database
        // Reference for Firebase.
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Contacts");
        // we are use add listerner
        // for event listener method
        // which is called with query.
        Query query = dbref.child(position);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // remove the value at refernce
                dataSnapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // start detail screen when an item list is pressed
        Intent intent = new Intent();
        intent.setClass(ListActivity.this, ContactActivity.class);
        intent.putExtra("id", listaContactos.get(position).getObjectId());
        startActivity(intent);
    }

    private void listarDatos() {
        databaseReference.child("Contact").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                listaContactos.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Contact contact = dataSnapshot.getValue(Contact.class);
                    listaContactos.add(contact);
                    contactAdapter = new ContactAdapter(ListActivity.this, listaContactos);
                    mListView.setAdapter(contactAdapter);
                    registerForContextMenu(mListView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
