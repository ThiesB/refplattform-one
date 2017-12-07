import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectRoles } from './project-roles.model';
import { ProjectRolesPopupService } from './project-roles-popup.service';
import { ProjectRolesService } from './project-roles.service';

@Component({
    selector: 'jhi-project-roles-delete-dialog',
    templateUrl: './project-roles-delete-dialog.component.html'
})
export class ProjectRolesDeleteDialogComponent {

    projectRoles: ProjectRoles;

    constructor(
        private projectRolesService: ProjectRolesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.projectRolesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'projectRolesListModification',
                content: 'Deleted an projectRoles'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-project-roles-delete-popup',
    template: ''
})
export class ProjectRolesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private projectRolesPopupService: ProjectRolesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.projectRolesPopupService
                .open(ProjectRolesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
