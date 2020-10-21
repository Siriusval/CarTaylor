package impl;

import api.CompatibilityManager;
import api.PartType;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation class of CompatibilityManager
 *
 * @see CompatibilityManager
 * @author Valentin Hulot
 */
public class CompatibilityManagerImpl extends  CompatibilityCheckerImpl implements CompatibilityManager  {

    /**
     * Constructor for CompatibilityManagerImpl<br>
     * Initialize the attributes
     */
    public CompatibilityManagerImpl() {
       super();
    }

    /**
     * {@inheritDoc}
     * @param reference, the part to which we add the incompatibilities
     * @param target, the parts that are incompatibles with the reference
     * @throws IllegalArgumentException if arguments are null or set is empty
     */
    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) throws IllegalArgumentException {

        //Check if null or empty
        Objects.requireNonNull(reference, "reference cannot be null");
        Objects.requireNonNull(target,"target cannot be null");
        if(target.isEmpty()){
            throw new IllegalArgumentException("target cannot be empty");
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

        Objects.requireNonNull(reference,"reference cannot be null");
        Objects.requireNonNull(target,"target cannot be null");

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

        Objects.requireNonNull(reference,"reference cannot be null");
        Objects.requireNonNull(target,"target cannot be null");
        if(target.isEmpty()){
            throw new IllegalArgumentException("target cannot be empty");
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
        Objects.requireNonNull(reference,"reference cannot be null");
        Objects.requireNonNull(target,"target cannot be null");

        this.requirements.removeEdge(reference,target);
    }

}
