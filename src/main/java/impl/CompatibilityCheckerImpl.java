package impl;

import api.CompatibilityChecker;
import api.PartType;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Objects;
import java.util.Set;

public class CompatibilityCheckerImpl implements CompatibilityChecker {


    /** Graph that represents the requirements between parts */
    protected final Graph<PartType, DefaultEdge> requirements;
    /** Graph that represents the incompatibilities between parts */
    protected final Graph<PartType,DefaultEdge> incompatibilities;
    /** Object that allow to get connectivity aspects of the graph incompatibilities */
    private final ConnectivityInspector<PartType,DefaultEdge> incompatibilitiesConnectivityInspector;
    /** Object that allow to get connectivity aspects of the graph requirements */
    private final ConnectivityInspector<PartType,DefaultEdge> requirementsConnectivityInspector ;

    public CompatibilityCheckerImpl() {
        this.requirements =  new SimpleGraph<>(DefaultEdge.class);
        this.incompatibilities = new SimpleGraph<>(DefaultEdge.class);
        this.incompatibilitiesConnectivityInspector =  new ConnectivityInspector<>(incompatibilities);
        this.requirementsConnectivityInspector = new ConnectivityInspector<>(requirements);
    }


    /**
     * {@inheritDoc}
     * @param reference, the PartType to be checked for incompatibilities
     * @return the set of incompatible parts with the reference
     *
     */
    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        Objects.requireNonNull(reference,"reference cannot be null");
        return this.incompatibilitiesConnectivityInspector.connectedSetOf(reference);
    }

    /**
     * {@inheritDoc}
     * @param reference, the PartType to be checked for requirements
     * @return the set of required parts with the reference
     */
    @Override
    public Set<PartType> getRequirements(PartType reference) {
        Objects.requireNonNull(reference,"reference cannot be null");
        return this.requirementsConnectivityInspector.connectedSetOf(reference);
    }
}
