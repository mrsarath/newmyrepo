package com.bos.parser;

import java.io.File;
import java.util.*;

import org.apache.commons.io.FileUtils;

import com.bos.parser.domain.PerformanceEntry;

public class PerformanceLogger {
	
	public static void main(String[] str) {
		
		try {
			//File htmlTemplateFile = new File("template.html");
			String htmlString = ""; //FileUtils.readFileToString(htmlTemplateFile);
			
			htmlString = writeSVG();
			File newHtmlFile = new File("path/new.html");
			FileUtils.writeStringToFile(newHtmlFile, htmlString);
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace() + ex.getMessage());
		}
	}
	
	public static String writeSVG(){
		
        List<PerformanceEntry> pListOld = new ArrayList<>();
		
        pListOld.add(new PerformanceEntry("Login", 8));
        pListOld.add(new PerformanceEntry("Transaction_History", 11));
        pListOld.add(new PerformanceEntry("Research_Article", 40));
        
        
		List<PerformanceEntry> pListNew = new ArrayList<>();
		
		pListNew.add(new PerformanceEntry("Login", 5));
		pListNew.add(new PerformanceEntry("Transaction_History", 7));
		pListNew.add(new PerformanceEntry("Research_Article", 24));		
        
		
		String html ="<!DOCTYPE html>		<style>\n" +
		".newperf {	fill: steelblue;		}" +
		"\n.oldperf { fill: red;		}" +	
		"\n.chart text { fill: black;		  font: 10px sans-serif;		  text-anchor: middle;		}" +
		
        "\ntext { font-size:2em; text-anchor:start   font-family:sans-serif; }"+  

		"\n</style>"+
		"<svg class=\"chart\" width=\"1200\" height=\"1200\">";
		
		int counter = 0;
		
		for(PerformanceEntry ent: pListNew) {
			
			//Old
			PerformanceEntry oldEnt = findOldPerfEntry(ent, pListOld);
			Integer old_y=0;
			Integer old_height=0;
			List<Integer> oldValList = calcValues(old_y, old_height, oldEnt.getDuration());
			old_y = oldValList.get(0);
			old_height = oldValList.get(1);
			
			//int old_y = (int) (oldEnt.getDuration() * 10);
			//int old_height = totHeight - old_y;
			
			//New
			//int totHeight  = 780;
			Integer new_y=0;
			Integer new_height=0;
			//int y = (int) (ent.getDuration() * 10);
			//int height = totHeight - y;
			
			
			List<Integer> newValList = calcValues(new_y, new_height, ent.getDuration());
			new_y = newValList.get(0);
			new_height = newValList.get(1);
			
			int tslt_old = 40*counter;
			int tslt_new = 20+ (40*counter);			
			
			
			//old
			html += 
		  "<g writing-mode=\"tb\" text-anchor=\"start\" glyph-orientation-vertical=\"0\" transform=\"translate("+tslt_old+",0)\">"
		  + "<rect class=\"oldperf\" width=\"19\" height=\""+old_height+"\" y=\""+old_y+"\"></rect>";
		  
			//transform=\"rotate(45 -10 10)\"
		  html += "\n<text y=\"650px\" dx=\"0.71em\" text-anchor=\"start\" glyph-orientation-vertical=\"0\" >"+ent.getTransactionName()+"</text>"
		  		+ "\n</g>";
		
			//counter++;
		
			//new
			html += 
				  "<g transform=\"translate("+tslt_new+",0)\">"
				  + "<rect class=\"newperf\" width=\"19\" height=\""+new_height+"\" y=\""+new_y+"\"></rect>"
				  		;
		
			//html += "<text x=\""+tslt_old+"\" y=\"610\">"+ent.getTransactionName()+"</text>";
			
			html +=  "</g>";
			
		    counter++;
		}
		  
		html += "</svg>"
		  		+ "</html>";
		  
		  return html;

	}
	
	public static PerformanceEntry findOldPerfEntry(PerformanceEntry newPerf, List<PerformanceEntry> pListOld) {
		
		for (PerformanceEntry oldEnt: pListOld) {
			if (oldEnt.getTransactionName().equals(newPerf.getTransactionName()))
				return oldEnt;
		}
		
		return null;
	}
	
	public static List<Integer> calcValues(Integer vy, Integer vheight, double duration) {
		
		int totHeight  = 600;
		vy = 600 - (int) (duration * 8);
		vheight = totHeight - vy;
		
		return Arrays.asList(new Integer[]{vy, vheight});
	}

}
