import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { DocumentTypesComponent } from './document-types.component';
import { DocumentTypesDetailComponent } from './document-types-detail.component';
import { DocumentTypesPopupComponent } from './document-types-dialog.component';
import { DocumentTypesDeletePopupComponent } from './document-types-delete-dialog.component';

export const documentTypesRoute: Routes = [
    {
        path: 'document-types',
        component: DocumentTypesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.documentTypes.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'document-types/:id',
        component: DocumentTypesDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.documentTypes.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const documentTypesPopupRoute: Routes = [
    {
        path: 'document-types-new',
        component: DocumentTypesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.documentTypes.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'document-types/:id/edit',
        component: DocumentTypesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.documentTypes.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'document-types/:id/delete',
        component: DocumentTypesDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.documentTypes.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
