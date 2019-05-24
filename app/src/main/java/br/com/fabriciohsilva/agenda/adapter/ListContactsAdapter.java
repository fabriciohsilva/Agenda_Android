package br.com.fabriciohsilva.agenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.fabriciohsilva.agenda.R;
import br.com.fabriciohsilva.agenda.model.Contact;

public class ListContactsAdapter extends BaseAdapter {

    private final List<Contact> contacts = new ArrayList<>();
    private final Context context;

    public ListContactsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Contact getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contacts.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View createdView = LayoutInflater
                .from(context)
                .inflate(R.layout.item_contact, viewGroup, false);
        Contact contact = contacts.get(position);
        TextView name = createdView.findViewById(R.id.itemContactName);
        name.setText(contact.getName());
        TextView telephone = createdView.findViewById(R.id.itemContactTelephone);
        telephone.setText(contact.getTelephone());
        return createdView;
    }

    public void clear() {
        contacts.clear();
    }

    public void addAll(List<Contact> contacts) {
        this.contacts.addAll(contacts);
    }

    public void remove(Contact contact) {
        contacts.remove(contact);
    }
}
