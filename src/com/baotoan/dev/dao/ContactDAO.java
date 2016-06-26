package com.baotoan.dev.dao;

import java.util.Map;

import com.baotoan.dev.entity.Contact;

public interface ContactDAO {
	public boolean addContact(Contact contact);
	public boolean delContact(int id);
	public boolean updateContact(Contact contact);
	public Contact getContact(int id);
	public Map<String, Object> getAll(int currentPage, int numOrRecordPerPage);
}
