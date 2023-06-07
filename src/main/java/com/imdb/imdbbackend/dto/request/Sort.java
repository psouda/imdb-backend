package com.imdb.imdbbackend.dto.request;

/**
 * The sort class include direction and by fields
 */
public class Sort {
    private SortDirection direction;
    private String by;

    public Sort() {
    }

    public Sort(SortDirection direction, String by) {
        this.direction = direction;
        this.by = by;
    }

    public SortDirection getDirection() {
        return direction;
    }

    public void setDirection(SortDirection direction) {
        this.direction = direction;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "direction=" + direction +
                ", by='" + by + '\'' +
                '}';
    }
}