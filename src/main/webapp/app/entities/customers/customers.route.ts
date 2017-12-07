import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CustomersComponent } from './customers.component';
import { CustomersDetailComponent } from './customers-detail.component';
import { CustomersPopupComponent } from './customers-dialog.component';
import { CustomersDeletePopupComponent } from './customers-delete-dialog.component';

export const customersRoute: Routes = [
    {
        path: 'customers',
        component: CustomersComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.customers.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'customers/:id',
        component: CustomersDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.customers.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const customersPopupRoute: Routes = [
    {
        path: 'customers-new',
        component: CustomersPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.customers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customers/:id/edit',
        component: CustomersPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.customers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customers/:id/delete',
        component: CustomersDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.customers.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
