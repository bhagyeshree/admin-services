package com.cognizant.goldenretrievers.adminservices;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
final class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_generator")
    @SequenceGenerator(name="admin_generator", sequenceName = "admin_sequence", allocationSize=1)
    private final long id;
    private final String status;

    public Badge(){
        this.id = 0L;
        this.status = null;
    }

    @JsonCreator
    public Badge(@JsonProperty("id") final long id,@JsonProperty("status") final String status) {
        this.id = id;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Badge)) return false;
        Badge badge = (Badge) o;
        return getId() == badge.getId() &&
                Objects.equals(getStatus(), badge.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus());
    }
}
