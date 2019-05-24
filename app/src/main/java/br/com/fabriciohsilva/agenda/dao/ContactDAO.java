package br.com.fabriciohsilva.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.fabriciohsilva.agenda.model.Contact;


public class ContactDAO extends SQLiteOpenHelper {

    private final static List<Contact> contacts = new ArrayList<>();

    public ContactDAO(Context context) {
        super(context, "ContactList", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Contacts (id INTEGER PRIMARY KEY, name TEXT NOT NULL, telephone TEXT, email TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Contacts";
        db.execSQL(sql);
        onCreate(db);
    }

    public void save(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getContactData(contact);
        db.insert("Contacts", null, data);
    }

    @NonNull
    private ContentValues getContactData(Contact contact) {
        ContentValues data = new ContentValues();
        data.put("name", contact.getName());
        data.put("telephone", contact.getTelephone());
        data.put("email", contact.getEmail());
        return data;
    }

    public void edit(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getContactData(contact);
        String[] params = {contact.getId().toString()};
        db.update("Contacts", data, "id = ?", params);
    }


    @Nullable
    private Contact searchId(Contact contact) {
        for (Contact a :
                contacts) {
            if (Objects.equals(a.getId(), contact.getId())) {
                return a;
            }
        }
        return null;
    }

    public List<Contact> all() {
        String sql = "SELECT * from Contacts;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Contact> contacts = new ArrayList<>();
        while(c.moveToNext()){
            Contact contact = new Contact();
            contact.setId(c.getLong(c.getColumnIndex("id")));
            contact.setName(c.getString(c.getColumnIndex("name")));
            contact.setTelephone(c.getString(c.getColumnIndex("telephone")));
            contact.setEmail(c.getString(c.getColumnIndex("email")));
            contacts.add(contact);
        }
        c.close();
        return contacts;
    }

    public void remove(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {contact.getId().toString()};
        db.delete("Contacts", "id = ?", params);
    }

}
