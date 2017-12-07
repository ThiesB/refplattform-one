import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { DocumentTypes } from './document-types.model';
import { DocumentTypesService } from './document-types.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-document-types',
    templateUrl: './document-types.component.html'
})
export class DocumentTypesComponent implements OnInit, OnDestroy {
documentTypes: DocumentTypes[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private documentTypesService: DocumentTypesService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.documentTypesService.query().subscribe(
            (res: ResponseWrapper) => {
                this.documentTypes = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInDocumentTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: DocumentTypes) {
        return item.id;
    }
    registerChangeInDocumentTypes() {
        this.eventSubscriber = this.eventManager.subscribe('documentTypesListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
