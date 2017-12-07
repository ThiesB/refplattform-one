import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { DocumentTypes } from './document-types.model';
import { DocumentTypesPopupService } from './document-types-popup.service';
import { DocumentTypesService } from './document-types.service';

@Component({
    selector: 'jhi-document-types-dialog',
    templateUrl: './document-types-dialog.component.html'
})
export class DocumentTypesDialogComponent implements OnInit {

    documentTypes: DocumentTypes;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private documentTypesService: DocumentTypesService,
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
        if (this.documentTypes.id !== undefined) {
            this.subscribeToSaveResponse(
                this.documentTypesService.update(this.documentTypes));
        } else {
            this.subscribeToSaveResponse(
                this.documentTypesService.create(this.documentTypes));
        }
    }

    private subscribeToSaveResponse(result: Observable<DocumentTypes>) {
        result.subscribe((res: DocumentTypes) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: DocumentTypes) {
        this.eventManager.broadcast({ name: 'documentTypesListModification', content: 'OK'});
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
    selector: 'jhi-document-types-popup',
    template: ''
})
export class DocumentTypesPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private documentTypesPopupService: DocumentTypesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.documentTypesPopupService
                    .open(DocumentTypesDialogComponent as Component, params['id']);
            } else {
                this.documentTypesPopupService
                    .open(DocumentTypesDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
