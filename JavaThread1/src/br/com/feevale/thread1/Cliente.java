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
public class Cliente extends Thread {
    private Contador contador;
    

    public Cliente(Contador cont, String nomeThread  ) {
        super(nomeThread);
        contador = cont;
    }

    public void run() {
        for (int i = 0; i < 200; i++) {
            contador.setContador();
            try {
                 System.out.println("Nome:"+this.getName()+" adicionou no contador");
               sleep((int)(Math.random() * 50));
                //sleep((int)(Math.random() * 50));
                //sleep((int)( 100));
            } catch (InterruptedException e) { }
        }
        //System.out.println("Nome:"+this.getName()+" /n Quantidade final contador:"+contador.getContador());
    }
}
