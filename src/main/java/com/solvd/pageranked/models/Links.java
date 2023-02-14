package com.solvd.pageranked.models;

import java.util.Objects;

public class Links {
    private int id;
    private String linkHref;
    private String linkText;

    public Links() {
    }

    public Links(String linkHref) {
        this.linkHref = linkHref;
    }

    public Links(int id, String linkHref) {
        this.id = id;
        this.linkHref = linkHref;
    }

    public Links(int id, String linkHref, String linkText) {
        this.id = id;
        this.linkHref = linkHref;
        this.linkText = linkText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkHref() {
        return linkHref;
    }

    public void setLinkHref(String linkHref) {
        this.linkHref = linkHref;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Links links = (Links) o;
        return id == links.id && Objects.equals(linkHref, links.linkHref) && Objects.equals(linkText, links.linkText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, linkHref, linkText);
    }

    @Override
    public String toString() {
        return "Links{" +
                "id=" + id +
                ", linkHref='" + linkHref + '\'' +
                ", linkText='" + linkText + '\'' +
                '}';
    }
}
