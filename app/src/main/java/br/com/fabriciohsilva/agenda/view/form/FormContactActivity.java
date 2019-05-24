package br.com.fabriciohsilva.agenda.view.form;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fabriciohsilva.agenda.R;
import br.com.fabriciohsilva.agenda.helpers.FormHelper;
import br.com.fabriciohsilva.agenda.model.Contact;
import br.com.fabriciohsilva.agenda.dao.ContactDAO;


public class FormContactActivity extends AppCompatActivity {

    private android.content.res.Resources res;
    private EditText fieldName;
    private EditText fieldTelephone;
    private EditText fieldEmail;
    private final ContactDAO dao = new ContactDAO(this);
    private Contact contact;
    private FormHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        res = getResources();
        String APPBAR_TITLE_NEW_CONTACT = res.getString(R.string.new_contact);
        String APPBAR_TITLE_EDIT_CONTACT = res.getString(R.string.edit_contact);

        helper = new FormHelper(this);

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        if(contact != null){
            helper.fillForm(contact);
            setTitle(APPBAR_TITLE_EDIT_CONTACT);
        } else {
            setTitle(APPBAR_TITLE_NEW_CONTACT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuSave){
            finishForm();
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillFields() {
        fieldName.setText(contact.getName());
        fieldTelephone.setText(contact.getTelephone());
        fieldEmail.setText(contact.getEmail());
    }

    private void finishForm() {
        Contact contact = helper.getContact();
        ContactDAO dao = new ContactDAO(this);

        if(contact.getId() != null){
            dao.edit(contact);
        } else {
            dao.save(contact);
        }
        dao.close();
        Toast.makeText(FormContactActivity.this, res.getString(R.string.contact)+ " " + contact.getName() + " " + res.getString(R.string.save) + ".", Toast.LENGTH_SHORT).show();
        finish();
    }

}
