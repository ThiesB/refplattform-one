import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Languages } from './languages.model';
import { LanguagesPopupService } from './languages-popup.service';
import { LanguagesService } from './languages.service';

@Component({
    selector: 'jhi-languages-dialog',
    templateUrl: './languages-dialog.component.html'
})
export class LanguagesDialogComponent implements OnInit {

    languages: Languages;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private languagesService: LanguagesService,
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
        if (this.languages.id !== undefined) {
            this.subscribeToSaveResponse(
                this.languagesService.update(this.languages));
        } else {
            this.subscribeToSaveResponse(
                this.languagesService.create(this.languages));
        }
    }

    private subscribeToSaveResponse(result: Observable<Languages>) {
        result.subscribe((res: Languages) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Languages) {
        this.eventManager.broadcast({ name: 'languagesListModification', content: 'OK'});
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
    selector: 'jhi-languages-popup',
    template: ''
})
export class LanguagesPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private languagesPopupService: LanguagesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.languagesPopupService
                    .open(LanguagesDialogComponent as Component, params['id']);
            } else {
                this.languagesPopupService
                    .open(LanguagesDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
