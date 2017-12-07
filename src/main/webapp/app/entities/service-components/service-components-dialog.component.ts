import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ServiceComponents } from './service-components.model';
import { ServiceComponentsPopupService } from './service-components-popup.service';
import { ServiceComponentsService } from './service-components.service';
import { ConsultingDivision, ConsultingDivisionService } from '../consulting-division';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-service-components-dialog',
    templateUrl: './service-components-dialog.component.html'
})
export class ServiceComponentsDialogComponent implements OnInit {

    serviceComponents: ServiceComponents;
    isSaving: boolean;

    consultingdivisions: ConsultingDivision[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private serviceComponentsService: ServiceComponentsService,
        private consultingDivisionService: ConsultingDivisionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.consultingDivisionService.query()
            .subscribe((res: ResponseWrapper) => { this.consultingdivisions = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.serviceComponents.id !== undefined) {
            this.subscribeToSaveResponse(
                this.serviceComponentsService.update(this.serviceComponents));
        } else {
            this.subscribeToSaveResponse(
                this.serviceComponentsService.create(this.serviceComponents));
        }
    }

    private subscribeToSaveResponse(result: Observable<ServiceComponents>) {
        result.subscribe((res: ServiceComponents) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: ServiceComponents) {
        this.eventManager.broadcast({ name: 'serviceComponentsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackConsultingDivisionById(index: number, item: ConsultingDivision) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-service-components-popup',
    template: ''
})
export class ServiceComponentsPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private serviceComponentsPopupService: ServiceComponentsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.serviceComponentsPopupService
                    .open(ServiceComponentsDialogComponent as Component, params['id']);
            } else {
                this.serviceComponentsPopupService
                    .open(ServiceComponentsDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
