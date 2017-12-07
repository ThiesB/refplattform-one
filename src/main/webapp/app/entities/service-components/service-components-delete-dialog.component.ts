import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ServiceComponents } from './service-components.model';
import { ServiceComponentsPopupService } from './service-components-popup.service';
import { ServiceComponentsService } from './service-components.service';

@Component({
    selector: 'jhi-service-components-delete-dialog',
    templateUrl: './service-components-delete-dialog.component.html'
})
export class ServiceComponentsDeleteDialogComponent {

    serviceComponents: ServiceComponents;

    constructor(
        private serviceComponentsService: ServiceComponentsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.serviceComponentsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'serviceComponentsListModification',
                content: 'Deleted an serviceComponents'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-service-components-delete-popup',
    template: ''
})
export class ServiceComponentsDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private serviceComponentsPopupService: ServiceComponentsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.serviceComponentsPopupService
                .open(ServiceComponentsDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
