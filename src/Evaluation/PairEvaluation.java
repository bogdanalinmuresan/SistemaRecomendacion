/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

/**
 *
 * @author bogdan
 */
public class PairEvaluation {
    double first;
    double second;
    
    public PairEvaluation(int i,int j){
        first=i;
        second=j;
    }
    
    public double getFirst(){
        return first;
    }
    public void setFirst(double f){
        this.first=f;
    }
    
    public double getSecond(){
        return second;
    }
    
    public void setSecond(double s){
        this.second=s;
    }
}
