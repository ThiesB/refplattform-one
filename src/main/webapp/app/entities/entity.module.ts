import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { RefplattformOneCustomerReferencesModule } from './customer-references/customer-references.module';
import { RefplattformOneConsultingDivisionModule } from './consulting-division/consulting-division.module';
import { RefplattformOneCustomersModule } from './customers/customers.module';
import { RefplattformOneIndustriesModule } from './industries/industries.module';
import { RefplattformOneProjectRolesModule } from './project-roles/project-roles.module';
import { RefplattformOneServiceComponentsModule } from './service-components/service-components.module';
import { RefplattformOneDownloadsModule } from './downloads/downloads.module';
import { RefplattformOneDocumentTypesModule } from './document-types/document-types.module';
import { RefplattformOneLanguagesModule } from './languages/languages.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        RefplattformOneCustomerReferencesModule,
        RefplattformOneConsultingDivisionModule,
        RefplattformOneCustomersModule,
        RefplattformOneIndustriesModule,
        RefplattformOneProjectRolesModule,
        RefplattformOneServiceComponentsModule,
        RefplattformOneDownloadsModule,
        RefplattformOneDocumentTypesModule,
        RefplattformOneLanguagesModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RefplattformOneEntityModule {}
