package jhipster.org.service;

import jhipster.org.service.dto.CustomersDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Customers.
 */
public interface CustomersService {

    /**
     * Save a customers.
     *
     * @param customersDTO the entity to save
     * @return the persisted entity
     */
    CustomersDTO save(CustomersDTO customersDTO);

    /**
     * Get all the customers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomersDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customers.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomersDTO findOne(Long id);

    /**
     * Delete the "id" customers.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
