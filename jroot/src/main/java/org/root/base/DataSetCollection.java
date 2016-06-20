/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.root.base;

import java.util.ArrayList;

/**
 *
 * @author gavalian
 */
public class DataSetCollection {
    
    private ArrayList<IDataSet>  dsCollection = new ArrayList<IDataSet>();
    private ArrayList<String>    dsCollectionOptions = new ArrayList<String>();
    
    private Boolean              collectionDataRangeScale = true;
    private DataRegion           fixedDataRegion = new DataRegion();
    
    public DataSetCollection(){
        
    }
    
    public void clear(){
        this.dsCollection.clear();
        this.dsCollectionOptions.clear();
    }
    
    public DataRegion getDataRegion(){
        
        if(this.dsCollection.isEmpty()){
            return new DataRegion(0.0,1.0,0.0,1.0);
        }
        
        DataRegion region = new DataRegion(this.dsCollection.get(0).getDataRegion());
        //if(this.collectionDataRangeScale==true){
        for(int loop = 0; loop < this.dsCollection.size(); loop++){
            region.combine(this.dsCollection.get(loop).getDataRegion());
        }
        
        if(this.collectionDataRangeScale==false){
            DataRegion fixedRegion = new DataRegion(this.fixedDataRegion);
            if(fixedRegion.MINIMUM_X==fixedRegion.MAXIMUM_X){
                fixedRegion.MINIMUM_X = region.MINIMUM_X;
                fixedRegion.MAXIMUM_X = region.MAXIMUM_X;
            }
            if(fixedRegion.MINIMUM_Y==fixedRegion.MAXIMUM_Y){
                fixedRegion.MINIMUM_Y = region.MINIMUM_Y;
                fixedRegion.MAXIMUM_Y = region.MAXIMUM_Y;
            }
            return fixedRegion;
        }
        //} else {
        //    return new DataRegion(this.fixedDataRegion);
        //}
        return region;
    }
    
    
    public void setDataRegion(double xmin, double xmax, double ymin, double ymax){
        this.fixedDataRegion.MINIMUM_X = xmin;
        this.fixedDataRegion.MAXIMUM_X = xmax;
        this.fixedDataRegion.MINIMUM_Y = ymin;
        this.fixedDataRegion.MAXIMUM_Y = ymax;
        this.collectionDataRangeScale = false;
    }
    
    public void setAutoScale(boolean flag){
        this.collectionDataRangeScale = flag;
    }
    
    public void addDataSet(IDataSet ds){
    	this.addDataSet(ds, "");
    }
    
    public void addDataSet(IDataSet ds, String option){
        this.dsCollection.add(ds);
        this.dsCollectionOptions.add(option);
    }

    public void  removeDataSet(int index){
    		this.dsCollection.remove(index);
    		this.dsCollectionOptions.remove(index);
    }

    public void  removeDataSet(IDataSet ds){
    	removeDataSet(this.dsCollection.indexOf(ds));
    }
    
    
    public int  getCount(){ return this.dsCollection.size();}
    public IDataSet  getDataSet(int index){ return this.dsCollection.get(index);}
    public String    getDataSetOption(int index){ return this.dsCollectionOptions.get(index);}
    public void      setDataSetOption(int index,String option){ this.dsCollectionOptions.set(index, option);}


    public void setAxisLog(boolean flag, String axis){
        //if(axis.compareTo("X")==0) 
    }
}
