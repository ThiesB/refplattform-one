import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { ProjectRoles } from './project-roles.model';
import { ProjectRolesService } from './project-roles.service';

@Component({
    selector: 'jhi-project-roles-detail',
    templateUrl: './project-roles-detail.component.html'
})
export class ProjectRolesDetailComponent implements OnInit, OnDestroy {

    projectRoles: ProjectRoles;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private projectRolesService: ProjectRolesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProjectRoles();
    }

    load(id) {
        this.projectRolesService.find(id).subscribe((projectRoles) => {
            this.projectRoles = projectRoles;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProjectRoles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'projectRolesListModification',
            (response) => this.load(this.projectRoles.id)
        );
    }
}
