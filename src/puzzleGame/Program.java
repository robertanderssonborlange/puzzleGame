/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzleGame;

/**
 * This class contains the main method from which the execution is started. 
 * @author code by Hans Edy Mårtensson
 */
public class Program {
    /**
     * The main method start up the execution of the menu.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.run();
    }
}
