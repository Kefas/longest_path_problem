package myJgrapht;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import myPkg.ExportImport;

import org.jgrapht.alg.HamiltonianCycle;
import org.jgrapht.ext.MatrixExporter;
import org.jgrapht.ext.VisioExporter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.graph.WeightedMultigraph;

public class Hamilton {
	private SimpleWeightedGraph<Integer, DefaultWeightedEdge> g = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(
			DefaultWeightedEdge.class);
	private double[][] tab;
	private List<Integer> hamiltonCycle;
	
	public Hamilton(double [][] initTab){
		tab  = initTab;
		
		int x = 0, y = 0;

		List<Integer> vertices = new ArrayList<Integer>();
		
		for (int i = 0; i < tab.length; i++)
			vertices.add(i);

		addVertex(vertices);

		for (x = 0; x < tab.length; x++) {
			for (y = x + 1; y < tab.length; y++)
				addEdge(x, y,  tab[x][y]);
		}
		
	}

	public List<Integer> getHamiltonCycle() {
		return hamiltonCycle;
	}

	public void setHamiltonCycle(List<Integer> hamiltonCycle) {
		this.hamiltonCycle = hamiltonCycle;
	}

	public void addVertex(Integer vertices) {
		g.addVertex(vertices);
	}

	public void addVertex(List<Integer> ids) {
		for (int id : ids) {
			g.addVertex(id);
		}
	}

	public void addEdge(int source, int destination, double tab2) {
		DefaultWeightedEdge edge = g.addEdge(source, destination);
		g.setEdgeWeight(edge, tab2);
	}

	public List<Integer> execute() {
		
		List<Integer> output = HamiltonianCycle
				.getApproximateOptimalForCompleteGraph(g);
		

		// write output to file
		Writer writer = null;
		try {
			writer = new FileWriter("output.txt");
			for (int i = 0; i < output.size(); i++) {
				writer.append(output.get(i).toString());
				writer.append(" ");
			}
			writer.append("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output;
	}

	public void importFromFile(String path) {

		int x = 0, y = 0;

		List<Integer> vertices = new ArrayList<Integer>();
		ExportImport ex = new ExportImport();
		tab = ex.importFromFile(path);

		for (int i = 0; i < tab.length; i++)
			vertices.add(i);
	//	Hamilton h = new Hamilton();
		addVertex(vertices);

		for (x = 0; x < tab.length; x++) {
			for (y = x + 1; y < tab.length; y++)
				addEdge(x, y,  tab[x][y]);
		}

	}

//	public static void main(String[] args) {
//		// List<Integer> vertices = new ArrayList<Integer>();
//		// vertices.add(0);
//		// vertices.add(1);
//		// vertices.add(2);
//		// Hamilton h = new Hamilton();
//		//
//		// h.addVertex(vertices);
//		// h.addEdge(0, 1, 1);
//		// h.addEdge(0, 2, 5);
////		 h.export(g);
//		// h.addEdge(1, 2, 3);
//		// List<Integer> output = h.execute();
//		//
//		// System.out.println("wut" + output);
//		// System.out.println(g);
//
//		Hamilton h = new Hamilton();
//		importFromFile("input.txt");
//		new ExportImport().exportToFile(tab, "test2.txt");
//		h.hamiltonCycle = h.execute();
//		System.out.println("Cykl hamiltona:" + h.hamiltonCycle);
//	}
}
