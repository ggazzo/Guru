package com.guru.generator;
public class MethodConstructor {
	private String identifier, params, returns;
	public MethodConstructor(String identifier,String params,String returns) {
		this.identifier = identifier;
		this.params = params;
		this.returns = returns;				
	}
	public String toHeader(){		
		return "\t" + (returns!=null?returns+" ":"")+identifier+"("+params+");";
	}
	public String toImplementation(){	
		System.out.println("gerando o metodo "+identifier);
		
		return (returns!=null?returns+" ":"")+"%class%::" +identifier+"("+(params.equals("void")?"":params)+"){};";
	}
}
