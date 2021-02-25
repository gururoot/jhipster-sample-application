package ar.gururoot.myapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ar.gururoot.myapp");

        noClasses()
            .that()
            .resideInAnyPackage("ar.gururoot.myapp.service..")
            .or()
            .resideInAnyPackage("ar.gururoot.myapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ar.gururoot.myapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
