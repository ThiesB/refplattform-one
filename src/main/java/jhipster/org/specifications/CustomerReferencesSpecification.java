package jhipster.org.specifications;

import jhipster.org.domain.CustomerReferences;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;

public class CustomerReferencesSpecification {
    public static Specification<CustomerReferences> containsTextInAttributes(String text, List<String> attributes) {
        if (!text.contains("%")) {
            text = "%" + text + "%";
        }
        String finalText = text;
        return (root, query, builder) -> builder.or(root.getModel().getDeclaredSingularAttributes().stream()
            .filter(a -> attributes.contains(a.getName()))
            .map(a -> builder.like(root.get(a.getName()), finalText))
            .toArray(Predicate[]::new)
        );
    }

    public static Specification<CustomerReferences> containsTextInName(String text) {
        return containsTextInAttributes(text, Arrays.asList("titel", "projectTeam", "referenceOwner", "schlagworte", "kritischeErfolgsfaktoren", "anmerkungen"));
    }
}
