package generator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Classes {
	private ArrayList<ClassBuilder> classes = new ArrayList<ClassBuilder>();
	private Iterator<ClassBuilder> iter1;
	private ClassBuilder lastClass;
	
	public ClassBuilder addClass(String name){
		System.out.println(name);
		this.lastClass = new ClassBuilder(name);
		classes.add(this.lastClass);
		return this.lastClass; 
	}
	public void build(){
		iter1 = classes.iterator();
		while(iter1.hasNext()){
			try {
				iter1.next().build();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void addMethod(String name){
		lastClass.addMethod(name);
	}
}
