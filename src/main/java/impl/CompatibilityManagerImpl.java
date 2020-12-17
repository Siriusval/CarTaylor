package impl;

import api.CompatibilityManager;
import api.PartType;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation class of CompatibilityManager
 *
 * @see CompatibilityManager
 * @author Valentin Hulot
 */
public class CompatibilityManagerImpl implements CompatibilityManager  {

    /** Constant for exception message */
    public static final String REFERENCE_CANNOT_BE_NULL = "reference cannot be null";
    /** Constant for exception message */
    public static final String TARGET_CANNOT_BE_NULL = "target cannot be null";
    /** Constant for exception message */
    public static final String TARGET_CANNOT_BE_EMPTY = "target cannot be empty";
    /** Graph that represents the requirements between parts */
    protected final Graph<PartType, DefaultEdge> requirements;
    /** Graph that represents the incompatibilities between parts */
    protected final Graph<PartType,DefaultEdge> incompatibilities;

    /**
     * Constructor for CompatibilityManagerImpl<br>
     * Initialize the attributes
     */
    public CompatibilityManagerImpl() {
        this.requirements =  new DefaultDirectedGraph<>(DefaultEdge.class); //Directed
        this.incompatibilities = new DefaultUndirectedGraph<>(DefaultEdge.class); //Undirected
    }

    /**
     * {@inheritDoc}
     * @param reference, the part to which we add the incompatibilities
     * @param target, the parts that are incompatibles with the reference
     * @throws IllegalArgumentException if arguments are null or set is empty
     */
    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) {

        // /!\ Symmetric relation

        //Check if null or empty
        Objects.requireNonNull(reference, REFERENCE_CANNOT_BE_NULL);
        Objects.requireNonNull(target, TARGET_CANNOT_BE_NULL);
        if(target.isEmpty()){
            throw new IllegalArgumentException(TARGET_CANNOT_BE_EMPTY);
        }

        this.incompatibilities.addVertex(reference); //Add a new vertex with this part (if it already exists, do nothing)

        for(PartType el : target){ //For each partType, add a vertex, and an edge between it and the reference part
            this.incompatibilities.addVertex(el);
            this.incompatibilities.addEdge(reference,el);
        }
    }


    /**
     * {@inheritDoc}
     * @param reference, the part to which we remove the incompatibility
     * @param target, the part that is no longer incompatible with the reference
     */
    @Override
    public void removeIncompatibility(PartType reference, PartType target) {

        Objects.requireNonNull(reference, REFERENCE_CANNOT_BE_NULL);
        Objects.requireNonNull(target, TARGET_CANNOT_BE_NULL);

        this.incompatibilities.removeEdge(reference,target);
    }

    /**
     * {@inheritDoc}
     * @param reference, the part to which we add the requirements
     * @param target, the parts that are required with the reference
     * @throws IllegalArgumentException if arguments are null or set is empty
     */
    @Override
    public void addRequirements(PartType reference, Set<PartType> target) {

        // /!\ Not Symmetric relation

        Objects.requireNonNull(reference, REFERENCE_CANNOT_BE_NULL);
        Objects.requireNonNull(target, TARGET_CANNOT_BE_NULL);
        if(target.isEmpty()){
            throw new IllegalArgumentException(TARGET_CANNOT_BE_EMPTY);
        }

        this.requirements.addVertex(reference); //Add a new vertex with this part (if it already exists, do nothing)

        for(PartType el : target){ //For each partType, add a vertex, and an edge between it and the reference part
            this.requirements.addVertex(el);
            this.requirements.addEdge(reference,el);
        }
        
    }

    /**
     * {@inheritDoc}
     * @param reference, the part to which we remove the requirement
     * @param target, the part that is no longer required with the reference
     */
    @Override
    public void removeRequirement(PartType reference, PartType target) {
        Objects.requireNonNull(reference, REFERENCE_CANNOT_BE_NULL);
        Objects.requireNonNull(target, TARGET_CANNOT_BE_NULL);

        this.requirements.removeEdge(reference,target);
    }


    /**
     * {@inheritDoc}
     * @param reference, the PartType to be checked for incompatibilities
     * @return the set of incompatible parts with the reference
     *
     */
    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        Objects.requireNonNull(reference, REFERENCE_CANNOT_BE_NULL);

        if(this.incompatibilities.containsVertex(reference)){
            return Set.copyOf(Graphs.successorListOf(this.incompatibilities,reference)); //copyOf return an unmodifiable set
        }
        return Collections.emptySet();
    }

    /**
     * {@inheritDoc}
     * @param reference, the PartType to be checked for requirements
     * @return the set of required parts with the reference
     */
    @Override
    public Set<PartType> getRequirements(PartType reference) {
        Objects.requireNonNull(reference, REFERENCE_CANNOT_BE_NULL);
        if(this.requirements.containsVertex(reference)){
            return  Set.copyOf(Graphs.successorListOf(this.requirements,reference)); //copyOf return an unmodifiable set
        }
        return Collections.emptySet();

    }

}
