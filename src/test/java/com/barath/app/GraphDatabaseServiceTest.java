package com.barath.app;

import org.junit.Test;

import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;


public class GraphDatabaseServiceTest {
	
	private static final String ORIENTDB_URI="plocal:/Users/barath/Downloads/orientdb-3.0.13/databases/test-db";
	private static final String ORIENTDB_USERNAME="admin";
	private static final String ORIENTDB_PASSWORD="admin";
	

	@Test
	public void testOrientGraphDB() {

		OrientGraphFactory graphFactory = new OrientGraphFactory(ORIENTDB_URI,ORIENTDB_USERNAME,ORIENTDB_PASSWORD);
		GraphDatabaseService service = new GraphDatabaseService(graphFactory);
		service.init();
	}

}
