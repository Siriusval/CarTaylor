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
     */
    public ConfiguratorImpl() {
        this.compatibilityManager = new CompatibilityManagerImpl();
        this.categories = new HashSet<>();
        this.variants = new HashSet<>();
        init();

        Set<Part> selectedParts = new HashSet<>();
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
        PartType EG100 = new PartTypeImpl("EG100",part.Engine.class,engineCategory);
        PartType EG133 = new PartTypeImpl("EG133",part.Engine.class,engineCategory);
        PartType EG210 = new PartTypeImpl("EG210",part.Engine.class,engineCategory);
        PartType ED110 = new PartTypeImpl("ED110",part.Engine.class,engineCategory);
        PartType ED180 = new PartTypeImpl("ED180",part.Engine.class,engineCategory);
        PartType EH120 = new PartTypeImpl("EH120",part.Engine.class,engineCategory);
        variants.add(EG100);
        variants.add(EG133);
        variants.add(EG210);
        variants.add(ED110);
        variants.add(ED180);
        variants.add(EH120);

        //Transmission parts
        PartType TM5 = new PartTypeImpl("TM5",part.Transmission.class, transmissionCategory);
        PartType TM6 = new PartTypeImpl("TM6",part.Transmission.class,transmissionCategory);
        PartType TA5 = new PartTypeImpl("TA5",part.Transmission.class,transmissionCategory);
        PartType TS6 = new PartTypeImpl("TS6",part.Transmission.class,transmissionCategory);
        PartType TSF7 = new PartTypeImpl("TSF7",part.Transmission.class,transmissionCategory);
        PartType TC120 = new PartTypeImpl("TC120",part.Transmission.class,transmissionCategory);
        variants.add(TM5);
        variants.add(TM6);
        variants.add(TA5);
        variants.add(TS6);
        variants.add(TSF7);
        variants.add(TC120);

        //Exterior parts
        PartType XC = new PartTypeImpl("XC",part.Exterior.class,exteriorCategory);
        PartType XM = new PartTypeImpl("XM",part.Exterior.class,exteriorCategory);
        PartType XS = new PartTypeImpl("XS",part.Exterior.class,exteriorCategory);
        variants.add(XC);
        variants.add(XM);
        variants.add(XS);

        //Interior parts
        PartType IN = new PartTypeImpl("IN",part.Interior.class,interiorCategory);
        PartType IH = new PartTypeImpl("IH",part.Interior.class,interiorCategory);
        PartType IS = new PartTypeImpl("IS",part.Interior.class,interiorCategory);
        variants.add(IN);
        variants.add(IH);
        variants.add(IS);

        //Incompatibilities
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

        return this.variants.stream().filter(v -> v.getCategory().equals(category)).collect(Collectors.toUnmodifiableSet());
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
