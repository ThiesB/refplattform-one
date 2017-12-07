import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CustomerReferences } from './customer-references.model';
import { CustomerReferencesPopupService } from './customer-references-popup.service';
import { CustomerReferencesService } from './customer-references.service';
import { ConsultingDivision, ConsultingDivisionService } from '../consulting-division';
import { Customers, CustomersService } from '../customers';
import { Industries, IndustriesService } from '../industries';
import { ProjectRoles, ProjectRolesService } from '../project-roles';
import { ServiceComponents, ServiceComponentsService } from '../service-components';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-customer-references-dialog',
    templateUrl: './customer-references-dialog.component.html'
})
export class CustomerReferencesDialogComponent implements OnInit {

    customerReferences: CustomerReferences;
    isSaving: boolean;

    consultingdivisions: ConsultingDivision[];

    customers: Customers[];

    industries: Industries[];

    projectroles: ProjectRoles[];

    servicecomponents: ServiceComponents[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private customerReferencesService: CustomerReferencesService,
        private consultingDivisionService: ConsultingDivisionService,
        private customersService: CustomersService,
        private industriesService: IndustriesService,
        private projectRolesService: ProjectRolesService,
        private serviceComponentsService: ServiceComponentsService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.consultingDivisionService.query()
            .subscribe((res: ResponseWrapper) => { this.consultingdivisions = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.customersService.query()
            .subscribe((res: ResponseWrapper) => { this.customers = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.industriesService.query()
            .subscribe((res: ResponseWrapper) => { this.industries = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.projectRolesService.query()
            .subscribe((res: ResponseWrapper) => { this.projectroles = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.serviceComponentsService.query()
            .subscribe((res: ResponseWrapper) => { this.servicecomponents = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.customerReferences.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerReferencesService.update(this.customerReferences));
        } else {
            this.subscribeToSaveResponse(
                this.customerReferencesService.create(this.customerReferences));
        }
    }

    private subscribeToSaveResponse(result: Observable<CustomerReferences>) {
        result.subscribe((res: CustomerReferences) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: CustomerReferences) {
        this.eventManager.broadcast({ name: 'customerReferencesListModification', content: 'OK'});
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
        return item.divisionName;
    }

    trackCustomersById(index: number, item: Customers) {
        return item.customerName;
    }

    trackIndustriesById(index: number, item: Industries) {
        return item.industryName;
    }

    trackProjectRolesById(index: number, item: ProjectRoles) {
        return item.roleName;
    }

    trackServiceComponentsById(index: number, item: ServiceComponents) {
        return item.serviceName;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-customer-references-popup',
    template: ''
})
export class CustomerReferencesPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerReferencesPopupService: CustomerReferencesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.customerReferencesPopupService
                    .open(CustomerReferencesDialogComponent as Component, params['id']);
            } else {
                this.customerReferencesPopupService
                    .open(CustomerReferencesDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
