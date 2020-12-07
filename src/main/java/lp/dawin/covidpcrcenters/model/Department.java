package lp.dawin.covidpcrcenters.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Department Entity
 */
@Entity
public class Department {

    /**
     * Code (used as iunique dentifier)
     */
    @Id
    private String code;

    /**
     * name (label)
     */
    @Column(nullable = false)
    private String name;

    /**
     * Region name
     */
    @Column(nullable = false)
    private String region;

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
}
