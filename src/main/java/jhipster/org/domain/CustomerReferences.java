package jhipster.org.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A CustomerReferences.
 */
@Entity
@Table(name = "customer_references")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CustomerReferences implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "titel", nullable = false)
    private String titel;

    @Column(name = "project_time_span")
    private Integer projectTimeSpan;

    @Column(name = "project_volume")
    private Integer projectVolume;

    @Column(name = "project_team")
    private String projectTeam;

    @Column(name = "exxeta_consulting_amount")
    private Integer exxetaConsultingAmount;

    @NotNull
    @Column(name = "reference_owner", nullable = false)
    private String referenceOwner;

    @Column(name = "exxeta_project")
    private Boolean exxetaProject;

    @Column(name = "finished")
    private Boolean finished;

    @Column(name = "schlagworte")
    private String schlagworte;

    @Column(name = "contact")
    private String contact;

    @Column(name = "kritische_erfolgsfaktoren")
    private String kritischeErfolgsfaktoren;

    @Column(name = "anmerkungen")
    private String anmerkungen;

    @OneToMany(mappedBy = "customerReferences")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Downloads> downloads = new HashSet<>();

    @ManyToOne
    private ConsultingDivision consultingdivision;

    @ManyToOne
    private Customers customer;

    @ManyToOne
    private Industries industry;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "customer_references_projectrole",
               joinColumns = @JoinColumn(name="customer_references_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="projectroles_id", referencedColumnName="id"))
    private Set<ProjectRoles> projectroles = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "customer_references_servicecomponent",
               joinColumns = @JoinColumn(name="customer_references_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="servicecomponents_id", referencedColumnName="id"))
    private Set<ServiceComponents> servicecomponents = new HashSet<>();

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

    public CustomerReferences titel(String titel) {
        this.titel = titel;
        return this;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Integer getProjectTimeSpan() {
        return projectTimeSpan;
    }

    public CustomerReferences projectTimeSpan(Integer projectTimeSpan) {
        this.projectTimeSpan = projectTimeSpan;
        return this;
    }

    public void setProjectTimeSpan(Integer projectTimeSpan) {
        this.projectTimeSpan = projectTimeSpan;
    }

    public Integer getProjectVolume() {
        return projectVolume;
    }

    public CustomerReferences projectVolume(Integer projectVolume) {
        this.projectVolume = projectVolume;
        return this;
    }

    public void setProjectVolume(Integer projectVolume) {
        this.projectVolume = projectVolume;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public CustomerReferences projectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
        return this;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public Integer getExxetaConsultingAmount() {
        return exxetaConsultingAmount;
    }

    public CustomerReferences exxetaConsultingAmount(Integer exxetaConsultingAmount) {
        this.exxetaConsultingAmount = exxetaConsultingAmount;
        return this;
    }

    public void setExxetaConsultingAmount(Integer exxetaConsultingAmount) {
        this.exxetaConsultingAmount = exxetaConsultingAmount;
    }

    public String getReferenceOwner() {
        return referenceOwner;
    }

    public CustomerReferences referenceOwner(String referenceOwner) {
        this.referenceOwner = referenceOwner;
        return this;
    }

    public void setReferenceOwner(String referenceOwner) {
        this.referenceOwner = referenceOwner;
    }

    public Boolean isExxetaProject() {
        return exxetaProject;
    }

    public CustomerReferences exxetaProject(Boolean exxetaProject) {
        this.exxetaProject = exxetaProject;
        return this;
    }

    public void setExxetaProject(Boolean exxetaProject) {
        this.exxetaProject = exxetaProject;
    }

    public Boolean isFinished() {
        return finished;
    }

    public CustomerReferences finished(Boolean finished) {
        this.finished = finished;
        return this;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getSchlagworte() {
        return schlagworte;
    }

    public CustomerReferences schlagworte(String schlagworte) {
        this.schlagworte = schlagworte;
        return this;
    }

    public void setSchlagworte(String schlagworte) {
        this.schlagworte = schlagworte;
    }

    public String getContact() {
        return contact;
    }

    public CustomerReferences contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getKritischeErfolgsfaktoren() {
        return kritischeErfolgsfaktoren;
    }

    public CustomerReferences kritischeErfolgsfaktoren(String kritischeErfolgsfaktoren) {
        this.kritischeErfolgsfaktoren = kritischeErfolgsfaktoren;
        return this;
    }

    public void setKritischeErfolgsfaktoren(String kritischeErfolgsfaktoren) {
        this.kritischeErfolgsfaktoren = kritischeErfolgsfaktoren;
    }

    public String getAnmerkungen() {
        return anmerkungen;
    }

    public CustomerReferences anmerkungen(String anmerkungen) {
        this.anmerkungen = anmerkungen;
        return this;
    }

    public void setAnmerkungen(String anmerkungen) {
        this.anmerkungen = anmerkungen;
    }

    public Set<Downloads> getDownloads() {
        return downloads;
    }

    public CustomerReferences downloads(Set<Downloads> downloads) {
        this.downloads = downloads;
        return this;
    }

    public CustomerReferences addDownload(Downloads downloads) {
        this.downloads.add(downloads);
        downloads.setCustomerReferences(this);
        return this;
    }

    public CustomerReferences removeDownload(Downloads downloads) {
        this.downloads.remove(downloads);
        downloads.setCustomerReferences(null);
        return this;
    }

    public void setDownloads(Set<Downloads> downloads) {
        this.downloads = downloads;
    }

    public ConsultingDivision getConsultingdivision() {
        return consultingdivision;
    }

    public CustomerReferences consultingdivision(ConsultingDivision consultingDivision) {
        this.consultingdivision = consultingDivision;
        return this;
    }

    public void setConsultingdivision(ConsultingDivision consultingDivision) {
        this.consultingdivision = consultingDivision;
    }

    public Customers getCustomer() {
        return customer;
    }

    public CustomerReferences customer(Customers customers) {
        this.customer = customers;
        return this;
    }

    public void setCustomer(Customers customers) {
        this.customer = customers;
    }

    public Industries getIndustry() {
        return industry;
    }

    public CustomerReferences industry(Industries industries) {
        this.industry = industries;
        return this;
    }

    public void setIndustry(Industries industries) {
        this.industry = industries;
    }

    public Set<ProjectRoles> getProjectroles() {
        return projectroles;
    }

    public CustomerReferences projectroles(Set<ProjectRoles> projectRoles) {
        this.projectroles = projectRoles;
        return this;
    }

    public CustomerReferences addProjectrole(ProjectRoles projectRoles) {
        this.projectroles.add(projectRoles);
        return this;
    }

    public CustomerReferences removeProjectrole(ProjectRoles projectRoles) {
        this.projectroles.remove(projectRoles);
        return this;
    }

    public void setProjectroles(Set<ProjectRoles> projectRoles) {
        this.projectroles = projectRoles;
    }

    public Set<ServiceComponents> getServicecomponents() {
        return servicecomponents;
    }

    public CustomerReferences servicecomponents(Set<ServiceComponents> serviceComponents) {
        this.servicecomponents = serviceComponents;
        return this;
    }

    public CustomerReferences addServicecomponent(ServiceComponents serviceComponents) {
        this.servicecomponents.add(serviceComponents);
        return this;
    }

    public CustomerReferences removeServicecomponent(ServiceComponents serviceComponents) {
        this.servicecomponents.remove(serviceComponents);
        return this;
    }

    public void setServicecomponents(Set<ServiceComponents> serviceComponents) {
        this.servicecomponents = serviceComponents;
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
        CustomerReferences customerReferences = (CustomerReferences) o;
        if (customerReferences.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerReferences.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerReferences{" +
            "id=" + getId() +
            ", titel='" + getTitel() + "'" +
            ", projectTimeSpan=" + getProjectTimeSpan() +
            ", projectVolume=" + getProjectVolume() +
            ", projectTeam='" + getProjectTeam() + "'" +
            ", exxetaConsultingAmount=" + getExxetaConsultingAmount() +
            ", referenceOwner='" + getReferenceOwner() + "'" +
            ", exxetaProject='" + isExxetaProject() + "'" +
            ", finished='" + isFinished() + "'" +
            ", schlagworte='" + getSchlagworte() + "'" +
            ", contact='" + getContact() + "'" +
            ", kritischeErfolgsfaktoren='" + getKritischeErfolgsfaktoren() + "'" +
            ", anmerkungen='" + getAnmerkungen() + "'" +
            "}";
    }
}
