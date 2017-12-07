import { Route } from '@angular/router';

import { ReferenceComponent } from './';

export const REFERENCE_ROUTE: Route = {
    path: 'reference',
    component: ReferenceComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    }
};
