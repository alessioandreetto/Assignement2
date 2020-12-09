////////////////////////////////////////////////////////////////////
// Alessio Andreetto 1193550
////////////////////////////////////////////////////////////////////
 package it.unipd.tos.model;

 public class User {
     private String name;
     private String surname;
     private int age;

     public User(String n, String s, int e){
         this.name = n;
         this.surname = s;
         this.age = e;
     }

     public String getName(){
         return name;
     }

     public String getSurname(){
         return surname;
     }

     public int getAge(){
         return age;
    }
 }