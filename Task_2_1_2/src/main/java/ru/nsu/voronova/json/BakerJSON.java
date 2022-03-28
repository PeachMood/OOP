package ru.nsu.voronova.json;

/**
 * Readonly class for describing a json object. The baker is described by id and work experience.
 */
public class BakerJSON {
    private int id;
    private int workingExperience;

    /**
     * Returns read baker's id.
     * @return - baker's id.
     */
    public int id() {
        return id;
    }

    /**
     * Returns read baker's working experience.
     * @return - baker's working experience.
     */
    public int workingExperience() {
        return workingExperience;
    }
}
