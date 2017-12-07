import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Industries } from './industries.model';
import { IndustriesService } from './industries.service';

@Component({
    selector: 'jhi-industries-detail',
    templateUrl: './industries-detail.component.html'
})
export class IndustriesDetailComponent implements OnInit, OnDestroy {

    industries: Industries;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private industriesService: IndustriesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInIndustries();
    }

    load(id) {
        this.industriesService.find(id).subscribe((industries) => {
            this.industries = industries;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInIndustries() {
        this.eventSubscriber = this.eventManager.subscribe(
            'industriesListModification',
            (response) => this.load(this.industries.id)
        );
    }
}
