import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RefplattformOneSharedModule } from '../../shared';
import {
    CustomerReferencesService,
    CustomerReferencesPopupService,
    CustomerReferencesComponent,
    CustomerReferencesDetailComponent,
    CustomerReferencesDialogComponent,
    CustomerReferencesPopupComponent,
    CustomerReferencesDeletePopupComponent,
    CustomerReferencesDeleteDialogComponent,
    customerReferencesRoute,
    customerReferencesPopupRoute,
} from './';

const ENTITY_STATES = [
    ...customerReferencesRoute,
    ...customerReferencesPopupRoute,
];

@NgModule({
    imports: [
        RefplattformOneSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CustomerReferencesComponent,
        CustomerReferencesDetailComponent,
        CustomerReferencesDialogComponent,
        CustomerReferencesDeleteDialogComponent,
        CustomerReferencesPopupComponent,
        CustomerReferencesDeletePopupComponent,
    ],
    entryComponents: [
        CustomerReferencesComponent,
        CustomerReferencesDialogComponent,
        CustomerReferencesPopupComponent,
        CustomerReferencesDeleteDialogComponent,
        CustomerReferencesDeletePopupComponent,
    ],
    providers: [
        CustomerReferencesService,
        CustomerReferencesPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RefplattformOneCustomerReferencesModule {}
