/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feevale.thread1;

/**
 *
 * @author pbarros
 */
public class ExemploPrincipal {
    
    
    public static void main(String[] args) {
        Contador cont = new Contador();
        Cliente c1 = new Cliente(cont,"t1");
        Cliente c2 = new Cliente(cont,"t2");
        Cliente c3 = new Cliente(cont,"t3");
        c1.start();
        c2.start();
        c3.start();
        
        try{
            c1.join();
            c2.join();
            c3.join();
        }catch(Exception ex) 
        { 
            System.out.println("Exception has " + 
                                "been caught" + ex); 
        } 
        System.out.println("Thread's finalizadas, quantidade final contador:"+cont.getContador());
    }
}
