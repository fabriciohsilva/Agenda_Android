package br.com.fabriciohsilva.agenda.helpers;

import android.widget.EditText;

import br.com.fabriciohsilva.agenda.R;
import br.com.fabriciohsilva.agenda.model.Contact;
import br.com.fabriciohsilva.agenda.view.form.FormContactActivity;

public class FormHelper {

    private final EditText fieldName;
    private final EditText fieldTelephone;
    private final EditText fieldEmail;

    private Contact contact;

    public FormHelper(FormContactActivity activity){
        fieldName      = (EditText) activity.findViewById(R.id.activity_form_contact_name);
        fieldTelephone = (EditText) activity.findViewById(R.id.activity_form_contact_telephone);
        fieldEmail     = (EditText) activity.findViewById(R.id.activity_form_contact_email);
        contact = new Contact();
    }

    public Contact getContact() {
        contact.setName(fieldName.getText().toString());
        contact.setTelephone(fieldTelephone.getText().toString());
        contact.setEmail(fieldEmail.getText().toString());

        return contact;
    }

    public void fillForm(Contact contact) {
        fieldName.setText(contact.getName());
        fieldTelephone.setText(contact.getTelephone());
        fieldEmail.setText(contact.getEmail());
        this.contact = contact;
    }
}