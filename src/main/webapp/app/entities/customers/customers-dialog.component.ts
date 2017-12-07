import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Customers } from './customers.model';
import { CustomersPopupService } from './customers-popup.service';
import { CustomersService } from './customers.service';

@Component({
    selector: 'jhi-customers-dialog',
    templateUrl: './customers-dialog.component.html'
})
export class CustomersDialogComponent implements OnInit {

    customers: Customers;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private customersService: CustomersService,
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
        if (this.customers.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customersService.update(this.customers));
        } else {
            this.subscribeToSaveResponse(
                this.customersService.create(this.customers));
        }
    }

    private subscribeToSaveResponse(result: Observable<Customers>) {
        result.subscribe((res: Customers) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Customers) {
        this.eventManager.broadcast({ name: 'customersListModification', content: 'OK'});
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
    selector: 'jhi-customers-popup',
    template: ''
})
export class CustomersPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customersPopupService: CustomersPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.customersPopupService
                    .open(CustomersDialogComponent as Component, params['id']);
            } else {
                this.customersPopupService
                    .open(CustomersDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
