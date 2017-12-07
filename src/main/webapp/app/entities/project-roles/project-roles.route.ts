import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { ProjectRolesComponent } from './project-roles.component';
import { ProjectRolesDetailComponent } from './project-roles-detail.component';
import { ProjectRolesPopupComponent } from './project-roles-dialog.component';
import { ProjectRolesDeletePopupComponent } from './project-roles-delete-dialog.component';

export const projectRolesRoute: Routes = [
    {
        path: 'project-roles',
        component: ProjectRolesComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.projectRoles.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'project-roles/:id',
        component: ProjectRolesDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.projectRoles.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const projectRolesPopupRoute: Routes = [
    {
        path: 'project-roles-new',
        component: ProjectRolesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.projectRoles.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'project-roles/:id/edit',
        component: ProjectRolesPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.projectRoles.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'project-roles/:id/delete',
        component: ProjectRolesDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'refplattformOneApp.projectRoles.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
