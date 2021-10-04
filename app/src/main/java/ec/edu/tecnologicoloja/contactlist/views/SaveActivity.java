package ec.edu.tecnologicoloja.contactlist.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import ec.edu.tecnologicoloja.contactlist.R;
import ec.edu.tecnologicoloja.contactlist.model.Contact;

public class SaveActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private Button BtnCancelar, BtnGuardar;
    private EditText txtNombre, txtNumero, txtCumple, txtCiudad, txtPais, txtGenero, txtDescripcion, txtCorreo, txtFoto;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        BtnCancelar = (Button) findViewById(R.id.btnCancel);
        BtnGuardar = (Button) findViewById(R.id.btnSave);

        txtNombre = (EditText) findViewById(R.id.editName);
        txtNumero = (EditText) findViewById(R.id.editNumber);
        txtCumple = (EditText) findViewById(R.id.editBirthday);
        txtCiudad = (EditText) findViewById(R.id.editCity);
        txtPais = (EditText) findViewById(R.id.editCountry);
        txtGenero = (EditText) findViewById(R.id.editGender);
        txtDescripcion = (EditText) findViewById(R.id.editDescription);
        txtCorreo = (EditText) findViewById(R.id.editEmail);
        txtFoto = (EditText) findViewById(R.id.editPhoto);

        inicializarFirebase();

        BtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        BtnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(SaveActivity.this);
                dialogo1.setTitle("Aviso!");
                dialogo1.setMessage("¿Estás seguro de agregar este contacto?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Contact contact = new Contact();
                        contact.setObjectId(UUID.randomUUID().toString());
                        contact.setName(txtNombre.getText().toString().trim());
                        contact.setPhone(txtNumero.getText().toString().trim());
                        contact.setBirthday(txtCumple.getText().toString().trim());
                        contact.setCity(txtCiudad.getText().toString().trim());
                        contact.setCountry(txtPais.getText().toString().trim());
                        contact.setGender(txtGenero.getText().toString().trim());
                        contact.setDescription(txtDescripcion.getText().toString().trim());
                        contact.setEmail(txtCorreo.getText().toString().trim());
                        contact.setImageUrl(txtFoto.getText().toString().trim());

                        databaseReference.child("Contact").child(contact.getObjectId()).setValue(contact);
                        Toast toast = Toast.makeText(SaveActivity.this, "Contacto Agregado!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                        Intent intent = new Intent(SaveActivity.this, ListActivity.class);
                        startActivity(intent);
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.show();
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}