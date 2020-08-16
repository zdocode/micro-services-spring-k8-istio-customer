package com.zyllion.sigma.customer;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.zyllion.sigma.customer");

        noClasses()
            .that()
                .resideInAnyPackage("com.zyllion.sigma.customer.service..")
            .or()
                .resideInAnyPackage("com.zyllion.sigma.customer.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.zyllion.sigma.customer.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
