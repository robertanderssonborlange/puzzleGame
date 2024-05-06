/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

/**
 * This class is the base class to the subclasses Letters and Puzzle.
 * The purpose of the class is to have  in one place, all the definition of
 * members that is equal to the subclasses. All not private members of this base
 * class are visible in all the subclasses. The subclasses can use everything
 * that are visible and not private in this base class. 
 * @author Please, enter your name instead of this sentence if you do any changes.
 */
public abstract class State implements IState, Comparable<IState> {
    protected IState parent = null;
    protected int depth = 0;
    protected int heuristicValue;
    
    /**
     * Any children will not be created if the parents depth is equal to the
     * depthBound. This method call a overloaded method createChildren() without
     * any parameters. That method is specific for the type of state. For that
     * reason the overloaded method has to be placed in each particular subclass.
     * @param depthBound Maximal depth for any node.
     * @return the created children
     */
    @Override
     public IState[] createChildren(int depthBound) {
        if (this.depth < depthBound)
            return createChildren();
    
        return new IState[4];
    }

    /**
    * The method shall return the parent of this state.
     * @return The parent of this state.
     */
    @Override
    public IState getParent() {
        return parent;
    }
    
    /**
     * The method set a parent to the object.
     * @param aNewParent The new parent that shall be assigned to this object
     */
    @Override
    public void setParent(IState aNewParent) {
        parent = aNewParent;
    }
    
    /**
     * The method return the heuristic value.
     * @return The heuristic value.
     */
    @Override
    public int getHeuristic() {
        return heuristicValue;
    }
    
    /**
     * This method defines the natural sorting order of puzzles.
     * The method is an implementation of the method comparTo in the interface
     * Comparable. The method is called by any Containers sorting methods.
     * The method has to return:
     *  * an integer less then 0, if this heuristic value is less then
     *    theOtherNode heuristic value,
     *  * 0, if this heuristic value is equal to the theOtherNode heuristic value,
     *  * an integer greater then 0, if this heuristic value is greater then
     *    theOtherNode heuristic value.
     * @param theOtherNode An reference to the object that has a value that this
     * value shall be compared with. 
     * @return A value, less then 0, 0 or greater then 0, depending on if
     * this value is less, equal or greater then theOtherNode value.
     */
    @Override
    public int compareTo(IState theOtherNode) {
        return  theOtherNode.getHeuristic() - getHeuristic();
    }

    /**
     * The method return the depth of the node.
     * @return The depth down to the node
     */
    @Override
    public int getDepth() {
        return depth;
    }
    
    /**
     * The method set a new depth to the node.
     * @param newDepth The depth that shall be assigned to the node.
     */
    @Override
    public void setDepth(int newDepth){
        if (newDepth >= 0)
            this.depth = newDepth;
    }   
}
