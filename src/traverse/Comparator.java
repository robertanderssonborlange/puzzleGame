package traverse;

import state.IState;
import state.State;

/**
 * This is a comparator-class that implements, the java.util.comparator Interface.
 *
 * The class is used to sort a collection. In this case it will be sorted by Heurstic-value.
 *
 * @author Robert Andersson
 */
public class Comparator implements java.util.Comparator<IState>{


    @Override
    /**
     * Compares tho iStates, by their Heurstic values.
     *
     * Returns -1 if the first object have a smaller heuristic then the second.
     * and 1 if the first object have a bigger heuristic then the second.
     *
     * If the heuristic are the same for the two objects, 0 will be returned.
     *
     *
     * @return int -1, 1 or 0
     */
    public int compare(IState iState1, IState iState2) {
        if (iState1.getHeuristic() < iState2.getHeuristic()) return -1;
        else if (iState1.getHeuristic() > iState2.getHeuristic()) return 1;
        else return 0;
    }
}