/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzleGame;
import java.util.*;
import state.IState;
import traverse.ITraverse;

/**
 * The class has a solver that search for a solution with help of breadth first
 * an prints out that solution.
 * @author Robert Andersson, orginal code by Hans Edy Mårtensson
 */
class Solver {
    private ITraverse traverser = null;

    public void setTraversingStrategi(ITraverse traverser){
        this.traverser = traverser;
    }
    
    /**
     * The method solve search for a solution among the states with help of a
     * traversal. The method print out the solution if it is found. 
     */
    public void searchSolution() {
        System.out.println("The solver is running.. .");

        Date startTime = new Date();

        IState aSolution = traverser.search();

        Date endTime = new Date();

        long milliseconds = endTime.getTime() - startTime.getTime(); //how long the search is ongoing

        if (aSolution != null){

            System.out.println("Found a solution!");
            LinkedList<IState> trace = solutionTrack(aSolution);
            trace.forEach(aState -> aState.show());
            System.out.println();
            System.out.println("The depth of the solution = " + (trace.size() - 1));
        }else
            System.out.println("Could not find any solution.");
        
        System.out.println("Number of iterations = " + traverser.getIterations());
        System.out.print("Elapsed time to search = " + milliseconds + " milliseconds");
        System.out.println();
    }
    
    /**
     * The method makes a list with references to the nodes that are included
     * in the track from start to goal.
     * @param state The goal node that has been found.
     * @return 
     */
    private LinkedList<IState> solutionTrack(IState state) {
        LinkedList<IState> track = new LinkedList<>();
        
        while (state != null){
            track.addFirst(state);
            state = state.getParent();
        }
        
        return track;
    }
}
