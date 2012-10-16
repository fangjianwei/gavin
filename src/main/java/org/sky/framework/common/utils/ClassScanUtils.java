package org.sky.framework.common.utils;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang.StringUtils;
import org.sky.framework.common.utils.logging.Logger;
import org.sky.framework.common.utils.logging.LoggerFactory;

public class ClassScanUtils {
	
	private static final Logger log = LoggerFactory.getLogger(ClassScanUtils.class);
	
	public static Set<Class<?>> getClass( String basePackage ){
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		
		if( StringUtils.isBlank(basePackage) ){
			log.warn("there has empty url scan!");
			return classes;
		}
		
		boolean recursive = false;
		if( basePackage.endsWith(".*") ){
			basePackage = basePackage.substring(0,basePackage.length()-2);
			recursive = true;
		}


		
		String packageName = basePackage;
		String packageDir = basePackage.replace('.', '/');
		
		log.debug("=========扫描路径："+packageDir+"==============");

		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDir);
			while( dirs.hasMoreElements() ){
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();				
				if( "file".equals(protocol) ){
					log.debug("文件类型的扫描");
					String packagePath = URLDecoder.decode(url.getPath(), "utf-8");
					findAndAddClassesInPackageByFile(packageName, packagePath, recursive, classes);
				}else if( "jar".equals(protocol) ){
					log.debug("jar类型的扫描");
					
					JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
					findAndAddClassesInPackageJar(packageName, packageDir, jar, recursive, classes);

					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return classes;
	}
	
	@SuppressWarnings("unchecked")
	public static void  findAndAddClassesInPackageByFile(String packageName,  
            String packagePath, final boolean recursive, Set<Class<?>> classes){
		File file = new File(packagePath);
		if( !file.exists()||!file.isDirectory() ){
			log.warn("the path '"+packagePath+"' hasn't exist!");
			return;
		}
		
		File[] files = file.listFiles(new java.io.FileFilter() {
			public boolean accept(File file) {
				if(recursive&&file.isDirectory()||file.getName().endsWith(".class")){
					return true;
				}else{
					return false;
				}
			}
		});
		
		for( File pathFile:files ){
			
			if( pathFile.isDirectory() ){
				String deepPackageName = packageName+"."+pathFile.getName();
				findAndAddClassesInPackageByFile(deepPackageName,pathFile.getAbsolutePath(), recursive, classes);
			}else{
				String className = pathFile.getName().substring(0,pathFile.getName().length()-6);

				log.debug( className );

				try {
					Class clazz = Thread.currentThread().getContextClassLoader().loadClass(packageName+"."+className);
					classes.add(clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void  findAndAddClassesInPackageJar(String packageName,  
            String packagePath,JarFile jar, final boolean recursive, Set<Class<?>> classes){
		Enumeration<JarEntry> jarEntrys =  jar.entries();
		while( jarEntrys.hasMoreElements() ){
			JarEntry jarEntry = jarEntrys.nextElement();
			String name = jarEntry.getName();
			if( name.startsWith("/") ){
				name = name.substring(1);
			}
			
			if(name.startsWith(packagePath)&&name.endsWith(".class")&&!jarEntry.isDirectory()){				

				name = name.replace('/', '.');
				String className = name.substring(packageName.length()+1,name.length()-6);
				
				if(className.indexOf(".")!=-1&&!recursive) continue;

				try {
					log.debug(packageName+"."+className);
					
					Class clazz = Thread.currentThread().getContextClassLoader().loadClass(packageName+"."+className);
					classes.add(clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch ( Throwable t ){
					log.warn(packageName+"."+className+", can't load");
				}
			}
			
		}
	}

}
