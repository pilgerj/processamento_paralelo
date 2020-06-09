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
public class Produtor extends Thread {
    private Estoque produtos;
    public Produtor() { }
    public Produtor(Estoque prod, String nome) {
        super(nome);
        this.produtos = prod;
    }
    public void produzir() {
        synchronized (produtos) {
            if(produtos.getEstoqueLotado()){
                System.out.println("|Produtor|- " + this.getName() + "\t - Estoque esta lotado");
            }else{
                int valor = (int)(Math.random() * 10000);
                this.produtos.setAddEstoque(valor);
                System.out.println("|Produtor|- " + this.getName() + "\t - Novo produto adicionado" + valor);
                produtos.notifyAll();
            }
        }
    }
    public void run() {
        while (true) {
            this.produzir();
            try{
                Thread.sleep((int)(Math.random() * 500));
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}