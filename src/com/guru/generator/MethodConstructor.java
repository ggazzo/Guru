package com.guru.generator;

import java.util.ArrayList;
import java.util.Iterator;

public class MethodConstructor implements Comparable{
	public String identifier, params, returns;
	private ArrayList<Statement> statements = new ArrayList<Statement>();
	private Iterator<Statement> iter1;
	private int level = 0;
	
	public MethodConstructor(String identifier,String params,String returns) {
		this.identifier = identifier;
		this.params = params;
		this.returns = returns;			
		System.out.println(identifier);
	}
	
	public void addStatement(String a){
		System.out.println("linha de código, metodo "+identifier);
		statements.add(new Statement(a));
	}
	
	public String toHeader(){				
		// Fiz assim para ficar mais facil de notar a estrutura <3
		String template = "%returns% %name% (%params%);";
		
		System.out.println("gerando a assinatura do metodo " + identifier);
		
		return this.replaceToHeader(template);				
	}
	
	public String toImplementation(){
		// Fiz assim para ficar mais facil de notar a estrutura <3
		
		String template = "\n%returns% %class%::%name% (%params%){%statements%}";
		
		System.out.println("gerando o metodo "+identifier);
				
		return this.replace(template).replaceAll("( )*\n}","}");
		
	}
	
	private String statementToString(){
		String retorno = "";
		iter1 = statements.iterator();
		while(iter1.hasNext()){
			retorno+= "\n" + iter1.next();
		}
		return retorno + '\n';
	}
	
	private String replace(String t){
		return t.replace("%returns% " ,returns!=null ? returns+" ":"" )
				.replace("%name%" ,identifier)				
				.replace("%statements%", this.statementToString())
				.replace("%params%" ,params.equals("void")?"":params);		
	}
	
	private String replaceToHeader(String t){
		return t.replace("%returns% " ,returns!=null ? "virtual "+returns+" ":"" )
				.replace("%name%" ,identifier)
				.replace("%params%" ,params);		
	}
    
    @Override
    public int compareTo(Object t) {
        return this.identifier.compareTo(((MethodConstructor)t).identifier);
    }
}
