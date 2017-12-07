import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RefplattformOneSharedModule } from '../../shared';
import {
    DocumentTypesService,
    DocumentTypesPopupService,
    DocumentTypesComponent,
    DocumentTypesDetailComponent,
    DocumentTypesDialogComponent,
    DocumentTypesPopupComponent,
    DocumentTypesDeletePopupComponent,
    DocumentTypesDeleteDialogComponent,
    documentTypesRoute,
    documentTypesPopupRoute,
} from './';

const ENTITY_STATES = [
    ...documentTypesRoute,
    ...documentTypesPopupRoute,
];

@NgModule({
    imports: [
        RefplattformOneSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        DocumentTypesComponent,
        DocumentTypesDetailComponent,
        DocumentTypesDialogComponent,
        DocumentTypesDeleteDialogComponent,
        DocumentTypesPopupComponent,
        DocumentTypesDeletePopupComponent,
    ],
    entryComponents: [
        DocumentTypesComponent,
        DocumentTypesDialogComponent,
        DocumentTypesPopupComponent,
        DocumentTypesDeleteDialogComponent,
        DocumentTypesDeletePopupComponent,
    ],
    providers: [
        DocumentTypesService,
        DocumentTypesPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RefplattformOneDocumentTypesModule {}
