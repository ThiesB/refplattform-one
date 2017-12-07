import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Languages } from './languages.model';
import { LanguagesService } from './languages.service';

@Component({
    selector: 'jhi-languages-detail',
    templateUrl: './languages-detail.component.html'
})
export class LanguagesDetailComponent implements OnInit, OnDestroy {

    languages: Languages;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private languagesService: LanguagesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLanguages();
    }

    load(id) {
        this.languagesService.find(id).subscribe((languages) => {
            this.languages = languages;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLanguages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'languagesListModification',
            (response) => this.load(this.languages.id)
        );
    }
}
