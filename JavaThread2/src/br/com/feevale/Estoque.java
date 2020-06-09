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
public class Estoque {
    
    int qtdProdutos=10;
    
    private int[] produtos = new int[qtdProdutos];
    
    public Estoque() {
        
        for (int i = 0; i < qtdProdutos; i++) {
            produtos[i]=0;
        }
    }
    public int getProdutoEstoque() {
        int produto=0;
        for (int i = 0; i < qtdProdutos; i++) {
            if(produtos[i]>0){
                produto=produtos[i];
                produtos[i]=0;
                break;
            }
        }
        return produto;
    }
    public int setAddEstoque(int arg) {
        int produto=arg;
        for (int i = 0; i < qtdProdutos; i++) {
            if(produtos[i]==0){
                produtos[i]=produto;
                break;
            }
        }
        return produto;
    }
    public boolean getEstoqueLotado() {
        boolean lotado=true;
        for (int i = 0; i < qtdProdutos; i++) {
            if(produtos[i]==0){
                lotado=false;
                break;
            }
        }
        return lotado;
    }
    public int getQtdEstoque() {
        int qtd=0;
        for (int i = 0; i < qtdProdutos; i++) {
            if(produtos[i]>0){
                qtd++;
            }
        }
        return qtd;
    }
}
