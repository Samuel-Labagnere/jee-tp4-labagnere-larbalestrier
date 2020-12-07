package lp.dawin.covidpcrcenters.services.dto;

import javax.validation.constraints.NotBlank;

public class SaveCenterDTO {
    /**
     * Identifier of center legal FINESS (Fichier National des Etablissements Sanitaires et Sociaux)
     */
    private String idLegalFiness;

    /**
     * Identifier of physical center FINESS
     */
    private String idFiness;

    /**
     * Center name (label)
     */
    @NotBlank
    private String name;

    /**
     * Center full address
     */
    private String fullAddress;

    /**
     * Geo position
     */
    private Double longitude;
    private Double latitude;

    /**
     * Contact phone number
     */
    private String phoneNumber;

    /**
     * Gets idLegalFiness.
     *
     * @return value of idLegalFiness
     */
    public String getIdLegalFiness() {
        return idLegalFiness;
    }

    /**
     * Sets new idLegalFiness.
     *
     * @param idLegalFiness new value of idLegalFiness
     */
    public void setIdLegalFiness(final String idLegalFiness) {
        this.idLegalFiness = idLegalFiness;
    }

    /**
     * Gets idFiness.
     *
     * @return value of idFiness
     */
    public String getIdFiness() {
        return idFiness;
    }

    /**
     * Sets new idFiness.
     *
     * @param idFiness new value of idFiness
     */
    public void setIdFiness(final String idFiness) {
        this.idFiness = idFiness;
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
     * Gets fullAddress.
     *
     * @return value of fullAddress
     */
    public String getFullAddress() {
        return fullAddress;
    }

    /**
     * Sets new fullAddress.
     *
     * @param fullAddress new value of fullAddress
     */
    public void setFullAddress(final String fullAddress) {
        this.fullAddress = fullAddress;
    }

    /**
     * Gets longitude.
     *
     * @return value of longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets new longitude.
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets latitude.
     *
     * @return value of latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets new latitude.
     *
     * @param latitude new value of latitude
     */
    public void setLatitude(final Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets phoneNumber.
     *
     * @return value of phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets new phoneNumber.
     *
     * @param phoneNumber new value of phoneNumber
     */
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
