package com.barath.app;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

public class OrientDBGraphTest {
	
	private static final Logger logger = LoggerFactory.getLogger(OrientDBGraphTest.class);
	private static final String ORIENTDB_URI="plocal:/Users/barath/Downloads/orientdb-3.0.13/databases/test-db";
	private static final String ORIENTDB_USERNAME="admin";
	private static final String ORIENTDB_PASSWORD="admin";
	
	
	@Test
	public void testOrientDBWithTx() {
		
		OrientGraphFactory graphFactory = new OrientGraphFactory(ORIENTDB_URI,ORIENTDB_USERNAME,ORIENTDB_PASSWORD);
		OrientGraph graph=graphFactory.getTx();
		
		Vertex person1=	graph.addVertex("class:Person");
		person1.setProperty("name", "PANKAJ");
		person1.setProperty("age", 26);
		
		Vertex vAddress = graph.addVertex("class:Address");
		vAddress.setProperty("street", "Van Ness Ave.");
		vAddress.setProperty("city", "San Francisco");
		vAddress.setProperty("state", "California");
		
		OrientEdge livesEdge=graph.addEdge(null, person1, vAddress, "lives");
		System.out.println("Edge is created "+livesEdge);
	
		graph.commit();
		Iterable<Edge>  edges=graph.getEdgesOfClass("lives");
		edges.forEach( (edge) -> {
			edge.getPropertyKeys().forEach( key -> logger.info(" key {}",key));
			logger.info("EDGE label {}",edge.getLabel());
		});
		
	}
	
	@Test
	public void testOrientDBWithNoTx() {
		
		OrientGraphFactory graphFactory = new OrientGraphFactory(ORIENTDB_URI,ORIENTDB_USERNAME,ORIENTDB_PASSWORD);
		OrientGraphNoTx graph=graphFactory.getNoTx();
		
		Vertex person1=	graph.addVertex("class:Person");
		person1.setProperty("name", "PANKAJ");
		person1.setProperty("age", 26);
		
		Vertex vAddress = graph.addVertex("class:Address");
		vAddress.setProperty("street", "Van Ness Ave.");
		vAddress.setProperty("city", "San Francisco");
		vAddress.setProperty("state", "California");
		
		OrientEdge livesEdge=graph.addEdge(null, person1, vAddress, "lives");
		System.out.println("Edge is created "+livesEdge);
	
		graph.commit();
		Iterable<Edge>  edges=graph.getEdgesOfClass("lives");
		edges.forEach( (edge) -> {
			edge.getPropertyKeys().forEach( key -> logger.info(" key {}",key));
			logger.info("EDGE label {}",edge.getLabel());
		});
		
	}
	
	@Test
	public void testOrientDBOrientGraph() {
		OrientGraph graph = new OrientGraph(ORIENTDB_URI,ORIENTDB_USERNAME, ORIENTDB_PASSWORD);
        try {
            Vertex vPerson = graph.addVertex("class:Person");
            vPerson.setProperty("name", "John");
            vPerson.setProperty("age", 24);

            Vertex vAddress = graph.addVertex("class:Address");
            vAddress.setProperty("street", "Van Ness Ave.");
            vAddress.setProperty("city", "San Francisco");
            vAddress.setProperty("state", "California");
        } finally {
            graph.shutdown();
        }   
       
	}

}
