package generator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ClassBuilder {
	private String nameClass;
	private ArrayList<MethodConstructor> methods;
	private Iterator<MethodConstructor> iter1;
	
	public static void main(String args []){
		ClassBuilder c = new ClassBuilder("Teste");		
		c.addConstructor();
		c.addMethod("teste");
		c.addMethod("teste","bunda anta","int");
		try {
			c.build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ClassBuilder(String name){
		nameClass = name;
		methods = new ArrayList<MethodConstructor>();
		
		
	}
	

	public void addConstructor(){
		addMethod(this.nameClass, "void", null);
	}
	
	public void addMethod(String identifier){
		addMethod(identifier, "void", "void");
	}
	
	public void addMethod(String identifier,String params,String returns){
		methods.add(new MethodConstructor(identifier, params, returns));
	}
	
	public void build() throws IOException{
		makeHeader();
		makeImplementation();
	}
	
	
	private void makeImplementation() throws IOException{
		File f = new File("./src-gen/"+this.nameClass+".c");
		FileWriter fw = new FileWriter(f);
		f.createNewFile();
		
		write(fw, "#include %class%.h");
		
		
		iter1 = methods.iterator();
		while(iter1.hasNext()){
			write(fw,iter1.next().toImplementation());
		}
		
		fw.close();
	}
	
	private void makeHeader() throws IOException{
		
		File f = new File("./src-gen/"+this.nameClass+".h");
		
		f.createNewFile();
		
		
		FileWriter fw = new FileWriter(f);
	
		
		/*	
		 * #ifndef Sensor_h
		 * #define Sensor_h
		 * #endif
		 */
		
		
		
		write(fw,"#ifndef %CLASS%_H");
		write(fw,"#define %CLASS%_H");
		
		
		
		
		
		/*
		 * class Teste {
		 * 
		 * 
		 * }*/
		write(fw, "class %class% {"); 
				
		iter1 = methods.iterator();
		while(iter1.hasNext()){
			write(fw,iter1.next().toHeader());
		}
		
		write(fw,"}");
		
		
		write(fw,"#endif");
		
		fw.close();

		
	}
	
	private void write(FileWriter fw , String str) throws IOException{
		str = str.replace("%class%", this.nameClass);
		str = str.replace("%CLASS%", this.nameClass.toUpperCase());		
		fw.write(str+"\n");				
	}
	
	public String toString(){			
		return "";
	}
}
