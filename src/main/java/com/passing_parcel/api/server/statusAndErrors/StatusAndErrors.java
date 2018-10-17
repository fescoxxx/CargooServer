package com.passing_parcel.api.server.statusAndErrors;

public class StatusAndErrors {

    private String STATUS;
    private String ERROR;

    public StatusAndErrors() {
    }

    public StatusAndErrors(String STATUS, String ERROR) {
        this.STATUS = STATUS;
        this.ERROR = ERROR;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getERROR() {
        return ERROR;
    }

    public void setERROR(String ERROR) {
        this.ERROR = ERROR;
    }
}
