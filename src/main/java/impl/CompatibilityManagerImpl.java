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

    private Graph<PartType,DefaultEdge> requirements = new SimpleGraph<>(DefaultEdge.class);
    private Graph<PartType,DefaultEdge> incompatibilities = new SimpleGraph<>(DefaultEdge.class);


    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) {
        incompatibilities.addVertex(reference);
        for(PartType el : target){
            incompatibilities.addVertex(el);
            incompatibilities.addEdge(reference,el);
        }
    }

    @Override
    public void removeIncompatibility(PartType reference, PartType target) {
        incompatibilities.removeEdge(reference,target);
    }

    @Override
    public void addRequirements(PartType reference, Set<PartType> target) {
        requirements.addVertex(reference);
        for(PartType el : target){
            requirements.addVertex(el);
            requirements.addEdge(reference,el);
        }
    }

    @Override
    public void removeRequirement(PartType reference, PartType target) {
        requirements.removeEdge(reference,target);
    }

    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        ConnectivityInspector<PartType,DefaultEdge> connectivityInspector = new ConnectivityInspector<>(incompatibilities);

        return connectivityInspector.connectedSetOf(reference);
    }

    @Override
    public Set<PartType> getRequirements(PartType reference) {
        ConnectivityInspector<PartType,DefaultEdge> connectivityInspector = new ConnectivityInspector<>(requirements);

        return connectivityInspector.connectedSetOf(reference);
    }
}
