/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.root.fx.base;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gavalian
 */
public class Dimension1D {

    double dimMin  = 0.0;
    double dimMax  = 1.0;
    
    boolean isLog = false;    
    
    public Dimension1D(){
        
    }
    
    public Dimension1D(double min, double max){
        this.setMinMax(min, max);
    }
    
    public final void setMinMax(double min, double max){
        this.dimMin = min;
        this.dimMax = max;
    }
    
    public double getMin(){
        return this.dimMin;
    }
    
    public boolean  getLog(){
        return this.isLog;
    }
    
    public void setLog(boolean flag){
        this.isLog = flag;
    }
    
    public double getMax(){
        return this.dimMax;
    }
    
    public double getFraction(double point){
        if(this.isLog==false)
            return (point-this.dimMin)/(this.dimMax-this.dimMin);
        double min = this.dimMin;
        double distance = Math.log10(point) - Math.log10(min);
        //System.out.println("distance = " + distance + " " + point);
        double frac = distance/this.length();
        return frac;
    }
    
    public double getPoint(double fraction){        
        return this.dimMin+(this.dimMax-this.dimMin)*fraction;
    }
    
    public double getLength(){
        return Math.abs(dimMax-dimMin);
    }
    
    public double convert(double x, Dimension1D dim){
        double xf = dim.getFraction(x);
        return this.getPoint(xf);
    }
    
    public double length(){
        if(isLog==false) return (dimMax-dimMin);        
        double min = dimMin;
        return Math.log10(dimMax) - Math.log10(min);
    }
    
    public void grow(double point){
        if(point<dimMin){
            this.setMinMax(point, dimMax);
        } else if(point>dimMax){
            this.setMinMax(dimMin, point);
        }        
    }
        
    /**
     * returns an array of nice numbers to represent the dimension
     * as axis.
     * @param maxTicks
     * @return 
     */
    public List<Double>  getDimensionTicks(int maxTicks){
        List<Double>  axisTicks = new ArrayList<Double>();
        
        double range = niceNum(dimMax - dimMin, false);
        double tickSpacing = niceNum(range / (maxTicks - 1), true);
        double niceMin =
                Math.floor(dimMin / tickSpacing) * tickSpacing;
        double niceMax =
                Math.ceil(dimMax / tickSpacing) * tickSpacing;
        
        double numberX = niceMin;
        while(numberX<=this.dimMax){
            if(numberX>=this.dimMin&&numberX<=this.dimMax){
                axisTicks.add(numberX);
            }
            numberX += tickSpacing;
        }
        return axisTicks;
    }
    
    public boolean  contains(double x){
        return (x>=this.dimMin&&x<=this.dimMax);
    }
    /**
     * Returns a "nice" number approximately equal to range Rounds
     * the number if round = true Takes the ceiling if round = false.
     *
     * @param range the data range
     * @param round whether to round the result
     * @return a "nice" number to be used for the data range
     */
    private double niceNum(double range, boolean round) {
        
        double exponent; /** exponent of range */
        double fraction; /** fractional part of range */
        double niceFraction; /** nice, rounded fraction */
        
        exponent = Math.floor(Math.log10(range));
        fraction = range / Math.pow(10, exponent);
        
        if (round) {
            if (fraction < 1.5)
                niceFraction = 1;
            else if (fraction < 3)
                niceFraction = 2;
            else if (fraction < 7)
                niceFraction = 5;
            else
                niceFraction = 10;
        } else {
            if (fraction <= 1)
                niceFraction = 1;
            else if (fraction <= 2)
                niceFraction = 2;
            else if (fraction <= 5)
                niceFraction = 5;
            else
                niceFraction = 10;
        }
        
        return niceFraction * Math.pow(10, exponent);
    }
    
    public static void main(String[] args){
        
        Dimension1D  x = new Dimension1D(1.0,10.0);
        x.setLog(true);
        for(double value = 1.0; value < 10.0 ; value += 1.0){
            System.out.println(value + " --> " + x.getFraction(value) + "  length = " + x.length());
        }
                
        List<Double>  ticks = x.getDimensionTicks(8);
        for(Double v : ticks){
            System.out.println(v);
        }
    }
}
