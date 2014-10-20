package com.guru;
import java.io.File;
import java.io.FilenameFilter;

import com.guru.language.*;

public class Guru {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = null;
		File[] paths;
		final String [] p = new String[1];
		f = new File("./examples");
		FilenameFilter fileNameFilter = new FilenameFilter() {
			   
            @Override
            public boolean accept(File dir, String name) {
               if(name.lastIndexOf('.')>0)
               {
                  // get last index for '.' char
                  int lastIndex = name.lastIndexOf('.');
                  
                  // get extension
                  String str = name.substring(lastIndex);
                  
                  // match path name extension
                  if(str.equals(".guru"))
                  {	
                	  p[0] =  dir.getAbsolutePath()+"/"+name;
                	 GuruCParser.main(p);
                     return true;
                  }
               }
               return false;
            }
         };
         // returns pathnames for files and directory
         paths = f.listFiles(fileNameFilter);
		
	}

}
