/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jlab.clas12.physics;

import org.jlab.clas.detector.DetectorDescriptor;
import org.jlab.clas.physics.Vector3;
import org.jlab.geom.prim.Line3D;
import org.jlab.geom.prim.Path3D;
import org.jlab.geom.prim.Point3D;

/**
 *
 * @author gavalian
 */
public class DetectorResponse {
    
    private DetectorDescriptor  descriptor  = new DetectorDescriptor();
    private Vector3             hitPosition = new Vector3();
    //private Point3D             hitPosition = new Vector3();
    private Vector3             hitPositionMatched = new Vector3();
    private Double             detectorTime = 0.0;
    private Double           detectorEnergy = 0.0;
    private Double           particlePath   = 0.0;
    private int              association    = -1;
    
    public DetectorResponse(){
        
    }
    
    public void   setTime(double time){ this.detectorTime = time;}
    public void   setPosition(double x, double y, double z){this.hitPosition.setXYZ(x, y, z);}
    public void   setPath(double path){ this.particlePath = path;}
    public void   setEnergy(double energy) { this.detectorEnergy = energy; }
    
    public double getTime(){ return this.detectorTime;}
    public double getEnergy(){ return this.detectorEnergy; }
    public double getPath(){ return this.particlePath;}
    public Vector3 getPosition(){ return this.hitPosition;}
    public Vector3 getMatchedPosition(){ return this.hitPositionMatched;}
    
    public DetectorDescriptor getDescriptor(){ return this.descriptor;}
    
    public int getAssociation(){ return this.association;}
    public void setAssociation(int asc){ this.association = asc;}
    
    public Line3D  getDistance(DetectorParticle particle){
        Path3D  trajectory = particle.getTrajectory();
        return trajectory.distance(hitPosition.x(),hitPosition.y(),hitPosition.z());
    }
    
    public int  getParticleMatch(DetectorEvent event){
        
        double  distance = 1000.0;
        int     index    = -1;
        
        int nparticles = event.getParticles().size();
        for(int p = 0; p < nparticles; p++){
            DetectorParticle  particle = event.getParticles().get(p);
            Line3D distanceLine = this.getDistance(particle);
            if(distanceLine.length()<distance){
                distance = distanceLine.length();
                index    = p;
            }
        }
        return index;
    }
    
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(String.format("\t [%8s] [%3d %3d %3d] ", 
                this.descriptor.getType().toString(),
                this.descriptor.getSector(),
                this.descriptor.getLayer(),
                this.descriptor.getComponent()
                ));
        str.append(String.format(" T/P/E %8.4f %8.4f %8.4f", this.detectorTime,
                this.particlePath,
                this.detectorEnergy));
        str.append(String.format(" POS [ %8.4f %8.4f %8.4f ]", 
                this.hitPosition.x(),this.hitPosition.y(),this.hitPosition.z()));
        str.append(String.format(" ACCURACY [ %8.4f %8.4f %8.4f ] ",
                this.hitPosition.x()-this.hitPositionMatched.x(),
                this.hitPosition.y()-this.hitPositionMatched.y(),
                this.hitPosition.z()-this.hitPositionMatched.z()
                ));
        return str.toString();
    }
}
