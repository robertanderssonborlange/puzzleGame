/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traverse;

import state.IState;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The class search for a solution with help of an breadth first traversal.
 * The class inherit from the base class Traverse and implements some of the
 * methods that are declared in the interface ITraverse.
 *
 * @author Robert Andersson, orginal code by Hans Edy Mårtensson
 */
public class BreadthFirst implements ITraverse {

    private IState start, goal;
    private int iteration = 0;
    private boolean verbose;
    private boolean leftToRight;


    /**
     * The constructor saves the start and goal states.
     *
     * @param startState The start state from which the search of a solution begins.
     * @param goalState  The goal state which the solver search for.
     * @param verbose    If true the content of open and closed is printed out.
     */
    public BreadthFirst(IState startState, IState goalState, boolean verbose) {
        start = startState;
        goal = goalState;
        this.verbose = verbose;

    }

    /**
     * This is an implementation of the breadth first algorithm that can be
     * find in the textbook chapter 3.
     * The method does a breath first traversal to find the goal state. If the
     * goal is found the trace from start down to the goal is returned. If the
     * goal is not found the method return null
     *
     * @return the trace from start to goal or null if the goal is not found.
     */
    @Override
    public IState search() {

        Queue<IState> open = new LinkedList<>();
        Queue<IState> closed = new LinkedList<>();

        open.add(start);

        while (!open.isEmpty()) {

            showStatus("", open, closed, iteration);

            IState tempState = open.poll();
            iteration++;

            if (tempState.equals(goal)) {
                return tempState;
            } else {
                closed.add(tempState);
                IState[] children = tempState.createChildren();

                    for (IState child : children) {
                        if (child != null && child.equals(goal)) {
                            return child;

                        } else if (child != null && !open.contains(child) && !closed.contains(child)) {
                            open.add(child);
                        }
                    }

            }//else
        }//while
        return null;
    }

    /**
     * The method return the number of iterations that has been done
     * to find the goal state.
     *
     * @return the number of iterations
     */
    @Override
    public int getIterations() {
        return iteration;
    }

    /**
     * The method print out the content of the both list open and closed.
     *
     * @param label     A label that is printed out at the beginning of the first row
     * @param open      A list with all the nodes that are discovered but not visited.
     * @param closed    A list with all the nodes that are visited.
     * @param iteration The count of iterations, for each iteration a node was visited.
     */
    protected void showStatus(String label, Iterable<IState> open, Iterable<IState> closed, int iteration) {
        if (verbose) {
            System.out.print(label + ", iteration: " + iteration);

            System.out.println(" *****************");

            System.out.println("OPEN NODES");
            open.forEach(savedState -> savedState.show());
            System.out.println();

            System.out.println("CLOSED NODES");
            closed.forEach(savedState -> savedState.show());
            System.out.println();

            System.out.println("----------------------------------------------");
        }
    }


}
