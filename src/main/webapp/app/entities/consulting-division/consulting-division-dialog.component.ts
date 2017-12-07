import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ConsultingDivision } from './consulting-division.model';
import { ConsultingDivisionPopupService } from './consulting-division-popup.service';
import { ConsultingDivisionService } from './consulting-division.service';

@Component({
    selector: 'jhi-consulting-division-dialog',
    templateUrl: './consulting-division-dialog.component.html'
})
export class ConsultingDivisionDialogComponent implements OnInit {

    consultingDivision: ConsultingDivision;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private consultingDivisionService: ConsultingDivisionService,
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
        if (this.consultingDivision.id !== undefined) {
            this.subscribeToSaveResponse(
                this.consultingDivisionService.update(this.consultingDivision));
        } else {
            this.subscribeToSaveResponse(
                this.consultingDivisionService.create(this.consultingDivision));
        }
    }

    private subscribeToSaveResponse(result: Observable<ConsultingDivision>) {
        result.subscribe((res: ConsultingDivision) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: ConsultingDivision) {
        this.eventManager.broadcast({ name: 'consultingDivisionListModification', content: 'OK'});
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
    selector: 'jhi-consulting-division-popup',
    template: ''
})
export class ConsultingDivisionPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private consultingDivisionPopupService: ConsultingDivisionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.consultingDivisionPopupService
                    .open(ConsultingDivisionDialogComponent as Component, params['id']);
            } else {
                this.consultingDivisionPopupService
                    .open(ConsultingDivisionDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
