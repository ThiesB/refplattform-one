import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerReferences } from './customer-references.model';
import { CustomerReferencesPopupService } from './customer-references-popup.service';
import { CustomerReferencesService } from './customer-references.service';

@Component({
    selector: 'jhi-customer-references-delete-dialog',
    templateUrl: './customer-references-delete-dialog.component.html'
})
export class CustomerReferencesDeleteDialogComponent {

    customerReferences: CustomerReferences;

    constructor(
        private customerReferencesService: CustomerReferencesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.customerReferencesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerReferencesListModification',
                content: 'Deleted an customerReferences'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-customer-references-delete-popup',
    template: ''
})
export class CustomerReferencesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerReferencesPopupService: CustomerReferencesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.customerReferencesPopupService
                .open(CustomerReferencesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
