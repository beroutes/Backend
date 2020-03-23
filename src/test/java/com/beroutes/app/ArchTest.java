package com.beroutes.app;

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
            .importPackages("com.beroutes.app");

        noClasses()
            .that()
                .resideInAnyPackage("com.beroutes.app.service..")
            .or()
                .resideInAnyPackage("com.beroutes.app.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.beroutes.app.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
