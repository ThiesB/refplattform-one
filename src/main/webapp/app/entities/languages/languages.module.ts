import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RefplattformOneSharedModule } from '../../shared';
import {
    LanguagesService,
    LanguagesPopupService,
    LanguagesComponent,
    LanguagesDetailComponent,
    LanguagesDialogComponent,
    LanguagesPopupComponent,
    LanguagesDeletePopupComponent,
    LanguagesDeleteDialogComponent,
    languagesRoute,
    languagesPopupRoute,
} from './';

const ENTITY_STATES = [
    ...languagesRoute,
    ...languagesPopupRoute,
];

@NgModule({
    imports: [
        RefplattformOneSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LanguagesComponent,
        LanguagesDetailComponent,
        LanguagesDialogComponent,
        LanguagesDeleteDialogComponent,
        LanguagesPopupComponent,
        LanguagesDeletePopupComponent,
    ],
    entryComponents: [
        LanguagesComponent,
        LanguagesDialogComponent,
        LanguagesPopupComponent,
        LanguagesDeleteDialogComponent,
        LanguagesDeletePopupComponent,
    ],
    providers: [
        LanguagesService,
        LanguagesPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RefplattformOneLanguagesModule {}
