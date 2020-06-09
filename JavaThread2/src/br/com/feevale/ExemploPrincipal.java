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
public class ExemploPrincipal {
    int qtdMax=10;
     public static void main(String[] args) {
        Estoque listaProdutos = new Estoque();
        Produtor p1 = new Produtor(listaProdutos, "Jose");
        Consumidor c1 = new Consumidor(listaProdutos,"Claudio");
        Consumidor c2 = new Consumidor(listaProdutos,"Marcia");
        p1.start();
        c1.start();
        c2.start();
    }
    
}
