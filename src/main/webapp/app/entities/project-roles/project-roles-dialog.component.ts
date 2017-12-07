import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ProjectRoles } from './project-roles.model';
import { ProjectRolesPopupService } from './project-roles-popup.service';
import { ProjectRolesService } from './project-roles.service';

@Component({
    selector: 'jhi-project-roles-dialog',
    templateUrl: './project-roles-dialog.component.html'
})
export class ProjectRolesDialogComponent implements OnInit {

    projectRoles: ProjectRoles;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private projectRolesService: ProjectRolesService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.projectRoles.id !== undefined) {
            this.subscribeToSaveResponse(
                this.projectRolesService.update(this.projectRoles));
        } else {
            this.subscribeToSaveResponse(
                this.projectRolesService.create(this.projectRoles));
        }
    }

    private subscribeToSaveResponse(result: Observable<ProjectRoles>) {
        result.subscribe((res: ProjectRoles) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: ProjectRoles) {
        this.eventManager.broadcast({ name: 'projectRolesListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-project-roles-popup',
    template: ''
})
export class ProjectRolesPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private projectRolesPopupService: ProjectRolesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.projectRolesPopupService
                    .open(ProjectRolesDialogComponent as Component, params['id']);
            } else {
                this.projectRolesPopupService
                    .open(ProjectRolesDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
