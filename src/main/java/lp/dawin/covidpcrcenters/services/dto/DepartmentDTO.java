package lp.dawin.covidpcrcenters.services.dto;

/**
 * Represents a departement transfer object
 */
public class DepartmentDTO {

    /**
     * Department code
     */
    private String code;

    /**
     * Department name (label)
     */
    private String name;

    /**
     * Department region name
     */
    private String region;

    /**
     * Number of PCR center available in this department
     */
    private long nbCenter;

    /**
     * Gets code.
     *
     * @return value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets new code.
     *
     * @param code new value of code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Gets name.
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new name.
     *
     * @param name new value of name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets region.
     *
     * @return value of region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets new region.
     *
     * @param region new value of region
     */
    public void setRegion(final String region) {
        this.region = region;
    }

    /**
     * Gets nbCenter.
     *
     * @return value of nbCenter
     */
    public long getNbCenter() {
        return nbCenter;
    }

    /**
     * Sets new nbCenter.
     *
     * @param nbCenter new value of nbCenter
     */
    public void setNbCenter(final long nbCenter) {
        this.nbCenter = nbCenter;
    }
}
