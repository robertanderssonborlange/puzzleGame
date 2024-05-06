package traverse;

import state.IState;

import java.util.PriorityQueue;

/**
 * The class search for a solution with help of an best first traversal.
 * implements some of the methods that are declared in the interface ITraverse.
 *
 * @author Robert Andersson, orginal code by Hans Edy Mårtensson
 */
public class BestFirst implements ITraverse {

    private Comparator comparator;
    private IState start, goal;
    private int iteration = 0;
    private boolean verbose;

    public BestFirst(IState startState, IState goalState, boolean verbose) {
        start = startState;
        goal = goalState;
        this.verbose = verbose;
        comparator = new Comparator(); //for sorting
    }


    @Override
    public IState search() {

        PriorityQueue<IState> open = new PriorityQueue<>(comparator);
        PriorityQueue<IState> closed = new PriorityQueue<>(comparator);

        open.offer(start);
        start.calculateHeuristicValue(goal);

        while (!open.isEmpty()) {

            //   showStatus("", open, closed, iteration);

            IState tempState = open.poll();

            iteration++;

            if (tempState.equals(goal)) {
                return tempState;
            } else {
                IState[] children = tempState.createChildren();

                for (IState child : children) {
                    if (child != null && !open.contains(child) && !closed.contains(child)) {
                        child.calculateHeuristicValue(goal);
                        if (child.equals(goal)) return child; //goal-check
                        open.offer(child);
                    }
                }
                closed.offer(tempState);
            }
        }
        return null; //search failed
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
