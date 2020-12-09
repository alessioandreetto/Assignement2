////////////////////////////////////////////////////////////////////
// Alessio Andreetto 1193550
////////////////////////////////////////////////////////////////////
 package it.unipd.tos.model;

 public class MenuItem {

     public enum type {
         Gelato,
         Budino,
         Bevanda
     }

     private type tipo;
     private String name;
     private double price;

     public MenuItem(type t, String n, double p) {
         this.tipo = t;
         this.name = n;
         this.price = p;
     }

     public type getItemType() {
         return tipo;
     }

     public String getName() {
         return name;
     }

     public double getPrice() {
         return price;
     }
 } 