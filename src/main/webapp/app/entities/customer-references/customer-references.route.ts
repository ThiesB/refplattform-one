import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CustomerReferencesComponent } from './customer-references.component';
import { CustomerReferencesDetailComponent } from './customer-references-detail.component';
import { CustomerReferencesPopupComponent } from './customer-references-dialog.component';
import { CustomerReferencesDeletePopupComponent } from './customer-references-delete-dialog.component';

export const customerReferencesRoute: Routes = [
    {
        path: 'customer-references',
        component: CustomerReferencesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.customerReferences.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'customer-references/:id',
        component: CustomerReferencesDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.customerReferences.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const customerReferencesPopupRoute: Routes = [
    {
        path: 'customer-references-new',
        component: CustomerReferencesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.customerReferences.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customer-references/:id/edit',
        component: CustomerReferencesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.customerReferences.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customer-references/:id/delete',
        component: CustomerReferencesDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.customerReferences.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
