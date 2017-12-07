import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { LanguagesComponent } from './languages.component';
import { LanguagesDetailComponent } from './languages-detail.component';
import { LanguagesPopupComponent } from './languages-dialog.component';
import { LanguagesDeletePopupComponent } from './languages-delete-dialog.component';

export const languagesRoute: Routes = [
    {
        path: 'languages',
        component: LanguagesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.languages.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'languages/:id',
        component: LanguagesDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.languages.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const languagesPopupRoute: Routes = [
    {
        path: 'languages-new',
        component: LanguagesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.languages.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'languages/:id/edit',
        component: LanguagesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.languages.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'languages/:id/delete',
        component: LanguagesDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.languages.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
