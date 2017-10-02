package com.barath.app;

import javax.annotation.PostConstruct;

import org.springframework.data.orient.commons.core.OrientDatabaseFactory;
import org.springframework.stereotype.Service;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

import junit.framework.Test;

@Service
public class TestGraphDatabase {
	
	private OrientGraphFactory  graphFactory;
	
	public TestGraphDatabase(OrientGraphFactory  graphFactory) {
		this.graphFactory=graphFactory;
	}
	
	
	@PostConstruct
	public void init() {
		
			OrientGraph graph=this.graphFactory.getTx();
		
		//	graph.createVertexType("Person");
			//graph.createVertexType("Address");
			
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
				edge.getPropertyKeys().forEach(System.out::println);
				System.out.println("EDGE "+edge.getLabel());
			});
	}

}
