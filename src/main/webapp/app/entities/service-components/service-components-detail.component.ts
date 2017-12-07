import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { ServiceComponents } from './service-components.model';
import { ServiceComponentsService } from './service-components.service';

@Component({
    selector: 'jhi-service-components-detail',
    templateUrl: './service-components-detail.component.html'
})
export class ServiceComponentsDetailComponent implements OnInit, OnDestroy {

    serviceComponents: ServiceComponents;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private serviceComponentsService: ServiceComponentsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInServiceComponents();
    }

    load(id) {
        this.serviceComponentsService.find(id).subscribe((serviceComponents) => {
            this.serviceComponents = serviceComponents;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInServiceComponents() {
        this.eventSubscriber = this.eventManager.subscribe(
            'serviceComponentsListModification',
            (response) => this.load(this.serviceComponents.id)
        );
    }
}
