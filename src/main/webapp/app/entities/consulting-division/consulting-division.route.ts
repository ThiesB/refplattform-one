import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ConsultingDivisionComponent } from './consulting-division.component';
import { ConsultingDivisionDetailComponent } from './consulting-division-detail.component';
import { ConsultingDivisionPopupComponent } from './consulting-division-dialog.component';
import { ConsultingDivisionDeletePopupComponent } from './consulting-division-delete-dialog.component';

export const consultingDivisionRoute: Routes = [
    {
        path: 'consulting-division',
        component: ConsultingDivisionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.consultingDivision.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'consulting-division/:id',
        component: ConsultingDivisionDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.consultingDivision.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const consultingDivisionPopupRoute: Routes = [
    {
        path: 'consulting-division-new',
        component: ConsultingDivisionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.consultingDivision.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'consulting-division/:id/edit',
        component: ConsultingDivisionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.consultingDivision.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'consulting-division/:id/delete',
        component: ConsultingDivisionDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.consultingDivision.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
