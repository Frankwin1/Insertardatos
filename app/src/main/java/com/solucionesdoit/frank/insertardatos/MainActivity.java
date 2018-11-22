package com.solucionesdoit.frank.insertardatos;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editDistrito,editTelefono,editId;
    Button botonAgregar;
    private SQLiteDatabase slq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editName = (EditText)findViewById(R.id.editText_cliente);
        editDistrito =(EditText)findViewById(R.id.editText_distrito);
        editTelefono  =(EditText)findViewById(R.id.editText_telefono);
        botonAgregar =(Button)findViewById(R.id.button_agregar);
        AddData();
    }
    public void AddData(){
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editName.getText().toString();
                String distrito = editDistrito.getText().toString();
                String telefono = editTelefono.getText().toString();
                if (TextUtils.isEmpty(nombre)){
                    Toast.makeText(MainActivity.this,"Por favor escriba su nombre",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(distrito)){
                    Toast.makeText(MainActivity.this, "Porfavor escribe su distrito",Toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(telefono)){
                    Toast.makeText(MainActivity.this,"Por favor escribe su numero de telefono",Toast.LENGTH_LONG).show();
                }
                boolean isInserted = myDb.insertData(nombre,distrito,telefono);
                if(isInserted == true){
                    Toast.makeText(MainActivity.this,"Datos Ingresados",Toast.LENGTH_LONG).show();
                    editName.setText("");
                    editDistrito.setText("");
                    editTelefono.setText("");
                }
                else
                    Toast.makeText(MainActivity.this,"Los datos no se ingresaron",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
