import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RefplattformOneSharedModule } from '../../shared';
import {
    ProjectRolesService,
    ProjectRolesPopupService,
    ProjectRolesComponent,
    ProjectRolesDetailComponent,
    ProjectRolesDialogComponent,
    ProjectRolesPopupComponent,
    ProjectRolesDeletePopupComponent,
    ProjectRolesDeleteDialogComponent,
    projectRolesRoute,
    projectRolesPopupRoute,
} from './';

const ENTITY_STATES = [
    ...projectRolesRoute,
    ...projectRolesPopupRoute,
];

@NgModule({
    imports: [
        RefplattformOneSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ProjectRolesComponent,
        ProjectRolesDetailComponent,
        ProjectRolesDialogComponent,
        ProjectRolesDeleteDialogComponent,
        ProjectRolesPopupComponent,
        ProjectRolesDeletePopupComponent,
    ],
    entryComponents: [
        ProjectRolesComponent,
        ProjectRolesDialogComponent,
        ProjectRolesPopupComponent,
        ProjectRolesDeleteDialogComponent,
        ProjectRolesDeletePopupComponent,
    ],
    providers: [
        ProjectRolesService,
        ProjectRolesPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RefplattformOneProjectRolesModule {}
