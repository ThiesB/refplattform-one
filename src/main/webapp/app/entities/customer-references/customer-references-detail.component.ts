import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerReferences } from './customer-references.model';
import { CustomerReferencesService } from './customer-references.service';

@Component({
    selector: 'jhi-customer-references-detail',
    templateUrl: './customer-references-detail.component.html'
})
export class CustomerReferencesDetailComponent implements OnInit, OnDestroy {

    customerReferences: CustomerReferences;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private customerReferencesService: CustomerReferencesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCustomerReferences();
    }

    load(id) {
        this.customerReferencesService.find(id).subscribe((customerReferences) => {
            this.customerReferences = customerReferences;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCustomerReferences() {
        this.eventSubscriber = this.eventManager.subscribe(
            'customerReferencesListModification',
            (response) => this.load(this.customerReferences.id)
        );
    }
}
