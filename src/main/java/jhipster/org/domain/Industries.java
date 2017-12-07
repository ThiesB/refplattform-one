package jhipster.org.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A Industries.
 */
@Entity
@Table(name = "industries")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Industries implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "industry_name", nullable = false)
    private String industryName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndustryName() {
        return industryName;
    }

    public Industries industryName(String industryName) {
        this.industryName = industryName;
        return this;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Industries industries = (Industries) o;
        if (industries.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), industries.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Industries{" +
            "id=" + getId() +
            ", industryName='" + getIndustryName() + "'" +
            "}";
    }
}
