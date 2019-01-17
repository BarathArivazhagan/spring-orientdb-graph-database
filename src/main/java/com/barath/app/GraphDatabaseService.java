package com.barath.app;

import java.awt.RenderingHints.Key;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;


@Service
public class GraphDatabaseService {
	
	private static final Logger logger = LoggerFactory.getLogger(GraphDatabaseService.class);
	private final OrientGraphFactory  graphFactory;
	
	public GraphDatabaseService(OrientGraphFactory  graphFactory) {
		this.graphFactory=graphFactory;
	}
	
	
	@PostConstruct
	public void init() {
			
		logger.info("initializing some values ");
			OrientGraph graph=this.graphFactory.getTx();
					
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

}
