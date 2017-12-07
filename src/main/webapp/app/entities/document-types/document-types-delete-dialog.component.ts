import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DocumentTypes } from './document-types.model';
import { DocumentTypesPopupService } from './document-types-popup.service';
import { DocumentTypesService } from './document-types.service';

@Component({
    selector: 'jhi-document-types-delete-dialog',
    templateUrl: './document-types-delete-dialog.component.html'
})
export class DocumentTypesDeleteDialogComponent {

    documentTypes: DocumentTypes;

    constructor(
        private documentTypesService: DocumentTypesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.documentTypesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'documentTypesListModification',
                content: 'Deleted an documentTypes'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-document-types-delete-popup',
    template: ''
})
export class DocumentTypesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private documentTypesPopupService: DocumentTypesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.documentTypesPopupService
                .open(DocumentTypesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
