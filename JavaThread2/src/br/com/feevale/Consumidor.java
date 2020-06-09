/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feevale;

/**
 *
 * @author pbarros
 */
public class Consumidor extends Thread {
    
    private Estoque produtos;
    
    public Consumidor(Estoque prod, String nome) {
        super(nome);
        this.produtos = prod;
    }
    
    public void consumir() {
        synchronized (produtos){
            /* Verifica se existem itens no estoque */
            if (produtos.getQtdEstoque() > 0) {
                int valor = (int)produtos.getProdutoEstoque();
                System.out.println("|Consumidor|- " + this.getName()+"\t - Produto removido do estoque-" + valor);
            }
            else {
                try {
                    System.out.println("|Consumidor|- " + this.getName()+"\t - Esperando por novos produtos.");
                    produtos.wait();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void run(){
        while (true) {
            this.consumir();
            try{
                Thread.sleep((int)(Math.random() * 1000));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
