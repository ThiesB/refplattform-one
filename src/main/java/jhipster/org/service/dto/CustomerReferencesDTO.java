package jhipster.org.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CustomerReferences entity.
 */
public class CustomerReferencesDTO implements Serializable {

    private Long id;

    @NotNull
    private String titel;

    private Integer projectTimeSpan;

    private Integer projectVolume;

    private String projectTeam;

    private Integer exxetaConsultingAmount;

    @NotNull
    private String referenceOwner;

    private Boolean exxetaProject;

    private Boolean finished;

    private String schlagworte;

    private String contact;

    private String kritischeErfolgsfaktoren;

    private String anmerkungen;

    private Long consultingdivisionId;

    private Long customerId;

    private Long industryId;

    private Set<ProjectRolesDTO> projectroles = new HashSet<>();

    private Set<ServiceComponentsDTO> servicecomponents = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Integer getProjectTimeSpan() {
        return projectTimeSpan;
    }

    public void setProjectTimeSpan(Integer projectTimeSpan) {
        this.projectTimeSpan = projectTimeSpan;
    }

    public Integer getProjectVolume() {
        return projectVolume;
    }

    public void setProjectVolume(Integer projectVolume) {
        this.projectVolume = projectVolume;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public Integer getExxetaConsultingAmount() {
        return exxetaConsultingAmount;
    }

    public void setExxetaConsultingAmount(Integer exxetaConsultingAmount) {
        this.exxetaConsultingAmount = exxetaConsultingAmount;
    }

    public String getReferenceOwner() {
        return referenceOwner;
    }

    public void setReferenceOwner(String referenceOwner) {
        this.referenceOwner = referenceOwner;
    }

    public Boolean isExxetaProject() {
        return exxetaProject;
    }

    public void setExxetaProject(Boolean exxetaProject) {
        this.exxetaProject = exxetaProject;
    }

    public Boolean isFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getSchlagworte() {
        return schlagworte;
    }

    public void setSchlagworte(String schlagworte) {
        this.schlagworte = schlagworte;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getKritischeErfolgsfaktoren() {
        return kritischeErfolgsfaktoren;
    }

    public void setKritischeErfolgsfaktoren(String kritischeErfolgsfaktoren) {
        this.kritischeErfolgsfaktoren = kritischeErfolgsfaktoren;
    }

    public String getAnmerkungen() {
        return anmerkungen;
    }

    public void setAnmerkungen(String anmerkungen) {
        this.anmerkungen = anmerkungen;
    }

    public Long getConsultingdivisionId() {
        return consultingdivisionId;
    }

    public void setConsultingdivisionId(Long consultingDivisionId) {
        this.consultingdivisionId = consultingDivisionId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customersId) {
        this.customerId = customersId;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industriesId) {
        this.industryId = industriesId;
    }

    public Set<ProjectRolesDTO> getProjectroles() {
        return projectroles;
    }

    public void setProjectroles(Set<ProjectRolesDTO> projectRoles) {
        this.projectroles = projectRoles;
    }

    public Set<ServiceComponentsDTO> getServicecomponents() {
        return servicecomponents;
    }

    public void setServicecomponents(Set<ServiceComponentsDTO> serviceComponents) {
        this.servicecomponents = serviceComponents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerReferencesDTO customerReferencesDTO = (CustomerReferencesDTO) o;
        if(customerReferencesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerReferencesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerReferencesDTO{" +
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
