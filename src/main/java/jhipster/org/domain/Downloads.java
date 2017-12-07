package jhipster.org.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A Downloads.
 */
@Entity
@Table(name = "downloads")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Downloads implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "titel", nullable = false)
    private String titel;

    @Column(name = "bereich")
    private String bereich;

    @NotNull
    @Column(name = "anlage_url", nullable = false)
    private String anlageURL;

    @ManyToOne
    private CustomerReferences customerReferences;

    @ManyToOne
    private DocumentTypes doctype;

    @ManyToOne
    private Languages language;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public Downloads titel(String titel) {
        this.titel = titel;
        return this;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBereich() {
        return bereich;
    }

    public Downloads bereich(String bereich) {
        this.bereich = bereich;
        return this;
    }

    public void setBereich(String bereich) {
        this.bereich = bereich;
    }

    public String getAnlageURL() {
        return anlageURL;
    }

    public Downloads anlageURL(String anlageURL) {
        this.anlageURL = anlageURL;
        return this;
    }

    public void setAnlageURL(String anlageURL) {
        this.anlageURL = anlageURL;
    }

    public CustomerReferences getCustomerReferences() {
        return customerReferences;
    }

    public Downloads customerReferences(CustomerReferences customerReferences) {
        this.customerReferences = customerReferences;
        return this;
    }

    public void setCustomerReferences(CustomerReferences customerReferences) {
        this.customerReferences = customerReferences;
    }

    public DocumentTypes getDoctype() {
        return doctype;
    }

    public Downloads doctype(DocumentTypes documentTypes) {
        this.doctype = documentTypes;
        return this;
    }

    public void setDoctype(DocumentTypes documentTypes) {
        this.doctype = documentTypes;
    }

    public Languages getLanguage() {
        return language;
    }

    public Downloads language(Languages languages) {
        this.language = languages;
        return this;
    }

    public void setLanguage(Languages languages) {
        this.language = languages;
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
        Downloads downloads = (Downloads) o;
        if (downloads.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), downloads.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Downloads{" +
            "id=" + getId() +
            ", titel='" + getTitel() + "'" +
            ", bereich='" + getBereich() + "'" +
            ", anlageURL='" + getAnlageURL() + "'" +
            "}";
    }
}
