/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kth.element.java;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Josh
 */
public class KthElementJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList a = new ArrayList<Integer>();
        fill(a,1000000);
        
        System.out.println( selectV2(3, a));
        Collections.sort(a);
        print(a);
//        ArrayList test = new ArrayList<Integer>();
//        test.add(3);
//        test.add(2);
//        test.add(9);
//        test.add(4);
//        test.add(5);
//        System.out.println( findMedian(test));
    }
    
    public static void print(ArrayList<Integer> a){
        for(int i=0; i<a.size(); i++){
            System.out.print(a.get(i) +" ");
        }
        System.out.print("\n");
    }
    
    public static void fill(ArrayList<Integer> a, int size){
        Random rand = new Random();
        for(int i=0; i<size; i++){
            int start = a.size();
            while(a.size()==start){
                int insert = rand.nextInt(Integer.MAX_VALUE);
                if(!a.contains(insert)) a.add(insert);
            }
            
        }
    }
    
    public static int select(int k, ArrayList<Integer> s){
        Random rand = new Random();
        if(s.size()==1) return s.get(0);
        else{
            int m = s.get(rand.nextInt(s.size()));
            ArrayList s1 = new ArrayList<Integer>();
            ArrayList s2 = new ArrayList<Integer>();
            ArrayList s3 = new ArrayList<Integer>();
            for(int i=0; i<s.size(); i++){
                if(s.get(i)<m) s1.add(s.get(i));
                else if (s.get(i)>m) s3.add(s.get(i));
                else s2.add(s.get(i));
            }
            if(s1.size()>=k){
                return select(k,s1);
            }else if(s1.size()+s2.size()>=k){
                return m;
            }else{
                return select(k-s1.size()-s2.size(), s3);
            }
        }
        
    }
    
    public static int selectV2(int k, ArrayList<Integer> s){
        if(s.size()<50){
            Collections.sort(s);
            return s.get(k-1);
        }else{
            ArrayList m = new ArrayList<Integer>();
            ArrayList temp = new ArrayList<Integer>();
            for(int i=0; i<s.size(); i++){
                temp.add(s.get(i));
                if(temp.size()==5 || (i==(s.size()-1))){
                    m.add(findMedian(temp));
                    temp.clear();
                }
            }
            int med = selectV2((int)(Math.ceil((double)m.size()/2.0f)),m);
            ArrayList s1 = new ArrayList<Integer>();
            ArrayList s2 = new ArrayList<Integer>();
            ArrayList s3 = new ArrayList<Integer>();
            for(int i=0; i<s.size(); i++){
                if(s.get(i)<med) s1.add(s.get(i));
                else if (s.get(i)>med) s3.add(s.get(i));
                else s2.add(s.get(i));
            }
            if(s1.size()>=k) return selectV2(k,s1);
            else if ((s1.size()+s2.size())>=k) return med;
            else return selectV2(k-s1.size()-s2.size(),s3);
            
        }
        
    }

    private static int findMedian(ArrayList<Integer> a) {
        if(a.size()==5){
            if(a.get(0)<a.get(1))
            swap(a,0,1);  //a[0]>a[1]
        if(a.get(2)<a.get(3))
            swap(a,2,3);  //a[2]>a[3]

        if(a.get(0) < a.get(2)) 
        {
          swap(a,0,2);
          swap(a,1,3);
        }   
               // we don't require a[0] any more. 

        if(a.get(1)<a.get(4))
            swap(a,1,4);

        if ( a.get(1) > a.get(2) )
        {
            if ( a.get(2) > a.get(4) ) return a.get(2);
            else return a.get(4);
        }
        else
        {
          if ( a.get(1) > a.get(3) ) return a.get(1);
          else return a.get(3);
        } 
     }else if(a.size()==4){
         return 0;
     }else if(a.size()==3){
         if(a.get(0)>a.get(1)){
             swap(a,0,1);
         if(a.get(1)>a.get(2)){
             swap(a,1,2);
         }
         if(a.get(1)>a.get(0)) return a.get(1);
         else return a.get(0);
     }
        
    }else if(a.size()==2){
        return 0;
    }
        return 0;
    }

    private static void swap(ArrayList<Integer> a, int i, int j) {
        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }
    
}
