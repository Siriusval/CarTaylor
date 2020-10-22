package impl;

import api.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation class of interface Configurator
 * It makes available the key objects / methods
 *
 * @see Configurator
 * @author Valentin Hulot
 */
public class ConfiguratorImpl implements Configurator {

    /** Configuration object, represent a configuration of partTypes  */
    private final Configuration configuration;
    /** CompatibilityChecker object, manage requirements and incompatibilities */
    private final CompatibilityManager compatibilityManager;
    /** Set of all the categories */
    private final Set<Category> categories;
    /** Set of all the variants (PartTypes) */
    private final Set<PartType> variants;



    /**
     * Constructor for ConfiguratorImpl
     * @param compatibilityChecker, the compatibilityChecker
     * @param categories, set of categories
     * @param variants, set of variants/partTypes
     */
    public ConfiguratorImpl(CompatibilityManager compatibilityManager) {

        Objects.requireNonNull(compatibilityManager,"compatibilityManager cannot be null");

        this.compatibilityManager = compatibilityManager;


        this.categories = new HashSet<>();
        this.variants = new HashSet<>();
        init();

        Set<PartType> selectedParts = new HashSet<>();
        this.configuration = new ConfigurationImpl(this, selectedParts);
    }

    private void init(){
        //Categories
        Category engineCategory = new CategoryImpl("engine");
        Category transmissionCategory = new CategoryImpl("transmission");
        Category exteriorCategory = new CategoryImpl("exterior");
        Category interiorCategory = new CategoryImpl("interior");
        categories.add(engineCategory);
        categories.add(transmissionCategory);
        categories.add(interiorCategory);
        categories.add(exteriorCategory);


        //Variants
        //Engine parts
        PartType EG100 = new PartTypeImpl("EG100",engineCategory);
        PartType EG133 = new PartTypeImpl("EG133",engineCategory);
        PartType EG210 = new PartTypeImpl("EG210",engineCategory);
        PartType ED110 = new PartTypeImpl("ED110",engineCategory);
        PartType ED180 = new PartTypeImpl("ED180",engineCategory);
        PartType EH120 = new PartTypeImpl("EH120",engineCategory);
        variants.add(EG100);
        variants.add(EG133);
        variants.add(EG210);
        variants.add(ED110);
        variants.add(ED180);
        variants.add(EH120);

        //Transmission parts
        PartType TM5 = new PartTypeImpl("TM5",transmissionCategory);
        PartType TM6 = new PartTypeImpl("TM6",transmissionCategory);
        PartType TA5 = new PartTypeImpl("TA5",transmissionCategory);
        PartType TS6 = new PartTypeImpl("TS6",transmissionCategory);
        PartType TSF7 = new PartTypeImpl("TSF7",transmissionCategory);
        PartType TC120 = new PartTypeImpl("TC120",transmissionCategory);
        variants.add(TM5);
        variants.add(TM6);
        variants.add(TA5);
        variants.add(TS6);
        variants.add(TSF7);
        variants.add(TC120);

        //Exterior parts
        PartType XC = new PartTypeImpl("XC",exteriorCategory);
        PartType XM = new PartTypeImpl("XM",exteriorCategory);
        PartType XS = new PartTypeImpl("XS",exteriorCategory);
        variants.add(XC);
        variants.add(XM);
        variants.add(XS);

        //Interior parts
        PartType IN = new PartTypeImpl("IN",interiorCategory);
        PartType IH = new PartTypeImpl("IH",interiorCategory);
        PartType IS = new PartTypeImpl("IS",interiorCategory);
        variants.add(IN);
        variants.add(IH);
        variants.add(IS);

        //Incopatiblities
        compatibilityManager.addIncompatibilities(TA5,Set.of(EG100));
        compatibilityManager.addIncompatibilities(TSF7,Set.of(EG100,EG133,ED110));
        compatibilityManager.addIncompatibilities(XC,Set.of(EG210));
        compatibilityManager.addIncompatibilities(XM,Set.of(EG100));
        compatibilityManager.addIncompatibilities(XS,Set.of(EG100));
        compatibilityManager.addIncompatibilities(IS,Set.of(EG100,TM5));

        //Requirements
        compatibilityManager.addRequirements(EH120,Set.of(TC120));
        compatibilityManager.addRequirements(TC120,Set.of(EH120));
        compatibilityManager.addRequirements(XS,Set.of(IS));
        compatibilityManager.addRequirements(IS,Set.of(XS));
    }


    /**
     * {@inheritDoc}
     * @return a copy of the set of categories
     */
    @Override
    public Set<Category> getCategories() {

        return Collections.unmodifiableSet(this.categories);
    }

    /**
     * {@inheritDoc}
     * @param category, the category of the elements we want to retrieve
     * @return a copy of the set of variants
     */
    @Override
    public Set<PartType> getVariants(Category category) {
        Objects.requireNonNull(category,"category cannot be null");

        return this.variants.stream().filter(v -> v.getCategory()== category).collect(Collectors.toUnmodifiableSet());
    }

    /**
     * {@inheritDoc}
     * @return the configuration
     */
    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }

    /**
     * {@inheritDoc}
     * @return the compatibilityChecker
     */
    @Override
    public CompatibilityChecker getCompatibilityChecker() {
        return this.compatibilityManager;
    }
}
