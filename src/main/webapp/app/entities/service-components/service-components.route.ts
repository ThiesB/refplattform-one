import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ServiceComponentsComponent } from './service-components.component';
import { ServiceComponentsDetailComponent } from './service-components-detail.component';
import { ServiceComponentsPopupComponent } from './service-components-dialog.component';
import { ServiceComponentsDeletePopupComponent } from './service-components-delete-dialog.component';

export const serviceComponentsRoute: Routes = [
    {
        path: 'service-components',
        component: ServiceComponentsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.serviceComponents.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'service-components/:id',
        component: ServiceComponentsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.serviceComponents.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const serviceComponentsPopupRoute: Routes = [
    {
        path: 'service-components-new',
        component: ServiceComponentsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.serviceComponents.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'service-components/:id/edit',
        component: ServiceComponentsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.serviceComponents.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'service-components/:id/delete',
        component: ServiceComponentsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.serviceComponents.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
