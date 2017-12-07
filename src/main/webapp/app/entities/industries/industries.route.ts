import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { IndustriesComponent } from './industries.component';
import { IndustriesDetailComponent } from './industries-detail.component';
import { IndustriesPopupComponent } from './industries-dialog.component';
import { IndustriesDeletePopupComponent } from './industries-delete-dialog.component';

export const industriesRoute: Routes = [
    {
        path: 'industries',
        component: IndustriesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.industries.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'industries/:id',
        component: IndustriesDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.industries.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const industriesPopupRoute: Routes = [
    {
        path: 'industries-new',
        component: IndustriesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.industries.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'industries/:id/edit',
        component: IndustriesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.industries.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'industries/:id/delete',
        component: IndustriesDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.industries.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
