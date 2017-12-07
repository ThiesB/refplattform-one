import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { DocumentTypes } from './document-types.model';
import { DocumentTypesService } from './document-types.service';

@Component({
    selector: 'jhi-document-types-detail',
    templateUrl: './document-types-detail.component.html'
})
export class DocumentTypesDetailComponent implements OnInit, OnDestroy {

    documentTypes: DocumentTypes;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private documentTypesService: DocumentTypesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDocumentTypes();
    }

    load(id) {
        this.documentTypesService.find(id).subscribe((documentTypes) => {
            this.documentTypes = documentTypes;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDocumentTypes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'documentTypesListModification',
            (response) => this.load(this.documentTypes.id)
        );
    }
}
