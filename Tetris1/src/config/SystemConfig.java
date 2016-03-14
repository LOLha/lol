package config;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class SystemConfig {
	private int xMin;
	
	private int xMax;
	
	private int yMin;
	
	private int yMax;
	
	private List<Point[]> typeConfig;

	public SystemConfig(Element system){
		this.xMin = Integer.parseInt(system.attributeValue("xMin"));
		this.xMax = Integer.parseInt(system.attributeValue("xMax"));
		this.yMin = Integer.parseInt(system.attributeValue("yMin"));
		this.yMax = Integer.parseInt(system.attributeValue("yMax"));
		List<Element> elements = system.elements("rect");
		typeConfig = new ArrayList<Point[]>(elements.size());
		for(Element rect:elements){
			List<Element> pointConfig = rect.elements("point");
			Point[] points = new Point[pointConfig.size()];
			for(int i=0;i<points.length;i++){
				points[i] = new Point(
						Integer.parseInt(pointConfig.get(i).attributeValue("x")),
					Integer.parseInt(pointConfig.get(i).attributeValue("y"))			
						);
			}
			
			typeConfig.add(points);
		}
		
	}


	public int getxMin() {
		return xMin;
	}


	public int getyMin() {
		return yMin;
	}


	public int getxMax() {
		return xMax;
	}


	public int getyMax() {
		return yMax;
	}


	public List<Point[]> getTypeConfig() {
		return typeConfig;
	}
	
}
		
	
	
	
