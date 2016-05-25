/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jlab.clasrec.loader;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jlab.clasrec.main.DetectorMonitoring;
import org.jlab.clasrec.main.DetectorReconstruction;

/**
 *
 * @author gavalian
 */
public class JarPluginLoader {
    
    private ArrayList<String>  classNames = new ArrayList<String>();
    private TreeMap<String,DetectorReconstruction>  jarClasses
            = new TreeMap<String,DetectorReconstruction>();
    
    private TreeMap<String,DetectorMonitoring>    jarMonitoringClasses = new TreeMap<String,DetectorMonitoring>();
            
    public JarPluginLoader(){
        
    }
    
    public void scan(String jarfile, String classname){
         //System.err.println("[JarPluginLoader] -----> scanninng......");
        //System.err.println("[JarPluginLoader] -----> check for class DetectorReconstruction");
        try {
            Class.forName(classname);
            // it exists on the classpath
        } catch(ClassNotFoundException e) {
            // it does not exist on the classpath
            System.err.println("[JarPluginLoader] ERROR : Could not resolve the CLASS");
            return;
        }
        try {            
            JarFile jarFile = new JarFile(jarfile);
            Enumeration e = jarFile.entries();
            
            //URL[] urls = { new URL("jar:file:" + jarfile+"!/") };
            //URLClassLoader cl = URLClassLoader.newInstance(urls);
            URL[] urls = {new URL("jar:file:" + jarfile + "!/") };
            URLClassLoader cl = URLClassLoader.newInstance(urls);
             
            while (e.hasMoreElements()) {
                JarEntry je = (JarEntry) e.nextElement();
                if(je.isDirectory() || !je.getName().endsWith(".class")){
                    continue;
                }
                String className = je.getName().substring(0,je.getName().length()-6);
                className = className.replace('/', '.');
                //System.err.println("CLASS = " + className);
                //Class c = cl.loadClass(className);
                Class c = Class.forName(className);
                if(c.getSuperclass()==DetectorReconstruction.class){
                    //System.err.println("\t ====> CLASS = " + className);
                    Class  rec = (Class) c.newInstance();
                    //jarGenericClasses.put(rec.getName(), rec);
                }
            }
        } catch (IOException ex) {
            System.err.println("[JarFileLoader] ----> (warning) file is not a proper jar : " + jarfile);
            //Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clear() { jarClasses.clear();}
    
    public void scan(String jarfile) {       
        //System.err.println("[JarPluginLoader] -----> scanninng......");
        //System.err.println("[JarPluginLoader] -----> check for class DetectorReconstruction");
        try {
            Class.forName("org.jlab.clasrec.main.DetectorReconstruction");
            // it exists on the classpath
        } catch(ClassNotFoundException e) {
            // it does not exist on the classpath
            System.err.println("[JarPluginLoader] ERROR : Could not resolve the CLASS");
            return;
        }
        try {            
            JarFile jarFile = new JarFile(jarfile);
            Enumeration e = jarFile.entries();
            
            //URL[] urls = { new URL("jar:file:" + jarfile+"!/") };
            //URLClassLoader cl = URLClassLoader.newInstance(urls);
            URL[] urls = {new URL("jar:file:" + jarfile + "!/") };
            URLClassLoader cl = URLClassLoader.newInstance(urls);
             
            while (e.hasMoreElements()) {
                JarEntry je = (JarEntry) e.nextElement();
                if(je.isDirectory() || !je.getName().endsWith(".class")){
                    continue;
                }
                String className = je.getName().substring(0,je.getName().length()-6);
                className = className.replace('/', '.');
                //System.err.println("CLASS = " + className);
                //Class c = cl.loadClass(className);
                Class c = Class.forName(className);
                if(c.getSuperclass()==DetectorReconstruction.class){
                    //System.err.println("\t ====> CLASS = " + className);
                    DetectorReconstruction  rec = (DetectorReconstruction) c.newInstance();
                    jarClasses.put(rec.getName(), rec);
                }
            }
        } catch (IOException ex) {
            System.err.println("[JarFileLoader] ----> (warning) file is not a proper jar : " + jarfile);
            //Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void scanMonitoring(String jarfile) {       
        //System.err.println("[JarPluginLoader] -----> scanninng......");
        //System.err.println("[JarPluginLoader] -----> check for class DetectorReconstruction");
        try {
            Class.forName("org.jlab.clasrec.main.DetectorMonitoring");
            // it exists on the classpath
        } catch(ClassNotFoundException e) {
            // it does not exist on the classpath
            System.err.println("[JarPluginLoader] ERROR : Could not resolve the CLASS");
            return;
        }
        try {            
            JarFile jarFile = new JarFile(jarfile);
            Enumeration e = jarFile.entries();
            
            //URL[] urls = { new URL("jar:file:" + jarfile+"!/") };
            //URLClassLoader cl = URLClassLoader.newInstance(urls);
            URL[] urls = {new URL("jar:file:" + jarfile + "!/") };
            URLClassLoader cl = URLClassLoader.newInstance(urls);
             
            while (e.hasMoreElements()) {
                JarEntry je = (JarEntry) e.nextElement();
                if(je.isDirectory() || !je.getName().endsWith(".class")){
                    continue;
                }
                String className = je.getName().substring(0,je.getName().length()-6);
                className = className.replace('/', '.');
                //System.err.println("CLASS = " + className);
                //Class c = cl.loadClass(className);
                Class c = Class.forName(className);
                if(c.getSuperclass()==DetectorMonitoring.class){
                    //System.err.println("\t ====> CLASS = " + className);
                    DetectorMonitoring  rec = (DetectorMonitoring) c.newInstance();
                    jarMonitoringClasses.put(rec.getName(), rec);
                }
            }
        } catch (IOException ex) {
            System.err.println("[JarFileLoader] ----> (warning) file is not a proper jar : " + jarfile);
            //Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JarPluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  TreeMap<String,DetectorReconstruction> getClassMap(){ return jarClasses;}
    public  TreeMap<String,DetectorMonitoring>     getMonitoringClassMap(){ return jarMonitoringClasses;}
    
    public static void main(String[] args){
        String jarFilePath = args[0];
        JarPluginLoader loader = new JarPluginLoader();
        loader.scan(jarFilePath);
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        
        return str.toString();
    }

}
