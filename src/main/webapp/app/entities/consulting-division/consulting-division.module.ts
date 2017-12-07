import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RefplattformOneSharedModule } from '../../shared';
import {
    ConsultingDivisionService,
    ConsultingDivisionPopupService,
    ConsultingDivisionComponent,
    ConsultingDivisionDetailComponent,
    ConsultingDivisionDialogComponent,
    ConsultingDivisionPopupComponent,
    ConsultingDivisionDeletePopupComponent,
    ConsultingDivisionDeleteDialogComponent,
    consultingDivisionRoute,
    consultingDivisionPopupRoute,
} from './';

const ENTITY_STATES = [
    ...consultingDivisionRoute,
    ...consultingDivisionPopupRoute,
];

@NgModule({
    imports: [
        RefplattformOneSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ConsultingDivisionComponent,
        ConsultingDivisionDetailComponent,
        ConsultingDivisionDialogComponent,
        ConsultingDivisionDeleteDialogComponent,
        ConsultingDivisionPopupComponent,
        ConsultingDivisionDeletePopupComponent,
    ],
    entryComponents: [
        ConsultingDivisionComponent,
        ConsultingDivisionDialogComponent,
        ConsultingDivisionPopupComponent,
        ConsultingDivisionDeleteDialogComponent,
        ConsultingDivisionDeletePopupComponent,
    ],
    providers: [
        ConsultingDivisionService,
        ConsultingDivisionPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RefplattformOneConsultingDivisionModule {}
