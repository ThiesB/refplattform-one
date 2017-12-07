import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Industries } from './industries.model';
import { IndustriesPopupService } from './industries-popup.service';
import { IndustriesService } from './industries.service';

@Component({
    selector: 'jhi-industries-dialog',
    templateUrl: './industries-dialog.component.html'
})
export class IndustriesDialogComponent implements OnInit {

    industries: Industries;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private industriesService: IndustriesService,
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
        if (this.industries.id !== undefined) {
            this.subscribeToSaveResponse(
                this.industriesService.update(this.industries));
        } else {
            this.subscribeToSaveResponse(
                this.industriesService.create(this.industries));
        }
    }

    private subscribeToSaveResponse(result: Observable<Industries>) {
        result.subscribe((res: Industries) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Industries) {
        this.eventManager.broadcast({ name: 'industriesListModification', content: 'OK'});
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
    selector: 'jhi-industries-popup',
    template: ''
})
export class IndustriesPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private industriesPopupService: IndustriesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.industriesPopupService
                    .open(IndustriesDialogComponent as Component, params['id']);
            } else {
                this.industriesPopupService
                    .open(IndustriesDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
