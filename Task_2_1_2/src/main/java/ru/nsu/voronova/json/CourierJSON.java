package ru.nsu.voronova.json;


/**
 * Readonly class for describing a json object. The courier is described by id and bag capacity.
 */
public class CourierJSON {
    private int id;
    private int bagCapacity;

    /**
     * Returns read courier's id.
     *
     * @return - courier's id.
     */
    public int id() {
        return id;
    }

    /**
     * Returns read courier's id.
     *
     * @return - courier's id.
     */
    public int bagCapacity() {
        return bagCapacity;
    }
}
