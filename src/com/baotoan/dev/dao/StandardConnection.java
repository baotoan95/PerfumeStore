package com.baotoan.dev.dao;

import com.baotoan.dev.utils.ConnectionUtil;

public abstract class StandardConnection {
	public ConnectionUtil connection = new ConnectionUtil();
	
	public void releaseConnection() {
		connection = new ConnectionUtil();
	}
}
