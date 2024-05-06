/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

/**
 * This class is an implementation of the tree of letters that you find
 * in the textbook, chapter 3. With help of instances of the class make it
 * possible to debug the breath and depth first traverse. The class implements
 * the methods that are declared in the interface IState.
 * @author Please, enter your name instead of this sentence if you do any changes.
 */
public class Letters extends State {
    private char letter = '?';
    
    /**
     * Initializes the object that is created, with a letter. 
     * @param letter 
     */
    public Letters(char letter) {
        this.letter = letter;
    }

    /**
     * The method return true if two letter object are equal, otherwise false.
     * The method is called by the method contains in any collection. Example of
     * collections is Stack, Queue and LinkedList, see the documentation of
     * package java.util for more information about collection classes.
     * @param anObject The object that this object shall be compered with
     * @return true if this object letter is equal to the anObjects letter,
     * otherwise false.
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject == null) return false;
        if (anObject == this) return true;
        if (!(anObject instanceof Letters))return false;
        
        Letters anotherLetter = (Letters)anObject;
        if (anotherLetter.hashCode() != this.hashCode())return false;
      
        return letter == anotherLetter.letter;
    }

    /**
     * The method return integer that will be the same for all objects that
     * represent same letter.
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.letter;
        return hash;
    }
    
    /**
     * The method print out the current state of the node and its depth.
     */
    @Override
    public void show() {
        System.out.print(" " + letter + depth + heuristicValue);
    }

    /**
     * The tree of letters is described in the textbook, chapter 3.
     * The method create the children and return the references to them in a
     * array with 4 element. Some of the elements can have the value null if
     * it not was possible to create all children. 
     * @return An array with children, some of the elements can be null. 
     */
    @Override
   public IState[] createChildren() {
        Letters[] children = new Letters[4];

        switch (letter) {
            case 'A':
                children[0] = new Letters('B');
                children[1] = new Letters('C');
                children[2] = new Letters('D');
                break;
           case 'B':
                children[0] = new Letters('E');
                children[1] = new Letters('F');
                break;
           case 'C':
                children[0] = new Letters('G');
                children[1] = new Letters('H');
                break;
           case 'D':
                children[0] = new Letters('I');
                children[1] = new Letters('J');
                break;
           case 'E':
                children[0] = new Letters('K');
                children[1] = new Letters('L');
                break;
           case 'F':
                children[0] = new Letters('L');
                children[1] = new Letters('M');
                break;
           case 'G':
                children[0] = new Letters('N');
                break;
           case 'H':
                children[0] = new Letters('O');
                children[1] = new Letters('P');
                break;
           case 'I':
                children[0] = new Letters('P');
                children[1] = new Letters('Q');
                break;
           case 'J':
                children[0] = new Letters('R');
                break;
           case 'K':
                children[0] = new Letters('S');
                break;
           case 'L':
                children[0] = new Letters( 'T');
                break;
           case 'P':
                children[0] = new Letters('U');
                break;
        }
        
        for(Letters child : children)
            if (child != null) {
                child.parent = this;
                child.depth = this.depth + 1;
            }
    
        return children;
    }
   
    /**
     * calculate a heuristic value and assign it to the node
     * @param goal the goal state, not needed for the tree of letters
     */
    @Override
    public void calculateHeuristicValue(IState goal)
    {
        switch (letter) {
            case 'A':
                heuristicValue = 5;
                break;
           case 'B':
                heuristicValue = 4;
                break;
           case 'C':
                heuristicValue = 4;
                break;
           case 'D':
                heuristicValue = 5;
                break;
           case 'E':
                heuristicValue = 5;
                break;
           case 'F':
                heuristicValue = 5;
                break;
           case 'G':
                heuristicValue = 4;
                break;
           case 'H':
                heuristicValue = 3;
                break;
           case 'O':
                heuristicValue = 2;
                break;
           case 'P':
                heuristicValue = 3;
                break;
           default:
                heuristicValue = 5;
        }  
    }
}
