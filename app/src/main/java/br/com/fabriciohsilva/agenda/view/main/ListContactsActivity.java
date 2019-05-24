package br.com.fabriciohsilva.agenda.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.fabriciohsilva.agenda.R;
import br.com.fabriciohsilva.agenda.model.Contact;
import br.com.fabriciohsilva.agenda.adapter.ListContactsAdapter;
import br.com.fabriciohsilva.agenda.dao.ContactDAO;
import br.com.fabriciohsilva.agenda.view.form.FormContactActivity;


public class ListContactsActivity extends AppCompatActivity {

    private static final String KEY_CONTACT = "contact";
    private final ContactDAO dao = new ContactDAO(this);
    private ListContactsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        android.content.res.Resources res = getResources();
        String APPBAR_TITLE = res.getString(R.string.contact_list);
        
        setTitle(APPBAR_TITLE);
        configFabNewContact();
        configList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.menu_remove, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.menuRemove) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Contact contact = adapter.getItem(menuInfo.position);
            remove(contact);
        }

        return super.onContextItemSelected(item);
    }

    private void configFabNewContact() {
        FloatingActionButton buttonNewContact = findViewById(R.id.fabNewContact);
        buttonNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFormInsertMode();
            }
        });
    }

    private void openFormInsertMode() {
        startActivity(new Intent(this, FormContactActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateContactList();
    }

    private void updateContactList() {
        adapter.clear();
        adapter.addAll(dao.all());
    }

    private void configList() {
        ListView contactList = findViewById(R.id.contactList);
        configAdapter(contactList);
        configItemClickListener(contactList);
        registerForContextMenu(contactList);
    }

    private void remove(Contact contact) {
        dao.remove(contact);
        adapter.remove(contact);
        adapter.notifyDataSetChanged();
    }

    private void configItemClickListener(ListView contactList) {
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Contact contact = (Contact) adapterView.getItemAtPosition(position);
                openFormEditMode(contact);
            }
        });
    }

    private void openFormEditMode(Contact contact) {
        Intent intent = new Intent(ListContactsActivity.this, FormContactActivity.class);
        intent.putExtra(KEY_CONTACT, contact);
        startActivity(intent);
    }

    private void configAdapter(ListView contactList) {
        adapter = new ListContactsAdapter(this);
        contactList.setAdapter(adapter);
    }
}
