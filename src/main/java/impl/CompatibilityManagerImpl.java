package impl;

import api.CompatibilityManager;
import api.PartType;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Set;

/**
 * Implementation class of CompatibilityManager
 *
 * @see CompatibilityManager
 * @author Valentin Hulot
 */
public class CompatibilityManagerImpl implements CompatibilityManager {

    /** Graph that represents the requirements between parts */
    private Graph<PartType,DefaultEdge> requirements;
    /** Graph that represents the incompatibilites between parts */
    private Graph<PartType,DefaultEdge> incompatibilities;
    /** Object that allow to get connectivity aspects of the graph incompatibilities */
    private ConnectivityInspector<PartType,DefaultEdge> incompatibilitiesConnectivityInspector;
    /** Object that allow to get connectivity aspects of the graph incompatibilities */
    private ConnectivityInspector<PartType,DefaultEdge> requirementsConnectivityInspector ;

    /**
     * Constructor for CompatibilityManagerImpl<br>
     * Initialize the attributes
     */
    public CompatibilityManagerImpl() {
        this.requirements =  new SimpleGraph<>(DefaultEdge.class);
        this.incompatibilities = new SimpleGraph<>(DefaultEdge.class);
        this.incompatibilitiesConnectivityInspector =  new ConnectivityInspector<>(incompatibilities);
        this.requirementsConnectivityInspector = new ConnectivityInspector<>(requirements);
    }

    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) {

        this.incompatibilities.addVertex(reference); //Add a new vertex with this part (if it already exists, do nothing)

        for(PartType el : target){ //For each partType, add a vertex, and an edge between it and the reference part
            this.incompatibilities.addVertex(el);
            this.incompatibilities.addEdge(reference,el);
        }
    }

    @Override
    public void removeIncompatibility(PartType reference, PartType target) {
        this.incompatibilities.removeEdge(reference,target);
    }

    @Override
    public void addRequirements(PartType reference, Set<PartType> target) {

        this.requirements.addVertex(reference); //Add a new vertex with this part (if it already exists, do nothing)

        for(PartType el : target){ //For each partType, add a vertex, and an edge between it and the reference part
            this.requirements.addVertex(el);
            this.requirements.addEdge(reference,el);
        }
    }

    @Override
    public void removeRequirement(PartType reference, PartType target) {
        this.requirements.removeEdge(reference,target);
    }

    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        return this.incompatibilitiesConnectivityInspector.connectedSetOf(reference);
    }

    @Override
    public Set<PartType> getRequirements(PartType reference) {

        return this.requirementsConnectivityInspector.connectedSetOf(reference);
    }
}
