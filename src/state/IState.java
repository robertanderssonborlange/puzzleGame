/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

/**
 * This interface is the facade to the package state. Use the interface to
 * access methods needed for the menu and the solver.
 * @author Please, enter your name instead of this sentence if you do any changes.
 */
public interface IState {
    /**
     * The method shall print out the state of the node.
     */
    void show();
    
    /**
     * The method shall create the children of a node and return the
     * references to them in a array with 4 element. Some of the elements
     * can have the value null if it not was possible to create all children.
     * @return An array with children, some of the elements can be null.
     */
    IState[] createChildren();
    
    /**
     * Any children will not be created if the parents depth is equal to
     * depthBound
     * @param depthBound Maximal depth for any state.
     * @return the created children
     */
    IState[] createChildren(int depthBound);
    
    /**
     * The method shall return the parent of this state.
     * @return The parent of this state.
     */
    IState getParent();
    
    /**
     * Set a parent to the object
     * @param aNewParent a reference to the parent.
     */
    void setParent(IState aNewParent);
    
    /**
     * The method shall return the heuristic value.
     * @return The heuristic value.
     */
    int getHeuristic();
    
    /**
     * The method shall return the depth of the node.
     * @return The depth down to the node
     */
    int getDepth();
    
    /**
     * The method shall set a new depth to the node.
     * @param newDepth The depth that shall be assigned to the node.
     */
    void setDepth(int newDepth);
    
    /**
     * This method shall calculate a heuristic value and assign it to the node
     * @param goal The goal state that is searched
     */
    void calculateHeuristicValue(IState goal);

    int compareTo(IState other);
}
