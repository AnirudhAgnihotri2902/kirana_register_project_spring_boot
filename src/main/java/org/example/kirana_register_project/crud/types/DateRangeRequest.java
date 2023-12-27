package org.example.kirana_register_project.crud.types;

import java.util.Date;

public class DateRangeRequest {

    private Date startDate;
    private Date endDate;

    // Constructors, getters, and setters

    public DateRangeRequest() {
        // Default constructor
    }

    public DateRangeRequest(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

