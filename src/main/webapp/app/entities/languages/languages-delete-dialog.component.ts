import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Languages } from './languages.model';
import { LanguagesPopupService } from './languages-popup.service';
import { LanguagesService } from './languages.service';

@Component({
    selector: 'jhi-languages-delete-dialog',
    templateUrl: './languages-delete-dialog.component.html'
})
export class LanguagesDeleteDialogComponent {

    languages: Languages;

    constructor(
        private languagesService: LanguagesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.languagesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'languagesListModification',
                content: 'Deleted an languages'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-languages-delete-popup',
    template: ''
})
export class LanguagesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private languagesPopupService: LanguagesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.languagesPopupService
                .open(LanguagesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
