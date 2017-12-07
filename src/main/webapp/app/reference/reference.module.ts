import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RefplattformOneSharedModule } from '../shared';

import { REFERENCE_ROUTE, ReferenceComponent } from './';

@NgModule({
    imports: [
        RefplattformOneSharedModule,
        RouterModule.forChild([ REFERENCE_ROUTE ])
    ],
    declarations: [
        ReferenceComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RefplattformOneReferenceModule {}
