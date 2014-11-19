package com.guru.generator;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public final class ClassBuilder {

    private final String nameClass;
    private MethodConstructor lastMethod;
    private final MethodConstructor destructMethod;
    private final Map<String, ArrayList<MethodConstructor>> methods = new HashMap<>();
    private final Map<String, ArrayList<Attribute>> attributes = new HashMap<>();
    private Iterator<MethodConstructor> iter1;
    private Iterator<Attribute> iter2;    
    private int level = 0;

    public ClassBuilder(String name) {
        nameClass = name;
        methods.put("public", new ArrayList<MethodConstructor>());
        methods.put("private", new ArrayList<MethodConstructor>());
        methods.put("protected", new ArrayList<MethodConstructor>());
        attributes.put("public", new ArrayList<Attribute>());
        attributes.put("private", new ArrayList<Attribute>());
        attributes.put("protected", new ArrayList<Attribute>());
        destructMethod = addMethod("~%class%".replace("%class%", name), "void", null, "public");
    }

    public MethodConstructor addConstructor(String v) {
        return addMethod(this.nameClass, "void", null, v);
    }

    public MethodConstructor addConstructor(String params, String v) {
        return addMethod(this.nameClass, params, null, v);
    }

    public MethodConstructor addMethod(String identifier) {
        return addMethod(identifier, "void", "void", "public");
    }

    public MethodConstructor addMethod(String identifier, String visibility) {
        return addMethod(identifier, "void", "void", visibility);
    }

    public MethodConstructor addMethodWithReturn(String identifier, String returns) {
        return addMethod(identifier, "void", returns, "public");
    }

    public MethodConstructor addMethodWithReturn(String identifier, String returns, String v) {
        return addMethod(identifier, "void", returns, v);
    }

    public MethodConstructor addMethodWithParams(String identifier, String params) {
        return addMethod(identifier, params, "void", "public");
    }

    public MethodConstructor addMethodWithParams(String identifier, String params, String v) {
        return addMethod(identifier, params, "void", v);
    }

    public MethodConstructor addMethod(String identifier, String params, String returns) {
        return addMethod(identifier, params, returns, "public");
    }

    public MethodConstructor addMethod(String identifier, String params, String returns, String visibility) {
        lastMethod = new MethodConstructor(identifier, params, returns);
        methods.get(visibility.equals("") ? "public" : visibility).add(lastMethod);
        return lastMethod;
    }

    public Attribute addAttribute(String type, String visibility) {
        Attribute a = this.findAttributeByType(this.attributes.get(visibility.equals("") ? "public" : visibility), type);
        
        
        return a;
    }
    
    private Attribute findAttributeByType(ArrayList<Attribute> attr, String type){
        for (Attribute next : attr) {
            if(next.getType().equals(type)){
                return next;
            }
            
        }
        Attribute a = new Attribute(type);
        attr.add(a);
        return a;
    
    }

    public void build() throws IOException {
        makeHeader();
        makeImplementation();
    }

    public void addStatement(String a) {
        lastMethod.addStatement(a);
    }

    private void makeImplementation() throws IOException {
        File f = new File("./src-gen/" + this.nameClass + ".c");
        try (FileWriter fw = new FileWriter(f)) {
            f.createNewFile();
            System.out.println("criado o arquivo:" + f.getName());

            write(fw, "/* Código gerado automaticamente pelo GuruParser*/");
            write(fw, "#include %class%.h");

            writeMethods("public", fw);
            writeMethods("private", fw);
            writeMethods("protected", fw);
        }
    }

    private void writeMethods(String v, FileWriter fw) throws IOException {
        iter1 = methods.get(v).iterator();
        while (iter1.hasNext()) {
            write(fw, iter1.next().toImplementation());
        }

    }

    private void writeSignature(String v, FileWriter fw) throws IOException {

        if (methods.get(v).isEmpty() && attributes.get(v).isEmpty()) {
            return;
        }

        iter1 = methods.get(v).iterator();
        iter2 = attributes.get(v).iterator();
        
        write(fw, "    " + v + ":");
        
        while (iter2.hasNext()) {
            write(fw, "        " + iter2.next().toString());
        }

        write(fw, "");
        
        
        
        while (iter1.hasNext()) {
            write(fw, "        virtual " + iter1.next().toHeader());
        }
        
        
    }

    private void makeHeader() throws IOException {

        File f = new File("./src-gen/" + this.nameClass + ".h");

        f.createNewFile();
        System.out.println("criado o arquivo:" + f.getName());

        FileWriter fw = new FileWriter(f);

        /* \/* Código gerado automaticamente pelo GuruParser*\/
         * #ifndef %CLASS%
         * #define %CLASS%
         * class %class {
         *  public:
         *      .
         *      .         
         *  private:        
         *      .        
         *      .          
         *  protected:
         *      .        
         *      .          
         * }
         * #endif
         */
        write(fw, "/* Código gerado automaticamente pelo GuruParser*/");

        write(fw, "#ifndef %CLASS%_H");
        write(fw, "#define %CLASS%_H");
        write(fw, "class %class% {");
        writeSignature("public", fw);
        writeSignature("private", fw);
        writeSignature("protected", fw);
        write(fw, "}");
        write(fw, "#endif");

        fw.close();

    }

    private void write(FileWriter fw, String str) throws IOException {
        String tab = "";
        str = str.replace("%class%", this.nameClass);
        str = str.replace("%CLASS%", this.nameClass.toUpperCase());

        String[] lines = str.split("\n");
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("}")) {
                level--;
                tab = "";
                for (int j = 0; j < level; j++) {
                    tab += "    ";
                }
            }
            lines[i] = tab + lines[i];
            if (lines[i].contains("{")) {
                level++;
                tab += "    ";
            }

        }
        str = "";
        for (int i = 0; i < lines.length; i++) {
            str += "\n" + lines[i];
        }
        fw.write(str);
    }

    public String toString() {
        return "";
    }
}
